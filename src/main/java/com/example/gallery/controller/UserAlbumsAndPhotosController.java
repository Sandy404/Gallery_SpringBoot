package com.example.gallery.controller;

import com.example.gallery.model.aggregation.AlbumAggregationResult;
import com.example.gallery.model.aggregation.PhotoAggregationResult;
import com.example.gallery.repository.AlbumRepository;
import com.example.gallery.repository.PhotoRepository;
import com.example.gallery.service.UserAlbumAndPhotosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class UserAlbumsAndPhotosController {
    private final PhotoRepository photoRepository;
    private final AlbumRepository albumRepository;
    private final UserAlbumAndPhotosService userAlbumAndPhotosService;


    @GetMapping("/albums/{albumId}/photos")
    public Mono<ResponseEntity<Flux<PhotoAggregationResult>>> getPhotoByAlbumId(@PathVariable Integer albumId) {
        return userAlbumAndPhotosService.getPhotoByAlbumId(albumId);
    }

    @GetMapping("/users/{userId}/albums")
    public Mono<ResponseEntity<Flux<AlbumAggregationResult>>> getAlbumByUserId(@PathVariable Integer userId) {
        return userAlbumAndPhotosService.getAlbumByUserId(userId);
    }


}
