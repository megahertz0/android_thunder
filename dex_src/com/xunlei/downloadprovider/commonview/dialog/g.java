package com.xunlei.downloadprovider.commonview.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.xunlei.downloadprovider.commonview.RoundProgressBar;
import com.xunlei.downloadprovidercommon.R;

@Deprecated
// compiled from: XLBatchDeleteRPbarDialog.java
public final class g extends XLBaseDialog {
    public TextView a;
    private Context b;
    private RoundProgressBar c;

    public g(Context context) {
        super(context, R.style.bt_dialog);
        this.b = context.getApplicationContext();
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.xl_batch_delete_round_progress_dialog, null);
        this.a = (TextView) inflate.findViewById(R.id.dlg_pro_hint);
        this.c = (RoundProgressBar) inflate.findViewById(R.id.dlg_pro);
        setContentView(inflate);
    }

    public final void a(String str) {
        if (str != null) {
            this.a.setText(str);
        }
    }

    public final void setTitle(int i) {
        a(this.b.getResources().getString(i));
    }

    public final void a(long j) {
        this.c.setMax(j);
    }

    public final void b(long j) {
        this.c.setProgress(j);
    }
}
