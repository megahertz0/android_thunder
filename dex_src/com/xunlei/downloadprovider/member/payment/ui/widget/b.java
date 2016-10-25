package com.xunlei.downloadprovider.member.payment.ui.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.member.payment.ui.a.j;
import com.xunlei.downloadprovider.member.payment.ui.widget.b.c;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.android.spdy.SpdyProtocol;

// compiled from: VoucherListDialog.java
public final class b extends Dialog {
    public ListView a;
    public b b;
    public a c;
    public List<c> d;
    public HashSet<String> e;

    // compiled from: VoucherListDialog.java
    public static interface a {
        void a();

        void a(c cVar);
    }

    // compiled from: VoucherListDialog.java
    private static class b extends com.xunlei.downloadprovider.member.payment.ui.a.a<c> {
        HashSet<String> d;

        protected final /* synthetic */ void a(Object obj, j jVar) {
            boolean z = true;
            c cVar = (c) obj;
            VoucherItemLayout voucherItemLayout = (VoucherItemLayout) jVar.a(R.id.pay_voucher_item_layout);
            int i = cVar.a;
            if (cVar.c != 0) {
                boolean z2 = true;
            } else {
                int i2 = 0;
            }
            voucherItemLayout.d = i;
            voucherItemLayout.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            voucherItemLayout.b.setVisibility(0);
            if (z2) {
                voucherItemLayout.a.setText(Html.fromHtml(String.format(voucherItemLayout.getResources().getString(R.string.pay_voucher_format_item_lock), new Object[]{Integer.valueOf(voucherItemLayout.d)})));
            } else {
                voucherItemLayout.a.setText(Html.fromHtml(String.format(voucherItemLayout.getResources().getString(R.string.pay_voucher_format_item), new Object[]{Integer.valueOf(voucherItemLayout.d)})));
            }
            TextView textView = voucherItemLayout.c;
            if (z2) {
                i = 0;
            } else {
                i = 8;
            }
            textView.setVisibility(i);
            if (z2) {
                z = false;
            }
            voucherItemLayout.setEnabled(z);
            if (this.d.contains(cVar.b)) {
                voucherItemLayout.e.setVisibility(0);
                voucherItemLayout.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                voucherItemLayout.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                return;
            }
            voucherItemLayout.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            voucherItemLayout.b.setVisibility(0);
        }

        public b(Context context) {
            super(context);
            this.d = new HashSet();
        }

        protected final int a() {
            return R.layout.pay_voucher_item;
        }
    }

    public b(Context context) {
        super(context, 2131427596);
        this.d = new ArrayList();
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.pay_voucher_list_dlg);
        this.a = (ListView) findViewById(R.id.pay_voucher_lv);
        this.b = new b(getContext());
        this.b.a(this.d);
        this.a.setAdapter(this.b);
        findViewById(R.id.pay_voucher_cancel_tv).setOnClickListener(new d(this));
        this.a.setChoiceMode(1);
        this.a.setOnItemClickListener(new e(this));
    }

    public final void show() {
        setCanceledOnTouchOutside(false);
        super.show();
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        attributes.gravity = 80;
        window.setAttributes(attributes);
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getRepeatCount() != 0) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.c != null) {
            this.c.a();
        }
        dismiss();
        return true;
    }
}
