package com.quantcast.activecookie.usecase;

import com.quantcast.activecookie.repo.CookieRepository;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.Optional;

@AllArgsConstructor
public final class FindMostActiveCookie {

    private final CookieRepository repository;

    public Optional<String> findMostActiveCookie(Date date) {
        return repository.findMostActiveCookie(date);
    }
}
