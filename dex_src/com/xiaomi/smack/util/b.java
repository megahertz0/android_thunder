package com.xiaomi.smack.util;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class b extends Writer {
    Writer a;
    List b;

    public b(Writer writer) {
        this.a = null;
        this.b = new ArrayList();
        this.a = writer;
    }

    private void a(String str) {
        synchronized (this.b) {
            m[] mVarArr = new m[this.b.size()];
            this.b.toArray(mVarArr);
        }
        for (m mVar : mVarArr) {
            mVar.a(str);
        }
    }

    public void a(m mVar) {
        if (mVar != null) {
            synchronized (this.b) {
                if (!this.b.contains(mVar)) {
                    this.b.add(mVar);
                }
            }
        }
    }

    public void b(m mVar) {
        synchronized (this.b) {
            this.b.remove(mVar);
        }
    }

    public void close() {
        this.a.close();
    }

    public void flush() {
        this.a.flush();
    }

    public void write(int i) {
        this.a.write(i);
    }

    public void write(String str) {
        this.a.write(str);
        a(str);
    }

    public void write(String str, int i, int i2) {
        this.a.write(str, i, i2);
        a(str.substring(i, i + i2));
    }

    public void write(char[] cArr) {
        this.a.write(cArr);
        a(new String(cArr));
    }

    public void write(char[] cArr, int i, int i2) {
        this.a.write(cArr, i, i2);
        a(new String(cArr, i, i2));
    }
}
