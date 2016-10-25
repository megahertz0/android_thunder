package com.xunlei.tdlive.control;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.xllib.R;
import org.android.spdy.TnetStatusCode;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class RankTabIndicator extends LinearLayout {
    ImageView a;
    ImageView b;
    TextView c;

    public RankTabIndicator(Context context) {
        super(context);
    }

    public RankTabIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RankTabIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        setGravity(R.styleable.Toolbar_maxButtonHeight);
        setOrientation(1);
        this.a = new ImageView(getContext());
        this.a.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        this.a.setImageResource(com.xunlei.tdlive.R.drawable.xllive_rank_down);
        addView(this.a, TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL, TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
        this.c = new TextView(getContext());
        this.c.setGravity(R.styleable.Toolbar_maxButtonHeight);
        this.c.setTextColor(-298434);
        this.c.setTextSize(12.0f);
        addView(this.c, TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL, TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
        this.b = new ImageView(getContext());
        this.b.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        this.b.setImageResource(com.xunlei.tdlive.R.drawable.xllive_rank_down);
        addView(this.b, TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL, TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
    }

    public void setTitle(String str) {
        this.c.setText(str);
    }

    public void select(boolean z) {
        this.b.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        this.c.setTextColor(z ? -298434 : -6776680);
    }
}
