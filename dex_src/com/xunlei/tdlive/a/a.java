package com.xunlei.tdlive.a;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.control.RoundImageView;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveGetFollowListRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: FollowListAdapter.java
public class a extends i<String> {
    public void a(String str, boolean z, boolean z2) {
        String k = f.a().k();
        new XLLiveGetFollowListRequest(k, f.a().l(), k, "focus", 0, 100).send(new b(this));
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a$a com_xunlei_tdlive_a_a_a;
        if (view == null || view.getTag() == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.xllive_livelist_follow_liveuser_item, viewGroup, false);
            a$a com_xunlei_tdlive_a_a_a2 = new a$a(this);
            view.setTag(com_xunlei_tdlive_a_a_a2);
            com_xunlei_tdlive_a_a_a2.a = (RoundImageView) view.findViewById(R.id.head_image);
            com_xunlei_tdlive_a_a_a = com_xunlei_tdlive_a_a_a2;
        } else {
            com_xunlei_tdlive_a_a_a = (a$a) view.getTag();
        }
        JsonWrapper a = a(i);
        if (a != null) {
            com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(com_xunlei_tdlive_a_a_a.a, a.getString("avatar", BuildConfig.VERSION_NAME));
        }
        return view;
    }
}
