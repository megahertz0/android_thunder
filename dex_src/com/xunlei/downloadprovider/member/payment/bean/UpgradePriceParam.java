package com.xunlei.downloadprovider.member.payment.bean;

import android.text.TextUtils;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UpgradePriceParam {
    private static final int DIVISOR_TEN = 10;
    public static final String UPGRADE_UPRATE_PROMOTION = "uprate";
    private float discount;
    private int hUpprice;
    private int hdays;
    private float hprice;
    private int mdays;
    private float mprice;
    private int nUpprice;
    private int ndays;
    private float nprice;
    private float price;
    private int ret;
    private int sdays;
    private float sprice;
    private int tdays;
    private String tostr;
    private HashMap<String, String> upgrateCfg;
    private float uprate;

    public UpgradePriceParam() {
        this.uprate = 1.0f;
        this.discount = 1.0f;
        this.tostr = new StringBuilder("UpgradePriceParam{ret=").append(this.ret).append(", mdays=").append(this.mdays).append(", ndays=").append(this.ndays).append(", hdays=").append(this.hdays).append(", mprice=").append(this.mprice).append(", nprice=").append(this.nprice).append(", hprice=").append(this.hprice).append(", price=").append(this.price).append(", tdays=").append(this.tdays).append(", discount=").append(this.discount).append('}').toString();
    }

    public boolean isSuccess() {
        return this.ret == 0;
    }

    public int getMdays() {
        return this.mdays;
    }

    public int getNdays() {
        return this.ndays;
    }

    public int getHdays() {
        return this.hdays;
    }

    public float getMprice() {
        return this.mprice;
    }

    public float getNprice() {
        return this.nprice;
    }

    public float getHprice() {
        return this.hprice;
    }

    public float getPrice() {
        return this.price;
    }

    public int getTdays() {
        return this.tdays;
    }

    public float getDiscount() {
        return this.discount;
    }

    public float getUprate() {
        return this.uprate;
    }

    public int getSdays() {
        return this.sdays;
    }

    public float getSprice() {
        return this.sprice;
    }

    public int getNUpprice() {
        return this.nUpprice;
    }

    public int getHUpprice() {
        return this.hUpprice;
    }

    public HashMap<String, String> getUpgrateCfg() {
        return this.upgrateCfg;
    }

    public String toString() {
        return this.tostr;
    }

    public static UpgradePriceParam parseFrom(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            UpgradePriceParam upgradePriceParam = new UpgradePriceParam();
            upgradePriceParam.ret = jSONObject.getInt("ret");
            if (upgradePriceParam.isSuccess()) {
                upgradePriceParam.price = (float) jSONObject.getDouble("price");
                upgradePriceParam.tdays = jSONObject.getInt("tdays");
                upgradePriceParam.uprate = (float) jSONObject.optDouble(UPGRADE_UPRATE_PROMOTION, 1.0d);
                upgradePriceParam.discount = (float) jSONObject.optDouble("discount", 1.0d);
                upgradePriceParam.mprice = (float) jSONObject.optDouble("mprice");
                upgradePriceParam.nprice = (float) jSONObject.optDouble("nprice");
                upgradePriceParam.hprice = (float) jSONObject.optDouble("hprice", 0.0d);
                upgradePriceParam.sprice = (float) jSONObject.optDouble("sprice");
                upgradePriceParam.mdays = jSONObject.optInt("mdays");
                upgradePriceParam.ndays = jSONObject.optInt("ndays");
                upgradePriceParam.hdays = jSONObject.optInt("hdays");
                upgradePriceParam.sdays = jSONObject.optInt("sdays");
                upgradePriceParam.nUpprice = jSONObject.optInt("n_upprice");
                upgradePriceParam.hUpprice = jSONObject.optInt("h_upprice");
                JSONObject optJSONObject = jSONObject.optJSONObject("uprateCfg");
                if (optJSONObject != null) {
                    HashMap hashMap = new HashMap();
                    JSONArray names = optJSONObject.names();
                    for (int i = 0; i < names.length(); i++) {
                        hashMap.put(names.getString(i), optJSONObject.getString(names.getString(i)));
                    }
                    upgradePriceParam.upgrateCfg = hashMap;
                }
            }
            return upgradePriceParam;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public float getPayCountOfDays(int i) {
        if (this.tdays == i) {
            return this.price;
        }
        if (i % 31 != 0) {
            return 1.0f;
        }
        float uprateFromMap = getUprateFromMap(i);
        Integer.valueOf(0);
        if (this.hdays > i) {
            return uprateFromMap * ((float) ((i / 31) * getPriceMonth(MqttConnectOptions.MQTT_VERSION_3_1).intValue()));
        }
        int i2 = i - this.hdays;
        Integer priceMonth = getPriceMonth(SimpleLog.LOG_LEVEL_DEBUG);
        float round = ((float) Math.round((((double) i2) / 31.0d) * 10.0d)) / 10.0f;
        return uprateFromMap * ((round * ((float) priceMonth.intValue())) + this.hprice);
    }

    private Integer getPriceMonth(int i) {
        if (i == 2) {
            return Integer.valueOf(this.nUpprice);
        }
        return i == 3 ? Integer.valueOf(this.hUpprice) : Integer.valueOf(1);
    }

    public float getUprateFromMap(int i) {
        if (i == this.tdays) {
            return this.uprate;
        }
        if (this.upgrateCfg == null || this.upgrateCfg.size() <= 0) {
            return 1.0f;
        }
        List arrayList = new ArrayList(this.upgrateCfg.keySet());
        Collections.sort(arrayList, new Comparator<String>() {
            public int compare(String str, String str2) {
                Integer d = PayUtil.d(str);
                Integer d2 = PayUtil.d(str2);
                if (d.intValue() < d2.intValue()) {
                    return 1;
                }
                return d.intValue() > d2.intValue() ? -1 : 0;
            }
        });
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (i >= PayUtil.d((String) arrayList.get(i2)).intValue()) {
                return PayUtil.e((String) this.upgrateCfg.get(arrayList.get(i2)));
            }
        }
        return 1.0f;
    }
}
