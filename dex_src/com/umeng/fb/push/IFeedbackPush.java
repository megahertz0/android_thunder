package com.umeng.fb.push;

import android.content.Intent;

public interface IFeedbackPush {
    boolean dealFBMessage(FBMessage fBMessage);

    void disable();

    void enable();

    void init(Class<?> cls, boolean z);

    void init(boolean z);

    boolean onFBMessage(Intent intent);
}
