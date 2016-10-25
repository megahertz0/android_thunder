package com.xunlei.tdlive;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.util.XLog;
import org.android.spdy.SpdyProtocol;

public class AboutUsActivity extends BaseActivity implements OnClickListener, OnLongClickListener {
    private int a;

    public AboutUsActivity() {
        this.a = 0;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_about_us);
        setTitle("\u5173\u4e8e\u6211\u4eec");
        setLeftVisible(true);
        setLeftClickListener(this);
        setLeftDrawable(getResources().getDrawable(R.drawable.xllive_ic_back));
        try {
            PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            TextView textView = (TextView) findViewById(R.id.tvVersionName);
            textView.setText(new StringBuilder("V ").append(packageInfo.versionName).toString());
            textView.setOnLongClickListener(this);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
        findViewById(R.id.xllive_logo).setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == this.mTitleBarLeft) {
            finish();
        } else if (view.getId() == R.id.xllive_logo) {
            int i = this.a + 1;
            this.a = i;
            if (i >= 5) {
                this.a = 0;
                if (getInt("xllive_enable_log", 0) == 0) {
                    putInt("xllive_enable_log", 1);
                    XLog.enableLog(true);
                    showToast("\u5df2\u5f00\u542f\u65e5\u5fd7\r\n\u518d\u6b21\u8fde\u7eed\u70b9\u51fb5\u6b21\u53ef\u5173\u95ed", 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
                    return;
                }
                putInt("xllive_enable_log", 0);
                XLog.enableLog(false);
                showToast("\u5df2\u5173\u95ed\u65e5\u5fd7", 0, R.layout.xllive_common_toast, R.id.text, SpdyProtocol.CUSTOM);
            }
        } else if (view.getId() == R.id.tvReport) {
            WebBrowserActivity.start(this, "http://show.xunlei.com/mm/sign/?from=gwgg", "\u62a5\u540d", false);
        }
    }

    public boolean onLongClick(View view) {
        if (view.getId() == R.id.tvVersionName) {
            WebBrowserActivity.start(this, "about:blank", null, true, true);
        }
        return true;
    }
}
