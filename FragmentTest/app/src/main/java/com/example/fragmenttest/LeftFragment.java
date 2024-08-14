package com.example.fragmenttest;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class LeftFragment extends Fragment {
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_left, container, false);

        button = view.findViewById(R.id.button);

        FirstLookActivity activity = (FirstLookActivity) getActivity();
        if (activity != null) {
            button.setOnClickListener(v -> activity.replaceFragment(new AnotherRightFragment()));
        }

        return view;
    }

    public void setBtnOnClickListener(View.OnClickListener l) {
        if (button == null) return;
        button.setOnClickListener(l);
    }
}