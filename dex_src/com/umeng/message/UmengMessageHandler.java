package com.umeng.message;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.text.TextUtils;
import com.umeng.a;
import com.umeng.common.UmLog;
import com.umeng.common.c;
import com.umeng.message.entity.UMessage;
import com.umeng.message.entity.UNotificationItem;
import com.umeng.message.proguard.g;
import com.umeng.message.proguard.i;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class UmengMessageHandler implements UHandler {
    private static int a = 0;
    private static final String b;
    private static Date c = null;
    private static String d = null;
    private static final String g = "umeng_push_notification_default_large_icon";
    private static final String h = "umeng_push_notification_default_small_icon";
    private static final String i = "umeng_push_notification_default_sound";
    private UMessage e;
    private int f;

    public UmengMessageHandler() {
        this.e = null;
    }

    static {
        a = 64;
        b = UmengMessageHandler.class.getName();
        d = "9999999999999";
    }

    public void setPrevMessage(UMessage uMessage) {
        this.e = uMessage;
    }

    public void handleMessage(Context context, UMessage uMessage) {
        if (UMessage.DISPLAY_TYPE_NOTIFICATION.equals(uMessage.display_type)) {
            dealWithNotificationMessage(context, uMessage);
        } else if (UMessage.DISPLAY_TYPE_CUSTOM.equals(uMessage.display_type)) {
            UTrack.getInstance(context).setClearPrevMessage(false);
            dealWithCustomMessage(context, uMessage);
        }
    }

    public void dealWithNotificationMessage(Context context, UMessage uMessage) {
        UmLog.d(b, new StringBuilder("notify: ").append(uMessage.getRaw().toString()).toString());
        if (!uMessage.hasResourceFromInternet() || MessageSharedPrefs.getInstance(context).hasMessageResourceDownloaded(uMessage.msg_id) || !startDownloadResourceService(context, uMessage)) {
            String substring;
            String lastMessageMsgID = MessageSharedPrefs.getInstance(context).getLastMessageMsgID();
            lastMessageMsgID = a.d.equals(lastMessageMsgID) ? a.d : lastMessageMsgID.substring(R.styleable.Toolbar_contentInsetLeft, R.styleable.Toolbar_navigationIcon);
            if (uMessage.msg_id != null && 22 == uMessage.msg_id.length() && uMessage.msg_id.startsWith("u")) {
                MessageSharedPrefs.getInstance(context).setLastMessageMsgID(uMessage.msg_id);
                substring = uMessage.msg_id.substring(R.styleable.Toolbar_contentInsetLeft, R.styleable.Toolbar_navigationIcon);
            } else {
                substring = d;
            }
            boolean z = a.d.equals(lastMessageMsgID) ? true : substring.compareToIgnoreCase(lastMessageMsgID) >= 0;
            MessageSharedPrefs.getInstance(context).removeMessageResouceRecord(uMessage.msg_id);
            Notification notification = getNotification(context, uMessage);
            if (notification != null) {
                int i = notification.icon;
            }
            if (notification == null) {
                Builder builder = new Builder(context);
                if (a(context, builder, uMessage)) {
                    builder.setContentTitle(uMessage.title).setContentText(uMessage.text).setTicker(uMessage.ticker).setAutoCancel(true);
                    notification = builder.getNotification();
                } else {
                    return;
                }
            }
            this.f = new Random(System.nanoTime()).nextInt();
            PendingIntent clickPendingIntent = getClickPendingIntent(context, uMessage);
            notification.deleteIntent = getDismissPendingIntent(context, uMessage);
            notification.contentIntent = clickPendingIntent;
            int notificationDefaults = getNotificationDefaults(context, uMessage);
            if ((notificationDefaults & 1) != 0) {
                Uri sound = getSound(context, uMessage);
                if (sound != null) {
                    notification.sound = getSound(context, uMessage);
                }
                if (sound != null) {
                    notificationDefaults ^= 1;
                }
            }
            notification.defaults = notificationDefaults;
            a(context, notification, z, uMessage);
        }
    }

    private void a(Context context, Notification notification, boolean z, UMessage uMessage) {
        try {
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
            int i = this.f;
            if (!(g.c(context) && g.b(context) && !PushAgent.getInstance(context).getNotificationOnForeground()) && (MessageSharedPrefs.getInstance(context).getDisplayNotificationNumber() != 1 || z)) {
                if (MessageSharedPrefs.getInstance(context).getDisplayNotificationNumber() > 0) {
                    while (MessageNotificationQueue.getInstance().size() >= MessageSharedPrefs.getInstance(context).getDisplayNotificationNumber()) {
                        UNotificationItem pollFirst = MessageNotificationQueue.getInstance().pollFirst();
                        notificationManager.cancel(pollFirst.id);
                        UTrack.getInstance(context).setClearPrevMessage(false);
                        UTrack.getInstance(context).trackMsgDismissed(pollFirst.message);
                    }
                    MessageNotificationQueue.getInstance().addLast(new UNotificationItem(i, uMessage));
                }
                notificationManager.notify(i, notification);
                return;
            }
            UTrack.getInstance(context).setClearPrevMessage(false);
            UTrack.getInstance(context).trackMsgDismissed(uMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"NewApi", "Wakelock"})
    private void a(Context context) {
        try {
            PowerManager powerManager = (PowerManager) context.getSystemService("power");
            boolean z = false;
            if (VERSION.SDK_INT >= 7) {
                z = new AnonymousClass_1(this, powerManager).a();
            } else {
                UmLog.d(b, "android os version < 7, skip checking screen on status");
            }
            UmLog.d(b, new StringBuilder("screen on.................................").append(z).toString());
            if (!z) {
                powerManager.newWakeLock(805306374, "MyLock").acquire(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dealWithCustomMessage(Context context, UMessage uMessage) {
    }

    public PendingIntent getClickPendingIntent(Context context, UMessage uMessage) {
        Intent intent = new Intent();
        intent.setClass(context, NotificationProxyBroadcastReceiver.class);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MSG, uMessage.getRaw().toString());
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_ACTION, XZBDevice.Stop);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MESSAGE_ID, uMessage.message_id);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_NOTIFICATION_ID, this.f);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_TASK_ID, uMessage.task_id);
        return PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, 268435456);
    }

    public PendingIntent getDismissPendingIntent(Context context, UMessage uMessage) {
        Intent intent = new Intent();
        intent.setClass(context, NotificationProxyBroadcastReceiver.class);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MSG, uMessage.getRaw().toString());
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_ACTION, XZBDevice.Success);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_MESSAGE_ID, uMessage.message_id);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_NOTIFICATION_ID, this.f);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_TASK_ID, uMessage.task_id);
        return PendingIntent.getBroadcast(context, (int) (System.currentTimeMillis() + 1), intent, 268435456);
    }

    public boolean isInNoDisturbTime(Context context) {
        boolean z;
        boolean z2;
        boolean z3;
        int i = Calendar.getInstance().get(XZBDevice.Success);
        int i2 = Calendar.getInstance().get(XZBDevice.Fail);
        if ((i * 60) + i2 >= (PushAgent.getInstance(context).getNoDisturbStartHour() * 60) + PushAgent.getInstance(context).getNoDisturbStartMinute()) {
            z = true;
        } else {
            Object obj = null;
        }
        if ((i * 60) + i2 <= (PushAgent.getInstance(context).getNoDisturbEndHour() * 60) + PushAgent.getInstance(context).getNoDisturbEndMinute()) {
            z2 = true;
        } else {
            Object obj2 = null;
        }
        if ((PushAgent.getInstance(context).getNoDisturbEndHour() * 60) + PushAgent.getInstance(context).getNoDisturbEndMinute() >= (PushAgent.getInstance(context).getNoDisturbStartHour() * 60) + PushAgent.getInstance(context).getNoDisturbStartMinute()) {
            z3 = true;
        } else {
            Object obj3 = null;
        }
        return z3 ? z && z2 : z || z2;
    }

    public int getNotificationDefaults(Context context, UMessage uMessage) {
        int i = 0;
        long muteDuration = (1 * ((long) MessageSharedPrefs.getInstance(context).getMuteDuration())) * 1000;
        if (!isInNoDisturbTime(context)) {
            if (c == null || Calendar.getInstance().getTimeInMillis() - c.getTime() >= muteDuration) {
                int notificationPlayVibrate = MessageSharedPrefs.getInstance(context).getNotificationPlayVibrate();
                UmLog.d(b, new StringBuilder("playVibrate:").append(notificationPlayVibrate).toString());
                if (notificationPlayVibrate == 1) {
                    i = 2;
                } else if (notificationPlayVibrate != 2 && uMessage.play_vibrate) {
                    i = 2;
                }
                notificationPlayVibrate = MessageSharedPrefs.getInstance(context).getNotificationPlayLights();
                UmLog.d(b, new StringBuilder("playLights:").append(notificationPlayVibrate).toString());
                if (notificationPlayVibrate == 1) {
                    i |= 4;
                } else if (notificationPlayVibrate != 2 && uMessage.play_lights) {
                    i |= 4;
                }
                notificationPlayVibrate = MessageSharedPrefs.getInstance(context).getNotificationPlaySound();
                UmLog.d(b, new StringBuilder("playSound:").append(notificationPlayVibrate).toString());
                if (notificationPlayVibrate == 1) {
                    i |= 1;
                } else if (notificationPlayVibrate != 2 && uMessage.play_sound) {
                    i |= 1;
                }
                c = Calendar.getInstance().getTime();
                if (uMessage.screen_on) {
                    a(context);
                }
            }
        }
        return i;
    }

    public boolean startDownloadResourceService(Context context, UMessage uMessage) {
        try {
            Intent intent = new Intent(context, UmengDownloadResourceService.class);
            intent.putExtra("body", uMessage.getRaw().toString());
            intent.putExtra(SocializeConstants.WEIBO_ID, uMessage.message_id);
            intent.putExtra("task_id", uMessage.task_id);
            context.startService(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean a(Context context, Builder builder, UMessage uMessage) {
        int smallIconId = getSmallIconId(context, uMessage);
        Bitmap largeIcon = getLargeIcon(context, uMessage);
        if (smallIconId < 0) {
            return false;
        }
        builder.setSmallIcon(smallIconId);
        builder.setLargeIcon(largeIcon);
        return true;
    }

    public int getSmallIconId(Context context, UMessage uMessage) {
        int i;
        int i2 = -1;
        try {
            if (!TextUtils.isEmpty(uMessage.icon)) {
                i2 = c.a(context).c(uMessage.icon);
            }
            if (i2 < 0) {
                i2 = c.a(context).c(h);
            }
            if (i2 < 0) {
                UmLog.d(b, "no custom notificaiton icon, fail back to app icon.");
                i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).applicationInfo.icon;
            } else {
                i = i2;
            }
            if (i < 0) {
                try {
                    UmLog.e(b, "Cann't find appropriate icon for notification, please make sure you have specified an icon for this notification or the app has defined an icon.");
                } catch (Exception e) {
                    Exception e2 = e;
                    e2.printStackTrace();
                    return i;
                }
            }
        } catch (Exception e3) {
            Exception exception = e3;
            i = -1;
            e2 = exception;
            e2.printStackTrace();
            return i;
        }
        return i;
    }

    public Bitmap getLargeIcon(Context context, UMessage uMessage) {
        try {
            Bitmap decodeFile;
            Bitmap decodeResource;
            int dimension;
            if (uMessage.isLargeIconFromInternet()) {
                decodeFile = BitmapFactory.decodeFile(UmengDownloadResourceService.getMessageResourceFolder(context, uMessage) + uMessage.img.hashCode());
            } else {
                decodeFile = null;
            }
            if (decodeFile == null) {
                int i = -1;
                if (!TextUtils.isEmpty(uMessage.largeIcon)) {
                    i = c.a(context).c(uMessage.largeIcon);
                }
                if (i < 0) {
                    i = c.a(context).c(g);
                }
                if (i > 0) {
                    decodeResource = BitmapFactory.decodeResource(context.getResources(), i);
                    if (decodeResource != null) {
                        return null;
                    }
                    if (VERSION.SDK_INT < 11) {
                        dimension = (int) context.getResources().getDimension(17104902);
                    } else {
                        dimension = i.a((float) a);
                    }
                    return Bitmap.createScaledBitmap(decodeResource, dimension, dimension, true);
                }
            }
            decodeResource = decodeFile;
            if (decodeResource != null) {
                return null;
            }
            if (VERSION.SDK_INT < 11) {
                dimension = i.a((float) a);
            } else {
                dimension = (int) context.getResources().getDimension(17104902);
            }
            return Bitmap.createScaledBitmap(decodeResource, dimension, dimension, true);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Uri getSound(Context context, UMessage uMessage) {
        try {
            String str;
            if (uMessage.isSoundFromInternet()) {
                str = UmengDownloadResourceService.getMessageResourceFolder(context, uMessage) + uMessage.sound.hashCode();
                if (!new File(str).exists()) {
                    str = null;
                }
            } else {
                str = null;
            }
            if (str == null) {
                int i = -1;
                if (!TextUtils.isEmpty(uMessage.sound)) {
                    i = c.a(context).h(uMessage.sound);
                }
                if (i < 0) {
                    i = c.a(context).h(i);
                }
                if (i > 0) {
                    str = new StringBuilder("android.resource://").append(context.getPackageName()).append("/").append(i).toString();
                }
            }
            return str != null ? Uri.parse(str) : null;
        } catch (Throwable th) {
            return null;
        }
    }

    public Notification getNotification(Context context, UMessage uMessage) {
        return null;
    }
}
