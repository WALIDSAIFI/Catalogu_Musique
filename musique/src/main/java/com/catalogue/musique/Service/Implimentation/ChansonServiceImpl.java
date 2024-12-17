package com.catalogue.musique.Service.Implimentation;
import com.catalogue.musique.DTO.ChansonDTO;
import com.catalogue.musique.Entity.Album;
import com.catalogue.musique.Entity.Chanson;
import com.catalogue.musique.Exception.ChansonNotFoundException;
import com.catalogue.musique.Mapper.ChansonMapper;
import com.catalogue.musique.Repository.AlbumRepository;
import com.catalogue.musique.Repository.ChansonRepository;
import com.catalogue.musique.Service.Interface.AlbumService;
import com.catalogue.musique.Service.Interface.ChansonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChansonServiceImpl implements ChansonService {

    @Autowired
    private ChansonRepository chansonRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private ChansonMapper chansonMapper;

    @Autowired
    private AlbumService albumService;


    @Override
    public List<ChansonDTO> getAllChansons() {
        List<Chanson> chansons = chansonRepository.findAll();
        return chansonMapper.toDto(chansons);
    }

    @Override
    public ChansonDTO addChanson(ChansonDTO chansonDTO) {

        Chanson chanson = chansonMapper.toEntity(chansonDTO);

        chanson = chansonRepository.save(chanson);

        Album album = albumRepository.findById(chansonDTO.getAlbumId())
                .orElseThrow(() -> new RuntimeException("Album not found"));

        if (album.getChansons() == null) {
            album.setChansons(new ArrayList<>());
        }
        album.getChansons().add(chanson);

        albumRepository.save(album);


        return chansonMapper.toDto(chanson);
    }


    @Override
    public ChansonDTO updateChanson(String id, ChansonDTO chansonDTO) {
        Chanson chanson = chansonRepository.findById(id).orElseThrow(() -> new ChansonNotFoundException("Chanson notrouver !"));
        chanson.setTitre(chansonDTO.getTitre());
        chanson.setDuree(chansonDTO.getDuree());
        chanson.setTrackNumber(chansonDTO.getTrackNumber());
        chanson = chansonRepository.save(chanson);
        return chansonMapper.toDto(chanson);
    }

    @Override
    public void deleteChanson(String id) {
        chansonRepository.deleteById(id);
    }

    @Override
    public void deleteChansonByTitre(String titre) {
        List<Chanson> chansons = chansonRepository.findByTitreContaining(titre);
        if (chansons.isEmpty()) {
            throw new ChansonNotFoundException("Chanson avec le titre '" + titre + "' non trouvée !");
        }
        chansonRepository.deleteAll(chansons);
    }
}
