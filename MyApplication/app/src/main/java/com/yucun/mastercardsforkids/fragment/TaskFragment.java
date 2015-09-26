package com.yucun.mastercardsforkids.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yucun.mastercardsforkids.R;
import com.yucun.mastercardsforkids.adapter.TaskAdapter;
import com.yucun.mastercardsforkids.model.UserTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class TaskFragment extends Fragment {
    private List<UserTask> userTaskList;
    @Bind(R.id.task_list)
    RecyclerView taskList;
    TaskAdapter taskAdapter;
    private void createDummyData(){
        userTaskList=new ArrayList<>();
        UserTask task1=new UserTask("Buy soy sause","");
        UserTask task2=new UserTask("Buy pen","");
        UserTask task3=new UserTask("Pay tuition fees ","");
        userTaskList.add(task1);
        userTaskList.add(task2);
        userTaskList.add(task3);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.task_fragment,container);
        ButterKnife.bind(this,view);
        createDummyData();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskAdapter= TaskAdapter
                .TaskAdapterBuilder
                .newinstance()
                .setContext(getActivity())
                .setUserTaskList(userTaskList)
                .build();
        taskList.setLayoutManager(new LinearLayoutManager(getContext()));
        taskList.setItemAnimator(new DefaultItemAnimator());
        taskList.setAdapter(taskAdapter);
    }
}
