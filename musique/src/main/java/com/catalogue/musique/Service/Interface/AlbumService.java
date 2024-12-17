package com.catalogue.musique.Service.Interface;

import com.catalogue.musique.DTO.AlbumDTO;

import java.util.List;

public interface AlbumService {
    List<AlbumDTO> getAllAlbums();
    AlbumDTO getAlbumById(String id);
    AlbumDTO addAlbum(AlbumDTO albumDTO);
    AlbumDTO updateAlbum(String id, AlbumDTO albumDTO);
    void deleteAlbum(String id);
}
