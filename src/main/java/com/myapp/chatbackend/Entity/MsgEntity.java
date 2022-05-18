package com.myapp.chatbackend.Entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;



@Entity
@Table(name = "chat_messages_table")
@NoArgsConstructor @Setter @Getter @ToString
public class MsgEntity {
        @Id
        private Long id;
        private Long msgId;
        private Long fromId;
        private Long toId;
        private DeliveryStatus status;
        private String fromName;
        private String toName;
        private String message;
        private Date date;


        public MsgEntity(Long msgId, Long fromId, Long toId, DeliveryStatus status, String fromName, String toName, String message, Date date) {
                this.msgId = msgId;
                this.fromId = fromId;
                this.toId = toId;
                this.status = status;
                this.fromName = fromName;
                this.toName = toName;
                this.message = message;
                this.date = date;
        }
}
