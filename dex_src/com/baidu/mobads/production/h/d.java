package com.baidu.mobads.production.h;

import android.content.Context;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import java.util.HashMap;

public class d extends com.baidu.mobads.vo.d {
    public d(Context context, SlotType slotType) {
        super(context, null, slotType);
        this.b = this.i.replaceURLWithSupportProtocol("http://mobads.baidu.com/cpro/ui/mads.php");
    }

    protected HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(IXAdRequestInfo.FET, "ANTI,HTML,MSSP");
        return hashMap;
    }

    public String b() {
        return super.b();
    }
}
