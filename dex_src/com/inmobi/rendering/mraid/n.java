package com.inmobi.rendering.mraid;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Vibrator;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import anet.channel.util.HttpConstant;
import com.alipay.sdk.packet.d;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.plus.PlusShare.Builder;
import com.inmobi.ads.b.e;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.InMobiAdActivity;
import com.inmobi.rendering.RenderView;
import com.taobao.accs.common.Constants;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.android.spdy.SpdyAgent;

// compiled from: SystemTasksProcessor.java
public class n {
    private static final String a;
    private RenderView b;
    private a c;

    // compiled from: SystemTasksProcessor.java
    class AnonymousClass_1 implements com.inmobi.rendering.InMobiAdActivity.a {
        final /* synthetic */ Context a;
        final /* synthetic */ int b;
        final /* synthetic */ String c;
        final /* synthetic */ String d;
        final /* synthetic */ String e;
        final /* synthetic */ String f;

        AnonymousClass_1(Context context, int i, String str, String str2, String str3, String str4) {
            this.a = context;
            this.b = i;
            this.c = str;
            this.d = str2;
            this.e = str3;
            this.f = str4;
        }

        public void a(int i, Intent intent) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Result code: ").append(i).toString());
            if (this.b == a.a(this.a)) {
                Logger.a(InternalLogLevel.INTERNAL, a, "User cancelled the create calendar event");
                return;
            }
            int i2;
            ContentValues contentValues = new ContentValues();
            String str = this.c;
            Object obj = -1;
            switch (str.hashCode()) {
                case -1320822226:
                    if (str.equals("tentative")) {
                        i2 = 0;
                    }
                    break;
                case -804109473:
                    if (str.equals("confirmed")) {
                        i2 = 1;
                    }
                    break;
                case 476588369:
                    if (str.equals("cancelled")) {
                        i2 = 2;
                    }
                    break;
            }
            switch (i2) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    contentValues.put("eventStatus", Integer.valueOf(0));
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    contentValues.put("eventStatus", Integer.valueOf(1));
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    contentValues.put("eventStatus", Integer.valueOf(XZBDevice.DOWNLOAD_LIST_RECYCLE));
                    break;
            }
            ContentResolver contentResolver = this.a.getContentResolver();
            contentResolver.update(ContentUris.withAppendedId(Events.CONTENT_URI, (long) a.a(this.a)), contentValues, null, null);
            if (this.d != null && !com.umeng.a.d.equals(this.d)) {
                try {
                    if (this.d.startsWith(SocializeConstants.OP_DIVIDER_PLUS)) {
                        i2 = Integer.parseInt(this.d.substring(1)) / 60000;
                    } else {
                        i2 = Integer.parseInt(this.d) / 60000;
                    }
                } catch (NumberFormatException e) {
                    Calendar b = a.b(this.d);
                    if (b == null) {
                        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Invalid reminder date provided. date string: ").append(this.d).toString());
                        return;
                    }
                    i2 = ((int) (b.getTimeInMillis() - a.b(this.e).getTimeInMillis())) / 60000;
                    if (i2 > 0) {
                        n.this.b.a(this.f, "Reminder format is incorrect. Reminder can be set only before the event starts", "createCalendarEvent");
                    }
                }
                i2 = -i2;
                contentResolver.delete(Reminders.CONTENT_URI, "event_id=?", new String[]{a.a(this.a)});
                if (i2 < 0) {
                    n.this.b.a(this.f, "Reminder format is incorrect. Reminder can be set only before the event starts", "createCalendarEvent");
                    return;
                }
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("event_id", Integer.valueOf(a.a(this.a)));
                contentValues2.put(d.q, Integer.valueOf(1));
                contentValues2.put("minutes", Integer.valueOf(i2));
                contentResolver.insert(Reminders.CONTENT_URI, contentValues2);
            }
        }
    }

    // compiled from: SystemTasksProcessor.java
    static final class a extends Handler {
        private static final String a;
        private WeakReference<RenderView> b;

        static {
            a = a.class.getSimpleName();
        }

        public a(Looper looper, RenderView renderView) {
            super(looper);
            this.b = new WeakReference(renderView);
        }

        public final void handleMessage(Message message) {
            switch (message.what) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    String str = (String) message.obj;
                    RenderView renderView = (RenderView) this.b.get();
                    if (renderView != null) {
                        renderView.a(str, "broadcastEvent('vibrateComplete');");
                    }
                default:
                    Logger.a(InternalLogLevel.INTERNAL, a, "Unknown message type. Ignoring ...");
            }
        }
    }

    static {
        a = n.class.getSimpleName();
    }

    public n(RenderView renderView) {
        this.b = renderView;
        HandlerThread handlerThread = new HandlerThread("SystemTasksHandlerThread");
        handlerThread.start();
        this.c = new a(handlerThread.getLooper(), renderView);
    }

    @TargetApi(14)
    public void a(String str, Context context, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11) {
        int a = a.a(context);
        Calendar b = a.b(str3);
        if (b == null) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Invalid event start date provided. date string: ").append(str3).toString());
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Event start: ").append(b.get(1)).append(SocializeConstants.OP_DIVIDER_MINUS).append(b.get(XZBDevice.DOWNLOAD_LIST_RECYCLE)).append(SocializeConstants.OP_DIVIDER_MINUS).append(b.get(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)).toString());
        Calendar b2 = a.b(str4);
        if (b2 == null) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Invalid event end date provided. date string: ").append(str4).toString());
            return;
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Event end: ").append(b2.get(1)).append(SocializeConstants.OP_DIVIDER_MINUS).append(b2.get(XZBDevice.DOWNLOAD_LIST_RECYCLE)).append(SocializeConstants.OP_DIVIDER_MINUS).append(b2.get(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED)).toString());
        Intent putExtra = new Intent("android.intent.action.INSERT").setData(Events.CONTENT_URI).putExtra("calendar_id", str2).putExtra("beginTime", b.getTimeInMillis()).putExtra("endTime", b2.getTimeInMillis()).putExtra("allDay", false).putExtra(WebBrowserActivity.EXTRA_TITLE, str6).putExtra("eventLocation", str5).putExtra(Impl.COLUMN_DESCRIPTION, str7);
        if (str9.equals("transparent")) {
            putExtra.putExtra("availability", 1);
        } else {
            if (str9.equals("opaque")) {
                putExtra.putExtra("availability", 0);
            }
        }
        String a2 = a(str10);
        if (a2.length() != 0) {
            putExtra.putExtra("rrule", a2);
        }
        int a3 = InMobiAdActivity.a(putExtra, new AnonymousClass_1(context, a, str8, str11, str3, str));
        Intent intent = new Intent(context, InMobiAdActivity.class);
        intent.putExtra("com.inmobi.rendering.InMobiAdActivity.EXTRA_AD_ACTIVITY_TYPE", R.styleable.AppCompatTheme_checkedTextViewStyle);
        intent.putExtra(SocializeConstants.WEIBO_ID, a3);
        com.inmobi.commons.a.a.a(context, intent);
    }

    public void a(String str, Context context, int i, String str2, String str3, String str4) {
        if (str2 == null || str2.length() == 0 || str3 == null || str3.length() == 0 || !str3.startsWith(HttpConstant.HTTP) || str4 == null || str4.length() == 0 || !str4.startsWith(HttpConstant.HTTP) || !str4.endsWith(".jpg")) {
            this.b.a(str, "Attempting to share with null/empty/invalid parameters", "postToSocial");
            return;
        }
        Intent intent = null;
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                if (intent != null) {
                    try {
                        com.inmobi.commons.a.a.a(context, intent);
                        return;
                    } catch (ActivityNotFoundException e) {
                    }
                }
                a(context, i, str2, str3, str4);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                if (b()) {
                    intent = new Builder(context).setType("text/plain").setText(str2 + " " + str3 + " " + str4).setContentUrl(Uri.parse(str4)).getIntent();
                }
                if (intent != null) {
                    com.inmobi.commons.a.a.a(context, intent);
                    return;
                }
                a(context, i, str2, str3, str4);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                String str5 = str2 + " " + str3 + " " + str4;
                intent = new Intent();
                intent.setType("text/plain");
                intent.setPackage("com.twitter.android");
                intent.putExtra("android.intent.extra.TEXT", str5);
                if (intent != null) {
                    com.inmobi.commons.a.a.a(context, intent);
                    return;
                }
                a(context, i, str2, str3, str4);
            default:
                this.b.a(str, "Unsupported type of social network", "postToSocial");
        }
    }

    private boolean b() {
        try {
            return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(com.inmobi.commons.a.a.b()) == 0;
        } catch (NoClassDefFoundError e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Google Play Services is not installed!");
            return false;
        } catch (RuntimeException e2) {
            e2.printStackTrace();
            Map hashMap = new HashMap();
            hashMap.put(JsInterface.FUNPLAY_AD_TRPE, "RuntimeException");
            hashMap.put(Constants.SHARED_MESSAGE_ID_FILE, e2.getMessage());
            com.inmobi.commons.core.c.a.a().a("ads", "ExceptionCaught", hashMap);
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.content.Context r4, int r5, java.lang.String r6, java.lang.String r7, java.lang.String r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.mraid.n.a(android.content.Context, int, java.lang.String, java.lang.String, java.lang.String):void");
        /*
        this = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0 = r0.append(r6);
        r1 = " ";
        r0 = r0.append(r1);
        r0 = r0.append(r7);
        r1 = " ";
        r0 = r0.append(r1);
        r0 = r0.append(r8);
        r1 = r0.toString();
        r0 = 0;
        switch(r5) {
            case 1: goto L_0x003c;
            case 2: goto L_0x008a;
            case 3: goto L_0x00a2;
            default: goto L_0x0027;
        };
    L_0x0027:
        if (r0 == 0) goto L_0x00c9;
    L_0x0029:
        r1 = new android.content.Intent;
        r2 = "android.intent.action.VIEW";
        r1.<init>(r2);
        r0 = android.net.Uri.parse(r0);
        r1.setData(r0);
        com.inmobi.commons.a.a.a(r4, r1);	 Catch:{ ActivityNotFoundException -> 0x00bb }
    L_0x003b:
        return;
    L_0x003c:
        r0 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "https://www.facebook.com/dialog/feed?app_id=181821551957328&link=";
        r0.<init>(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "UTF-8";
        r2 = java.net.URLEncoder.encode(r7, r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r0 = r0.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "&picture=";
        r0 = r0.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "UTF-8";
        r2 = java.net.URLEncoder.encode(r8, r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r0 = r0.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "&name=&description=";
        r0 = r0.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "UTF-8";
        r2 = java.net.URLEncoder.encode(r6, r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r0 = r0.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "&redirect_uri=";
        r0 = r0.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "UTF-8";
        r2 = java.net.URLEncoder.encode(r7, r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r0 = r0.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r0 = r0.toString();	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        goto L_0x0027;
    L_0x008a:
        r0 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "https://m.google.com/app/plus/x/?v=compose&content=";
        r0.<init>(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "UTF-8";
        r2 = java.net.URLEncoder.encode(r1, r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r0 = r0.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r0 = r0.toString();	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        goto L_0x0027;
    L_0x00a2:
        r0 = new java.lang.StringBuilder;	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "http://twitter.com/home?status=";
        r0.<init>(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r2 = "UTF-8";
        r2 = java.net.URLEncoder.encode(r1, r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r0 = r0.append(r2);	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        r0 = r0.toString();	 Catch:{ UnsupportedEncodingException -> 0x00ed }
        goto L_0x0027;
    L_0x00bb:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;
        r2 = a;
        r0 = r0.getMessage();
        com.inmobi.commons.core.utilities.Logger.a(r1, r2, r0);
        goto L_0x003b;
    L_0x00c9:
        r0 = new android.content.Intent;
        r0.<init>();
        r2 = "text/plain";
        r0.setType(r2);
        r2 = "android.intent.extra.TEXT";
        r0.putExtra(r2, r1);
        com.inmobi.commons.a.a.a(r4, r0);	 Catch:{ ActivityNotFoundException -> 0x00df }
        goto L_0x003b;
    L_0x00df:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;
        r2 = a;
        r0 = r0.getMessage();
        com.inmobi.commons.core.utilities.Logger.a(r1, r2, r0);
        goto L_0x003b;
    L_0x00ed:
        r0 = move-exception;
        goto L_0x003b;
        */
    }

    public void a(String str, Context context) {
        ((Vibrator) context.getSystemService("vibrator")).vibrate(2000);
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = str;
        this.c.sendMessageDelayed(obtain, 2000);
    }

    public void a(String str, Context context, String str2, int i) {
        Vibrator vibrator = (Vibrator) context.getSystemService("vibrator");
        String replaceAll = str2.replaceAll("\\[", com.umeng.a.d).replaceAll("\\]", com.umeng.a.d);
        if (replaceAll == null || com.umeng.a.d.equals(replaceAll.trim())) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Vibration canceled");
            a(context);
            return;
        }
        String[] split = replaceAll.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        int length = split.length;
        e renderingConfig = this.b.getRenderingConfig();
        if (length > renderingConfig.f()) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("vibration pattern exceeds max length. Will be truncated to max ").append(renderingConfig.f()).toString());
            length = renderingConfig.f();
        }
        long[] jArr = new long[length];
        int i2 = 0;
        int i3 = 0;
        while (i3 < length) {
            try {
                jArr[i3] = Long.parseLong(split[i3]);
                if (jArr[i3] > ((long) (renderingConfig.e() * 1000))) {
                    Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("vibration duration exceeds max. Will only vibrate for max ").append(renderingConfig.e()).append("ms").toString());
                    jArr[i3] = (long) renderingConfig.e();
                }
                if (jArr[i3] < 0) {
                    this.b.a(str, "Negative duration not allowed in vibration .", "vibrate");
                }
                i2 = (int) (((long) i2) + jArr[i3]);
                i3++;
            } catch (NumberFormatException e) {
                this.b.a(str, "Invalid values of pattern in vibration .", "vibrate");
            }
        }
        if (jArr.length != 0) {
            a(context);
            vibrator.vibrate(jArr, i);
            Message obtain = Message.obtain();
            obtain.what = 1;
            obtain.obj = str;
            this.c.sendMessageDelayed(obtain, (long) i2);
        }
    }

    public void a(Context context) {
        if (this.c != null && this.c.hasMessages(1)) {
            this.c.removeMessages(1);
            ((Vibrator) context.getSystemService("vibrator")).cancel();
            Logger.a(InternalLogLevel.INTERNAL, a, "Canceling any pending/ongoing vibrate requests");
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r8) {
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.rendering.mraid.n.a(java.lang.String):java.lang.String");
        /*
        this = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r8.length();
        if (r1 == 0) goto L_0x01c1;
    L_0x000b:
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x01a1 }
        r1.<init>(r8);	 Catch:{ JSONException -> 0x01a1 }
        r2 = "frequency";
        r2 = r1.optString(r2);	 Catch:{ JSONException -> 0x01a1 }
        if (r2 == 0) goto L_0x0193;
    L_0x0019:
        r3 = "";
        r3 = r3.equals(r2);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 != 0) goto L_0x0193;
    L_0x0022:
        r3 = "daily";
        r3 = r3.equals(r2);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 != 0) goto L_0x0046;
    L_0x002b:
        r3 = "weekly";
        r3 = r3.equals(r2);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 != 0) goto L_0x0046;
    L_0x0034:
        r3 = "monthly";
        r3 = r3.equals(r2);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 != 0) goto L_0x0046;
    L_0x003d:
        r3 = "yearly";
        r3 = r3.equals(r2);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 == 0) goto L_0x0185;
    L_0x0046:
        r3 = "freq=";
        r3 = r0.append(r3);	 Catch:{ JSONException -> 0x01a1 }
        r3 = r3.append(r2);	 Catch:{ JSONException -> 0x01a1 }
        r4 = ";";
        r3.append(r4);	 Catch:{ JSONException -> 0x01a1 }
        r3 = "interval";
        r3 = r1.optString(r3);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 == 0) goto L_0x007e;
    L_0x0060:
        r4 = "";
        r4 = r4.equals(r3);	 Catch:{ JSONException -> 0x01a1 }
        if (r4 != 0) goto L_0x007e;
    L_0x0069:
        r4 = "interval=";
        r4 = r0.append(r4);	 Catch:{ JSONException -> 0x01a1 }
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ JSONException -> 0x01a1 }
        r3 = r4.append(r3);	 Catch:{ JSONException -> 0x01a1 }
        r4 = ";";
        r3.append(r4);	 Catch:{ JSONException -> 0x01a1 }
    L_0x007e:
        r3 = "expires";
        r3 = r1.optString(r3);	 Catch:{ JSONException -> 0x01a1 }
        r3 = com.inmobi.rendering.mraid.a.a(r3);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 == 0) goto L_0x00b0;
    L_0x008b:
        r4 = "until=";
        r4 = r0.append(r4);	 Catch:{ JSONException -> 0x01a1 }
        r5 = "+";
        r6 = "Z+";
        r3 = r3.replace(r5, r6);	 Catch:{ JSONException -> 0x01a1 }
        r5 = "-";
        r6 = "Z-";
        r3 = r3.replace(r5, r6);	 Catch:{ JSONException -> 0x01a1 }
        r3 = r4.append(r3);	 Catch:{ JSONException -> 0x01a1 }
        r4 = ";";
        r3.append(r4);	 Catch:{ JSONException -> 0x01a1 }
    L_0x00b0:
        r3 = "weekly";
        r3 = r2.equals(r3);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 == 0) goto L_0x00d7;
    L_0x00b9:
        r3 = "daysInWeek";
        r3 = r1.optJSONArray(r3);	 Catch:{ JSONException -> 0x01a1 }
        r3 = com.inmobi.rendering.mraid.a.a(r3);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 == 0) goto L_0x00d7;
    L_0x00c6:
        r4 = "byday=";
        r4 = r0.append(r4);	 Catch:{ JSONException -> 0x01a1 }
        r3 = r4.append(r3);	 Catch:{ JSONException -> 0x01a1 }
        r4 = ";";
        r3.append(r4);	 Catch:{ JSONException -> 0x01a1 }
    L_0x00d7:
        r3 = "monthly";
        r3 = r2.equals(r3);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 == 0) goto L_0x0102;
    L_0x00e0:
        r3 = "daysInMonth";
        r3 = r1.optJSONArray(r3);	 Catch:{ JSONException -> 0x01a1 }
        r4 = -31;
        r5 = 31;
        r3 = com.inmobi.rendering.mraid.a.a(r3, r4, r5);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 == 0) goto L_0x0102;
    L_0x00f1:
        r4 = "bymonthday=";
        r4 = r0.append(r4);	 Catch:{ JSONException -> 0x01a1 }
        r3 = r4.append(r3);	 Catch:{ JSONException -> 0x01a1 }
        r4 = ";";
        r3.append(r4);	 Catch:{ JSONException -> 0x01a1 }
    L_0x0102:
        r3 = "yearly";
        r3 = r2.equals(r3);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 == 0) goto L_0x012d;
    L_0x010b:
        r3 = "daysInYear";
        r3 = r1.optJSONArray(r3);	 Catch:{ JSONException -> 0x01a1 }
        r4 = -366; // 0xfffffffffffffe92 float:NaN double:NaN;
        r5 = 366; // 0x16e float:5.13E-43 double:1.81E-321;
        r3 = com.inmobi.rendering.mraid.a.a(r3, r4, r5);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 == 0) goto L_0x012d;
    L_0x011c:
        r4 = "byyearday=";
        r4 = r0.append(r4);	 Catch:{ JSONException -> 0x01a1 }
        r3 = r4.append(r3);	 Catch:{ JSONException -> 0x01a1 }
        r4 = ";";
        r3.append(r4);	 Catch:{ JSONException -> 0x01a1 }
    L_0x012d:
        r3 = "monthly";
        r3 = r2.equals(r3);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 == 0) goto L_0x0156;
    L_0x0136:
        r3 = "weeksInMonth";
        r3 = r1.optJSONArray(r3);	 Catch:{ JSONException -> 0x01a1 }
        r4 = -4;
        r5 = 4;
        r3 = com.inmobi.rendering.mraid.a.a(r3, r4, r5);	 Catch:{ JSONException -> 0x01a1 }
        if (r3 == 0) goto L_0x0156;
    L_0x0145:
        r4 = "byweekno=";
        r4 = r0.append(r4);	 Catch:{ JSONException -> 0x01a1 }
        r3 = r4.append(r3);	 Catch:{ JSONException -> 0x01a1 }
        r4 = ";";
        r3.append(r4);	 Catch:{ JSONException -> 0x01a1 }
    L_0x0156:
        r3 = "yearly";
        r2 = r2.equals(r3);	 Catch:{ JSONException -> 0x01a1 }
        if (r2 == 0) goto L_0x0180;
    L_0x015f:
        r2 = "monthsInYear";
        r1 = r1.optJSONArray(r2);	 Catch:{ JSONException -> 0x01a1 }
        r2 = 1;
        r3 = 12;
        r1 = com.inmobi.rendering.mraid.a.a(r1, r2, r3);	 Catch:{ JSONException -> 0x01a1 }
        if (r1 == 0) goto L_0x0180;
    L_0x016f:
        r2 = "bymonth=";
        r2 = r0.append(r2);	 Catch:{ JSONException -> 0x01a1 }
        r1 = r2.append(r1);	 Catch:{ JSONException -> 0x01a1 }
        r2 = ";";
        r1.append(r2);	 Catch:{ JSONException -> 0x01a1 }
    L_0x0180:
        r0 = r0.toString();	 Catch:{ JSONException -> 0x01a1 }
    L_0x0184:
        return r0;
    L_0x0185:
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ JSONException -> 0x01a1 }
        r1 = a;	 Catch:{ JSONException -> 0x01a1 }
        r2 = "Error Parsing recurrence string. Invalid Frequency supplied";
        com.inmobi.commons.core.utilities.Logger.a(r0, r1, r2);	 Catch:{ JSONException -> 0x01a1 }
        r0 = "";
        goto L_0x0184;
    L_0x0193:
        r0 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ JSONException -> 0x01a1 }
        r1 = a;	 Catch:{ JSONException -> 0x01a1 }
        r2 = "Error Parsing recurrence string. Frequency supplied is null";
        com.inmobi.commons.core.utilities.Logger.a(r0, r1, r2);	 Catch:{ JSONException -> 0x01a1 }
        r0 = "";
        goto L_0x0184;
    L_0x01a1:
        r0 = move-exception;
        r1 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;
        r2 = a;
        r3 = new java.lang.StringBuilder;
        r4 = "Error Parsing recurrence string";
        r3.<init>(r4);
        r0 = r0.toString();
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.inmobi.commons.core.utilities.Logger.a(r1, r2, r0);
        r0 = "";
        goto L_0x0184;
    L_0x01c1:
        r0 = "";
        goto L_0x0184;
        */
    }
}
