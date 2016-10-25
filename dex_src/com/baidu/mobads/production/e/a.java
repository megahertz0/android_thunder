package com.baidu.mobads.production.e;

import android.content.Context;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.vo.d;
import java.util.HashMap;

public class a extends d {
    public a(Context context) {
        super(context, null, SlotType.SLOT_TYPE_FRONTLINK);
        this.b = this.i.replaceURLWithSupportProtocol("http://mobads.baidu.com/cpro/ui/mads.php");
        b(this.h.getSupportedActionType4RequestingLandingPage());
        f(this.h.getAdCreativeTypeImage() + this.h.getAdCreativeTypeText());
        d(1);
    }

    protected HashMap<String, String> a() {
        HashMap<String, String> hashMap = new HashMap();
        hashMap.put(IXAdRequestInfo.FET, "LU,ANTI,HTML,MSSP");
        return hashMap;
    }

    public String b() {
        return super.b();
    }
}
