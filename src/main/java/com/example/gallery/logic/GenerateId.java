package com.example.gallery.logic;

import com.example.gallery.model.Album;
import com.example.gallery.model.Photo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Component
public class GenerateId {
    private final ReactiveMongoTemplate mongoTemplate;

    public Mono<Integer> generateAlbumId() {
        return mongoTemplate.findAll(Album.class)
                .map(Album::getId)
                .reduce(0, Math::max)
                .map(maxId -> maxId + 1);
    }

    public Mono<Integer> generatePhotoId() {
        return mongoTemplate.findAll(Photo.class)
                .map(Photo::getId)
                .reduce(0, Math::max)
                .map(maxId -> maxId + 1);
    }
}
