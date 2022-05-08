package com.myapp.chatbackend.User;

import com.myapp.chatbackend.User.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query("select u from UserEntity u where u.username= ?1")
    UserEntity findByUsername(String username);
}
