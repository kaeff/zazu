import net.kaeff.zazu.model.DayLog;

import org.joda.time.Duration;
import org.joda.time.LocalTime;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DayLogTest {

    @Test
    public void testHappyPath() throws Exception {
        DayLog dayLog = DayLog.parse("09:00", "18:00", "1");

        assertThat(dayLog.getBegin(), is(new LocalTime(9, 0)));
        assertThat(dayLog.getEnd(), is(new LocalTime(18, 0)));
        assertThat(dayLog.getBreaks(), is(Duration.standardHours(1L)));
    }

    @Test
    public void testHalfHourBreak() throws Exception {
        DayLog dayLog = DayLog.parse("09:00", "18:00", "0.5");

        assertThat(Duration.standardMinutes(30), is(dayLog.getBreaks()));
    }

    @Test
    public void testDefaultWorkingHours() throws Exception {
        DayLog dayLog = DayLog.parse("09:00", "18:00", "1");

        assertThat(dayLog.asWorkHours(), is(Duration.standardHours(8)));
    }

    @Test
    public void testMinutePrecisionInBeginAndEnd() throws Exception {
        DayLog dayLog = DayLog.parse("09:01:58", "10:02:59", "0");

        assertThat(dayLog.asWorkHours(), is(Duration.standardMinutes(61)));
    }

    @Test
    public void testParseEmptyValues() throws Exception {
        DayLog.parse("", "", "");

    }

    @Test
    public void testIncompleteWorkingHours() throws Exception {
        assertThat(DayLog.parse("", "18:00", "1").asWorkHours(), is(Duration.standardHours(0)));
        assertThat(DayLog.parse("09:00", "", "1").asWorkHours(), is(Duration.standardHours(0)));
        assertThat(DayLog.parse("09:00", "18:00", "").asWorkHours(), is(Duration.standardHours(9)));

    }
}