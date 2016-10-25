package com.xunlei.downloadprovider.member.payment.ui;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import com.xunlei.downloadprovider.frame.BaseFragment;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

public abstract class BasePayPagerActivity extends BasePayActivity {
    ViewPager d;
    ah e;
    protected List<x> f;
    private TabHost g;
    private OnPageChangeListener h;
    private OnTabChangeListener i;

    protected abstract List<x> f();

    public BasePayPagerActivity() {
        this.f = new ArrayList();
        this.h = new v(this);
        this.i = new w(this);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected final void c() {
        this.f = f();
        findViewById(R.id.white_button_line).setVisibility(XZBDevice.Wait);
        findViewById(com.xunlei.xiazaibao.R.id.xreader_common_divide).setVisibility(XZBDevice.Wait);
        this.d = (ViewPager) findViewById(2131755693);
        this.d.removeAllViews();
        this.e = new ah(getSupportFragmentManager(), this.f);
        this.d.setAdapter(this.e);
        this.d.setOnPageChangeListener(this.h);
        this.g = (TabHost) findViewById(16908306);
        this.g.setup();
        a(this.f);
        this.g.setOnTabChangedListener(this.i);
        this.g.getTabWidget().setDividerDrawable(null);
    }

    private void a(List<x> list) {
        LayoutInflater from = LayoutInflater.from(this);
        int size = list.size();
        this.g.clearAllTabs();
        for (int i = 0; i < size; i++) {
            x xVar = (x) list.get(i);
            View inflate = from.inflate(2130968996, null);
            TextView textView = (TextView) inflate.findViewById(2131757041);
            textView.setText(xVar.a);
            TabSpec content = this.g.newTabSpec(xVar.a).setIndicator(inflate).setContent(16908305);
            Bundle bundle = xVar.c;
            if (bundle != null) {
                int i2;
                int i3 = bundle.getInt("VasType");
                switch (i3) {
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        i2 = 2130838472;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        i2 = 2130838473;
                        break;
                    case 204:
                        i2 = 2130838471;
                        break;
                    default:
                        i2 = 2130838473;
                        break;
                }
                textView.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(i2), null, null, null);
                inflate.findViewById(2131757043).setVisibility(i3 == 5 ? 0 : XZBDevice.Wait);
            }
            if (i < size - 1) {
                LayoutParams layoutParams;
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) inflate.getLayoutParams();
                if (layoutParams2 == null) {
                    layoutParams = new LinearLayout.LayoutParams(0, -2);
                }
                layoutParams.weight = 1.0f;
                layoutParams.setMargins(0, 0, getResources().getDimensionPixelSize(2131362137), 0);
                inflate.setLayoutParams(layoutParams);
            }
            this.g.addTab(content);
        }
        j();
        i();
    }

    private void i() {
        int childCount = this.g.getTabWidget().getChildCount();
        for (int i = 0; i < childCount; i++) {
            boolean z;
            ViewGroup viewGroup = (ViewGroup) this.g.getTabWidget().getChildAt(i);
            if (this.g.getCurrentTab() == i) {
                z = true;
            } else {
                z = false;
            }
            viewGroup.setSelected(z);
            viewGroup.findViewById(2131757044).setVisibility(z ? 0 : XZBDevice.Wait);
        }
    }

    private void j() {
        for (int i = 0; i < this.g.getTabWidget().getChildCount(); i++) {
            this.g.getTabWidget().getChildAt(i).setOnClickListener(new u(this, i));
        }
    }

    protected void d() {
    }

    protected BaseFragment e() {
        return c(this.d.getCurrentItem());
    }

    protected BaseFragment c(int i) {
        return (BaseFragment) this.e.a.get(i);
    }

    protected final boolean g() {
        return (this.e == null || this.d == null) ? false : true;
    }

    protected final void h() {
        this.g.getTabWidget().removeAllViews();
        this.f = f();
        a(this.f);
        ah ahVar = this.e;
        ahVar.a.clear();
        if (ahVar.b != null) {
            ahVar.b.clear();
        }
        this.e.b = this.f;
        this.e.notifyDataSetChanged();
    }
}
