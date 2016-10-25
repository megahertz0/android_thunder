package com.alipay.sdk.protocol;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.tid.a;
import com.alipay.sdk.util.h;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.tdlive.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public final class b {
    public a a;
    public String[] b;
    private String c;

    private b(String str) {
        this.c = str;
    }

    private b(String str, a aVar) {
        this.c = str;
        this.a = aVar;
    }

    private static void a(b bVar) {
        String[] strArr = bVar.b;
        if (strArr.length == 3 && TextUtils.equals(com.alipay.sdk.cons.b.c, strArr[0])) {
            Context context = com.alipay.sdk.sys.b.a().a;
            com.alipay.sdk.tid.b a = com.alipay.sdk.tid.b.a();
            if (!TextUtils.isEmpty(strArr[1]) && !TextUtils.isEmpty(strArr[2])) {
                a.a = strArr[1];
                a.b = strArr[2];
                a aVar = new a(context);
                try {
                    aVar.a(com.alipay.sdk.util.a.a(context).a(), com.alipay.sdk.util.a.a(context).b(), a.a, a.b);
                    aVar.close();
                } catch (Exception e) {
                    aVar.close();
                }
            }
        }
    }

    public static List<b> a(JSONObject jSONObject) {
        List<b> arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        Object optString = jSONObject.optString(SelectCountryActivity.EXTRA_COUNTRY_NAME, com.umeng.a.d);
        String[] strArr = null;
        if (!TextUtils.isEmpty(optString)) {
            strArr = optString.split(h.b);
        }
        for (int i = 0; i < strArr.length; i++) {
            a a = a.a(strArr[i]);
            if (a != a.a) {
                b bVar = new b(strArr[i], a);
                bVar.b = a(strArr[i]);
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    private static String[] a(String str) {
        List arrayList = new ArrayList();
        int indexOf = str.indexOf(R.styleable.AppCompatTheme_textAppearanceLargePopupMenu);
        int lastIndexOf = str.lastIndexOf(R.styleable.AppCompatTheme_textAppearanceSmallPopupMenu);
        if (indexOf == -1 || lastIndexOf == -1 || lastIndexOf <= indexOf) {
            return null;
        }
        String[] split = str.substring(indexOf + 1, lastIndexOf).split(MiPushClient.ACCEPT_TIME_SEPARATOR);
        if (split != null) {
            for (indexOf = 0; indexOf < split.length; indexOf++) {
                if (!TextUtils.isEmpty(split[indexOf])) {
                    arrayList.add(split[indexOf].trim().replaceAll("'", com.umeng.a.d).replaceAll(h.f, com.umeng.a.d));
                }
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    private static String[] b(String str) {
        return !TextUtils.isEmpty(str) ? str.split(h.b) : null;
    }

    private String a() {
        return this.c;
    }

    private a b() {
        return this.a;
    }

    private String[] c() {
        return this.b;
    }
}
