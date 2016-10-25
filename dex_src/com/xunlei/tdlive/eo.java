package com.xunlei.tdlive;

import android.os.Parcel;
import android.os.Parcelable.Creator;

// compiled from: StatService.java
final class eo implements Creator<EventData> {
    eo() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }

    public final EventData a(Parcel parcel) {
        BaseData baseData = (BaseData) BaseData.CREATOR.createFromParcel(parcel);
        EventData eventData = new EventData();
        eventData.a = baseData.a;
        eventData.b = baseData.b;
        int readInt = parcel.readInt();
        if ((readInt & 1) == 1) {
            eventData.c = parcel.readString();
        }
        if ((readInt & 16) == 16) {
            eventData.d = parcel.readString();
        }
        return eventData;
    }

    public final EventData[] a(int i) {
        return new EventData[i];
    }
}
