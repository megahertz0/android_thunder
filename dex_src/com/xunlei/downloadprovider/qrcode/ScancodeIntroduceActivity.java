package com.xunlei.downloadprovider.qrcode;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.widget.TextView;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class ScancodeIntroduceActivity extends BaseActivity implements OnClickListener {
    private View a;
    private View b;
    private View c;
    private TextView d;
    private WebView e;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968934);
        this.a = findViewById(2131756833);
        this.a.setBackgroundColor(Color.parseColor("#ff000000"));
        this.b = findViewById(2131756834);
        this.c = this.b.findViewById(R.id.titlebar_left);
        this.d = (TextView) this.b.findViewById(R.id.titlebar_title);
        this.e = (WebView) findViewById(2131756835);
        this.e.setBackgroundColor(0);
        this.c.setOnClickListener(this);
        this.d.setText(getString(2131232400));
        this.b.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.e.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.e.setWebViewClient(new m(this));
        WebSettings settings = this.e.getSettings();
        this.e.setHorizontalScrollBarEnabled(false);
        this.e.setVerticalScrollbarOverlay(true);
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        this.e.setBackgroundColor(0);
        try {
            this.e.loadUrl("file:///android_asset/help/pai_step.html");
        } catch (Exception e) {
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (4 != i) {
            return super.onKeyUp(i, keyEvent);
        }
        finish();
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.titlebar_left:
                finish();
            default:
                break;
        }
    }
}
