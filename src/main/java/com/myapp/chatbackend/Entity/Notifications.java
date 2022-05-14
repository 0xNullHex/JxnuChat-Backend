package com.myapp.chatbackend.Entity;

public class Notifications {
        private Long id;
        private Long fromId;
        private String fromName;


    public Notifications(Long id, Long fromId, String fromName) {
        this.id = id;
        this.fromId = fromId;
        this.fromName = fromName;
    }

    public Notifications() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
}
