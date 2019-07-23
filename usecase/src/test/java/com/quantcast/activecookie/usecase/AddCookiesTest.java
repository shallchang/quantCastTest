package com.quantcast.activecookie.usecase;

import com.quantcast.activecookie.domain.Cookie;
import com.quantcast.activecookie.repo.CookieRepository;
import com.quantcast.activecookie.repo.InMemoryMapCookieRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddCookiesTest {

    private CookieRepository repository;

    private AddCookies addCookies;

    @Before
    public void setUp(){
        repository = new InMemoryMapCookieRepository();
        addCookies = new AddCookies(repository);
    }

    @Test
    public void testFindDailyMostActiveCookies(){
        List<Cookie> cookies = new ArrayList<>();
        Cookie cookie1 = new Cookie("Cookie1", LocalDate.of(2019, 7, 22));
        Cookie cookie2 = new Cookie("Cookie1", LocalDate.of(2019, 7, 22));
        Cookie cookie3 = new Cookie("Cookie2", LocalDate.of(2019, 7, 22));
        Cookie cookie4 = new Cookie("Cookie2", LocalDate.of(2019, 7, 22));
        Cookie cookie5 = new Cookie("Cookie3", LocalDate.of(2019, 7, 22));

        cookies.add(cookie1);
        cookies.add(cookie2);
        cookies.add(cookie3);
        cookies.add(cookie4);
        cookies.add(cookie5);

        List<Cookie> mostActiveCookies = addCookies.findDailyMostActiveCookies(cookies);
        List<Cookie> expectedResult = new ArrayList<>();
        expectedResult.add(cookie1);
        expectedResult.add(cookie2);

        assertEquals(2, mostActiveCookies.size());
        assertTrue(mostActiveCookies.containsAll(expectedResult));
    }

    @Test
    public void testAddCookies(){
        List<Cookie> cookies = new ArrayList<>();
        Cookie cookie1 = new Cookie("Cookie1", LocalDate.of(2019, 7, 22));
        Cookie cookie2 = new Cookie("Cookie1", LocalDate.of(2019, 7, 22));
        Cookie cookie3 = new Cookie("Cookie2", LocalDate.of(2019, 7, 22));
        Cookie cookie4 = new Cookie("Cookie2", LocalDate.of(2019, 7, 22));
        Cookie cookie5 = new Cookie("Cookie3", LocalDate.of(2019, 7, 22));

        cookies.add(cookie1);
        cookies.add(cookie2);
        cookies.add(cookie3);
        cookies.add(cookie4);
        cookies.add(cookie5);

        boolean added = addCookies.add(cookies);

        assertTrue(added);
    }
}
