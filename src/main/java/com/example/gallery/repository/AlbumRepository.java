package com.example.gallery.repository;

import com.example.gallery.model.Album;
import com.example.gallery.model.aggregation.AlbumAggregationResult;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface AlbumRepository extends ReactiveMongoRepository<Album, Integer> {

    Flux<Album> findByUserId(Integer userId);

    Flux<Album> findByUserIdOrderByUpdatedAtDesc(Integer userId);

    @Aggregation(pipeline = {
            "{ $match: { userId: ?0 } }", // ?0 refers to the first parameter of the method
            "{ $sort: { updatedDate: -1 } }",
            "{ $group: { _id: $userId, albums: { $push: $$ROOT } } }"
    })
    Flux<AlbumAggregationResult> aggregateAlbums(Integer userId);
}