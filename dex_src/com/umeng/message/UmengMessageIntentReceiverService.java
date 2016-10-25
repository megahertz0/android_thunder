package com.umeng.message;

import android.content.Context;
import org.android.agoo.message.MessageReceiverService;

public class UmengMessageIntentReceiverService extends MessageReceiverService {
    public String getIntentServiceClassName(Context context) {
        return MsgConstant.DEFAULT_INTENT_SERVICE_CLASS_NAME;
    }
}
