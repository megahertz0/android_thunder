package com.mediav.ads.sdk.adcore;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.mediav.ads.sdk.interfaces.ActivityBridge;

public class MvActivity extends Activity {
    public static ActivityBridge activityBridge;

    public MvActivity() {
        if (activityBridge != null) {
            activityBridge.onInit(this);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (activityBridge != null) {
            activityBridge.onCreate(bundle);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (activityBridge != null) {
            activityBridge.onDestroy();
            activityBridge = null;
        }
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (activityBridge != null) {
            activityBridge.onNewIntent(intent);
        }
    }

    protected void onResume() {
        super.onResume();
        if (activityBridge != null) {
            activityBridge.onResume();
        }
    }

    protected void onPause() {
        super.onPause();
        if (activityBridge != null) {
            activityBridge.onPause();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (activityBridge != null) {
            activityBridge.onConfigurationChanged(configuration);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        if (activityBridge != null) {
            activityBridge.onLowMemory();
        }
    }

    public void onTrimMemory(int i) {
        super.onTrimMemory(i);
        if (activityBridge != null) {
            activityBridge.onTrimMemory(i);
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return activityBridge != null ? activityBridge.dispatchKeyEvent(keyEvent) : super.dispatchKeyEvent(keyEvent);
    }

    protected void onStart() {
        super.onStart();
        if (activityBridge != null) {
            activityBridge.onStart();
        }
    }

    protected void onRestart() {
        super.onRestart();
        if (activityBridge != null) {
            activityBridge.onRestart();
        }
    }

    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (activityBridge != null) {
            activityBridge.onRestoreInstanceState(bundle);
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (activityBridge != null) {
            activityBridge.onSaveInstanceState(bundle);
        }
    }

    protected void onStop() {
        super.onStop();
        if (activityBridge != null) {
            activityBridge.onStop();
        }
    }
}
