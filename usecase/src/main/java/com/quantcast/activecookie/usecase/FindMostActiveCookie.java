package com.quantcast.activecookie.usecase;

import com.quantcast.activecookie.repo.CookieRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public final class FindMostActiveCookie {

    private final CookieRepository repository;

    public Optional<List<String>> findByDate(LocalDate date) {
        return repository.findMostActiveCookie(date);
    }
}
