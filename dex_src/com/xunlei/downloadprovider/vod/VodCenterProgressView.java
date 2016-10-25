package com.xunlei.downloadprovider.vod;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class VodCenterProgressView extends LinearLayout {
    private ImageView a;
    private ProgressBar b;
    private CenterProgressType c;

    public enum CenterProgressType {
        CenterProgress_Volume,
        CenterProgress_Brightness;

        static {
            CenterProgress_Volume = new com.xunlei.downloadprovider.vod.VodCenterProgressView.CenterProgressType("CenterProgress_Volume", 0);
            CenterProgress_Brightness = new com.xunlei.downloadprovider.vod.VodCenterProgressView.CenterProgressType("CenterProgress_Brightness", 1);
            a = new com.xunlei.downloadprovider.vod.VodCenterProgressView.CenterProgressType[]{CenterProgress_Volume, CenterProgress_Brightness};
        }
    }

    public VodCenterProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = null;
    }

    public VodCenterProgressView(Context context) {
        super(context);
        this.a = null;
        this.b = null;
    }

    @SuppressLint({"NewApi"})
    public VodCenterProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = null;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (ImageView) findViewById(2131757151);
        this.b = (ProgressBar) findViewById(2131757152);
    }

    public void setProgress(int i) {
        switch (AnonymousClass_1.a[this.c.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                if (i > 0) {
                    this.a.setBackgroundResource(2130839720);
                } else {
                    this.a.setBackgroundResource(2130839721);
                }
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.a.setBackgroundResource(2130839714);
                break;
        }
        this.b.setProgress(i);
    }

    public final void a(CenterProgressType centerProgressType, int i, int i2) {
        this.c = centerProgressType;
        this.b.setMax(i2);
        this.b.setProgress(i);
    }

    public CenterProgressType getType() {
        return this.c;
    }
}
