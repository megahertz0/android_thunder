package com.umeng.message;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.umeng.common.UmLog;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.k;
import java.lang.reflect.Method;
import java.util.Map.Entry;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

public class UmengNotificationClickHandler implements UHandler {
    private static final String a;

    static {
        a = UmengNotificationClickHandler.class.getName();
    }

    public void handleMessage(Context context, UMessage uMessage) {
        try {
            if (!uMessage.clickOrDismiss) {
                dismissNotification(context, uMessage);
            } else if (TextUtils.equals("autoupdate", uMessage.display_type) && PushAgent.getInstance(context).isIncludesUmengUpdateSDK()) {
                autoUpdate(context, uMessage);
            } else {
                if (!TextUtils.isEmpty(uMessage.after_open)) {
                    if (TextUtils.equals("go_url", uMessage.after_open)) {
                        openUrl(context, uMessage);
                        return;
                    } else if (TextUtils.equals("go_activity", uMessage.after_open)) {
                        openActivity(context, uMessage);
                        return;
                    } else if (TextUtils.equals("go_custom", uMessage.after_open)) {
                        dealWithCustomAction(context, uMessage);
                        return;
                    } else if (TextUtils.equals("go_app", uMessage.after_open)) {
                        launchApp(context, uMessage);
                        return;
                    }
                }
                if (uMessage.url != null && !TextUtils.isEmpty(uMessage.url.trim())) {
                    openUrl(context, uMessage);
                } else if (uMessage.activity != null && !TextUtils.isEmpty(uMessage.activity.trim())) {
                    openActivity(context, uMessage);
                } else if (uMessage.custom == null || TextUtils.isEmpty(uMessage.custom.trim())) {
                    launchApp(context, uMessage);
                } else {
                    dealWithCustomAction(context, uMessage);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissNotification(Context context, UMessage uMessage) {
    }

    public void autoUpdate(Context context, UMessage uMessage) {
        try {
            Object g = k.a(context).g();
            Class forName = Class.forName("com.umeng.update.UmengUpdateAgent");
            Class forName2 = Class.forName("com.umeng.update.UpdateResponse");
            Method method = forName.getMethod("showUpdateDialog", new Class[]{Context.class, forName2});
            if (g != null) {
                method.invoke(forName, new Object[]{context, forName2.cast(g)});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void openUrl(Context context, UMessage uMessage) {
        if (uMessage.url != null && !TextUtils.isEmpty(uMessage.url.trim())) {
            UmLog.d(a, new StringBuilder("handleMessage(): open url: ").append(uMessage.url).toString());
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(uMessage.url));
            a(intent, uMessage);
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
        }
    }

    public void openActivity(Context context, UMessage uMessage) {
        if (uMessage.activity != null && !TextUtils.isEmpty(uMessage.activity.trim())) {
            Intent intent = new Intent();
            a(intent, uMessage);
            intent.setClassName(context, uMessage.activity);
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            context.startActivity(intent);
        }
    }

    public void launchApp(Context context, UMessage uMessage) {
        Intent launchIntentForPackage = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
        if (launchIntentForPackage == null) {
            UmLog.e(a, new StringBuilder("handleMessage(): cannot find app: ").append(context.getPackageName()).toString());
            return;
        }
        launchIntentForPackage.setPackage(null);
        launchIntentForPackage.addFlags(ClientDefaults.MAX_MSG_SIZE);
        a(launchIntentForPackage, uMessage);
        context.startActivity(launchIntentForPackage);
        UmLog.d(a, new StringBuilder("handleMessage(): lunach app: ").append(context.getPackageName()).toString());
    }

    public void dealWithCustomAction(Context context, UMessage uMessage) {
    }

    private Intent a(Intent intent, UMessage uMessage) {
        if (!(intent == null || uMessage == null || uMessage.extra == null)) {
            for (Entry entry : uMessage.extra.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                if (str != null) {
                    intent.putExtra(str, str2);
                }
            }
        }
        return intent;
    }
}
