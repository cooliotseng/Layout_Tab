package com.ldb.android.ui.layout_tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * ViewPager + Fragment + FragmentPagerAdapter
 * Created by lsp on 2016/7/3.
 */
public class ViewPagerAndFragmentPagerAdapterActivity extends FragmentActivity
        implements View.OnClickListener, ViewPager.OnPageChangeListener{

    private static final String LOG_TAG =
            ViewPagerAndFragmentPagerAdapterActivity.class.getSimpleName();

    // 三个textview
    private TextView tab1Tv, tab2Tv, tab3Tv, tab4Tv;
    // 指示器
    private ImageView cursorImg;
    // viewPager
    private ViewPager viewPager;
    // fragment对象集合
    private ArrayList<Fragment> fragmentsList;
    // 记录当前选中的tab的index
    private int currentIndex = 0;
    // 指示器的偏移量
    private int offset = 0;
    // 左margin
    private int leftMargin = 0;
    // 屏幕宽度
    private int screenWidth = 0;
    // 屏幕宽度的三分之一
    //private int screen1_3;
    // Tab的数量
    private int tabCount;
    // 一个Tab的宽度 screenWidth / tabCount
    private int widthOfOneTab;
    //
    private LinearLayout.LayoutParams lp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_fragmentpageradapter);

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

        tab1Tv = (TextView) findViewById(R.id.tab1_tv);
        tab2Tv = (TextView) findViewById(R.id.tab2_tv);
        tab3Tv = (TextView) findViewById(R.id.tab3_tv);
        tab4Tv = (TextView) findViewById(R.id.tab4_tv);

        tabCount = 4;
        widthOfOneTab = screenWidth / tabCount;

        tab1Tv.setOnClickListener(this);
        tab2Tv.setOnClickListener(this);
        tab3Tv.setOnClickListener(this);
        tab4Tv.setOnClickListener(this);

        initViewPager();
    }

    private void initViewPager(){
        viewPager = (ViewPager) findViewById(R.id.third_vp);
        fragmentsList = new ArrayList<>();
        Fragment fragment = new FragmentAndFManager_Fragment1();
        fragmentsList.add(fragment);
        fragment = new FragmentAndFManager_Fragment2();
        fragmentsList.add(fragment);
        fragment = new FragmentAndFManager_Fragment3();
        fragmentsList.add(fragment);
        fragment = new FragmentAndFManager_Fragment4();
        fragmentsList.add(fragment);

        viewPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragmentsList));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(this);
        viewPager.setPageMargin(100);
    }

    @Override
    public void onClick(View v) {
        Log.d(LOG_TAG, "In onClick v.getId() = " + v.getId());
        switch(v.getId()){
            case R.id.tab1_tv:
                viewPager.setCurrentItem(0);
                break;
            case R.id.tab2_tv:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tab3_tv:
                viewPager.setCurrentItem(2);
                break;
            case R.id.tab4_tv:
                viewPager.setCurrentItem(3);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        offset = (widthOfOneTab - cursorImg.getLayoutParams().width) / 2;
        Log.d(LOG_TAG, "In onPageScrolled: " + position + " -- " + positionOffset + " -- " +
                positionOffsetPixels);
        Log.d(LOG_TAG, "In onPageScrolled ScrollX: " + viewPager.getScrollX());
//        Log.d(LOG_TAG, "In onPageScrolled viewPager.child: " + viewPager.getChildAt(position).toString());
        Log.d(LOG_TAG, "In onPageScrolled viewPager.left:" + viewPager.getLeft());
        for(int i = 0; i < viewPager.getChildCount(); i++) {
            Log.d(LOG_TAG, "In onPageScrolled viewPager.child:" + i);
            Log.d(LOG_TAG, "In onPageScrolled viewPager.child.left: " + viewPager.getChildAt(i).getLeft());
            Log.d(LOG_TAG, "In onPageScrolled viewPager.child.width: " + viewPager.getChildAt(i).getWidth());
            Log.d(LOG_TAG, "In onPageScrolled viewPager.child.ScrollX: " + viewPager.getChildAt(i).getScrollX());
        }

        //final float scale = getResources().getDisplayMetrics().density;
//        if(position == 0){
//            lp.leftMargin = (int) (positionOffsetPixels / 3) + offset;
//        }else if(position == 1){
//            lp.leftMargin = (int) (positionOffsetPixels / 3) + widthOfOneTab + offset;
//        }

        // position 是距离ViewPager内容区左边最近的可视view在adapter中的位置position。
        // positionOffset, posifsetPixels 是position对应可视view的左边偏离ViewPager内容区左边的距离与view宽度的比例或距离。
        if(position < tabCount - 1){
            lp.leftMargin = (int) (positionOffsetPixels / tabCount) + widthOfOneTab * position + offset;
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

