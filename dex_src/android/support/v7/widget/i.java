package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: ActionMenuPresenter.java
final class i implements Creator<SavedState> {
    i() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new SavedState[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new SavedState(parcel);
    }
}
