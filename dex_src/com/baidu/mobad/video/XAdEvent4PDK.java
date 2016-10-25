package com.baidu.mobad.video;

import com.baidu.mobads.interfaces.IXAdEvent4PDK;
import com.baidu.mobads.interfaces.IXAdProd;
import com.baidu.mobads.openad.d.b;
import java.util.HashMap;

public class XAdEvent4PDK extends b implements IXAdEvent4PDK {
    private IXAdProd a;

    public XAdEvent4PDK(String str, IXAdProd iXAdProd) {
        super(str);
        this.a = iXAdProd;
    }

    public XAdEvent4PDK(String str, String str2, IXAdProd iXAdProd) {
        super(str, str2);
        this.a = iXAdProd;
    }

    public XAdEvent4PDK(String str, HashMap<String, Object> hashMap, IXAdProd iXAdProd) {
        super(str, (HashMap) hashMap);
        this.a = iXAdProd;
    }

    public XAdEvent4PDK(String str, int i, IXAdProd iXAdProd) {
        super(str, i);
        this.a = iXAdProd;
    }

    public XAdEvent4PDK(String str, int i, HashMap<String, Object> hashMap, IXAdProd iXAdProd) {
        super(str, i, (HashMap) hashMap);
        this.a = iXAdProd;
    }

    public XAdEvent4PDK(String str, int i, String str2, IXAdProd iXAdProd) {
        super(str, i, str2);
        this.a = iXAdProd;
    }

    public IXAdProd getAdSlot() {
        return this.a;
    }
}
