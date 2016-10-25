package com.xunlei.downloadprovider.member.payment.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.xunlei.downloadprovider.R;
import org.apache.commons.logging.impl.SimpleLog;

public class PaymentTypeView extends LinearLayout implements OnClickListener {
    private CheckBox a;
    private CheckBox b;
    private RelativeLayout c;
    private RelativeLayout d;
    private a e;

    public static interface a {
        void a(int i);
    }

    public PaymentTypeView(Context context) {
        super(context);
    }

    public PaymentTypeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PaymentTypeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (CheckBox) findViewById(R.id.allipay_select_cb);
        this.b = (CheckBox) findViewById(R.id.wxpay_select_cb);
        this.c = (RelativeLayout) findViewById(R.id.alipay_layout);
        this.c.setOnClickListener(this);
        this.d = (RelativeLayout) findViewById(R.id.wxpay_layout);
        this.d.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.wxpay_layout:
                this.b.setChecked(true);
                this.a.setChecked(false);
                if (this.e != null) {
                    this.e.a(SimpleLog.LOG_LEVEL_DEBUG);
                }
            case R.id.alipay_layout:
                this.b.setChecked(false);
                this.a.setChecked(true);
                if (this.e != null) {
                    this.e.a(1);
                }
            default:
                break;
        }
    }

    public void setDefaultSelectType(int i) {
        if (1 == i) {
            this.b.setChecked(false);
            this.a.setChecked(true);
            return;
        }
        this.b.setChecked(true);
        this.a.setChecked(false);
    }

    public void setOnPaymentTypeSelectListener(a aVar) {
        this.e = aVar;
    }
}
