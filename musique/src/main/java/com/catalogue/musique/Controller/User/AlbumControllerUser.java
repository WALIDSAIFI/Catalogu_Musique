package com.catalogue.musique.Controller.User;

import com.catalogue.musique.DTO.AlbumDTO;
import com.catalogue.musique.Service.Interface.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user/album")
@Validated
public class AlbumControllerUser{

    @Autowired
    private AlbumService albumService;

    // Récupérer tous les albums
    @GetMapping("/")
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        List<AlbumDTO> albums = albumService.getAllAlbums();
        return new ResponseEntity<>(albums, HttpStatus.OK);
    }

    // Récupérer un album par son ID
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable("id") String id) {
        AlbumDTO album = albumService.getAlbumById(id);
        return new ResponseEntity<>(album, HttpStatus.OK);
    }

    // Ajouter un nouvel album
    @PostMapping("/")
    public ResponseEntity<String> addAlbum(@Valid @RequestBody AlbumDTO albumDTO) {
        albumService.addAlbum(albumDTO);
        String message = "Album ajouté avec succès.";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    // Mettre à jour un album existant
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAlbum(
            @PathVariable("id") String id,
            @Valid @RequestBody AlbumDTO albumDTO) {
        albumService.updateAlbum(id, albumDTO);
        String message = "Album mis à jour avec succès.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // Supprimer un album
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlbum(@PathVariable("id") String id) {
        albumService.deleteAlbum(id);
        String message = "Album supprimé avec succès.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
