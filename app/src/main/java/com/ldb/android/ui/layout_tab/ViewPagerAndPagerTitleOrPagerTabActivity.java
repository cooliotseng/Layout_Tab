package com.ldb.android.ui.layout_tab;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lsp on 2016/7/3.
 */
public class ViewPagerAndPagerTitleOrPagerTabActivity extends Activity{

    // viewpager
    private ViewPager viewPager;
    // viewpager的标题　使用PagerTitleStrip只需要把Lyaout文件中的PagerTabStrip改为PagerTitleStrip
    private PagerTitleStrip titleStrip;
    // viewpager的指示器
    private PagerTabStrip tabStrip;
    // view集合
    private List<View> viewList;
    // 标题集合
    private List<String> titleList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_pagertitle_pagertab);

        init();
    }

    private void init(){
        viewList = new ArrayList<>();
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.viewpager_pageradapter_tab1, null);
        viewList.add(view);
        view = inflater.inflate(R.layout.viewpager_pageradapter_tab2, null);
        viewList.add(view);
        view = inflater.inflate(R.layout.viewpager_pageradapter_tab3, null);
        viewList.add(view);

        titleList = new ArrayList<>();
        titleList.add("标题1");
        titleList.add("标题2");
        titleList.add("标题3");

        initViewPager();
    }
    private void initViewPager(){
        viewPager = (ViewPager) findViewById(R.id.fifth_vp);
        viewPager.setAdapter(pagerAdapter);

        // 修改指示器的颜色
//        tabStrip = (PagerTabStrip) findViewById(R.id.fifth_strip);
//        tabStrip.setTabIndicatorColor(Color.RED);

    }

    /**
     * 适配器
     */
    PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titleList.get(position);
        }
    };
}
