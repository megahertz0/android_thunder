package com.xiaomi.push.service;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.Pair;
import android.widget.RemoteViews;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import com.sina.weibo.sdk.constant.WBConstants;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.common.SocializeConstants;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.channel.commonutils.reflect.a;
import com.xiaomi.xmpush.thrift.i;
import com.xiaomi.xmpush.thrift.o;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.android.agoo.message.MessageService;
import org.json.JSONObject;

public class r {
    public static long a;
    private static LinkedList<Pair<Integer, String>> b;

    static {
        a = 0;
        b = new LinkedList();
    }

    private static int a(Context context, String str, String str2) {
        return str.equals(context.getPackageName()) ? context.getResources().getIdentifier(str2, "drawable", str) : 0;
    }

    private static Notification a(Notification notification, String str) {
        try {
            Field declaredField = Notification.class.getDeclaredField("extraNotification");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(notification);
            Method declaredMethod = obj.getClass().getDeclaredMethod("setTargetPkg", new Class[]{CharSequence.class});
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(obj, new Object[]{str});
        } catch (Throwable e) {
            b.a(e);
        }
        return notification;
    }

    @SuppressLint({"NewApi"})
    private static Notification a(Context context, o oVar, byte[] bArr, RemoteViews remoteViews, PendingIntent pendingIntent) {
        int a;
        long currentTimeMillis;
        String str;
        Notification notification;
        Object a2;
        i m = oVar.m();
        Builder builder = new Builder(context);
        String[] a3 = a(context, m);
        builder.setContentTitle(a3[0]);
        builder.setContentText(a3[1]);
        if (remoteViews != null) {
            builder.setContent(remoteViews);
        } else if (VERSION.SDK_INT >= 16) {
            builder.setStyle(new BigTextStyle().bigText(a3[1]));
        }
        builder.setWhen(System.currentTimeMillis());
        builder.setContentIntent(pendingIntent);
        Map s = m.s();
        int i = 0;
        if (s != null && s.containsKey("_wbxr") && "com.xiaomi.xmsf".equals(a(oVar))) {
            i = a(context, a(oVar), "wbxr");
            if (i > 0) {
                int i2 = 1;
                if (r2 == null) {
                    builder.setSmallIcon(i);
                    builder.setLargeIcon(a(context, i));
                } else {
                    i = a(context, a(oVar), "mipush_notification");
                    a = a(context, a(oVar), "mipush_small_notification");
                    if (i > 0 || a <= 0) {
                        builder.setSmallIcon(f(context, a(oVar)));
                    } else {
                        builder.setLargeIcon(a(context, i));
                        builder.setSmallIcon(a);
                    }
                }
                builder.setAutoCancel(true);
                currentTimeMillis = System.currentTimeMillis();
                if (s != null && s.containsKey("ticker")) {
                    builder.setTicker((CharSequence) s.get("ticker"));
                }
                if (currentTimeMillis - a > 10000) {
                    a = currentTimeMillis;
                    a = e(context, a(oVar)) ? c(context, a(oVar)) : m.f;
                    builder.setDefaults(a);
                    if (!(s == null || (a & 1) == 0)) {
                        str = (String) s.get("sound_uri");
                        if (!TextUtils.isEmpty(str) && str.startsWith(new StringBuilder("android.resource://").append(a(oVar)).toString())) {
                            builder.setDefaults(a ^ 1);
                            builder.setSound(Uri.parse(str));
                        }
                    }
                }
                notification = builder.getNotification();
                if (r2 != null) {
                    a2 = a.a(notification, "extraNotification");
                    if (a2 != null) {
                        a.a(a2, "setCustomizedIcon", new Object[]{Boolean.valueOf(true)});
                    }
                }
                return notification;
            }
        }
        Object obj = null;
        if (obj == null) {
            i = a(context, a(oVar), "mipush_notification");
            a = a(context, a(oVar), "mipush_small_notification");
            if (i > 0) {
            }
            builder.setSmallIcon(f(context, a(oVar)));
        } else {
            builder.setSmallIcon(i);
            builder.setLargeIcon(a(context, i));
        }
        builder.setAutoCancel(true);
        currentTimeMillis = System.currentTimeMillis();
        builder.setTicker((CharSequence) s.get("ticker"));
        if (currentTimeMillis - a > 10000) {
            a = currentTimeMillis;
            if (e(context, a(oVar))) {
            }
            builder.setDefaults(a);
            str = (String) s.get("sound_uri");
            builder.setDefaults(a ^ 1);
            builder.setSound(Uri.parse(str));
        }
        notification = builder.getNotification();
        if (obj != null) {
            a2 = a.a(notification, "extraNotification");
            if (a2 != null) {
                a.a(a2, "setCustomizedIcon", new Object[]{Boolean.valueOf(true)});
            }
        }
        return notification;
    }

