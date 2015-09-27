package com.yucun.mastercardsforkids.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yucun.mastercardsforkids.R;
import com.yucun.mastercardsforkids.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class WalletFragment extends Fragment {
    @Bind(R.id.allowance_record)
    TextView allowanceRecord;
    @Bind(R.id.educash_record)
    TextView educashRecord;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.wallet_fragment,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allowanceRecord.setText(Integer.toString(((MainActivity) getActivity()).getProfile().getAllowance()));
        educashRecord.setText(Integer.toString(((MainActivity) getActivity()).getProfile().getEducash()));
    }
}
