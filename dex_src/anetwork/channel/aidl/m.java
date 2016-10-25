package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: Taobao
final class m implements Creator<ParcelableRequest> {
    m() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ParcelableRequest[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return ParcelableRequest.a(parcel);
    }
}
