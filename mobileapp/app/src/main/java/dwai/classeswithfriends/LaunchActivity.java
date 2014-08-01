package dwai.classeswithfriends;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.*;
import com.facebook.model.*;
import com.facebook.Session;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;

import dwai.classeswithfriends.schedulepackage.ScheduleActivity;

public class LaunchActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        ActionBar actionBar = getActionBar();
        actionBar.setIcon(R.drawable.student);

        final Typeface mFont = Typeface.createFromAsset(getAssets(),
                "fonts/proxima.ttf");
        final ViewGroup mContainer = (ViewGroup) findViewById(
                android.R.id.content).getRootView();
        LaunchActivity.setAppFont(mContainer, mFont, true);


        Session.openActiveSession(this, true, new Session.StatusCallback() {

            // callback when session changes state
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                if (session.isOpened()) {

                    Request.newMeRequest(session, new Request.GraphUserCallback() {

                        // callback after Graph API response with user object
                        @Override
                        public void onCompleted(GraphUser user, Response response) {
                            if (user != null) {
                                Intent i = new Intent();
                               // new RequestTask().execute(user.getInnerJSONObject().toString());

                            }
                        }
                    }).executeAsync();
                }
            }
        });



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
    }


    private class RequestTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String...v) {
            String getURL = "something";
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(getURL);
            HttpEntity entity = null;

            try {
                httppost.setEntity(new StringEntity(v[0]));
                HttpResponse resp = httpclient.execute(httppost);
                entity = resp.getEntity();
            }
            catch(Exception e){
                //TODO: gracefully handle error
                e.printStackTrace();
            }

            String returnedString = "";
            String line = "";
            try {

                BufferedReader in = new BufferedReader(new InputStreamReader(entity.getContent()));
                while ((line = in.readLine()) != null) {
                    returnedString += line;
                }
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return returnedString;
        }

        @Override
        protected void onPostExecute(String data){
            startActivity(ScheduleActivity.generateIntent(getApplicationContext(), data));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.launch, menu);
        return super.onCreateOptionsMenu(menu);
    }



    public static final void setAppFont(ViewGroup mContainer, Typeface mFont, boolean reflect)
    {
        if (mContainer == null || mFont == null) return;

        final int mCount = mContainer.getChildCount();

        // Loop through all of the children.
        for (int i = 0; i < mCount; ++i)
        {
            final View mChild = mContainer.getChildAt(i);
            if (mChild instanceof TextView)
            {
                // Set the font if it is a TextView.
                ((TextView) mChild).setTypeface(mFont);
            }
            else if (mChild instanceof ViewGroup)
            {
                // Recursively attempt another ViewGroup.
                setAppFont((ViewGroup) mChild, mFont,true);
            }
            else if (reflect)
            {
                try {
                    Method mSetTypeface = mChild.getClass().getMethod("setTypeface", Typeface.class);
                    mSetTypeface.invoke(mChild, mFont);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }




}
