package com.xunlei.tdlive.control;

import android.content.Context;
import android.view.View;
import android.widget.TabHost.TabContentFactory;

class FragmentTabHost$a implements TabContentFactory {
    private final Context a;

    public FragmentTabHost$a(Context context) {
        this.a = context;
    }

    public View createTabContent(String str) {
        View view = new View(this.a);
        view.setMinimumWidth(0);
        view.setMinimumHeight(0);
        return view;
    }
}
