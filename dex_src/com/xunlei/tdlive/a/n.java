package com.xunlei.tdlive.a;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.a.j.a;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveGetBannerRequest;
import com.xunlei.tdlive.user.f;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: LiveListBannerAdapter.java
public class n extends k<String> {
    public n(a aVar) {
        this.a = aVar;
    }

    public void a(String str, boolean z, boolean z2) {
        if (b()) {
            if (this.a != null) {
                this.a.a(str, false, false);
            }
            new XLLiveGetBannerRequest(f.a().k(), f.a().l()).send(new o(this, str));
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View imageView = new ImageView(viewGroup.getContext());
        imageView.setScaleType(ScaleType.CENTER_CROP);
        imageView.setBackgroundColor(-1578257);
        imageView.setId(i);
        JsonWrapper d = d(i);
        if (d != null) {
            com.xunlei.tdlive.util.a.a(viewGroup.getContext()).a(imageView, d.getString(WeiXinShareContent.TYPE_IMAGE, BuildConfig.VERSION_NAME), com.xunlei.tdlive.util.a.a(viewGroup.getContext(), R.drawable.xllive_banner_loding));
        }
        return imageView;
    }

    protected void a(int i, View view) {
    }
}
