package com.xunlei.downloadprovider.member.payment.ui.widget;

// compiled from: VoucherListDialog.java
public final class c implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ b b;

    public c(b bVar) {
        this.b = bVar;
        this.a = -1;
    }

    public final void run() {
        this.b.a.setItemChecked(this.a, true);
    }
}
