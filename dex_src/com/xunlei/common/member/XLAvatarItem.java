package com.xunlei.common.member;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.xunlei.common.encrypt.Base64;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class XLAvatarItem implements Parcelable {
    public static final Creator<XLAvatarItem> CREATOR;
    public int mAvatarId;
    public String mAvatarUrl;

    public XLAvatarItem(JSONObject jSONObject) {
        this.mAvatarUrl = BuildConfig.VERSION_NAME;
        this.mAvatarId = -1;
        if (jSONObject != null) {
            try {
                this.mAvatarId = jSONObject.getInt(AgooConstants.MESSAGE_ID);
                this.mAvatarUrl = new String(Base64.decode(jSONObject.getString(SHubBatchQueryKeys.url)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public XLAvatarItem(Parcel parcel) {
        this.mAvatarUrl = BuildConfig.VERSION_NAME;
        this.mAvatarId = -1;
        readFromParcel(parcel);
    }

    static {
        CREATOR = new Creator<XLAvatarItem>() {
            private static XLAvatarItem a(Parcel parcel) {
                return new XLAvatarItem(parcel);
            }

            private static XLAvatarItem[] a(int i) {
                return new XLAvatarItem[i];
            }

            public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
                return new XLAvatarItem[i];
            }

            public final /* synthetic */ Object createFromParcel(Parcel parcel) {
                return new XLAvatarItem(parcel);
            }
        };
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mAvatarUrl);
        parcel.writeInt(this.mAvatarId);
    }

    public void readFromParcel(Parcel parcel) {
        this.mAvatarUrl = parcel.readString();
        this.mAvatarId = parcel.readInt();
    }
}
