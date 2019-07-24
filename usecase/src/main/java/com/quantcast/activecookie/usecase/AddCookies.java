package com.quantcast.activecookie.usecase;

import com.quantcast.activecookie.domain.Cookie;
import com.quantcast.activecookie.repo.CookieRepository;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class AddCookies {

    private final CookieRepository repository;

    public boolean add(List<Cookie> cookies){
        if(cookies.isEmpty()){
            return false;
        }
        LocalDate indexDate = cookies.get(0).getDate();
        List<Cookie> dailyCookies = new ArrayList<>();
        for(Cookie cookie : cookies){
            if(cookie.getDate().equals(indexDate)){
                dailyCookies.add(cookie);
            }else{
                List<Cookie> mostActiveCookies = findDailyMostActiveCookies(dailyCookies);
                mostActiveCookies.forEach(c -> repository.add(c));
                indexDate = cookie.getDate();
                dailyCookies.clear();
                dailyCookies.add(cookie);
            }
        }
        if(!dailyCookies.isEmpty()){
            List<Cookie> mostActiveCookies = findDailyMostActiveCookies(dailyCookies);
            mostActiveCookies.forEach(c -> repository.add(c));
        }

        return true;
    }

    public List<Cookie> findDailyMostActiveCookies(List<Cookie> cookies){
        LocalDate date = cookies.get(0).getDate();
        Map<String, Integer> cookieOccurrences = new HashMap<>();
        int maxOccurence = 0;

        for(Cookie cookie:cookies){
            if(!cookie.getDate().equals(date)){
                return null;
            }
            String id = cookie.getId();
            if(cookieOccurrences.containsKey(id)){
                int occurrence = cookieOccurrences.get(id);
                occurrence += 1;
                cookieOccurrences.put(id, occurrence);

                if(occurrence > maxOccurence){
                    maxOccurence = occurrence;
                }
            }else{
                cookieOccurrences.put(id, 1);
                if(maxOccurence == 0){
                    maxOccurence = 1;
                }
            }
        }

        //find all cookies with max occurrence
        List<Cookie> mostActiveCookies = new ArrayList<>();
        Iterator it = cookieOccurrences.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry cookie = (Map.Entry)it.next();
            if((int)cookie.getValue() == maxOccurence){
                mostActiveCookies.add(new Cookie((String)cookie.getKey(), date));
            }
        }

        return mostActiveCookies;
    }

}
