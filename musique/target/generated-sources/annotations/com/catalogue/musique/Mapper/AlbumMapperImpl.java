package com.catalogue.musique.Mapper;

import com.catalogue.musique.DTO.AlbumDTO;
import com.catalogue.musique.Entity.Album;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T13:46:17+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class AlbumMapperImpl implements AlbumMapper {

    @Autowired
    private ChansonMapper chansonMapper;

    @Override
    public AlbumDTO toDTO(Album album) {
        if ( album == null ) {
            return null;
        }

        AlbumDTO albumDTO = new AlbumDTO();

        albumDTO.setId( album.getId() );
        albumDTO.setTitre( album.getTitre() );
        albumDTO.setArtiste( album.getArtiste() );
        albumDTO.setAnnee( album.getAnnee() );
        albumDTO.setChansons( chansonMapper.toDto( album.getChansons() ) );

        return albumDTO;
    }

    @Override
    public Album toEntity(AlbumDTO albumDTO) {
        if ( albumDTO == null ) {
            return null;
        }

        Album album = new Album();

        album.setId( albumDTO.getId() );
        album.setTitre( albumDTO.getTitre() );
        album.setArtiste( albumDTO.getArtiste() );
        album.setAnnee( albumDTO.getAnnee() );
        album.setChansons( chansonMapper.toEntity( albumDTO.getChansons() ) );

        return album;
    }

    @Override
    public List<AlbumDTO> toDTO(List<Album> albums) {
        if ( albums == null ) {
            return null;
        }

        List<AlbumDTO> list = new ArrayList<AlbumDTO>( albums.size() );
        for ( Album album : albums ) {
            list.add( toDTO( album ) );
        }

        return list;
    }
}
