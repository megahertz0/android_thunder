package com.tencent.wxop.stat;

import android.app.Activity;

public class EasyActivity extends Activity {
    protected void onPause() {
        super.onPause();
        StatService.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }
}
