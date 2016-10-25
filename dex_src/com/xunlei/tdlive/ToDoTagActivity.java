package com.xunlei.tdlive;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.usercenter.y;
import com.xunlei.xiazaibao.BuildConfig;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class ToDoTagActivity extends BaseActivity {
    private TextView a;
    private ImageView b;
    private TextView c;
    private ImageView d;
    private String e;

    public ToDoTagActivity() {
        this.e = BuildConfig.VERSION_NAME;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getIntent().getExtras();
        }
        this.e = bundle.getString("tag");
        setContentView(R.layout.xllive_activity_to_do_tag);
        this.a = (TextView) findViewById(R.id.tvTitle);
        this.b = (ImageView) findViewById(R.id.ivBack);
        this.b.setOnClickListener(new eq(this));
        this.c = (TextView) findViewById(R.id.tvResult);
        this.d = (ImageView) findViewById(R.id.ivDesc);
        this.a.setText(this.e);
        this.c.setText(new StringBuilder("\u6d3b\u52a8\uff1a").append(this.e).toString());
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
}
