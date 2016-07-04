package com.ldb.android.ui.layout_tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * ViewPager + Fragment + FragmentPagerAdapter
 * Created by lsp on 2016/7/3.
 */
public class ViewPagerAndFragmentPagerAdapterDynamicActivity extends FragmentActivity
        implements ViewPager.OnPageChangeListener{

    private static final String LOG_TAG =
            ViewPagerAndFragmentPagerAdapterDynamicActivity.class.getSimpleName();

    // 三个textview
    private TextView tab1Tv, tab2Tv, tab3Tv;
    private ArrayList<TextViewInfo> tabInfoList;
    // 指示器
    private ImageView cursorImg;
    // viewPager
    private ViewPager viewPager;
    // fragment对象集合
    private ArrayList<Fragment> fragmentsList;
    // 记录当前选中的tab的index
    private int currentIndex = 0;
    // 指示器的偏移量
    //private int offset = 0;
    // 左margin
    private int leftMargin = 0;
    // 屏幕宽度
    private int screenWidth = 0;
    // 屏幕宽度的三分之一
    //private int screen1_3;
    //
    private LinearLayout.LayoutParams lp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_fragmentpageradapter_dynamic);

        init();
    }

    /**
     * 初始化操作
     */
    private void init(){
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;

        cursorImg = (ImageView) findViewById(R.id.cursor);
        lp = (LinearLayout.LayoutParams) cursorImg.getLayoutParams();
        leftMargin = lp.leftMargin;

        initViewPager();

        tabInfoList = new ArrayList<TextViewInfo>();
        initTabInfo();

        LinearLayout tabLinearLayout = (LinearLayout) findViewById(R.id.tab_linearlayout);
        for(TextViewInfo textViewInfo : tabInfoList){
            final int index = tabInfoList.indexOf(textViewInfo);
            TextView textView = new TextView(this);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
            layoutParams.weight = textViewInfo.getLayoutWeight();
            layoutParams.gravity = Gravity.CENTER;
            textView.setLayoutParams(layoutParams);
            textView.setText(textViewInfo.getText());
            textView.setTextColor(textViewInfo.getTextColor());
            textView.setGravity(textViewInfo.getGravity());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(LOG_TAG, "In onClick index = " + index);
                    viewPager.setCurrentItem(index);
                }
            });
            tabLinearLayout.addView(textView);
        }
    }

    private void initViewPager(){
        viewPager = (ViewPager) findViewById(R.id.third_vp);
        fragmentsList = new ArrayList<>();
        // 如何使fragment也根据tabInfoList生成?
        Fragment fragment = new FragmentAndFManager_Fragment1();
        fragmentsList.add(fragment);
        fragment = new FragmentAndFManager_Fragment2();
        fragmentsList.add(fragment);
        fragment = new FragmentAndFManager_Fragment3();
        fragmentsList.add(fragment);
        fragment = new FragmentAndFManager_Fragment1();
        fragmentsList.add(fragment);


        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentsList));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
    }

    /**
     * 初始化tab信息
     */
    private void initTabInfo(){
        TextViewInfo textView;
        for(int i = 0; i < 4; i++){
            textView = new TextViewInfo();
            textView.setId("tab" + (i + 1) + "_tv");
            textView.setLayoutWidth("0");
            textView.setLayoutHeight("match_parent");
            textView.setLayoutWeight(1);
            textView.setText("选项卡" + (i + 1));
            textView.setTextColor(0xFF707070);
            textView.setLayoutGravity(Gravity.CENTER);
            textView.setGravity(Gravity.CENTER);
            tabInfoList.add(textView);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int count = tabInfoList.size();
        int itemWidth = screenWidth / count;
        int offset = (itemWidth - cursorImg.getLayoutParams().width) / 2;
        Log.d(LOG_TAG, "In onPageScrolled: " + position + " -- " + positionOffset + " -- " +
                positionOffsetPixels);
        if(position < count - 1) {
            lp.leftMargin = (int) (positionOffsetPixels / count) + itemWidth * position + offset;
        }

        cursorImg.setLayoutParams(lp);
        currentIndex = position;
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}

