package com.xunlei.tdlive;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.xunlei.common.member.XLUserUtil;
import com.xunlei.tdlive.b.a;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.protocol.DNSCache;
import com.xunlei.tdlive.protocol.XLLiveRequest;
import com.xunlei.tdlive.protocol.XLLiveRequest.IDNSCache;
import com.xunlei.tdlive.user.DefaultXLOnUserListener;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.ac;
import java.util.ArrayList;

public class SettingActivity extends BaseActivity implements OnClickListener, OnLongClickListener, OnCheckedChangeListener {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private DefaultXLOnUserListener e;
    private int f;

    public SettingActivity() {
        this.a = 0;
        this.b = 1;
        this.c = 2;
        this.d = 3;
        this.f = 0;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_activity_settings);
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.tbPush);
        toggleButton.setChecked(a.a((Context) this).b());
        toggleButton.setOnCheckedChangeListener(this);
        int i = R.drawable.xllive_debug_black;
        if ("Test".equals(ac.d("UMENG_CHANNEL"))) {
            i = R.drawable.xllive_debug;
        }
        setRightVisible(true);
        setRightDrawable(getResources().getDrawable(i));
        setRightClickListener(this);
        setRightLongClickListener(this);
        findViewById(R.id.lvAboutUs).setOnClickListener(this);
        findViewById(R.id.lvFeedback).setOnClickListener(this);
        findViewById(R.id.lvUserProtocol).setOnClickListener(this);
        findViewById(R.id.lvCheckUpdate).setOnClickListener(this);
        findViewById(R.id.btnLogout).setOnClickListener(this);
        this.e = new ei(this);
    }

    protected void onResume() {
        super.onResume();
        Resources resources = getResources();
        setLeftVisible(true);
        setLeftClickListener(this);
        setLeftDrawable(resources.getDrawable(R.drawable.xllive_ic_back));
        setTitle(resources.getString(R.string.settings));
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.lvFeedback) {
            startActivity(new Intent(this, FeedbackActivity.class));
        } else if (id == R.id.lvAboutUs) {
            WebBrowserActivity.start(this, "http://h5.live.xunlei.com/android/about.html", "\u5173\u4e8e\u6211\u4eec", false);
        } else if (id == R.id.lvUserProtocol) {
            WebBrowserActivity.start(this, "http://h5.live.xunlei.com/android/tos.html", "\u7528\u6237\u534f\u8bae", false);
        } else if (id == R.id.btnLogout) {
            if (f.a().b()) {
                XLUserUtil.getInstance().userLogout(this.e, null);
            }
        } else if (id == R.id.left) {
            finish();
        } else if (view != this.mTitleBarRight && id == R.id.lvCheckUpdate) {
            TextView textView = (TextView) findViewById(R.id.tvUpdateResult);
            if (this.f == 0) {
                checkUpdate(new ej(this, textView));
            } else if (this.f == 2) {
                com.xunlei.tdlive.c.a.a(getApplicationContext());
            }
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        a.a((Context) this).a(z);
    }

    public boolean onLongClick(View view) {
        int i = 0;
        if (view == this.mTitleBarRight) {
            String str;
            int i2 = getInt("xllive_enable_log", 0);
            ArrayList arrayList = new ArrayList();
            arrayList.add(i2 == 0 ? "\u5f00\u542f\u65e5\u5fd7" : "\u5173\u95ed\u65e5\u5fd7");
            arrayList.add("\u65e0\u9650\u7f51\u901f");
            arrayList.add("\u8c03\u8bd5WebView");
            arrayList.add("\u6b63\u5f0f\u73af\u5883");
            IDNSCache[] dNSCaches = DNSCache.getDNSCaches();
            int length = dNSCaches.length;
            for (int i3 = 0; i3 < length; i3++) {
                arrayList.add(dNSCaches[i3].disc());
            }
            String[] strArr = new String[arrayList.size()];
            while (i < arrayList.size()) {
                strArr[i] = (String) arrayList.get(i);
                i++;
            }
            IDNSCache dNSCahce = XLLiveRequest.getDNSCahce();
            StringBuilder stringBuilder = new StringBuilder("\u60a8\u5728");
            if (dNSCahce == null) {
                str = "\u6b63\u5f0f\u73af\u5883";
            } else {
                str = dNSCahce.disc();
            }
            new com.xunlei.tdlive.base.a(this, stringBuilder.append(str).toString(), "\u53d6\u6d88", strArr).b(new ek(this, i2, arrayList));
        }
        return true;
    }
}
