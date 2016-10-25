package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class SignalLevelView extends FrameLayout {
    private TextView a;
    private TextView b;
    private ImageView c;
    private a d;

    public enum a {
        IDLE,
        LOW,
        MEDIUM,
        STRONG;

        static {
            a = new com.xunlei.tdlive.play.view.SignalLevelView.a("IDLE", 0);
            b = new com.xunlei.tdlive.play.view.SignalLevelView.a("LOW", 1);
            c = new com.xunlei.tdlive.play.view.SignalLevelView.a("MEDIUM", 2);
            d = new com.xunlei.tdlive.play.view.SignalLevelView.a("STRONG", 3);
            e = new com.xunlei.tdlive.play.view.SignalLevelView.a[]{a, b, c, d};
        }
    }

    public SignalLevelView(Context context) {
        super(context);
        a();
    }

    public SignalLevelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public SignalLevelView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.xllive_signal_level_view, this);
        this.c = (ImageView) findViewById(R.id.img_signal_level);
        this.a = (TextView) findViewById(R.id.txt_upd);
        this.b = (TextView) findViewById(R.id.txt_tip);
        setSignalLevel(0.0f, a.a);
    }

    public void setSignalLevel(float f, a aVar) {
        b();
        switch (ag.a[aVar.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                this.b.setText(BuildConfig.VERSION_NAME);
                break;
            case SimpleLog.LOG_LEVEL_DEBUG:
                this.c.setImageResource(R.drawable.xllive_signal_level_low);
                this.b.setText("\u5f53\u524d\u7f51\u7edc\u73af\u5883\u5dee");
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1:
                this.c.setImageResource(R.drawable.xllive_signal_level_medium);
                this.b.setText("\u5f53\u524d\u7f51\u7edc\u73af\u5883\u4e0d\u4f73");
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                this.b.setText(BuildConfig.VERSION_NAME);
                break;
        }
        this.d = aVar;
        this.a.setText(String.format("%.1fkB/s", new Object[]{Float.valueOf(f)}));
    }

    private void b() {
        Animation animation = getAnimation();
        if (animation != null) {
            animation.setAnimationListener(null);
            clearAnimation();
        }
    }

    public a getmLevel() {
        return this.d;
    }
}
