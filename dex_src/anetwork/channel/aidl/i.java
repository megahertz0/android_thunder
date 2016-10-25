package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: Taobao
final class i implements Creator<ParcelableHeader> {
    i() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ParcelableHeader[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return ParcelableHeader.a(parcel);
    }
}
