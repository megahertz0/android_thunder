package com.umeng.message;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import com.umeng.common.UmLog;

public abstract class UmengMessageService extends IntentService {
    private static final String a;

    public abstract void onMessage(Context context, Intent intent);

    static {
        a = UmengMessageService.class.getSimpleName();
    }

    public UmengMessageService() {
        super("UmengMessageService");
    }

    public void onCreate() {
        super.onCreate();
    }

    protected void onHandleIntent(Intent intent) {
        onMessage(this, intent);
        UmLog.i(a, new StringBuilder("message:").append(intent.getStringExtra("body")).toString());
    }
}
