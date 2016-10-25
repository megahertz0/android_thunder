package com.xunlei.tdlive.usercenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.protocol.XLLiveGetFollowListRequest;
import com.xunlei.tdlive.protocol.XLLiveGetFollowListRequest.UserListResp;
import com.xunlei.tdlive.protocol.XLLiveGetFollowListRequest.UserListResp.UserInfo;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.a;
import com.xunlei.tdlive.util.d;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.List;

// compiled from: UserListAdapter.java
public class v extends d<UserInfo> implements ObjectCallBack {
    private String d;
    private String e;
    private int f;
    private XLLiveGetFollowListRequest g;
    private boolean h;

    public v(Context context, String str, String str2) {
        super(context);
        this.f = 0;
        this.h = true;
        this.d = str;
        this.e = str2;
    }

    public View a(Context context, UserInfo userInfo, ViewGroup viewGroup) {
        a aVar = new a(null);
        View inflate = LayoutInflater.from(this.b).inflate(R.layout.xllive_item_user, viewGroup, false);
        inflate.setTag(aVar);
        aVar.a = (ImageView) inflate.findViewById(R.id.ivAvatar);
        aVar.b = (TextView) inflate.findViewById(R.id.tvNickname);
        aVar.c = (TextView) inflate.findViewById(R.id.tvTitle);
        aVar.d = (TextView) inflate.findViewById(R.id.ivFollow);
        aVar.e = (TextView) inflate.findViewById(R.id.tvLiveFlagname);
        return inflate;
    }

    public void a(View view, Context context, UserInfo userInfo) {
        a aVar = (a) view.getTag();
        a.a(this.b).a(aVar.a, userInfo.avatar, a.a(this.b, R.drawable.xllive_avatar_default));
        aVar.b.setText(userInfo.nickname);
        aVar.c.setText(userInfo.sign);
        if (f.a().b(String.valueOf(userInfo.userid))) {
            aVar.d.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            return;
        }
        TextView textView = aVar.d;
        textView.setVisibility(0);
        a(textView, userInfo.isFollow());
        int i = "fans".equals(this.e) ? XZBDevice.DOWNLOAD_LIST_RECYCLE : "focus".equals(this.e) ? XZBDevice.DOWNLOAD_LIST_FAILED : 0;
        if (userInfo.isLiving()) {
            aVar.e.setVisibility(0);
            aVar.b.setMaxWidth((int) (((double) d.a(this.b, 100.0f)) + 0.5d));
        } else {
            aVar.e.setVisibility(XZBDevice.Wait);
            aVar.b.setMaxWidth(10000);
        }
        textView.setOnClickListener(new x(this, textView, new e(userInfo.isFollow(), String.valueOf(userInfo.userid), i, new w(this, textView))));
    }

    private void a(TextView textView, boolean z) {
        textView.setSelected(z);
        textView.setText(z ? R.string.followed : R.string.follow);
    }

    public void a(int i) {
        if (b()) {
            this.g = new XLLiveGetFollowListRequest(f.a().k(), f.a().l(), this.d, this.e, this.f, i);
            this.g.send(this);
        }
    }

    public void b(int i) {
        this.f = 0;
        a(i);
    }

    public void onResponse(int i, String str, Object obj) {
        List list = null;
        if (i == 0 && (obj instanceof UserListResp)) {
            list = ((UserListResp) obj).data;
        }
        if (this.f == 0) {
            b(list);
        } else {
            a(list);
        }
        this.f = (list == null ? 0 : list.size()) + this.f;
        c();
        if (this.c != null) {
            this.c.b();
        }
    }

    public void d() {
        if (this.g != null) {
            this.g.cancel();
        }
    }
}
