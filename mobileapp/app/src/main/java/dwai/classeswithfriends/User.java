package dwai.classeswithfriends;

/**
 * Created by Stefan on 7/31/2014.
 */
public class User {
    private Schedule schedule;
    private String name;
    private String id;

    public User(Schedule schedule, String name, String id) {
        this.schedule = schedule;
        this.name = name;
        this.id = id;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
