package com.quantcast.activecookie.controller;

import com.quantcast.activecookie.domain.Cookie;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelCookieReader implements CookieReader{

    public List<Cookie> read(final File filePath) {
        List<Cookie> cookies = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(filePath);
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();

                String id = row.getCell(0).getStringCellValue();
                String date = row.getCell(1).getStringCellValue().substring(0, 10);

                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                cookies.add(new Cookie(id, localDate));
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cookies;
    }
}
