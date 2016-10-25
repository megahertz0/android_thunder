package com.xunlei.tdlive.withdraw;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.user.k;
import com.xunlei.tdlive.user.k.a;
import com.xunlei.tdlive.user.k.b;
import com.xunlei.tdlive.user.k.c;

// compiled from: WxBindPage.java
public class o extends a implements OnClickListener, a, b {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.xllive_bind_wx, viewGroup, false);
        inflate.findViewById(R.id.tvBind).setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        if (view.getId() != R.id.tvBind) {
            return;
        }
        if (WXAPIFactory.createWXAPI(getActivity(), "wx18eada9ea7fbf76c", true).isWXAppInstalled()) {
            k.a().a(getActivity(), this, this);
        } else {
            c(R.string.wx_not_installed);
        }
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            c(R.string.bind_wx_failed);
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("KEY_UNION_ID", str);
        this.k.a(f.class, false, bundle);
    }

    public void a(c cVar) {
        if (cVar != null) {
            this.k.a("nickname", cVar.b);
            this.k.a("headimgurl", cVar.c);
        }
    }
}
