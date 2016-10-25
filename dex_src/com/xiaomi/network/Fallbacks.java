package com.xiaomi.network;

import android.text.TextUtils;
import com.taobao.accs.internal.b;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

class Fallbacks {
    private String host;
    private final ArrayList<Fallback> mFallbacks;

    public Fallbacks() {
        this.mFallbacks = new ArrayList();
    }

    public Fallbacks(String str) {
        this.mFallbacks = new ArrayList();
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("the host is empty");
        }
        this.host = str;
    }

    public synchronized void addFallback(Fallback fallback) {
        int i = 0;
        while (i < this.mFallbacks.size()) {
            if (((Fallback) this.mFallbacks.get(i)).a(fallback)) {
                this.mFallbacks.set(i, fallback);
                break;
            }
            i++;
        }
        if (i >= this.mFallbacks.size()) {
            this.mFallbacks.add(fallback);
        }
    }

    public synchronized Fallbacks fromJSON(JSONObject jSONObject) {
        this.host = jSONObject.getString(b.ELECTION_KEY_HOST);
        JSONArray jSONArray = jSONObject.getJSONArray("fbs");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.mFallbacks.add(new Fallback(this.host).a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized Fallback getFallback() {
        Fallback fallback;
        for (int size = this.mFallbacks.size() - 1; size >= 0; size--) {
            fallback = (Fallback) this.mFallbacks.get(size);
            if (fallback.a()) {
                HostManager.getInstance().setCurrentISP(fallback.e());
                break;
            }
        }
        fallback = null;
        return fallback;
    }

    public ArrayList<Fallback> getFallbacks() {
        return this.mFallbacks;
    }

    public String getHost() {
        return this.host;
    }

    public synchronized void purge(boolean z) {
        for (int size = this.mFallbacks.size() - 1; size >= 0; size--) {
            Fallback fallback = (Fallback) this.mFallbacks.get(size);
            if (z) {
                if (fallback.c()) {
                    this.mFallbacks.remove(size);
                }
            } else if (!fallback.b()) {
                this.mFallbacks.remove(size);
            }
        }
    }

    public synchronized JSONObject toJSON() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put(b.ELECTION_KEY_HOST, this.host);
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.mFallbacks.iterator();
        while (it.hasNext()) {
            jSONArray.put(((Fallback) it.next()).h());
        }
        jSONObject.put("fbs", jSONArray);
        return jSONObject;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.host);
        stringBuilder.append("\n");
        Iterator it = this.mFallbacks.iterator();
        while (it.hasNext()) {
            stringBuilder.append((Fallback) it.next());
        }
        return stringBuilder.toString();
    }
}
