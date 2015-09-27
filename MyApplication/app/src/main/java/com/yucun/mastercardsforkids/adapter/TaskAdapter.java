package com.yucun.mastercardsforkids.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yucun.mastercardsforkids.R;
import com.yucun.mastercardsforkids.model.UserTask;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {
    private Context context;
    private List<UserTask> userTaskList;
    private TaskAdapter(TaskAdapterBuilder builder){
        this.context=builder.getContext();
        this.userTaskList=builder.getUserTaskList();
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
        UserTask userTask=userTaskList.get(position);
        viewHolder.taskName.setText(userTask.getTaskName());
        viewHolder.taskStatus.setChecked(userTask.getStatus());
        viewHolder.taskStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    new AlertDialog.Builder(context)
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    viewHolder.taskStatus.setChecked(true);
                                }
                            }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                                    viewHolder.taskStatus.setChecked(false);
                        }
                    }).setMessage("DID YOU DO A GOOD JOB ?").show();
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
        private List<UserTask> userTaskList;
        public static TaskAdapterBuilder newinstance(){
            return new TaskAdapterBuilder();
        }

        public Context getContext() {
            return context;
        }

        public List<UserTask> getUserTaskList() {
            return userTaskList;
        }

        public TaskAdapterBuilder setContext(Context context) {
            this.context = context;
            return this;
        }

        public TaskAdapterBuilder setUserTaskList(List<UserTask> userTaskList) {
            this.userTaskList = userTaskList;
            return this;
        }
        public TaskAdapter build(){
            return new TaskAdapter(this);
        }
    }
}
