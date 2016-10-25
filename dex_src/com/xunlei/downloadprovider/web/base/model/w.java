package com.xunlei.downloadprovider.web.base.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.xunlei.downloadprovider.web.base.model.StateSyncManager.CommentSateInfo;

// compiled from: StateSyncManager.java
final class w implements Creator<CommentSateInfo> {
    w() {
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new CommentSateInfo[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new CommentSateInfo(parcel);
    }
}
