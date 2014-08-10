package dwai.classeswithfriends.schedulepackage;

/**
 * Created by Stefan on 8/9/2014.
 */
public class SchoolClass {
    private String day,start,end;

    public SchoolClass(String day, String start, String end) {
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public String getDay() {
        return day;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }
}
