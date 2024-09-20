package com.siete.rehapp.controller;

import com.siete.rehapp.dto.VideoDTO;
import com.siete.rehapp.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/rehapp/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/module/{moduleId}")
    public ResponseEntity<List<VideoDTO>> getVideosByModuleId(@PathVariable Long moduleId) {
        log.info("Fectching videos for module id: {}", moduleId);
        List<VideoDTO> videos = videoService.getVideosByModuleId(moduleId);
        log.info("Videos fetched successfully for module id: {}", moduleId);
        return ResponseEntity.ok(videos);
    }

}
