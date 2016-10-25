package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: SearchView.java
final class ca implements Creator<SavedState> {
    ca() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new SavedState[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }
}
