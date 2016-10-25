package com.mediav.ads.sdk.attibutes;

import com.mediav.ads.sdk.interfaces.IMvProductAdAttributes;
import java.util.HashMap;

public class MvProductAdAttributes implements IMvProductAdAttributes {
    private HashMap<String, String> productInfo;

    public MvProductAdAttributes() {
        this.productInfo = new HashMap();
    }

    public HashMap<String, String> getAttributes() {
        return this.productInfo;
    }

    public void setCategory(String str, int i) {
        this.productInfo.put("qhcn", str);
        this.productInfo.put("qhtid", String.valueOf(i));
    }

    public void setPrice(double d) {
        this.productInfo.put("price", String.valueOf(d));
    }
}
