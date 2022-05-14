package com.myapp.chatbackend.Interface;

import com.myapp.chatbackend.Entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<RoomEntity,Long> {

    Optional<RoomEntity> findByIds(Long fromId, Long toId);

}
