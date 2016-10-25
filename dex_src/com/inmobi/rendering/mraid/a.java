package com.inmobi.rendering.mraid;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.provider.CalendarContract.Events;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.download.DownloadManager;
import com.xunlei.tdlive.WebBrowserActivity;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

// compiled from: CalendarUtil.java
public class a {
    private static final SimpleDateFormat[] a;
    private static String b;

    static {
        a = new SimpleDateFormat[]{new SimpleDateFormat("yyyy-MM-dd'T'hh:mmZ", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz", Locale.US), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US), new SimpleDateFormat("yyyy-MM-dd", Locale.US), new SimpleDateFormat("yyyy-MM", Locale.US), new SimpleDateFormat("yyyyMMddHHmmssZ", Locale.US), new SimpleDateFormat("yyyyMMddHHmm", Locale.US), new SimpleDateFormat("yyyyMMdd", Locale.US), new SimpleDateFormat("yyyyMM", Locale.US), new SimpleDateFormat("yyyy", Locale.US)};
        b = a.class.getSimpleName();
    }

    @TargetApi(14)
    public static int a(Context context) {
        Cursor query = context.getContentResolver().query(Events.CONTENT_URI, new String[]{DownloadManager.COLUMN_ID, WebBrowserActivity.EXTRA_TITLE}, null, null, null);
        if (query == null || !query.moveToLast()) {
            return 0;
        }
        int columnIndex = query.getColumnIndex(WebBrowserActivity.EXTRA_TITLE);
        int columnIndex2 = query.getColumnIndex(DownloadManager.COLUMN_ID);
        String string = query.getString(columnIndex);
        String string2 = query.getString(columnIndex2);
        if (string != null) {
            columnIndex = Integer.parseInt(string2);
        } else {
            columnIndex = 0;
        }
        query.close();
        return columnIndex;
    }

    @SuppressLint({"SimpleDateFormat"})
    public static String a(String str) {
        String str2 = null;
        int i = 0;
        if (!(str == null || com.umeng.a.d.equals(str))) {
            Date parse;
            SimpleDateFormat[] simpleDateFormatArr = a;
            int length = simpleDateFormatArr.length;
            for (int i2 = 0; i2 < length; i2++) {
                try {
                    parse = simpleDateFormatArr[i2].parse(str);
                    break;
                } catch (ParseException e) {
                }
            }
            parse = null;
            if (parse != null) {
                DateFormat[] dateFormatArr = new DateFormat[]{new SimpleDateFormat("yyyyMMdd'T'HHmmssZ", Locale.US), new SimpleDateFormat("yyyyMMdd'T'HHmm", Locale.US), new SimpleDateFormat("yyyyMMdd", Locale.US)};
                while (i < 3) {
                    try {
                        str2 = dateFormatArr[i].format(Long.valueOf(parse.getTime()));
                        break;
                    } catch (IllegalArgumentException e2) {
                        i++;
                    }
                }
            }
        }
        return str2;
    }

    public static GregorianCalendar b(String str) {
        SimpleDateFormat[] simpleDateFormatArr = a;
        int length = simpleDateFormatArr.length;
        for (int i = 0; i < length; i++) {
            SimpleDateFormat simpleDateFormat = simpleDateFormatArr[i];
            try {
                Date parse = simpleDateFormat.parse(str);
                Calendar gregorianCalendar = new GregorianCalendar();
                gregorianCalendar.setTime(parse);
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Date format: ").append(simpleDateFormat.toPattern()).toString());
                return (GregorianCalendar) gregorianCalendar;
            } catch (ParseException e) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Skipping format: ").append(simpleDateFormat.toPattern()).toString());
            }
        }
        return null;
    }

    public static String a(JSONArray jSONArray, int i, int i2) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i3 = 0;
        while (i3 < jSONArray.length()) {
            try {
                int i4 = jSONArray.getInt(i3);
                if (i4 < i || i4 > i2 || i4 == 0) {
                    Logger.a(InternalLogLevel.INTERNAL, b, "Value not in range");
                } else {
                    stringBuilder.append(i4).append(MiPushClient.ACCEPT_TIME_SEPARATOR);
                }
                i3++;
            } catch (JSONException e) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Could not parse day ").append(e.getMessage()).toString());
                return null;
            }
        }
        String toString = stringBuilder.toString();
        int length = toString.length();
        if (length == 0) {
            return null;
        }
        return toString.charAt(length + -1) == ',' ? toString.substring(0, length - 1) : toString;
    }

    public static String a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                stringBuilder.append(jSONArray.get(i) + MiPushClient.ACCEPT_TIME_SEPARATOR);
                i++;
            } catch (JSONException e) {
                Logger.a(InternalLogLevel.INTERNAL, b, new StringBuilder("Could not parse day object ").append(e.toString()).toString());
                return null;
            }
        }
        String toString = stringBuilder.toString();
        int length = toString.length();
        if (length == 0) {
            return null;
        }
        return toString.charAt(length + -1) == ',' ? toString.substring(0, length - 1) : toString;
    }
}
