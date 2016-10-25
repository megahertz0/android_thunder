package com.google.zxing.qrcode.decoder;

// compiled from: DataMask.java
abstract class c {
    private static final c[] a;

    // compiled from: DataMask.java
    private static final class a extends c {
        private a() {
            super();
        }

        final boolean a(int i, int i2) {
            return ((i + i2) & 1) == 0;
        }
    }

    // compiled from: DataMask.java
    private static final class b extends c {
        private b() {
            super();
        }

        final boolean a(int i, int i2) {
            return (i & 1) == 0;
        }
    }

    // compiled from: DataMask.java
    private static final class c extends c {
        private c() {
            super();
        }

        final boolean a(int i, int i2) {
            return i2 % 3 == 0;
        }
    }

    // compiled from: DataMask.java
    private static final class d extends c {
        private d() {
            super();
        }

        final boolean a(int i, int i2) {
            return (i + i2) % 3 == 0;
        }
    }

    // compiled from: DataMask.java
    private static final class e extends c {
        private e() {
            super();
        }

        final boolean a(int i, int i2) {
            return (((i / 2) + (i2 / 3)) & 1) == 0;
        }
    }

    // compiled from: DataMask.java
    private static final class f extends c {
        private f() {
            super();
        }

        final boolean a(int i, int i2) {
            int i3 = i * i2;
            return (i3 % 3) + (i3 & 1) == 0;
        }
    }

    // compiled from: DataMask.java
    private static final class g extends c {
        private g() {
            super();
        }

        final boolean a(int i, int i2) {
            int i3 = i * i2;
            return (((i3 % 3) + (i3 & 1)) & 1) == 0;
        }
    }

    // compiled from: DataMask.java
    private static final class h extends c {
        private h() {
            super();
        }

        final boolean a(int i, int i2) {
            return ((((i + i2) & 1) + ((i * i2) % 3)) & 1) == 0;
        }
    }

    abstract boolean a(int i, int i2);

    static {
        a = new c[]{new a(), new b(), new c(), new d(), new e(), new f(), new g(), new h()};
    }

    private c() {
    }

    final void a(com.google.zxing.common.b bVar, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            for (int i3 = 0; i3 < i; i3++) {
                if (a(i2, i3)) {
                    bVar.c(i3, i2);
                }
            }
        }
    }

    static c a(int i) {
        if (i >= 0 && i <= 7) {
            return a[i];
        }
        throw new IllegalArgumentException();
    }
}
