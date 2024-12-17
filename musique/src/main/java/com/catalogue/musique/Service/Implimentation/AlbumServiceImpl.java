package com.catalogue.musique.Service.Implimentation;

import com.catalogue.musique.DTO.AlbumDTO;
import com.catalogue.musique.Entity.Album;
import com.catalogue.musique.Mapper.AlbumMapper;
import com.catalogue.musique.Repository.AlbumRepository;
import com.catalogue.musique.Service.Interface.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private AlbumMapper albumMapper;

    @Override
    public List<AlbumDTO> getAllAlbums() {
        List<Album> albums = albumRepository.findAll();
        return albumMapper.toDTO(albums);
    }

    @Override
    public AlbumDTO getAlbumById(String id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));
        return albumMapper.toDTO(album);
    }

    @Override
    public AlbumDTO addAlbum(AlbumDTO albumDTO) {
        Album album = albumMapper.toEntity(albumDTO);
        album = albumRepository.save(album);
        return albumMapper.toDTO(album);
    }

    @Override
    public AlbumDTO updateAlbum(String id, AlbumDTO albumDTO) {
        Album existingAlbum = albumRepository.findById(id).orElseThrow(() -> new RuntimeException("Album not found"));
        existingAlbum.setTitre(albumDTO.getTitre());
        existingAlbum.setArtiste(albumDTO.getArtiste());
        existingAlbum.setAnnee(albumDTO.getAnnee());
        Album updatedAlbum = albumRepository.save(existingAlbum);
        return albumMapper.toDTO(updatedAlbum);
    }

    @Override
    public void deleteAlbum(String id) {
        albumRepository.deleteById(id);
    }
}
