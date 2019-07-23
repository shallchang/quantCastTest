package com.quantcast.activecookie.repo;

import com.quantcast.activecookie.domain.Cookie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CookieRepository {

    boolean add(Cookie cookie);
    Optional<List<String>> findMostActiveCookie(LocalDate date);
}