package com.catalogue.musique.Repository;

import com.catalogue.musique.Entity.Album;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AlbumRepository extends MongoRepository<Album, String> {
    public Album findByTitre(String name);

    Album findByArtiste(String artiste);
}
