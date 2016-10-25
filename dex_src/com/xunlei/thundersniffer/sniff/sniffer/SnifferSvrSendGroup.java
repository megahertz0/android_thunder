package com.xunlei.thundersniffer.sniff.sniffer;

import com.xunlei.analytics.b.c;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class SnifferSvrSendGroup {
    private static final String i;
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    ArrayList<FileInfo> h;

    public static class FileInfo implements Comparable<com.xunlei.thundersniffer.sniff.sniffer.SnifferSvrSendGroup.FileInfo> {
        String a;
        String b;

        public FileInfo(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public int compareTo(com.xunlei.thundersniffer.sniff.sniffer.SnifferSvrSendGroup.FileInfo fileInfo) {
            int compareTo = this.a.compareTo(fileInfo.a);
            return compareTo != 0 ? compareTo : this.b.compareTo(fileInfo.b);
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof com.xunlei.thundersniffer.sniff.sniffer.SnifferSvrSendGroup.FileInfo)) {
                return false;
            }
            com.xunlei.thundersniffer.sniff.sniffer.SnifferSvrSendGroup.FileInfo fileInfo = (com.xunlei.thundersniffer.sniff.sniffer.SnifferSvrSendGroup.FileInfo) obj;
            boolean equals = fileInfo.a.equals(this.a);
            return equals ? fileInfo.b.equals(this.b) : equals;
        }

        public int hashCode() {
            return this.a.hashCode() + this.b.hashCode();
        }
    }

    SnifferSvrSendGroup() {
        this.a = BuildConfig.VERSION_NAME;
        this.b = BuildConfig.VERSION_NAME;
        this.c = c.f;
        this.d = BuildConfig.VERSION_NAME;
        this.e = BuildConfig.VERSION_NAME;
        this.f = BuildConfig.VERSION_NAME;
        this.g = BuildConfig.VERSION_NAME;
        this.h = new ArrayList();
    }

    static {
        i = SnifferSvrSendGroup.class.getSimpleName();
    }

    public final synchronized void a(FileInfo fileInfo) {
        if (!this.h.contains(fileInfo)) {
            this.h.add(fileInfo);
        }
    }

    public final synchronized JSONObject a() {
        JSONObject jSONObject;
        jSONObject = new JSONObject();
        try {
            jSONObject.put(SetKey.NSRC, this.a == null ? BuildConfig.VERSION_NAME : this.a);
            jSONObject.put(SetKey.KEYWORD, this.b == null ? BuildConfig.VERSION_NAME : this.b);
            jSONObject.put(SetKey.PAGE, this.c);
            jSONObject.put(SetKey.REFURL, this.d == null ? BuildConfig.VERSION_NAME : this.d);
            jSONObject.put(SetKey.LAST_MODIFIED, this.e);
            jSONObject.put(SetKey.ETAG, this.f);
            jSONObject.put(SetKey.HEAD, this.g == null ? BuildConfig.VERSION_NAME : this.g);
            Collection arrayList = new ArrayList();
            Collection arrayList2 = new ArrayList();
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                FileInfo fileInfo = (FileInfo) it.next();
                arrayList.add(fileInfo.a);
                arrayList2.add(fileInfo.b);
            }
            this.h.clear();
            JSONArray jSONArray = new JSONArray(arrayList);
            JSONArray jSONArray2 = new JSONArray(arrayList2);
            jSONObject.put(SetKey.FILEINFO, jSONArray);
            jSONObject.put(SetKey.ORIGINAL_FILEINFO, jSONArray2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
