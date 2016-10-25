package com.xunlei.downloadprovider.discovery.kuainiao;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.tdlive.R;

public class KuaiNiaoActivity extends ThunderTask {
    public static final String a;
    private int b;
    private BaseFragment c;
    private boolean d;

    public KuaiNiaoActivity() {
        this.b = 2131756122;
        this.d = false;
    }

    static {
        a = KuaiNiaoActivity.class.getSimpleName();
    }

    public static Intent a(Context context, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("from_where", z);
        Intent intent = new Intent(context, KuaiNiaoActivity.class);
        intent.putExtras(bundle);
        return intent;
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
                this.c = new KuaiNiaoFragment();
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
        if (i == 4 && this.c != null && this.c.onBackPressed()) {
            return true;
        }
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        finish();
        return true;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.translate_between_interface_left_in, R.anim.translate_between_interface_right_out);
    }
}
