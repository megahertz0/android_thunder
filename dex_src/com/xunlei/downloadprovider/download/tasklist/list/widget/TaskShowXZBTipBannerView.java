package com.xunlei.downloadprovider.download.tasklist.list.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.tasklist.list.b.b;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.e;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.xiazaibao.remotedownload.XZBWebviewActivity;
import com.xunlei.downloadprovider.xiazaibao.setting.DownloadDevieSettingActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xiazaibao.shoulei.XZBShouleiUtil;

@Deprecated
public class TaskShowXZBTipBannerView extends FrameLayout implements OnClickListener {
    private boolean a;
    private a b;
    private b c;
    private a d;

    public TaskShowXZBTipBannerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public TaskShowXZBTipBannerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void setOperateCallback(a aVar) {
        this.d = aVar;
    }

    private void a(Context context) {
        this.c = new b(LayoutInflater.from(context).inflate(2130968832, this));
        if (!isInEditMode()) {
            LoginHelper.a();
            boolean z = LoginHelper.c() && XZBShouleiUtil.getInstance().getDefaultDevice() != null;
            this.a = z;
            this.c.a("\u4e0b\u8f7d\u6587\u4ef6\u8fc7\u5927\u5360\u5185\u5b58\uff1f\u4e0b\u8f7d\u5230\u4e0b\u8f7d\u5b9d");
            if (this.a) {
                this.c.b("\u53bb\u8bbe\u7f6e");
            } else {
                this.c.b("\u53bb\u770b\u770b");
            }
            this.c.b.setOnClickListener(this);
            this.c.d.setOnClickListener(this);
            e.a();
            if (e.b() == 1) {
                setVisibility(0);
            } else {
                setVisibility(XZBDevice.Wait);
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131756312:
                if (this.b != null) {
                    this.b.m = false;
                }
                e.a();
                e.a(com.umeng.a.d);
                e.a();
                e.c();
                setVisibility(XZBDevice.Wait);
            case 2131756314:
                if (this.b != null) {
                    this.b.m = false;
                }
                e.a();
                e.a(com.umeng.a.d);
                e.a();
                e.c();
                setVisibility(XZBDevice.Wait);
                if (this.a) {
                    DownloadDevieSettingActivity.a(getContext());
                } else {
                    XZBWebviewActivity.a(getContext(), "v_an_shoulei_download");
                }
            default:
                break;
        }
    }
}
