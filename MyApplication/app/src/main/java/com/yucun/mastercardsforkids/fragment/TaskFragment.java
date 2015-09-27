package com.yucun.mastercardsforkids.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseRelation;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.yucun.mastercardsforkids.R;
import com.yucun.mastercardsforkids.activity.MainActivity;
import com.yucun.mastercardsforkids.adapter.TaskAdapter;
import com.yucun.mastercardsforkids.model.Task;
import com.yucun.mastercardsforkids.model.UserTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class TaskFragment extends Fragment {
    private List<Task> userTaskList;
    @Bind(R.id.task_fragment_title)
    TextView taskTitle;
    @Bind(R.id.task_list)
    RecyclerView taskList;
    TaskAdapter taskAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.task_fragment,container,false);
        ButterKnife.bind(this,view);
        this.userTaskList=((MainActivity)getActivity()).getProfile().getTasks();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/font.ttf");
        taskTitle.setTypeface(font);
        taskAdapter= TaskAdapter
                .TaskAdapterBuilder
                .newinstance()
                .setContext(getActivity())
                .setUserTaskList(userTaskList)
                .build();
        taskList.setLayoutManager(new LinearLayoutManager(getActivity()));
        taskList.setItemAnimator(new DefaultItemAnimator());
        taskList.setAdapter(taskAdapter);

    }
    public static Fragment newInstance() {

        Bundle args = new Bundle();

        TaskFragment fragment = new TaskFragment();
        fragment.setArguments(args);
        return fragment;
    }


}
