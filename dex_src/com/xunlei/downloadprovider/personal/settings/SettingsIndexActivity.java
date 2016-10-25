package com.xunlei.downloadprovider.personal.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.xunlei.downloadprovider.app.BaseActivity;

public class SettingsIndexActivity extends BaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968781);
        FragmentTransaction beginTransaction = this.mFragmentManager.beginTransaction();
        beginTransaction.add(2131756077, Fragment.instantiate(this, SettingsIndexFragment.class.getName()), SettingsIndexFragment.class.getName());
        beginTransaction.commitAllowingStateLoss();
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }
}
