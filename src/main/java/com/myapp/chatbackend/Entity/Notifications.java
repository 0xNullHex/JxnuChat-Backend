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


    public Notifications(Long id, Long fromId, String fromName) {
        this.id = id;
        this.fromId = fromId;
        this.fromName = fromName;
    }
}
