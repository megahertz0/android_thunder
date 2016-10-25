package com.xunlei.downloadprovider.personal.lixianspace;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.task.ThunderTask;

public class LixianSpaceActivity extends ThunderTask {
    public static final String a;
    private int b;
    private BaseFragment c;

    public LixianSpaceActivity() {
        this.b = 2131756122;
    }

    static {
        a = LixianSpaceActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968791);
        a(getIntent());
    }

    public void onResume() {
        super.onResume();
    }

    protected void onNewIntent(Intent intent) {
        a(intent);
    }

    private void a(Intent intent) {
        if (intent != null) {
            setIntent(intent);
            Intent intent2 = getIntent();
            if (intent2 != null) {
                this.c = new LixianSpaceFragment();
                if (intent2.getExtras() != null) {
                    this.c.setExtras(intent2.getExtras());
                }
                FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
                beginTransaction.replace(this.b, this.c);
                beginTransaction.commitAllowingStateLoss();
            }
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return (i == 4 && this.c != null && this.c.onBackPressed()) ? true : super.onKeyDown(i, keyEvent);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void finish() {
        super.finish();
    }
}
