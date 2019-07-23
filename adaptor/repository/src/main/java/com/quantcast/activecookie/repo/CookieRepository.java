package com.quantcast.activecookie.repo;

import java.util.Date;
import java.util.Optional;

public interface CookieRepository {

    public Optional<String> findMostActiveCookie(Date date);

}