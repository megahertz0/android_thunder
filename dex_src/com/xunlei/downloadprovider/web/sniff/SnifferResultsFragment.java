package com.xunlei.downloadprovider.web.sniff;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebBackForwardList;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.Sniff;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.web.sniff.m.a;
import com.xunlei.downloadprovider.web.sniff.widget.AnimProgressBar;
import com.xunlei.downloadprovider.web.sniff.widget.RiseNumberTextView;
import com.xunlei.downloadprovider.web.sniff.widget.SniffMask;
import com.xunlei.downloadprovider.web.sniff.widget.SniffResultLayout;
import com.xunlei.downloadprovider.web.sniff.widget.SuffixListView;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.sniff.SniffingPageResource;
import com.xunlei.thundersniffer.sniff.SniffingResource;
import com.xunlei.thundersniffer.sniff.SniffingResourceGroup;
import com.xunlei.thundersniffer.sniff.misc.ResourceOperationListener;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.ArrayList;
import java.util.List;

public class SnifferResultsFragment extends Fragment {
    private View A;
    private View B;
    private View C;
    private View D;
    private Button E;
    private TextView F;
    private TextView G;
    private View H;
    private SuffixListView I;
    private View J;
    private View K;
    private LayoutInflater L;
    private r M;
    private List<String> N;
    private boolean O;
    private String P;
    private ArrayList<Integer> Q;
    private int R;
    private c S;
    private Handler T;
    private a U;
    private ResourceOperationListener V;
    private int W;
    private int X;
    private String Y;
    private boolean Z;
    public SniffResultLayout a;
    private int aa;
    private OnClickListener ab;
    private OnItemClickListener ac;
    private a ad;
    public ListView b;
    public com.xunlei.downloadprovider.web.sniff.a.a c;
    public m d;
    public b e;
    String f;
    boolean g;
    public d h;
    private final int i;
    private final int j;
    private final int k;
    private final int l;
    private final int m;
    private final int n;
    private final int o;
    private final int p;
    private final int q;
    private final int r;
    private SniffMask s;
    private ListView t;
    private AnimProgressBar u;
    private View v;
    private RiseNumberTextView w;
    private View x;
    private View y;
    private RiseNumberTextView z;

    public static interface b {
        void a(float f);

        void a(int i, int i2);

        void a(int i, SniffingResourceGroup sniffingResourceGroup);

        void a(SniffingResource sniffingResource);

        void a(String str, String str2);

        void a(boolean z);

        void b();

        void b(SniffingResource sniffingResource);

        void b(boolean z);

        void c();

        void d();
    }

    public static interface d {
        void a();

        void a(float f);

        void a(int i);

        void a(WebBackForwardList webBackForwardList);

        void a(SniffingResourceGroup sniffingResourceGroup, String str);

        void a(String str);

        void a(String str, SniffingPageResource sniffingPageResource);

        void a(String str, String str2);

        void a(boolean z);

        void b();

        void b(WebBackForwardList webBackForwardList);

        void b(String str, String str2);

        void c();
    }

    public SnifferResultsFragment() {
        this.i = 0;
        this.j = 1;
        this.k = 2;
        this.l = 3;
        this.m = 4;
        this.n = 6;
        this.o = 11;
        this.p = 7;
        this.q = 12;
        this.r = 13;
        if (com.xunlei.downloadprovider.web.sniff.a.a.b == null) {
            com.xunlei.downloadprovider.web.sniff.a.a.b = new com.xunlei.downloadprovider.web.sniff.a.a();
        }
        this.c = com.xunlei.downloadprovider.web.sniff.a.a.b;
        this.R = -1;
        this.S = null;
        this.T = new c(this);
        this.U = new a(this);
        this.V = new d(this);
        this.W = -1;
        this.X = -1;
        this.Y = com.umeng.a.d;
        this.f = com.umeng.a.d;
        this.h = new e(this);
        this.ab = new f(this);
        this.ac = new g(this);
        this.ad = new h(this);
    }

