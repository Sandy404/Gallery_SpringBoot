package com.example.gallery.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
class Company {
    private String name;
    private String catchPhrase;
    private String bs;
}