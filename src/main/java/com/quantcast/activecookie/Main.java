package com.quantcast.activecookie;

import com.quantcast.activecookie.controller.ExcelMostActiveCookieController;
import com.quantcast.activecookie.controller.NoCookieFoundException;
import com.quantcast.activecookie.controller.exception.FilePathInvalidException;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

public class Main {

    public static void main(String[]args){
        ExcelMostActiveCookieController controller = new ExcelMostActiveCookieController();

        try{
            File file = new File(args[0]);
            List<String> mostActiveCookies = controller.findMostActiveCookieByDate(file, LocalDate.parse(args[1]));
            mostActiveCookies.forEach(System.out::println);
        }catch (DateTimeParseException e){
            System.out.println(String.format("DateTime %s passed is invalid", args[1]));
        }catch (FilePathInvalidException e){
            System.out.println(e.getMessage());
        }catch (NoCookieFoundException e){
            System.out.println(e.getMessage());
        }
    }
}
