package com.xunlei.downloadprovider.thirdpart;

import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.thirdpart.a.a;
import com.xunlei.downloadprovider.web.core.JsInterface;

public class ThirdPartActivity extends BaseActivity {
    static final String a;
    public boolean b;

    public ThirdPartActivity() {
        this.b = false;
    }

    static {
        a = ThirdPartActivity.class.getSimpleName();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int checkRequiredPermissionsForLaunch = checkRequiredPermissionsForLaunch(this);
        if (checkRequiredPermissionsForLaunch != 0) {
            setContentView(2130968610);
            if (VERSION.SDK_INT < 16) {
                getWindow().setFlags(JsInterface.MSG_JS_COLLECT_WEBSITE, JsInterface.MSG_JS_COLLECT_WEBSITE);
            } else {
                getWindow().getDecorView().setSystemUiVisibility(1284);
            }
            checkRequiredPermissionsForLaunch = requestRequiredPermissionsForLaunch();
        }
        if (checkRequiredPermissionsForLaunch == 0) {
            setContentView(2130969000);
            a(getIntent());
            finish();
        }
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        this.b = false;
        if ("shortcut_download".equals(intent.getStringExtra(a.a))) {
            BrothersApplication.a().a("launch_from_shortcut_download");
        }
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onRestart() {
        super.onRestart();
    }

    protected void onResume() {
        super.onResume();
        if (VERSION.SDK_INT >= 23 && requestRequiredPermissionsForLaunch() == 0) {
            onRequiredPermissionsForLaunchReady();
        }
    }

    private void a(Intent intent) {
        this.b = true;
        a.a(this, intent);
    }

    public void onRequiredPermissionsForLaunchReady() {
        if (!this.b) {
            a(getIntent());
        }
        finish();
    }

    public static void a(Context context, String str) {
        Parcelable intent = new Intent();
        intent.setAction("com.xulei.downloadprovider.thirdpart.start");
        intent.addFlags(67108864);
        intent.addFlags(1073741824);
        intent.addFlags(268435456);
        intent.putExtra(a.a, str);
        Intent intent2 = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
        intent2.putExtra("android.intent.extra.shortcut.INTENT", intent);
        intent2.putExtra("android.intent.extra.shortcut.NAME", context.getString(2131231362));
        intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", ShortcutIconResource.fromContext(context, 2130839166));
        intent2.putExtra("duplicate", false);
        context.sendBroadcast(intent2);
    }
}
