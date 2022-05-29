package com.myapp.chatbackend.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Notifications {
        private Long id;
        private Long fromId;
        private String fromName;
        private String message;


    public Notifications(Long id, Long fromId, String fromName, String message) {
        this.id = id;
        this.fromId = fromId;
        this.fromName = fromName;
        this.message = message;
    }
}
