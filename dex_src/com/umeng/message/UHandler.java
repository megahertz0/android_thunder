package com.umeng.message;

import android.content.Context;
import com.umeng.message.entity.UMessage;

public interface UHandler {
    void handleMessage(Context context, UMessage uMessage);
}
