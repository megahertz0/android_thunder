package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.taobao.accs.utl.UtilityImpl;
import com.tencent.bugly.proguard.a;
import java.util.Map;

// compiled from: BUGLY
public class UserInfoBean implements Parcelable {
    public static final Creator<UserInfoBean> CREATOR;
    public long a;
    public int b;
    public String c;
    public String d;
    public long e;
    public long f;
    public long g;
    public long h;
    public long i;
    public String j;
    public long k;
    public boolean l;
    public String m;
    public String n;
    public int o;
    public int p;
    public int q;
    public Map<String, String> r;
    public Map<String, String> s;

    public UserInfoBean() {
        this.k = 0;
        this.l = false;
        this.m = UtilityImpl.NET_TYPE_UNKNOWN;
        this.p = -1;
        this.q = -1;
        this.r = null;
        this.s = null;
    }

    public UserInfoBean(Parcel parcel) {
        boolean z = true;
        this.k = 0;
        this.l = false;
        this.m = UtilityImpl.NET_TYPE_UNKNOWN;
        this.p = -1;
        this.q = -1;
        this.r = null;
        this.s = null;
        this.b = parcel.readInt();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readLong();
        this.f = parcel.readLong();
        this.g = parcel.readLong();
        this.h = parcel.readLong();
        this.i = parcel.readLong();
        this.j = parcel.readString();
        this.k = parcel.readLong();
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.l = z;
        this.m = parcel.readString();
        this.p = parcel.readInt();
        this.q = parcel.readInt();
        this.r = a.b(parcel);
        this.s = a.b(parcel);
        this.n = parcel.readString();
        this.o = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeLong(this.e);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
        parcel.writeLong(this.h);
        parcel.writeLong(this.i);
        parcel.writeString(this.j);
        parcel.writeLong(this.k);
        parcel.writeByte((byte) (this.l ? 1 : 0));
        parcel.writeString(this.m);
        parcel.writeInt(this.p);
        parcel.writeInt(this.q);
        a.b(parcel, this.r);
        a.b(parcel, this.s);
        parcel.writeString(this.n);
        parcel.writeInt(this.o);
    }

    static {
        CREATOR = new Creator<UserInfoBean>() {
            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new UserInfoBean(parcel);
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new UserInfoBean[i];
            }
        };
    }
}
