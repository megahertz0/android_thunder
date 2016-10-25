package com.xunlei.downloadprovider.homepage.relax;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.nostra13.universalimageloader.core.c;
import com.tencent.connect.common.Constants;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.a.h;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.b.c.e;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.frame.BasePageFragment;
import com.xunlei.downloadprovider.frame.af;
import com.xunlei.downloadprovider.homepage.j;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.RelaxDataType;
import com.xunlei.downloadprovider.model.protocol.b.d;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovidershare.ba;
import com.xunlei.downloadprovidershare.d.a;
import com.xunlei.downloadprovidershare.data.ShareBean;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class RelaxListFragment extends BasePageFragment implements af, a {
    public static List<d> a;
    public static com.xunlei.downloadprovider.homepage.relax.d.a b;
    private final String c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private c i;
    private PullToRefreshListView j;
    private UnifiedLoadingView k;
    private boolean l;
    private h.a m;
    private b n;
    private View o;
    private ErrorView p;
    private com.xunlei.downloadprovider.homepage.relax.d.a.a q;
    private com.xunlei.downloadprovider.homepage.relax.b.a.a r;

    public RelaxListFragment() {
        this.c = RelaxListFragment.class.getSimpleName();
        this.d = 1000;
        this.e = 0;
        this.f = 1;
        this.g = 2;
        this.h = 10;
        this.l = true;
        this.m = new a(this);
        this.n = new b(this.m);
        this.q = new b(this);
        this.r = new c(this);
    }

    static /* synthetic */ void a(Message message) {
        if (message.arg1 == 0 && message.obj != null && ((Integer) message.obj).intValue() == 0) {
            com.xunlei.downloadprovider.homepage.relax.d.a aVar = b;
            if (aVar.c != null) {
                aVar.c.p.setSelected(true);
                aVar.c.q.setSelected(true);
            }
        }
    }

    static /* synthetic */ boolean a(RelaxListFragment relaxListFragment, long j, String str) {
        if (com.xunlei.xllib.a.b.a(BrothersApplication.a())) {
            com.xunlei.downloadprovider.model.protocol.c.b.c cVar = new com.xunlei.downloadprovider.model.protocol.c.b.c(relaxListFragment.n);
            StringBuilder stringBuilder = new StringBuilder("http://comment.m.xunlei.com/cgi-bin/userMark?");
            stringBuilder.append(com.xunlei.downloadprovider.model.protocol.c.c.a());
            stringBuilder.append("&resourceID=").append(j);
            stringBuilder.append("&from=").append(str);
            stringBuilder.append("&op=").append("good");
            e aVar = new com.xunlei.downloadprovider.b.c.a(stringBuilder.toString(), Constants.HTTP_GET, null, null, null, new com.xunlei.downloadprovider.model.protocol.c.c.d(), 10000, 10000, 1);
            aVar.setBpOnDataLoaderCompleteListener(new com.xunlei.downloadprovider.model.protocol.c.b.d(cVar));
            cVar.setBpFuture(aVar);
            com.xunlei.downloadprovider.model.protocol.c.b.c.runBox(cVar);
            return true;
        }
        relaxListFragment.k.b();
        relaxListFragment.b(BrothersApplication.a().getString(2131231758));
        return false;
    }

    static {
        a = new ArrayList();
    }

    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        new StringBuilder().append(getClass()).append("---createView---").append(Thread.currentThread().getId());
        this.o = layoutInflater.inflate(2130968928, viewGroup, false);
        b = new com.xunlei.downloadprovider.homepage.relax.d.a(getActivity(), a);
        this.j = (PullToRefreshListView) this.o.findViewById(2131756802);
        this.j.setMode(Mode.PULL_FROM_START);
        this.j.a = true;
        this.j.setScrollBar(R.drawable.common_scroll_bar);
        this.j.setFooterNeedShow(true);
        this.j.setAdapter(b);
        this.j.setOnRefreshListener(new d(this));
        this.k = (UnifiedLoadingView) this.o.findViewById(2131755563);
        this.p = (ErrorView) this.o.findViewById(2131756034);
        this.p.setErrorType(0);
        this.p.setActionButtonListener(new e(this));
        this.k.setOnClickListener(null);
        this.p.setOnClickListener(null);
        this.k.a();
        b();
        if (this.i == null) {
            this.i = ((BaseActivity) getActivity()).options;
            b.b = this.i;
            b.a = this.q;
        }
        return this.o;
    }

    private void b() {
        long j;
        long j2 = 0;
        if (a.size() > 0) {
            d dVar = (d) a.get(0);
            j = dVar.a;
            j2 = dVar.j;
        } else {
            j = 0;
        }
        RelaxDataManager.a().a(RelaxDataType.RES, GuestureType.TOP, this.r, j, j2);
    }

    private void a(int i) {
        if (a.size() == 0) {
            if (this.p.getVisibility() != 0) {
                this.p.setVisibility(0);
            }
            if (com.xunlei.xllib.a.b.a(getApplicationContext())) {
                this.p.setErrorType(0);
            } else {
                this.p.setErrorType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            }
        } else if (this.l) {
            String str = null;
            if (i == 0) {
                str = BrothersApplication.a().getString(2131232893);
            } else if (i == 1) {
                str = BrothersApplication.a().getString(2131232372);
            } else if (i == 2) {
                str = BrothersApplication.a().getString(2131232189);
            }
            b(str);
        }
        this.k.b();
        this.j.m();
    }

    private static String a(String str) {
        try {
            com.xunlei.downloadprovider.d.d.a(new HashMap(), str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format("http://m.sjzhushou.com/ios_page/publish/share_page/share_data.html?fr=duanzi&md5=%s&restype=%s", new Object[]{r1.get("md5"), r1.get("restype")});
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onPause() {
        super.onPause();
        this.n.removeMessages(3109);
        this.n.removeMessages(3110);
        this.n.removeMessages(IHost.HOST_NOFITY_REFRESH_LIST);
        a(com.umeng.a.d);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        com.xunlei.downloadprovidershare.d.b().a(i, i2, intent);
    }

    public void onMainTabClick(boolean z) {
        super.onMainTabClick(z);
        if (!z) {
            c();
        }
    }

    private void b(String str) {
        Context activity = getActivity();
        if (activity != null && !com.xunlei.xllib.a.b.a(activity)) {
            XLToast.b(this.mActivity, XLToastType.XLTOAST_TYPE_ALARM, str);
        }
    }

    public void onUserVisible(boolean z) {
        super.onUserVisible(z);
        j.b();
    }

    public void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
        ThunderReporter.c.a(shareBean.e, ba.a(share_media, shareBean), String.valueOf(shareBean.k));
    }

    public void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        ThunderReporter.c.a(shareBean.e, ba.a(share_media, shareBean), com.xunlei.downloadprovidershare.d.a(i), i, String.valueOf(shareBean.k));
    }

    public final void a() {
        c();
    }

    private void c() {
        if (this.j != null && this.j.getRefreshableView() != null) {
            this.j.o();
        }
    }

    static /* synthetic */ void a(RelaxListFragment relaxListFragment, Message message) {
        if (message.arg1 == 0 && message.obj != null) {
            com.xunlei.downloadprovider.model.protocol.c.c.c cVar = (com.xunlei.downloadprovider.model.protocol.c.c.c) message.obj;
            if (cVar.a == 0) {
                Object obj = (cVar.c == null || cVar.c.size() == 0) ? 1 : null;
                if (obj == null) {
                    for (com.xunlei.downloadprovider.model.protocol.c.a aVar : cVar.c) {
                        for (d dVar : a) {
                            if (aVar.a == dVar.a) {
                                dVar.r = aVar;
                                break;
                            }
                        }
                    }
                }
            }
        }
        b.notifyDataSetChanged();
        relaxListFragment.j.m();
        relaxListFragment.k.b();
    }

    static /* synthetic */ void a(RelaxListFragment relaxListFragment, com.xunlei.downloadprovider.homepage.relax.b.c cVar) {
        if (cVar != null) {
            int i = cVar.a;
            GuestureType guestureType = cVar.c;
            Collection<d> collection = cVar.d;
            if (relaxListFragment.p.getVisibility() != 8) {
                relaxListFragment.p.setVisibility(XZBDevice.Wait);
            }
            if (i == 2) {
                relaxListFragment.a(0);
                return;
            }
            if (guestureType == GuestureType.TOP && cVar.b == RelaxDataType.FAVOR) {
                a.clear();
            }
            if (i != 1) {
                if (guestureType == GuestureType.TOP) {
                    a.addAll(0, collection);
                } else if (guestureType == GuestureType.BOTTOM) {
                    a.addAll(collection);
                }
                int i2 = ((d) collection.get(0)).g;
                if (com.xunlei.xllib.a.b.a(BrothersApplication.a())) {
                    StringBuffer stringBuffer = new StringBuffer(com.umeng.a.d);
                    for (d dVar : collection) {
                        stringBuffer.append(dVar.a + MiPushClient.ACCEPT_TIME_SEPARATOR);
                    }
                    String str = com.umeng.a.d;
                    if (i2 == 0) {
                        str = "joy_picture";
                    }
                    com.xunlei.downloadprovider.model.protocol.c.b.a aVar = new com.xunlei.downloadprovider.model.protocol.c.b.a(relaxListFragment.n);
                    String toString = stringBuffer.toString();
                    StringBuilder stringBuilder = new StringBuilder("http://comment.m.xunlei.com/cgi-bin/queryMark?");
                    stringBuilder.append(com.xunlei.downloadprovider.model.protocol.c.c.a());
                    stringBuilder.append("&resourceID=").append(toString);
                    stringBuilder.append("&from=").append(str);
                    stringBuilder.append("&type=good,play,down,comment,commentNum");
                    stringBuilder.append("&maxNum=2");
                    e aVar2 = new com.xunlei.downloadprovider.b.c.a(stringBuilder.toString(), Constants.HTTP_GET, null, null, null, new com.xunlei.downloadprovider.model.protocol.c.c.b(), 10000, 10000, 1);
                    aVar2.setBpOnDataLoaderCompleteListener(new com.xunlei.downloadprovider.model.protocol.c.b.b(aVar));
                    aVar.setBpFuture(aVar2);
                    com.xunlei.downloadprovider.model.protocol.c.b.a.runBox(aVar);
                    return;
                }
                relaxListFragment.k.b();
                b.notifyDataSetChanged();
                relaxListFragment.j.m();
            } else if (guestureType == GuestureType.TOP) {
                relaxListFragment.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            } else {
                relaxListFragment.a(1);
            }
        }
    }

    static /* synthetic */ ShareBean a(d dVar) {
        String str;
        String str2 = "fun_home";
        String a = a(dVar.c);
        String str3 = dVar.d;
        CharSequence charSequence = dVar.b;
        if (TextUtils.isEmpty(charSequence)) {
            charSequence = dVar.i;
        }
        if (TextUtils.isEmpty(charSequence)) {
            str = "\u8da3\u56fe";
        } else {
            CharSequence charSequence2 = charSequence;
        }
        ShareBean shareBean = new ShareBean(str2, a, str3, str, com.umeng.a.d);
        shareBean.k = dVar.a;
        return shareBean;
    }

    static /* synthetic */ void a(RelaxListFragment relaxListFragment, d dVar) {
        new StringBuilder().append(relaxListFragment.getClass()).append("---showPicBrowse---").append(Thread.currentThread().getId());
        if (!com.xunlei.xllib.a.b.a(BrothersApplication.a())) {
            Object obj;
            com.xunlei.downloadprovider.homepage.relax.c.a.a();
            String str = dVar.e;
            if (TextUtils.isEmpty(str) || !new File(com.xunlei.downloadprovider.homepage.relax.c.a.a(str)).exists()) {
                obj = null;
            } else {
                obj = 1;
            }
            if (obj == null) {
                if (!com.xunlei.xllib.a.b.a(BrothersApplication.a())) {
                    relaxListFragment.k.b();
                    relaxListFragment.b(BrothersApplication.a().getString(2131231758));
                    return;
                }
                return;
            }
        }
        RelaxPicBrowseActivity.a(BrothersApplication.a(), dVar);
    }

    static /* synthetic */ void e(RelaxListFragment relaxListFragment) {
        long j;
        long j2 = 0;
        if (a.size() > 0) {
            d dVar = (d) a.get(a.size() - 1);
            j = dVar.a;
            j2 = dVar.j;
        } else {
            j = 0;
        }
        RelaxDataManager.a().a(RelaxDataType.RES, GuestureType.BOTTOM, relaxListFragment.r, j, j2);
    }
}
