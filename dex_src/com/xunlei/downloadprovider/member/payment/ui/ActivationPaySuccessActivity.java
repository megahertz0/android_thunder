package com.xunlei.downloadprovider.member.payment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.SimpleTitleBar;
import com.xunlei.downloadprovider.member.payment.a.j;
import com.xunlei.downloadprovider.member.payment.b;
import com.xunlei.downloadprovider.member.payment.external.PayUtil;
import com.xunlei.tdlive.R;

public class ActivationPaySuccessActivity extends BaseActivity implements OnClickListener {
    private SimpleTitleBar a;
    private TextView b;
    private Button c;

    public ActivationPaySuccessActivity() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968602);
        this.a = (SimpleTitleBar) findViewById(2131755178);
        this.a.setTitleText(2131231914);
        this.a.setBtnLeftListener(this);
        this.b = (TextView) findViewById(2131755183);
        this.b.setText(j.a().a.o());
        this.c = (Button) findViewById(2131755177);
        this.c.setOnClickListener(this);
        b.g();
    }

    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755177:
                PayUtil.a();
                a();
                finish();
            case R.id.simple_title_left:
                PayUtil.a();
                a();
                finish();
            default:
                break;
        }
    }

    private void a() {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent("action.type.activation.pay.success"));
    }

    public void onBackPressed() {
        PayUtil.a();
        a();
        finish();
        super.onBackPressed();
    }
}
