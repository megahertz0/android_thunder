package com.xunlei.downloadprovider.web.browser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.xunlei.downloadprovider.model.i;
import com.xunlei.downloadprovider.web.record.FavorAndHistroyActivity;
import com.xunlei.tdlive.R;

public class BrowserToolBarFragment extends Fragment implements OnClickListener {
    public final a a;
    private b b;
    private Button c;
    private Button d;
    private View e;
    private View f;
    private View g;
    private View h;

    public static interface b {
        void onBrowserGoBackButtonClick(View view);

        void onBrowserGoForwardButtonClick(View view);

        void onBrowserToolBarButtonClick(View view);
    }

    class a {
        OnClickListener a;
        private a c;
        private String d;

        public a() {
            this.c = new a(this);
        }

        public final void a(boolean z) {
            this.c.c = z;
            a(this.c.a, this.c.c);
        }

        private void a(boolean z, boolean z2) {
            if (this.c != null) {
                this.c.a = z;
                BrowserToolBarFragment.this = z2;
                if (this.c.a) {
                    BrowserToolBarFragment.this.h.setEnabled(true);
                    BrowserToolBarFragment.this.h.setSelected(BrowserToolBarFragment.this);
                    return;
                }
                BrowserToolBarFragment.this.h.setEnabled(false);
                BrowserToolBarFragment.this.h.setSelected(false);
            }
        }

        final void a(String str) {
            Toast toast = new Toast(BrowserToolBarFragment.this.getActivity());
            View inflate = LayoutInflater.from(BrowserToolBarFragment.this.getActivity()).inflate(2130968744, null);
            ((TextView) inflate.findViewById(R.id.tv_title)).setText(str);
            toast.setView(inflate);
            toast.setGravity(R.styleable.Toolbar_maxButtonHeight, 0, 0);
            toast.setDuration(0);
            toast.show();
        }

        public final void b(String str) {
            if (str == null) {
                this.d = com.umeng.a.d;
                return;
            }
            Object substring;
            boolean z;
            this.d = str;
            String str2 = null;
            if (str != null) {
                str2 = str.trim();
            }
            if (substring != null && substring.endsWith("/")) {
                substring = substring.substring(0, substring.length() - 1);
            }
            if ("http://m.sjzhushou.com/v2/site/site_add_2.4.html".equals(substring)) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                a(false, this.c.c);
            } else {
                a(true, this.c.c);
            }
            com.xunlei.downloadprovider.model.b a = i.a().a(str);
            if (a == null || TextUtils.isEmpty(a.c) || !a.c.endsWith(str)) {
                a(false);
            } else {
                a(true);
            }
        }
    }

    public BrowserToolBarFragment() {
        this.a = new a();
    }

    public final void a(boolean z) {
        if (this.e != null) {
            this.e.setEnabled(z);
        }
    }

    public final void b(boolean z) {
        if (this.f != null) {
            this.f.setEnabled(z);
        }
    }

    public final void c(boolean z) {
        if (this.c != null) {
            this.c.setEnabled(z);
            this.d.setEnabled(z);
            if (!z) {
                this.d.setAlpha(1.0f);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968766, viewGroup, false);
        this.e = inflate.findViewById(2131755982);
        this.e.setOnClickListener(this);
        this.f = inflate.findViewById(2131755983);
        this.f.setOnClickListener(this);
        this.c = (Button) inflate.findViewById(2131755986);
        this.c.setOnClickListener(this);
        this.d = (Button) inflate.findViewById(2131755987);
        this.d.setOnClickListener(this);
        this.h = inflate.findViewById(2131755984);
        this.h.setOnClickListener(this);
        this.g = inflate.findViewById(2131755985);
        this.g.setOnClickListener(this);
        return inflate;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.b = (b) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.b = null;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755982:
                if (this.b != null) {
                    this.b.onBrowserGoBackButtonClick(view);
                }
            case 2131755983:
                if (this.b != null) {
                    this.b.onBrowserGoForwardButtonClick(view);
                }
            case 2131755984:
                a aVar = this.a;
                if (aVar.a != null) {
                    aVar.a.onClick(null);
                }
            case 2131755985:
                com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.b.a("browser_favorite");
                getActivity().startActivity(new Intent(getActivity(), FavorAndHistroyActivity.class));
                com.xunlei.downloadprovider.commonview.a.a.a(getActivity());
            case 2131755986:
                if (this.b != null) {
                    this.b.onBrowserToolBarButtonClick(view);
                }
            case 2131755987:
                if (this.b != null) {
                    this.b.onBrowserToolBarButtonClick(this.c);
                }
            default:
                break;
        }
    }

    public final void d(boolean z) {
        if (z) {
            this.d.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            this.c.setAlpha(1.0f);
            return;
        }
        this.d.setAlpha(1.0f);
        this.c.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
    }
}
