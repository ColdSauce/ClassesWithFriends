package dwai.classeswithfriends;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Stefan on 7/31/2014.
 */
public class ScheduleItem extends LinearLayout {

    private String jsonData = "";

    public ScheduleItem(Context context,String jsonData) {
        super(context);
        this.jsonData = jsonData;
    }

    public String getJsonData() {
        return jsonData;
    }
}
