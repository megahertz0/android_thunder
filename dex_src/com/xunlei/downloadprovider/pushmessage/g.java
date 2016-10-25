package com.xunlei.downloadprovider.pushmessage;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.NotificationCompat.Builder;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.umeng.message.NotificationProxyBroadcastReceiver;
import com.umeng.message.entity.UMessage;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.b;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.pushmessage.a.a;
import com.xunlei.tdlive.sdk.XLLiveSDK;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: PushResultNotification.java
public final class g {
    public static void a(Context context, a aVar, Bitmap bitmap) {
        Notification notification = null;
        Intent intent = new Intent();
        intent.setClass(context, PushOnClickReceiver.class);
        intent.putExtra("mqtt_result", aVar);
        intent = new Intent();
        intent.setClass(context, PushOnClickReceiver.class);
        intent.putExtra("mqtt_result", aVar);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_ACTION, Impl.STATUS_PEER_NOT_FOUND_ERROR);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, (int) System.currentTimeMillis(), intent, 268435456);
        intent = new Intent();
        intent.setClass(context, PushOnClickReceiver.class);
        intent.putExtra("mqtt_result", aVar);
        intent.putExtra(NotificationProxyBroadcastReceiver.EXTRA_KEY_ACTION, Impl.STATUS_TIME_OUT);
        PendingIntent broadcast2 = PendingIntent.getBroadcast(context, (int) (System.currentTimeMillis() + 1), intent, 268435456);
        try {
            BrothersApplication brothersApplication = BrothersApplication.a;
            if (brothersApplication.d == null || !brothersApplication.d.a) {
                Object obj = null;
            } else {
                int i = 1;
            }
            if (obj == null && b.a().h()) {
                i = 1;
            } else {
                i = 0;
            }
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
            if (aVar != null) {
                switch (aVar.s) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        notification = new Builder(context).setAutoCancel(true).setTicker(aVar.h).setSmallIcon(2130903040).setDefaults(i).setNumber(0).setPriority(XZBDevice.DOWNLOAD_LIST_RECYCLE).setContentTitle(aVar.h).setContentText(aVar.j).build();
                        CharSequence charSequence;
                        if (com.xunlei.downloadprovider.a.b.i() >= 16) {
                            RemoteViews remoteViews;
                            CharSequence charSequence2;
                            if (TextUtils.isEmpty(aVar.m)) {
                                charSequence = aVar.h;
                                charSequence2 = aVar.j;
                                remoteViews = new RemoteViews(context.getPackageName(), 2130968877);
                                if (bitmap == null) {
                                    remoteViews.setImageViewResource(2131756569, 2130838614);
                                } else {
                                    remoteViews.setImageViewBitmap(2131756569, bitmap);
                                }
                                remoteViews.setTextViewText(2131756570, charSequence);
                                remoteViews.setTextViewText(2131756571, charSequence2);
                            } else {
                                charSequence2 = aVar.j;
                                int i2 = aVar.o;
                                remoteViews = new RemoteViews(context.getPackageName(), 2130968876);
                                if (bitmap == null) {
                                    remoteViews.setImageViewResource(2131756564, 2130838614);
                                } else {
                                    remoteViews.setImageViewBitmap(2131756564, bitmap);
                                }
                                remoteViews.setTextViewText(2131756567, charSequence2);
                                if (i2 == 1) {
                                    remoteViews.setViewVisibility(2131756565, 0);
                                } else {
                                    remoteViews.setViewVisibility(2131756565, XZBDevice.Wait);
                                }
                            }
                            notification.bigContentView = remoteViews;
                        } else {
                            CharSequence charSequence3 = aVar.h;
                            charSequence = aVar.j;
                            RemoteViews remoteViews2 = new RemoteViews(context.getPackageName(), 2130968886);
                            remoteViews2.setImageViewResource(2131756687, 2130838720);
                            remoteViews2.setTextViewText(2131756686, charSequence3);
                            remoteViews2.setTextViewText(2131756567, charSequence);
                            notification.contentView = remoteViews2;
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        LoginHelper.a();
                        if (LoginHelper.d()) {
                            notification = new Builder(context).setAutoCancel(true).setTicker(aVar.h).setSmallIcon(2130903040).setDefaults(i).setNumber(0).setPriority(XZBDevice.DOWNLOAD_LIST_RECYCLE).setContentTitle(aVar.h).setContentText(aVar.j).build();
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        if (XLLiveSDK.getInstance(context).canPlay()) {
                            notification = new Builder(context).setAutoCancel(true).setTicker(aVar.h).setSmallIcon(2130903040).setDefaults(i).setNumber(0).setPriority(XZBDevice.DOWNLOAD_LIST_RECYCLE).setContentTitle(aVar.h).setContentText(aVar.j).build();
                        }
                        break;
                    default:
                        break;
                }
            }
            if (notification != null) {
                if (broadcast != null) {
                    notification.contentIntent = broadcast;
                }
                if (broadcast2 != null) {
                    notification.deleteIntent = broadcast2;
                }
                if (com.xunlei.downloadprovider.pushmessage.b.a.a(context)) {
                    notificationManager.notify(27859, notification);
                }
            }
        } catch (Exception e) {
        }
    }
}
