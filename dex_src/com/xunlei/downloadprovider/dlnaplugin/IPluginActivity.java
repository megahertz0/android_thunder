package com.xunlei.downloadprovider.dlnaplugin;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public interface IPluginActivity {
    boolean IDispatchTouchEvent(MotionEvent motionEvent);

    void IFinish();

    View IGetContentView();

    Handler IGetInHandler();

    Resources IGetResource();

    void IInit(String str, String str2, Activity activity, ClassLoader classLoader, PackageInfo packageInfo, boolean z, int i);

    boolean IIsWrapContent();

    void IOnActivityResult(int i, int i2, Intent intent);

    void IOnConfigurationChanged(Configuration configuration);

    void IOnCreate(Bundle bundle);

    boolean IOnCreateOptionsMenu(Menu menu);

    void IOnDestroy();

    boolean IOnKeyDown(int i, KeyEvent keyEvent);

    boolean IOnKeyMultiple(int i, int i2, KeyEvent keyEvent);

    boolean IOnKeyUp(int i, KeyEvent keyEvent);

    boolean IOnMenuItemSelected(int i, MenuItem menuItem);

    void IOnNewIntent(Intent intent);

    boolean IOnOptionsItemSelected(MenuItem menuItem);

    void IOnPause();

    boolean IOnPrepareOptionsMenu(Menu menu);

    void IOnRestart();

    void IOnRestoreInstanceState(Bundle bundle);

    void IOnResume();

    void IOnSaveInstanceState(Bundle bundle);

    void IOnSetTheme();

    void IOnStart();

    void IOnStop();

    boolean IOnTouchEvent(MotionEvent motionEvent);

    void IOnUserInteraction();

    void IOnWindowFocusChanged(boolean z);

    void ISetIntent(Intent intent);

    void ISetIsTab();

    void ISetOutHandler(Handler handler);
}
