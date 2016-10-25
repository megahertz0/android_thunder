package com.baidu.mobads.production.b;

import android.content.Context;
import com.baidu.mobad.feeds.RequestParameters;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.production.d.a;

public class b extends a {
    private a x;

    public b(Context context, String str) {
        super(context);
        setId(str);
        setActivity(context);
        setAdSlotBase(null);
        this.p = SlotType.SLOT_TYPE_VERLINK;
        this.x = new a(getApplicationContext(), getActivity(), this.p);
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
