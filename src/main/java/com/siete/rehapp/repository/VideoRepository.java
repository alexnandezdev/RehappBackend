package com.siete.rehapp.repository;

import com.siete.rehapp.entity.VideoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoRepository extends JpaRepository<VideoEntity, Long> {
    List<VideoEntity> findByModule_ModuleId(Long moduleId);
}