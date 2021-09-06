package br.com.example.springshop.utils;

import br.com.example.springshop.security.JwtUtil;
import org.springframework.stereotype.Component;

import java.util.Date;

public class DateUtils {
    public static Date formatExpirationDateToken() {
        Date date = new Date(System.currentTimeMillis() + JwtUtil.JWT_EXPIRATION_MINUTES * 1000);
        return date;
    }
}
