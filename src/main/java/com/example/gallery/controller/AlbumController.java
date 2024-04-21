package com.example.gallery.controller;


import com.example.gallery.model.Album;
import com.example.gallery.service.AlbumService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;


    @PostMapping
    public Mono<ResponseEntity<Album>> createAlbum(@Valid @RequestBody Album album) {

        return albumService.createAlbum(album);
    }

    @GetMapping
    public Mono<ResponseEntity<Flux<Album>>> getAllAlbums() {
        return albumService.getAllAlbums();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Album>> getAlbumById(@PathVariable Integer id) {
        return albumService.getAlbumById(id);
    }

    @GetMapping("/user/{userId}")
    public Flux<Album> getAlbumsByUserId(@PathVariable Integer userId) {
        return albumService.getAlbumsByUserId(userId);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Album>> updateAlbum(@PathVariable Integer id, @Valid @RequestBody Album album) {
        return albumService.updateAlbum(id, album);
    }

    @DeleteMapping("/{id}")
    public Mono<String> deleteAlbum(@PathVariable Integer id) {
        return albumService.deleteAlbum(id);
    }

}
