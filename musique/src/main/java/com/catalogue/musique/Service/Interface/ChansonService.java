package com.catalogue.musique.Service.Interface;

import com.catalogue.musique.DTO.ChansonDTO;

import java.util.List;

public interface ChansonService {

    List<ChansonDTO> getAllChansons();

    ChansonDTO addChanson(ChansonDTO chansonDTO);

    ChansonDTO updateChanson(String id, ChansonDTO chansonDTO);

    void deleteChanson(String id);
    public void deleteChansonByTitre(String titre);
}
