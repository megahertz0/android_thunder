package com.xunlei.downloadprovider.personal.settings.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.ui.SlipButton;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class SettingsItem extends FrameLayout {
    private boolean a;
    private int b;
    private int c;
    private int d;
    private String e;
    private String f;
    private boolean g;
    private boolean h;
    private boolean i;
    private int j;
    private boolean k;
    private int l;
    private ViewGroup m;
    private RadioButton n;
    private TextView o;
    private TextView p;
    private SlipButton q;
    private ImageView r;
    private TextView s;
    private a t;

    public static interface a {
    }

    public SettingsItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(attributeSet);
        a();
    }

    public SettingsItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
        a();
    }

    private void a(AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.settings_item);
        this.a = obtainStyledAttributes.getBoolean(0, false);
        this.b = obtainStyledAttributes.getResourceId(1, -1);
        this.c = obtainStyledAttributes.getDimensionPixelSize(SimpleLog.LOG_LEVEL_DEBUG, com.xunlei.xllib.R.styleable.AppCompatTheme_dialogTheme);
        this.d = obtainStyledAttributes.getDimensionPixelSize(MqttConnectOptions.MQTT_VERSION_3_1, com.xunlei.xllib.R.styleable.AppCompatTheme_dialogTheme);
        this.e = obtainStyledAttributes.getString(MqttConnectOptions.MQTT_VERSION_3_1_1);
        this.f = obtainStyledAttributes.getString(SimpleLog.LOG_LEVEL_ERROR);
        this.g = obtainStyledAttributes.getBoolean(SimpleLog.LOG_LEVEL_OFF, false);
        this.i = obtainStyledAttributes.getBoolean(com.xunlei.xllib.R.styleable.Toolbar_titleMargins, false);
        this.h = obtainStyledAttributes.getBoolean(SpdyProtocol.PUBKEY_SEQ_ADASH, false);
        this.j = obtainStyledAttributes.getResourceId(SimpleLog.LOG_LEVEL_FATAL, 0);
        this.k = obtainStyledAttributes.getBoolean(com.xunlei.xllib.R.styleable.Toolbar_titleMarginStart, false);
        this.l = obtainStyledAttributes.getResourceId(com.xunlei.xllib.R.styleable.Toolbar_titleMarginEnd, R.drawable.settings_new_flag_bg);
        obtainStyledAttributes.recycle();
    }

    private void a() {
        this.m = (ViewGroup) ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.settings_item, null);
        this.n = (RadioButton) this.m.findViewById(com.xunlei.xllib.R.id.icon);
        this.o = (TextView) this.m.findViewById(com.xunlei.xllib.R.id.title);
        this.p = (TextView) this.m.findViewById(R.id.title_sub);
        this.q = (SlipButton) this.m.findViewById(R.id.slip_btn);
        this.q.setOnClickListener(new b(this));
        this.r = (ImageView) this.m.findViewById(R.id.arrow);
        if (!this.a || this.b == -1) {
            this.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            this.n.setVisibility(0);
            this.n.setButtonDrawable(this.b);
        }
        LayoutParams layoutParams = this.n.getLayoutParams();
        layoutParams.width = this.c;
        layoutParams.height = this.d;
        this.n.setLayoutParams(layoutParams);
        this.o.setText(this.e);
        if (TextUtils.isEmpty(this.f)) {
            this.p.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            this.p.setText(this.f);
        }
        if (this.g) {
            this.q.setVisibility(0);
        } else {
            this.q.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        this.q.a = new c(this);
        this.q.a(this.h, false);
        if (this.i) {
            this.r.setVisibility(0);
        } else {
            this.r.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (this.j != 0) {
            this.m.setBackgroundResource(this.j);
        }
        this.s = (TextView) this.m.findViewById(R.id.new_flag);
        if (this.k) {
            this.s.setVisibility(0);
        } else {
            this.s.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        this.s.setBackgroundResource(this.l);
        this.m.setDuplicateParentStateEnabled(true);
        addView(this.m, new FrameLayout.LayoutParams(-1, -1));
    }

    public void setTitleText(int i) {
        this.o.setText(i);
    }

    public void setSubTitleVisibility(int i) {
        this.p.setVisibility(i);
    }

    public void setRootViewHeight(int i) {
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.m.getLayoutParams();
        layoutParams.height = i;
        this.m.setLayoutParams(layoutParams);
    }

    public void setItemBackground(int i) {
        this.m.setBackgroundResource(i);
    }

    public void setCheckboxState(boolean z) {
        this.q.a(z, true);
    }

    public boolean getCheckboxState() {
        return this.q.getSwitchState();
    }

    public void setNewFlagVisibility(int i) {
        this.s.setVisibility(i);
    }

    public void setNewFlagBackgroud(int i) {
        this.s.setBackgroundResource(i);
    }

    public void setNewFlagText(String str) {
        this.s.setText(str);
    }

    public void setOnCheckboxStateChangeListener(a aVar) {
        this.t = aVar;
    }
}
