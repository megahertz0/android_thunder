package com.xunlei.downloadprovider.web.record;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.web.record.aa.b;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecordPageView extends RelativeLayout {
    String a;
    long b;
    List<t> c;
    List<t> d;
    Context e;
    aa f;
    b g;
    private boolean h;
    private boolean i;
    private BaseAdapter j;
    private ListView k;
    private ViewGroup l;
    private Handler m;
    private a n;
    private d o;
    private TextView p;
    private boolean q;

    static interface a {
        void a();

        void a(int i);

        void a(String str);

        void a(boolean z);
    }

    public static interface d {
        void a(int i);

        void a(long j, boolean z, int i);
    }

    public RecordPageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = "favor";
        this.h = false;
        this.b = 0;
        this.i = false;
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.k = null;
        this.e = null;
        this.n = null;
        this.o = null;
        this.f = null;
        this.q = false;
        this.g = new x(this);
        e();
    }

    public RecordPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = "favor";
        this.h = false;
        this.b = 0;
        this.i = false;
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.k = null;
        this.e = null;
        this.n = null;
        this.o = null;
        this.f = null;
        this.q = false;
        this.g = new x(this);
        e();
    }

    public RecordPageView(Context context) {
        super(context);
        this.a = "favor";
        this.h = false;
        this.b = 0;
        this.i = false;
        this.c = new ArrayList();
        this.d = new ArrayList();
        this.k = null;
        this.e = null;
        this.n = null;
        this.o = null;
        this.f = null;
        this.q = false;
        this.g = new x(this);
        e();
    }

    public void setTabType(String str) {
        this.a = str;
        this.p.setText(getEmptyHint());
    }

    public String getTabType() {
        return this.a;
    }

    public void setDelCallback(a aVar) {
        this.n = aVar;
    }

    private String getEmptyHint() {
        return "favor".equals(this.a) ? "\u6682\u65e0\u6536\u85cf" : "\u6682\u65e0\u5386\u53f2";
    }

    private final void e() {
        ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(2130968925, this);
        this.k = (ListView) findViewById(2131756788);
        View findViewById = findViewById(2131756789);
        this.p = (TextView) findViewById(2131755867);
        this.k.setEmptyView(findViewById);
        this.j = new b(this, (byte) 0);
        this.k.setAdapter(this.j);
        this.l = (ViewGroup) findViewById(2131756787);
        this.l.setOnClickListener(new u(this));
        this.k.setOnScrollListener(new v(this));
        this.m = new c(this, (byte) 0);
    }

    final void b() {
        if (this.n != null) {
            this.n.a(this.h);
        }
    }

    final void c() {
        Iterator it = this.c.iterator();
        while (it.hasNext()) {
            if (((t) it.next()).a) {
                it.remove();
            }
        }
        this.j.notifyDataSetChanged();
    }

    final void a(boolean z, t tVar) {
        this.h = z;
        f();
        if (z && tVar != null) {
            tVar.a = true;
            this.d.add(tVar);
        }
        if (this.h) {
            this.l.setVisibility(0);
        } else {
            this.l.setVisibility(XZBDevice.Wait);
        }
        this.j.notifyDataSetChanged();
    }

    private void f() {
        this.d.clear();
        for (t tVar : this.c) {
            tVar.a = false;
        }
    }

    public int getSelectedSize() {
        return this.d.size();
    }

    public final boolean d() {
        return this.d.size() >= this.c.size();
    }

    public final void a(boolean z) {
        if (z) {
            this.d.clear();
            this.d.addAll(this.c);
            for (t tVar : this.c) {
                tVar.a = true;
            }
        } else {
            f();
        }
        this.j.notifyDataSetChanged();
    }

    public void setIsDestry(boolean z) {
        this.i = z;
        if (this.f != null) {
            this.f.e = z;
        }
    }

    public void setUpdateCallback(d dVar) {
        this.o = dVar;
    }

    public List<t> getLocal() {
        return this.c;
    }

    public final void a() {
        new w(this).start();
    }

    static /* synthetic */ void o(RecordPageView recordPageView) {
        if (recordPageView.n != null) {
            recordPageView.n.a();
        }
    }
}
