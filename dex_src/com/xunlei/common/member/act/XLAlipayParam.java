package com.xunlei.common.member.act;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.xunlei.xiazaibao.BuildConfig;

public class XLAlipayParam implements Parcelable {
    public static final Creator<XLAlipayParam> CREATOR;
    private String mAppId;
    public int mNeedLoading;
    public String mTargetId;

    public XLAlipayParam() {
        this.mAppId = BuildConfig.VERSION_NAME;
        this.mTargetId = BuildConfig.VERSION_NAME;
        this.mNeedLoading = 1;
    }

    public XLAlipayParam(Parcel parcel) {
        this.mAppId = BuildConfig.VERSION_NAME;
        this.mTargetId = BuildConfig.VERSION_NAME;
        this.mNeedLoading = 1;
        this.mAppId = parcel.readString();
        this.mTargetId = parcel.readString();
        this.mNeedLoading = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAppId);
        parcel.writeString(this.mTargetId);
        parcel.writeInt(this.mNeedLoading);
    }

    static {
        CREATOR = new Creator<XLAlipayParam>() {
            public final XLAlipayParam[] newArray(int i) {
                return new XLAlipayParam[i];
            }

            public final XLAlipayParam createFromParcel(Parcel parcel) {
                return new XLAlipayParam(parcel);
            }
        };
    }
}
