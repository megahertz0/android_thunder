package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.taskDetail.au;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovidershare.ba;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.downloadprovidershare.d.a;
import com.xunlei.downloadprovidershare.data.ShareBean;
import org.android.spdy.SpdyProtocol;

public class TaskDetailShareBar extends FrameLayout implements OnClickListener, a {
    private com.xunlei.downloadprovider.download.tasklist.a.a a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;

    public void setCurrentTask(com.xunlei.downloadprovider.download.tasklist.a.a aVar) {
        this.a = aVar;
    }

    public TaskDetailShareBar(Context context) {
        super(context);
        a(context);
    }

    public TaskDetailShareBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public TaskDetailShareBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.b = LayoutInflater.from(context).inflate(R.layout.layout_detail_share_bar, this);
        this.c = findViewById(R.id.detail_share_weixin_btn);
        this.c.setOnClickListener(this);
        this.d = findViewById(R.id.detail_share_friends_circle_btn);
        this.d.setOnClickListener(this);
        this.e = findViewById(R.id.detail_share_qq_btn);
        this.e.setOnClickListener(this);
        this.f = findViewById(R.id.detail_share_qzone_btn);
        this.f.setOnClickListener(this);
        this.g = findViewById(R.id.share_btn_container);
        this.h = findViewById(R.id.share_expand_view);
    }

    public void onClick(View view) {
        SHARE_MEDIA share_media;
        getContext();
        ShareBean a = au.a(this.a, "download_detail_bar");
        switch (view.getId()) {
            case R.id.detail_share_weixin_btn:
                share_media = SHARE_MEDIA.WEIXIN;
                break;
            case R.id.detail_share_friends_circle_btn:
                share_media = SHARE_MEDIA.WEIXIN_CIRCLE;
                break;
            case R.id.detail_share_qq_btn:
                share_media = SHARE_MEDIA.QQ;
                break;
            case R.id.detail_share_qzone_btn:
                share_media = SHARE_MEDIA.QZONE;
                break;
            default:
                share_media = null;
                break;
        }
        com.xunlei.downloadprovider.download.report.a.c(ba.a(share_media, a), a.e);
        com.xunlei.downloadprovider.frame.user.a.a().a(String.valueOf(LoginHelper.a().j));
        d.b().a((Activity) getContext(), a, share_media, this);
    }

    public void setEditMode(boolean z) {
        if (z) {
            this.g.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            return;
        }
        this.g.setVisibility(0);
        this.h.setVisibility(0);
    }

    public void onShareTargetClicked(SHARE_MEDIA share_media, ShareBean shareBean) {
    }

    public void onShareComplete(int i, SHARE_MEDIA share_media, ShareBean shareBean) {
        com.xunlei.downloadprovider.download.report.a.d(ba.a(share_media, shareBean), d.a(i), shareBean.e);
    }
}
