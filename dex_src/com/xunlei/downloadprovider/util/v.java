package com.xunlei.downloadprovider.util;

import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.register.mobile.SelectCountryActivity;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.d.b;
import com.xunlei.downloadprovider.homepage.j;
import com.xunlei.downloadprovidercommon.a.c;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: RedPointOnlineConfigure.java
public final class v {
    public static String a;
    private static v d;
    private final String b;
    private Map<String, com.xunlei.downloadprovider.discovery.redpoint.a> c;
    private List<a> e;
    private String f;

    // compiled from: RedPointOnlineConfigure.java
    public static interface a {
        void a(v vVar);
    }

    static {
        a = "RedPoint";
        d = new v();
    }

    public static v a() {
        return d;
    }

    private v() {
        this.b = "red_points.json";
    }

    public final void a(a aVar) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.add(aVar);
    }

    public final void b(a aVar) {
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.remove(aVar);
    }

    public final boolean b() {
        return this.c != null && this.c.size() > 0;
    }

    public final com.xunlei.downloadprovider.discovery.redpoint.a a(String str) {
        return (TextUtils.isEmpty(str) || this.c == null) ? null : (com.xunlei.downloadprovider.discovery.redpoint.a) this.c.get(str);
    }

    private String g() {
        if (TextUtils.isEmpty(this.f)) {
            this.f = BrothersApplication.a().getCacheDir().getAbsolutePath() + "red_points.json";
        }
        return this.f;
    }

    public static boolean c() {
        return b.a(System.currentTimeMillis(), BrothersApplication.a().getSharedPreferences("red_point_online_config", 0).getLong("last_load_config_time", 0));
    }

    protected final JSONObject d() {
        InputStream fileInputStream;
        JSONObject jSONObject = null;
        try {
            File file = new File(g());
            if (file.exists()) {
                new StringBuilder("loadConfigureFromLocal - cache : ").append(file.getAbsolutePath());
                fileInputStream = new FileInputStream(file);
            } else {
                fileInputStream = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        if (fileInputStream == null) {
            return null;
        }
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            fileInputStream.close();
            jSONObject = new JSONObject(new String(bArr, GameManager.DEFAULT_CHARSET));
            return jSONObject;
        } catch (Exception e2) {
            e2.printStackTrace();
            return jSONObject;
        }
    }

    private static Map<String, com.xunlei.downloadprovider.discovery.redpoint.a> b(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                Map<String, com.xunlei.downloadprovider.discovery.redpoint.a> hashMap = new HashMap();
                JSONArray jSONArray = jSONObject.getJSONArray("redpoints");
                if (jSONArray != null && jSONArray.length() > 0) {
                    int length = jSONArray.length();
                    for (int i = 0; i < length; i++) {
                        JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                        com.xunlei.downloadprovider.discovery.redpoint.a aVar = new com.xunlei.downloadprovider.discovery.redpoint.a();
                        aVar.a = jSONObject2.optString(SelectCountryActivity.EXTRA_COUNTRY_NAME);
                        String string = jSONObject2.getString("bus_name");
                        aVar.b = string;
                        aVar.c = jSONObject2.optInt("red_point", -1);
                        aVar.d = jSONObject2.optString("cof_text");
                        aVar.e = jSONObject2.optString("pic_url");
                        aVar.o = jSONObject2.optString("jump_url");
                        aVar.h = jSONObject2.getString("start_time");
                        aVar.j = jSONObject2.getString("end_time");
                        aVar.f = jSONObject2.optInt(Impl.COLUMN_STATUS);
                        aVar.g = jSONObject2.optInt(WBConstants.AUTH_PARAMS_DISPLAY);
                        aVar.l = jSONObject2.optInt("phase");
                        aVar.p = BrothersApplication.a().getSharedPreferences("red_point_online_config", 0).getLong(aVar.b, 0);
                        aVar.i = b.a(aVar.h);
                        aVar.k = b.a(aVar.j);
                        if (!(string.equals("neaby") || string.equals("berry_live"))) {
                            if (string.equals("activity_center")) {
                                aVar.m = 2130838655;
                                aVar.n = true;
                            } else if (string.equals("game_center")) {
                                aVar.m = 2130838654;
                                aVar.n = true;
                            } else if (string.equals("snatch")) {
                                aVar.m = 2130838224;
                                aVar.n = false;
                            } else if (string.equals("finance")) {
                                aVar.m = 2130838222;
                                aVar.n = false;
                            } else if (string.equals("beautiful_photo")) {
                                aVar.m = 2130838223;
                                aVar.n = false;
                            } else if (string.equals("kuainiao")) {
                                aVar.m = 2130838658;
                                aVar.n = true;
                            } else if (string.equals("remote_download")) {
                                aVar.m = 2130838222;
                                aVar.n = false;
                            }
                            hashMap.put(aVar.b, aVar);
                        }
                        aVar.m = 2130838223;
                        aVar.n = true;
                        hashMap.put(aVar.b, aVar);
                    }
                }
                return hashMap;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public final void a(long j, String str) {
        if (!TextUtils.isEmpty(str) && this.c != null && this.c.size() != 0) {
            com.xunlei.downloadprovider.discovery.redpoint.a aVar = (com.xunlei.downloadprovider.discovery.redpoint.a) this.c.get(str);
            if (aVar != null && j <= aVar.k && j >= aVar.i) {
                aVar.p = j;
                Editor edit = BrothersApplication.a().getSharedPreferences("red_point_online_config", 0).edit();
                edit.putLong(str, j);
                edit.commit();
            }
        }
    }

    public final boolean b(String str) {
        if (TextUtils.isEmpty(str) || this.c == null || this.c.size() == 0) {
            return false;
        }
        com.xunlei.downloadprovider.discovery.redpoint.a aVar = (com.xunlei.downloadprovider.discovery.redpoint.a) this.c.get(str);
        return aVar == null ? false : aVar.a();
    }

    public final void e() {
        if (this.c != null && this.c.size() != 0) {
            if (!b.a(System.currentTimeMillis(), BrothersApplication.a().getSharedPreferences("red_point_online_config", 0).getLong("red_point_status_report_time", 0))) {
                c a = com.xunlei.downloadprovidercommon.a.a.a(j.a, j.b);
                if (this.c.containsKey("choiceness")) {
                    a.a("top_collect", "point");
                } else {
                    a.a("top_collect", MessageService.MSG_DB_READY_REPORT);
                }
                if (this.c.containsKey("classify")) {
                    a.a("top_class", "point");
                } else {
                    a.a("top_class", MessageService.MSG_DB_READY_REPORT);
                }
                if (this.c.containsKey("short_movie")) {
                    a.a("top_video", "point");
                } else {
                    a.a("top_video", MessageService.MSG_DB_READY_REPORT);
                }
                if (this.c.containsKey("fun_pic")) {
                    a.a("top_fun", "point");
                } else {
                    a.a("top_fun", MessageService.MSG_DB_READY_REPORT);
                }
                if (this.c.containsKey("recommend")) {
                    a.a("foot_home", "point");
                } else {
                    a.a("foot_home", MessageService.MSG_DB_READY_REPORT);
                }
                if (this.c.containsKey("search")) {
                    a.a("foot_search", "point");
                } else {
                    a.a("foot_search", MessageService.MSG_DB_READY_REPORT);
                }
                if (this.c.containsKey("find")) {
                    a.a("foot_find", "point");
                } else {
                    a.a("foot_find", MessageService.MSG_DB_READY_REPORT);
                }
                if (this.c.containsKey("user_center")) {
                    a.a("foot_personal", "point");
                } else {
                    a.a("foot_personal", MessageService.MSG_DB_READY_REPORT);
                }
                j.a(a);
                Editor edit = BrothersApplication.a().getSharedPreferences("red_point_online_config", 0).edit();
                edit.putLong("red_point_status_report_time", System.currentTimeMillis());
                edit.commit();
            }
        }
    }

    static /* synthetic */ void a(v vVar) {
        if (vVar.e != null) {
            for (a aVar : vVar.e) {
                aVar.a(vVar);
            }
        }
    }

    static /* synthetic */ void a(Map map, Map map2) {
        if (map2 != null) {
            for (Entry entry : map.entrySet()) {
                int i;
                com.xunlei.downloadprovider.discovery.redpoint.a aVar = (com.xunlei.downloadprovider.discovery.redpoint.a) entry.getValue();
                com.xunlei.downloadprovider.discovery.redpoint.a aVar2 = (com.xunlei.downloadprovider.discovery.redpoint.a) map2.get(entry.getKey());
                if (aVar == null || aVar2 == null) {
                    if (aVar == null && aVar2 == null) {
                        i = 1;
                    } else {
                        i = 0;
                    }
                } else if (aVar.b().equals(aVar2.b())) {
                    i = 1;
                } else {
                    i = 0;
                }
                if (i == 0 && aVar != null) {
                    aVar.p = 0;
                    String str = aVar.b;
                    Editor edit = BrothersApplication.a().getSharedPreferences("red_point_online_config", 0).edit();
                    edit.putLong(str, 0);
                    edit.commit();
                }
            }
        }
    }

    static /* synthetic */ boolean a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null) {
            return jSONObject == null && jSONObject2 == null;
        } else {
            return jSONObject.toString().equals(jSONObject2.toString());
        }
    }

    static /* synthetic */ void f() {
        Editor edit = BrothersApplication.a().getSharedPreferences("red_point_online_config", 0).edit();
        edit.putLong("last_load_config_time", System.currentTimeMillis());
        edit.commit();
    }
}
