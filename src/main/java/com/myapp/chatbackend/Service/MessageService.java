package com.myapp.chatbackend.Service;

import com.myapp.chatbackend.Entity.DeliveryStatus;
import com.myapp.chatbackend.Entity.MsgEntity;
import com.myapp.chatbackend.Interface.MessageRepository;
import org.hibernate.Criteria;
import org.hibernate.query.Query;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {


        private final MessageRepository messageRepository;
        private final RoomService roomService;

    @Autowired
    public MessageService(MessageRepository messageRepository, RoomService roomService) {
        this.messageRepository = messageRepository;
        this.roomService = roomService;
    }

    public MsgEntity save(MsgEntity message) {
            message.setStatus(DeliveryStatus.READ);
            messageRepository.save(message);
            return message;
        }

        public long countNewMessages(Long fromId, Long toId) {
            return messageRepository.countByFromIdAndToIdAndStatus(fromId, toId, DeliveryStatus.READ);
        }

        public List<MsgEntity> findMessages(Long fromId, Long toId) {
            Optional<String> msgId = roomService.getMsgId(fromId, toId, false);

            List<MsgEntity> messages = msgId.map(id -> messageRepository.findByMsgId(Long.valueOf(id))).orElse(new ArrayList<>());

            if(messages.size() > 0) {
                updateStatuses(fromId, toId, DeliveryStatus.SENT);
            }

            return messages;
        }

        public MsgEntity findById(Long id) {
            return messageRepository
                    .findById(id)
                    .map(message -> {
                        message.setStatus(DeliveryStatus.SENT);
                        return messageRepository.save(message);
                    })
                    .orElseThrow(() ->
                            new IllegalStateException("message not found (" + id + ")"));
        }

        public void updateStatuses(Long fromId, Long toId, DeliveryStatus status) {

            MsgEntity msgEntity= messageRepository.findByFromIdAndToId(fromId,toId);
            msgEntity.setStatus(status);
            messageRepository.save(msgEntity);

        }

}
