package com.catalogue.musique.Mapper;

import com.catalogue.musique.DTO.ChansonDTO;
import com.catalogue.musique.Entity.Chanson;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ChansonMapper {

    ChansonMapper INSTANCE = Mappers.getMapper(ChansonMapper.class);

    ChansonDTO toDto(Chanson chanson);

    List<ChansonDTO> toDto(List<Chanson> chansons);

    Chanson toEntity(ChansonDTO chansonDTO);

    List<Chanson> toEntity(List<ChansonDTO> chansonDTOs);
}
