package com.xunlei.tdlive;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.usercenter.y;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class PropertyTagActivity extends BaseActivity {
    private TextView a;
    private ImageView b;
    private FrameLayout c;
    private TextView d;
    private String e;

    public PropertyTagActivity() {
        this.e = BuildConfig.VERSION_NAME;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getIntent().getExtras();
        }
        this.e = bundle.getString("tag");
        setContentView(R.layout.xllive_activity_property_tag);
        this.a = (TextView) findViewById(R.id.tvTitle);
        this.b = (ImageView) findViewById(R.id.ivBack);
        this.b.setOnClickListener(new ds(this));
        this.c = (FrameLayout) findViewById(R.id.fragment_container);
        this.d = (TextView) findViewById(R.id.tvResult);
        this.a.setText(this.e);
        a(R.styleable.AppCompatTheme_buttonStyle);
        String m = f.a().m();
        String o = f.a().o();
        String k = f.a().k();
        Bundle bundle2 = new Bundle();
        bundle2.putString("key_userid", k);
        bundle2.putInt("KEY_TAG", MqttConnectOptions.MQTT_VERSION_3_1);
        bundle2.putString("KEY_NICKNAME", m);
        bundle2.putString("KEY_IMAGE_URL", o);
        y yVar = new y();
        yVar.setArguments(bundle2);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, yVar).commit();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        bundle.putAll(getIntent().getExtras());
        super.onSaveInstanceState(bundle);
    }

    private void a(int i) {
        this.d.setText(new StringBuilder("\u5171\u627e\u5230").append(i).append("\u4f4d\u542b\u6709\u201c").append(i).append("\u201d\u6807\u7b7e\u7684\u4e3b\u64ad").toString());
    }
}
