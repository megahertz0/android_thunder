package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import anet.channel.util.ALog;
import java.util.List;
import java.util.Map;

// compiled from: Taobao
public class ParcelableHeader implements Parcelable {
    public static Creator<ParcelableHeader> c;
    public int a;
    public Map<String, List<String>> b;

    public ParcelableHeader(int i, Map<String, List<String>> map) {
        this.b = map;
        this.a = i;
    }

    ParcelableHeader() {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.b != null) {
            parcel.writeInt(1);
            parcel.writeMap(this.b);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeInt(this.a);
    }

    static ParcelableHeader a(Parcel parcel) {
        ParcelableHeader parcelableHeader = new ParcelableHeader();
        try {
            if (parcel.readInt() == 1) {
                parcelableHeader.b = parcel.readHashMap(ParcelableHeader.class.getClassLoader());
            }
            parcelableHeader.a = parcel.readInt();
        } catch (Throwable th) {
            ALog.e("ANet.ParcelableHeader", "[readFromParcel]", null, th, new Object[0]);
        }
        return parcelableHeader;
    }

    static {
        c = new i();
    }

    public String toString() {
        return new StringBuilder("ParcelableResponseHeader [responseCode=").append(this.a).append(", header=").append(this.b).append("]").toString();
    }
}
