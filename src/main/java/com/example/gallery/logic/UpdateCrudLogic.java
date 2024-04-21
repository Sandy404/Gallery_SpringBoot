package com.example.gallery.logic;

import com.example.gallery.model.Album;
import com.example.gallery.model.Photo;
import org.springframework.stereotype.Component;

@Component
public class UpdateCrudLogic {
    public void updateAlbum(Album existingAlbum, Album album) {
        if (album.getUserId() != null) {
            existingAlbum.setUserId(album.getUserId());
        }
        if (album.getTitle() != null) {
            existingAlbum.setTitle(album.getTitle());
        }
    }

    public void updatePhoto(Photo existingPhoto, Photo photo) {
        if (photo.getAlbumId() != null) {
            existingPhoto.setAlbumId(photo.getAlbumId());
        }
        if (photo.getTitle() != null) {
            existingPhoto.setTitle(photo.getTitle());
        }
        if (photo.getUrl() != null) {
            existingPhoto.setUrl(photo.getUrl());
        }
        if (photo.getThumbnailUrl() != null) {
            existingPhoto.setThumbnailUrl(photo.getThumbnailUrl());
        }
    }
}
