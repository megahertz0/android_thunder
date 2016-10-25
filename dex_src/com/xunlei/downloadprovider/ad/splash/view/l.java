package com.xunlei.downloadprovider.ad.splash.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.uc.addon.sdk.remote.TabsImpl;
import com.xunlei.downloadprovider.ad.common.b;
import com.xunlei.downloadprovider.ad.splash.a.a;
import com.xunlei.downloadprovider.ad.splash.a.c;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.android.spdy.SpdyAgent;

// compiled from: SplashWrapAdView.java
public final class l extends b {
    private View l;
    private View m;
    private View n;
    private ImageView o;
    private RatingBar p;
    private ImageView q;
    private TextView r;
    private View s;
    private ImageView t;
    private a u;
    private int v;
    private int w;
    private int x;
    private int y;

    public l(Context context) {
        super(context);
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.l = LayoutInflater.from(this.a).inflate(2130968828, this);
        a();
    }

    protected final void a() {
        super.a();
        this.m = this.l.findViewById(2131756304);
        this.n = this.l.findViewById(2131756307);
        this.o = (ImageView) this.l.findViewById(2131756305);
        this.p = (RatingBar) this.l.findViewById(2131756306);
        this.q = (ImageView) this.l.findViewById(2131756308);
        this.r = (TextView) this.l.findViewById(2131756309);
        this.s = this.l.findViewById(2131756310);
        this.t = (ImageView) this.l.findViewById(2131756297);
        this.g = (CountDownCircleProgressBar) this.l.findViewById(2131756156);
        m();
        String str = "background_9";
        Set hashSet = new HashSet();
        hashSet.add("background_2");
        hashSet.add("background_3");
        hashSet.add("background_4");
        hashSet.add("background_7");
        hashSet.add("background_8");
        hashSet.add("background_9");
        hashSet.add("background_10");
        hashSet.add("background_13");
        hashSet.add("background_14");
        if (hashSet.contains(str)) {
            Object obj;
            str = (String) b.a(a(str, hashSet), str);
            int i;
            switch (str.hashCode()) {
                case -1893605680:
                    if (str.equals("background_10")) {
                        obj = R.styleable.Toolbar_contentInsetEnd;
                    }
                    i = -1;
                    break;
                case -1893605677:
                    if (str.equals("background_13")) {
                        obj = R.styleable.Toolbar_contentInsetLeft;
                    }
                    i = -1;
                    break;
                case -1893605676:
                    if (str.equals("background_14")) {
                        obj = XZBDevice.Wait;
                    }
                    i = -1;
                    break;
                case -338178719:
                    if (str.equals("background_2")) {
                        obj = null;
                    }
                    i = -1;
                    break;
                case -338178718:
                    if (str.equals("background_3")) {
                        obj = 1;
                    }
                    i = -1;
                    break;
                case -338178717:
                    if (str.equals("background_4")) {
                        obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                    }
                    i = -1;
                    break;
                case -338178714:
                    if (str.equals("background_7")) {
                        obj = XZBDevice.DOWNLOAD_LIST_FAILED;
                    }
                    i = -1;
                    break;
                case -338178713:
                    if (str.equals("background_8")) {
                        obj = XZBDevice.DOWNLOAD_LIST_ALL;
                    }
                    i = -1;
                    break;
                case -338178712:
                    if (str.equals("background_9")) {
                        obj = XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
                    }
                    i = -1;
                    break;
                default:
                    i = -1;
                    break;
            }
            switch (obj) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    setAdStyle("background_2");
                    setBackground(2130839258);
                    setContentBackground(2130839269);
                    setTitle(2130839283);
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    setAdStyle("background_3");
                    setBackground(2130839264);
                    setContentBackground(2130839269);
                    setTitle(2130839283);
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    setAdStyle("background_4");
                    setBackground(2130839265);
                    setContentBackground(2130839269);
                    setTitle(2130839283);
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    setAdStyle("background_7");
                    setBackground(2130839267);
                    setContentBackground(2130839273);
                    setTitle(2130839283);
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    setAdStyle("background_8");
                    setBackground(2130839259);
                    setContentBackground(2130839273);
                    setTitle(2130839283);
                    break;
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    l();
                    break;
                case R.styleable.Toolbar_contentInsetEnd:
                    setAdStyle("background_10");
                    setBackground(2130839261);
                    setContentBackground(2130839273);
                    setTitle(2130839282);
                    n();
                    break;
                case R.styleable.Toolbar_contentInsetLeft:
                    setAdStyle("background_13");
                    setBackground(2130839260);
                    setContentBackground(2130839271);
                    setTitle(2130839280);
                    m();
                    break;
                case XZBDevice.Wait:
                    setAdStyle("background_14");
                    setBackground(2130839263);
                    setContentBackground(2130839272);
                    setTitle(2130839282);
                    n();
                    break;
                default:
                    l();
                    break;
            }
            k();
            this.g.setOnClickListener(new m(this));
            setOnClickListener(new n(this));
            return;
        }
        throw new RuntimeException(new StringBuilder("styles must include defaultStyle: ").append(str).toString());
    }

    public final void a(a aVar) {
        k();
        this.u = aVar;
        this.f = Math.max(TabsImpl.SYNC_TIME_OUT, this.u.b());
        setCountDown$2566ab5(this.f);
        setScore(this.u.k());
        setDesc(this.u.i());
        setImage(this.u.a());
        setActionName(this.u.g());
        if (aVar instanceof c) {
            setAdSourceIconIv(2130839277);
        } else if (aVar instanceof com.xunlei.downloadprovider.ad.splash.a.b) {
            setAdSourceIconIv(2130839276);
        }
        h();
        b();
    }

    public final void setBackground(int i) {
        this.w = i;
        this.m.setBackgroundResource(i);
    }

    public final void setContentBackground(int i) {
        this.n.setBackgroundResource(i);
    }

    public final void setTitle(int i) {
        this.v = i;
        this.o.setImageResource(i);
    }

    public final void setScore(float f) {
        this.p.setRating(f);
    }

    public final void setImage(Bitmap bitmap) {
        if (bitmap != null) {
            this.q.setImageBitmap(bitmap);
        } else {
            this.q.setImageResource(R.color.transparent);
        }
    }

    public final void setImage(int i) {
        this.q.setImageResource(i);
    }

    public final void setDesc(String str) {
        this.r.setText(str);
    }

    public final void setActionName(boolean z) {
        if (z) {
            this.s.setBackgroundDrawable(getResources().getDrawable(this.x));
        } else {
            this.s.setBackgroundDrawable(getResources().getDrawable(this.y));
        }
    }

    public final void setAdSourceIconIv(int i) {
        this.t.setImageResource(i);
        this.t.setVisibility(0);
    }

    private void k() {
        boolean z = false;
        this.g.setProgress(0);
        setImage((int) R.color.transparent);
        setScore(5.0f);
        setDesc(com.umeng.a.d);
        if (this.u != null && this.u.g()) {
            z = true;
        }
        setActionName(z);
        this.t.setVisibility(XZBDevice.Wait);
    }

    private static Map<String, Integer> a(String str, Set<String> set) {
        Map<String, Integer> hashMap = new HashMap();
        int size = set.size();
        int i = 100 / size;
        int i2 = 100 - (size * i);
        for (String str2 : set) {
            int i3;
            if (str2.equals(str)) {
                i3 = i + i2;
            } else {
                i3 = i;
            }
            hashMap.put(str2, Integer.valueOf(i3));
        }
        for (Entry entry : hashMap.entrySet()) {
            new StringBuilder("key: ").append((String) entry.getKey()).append(" value: ").append(entry.getValue());
        }
        return hashMap;
    }

    private void l() {
        setAdStyle("background_9");
        setBackground(2130839268);
        setContentBackground(2130839273);
        setTitle(2130839280);
        n();
    }

    private void m() {
        this.x = 2130839244;
        this.y = 2130839256;
    }

    private void n() {
        this.x = 2130839247;
        this.y = 2130839250;
    }
}
