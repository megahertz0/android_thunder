package com.xunlei.downloadprovider.download.tasklist.list.d;

import android.os.Handler;
import com.xunlei.downloadprovider.R;

// compiled from: KuainiaoTrialRemindViewHolder.java
final class k extends com.xunlei.downloadprovider.download.util.k {
    final /* synthetic */ b a;

    k(b bVar, Handler handler) {
        this.a = bVar;
        super(handler);
    }

    public final void a() {
        if (this.a.A) {
            b.a(this.a, this.a.v);
            if (this.a.t) {
                this.a.c.setText(this.a.k.getString(R.string.download_list_kuainiao_in_trial_tip2, new Object[]{Integer.valueOf(this.a.o), Integer.valueOf(this.a.s)}));
            } else {
                this.a.c.setText(this.a.k.getString(R.string.download_list_kuainiao_in_trial_tip1, new Object[]{Integer.valueOf(this.a.o), Integer.valueOf(this.a.r), Integer.valueOf(this.a.s)}));
            }
            b.v(this.a);
        } else {
            b.a(this.a, this.a.u);
            if (this.a.t) {
                this.a.c.setText(this.a.k.getString(R.string.download_list_kuainiao_in_trial_tip2, new Object[]{Integer.valueOf(this.a.o), Integer.valueOf(this.a.s)}));
            } else {
                this.a.c.setText(this.a.k.getString(R.string.download_list_kuainiao_in_trial_tip1, new Object[]{Integer.valueOf(this.a.o), Integer.valueOf(this.a.r), Integer.valueOf(this.a.s)}));
            }
            b.z(this.a);
        }
        if (this.a.v < 0 || this.a.u < 0) {
            this.a.B.b();
            this.a.B = null;
            this.b = null;
            this.a.a(this.a.k.getString(R.string.download_list_kuainiao_success, new Object[]{Integer.valueOf(this.a.n)}), false, this.a.k.getString(R.string.download_list_kuainiao_open), b.d);
        }
    }
}
