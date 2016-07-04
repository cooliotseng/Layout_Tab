package com.ldb.android.ui.layout_tab;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ViewPager + PageAdapter实现Tab界面
 * Created by lsp on 2016/6/30.
 */
public class ViewPagerAndPagerAdapterActivity extends Activity{

    private static final String LOG_TAG =
            ViewPagerAndPagerAdapterActivity.class.getSimpleName();

    private ViewPager viewPager;
    private ArrayList<View> list = new ArrayList<>();
    // 底部点的布局
    private LinearLayout pointLayout;
    // 底部的点
    private ImageView[] dots;
    // 当前选中的索引
    private int currentIndex;
    private boolean flag = true;
    // 自增int
    private AtomicInteger what = new AtomicInteger(0);
    private boolean isSlipped;
    // 控制循环播放图片线程
    private boolean isRunning;

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            Log.d(LOG_TAG, "In PagerAdapter.getCount()");
            return list.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            Log.d(LOG_TAG, "In PagerAdapter.isViewFromObject()");
            return view == object;
        }

        // viewPager.setCurrentItem() --> viewPager.populate() --> viewPager.addNewItem()
        // --> adapter.instantiateItem()

        // 在ViewPager.addNewItem()方法中调用
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.d(LOG_TAG, "In PagerAdapter.instantiateItem()");
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            Log.d(LOG_TAG, "In PagerAdapter.destroyItem()");
            container.removeView(list.get(position));
        }
    };

    private final Handler viewHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Log.d(LOG_TAG, "In Handler.handleMessage start");
            viewPager.setCurrentItem(msg.what);
            Log.d(LOG_TAG, "In Handler.handleMessage stop");
            setDots(msg.what);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "In onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager_pageradapter);

        init();
        initDots();

    }

    @Override
    protected void onStart() {
        Log.d(LOG_TAG, "In onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(LOG_TAG, "In onResume");
        super.onResume();
        isRunning = true;
        loopPlay();
    }

    @Override
    protected void onPause() {
        Log.d(LOG_TAG, "In onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(LOG_TAG, "In onStop");
        super.onStop();
        isRunning = false;
    }

    @Override
    protected void onDestroy() {
        Log.d(LOG_TAG, "In onDestroy");
        super.onDestroy();
    }

    private void init(){
        isSlipped = false;

        viewPager = (ViewPager) findViewById(R.id.first_vp);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.viewpager_pageradapter_tab1, null);
        View view2 = inflater.inflate(R.layout.viewpager_pageradapter_tab2, null);
        View view3 = inflater.inflate(R.layout.viewpager_pageradapter_tab3, null);
        View view4 = inflater.inflate(R.layout.viewpager_pageradapter_tab4, null);
        list.add(view1);
        list.add(view2);
        list.add(view3);
        list.add(view4);

        viewPager.setAdapter(pagerAdapter);
        // setOnPageChangeListener 弃用了
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                Log.d(LOG_TAG, "In OnPageChangeListener.onPageScrolled");
            }

            @Override
            public void onPageSelected(int position) {
                Log.d(LOG_TAG, "In OnPageChangeListener.onPageSelected");
                isSlipped = true;
                setDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                Log.d(LOG_TAG, "In OnPageChangeListener.onPageScrollStateChanged");
            }
        });

    }

    /**
     * 初始化底部的点
     */
    private void initDots(){
        pointLayout = (LinearLayout) findViewById(R.id.point_layout);
        dots = new ImageView[list.size()];
        for(int i = 0; i < list.size(); i++){
            dots[i] = (ImageView) pointLayout.getChildAt(i);
        }
        currentIndex = 0;
        dots[currentIndex].setBackgroundResource(R.drawable.dian_down);
    }

    /**
     * 当滚动时更换点的背景图
     */
    private void setDots(int position){
        if(position < 0 || position > list.size() - 1 || currentIndex == position){
            return;
        }
        dots[position].setBackgroundResource(R.drawable.dian_down);
        dots[currentIndex].setBackgroundResource(R.drawable.dian);
        currentIndex = position;
    }

    /**
     * 循环播放图片
     */
    private void loopPlay() {
        /**
         * 开辟线程来控制图片左右轮播
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(LOG_TAG, "Runnable.run isRunning = " + isRunning);
                while (isRunning) {
                    Log.d(LOG_TAG, "In loopPlay.run isSlipped = " + isSlipped);
                    Log.d(LOG_TAG, "In loopPlay.run currentIndex = " + currentIndex);
                    // 处理手动滑动的情况
                    if (isSlipped) {
                        isSlipped = false;
                        Log.d(LOG_TAG, "In loopPlay.run isSlipped was recovered ");
                        what.set(currentIndex);
                    }
                    viewHandler.sendEmptyMessage(what.get());
                    if (what.get() >= list.size() - 1) {
                        flag = false;
                    }
                    if (what.get() < 1) {
                        flag = true;
                    }
                    if (flag) {
                        what.incrementAndGet();
                    } else {
                        what.decrementAndGet();
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
