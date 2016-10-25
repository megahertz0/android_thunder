package com.umeng.fb.push;

import android.content.Context;
import android.content.Intent;

public class FeedbackPush {
    private static FeedbackPush b;
    private final String a;
    private IFeedbackPush c;

    public static FeedbackPush getInstance(Context context) {
        if (b == null) {
            b = new FeedbackPush(context);
        }
        return b;
    }

    private FeedbackPush(Context context) {
        this.a = FeedbackPush.class.getName();
        try {
            Class.forName("com.umeng.message.PushAgent");
            this.c = FeedbackPushImpl.getInstance(context);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void init(Class<?> cls, boolean z) {
        this.c.init(cls, z);
    }

    public void init(boolean z) {
        this.c.init(z);
    }

    public boolean onFBMessage(Intent intent) {
        return this.c.onFBMessage(intent);
    }

    public boolean dealFBMessage(FBMessage fBMessage) {
        return this.c.dealFBMessage(fBMessage);
    }

    public void enable() {
        this.c.enable();
    }

    public void disable() {
        this.c.disable();
    }
}
