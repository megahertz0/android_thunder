package com.xunlei.downloadprovider.member.payment.bean;

import android.text.TextUtils;
import android.util.SparseArray;
import java.util.Iterator;
import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenPriceParam {
    private float dismonthprice;
    private int flag;
    private SparseArray<Float> mPriceArray;
    private float monthprice;
    private int ret;
    private String toStr;

    public OpenPriceParam() {
        this.mPriceArray = new SparseArray();
        this.toStr = new StringBuilder("OpenPriceParam{ret=").append(this.ret).append(", mPriceArray=").append(this.mPriceArray).append('}').toString();
    }

    public boolean isSuccess() {
        return this.ret == 0;
    }

    public SparseArray<Float> getPriceArray() {
        return this.mPriceArray;
    }

    public float getMonthPrice() {
        return this.monthprice;
    }

    public static OpenPriceParam parseFrom(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            OpenPriceParam openPriceParam = new OpenPriceParam();
            openPriceParam.ret = jSONObject.getInt("ret");
            if (openPriceParam.isSuccess()) {
                openPriceParam.monthprice = (float) jSONObject.optDouble("monthprice");
                openPriceParam.dismonthprice = (float) jSONObject.optDouble("dismonthprice");
                openPriceParam.flag = jSONObject.optInt(AgooConstants.MESSAGE_FLAG);
                JSONObject jSONObject2 = jSONObject.getJSONObject("prize");
                Iterator keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String str2 = (String) keys.next();
                    if (TextUtils.isDigitsOnly(str2)) {
                        openPriceParam.mPriceArray.put(Integer.parseInt(str2), Float.valueOf((float) jSONObject2.getDouble(str2)));
                    }
                }
            }
            return openPriceParam;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String toString() {
        return this.toStr;
    }
}
