package com.xiaomi.smack.packet;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.xiaomi.smack.util.g;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class a implements e {
    private String a;
    private String b;
    private String[] c;
    private String[] d;
    private String e;
    private List<a> f;

    public a(String str, String str2, String[] strArr, String[] strArr2) {
        this.c = null;
        this.d = null;
        this.f = null;
        this.a = str;
        this.b = str2;
        this.c = strArr;
        this.d = strArr2;
    }

    public a(String str, String str2, String[] strArr, String[] strArr2, String str3, List<a> list) {
        this.c = null;
        this.d = null;
        this.f = null;
        this.a = str;
        this.b = str2;
        this.c = strArr;
        this.d = strArr2;
        this.e = str3;
        this.f = list;
    }

    public static a a(Bundle bundle) {
        List arrayList;
        int i = 0;
        String string = bundle.getString("ext_ele_name");
        String string2 = bundle.getString("ext_ns");
        String string3 = bundle.getString("ext_text");
        Bundle bundle2 = bundle.getBundle("attributes");
        Set<String> keySet = bundle2.keySet();
        String[] strArr = new String[keySet.size()];
        String[] strArr2 = new String[keySet.size()];
        int i2 = 0;
        for (String str : keySet) {
            strArr[i2] = str;
            strArr2[i2] = bundle2.getString(str);
            i2++;
        }
        if (bundle.containsKey("children")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("children");
            arrayList = new ArrayList(parcelableArray.length);
            int length = parcelableArray.length;
            while (i < length) {
                arrayList.add(a((Bundle) parcelableArray[i]));
                i++;
            }
        } else {
            arrayList = null;
        }
        return new a(string, string2, strArr, strArr2, string3, arrayList);
    }

    public static Parcelable[] a(List<a> list) {
        return a((a[]) list.toArray(new a[list.size()]));
    }

    public static Parcelable[] a(a[] aVarArr) {
        if (aVarArr == null) {
            return null;
        }
        Parcelable[] parcelableArr = new Parcelable[aVarArr.length];
        for (int i = 0; i < aVarArr.length; i++) {
            parcelableArr[i] = aVarArr[i].f();
        }
        return parcelableArr;
    }

    public String a() {
        return this.a;
    }

    public String a(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (this.c != null) {
            for (int i = 0; i < this.c.length; i++) {
                if (str.equals(this.c[i])) {
                    return this.d[i];
                }
            }
        }
        return null;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            this.e = str;
        } else {
            this.e = g.a(str);
        }
    }

    public String c() {
        return !TextUtils.isEmpty(this.e) ? g.b(this.e) : this.e;
    }

    public String d() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<").append(this.a);
        if (!TextUtils.isEmpty(this.b)) {
            stringBuilder.append(" xmlns=\"").append(this.b).append(h.f);
        }
        if (this.c != null && this.c.length > 0) {
            for (int i = 0; i < this.c.length; i++) {
                if (!TextUtils.isEmpty(this.d[i])) {
                    stringBuilder.append(" ").append(this.c[i]).append("=\"").append(g.a(this.d[i])).append(h.f);
                }
            }
        }
        if (!TextUtils.isEmpty(this.e)) {
            stringBuilder.append(">").append(this.e).append("</").append(this.a).append(">");
        } else if (this.f == null || this.f.size() <= 0) {
            stringBuilder.append("/>");
        } else {
            stringBuilder.append(">");
            for (a aVar : this.f) {
                stringBuilder.append(aVar.d());
            }
            stringBuilder.append("</").append(this.a).append(">");
        }
        return stringBuilder.toString();
    }

    public Bundle e() {
        Bundle bundle = new Bundle();
        bundle.putString("ext_ele_name", this.a);
        bundle.putString("ext_ns", this.b);
        bundle.putString("ext_text", this.e);
        Bundle bundle2 = new Bundle();
        if (this.c != null && this.c.length > 0) {
            for (int i = 0; i < this.c.length; i++) {
                bundle2.putString(this.c[i], this.d[i]);
            }
        }
        bundle.putBundle("attributes", bundle2);
        if (this.f != null && this.f.size() > 0) {
            bundle.putParcelableArray("children", a(this.f));
        }
        return bundle;
    }

    public Parcelable f() {
        return e();
    }

    public String toString() {
        return d();
    }
}
