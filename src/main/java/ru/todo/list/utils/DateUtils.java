package ru.todo.list.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtils {

    private static final String DEFAULT_FORMAT = "dd.MM.yyyy HH:mm:ss";

    public static String getCurrentDate(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);
        LocalDateTime now = LocalDateTime.now();

        return dateTimeFormatter.format(now);
    }

}
