package com.yucun.mastercardsforkids.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.software.shell.fab.ActionButton;
import com.yucun.mastercardsforkids.R;
import com.yucun.mastercardsforkids.activity.EarnMoneyActivity;
import com.yucun.mastercardsforkids.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class HomeFragment extends Fragment {
    @Bind(R.id.task_text)
    TextView taskText;
    @Bind(R.id.goal_text)
    TextView goalText;
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.wallet_text)
    TextView walletText;
    @Bind(R.id.transaction_text)
    TextView transactionText;
    @Bind(R.id.task_area)
    RelativeLayout taskArea;
    @Bind(R.id.wallet_area)
    RelativeLayout walletArea;
    @Bind(R.id.goals_area)
    RelativeLayout goalsArea;
    @Bind(R.id.transaction_area)
    RelativeLayout transactionArea;
    @Bind(R.id.profile_name)
    TextView profile_name;
    @Bind(R.id.scan_text)
    TextView scan_text;

    @Bind(R.id.task_button)
    ActionButton task_button;
    @Bind(R.id.wallet_button)
    ActionButton wallet_button;
    @Bind(R.id.goal_button)
    ActionButton goal_button;
    @Bind(R.id.transaction_button)
    ActionButton transaction_button;
    @Bind(R.id.scan_button)
    ActionButton scan_button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        ButterKnife.bind(this, view);

        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fonts/font.ttf");
        profile_name.setTypeface(font);
        taskText.setTypeface(font);
        goalText.setTypeface(font);
        walletText.setTypeface(font);
        transactionText.setTypeface(font);
        scan_text.setTypeface(font);

        task_button.setButtonColor(getResources().getColor(R.color.brown_500));
        wallet_button.setButtonColor(getResources().getColor(R.color.brown_500));
        goal_button.setButtonColor(getResources().getColor(R.color.brown_500));
        transaction_button.setButtonColor(getResources().getColor(R.color.brown_500));
        scan_button.setButtonColor(getResources().getColor(R.color.brown_500));

        task_button.playShowAnimation();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //((MainActivity)getActivity()).getProfile()
        taskArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, TaskFragment.instantiate(getActivity(), TaskFragment.class.getName()))
                        .addToBackStack("home")
                        .commit();
            }
        });
        walletArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, WalletFragment.instantiate(getActivity(), WalletFragment.class.getName()))
                        .addToBackStack("home")
                        .commit();
            }
        });
        goalsArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, GoalsFragment.instantiate(getActivity(), GoalsFragment.class.getName()))
                        .addToBackStack("home")
                        .commit();
            }
        });
        transactionArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.container, TransactionFragment.instantiate(getActivity(), TransactionFragment.class.getName()))
                        .addToBackStack("home")
                        .commit();
            }
        });
        scan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EarnMoneyActivity.class));
            }
        });
    }
}
