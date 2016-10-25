package android.support.design.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: TextInputLayout.java
final class bd implements Creator<SavedState> {
    bd() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new SavedState[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }
}
