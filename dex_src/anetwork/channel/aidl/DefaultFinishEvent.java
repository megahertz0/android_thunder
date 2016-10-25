package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import anet.channel.util.ErrorConstant;
import anetwork.channel.d.a;

// compiled from: Taobao
public class DefaultFinishEvent implements Parcelable, a {
    public static final Creator<DefaultFinishEvent> CREATOR;
    public Object a;
    int b;
    public String c;
    anetwork.channel.f.a d;

    public final int a() {
        return this.b;
    }

    public final String b() {
        return this.c;
    }

    public final anetwork.channel.f.a c() {
        return this.d;
    }

    public DefaultFinishEvent(byte b) {
        this(-200, null);
    }

    public DefaultFinishEvent(int i, anetwork.channel.f.a aVar) {
        this.b = i;
        this.c = ErrorConstant.getErrMsg(i);
        this.d = aVar;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("DefaultFinishEvent [");
        stringBuilder.append("code=").append(this.b);
        stringBuilder.append(", desc=").append(this.c);
        stringBuilder.append(", context=").append(this.a);
        stringBuilder.append(", statisticData=").append(this.d);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
        if (this.d != null) {
            parcel.writeSerializable(this.d);
        }
    }

    static DefaultFinishEvent a(Parcel parcel) {
        DefaultFinishEvent defaultFinishEvent = new DefaultFinishEvent();
        try {
            defaultFinishEvent.b = parcel.readInt();
            defaultFinishEvent.c = parcel.readString();
            try {
                defaultFinishEvent.d = (anetwork.channel.f.a) parcel.readSerializable();
            } catch (Throwable th) {
            }
        } catch (Throwable th2) {
        }
        return defaultFinishEvent;
    }

    static {
        CREATOR = new b();
    }
}
