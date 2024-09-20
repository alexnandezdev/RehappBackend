package com.siete.rehapp.repository;

import com.siete.rehapp.entity.PhysiotherapistEntity;
import com.siete.rehapp.entity.UserEntity;
import com.siete.rehapp.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository <UserEntity, Long> {

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByIdentificationNumberAndUserType(String identificationNumber, UserType userType);

    @Query("SELECT p FROM PhysiotherapistEntity p WHERE p.user.userId = :userId")
    Optional<PhysiotherapistEntity> findPhysiotherapistByUserId(@Param("userId") Long userId);

}
