package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xunlei.tdlive.R;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class ConnectMicView extends FrameLayout {
    private ImageView a;
    private ImageView b;
    private a c;

    public enum a {
        IDLE,
        CONNECTING,
        CONNECTED;

        static {
            a = new com.xunlei.tdlive.play.view.ConnectMicView.a("IDLE", 0);
            b = new com.xunlei.tdlive.play.view.ConnectMicView.a("CONNECTING", 1);
            c = new com.xunlei.tdlive.play.view.ConnectMicView.a("CONNECTED", 2);
            d = new com.xunlei.tdlive.play.view.ConnectMicView.a[]{a, b, c};
        }
    }

    public ConnectMicView(Context context) {
        super(context);
        this.c = a.a;
        a();
    }

    public ConnectMicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = a.a;
        a();
    }

    public ConnectMicView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = a.a;
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.xllive_connect_mic_view, this);
        this.a = (ImageView) findViewById(R.id.head_image);
        this.b = (ImageView) findViewById(R.id.connect_button);
        setState(a.a);
    }

    public a getState() {
        return this.c;
    }

    public void setState(a aVar) {
        Animation animation = getAnimation();
        if (animation != null) {
            animation.setAnimationListener(null);
            clearAnimation();
        }
        switch (i.a[aVar.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.b.setVisibility(0);
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                this.a.setVisibility(0);
                this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                b();
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                this.a.setVisibility(0);
                this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                break;
            default:
                return;
        }
        this.c = aVar;
    }

    private void b() {
        Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.xllive_connect_mic);
        loadAnimation.setAnimationListener(new h(this));
        startAnimation(loadAnimation);
    }

    public ImageView getHeadImageView() {
        return this.a;
    }
}
