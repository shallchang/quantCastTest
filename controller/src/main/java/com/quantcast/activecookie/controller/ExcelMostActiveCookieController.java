package com.quantcast.activecookie.controller;

import com.quantcast.activecookie.domain.Cookie;
import com.quantcast.activecookie.repo.CookieRepository;
import com.quantcast.activecookie.repo.InMemoryMapCookieRepository;
import com.quantcast.activecookie.usecase.AddCookies;
import com.quantcast.activecookie.usecase.FindMostActiveCookie;

import java.io.File;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ExcelMostActiveCookieController {

    private CookieRepository repository;
    private AddCookies addCookiesUseCase;
    private FindMostActiveCookie findMostActiveCookieUseCase;
    private ExcelCookieReader reader;

    public ExcelMostActiveCookieController(){
        repository = new InMemoryMapCookieRepository();
        addCookiesUseCase = new AddCookies(repository);
        findMostActiveCookieUseCase = new FindMostActiveCookie(repository);
        reader = new ExcelCookieReader();
    }

    public List<String> findMostActiveCookieByDate(File filePath, LocalDate date){
        List<Cookie> cookies = reader.read(filePath);
        addCookiesUseCase.add(cookies);
        Optional<List<String>> mostActiveCookies = findMostActiveCookieUseCase.findByDate(date);
        if(mostActiveCookies.isPresent()){
            return mostActiveCookies.get();
        }
        throw new NoCookieFoundException("No Cookie Found in Date Passed");
    }
}
