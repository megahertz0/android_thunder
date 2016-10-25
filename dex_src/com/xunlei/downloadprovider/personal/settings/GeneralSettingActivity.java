package com.xunlei.downloadprovider.personal.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.umeng.socialize.utils.Log;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.ui.SlipButton;
import com.xunlei.downloadprovider.businessutil.b;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.player.a.c;
import com.xunlei.downloadprovider.pushmessage.b.a;
import com.xunlei.downloadprovider.util.ai;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class GeneralSettingActivity extends BaseActivity implements OnClickListener {
    private View a;
    private TextView b;
    private RelativeLayout c;
    private SlipButton d;
    private View e;
    private SlipButton f;
    private RelativeLayout g;
    private RelativeLayout h;
    private TextView i;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968968);
        this.a = findViewById(R.id.titlebar_left);
        this.a.setOnClickListener(this);
        this.b = (TextView) findViewById(R.id.titlebar_title);
        this.b.setText(getString(2131232490));
        new f((Activity) this).j.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.d = (SlipButton) findViewById(2131756905);
        this.c = (RelativeLayout) findViewById(2131756904);
        this.d.a(a.a(this), false);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e = findViewById(2131756906);
        this.f = (SlipButton) findViewById(2131756907);
        this.f.a(b.a().h(), false);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.h = (RelativeLayout) findViewById(2131756908);
        this.i = (TextView) findViewById(2131756909);
        this.h.setOnClickListener(this);
        this.g = (RelativeLayout) findViewById(2131756911);
        this.g.setOnClickListener(this);
    }

    public void onClick(View view) {
        boolean z = true;
        boolean z2 = false;
        switch (view.getId()) {
            case R.id.titlebar_left:
                finish();
            case 2131756904:
            case 2131756905:
                Log.v("test_push_btn_click", new StringBuilder("mBtnPushRun.getSwitchState() --> ").append(this.d.getSwitchState()).toString());
                if (!this.d.getSwitchState()) {
                    z2 = true;
                }
                Log.v("test_push_btn_click", new StringBuilder("state --> ").append(z2).toString());
                this.d.a(z2, true);
                new j(this, "pushmessageservice").a("isAwaysRun", z2);
                StatReporter.reportSwitcherClick(5004, "receiveNotify", z2, null);
                if (!ai.a()) {
                    return;
                }
                if (z2) {
                    MiPushClient.registerPush(this, "2882303761517301192", "5171730196192");
                    Logger.setLogger(this, new o(this));
                    return;
                }
                MiPushClient.unregisterPush(getApplicationContext());
            case 2131756906:
            case 2131756907:
                if (this.f.getSwitchState()) {
                    z = false;
                }
                this.f.a(z, false);
                b.a().a(z);
                StatReporter.reportSwitcherClick(5004, "notifySound", z, null);
            case 2131756908:
                startActivity(new Intent(this, AutoPlaySettingActivity.class));
            case 2131756911:
                XLAlarmDialog xLAlarmDialog = new XLAlarmDialog(this);
                xLAlarmDialog.setContent(getString(2131232470));
                xLAlarmDialog.setCancelButtonText(getString(2131232469));
                xLAlarmDialog.setConfirmButtonText(getString(2131232471));
                xLAlarmDialog.setOnClickCancelButtonListener(new m(this));
                xLAlarmDialog.setOnClickConfirmButtonListener(new n(this));
                xLAlarmDialog.show();
            default:
                break;
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void onResume() {
        super.onResume();
        TextView textView = this.i;
        int i = -1;
        switch (c.a().a(c.c)) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                i = 2131232458;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                i = 2131232459;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                i = 2131232457;
                break;
        }
        textView.setText(i);
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
    }
}
