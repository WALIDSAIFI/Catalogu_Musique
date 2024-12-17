package com.catalogue.musique.Mapper;

import com.catalogue.musique.DTO.ChansonDTO;
import com.catalogue.musique.Entity.Chanson;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T13:46:17+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ChansonMapperImpl implements ChansonMapper {

    @Override
    public ChansonDTO toDto(Chanson chanson) {
        if ( chanson == null ) {
            return null;
        }

        ChansonDTO chansonDTO = new ChansonDTO();

        chansonDTO.setId( chanson.getId() );
        chansonDTO.setTitre( chanson.getTitre() );
        chansonDTO.setDuree( chanson.getDuree() );
        chansonDTO.setTrackNumber( chanson.getTrackNumber() );
        chansonDTO.setAlbumId( chanson.getAlbumId() );

        return chansonDTO;
    }

    @Override
    public List<ChansonDTO> toDto(List<Chanson> chansons) {
        if ( chansons == null ) {
            return null;
        }

        List<ChansonDTO> list = new ArrayList<ChansonDTO>( chansons.size() );
        for ( Chanson chanson : chansons ) {
            list.add( toDto( chanson ) );
        }

        return list;
    }

    @Override
    public Chanson toEntity(ChansonDTO chansonDTO) {
        if ( chansonDTO == null ) {
            return null;
        }

        Chanson chanson = new Chanson();

        chanson.setId( chansonDTO.getId() );
        chanson.setTitre( chansonDTO.getTitre() );
        chanson.setDuree( chansonDTO.getDuree() );
        chanson.setTrackNumber( chansonDTO.getTrackNumber() );
        chanson.setAlbumId( chansonDTO.getAlbumId() );

        return chanson;
    }

    @Override
    public List<Chanson> toEntity(List<ChansonDTO> chansonDTOs) {
        if ( chansonDTOs == null ) {
            return null;
        }

        List<Chanson> list = new ArrayList<Chanson>( chansonDTOs.size() );
        for ( ChansonDTO chansonDTO : chansonDTOs ) {
            list.add( toEntity( chansonDTO ) );
        }

        return list;
    }
}
