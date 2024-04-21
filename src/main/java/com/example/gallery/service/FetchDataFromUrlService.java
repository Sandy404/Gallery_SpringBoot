package com.example.gallery.service;

import com.example.gallery.logic.SetUserRoleLogic;
import com.example.gallery.model.Album;
import com.example.gallery.model.Photo;
import com.example.gallery.model.user.User;
import com.example.gallery.repository.AlbumRepository;
import com.example.gallery.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FetchDataFromUrlService {
    private final WebClient webClient;
    private final AlbumRepository albumRepository;
    private final PhotoRepository photoRepository;
    private final SetUserRoleLogic setUserRoleLogic;

    public Flux<Album> fetchAlbumsFromURL() {
        return webClient.get().uri("/albums")
                .retrieve()
                .bodyToFlux(Album.class)
                .map(album -> {
                    album.setUpdatedAt(LocalDateTime.now());
                    return album;
                })
                .flatMap(albumRepository::save);
    }

    public Flux<Photo> fetchPhotosFromURL() {
        return webClient.get().uri("/photos")
                .retrieve()
                .bodyToFlux(Photo.class)
                .map(photo -> {
                    photo.setUpdatedAt(LocalDateTime.now());
                    return photo;
                })
                .flatMap(photoRepository::save);
    }

    public Flux<User> fetchUsersFromURL() {
        return webClient.get().uri("/users")
                .retrieve()
                .bodyToFlux(User.class)
                .index()
                .flatMap(setUserRoleLogic::setUserRoleAndSave);
    }
}
