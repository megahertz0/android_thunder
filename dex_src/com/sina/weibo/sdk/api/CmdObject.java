package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.umeng.a;
import com.xunlei.tdlive.R;

public class CmdObject extends BaseMediaObject {
    public static final String CMD_HOME = "home";
    public static final Creator<CmdObject> CREATOR;
    public String cmd;

    static {
        CREATOR = new Creator<CmdObject>() {
            public CmdObject createFromParcel(Parcel parcel) {
                return new CmdObject(parcel);
            }

            public CmdObject[] newArray(int i) {
                return new CmdObject[i];
            }
        };
    }

    public CmdObject(Parcel parcel) {
        this.cmd = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cmd);
    }

    public boolean checkArgs() {
        return (this.cmd == null || this.cmd.length() == 0 || this.cmd.length() > 1024) ? false : true;
    }

    public int getObjType() {
        return R.styleable.Toolbar_contentInsetLeft;
    }

    protected BaseMediaObject toExtraMediaObject(String str) {
        return this;
    }

    protected String toExtraMediaString() {
        return a.d;
    }
}
