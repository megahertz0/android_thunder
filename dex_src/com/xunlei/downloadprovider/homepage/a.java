package com.xunlei.downloadprovider.homepage;

// compiled from: AutoRefreshHelper.java
public final class a {
    public boolean a;
    private boolean b;
    private boolean c;
    private a d;

    // compiled from: AutoRefreshHelper.java
    public static interface a {
        void a();

        long b();

        void c();
    }

    public a(a aVar) {
        this.b = true;
        this.a = this.b;
        this.d = aVar;
    }

    public final void a() {
        new StringBuilder("checkAndAutoRefreshData--mShouldAutoRefresh=").append(this.b).append("|mAllowRefreshOnNextCheck=").append(this.a);
        if (!this.b) {
            return;
        }
        if (!this.a) {
            this.a = true;
        } else if (!this.c) {
            boolean z;
            if (System.currentTimeMillis() - this.d.b() >= 1800000) {
                z = true;
            } else {
                Object obj = null;
            }
            if (z) {
                this.c = true;
                this.d.a();
            }
        }
    }

    public final void a(boolean z) {
        this.c = false;
        if (z) {
            this.d.c();
        }
    }
}
