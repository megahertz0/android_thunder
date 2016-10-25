package com.xunlei.tdlive.withdraw;

import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.protocol.XLLiveGetWithdrawInfoRequest.WidthdrawInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;

// compiled from: MyInComeHomePage.java
class d implements ObjectCallBack {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public void onResponse(int i, String str, Object obj) {
        int i2 = -1;
        if (this.a.c()) {
            int i3;
            int i4;
            TextView c;
            boolean z;
            if (i == 0 && (obj instanceof WidthdrawInfo)) {
                WidthdrawInfo widthdrawInfo = (WidthdrawInfo) obj;
                if (widthdrawInfo.data != null) {
                    try {
                        i2 = Integer.parseInt(widthdrawInfo.data.current_gold_coin);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    i3 = widthdrawInfo.data.money;
                    i4 = i2;
                    i2 = widthdrawInfo.data.exchange_money;
                    if (i3 < 0) {
                        this.a.k.a("KEY_WITHDRAWABLE_MONEY", Integer.valueOf(i2));
                        this.a.n.setText(Float.toString(((float) i3) / 100.0f));
                    } else {
                        this.a.n.setText(R.string.unknown);
                    }
                    if (i4 < 0) {
                        this.a.m.setText(Integer.toString(i4));
                    } else {
                        this.a.m.setText(R.string.unknown);
                    }
                    c = this.a.o;
                    if (i3 <= 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    c.setEnabled(z);
                }
            }
            i3 = -1;
            i4 = -1;
            if (i3 < 0) {
                this.a.n.setText(R.string.unknown);
            } else {
                this.a.k.a("KEY_WITHDRAWABLE_MONEY", Integer.valueOf(i2));
                this.a.n.setText(Float.toString(((float) i3) / 100.0f));
            }
            if (i4 < 0) {
                this.a.m.setText(R.string.unknown);
            } else {
                this.a.m.setText(Integer.toString(i4));
            }
            c = this.a.o;
            if (i3 <= 0) {
                z = false;
            } else {
                z = true;
            }
            c.setEnabled(z);
        }
    }
}
