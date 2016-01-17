package net.kaeff.zazu.model;

import org.joda.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(suppressConstructorProperties = true)
public class TimeLog {

    LocalDateTime dateTime;
    TimeLogType type;
}
