package com.example.gallery.model.aggregation;

import com.example.gallery.model.Album;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AlbumAggregationResult {
    private String id;
    private List<Album> albums;
}
