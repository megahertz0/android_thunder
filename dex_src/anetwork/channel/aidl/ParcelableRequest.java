package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import anet.channel.request.BodyEntry;
import anet.channel.util.ALog;
import anetwork.channel.a;
import anetwork.channel.entity.k;
import anetwork.channel.f;
import anetwork.channel.g;
import java.util.ArrayList;
import java.util.List;

// compiled from: Taobao
public class ParcelableRequest implements Parcelable {
    public static final Creator<ParcelableRequest> CREATOR;
    public long a;
    public BodyEntry b;
    public int c;
    public String d;
    public String e;
    public boolean f;
    public List<a> g;
    public String h;
    public List<f> i;
    public int j;
    public int k;
    public int l;
    public String m;
    public boolean n;
    public boolean o;
    private g p;

    public ParcelableRequest() {
        this.g = new ArrayList();
        this.i = new ArrayList();
        this.o = true;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.p != null) {
            try {
                int i2;
                int i3;
                parcel.writeInt(this.p.d());
                parcel.writeString(this.d.toString());
                parcel.writeString(this.p.f());
                parcel.writeBooleanArray(new boolean[]{this.p.a()});
                parcel.writeString(this.p.c());
                List arrayList = new ArrayList();
                if (this.p.b() != null) {
                    for (i2 = 0; i2 < this.p.b().size(); i2++) {
                        if (this.p.b().get(i2) != null) {
                            arrayList.add(((a) this.p.b().get(i2)).a() + com.alipay.sdk.sys.a.b + ((a) this.p.b().get(i2)).b());
                        }
                    }
                }
                parcel.writeList(arrayList);
                arrayList = this.p.e();
                List arrayList2 = new ArrayList();
                if (arrayList != null) {
                    for (i2 = 0; i2 < arrayList.size(); i2++) {
                        f fVar = (f) arrayList.get(i2);
                        if (fVar != null) {
                            arrayList2.add(fVar.a() + com.alipay.sdk.sys.a.b + fVar.b());
                        }
                    }
                }
                parcel.writeList(arrayList2);
                parcel.writeParcelable(this.b, 0);
                parcel.writeLong(this.a);
                parcel.writeInt(this.p.g());
                parcel.writeInt(this.p.h());
                parcel.writeInt(this.p.i());
                parcel.writeString(this.p.j());
                parcel.writeInt(this.n ? 1 : 0);
                if (this.o) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                parcel.writeInt(i3);
            } catch (Throwable th) {
                ALog.w("ANet.ParcelableRequest", "[writeToParcel]", null, th, new Object[0]);
            }
        }
    }

    public static ParcelableRequest a(Parcel parcel) {
        ParcelableRequest parcelableRequest = new ParcelableRequest();
        try {
            int i;
            int indexOf;
            boolean z;
            parcelableRequest.c = parcel.readInt();
            parcelableRequest.d = parcel.readString();
            parcelableRequest.e = parcel.readString();
            boolean[] zArr = new boolean[1];
            parcel.readBooleanArray(zArr);
            parcelableRequest.f = zArr[0];
            parcelableRequest.h = parcel.readString();
            ArrayList arrayList = new ArrayList();
            parcel.readList(arrayList, ParcelableRequest.class.getClassLoader());
            for (i = 0; i < arrayList.size(); i++) {
                String str = (String) arrayList.get(i);
                if (str != null) {
                    indexOf = str.indexOf(com.alipay.sdk.sys.a.b);
                    if (indexOf != -1 && indexOf != str.length() - 1) {
                        parcelableRequest.g.add(new anetwork.channel.entity.a(str.substring(0, indexOf), str.substring(indexOf + 1)));
                    }
                }
            }
            List readArrayList = parcel.readArrayList(ParcelableRequest.class.getClassLoader());
            if (readArrayList != null) {
                for (i = 0; i < readArrayList.size(); i++) {
                    str = (String) readArrayList.get(i);
                    if (str != null) {
                        indexOf = str.indexOf(com.alipay.sdk.sys.a.b);
                        if (indexOf != -1 && indexOf != str.length() - 1) {
                            parcelableRequest.i.add(new k(str.substring(0, indexOf), str.substring(indexOf + 1)));
                        }
                    }
                }
            }
            parcelableRequest.b = (BodyEntry) parcel.readParcelable(ParcelableRequest.class.getClassLoader());
            parcelableRequest.a = parcel.readLong();
            parcelableRequest.j = parcel.readInt();
            parcelableRequest.k = parcel.readInt();
            parcelableRequest.l = parcel.readInt();
            parcelableRequest.m = parcel.readString();
            parcelableRequest.n = parcel.readInt() == 1;
            if (parcel.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            parcelableRequest.o = z;
        } catch (Throwable th) {
            ALog.w("ANet.ParcelableRequest", "[readFromParcel]", null, th, new Object[0]);
        }
        return parcelableRequest;
    }

    static {
        CREATOR = new m();
    }
}
