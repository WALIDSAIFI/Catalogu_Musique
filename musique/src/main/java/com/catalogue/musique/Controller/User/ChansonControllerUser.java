package com.catalogue.musique.Controller.User;
import com.catalogue.musique.DTO.ChansonDTO;
import com.catalogue.musique.Service.Interface.ChansonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user/chanson")
@Validated
public class ChansonControllerUser {

    @Autowired
    private ChansonService chansonService;

    @GetMapping
    public ResponseEntity<List<ChansonDTO>> getAllChansons(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<ChansonDTO> chansons = chansonService.getAllChansons();
        return new ResponseEntity<>(chansons, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addChanson(@Valid @RequestBody ChansonDTO chansonDTO) {
        chansonService.addChanson(chansonDTO);
        String message = "Chanson ajoutée avec succès.";
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChanson(
            @PathVariable("id") String id,
            @Valid @RequestBody ChansonDTO chansonDTO) {
        chansonService.updateChanson(id, chansonDTO);
        String message = "Chanson mise à jour avec succès.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{titre}")
    public ResponseEntity<String> deleteChansonByTitre(@PathVariable("titre") String titre) {
        chansonService.deleteChansonByTitre(titre);
        String message = "Chanson(s) supprimée(s) avec succès.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
