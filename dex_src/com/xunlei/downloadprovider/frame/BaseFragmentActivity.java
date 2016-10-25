package com.xunlei.downloadprovider.frame;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class BaseFragmentActivity extends FragmentActivity {
    public FragmentManager mFragmentManager;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mFragmentManager = getSupportFragmentManager();
    }

    public void onLowMemory() {
        super.onLowMemory();
    }
}
