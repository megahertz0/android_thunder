package com.alipay.sdk.widget;

import com.alipay.sdk.widget.a.a;

final class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public final void run() {
        if (this.a.e == null) {
            this.a.e = new a(this.a.f);
        }
        try {
            if (!this.a.e.isShowing()) {
                this.a.e.show();
            }
        } catch (Exception e) {
        }
    }
}
