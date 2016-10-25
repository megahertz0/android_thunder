package com.inmobi.ads;

import com.inmobi.ads.NativeStrandAsset.AssetInteractionMode;
import com.inmobi.ads.NativeStrandAsset.AssetType;
import org.json.JSONObject;

// compiled from: NativeStrandCtaAsset.java
class w extends ah {

    // compiled from: NativeStrandCtaAsset.java
    static class a extends a {
        public a(int i, int i2, int i3, int i4, b bVar, a aVar, String str, String str2, int i5, String str3, a[] aVarArr) {
            super(i, i2, i3, i4, bVar, aVar, str, str2);
            this.f = i5;
            this.h = Integer.MAX_VALUE;
            if (str3.length() == 0) {
                str3 = "#ff000000";
            }
            this.g = str3;
            int min = Math.min(aVarArr.length, 1);
            this.i = new a[min];
            System.arraycopy(aVarArr, 0, this.i, 0, min);
        }
    }

    public w(String str, t tVar, String str2, AssetInteractionMode assetInteractionMode, JSONObject jSONObject) {
        this(str, tVar, str2, new ai[0], assetInteractionMode, jSONObject);
    }

    public w(String str, t tVar, String str2, ai[] aiVarArr, AssetInteractionMode assetInteractionMode, JSONObject jSONObject) {
        super(str, AssetType.ASSET_TYPE_CTA, tVar, str2);
        a(aiVarArr);
        this.g = true;
        if (jSONObject != null) {
            this.h = assetInteractionMode;
            this.e = jSONObject;
        }
    }
}
