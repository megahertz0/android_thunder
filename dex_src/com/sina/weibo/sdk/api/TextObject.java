package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.sina.weibo.sdk.utils.LogUtil;
import com.umeng.a;

public class TextObject extends BaseMediaObject {
    public static final Creator<TextObject> CREATOR;
    public String text;

    public TextObject(Parcel parcel) {
        this.text = parcel.readString();
    }

    static {
        CREATOR = new Creator<TextObject>() {
            public TextObject createFromParcel(Parcel parcel) {
                return new TextObject(parcel);
            }

            public TextObject[] newArray(int i) {
                return new TextObject[i];
            }
        };
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.text);
    }

    public boolean checkArgs() {
        if (this.text != null && this.text.length() != 0 && this.text.length() <= 1024) {
            return true;
        }
        LogUtil.e("Weibo.TextObject", "checkArgs fail, text is invalid");
        return false;
    }

    public int getObjType() {
        return 1;
    }

    protected BaseMediaObject toExtraMediaObject(String str) {
        return this;
    }

    protected String toExtraMediaString() {
        return a.d;
    }
}
