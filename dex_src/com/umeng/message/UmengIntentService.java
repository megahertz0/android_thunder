package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;

public class UmengIntentService extends UmengBaseIntentService {
    private static final String a;

    static {
        a = UmengIntentService.class.getName();
    }

    protected void onMessage(Context context, Intent intent) {
        super.onMessage(context, intent);
        String stringExtra = intent.getStringExtra("body");
        String stringExtra2 = intent.getStringExtra(SocializeConstants.WEIBO_ID);
        String stringExtra3 = intent.getStringExtra("task_id");
        String pushIntentServiceClass = MessageSharedPrefs.getInstance(context).getPushIntentServiceClass();
        if (pushIntentServiceClass.equalsIgnoreCase(a.d)) {
            try {
                Intent intent2 = new Intent();
                intent2.setPackage(context.getPackageName());
                intent2.setAction(MsgConstant.MESSAGE_MESSAGE_HANDLER_ACTION);
                intent2.putExtra("body", stringExtra);
                intent2.putExtra(SocializeConstants.WEIBO_ID, stringExtra2);
                intent2.putExtra("task_id", stringExtra3);
                context.startService(intent2);
                return;
            } catch (Exception e) {
                e.getMessage();
            }
        }
        Intent intent3 = new Intent();
        intent3.setClassName(context, pushIntentServiceClass);
        intent3.setPackage(context.getPackageName());
        intent3.putExtra("body", stringExtra);
        intent3.putExtra(SocializeConstants.WEIBO_ID, stringExtra2);
        intent3.putExtra("task_id", stringExtra3);
        context.startService(intent3);
    }
}
