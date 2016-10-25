package com.uc.addon.sdk.remote.protocol;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.umeng.a;

public class HistoryItem implements Parcelable {
    public static final Creator CREATOR;
    public static final int TYPE_PAGE = 0;
    public static final int TYPE_VIDEO = 1;
    public String title;
    public int type;
    public String url;
    public int visitedCount;
    public long visitedTime;

    static {
        CREATOR = new Creator() {
            public final HistoryItem createFromParcel(Parcel parcel) {
                return new HistoryItem(parcel);
            }

            public final HistoryItem[] newArray(int i) {
                return new HistoryItem[i];
            }
        };
    }

    public HistoryItem() {
        this.title = a.d;
        this.url = a.d;
        this.type = 0;
        this.visitedCount = 1;
        this.visitedTime = System.currentTimeMillis();
    }

    public HistoryItem(Parcel parcel) {
        this.title = a.d;
        this.url = a.d;
        this.type = 0;
        this.visitedCount = 1;
        this.visitedTime = System.currentTimeMillis();
        this.title = parcel.readString();
        this.url = parcel.readString();
        this.type = parcel.readInt();
        this.visitedCount = parcel.readInt();
        this.visitedTime = parcel.readLong();
    }

    public int describeContents() {
        return TYPE_PAGE;
    }

    public String toString() {
        return new StringBuilder("HistoryItem [title=").append(this.title).append(", url=").append(this.url).append(", type=").append(this.type).append(", visitedCount=").append(this.visitedCount).append(", visitedTime=").append(this.visitedTime).append("]").toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.url);
        parcel.writeInt(this.type);
        parcel.writeInt(this.visitedCount);
        parcel.writeLong(this.visitedTime);
    }
}
