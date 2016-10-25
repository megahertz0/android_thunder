package com.mediav.ads.sdk.interfaces;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;

public interface ActivityBridge {
    boolean dispatchKeyEvent(KeyEvent keyEvent);

    void onConfigurationChanged(Configuration configuration);

    void onCreate(Bundle bundle);

    void onDestroy();

    void onInit(Activity activity);

    void onLowMemory();

    void onNewIntent(Intent intent);

    void onPause();

    void onRestart();

    void onRestoreInstanceState(Bundle bundle);

    void onResume();

    void onSaveInstanceState(Bundle bundle);

    void onStart();

    void onStop();

    void onTrimMemory(int i);
}
