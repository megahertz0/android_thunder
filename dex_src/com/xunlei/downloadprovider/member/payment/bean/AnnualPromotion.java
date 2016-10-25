package com.xunlei.downloadprovider.member.payment.bean;

import org.json.JSONException;
import org.json.JSONObject;

public class AnnualPromotion {
    public static final String PROMOTION_LOTTERY = "lottery";
    public static final String PROMOTION_NONE = "none";
    public static final String PROMOTION_ONE_DOLLAR = "one_dollar";
    private String normalMemberPromotion;
    private String platinumMemberPromotion;
    private String svipMemberPromotion;

    public String getPlatinumMemberPromotion() {
        return this.platinumMemberPromotion;
    }

    public String getNormalMemberPromotion() {
        return this.normalMemberPromotion;
    }

    public String getSvipMemberPromotion() {
        return this.svipMemberPromotion;
    }

    public static AnnualPromotion parseFrom(JSONObject jSONObject) throws JSONException {
        AnnualPromotion annualPromotion = new AnnualPromotion();
        annualPromotion.platinumMemberPromotion = jSONObject.getString("high_annual_fee");
        annualPromotion.svipMemberPromotion = jSONObject.getString("super_annual_fee");
        annualPromotion.normalMemberPromotion = jSONObject.getString("openvip_annual_fee");
        return annualPromotion;
    }
}
