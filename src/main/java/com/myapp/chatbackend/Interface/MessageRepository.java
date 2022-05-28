package com.myapp.chatbackend.Interface;

import com.myapp.chatbackend.Entity.DeliveryStatus;
import com.myapp.chatbackend.Entity.MsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<MsgEntity,Long> {

    long countByFromIdAndToIdAndStatus(Long fromId, Long toId, DeliveryStatus status);
    List<MsgEntity> findByFromIdAndToId(Long fromId, Long toId);
    List<MsgEntity> findByMsgId(String msgId);
}
