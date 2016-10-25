package com.xunlei.downloadprovider.member.payment.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;

public class VoucherItemLayout extends RelativeLayout implements Checkable {
    TextView a;
    CheckBox b;
    TextView c;
    int d;
    ProgressBar e;
    private boolean f;

    public VoucherItemLayout(Context context) {
        super(context);
    }

    public VoucherItemLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VoucherItemLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (TextView) findViewById(R.id.pay_voucher_item_tv);
        this.b = (CheckBox) findViewById(R.id.pay_voucher_item_select_cb);
        this.c = (TextView) findViewById(R.id.pay_voucher_item_lock_tv);
        this.c.setVisibility(isEnabled() ? 8 : 0);
        this.e = (ProgressBar) findViewById(R.id.loading_pb);
        this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }

    public void setChecked(boolean z) {
        this.f = z;
        this.b.setChecked(this.f);
    }

    public boolean isChecked() {
        return this.f;
    }

    public void toggle() {
        setChecked(!this.f);
    }
}
