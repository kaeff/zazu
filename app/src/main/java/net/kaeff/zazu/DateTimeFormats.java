package net.kaeff.zazu;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeFormats {
    public static final DateTimeFormatter SHORT_TIME = DateTimeFormat.shortTime();
}
