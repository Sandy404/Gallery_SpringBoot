package com.example.gallery.repository;

import com.example.gallery.model.Photo;
import com.example.gallery.model.aggregation.PhotoAggregationResult;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface PhotoRepository extends ReactiveMongoRepository<Photo, Integer> {

    Flux<Photo> findByAlbumId(Integer albumId);

    Flux<Object> findByAlbumIdInOrderByUpdatedAtDesc(List<Integer> albumIds);

    Flux<Photo> findByAlbumIdOrderByUpdatedAtDesc(Integer albumId);

    @Aggregation(pipeline = {
            "{ $match: { albumId: ?0 } }", // ?0 refers to the first parameter of the method
            "{ $sort: { updatedDate: -1 } }",
            "{ $group: { _id: $albumId, photos: { $push: $$ROOT } } }"
    })
    Flux<PhotoAggregationResult> aggregatePhotos(Integer albumId);
}
