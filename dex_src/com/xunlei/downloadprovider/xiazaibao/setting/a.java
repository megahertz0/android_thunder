package com.xunlei.downloadprovider.xiazaibao.setting;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.uc.addon.sdk.remote.Tabs;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;
import com.xunlei.xiazaibao.shoulei.DownloadPathMsg;
import com.xunlei.xiazaibao.shoulei.DownloadPathType;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;
import org.android.spdy.SpdyAgent;

// compiled from: DownloadDeviceSettingFragment.java
public class a extends Fragment implements OnClickListener, d {
    private static final String a;
    private b b;
    private ImageView c;
    private TextView d;
    private RelativeLayout e;
    private RelativeLayout f;
    private RelativeLayout g;
    private TextView h;
    private TextView i;
    private ColorStateList j;
    private ImageView k;
    private ImageView l;
    private ImageView m;
    private TextView n;
    private TextView o;
    private RelativeLayout p;
    private TextView q;
    private TextView r;
    private DownloadDevieSettingActivity s;
    private int t;

    static {
        a = a.class.getSimpleName();
    }

    public a() {
        this.t = 0;
    }

    public static a a() {
        Bundle bundle = new Bundle();
        a aVar = new a();
        aVar.setArguments(bundle);
        return aVar;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.b = new b(this);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.s = (DownloadDevieSettingActivity) getActivity();
        this.j = getResources().getColorStateList(R.color.TextAppearanceEntrySecondaryTitle);
        View inflate = layoutInflater.inflate(com.xunlei.xiazaibao.R.layout.fragment_downloaddevicesetting, viewGroup, false);
        this.c = (ImageView) inflate.findViewById(com.xunlei.xiazaibao.R.id.titlebar_left);
        this.c.setOnClickListener(this);
        this.d = (TextView) inflate.findViewById(com.xunlei.xiazaibao.R.id.titlebar_title);
        this.d.setText(2131232665);
        this.e = (RelativeLayout) inflate.findViewById(com.xunlei.xiazaibao.R.id.layout_downloaddevice_mobile);
        this.e.setOnClickListener(this);
        this.f = (RelativeLayout) inflate.findViewById(com.xunlei.xiazaibao.R.id.layout_downloaddevice_xzb);
        this.h = (TextView) this.f.findViewById(com.xunlei.xiazaibao.R.id.tv_downloaddevice_xzb_name);
        this.f.setOnClickListener(this);
        this.g = (RelativeLayout) inflate.findViewById(com.xunlei.xiazaibao.R.id.layout_downloaddevice_mobileandxzb);
        this.i = (TextView) inflate.findViewById(com.xunlei.xiazaibao.R.id.img_downloaddevice_mobileandxzb_icon);
        this.g.setOnClickListener(this);
        this.k = (ImageView) inflate.findViewById(com.xunlei.xiazaibao.R.id.img_downloaddevice_mobile_select);
        this.l = (ImageView) inflate.findViewById(com.xunlei.xiazaibao.R.id.img_downloaddevice_xzb_select);
        this.m = (ImageView) inflate.findViewById(com.xunlei.xiazaibao.R.id.img_downloaddevice_mobileandxzb_select);
        this.n = (TextView) inflate.findViewById(com.xunlei.xiazaibao.R.id.tv_downloaddevice_xzb_status_one);
        this.o = (TextView) inflate.findViewById(com.xunlei.xiazaibao.R.id.tv_downloaddevice_xzb_status_two);
        this.p = (RelativeLayout) inflate.findViewById(com.xunlei.xiazaibao.R.id.layout_downloaddevice_select_xzb);
        this.p.setOnClickListener(this);
        this.r = (TextView) inflate.findViewById(com.xunlei.xiazaibao.R.id.tv_downloaddevice_xzbname);
        this.p.setVisibility(XZBDevice.Wait);
        this.q = (TextView) inflate.findViewById(com.xunlei.xiazaibao.R.id.tv_downloaddevicesetting_select_xzbtips);
        f();
        DownloadPathMsg downloadPathTypeMsg = XZBShouleiUtil.getInstance().getDownloadPathTypeMsg();
        if (downloadPathTypeMsg.getDownloadPathType() == DownloadPathType.MOBILE) {
            this.k.setSelected(true);
            this.l.setSelected(false);
            this.m.setSelected(false);
        } else if (downloadPathTypeMsg.getDownloadPathType() == DownloadPathType.XZB) {
            this.k.setSelected(false);
            this.l.setSelected(true);
            this.m.setSelected(false);
        } else if (downloadPathTypeMsg.getDownloadPathType() == DownloadPathType.MOBILE_XZB) {
            this.k.setSelected(false);
            this.l.setSelected(false);
            this.m.setSelected(true);
        }
        c();
        h();
        return inflate;
    }

