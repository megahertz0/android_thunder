package com.xunlei.downloadprovider.xiazaibao.remotedownload;

import android.app.Activity;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.g.a;
import com.xunlei.downloadprovider.xiazaibao.view.LoadingMoreRecyclerView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.XZBDownloadTaskSet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.android.spdy.SpdyAgent;

public class RemoteDownloadListFragment extends Fragment implements OnClickListener, OnTouchListener, c {
    LoadingMoreRecyclerView a;
    q b;
    RemoteDownloadListActivity c;
    boolean d;
    boolean e;
    a f;
    a g;
    private int h;
    private ab i;
    private ErrorView j;
    private UnifiedLoadingView k;
    private AtomicInteger l;
    private AtomicBoolean m;
    private AtomicBoolean n;
    private Handler o;
    private Runnable p;
    private d q;
    private e r;
    private LoadingMoreRecyclerView.a s;

    public RemoteDownloadListFragment() {
        this.l = new AtomicInteger(0);
        this.m = new AtomicBoolean(false);
        this.n = new AtomicBoolean(false);
        this.o = new Handler();
        this.p = new r(this);
        this.r = new s(this);
        this.f = new u(this);
        this.s = new v(this);
    }

    static /* synthetic */ void a(RemoteDownloadListFragment remoteDownloadListFragment, boolean z) {
        remoteDownloadListFragment.k.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        remoteDownloadListFragment.a.setVisibility(0);
        if (z) {
            remoteDownloadListFragment.j.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            remoteDownloadListFragment.a.setBackgroundColor(remoteDownloadListFragment.c.getResources().getColor(R.color.common_content_bkg_color));
            return;
        }
        remoteDownloadListFragment.j.setVisibility(0);
        remoteDownloadListFragment.j.setErrorType(0);
        remoteDownloadListFragment.a.setBackgroundColor(remoteDownloadListFragment.c.getResources().getColor(R.color.white));
    }

    static /* synthetic */ void b(RemoteDownloadListFragment remoteDownloadListFragment, int i) {
        remoteDownloadListFragment.k.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        remoteDownloadListFragment.a.setVisibility(0);
        remoteDownloadListFragment.a.setBackgroundColor(remoteDownloadListFragment.c.getResources().getColor(R.color.white));
        remoteDownloadListFragment.j.setVisibility(0);
        if (i == -3 || i == 45) {
            remoteDownloadListFragment.j.a(remoteDownloadListFragment.c.getResources().getDrawable(R.drawable.bg_invalid_network), "\u83b7\u53d6\u4efb\u52a1\u5931\u8d25", "\u8bf7\u68c0\u67e5\u4e0b\u8f7d\u5b9d\u7ed1\u5b9a\u72b6\u6001\u540e\u91cd\u8bd5");
        } else if (i == -4) {
            remoteDownloadListFragment.j.a(remoteDownloadListFragment.c.getResources().getDrawable(R.drawable.bg_invalid_network), "\u8bbe\u5907\u79bb\u7ebf", "\u8bf7\u68c0\u67e5\u4e0b\u8f7d\u5b9d\u7f51\u7edc\u8fde\u63a5\u540e\u91cd\u8bd5");
        } else {
            remoteDownloadListFragment.j.setErrorType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        }
    }

    static /* synthetic */ void h(RemoteDownloadListFragment remoteDownloadListFragment) {
        remoteDownloadListFragment.k.setVisibility(0);
        remoteDownloadListFragment.a.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        remoteDownloadListFragment.j.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
    }

    final void a() {
        if (this.h == 2) {
            this.o.removeCallbacks(this.p);
            d();
            return;
        }
        this.o.removeCallbacks(this.p);
        this.o.postDelayed(this.p, 10);
    }

