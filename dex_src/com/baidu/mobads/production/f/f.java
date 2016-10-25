package com.baidu.mobads.production.f;

import android.app.Activity;
import android.content.Context;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.vo.d;
import java.util.HashMap;

public class f extends d {
    private Boolean a;

    public f(Context context, Activity activity, SlotType slotType, Boolean bool) {
        super(context, activity, slotType);
        this.a = bool;
        if (c().booleanValue()) {
            this.b = this.i.replaceURLWithSupportProtocol("http://mobads.baidu.com/ads/index.htm");
        }
    }

    public Boolean c() {
        return this.a;
    }

    protected HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put("xyz", "hihihi");
        return hashMap;
    }

    public String b() {
        return c().booleanValue() ? super.b() : "http://211.151.146.65:8080/wlantest/shanghai_sun/mock_ad_server_intersitial_video.json";
    }
}
