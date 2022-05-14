package com.myapp.chatbackend.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "chat_rooms_table")
public class RoomEntity {
        @Id
        private Long id;
        private String msgId;
        private Long fromId;
        private Long toId;

    public RoomEntity() {
    }

    public RoomEntity(String msgId, Long fromId, Long toId) {
        this.msgId = msgId;
        this.fromId = fromId;
        this.toId = toId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "id=" + id +
                ", msgId=" + msgId +
                ", fromId=" + fromId +
                ", toId=" + toId +
                '}';
    }
}


