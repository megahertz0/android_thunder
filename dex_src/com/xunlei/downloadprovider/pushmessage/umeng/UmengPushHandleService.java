package com.xunlei.downloadprovider.pushmessage.umeng;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.umeng.fb.push.FeedbackPush;
import com.umeng.message.UmengMessageService;
import com.umeng.message.entity.UMessage;
import com.xunlei.downloadprovider.pushmessage.a.a;
import com.xunlei.downloadprovider.pushmessage.d;
import com.xunlei.downloadprovider.pushmessage.g;
import com.xunlei.downloadprovider.pushmessage.h;
import com.xunlei.downloadprovider.util.ai;
import org.json.JSONObject;

public class UmengPushHandleService extends UmengMessageService {
    static final String a;

    static {
        a = UmengPushHandleService.class.getSimpleName();
    }

    public void onCreate() {
        super.onCreate();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onMessage(Context context, Intent intent) {
        try {
            if (!ai.a() && !FeedbackPush.getInstance(context).onFBMessage(intent)) {
                String stringExtra = intent.getStringExtra("body");
                new StringBuilder("custom=").append(new UMessage(new JSONObject(stringExtra)).custom);
                a b = h.b(stringExtra);
                if (b != null) {
                    b.u = 2;
                    d.a(b);
                    if (!com.xunlei.downloadprovider.pushmessage.c.a.a().a(b.l)) {
                        if (!b.l.equals(com.umeng.a.d)) {
                            com.xunlei.downloadprovider.pushmessage.c.a.a().a(b.l, stringExtra);
                        }
                        CharSequence charSequence = b.m;
                        if (TextUtils.isEmpty(charSequence)) {
                            charSequence = b.n;
                        }
                        if (TextUtils.isEmpty(charSequence)) {
                            g.a(context, b, null);
                        } else {
                            d.a(context, b);
                        }
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
