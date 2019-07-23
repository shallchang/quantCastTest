package com.quantcast.activecookie.repo;

import com.quantcast.activecookie.domain.Cookie;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


public class InMemoryMapCookieRepositoryTest {

    private CookieRepository repository;

    @Before
    public void setUp(){
        repository = new InMemoryMapCookieRepository();
    }

    @Test
    public void testAddCookie(){
        Cookie cookie = new Cookie("Cookie", LocalDate.of(2019, 07, 22));
        boolean added = repository.add(cookie);
        assertTrue(added);
    }

    @Test
    public void testFindMostActiveCookie(){
        Cookie cookie1 = new Cookie("Cookie1", LocalDate.of(2019, 7, 22));
        Cookie cookie2 = new Cookie("Cookie1", LocalDate.of(2019, 7, 22));
        Cookie cookie3 = new Cookie("Cookie2", LocalDate.of(2019, 7, 22));
        repository.add(cookie1);
        repository.add(cookie2);
        repository.add(cookie3);

        List<String> mostActiveCookies = repository.findMostActiveCookie(LocalDate.of(2019, 7, 22)).get();
        List<String> expectedCookies = new ArrayList<>();
        expectedCookies.add("Cookie1");

        assertTrue(mostActiveCookies.containsAll(expectedCookies));
    }
}
