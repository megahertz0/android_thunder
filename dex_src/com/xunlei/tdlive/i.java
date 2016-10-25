package com.xunlei.tdlive;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.a.c;
import com.xunlei.tdlive.a.c.b;
import com.xunlei.tdlive.a.r;
import com.xunlei.tdlive.base.h;
import com.xunlei.tdlive.base.n;
import com.xunlei.tdlive.control.PagerIndicator;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveGetGiftDialogBannerInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveGetUserInfoRequest.LType;
import com.xunlei.tdlive.protocol.XLLiveSendGiftRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.d;
import com.xunlei.tdlive.util.q;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.agoo.common.AgooConstants;

// compiled from: LiveGiftDialog.java
public class i extends h implements OnPageChangeListener, OnClickListener, b, f.b {
    private static long a;
    private a b;
    private TextView c;
    private View d;
    private ViewPager e;
    private c f;
    private PagerIndicator g;
    private r h;
    private int i;
    private String j;
    private String k;
    private String l;
    private boolean m;
    private boolean n;
    private int o;

    static {
        a = 0;
    }

    public i(Context context, String str, String str2, int i, String str3, boolean z, a aVar) {
        super(context, R.style.TransparentDialogStyle);
        this.b = null;
        this.i = -1;
        this.j = BuildConfig.VERSION_NAME;
        this.k = BuildConfig.VERSION_NAME;
        this.n = false;
        this.o = 0;
        setCanceledOnTouchOutside(true);
        this.j = str;
        this.k = str2;
        this.l = str3;
        this.m = z;
        this.b = aVar;
        this.i = i;
    }

    public void a() {
        if (f.a().b()) {
            new XLLiveGetUserInfoRequest(f.a().k(), f.a().l(), BuildConfig.VERSION_NAME, LType.XL).send(new j(this));
        }
    }

