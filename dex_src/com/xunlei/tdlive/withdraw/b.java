package com.xunlei.tdlive.withdraw;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.protocol.XLLiveGetBindInfoRequest;
import com.xunlei.tdlive.protocol.XLLiveGetBindInfoRequest.BindInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.user.f;

// compiled from: FollowGuidePage.java
public class b extends a implements OnClickListener, ObjectCallBack {
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.xllive_follow_guide, viewGroup, false);
        inflate.findViewById(R.id.btnNext).setOnClickListener(this);
        return inflate;
    }

    private void d() {
        new XLLiveGetBindInfoRequest(f.a().k(), f.a().l()).send(this);
    }

    public void onResponse(int i, String str, Object obj) {
        if (!c()) {
            return;
        }
        if (i != 0) {
            a_(str);
        } else if (obj instanceof BindInfo) {
            BindInfo bindInfo = (BindInfo) obj;
            if (bindInfo.data == null || !bindInfo.data.isFollow()) {
                c(R.string.follow_check_failed);
            } else {
                this.k.a(n.class, true);
            }
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btnNext) {
            d();
        }
    }
}
