package com.xiaomi.mipush.sdk;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.mipush.sdk.MiPushClient.MiPushClientCallback;
import com.xunlei.tdlive.R;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PushMessageHandler extends IntentService {
    private static List<MiPushClientCallback> a;

    static interface a extends Serializable {
    }

    static {
        a = new ArrayList();
    }

    public PushMessageHandler() {
        super("mipush message handler");
    }

    protected static void a() {
        synchronized (a) {
            a.clear();
        }
    }

    public static void a(long j, String str, String str2) {
        synchronized (a) {
            for (MiPushClientCallback miPushClientCallback : a) {
                miPushClientCallback.onInitializeResult(j, str, str2);
            }
        }
    }

    public static void a(Context context, MiPushMessage miPushMessage) {
        synchronized (a) {
            for (MiPushClientCallback miPushClientCallback : a) {
                if (a(miPushMessage.getCategory(), miPushClientCallback.getCategory())) {
                    miPushClientCallback.onReceiveMessage(miPushMessage.getContent(), miPushMessage.getAlias(), miPushMessage.getTopic(), miPushMessage.isNotified());
                    miPushClientCallback.onReceiveMessage(miPushMessage);
                }
            }
        }
    }

    public static void a(Context context, a aVar) {
        String str = null;
        if (aVar instanceof MiPushMessage) {
            a(context, (MiPushMessage) aVar);
        } else if (aVar instanceof MiPushCommandMessage) {
            MiPushCommandMessage miPushCommandMessage = (MiPushCommandMessage) aVar;
            String command = miPushCommandMessage.getCommand();
            List commandArguments;
            if (MiPushClient.COMMAND_REGISTER.equals(command)) {
                commandArguments = miPushCommandMessage.getCommandArguments();
                if (!(commandArguments == null || commandArguments.isEmpty())) {
                    str = (String) commandArguments.get(0);
                }
                a(miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), str);
            } else if (MiPushClient.COMMAND_SET_ALIAS.equals(command) || MiPushClient.COMMAND_UNSET_ALIAS.equals(command) || MiPushClient.COMMAND_SET_ACCEPT_TIME.equals(command)) {
                a(context, miPushCommandMessage.getCategory(), command, miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), miPushCommandMessage.getCommandArguments());
            } else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC.equals(command)) {
                commandArguments = miPushCommandMessage.getCommandArguments();
                r5 = (commandArguments == null || commandArguments.isEmpty()) ? null : (String) commandArguments.get(0);
                a(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), r5);
            } else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC.equals(command)) {
                commandArguments = miPushCommandMessage.getCommandArguments();
                r5 = (commandArguments == null || commandArguments.isEmpty()) ? null : (String) commandArguments.get(0);
                b(context, miPushCommandMessage.getCategory(), miPushCommandMessage.getResultCode(), miPushCommandMessage.getReason(), r5);
            }
        }
    }

    protected static void a(Context context, String str, long j, String str2, String str3) {
        synchronized (a) {
            for (MiPushClientCallback miPushClientCallback : a) {
                if (a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onSubscribeResult(j, str2, str3);
                }
            }
        }
    }

    protected static void a(Context context, String str, String str2, long j, String str3, List<String> list) {
        synchronized (a) {
            for (MiPushClientCallback miPushClientCallback : a) {
                if (a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onCommandResult(str2, j, str3, list);
                }
            }
        }
    }

    protected static void a(MiPushClientCallback miPushClientCallback) {
        synchronized (a) {
            if (!a.contains(miPushClientCallback)) {
                a.add(miPushClientCallback);
            }
        }
    }

    protected static boolean a(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || TextUtils.equals(str, str2);
    }

    protected static void b(Context context, String str, long j, String str2, String str3) {
        synchronized (a) {
            for (MiPushClientCallback miPushClientCallback : a) {
                if (a(str, miPushClientCallback.getCategory())) {
                    miPushClientCallback.onUnsubscribeResult(j, str2, str3);
                }
            }
        }
    }

    public static boolean b() {
        return a.isEmpty();
    }

    protected void onHandleIntent(Intent intent) {
        try {
            if ("com.xiaomi.mipush.sdk.WAKEUP".equals(intent.getAction())) {
                if (a.a((Context) this).i()) {
                    j.a((Context) this).a();
                }
            } else if (1 != PushMessageHelper.getPushMode(this)) {
                Intent intent2 = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
                intent2.setPackage(getPackageName());
                intent2.putExtras(intent);
                try {
                    List<ResolveInfo> queryBroadcastReceivers = getPackageManager().queryBroadcastReceivers(intent2, R.styleable.AppCompatTheme_actionModeCutDrawable);
                    if (queryBroadcastReceivers != null) {
                        for (ResolveInfo resolveInfo : queryBroadcastReceivers) {
                            if (resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName.equals(getPackageName())) {
                                break;
                            }
                        }
                    }
                    ResolveInfo resolveInfo2 = null;
                    if (resolveInfo2 != null) {
                        ((PushMessageReceiver) Class.forName(resolveInfo2.activityInfo.name).newInstance()).onReceive(getApplicationContext(), intent2);
                    } else {
                        b.d("cannot find the receiver to handler this message, check your manifest");
                    }
                } catch (Throwable e) {
                    b.a(e);
                }
            } else if (b()) {
                b.d("receive a message before application calling initialize");
            } else {
                a a = i.a((Context) this).a(intent);
                if (a != null) {
                    a((Context) this, a);
                }
            }
        } catch (Throwable e2) {
            b.a(e2);
        }
    }
}
