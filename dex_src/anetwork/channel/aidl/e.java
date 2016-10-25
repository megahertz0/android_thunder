package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: Taobao
final class e implements Creator<NetworkResponse> {
    e() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new NetworkResponse[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return NetworkResponse.a(parcel);
    }
}
