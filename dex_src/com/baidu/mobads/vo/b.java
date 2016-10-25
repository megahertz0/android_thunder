package com.baidu.mobads.vo;

import com.baidu.mobads.AdSettings;
import com.baidu.mobads.interfaces.IXAdConstants4PDK.SlotType;
import com.baidu.mobads.interfaces.IXAdProdInfo;
import org.json.JSONObject;

public class b implements IXAdProdInfo {
    private d a;
    private SlotType b;
    private JSONObject c;
    private boolean d;

    public boolean isAutoPlay() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public int getApt() {
        return this.a.getApt();
    }

    public boolean isMsspTagAvailable() {
        return false;
    }

    public b(d dVar, SlotType slotType) {
        this.d = false;
        this.a = dVar;
        this.b = slotType;
    }

    public int getRequestAdWidth() {
        return this.a.getW();
    }

    public int getRequestAdHeight() {
        return this.a.getH();
    }

    public String getAdPlacementId() {
        return this.a.getApid();
    }

    public String getProdType() {
        return this.a.getProd();
    }

    public SlotType getType() {
        return this.b;
    }

    public JSONObject getAttribute() {
        return this.c != null ? this.c : AdSettings.getAttr();
    }

    public void a(JSONObject jSONObject) {
        this.c = jSONObject;
    }

    public int getInstanceCount() {
        return 0;
    }

    public String getAdRequestURL() {
        return this.a.b();
    }
}
