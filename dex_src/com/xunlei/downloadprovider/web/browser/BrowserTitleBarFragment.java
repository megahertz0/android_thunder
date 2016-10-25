package com.xunlei.downloadprovider.web.browser;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.DownloadEntranceView;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.downloads.task.d;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class BrowserTitleBarFragment extends Fragment implements OnClickListener {
    String a;
    ProgressBar b;
    com.xunlei.downloadprovider.download.b.a c;
    protected boolean d;
    final a e;
    private String f;
    private b g;
    private DownloadEntranceView h;
    private View i;
    private TextView j;
    private EditText k;
    private TextView l;
    private View m;
    private View n;
    private View o;
    private View p;
    private OnEditorActionListener q;
    private TextWatcher r;
    private OnFocusChangeListener s;

    public static interface b {
        void a(String str);

        void a(boolean z);

        void onBrowserRefreshButtonClick(View view);

        void onBrowserStopButtonClick(View view);

        void onBrowserTitleBarBackButtonClick(View view);
    }

    class a {
        boolean a;
        InputAutoCompleteView b;
        TextWatcher c;

        a() {
            this.a = false;
            this.c = new ab(this);
        }

        public final void a() {
            if (this.b != null) {
                BrowserTitleBarFragment.this.k.removeTextChangedListener(this.c);
                if (this.a) {
                    InputAutoCompleteView inputAutoCompleteView = this.b;
                    inputAutoCompleteView.startAnimation(AnimationUtils.loadAnimation(BrothersApplication.a().getApplicationContext(), 2131034130));
                    inputAutoCompleteView.setVisibility(XZBDevice.Wait);
                } else if (this.b.getVisibility() == 0) {
                    this.b.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
                }
                this.a = false;
            }
        }
    }

    public BrowserTitleBarFragment() {
        this.q = new y(this);
        this.r = new z(this);
        this.s = new aa(this);
        this.d = false;
        this.e = new a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.f = getArguments().getString(WebBrowserActivity.EXTRA_TITLE);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968765, viewGroup, false);
        this.j = (TextView) inflate.findViewById(2131755975);
        this.j.setOnClickListener(this);
        this.k = (EditText) inflate.findViewById(2131755970);
        this.k.addTextChangedListener(this.r);
        this.k.setOnFocusChangeListener(this.s);
        this.k.setOnEditorActionListener(this.q);
        this.l = (TextView) inflate.findViewById(2131755972);
        this.l.setOnClickListener(this);
        this.i = inflate.findViewById(2131755971);
        this.i.setOnClickListener(this);
        this.b = (ProgressBar) inflate.findViewById(2131755981);
        inflate.findViewById(2131755974).setOnClickListener(this);
        this.o = inflate.findViewById(2131755976);
        this.o.setOnClickListener(this);
        this.p = inflate.findViewById(2131755977);
        this.p.setOnClickListener(this);
        this.o.setVisibility(0);
        this.p.setVisibility(XZBDevice.Wait);
        this.h = (DownloadEntranceView) inflate.findViewById(2131755979);
        this.h.setOnClickListener(this);
        this.c = new com.xunlei.downloadprovider.download.b.a(this.h);
        this.m = inflate.findViewById(2131755973);
        this.n = inflate.findViewById(2131755969);
        return inflate;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            this.g = (b) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.g = null;
    }

    public void onResume() {
        super.onResume();
        if (this.c != null) {
            this.c.a();
        }
    }

    public void onPause() {
        super.onPause();
        if (this.c != null) {
            this.c.b();
        }
        d();
    }

    public void onDestroy() {
        if (this.c != null) {
            this.c.b();
            this.c = null;
        }
        super.onDestroy();
    }

    public final void a(String str) {
        if (str != null) {
            str = str.replace(" - \u767e\u5ea6", com.umeng.a.d);
        }
        a(str, false);
    }

    public final void a(String str, boolean z) {
        this.f = str;
        if (this.j != null) {
            this.j.setText(str);
        }
        if (z) {
            this.a = str;
        }
    }

    public final void a() {
        this.d = false;
        if (!(this.m.getVisibility() == 0 || this.g == null)) {
            this.g.a(false);
        }
        this.m.setVisibility(0);
        this.n.setVisibility(XZBDevice.Wait);
        this.e.a();
    }

    public final boolean b() {
        return this.d;
    }

    private void b(String str) {
        if (TextUtils.isEmpty(str)) {
            this.l.setText(2131232759);
        } else if (com.xunlei.downloadprovider.util.c.a.f(str)) {
            this.l.setText(2131232760);
        } else {
            this.l.setText(2131232761);
        }
    }

    private String e() {
        try {
            return this.k.getText().toString();
        } catch (Exception e) {
            return com.umeng.a.d;
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755971:
                this.k.setText(com.umeng.a.d);
                this.k.requestFocus();
            case 2131755972:
                if (this.l.getText() == null || this.l.getText() != getResources().getText(2131232759)) {
                    String e = e();
                    if (TextUtils.isEmpty(e)) {
                        Toast.makeText(getActivity(), 2131230900, 0).show();
                        return;
                    } else if (this.g != null) {
                        this.g.a(e);
                        return;
                    } else {
                        return;
                    }
                }
                a();
            case 2131755974:
                if (this.g != null) {
                    this.g.onBrowserTitleBarBackButtonClick(view);
                }
            case 2131755975:
                this.d = true;
                if (this.m.getVisibility() == 0) {
                    if (this.g != null) {
                        this.g.a(true);
                    }
                    this.m.setVisibility(XZBDevice.Wait);
                    this.n.setVisibility(0);
                    b(this.a);
                    this.k.setText(this.a == null ? com.umeng.a.d : this.a);
                    this.k.requestFocus();
                    a aVar = this.e;
                    if (aVar.b != null) {
                        boolean z;
                        if (aVar.b.getVisibility() == 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (!z) {
                            aVar.d.k.addTextChangedListener(aVar.c);
                            aVar.b.a();
                            aVar.b.e.clear();
                            InputAutoCompleteView inputAutoCompleteView = aVar.b;
                            inputAutoCompleteView.startAnimation(AnimationUtils.loadAnimation(BrothersApplication.a().getApplicationContext(), 2131034131));
                            inputAutoCompleteView.setVisibility(0);
                            aVar.b.setViewHeight(false);
                            aVar.a = true;
                            aVar.d.k.requestFocus();
                        }
                    }
                    ((InputMethodManager) getActivity().getSystemService("input_method")).showSoftInput(this.k, XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    return;
                }
                this.m.setVisibility(XZBDevice.Wait);
                this.n.setVisibility(0);
                if (!(this.a == null || this.a.equals(this.k.getText()))) {
                    this.k.setText(this.a);
                }
                if (!(!this.k.hasFocus() || this.k.getText() == null || TextUtils.isEmpty(this.k.getText().toString()))) {
                    this.k.selectAll();
                    this.k.scrollTo(0, 0);
                }
                b(this.a);
            case 2131755976:
                if (this.g != null) {
                    this.g.onBrowserRefreshButtonClick(view);
                }
            case 2131755977:
                if (this.g != null) {
                    this.g.onBrowserStopButtonClick(view);
                }
            case 2131755979:
                if (this.h.a()) {
                    d.a();
                    d.o();
                }
                StatReporter.reportDownloadEntryClick("browser");
                DownloadCenterActivity.a(getActivity(), DLCenterEntry.browser.toString());
            default:
                break;
        }
    }

    public final void a(boolean z) {
        if (this.b == null) {
            return;
        }
        if (z) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(XZBDevice.Wait);
        }
    }

    public final void b(boolean z) {
        if (z) {
            this.o.setVisibility(XZBDevice.Wait);
            this.p.setVisibility(0);
            return;
        }
        this.o.setVisibility(0);
        this.p.setVisibility(XZBDevice.Wait);
    }

    public final void c() {
        getView().setVisibility(0);
    }

    public final void d() {
        ((InputMethodManager) getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.k.getWindowToken(), 0);
    }
}
