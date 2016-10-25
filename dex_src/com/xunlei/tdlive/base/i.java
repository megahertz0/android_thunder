package com.xunlei.tdlive.base;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.AutoScrollHelper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.HashMap;
import java.util.Timer;

// compiled from: BaseFragment.java
public class i extends Fragment {
    public View a;
    protected View b;
    public View c;
    protected TextView d;
    protected View e;
    protected TextView f;
    public View g;
    protected TextView h;
    protected View i;
    protected TextView j;
    private m k;
    private HashMap<Integer, Timer> l;
    private Toast m;
    private boolean n;
    private Handler o;
    private Handler p;

    public i() {
        this.k = new m(null, null, 0);
        this.l = new HashMap();
        this.n = false;
        this.o = new j(this);
        this.p = new k(this);
    }

    public void a(m mVar) {
        this.k = mVar;
    }

    public View b(int i) {
        return getView() != null ? getView().findViewById(i) : null;
    }

    public Toast a_(String str) {
        return a(str, 0);
    }

    public Toast c(int i) {
        return a(i, 0);
    }

    public Toast a(int i, int i2) {
        return a(getString(i), i2);
    }

    public Toast a(String str, int i) {
        if (this.m == null) {
            this.m = Toast.makeText(getActivity(), str, i);
        }
        this.m.setDuration(i);
        this.m.setText(str);
        this.m.show();
        return this.m;
    }

    public Toast a(int i, int i2, int i3, int i4, int i5) {
        return a(getString(i), i2, i3, i4, i5);
    }

    public Toast a(String str, int i, int i2, int i3, int i4) {
        try {
            View inflate = getActivity().getLayoutInflater().inflate(i2, null);
            ((TextView) inflate.findViewById(i3)).setText(str);
            this.m = new Toast(getActivity());
            this.m.setView(inflate);
            this.m.setGravity(i4, 0, 0);
            this.m.setDuration(i);
            this.m.show();
        } catch (Exception e) {
        }
        return this.m;
    }

    public long a(int i, long j) {
        return a(i, j, j);
    }

    public long a(int i, long j, long j2) {
        d(i);
        try {
            Timer timer = new Timer();
            this.l.put(Integer.valueOf(i), timer);
            timer.schedule(new a(this, i), j, j2);
        } catch (Throwable th) {
        }
        return (long) i;
    }

    public void d(int i) {
        if (this.l.containsKey(Integer.valueOf(i))) {
            try {
                ((Timer) this.l.get(Integer.valueOf(i))).cancel();
                this.l.remove(Integer.valueOf(i));
            } catch (Throwable th) {
                this.l.remove(Integer.valueOf(i));
            }
        }
    }

    public void startActivity(Intent intent) {
        getActivity().startActivity(intent);
    }

    public void b(String str, int i) {
        PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).edit().putInt(str, i).commit();
    }

    public int c(String str, int i) {
        return PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getInt(str, i);
    }

    public void c(boolean z) {
        if (a_()) {
            this.a.setVisibility(z ? 0 : XZBDevice.Wait);
        }
    }

    public void e(int i) {
        if (a_()) {
            try {
                this.b.setBackgroundResource(i);
            } catch (Exception e) {
            }
        }
    }

    public void a(OnClickListener onClickListener) {
        if (a_()) {
            this.a.setOnClickListener(onClickListener);
        }
    }

    public void b(String str) {
        a(str, (float) AutoScrollHelper.RELATIVE_UNSPECIFIED, -1);
    }

    public void a(String str, float f, int i) {
        if (a_()) {
            this.f.setText(str);
            if (f != 0.0f) {
                this.f.setTextSize(f);
            }
            if (i != -1) {
                this.f.setTextColor(i);
            }
        }
    }

    public void d(boolean z) {
        if (a_()) {
            this.c.setVisibility(z ? 0 : XZBDevice.Wait);
        }
    }

    public void a(Drawable drawable) {
        if (a_()) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.d.setCompoundDrawables(drawable, null, null, null);
        }
    }

    public void b(OnClickListener onClickListener) {
        if (a_()) {
            this.c.setOnClickListener(onClickListener);
        }
    }

    public void e(boolean z) {
        if (a_()) {
            this.g.setVisibility(z ? 0 : XZBDevice.Wait);
        }
    }

    public void b(Drawable drawable) {
        if (a_()) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            this.h.setCompoundDrawables(drawable, null, null, null);
        }
    }

    protected boolean a_() {
        if (this.a != null) {
            return true;
        }
        this.a = b(R.id.xllive_title_bar);
        if (this.a != null) {
            this.b = this.a.findViewById(R.id.background);
            this.c = this.a.findViewById(R.id.left);
            this.d = (TextView) this.a.findViewById(R.id.ltext);
            this.e = this.a.findViewById(R.id.center);
            this.f = (TextView) this.a.findViewById(R.id.ctext);
            this.g = this.a.findViewById(R.id.right);
            this.h = (TextView) this.a.findViewById(R.id.rtext);
            this.i = this.a.findViewById(R.id.title_msg_bar);
            this.j = (TextView) this.i.findViewById(R.id.title_msg_text);
        }
        return this.a != null;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.a = null;
    }

    public void onDestroy() {
        super.onDestroy();
        for (Integer num : this.l.keySet()) {
            try {
                ((Timer) this.l.get(num)).cancel();
            } catch (Exception e) {
            }
        }
        this.l.clear();
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        a_();
    }

    public void onResume() {
        super.onResume();
        q.b(getClass().getSimpleName());
    }

    public void onPause() {
        super.onPause();
        q.c(getClass().getSimpleName());
    }

    public void a_(int i) {
    }

    protected void onMessage(Message message) {
    }

    public void a(boolean z) {
    }

    public void a() {
    }
}
