package com.example.gallery.model.aggregation;

import com.example.gallery.model.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PhotoAggregationResult {
    private String id;
    private List<Photo> photos;

}