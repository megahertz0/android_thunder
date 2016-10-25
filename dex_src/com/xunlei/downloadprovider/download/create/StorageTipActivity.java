package com.xunlei.downloadprovider.download.create;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.businessutil.a;
import com.xunlei.downloadprovider.businessutil.b;
import com.xunlei.downloadprovider.commonview.RoundProgressBar;
import com.xunlei.downloadprovider.personal.settings.ChooseSDcardActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.d;

public class StorageTipActivity extends BaseActivity implements OnClickListener {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private RoundProgressBar e;

    public StorageTipActivity() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }

    protected void onCreate(Bundle bundle) {
        long b;
        long a;
        int i;
        long j = 0;
        super.onCreate(bundle);
        setContentView(2130968725);
        this.a = (TextView) findViewById(2131755711);
        this.b = (TextView) findViewById(2131755712);
        this.c = (TextView) findViewById(2131755713);
        this.d = (TextView) findViewById(2131755715);
        this.e = (RoundProgressBar) findViewById(2131755714);
        this.d.setVisibility(XZBDevice.Wait);
        findViewById(2131755716).setOnClickListener(this);
        String a2 = a.a();
        int d = b.a().d();
        String b2;
        if (1 == d) {
            b2 = k.b();
            b = k.b(b2);
            a = k.a(b2);
            i = 2131231974;
        } else if (2 == d) {
            b2 = k.c();
            b = k.b(b2);
            a = k.a(b2);
            i = 2131232399;
        } else {
            a = 0;
            b = 0;
            i = 0;
        }
        this.a.setText(getString(i) + a2 + getString(2131232649));
        this.e.setText(getString(2131232640, new Object[]{com.xunlei.downloadprovider.d.a.a(b, 1)}));
        if (a >= b) {
            j = a - b;
        }
        this.e.setMax(a);
        this.e.setProgress(j);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            boolean z;
            int x = (int) motionEvent.getX();
            int y = (int) motionEvent.getY();
            int scaledWindowTouchSlop = ViewConfiguration.get(this).getScaledWindowTouchSlop();
            View decorView = getWindow().getDecorView();
            if (x < (-scaledWindowTouchSlop) || y < (-scaledWindowTouchSlop) || x > decorView.getWidth() + scaledWindowTouchSlop || y > decorView.getHeight() + scaledWindowTouchSlop) {
                z = true;
            } else {
                Object obj = null;
            }
            if (z) {
                finish();
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = d.a(getApplicationContext()) - g.a(this, 36.0f);
        getWindow().setAttributes(attributes);
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755715:
                finish();
                ChooseSDcardActivity.a((Context) this);
            case 2131755716:
                finish();
            default:
                break;
        }
    }
}
