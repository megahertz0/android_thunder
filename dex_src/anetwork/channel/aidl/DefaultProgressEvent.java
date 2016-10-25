package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

// compiled from: Taobao
public class DefaultProgressEvent implements Parcelable {
    public static final Creator<DefaultProgressEvent> CREATOR;
    public int a;
    public int b;
    public int c;
    public String d;
    public Object e;
    public byte[] f;

    public String toString() {
        return new StringBuilder("DefaultProgressEvent [index=").append(this.a).append(", size=").append(this.b).append(", total=").append(this.c).append(", desc=").append(this.d).append("]").toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.f != null ? this.f.length : 0);
        parcel.writeByteArray(this.f);
    }

    public static DefaultProgressEvent a(Parcel parcel) {
        DefaultProgressEvent defaultProgressEvent = new DefaultProgressEvent();
        try {
            defaultProgressEvent.a = parcel.readInt();
            defaultProgressEvent.b = parcel.readInt();
            defaultProgressEvent.c = parcel.readInt();
            defaultProgressEvent.d = parcel.readString();
            int readInt = parcel.readInt();
            if (readInt > 0) {
                byte[] bArr = new byte[readInt];
                parcel.readByteArray(bArr);
                defaultProgressEvent.f = bArr;
            }
        } catch (Exception e) {
        }
        return defaultProgressEvent;
    }

    static {
        CREATOR = new c();
    }
}
