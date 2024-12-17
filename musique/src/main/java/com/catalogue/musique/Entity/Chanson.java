package com.catalogue.musique.Entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "chansons")
public class Chanson {
    @Id
    private String id;
    private String titre;
    private int duree;
    private int trackNumber;
    private String albumId;
}
