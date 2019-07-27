package com.quantcast.activecookie;

import com.quantcast.activecookie.controller.ExcelMostActiveCookieController;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[]args){
        ExcelMostActiveCookieController controller = new ExcelMostActiveCookieController();
        List<String> mostActiveCookies = controller.findMostActiveCookieByDate(new File(args[0]), LocalDate.parse(args[1]));

        mostActiveCookies.forEach(System.out::println);
    }
}
