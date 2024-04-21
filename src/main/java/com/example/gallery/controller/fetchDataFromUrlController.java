package com.example.gallery.controller;

import com.example.gallery.model.Album;
import com.example.gallery.model.Photo;
import com.example.gallery.model.user.User;
import com.example.gallery.service.FetchDataFromUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class fetchDataFromUrlController {
    private final FetchDataFromUrlService dataService;

    @GetMapping("/fetch-albums")
    public Flux<Album> fetchAlbumsFromUrl() {
        return dataService.fetchAlbumsFromURL();
    }

    @GetMapping("/fetch-photos")
    public Flux<Photo> fetchPhotosFromUrl() {
        return dataService.fetchPhotosFromURL();
    }

    @GetMapping("/fetch-users")
    public Flux<User> fetchUsersFromUrl() {
        return dataService.fetchUsersFromURL();
    }
}
