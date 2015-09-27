package com.yucun.mastercardsforkids.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
import com.yucun.mastercardsforkids.model.Profile;
import com.yucun.mastercardsforkids.model.Task;
import com.yucun.mastercardsforkids.model.UserTask;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private Profile profile;
    private Context context;
    private List<Task> userTaskList;
    private TaskAdapter(TaskAdapterBuilder builder){
        this.context=builder.getContext();
        this.userTaskList=builder.getUserTaskList();
        this.profile=builder.getProfile();
    }
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        /**
         * Steps
         * 1. save the context for future usage(Optional)
         *
         * 2. Pass the context to the LayoutInflater
         *
         * 3. inflate a new view with
         *    1. layout file
         *    2. The root view to be attached
         *    3. Attach to the root view or not
         *
         * 4. Create a new ViewHolder which defined as an inner class of CardViewAdapter and return
         */
        this.context = parent.getContext();

        View view = LayoutInflater
                .from(context)
                .inflate(R.layout.task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TaskAdapter.ViewHolder viewHolder, int position) {
        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/font.ttf");
        final Task userTask=userTaskList.get(position);
        viewHolder.taskName.setText(userTask.getName());
        viewHolder.taskName.setTypeface(font);
        viewHolder.taskStatus.setChecked(!userTask.getEnabled());
        viewHolder.taskStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new AlertDialog.Builder(context)
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    viewHolder.taskStatus.setChecked(true);
                                    userTask.setEnabled(false);
                                    profile.setAllowance(profile.getAllowance()-userTask.getAmount());
                                    changeTaskStatus(userTask);
                                }
                            }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                                    viewHolder.taskStatus.setChecked(false);
                            userTask.setEnabled(true);
                            profile.setAllowance(profile.getAllowance()+userTask.getAmount());
                            changeTaskStatus(userTask);
                        }
                    }).setMessage("DID YOU DO A GOOD JOB ?").show();
                }else{
                    viewHolder.taskStatus.setChecked(false);
                    userTask.setEnabled(true);
                    profile.setAllowance(profile.getAllowance()+userTask.getAmount());
                    changeTaskStatus(userTask);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userTaskList == null ? 0 : userTaskList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.task_checkbox)
        CheckBox taskStatus;
        @Bind(R.id.task_name)
        TextView taskName;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
    public static class TaskAdapterBuilder{
        private Context context;
        private List<Task> userTaskList;
        private Profile profile;
        public static TaskAdapterBuilder newinstance(){
            return new TaskAdapterBuilder();
        }

        public Context getContext() {
            return context;
        }

        public List<Task> getUserTaskList() {
            return userTaskList;
        }

        public TaskAdapterBuilder setContext(Context context) {
            this.context = context;
            return this;
        }

        public TaskAdapterBuilder setUserTaskList(List<Task> userTaskList) {
            this.userTaskList = userTaskList;
            return this;
        }
        public TaskAdapterBuilder setUserProfile(Profile profile){
            this.profile=profile;
            return this;
        }

        public Profile getProfile() {
            return profile;
        }

        public TaskAdapter build(){
            return new TaskAdapter(this);
        }
    }


    public void changeTaskStatus(final Task task){

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Profile");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> list, ParseException e) {
                if (e == null) {
                    ParseObject rawProfile = list.get(0);

                    // change allowance
                    if(task.getEnabled()){
                        int new_allowance = rawProfile.getInt("allowance") + task.getAmount();
                        rawProfile.put("allowance", new_allowance);
                    }else{
                        int new_allowance = rawProfile.getInt("allowance") - task.getAmount();
                        rawProfile.put("allowance", new_allowance);
                    }


                    // set task enabled to false
                    ParseRelation<ParseObject> tasks = rawProfile.getRelation("tasks");

                    try{
                        for(ParseObject object : tasks.getQuery().find()){
                            if(object.getObjectId().equals(task.getObjectId())){
                                object.put("enabled", !task.getEnabled());
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
