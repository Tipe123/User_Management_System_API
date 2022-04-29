package com.user.mngt.system.repository;


import com.user.mngt.system.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//it's an interface with functions that can be performed on the database table UserEntity
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
