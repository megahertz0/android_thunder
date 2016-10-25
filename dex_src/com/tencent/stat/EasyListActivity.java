package com.tencent.stat;

import android.app.ListActivity;

public class EasyListActivity extends ListActivity {
    protected void onPause() {
        super.onPause();
        StatService.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        StatService.onResume(this);
    }
}
