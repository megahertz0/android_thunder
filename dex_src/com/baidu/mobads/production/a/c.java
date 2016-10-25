package com.baidu.mobads.production.a;

import android.app.Activity;
import android.content.Context;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.vo.d;
import java.util.HashMap;

public class c extends d {
    public c(Context context, Activity activity, SlotType slotType) {
        super(context, activity, slotType);
        this.b = this.i.replaceURLWithSupportProtocol("http://mobads.baidu.com/ads/index.htm");
    }

    protected HashMap<String, String> a() {
        return new HashMap();
    }

    public String b() {
        return "http://127.0.0.1";
    }
}
