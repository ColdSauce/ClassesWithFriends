package dwai.classeswithfriends.schedulepackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stefan on 8/1/2014.
 */
public class Day {
    private DayType dayType;
    List<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();

    public Day(DayType dayType, List<ScheduleItem> scheduleItems) {
        this.dayType = dayType;
        this.scheduleItems = scheduleItems;
    }

    public DayType getDayType() {
        return dayType;
    }

    public List<ScheduleItem> getScheduleItems() {
        return scheduleItems;
    }
}
