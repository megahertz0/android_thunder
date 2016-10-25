package com.inmobi.commons.core.network;

// compiled from: AsyncNetworkTask.java
public final class a {
    private NetworkRequest a;
    private a b;

    // compiled from: AsyncNetworkTask.java
    public static interface a {
        void a(c cVar);

        void b(c cVar);
    }

    public a(NetworkRequest networkRequest, a aVar) {
        this.a = networkRequest;
        this.b = aVar;
    }

    public final void a() {
        new Thread(new Runnable() {
            public void run() {
                c a = new b(a.this).a();
                if (a.a()) {
                    a.this.b.b(a);
                } else {
                    a.this.b.a(a);
                }
            }
        }).start();
    }
}
