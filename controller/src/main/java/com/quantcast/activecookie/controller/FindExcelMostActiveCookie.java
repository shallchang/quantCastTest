package com.quantcast.activecookie.controller;

import com.quantcast.activecookie.domain.Cookie;
import com.quantcast.activecookie.repo.CookieRepository;
import com.quantcast.activecookie.repo.InMemoryMapCookieRepository;
import com.quantcast.activecookie.usecase.AddCookies;
import com.quantcast.activecookie.usecase.FindMostActiveCookie;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class FindExcelMostActiveCookie {

    private CookieRepository repository;
    private AddCookies addCookiesUseCase;
    private FindMostActiveCookie findMostActiveCookieUseCase;
    private ExcelCookieReader reader;

    public FindExcelMostActiveCookie(){
        repository = new InMemoryMapCookieRepository();
        addCookiesUseCase = new AddCookies(repository);
        findMostActiveCookieUseCase = new FindMostActiveCookie(repository);
        reader = new ExcelCookieReader();
    }

    public void findMostActiveCookie(){
        List<Cookie> cookies = reader.read(new File("/users/xiaohanzhang/Documents/test.xlsx"));
        addCookiesUseCase.add(cookies);
        List<String> mostActiveCookies = findMostActiveCookieUseCase.findByDate(LocalDate.of(2018, 12, 9)).get();

        mostActiveCookies.forEach(e->System.out.println(e));
    }

    public static void main(String[]args){
        FindExcelMostActiveCookie findMostActiveCookie = new FindExcelMostActiveCookie();

        findMostActiveCookie.findMostActiveCookie();
    }
}
