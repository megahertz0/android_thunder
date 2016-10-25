package com.uc.addon.sdk.remote.protocol;

import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.uc.addon.sdk.remote.protocol.RemoteView.ActionInfo;
import com.uc.addon.sdk.remote.protocol.RemoteView.ActionSetBaseObjectValue;

public class RemoteFloatView extends RemoteView {
    public static final Creator CREATOR;
    public static final int TYPE_LAYER_BUTTON = 1000;
    public static final int TYPE_LAYER_BUTTON_INCLUDE_HOME = 1001;
    private int c;

    static {
        CREATOR = new Creator() {
            public final RemoteFloatView createFromParcel(Parcel parcel) {
                return new RemoteFloatView(parcel);
            }

            public final RemoteFloatView[] newArray(int i) {
                return new RemoteFloatView[i];
            }
        };
    }

    public RemoteFloatView(Parcel parcel) {
        super(parcel);
        this.c = 1000;
        this.c = parcel.readInt();
    }

    public RemoteFloatView(String str, int i, int i2, Handler handler) {
        super(str, i, handler);
        this.c = 1000;
        this.c = i2;
    }

    protected final void a(int i, Object obj) {
        super.a(i, obj);
        if (i == 4097 && obj != null) {
            this.c = ((Integer) obj).intValue();
        }
    }

    public int getLayerType() {
        return this.c;
    }

    public void setLayerType(int i) {
        this.c = i;
        ActionInfo actionInfo = new ActionInfo();
        actionInfo.c = "setLayerType";
        a(a(actionInfo));
        a(new ActionSetBaseObjectValue(this, Integer.valueOf(i), 4, 4097), actionInfo);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.c);
    }
}
