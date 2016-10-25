package com.xunlei.tdlive.withdraw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveGetCashRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.a;

// compiled from: WithdrawVerifyPage.java
public class n extends a implements OnClickListener, JsonCallBack {
    private EditText m;
    private double n;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        Bundle arguments = getArguments();
        if (arguments != null) {
            i = arguments.getInt("KEY_WITHDRAWABLE_MONEY");
        } else {
            i = 0;
        }
        Object a = this.k.a("KEY_WITHDRAWABLE_MONEY");
        if (a != null && (a instanceof Integer)) {
            i = ((Integer) a).intValue();
        }
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.xllive_withdraw_verify, viewGroup, false);
        inflate.findViewById(R.id.tvWithdraw).setOnClickListener(this);
        this.m = (EditText) inflate.findViewById(R.id.etNum);
        ((TextView) inflate.findViewById(R.id.tvWidthdrawTotal)).setText(getResources().getString(R.string.today_can_withdraw, new Object[]{Float.valueOf(((float) i) / 100.0f)}));
        this.m.setText(String.format("%.2f", new Object[]{Float.valueOf(((float) i) / 100.0f)}));
        a = this.k.a("headimgurl");
        if (a != null && (a instanceof String)) {
            String toString = a.toString();
            a.a(getActivity()).a((ImageView) inflate.findViewById(R.id.ivAvatar), toString, a.b(getActivity()));
        }
        a = this.k.a("nickname");
        if (a != null && (a instanceof String)) {
            a.toString();
        }
        return inflate;
    }

    private void d() {
        try {
            double parseDouble = Double.parseDouble(this.m.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
            parseDouble = 0.0d;
        }
        if (parseDouble > 0.0d) {
            this.n = parseDouble;
            new XLLiveGetCashRequest(f.a().k(), f.a().l(), (int) (this.n * 100.0d)).send(this);
            return;
        }
        c(R.string.withdraw_less_than_0);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.tvWithdraw) {
            e();
            d();
        }
    }

    private void e() {
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService("input_method");
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(this.m.getApplicationWindowToken(), 0);
        }
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (!c()) {
            return;
        }
        if (i == 0) {
            Bundle bundle = new Bundle();
            bundle.putDouble("KEY_WITHDRAW_NUM", this.n);
            this.k.a(k.class, true, bundle);
            return;
        }
        a_(str);
    }
}
