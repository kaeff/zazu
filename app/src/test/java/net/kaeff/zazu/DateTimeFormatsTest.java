package net.kaeff.zazu;

import org.joda.time.Duration;
import org.joda.time.Period;
import org.junit.Test;

import static net.kaeff.zazu.DateTimeFormats.PERIOD_FORMAT;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DateTimeFormatsTest {

    @Test
    public void testPeriodFormat() throws Exception {
        assertThat(PERIOD_FORMAT.print(Period.minutes(0)), is("0:00"));
        assertThat(PERIOD_FORMAT.print(Period.minutes(45)), is("0:45"));
        assertThat(PERIOD_FORMAT.print(Period.hours(1)), is("1:00"));
        assertThat(PERIOD_FORMAT.print(Duration.standardMinutes(65).toPeriod()), is("1:05"));
        assertThat(PERIOD_FORMAT.print(Duration.standardMinutes(75).toPeriod()), is("1:15"));
        assertThat(PERIOD_FORMAT.print(Period.hours(10)), is("10:00"));

    }
}