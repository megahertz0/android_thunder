package com.xunlei.downloadprovider.vod;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue;
import org.android.spdy.TnetStatusCode;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class PopupDefinitionChoiceView extends LinearLayout {
    private static final String a;
    private View b;
    private LayoutInflater c;
    private Button d;
    private Button e;
    private Button f;
    private Button g;
    private View h;
    private View i;
    private View j;
    private View k;
    private b l;
    private OnClickListener m;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[SharpnessValue.values().length];
            try {
                a[SharpnessValue.flv.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[SharpnessValue.mp4.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[SharpnessValue.hd2.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[SharpnessValue.hd3.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    static {
        a = PopupDefinitionChoiceView.class.getSimpleName();
    }

    public PopupDefinitionChoiceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = new d(this);
    }

    public PopupDefinitionChoiceView(Context context) {
        super(context);
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = new d(this);
    }

    @SuppressLint({"NewApi"})
    public PopupDefinitionChoiceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = new d(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.d = (Button) findViewById(R.id.vod_definition_choice_btn_flv);
        this.e = (Button) findViewById(R.id.vod_definition_choice_btn_mp4);
        this.f = (Button) findViewById(R.id.vod_definition_choice_btn_hd2);
        this.g = (Button) findViewById(R.id.vod_definition_choice_btn_hd3);
        this.h = findViewById(R.id.vod_definition_choice_hd3_lay);
        this.i = findViewById(R.id.vod_definition_choice_hd2_lay);
        this.j = findViewById(R.id.vod_definition_choice_mp4_lay);
        this.k = findViewById(R.id.vod_definition_choice_flv_lay);
        this.d.setOnClickListener(this.m);
        this.e.setOnClickListener(this.m);
        this.f.setOnClickListener(this.m);
        this.g.setOnClickListener(this.m);
        measure(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL, TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
    }

    public void setOnDefinitionChoicedListener(b bVar) {
        this.l = bVar;
    }

    public void setChoicedDefinition(SharpnessValue sharpnessValue) {
        this.d.setTextColor(getContext().getResources().getColorStateList(R.color.vod_player_text_btn_select_episode_selector));
        this.e.setTextColor(getContext().getResources().getColorStateList(R.color.vod_player_text_btn_select_episode_selector));
        this.f.setTextColor(getContext().getResources().getColorStateList(R.color.vod_player_text_btn_select_episode_selector));
        this.g.setTextColor(getContext().getResources().getColorStateList(R.color.vod_player_text_btn_select_episode_selector));
        switch (AnonymousClass_1.a[sharpnessValue.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                this.d.setTextColor(getContext().getResources().getColorStateList(R.color.vod_player_text_btn_definition_sel_selector));
            case SimpleLog.LOG_LEVEL_DEBUG:
                this.e.setTextColor(getContext().getResources().getColorStateList(R.color.vod_player_text_btn_definition_sel_selector));
            case MqttConnectOptions.MQTT_VERSION_3_1:
                this.f.setTextColor(getContext().getResources().getColorStateList(R.color.vod_player_text_btn_definition_sel_selector));
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                this.g.setTextColor(getContext().getResources().getColorStateList(R.color.vod_player_text_btn_definition_sel_selector));
            default:
                break;
        }
    }
}
