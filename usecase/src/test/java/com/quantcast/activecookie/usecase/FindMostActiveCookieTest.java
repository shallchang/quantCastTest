package com.quantcast.activecookie.usecase;

import com.quantcast.activecookie.repo.CookieRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindMostActiveCookieTest {

    @Mock
    private CookieRepository repository;

    private FindMostActiveCookie findMostActiveCookie;

    @Before
    public void setUp(){
        findMostActiveCookie = new FindMostActiveCookie(repository);
    }

    @Test
    public void testFindMostActiveCookie() {
        LocalDate date = LocalDate.of(2019, 7, 22);
        List<String> ids = new ArrayList<>();

        when(repository.findMostActiveCookie(date)).thenReturn(Optional.of(ids));

        List<String> retrievedIds = findMostActiveCookie.findByDate(date).get();
        assertEquals(retrievedIds, ids);
    }
}
