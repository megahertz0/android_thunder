package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: Taobao
final class l implements Creator<ParcelableObject> {
    l() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new ParcelableObject[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new ParcelableObject((byte) 0);
    }
}
