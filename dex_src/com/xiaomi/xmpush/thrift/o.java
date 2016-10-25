package com.xiaomi.xmpush.thrift;

import com.taobao.accs.common.Constants;
import com.tencent.open.SocialConstants;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import org.android.spdy.SpdyAgent;
import org.apache.thrift.b;
import org.apache.thrift.meta_data.a;
import org.apache.thrift.meta_data.g;
import org.apache.thrift.protocol.c;
import org.apache.thrift.protocol.f;
import org.apache.thrift.protocol.i;
import org.apache.thrift.protocol.k;

public class o implements Serializable, Cloneable, b<o, a> {
    public static final Map<a, org.apache.thrift.meta_data.b> i;
    private static final k j;
    private static final c k;
    private static final c l;
    private static final c m;
    private static final c n;
    private static final c o;
    private static final c p;
    private static final c q;
    private static final c r;
    public a a;
    public boolean b;
    public boolean c;
    public ByteBuffer d;
    public String e;
    public String f;
    public j g;
    public i h;
    private BitSet s;

    static {
        j = new k("XmPushActionContainer");
        k = new c(JsInterface.KEY_ACTION, (byte) 8, (short) 1);
        l = new c("encryptAction", (byte) 2, (short) 2);
        m = new c("isRequest", (byte) 2, (short) 3);
        n = new c("pushAction", (byte) 11, (short) 4);
        o = new c(SocialConstants.PARAM_APP_ID, (byte) 11, (short) 5);
        p = new c(JsInterface.KEY_APK_NAME, (byte) 11, (short) 6);
        q = new c(Constants.KEY_TARGET, (byte) 12, (short) 7);
        r = new c("metaInfo", (byte) 12, (short) 8);
        Map enumMap = new EnumMap(a.class);
        enumMap.put(a.a, new org.apache.thrift.meta_data.b(JsInterface.KEY_ACTION, (byte) 1, new a((byte) 16, a.class)));
        enumMap.put(a.b, new org.apache.thrift.meta_data.b("encryptAction", (byte) 1, new org.apache.thrift.meta_data.c((byte) 2)));
        enumMap.put(a.c, new org.apache.thrift.meta_data.b("isRequest", (byte) 1, new org.apache.thrift.meta_data.c((byte) 2)));
        enumMap.put(a.d, new org.apache.thrift.meta_data.b("pushAction", (byte) 1, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.e, new org.apache.thrift.meta_data.b(SocialConstants.PARAM_APP_ID, (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.f, new org.apache.thrift.meta_data.b(JsInterface.KEY_APK_NAME, (byte) 2, new org.apache.thrift.meta_data.c((byte) 11)));
        enumMap.put(a.g, new org.apache.thrift.meta_data.b(Constants.KEY_TARGET, (byte) 1, new g((byte) 12, j.class)));
        enumMap.put(a.h, new org.apache.thrift.meta_data.b("metaInfo", (byte) 2, new g((byte) 12, i.class)));
        i = Collections.unmodifiableMap(enumMap);
        org.apache.thrift.meta_data.b.a(o.class, i);
    }

    public o() {
        this.s = new BitSet(2);
        this.b = true;
        this.c = true;
    }

    public a a() {
        return this.a;
    }

    public o a(a aVar) {
        this.a = aVar;
        return this;
    }

    public o a(i iVar) {
        this.h = iVar;
        return this;
    }

    public o a(j jVar) {
        this.g = jVar;
        return this;
    }

    public o a(String str) {
        this.e = str;
        return this;
    }

    public o a(ByteBuffer byteBuffer) {
        this.d = byteBuffer;
        return this;
    }

    public o a(boolean z) {
        this.b = z;
        b(true);
        return this;
    }

    public void a(f fVar) {
        fVar.g();
        while (true) {
            c i = fVar.i();
            if (i.b != null) {
                switch (i.c) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        if (i.b == 8) {
                            this.a = a.a(fVar.t());
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        if (i.b == (byte) 2) {
                            this.b = fVar.q();
                            b(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        if (i.b == (byte) 2) {
                            this.c = fVar.q();
                            d(true);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        if (i.b == (byte) 11) {
                            this.d = fVar.x();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        if (i.b == (byte) 11) {
                            this.e = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case R.styleable.Toolbar_contentInsetEnd:
                        if (i.b == (byte) 11) {
                            this.f = fVar.w();
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case R.styleable.Toolbar_contentInsetLeft:
                        if (i.b == (byte) 12) {
                            this.g = new j();
                            this.g.a(fVar);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    case XZBDevice.Wait:
                        if (i.b == (byte) 12) {
                            this.h = new i();
                            this.h.a(fVar);
                        } else {
                            i.a(fVar, i.b);
                        }
                        break;
                    default:
                        i.a(fVar, i.b);
                        break;
                }
                fVar.j();
            } else {
                fVar.h();
                if (!d()) {
                    throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'encryptAction' was not found in serialized data! Struct: ").append(toString()).toString());
                } else if (e()) {
                    o();
                    return;
                } else {
                    throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'isRequest' was not found in serialized data! Struct: ").append(toString()).toString());
                }
            }
        }
    }

    public boolean a(o oVar) {
        if (oVar == null) {
            return false;
        }
        boolean b = b();
        boolean b2 = oVar.b();
        if (((b || b2) && (!b || !b2 || !this.a.equals(oVar.a))) || this.b != oVar.b || this.c != oVar.c) {
            return false;
        }
        b = g();
        b2 = oVar.g();
        if ((b || b2) && (!b || !b2 || !this.d.equals(oVar.d))) {
            return false;
        }
        b = i();
        b2 = oVar.i();
        if ((b || b2) && (!b || !b2 || !this.e.equals(oVar.e))) {
            return false;
        }
        b = k();
        b2 = oVar.k();
        if ((b || b2) && (!b || !b2 || !this.f.equals(oVar.f))) {
            return false;
        }
        b = l();
        b2 = oVar.l();
        if ((b || b2) && (!b || !b2 || !this.g.a(oVar.g))) {
            return false;
        }
        b = n();
        b2 = oVar.n();
        return !(b || b2) || (b && b2 && this.h.a(oVar.h));
    }

    public int b(o oVar) {
        if (!getClass().equals(oVar.getClass())) {
            return getClass().getName().compareTo(oVar.getClass().getName());
        }
        int compareTo = Boolean.valueOf(b()).compareTo(Boolean.valueOf(oVar.b()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (b()) {
            compareTo = org.apache.thrift.c.a(this.a, oVar.a);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(d()).compareTo(Boolean.valueOf(oVar.d()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (d()) {
            compareTo = org.apache.thrift.c.a(this.b, oVar.b);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(e()).compareTo(Boolean.valueOf(oVar.e()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (e()) {
            compareTo = org.apache.thrift.c.a(this.c, oVar.c);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(g()).compareTo(Boolean.valueOf(oVar.g()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (g()) {
            compareTo = org.apache.thrift.c.a(this.d, oVar.d);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(i()).compareTo(Boolean.valueOf(oVar.i()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (i()) {
            compareTo = org.apache.thrift.c.a(this.e, oVar.e);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(k()).compareTo(Boolean.valueOf(oVar.k()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (k()) {
            compareTo = org.apache.thrift.c.a(this.f, oVar.f);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(l()).compareTo(Boolean.valueOf(oVar.l()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (l()) {
            compareTo = org.apache.thrift.c.a(this.g, oVar.g);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        compareTo = Boolean.valueOf(n()).compareTo(Boolean.valueOf(oVar.n()));
        if (compareTo != 0) {
            return compareTo;
        }
        if (n()) {
            compareTo = org.apache.thrift.c.a(this.h, oVar.h);
            if (compareTo != 0) {
                return compareTo;
            }
        }
        return 0;
    }

    public o b(String str) {
        this.f = str;
        return this;
    }

    public void b(f fVar) {
        o();
        fVar.a(j);
        if (this.a != null) {
            fVar.a(k);
            fVar.a(this.a.a());
            fVar.b();
        }
        fVar.a(l);
        fVar.a(this.b);
        fVar.b();
        fVar.a(m);
        fVar.a(this.c);
        fVar.b();
        if (this.d != null) {
            fVar.a(n);
            fVar.a(this.d);
            fVar.b();
        }
        if (this.e != null && i()) {
            fVar.a(o);
            fVar.a(this.e);
            fVar.b();
        }
        if (this.f != null && k()) {
            fVar.a(p);
            fVar.a(this.f);
            fVar.b();
        }
        if (this.g != null) {
            fVar.a(q);
            this.g.b(fVar);
            fVar.b();
        }
        if (this.h != null && n()) {
            fVar.a(r);
            this.h.b(fVar);
            fVar.b();
        }
        fVar.c();
        fVar.a();
    }

    public void b(boolean z) {
        this.s.set(0, z);
    }

    public boolean b() {
        return this.a != null;
    }

    public o c(boolean z) {
        this.c = z;
        d(true);
        return this;
    }

    public boolean c() {
        return this.b;
    }

    public /* synthetic */ int compareTo(Object obj) {
        return b((o) obj);
    }

    public void d(boolean z) {
        this.s.set(1, z);
    }

    public boolean d() {
        return this.s.get(0);
    }

    public boolean e() {
        return this.s.get(1);
    }

    public boolean equals(Object obj) {
        return (obj != null && (obj instanceof o)) ? a((o) obj) : false;
    }

    public byte[] f() {
        a(org.apache.thrift.c.c(this.d));
        return this.d.array();
    }

    public boolean g() {
        return this.d != null;
    }

    public String h() {
        return this.e;
    }

    public int hashCode() {
        return 0;
    }

    public boolean i() {
        return this.e != null;
    }

    public String j() {
        return this.f;
    }

    public boolean k() {
        return this.f != null;
    }

    public boolean l() {
        return this.g != null;
    }

    public i m() {
        return this.h;
    }

    public boolean n() {
        return this.h != null;
    }

    public void o() {
        if (this.a == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'action' was not present! Struct: ").append(toString()).toString());
        } else if (this.d == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'pushAction' was not present! Struct: ").append(toString()).toString());
        } else if (this.g == null) {
            throw new org.apache.thrift.protocol.g(new StringBuilder("Required field 'target' was not present! Struct: ").append(toString()).toString());
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("XmPushActionContainer(");
        stringBuilder.append("action:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("encryptAction:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("isRequest:");
        stringBuilder.append(this.c);
        stringBuilder.append(", ");
        stringBuilder.append("pushAction:");
        if (this.d == null) {
            stringBuilder.append("null");
        } else {
            org.apache.thrift.c.a(this.d, stringBuilder);
        }
        if (i()) {
            stringBuilder.append(", ");
            stringBuilder.append("appid:");
            if (this.e == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.e);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("packageName:");
            if (this.f == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.f);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("target:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.g);
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("metaInfo:");
            if (this.h == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.h);
            }
        }
        stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
        return stringBuilder.toString();
    }
}
