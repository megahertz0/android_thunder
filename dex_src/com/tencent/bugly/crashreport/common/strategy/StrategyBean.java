package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.proguard.a;
import java.util.Map;

// compiled from: BUGLY
public class StrategyBean implements Parcelable {
    public static final Creator<StrategyBean> CREATOR;
    public static String a;
    private static String w;
    private static String x;
    private static String y;
    public long b;
    public long c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public boolean l;
    public long m;
    public long n;
    public String o;
    public String p;
    public String q;
    public String r;
    public Map<String, String> s;
    public int t;
    public long u;
    public long v;

    static {
        w = "http://android.bugly.qq.com/rqd/async";
        x = "http://android.bugly.qq.com/rqd/async";
        y = "http://rqd.uu.qq.com/rqd/sync";
        CREATOR = new Creator<StrategyBean>() {
            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new StrategyBean(parcel);
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new StrategyBean[i];
            }
        };
    }

    public StrategyBean() {
        this.b = -1;
        this.c = -1;
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = false;
        this.i = true;
        this.j = true;
        this.k = true;
        this.l = true;
        this.n = 30000;
        this.o = w;
        this.p = x;
        this.q = y;
        this.t = 10;
        this.u = 300000;
        this.v = -1;
        this.c = System.currentTimeMillis();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("S(@L@L@)");
        a = stringBuilder.toString();
        stringBuilder.setLength(0);
        stringBuilder.append("*^@K#K@!");
        this.r = stringBuilder.toString();
    }

    public StrategyBean(Parcel parcel) {
        boolean z = true;
        this.b = -1;
        this.c = -1;
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = false;
        this.i = true;
        this.j = true;
        this.k = true;
        this.l = true;
        this.n = 30000;
        this.o = w;
        this.p = x;
        this.q = y;
        this.t = 10;
        this.u = 300000;
        this.v = -1;
        try {
            boolean z2;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("S(@L@L@)");
            a = stringBuilder.toString();
            this.c = parcel.readLong();
            this.d = parcel.readByte() == (byte) 1;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.e = z2;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f = z2;
            this.o = parcel.readString();
            this.p = parcel.readString();
            this.r = parcel.readString();
            this.s = a.b(parcel);
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.g = z2;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.h = z2;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.k = z2;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.l = z2;
            this.n = parcel.readLong();
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.i = z2;
            if (parcel.readByte() != (byte) 1) {
                z = false;
            }
            this.j = z;
            this.m = parcel.readLong();
            this.t = parcel.readInt();
            this.u = parcel.readLong();
            this.v = parcel.readLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeLong(this.c);
        parcel.writeByte((byte) (this.d ? 1 : 0));
        if (this.e) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.r);
        a.b(parcel, this.s);
        if (this.g) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.h) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.k) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.l) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeLong(this.n);
        if (this.i) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!this.j) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeLong(this.m);
        parcel.writeInt(this.t);
        parcel.writeLong(this.u);
        parcel.writeLong(this.v);
    }
}
