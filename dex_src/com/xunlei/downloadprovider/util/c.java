package com.xunlei.downloadprovider.util;

import org.android.agoo.common.AgooConstants;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: CopyrightUtil.java
public final class c {
    public static String a(String str) {
        try {
            return new JSONObject(str).optString(AgooConstants.MESSAGE_POPUP);
        } catch (JSONException e) {
            return null;
        }
    }

    public static String b(String str) {
        try {
            return new JSONObject(str).optString("popupUrl");
        } catch (JSONException e) {
            return null;
        }
    }
}
