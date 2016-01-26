package net.kaeff.zazu.model;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

import net.kaeff.zazu.DateTimeFormats;

import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.joda.time.Minutes;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor(suppressConstructorProperties = true, access = AccessLevel.PRIVATE)
public class DayLog {
    LocalTime begin;
    LocalTime end;
    Duration breaks;

    public static DayLog parse(String beginText, String endText, String breaksText) {
        LocalTime begin = Optional.fromNullable(Strings.emptyToNull(beginText)).transform(DateTimeFormats.PARSE_TIME).orNull();
        LocalTime end = Optional.fromNullable(Strings.emptyToNull(endText)).transform(DateTimeFormats.PARSE_TIME).orNull();
        Duration breaks = Duration.standardMinutes(0);
        if (!Strings.isNullOrEmpty(breaksText)) {
            breaks = Duration.standardMinutes(new BigDecimal(breaksText).multiply(BigDecimal.valueOf(60)).longValue());
        }
        return new DayLog(begin, end, breaks);
    }

    public Duration asWorkHours() {
        if (begin != null && end != null) {
            return Minutes.minutesBetween(begin, end).toStandardDuration().minus(breaks);
        } else {
            return Duration.standardMinutes(0);
        }

    }
}
