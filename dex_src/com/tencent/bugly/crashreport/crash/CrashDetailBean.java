package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.umeng.a;
import java.util.Map;
import java.util.UUID;

// compiled from: BUGLY
public class CrashDetailBean implements Parcelable, Comparable<CrashDetailBean> {
    public static final Creator<CrashDetailBean> CREATOR;
    public String A;
    public long B;
    public long C;
    public long D;
    public long E;
    public long F;
    public long G;
    public String H;
    public String I;
    public String J;
    public String K;
    public long L;
    public boolean M;
    public Map<String, String> N;
    public int O;
    public int P;
    public Map<String, String> Q;
    public Map<String, String> R;
    public byte[] S;
    public String T;
    public String U;
    private String V;
    public long a;
    public int b;
    public String c;
    public boolean d;
    public String e;
    public String f;
    public String g;
    public Map<String, PlugInBean> h;
    public Map<String, PlugInBean> i;
    public boolean j;
    public boolean k;
    public int l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public long r;
    public String s;
    public int t;
    public String u;
    public String v;
    public String w;
    public byte[] x;
    public Map<String, String> y;
    public String z;

    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        CrashDetailBean crashDetailBean = (CrashDetailBean) obj;
        if (crashDetailBean != null) {
            long j = this.r - crashDetailBean.r;
            if (j <= 0) {
                return j < 0 ? -1 : 0;
            }
        }
        return 1;
    }

    public CrashDetailBean() {
        this.a = -1;
        this.b = 0;
        this.c = UUID.randomUUID().toString();
        this.d = false;
        this.e = a.d;
        this.f = a.d;
        this.g = a.d;
        this.h = null;
        this.i = null;
        this.j = false;
        this.k = false;
        this.l = 0;
        this.m = a.d;
        this.n = a.d;
        this.o = a.d;
        this.p = a.d;
        this.q = a.d;
        this.r = -1;
        this.s = null;
        this.t = 0;
        this.u = a.d;
        this.v = a.d;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = a.d;
        this.A = a.d;
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.E = -1;
        this.F = -1;
        this.G = -1;
        this.H = a.d;
        this.V = a.d;
        this.I = a.d;
        this.J = a.d;
        this.K = a.d;
        this.L = -1;
        this.M = false;
        this.N = null;
        this.O = -1;
        this.P = -1;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = a.d;
        this.U = null;
    }

    public CrashDetailBean(Parcel parcel) {
        boolean z;
        boolean z2 = true;
        this.a = -1;
        this.b = 0;
        this.c = UUID.randomUUID().toString();
        this.d = false;
        this.e = a.d;
        this.f = a.d;
        this.g = a.d;
        this.h = null;
        this.i = null;
        this.j = false;
        this.k = false;
        this.l = 0;
        this.m = a.d;
        this.n = a.d;
        this.o = a.d;
        this.p = a.d;
        this.q = a.d;
        this.r = -1;
        this.s = null;
        this.t = 0;
        this.u = a.d;
        this.v = a.d;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = a.d;
        this.A = a.d;
        this.B = -1;
        this.C = -1;
        this.D = -1;
        this.E = -1;
        this.F = -1;
        this.G = -1;
        this.H = a.d;
        this.V = a.d;
        this.I = a.d;
        this.J = a.d;
        this.K = a.d;
        this.L = -1;
        this.M = false;
        this.N = null;
        this.O = -1;
        this.P = -1;
        this.Q = null;
        this.R = null;
        this.S = null;
        this.T = a.d;
        this.U = null;
        this.b = parcel.readInt();
        this.c = parcel.readString();
        this.d = parcel.readByte() == (byte) 1;
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.j = z;
        if (parcel.readByte() == (byte) 1) {
            z = true;
        } else {
            z = false;
        }
        this.k = z;
        this.l = parcel.readInt();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readLong();
        this.s = parcel.readString();
        this.t = parcel.readInt();
        this.u = parcel.readString();
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.y = com.tencent.bugly.proguard.a.b(parcel);
        this.z = parcel.readString();
        this.A = parcel.readString();
        this.B = parcel.readLong();
        this.C = parcel.readLong();
        this.D = parcel.readLong();
        this.E = parcel.readLong();
        this.F = parcel.readLong();
        this.G = parcel.readLong();
        this.H = parcel.readString();
        this.V = parcel.readString();
        this.I = parcel.readString();
        this.J = parcel.readString();
        this.K = parcel.readString();
        this.L = parcel.readLong();
        if (parcel.readByte() != (byte) 1) {
            z2 = false;
        }
        this.M = z2;
        this.N = com.tencent.bugly.proguard.a.b(parcel);
        this.h = com.tencent.bugly.proguard.a.a(parcel);
        this.i = com.tencent.bugly.proguard.a.a(parcel);
        this.O = parcel.readInt();
        this.P = parcel.readInt();
        this.Q = com.tencent.bugly.proguard.a.b(parcel);
        this.R = com.tencent.bugly.proguard.a.b(parcel);
        this.S = parcel.createByteArray();
        this.x = parcel.createByteArray();
        this.T = parcel.readString();
        this.U = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
        parcel.writeByte((byte) (this.d ? 1 : 0));
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        if (this.j) {
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
        parcel.writeInt(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeLong(this.r);
        parcel.writeString(this.s);
        parcel.writeInt(this.t);
        parcel.writeString(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        com.tencent.bugly.proguard.a.b(parcel, this.y);
        parcel.writeString(this.z);
        parcel.writeString(this.A);
        parcel.writeLong(this.B);
        parcel.writeLong(this.C);
        parcel.writeLong(this.D);
        parcel.writeLong(this.E);
        parcel.writeLong(this.F);
        parcel.writeLong(this.G);
        parcel.writeString(this.H);
        parcel.writeString(this.V);
        parcel.writeString(this.I);
        parcel.writeString(this.J);
        parcel.writeString(this.K);
        parcel.writeLong(this.L);
        if (!this.M) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        com.tencent.bugly.proguard.a.b(parcel, this.N);
        com.tencent.bugly.proguard.a.a(parcel, this.h);
        com.tencent.bugly.proguard.a.a(parcel, this.i);
        parcel.writeInt(this.O);
        parcel.writeInt(this.P);
        com.tencent.bugly.proguard.a.b(parcel, this.Q);
        com.tencent.bugly.proguard.a.b(parcel, this.R);
        parcel.writeByteArray(this.S);
        parcel.writeByteArray(this.x);
        parcel.writeString(this.T);
        parcel.writeString(this.U);
    }

    static {
        CREATOR = new Creator<CrashDetailBean>() {
            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new CrashDetailBean(parcel);
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new CrashDetailBean[i];
            }
        };
    }
}
