package com.ldb.android.ui.layout_tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by lsp on 2016/7/1.
 */
public class FragmentAndFManagerActivity extends FragmentActivity
        implements View.OnClickListener {
    private static final String LOG_TAG = FragmentAndFManagerActivity.class.getSimpleName();

    // 三个选项卡
    private LinearLayout tab1Layout, tab2Layout, tab3Layout;
    // 默认选中第一个
    private int index = 1;
    // fragment管理类
    private FragmentManager fragmentManager;
    // 三个fragment
    private Fragment tab1Fragment, tab2Fragment, tab3Fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_fragmentmanager);
        fragmentManager = getSupportFragmentManager();
        init();
    }

    /**
     * 初始化控件
     */
    private void init(){
        tab1Layout = (LinearLayout) findViewById(R.id.tab1_layout);
        tab2Layout = (LinearLayout) findViewById(R.id.tab2_layout);
        tab3Layout = (LinearLayout) findViewById(R.id.tab3_layout);

        tab1Layout.setOnClickListener(this);
        tab2Layout.setOnClickListener(this);
        tab3Layout.setOnClickListener(this);

        setDefaultFragment();

    }

    /**
     * 设置默认显示的fragment
     */
    private void setDefaultFragment(){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        tab1Fragment = new FragmentAndFManager_Fragment1();
        transaction.replace(R.id.content_layout, tab1Fragment);
        transaction.commit();
    }

    /**
     * 切换fragment
     * @param newFragment
     */
    private void replaceFragment(Fragment newFragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if(!newFragment.isAdded()) {
            Log.d(LOG_TAG, "In replaceFragment -- replace");
            transaction.replace(R.id.content_layout, newFragment);
            transaction.commit();
        }else{
            Log.d(LOG_TAG, "In replaceFragment -- show");
            transaction.show(newFragment);
        }
    }

    /**
     * 改变选项卡的选中状态
     */
    private void clearStatus(){
        if(index == 1){
            tab1Layout.setBackgroundColor(getResources().getColor(R.color.tab));
        }else if(index == 2){
            tab2Layout.setBackgroundColor(getResources().getColor(R.color.tab));
        }else if(index == 3){
            tab3Layout.setBackgroundColor(getResources().getColor(R.color.tab));
        }
    }

    @Override
    public void onClick(View v) {
        clearStatus();
        switch(v.getId()){
            case R.id.tab1_layout:
                if(tab1Fragment == null){
                    tab1Fragment = new FragmentAndFManager_Fragment1();
                }
                replaceFragment(tab1Fragment);
                tab1Layout.setBackgroundColor(getResources().getColor(R.color.tab_down));
                index = 1;
                break;
            case R.id.tab2_layout:
                if(tab2Fragment == null){
                    tab2Fragment = new FragmentAndFManager_Fragment2();
                }
                replaceFragment(tab2Fragment);
                tab2Layout.setBackgroundColor(getResources().getColor(R.color.tab_down));
                index = 2;
                break;
            case R.id.tab3_layout:
                if(tab3Fragment == null){
                    tab3Fragment = new FragmentAndFManager_Fragment3();
                }
                replaceFragment(tab3Fragment);
                tab3Layout.setBackgroundColor(getResources().getColor(R.color.tab_down));
                index = 3;
                break;
        }
    }
}
