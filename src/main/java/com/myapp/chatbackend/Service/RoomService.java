package com.myapp.chatbackend.Service;

import com.myapp.chatbackend.Entity.RoomEntity;
import com.myapp.chatbackend.Interface.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class RoomService{


    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Optional<String> getMsgId(
            Long fromId, Long toId, boolean createIfNotExist) {

        return roomRepository
                .findByFromIdAndToId(fromId, toId)
                .map(RoomEntity::getMsgId)
                .or(() -> {
                    if(!createIfNotExist) {
                        return  Optional.empty();
                    }
                    String msgId = String.format("%s_%s", toId, fromId);

                    RoomEntity to_from = new RoomEntity(msgId,fromId,toId);

                    RoomEntity from_to = new RoomEntity(msgId,toId,fromId);
                    roomRepository.save(to_from);
                    roomRepository.save(from_to);

                    return Optional.of(msgId);
                });
    }

}
