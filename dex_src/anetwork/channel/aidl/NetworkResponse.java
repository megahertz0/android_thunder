package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anetwork.channel.f.a;
import java.util.List;
import java.util.Map;

// compiled from: Taobao
public class NetworkResponse implements Parcelable {
    public static final Creator<NetworkResponse> CREATOR;
    int a;
    public byte[] b;
    public Map<String, List<String>> c;
    public a d;
    private String e;
    private Throwable f;

    public final void a(int i) {
        this.a = i;
        this.e = ErrorConstant.getErrMsg(i);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("NetworkResponse [");
        stringBuilder.append("statusCode=").append(this.a);
        stringBuilder.append(", desc=").append(this.e);
        stringBuilder.append(", connHeadFields=").append(this.c);
        stringBuilder.append(", bytedata=").append(this.b != null ? new String(this.b) : com.umeng.a.d);
        stringBuilder.append(", error=").append(this.f);
        stringBuilder.append(", statisticData=").append(this.d).append("]");
        return stringBuilder.toString();
    }

    public NetworkResponse(byte b) {
        this('\u0000');
    }

    private NetworkResponse(char c) {
        this.a = -201;
        this.e = ErrorConstant.getErrMsg(ErrorConstant.ERROR_REQUEST_FAIL);
        this.b = null;
        this.c = null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeString(this.e);
        int i2 = 0;
        if (this.b != null) {
            i2 = this.b.length;
        }
        parcel.writeInt(i2);
        if (i2 > 0) {
            parcel.writeByteArray(this.b);
        }
        parcel.writeMap(this.c);
        if (this.d != null) {
            parcel.writeSerializable(this.d);
        }
    }

    public static NetworkResponse a(Parcel parcel) {
        NetworkResponse networkResponse = new NetworkResponse();
        try {
            networkResponse.a = parcel.readInt();
            networkResponse.e = parcel.readString();
            int readInt = parcel.readInt();
            if (readInt > 0) {
                networkResponse.b = new byte[readInt];
                parcel.readByteArray(networkResponse.b);
            }
            networkResponse.c = parcel.readHashMap(NetworkResponse.class.getClassLoader());
            try {
                networkResponse.d = (a) parcel.readSerializable();
            } catch (Throwable th) {
                ALog.i("ANet.NetworkResponse", "[readFromParcel] source.readSerializable() error", null, new Object[0]);
            }
        } catch (Throwable e) {
            ALog.w("ANet.NetworkResponse", "[readFromParcel]", null, e, new Object[0]);
        }
        return networkResponse;
    }

    static {
        CREATOR = new e();
    }
}
