package com.catalogue.musique.Mapper;

import com.catalogue.musique.DTO.AlbumDTO;
import com.catalogue.musique.Entity.Album;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ChansonMapper.class})
public interface AlbumMapper {
    AlbumMapper INSTANCE = Mappers.getMapper(AlbumMapper.class);

    AlbumDTO toDTO(Album album);

    Album toEntity(AlbumDTO albumDTO);
    List<AlbumDTO> toDTO(List<Album> albums);
}