    public void b() {
        new XLLiveGetGiftDialogBannerInfoRequest(f.a().k(), f.a().l()).send(new k(this));
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_dialog_gift);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 80;
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.ActionSheetDialogAnimation);
        findViewById(R.id.send).setOnClickListener(this);
        findViewById(R.id.recharge_area).setOnClickListener(this);
        findViewById(R.id.activity).setOnClickListener(this);
        this.n = false;
        this.d = findViewById(R.id.send);
        this.c = (TextView) findViewById(R.id.coin_count);
        this.g = (PagerIndicator) findViewById(R.id.pager_indicator);
        PagerIndicator pagerIndicator = this.g;
        BaseAdapter rVar = new r();
        this.h = rVar;
        pagerIndicator.setAdapter(rVar);
        this.g.setSingleVisible(false);
        this.e = (ViewPager) findViewById(R.id.gift_pannel);
        this.e.setLayoutParams(new LinearLayout.LayoutParams(-1, ((int) d.a(getContext(), 5.0f)) + ((this.e.getResources().getDisplayMetrics().widthPixels / 4) * 2)));
        this.e.setPadding(0, (int) d.a(getContext(), 5.0f), 0, 0);
        ViewPager viewPager = this.e;
        c cVar = new c(getContext(), this);
        this.f = cVar;
        viewPager.setAdapter(cVar);
        this.e.setOnPageChangeListener(this);
        this.e.setCurrentItem(0);
        a();
    }

    public void onStart() {
        super.onStart();
        b();
        f.a().a(this);
        this.f.a(null);
    }

    public void onStop() {
        super.onStop();
        f.a().b(this);
        this.f.a();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.tdlive.i.onClick(android.view.View):void");
        /*
        this = this;
        r0 = r6.getId();
        r1 = com.xunlei.tdlive.R.id.send;
        if (r0 != r1) goto L_0x0042;
    L_0x0008:
        r0 = r6.getTag();	 Catch:{ Throwable -> 0x0040 }
        if (r0 == 0) goto L_0x002a;
    L_0x000e:
        r0 = com.xunlei.tdlive.user.f.a();	 Catch:{ Throwable -> 0x0040 }
        r0 = r0.b();	 Catch:{ Throwable -> 0x0040 }
        r1 = com.xunlei.tdlive.user.f.a();	 Catch:{ Throwable -> 0x0040 }
        r2 = r5.getOwnerActivity();	 Catch:{ Throwable -> 0x0040 }
        r3 = "gift";
        r4 = new com.xunlei.tdlive.l;	 Catch:{ Throwable -> 0x0040 }
        r4.<init>(r5, r0, r6);	 Catch:{ Throwable -> 0x0040 }
        r1.a(r2, r3, r4);	 Catch:{ Throwable -> 0x0040 }
    L_0x0029:
        return;
    L_0x002a:
        r0 = r5.getContext();	 Catch:{ Throwable -> 0x0040 }
        r1 = r5.getContext();	 Catch:{ Throwable -> 0x0040 }
        r1 = r1.getResources();	 Catch:{ Throwable -> 0x0040 }
        r2 = com.xunlei.tdlive.R.string.please_select_a_gift;	 Catch:{ Throwable -> 0x0040 }
        r1 = r1.getString(r2);	 Catch:{ Throwable -> 0x0040 }
        com.xunlei.tdlive.base.n.a(r0, r1);	 Catch:{ Throwable -> 0x0040 }
        goto L_0x0029;
    L_0x0040:
        r0 = move-exception;
        goto L_0x0029;
    L_0x0042:
        r1 = com.xunlei.tdlive.R.id.recharge_area;
        if (r0 != r1) goto L_0x005a;
    L_0x0046:
        r0 = com.xunlei.tdlive.user.f.a();
        r1 = r5.getContext();
        r2 = "pay";
        r3 = new com.xunlei.tdlive.n;
        r3.<init>(r5);
        r0.a(r1, r2, r3);
        goto L_0x0029;
    L_0x005a:
        r1 = com.xunlei.tdlive.R.id.activity;
        if (r0 != r1) goto L_0x0029;
    L_0x005e:
        r0 = r6.getTag();	 Catch:{ Throwable -> 0x00ad }
        r0 = (com.xunlei.tdlive.modal.JsonWrapper) r0;	 Catch:{ Throwable -> 0x00ad }
        if (r0 == 0) goto L_0x0029;
    L_0x0066:
        r1 = "type";
        r2 = -1;
        r1 = r0.getInt(r1, r2);	 Catch:{ Throwable -> 0x00ad }
        r2 = "url";
        r3 = "";
        r2 = r0.getString(r2, r3);	 Catch:{ Throwable -> 0x00ad }
        r3 = "title";
        r4 = "";
        r0 = r0.getString(r3, r4);	 Catch:{ Throwable -> 0x00ad }
        r3 = r5.getContext();	 Catch:{ Throwable -> 0x00ad }
        r4 = 0;
        com.xunlei.tdlive.DispatcherActivity.a(r3, r1, r0, r2, r4);	 Catch:{ Throwable -> 0x00ad }
        r0 = "activity";
        r0 = com.xunlei.tdlive.util.q.e(r0);	 Catch:{ Throwable -> 0x00ad }
        r1 = "activity_gift_page_click";
        r0 = r0.a(r1);	 Catch:{ Throwable -> 0x00ad }
        r1 = "target";
        r0 = r0.a(r1, r2);	 Catch:{ Throwable -> 0x00ad }
        r1 = 1;
        r1 = new java.lang.String[r1];	 Catch:{ Throwable -> 0x00ad }
        r2 = 0;
        r3 = "target";
        r1[r2] = r3;	 Catch:{ Throwable -> 0x00ad }
        r0.b(r1);	 Catch:{ Throwable -> 0x00ad }
        goto L_0x0029;
    L_0x00ad:
        r0 = move-exception;
        goto L_0x0029;
        */
    }

    public void a(boolean z) {
        if (z) {
            a();
        }
    }

    public void a(boolean z, boolean z2) {
        if (z) {
            this.h.a(this.f.getCount());
            if (!z2 && this.i != -1) {
                this.f.a(this.i);
                this.i = -1;
            }
        }
    }

    public void a(int i) {
        this.e.setCurrentItem(i, false);
        if (this.o == 0 && !this.n) {
            this.n = true;
            b(0);
        }
    }

    public boolean a(boolean z, JsonWrapper jsonWrapper, boolean z2) {
        if (z) {
            int i = jsonWrapper.getInt("level", 0);
            int i2 = jsonWrapper.getInt("costnum", 0);
            int i3 = jsonWrapper.getInt("giftnum", 0);
            int i4 = jsonWrapper.getInt("tick", 0);
            int i5 = jsonWrapper.getInt("remaintime", 0);
            if (i != 0 && c() < ((long) i2) && f.a().b()) {
                if (!z2) {
                    d();
                }
                return false;
            } else if (i != 0 || i3 > 0) {
                this.d.setEnabled(true);
                this.d.setTag(jsonWrapper);
                return true;
            } else {
                String str;
                Context context = getContext();
                if (i5 <= 0) {
                    str = "\u4eca\u5929\u9886\u5b8c\u5566\uff0c\u660e\u5929\u518d\u6765\u54e6~";
                } else {
                    str = new StringBuilder("\u8fd8\u6709").append(i5 - i4).append("\u79d2\u5c31\u80fd\u9886\u53d6~").toString();
                }
                n.a(context, str);
                return false;
            }
        }
        this.d.setEnabled(false);
        this.d.setTag(null);
        return true;
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        this.g.select(i);
        this.f.a(-1);
        b(i);
    }

    public void onPageScrollStateChanged(int i) {
    }

    private void a(long j) {
        CharSequence charSequence;
        if (j < 0) {
            charSequence = "0";
            a = 0;
        } else {
            charSequence = String.valueOf(j);
            a = j;
        }
        this.c.setText(charSequence);
    }

    private long c() {
        return a;
    }

    private void d() {
        new com.xunlei.tdlive.base.c(getContext(), null, "\u91d1\u5e01\u4e0d\u8db3\u54e6\u4eb2\r\n\u5feb\u5145\u503c\u91d1\u5e01\u9001TA\u793c\u7269\u5427^^", "\u53d6 \u6d88", new String[]{"\u5145 \u503c"}).b(new o(this));
    }

    private void a(JsonWrapper jsonWrapper) {
        q.e("send_gift").a("hostid", this.k).a(AgooConstants.MESSAGE_TYPE, "live").a("giftid", jsonWrapper.getInt("giftid", 0)).a("gift_num", 1).a("gift_value", jsonWrapper.getInt("costnum", 0)).a("follow", q.e("live_room_show").d("is_follow"));
        new XLLiveSendGiftRequest(f.a().k(), f.a().l(), this.j, this.k, jsonWrapper.getInt("giftid", 0), 1).send(new p(this));
    }

    private void b(int i) {
        q.e("gift_page_show").a(this.l).b(this.m ? "red" : SocializeProtocolConstants.PROTOCOL_NORMAL_SHARE).a("hostid", this.k).a(SetKey.PAGE, i).b(new String[0]);
    }
}