    public final int b() {
        if (this.c == null) {
            return 10000;
        }
        NetworkInfo activeNetworkInfo;
        int i;
        Object obj;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.c.getSystemService("connectivity");
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                switch (activeNetworkInfo.getType()) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        i = 1;
                        break;
                    default:
                        obj = null;
                        break;
                }
                if (obj != null) {
                    i = 1;
                    if (obj != null) {
                        return 5000;
                    }
                    connectivityManager = (ConnectivityManager) this.c.getSystemService("connectivity");
                    if (connectivityManager != null) {
                        activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        if (activeNetworkInfo != null) {
                            switch (activeNetworkInfo.getType()) {
                                case SpdyAgent.ACCS_TEST_SERVER:
                                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                case XZBDevice.DOWNLOAD_LIST_FAILED:
                                case XZBDevice.DOWNLOAD_LIST_ALL:
                                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                                    i = 1;
                                    break;
                                default:
                                    obj = null;
                                    break;
                            }
                            if (obj != null) {
                                i = 1;
                                return obj == null ? 10000 : 10000;
                            }
                        }
                    }
                    obj = null;
                    if (obj == null) {
                    }
                }
            }
        }
        obj = null;
        if (obj != null) {
            return 5000;
        }
        connectivityManager = (ConnectivityManager) this.c.getSystemService("connectivity");
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                switch (activeNetworkInfo.getType()) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        i = 1;
                        break;
                    default:
                        obj = null;
                        break;
                }
                if (obj != null) {
                    i = 1;
                    if (obj == null) {
                    }
                }
            }
        }
        obj = null;
        if (obj == null) {
        }
    }

    private boolean c() {
        if (this.c == null) {
            return true;
        }
        int i;
        RemoteDownloadListActivity remoteDownloadListActivity = this.c;
        if (remoteDownloadListActivity.a != null) {
            RemoteDownloadContainerFragment remoteDownloadContainerFragment = remoteDownloadListActivity.a;
            if (remoteDownloadContainerFragment.g != null) {
                i = remoteDownloadContainerFragment.g.a;
                return i != this.h;
            }
        }
        i = 0;
        if (i != this.h) {
        }
    }

    public static RemoteDownloadListFragment a(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt("downloadtype", i);
        RemoteDownloadListFragment remoteDownloadListFragment = new RemoteDownloadListFragment();
        remoteDownloadListFragment.setArguments(bundle);
        return remoteDownloadListFragment;
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof RemoteDownloadListActivity) {
            this.c = (RemoteDownloadListActivity) activity;
            this.q = this.c.c;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.h = getArguments().getInt("downloadtype");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968777, viewGroup, false);
        this.i = new ab(this);
        this.b = new q(getActivity(), this.q, this.h);
        this.b.f = this.f;
        this.k = (UnifiedLoadingView) inflate.findViewById(2131755563);
        this.a = (LoadingMoreRecyclerView) inflate.findViewById(2131756067);
        this.a.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.a.setAdapter(this.b);
        this.j = (ErrorView) inflate.findViewById(R.id.emptyView);
        this.a.setVisibility(0);
        this.j.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.j.setActionButtonListener(new b(this, (byte) 0));
        this.a.setFootEnable(true);
        this.a.setLoadMoreListener(this.s);
        this.a.setOnTouchListener(this);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        new Handler().postDelayed(new t(this), (long) (this.h * 100));
    }

    public void onClick(View view) {
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        motionEvent.getAction();
        if (motionEvent.getAction() == 1) {
            this.e = true;
        }
        return false;
    }

    public void onStop() {
        super.onStop();
        this.e = false;
    }

    public void onStart() {
        super.onStart();
        this.e = true;
    }

    private static int b(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                return XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                return 1;
            default:
                return XZBDevice.DOWNLOAD_LIST_ALL;
        }
    }

    private void d() {
        int incrementAndGet = this.l.incrementAndGet();
        ab abVar = this.i;
        abVar.a.c(incrementAndGet, b(this.h), this.r);
    }

    static /* synthetic */ void e(RemoteDownloadListFragment remoteDownloadListFragment) {
        int incrementAndGet = remoteDownloadListFragment.l.incrementAndGet();
        ab abVar = remoteDownloadListFragment.i;
        abVar.a.a(incrementAndGet, remoteDownloadListFragment.r);
    }

    static /* synthetic */ boolean a(RemoteDownloadListFragment remoteDownloadListFragment, int i) {
        return remoteDownloadListFragment.l.get() == i;
    }

    static /* synthetic */ void g(RemoteDownloadListFragment remoteDownloadListFragment) {
        remoteDownloadListFragment.o.removeCallbacks(remoteDownloadListFragment.p);
        remoteDownloadListFragment.o.postDelayed(remoteDownloadListFragment.p, (long) remoteDownloadListFragment.b());
    }

    static /* synthetic */ void a(RemoteDownloadListFragment remoteDownloadListFragment, int i, XZBDownloadTaskSet xZBDownloadTaskSet) {
        if (remoteDownloadListFragment.c() && i == 0 && xZBDownloadTaskSet != null) {
            remoteDownloadListFragment.q.a(xZBDownloadTaskSet.getSpeedCount(), xZBDownloadTaskSet.getSpeedupCount());
            new StringBuilder("notifyTitleStatusChange downloadingCount = ").append(xZBDownloadTaskSet.getDowloadingNum() + xZBDownloadTaskSet.getServerFailNum()).append(" downloadedCount = ").append(xZBDownloadTaskSet.getCompleteNum()).append(" speed = ").append(xZBDownloadTaskSet.getSpeedCount());
        }
        if (remoteDownloadListFragment.g != null && remoteDownloadListFragment.c()) {
            remoteDownloadListFragment.g.a(i, xZBDownloadTaskSet);
        }
    }

    static /* synthetic */ void l(RemoteDownloadListFragment remoteDownloadListFragment) {
        int incrementAndGet = remoteDownloadListFragment.l.incrementAndGet();
        ab abVar = remoteDownloadListFragment.i;
        abVar.a.a(incrementAndGet, b(remoteDownloadListFragment.h), remoteDownloadListFragment.r);
    }

    static /* synthetic */ void o(RemoteDownloadListFragment remoteDownloadListFragment) {
        int incrementAndGet = remoteDownloadListFragment.l.incrementAndGet();
        ab abVar = remoteDownloadListFragment.i;
        abVar.a.b(incrementAndGet, b(remoteDownloadListFragment.h), remoteDownloadListFragment.r);
    }
}
