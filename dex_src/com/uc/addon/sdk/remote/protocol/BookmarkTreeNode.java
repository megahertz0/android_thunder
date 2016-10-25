package com.uc.addon.sdk.remote.protocol;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class BookmarkTreeNode implements Parcelable {
    public static final Creator CREATOR;
    public static final int ROOT_DIRECTORY_ID = 0;
    public int mId;
    public int mParentId;
    public String mTitle;
    public String mUrl;

    static {
        CREATOR = new Creator() {
            public final BookmarkTreeNode createFromParcel(Parcel parcel) {
                return new BookmarkTreeNode((byte) 0);
            }

            public final BookmarkTreeNode[] newArray(int i) {
                return new BookmarkTreeNode[i];
            }
        };
    }

    private BookmarkTreeNode(Parcel parcel) {
        this.mId = parcel.readInt();
        this.mParentId = parcel.readInt();
        this.mTitle = parcel.readString();
        this.mUrl = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public boolean isFolder() {
        return this.mUrl == null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mParentId);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mUrl);
    }
}
