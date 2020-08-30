package ugr.cc.statistics.util;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class GeneralUtils {

    public static Date sqlToday() {
        LocalDate localDate = LocalDate.now();
        return Date.valueOf(localDate);
    }

    public static Time sqlNow() {
        LocalTime localTime = LocalTime.now();
        return Time.valueOf(localTime);
    }
}
