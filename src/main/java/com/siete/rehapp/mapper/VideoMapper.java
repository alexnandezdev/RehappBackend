package com.siete.rehapp.mapper;

import com.siete.rehapp.dto.VideoDTO;
import com.siete.rehapp.entity.VideoEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VideoMapper {

    VideoDTO toVideoDTO(VideoEntity videoEntity);
    VideoEntity toVideoEntity(VideoDTO videoDTO);

}
