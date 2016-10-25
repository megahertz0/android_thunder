package com.baidu.mobads.vo;

import com.baidu.mobads.c.a;
import com.baidu.mobads.interfaces.IXAdInstanceInfo;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.IXAdResponseInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class c implements IXAdResponseInfo {
    private int a;
    private Boolean b;
    private String c;
    private int d;
    private int e;
    private String f;
    private String g;
    private String h;
    private int i;
    private int j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private ArrayList<IXAdInstanceInfo> p;
    private long q;

    public c(String str) {
        boolean z = true;
        this.a = 0;
        this.b = Boolean.valueOf(false);
        this.i = -1;
        this.j = -1;
        this.p = new ArrayList();
        this.o = str;
        this.q = System.currentTimeMillis();
        a.c = str;
        JSONObject jSONObject = new JSONObject(str);
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("ad");
            if (jSONArray != null) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        this.p.add(new XAdInstanceInfo(jSONArray.getJSONObject(i)));
                    } catch (JSONException e) {
                    }
                }
            }
        } catch (Exception e2) {
            this.p = new ArrayList();
        }
        this.a = jSONObject.optInt(IXAdRequestInfo.AD_COUNT, 0);
        this.d = jSONObject.optInt("x", 0);
        this.e = jSONObject.optInt("y", 0);
        if (jSONObject.optInt("m", 0) != 1) {
            z = false;
        }
        this.b = Boolean.valueOf(z);
        this.c = jSONObject.optString("u", com.umeng.a.d);
        this.f = jSONObject.optString("exp2", "{}");
        this.g = jSONObject.optString("ext_act", "{}");
        this.i = jSONObject.optInt("lunpan", -1);
        this.j = jSONObject.optInt("intIcon", -1);
        this.k = jSONObject.optString("ck", com.umeng.a.d);
        this.l = jSONObject.optString("req_id");
        this.m = jSONObject.optString("error_code", com.umeng.a.d);
        this.n = jSONObject.optString("error_msg", com.umeng.a.d);
        try {
            this.h = jSONObject.getString("theme");
        } catch (JSONException e3) {
        }
    }

    public IXAdInstanceInfo getPrimaryAdInstanceInfo() {
        return this.p.size() > 0 ? (IXAdInstanceInfo) this.p.get(0) : null;
    }

    public int getAdsNum() {
        return this.a;
    }

    public void setAdsNum(int i) {
        this.a = i;
    }

    public Boolean getOpenPointModeForWall() {
        return this.b;
    }

    public void setOpenPointModeForWall(Boolean bool) {
        this.b = bool;
    }

    public String getPointUnitForWall() {
        return this.c;
    }

    public void setPointUnitForWall(String str) {
        this.c = str;
    }

    public int getLongitude() {
        return this.d;
    }

    public void setLongitude(int i) {
        this.d = i;
    }

    public int getLatitude() {
        return this.e;
    }

    public void setLatitude(int i) {
        this.e = i;
    }

    public String getExp2() {
        return this.f;
    }

    public void setExp2(String str) {
        this.f = str;
    }

    public String getExtentionActionExp() {
        return this.g;
    }

    public void setExtentionActionExp(String str) {
        this.g = str;
    }

    public String getDefaultFillInThemeForStaticAds() {
        return this.h;
    }

    public void setDefaultFillInThemeForStaticAds(String str) {
        this.h = str;
    }

    public int getLunpan() {
        return this.i;
    }

    public void setLunpan(int i) {
        this.i = i;
    }

    public int getIntIcon() {
        return this.j;
    }

    public void setIntIcon(int i) {
        this.j = i;
    }

    public String getBaiduidOfCookie() {
        return this.k;
    }

    public void setBaiduidOfCookie(String str) {
        this.k = str;
    }

    public String getRequestId() {
        return this.l;
    }

    public void setRequestId(String str) {
        this.l = str;
    }

    public String getErrorCode() {
        return this.m;
    }

    public void setErrorCode(String str) {
        this.m = str;
    }

    public String getErrorMessage() {
        return this.n;
    }

    public void setErrorMessage(String str) {
        this.n = str;
    }

    public String getOriginResponseStr() {
        return this.o;
    }

    public void setOriginResponseStr(String str) {
        this.o = str;
    }

    public ArrayList<IXAdInstanceInfo> getAdInstanceList() {
        return this.p;
    }

    public void setAdInstanceList(ArrayList<IXAdInstanceInfo> arrayList) {
        this.p = arrayList;
    }

    public long getTimeStamp() {
        return this.q;
    }
}
