package com.xunlei.downloadprovider.download.tasklist.list.xzb;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.XZBWebviewActivity;

// compiled from: NoBindXZBDeviceDialog.java
public final class b extends XLBaseDialog implements OnClickListener {
    protected String a;
    private TextView b;
    private TextView c;
    private SaveToXZBEntry d;

    public b(Context context, SaveToXZBEntry saveToXZBEntry) {
        super(context, 2131427871);
        this.a = getClass().getSimpleName();
        setContentView(2130968883);
        setCanceledOnTouchOutside(true);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        this.b = (TextView) findViewById(2131756673);
        this.c = (TextView) findViewById(2131756672);
        this.b.setOnClickListener(this);
        this.c.setOnClickListener(this);
        this.d = saveToXZBEntry;
    }

    public final void onClick(View view) {
        dismiss();
        switch (view.getId()) {
            case 2131756672:
                dismiss();
            case 2131756673:
                if (!this.d.equals(SaveToXZBEntry.other)) {
                    XZBReporter.a(this.d);
                }
                XZBWebviewActivity.a(getContext(), "v_an_shoulei_store");
            default:
                break;
        }
    }
}
