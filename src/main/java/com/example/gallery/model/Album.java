package com.example.gallery.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Document(collection="albums")
public class Album {
    @Id
    private Integer id;
    private Integer userId;
    @Size(min=3,max=30)
    private String title;
    private LocalDateTime updatedAt;



}