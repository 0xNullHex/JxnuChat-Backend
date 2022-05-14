package com.myapp.chatbackend.Controller;

import com.myapp.chatbackend.Entity.MsgEntity;
import com.myapp.chatbackend.Entity.Notifications;
import com.myapp.chatbackend.Service.MessageService;
import com.myapp.chatbackend.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class MessagingController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private MessageService messageService;
    @Autowired
    private RoomService roomService;

    @MessageMapping("/chat")
    public void processMessage(@Payload MsgEntity message) {
        var chatId = roomService.getMsgId(message.getFromId(), message.getToId(), true);
        message.setMsgId(Long.valueOf(chatId.get()));

        MsgEntity saved = messageService.save(message);
        messagingTemplate.convertAndSendToUser(
                Long.toString(message.getToId()),"/queue/messages",
                new Notifications(
                        saved.getId(),
                        saved.getFromId(),
                        saved.getFromName()));
    }

    @GetMapping("/messages/{fromId}/{toId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable String fromId,
            @PathVariable String toId) {

        return ResponseEntity
                .ok(messageService.countNewMessages(Long.valueOf(fromId), Long.valueOf(toId)));
    }

    @GetMapping("/messages/{fromId}/{toId}")
    public ResponseEntity<?> findChatMessages ( @PathVariable String fromId,
                                                @PathVariable String toId) {
        return ResponseEntity
                .ok(messageService.findMessages(Long.valueOf(fromId), Long.valueOf(toId)));
    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable String id) {
        return ResponseEntity
                .ok(messageService.findById(Long.valueOf(id)));
    }
}
