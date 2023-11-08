package com.cs.javademo.java.dateTime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTime {
    public static void main(String[] args) {
        //default time zone
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Date input = new Date();
        Instant instant = input.toInstant();
        ZonedDateTime zdt = instant.atZone(defaultZoneId);
        LocalDate date = zdt.toLocalDate();
        //Displaying LocalDate and Date
        System.out.println("Date is: " + input);
        System.out.println("LocalDate is: " + date);

        //creating the instance of LocalDate using the day, month, year info
        LocalDate localDate1 = LocalDate.now(defaultZoneId);
        //local date + atStartOfDay() + default time zone + toInstant() = Date
        Date date1 = Date.from(localDate1.atStartOfDay(defaultZoneId).toInstant());
        //Displaying LocalDate and Date
        System.out.println("LocalDate is: " + localDate1);
        System.out.println("Date is: " + date1);

        LocalDate localDate =  LocalDate.now().plusDays(1).minus(1, ChronoUnit.MONTHS);
        System.out.println(localDate);
        DayOfWeek day = LocalDate.parse("2016-08-12").getDayOfWeek();
        System.out.println(day);
        System.out.println(LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30")));
        int five = Period.between(LocalDate.parse("2016-08-12"), LocalDate.parse("2018-08-12")).getDays();
        LocalTime initialTime = LocalTime.of(6, 30, 0);
        LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
        long thirty = ChronoUnit.SECONDS.between(initialTime, finalTime);
        System.out.println( LocalDateTime.of(2015, Month.JANUARY, 25, 6, 30).format(DateTimeFormatter.ISO_DATE));
        System.out.println(LocalDateTime.now().atZone(ZoneId.of("CET")));
        System.out.println(LocalTime.now().truncatedTo(ChronoUnit.HOURS));
         day = DayOfWeek.WEDNESDAY;
        System.out.println(
                switch (day) {
                    case MONDAY, FRIDAY, SUNDAY -> 6;
                    case TUESDAY                -> 7;
                    case THURSDAY, SATURDAY     -> 8;
                    case WEDNESDAY              -> 9;
                    default -> throw new IllegalStateException("Invalid day: " + day);
                });

    }
}
