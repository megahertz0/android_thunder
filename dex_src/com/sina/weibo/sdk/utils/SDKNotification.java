package com.sina.weibo.sdk.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.Builder;
import com.umeng.message.entity.UMessage;

public class SDKNotification {
    private Context mContext;
    private Notification mNotification;

    public static class SDKNotificationBuilder {
        private String mNotificationContent;
        private PendingIntent mNotificationPendingIntent;
        private String mNotificationTitle;
        private Uri mSoundUri;
        private String mTickerText;
        private long[] mVibrate;

        public static com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder buildUpon() {
            return new com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder();
        }

        public com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder setTickerText(String str) {
            this.mTickerText = str;
            return this;
        }

        public com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder setNotificationTitle(String str) {
            this.mNotificationTitle = str;
            return this;
        }

        public com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder setNotificationContent(String str) {
            this.mNotificationContent = str;
            return this;
        }

        public com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder setNotificationPendingIntent(PendingIntent pendingIntent) {
            this.mNotificationPendingIntent = pendingIntent;
            return this;
        }

        public com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder setVibrate(long[] jArr) {
            this.mVibrate = jArr;
            return this;
        }

        public com.sina.weibo.sdk.utils.SDKNotification.SDKNotificationBuilder setSoundUri(Uri uri) {
            this.mSoundUri = uri;
            return this;
        }

        public SDKNotification build(Context context) {
            Builder builder = new Builder(context);
            builder.setAutoCancel(true);
            builder.setContentIntent(this.mNotificationPendingIntent);
            builder.setTicker(this.mTickerText);
            builder.setSmallIcon(getNotificationIcon(context));
            builder.setWhen(System.currentTimeMillis());
            if (this.mSoundUri != null) {
                builder.setSound(this.mSoundUri);
            }
            if (this.mVibrate != null) {
                builder.setVibrate(this.mVibrate);
            }
            builder.setLargeIcon(((BitmapDrawable) ResourceManager.getDrawable(context, "weibosdk_notification_icon.png")).getBitmap());
            builder.setContentTitle(this.mNotificationTitle);
            builder.setContentText(this.mNotificationContent);
            return new SDKNotification(builder.build(), null);
        }

        private static int getNotificationIcon(Context context) {
            int resourceId = getResourceId(context, "com_sina_weibo_sdk_weibo_logo", "drawable");
            return resourceId > 0 ? resourceId : 17301659;
        }

        private static int getResourceId(Context context, String str, String str2) {
            String packageName = context.getApplicationContext().getPackageName();
            try {
                return context.getPackageManager().getResourcesForApplication(packageName).getIdentifier(str, str2, packageName);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
                return 0;
            }
        }
    }

    private SDKNotification(Context context, Notification notification) {
        this.mContext = context.getApplicationContext();
        this.mNotification = notification;
    }

    public void show(int i) {
        if (this.mNotification != null) {
            ((NotificationManager) this.mContext.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION)).notify(i, this.mNotification);
        }
    }
}
