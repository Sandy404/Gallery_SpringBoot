package com.example.gallery.service;

import com.example.gallery.advice.exception.IdNotFoundException;
import com.example.gallery.logic.GenerateId;
import com.example.gallery.logic.UpdateCrudLogic;
import com.example.gallery.model.Album;
import com.example.gallery.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final UpdateCrudLogic updateCrudLogic;
    private final GenerateId generateId;

    public Mono<ResponseEntity<Album>> createAlbum(Album album) {
        return generateId.generateAlbumId()
                .flatMap(newId -> {
                    album.setId(newId);
                    return albumRepository.save(album)
                            .map(savedAlbum -> ResponseEntity.status(HttpStatus.CREATED).body(savedAlbum));
                });
    }

    public Mono<ResponseEntity<Flux<Album>>> getAllAlbums() {
        return Mono.just(ResponseEntity.ok(albumRepository.findAll()));
    }

    public Mono<ResponseEntity<Album>> getAlbumById(Integer id) {
        return albumRepository.findById(id)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.error(new IdNotFoundException("Album with id " + id + " not found")));
    }

    public Flux<Album> getAlbumsByUserId(Integer userId) {
        return albumRepository.findByUserId(userId)
                .switchIfEmpty(Flux.error(new IdNotFoundException("No albums found for user with id " + userId)));
    }

    public Mono<ResponseEntity<Album>> updateAlbum(Integer id, Album album) {
        return albumRepository.findById(id)
                .flatMap(existingAlbum -> {
                    updateCrudLogic.updateAlbum(existingAlbum, album);
                    return albumRepository.save(existingAlbum)
                            .map(updatedAlbum -> ResponseEntity.ok(updatedAlbum));
                })
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }


    public Mono<String> deleteAlbum(Integer id) {
        return albumRepository.existsById(id)
                .flatMap(exists -> {
                    if (exists) {
                        return albumRepository.deleteById(id)
                                .then(Mono.just("Album with id " + id + " is deleted"));
                    } else {
                        return Mono.error(new IdNotFoundException("Album with id " + id + " not found"));
                    }
                });
    }

    public Flux<Album> getAlbumByUserId_DescOrderDate(Integer userId) {
        return albumRepository.findByUserIdOrderByUpdatedAtDesc(userId)
                .switchIfEmpty(Flux.error(new IdNotFoundException("No albums found for user with id " + userId)));
    }

}