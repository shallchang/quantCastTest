package com.quantcast.activecookie.repo;

import com.quantcast.activecookie.domain.Cookie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryMapCookieRepository implements CookieRepository {

    private final Map<LocalDate, List<String>> cookieRepository;

    public InMemoryMapCookieRepository(){
        cookieRepository = new HashMap<>();
    }

    @Override
    public boolean add(Cookie cookie) {
        LocalDate date = cookie.getDate();
        if(cookieRepository.containsKey(cookie.getDate())){
            List<String> ids = cookieRepository.get(date);
            ids.add(cookie.getId());
            cookieRepository.put(date, ids);
        }else{
            List<String> ids = new ArrayList<>();
            ids.add(cookie.getId());
            cookieRepository.put(date, ids);
        }

        return true;
    }

    @Override
    public Optional<List<String>> findMostActiveCookie(LocalDate date) {
        return Optional.ofNullable(cookieRepository.get(date));
    }
}