    static /* synthetic */ void a(SnifferResultsFragment snifferResultsFragment, int i) {
        snifferResultsFragment.R = i;
        snifferResultsFragment.Q.add(Integer.valueOf(i));
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                snifferResultsFragment.S = new e(snifferResultsFragment, (byte) 0);
            case XZBDevice.DOWNLOAD_LIST_ALL:
                snifferResultsFragment.S = new f(snifferResultsFragment, (byte) 0);
            case R.styleable.Toolbar_contentInsetEnd:
                snifferResultsFragment.S = new k(snifferResultsFragment, (byte) 0);
            case R.styleable.Toolbar_contentInsetLeft:
                snifferResultsFragment.S = new l(snifferResultsFragment, (byte) 0);
            case XZBDevice.Success:
                snifferResultsFragment.S = new i(snifferResultsFragment, (byte) 0);
            case XZBDevice.Fail:
                snifferResultsFragment.S = new h(snifferResultsFragment, (byte) 0);
            case XZBDevice.Upload:
                snifferResultsFragment.S = new n(snifferResultsFragment, (byte) 0);
            default:
                snifferResultsFragment.S = null;
        }
    }

    static /* synthetic */ int r(SnifferResultsFragment snifferResultsFragment) {
        int i = snifferResultsFragment.aa;
        snifferResultsFragment.aa = i + 1;
        return i;
    }

    static /* synthetic */ void w(SnifferResultsFragment snifferResultsFragment) {
        if (snifferResultsFragment.z.getAlpha() != 1.0f) {
            snifferResultsFragment.z.setText(snifferResultsFragment.w.getText().toString());
            snifferResultsFragment.z.setAlpha(1.0f);
            snifferResultsFragment.A.setAlpha(1.0f);
            snifferResultsFragment.B.setAlpha(1.0f);
            snifferResultsFragment.w.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            snifferResultsFragment.x.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
        }
        if (snifferResultsFragment.e != null) {
            snifferResultsFragment.e.b(false);
        }
    }

    static /* synthetic */ void y(SnifferResultsFragment snifferResultsFragment) {
        if (snifferResultsFragment.z.getAlpha() != 0.0f) {
            snifferResultsFragment.z.setText(snifferResultsFragment.w.getText().toString());
            snifferResultsFragment.z.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            snifferResultsFragment.A.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            snifferResultsFragment.B.setAlpha(AutoScrollHelper.RELATIVE_UNSPECIFIED);
            snifferResultsFragment.w.setAlpha(1.0f);
            snifferResultsFragment.x.setAlpha(1.0f);
        }
        if (snifferResultsFragment.e != null) {
            snifferResultsFragment.e.b(true);
        }
    }

    static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return com.umeng.a.d;
        }
        return str.contains(" ") ? str.substring(0, str.indexOf(" ")) : str;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968774, viewGroup, false);
        this.v = inflate.findViewById(2131756049);
        this.v.setOnClickListener(this.ab);
        this.x = inflate.findViewById(2131756057);
        this.A = inflate.findViewById(2131756051);
        this.y = inflate.findViewById(2131756052);
        this.y.setOnClickListener(new i(this));
        this.t = (ListView) inflate.findViewById(2131756060);
        this.b = (ListView) inflate.findViewById(2131756058);
        this.t.setOnItemClickListener(this.ac);
        this.C = inflate.findViewById(2131756063);
        this.L = LayoutInflater.from(getActivity());
        this.H = inflate.findViewById(2131756059);
        this.I = (SuffixListView) this.H.findViewById(2131757018);
        SuffixListView.a(this.U);
        this.J = this.H.findViewById(2131757019);
        this.s = (SniffMask) inflate.findViewById(2131756047);
        this.s.setMaskClickistener(new o(this, (byte) 0));
        this.a = (SniffResultLayout) inflate.findViewById(2131756048);
        this.a.setListener(new k(this));
        this.w = (RiseNumberTextView) inflate.findViewById(2131756054);
        this.w.setText(getActivity().getString(2131232731));
        this.w.setSpeed(TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.z = (RiseNumberTextView) inflate.findViewById(2131756055);
        this.B = inflate.findViewById(2131756050);
        this.u = (AnimProgressBar) inflate.findViewById(2131756056);
        this.D = inflate.findViewById(2131756066);
        this.F = (TextView) inflate.findViewById(2131756989);
        this.G = (TextView) inflate.findViewById(2131756990);
        this.E = (Button) this.D.findViewById(2131756991);
        this.K = inflate.findViewById(2131756061);
        this.E.setOnClickListener(new j(this));
        return inflate;
    }

    public void onResume() {
        super.onResume();
        a();
    }

    public final void a() {
        if (this.d != null) {
            this.d.notifyDataSetChanged();
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.O = false;
        this.Q = new ArrayList();
        this.N = new ArrayList();
        this.R = -1;
    }

    public void onDetach() {
        super.onDetach();
        this.Q.clear();
        this.e = null;
        this.d = null;
        this.M = null;
        this.c.a.clear();
        SuffixListView.b(this.U);
    }

    static /* synthetic */ void c(SnifferResultsFragment snifferResultsFragment) {
        if (snifferResultsFragment.S != null) {
            snifferResultsFragment.S.a();
        }
    }

    static /* synthetic */ boolean o(SnifferResultsFragment snifferResultsFragment) {
        return snifferResultsFragment.X != snifferResultsFragment.W;
    }

    static /* synthetic */ void c(SnifferResultsFragment snifferResultsFragment, String str) {
        String str2;
        String str3;
        String str4 = com.umeng.a.d;
        Object b = snifferResultsFragment.c.b(str);
        if (snifferResultsFragment.t.getVisibility() != 0 && snifferResultsFragment.b.getVisibility() != 0) {
            str2 = "notResult";
        } else if (snifferResultsFragment.t.getVisibility() != 0 || snifferResultsFragment.b.getVisibility() == 0) {
            str2 = "file";
        } else {
            str2 = "folder";
        }
        if (b == null) {
            str3 = "notResult";
        } else {
            if (!(b instanceof SniffingResourceGroup)) {
                if (!(b instanceof SniffingPageResource)) {
                    str3 = str4;
                } else if (((SniffingPageResource) b).isGrouped) {
                    str3 = "folder";
                }
            }
            str3 = "file";
        }
        String str5 = "sniff_5_back";
        Sniff.a(g.a("android_sniff", str5, str5).a("back_to", str3, (int) XZBDevice.DOWNLOAD_LIST_FAILED).a("back_from", str2, (int) XZBDevice.DOWNLOAD_LIST_FAILED));
    }

    static /* synthetic */ void a(SnifferResultsFragment snifferResultsFragment, SniffingResource sniffingResource) {
        if (snifferResultsFragment.e != null) {
            snifferResultsFragment.e.a(sniffingResource);
        }
    }

    static /* synthetic */ void b(SnifferResultsFragment snifferResultsFragment, SniffingResource sniffingResource) {
        if (snifferResultsFragment.e != null) {
            snifferResultsFragment.e.b(sniffingResource);
        }
    }

    static /* synthetic */ int d(SnifferResultsFragment snifferResultsFragment, int i) {
        int size = (snifferResultsFragment.Q.size() - 1) - i;
        return size < 0 ? -1 : ((Integer) snifferResultsFragment.Q.get(size)).intValue();
    }
}
