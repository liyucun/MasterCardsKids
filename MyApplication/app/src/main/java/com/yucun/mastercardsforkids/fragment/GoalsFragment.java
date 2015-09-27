package com.yucun.mastercardsforkids.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.yucun.mastercardsforkids.R;
import com.yucun.mastercardsforkids.activity.MainActivity;
import com.yucun.mastercardsforkids.adapter.GoalsAdapter;
import com.yucun.mastercardsforkids.model.Goal;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class GoalsFragment extends Fragment {
    private float budget=0.0f;
    @Bind(R.id.goals_list)
    RecyclerView goalsList;
    private List<Goal> goals;
    private void createDummyGoals(){
        this.goals = new ArrayList<>();
        Goal goal1=new Goal();
        goal1.setName("iPad");
        goal1.setAmount(1000);
        Goal goal2=new Goal();
        goal2.setName("Remote Contorl Car");
        goal2.setAmount(10000);
        goals.add(goal1);
        goals.add(goal2);
    }
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.goals_fragment,container,false);
        ButterKnife.bind(this, view);
        //createDummyGoals();
        this.goals=((MainActivity)getActivity()).getProfile().getGoals();
        this.budget=((MainActivity)getActivity()).getProfile().getAllowance();
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        GoalsAdapter goalsAdapter=GoalsAdapter
                .GoalsAdapterBuilder.newInstance()
                .setGoals(goals)
                .setBudget(this.budget)
                .setContext(getActivity())
                .build();
        goalsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        goalsList.setItemAnimator(new DefaultItemAnimator());
        goalsList.setAdapter(goalsAdapter);
    }

    public void addGoalToParse(final Goal goal){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    final ParseObject rawProfile = list.get(0);

                    // add goal
                    final ParseRelation<ParseObject> goals = rawProfile.getRelation("goals");

                    final ParseObject new_goal = new ParseObject("Goal");
                    new_goal.put("name", goal.getName());
                    new_goal.put("amount", goal.getAmount());
                    new_goal.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            goals.add(new_goal);
                            rawProfile.saveInBackground();
                        }
                    });

                }
            }
        });
    }
}
