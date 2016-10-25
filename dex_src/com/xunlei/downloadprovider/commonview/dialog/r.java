package com.xunlei.downloadprovider.commonview.dialog;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.umeng.message.proguard.j;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.tdlive.im.ChatMessage;

// compiled from: XLProgressDialog.java
public final class r extends d {
    private static int i;
    private long j;
    private long k;
    private int l;
    private ProgressBar m;
    private TextView n;
    private TextView o;
    private Handler p;

    static {
        i = 1000;
    }

    public r(Context context) {
        super(context, R.style.bt_dialog);
        this.p = new s(this);
        a(0);
        b(0);
    }

    public final void a(long j) {
        new StringBuilder("setMax(").append(j).append(j.t);
        this.j = j;
    }

    public final void b(long j) {
        int i = ChatMessage.FLAG_SYS_NOTIFY;
        Object obj = null;
        long j2 = this.j;
        if (j2 == 0) {
            i = 0;
        } else {
            int i2;
            int i3 = (int) ((((float) j) / ((float) j2)) * 1000.0f);
            if (i3 >= 0) {
                i2 = i3;
            }
            if (i2 <= 1000) {
                i = i2;
            }
        }
        this.k = j;
        this.l = i;
        this.p.sendEmptyMessage(i);
    }

    protected final void a(Context context) {
        this.a = LayoutInflater.from(context).inflate(R.layout.xl_progress_dialog, null);
        super.a(context);
        this.o = (TextView) this.a.findViewById(R.id.xl_dlg_pro_state);
        this.n = (TextView) this.a.findViewById(R.id.xl_dlg_pro_percentage);
        this.m = (ProgressBar) this.a.findViewById(R.id.xl_dlg_pro);
        this.m.setMax(ChatMessage.FLAG_SYS_NOTIFY);
        setContentView(this.a);
    }
}
