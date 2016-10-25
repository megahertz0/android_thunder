package com.xunlei.common.httpclient.request;

import com.alipay.sdk.sys.a;
import com.sina.weibo.sdk.component.GameManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

// compiled from: RequestParams.java
public final class b {
    private static String a;
    private ConcurrentHashMap<String, String> b;
    private ConcurrentHashMap<String, a> c;
    private ConcurrentHashMap<String, ArrayList<String>> d;

    static {
        a = GameManager.DEFAULT_CHARSET;
    }

    public b() {
        c();
    }

    private b(Map<String, String> map) {
        c();
        for (Entry entry : map.entrySet()) {
            a((String) entry.getKey(), (String) entry.getValue());
        }
    }

    private b(String str, String str2) {
        c();
        a(str, str2);
    }

    private b(Object... objArr) {
        c();
        int length = objArr.length;
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Supplied arguments must be even");
        }
        for (int i = 0; i < length; i += 2) {
            a(String.valueOf(objArr[i]), String.valueOf(objArr[i + 1]));
        }
    }

    private void a(String str, String str2) {
        if (str != null && str2 != null) {
            this.b.put(str, str2);
        }
    }

    private void a(String str, File file) throws FileNotFoundException {
        a(str, new FileInputStream(file), file.getName());
    }

    private void a(String str, ArrayList<String> arrayList) {
        if (str != null) {
            this.d.put(str, arrayList);
        }
    }

    private void b(String str, String str2) {
        if (str != null && str2 != null) {
            ArrayList arrayList = (ArrayList) this.d.get(str);
            if (arrayList == null) {
                arrayList = new ArrayList();
                if (str != null) {
                    this.d.put(str, arrayList);
                }
            }
            arrayList.add(str2);
        }
    }

    private void a(String str, InputStream inputStream) {
        a(str, inputStream, null);
    }

    private void a(String str, InputStream inputStream, String str2, String str3) {
        if (str != null && inputStream != null) {
            this.c.put(str, new a(inputStream, str2, null));
        }
    }

    private boolean a(String str) {
        return this.b.containsKey(str) || this.c.containsKey(str) || this.d.containsKey(str);
    }

    private void b(String str) {
        this.b.remove(str);
        this.c.remove(str);
        this.d.remove(str);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.b.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(a.b);
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        for (Entry entry2 : this.c.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(a.b);
            }
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILE");
        }
        for (Entry entry22 : this.d.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(a.b);
            }
            ArrayList arrayList = (ArrayList) entry22.getValue();
            for (int i = 0; i < arrayList.size(); i++) {
                if (i != 0) {
                    stringBuilder.append(a.b);
                }
                stringBuilder.append((String) entry22.getKey());
                stringBuilder.append("=");
                stringBuilder.append((String) arrayList.get(i));
            }
        }
        return stringBuilder.toString();
    }

    public final HttpEntity a() {
        if (this.c.isEmpty()) {
            try {
                return new UrlEncodedFormEntity(d(), a);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        e eVar = new e();
        for (Entry entry : this.b.entrySet()) {
            eVar.a((String) entry.getKey(), (String) entry.getValue());
        }
        for (Entry entry2 : this.d.entrySet()) {
            Iterator it = ((ArrayList) entry2.getValue()).iterator();
            while (it.hasNext()) {
                eVar.a((String) entry2.getKey(), (String) it.next());
            }
        }
        int size = this.c.entrySet().size() - 1;
        int i = 0;
        for (Entry entry22 : this.c.entrySet()) {
            a aVar = (a) entry22.getValue();
            if (aVar.a != null) {
                boolean z = i == size;
                if (aVar.b != null) {
                    eVar.a((String) entry22.getKey(), aVar.a(), aVar.a, aVar.b);
                } else {
                    eVar.a((String) entry22.getKey(), aVar.a(), aVar.a, z);
                }
            }
            i++;
        }
        return eVar;
    }

    private void c() {
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
        this.d = new ConcurrentHashMap();
    }

    private List<BasicNameValuePair> d() {
        List<BasicNameValuePair> linkedList = new LinkedList();
        for (Entry entry : this.b.entrySet()) {
            linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        for (Entry entry2 : this.d.entrySet()) {
            Iterator it = ((ArrayList) entry2.getValue()).iterator();
            while (it.hasNext()) {
                linkedList.add(new BasicNameValuePair((String) entry2.getKey(), (String) it.next()));
            }
        }
        return linkedList;
    }

    public final String b() {
        return URLEncodedUtils.format(d(), a);
    }

    private void a(String str, InputStream inputStream, String str2) {
        if (str != null && inputStream != null) {
            this.c.put(str, new a(inputStream, str2, null));
        }
    }
}
