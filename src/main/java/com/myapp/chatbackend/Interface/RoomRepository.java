package com.myapp.chatbackend.Interface;

import com.myapp.chatbackend.Entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity,Long> {
    Optional<RoomEntity> findAllByFromIdAndToId(Long fromId, Long toId);
}
