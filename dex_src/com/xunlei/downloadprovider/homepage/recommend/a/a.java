package com.xunlei.downloadprovider.homepage.recommend.a;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.recommend.ShortTimeVideoListActivity;
import com.xunlei.downloadprovider.homepage.recommend.feed.ao;
import com.xunlei.downloadprovider.model.protocol.e.a.c;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.Iterator;

// compiled from: ShortTimeVideoListAdapter.java
public final class a extends BaseAdapter {
    public ArrayList a;
    public boolean b;
    public int c;
    public boolean d;
    public Context e;
    public a f;
    public ListView g;
    public boolean h;
    com.xunlei.downloadprovider.a.h.a i;
    public long j;
    public b k;
    private ArrayList l;
    private boolean m;
    private boolean n;
    private Object o;
    private com.xunlei.downloadprovider.homepage.a p;
    private boolean q;

    // compiled from: ShortTimeVideoListAdapter.java
    public static class a {
        public int a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public int g;
        public String h;
        public String i;
    }

    static /* synthetic */ void a(a aVar, Message message) {
        aVar.d = false;
        com.xunlei.downloadprovider.model.protocol.e.a.b bVar = (com.xunlei.downloadprovider.model.protocol.e.a.b) message.obj;
        if (bVar.c != 0) {
            aVar.a(true, bVar.b);
            return;
        }
        ArrayList arrayList = bVar.a;
        aVar.f.e = bVar.d;
        aVar.f.d = bVar.g;
        aVar.f.g = bVar.i;
        aVar.f.h = bVar.j;
        aVar.f.i = bVar.k;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            cVar.j = bVar.g;
            cVar.k = bVar.e;
            cVar.u = bVar.i;
            cVar.v = bVar.j;
            cVar.w = bVar.k;
            cVar.x = bVar.d;
        }
        if (bVar.b == 1 || bVar.b == 2) {
            aVar.l.remove(aVar.o);
            aVar.a.remove(aVar.o);
            it = arrayList.iterator();
            while (it.hasNext()) {
                cVar = (c) it.next();
                cVar.h = aVar.f.a;
                aVar.l.add(cVar);
                aVar.a(cVar);
            }
            aVar.a(arrayList, false);
            if (arrayList.size() < aVar.c) {
                aVar.b = false;
            } else {
                aVar.b = true;
                aVar.l.add(aVar.o);
                aVar.a.add(aVar.o);
            }
            if (arrayList.size() > 0) {
                aVar.notifyDataSetChanged();
            }
        } else {
            a aVar2 = (a) aVar.l.get(0);
            aVar.l.clear();
            aVar.a.clear();
            aVar.l.add(aVar2);
            aVar.a.add(aVar2);
            it = arrayList.iterator();
            while (it.hasNext()) {
                cVar = (c) it.next();
                cVar.h = aVar.f.a;
                aVar.l.add(cVar);
                aVar.a(cVar);
            }
            aVar.a(arrayList, true);
            if (arrayList.size() < aVar.c) {
                aVar.b = false;
            } else {
                aVar.b = true;
                aVar.l.add(aVar.o);
                aVar.a.add(aVar.o);
            }
            if (arrayList.size() > 0) {
                aVar.notifyDataSetChanged();
            }
        }
        aVar.a(false, bVar.b);
    }

    private void a(c cVar) {
        ao aoVar = new ao();
        aoVar.d = cVar.e;
        aoVar.l = cVar.j;
        aoVar.c = cVar.i;
        aoVar.b = cVar.c;
        aoVar.a = cVar.a;
        aoVar.q = cVar.b;
        aoVar.n = cVar.k;
        aoVar.i = cVar.l;
        aoVar.h = cVar.g;
        aoVar.f = cVar.n;
        aoVar.j = cVar.o;
        aoVar.k = cVar.p;
        aoVar.e = cVar.q;
        aoVar.a(cVar.r);
        aoVar.z = cVar.t;
        aoVar.y = cVar.s;
        this.a.add(aoVar);
    }

    private void a(boolean z, int i) {
        int count;
        if (!(getCount() <= 1 || com.xunlei.xllib.a.b.a(this.e) || i == 2)) {
            Toast toast = new Toast(this.e);
            View inflate = LayoutInflater.from(this.e).inflate(2130968875, null);
            toast.setGravity(R.styleable.Toolbar_maxButtonHeight, 0, 0);
            toast.setView(inflate);
            toast.setDuration(0);
            toast.show();
        }
        ShortTimeVideoListActivity shortTimeVideoListActivity = (ShortTimeVideoListActivity) this.e;
        boolean z2 = this.n;
        shortTimeVideoListActivity.b.m();
        if (!(shortTimeVideoListActivity.c == null || shortTimeVideoListActivity.c.b)) {
            shortTimeVideoListActivity.b.setMode(Mode.PULL_FROM_START);
        }
        if (shortTimeVideoListActivity.c != null) {
            count = shortTimeVideoListActivity.c.getCount();
        } else {
            count = 0;
        }
        if (!z2) {
            shortTimeVideoListActivity.e.b();
            if (count <= 1) {
                if (z) {
                    shortTimeVideoListActivity.a();
                } else {
                    shortTimeVideoListActivity.g.setVisibility(XZBDevice.Wait);
                    shortTimeVideoListActivity.f.setText(shortTimeVideoListActivity.getResources().getString(2131232582));
                    shortTimeVideoListActivity.h.setVisibility(XZBDevice.Wait);
                    shortTimeVideoListActivity.i.setImageResource(R.drawable.bg_page_empty);
                }
                shortTimeVideoListActivity.j.setVisibility(0);
                return;
            }
        } else if (count > 1) {
            shortTimeVideoListActivity.e.b();
        } else {
            shortTimeVideoListActivity.a();
            return;
        }
        shortTimeVideoListActivity.j.setVisibility(XZBDevice.Wait);
    }

    private void a() {
        ShortTimeVideoListActivity shortTimeVideoListActivity = (ShortTimeVideoListActivity) this.e;
        boolean z = this.d;
        int i = 0;
        if (shortTimeVideoListActivity.c != null) {
            i = shortTimeVideoListActivity.c.getCount();
        }
        if (!z) {
            shortTimeVideoListActivity.e.b();
            if (i <= 1) {
                shortTimeVideoListActivity.a();
            } else {
                shortTimeVideoListActivity.j.setVisibility(XZBDevice.Wait);
            }
        }
    }

    public a(Context context, a aVar, ListView listView, com.xunlei.downloadprovider.homepage.a aVar2) {
        this.b = true;
        this.m = true;
        this.c = 20;
        this.o = new Object();
        this.i = new b(this);
        this.k = new b(this.i);
        this.e = context;
        this.l = new ArrayList();
        this.a = new ArrayList();
        this.f = aVar;
        this.f.g = -1;
        this.g = listView;
        this.p = aVar2;
        this.l.add(aVar);
        this.a.add(aVar);
        notifyDataSetChanged();
        a(0);
    }

    private boolean b() {
        return d.a(this.l) ? false : this.o.equals(this.l.get(this.l.size() - 1));
    }

    public final int getCount() {
        return this.l.size();
    }

    public final Object getItem(int i) {
        return this.l.get(i);
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final int getViewTypeCount() {
        return XZBDevice.DOWNLOAD_LIST_FAILED;
    }

    public final int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        return (b() && i == this.l.size() - 1) ? XZBDevice.DOWNLOAD_LIST_RECYCLE : 1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View getView(int r12, android.view.View r13, android.view.ViewGroup r14) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.homepage.recommend.a.a.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
        /*
        this = this;
        r0 = r11.getItemViewType(r12);
        switch(r0) {
            case 0: goto L_0x00bb;
            case 1: goto L_0x01cd;
            case 2: goto L_0x0586;
            default: goto L_0x0007;
        };
    L_0x0007:
        if (r13 != 0) goto L_0x0053;
    L_0x0009:
        r0 = r11.e;
        r0 = android.view.LayoutInflater.from(r0);
        r1 = 2130968977; // 0x7f040191 float:1.7546623E38 double:1.052838564E-314;
        r2 = 0;
        r13 = r0.inflate(r1, r2);
        r0 = new com.xunlei.downloadprovider.homepage.recommend.a.d;
        r0.<init>(r11);
        r13.setOnClickListener(r0);
        r1 = new com.xunlei.downloadprovider.homepage.recommend.a.a$b;
        r1.<init>();
        r0 = 2131756981; // 0x7f1007b5 float:1.9144885E38 double:1.0532278896E-314;
        r0 = r13.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1.a = r0;
        r0 = 2131756984; // 0x7f1007b8 float:1.914489E38 double:1.053227891E-314;
        r0 = r13.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1.b = r0;
        r0 = 2131756980; // 0x7f1007b4 float:1.9144883E38 double:1.053227889E-314;
        r0 = r13.findViewById(r0);
        r0 = (android.widget.ImageView) r0;
        r1.c = r0;
        r0 = 2131756982; // 0x7f1007b6 float:1.9144887E38 double:1.05322789E-314;
        r0 = r13.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1.d = r0;
        r13.setTag(r1);
    L_0x0053:
        r0 = r13.getTag();
        r0 = (com.xunlei.downloadprovider.homepage.recommend.a.a.b) r0;
        r1 = r11.getItem(r12);
        r1 = (com.xunlei.downloadprovider.model.protocol.e.a.c) r1;
        r2 = r0.d;
        r3 = r1.c;
        r2.setText(r3);
        r2 = r1.g;
        if (r2 != 0) goto L_0x0598;
    L_0x006a:
        r2 = r0.b;
        r3 = 8;
        r2.setVisibility(r3);
    L_0x0071:
        r2 = r0.a;
        r3 = r1.f;
        r2.setText(r3);
        r2 = r1.e;
        r3 = r0.c;
        r3 = r3.getTag();
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x00ba;
    L_0x0086:
        r2 = r0.c;
        r3 = 2130839160; // 0x7f020678 float:1.7283323E38 double:1.052774426E-314;
        r2.setImageResource(r3);
        r2 = r0.c;
        r3 = r1.e;
        r2.setTag(r3);
        r2 = new com.nostra13.universalimageloader.core.c$a;
        r2.<init>();
        r3 = 2130839160; // 0x7f020678 float:1.7283323E38 double:1.052774426E-314;
        r2.a = r3;
        r3 = 1;
        r2.h = r3;
        r2 = r2.a();
        r2 = r2.b();
        r3 = com.nostra13.universalimageloader.core.d.a();
        r1 = r1.e;
        r0 = r0.c;
        r4 = new com.xunlei.downloadprovider.homepage.recommend.a.e;
        r4.<init>(r11);
        r3.a(r1, r0, r2, r4);
    L_0x00ba:
        return r13;
    L_0x00bb:
        if (r13 != 0) goto L_0x01ad;
    L_0x00bd:
        r0 = r11.e;
        r0 = android.view.LayoutInflater.from(r0);
        r1 = 2130968975; // 0x7f04018f float:1.7546619E38 double:1.052838563E-314;
        r2 = 0;
        r13 = r0.inflate(r1, r2);
        r0 = 2131756972; // 0x7f1007ac float:1.9144867E38 double:1.053227885E-314;
        r0 = r13.findViewById(r0);
        r0 = (android.widget.ImageView) r0;
        r1 = 2131756971; // 0x7f1007ab float:1.9144865E38 double:1.0532278847E-314;
        r1 = r13.findViewById(r1);
        r1 = (android.widget.TextView) r1;
        r2 = new com.nostra13.universalimageloader.core.c$a;
        r2.<init>();
        r3 = 2130838201; // 0x7f0202b9 float:1.7281378E38 double:1.052773952E-314;
        r2.a = r3;
        r3 = 2130838201; // 0x7f0202b9 float:1.7281378E38 double:1.052773952E-314;
        r2.b = r3;
        r3 = 2130838201; // 0x7f0202b9 float:1.7281378E38 double:1.052773952E-314;
        r2.c = r3;
        r3 = 1;
        r2.m = r3;
        r3 = 1;
        r2.h = r3;
        r2.a();
        r3 = new com.nostra13.universalimageloader.core.b.b;
        r3.<init>();
        r2.q = r3;
        r2 = r2.b();
        r3 = com.nostra13.universalimageloader.core.d.a();
        r4 = r11.f;
        r4 = r4.d;
        r5 = new com.xunlei.downloadprovider.homepage.recommend.a.c;
        r5.<init>(r11, r1);
        r3.a(r4, r0, r2, r5);
        r1 = new com.xunlei.downloadprovider.homepage.recommend.a.a$c;
        r1.<init>();
        r0 = 2131756974; // 0x7f1007ae float:1.914487E38 double:1.053227886E-314;
        r0 = r13.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1.a = r0;
        r0 = 2131756975; // 0x7f1007af float:1.9144873E38 double:1.0532278866E-314;
        r0 = r13.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1.b = r0;
        r0 = 2131756973; // 0x7f1007ad float:1.9144869E38 double:1.0532278856E-314;
        r0 = r13.findViewById(r0);
        r0 = (android.widget.ImageView) r0;
        r1.c = r0;
        r0 = 2131756976; // 0x7f1007b0 float:1.9144875E38 double:1.053227887E-314;
        r0 = r13.findViewById(r0);
        r0 = (android.widget.TextView) r0;
        r1.d = r0;
        r13.setTag(r1);
        r0 = r1;
    L_0x014a:
        r1 = r0.a;
        r2 = r11.f;
        r2 = r2.b;
        r1.setText(r2);
        r1 = r0.b;
        r2 = r11.f;
        r2 = r2.e;
        r1.setText(r2);
        r1 = r0.a;
        if (r1 == 0) goto L_0x0186;
    L_0x0160:
        r2 = r11.e;
        if (r2 == 0) goto L_0x0186;
    L_0x0164:
        r2 = r11.f;
        r2 = r2.g;
        if (r2 <= 0) goto L_0x0186;
    L_0x016a:
        r2 = r11.e;
        r3 = 2130838561; // 0x7f020421 float:1.7282108E38 double:1.05277413E-314;
        r2 = android.support.v4.content.ContextCompat.getDrawable(r2, r3);
        r3 = 0;
        r4 = 0;
        r5 = r2.getMinimumWidth();
        r6 = r2.getMinimumHeight();
        r2.setBounds(r3, r4, r5, r6);
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r1.setCompoundDrawables(r3, r4, r2, r5);
    L_0x0186:
        r1 = r11.f;
        r1 = r1.g;
        if (r1 <= 0) goto L_0x01bd;
    L_0x018c:
        r1 = r0.c;
        r2 = 0;
        r1.setVisibility(r2);
        r1 = r11.f;
        r1 = r1.i;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 == 0) goto L_0x01b4;
    L_0x019c:
        r1 = r0.d;
        r2 = 0;
        r1.setVisibility(r2);
        r0 = r0.d;
        r1 = r11.f;
        r1 = r1.i;
        r0.setText(r1);
        goto L_0x00ba;
    L_0x01ad:
        r0 = r13.getTag();
        r0 = (com.xunlei.downloadprovider.homepage.recommend.a.a.c) r0;
        goto L_0x014a;
    L_0x01b4:
        r0 = r0.d;
        r1 = 8;
        r0.setVisibility(r1);
        goto L_0x00ba;
    L_0x01bd:
        r1 = r0.c;
        r2 = 8;
        r1.setVisibility(r2);
        r0 = r0.d;
        r1 = 8;
        r0.setVisibility(r1);
        goto L_0x00ba;
    L_0x01cd:
        if (r13 != 0) goto L_0x05be;
    L_0x01cf:
        r7 = new com.xunlei.downloadprovider.homepage.recommend.feed.a;
        r0 = r11.e;
        r1 = r11.p;
        r7.<init>(r0, r1);
        r0 = new android.widget.AbsListView$LayoutParams;
        r1 = -1;
        r2 = -2;
        r0.<init>(r1, r2);
        r7.setLayoutParams(r0);
    L_0x01e2:
        r0 = r11.a;
        r0 = r0.get(r12);
        r0 = (com.xunlei.downloadprovider.homepage.recommend.feed.ao) r0;
        r6 = r7;
        r6 = (com.xunlei.downloadprovider.homepage.recommend.feed.a) r6;
        r1 = 0;
        r6.setIsFeedType(r1);
        r1 = java.lang.Integer.valueOf(r12);
        r6.setTag(r1);
        r1 = r11.q;
        if (r1 != 0) goto L_0x021a;
    L_0x01fc:
        r1 = 0;
        r2 = java.lang.System.currentTimeMillis();
        r4 = r11.j;
        r2 = r2 - r4;
        r4 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 > 0) goto L_0x020b;
    L_0x020a:
        r1 = 1;
    L_0x020b:
        r2 = r11.f;
        r2 = r2.f;
        r3 = r11.f;
        r3 = r3.a;
        r3 = java.lang.String.valueOf(r3);
        com.xunlei.downloadprovider.homepage.recommend.a.a(r1, r0, r2, r3);
    L_0x021a:
        r6.setShortTimeVideoListAdapter(r11);
        r6.t = r12;
        r1 = r6.g;
        r1.setFeedVideoItemModel(r0);
        r1 = r6.a;
        if (r1 == r0) goto L_0x0252;
    L_0x0228:
        r6.a = r0;
        r0 = com.nostra13.universalimageloader.core.d.a();
        r1 = r6.c;
        r0.a(r1);
        r0 = com.nostra13.universalimageloader.core.d.a();
        r1 = r6.o;
        r0.a(r1);
        r0 = r6.h;
        if (r0 == 0) goto L_0x024a;
    L_0x0240:
        r0 = r6.h;
        r0 = r0.e;
        r0.a();
        r0 = 0;
        r6.h = r0;
    L_0x024a:
        r0 = r6.b;
        r0.removeAllViews();
        r6.c();
    L_0x0252:
        r0 = com.xunlei.downloadprovider.homepage.recommend.c.c.a();
        r1 = r6.a;
        r1 = r1.a;
        r0 = r0.a(r1);
        if (r0 != 0) goto L_0x0271;
    L_0x0260:
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r0 = r6.a;
        r0 = r0.a;
        r0 = java.lang.String.valueOf(r0);
        r0 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.a(r0);
        if (r0 == 0) goto L_0x0289;
    L_0x0271:
        r0 = r6.a;
        r1 = 1;
        r0.e = r1;
        r0 = r6.a;
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r1 = r6.a;
        r1 = r1.a;
        r1 = java.lang.String.valueOf(r1);
        r1 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.b(r1);
        r0.h = r1;
    L_0x0289:
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r0 = r6.a;
        r0 = r0.a;
        r0 = java.lang.String.valueOf(r0);
        r0 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.d(r0);
        if (r0 == 0) goto L_0x02ad;
    L_0x029a:
        r0 = r6.a;
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r1 = r6.a;
        r1 = r1.a;
        r1 = java.lang.String.valueOf(r1);
        r1 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.c(r1);
        r0.w = r1;
    L_0x02ad:
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r0 = r6.a;
        r0 = r0.r;
        r0 = java.lang.String.valueOf(r0);
        r0 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.f(r0);
        if (r0 == 0) goto L_0x02d1;
    L_0x02be:
        r0 = r6.a;
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r1 = r6.a;
        r2 = r1.r;
        r1 = java.lang.String.valueOf(r2);
        r2 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.e(r1);
        r0.u = r2;
    L_0x02d1:
        r0 = r6.a;
        if (r0 != 0) goto L_0x0325;
    L_0x02d5:
        r0 = r6.d;
        r1 = "";
        r0.setText(r1);
        r0 = r6.e;
        r1 = "";
        r0.setText(r1);
        r0 = r6.r;
        r1 = "";
        r0.setText(r1);
        r0 = r6.f;
        r1 = "";
        r0.setText(r1);
        r0 = r6.i;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = r6.g;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x0303:
        if (r12 != 0) goto L_0x051f;
    L_0x0305:
        r0 = 1;
        r6.setIsFirstElement(r0);
    L_0x0309:
        r0 = r6.a;
        if (r0 != 0) goto L_0x0525;
    L_0x030d:
        r6.a();
    L_0x0310:
        r0 = r11.f;
        r0 = r0.g;
        r1 = r6.n;
        r1 = r1.getImgVthumb();
        if (r1 == 0) goto L_0x0322;
    L_0x031c:
        if (r0 <= 0) goto L_0x057f;
    L_0x031e:
        r0 = 0;
        r1.setVisibility(r0);
    L_0x0322:
        r13 = r7;
        goto L_0x00ba;
    L_0x0325:
        r0 = r6.n;
        r0 = r0.getCommentNumTextView();
        r1 = "";
        r0.setText(r1);
        r0 = r6.n;
        r0 = r0.getSubjectNameTextView();
        r1 = r6.a;
        r1 = r1.n;
        r0.setText(r1);
        r0 = r6.d;
        r1 = r6.a;
        r1 = r1.b;
        r0.setText(r1);
        r0 = r6.a;
        r0 = r0.z;
        if (r0 <= 0) goto L_0x03ad;
    L_0x034d:
        r0 = r6.d;
        r1 = r0.getPaint();	 Catch:{ Exception -> 0x05bb }
        r1 = r1.getFontMetrics();	 Catch:{ Exception -> 0x05bb }
        r2 = r1.bottom;	 Catch:{ Exception -> 0x05bb }
        r1 = r1.top;	 Catch:{ Exception -> 0x05bb }
        r1 = r2 - r1;
        r2 = r0.getText();	 Catch:{ Exception -> 0x05bb }
        r2 = r2.toString();	 Catch:{ Exception -> 0x05bb }
        r3 = new android.text.SpannableString;	 Catch:{ Exception -> 0x05bb }
        r3.<init>(r2);	 Catch:{ Exception -> 0x05bb }
        r4 = r0.getResources();	 Catch:{ Exception -> 0x05bb }
        r5 = 2130838562; // 0x7f020422 float:1.728211E38 double:1.0527741303E-314;
        r4 = r4.getDrawable(r5);	 Catch:{ Exception -> 0x05bb }
        r5 = 0;
        r8 = 0;
        r9 = r4.getIntrinsicWidth();	 Catch:{ Exception -> 0x05bb }
        r10 = r4.getIntrinsicHeight();	 Catch:{ Exception -> 0x05bb }
        r4.setBounds(r5, r8, r9, r10);	 Catch:{ Exception -> 0x05bb }
        r5 = new com.xunlei.downloadprovider.homepage.recommend.feed.bd;	 Catch:{ Exception -> 0x05bb }
        r5.<init>(r4, r1);	 Catch:{ Exception -> 0x05bb }
        r1 = 0;
        r4 = r2.length();	 Catch:{ Exception -> 0x05bb }
        r8 = 17;
        r3.setSpan(r5, r1, r4, r8);	 Catch:{ Exception -> 0x05bb }
        r1 = "";
        r0.setText(r1);	 Catch:{ Exception -> 0x05bb }
        r0.append(r3);	 Catch:{ Exception -> 0x05bb }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x05bb }
        r3 = "  ";
        r1.<init>(r3);	 Catch:{ Exception -> 0x05bb }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x05bb }
        r1 = r1.toString();	 Catch:{ Exception -> 0x05bb }
        r0.append(r1);	 Catch:{ Exception -> 0x05bb }
    L_0x03ad:
        r0 = r6.h;
        if (r0 != 0) goto L_0x049a;
    L_0x03b1:
        r0 = r6.a;
        r0 = r0.i;
        if (r0 != 0) goto L_0x046d;
    L_0x03b7:
        r0 = r6.f;
        r1 = "";
        r0.setText(r1);
    L_0x03bf:
        r0 = r6.e;
        r1 = r6.a;
        r1 = r1.f;
        r1 = com.xunlei.downloadprovider.player.u.a(r1);
        r0.setText(r1);
        r0 = r6.a;
        r0 = r0.y;
        r2 = 0;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 == 0) goto L_0x0490;
    L_0x03d6:
        r0 = r6.a;
        r0 = r0.y;
        r0 = com.xunlei.xllib.b.j.a(r0);
        r1 = r6.r;
        r1.setText(r0);
    L_0x03e3:
        r0 = r6.a;
        r0 = r0.w;
        if (r0 != 0) goto L_0x04b4;
    L_0x03e9:
        r0 = r6.n;
        r0 = r0.getCommentNumTextView();
        r1 = 8;
        r0.setVisibility(r1);
    L_0x03f4:
        r0 = r6.a;
        r0 = r0.h;
        r6.p = r0;
        r0 = r6.a;
        r0 = r0.e;
        if (r0 != 0) goto L_0x040c;
    L_0x0400:
        r0 = r6.q;
        r1 = r6.a;
        r1 = r1.a;
        r0 = r0.contains(r1);
        if (r0 == 0) goto L_0x04de;
    L_0x040c:
        r0 = r6.n;
        r0 = r0.getClickNiceTextView();
        r1 = 1;
        r0.setSelected(r1);
        r0 = r6.n;
        r0 = r0.getClickNiceImageView();
        r1 = 1;
        r0.setSelected(r1);
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r0 = r6.a;
        r0 = r0.a;
        r0 = java.lang.String.valueOf(r0);
        r0 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.b(r0);
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r1 = r6.a;
        r1 = r1.a;
        r1 = java.lang.String.valueOf(r1);
        r1 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.a(r1);
        if (r1 == 0) goto L_0x04cd;
    L_0x0440:
        r1 = r6.q;
        r2 = r6.a;
        r2 = r2.a;
        r1 = r1.contains(r2);
        if (r1 != 0) goto L_0x04cd;
    L_0x044c:
        r1 = r6.n;
        r1 = r1.getClickNiceTextView();
        r0 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r0);
        r1.setText(r0);
    L_0x0459:
        r0 = new java.lang.StringBuilder;
        r1 = "mFeedVideoItemModel.getMovieId() == ";
        r0.<init>(r1);
        r1 = r6.a;
        r1 = r1.a;
        r0.append(r1);
        r6.b();
        goto L_0x0303;
    L_0x046d:
        r0 = r6.f;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r6.a;
        r2 = r2.i;
        r2 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r2);
        r1 = r1.append(r2);
        r2 = "\u6b21\u64ad\u653e";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.setText(r1);
        goto L_0x03bf;
    L_0x0490:
        r0 = r6.r;
        r1 = "";
        r0.setText(r1);
        goto L_0x03e3;
    L_0x049a:
        r0 = r6.e;
        r1 = "";
        r0.setText(r1);
        r0 = r6.r;
        r1 = "";
        r0.setText(r1);
        r0 = r6.f;
        r1 = "";
        r0.setText(r1);
        goto L_0x03e3;
    L_0x04b4:
        r1 = r6.n;
        r1 = r1.getCommentNumTextView();
        r2 = 0;
        r1.setVisibility(r2);
        r1 = r6.n;
        r1 = r1.getCommentNumTextView();
        r0 = java.lang.String.valueOf(r0);
        r1.setText(r0);
        goto L_0x03f4;
    L_0x04cd:
        r0 = r6.n;
        r0 = r0.getClickNiceTextView();
        r1 = r6.p;
        r1 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r1);
        r0.setText(r1);
        goto L_0x0459;
    L_0x04de:
        r0 = r6.p;
        if (r0 != 0) goto L_0x04fa;
    L_0x04e2:
        r0 = r6.n;
        r0 = r0.getClickNiceTextView();
        r1 = "";
        r0.setText(r1);
        r0 = r6.n;
        r0 = r0.getClickNiceImageView();
        r1 = 0;
        r0.setSelected(r1);
        goto L_0x0459;
    L_0x04fa:
        r0 = r6.n;
        r0 = r0.getClickNiceTextView();
        r1 = r6.p;
        r1 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r1);
        r0.setText(r1);
        r0 = r6.n;
        r0 = r0.getClickNiceTextView();
        r1 = 0;
        r0.setSelected(r1);
        r0 = r6.n;
        r0 = r0.getClickNiceImageView();
        r1 = 0;
        r0.setSelected(r1);
        goto L_0x0459;
    L_0x051f:
        r0 = 0;
        r6.setIsFirstElement(r0);
        goto L_0x0309;
    L_0x0525:
        r0 = r6.a;
        r0 = r0.l;
        r1 = com.xunlei.downloadprovider.homepage.recommend.feed.a.getSujectIconDisplayOptions();
        if (r0 != 0) goto L_0x056c;
    L_0x052f:
        r0 = r6.n;
        r0 = r0.getSubjectIconImageView();
        r1 = r6.getResources();
        r2 = 2130838200; // 0x7f0202b8 float:1.7281376E38 double:1.0527739515E-314;
        r1 = r1.getDrawable(r2);
        r0.setImageDrawable(r1);
    L_0x0543:
        r0 = r6.a;
        r0 = r0.d;
        r1 = r6.a;
        r1 = r1.j;
        r2 = r6.a;
        r2 = r2.k;
        r3 = r6.c;
        r4 = com.xunlei.downloadprovider.app.BrothersApplication.a;
        r4 = com.xunlei.xllib.a.d.a(r4);
        r5 = r6.s;
        r0 = com.xunlei.downloadprovider.util.a.a(r0, r1, r2, r3, r4, r5);
        r1 = r6.c;
        r2 = r6.a;
        r2 = r2.j;
        r3 = r6.a;
        r3 = r3.k;
        com.xunlei.downloadprovider.homepage.choiceness.a.a(r0, r1, r2, r3);
        goto L_0x0310;
    L_0x056c:
        r2 = r6.n;
        r2 = r2.getSubjectIconImageView();
        r3 = com.nostra13.universalimageloader.core.d.a();
        r4 = new com.xunlei.downloadprovider.homepage.recommend.feed.b;
        r4.<init>(r6, r2);
        r3.a(r0, r2, r1, r4);
        goto L_0x0543;
    L_0x057f:
        r0 = 8;
        r1.setVisibility(r0);
        goto L_0x0322;
    L_0x0586:
        if (r13 != 0) goto L_0x00ba;
    L_0x0588:
        r0 = r11.e;
        r0 = android.view.LayoutInflater.from(r0);
        r1 = 2130968702; // 0x7f04007e float:1.7546065E38 double:1.052838428E-314;
        r2 = 0;
        r13 = r0.inflate(r1, r2);
        goto L_0x00ba;
    L_0x0598:
        r2 = r0.b;
        r3 = 0;
        r2.setVisibility(r3);
        r2 = r0.b;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r1.g;
        r3 = r3.append(r4);
        r4 = "\u4eba\u8d5e\u8fc7";
        r3 = r3.append(r4);
        r3 = r3.toString();
        r2.setText(r3);
        goto L_0x0071;
    L_0x05bb:
        r0 = move-exception;
        goto L_0x03ad;
    L_0x05be:
        r7 = r13;
        goto L_0x01e2;
        */
    }

    public final void a(int i) {
        if (this.m) {
            this.n = true;
            new Thread(new f(this)).start();
            this.m = false;
        }
        int i2 = this.f.a;
        if (!this.d) {
            this.d = true;
            if (this.b || i == 0) {
                com.xunlei.downloadprovider.model.protocol.e.a aVar = new com.xunlei.downloadprovider.model.protocol.e.a(this.e);
                if (i == 0) {
                    aVar.a(0, this.c, this.k, i2, i);
                    return;
                }
                int size;
                if (b()) {
                    size = this.l.size() - 2;
                } else {
                    size = this.l.size() - 1;
                }
                aVar.a(size, this.c, this.k, i2, i);
            }
        }
    }

    private void a(ArrayList<c> arrayList, boolean z) {
        new Thread(new g(this, z, arrayList)).start();
    }

    public final void notifyDataSetChanged() {
        this.j = System.currentTimeMillis();
        com.xunlei.downloadprovider.homepage.recommend.a.f();
        super.notifyDataSetChanged();
    }

    public final void a(boolean z) {
        if (this.q && !z) {
            b(true);
        }
        this.q = z;
    }

    public final void b(boolean z) {
        int firstVisiblePosition = this.g.getFirstVisiblePosition() - this.g.getHeaderViewsCount();
        int lastVisiblePosition = this.g.getLastVisiblePosition();
        int count = getCount();
        int i = firstVisiblePosition;
        while (i < lastVisiblePosition) {
            if (i >= 0 && i < count && (this.a.get(i) instanceof ao)) {
                com.xunlei.downloadprovider.homepage.recommend.a.a(z, (ao) this.a.get(i), this.f.f, String.valueOf(this.f.a));
            }
            i++;
        }
    }

    static /* synthetic */ void b(a aVar, Message message) {
        aVar.n = false;
        ArrayList arrayList = (ArrayList) message.obj;
        if (arrayList == null || arrayList.size() == 0) {
            aVar.a();
            return;
        }
        if (aVar.l.size() <= 1) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                c cVar = (c) it.next();
                aVar.l.add(cVar);
                aVar.a(cVar);
                if (aVar.f.g == -1) {
                    aVar.f.g = cVar.u;
                    aVar.f.i = cVar.w;
                }
                if (aVar.f.h == null) {
                    aVar.f.h = cVar.v;
                }
                if (aVar.f.e == null) {
                    aVar.f.e = cVar.x;
                }
            }
        }
        aVar.a();
    }

    static /* synthetic */ void a(a aVar, int i) {
        g gVar = new g();
        gVar.a = "android_videoChannel";
        gVar.b = "videoChannel_click";
        gVar.c = "videoChannel_click";
        gVar.b("position", (long) i);
        gVar.b("channelid", (long) aVar.f.a);
        ThunderReporter.a(gVar, true);
    }

    static /* synthetic */ void d(a aVar) {
        SQLiteDatabase sQLiteDatabase;
        com.xunlei.downloadprovider.frame.user.a.a a = com.xunlei.downloadprovider.frame.user.a.a.a(BrothersApplication.a);
        int i = aVar.f.a;
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            sQLiteDatabase2 = a.getWritableDatabase();
            try {
                sQLiteDatabase2.execSQL(new StringBuilder("DELETE FROM short_time_video_list WHERE video_type=").append(i).toString());
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.close();
                }
            } catch (Exception e) {
                if (sQLiteDatabase2 != null) {
                    sQLiteDatabase2.close();
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                sQLiteDatabase = sQLiteDatabase2;
                Throwable th3 = th2;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                throw th3;
            }
        } catch (Exception e2) {
            if (sQLiteDatabase2 != null) {
                sQLiteDatabase2.close();
            }
        } catch (Throwable th4) {
            th2 = th4;
            sQLiteDatabase = null;
            th3 = th2;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            throw th3;
        }
    }
}
