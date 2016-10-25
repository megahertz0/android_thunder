package android.support.v7.widget;

import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v7.widget.RecyclerView.t;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;
import org.android.spdy.SpdyAgent;

// compiled from: AdapterHelper.java
final class o implements a {
    final ArrayList<b> a;
    final ArrayList<b> b;
    final a c;
    Runnable d;
    final boolean e;
    final aw f;
    int g;
    private Pool<b> h;

    // compiled from: AdapterHelper.java
    static interface a {
        t a(int i);

        void a(int i, int i2);

        void a(int i, int i2, Object obj);

        void a(b bVar);

        void b(int i, int i2);

        void b(b bVar);

        void c(int i, int i2);

        void d(int i, int i2);
    }

    // compiled from: AdapterHelper.java
    static class b {
        int a;
        int b;
        Object c;
        int d;

        b(int i, int i2, int i3, Object obj) {
            this.a = i;
            this.b = i2;
            this.d = i3;
            this.c = obj;
        }

        public final String toString() {
            String str;
            StringBuilder append = new StringBuilder().append(Integer.toHexString(System.identityHashCode(this))).append("[");
            switch (this.a) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    str = "add";
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    str = "rm";
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    str = "up";
                    break;
                case XZBDevice.Wait:
                    str = "mv";
                    break;
                default:
                    str = "??";
                    break;
            }
            return append.append(str).append(",s:").append(this.b).append("c:").append(this.d).append(",p:").append(this.c).append("]").toString();
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            b bVar = (b) obj;
            if (this.a != bVar.a) {
                return false;
            }
            if (this.a == 8 && Math.abs(this.d - this.b) == 1 && this.d == bVar.b && this.b == bVar.d) {
                return true;
            }
            if (this.d != bVar.d) {
                return false;
            }
            if (this.b != bVar.b) {
                return false;
            }
            return this.c != null ? this.c.equals(bVar.c) : bVar.c == null;
        }

