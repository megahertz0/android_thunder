package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.xiaomi.xmpush.thrift.i;
import com.xiaomi.xmpush.thrift.w;
import com.xunlei.tdlive.R;
import java.util.List;

public class PushMessageHelper {
    public static final String KEY_COMMAND = "key_command";
    public static final String KEY_MESSAGE = "key_message";
    public static final int MESSAGE_COMMAND = 3;
    public static final int MESSAGE_QUIT = 4;
    public static final int MESSAGE_RAW = 1;
    public static final int MESSAGE_SENDMESSAGE = 2;
    public static final String MESSAGE_TYPE = "message_type";
    public static final int PUSH_MODE_BROADCAST = 2;
    public static final int PUSH_MODE_CALLBACK = 1;
    private static int pushMode;

    static {
        pushMode = 0;
    }

    public static MiPushCommandMessage generateCommandMessage(String str, List<String> list, long j, String str2, String str3) {
        MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
        miPushCommandMessage.setCommand(str);
        miPushCommandMessage.setCommandArguments(list);
        miPushCommandMessage.setResultCode(j);
        miPushCommandMessage.setReason(str2);
        miPushCommandMessage.setCategory(str3);
        return miPushCommandMessage;
    }

    public static MiPushMessage generateMessage(w wVar, i iVar, boolean z) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.setMessageId(wVar.c());
        if (!TextUtils.isEmpty(wVar.j())) {
            miPushMessage.setMessageType(PUSH_MODE_CALLBACK);
            miPushMessage.setAlias(wVar.j());
        } else if (!TextUtils.isEmpty(wVar.h())) {
            miPushMessage.setMessageType(PUSH_MODE_BROADCAST);
            miPushMessage.setTopic(wVar.h());
        } else if (TextUtils.isEmpty(wVar.r())) {
            miPushMessage.setMessageType(0);
        } else {
            miPushMessage.setMessageType(MESSAGE_COMMAND);
            miPushMessage.setUserAccount(wVar.r());
        }
        miPushMessage.setCategory(wVar.p());
        if (wVar.l() != null) {
            miPushMessage.setContent(wVar.l().f());
        }
        if (iVar != null) {
            if (TextUtils.isEmpty(miPushMessage.getMessageId())) {
                miPushMessage.setMessageId(iVar.b());
            }
            if (TextUtils.isEmpty(miPushMessage.getTopic())) {
                miPushMessage.setTopic(iVar.f());
            }
            miPushMessage.setDescription(iVar.j());
            miPushMessage.setTitle(iVar.h());
            miPushMessage.setNotifyType(iVar.l());
            miPushMessage.setNotifyId(iVar.q());
            miPushMessage.setPassThrough(iVar.o());
            miPushMessage.setExtra(iVar.s());
        }
        miPushMessage.setNotified(z);
        return miPushMessage;
    }

    public static int getPushMode(Context context) {
        if (pushMode == 0) {
            if (isUseCallbackPushMode(context)) {
                setPushMode(PUSH_MODE_CALLBACK);
            } else {
                setPushMode(PUSH_MODE_BROADCAST);
            }
        }
        return pushMode;
    }

    private static boolean isIntentAvailable(Context context, Intent intent) {
        try {
            List queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, R.styleable.AppCompatTheme_actionModeCutDrawable);
            return (queryBroadcastReceivers == null || queryBroadcastReceivers.isEmpty()) ? false : true;
        } catch (Exception e) {
            return true;
        }
    }

    public static boolean isUseCallbackPushMode(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setClassName(context.getPackageName(), "com.xiaomi.mipush.sdk.PushServiceReceiver");
        return isIntentAvailable(context, intent);
    }

    public static void sendCommandMessageBroadcast(Context context, MiPushCommandMessage miPushCommandMessage) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra(MESSAGE_TYPE, MESSAGE_COMMAND);
        intent.putExtra(KEY_COMMAND, miPushCommandMessage);
        new PushServiceReceiver().onReceive(context, intent);
    }

    public static void sendQuitMessageBroadcast(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra(MESSAGE_TYPE, MESSAGE_QUIT);
        new PushServiceReceiver().onReceive(context, intent);
    }

    private static void setPushMode(int i) {
        pushMode = i;
    }
}
