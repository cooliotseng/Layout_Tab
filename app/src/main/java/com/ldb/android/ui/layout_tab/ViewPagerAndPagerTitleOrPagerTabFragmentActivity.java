package com.ldb.android.ui.layout_tab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;

/**
 * Created by lsp on 2016/7/4.
 */
public class ViewPagerAndPagerTitleOrPagerTabFragmentActivity extends FragmentActivity {

    // viewpager
    private ViewPager viewPager;
    // viewpager的标题　使用PagerTitleStrip只需要把Lyaout文件中的PagerTabStrip改为PagerTitleStrip
    private PagerTitleStrip titleStrip;
    // viewpager的指示器
    private PagerTabStrip tabStrip;
    // view集合
    private ArrayList<Fragment> fragmentsList;
    // 标题集合
    private ArrayList<String> titleList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_pagertitle_pagertab);

        init();
    }

    private void init(){
        fragmentsList = new ArrayList<>();
        Fragment fragment = new FragmentAndFManager_Fragment1();
        fragmentsList.add(fragment);
        fragment = new FragmentAndFManager_Fragment2();
        fragmentsList.add(fragment);
        fragment = new FragmentAndFManager_Fragment3();
        fragmentsList.add(fragment);

        titleList = new ArrayList<>();
        titleList.add("标题1");
        titleList.add("标题2");
        titleList.add("标题3");

        initViewPager();
    }
    private void initViewPager(){
        viewPager = (ViewPager) findViewById(R.id.fifth_vp);
        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentsList, titleList));

        // 修改指示器的颜色
//        tabStrip = (PagerTabStrip) findViewById(R.id.fifth_strip);
//        tabStrip.setTabIndicatorColor(Color.RED);

    }

}
