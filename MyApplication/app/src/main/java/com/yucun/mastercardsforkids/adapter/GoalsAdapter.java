package com.yucun.mastercardsforkids.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.yucun.mastercardsforkids.R;
import com.yucun.mastercardsforkids.activity.MainActivity;
import com.yucun.mastercardsforkids.model.Goal;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class GoalsAdapter extends RecyclerView.Adapter<GoalsAdapter.ViewHolder> {
    private GoalsAdapter(GoalsAdapterBuilder goalsAdapterBuilder){
        this.context=goalsAdapterBuilder.getContext();
        this.goals=goalsAdapterBuilder.getGoals();
        this.budget=goalsAdapterBuilder.getBudget();
    }
    float budget;
    Context context;
    List<Goal> goals;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
                .inflate(R.layout.goal, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Goal goal=goals.get(position);
        holder.goalName.setText(goal.getName());
        holder.goalAmount.setText(Integer.toString(goal.getAmount()));
        holder.goalProgress.setMax(100);
        holder.goalProgress.setProgress((int)(goal.getAmount()-budget)/100);

    }

    @Override
    public int getItemCount() {
        return goals == null ? 0 : goals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @Bind(R.id.goal_progress)
        ProgressBar goalProgress;
        @Bind(R.id.goal_name)
        TextView goalName;
        @Bind(R.id.goal_amount)
        TextView goalAmount;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public static class GoalsAdapterBuilder{
        private Context context;
        private List<Goal> goals;
        private float budget=0.0f;
        public static GoalsAdapterBuilder newInstance(){return new GoalsAdapterBuilder();}

        public Context getContext() {
            return context;
        }

        public GoalsAdapterBuilder setContext(Context context) {
            this.context = context;
            return this;
        }

        public float getBudget() {
            return budget;
        }

        public GoalsAdapterBuilder setBudget(float budget) {
            this.budget = budget;
            return this;
        }

        public List<Goal> getGoals() {
            return goals;
        }

        public GoalsAdapterBuilder setGoals(List<Goal> goals) {
            this.goals = goals;
            return this;
        }
        public GoalsAdapter build(){
            return new GoalsAdapter(this);
        }
    }
}
