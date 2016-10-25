package com.xunlei.downloadprovider.personal.settings;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.frame.user.ak;
import com.xunlei.downloadprovider.player.a.c;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class AutoPlaySettingActivity extends BaseActivity {
    private f a;
    private RadioGroup b;
    private RadioButton c;
    private RadioButton d;
    private RadioButton e;
    private int f;

    public AutoPlaySettingActivity() {
        this.f = -1;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968603);
        this.a = new f((Activity) this);
        this.a.i.setText(getResources().getString(2131232460));
        this.b = (RadioGroup) findViewById(2131755188);
        this.c = (RadioButton) findViewById(2131755189);
        this.d = (RadioButton) findViewById(2131755190);
        this.e = (RadioButton) findViewById(2131755191);
        this.f = c.a().a(c.c);
        switch (this.f) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.c.setChecked(true);
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.d.setChecked(true);
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.e.setChecked(true);
                break;
        }
        this.a.i.setOnClickListener(new h(this));
        this.b.setOnCheckedChangeListener(new i(this));
    }

    static /* synthetic */ void a(int i) {
        switch (i) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                ak.a(ak.a);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                ak.a(ak.b);
            default:
                break;
        }
    }
}
