package com.quantcast.activecookie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Cookie {

    private String id;
    private LocalDate date;
}
