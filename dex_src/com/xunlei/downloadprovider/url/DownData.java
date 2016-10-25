package com.xunlei.downloadprovider.url;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.xunlei.downloadprovider.model.protocol.a.a;
import java.util.List;

public class DownData implements Parcelable {
    public static final Creator<DownData> CREATOR;
    @Deprecated
    public boolean A;
    public String B;
    public String C;
    public String D;
    public String a;
    @Deprecated
    public String b;
    public String c;
    public String d;
    public String e;
    @Deprecated
    public int f;
    @Deprecated
    public int g;
    @Deprecated
    public String h;
    @Deprecated
    public String i;
    @Deprecated
    public int j;
    @Deprecated
    public boolean k;
    @Deprecated
    public String l;
    @Deprecated
    public boolean m;
    @Deprecated
    public boolean n;
    @Deprecated
    public boolean o;
    @Deprecated
    public int p;
    @Deprecated
    public String q;
    public long r;
    public String s;
    @Deprecated
    public boolean t;
    @Deprecated
    public List<String> u;
    @Deprecated
    public String v;
    @Deprecated
    public int w;
    @Deprecated
    public int x;
    @Deprecated
    public List<a> y;
    @Deprecated
    public List<a> z;

    public DownData() {
        this.a = com.umeng.a.d;
        this.b = com.umeng.a.d;
        this.c = com.umeng.a.d;
        this.d = com.umeng.a.d;
        this.e = com.umeng.a.d;
        this.h = com.umeng.a.d;
        this.i = com.umeng.a.d;
        this.l = com.umeng.a.d;
        this.m = false;
        this.n = false;
        this.o = true;
        this.q = com.umeng.a.d;
        this.s = com.umeng.a.d;
        this.A = false;
        this.B = com.umeng.a.d;
        this.C = com.umeng.a.d;
        this.D = com.umeng.a.d;
    }

    public DownData(String str, String str2, String str3) {
        this.a = com.umeng.a.d;
        this.b = com.umeng.a.d;
        this.c = com.umeng.a.d;
        this.d = com.umeng.a.d;
        this.e = com.umeng.a.d;
        this.h = com.umeng.a.d;
        this.i = com.umeng.a.d;
        this.l = com.umeng.a.d;
        this.m = false;
        this.n = false;
        this.o = true;
        this.q = com.umeng.a.d;
        this.s = com.umeng.a.d;
        this.A = false;
        this.B = com.umeng.a.d;
        this.C = com.umeng.a.d;
        this.D = com.umeng.a.d;
        this.a = str;
        this.b = str2;
        this.e = str2;
        this.r = 0;
        this.f = 0;
        this.s = str3;
    }

    public String toString() {
        return new StringBuilder("DownData [name=").append(this.a).append(", path=").append(this.b).append("]").toString();
    }

    static {
        CREATOR = new a();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeInt(this.j);
        if (this.k) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.l);
        if (this.m) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
        if (this.n) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
        if (this.o) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.p);
        parcel.writeLong(this.r);
        parcel.writeString(this.s);
        parcel.writeString(this.q);
        if (this.t) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
        if (this.u == null || this.u.size() <= 0) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(this.u.size());
        for (String str : this.u) {
            parcel.writeString(str);
        }
    }
}
