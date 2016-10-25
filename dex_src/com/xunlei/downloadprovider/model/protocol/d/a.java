package com.xunlei.downloadprovider.model.protocol.d;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: SearchBarHintManager.java
public class a {
    private static a d;
    String a;
    public String b;
    public String c;
    private ArrayList<a> e;
    private ArrayList<String> f;
    private ArrayList<String> g;
    private com.xunlei.downloadprovider.a.h.a h;
    private b i;

    // compiled from: SearchBarHintManager.java
    public static interface a {
        void a(String str, String str2);
    }

    private a() {
        this.a = "http://m.sjzhushou.com/searchmk/ioshotword?device=iPhone7%2C2&ios_ver=9.1&partnerId=0x20800002&peerID=00f87cbd92ce003V&peer_id=00f87cbd92ce003V&productID=31&product_id=31&ver=5.10.1.1931&versionCode=51001";
        this.h = new b(this);
        this.i = new b(this.h);
    }

    public static a a() {
        if (d == null) {
            a aVar = new a();
            d = aVar;
            new Thread(new c(aVar)).start();
        }
        return d;
    }

    public static String b() {
        return new StringBuilder("http://static.m.sjzhushou.com/search_default_words.json?versionCode=").append(com.xunlei.downloadprovider.a.b.x()).append("&rd=").append(com.xunlei.downloadprovider.util.a.a.a.a()).toString();
    }

    private void a(JSONObject jSONObject) {
        int i = 0;
        try {
            JSONArray optJSONArray = jSONObject.optJSONArray("outside");
            this.g = new ArrayList();
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    this.g.add((String) optJSONArray.get(i2));
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray("inside");
            this.f = new ArrayList();
            if (optJSONArray2 != null) {
                while (i < optJSONArray2.length()) {
                    this.f.add((String) optJSONArray2.get(i));
                    i++;
                }
            }
            String str = (this.f == null || this.f.size() == 0) ? com.umeng.a.d : (String) this.f.get(0);
            this.b = str;
            str = (this.g == null || this.g.size() == 0) ? com.umeng.a.d : (String) this.g.get(0);
            this.c = str;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static /* synthetic */ void d(a aVar) {
        SharedPreferences sharedPreferences = BrothersApplication.a().getSharedPreferences(a.class.getSimpleName(), 0);
        String string = sharedPreferences.getString("search_bar_hint", null);
        long j = sharedPreferences.getLong("Time_Get_Hint", -1);
        if (j >= 0 && System.currentTimeMillis() - j <= 86400000) {
            try {
                aVar.a(new JSONObject(string));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static /* synthetic */ void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            Editor edit = BrothersApplication.a().getSharedPreferences(a.class.getSimpleName(), 0).edit();
            edit.putString("search_bar_hint", str);
            edit.putLong("Time_Get_Hint", System.currentTimeMillis());
            edit.commit();
        }
    }
}
