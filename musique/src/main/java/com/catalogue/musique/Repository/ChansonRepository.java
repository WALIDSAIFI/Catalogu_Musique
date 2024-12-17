package com.catalogue.musique.Repository;

import com.catalogue.musique.Entity.Chanson;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ChansonRepository extends MongoRepository<Chanson, String> {

    List<Chanson> findByTitreContaining(String titre);
    List<Chanson> findByAlbumId(String albumId);
    Optional<Chanson> findByTrackNumber(int trackNumber);
    List<Chanson> findByDuree(int duree);
}
