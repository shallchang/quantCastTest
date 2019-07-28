package com.quantcast.activecookie.controller;

import com.quantcast.activecookie.controller.exception.FilePathInvalidException;
import com.quantcast.activecookie.domain.Cookie;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ExcelCookieReaderTest {

    private CookieReader reader;

    @Before
    public void setUp(){
        reader = new ExcelCookieReader();
    }

    @Test
    public void testReadCookieFromExcel(){
        File file = new File(getClass().getClassLoader().getResource("cookie_file.xlsx").getFile());

        List<Cookie> cookies = reader.read(file);

        List<Cookie> expectedCookies = new ArrayList<>();
        expectedCookies.add(new Cookie("AtY0laUfhglK3lC7", LocalDate.of(2018, 12, 9)));
        expectedCookies.add(new Cookie("SAZuXPGUrfbcn5UA", LocalDate.of(2018, 12, 9)));

        assertTrue(cookies.containsAll(expectedCookies));
    }

    @Test(expected = FilePathInvalidException.class)
    public void testShouldThrowFilePathInvalidException(){
        File file = new File("unknown.xlsx");

        reader.read(file);
    }

}
