package android.support.design.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: BottomSheetBehavior.java
final class f implements Creator<SavedState> {
    f() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new SavedState[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }
}
