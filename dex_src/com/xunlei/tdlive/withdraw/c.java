package com.xunlei.tdlive.withdraw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.tdlive.protocol.XLLiveGetBindInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveGetBindInfoRequest.BindInfo;
import com.xunlei.tdlive.protocol.XLLiveGetBindInfoRequest.WxInfo;
import com.xunlei.tdlive.protocol.XLLiveGetWithdrawInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import org.android.spdy.SpdyProtocol;

// compiled from: MyInComeHomePage.java
public class c extends a implements OnClickListener, ObjectCallBack {
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.xllive_my_income_home, viewGroup, false);
        this.o = (TextView) inflate.findViewById(R.id.tvWithdraw);
        this.o.setOnClickListener(this);
        this.o.setEnabled(false);
        this.o.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        inflate.findViewById(R.id.tvChange).setOnClickListener(this);
        inflate.findViewById(R.id.tvFAQ).setOnClickListener(this);
        this.m = (TextView) inflate.findViewById(R.id.tvBeans);
        this.n = (TextView) inflate.findViewById(R.id.tvWithdrawable);
        this.p = (TextView) inflate.findViewById(R.id.tvBeansTitle);
        this.q = (TextView) inflate.findViewById(R.id.tvWithdrawableTitle);
        if (f()) {
            this.p.setText(R.string.beans_this_month);
            this.q.setText(R.string.money_left);
            this.o.setText(R.string.apply_widthdraw);
        } else {
            this.p.setText(R.string.golen_beans);
            this.q.setText(R.string.withdrawable);
            this.o.setText(R.string.wx_widthdraw);
        }
        return inflate;
    }

    private void d() {
        a(new d(this));
    }

    public void onResume() {
        d();
        super.onResume();
    }

    private void a(ObjectCallBack objectCallBack) {
        new XLLiveGetWithdrawInfoRequest(f.a().k(), f.a().l()).send(objectCallBack);
    }

    private void e() {
        new XLLiveGetBindInfoRequest(f.a().k(), f.a().l()).send(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tvWithdraw) {
            a(new e(this));
        } else if (id != R.id.tvChange && id == R.id.tvFAQ) {
            if (f()) {
                WebBrowserActivity.start(getActivity(), "http://h5.live.xunlei.com/android/help_income.html", getResources().getString(R.string.FAQ), false);
            } else {
                WebBrowserActivity.start(getActivity(), "http://h5.live.xunlei.com/android/help.html", getResources().getString(R.string.FAQ), false);
            }
        }
    }

    public void onResponse(int i, String str, Object obj) {
        if (!c()) {
            return;
        }
        if (i == 0 && (obj instanceof BindInfo)) {
            BindInfo bindInfo = (BindInfo) obj;
            if (bindInfo.data != null) {
                WxInfo wxInfo = bindInfo.data.getWxInfo();
                if (wxInfo != null) {
                    this.k.a("nickname", wxInfo.nickname);
                    this.k.a("headimgurl", wxInfo.headimgurl);
                }
                if (!bindInfo.data.isBind()) {
                    this.k.a(o.class, false);
                    return;
                } else if (bindInfo.data.isFollow()) {
                    this.k.a(n.class, true);
                    return;
                } else {
                    this.k.a(b.class, false);
                    return;
                }
            }
            a(i, str);
            return;
        }
        a(i, str);
    }

    private void a(int i, String str) {
        XLog.i("MyInComeHomePage", new StringBuilder("get bind info failed. ret = ").append(i).append(", msg = ").append(str).toString());
    }

    private boolean f() {
        return f.a(getActivity()).p();
    }
}
