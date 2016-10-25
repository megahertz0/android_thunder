package com.xiaomi.network;

import com.taobao.accs.internal.b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import org.json.JSONArray;
import org.json.JSONObject;

class e implements Comparable<e> {
    String a;
    protected int b;
    private final LinkedList<AccessHistory> c;
    private long d;

    public e() {
        this(null, 0);
    }

    public e(String str, int i) {
        this.c = new LinkedList();
        this.d = 0;
        this.a = str;
        this.b = i;
    }

    public int a(e eVar) {
        return eVar == null ? 1 : eVar.b - this.b;
    }

    public synchronized e a(JSONObject jSONObject) {
        this.d = jSONObject.getLong("tt");
        this.b = jSONObject.getInt("wt");
        this.a = jSONObject.getString(b.ELECTION_KEY_HOST);
        JSONArray jSONArray = jSONObject.getJSONArray("ah");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.c.add(new AccessHistory().a(jSONArray.getJSONObject(i)));
        }
        return this;
    }

    public synchronized ArrayList<AccessHistory> a() {
        ArrayList<AccessHistory> arrayList;
        arrayList = new ArrayList();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            AccessHistory accessHistory = (AccessHistory) it.next();
            if (accessHistory.c() > this.d) {
                arrayList.add(accessHistory);
            }
        }
        this.d = System.currentTimeMillis();
        return arrayList;
    }

    protected synchronized void a(AccessHistory accessHistory) {
        if (accessHistory != null) {
            UploadHostStatHelper.a().b();
            this.c.add(accessHistory);
            int a = accessHistory.a();
            if (a > 0) {
                this.b += accessHistory.a();
            } else {
                int i = 0;
                int size = this.c.size() - 1;
                while (size >= 0 && ((AccessHistory) this.c.get(size)).a() < 0) {
                    i++;
                    size--;
                }
                this.b += a * i;
            }
            if (this.c.size() > 30) {
                this.b -= ((AccessHistory) this.c.remove()).a();
            }
        }
    }

    public synchronized JSONObject b() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        jSONObject.put("tt", this.d);
        jSONObject.put("wt", this.b);
        jSONObject.put(b.ELECTION_KEY_HOST, this.a);
        JSONArray jSONArray = new JSONArray();
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            jSONArray.put(((AccessHistory) it.next()).f());
        }
        jSONObject.put("ah", jSONArray);
        return jSONObject;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return a((e) obj);
    }

    public String toString() {
        return this.a + ":" + this.b;
    }
}
