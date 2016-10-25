package com.xunlei.downloadprovider.member.payment.a;

import com.qq.e.comm.constants.Constants.KEYS;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.member.payment.bean.VoucherDataBean;
import com.xunlei.downloadprovider.member.payment.bean.VoucherStateResultBean;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: VoucherHelper.java
public class k extends e {
    private static k b;
    public j a;
    private Map<Integer, List<String>> c;
    private Map<Integer, String> d;

    // compiled from: VoucherHelper.java
    public static interface a {
        void a(boolean z);
    }

    static /* synthetic */ void a(k kVar, String str, a aVar) {
        kVar.c.clear();
        kVar.d.clear();
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(KEYS.RET, XZBDevice.DOWNLOAD_LIST_RECYCLE) != 0) {
                aVar.a(false);
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(SocializeConstants.JSON_DATA);
            JSONArray names = jSONObject2.names();
            for (int i = 0; i < names.length(); i++) {
                String str2 = (String) names.get(i);
                JSONObject jSONObject3 = jSONObject2.getJSONObject(str2);
                JSONArray names2 = jSONObject3.names();
                List arrayList = new ArrayList();
                for (int i2 = 0; i2 < names2.length(); i2++) {
                    arrayList.add(jSONObject3.getString((String) names2.get(i2)));
                }
                int parseInt = Integer.parseInt(str2) / 100;
                kVar.c.put(Integer.valueOf(parseInt), arrayList);
                kVar.d.put(Integer.valueOf(parseInt), arrayList.get(0));
            }
            aVar.a(true);
        } catch (Exception e) {
            aVar.a(false);
            e.printStackTrace();
        }
    }

    private k() {
        this.a = j.a();
        this.c = new TreeMap();
        this.d = new TreeMap();
    }

    public static k a() {
        if (b == null) {
            synchronized (k.class) {
                if (b == null) {
                    b = new k();
                }
            }
        }
        return b;
    }

    public final VoucherDataBean a(int i, int i2) {
        int i3 = 0;
        if (this.d.size() <= 0) {
            return null;
        }
        Object[] toArray = this.d.keySet().toArray();
        if (i > 75) {
            Map treeMap = new TreeMap();
            treeMap.putAll(this.d);
            VoucherDataBean voucherDataBean = new VoucherDataBean();
            if (i2 == 1) {
                int length = toArray.length;
                int i4 = 0;
                while (i4 < length) {
                    int parseInt = Integer.parseInt(toArray[i4].toString());
                    if (parseInt < 5) {
                        treeMap.remove(Integer.valueOf(parseInt));
                    }
                    if (parseInt <= i3) {
                        parseInt = i3;
                    }
                    i4++;
                    i3 = parseInt;
                }
            }
            if (treeMap.size() <= 0) {
                return null;
            }
            voucherDataBean.setVoucherList(treeMap);
            voucherDataBean.setDefaultVoucherNum(i3);
            return voucherDataBean;
        } else if (i > 35) {
            return a(i2, toArray);
        } else {
            return i2 == 1 ? null : a(toArray);
        }
    }

    private VoucherDataBean a(int i, Object[] objArr) {
        Map treeMap = new TreeMap();
        VoucherDataBean voucherDataBean = new VoucherDataBean();
        int length = objArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int parseInt = Integer.parseInt(objArr[i2].toString());
            if (parseInt < 10) {
                if (i != 1) {
                    treeMap.put(Integer.valueOf(parseInt), this.d.get(Integer.valueOf(parseInt)));
                } else if (parseInt >= 5) {
                    treeMap.put(Integer.valueOf(parseInt), this.d.get(Integer.valueOf(parseInt)));
                }
                if (parseInt > i3) {
                    i2++;
                    i3 = parseInt;
                }
            }
            parseInt = i3;
            i2++;
            i3 = parseInt;
        }
        if (treeMap.size() <= 0) {
            return null;
        }
        voucherDataBean.setVoucherList(treeMap);
        voucherDataBean.setDefaultVoucherNum(i3);
        return voucherDataBean;
    }

    private VoucherDataBean a(Object[] objArr) {
        Map treeMap = new TreeMap();
        VoucherDataBean voucherDataBean = new VoucherDataBean();
        int length = objArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int parseInt = Integer.parseInt(objArr[i].toString());
            if (parseInt < 5) {
                treeMap.put(Integer.valueOf(parseInt), this.d.get(Integer.valueOf(parseInt)));
                if (parseInt > i2) {
                    i++;
                    i2 = parseInt;
                }
            }
            parseInt = i2;
            i++;
            i2 = parseInt;
        }
        if (treeMap.size() <= 0) {
            return null;
        }
        voucherDataBean.setVoucherList(treeMap);
        voucherDataBean.setDefaultVoucherNum(i2);
        return voucherDataBean;
    }

    static /* synthetic */ void a(String str, b bVar) {
        try {
            VoucherStateResultBean voucherStateResultBean = new VoucherStateResultBean();
            JSONObject jSONObject = new JSONObject(str);
            voucherStateResultBean.setState(jSONObject.optInt("state"));
            voucherStateResultBean.setCashMinus(jSONObject.optInt("cash_minus"));
            voucherStateResultBean.setMin(jSONObject.optString("min"));
            voucherStateResultBean.setLimitMonth(jSONObject.optString("limit_month"));
            bVar.a(voucherStateResultBean);
        } catch (JSONException e) {
            e.printStackTrace();
            bVar.a(null);
        }
    }
}
