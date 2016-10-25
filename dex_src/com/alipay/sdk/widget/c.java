package com.alipay.sdk.widget;

final class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        if (this.a.e != null) {
            try {
                this.a.e.dismiss();
            } catch (Exception e) {
            }
        }
    }
}
