package net.kaeff.zazu.model;

import net.kaeff.zazu.DateTimeFormats;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(suppressConstructorProperties = true)
public class TimeLog {

    LocalDateTime dateTime;
    TimeLogType type;

    public String toHumanReadable() {
        return DateTimeFormat.shortDate().print(dateTime) + ": " + type.asHumanNoun() + " " + DateTimeFormats.SHORT_TIME.print(dateTime);
    }

}
