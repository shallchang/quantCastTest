package com.quantcast.activecookie.repo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ExcelCookieRepository implements CookieRepository {

    private final Map<Date, String> cookieRepository = new HashMap<>();

    @Override
    public Optional<String> findMostActiveCookie(Date date) {
        return Optional.ofNullable(cookieRepository.get(date));
    }
}