    public void onResume() {
        super.onResume();
        XZBDevice downloadPathDetailMsg = XZBShouleiUtil.getInstance().getDownloadPathTypeMsg().getDownloadPathDetailMsg();
        if (downloadPathDetailMsg == null) {
            downloadPathDetailMsg = XZBShouleiUtil.getInstance().getDefaultDevice();
        }
        if (downloadPathDetailMsg == null || TextUtils.isEmpty(downloadPathDetailMsg.getDeviceName())) {
            f();
        } else {
            this.r.setText(downloadPathDetailMsg.getDeviceName());
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == 2131755660) {
            this.s.finish();
        } else if (id == 2131756003) {
            XZBReporter.a(1);
            f();
            if (this.n.getVisibility() != 0) {
                if (!(TextUtils.equals(this.n.getText().toString(), getString(2131232661)) && TextUtils.equals(this.n.getText().toString(), getString(2131232654)) && TextUtils.equals(this.n.getText().toString(), getString(2131232655)))) {
                    e();
                }
            }
            this.k.setSelected(true);
            this.l.setSelected(false);
            this.m.setSelected(false);
            b.a(DownloadPathType.MOBILE, null);
        } else if (id == 2131756007) {
            XZBReporter.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            g();
            this.k.setSelected(false);
            this.l.setSelected(true);
            this.m.setSelected(false);
            downloadPathDetailMsg = XZBShouleiUtil.getInstance().getDownloadPathTypeMsg().getDownloadPathDetailMsg();
            if (downloadPathDetailMsg == null) {
                downloadPathDetailMsg = XZBShouleiUtil.getInstance().getDefaultDevice();
            }
            e();
            b.a(DownloadPathType.XZB, downloadPathDetailMsg);
        } else if (id == 2131756014) {
            XZBReporter.a((int) XZBDevice.DOWNLOAD_LIST_FAILED);
            g();
            this.k.setSelected(false);
            this.l.setSelected(false);
            this.m.setSelected(true);
            downloadPathDetailMsg = XZBShouleiUtil.getInstance().getDownloadPathTypeMsg().getDownloadPathDetailMsg();
            if (downloadPathDetailMsg == null) {
                downloadPathDetailMsg = XZBShouleiUtil.getInstance().getDefaultDevice();
            }
            e();
            b.a(DownloadPathType.MOBILE_XZB, downloadPathDetailMsg);
        } else if (id == 2131756011 || id == 2131756018) {
            h();
        } else if (id == 2131756022) {
            this.s.startActivity(new Intent(this.s, SelectXZBDeviceActivity.class));
        }
    }

    public final void b() {
        d();
        if (this.t > 0) {
            this.n.setText(2131232663);
            this.o.setText(2131232663);
            return;
        }
        this.n.setText(2131232656);
        this.o.setText(2131232656);
    }

    public final void a(int i, XZBDevice[] xZBDeviceArr) {
        switch (i) {
            case Tabs.TAB_CREATE_REACH_MAX_COUNT:
                c();
                f();
                d();
                this.n.setText(2131232661);
                this.o.setText(2131232661);
                this.k.setSelected(true);
                this.m.setSelected(false);
                this.l.setSelected(false);
            case SpdyAgent.ACCS_TEST_SERVER:
                XZBLog.d(a, "\u83b7\u53d6\u5230\u8bbe\u5907");
                if (xZBDeviceArr == null || xZBDeviceArr.length <= 0) {
                    XZBLog.d(a, "\u65e0\u8bbe\u5907");
                    c();
                    f();
                    d();
                    this.n.setText(2131232661);
                    this.o.setText(2131232661);
                    this.k.setSelected(true);
                    this.m.setSelected(false);
                    this.l.setSelected(false);
                    return;
                }
                if (xZBDeviceArr.length > 1) {
                    this.p.setVisibility(0);
                    this.r.setText(XZBShouleiUtil.getInstance().getDefaultDevice().getDeviceName());
                }
                this.f.setClickable(true);
                this.g.setClickable(true);
                this.h.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                this.i.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                e();
                DownloadPathMsg downloadPathTypeMsg = XZBShouleiUtil.getInstance().getDownloadPathTypeMsg();
                if (downloadPathTypeMsg.getDownloadPathType() == DownloadPathType.MOBILE) {
                    this.k.setSelected(true);
                    this.l.setSelected(false);
                    this.m.setSelected(false);
                    f();
                } else if (downloadPathTypeMsg.getDownloadPathType() == DownloadPathType.XZB) {
                    this.k.setSelected(false);
                    this.l.setSelected(true);
                    this.m.setSelected(false);
                    g();
                    this.r.setText(downloadPathTypeMsg.getDownloadPathDetailMsg() != null ? downloadPathTypeMsg.getDownloadPathDetailMsg().getDeviceName() : XZBShouleiUtil.getInstance().getDefaultDevice().getDeviceName());
                } else if (downloadPathTypeMsg.getDownloadPathType() == DownloadPathType.MOBILE_XZB) {
                    g();
                    this.k.setSelected(false);
                    this.l.setSelected(false);
                    this.m.setSelected(true);
                    this.r.setText(downloadPathTypeMsg.getDownloadPathDetailMsg() != null ? downloadPathTypeMsg.getDownloadPathDetailMsg().getDeviceName() : XZBShouleiUtil.getInstance().getDefaultDevice().getDeviceName());
                }
            default:
                c();
                f();
                d();
                this.k.setSelected(true);
                this.l.setSelected(false);
                this.m.setSelected(false);
                if (this.t < 2) {
                    this.t++;
                    h();
                } else if (i == -1) {
                    this.n.setText(2131232654);
                    this.o.setText(2131232654);
                } else {
                    this.n.setText(2131232655);
                    this.o.setText(2131232655);
                }
        }
    }

    private void c() {
        this.f.setClickable(false);
        this.g.setClickable(false);
        this.h.setTextColor(this.j);
        this.i.setTextColor(this.j);
    }

    private void d() {
        this.n.setVisibility(0);
        this.o.setVisibility(0);
    }

    private void e() {
        this.n.setVisibility(XZBDevice.Wait);
        this.o.setVisibility(XZBDevice.Wait);
    }

    private void f() {
        this.p.setVisibility(XZBDevice.Wait);
        this.q.setVisibility(XZBDevice.Wait);
    }

    private void g() {
        this.p.setVisibility(0);
        this.q.setVisibility(0);
    }

    private void h() {
        b bVar = this.b;
        bVar.a.b();
        XZBShouleiUtil.getInstance().updateDeviceList(com.umeng.a.d, new c(bVar));
    }
}
