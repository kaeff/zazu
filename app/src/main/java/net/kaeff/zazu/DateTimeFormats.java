package net.kaeff.zazu;

import com.google.common.base.Function;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeFormats {
    public static final DateTimeFormatter SHORT_TIME = DateTimeFormat.shortTime();
    public static final PeriodFormatter PERIOD_FORMAT = new PeriodFormatterBuilder()
            .printZeroAlways()
            .appendHours()
            .appendSeparatorIfFieldsAfter(":")
            .minimumPrintedDigits(2)
            .appendMinutes()
            .toFormatter();
    public static final Function<String, LocalTime> PARSE_TIME = new Function<String, LocalTime>() {
        @Override
        public LocalTime apply(String input) {
            return parseTime(input);
        }
    };

    public static LocalTime parseTime(String str) {
        LocalTime currentTime = LocalTime.now();
        try {
            return SHORT_TIME.parseLocalTime(str);
        } catch (Exception e) {
            try {
                return LocalTime.parse(str);
            } catch (Exception e1) {
//                Log.e(DateTimeFormats.class.getCanonicalName(), "Can't understand this time value: " + str, e);
            }
        }
        return currentTime;
    }
}
