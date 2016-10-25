package com.xunlei.downloadprovider.personal.settings.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class SDCardViewHolder extends FrameLayout {
    public View a;
    public TextView b;
    public TextView c;
    public ImageView d;
    public ProgressBar e;
    public TextView f;
    public TextView g;
    public TextView h;
    public int i;
    String j;
    public boolean k;
    public boolean l;
    private a m;
    private boolean n;
    private boolean o;
    private String p;
    private Context q;
    private String r;
    private boolean s;
    private View t;

    public static interface a {
        void a(boolean z);
    }

    public SDCardViewHolder(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.n = false;
        this.r = getClass().getSimpleName();
        this.s = true;
        this.j = null;
        this.k = false;
        this.l = true;
        this.q = context;
    }

    public SDCardViewHolder(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = false;
        this.r = getClass().getSimpleName();
        this.s = true;
        this.j = null;
        this.k = false;
        this.l = true;
        this.q = context;
    }

    public SDCardViewHolder(Context context) {
        super(context);
        this.n = false;
        this.r = getClass().getSimpleName();
        this.s = true;
        this.j = null;
        this.k = false;
        this.l = true;
        this.q = context;
    }

    public final void a(String str, int i, boolean z) {
        if (this.a == null) {
            this.a = ((LayoutInflater) this.q.getSystemService("layout_inflater")).inflate(2130968651, null);
            addView(this.a, new LayoutParams(-1, -2));
        }
        this.p = str;
        this.o = z;
        this.n = z;
        this.i = i;
        this.d = (ImageView) this.a.findViewById(2131755442);
        this.b = (TextView) this.a.findViewById(2131755443);
        this.c = (TextView) this.a.findViewById(2131755444);
        this.e = (ProgressBar) this.a.findViewById(2131755445);
        this.f = (TextView) this.a.findViewById(2131755447);
        this.g = (TextView) this.a.findViewById(2131755449);
        this.h = (TextView) this.a.findViewById(2131755450);
        this.t = this.a.findViewById(2131755451);
        if (this.i == 1) {
            this.b.setText(2131232644);
            this.j = k.b();
        } else if (this.i == 2) {
            this.b.setText(2131232643);
            this.j = k.c();
        } else {
            return;
        }
        this.h.setText(this.p);
        if (TextUtils.isEmpty(this.p)) {
            this.a.setVisibility(XZBDevice.Wait);
        } else {
            long b = k.b(this.p);
            long a = k.a(this.p);
            long j = a - b;
            setUsedSize(j);
            setFreeSize(b);
            int i2 = (int) ((((double) j) / ((double) a)) * 10000.0d);
            this.e.setIndeterminate(false);
            this.e.setMax(10000);
            this.e.setProgress(i2);
        }
        if (this.l) {
            this.a.findViewById(2131755452).setVisibility(0);
        } else {
            this.a.findViewById(2131755452).setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        }
        setSelected(this.o);
        this.a.setOnClickListener(new a(this));
    }

    public void setSDCardPath(String str) {
        this.p = str;
        this.h.setText(new StringBuilder("\u5f53\u524d:").append(this.p).toString());
    }

    public void setSelected(boolean z) {
        this.o = z;
        this.d.setSelected(this.o);
        if (z) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(XZBDevice.Wait);
        }
        if (!this.k) {
            return;
        }
        if (z) {
            this.t.setVisibility(0);
        } else {
            this.t.setVisibility(XZBDevice.Wait);
        }
    }

    public boolean isSelected() {
        return this.o;
    }

    public void setAllowChoosePath(boolean z) {
        this.s = z;
    }

    public void setUsedSize(long j) {
        this.f.setText(String.format(this.q.getString(2131232466), new Object[]{com.xunlei.downloadprovider.d.a.a(j, 1)}));
    }

    public void setFreeSize(long j) {
        this.g.setText(String.format(this.q.getString(2131232463), new Object[]{com.xunlei.downloadprovider.d.a.a(j, 1)}));
    }

    public final void a() {
        String substring = this.p.substring(this.j.length());
        getContext();
        com.xunlei.downloadprovider.businessutil.a.a(this.i, substring);
        d.a();
        d.a(this.p);
        substring = "primarySDCard";
        if (this.i == 2) {
            substring = "slaverSDCard";
        }
        if (!this.n && this.o) {
            StatReporter.reportClick(5006, substring, this.p);
        }
    }

    public void setOnSelecteChangedListener(a aVar) {
        this.m = aVar;
    }
}
