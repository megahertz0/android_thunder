package com.xunlei.downloadprovider.xiazaibao.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class XZBDownloadTitleBar extends FrameLayout {
    private LinearLayout a;
    private TextView b;
    private TextView c;
    private ImageView d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private TextView h;
    private View i;

    public XZBDownloadTitleBar(Context context) {
        super(context);
        a(context);
    }

    public XZBDownloadTitleBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public XZBDownloadTitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.xzb_download_center_title_bar, null);
        this.d = (ImageView) viewGroup.findViewById(R.id.title_bar_left_iv);
        this.e = (ImageView) viewGroup.findViewById(R.id.title_bar_tip_icon);
        this.a = (LinearLayout) viewGroup.findViewById(R.id.center_title_layout);
        this.b = (TextView) viewGroup.findViewById(R.id.title_bar_center_title);
        this.c = (TextView) viewGroup.findViewById(R.id.view_4_touch_event);
        this.f = (ImageView) viewGroup.findViewById(R.id.title_bar_right_1_iv);
        this.g = (ImageView) viewGroup.findViewById(R.id.title_bar_right_2_iv);
        this.i = viewGroup.findViewById(R.id.icon_click_container);
        this.h = (TextView) viewGroup.findViewById(R.id.title_bar_main_center_title);
        addView(viewGroup);
    }

    public void setTipIcon(int i) {
        if (i == -1) {
            this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            return;
        }
        this.e.setImageResource(i);
        this.e.setVisibility(0);
    }

    public void setTipIconVisibility(int i) {
        this.e.setVisibility(i);
    }

    public void setLeftImageViewClickListener(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    public void setRightImageView1ClickListener(OnClickListener onClickListener) {
        this.f.setOnClickListener(onClickListener);
    }

    public void setRightImageView2ClickListener(OnClickListener onClickListener) {
        this.g.setOnClickListener(onClickListener);
    }

    public void setLeftImageViewImage(int i) {
        this.d.setImageResource(i);
    }

    public void setRightImageView1Image(int i) {
        this.f.setImageResource(i);
    }

    public void setRightImageView2Image(int i) {
        this.g.setImageResource(i);
    }

    public View getRightImageView2() {
        return this.g;
    }

    public int getRightImageView2Width() {
        return this.g.getWidth();
    }

    public int getRightImageView2Height() {
        return this.g.getHeight();
    }

    public void setTouchListener(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public void setCenterTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            a((int) SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            a(0);
        }
        this.b.setText(str);
    }

    public void setCenterTitle(int i) {
        if (i == -1) {
            a((int) SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            a(0);
        }
        this.b.setText(i);
    }

    public final boolean a(int i) {
        boolean z = this.a.getVisibility() != i;
        if (i == 0) {
            setMainCenterTitleVisibility(false);
        } else {
            setMainCenterTitleVisibility(true);
        }
        this.a.setVisibility(i);
        return z;
    }

    public void setIconContainerListener(OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.i.setOnClickListener(onClickListener);
            this.i.setClickable(true);
            return;
        }
        this.i.setClickable(false);
    }

    public void setMainCenterTitleVisibility(boolean z) {
        if (z) {
            this.h.setVisibility(0);
        } else {
            this.h.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        }
    }

    public void setMainCenterText(String str) {
        this.h.setText(str);
    }
}
