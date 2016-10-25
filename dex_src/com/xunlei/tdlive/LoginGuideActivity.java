package com.xunlei.tdlive;

import android.app.Dialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.umeng.message.ALIAS_TYPE;
import com.xunlei.common.member.XLOnUserListener;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.common.member.act.XLQQParam;
import com.xunlei.common.member.act.XLSinaParam;
import com.xunlei.common.member.act.XLWxParam;
import com.xunlei.download.proguard.c;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.XLog;
import com.xunlei.tdlive.util.ac;
import com.xunlei.tdlive.util.q;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xllib.R;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

public class LoginGuideActivity extends BaseActivity implements OnClickListener {
    private boolean a;
    private boolean b;
    private View c;
    private View d;
    private XLOnUserListener e;

    public LoginGuideActivity() {
        this.a = false;
        this.b = false;
        this.e = new dk(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_login_guide);
        f.b = -100;
        Intent intent = getIntent();
        if (intent != null) {
            this.b = intent.getBooleanExtra("LoginGuideActivity.first_start", false);
            if (intent.getBooleanExtra("LoginGuideActivity.EXTRA_SHOW_FOR_INVALID_SESSION", false)) {
                showToast(R.string.login_for_invalid_session, 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
            }
        }
        this.c = findViewById(R.id.btnWxLogin);
        this.c.setOnClickListener(this);
        this.d = findViewById(R.id.btnXlLogin);
        this.d.setOnClickListener(this);
        findViewById(R.id.btnQQLogin).setOnClickListener(this);
        findViewById(R.id.btnWbLogin).setOnClickListener(this);
        findViewById(R.id.btnRegister).setOnClickListener(this);
        f();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        XLog.d("LoginGuideActivity", new StringBuilder("LoginGuideActivity onActivityResult resultCode = ").append(i2).toString());
        if (i == 100 && i2 == -1) {
            a();
        }
    }

    protected void onResume() {
        super.onResume();
        if (f.a().f()) {
            showLoadingDialog("\u767b\u5f55\u4e2d...", false);
        } else {
            hideLoadingDialog();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnWxLogin) {
            a("weichat");
            if (b()) {
                IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this, "wx18eada9ea7fbf76c", true);
                if (!createWXAPI.isWXAppInstalled()) {
                    a(R.string.wx_not_installed, "http://weixin.qq.com");
                } else if (createWXAPI.isWXAppSupportAPI()) {
                    f.a().b(MqttConnectOptions.MQTT_VERSION_3_1);
                    c();
                } else {
                    showToast(R.string.wx_login_not_support, 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
                }
            }
        } else if (id == R.id.btnXlLogin) {
            a("thunder");
            if (XLUserUtil.getInstance().userIsOnline()) {
                a();
                return;
            }
            String str;
            String str2 = "login_detail_page_show";
            if (this.b) {
                str = "first_start";
            } else {
                str = null;
            }
            q.a(str2, str, "thunder", null);
            startActivityForResult(new Intent(this, LoginActivity.class), R.styleable.AppCompatTheme_buttonStyle);
        } else if (id == R.id.tvUserProtocol) {
            Intent intent = new Intent(this, WebBrowserActivity.class);
            intent.setData(Uri.parse("http://h5.live.xunlei.com/android/tos.html"));
            intent.putExtra(SetKey.TITLE, "\u7528\u6237\u534f\u8bae");
            intent.putExtra("right_btn", false);
            startActivity(intent);
        } else if (id == R.id.btnQQLogin) {
            a(ALIAS_TYPE.QQ);
            if (!ac.e("com.tencent.mobileqq")) {
                a(R.string.qq_not_installed, "http://im.qq.com/mobileqq/");
            } else if (b()) {
                f.a().b(SimpleLog.LOG_LEVEL_ERROR);
                d();
            }
        } else if (id == R.id.btnWbLogin) {
            a("weibo");
            if (b()) {
                f.a().b(SimpleLog.LOG_LEVEL_DEBUG);
                e();
            }
        } else if (id == R.id.btnRegister) {
            q.a("zb_resign_page_show", "login_home_page", null);
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }

    private void a(String str) {
        q.a("login_page_choice", str, null);
    }

    private boolean b() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        showToast(R.string.no_connection, 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
        return false;
    }

    private void c() {
        showLoadingDialog("\u767b\u5f55\u4e2d...", false);
        XLWxParam xLWxParam = new XLWxParam();
        xLWxParam.mWxAppId = "wx18eada9ea7fbf76c";
        f.a().a(new dl(this));
        XLUserUtil.getInstance().userThirdLogin(MqttConnectOptions.MQTT_VERSION_3_1, xLWxParam, this.e, "wx-login");
    }

    private void d() {
        showLoadingDialog("\u767b\u5f55\u4e2d...", false);
        XLQQParam xLQQParam = new XLQQParam();
        xLQQParam.mAppID = "1105518527";
        XLUserUtil.getInstance().userThirdLogin(SimpleLog.LOG_LEVEL_ERROR, xLQQParam, this.e, "qq-login");
    }

    private void e() {
        showLoadingDialog("\u767b\u5f55\u4e2d...", false);
        XLSinaParam xLSinaParam = new XLSinaParam();
        xLSinaParam.mSinaAppId = "4052521919";
        xLSinaParam.mRedirectUrl = "http://www.xunlei.com";
        XLUserUtil.getInstance().userThirdLogin(SimpleLog.LOG_LEVEL_DEBUG, xLSinaParam, this.e, "sina-login");
    }

    private void f() {
        ((TextView) findViewById(R.id.tvUserProtocol)).setOnClickListener(this);
    }

    public void a() {
        setResult(-1);
        finish();
    }

    public void onBackPressed() {
        if (f.a().b()) {
            if (!f.a().f()) {
                super.onBackPressed();
            }
        } else if (this.a) {
            setResult(0);
            finish();
        } else {
            showToast(R.string.toast_key_back_quit, 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
            this.a = true;
            setTimer(0, c.x);
        }
    }

    protected void onTimer(int i) {
        killTimer(i);
        this.a = false;
    }

    private void a(int i, String str) {
        View inflate = getLayoutInflater().inflate(R.layout.xllive_dialog_wx_not_installed, null);
        Dialog dialog = new Dialog(this, R.style.TransparentDialogStyle);
        dialog.setContentView(inflate);
        dialog.setCanceledOnTouchOutside(true);
        OnClickListener dmVar = new dm(this, dialog, str);
        ((TextView) inflate.findViewById(R.id.text_desc)).setText(i);
        inflate.findViewById(R.id.tvDownload).setOnClickListener(dmVar);
        inflate.findViewById(R.id.ivClose).setOnClickListener(dmVar);
        dialog.show();
        LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.y = 0;
        attributes.x = 0;
        attributes.width = -1;
        attributes.height = -2;
        attributes.gravity = 80;
        dialog.getWindow().setAttributes(attributes);
    }

    private void b(String str) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(str));
        intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
        startActivity(intent);
    }
}
