package com.siete.rehapp.service;

import com.siete.rehapp.dto.VideoDTO;
import com.siete.rehapp.entity.VideoEntity;
import com.siete.rehapp.exception.VideoException;
import com.siete.rehapp.mapper.VideoMapper;
import com.siete.rehapp.repository.VideoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoMapper videoMapper;

    public List<VideoDTO> getVideosByModuleId(Long moduleId){
        try {
            List<VideoEntity> videos = videoRepository.findByModule_ModuleId(moduleId);
            return videos.stream()
                    .map(videoMapper::toVideoDTO)
                    .collect(Collectors.toList());
        }catch (Exception e){
            log.error("Error getting videos with moduleId: {}", moduleId);
            throw new VideoException("Error fetching videos with moduleId" + moduleId + ": " + e.getMessage(), e);
        }
    }

}
