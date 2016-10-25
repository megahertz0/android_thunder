package com.xunlei.downloadprovider.frame;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import com.xunlei.downloadprovider.homepage.choiceness.ui.HomeChoicenessFragment;
import com.xunlei.downloadprovider.homepage.recommend.SummaryMoviesListFragment;
import com.xunlei.downloadprovider.homepage.relax.RelaxListFragment;
import com.xunlei.downloadprovider.search.ui.widget.HomeTitleBar;
import com.xunlei.downloadprovider.util.v;
import com.xunlei.downloadprovider.util.v.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseViewPagerFragment extends BaseCacheViewFragment implements a {
    public TabHost a;
    public ViewPager b;
    public boolean c;
    protected HomeTitleBar d;
    public int e;
    private f f;
    private TextView g;
    private List<TextView> h;
    private ImageView i;
    private int j;
    private long k;
    private int l;
    private OnPageChangeListener m;
    private OnTabChangeListener n;
    private af o;

    public abstract String[] b();

    public abstract Class<?>[] c();

    public BaseViewPagerFragment() {
        this.h = new ArrayList(2);
        this.j = 0;
        this.k = 0;
        this.l = this.j;
        this.m = new c(this);
        this.n = new d(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f = new f(getChildFragmentManager(), c(), (byte) 0);
        v.a().a((a) this);
    }

    public void a(View view) {
        this.i = (ImageView) view.findViewById(2131755692);
        this.d = (HomeTitleBar) view.findViewById(2131755350);
        this.e = (int) getResources().getDimension(2131362094);
        this.a = (TabHost) view.findViewById(16908306);
        this.a.setup();
        LayoutInflater from = LayoutInflater.from(getActivity());
        String[] b = b();
        int length = b.length;
        for (int i = 0; i < length; i++) {
            View inflate = from.inflate(2130968995, null);
            TextView textView = (TextView) inflate.findViewById(2131757041);
            this.h.add(textView);
            textView.setText(b[i]);
            a(i, (ImageView) inflate.findViewById(2131757042));
            this.a.addTab(this.a.newTabSpec(b[i]).setIndicator(inflate).setContent(16908305));
        }
        this.a.setOnTabChangedListener(this.n);
        this.a.getTabWidget().setDividerDrawable(null);
        this.b = (ViewPager) view.findViewById(2131755693);
        this.b.setAdapter(this.f);
        this.b.setOnPageChangeListener(this.m);
        this.b.setCurrentItem(this.j);
        e(this.j);
        if (this.j == 0) {
            new Handler().postDelayed(new a(this), 500);
        }
        new Handler().postDelayed(new b(this), 2000);
        e();
        this.c = true;
    }

    public void a(int i, View view) {
    }

    public final void a(int i) {
        if (this.b == null) {
            this.j = i;
        } else {
            this.b.setCurrentItem(i);
        }
    }

    public boolean onBackPressed() {
        BasePageFragment a = a();
        return a == null ? super.onBackPressed() : a.onBackPressed();
    }

    private void e(int i) {
        if (i >= 0 && i < b().length) {
            if (b()[i].equals("\u7cbe\u9009") || b()[i].equals("\u77ed\u7247") || b()[i].equals("\u8da3\u56fe")) {
                this.a.getTabWidget().getChildTabViewAt(i).setOnClickListener(new e(this, i));
            }
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
        v.a().b((a) this);
    }

    private void e() {
        if (this.g != null) {
            this.g.getPaint().setFakeBoldText(false);
        }
        View currentTabView = this.a.getCurrentTabView();
        TextView textView = (TextView) currentTabView.findViewById(2131757041);
        textView.getPaint().setFakeBoldText(true);
        int currentTab = this.a.getCurrentTab();
        currentTabView.findViewById(2131757042).setVisibility(XZBDevice.Wait);
        d(currentTab);
        this.g = textView;
    }

    private int f() {
        return this.f == null ? 0 : this.f.getCount();
    }

    protected final void b(int i) {
        BasePageFragment c = c(i);
        if (c != null) {
            c.onPageSelected();
        }
        if (c instanceof HomeChoicenessFragment) {
            this.o = (HomeChoicenessFragment) c;
        }
        if (c instanceof SummaryMoviesListFragment) {
            this.o = (SummaryMoviesListFragment) c;
        }
        if (c instanceof RelaxListFragment) {
            this.o = (RelaxListFragment) c;
        }
    }

    final BasePageFragment a() {
        return this.b == null ? null : c(this.b.getCurrentItem());
    }

    public final BasePageFragment c(int i) {
        return this.f == null ? null : (BasePageFragment) this.f.a.get(i);
    }

    public void a(v vVar) {
    }

    public void d(int i) {
    }

    public final void d() {
        if (this.a != null) {
            this.i.setTranslationX((float) ((this.a.getCurrentTab() * (this.a.getWidth() / f())) + (((this.a.getWidth() / f()) - this.i.getWidth()) / 2)));
        }
    }
}
