package com.uc.addon.sdk.remote.protocol;

import android.os.Handler;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class BannerBuilderRemote implements Parcelable {
    public static final Creator CREATOR;
    public IBannerClickListener mBannerClickListener;
    public int mButtonID;
    public String mButtonLable;
    public String mTextLable;
    public int mTextLableID;

    static {
        CREATOR = new Creator() {
            public final BannerBuilderRemote createFromParcel(Parcel parcel) {
                return new BannerBuilderRemote((byte) 0);
            }

            public final BannerBuilderRemote[] newArray(int i) {
                return new BannerBuilderRemote[i];
            }
        };
    }

    private BannerBuilderRemote(Parcel parcel) {
        this.mTextLableID = parcel.readInt();
        this.mButtonID = parcel.readInt();
        this.mTextLable = parcel.readString();
        this.mButtonLable = parcel.readString();
        this.mBannerClickListener = BannerClickListener.asInterface(parcel.readStrongBinder());
    }

    public BannerBuilderRemote addButton(String str, int i) {
        this.mButtonLable = str;
        this.mButtonID = i;
        return this;
    }

    public BannerBuilderRemote addTextLabel(String str, int i) {
        this.mTextLable = str;
        this.mTextLableID = i;
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public void setBannerClickListener(BannerClickListener bannerClickListener) {
        this.mBannerClickListener = bannerClickListener;
    }

    public void setHandler(Handler handler) {
        if (handler != null && this.mBannerClickListener != null) {
            ((BannerClickListener) this.mBannerClickListener).a(handler);
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mTextLableID);
        parcel.writeInt(this.mButtonID);
        parcel.writeString(this.mTextLable);
        parcel.writeString(this.mButtonLable);
        parcel.writeStrongBinder((IBinder) this.mBannerClickListener);
    }
}
