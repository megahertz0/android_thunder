package android.support.v7.app;

import android.os.Parcel;
import android.support.v4.os.ParcelableCompatCreatorCallbacks;

// compiled from: AppCompatDelegateImplV7.java
final class z implements ParcelableCompatCreatorCallbacks<SavedState> {
    z() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new SavedState[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return SavedState.a(parcel, classLoader);
    }
}
