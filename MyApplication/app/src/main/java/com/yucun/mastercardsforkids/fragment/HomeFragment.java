package com.yucun.mastercardsforkids.fragment;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.yucun.mastercardsforkids.R;
import com.yucun.mastercardsforkids.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class HomeFragment extends Fragment {
    @Bind(R.id.avatar)
    ImageView avatar;
    @Bind(R.id.task_area)
    RelativeLayout taskArea;
    @Bind(R.id.wallet_area)
    RelativeLayout walletArea;
    @Bind(R.id.goals_area)
    RelativeLayout goalsArea;
    @Bind(R.id.transaction_area)
    RelativeLayout transactionArea;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment,container,false);
        ButterKnife.bind(this, view);
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
    }
}
