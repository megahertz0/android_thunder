package com.xunlei.downloadprovider.util;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.widget.TextView;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;

public class XLAlarmDialogActivity extends BaseActivity {
    private static boolean i;
    private View a;
    private View b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private int h;
    private long j;

    static {
        i = false;
    }

    protected void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        this.h = intent.getIntExtra(JsInterface.FUNPLAY_AD_TRPE, 100100);
        this.j = intent.getLongExtra(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID, -1);
        if (this.h == 100102) {
            setTheme(R.style.bt_dialog);
        }
        super.onCreate(bundle);
        setContentView(R.layout.new_xl_dialog);
        this.a = findViewById(R.id.dlg_2btn_layout);
        this.b = findViewById(R.id.dlg_1btn_layout);
        this.c = (TextView) findViewById(R.id.dlg_title);
        this.d = (TextView) findViewById(R.id.dlg_content);
        this.e = (TextView) findViewById(R.id.dlg_left_btn);
        this.f = (TextView) findViewById(R.id.dlg_right_btn);
        this.g = (TextView) findViewById(R.id.dlg_bottom_btn);
        OnClickListener adVar = new ad(this);
        this.e.setOnClickListener(adVar);
        this.g.setOnClickListener(adVar);
        switch (this.h) {
            case 100100:
                this.c.setVisibility(0);
                this.a.setVisibility(0);
                this.c.setVisibility(0);
                this.d.setText(2131233236);
                this.f.setText(2131233235);
                this.f.setOnClickListener(new ae(this));
                break;
            case 100101:
                this.b.setVisibility(0);
                this.d.setText(2131233234);
                this.g.setText(R.string.ok);
                break;
            case 100102:
                this.b.setVisibility(0);
                this.d.setText(2131230804);
                this.g.setText(R.string.gotit);
                this.g.setOnClickListener(new af(this));
                break;
        }
        i = true;
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
            if (z && this.h == 100100) {
                finish();
                return true;
            }
        }
        return super.onTouchEvent(motionEvent);
    }

    protected void onDestroy() {
        super.onDestroy();
        i = false;
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }
}
