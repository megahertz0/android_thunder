package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.a;
import com.bumptech.glide.load.b;
import com.bumptech.glide.load.d;
import com.bumptech.glide.load.f;
import com.bumptech.glide.load.resource.e.c;
import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

// compiled from: EngineKey.java
public final class e implements b {
    private final String a;
    private final int b;
    private final int c;
    private final d d;
    private final d e;
    private final f f;
    private final com.bumptech.glide.load.e g;
    private final c h;
    private final a i;
    private final b j;
    private String k;
    private int l;
    private b m;

    public e(String str, b bVar, int i, int i2, d dVar, d dVar2, f fVar, com.bumptech.glide.load.e eVar, c cVar, a aVar) {
        this.a = str;
        this.j = bVar;
        this.b = i;
        this.c = i2;
        this.d = dVar;
        this.e = dVar2;
        this.f = fVar;
        this.g = eVar;
        this.h = cVar;
        this.i = aVar;
    }

    public final b a() {
        if (this.m == null) {
            this.m = new i(this.a, this.j);
        }
        return this.m;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        e eVar = (e) obj;
        if (!this.a.equals(eVar.a) || !this.j.equals(eVar.j) || this.c != eVar.c || this.b != eVar.b) {
            return false;
        }
        if (((this.f == null ? 1 : 0) ^ (eVar.f == null ? 1 : 0)) != 0) {
            return false;
        }
        if (this.f != null && !this.f.a().equals(eVar.f.a())) {
            return false;
        }
        int i;
        if (this.e == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (eVar.e == null ? 1 : 0)) != 0) {
            return false;
        }
        if (this.e != null && !this.e.a().equals(eVar.e.a())) {
            return false;
        }
        if (this.d == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (eVar.d == null ? 1 : 0)) != 0) {
            return false;
        }
        if (this.d != null && !this.d.a().equals(eVar.d.a())) {
            return false;
        }
        if (this.g == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (eVar.g == null ? 1 : 0)) != 0) {
            return false;
        }
        if (this.g != null && !this.g.a().equals(eVar.g.a())) {
            return false;
        }
        if (this.h == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (eVar.h == null ? 1 : 0)) != 0) {
            return false;
        }
        if (this.h != null && !this.h.a().equals(eVar.h.a())) {
            return false;
        }
        if (this.i == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (eVar.i == null ? 1 : 0)) == 0) {
            return this.i == null || this.i.a().equals(eVar.i.a());
        } else {
            return false;
        }
    }

    public final int hashCode() {
        int i = 0;
        if (this.l == 0) {
            int hashCode;
            this.l = this.a.hashCode();
            this.l = (this.l * 31) + this.j.hashCode();
            this.l = (this.l * 31) + this.b;
            this.l = (this.l * 31) + this.c;
            this.l = (this.d != null ? this.d.a().hashCode() : 0) + (this.l * 31);
            int i2 = this.l * 31;
            if (this.e != null) {
                hashCode = this.e.a().hashCode();
            } else {
                hashCode = 0;
            }
            this.l = hashCode + i2;
            i2 = this.l * 31;
            if (this.f != null) {
                hashCode = this.f.a().hashCode();
            } else {
                hashCode = 0;
            }
            this.l = hashCode + i2;
            i2 = this.l * 31;
            if (this.g != null) {
                hashCode = this.g.a().hashCode();
            } else {
                hashCode = 0;
            }
            this.l = hashCode + i2;
            i2 = this.l * 31;
            if (this.h != null) {
                hashCode = this.h.a().hashCode();
            } else {
                hashCode = 0;
            }
            this.l = hashCode + i2;
            hashCode = this.l * 31;
            if (this.i != null) {
                i = this.i.a().hashCode();
            }
            this.l = hashCode + i;
        }
        return this.l;
    }

    public final String toString() {
        if (this.k == null) {
            this.k = new StringBuilder("EngineKey{").append(this.a).append('+').append(this.j).append("+[").append(this.b).append('x').append(this.c).append("]+'").append(this.d != null ? this.d.a() : com.umeng.a.d).append('\'').append('+').append('\'').append(this.e != null ? this.e.a() : com.umeng.a.d).append('\'').append('+').append('\'').append(this.f != null ? this.f.a() : com.umeng.a.d).append('\'').append('+').append('\'').append(this.g != null ? this.g.a() : com.umeng.a.d).append('\'').append('+').append('\'').append(this.h != null ? this.h.a() : com.umeng.a.d).append('\'').append('+').append('\'').append(this.i != null ? this.i.a() : com.umeng.a.d).append('\'').append('}').toString();
        }
        return this.k;
    }

    public final void a(MessageDigest messageDigest) throws UnsupportedEncodingException {
        byte[] array = ByteBuffer.allocate(XZBDevice.Wait).putInt(this.b).putInt(this.c).array();
        this.j.a(messageDigest);
        messageDigest.update(this.a.getBytes(GameManager.DEFAULT_CHARSET));
        messageDigest.update(array);
        messageDigest.update((this.d != null ? this.d.a() : com.umeng.a.d).getBytes(GameManager.DEFAULT_CHARSET));
        messageDigest.update((this.e != null ? this.e.a() : com.umeng.a.d).getBytes(GameManager.DEFAULT_CHARSET));
        messageDigest.update((this.f != null ? this.f.a() : com.umeng.a.d).getBytes(GameManager.DEFAULT_CHARSET));
        messageDigest.update((this.g != null ? this.g.a() : com.umeng.a.d).getBytes(GameManager.DEFAULT_CHARSET));
        messageDigest.update((this.i != null ? this.i.a() : com.umeng.a.d).getBytes(GameManager.DEFAULT_CHARSET));
    }
}
