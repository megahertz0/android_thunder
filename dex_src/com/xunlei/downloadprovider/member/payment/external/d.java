package com.xunlei.downloadprovider.member.payment.external;

import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.bean.PayConfigurationParam;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import org.android.spdy.SpdyAgent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: PayConfigurationParser.java
public final class d {
    public static ArrayList<PayConfigurationParam> a(String str) throws JSONException {
        ArrayList<PayConfigurationParam> arrayList = new ArrayList();
        JSONArray jSONArray = new JSONArray(str);
        for (int i = 0; i < jSONArray.length(); i++) {
            PayConfigurationParam payConfigurationParam = new PayConfigurationParam();
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            payConfigurationParam.id = jSONObject.optString(SocializeConstants.WEIBO_ID);
            payConfigurationParam.order = jSONObject.optString("order");
            payConfigurationParam.member = jSONObject.optString("member");
            payConfigurationParam.limit = jSONObject.optString("limit");
            payConfigurationParam.limittype = jSONObject.optString("limittype");
            payConfigurationParam.limitdays = jSONObject.optString("limitdays");
            payConfigurationParam.limitdays2 = jSONObject.optString("limitdays2");
            payConfigurationParam.limittype2 = jSONObject.optString("limittype2");
            payConfigurationParam.op = jSONObject.optString("op");
            payConfigurationParam.vastype = jSONObject.optString("vastype");
            payConfigurationParam.showMonth = jSONObject.optString("show_month");
            payConfigurationParam.recommondMonth = jSONObject.optString("recommond_month");
            payConfigurationParam.recommondUpMonth = jSONObject.optString("recommond_upmonth");
            payConfigurationParam.mode = jSONObject.optString("mode");
            payConfigurationParam.tips = jSONObject.optString("tips");
            arrayList.add(payConfigurationParam);
        }
        return arrayList;
    }

    public static PayConfigurationParam a(ArrayList<PayConfigurationParam> arrayList, int i) {
        int i2 = 0;
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        int i3;
        j a = j.a();
        switch (a.d()) {
            case SpdyAgent.ACCS_TEST_SERVER:
                i3 = 1;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                i3 = !a.c() ? 8 : a.e() ? 5 : 2;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                i3 = !a.c() ? 9 : a.e() ? 6 : 3;
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                i3 = !a.c() ? 10 : a.e() ? 7 : 4;
                break;
            case 204:
                i3 = 11;
                break;
            default:
                i3 = 0;
                break;
        }
        j a2 = j.a();
        while (i2 < arrayList.size()) {
            PayConfigurationParam payConfigurationParam = (PayConfigurationParam) arrayList.get(i2);
            if (i3 == payConfigurationParam.getMember()) {
                if (i3 == 1) {
                    return payConfigurationParam;
                }
                if (payConfigurationParam.getLimit() == 1 && a(payConfigurationParam, PayUtil.b(a2.f()))) {
                    return payConfigurationParam;
                }
                if (payConfigurationParam.getLimit() == 2 && a(payConfigurationParam, (long) i)) {
                    return payConfigurationParam;
                }
            }
            i2++;
        }
        return null;
    }

    private static boolean a(PayConfigurationParam payConfigurationParam, long j) {
        if (payConfigurationParam.getLimittype() == 1 && j < ((long) payConfigurationParam.getLimitdays())) {
            return true;
        }
        if (payConfigurationParam.getLimittype() == 2) {
            if (payConfigurationParam.getLimittype2() == -1 && j >= ((long) payConfigurationParam.getLimitdays())) {
                return true;
            }
            if (payConfigurationParam.getLimittype2() == 1 && PayUtil.a((int) j, payConfigurationParam.getLimitdays(), payConfigurationParam.getLimitdays2())) {
                return true;
            }
        }
        return false;
    }
}
