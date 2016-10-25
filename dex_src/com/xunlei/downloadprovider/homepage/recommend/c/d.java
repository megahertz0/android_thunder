package com.xunlei.downloadprovider.homepage.recommend.c;

// compiled from: ClickNiceRecordHelper.java
final class d implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ String c;
    final /* synthetic */ String d;
    final /* synthetic */ c e;

    d(c cVar, String str, String str2, String str3, String str4) {
        this.e = cVar;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
    }

    public final void run() {
        if (!this.e.a(this.a)) {
            b bVar = new b();
            bVar.b = this.b;
            bVar.c = this.c;
            bVar.d = this.a;
            bVar.e = this.d;
            this.e.a.a(bVar);
        }
    }
}
