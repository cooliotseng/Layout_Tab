package com.ldb.android.ui.layout_tab;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lsp on 2016/7/1.
 */
public class FragmentAndFManager_Fragment1 extends Fragment {
    private static final String LOG_TAG = FragmentAndFManager_Fragment1.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "In onCreateView");
        View view = inflater.inflate(R.layout.fragment_fragmentmanager_tab1, null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(LOG_TAG, "In onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d(LOG_TAG, "In onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d(LOG_TAG, "In onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d(LOG_TAG, "In onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d(LOG_TAG, "In onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d(LOG_TAG, "In onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "In onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d(LOG_TAG, "In onDetach");
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        Log.d(LOG_TAG, "In onAttach");
        super.onAttach(context);
    }
}
