package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: Taobao
final class c implements Creator<DefaultProgressEvent> {
    c() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new DefaultProgressEvent[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return DefaultProgressEvent.a(parcel);
    }
}
