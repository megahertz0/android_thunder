package com.baidu.mobads.production.j;

import android.content.Context;
import com.baidu.mobad.feeds.RequestParameters;
import com.baidu.mobads.AdSize;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;

public class a extends com.baidu.mobads.production.d.a {
    private com.baidu.mobads.production.i.a x;

    public a(Context context, String str) {
        super(context);
        setId(str);
        setActivity(context);
        setAdSlotBase(null);
        this.p = SlotType.SLOT_TYPE_PREROLL;
        this.x = new com.baidu.mobads.production.i.a(getApplicationContext(), getActivity(), SlotType.SLOT_TYPE_PREROLL, this);
        this.x.c(AdSize.PrerollVideoNative.getValue());
        this.x.d(str);
    }

    public void a(RequestParameters requestParameters) {
        int width = requestParameters.getWidth();
        int height = requestParameters.getHeight();
        if (width > 0 && height > 0) {
            this.x.a(width);
            this.x.b(height);
        }
    }

    public void request() {
        super.a(this.x);
    }
}
