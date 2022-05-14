package com.myapp.chatbackend.Entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;



@Entity
@Table
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

        public MsgEntity() {
        }

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Long getMsgId() {
                return msgId;
        }

        public void setMsgId(Long msgId) {
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

        public DeliveryStatus getStatus() {
                return status;
        }

        public void setStatus(DeliveryStatus status) {
                this.status = status;
        }

        public String getFromName() {
                return fromName;
        }

        public void setFromName(String fromName) {
                this.fromName = fromName;
        }

        public String getToName() {
                return toName;
        }

        public void setToName(String toName) {
                this.toName = toName;
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public Date getDate() {
                return date;
        }

        public void setDate(Date date) {
                this.date = date;
        }

        @Override
        public String toString() {
                return "MsgEntity{" +
                        "id=" + id +
                        ", msgId=" + msgId +
                        ", fromId=" + fromId +
                        ", toId=" + toId +
                        ", status=" + status +
                        ", fromName='" + fromName + '\'' +
                        ", toName='" + toName + '\'' +
                        ", message='" + message + '\'' +
                        ", date=" + date +
                        '}';
        }
}
