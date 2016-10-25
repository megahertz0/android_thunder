package com.xunlei.downloadprovider.homepage.relax.d;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.c;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.model.protocol.b.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

// compiled from: RelaxItemAdaper.java
public final class a extends BaseAdapter {
    public a a;
    public c b;
    public b c;
    private Context d;
    private List<d> e;
    private List<Long> f;

    public final /* synthetic */ Object getItem(int i) {
        return a(i);
    }

    public a(Context context, List<d> list) {
        this.f = new ArrayList();
        this.d = context;
        this.e = list;
    }

    public final int getCount() {
        return this.e == null ? 0 : this.e.size();
    }

    private d a(int i) {
        return (d) this.e.get(i);
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        d a = a(i);
        if (view == null) {
            view = LayoutInflater.from(this.d).inflate(2130968929, null);
            bVar = new b(this, (byte) 0);
            bVar.a = view.findViewById(2131756804);
            bVar.b = (TextView) view.findViewById(2131756805);
            bVar.c = view.findViewById(2131756806);
            bVar.d = (ImageView) view.findViewById(2131756807);
            bVar.e = (ImageView) view.findViewById(2131756808);
            bVar.f = view.findViewById(2131756426);
            bVar.g = (TextView) view.findViewById(2131756809);
            bVar.h = view.findViewById(2131755854);
            bVar.i = (TextView) view.findViewById(2131756810);
            bVar.n = (ImageView) view.findViewById(2131756813);
            bVar.o = (TextView) view.findViewById(2131756817);
            bVar.p = (ImageView) view.findViewById(2131756820);
            bVar.q = (TextView) view.findViewById(2131756821);
            bVar.j = view.findViewById(2131756812);
            bVar.k = view.findViewById(2131756815);
            bVar.l = view.findViewById(2131756819);
            bVar.m = view.findViewById(2131756818);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        if (a.g == 0) {
            if (TextUtils.isEmpty(a.i)) {
                bVar.b.setVisibility(XZBDevice.Wait);
            } else {
                bVar.b.setVisibility(0);
                bVar.b.setText(a.i);
            }
            com.nostra13.universalimageloader.core.d.a().a(a.d, bVar.d, this.b);
            bVar.b.setVisibility(0);
            bVar.c.setVisibility(0);
            bVar.d.setVisibility(0);
            if (a.a() == 1) {
                bVar.e.setImageDrawable(BrothersApplication.a().getResources().getDrawable(2130838289));
                bVar.e.setVisibility(0);
            } else {
                bVar.e.setVisibility(XZBDevice.Wait);
            }
        }
        if (a.r == null) {
            bVar.l.setEnabled(false);
            bVar.m.setEnabled(false);
            bVar.q.setText(BrothersApplication.a.getString(2131232325));
        } else {
            bVar.l.setEnabled(true);
            bVar.m.setEnabled(true);
            if (a.r.f == 1 || this.f.contains(Long.valueOf(a.a))) {
                bVar.l.setClickable(false);
                bVar.m.setClickable(false);
                bVar.p.setSelected(true);
                bVar.q.setSelected(true);
            } else {
                bVar.l.setClickable(true);
                bVar.m.setClickable(true);
                bVar.p.setSelected(false);
                bVar.q.setSelected(false);
            }
            if (a.r.b == 0) {
                bVar.o.setText(BrothersApplication.a.getString(2131232326));
            } else {
                bVar.o.setText(BrothersApplication.a.getString(2131232326) + d.b(a.r.b));
            }
            if (a.r.d == 0) {
                bVar.q.setText(BrothersApplication.a.getString(2131232325));
            } else {
                bVar.q.setText(BrothersApplication.a.getString(2131232325) + d.b(a.r.d));
            }
            if (a.r.g == null || a.r.g.size() == 0) {
                bVar.f.setVisibility(XZBDevice.Wait);
                bVar.h.setVisibility(XZBDevice.Wait);
                bVar.g.setVisibility(XZBDevice.Wait);
                bVar.i.setVisibility(XZBDevice.Wait);
            } else if (a.r.g.size() == 1) {
                bVar.g.setVisibility(0);
                bVar.i.setVisibility(XZBDevice.Wait);
                bVar.f.setVisibility(0);
                bVar.h.setVisibility(XZBDevice.Wait);
                r0 = (com.xunlei.downloadprovider.model.protocol.c.a.a.a) a.r.g.get(0);
                bVar.g.setText(String.format("[%s]:%s", new Object[]{r0.a, r0.d}));
            } else if (a.r.g.size() > 1) {
                bVar.f.setVisibility(0);
                bVar.g.setVisibility(0);
                bVar.h.setVisibility(0);
                bVar.i.setVisibility(0);
                r0 = (com.xunlei.downloadprovider.model.protocol.c.a.a.a) a.r.g.get(0);
                bVar.g.setText(String.format("[%s]:%s", new Object[]{r0.a, r0.d}));
                r0 = (com.xunlei.downloadprovider.model.protocol.c.a.a.a) a.r.g.get(1);
                bVar.i.setText(String.format("[%s]:%s", new Object[]{r0.a, r0.d}));
            }
        }
        if (a.g == 0) {
            bVar.d.setOnClickListener(new b(this, bVar, a));
        }
        bVar.a.setOnClickListener(new c(this, bVar, a));
        bVar.j.setOnClickListener(new d(this, bVar, a));
        bVar.k.setOnClickListener(new e(this, bVar, a));
        bVar.l.setOnClickListener(new f(this, bVar, a));
        bVar.m.setOnClickListener(new g(this, bVar, a));
        LayoutParams layoutParams;
        if (getItemId(0) == ((long) i)) {
            layoutParams = (LayoutParams) bVar.a.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, 0);
            bVar.a.setLayoutParams(layoutParams);
        } else {
            layoutParams = (LayoutParams) bVar.a.getLayoutParams();
            layoutParams.setMargins(0, g.a(this.d, 9.0f), 0, 0);
            bVar.a.setLayoutParams(layoutParams);
        }
        return view;
    }

    static /* synthetic */ void a(a aVar, b bVar, d dVar) {
        aVar.c = bVar;
        if (!aVar.f.contains(Long.valueOf(dVar.a)) && dVar.r.f != 1 && aVar.a.b(dVar)) {
            dVar.r.d++;
            bVar.q.setText(BrothersApplication.a.getString(2131232325) + d.b(dVar.r.d));
            aVar.f.add(Long.valueOf(dVar.a));
        }
    }
}
