package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import cn.nodemedia.LivePublisher;
import com.xunlei.tdlive.R;
import org.android.spdy.SpdyProtocol;
import org.android.spdy.TnetStatusCode;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class PublishButtonBar extends LinearLayout {
    public View publish_camera_btn;
    public View publish_filter_btn;
    public View publish_flash_btn;
    public View publish_full_sreen_btn;
    public View publish_share_btn;

    public PublishButtonBar(Context context) {
        super(context);
        a();
    }

    public PublishButtonBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public PublishButtonBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.xllive_publish_button_bar, this, true);
        this.publish_share_btn = findViewById(R.id.publish_share_btn);
        this.publish_full_sreen_btn = findViewById(R.id.publish_full_sreen_btn);
        this.publish_flash_btn = findViewById(R.id.publish_flash_btn);
        this.publish_camera_btn = findViewById(R.id.publish_camera_btn);
        this.publish_camera_btn.setOnClickListener(new z(this));
        this.publish_filter_btn = findViewById(R.id.publish_filter_btn);
        this.publish_filter_btn.setOnClickListener(new aa(this));
        this.publish_flash_btn.setOnClickListener(new ab(this));
        if (!isInEditMode()) {
            b();
        }
    }

    private void b() {
        try {
            d();
            e();
            c();
        } catch (Throwable th) {
        }
    }

    private void c() {
        switch (LivePublisher.getFilterState()) {
            case TnetStatusCode.EASY_REQ_STAGE_NOT_SEND:
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                this.publish_filter_btn.setVisibility(0);
                this.publish_filter_btn.setSelected(false);
            case SimpleLog.LOG_LEVEL_TRACE:
                this.publish_filter_btn.setVisibility(0);
                this.publish_filter_btn.setSelected(true);
            default:
                break;
        }
    }

    private void d() {
        switch (LivePublisher.getFlashState()) {
            case TnetStatusCode.EASY_REQ_STAGE_NOT_SEND:
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                this.publish_flash_btn.setVisibility(0);
                this.publish_flash_btn.setSelected(false);
            case SimpleLog.LOG_LEVEL_TRACE:
                this.publish_flash_btn.setVisibility(0);
                this.publish_flash_btn.setSelected(true);
            default:
                break;
        }
    }

    private void e() {
        switch (LivePublisher.getCamera()) {
            case TnetStatusCode.EASY_REQ_STAGE_NOT_SEND:
            case SimpleLog.LOG_LEVEL_TRACE:
                this.publish_flash_btn.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                this.publish_share_btn.setVisibility(0);
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                this.publish_flash_btn.setVisibility(0);
                this.publish_share_btn.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            default:
                break;
        }
    }
}
