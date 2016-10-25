package com.a.a.b;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;

// compiled from: MultipartRequestParams.java
public final class b {
    protected ConcurrentHashMap<String, String> a;
    protected ConcurrentHashMap<String, a> b;

    // compiled from: MultipartRequestParams.java
    private static class a {
        public InputStream a;
        public String b;
        public String c;

        public a(InputStream inputStream, String str) {
            this.a = inputStream;
            this.b = str;
            this.c = null;
        }

        public final String a() {
            return this.b != null ? this.b : "nofilename";
        }
    }

    public b() {
        this.a = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
    }

    public final void a(String str, String str2) {
        if (str != null && str2 != null) {
            this.a.put(str, str2);
        }
    }

    public final HttpEntity a() {
        HttpEntity aVar = new a();
        if (!this.a.isEmpty()) {
            for (Entry entry : this.a.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                aVar.a();
                try {
                    aVar.b.write(new StringBuilder("Content-Disposition: form-data; name=\"").append(str).append("\"\r\n\r\n").toString().getBytes());
                    aVar.b.write(str2.getBytes());
                    aVar.b.write(new StringBuilder("\r\n--").append(aVar.a).append("\r\n").toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!this.b.isEmpty()) {
            int size = this.b.entrySet().size() - 1;
            int i = 0;
            for (Entry entry2 : this.b.entrySet()) {
                a aVar2 = (a) entry2.getValue();
                if (aVar2.a != null) {
                    boolean z = i == size;
                    if (aVar2.c != null) {
                        aVar.a((String) entry2.getKey(), aVar2.a(), aVar2.a, aVar2.c, z);
                    } else {
                        aVar.a((String) entry2.getKey(), aVar2.a(), aVar2.a, z);
                    }
                }
                i++;
            }
        }
        return aVar;
    }

    public final void a(String str, File file) {
        try {
            InputStream fileInputStream = new FileInputStream(file);
            String name = file.getName();
            if (str != null) {
                this.b.put(str, new a(fileInputStream, name));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
