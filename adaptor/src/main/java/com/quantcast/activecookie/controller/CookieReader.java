package com.quantcast.activecookie.controller;

import com.quantcast.activecookie.domain.Cookie;

import java.io.File;
import java.util.List;

public interface CookieReader {

    List<Cookie> read(File filePath);
}
