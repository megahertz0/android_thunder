package com.xiaomi.smack.util;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class a extends Reader {
    Reader a;
    List b;

    public a(Reader reader) {
        this.a = null;
        this.b = new ArrayList();
        this.a = reader;
    }

    public void a(f fVar) {
        if (fVar != null) {
            synchronized (this.b) {
                if (!this.b.contains(fVar)) {
                    this.b.add(fVar);
                }
            }
        }
    }

    public void b(f fVar) {
        synchronized (this.b) {
            this.b.remove(fVar);
        }
    }

    public void close() {
        this.a.close();
    }

    public void mark(int i) {
        this.a.mark(i);
    }

    public boolean markSupported() {
        return this.a.markSupported();
    }

    public int read() {
        return this.a.read();
    }

    public int read(char[] cArr) {
        return this.a.read(cArr);
    }

    public int read(char[] cArr, int i, int i2) {
        int read = this.a.read(cArr, i, i2);
        if (read > 0) {
            f[] fVarArr;
            String str = new String(cArr, i, read);
            synchronized (this.b) {
                fVarArr = new f[this.b.size()];
                this.b.toArray(fVarArr);
            }
            for (f fVar : fVarArr) {
                fVar.a(str);
            }
        }
        return read;
    }

    public boolean ready() {
        return this.a.ready();
    }

    public void reset() {
        this.a.reset();
    }

    public long skip(long j) {
        return this.a.skip(j);
    }
}
