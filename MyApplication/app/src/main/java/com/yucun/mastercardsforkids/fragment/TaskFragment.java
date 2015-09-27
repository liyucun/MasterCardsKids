package com.yucun.mastercardsforkids.fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    public void addTaskToParse(final Task task){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    final ParseObject rawProfile = list.get(0);

                    final ParseRelation<ParseObject> tasks = rawProfile.getRelation("tasks");
                    final ParseObject taskParseObject = new ParseObject("Task");
                    taskParseObject.put("name", task.getName());
                    taskParseObject.put("enabled", true);

                    taskParseObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if(e == null){
                                tasks.add(taskParseObject);
                                rawProfile.saveInBackground();
                            }

                        }
                    });


                }else{

                }
            }
        });
    }

    public void finishTask(final Task task){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ParseObject rawProfile = list.get(0);

                    // change allowance
                    int new_allowance = rawProfile.getInt("allowance") + task.getAmount();
                    rawProfile.put("allowance", new_allowance);

                    // set task enabled to false
                    ParseRelation<ParseObject> tasks = rawProfile.getRelation("tasks");

                    try{
                        for(ParseObject object : tasks.getQuery().find()){
                            if(object.getObjectId().equals(task.getObjectId())){
                                object.put("enabled", false);
                                object.saveInBackground();
                                break;
                            }
                        }
                    }catch (ParseException exception){

                    }

                    rawProfile.saveInBackground();
                }else{

                }
            }
        });
    }
}
