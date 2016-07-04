package com.ldb.android.ui.layout_tab;

import android.app.TabActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

/**
 * 使用TabWidget和TabHost和TabActivity来实现
 * <p>
 * TabActivity在API13之后被fragment替代了，所以不建议使用</>
 * Created by lsp on 2016/6/30.
 */
public class TabHostTabWidgetTabActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabhost_tabwidget_tabactivity);

        TabHost tabHost = getTabHost(); //(TabHost) findViewById(android.R.id.tabhost);

        tabHost.addTab(tabHost
                .newTabSpec("111")
                .setIndicator("", getResources().getDrawable(R.drawable.wuyong))
                .setContent(R.id.tab1));

        tabHost.addTab(tabHost
                .newTabSpec("222")
                .setIndicator("", getResources().getDrawable(R.drawable.gongsunsheng))
                .setContent(R.id.tab2));

        tabHost.addTab(tabHost
                .newTabSpec("333")
                .setIndicator("", getResources().getDrawable(R.drawable.likui))
                .setContent(R.id.tab3));

        tabHost.setBackgroundColor(Color.argb(150, 22, 70, 150));

        tabHost.setCurrentTab(0);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(TabHostTabWidgetTabActivity.this, tabId, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
