package com.yucun.mastercardsforkids.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yucun.mastercardsforkids.R;

import butterknife.ButterKnife;

/**
 * Created by jianhuizhu on 15-09-26.
 */
public class AlertDialogFragment extends DialogFragment {
    public static AlertDialogFragment newInstance() {
        Bundle args = new Bundle();
        AlertDialogFragment fragment = new AlertDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.alert_dialog_fragment,container);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }
}
