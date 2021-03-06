package com.myapp.chatbackend.Service;

import com.myapp.chatbackend.Entity.DeliveryStatus;
import com.myapp.chatbackend.Entity.MsgEntity;
import com.myapp.chatbackend.Interface.MessageRepository;
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
            message.setStatus(DeliveryStatus.SENT);
            messageRepository.save(message);
            return message;
        }

        public long countNewMessages(Long fromId, Long toId) {
            return messageRepository.countByFromIdAndToIdAndStatus(fromId, toId, DeliveryStatus.SENT);
        }

        public List<MsgEntity> findMessages(Long fromId, Long toId) {
            Optional<String> msgId = roomService.getMsgId(fromId, toId, false);

            List<MsgEntity> messages = msgId.map(messageRepository::findByMsgId).orElse(new ArrayList<>());

            if(messages.size() > 0) {
                updateStatuses(fromId, toId, DeliveryStatus.READ);
            }

            return messages;
        }

        public MsgEntity findById(Long id) {
            return messageRepository
                    .findById((id))
                    .map(message -> {
                        message.setStatus(DeliveryStatus.READ);
                        return messageRepository.save(message);
                    })
                    .orElseThrow(() ->
                            new IllegalStateException("message not found (" + id + ")"));
        }

        public void updateStatuses(Long fromId, Long toId, DeliveryStatus status) {

            List<MsgEntity> msgEntities= messageRepository.findByFromIdAndToId(fromId,toId);
            msgEntities.forEach(msgEntity -> msgEntity.setStatus(status));
            messageRepository.saveAll(msgEntities);

        }

}
