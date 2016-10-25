package android.support.design.widget;

import android.os.Parcel;
import android.support.design.widget.NavigationView.SavedState;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

// compiled from: NavigationView.java
final class af implements ParcelableCompatCreatorCallbacks<SavedState> {
    af() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new SavedState[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new SavedState(parcel, classLoader);
    }
}
