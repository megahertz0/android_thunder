package com.sina.weibo.sdk.register.mobile;

import android.text.TextUtils;
import java.io.Serializable;

public class Country implements Serializable, Comparable<Country> {
    public static final String CHINA_CODE = "0086";
    private static final long serialVersionUID = 0;
    private String code;
    private String[] mccs;
    private String name;
    private String pinyin;

    public Country(String str, String str2) {
        this.name = str;
        this.code = str2;
    }

    public String getName() {
        return this.name;
    }

    public String[] getMccs() {
        return this.mccs;
    }

    public void setMccs(String[] strArr) {
        this.mccs = strArr;
    }

    public String getPinyin() {
        return PinyinUtils.getObject().getPinyin(this.name).toLowerCase();
    }

    public void setPinyin(String str) {
        this.pinyin = str;
    }

    public String getCode() {
        return this.code;
    }

    public int compareTo(Country country) {
        if (TextUtils.isEmpty(this.pinyin)) {
            return -1;
        }
        return (country == null || TextUtils.isEmpty(country.pinyin)) ? 1 : this.pinyin.compareTo(country.pinyin);
    }

    public void setCode(String str) {
        this.code = str;
    }

    public void setName(String str) {
        this.name = str;
    }
}
