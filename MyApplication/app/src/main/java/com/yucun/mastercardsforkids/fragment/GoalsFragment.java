package com.yucun.mastercardsforkids.fragment;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;
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
    GoalsAdapter goalsAdapter;
    @Bind(R.id.goals_title)
    TextView goalsTitle;
    @Bind(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    private float budget=0.0f;
    @Bind(R.id.goals_list)
    RecyclerView goalsList;
    private List<Goal> goals;
    private void createDummyGoals(){
        this.goals = new ArrayList<>();
        Goal goal1=new Goal();
        goal1.setName("iPad");
        goal1.setAmount(1000);
        Goal goal2 = new Goal();
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
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/font.ttf");
        goalsTitle.setTypeface(font);
        goalsAdapter=GoalsAdapter
                .GoalsAdapterBuilder.newInstance()
                .setGoals(goals)
                .setBudget(this.budget)
                .setContext(getActivity())
                .build();
        goalsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        goalsList.setItemAnimator(new DefaultItemAnimator());
        goalsList.setAdapter(goalsAdapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, AddGoalFragment.instantiate(getActivity(), AddGoalFragment.class.getName()))
                        .commit();
            }
        });
    }


}
