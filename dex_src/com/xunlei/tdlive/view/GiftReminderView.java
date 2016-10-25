package com.xunlei.tdlive.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.modal.b.b;
import com.xunlei.tdlive.play.view.GiftReminder;
import com.xunlei.tdlive.play.view.GiftReminder.a;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.xiazaibao.BuildConfig;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class GiftReminderView extends LinearLayout implements b, a {
    private com.xunlei.tdlive.modal.b a;
    private GiftReminder[] b;

    public GiftReminderView(Context context) {
        super(context);
        a(context);
    }

    public GiftReminderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public GiftReminderView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    @TargetApi(21)
    public GiftReminderView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }

    private void a(Context context) {
        setOrientation(1);
        this.b = new GiftReminder[]{(GiftReminder) LayoutInflater.from(context).inflate(R.layout.xllive_gift_reminder, this, false), (GiftReminder) LayoutInflater.from(context).inflate(R.layout.xllive_gift_reminder, this, false)};
        if (!isInEditMode()) {
            this.b[0].setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
            this.b[1].setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
        }
        addView(this.b[0]);
        addView(this.b[1]);
    }

    public void addReminding(com.xunlei.tdlive.modal.b.a aVar) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.a != null) {
            this.a.a(aVar);
        }
        XLog.d(BuildConfig.VERSION_NAME, new StringBuilder("addReminding spent time ").append(System.currentTimeMillis() - currentTimeMillis).append(" s").toString());
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a = new com.xunlei.tdlive.modal.b(2);
        this.a.a((b) this);
        this.a.a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.a != null) {
            this.a.b();
            this.a.a(null);
            this.a = null;
        }
    }

    public boolean onMsgPop(com.xunlei.tdlive.modal.b.a aVar, int i) {
        if (this.a != null) {
            this.a.a(i, aVar.a);
        }
        if (i >= 0 && i <= 1) {
            this.b[i].act(aVar, this);
        }
        return true;
    }

    public void onGiftReminderViewState(GiftReminder giftReminder) {
        if (this.a != null) {
            this.a.a(giftReminder == this.b[0] ? 0 : 1, 0);
        }
        giftReminder.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
    }
}
