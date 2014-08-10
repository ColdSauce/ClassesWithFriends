package dwai.classeswithfriends.schedulepackage;

import java.util.ArrayList;
import java.util.List;

import dwai.classeswithfriends.schedulepackage.ScheduleItem;

/**
 * Created by Stefan on 7/31/2014.
 */
public class Schedule {
    private List<ScheduleItem> scheduleItems = new ArrayList<ScheduleItem>();

    public Schedule(List<ScheduleItem> scheduleItems) {
        this.scheduleItems = scheduleItems;
    }

    public List<ScheduleItem> getScheduleItems() {
        return scheduleItems;
    }

}
