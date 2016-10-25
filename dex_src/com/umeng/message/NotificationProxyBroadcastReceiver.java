package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.umeng.common.UmLog;
import com.umeng.message.entity.UMessage;
import com.umeng.message.entity.UNotificationItem;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationProxyBroadcastReceiver extends BroadcastReceiver {
    public static final int EXTRA_ACTION_CLICK = 10;
    public static final int EXTRA_ACTION_DISMISS = 11;
    public static final int EXTRA_ACTION_NOT_EXIST = -1;
    public static final String EXTRA_KEY_ACTION = "ACTION";
    public static final String EXTRA_KEY_MESSAGE_ID = "MESSAGE_ID";
    public static final String EXTRA_KEY_MSG = "MSG";
    public static final String EXTRA_KEY_NOTIFICATION_ID = "NOTIFICATION_ID";
    public static final String EXTRA_KEY_TASK_ID = "TASK_ID";
    public static final int LOCAL_ACTION_CLICK = 12;
    private static final String a;
    private UHandler b;

    static {
        a = NotificationProxyBroadcastReceiver.class.getName();
    }

    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra(EXTRA_KEY_MSG);
        try {
            int intExtra = intent.getIntExtra(EXTRA_KEY_ACTION, EXTRA_ACTION_NOT_EXIST);
            UmLog.d(a, String.format("onReceive[msg=%s, action=%d]", new Object[]{stringExtra, Integer.valueOf(intExtra)}));
            if (intExtra == 12) {
                a(context);
                return;
            }
            UMessage uMessage = new UMessage(new JSONObject(stringExtra));
            int intExtra2 = intent.getIntExtra(EXTRA_KEY_NOTIFICATION_ID, EXTRA_ACTION_NOT_EXIST);
            uMessage.message_id = intent.getStringExtra(EXTRA_KEY_MESSAGE_ID);
            uMessage.task_id = intent.getStringExtra(EXTRA_KEY_TASK_ID);
            switch (intExtra) {
                case EXTRA_ACTION_CLICK:
                    UmLog.d(a, "click notification");
                    UTrack.getInstance(context).setClearPrevMessage(true);
                    UTrack.getInstance(context).trackMsgClick(uMessage);
                    this.b = PushAgent.getInstance(context).getNotificationClickHandler();
                    if (this.b != null) {
                        uMessage.clickOrDismiss = true;
                        this.b.handleMessage(context, uMessage);
                    }
                    break;
                case EXTRA_ACTION_DISMISS:
                    UmLog.d(a, "dismiss notification");
                    UTrack.getInstance(context).setClearPrevMessage(true);
                    UTrack.getInstance(context).trackMsgDismissed(uMessage);
                    this.b = PushAgent.getInstance(context).getNotificationClickHandler();
                    if (this.b != null) {
                        uMessage.clickOrDismiss = false;
                        this.b.handleMessage(context, uMessage);
                    }
                    break;
            }
            if (MessageSharedPrefs.getInstance(context).getDisplayNotificationNumber() > 0) {
                MessageNotificationQueue.getInstance().remove(new UNotificationItem(intExtra2, uMessage));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(Context context) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            UmLog.e(a, new StringBuilder("handleMessage(): cannot find app: ").append(context.getPackageName()).toString());
            return;
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(268435456);
        context.startActivity(launchIntentForPackage);
        UmLog.d(a, new StringBuilder("handleMessage(): lunach app: ").append(context.getPackageName()).toString());
    }
}
