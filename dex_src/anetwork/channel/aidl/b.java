package anetwork.channel.aidl;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: Taobao
final class b implements Creator<DefaultFinishEvent> {
    b() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new DefaultFinishEvent[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return DefaultFinishEvent.a(parcel);
    }
}
