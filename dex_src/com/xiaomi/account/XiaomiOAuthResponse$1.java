package com.xiaomi.account;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class XiaomiOAuthResponse$1 implements Creator<XiaomiOAuthResponse> {
    XiaomiOAuthResponse$1() {
    }

    public final XiaomiOAuthResponse createFromParcel(Parcel parcel) {
        return new XiaomiOAuthResponse(parcel);
    }

    public final XiaomiOAuthResponse[] newArray(int i) {
        return new XiaomiOAuthResponse[i];
    }
}