        public final int hashCode() {
            return (((this.a * 31) + this.b) * 31) + this.d;
        }
    }

    o(a aVar) {
        this(aVar, (byte) 0);
    }

    private o(a aVar, byte b) {
        this.h = new SimplePool(30);
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.g = 0;
        this.c = aVar;
        this.e = false;
        this.f = new aw(this);
    }

    final void a() {
        a(this.a);
        a(this.b);
        this.g = 0;
    }

    final void b() {
        aw awVar = this.f;
        List list = this.a;
        while (true) {
            int i;
            Object obj;
            Object obj2 = null;
            int size = list.size() - 1;
            while (size >= 0) {
                Object obj3;
                int i2;
                b bVar;
                b bVar2;
                b bVar3;
                Object obj4;
                Object obj5;
                int i3;
                int size2;
                int i4;
                int i5;
                int i6;
                if (((b) list.get(size)).a != 8) {
                    obj3 = 1;
                } else if (obj2 != null) {
                    i = size;
                    if (i == -1) {
                        i2 = i + 1;
                        bVar = (b) list.get(i);
                        bVar2 = (b) list.get(i2);
                        switch (bVar2.a) {
                            case SpdyAgent.ACCS_ONLINE_SERVER:
                                size = 0;
                                if (bVar.d < bVar2.b) {
                                    size = -1;
                                }
                                if (bVar.b < bVar2.b) {
                                    size++;
                                }
                                if (bVar2.b <= bVar.b) {
                                    bVar.b += bVar2.d;
                                }
                                if (bVar2.b <= bVar.d) {
                                    bVar.d += bVar2.d;
                                }
                                bVar2.b = size + bVar2.b;
                                list.set(i, bVar2);
                                list.set(i2, bVar);
                                break;
                            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                bVar3 = null;
                                obj4 = null;
                                if (bVar.b >= bVar.d) {
                                    obj5 = null;
                                    if (bVar2.b == bVar.b && bVar2.d == bVar.d - bVar.b) {
                                        obj4 = 1;
                                    }
                                } else {
                                    obj5 = 1;
                                    if (bVar2.b == bVar.d + 1 && bVar2.d == bVar.b - bVar.d) {
                                        obj4 = 1;
                                    }
                                }
                                if (bVar.d < bVar2.b) {
                                    bVar2.b--;
                                } else if (bVar.d < bVar2.b + bVar2.d) {
                                    bVar2.d--;
                                    bVar.a = 2;
                                    bVar.d = 1;
                                    if (bVar2.d == 0) {
                                        list.remove(i2);
                                        awVar.a.a(bVar2);
                                    }
                                }
                                if (bVar.b <= bVar2.b) {
                                    bVar2.b++;
                                } else if (bVar.b < bVar2.b + bVar2.d) {
                                    bVar3 = awVar.a.a(XZBDevice.DOWNLOAD_LIST_RECYCLE, bVar.b + 1, (bVar2.b + bVar2.d) - bVar.b, null);
                                    bVar2.d = bVar.b - bVar2.b;
                                }
                                if (obj4 == null) {
                                    list.set(i, bVar2);
                                    list.remove(i2);
                                    awVar.a.a(bVar);
                                } else {
                                    if (obj5 == null) {
                                        if (bVar3 != null) {
                                            if (bVar.b > bVar3.b) {
                                                bVar.b -= bVar3.d;
                                            }
                                            if (bVar.d > bVar3.b) {
                                                bVar.d -= bVar3.d;
                                            }
                                        }
                                        if (bVar.b > bVar2.b) {
                                            bVar.b -= bVar2.d;
                                        }
                                        if (bVar.d > bVar2.b) {
                                            bVar.d -= bVar2.d;
                                        }
                                    } else {
                                        if (bVar3 != null) {
                                            if (bVar.b >= bVar3.b) {
                                                bVar.b -= bVar3.d;
                                            }
                                            if (bVar.d >= bVar3.b) {
                                                bVar.d -= bVar3.d;
                                            }
                                        }
                                        if (bVar.b >= bVar2.b) {
                                            bVar.b -= bVar2.d;
                                        }
                                        if (bVar.d >= bVar2.b) {
                                            bVar.d -= bVar2.d;
                                        }
                                    }
                                    list.set(i, bVar2);
                                    if (bVar.b == bVar.d) {
                                        list.set(i2, bVar);
                                    } else {
                                        list.remove(i2);
                                    }
                                    if (bVar3 != null) {
                                        list.add(i, bVar3);
                                    }
                                }
                            case XZBDevice.DOWNLOAD_LIST_ALL:
                                obj5 = null;
                                obj4 = null;
                                if (bVar.d < bVar2.b) {
                                    bVar2.b--;
                                } else if (bVar.d < bVar2.b + bVar2.d) {
                                    bVar2.d--;
                                    obj5 = awVar.a.a(XZBDevice.DOWNLOAD_LIST_ALL, bVar.b, 1, bVar2.c);
                                }
                                if (bVar.b <= bVar2.b) {
                                    bVar2.b++;
                                } else if (bVar.b < bVar2.b + bVar2.d) {
                                    i3 = (bVar2.b + bVar2.d) - bVar.b;
                                    obj4 = awVar.a.a(XZBDevice.DOWNLOAD_LIST_ALL, bVar.b + 1, i3, bVar2.c);
                                    bVar2.d -= i3;
                                }
                                list.set(i2, bVar);
                                if (bVar2.d <= 0) {
                                    list.set(i, bVar2);
                                } else {
                                    list.remove(i);
                                    awVar.a.a(bVar2);
                                }
                                if (obj5 != null) {
                                    list.add(i, obj5);
                                }
                                if (obj4 != null) {
                                    list.add(i, obj4);
                                }
                            default:
                                break;
                        }
                    } else {
                        size2 = this.a.size();
                        for (i = 0; i < size2; i++) {
                            bVar = (b) this.a.get(i);
                            switch (bVar.a) {
                                case SpdyAgent.ACCS_ONLINE_SERVER:
                                    c(bVar);
                                    break;
                                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                    i2 = bVar.b;
                                    i4 = bVar.d + bVar.b;
                                    obj = -1;
                                    size = bVar.b;
                                    i5 = 0;
                                    while (size < i4) {
                                        obj2 = null;
                                        if (this.c.a(size) == null || c(size)) {
                                            if (i3 == 0) {
                                                b(a(XZBDevice.DOWNLOAD_LIST_RECYCLE, i2, i5, null));
                                                obj2 = 1;
                                            }
                                            obj = 1;
                                        } else {
                                            if (i3 == 1) {
                                                c(a(XZBDevice.DOWNLOAD_LIST_RECYCLE, i2, i5, null));
                                                obj2 = 1;
                                            }
                                            obj = null;
                                        }
                                        if (obj2 == null) {
                                            i6 = size - i5;
                                            size = i4 - i5;
                                            obj4 = 1;
                                        } else {
                                            int i7 = size;
                                            size = i4;
                                            i4 = i5 + 1;
                                            i6 = i7;
                                        }
                                        i5 = i4;
                                        i4 = size;
                                        size = i6 + 1;
                                    }
                                    if (i5 != bVar.d) {
                                        a(bVar);
                                        bVar = a(XZBDevice.DOWNLOAD_LIST_RECYCLE, i2, i5, null);
                                    }
                                    if (i3 != 0) {
                                        b(bVar);
                                    } else {
                                        c(bVar);
                                    }
                                    break;
                                case XZBDevice.DOWNLOAD_LIST_ALL:
                                    i3 = bVar.b;
                                    i5 = bVar.b + bVar.d;
                                    i4 = bVar.b;
                                    i6 = 0;
                                    size = i3;
                                    i3 = -1;
                                    while (i4 < i5) {
                                        if (this.c.a(i4) == null || c(i4)) {
                                            if (i3 == 0) {
                                                b(a(XZBDevice.DOWNLOAD_LIST_ALL, size, i6, bVar.c));
                                                i6 = 0;
                                                size = i4;
                                            }
                                            i3 = size;
                                            size = i6;
                                            i6 = 1;
                                        } else {
                                            if (i3 == 1) {
                                                c(a(XZBDevice.DOWNLOAD_LIST_ALL, size, i6, bVar.c));
                                                i6 = 0;
                                                size = i4;
                                            }
                                            i3 = size;
                                            size = i6;
                                            obj2 = null;
                                        }
                                        i4++;
                                        Object obj6 = obj2;
                                        i6 = size + 1;
                                        size = i3;
                                        obj = obj6;
                                    }
                                    if (i6 != bVar.d) {
                                        obj4 = bVar.c;
                                        a(bVar);
                                        bVar = a(XZBDevice.DOWNLOAD_LIST_ALL, size, i6, obj4);
                                    }
                                    if (i3 != 0) {
                                        b(bVar);
                                    } else {
                                        c(bVar);
                                    }
                                    break;
                                case XZBDevice.Wait:
                                    c(bVar);
                                    break;
                            }
                            if (this.d != null) {
                                this.d.run();
                            }
                        }
                        this.a.clear();
                        return;
                    }
                } else {
                    obj3 = obj2;
                }
                size--;
                obj2 = obj3;
            }
            i = -1;
            if (i == -1) {
                size2 = this.a.size();
                for (i = 0; i < size2; i++) {
                    bVar = (b) this.a.get(i);
                    switch (bVar.a) {
                        case SpdyAgent.ACCS_ONLINE_SERVER:
                            c(bVar);
                            break;
                        case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                            i2 = bVar.b;
                            i4 = bVar.d + bVar.b;
                            obj = -1;
                            size = bVar.b;
                            i5 = 0;
                            while (size < i4) {
                                obj2 = null;
                                if (this.c.a(size) == null) {
                                }
                                if (i3 == 0) {
                                    b(a(XZBDevice.DOWNLOAD_LIST_RECYCLE, i2, i5, null));
                                    obj2 = 1;
                                }
                                obj = 1;
                                if (obj2 == null) {
                                    int i72 = size;
                                    size = i4;
                                    i4 = i5 + 1;
                                    i6 = i72;
                                } else {
                                    i6 = size - i5;
                                    size = i4 - i5;
                                    obj4 = 1;
                                }
                                i5 = i4;
                                i4 = size;
                                size = i6 + 1;
                            }
                            if (i5 != bVar.d) {
                                a(bVar);
                                bVar = a(XZBDevice.DOWNLOAD_LIST_RECYCLE, i2, i5, null);
                            }
                            if (i3 != 0) {
                                c(bVar);
                            } else {
                                b(bVar);
                            }
                            break;
                        case XZBDevice.DOWNLOAD_LIST_ALL:
                            i3 = bVar.b;
                            i5 = bVar.b + bVar.d;
                            i4 = bVar.b;
                            i6 = 0;
                            size = i3;
                            i3 = -1;
                            while (i4 < i5) {
                                if (this.c.a(i4) == null) {
                                }
                                if (i3 == 0) {
                                    b(a(XZBDevice.DOWNLOAD_LIST_ALL, size, i6, bVar.c));
                                    i6 = 0;
                                    size = i4;
                                }
                                i3 = size;
                                size = i6;
                                i6 = 1;
                                i4++;
                                Object obj62 = obj2;
                                i6 = size + 1;
                                size = i3;
                                obj = obj62;
                            }
                            if (i6 != bVar.d) {
                                obj4 = bVar.c;
                                a(bVar);
                                bVar = a(XZBDevice.DOWNLOAD_LIST_ALL, size, i6, obj4);
                            }
                            if (i3 != 0) {
                                c(bVar);
                            } else {
                                b(bVar);
                            }
                            break;
                        case XZBDevice.Wait:
                            c(bVar);
                            break;
                    }
                    if (this.d != null) {
                        this.d.run();
                    }
                }
                this.a.clear();
                return;
            }
            i2 = i + 1;
            bVar = (b) list.get(i);
            bVar2 = (b) list.get(i2);
            switch (bVar2.a) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    size = 0;
                    if (bVar.d < bVar2.b) {
                        size = -1;
                    }
                    if (bVar.b < bVar2.b) {
                        size++;
                    }
                    if (bVar2.b <= bVar.b) {
                        bVar.b += bVar2.d;
                    }
                    if (bVar2.b <= bVar.d) {
                        bVar.d += bVar2.d;
                    }
                    bVar2.b = size + bVar2.b;
                    list.set(i, bVar2);
                    list.set(i2, bVar);
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    bVar3 = null;
                    obj4 = null;
                    if (bVar.b >= bVar.d) {
                        obj5 = 1;
                        obj4 = 1;
                    } else {
                        obj5 = null;
                        obj4 = 1;
                    }
                    if (bVar.d < bVar2.b) {
                        bVar2.b--;
                    } else if (bVar.d < bVar2.b + bVar2.d) {
                        bVar2.d--;
                        bVar.a = 2;
                        bVar.d = 1;
                        if (bVar2.d == 0) {
                            list.remove(i2);
                            awVar.a.a(bVar2);
                        }
                    }
                    if (bVar.b <= bVar2.b) {
                        bVar2.b++;
                    } else if (bVar.b < bVar2.b + bVar2.d) {
                        bVar3 = awVar.a.a(XZBDevice.DOWNLOAD_LIST_RECYCLE, bVar.b + 1, (bVar2.b + bVar2.d) - bVar.b, null);
                        bVar2.d = bVar.b - bVar2.b;
                    }
                    if (obj4 == null) {
                        if (obj5 == null) {
                            if (bVar3 != null) {
                                if (bVar.b >= bVar3.b) {
                                    bVar.b -= bVar3.d;
                                }
                                if (bVar.d >= bVar3.b) {
                                    bVar.d -= bVar3.d;
                                }
                            }
                            if (bVar.b >= bVar2.b) {
                                bVar.b -= bVar2.d;
                            }
                            if (bVar.d >= bVar2.b) {
                                bVar.d -= bVar2.d;
                            }
                        } else {
                            if (bVar3 != null) {
                                if (bVar.b > bVar3.b) {
                                    bVar.b -= bVar3.d;
                                }
                                if (bVar.d > bVar3.b) {
                                    bVar.d -= bVar3.d;
                                }
                            }
                            if (bVar.b > bVar2.b) {
                                bVar.b -= bVar2.d;
                            }
                            if (bVar.d > bVar2.b) {
                                bVar.d -= bVar2.d;
                            }
                        }
                        list.set(i, bVar2);
                        if (bVar.b == bVar.d) {
                            list.remove(i2);
                        } else {
                            list.set(i2, bVar);
                        }
                        if (bVar3 != null) {
                            list.add(i, bVar3);
                        }
                    } else {
                        list.set(i, bVar2);
                        list.remove(i2);
                        awVar.a.a(bVar);
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    obj5 = null;
                    obj4 = null;
                    if (bVar.d < bVar2.b) {
                        bVar2.b--;
                    } else if (bVar.d < bVar2.b + bVar2.d) {
                        bVar2.d--;
                        obj5 = awVar.a.a(XZBDevice.DOWNLOAD_LIST_ALL, bVar.b, 1, bVar2.c);
                    }
                    if (bVar.b <= bVar2.b) {
                        bVar2.b++;
                    } else if (bVar.b < bVar2.b + bVar2.d) {
                        i3 = (bVar2.b + bVar2.d) - bVar.b;
                        obj4 = awVar.a.a(XZBDevice.DOWNLOAD_LIST_ALL, bVar.b + 1, i3, bVar2.c);
                        bVar2.d -= i3;
                    }
                    list.set(i2, bVar);
                    if (bVar2.d <= 0) {
                        list.remove(i);
                        awVar.a.a(bVar2);
                    } else {
                        list.set(i, bVar2);
                    }
                    if (obj5 != null) {
                        list.add(i, obj5);
                    }
                    if (obj4 != null) {
                        list.add(i, obj4);
                    }
                default:
                    break;
            }
        }
    }

    final void c() {
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            this.c.b((b) this.b.get(i));
        }
        a(this.b);
        this.g = 0;
    }

    private void b(b bVar) {
        if (bVar.a == 1 || bVar.a == 8) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int i;
        int b = b(bVar.b, bVar.a);
        int i2 = bVar.b;
        switch (bVar.a) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                i = 0;
                break;
            case XZBDevice.DOWNLOAD_LIST_ALL:
                i = 1;
                break;
            default:
                throw new IllegalArgumentException(new StringBuilder("op should be remove or update.").append(bVar).toString());
        }
        int i3 = 1;
        int i4 = b;
        b = i2;
        for (i2 = 1; i2 < bVar.d; i2++) {
            Object obj;
            int b2 = b(bVar.b + (i * i2), bVar.a);
            int i5;
            switch (bVar.a) {
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (b2 == i4) {
                        i5 = 1;
                    } else {
                        obj = null;
                    }
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    if (b2 == i4 + 1) {
                        i5 = 1;
                    } else {
                        obj = null;
                    }
                    break;
                default:
                    obj = null;
                    break;
            }
            if (obj != null) {
                i3++;
            } else {
                b a = a(bVar.a, i4, i3, bVar.c);
                a(a, b);
                a(a);
                if (bVar.a == 4) {
                    b += i3;
                }
                i3 = 1;
                i4 = b2;
            }
        }
        Object obj2 = bVar.c;
        a(bVar);
        if (i3 > 0) {
            b a2 = a(bVar.a, i4, i3, obj2);
            a(a2, b);
            a(a2);
        }
    }

    private void a(b bVar, int i) {
        this.c.a(bVar);
        switch (bVar.a) {
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.c.a(i, bVar.d);
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.c.a(i, bVar.d, bVar.c);
            default:
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
        }
    }

    private int b(int i, int i2) {
        int i3;
        int size = this.b.size() - 1;
        int i4 = i;
        while (size >= 0) {
            int i5;
            b bVar = (b) this.b.get(size);
            if (bVar.a == 8) {
                int i6;
                if (bVar.b < bVar.d) {
                    i6 = bVar.b;
                    i3 = bVar.d;
                } else {
                    i6 = bVar.d;
                    i3 = bVar.b;
                }
                if (i4 < i6 || i4 > r2) {
                    if (i4 < bVar.b) {
                        if (i2 == 1) {
                            bVar.b++;
                            bVar.d++;
                            i5 = i4;
                        } else if (i2 == 2) {
                            bVar.b--;
                            bVar.d--;
                        }
                    }
                    i5 = i4;
                } else if (i6 == bVar.b) {
                    if (i2 == 1) {
                        bVar.d++;
                    } else if (i2 == 2) {
                        bVar.d--;
                    }
                    i5 = i4 + 1;
                } else {
                    if (i2 == 1) {
                        bVar.b++;
                    } else if (i2 == 2) {
                        bVar.b--;
                    }
                    i5 = i4 - 1;
                }
            } else {
                if (bVar.b <= i4) {
                    if (bVar.a == 1) {
                        i5 = i4 - bVar.d;
                    } else if (bVar.a == 2) {
                        i5 = bVar.d + i4;
                    }
                } else if (i2 == 1) {
                    bVar.b++;
                    i5 = i4;
                } else if (i2 == 2) {
                    bVar.b--;
                }
                i5 = i4;
            }
            size--;
            i4 = i5;
        }
        for (i3 = this.b.size() - 1; i3 >= 0; i3--) {
            bVar = (b) this.b.get(i3);
            if (bVar.a == 8) {
                if (bVar.d == bVar.b || bVar.d < 0) {
                    this.b.remove(i3);
                    a(bVar);
                }
            } else if (bVar.d <= 0) {
                this.b.remove(i3);
                a(bVar);
            }
        }
        return i4;
    }

    private boolean c(int i) {
        int size = this.b.size();
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = (b) this.b.get(i2);
            if (bVar.a == 8) {
                if (a(bVar.d, i2 + 1) == i) {
                    return true;
                }
            } else if (bVar.a == 1) {
                int i3 = bVar.b + bVar.d;
                for (int i4 = bVar.b; i4 < i3; i4++) {
                    if (a(i4, i2 + 1) == i) {
                        return true;
                    }
                }
                continue;
            } else {
                continue;
            }
        }
        return false;
    }

    private void c(b bVar) {
        this.b.add(bVar);
        switch (bVar.a) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.c.c(bVar.b, bVar.d);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.c.b(bVar.b, bVar.d);
            case XZBDevice.DOWNLOAD_LIST_ALL:
                this.c.a(bVar.b, bVar.d, bVar.c);
            case XZBDevice.Wait:
                this.c.d(bVar.b, bVar.d);
            default:
                throw new IllegalArgumentException(new StringBuilder("Unknown update op type for ").append(bVar).toString());
        }
    }

    final boolean d() {
        return this.a.size() > 0;
    }

    final boolean a(int i) {
        return (this.g & i) != 0;
    }

    final int b(int i) {
        return a(i, 0);
    }

    final int a(int i, int i2) {
        int size = this.b.size();
        int i3 = i;
        while (i2 < size) {
            b bVar = (b) this.b.get(i2);
            if (bVar.a == 8) {
                if (bVar.b == i3) {
                    i3 = bVar.d;
                } else {
                    if (bVar.b < i3) {
                        i3--;
                    }
                    if (bVar.d <= i3) {
                        i3++;
                    }
                }
            } else if (bVar.b > i3) {
                continue;
            } else if (bVar.a == 2) {
                if (i3 < bVar.b + bVar.d) {
                    return -1;
                }
                i3 -= bVar.d;
            } else if (bVar.a == 1) {
                i3 += bVar.d;
            }
            i2++;
        }
        return i3;
    }

    final void e() {
        c();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            b bVar = (b) this.a.get(i);
            switch (bVar.a) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    this.c.b(bVar);
                    this.c.c(bVar.b, bVar.d);
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    this.c.b(bVar);
                    this.c.a(bVar.b, bVar.d);
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    this.c.b(bVar);
                    this.c.a(bVar.b, bVar.d, bVar.c);
                    break;
                case XZBDevice.Wait:
                    this.c.b(bVar);
                    this.c.d(bVar.b, bVar.d);
                    break;
            }
            if (this.d != null) {
                this.d.run();
            }
        }
        a(this.a);
        this.g = 0;
    }

    public final b a(int i, int i2, int i3, Object obj) {
        b bVar = (b) this.h.acquire();
        if (bVar == null) {
            return new b(i, i2, i3, obj);
        }
        bVar.a = i;
        bVar.b = i2;
        bVar.d = i3;
        bVar.c = obj;
        return bVar;
    }

    public final void a(b bVar) {
        if (!this.e) {
            bVar.c = null;
            this.h.release(bVar);
        }
    }

    private void a(List<b> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            a((b) list.get(i));
        }
        list.clear();
    }
}
