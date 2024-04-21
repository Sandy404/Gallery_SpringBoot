package com.example.gallery.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "photos")
public class Photo {
    @Id
    private Integer id;
    private Integer albumId;
    @Size(min=3,max=30)
    private String title;
    private String url;
    private String thumbnailUrl;
    private LocalDateTime updatedAt;

    public void setUpdatedDateAt(LocalDateTime now) {
    }
}
