package dwai.classeswithfriends.schedulepackage;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dwai.classeswithfriends.LaunchActivity;
import dwai.classeswithfriends.R;

public class CreateClassActivity extends Activity {


    private List<SchoolClass> schoolClasses = new ArrayList<SchoolClass>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        final Typeface mFont = Typeface.createFromAsset(getAssets(),
                "fonts/proxima.ttf");
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        LaunchActivity.setAppFont(mContainer, mFont, true);



    }

    public void clickedPlusButton(View v){
        final LinearLayout classTime = new LinearLayout(this);
        LinearLayout.LayoutParams classTimeParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        final Button[] classTimeButtons = {new Button(this), new Button(this), new Button(this)};
        final int DAYS_BUTTON = 0;
        final int START_BUTTON = 1;
        final int END_BUTTON = 2;

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.weight = 0.25f;
//        buttonParams.height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, , getResources().getDisplayMetrics());

        buttonParams.setMargins(0,0,(int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()),0);
        buttonParams.topMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
         classTime.setLayoutParams(classTimeParams);
        for(Button b : classTimeButtons){
            b.setLayoutParams(buttonParams);
            b.setTextSize(9);
            b.setTextColor(Color.WHITE);
            b.setBackgroundColor(getResources().getColor(R.color.lightblue));
            classTime.addView(b);
        }
        Button minusButton = new Button(this);

        LinearLayout.LayoutParams minusButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        minusButtonParams.width =  (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        minusButtonParams.height = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, getResources().getDisplayMetrics());
        minusButtonParams.topMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, getResources().getDisplayMetrics());
        minusButton.setBackgroundResource(R.drawable.removeitem);

        minusButton.setLayoutParams(minusButtonParams);
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((LinearLayout)findViewById(R.id.timeClasses)).removeView(classTime);
            }
        });
        classTimeButtons[DAYS_BUTTON].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDaysDialog(classTimeButtons[DAYS_BUTTON]);
            }
        });


        for(int i = START_BUTTON; i < END_BUTTON; i++){
            final int buttonOrder = i;
            classTimeButtons[i].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(classTimeButtons[buttonOrder]);
            }

        });
        }
        classTime.addView(minusButton);
        ((LinearLayout)findViewById(R.id.timeClasses)).addView(classTime);
    }
    public void showDaysDialog(final Button which){
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateClassActivity.this);


               final CharSequence[] items = {" Monday "," Tuesday "," Wednesday "," Thursday ", " Friday ", " Saturday " , " Sunday "};
               // arraylist to keep the selected items
                final ArrayList<CharSequence> seletedItems=new ArrayList<CharSequence>();

                    builder.setTitle("Select The Days You Have This Class On");
                    builder.setMultiChoiceItems(items, null,
                            new DialogInterface.OnMultiChoiceClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int indexSelected,
                                                    boolean isChecked) {
                                    if (isChecked) {
                                        // If the user checked the item, add it to the selected items
                                        seletedItems.add(items[indexSelected]);
                                    } else if (seletedItems.contains(items[indexSelected])) ;
                                    // Else, if the item is already in the array, remove it
                                    seletedItems.remove(Integer.valueOf(indexSelected));
                                }

                            }
                    );
//                builder.setMessage("Select days you will have this class on.")
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                String theText = "";
                                for(CharSequence word : seletedItems){
                                    theText += word.charAt(1) + " ";
                                }
                                which.setText(theText);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                builder.show();

    }

    public void showTimePickerDialog(final Button which){
        Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(CreateClassActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        String amOrPm = "";
                        if(selectedHour < 12){
                            amOrPm = " am";
                        }
                        else {
                            amOrPm = " pm";
                        }

                        which.setText(selectedHour%12 + ":" + selectedMinute + amOrPm);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

    }

    public void pressedCreateClass(View v){
       JSONArray classes = getAllClasses();
        

    }

    private JSONArray getAllClasses() {
        LinearLayout timeClasses = ((LinearLayout) findViewById(R.id.timeClasses));
        JSONArray root = new JSONArray();
        for (int i = 0; i < timeClasses.getChildCount(); i++) {
            LinearLayout theStats = (LinearLayout) timeClasses.getChildAt(i);
            JSONArray buttons = new JSONArray();
            for (int j = 0; j < theStats.getChildCount(); j++) {
                Button b = (Button) theStats.getChildAt(j);
                if (!b.getText().toString().isEmpty()) {
                    buttons.put(b.getText().toString());
                }
            }
            root.put(buttons);


        }
        return root;
    }
}
