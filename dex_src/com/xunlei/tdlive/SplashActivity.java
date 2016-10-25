package com.xunlei.tdlive;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.tdlive.protocol.XLLiveGetSplashImageRequest;
import com.xunlei.tdlive.protocol.XLLiveGetSplashImageRequest.GetSplashImageResp;
import com.xunlei.tdlive.protocol.XLLiveRequest.ObjectCallBack;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.a;
import com.xunlei.tdlive.util.ac;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class SplashActivity extends BaseActivity implements OnClickListener, ObjectCallBack {
    private boolean a;
    private ImageView b;
    private ImageView c;
    private View d;
    private View e;
    private long f;

    public SplashActivity() {
        this.a = false;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_splash);
        this.b = (ImageView) findViewById(R.id.bkimage);
        this.c = (ImageView) findViewById(R.id.image);
        this.d = findViewById(R.id.skip);
        this.e = findViewById(R.id.shoufa);
        this.d.setOnClickListener(this);
        String d = ac.d("UMENG_CHANNEL");
        if (!(d.equals("baidu") || d.equals("91"))) {
            d.equals("android");
        }
        Intent intent = getIntent();
        if (intent != null) {
            this.a = intent.getBooleanExtra("SplashActivity.waiting_login", false);
        }
        if (this.a) {
            new XLLiveGetSplashImageRequest().send(this);
        }
        this.f = SystemClock.elapsedRealtime();
        setTimer(ChatMessage.FLAG_SYS_NOTIFY, 1000);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i == 4 ? true : super.onKeyDown(i, keyEvent);
    }

    public void onClick(View view) {
        if (view == this.d) {
            a();
        }
    }

    protected void onTimer(int i) {
        if (this.a) {
            if (SystemClock.elapsedRealtime() - this.f > 30000) {
                XLog.d("SplashActivity", "autologin timeout.");
                f.a().i();
                a();
                return;
            }
            if (this.d.getVisibility() == 8) {
                String str = (String) this.c.getTag();
                if (str != null) {
                    if (this.c.getVisibility() == 8) {
                        a.a(getApplicationContext()).a(this.c, str, new el(this));
                    }
                    if (f.a().j()) {
                        setTimer(ChatMessage.FLAG_SYS_NOTIFY, 2000);
                        this.d.setVisibility(0);
                        return;
                    }
                }
                this.d.setVisibility(MqttConnectOptions.MQTT_VERSION_3_1_1);
            }
            if (!f.a().j()) {
                return;
            }
        }
        a();
    }

    public void onResponse(int i, String str, Object obj) {
        GetSplashImageResp getSplashImageResp = (GetSplashImageResp) obj;
        if (i == 0 && getSplashImageResp != null && getSplashImageResp.data != null && getSplashImageResp.data.id != null && getSplashImageResp.data.image != null && getSplashImageResp.data.md5 != null) {
            String string = getString("splash_id", BuildConfig.VERSION_NAME);
            String string2 = getString("splash_md5", BuildConfig.VERSION_NAME);
            int i2 = getInt("splash_display_count", 0);
            File a = a.a(this).a(getSplashImageResp.data.image);
            if (!string2.equals(getSplashImageResp.data.md5) || !string.equals(getSplashImageResp.data.id) || a == null || !a.exists()) {
                putString("splash_id", BuildConfig.VERSION_NAME);
                putString("splash_md5", BuildConfig.VERSION_NAME);
                putString("splash_image", BuildConfig.VERSION_NAME);
                putString("splash_beg_date", BuildConfig.VERSION_NAME);
                putString("splash_end_date", BuildConfig.VERSION_NAME);
                putInt("splash_display_count", 0);
                if (a != null && a.exists()) {
                    a.delete();
                }
                a.a(this).a(getSplashImageResp.data.image, new em(this, getSplashImageResp));
            } else if (i2 == 0) {
                this.c.setTag(getSplashImageResp.data.image);
            } else if (i2 == 1) {
                this.c.setTag(getSplashImageResp.data.image);
                putInt("splash_display_count", -1);
            } else if (i2 > 1) {
                this.c.setTag(getSplashImageResp.data.image);
                putInt("splash_display_count", i2 - 1);
            }
        }
    }

    private void a() {
        killTimer(ChatMessage.FLAG_SYS_NOTIFY);
        finish(17432576, 17432577);
    }
}
