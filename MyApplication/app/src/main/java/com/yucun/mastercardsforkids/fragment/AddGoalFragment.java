package com.yucun.mastercardsforkids.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.yucun.mastercardsforkids.R;
import com.yucun.mastercardsforkids.activity.MainActivity;
import com.yucun.mastercardsforkids.model.Goal;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class AddGoalFragment extends Fragment {
    @Bind(R.id.container)
    CoordinatorLayout container;
    @Bind(R.id.goal_name_edit)
    EditText goalNameEdit;
    @Bind(R.id.goal_amount_edit)
    EditText goalAmountEdit;
    @Bind(R.id.confirm_add_goal)
    TextView confirmAddGoal;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_goal_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        confirmAddGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (goalNameEdit.getText().toString().equals("") || goalAmountEdit.getText().toString().equals("") || (Integer.parseInt(goalAmountEdit.getText().toString()) <= 0)) {
                    /**
                     * Snack bar
                     * make function parameters
                     *
                     * 1st parameter is the view to attach on(parent view)
                     *               here is the container(root view)
                     *
                     * 2nd parameter is the message to be sent to user
                     *               here simply just a "Email does not match"
                     *
                     * 3rd parameter is the duration for this snackbar
                     *               here is Snackbar.LENGTH_SHORT
                     */
                    Snackbar.make(container, "Mmmm... you sure name or amount is correct?", Snackbar.LENGTH_SHORT)
                            .show();
                }
                else{
                    Goal goal=new Goal();
                    goal.setName(goalNameEdit.getText().toString());
                    goal.setAmount(Integer.parseInt(goalAmountEdit.getText().toString()));
                    ((MainActivity)getActivity()).getProfile().getGoals().add(goal);
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container,GoalsFragment.instantiate(getActivity(),GoalsFragment.class.getName()))
                            .commit();
                }
            }
        });
    }
}
