package com.example.gallery.service;

import com.example.gallery.advice.exception.IdNotFoundException;
import com.example.gallery.logic.GenerateId;
import com.example.gallery.logic.UpdateCrudLogic;
import com.example.gallery.model.Photo;
import com.example.gallery.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final UpdateCrudLogic updateCrudLogic;
    private final GenerateId generateId;

    public Mono<ResponseEntity<Photo>> createPhoto(Photo photo) {
        return generateId.generatePhotoId().flatMap(newId -> {
            photo.setId(newId);
            return photoRepository.save(photo).map(savedPhoto -> ResponseEntity.status(HttpStatus.CREATED).body(savedPhoto));
        });
    }

    public Mono<ResponseEntity<Flux<Photo>>> getAllPhotos() {
        return Mono.just(ResponseEntity.ok(photoRepository.findAll()));
    }

    public Mono<ResponseEntity<Photo>> getPhotoById(Integer id) {
        return photoRepository.findById(id).map(photo -> ResponseEntity.ok(photo)).switchIfEmpty(Mono.error(new IdNotFoundException("Photo with id " + id + " not found")));
    }

    public Flux<Photo> findPhotosByAlbumId(Integer albumId) {
        return photoRepository.findByAlbumId(albumId).switchIfEmpty(Flux.error(new IdNotFoundException("No photos found for album with id " + albumId)));
    }

    public Mono<ResponseEntity<Photo>> updatePhoto(Integer id, Photo photo) {
        return photoRepository.findById(id).flatMap(existingPhoto -> {
            updateCrudLogic.updatePhoto(existingPhoto, photo);
            return photoRepository.save(existingPhoto).map(updatedPhoto -> ResponseEntity.ok(updatedPhoto));
        }).switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }


    public Mono<String> deletePhoto(Integer id) {
        return photoRepository.existsById(id).flatMap(exists -> {
            if (exists) {
                return photoRepository.deleteById(id).then(Mono.just("Photo with id " + id + " is deleted"));
            } else {
                return Mono.error(new IdNotFoundException("Photo with id " + id + " not found"));
            }
        });
    }


}