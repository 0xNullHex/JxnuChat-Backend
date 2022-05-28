package com.myapp.chatbackend.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "chat_rooms_table")
@NoArgsConstructor
@Setter
@Getter
@ToString
public class RoomEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long id;
        private String msgId;
        private Long fromId;
        private Long toId;

    public RoomEntity(String msgId, Long fromId, Long toId) {
        this.msgId = msgId;
        this.fromId = fromId;
        this.toId = toId;
    }
}


