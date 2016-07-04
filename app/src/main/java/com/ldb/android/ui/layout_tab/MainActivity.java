package com.ldb.android.ui.layout_tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnTabActivityHostWidget, btnViewPagerAndPagerAdapter,
            btnFragmentAndFManager, btnViewPagerAndFragmentPagerAdapter,
            btnViewPagerAndPagerTitleOrPagerTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init(){
        btnTabActivityHostWidget = (Button) findViewById(R.id.btnTabActivityHostWidget);
        btnViewPagerAndPagerAdapter =
                (Button) findViewById(R.id.btnViewPagerAndPagerAdapter);
        btnFragmentAndFManager = (Button) findViewById(R.id.btnFragmentAndFManager);
        btnViewPagerAndFragmentPagerAdapter =
                (Button) findViewById(R.id.btnViewPagerAndFragmentPagerAdapter);
        btnViewPagerAndPagerTitleOrPagerTab =
                (Button) findViewById(R.id.btnViewPagerAndPagerTitleOrPagerTab);

        btnTabActivityHostWidget.setOnClickListener(this);
        btnViewPagerAndPagerAdapter.setOnClickListener(this);
        btnFragmentAndFManager.setOnClickListener(this);
        btnViewPagerAndFragmentPagerAdapter.setOnClickListener(this);
        btnViewPagerAndPagerTitleOrPagerTab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch(v.getId()){
            case R.id.btnTabActivityHostWidget:
                intent.setClass(this, TabHostTabWidgetTabActivity.class);
                break;
            case R.id.btnViewPagerAndPagerAdapter:
                intent.setClass(this, ViewPagerAndPagerAdapterActivity.class);
                break;

            case R.id.btnFragmentAndFManager:
                intent.setClass(this, FragmentAndFManagerActivity.class);
                break;
            case R.id.btnViewPagerAndFragmentPagerAdapter:
                intent.setClass(this, ViewPagerAndFragmentPagerAdapterActivity.class);
                break;
            case R.id.btnViewPagerAndPagerTitleOrPagerTab:
                intent.setClass(this, ViewPagerAndPagerTitleOrPagerTabActivity.class);
                break;

        }

        startActivity(intent);
    }
}
