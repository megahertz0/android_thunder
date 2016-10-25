package com.xunlei.tdlive.control;

import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class TwinkieListView extends ListView {
    public TwinkieListView(Context context) {
        super(context);
        b();
    }

    public TwinkieListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public TwinkieListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        if (VERSION.SDK_INT >= 9) {
            setOverScrollMode(SimpleLog.LOG_LEVEL_DEBUG);
        }
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    protected int getHeightLimit() {
        return getLayoutParams().height;
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a();
    }

    public void a() {
        int firstVisiblePosition = getFirstVisiblePosition();
        int lastVisiblePosition = getLastVisiblePosition();
        if (getMeasuredHeight() - getMeasuredHeight() >= 0) {
            int i = (lastVisiblePosition - firstVisiblePosition) + 1;
            for (lastVisiblePosition = 0; lastVisiblePosition < i; lastVisiblePosition++) {
                View childAt = getChildAt(lastVisiblePosition);
                if (childAt != null) {
                    float f;
                    switch (lastVisiblePosition) {
                        case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                            f = 0.2f;
                            break;
                        case SimpleLog.LOG_LEVEL_TRACE:
                            f = 0.4f;
                            break;
                        default:
                            f = 1.0f;
                            break;
                    }
                    childAt.setAlpha(f);
                }
            }
        }
    }
}
