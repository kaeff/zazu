package net.kaeff.zazu;

import org.joda.time.LocalDateTime;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(suppressConstructorProperties = true)
public class TimeLog {
    public static enum Type {
        MORNING;
    }

    LocalDateTime dateTime;
    Type type;
}
