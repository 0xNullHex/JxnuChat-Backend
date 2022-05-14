package com.myapp.chatbackend.Interface;

import com.myapp.chatbackend.Entity.DeliveryStatus;
import com.myapp.chatbackend.Entity.MsgEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<MsgEntity,Long> {

    long countByPartnersIdAndStatus(Long fromId, Long toId, DeliveryStatus status);

    @Query("select m from MsgEntity m where m.fromId= ?1 and m.toId= ?2")
    MsgEntity findByPartnersIds(Long fromId, Long toId);
    List<MsgEntity> findByMsgId(String msgId);
}
