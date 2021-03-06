package com.yucun.mastercardsforkids.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.yucun.mastercardsforkids.R;
import com.yucun.mastercardsforkids.fragment.GoalsFragment;
import com.yucun.mastercardsforkids.fragment.HomeFragment;
import com.yucun.mastercardsforkids.model.Goal;
import com.yucun.mastercardsforkids.model.Profile;
import com.yucun.mastercardsforkids.model.Task;

import java.util.ArrayList;
import java.util.List;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class MainActivity extends AppCompatActivity {
    Activity activity=this;
    private ProgressDialog progressDialog;
    public static Profile profile;
    public Profile getProfile(){
        return profile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = new Profile();
        // need add progress bar
        Handler handler=new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog = ProgressDialog.show(activity, null, "Loading...", true, false);
                    }
                });
        setupUserProfile();

    }

    public void setupUserProfile(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {

                if (e == null) {
                    ParseObject rawProfile = list.get(0);

                    profile.setAllowance((int) rawProfile.getNumber("allowance"));
                    profile.setCard(rawProfile.getString("card"));
                    profile.setEducash((int) rawProfile.getNumber("educash"));
                    profile.setObjectId(rawProfile.getObjectId());
                    ParseRelation<ParseObject> goals = rawProfile.getRelation("goals");

                    try {
                        List<ParseObject> raw_goal_list = goals.getQuery().find();
                        List<Goal> goal_list = new ArrayList<Goal>(raw_goal_list.size());

                        for (ParseObject rawGoal : raw_goal_list) {
                            Goal goal = new Goal(rawGoal.getObjectId(), rawGoal.getString("name"), (int) rawGoal.getNumber("amount"), rawGoal.getBoolean("enabled"));
                            goal_list.add(goal);
                        }

                        profile.setGoals(goal_list);

                    } catch (ParseException ex) {

                    }

                    ParseRelation<ParseObject> tasks = rawProfile.getRelation("tasks");

                    try {
                        List<ParseObject> raw_task_list = tasks.getQuery().find();
                        List<Task> task_list = new ArrayList<Task>(raw_task_list.size());

                        for (ParseObject rawTask : raw_task_list) {
                            Task task = new Task(rawTask.getObjectId(), rawTask.getString("name"),rawTask.getBoolean("enabled"), rawTask.getInt("amount"));
                            task_list.add(task);
                        }

                        profile.setTasks(task_list);

                    } catch (ParseException ex) {

                    }

                } else {
                    // error happened
                }
                progressDialog.dismiss();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, HomeFragment.instantiate(activity, HomeFragment.class.getName()))
                        .commit();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
        } else {
            getFragmentManager().popBackStack();
        }
    }
}
