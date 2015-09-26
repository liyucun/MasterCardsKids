package com.yucun.mastercardsforkids.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private List<UserTask> userTask;

    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(TaskAdapter.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
