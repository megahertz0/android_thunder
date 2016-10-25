package com.xunlei.tdlive.withdraw;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.protocol.XLLiveBindWXRequest;
import com.xunlei.tdlive.protocol.XLLiveGetVerifyCodeRequest;
import com.xunlei.tdlive.util.ac;
import org.android.spdy.SpdyProtocol;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: VerifyCodeCheckPage.java
public class f extends a implements OnClickListener {
    private EditText m;
    private EditText n;
    private TextView o;
    private TextView p;
    private TextView q;
    private String r;
    private boolean s;
    private int t;

    public f() {
        this.s = false;
        this.t = 60;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.r = getArguments().getString("KEY_UNION_ID");
        if (TextUtils.isEmpty(this.r)) {
            ac.a("unionid is empty");
        }
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.xllive_wx_bind_verify_code_check, viewGroup, false);
        this.o = (TextView) inflate.findViewById(R.id.tvGetCode);
        this.o.setOnClickListener(this);
        this.q = (TextView) inflate.findViewById(R.id.tvVerify);
        this.q.setOnClickListener(this);
        this.m = (EditText) inflate.findViewById(R.id.etPhoneNum);
        this.n = (EditText) inflate.findViewById(R.id.etVerifyCode);
        this.p = (TextView) inflate.findViewById(R.id.tvErrorMsg);
        this.o.setEnabled(false);
        this.q.setEnabled(false);
        this.m.addTextChangedListener(new g(this));
        this.n.addTextChangedListener(new h(this));
        return inflate;
    }

    private void d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("headimgurl", this.k.a("headimgurl"));
            jSONObject.put("nickname", this.k.a("nickname"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new XLLiveBindWXRequest(com.xunlei.tdlive.user.f.a().k(), com.xunlei.tdlive.user.f.a().l(), this.r, this.m.getText().toString().trim(), this.n.getText().toString().trim(), jSONObject.toString()).send(new i(this));
    }

    private void e() {
        new XLLiveGetVerifyCodeRequest(com.xunlei.tdlive.user.f.a().k(), com.xunlei.tdlive.user.f.a().l(), this.m.getText().toString().trim()).send(new j(this));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tvVerify) {
            if (!this.s) {
                this.s = true;
                d();
            }
        } else if (id == R.id.tvGetCode) {
            this.t = 60;
            a(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle, 1000);
            b(false);
            e();
        }
    }

    private void b(boolean z) {
        this.o.setEnabled(z);
        if (z) {
            this.o.setText(R.string.get_verify_code);
            return;
        }
        this.o.setText(getResources().getString(R.string.retry_ns_later, new Object[]{Integer.valueOf(this.t)}));
    }

    protected void a_(int i) {
        if (i == 100) {
            this.t--;
        }
        if (this.t <= 0) {
            d(i);
            b(true);
            this.n.setText(null);
            f();
            return;
        }
        b(false);
    }

    private void c(String str) {
        this.p.setVisibility(0);
        this.p.setText(str);
    }

    private void f() {
        this.p.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.p.setText(null);
    }
}
