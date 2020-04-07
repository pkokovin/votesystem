package ru.kokovin.votesystem.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;

//https://stackoverflow.com/questions/32792000/how-can-i-mock-java-time-localdate-now
//https://sebastiankoltun-blog.com/index.php/2018/05/21/mocking-time-in-java-8-mockito/

@Component
public class DateTimeUtil {


    static Clock clock;

    static ZoneId zoneId;

    @Autowired
    public DateTimeUtil(Clock clock, ZoneId zoneId) {
        DateTimeUtil.clock = clock;
        DateTimeUtil.zoneId = zoneId;
    }

    public static LocalDateTime current() {
        return LocalDateTime.now(getClock());
    }

    public static void useFixedClockAt(LocalDateTime date) {
        clock = Clock.fixed(date.atZone(zoneId).toInstant(), zoneId);
    }
    private static Clock getClock() {
        return clock;
    }

}
