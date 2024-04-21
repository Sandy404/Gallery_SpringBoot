
package com.example.gallery.controller;

import com.example.gallery.model.Photo;
import com.example.gallery.service.PhotoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/photos")
@RequiredArgsConstructor
public class PhotoController {
    private final PhotoService photoService;


    @PostMapping
    public Mono<ResponseEntity<Photo>> createPhoto(@Valid @RequestBody Photo photo) {

        return photoService.createPhoto(photo);

    }

    @GetMapping
    public Mono<ResponseEntity<Flux<Photo>>> getAllPhotos() {
        return photoService.getAllPhotos();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Photo>> getPhotoById(@PathVariable Integer id) {
        return photoService.getPhotoById(id);
    }

    @GetMapping("/album/{albumId}")
    public Flux<Photo> findPhotosByAlbumId(@PathVariable Integer albumId) {
        return photoService.findPhotosByAlbumId(albumId);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Photo>> updatePhoto(@PathVariable Integer id, @Valid @RequestBody Photo photo) {
        return photoService.updatePhoto(id, photo);
    }

    @DeleteMapping("/{id}")
    public Mono<String> deletePhoto(@PathVariable Integer id) {
        return photoService.deletePhoto(id);
    }
}