    private static PendingIntent a(Context context, o oVar, i iVar, byte[] bArr) {
        Intent intent;
        if (iVar != null && !TextUtils.isEmpty(iVar.g)) {
            intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse(iVar.g));
            intent.addFlags(268435456);
            return PendingIntent.getActivity(context, 0, intent, 134217728);
        } else if (b(oVar)) {
            intent = new Intent();
            intent.setComponent(new ComponentName("com.xiaomi.xmsf", "com.xiaomi.mipush.sdk.PushMessageHandler"));
            intent.putExtra("mipush_payload", bArr);
            intent.putExtra("mipush_notified", true);
            intent.addCategory(String.valueOf(iVar.q()));
            return PendingIntent.getService(context, 0, intent, 134217728);
        } else {
            intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
            intent.setComponent(new ComponentName(oVar.f, "com.xiaomi.mipush.sdk.PushMessageHandler"));
            intent.putExtra("mipush_payload", bArr);
            intent.putExtra("mipush_notified", true);
            intent.addCategory(String.valueOf(iVar.q()));
            return PendingIntent.getService(context, 0, intent, 134217728);
        }
    }

    private static Bitmap a(Context context, int i) {
        return a(context.getResources().getDrawable(i));
    }

    public static Bitmap a(Drawable drawable) {
        int i = 1;
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicHeight > 0) {
            i = intrinsicHeight;
        }
        Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, i, Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    static String a(o oVar) {
        if ("com.xiaomi.xmsf".equals(oVar.f)) {
            i m = oVar.m();
            if (!(m == null || m.s() == null)) {
                String str = (String) m.s().get("miui_package_name");
                if (!TextUtils.isEmpty(str)) {
                    return str;
                }
            }
        }
        return oVar.f;
    }

    public static void a(Context context, o oVar, byte[] bArr) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
        i m = oVar.m();
        RemoteViews b = b(context, oVar, bArr);
        PendingIntent a = a(context, oVar, m, bArr);
        if (a == null) {
            b.a("The click PendingIntent is null. ");
            return;
        }
        Notification a2;
        int c;
        if (VERSION.SDK_INT >= 11) {
            a2 = a(context, oVar, bArr, b, a);
        } else {
            Notification notification = new Notification(f(context, a(oVar)), null, System.currentTimeMillis());
            String[] a3 = a(context, m);
            try {
                notification.getClass().getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{context, a3[0], a3[1], a});
            } catch (Throwable e) {
                b.a(e);
            } catch (Throwable e2) {
                b.a(e2);
            } catch (Throwable e22) {
                b.a(e22);
            } catch (Throwable e222) {
                b.a(e222);
            }
            Map s = m.s();
            if (s != null && s.containsKey("ticker")) {
                notification.tickerText = (CharSequence) s.get("ticker");
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - a > 10000) {
                a = currentTimeMillis;
                int i = m.f;
                if (e(context, a(oVar))) {
                    c = c(context, a(oVar));
                } else {
                    c = i;
                }
                notification.defaults = c;
                if (!(s == null || (c & 1) == 0)) {
                    String str = (String) s.get("sound_uri");
                    if (!TextUtils.isEmpty(str) && str.startsWith(new StringBuilder("android.resource://").append(a(oVar)).toString())) {
                        notification.defaults = c ^ 1;
                        notification.sound = Uri.parse(str);
                    }
                }
            }
            notification.flags |= 16;
            if (b != null) {
                notification.contentView = b;
            }
            a2 = notification;
        }
        if ("com.xiaomi.xmsf".equals(context.getPackageName())) {
            a(a2, a(oVar));
        }
        c = m.q() + ((a(oVar).hashCode() / 10) * 10);
        notificationManager.notify(c, a2);
        Pair pair = new Pair(Integer.valueOf(c), a(oVar));
        synchronized (b) {
            b.add(pair);
            if (b.size() > 100) {
                b.remove();
            }
        }
    }

    public static void a(Context context, String str, int i) {
        int hashCode = ((str.hashCode() / 10) * 10) + i;
        ((NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION)).cancel(hashCode);
        synchronized (b) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                if (hashCode == ((Integer) pair.first).intValue() && TextUtils.equals(str, (CharSequence) pair.second)) {
                    b.remove(pair);
                    break;
                }
            }
        }
    }

    public static boolean a(Context context, String str) {
        List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.importance == 100 && Arrays.asList(runningAppProcessInfo.pkgList).contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a(Map<String, String> map) {
        if (map == null || !map.containsKey("notify_foreground")) {
            return true;
        }
        return MessageService.MSG_DB_NOTIFY_REACHED.equals((String) map.get("notify_foreground"));
    }

    private static String[] a(Context context, i iVar) {
        String h = iVar.h();
        String j = iVar.j();
        Map s = iVar.s();
        if (s != null) {
            int intValue = Float.valueOf((((float) context.getResources().getDisplayMetrics().widthPixels) / context.getResources().getDisplayMetrics().density) + 0.5f).intValue();
            String str;
            if (intValue <= 320) {
                str = (String) s.get("title_short");
                if (!TextUtils.isEmpty(str)) {
                    h = str;
                }
                str = (String) s.get("description_short");
                if (TextUtils.isEmpty(str)) {
                    str = j;
                }
                CharSequence charSequence = r0;
            } else if (intValue > 360) {
                str = (String) s.get("title_long");
                if (!TextUtils.isEmpty(str)) {
                    h = str;
                }
                str = (String) s.get("description_long");
                if (!TextUtils.isEmpty(str)) {
                    j = str;
                }
            }
        }
        return new String[]{h, j};
    }

    private static RemoteViews b(Context context, o oVar, byte[] bArr) {
        i m = oVar.m();
        String a = a(oVar);
        Map s = m.s();
        if (s == null) {
            return null;
        }
        String str = (String) s.get("layout_name");
        String str2 = (String) s.get("layout_value");
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            Resources resourcesForApplication = context.getPackageManager().getResourcesForApplication(a);
            int identifier = resourcesForApplication.getIdentifier(str, "layout", a);
            if (identifier == 0) {
                return null;
            }
            RemoteViews remoteViews = new RemoteViews(a, identifier);
            try {
                JSONObject jSONObject;
                Iterator keys;
                JSONObject jSONObject2 = new JSONObject(str2);
                if (jSONObject2.has("text")) {
                    jSONObject = jSONObject2.getJSONObject("text");
                    keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        CharSequence string = jSONObject.getString(str);
                        identifier = resourcesForApplication.getIdentifier(str, SocializeConstants.WEIBO_ID, a);
                        if (identifier > 0) {
                            remoteViews.setTextViewText(identifier, string);
                        }
                    }
                }
                if (jSONObject2.has(WBConstants.GAME_PARAMS_GAME_IMAGE_URL)) {
                    jSONObject = jSONObject2.getJSONObject(WBConstants.GAME_PARAMS_GAME_IMAGE_URL);
                    keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        String string2 = jSONObject.getString(str);
                        identifier = resourcesForApplication.getIdentifier(str, SocializeConstants.WEIBO_ID, a);
                        int identifier2 = resourcesForApplication.getIdentifier(string2, "drawable", a);
                        if (identifier > 0) {
                            remoteViews.setImageViewResource(identifier, identifier2);
                        }
                    }
                }
                if (jSONObject2.has("time")) {
                    jSONObject2 = jSONObject2.getJSONObject("time");
                    keys = jSONObject2.keys();
                    while (keys.hasNext()) {
                        str = (String) keys.next();
                        str2 = jSONObject2.getString(str);
                        if (str2.length() == 0) {
                            str2 = "yy-MM-dd hh:mm";
                        }
                        identifier = resourcesForApplication.getIdentifier(str, SocializeConstants.WEIBO_ID, a);
                        if (identifier > 0) {
                            remoteViews.setTextViewText(identifier, new SimpleDateFormat(str2).format(new Date(System.currentTimeMillis())));
                        }
                    }
                }
                return remoteViews;
            } catch (Throwable e) {
                b.a(e);
                return null;
            }
        } catch (Throwable e2) {
            b.a(e2);
            return null;
        }
    }

    public static void b(Context context, String str) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
        synchronized (b) {
            Iterator it = ((LinkedList) b.clone()).iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                if (TextUtils.equals((CharSequence) pair.second, str)) {
                    notificationManager.cancel(((Integer) pair.first).intValue());
                    b.remove(pair);
                }
            }
        }
    }

    static void b(Context context, String str, int i) {
        context.getSharedPreferences("pref_notify_type", 0).edit().putInt(str, i).commit();
    }

    public static boolean b(o oVar) {
        i m = oVar.m();
        return m != null && m.v();
    }

    static int c(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).getInt(str, InMobiClientPositioning.NO_REPEAT);
    }

    static void d(Context context, String str) {
        context.getSharedPreferences("pref_notify_type", 0).edit().remove(str).commit();
    }

    static boolean e(Context context, String str) {
        return context.getSharedPreferences("pref_notify_type", 0).contains(str);
    }

    private static int f(Context context, String str) {
        int a = a(context, str, "mipush_notification");
        int a2 = a(context, str, "mipush_small_notification");
        if (a <= 0) {
            a = a2 <= 0 ? context.getApplicationInfo().icon : a2;
        }
        return (a != 0 || VERSION.SDK_INT < 9) ? a : context.getApplicationInfo().logo;
    }
}
