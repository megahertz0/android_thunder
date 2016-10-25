package com.taobao.accs.client;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONObject;

// compiled from: Taobao
public class b {
    public static final String SP_AGOO_BIND_FILE_NAME = "AGOO_BIND";
    public static final String SP_BIND_FILE_NAME = "ACCS_BIND";
    private static b a;
    private Context b;
    private ConcurrentHashMap<String, Integer> c;
    private ConcurrentHashMap<String, Set<String>> d;
    private long e;
    private ConcurrentHashMap<String, Integer> f;
    private String g;
    private long h;

    static {
        a = null;
    }

    private b(Context context) {
        this.c = new ConcurrentHashMap();
        this.d = new ConcurrentHashMap();
        this.f = new ConcurrentHashMap();
        if (context == null) {
            throw new RuntimeException("Context is null!!");
        }
        this.b = context.getApplicationContext();
        b();
    }

    public void a(String str) {
        Integer num = (Integer) this.c.get(str);
        if (num == null || num.intValue() != 2) {
            this.c.put(str, Integer.valueOf(XZBDevice.DOWNLOAD_LIST_RECYCLE));
            a(SP_BIND_FILE_NAME, this.e, this.c);
        }
    }

    public void b(String str) {
        Integer num = (Integer) this.c.get(str);
        if (num == null || num.intValue() != 4) {
            this.c.put(str, Integer.valueOf(XZBDevice.DOWNLOAD_LIST_ALL));
            a(SP_BIND_FILE_NAME, this.e, this.c);
        }
    }

    public boolean c(String str) {
        if (this.c.isEmpty()) {
            b();
        }
        Integer num = (Integer) this.c.get(str);
        ALog.i("ClientManager", new StringBuilder("isAppBinded begin..appStatus=").append(num).append(",mBindStatus=").append(this.c).toString(), new Object[0]);
        return num != null && num.intValue() == 2;
    }

    public boolean d(String str) {
        Integer num = (Integer) this.c.get(str);
        return num != null && num.intValue() == 4;
    }

    public void a(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                Set set = (Set) this.d.get(str);
                if (set == null) {
                    set = new HashSet();
                }
                set.add(str2);
                this.d.put(str, set);
            }
        } catch (Exception e) {
            ALog.e("ClientManager", new StringBuilder("ClientManager").append(e.toString()).toString(), new Object[0]);
            e.printStackTrace();
        }
    }

    public void e(String str) {
        try {
            if (!TextUtils.isEmpty(str)) {
                this.d.remove(str);
            }
        } catch (Exception e) {
            ALog.e("ClientManager", new StringBuilder("ClientManager").append(e.toString()).toString(), new Object[0]);
            e.printStackTrace();
        }
    }

    public boolean b(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            Set set = (Set) this.d.get(str);
            if (set != null && set.contains(str2)) {
                return true;
            }
            return false;
        } catch (Exception e) {
            ALog.e("ClientManager", new StringBuilder("ClientManager").append(e.toString()).toString(), new Object[0]);
            e.printStackTrace();
        }
    }

    public void f(String str) {
        Integer num = (Integer) this.f.get(str);
        if (num == null || num.intValue() != 2) {
            this.f.put(str, Integer.valueOf(XZBDevice.DOWNLOAD_LIST_RECYCLE));
            a(SP_AGOO_BIND_FILE_NAME, this.h, this.f);
        }
    }

    public boolean g(String str) {
        if (this.f.isEmpty()) {
            c();
        }
        Integer num = (Integer) this.f.get(str);
        ALog.i("ClientManager", new StringBuilder("isAgooRegistered begin..appStatus=").append(num).append(",mAgooBindStatus=").append(this.f).toString(), new Object[0]);
        return num != null && num.intValue() == 2;
    }

    public void h(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.g = str;
        }
    }

    public void a() {
        this.g = null;
    }

    public boolean i(String str) {
        return this.g != null && this.g.equals(str);
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b(context);
            }
            bVar = a;
        }
        return bVar;
    }

    private void b() {
        try {
            Object string = this.b.getSharedPreferences(SP_BIND_FILE_NAME, 0).getString("bind_status", null);
            if (TextUtils.isEmpty(string)) {
                ALog.i("ClientManager", "restoreClients packs null return", new Object[0]);
                return;
            }
            JSONArray jSONArray = new JSONArray(string);
            this.e = jSONArray.getLong(0);
            if (System.currentTimeMillis() < this.e + 86400000) {
                for (int i = 1; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    this.c.put(jSONObject.getString("p"), Integer.valueOf(jSONObject.getInt("s")));
                }
                ALog.i("ClientManager", new StringBuilder("restoreClients mBindStatus restore=").append(this.c).toString(), new Object[0]);
                return;
            }
            ALog.i("ClientManager", "restoreClients expired", "lastFlushTime", Long.valueOf(this.e));
            this.e = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void c() {
        try {
            Object string = this.b.getSharedPreferences(SP_AGOO_BIND_FILE_NAME, 0).getString("bind_status", null);
            if (TextUtils.isEmpty(string)) {
                ALog.i("ClientManager", "restoreAgooClients packs null return", new Object[0]);
                return;
            }
            JSONArray jSONArray = new JSONArray(string);
            this.h = jSONArray.getLong(0);
            if (System.currentTimeMillis() < this.h + 86400000) {
                for (int i = 1; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    this.f.put(jSONObject.getString("p"), Integer.valueOf(jSONObject.getInt("s")));
                }
                ALog.i("ClientManager", new StringBuilder("restoreAgooClients mAgooBindStatus restore = ").append(this.f).toString(), new Object[0]);
                return;
            }
            ALog.i("ClientManager", "restoreAgooClients expired", "agooLastFlushTime", Long.valueOf(this.h));
            this.h = 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void a(String str, long j, Map<String, Integer> map) {
        try {
            String[] strArr = (String[]) map.keySet().toArray(new String[0]);
            JSONArray jSONArray = new JSONArray();
            if (j <= 0 || j >= System.currentTimeMillis()) {
                jSONArray.put(((double) System.currentTimeMillis()) - (Math.random() * 8.64E7d));
            } else {
                jSONArray.put(j);
            }
            int length = strArr.length;
            for (int i = 0; i < length; i++) {
                Object obj = strArr[i];
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("p", obj);
                jSONObject.put("s", ((Integer) map.get(obj)).intValue());
                jSONArray.put(jSONObject);
            }
            Editor edit = this.b.getSharedPreferences(str, 0).edit();
            edit.putString("bind_status", jSONArray.toString());
            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void j(String str) {
        try {
            Editor edit = this.b.getSharedPreferences(str, 0).edit();
            edit.clear();
            edit.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
