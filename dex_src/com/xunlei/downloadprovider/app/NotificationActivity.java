package com.xunlei.downloadprovider.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import com.xunlei.downloadprovider.commonview.dialog.d;

public class NotificationActivity extends BaseActivity {
    private static String a;
    private static Handler b;
    private QuitBroadcastReceiver c;
    private int d;
    private d e;

    public class QuitBroadcastReceiver extends BroadcastReceiver {
        public void onReceive(Context context, Intent intent) {
            if ("NOTIFICATION_ACTIVITI_QUIT".equals(intent.getAction())) {
                NotificationActivity.this.finish();
            }
        }
    }

    static {
        a = "com.xunlei.downloadprovider.app.showtype";
    }

    public static void a(Context context, Handler handler) {
        b = handler;
        Intent intent = new Intent();
        intent.setClass(context, NotificationActivity.class);
        intent.addFlags(268435456);
        intent.putExtra(a, 0);
        context.startActivity(intent);
    }

    public void onCreate(Bundle bundle) {
        CharSequence string;
        CharSequence string2;
        CharSequence string3;
        OnClickListener qVar;
        boolean z = true;
        OnClickListener onClickListener = null;
        super.onCreate(bundle);
        this.d = getIntent().getIntExtra(a, 0);
        new StringBuilder().append(getClass()).append("---initUI---mShowType---").append(this.d).append("---").append(Thread.currentThread().getId());
        if (this.d == 0) {
            string = getString(2131231756);
            string2 = getString(2131231746);
            string3 = getString(2131231746);
            onClickListener = new p(this);
            qVar = new q(this);
        } else if (this.d == 1) {
            string = getString(2131231754);
            string2 = getString(2131231753);
            string3 = getString(2131231745);
            onClickListener = new r(this);
            s sVar = new s(this);
            z = false;
        } else if (this.d == 2) {
            string = getString(2131231757);
            string2 = getString(2131231753);
            string3 = getString(2131231745);
            t tVar = new t(this);
            onClickListener = new u(this);
            t tVar2 = tVar;
            z = false;
        } else {
            z = false;
            string3 = null;
            string2 = null;
            string = null;
            qVar = null;
        }
        this.e = new d(this);
        this.e.setOnDismissListener(new v(this));
        this.e.a(string);
        this.e.c(string2);
        this.e.d(string3);
        this.e.a(onClickListener);
        this.e.b(qVar);
        this.e.a(z);
        this.e.show();
        this.c = new QuitBroadcastReceiver();
        registerReceiver(this.c, new IntentFilter("NOTIFICATION_ACTIVITI_QUIT"));
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.c);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }
}
