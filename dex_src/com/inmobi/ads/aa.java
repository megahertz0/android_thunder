package com.inmobi.ads;

import com.inmobi.ads.NativeStrandAsset.AssetInteractionMode;
import com.inmobi.ads.NativeStrandAsset.AssetType;
import org.json.JSONObject;

// compiled from: NativeStrandImageAsset.java
class aa extends NativeStrandAsset {
    public aa(String str, t tVar, String str2, AssetInteractionMode assetInteractionMode, JSONObject jSONObject) {
        this(str, tVar, str2, new ai[0], assetInteractionMode, jSONObject);
    }

    public aa(String str, t tVar, String str2, ai[] aiVarArr, AssetInteractionMode assetInteractionMode, JSONObject jSONObject) {
        super(str, AssetType.ASSET_TYPE_IMAGE, tVar, aiVarArr);
        this.d = str2;
        if (jSONObject != null) {
            this.g = true;
            this.h = assetInteractionMode;
            this.e = jSONObject;
        }
    }
}
