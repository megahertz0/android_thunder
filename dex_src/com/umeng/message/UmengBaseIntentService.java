package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.text.TextUtils;
import com.taobao.agoo.TaobaoBaseIntentService;
import com.umeng.common.UmLog;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.k;
import com.umeng.socialize.common.SocializeConstants;
import org.json.JSONObject;

public abstract class UmengBaseIntentService extends TaobaoBaseIntentService {
    private static final String a;

    static {
        a = UmengBaseIntentService.class.getName();
    }

    protected void onMessage(Context context, Intent intent) {
        if (Process.getElapsedCpuTime() < 3000) {
            UmLog.i(a, "App is launched by push message");
            PushAgent.setAppLaunchByMessage();
        }
        String stringExtra = intent.getStringExtra("body");
        UmLog.d(a, new StringBuilder("onMessage():[").append(stringExtra).append("]").toString());
        try {
            UMessage uMessage = new UMessage(new JSONObject(stringExtra));
            uMessage.message_id = intent.getStringExtra(SocializeConstants.WEIBO_ID);
            uMessage.task_id = intent.getStringExtra("task_id");
            UTrack.getInstance(getApplicationContext()).trackMsgArrival(uMessage);
            k.a(context).a(uMessage.msg_id, uMessage.display_type);
            k.a(context).a(uMessage.message_id, uMessage.task_id, uMessage.display_type);
            if (TextUtils.equals(UMessage.DISPLAY_TYPE_AUTOUPDATE, uMessage.display_type)) {
                String stringExtra2 = intent.getStringExtra(SocializeConstants.WEIBO_ID);
                String stringExtra3 = intent.getStringExtra("task_id");
                Intent intent2 = new Intent();
                intent2.setPackage(context.getPackageName());
                intent2.setAction(MsgConstant.MESSAGE_AUTOUPDATE_HANDLER_ACTION);
                intent2.putExtra("body", stringExtra);
                intent2.putExtra(SocializeConstants.WEIBO_ID, stringExtra2);
                intent2.putExtra("task_id", stringExtra3);
                context.startService(intent2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            UmLog.d(a, e.toString());
        }
    }

    protected void onError(Context context, String str) {
        UmLog.d(a, new StringBuilder("onError()[").append(str).append("]").toString());
    }

    protected void onRegistered(Context context, String str) {
    }

    protected void onUnregistered(Context context, String str) {
    }
}
