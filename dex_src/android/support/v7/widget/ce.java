package android.support.v7.widget;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: StaggeredGridLayoutManager.java
final class ce implements Creator<FullSpanItem> {
    ce() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new FullSpanItem[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new FullSpanItem(parcel);
    }
}
