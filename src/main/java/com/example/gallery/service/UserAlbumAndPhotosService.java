package com.example.gallery.service;

import com.example.gallery.advice.exception.IdNotFoundException;
import com.example.gallery.model.aggregation.AlbumAggregationResult;
import com.example.gallery.model.aggregation.PhotoAggregationResult;
import com.example.gallery.repository.AlbumRepository;
import com.example.gallery.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserAlbumAndPhotosService {
    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;

    public Mono<ResponseEntity<Flux<PhotoAggregationResult>>> getPhotoByAlbumId(@PathVariable Integer albumId) {
        return photoRepository.aggregatePhotos(albumId)
                .collectList()
                .flatMap(photoList -> {
                    if (photoList.isEmpty()) {
                        return Mono.error(new IdNotFoundException("No photos found for album with id " + albumId));
                    } else {
                        return Mono.just(ResponseEntity.ok(Flux.fromIterable(photoList)));
                    }
                })
                ;
    }

    public Mono<ResponseEntity<Flux<AlbumAggregationResult>>> getAlbumByUserId(@PathVariable Integer userId) {
        return albumRepository.aggregateAlbums(userId)
                .collectList()
                .flatMap(photoList -> {
                    if (photoList.isEmpty()) {
                        return Mono.error(new IdNotFoundException("No photos found for album with id " + userId));
                    } else {
                        return Mono.just(ResponseEntity.ok(Flux.fromIterable(photoList)));
                    }
                })
                ;
    }
}

