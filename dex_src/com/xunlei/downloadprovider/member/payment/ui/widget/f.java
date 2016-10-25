package com.xunlei.downloadprovider.member.payment.ui.widget;

import android.text.TextUtils;
import com.xunlei.downloadprovider.member.payment.a.k$b;
import com.xunlei.downloadprovider.member.payment.bean.VoucherStateResultBean;
import com.xunlei.downloadprovider.member.payment.ui.widget.b.c;

// compiled from: VoucherListDialog.java
final class f implements k$b {
    final /* synthetic */ c a;
    final /* synthetic */ e b;

    f(e eVar, c cVar) {
        this.b = eVar;
        this.a = cVar;
    }

    public final void a(VoucherStateResultBean voucherStateResultBean) {
        b d = this.b.a.b;
        CharSequence charSequence = this.a.b;
        if (!TextUtils.isEmpty(charSequence)) {
            d.d.remove(charSequence);
        }
        d.notifyDataSetChanged();
        if (voucherStateResultBean == null) {
            this.b.a.b.notifyDataSetChanged();
            return;
        }
        this.a.c = voucherStateResultBean.getState();
        new StringBuilder("onItemClick----queryVoucherStateResponse--id=").append(this.a.b).append(", state=").append(this.a.c);
        if (this.a.c != 0) {
            this.b.a.e.add(this.a.b);
        } else {
            if (this.b.a.c != null) {
                this.b.a.c.a(this.a);
            }
            this.b.a.dismiss();
        }
        this.b.a.b.notifyDataSetChanged();
    }
}
