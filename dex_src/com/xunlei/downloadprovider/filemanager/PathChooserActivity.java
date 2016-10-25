package com.xunlei.downloadprovider.filemanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.app.BrothersApplication.d;
import com.xunlei.downloadprovider.commonview.a.a;
import com.xunlei.downloadprovider.commonview.f;
import com.xunlei.downloadprovider.filemanager.ui.FileManagerDirView;
import com.xunlei.downloadprovider.filemanager.ui.h;
import com.xunlei.tdlive.R;
import java.io.File;

public class PathChooserActivity extends BaseActivity {
    protected String a;
    private f b;
    private TextView c;
    private View d;
    private FileManagerDirView e;
    private h f;
    private d g;

    public PathChooserActivity() {
        this.a = getClass().getSimpleName();
    }

    public static void a(Activity activity, String str) {
        Intent intent = new Intent(activity, PathChooserActivity.class);
        intent.putExtra("path", str);
        activity.startActivity(intent);
        a.c(activity);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968896);
        this.b = new f((Activity) this);
        this.b.g.setImageResource(R.drawable.common_close_icon_selector);
        this.b.i.setText(2131232462);
        this.b.n.setImageResource(R.drawable.common_add_icon_task_btn_selector);
        this.b.n.setVisibility(0);
        this.c = (TextView) findViewById(2131756705);
        this.e = (FileManagerDirView) findViewById(2131756708);
        this.d = findViewById(2131756706);
        this.e.setOnDirViewStateChangeListener(new k(this));
        this.d.setOnClickListener(new l(this));
        this.b.n.setOnClickListener(new m(this));
        a();
        this.g = new j(this);
        BrothersApplication.a().a(this.g);
    }

    protected void onDestroy() {
        BrothersApplication.a().b(this.g);
        super.onDestroy();
    }

    private void a() {
        String str = null;
        if (this.f != null && this.f.isShowing()) {
            this.f.dismiss();
            this.f = null;
        }
        this.e.d.clear();
        this.e.setJustShowDir(true);
        Intent intent = getIntent();
        if (intent != null) {
            str = intent.getStringExtra("path");
        }
        if (str == null) {
            str = com.xunlei.downloadprovider.businessutil.a.a();
            if (!str.endsWith(File.separator)) {
                str = str + File.separator;
            }
        }
        this.e.setCurrentPath(str);
        this.e.d();
        this.e.a();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 || keyEvent.getAction() != 0 || !this.e.f()) {
            return super.onKeyDown(i, keyEvent);
        }
        this.e.e();
        return true;
    }

    public void finish() {
        super.finish();
        a.d(this);
    }
}
