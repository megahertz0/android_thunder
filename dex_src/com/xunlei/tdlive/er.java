package com.xunlei.tdlive;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.tdlive.base.h;
import com.xunlei.tdlive.util.a;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: UserLevelUPDialog.java
public class er extends h implements OnClickListener {
    int a;
    String b;

    public er(Context context, int i, String str) {
        super(context, R.style.TransparentDialogStyle);
        this.a = 0;
        this.b = BuildConfig.VERSION_NAME;
        setCanceledOnTouchOutside(true);
        this.b = str;
        this.a = i;
    }

    private void a() {
        ((TextView) findViewById(R.id.level)).setText(new StringBuilder("\u76f4\u64ad\u7b49\u7ea7\u5347\u4e3a").append(this.a).append("\u7ea7").toString());
        a.a(getContext()).a((ImageView) findViewById(R.id.level_icon), this.b);
        findViewById(R.id.go_level).setOnClickListener(this);
        findViewById(R.id.close).setOnClickListener(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_dialog_level_up);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.height = -2;
        attributes.width = -2;
        attributes.gravity = 17;
        attributes.dimAmount = 0.5f;
        getWindow().setAttributes(attributes);
        getWindow().addFlags(SimpleLog.LOG_LEVEL_DEBUG);
        a();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.go_level) {
            dismiss();
            WebBrowserActivity.start(getContext(), "http://h5.live.xunlei.com/active/realname/level.html", "\u6211\u7684\u7b49\u7ea7", false);
            q.e("level_altert_click").a("level", this.a).a("clickid", "view").b(new String[0]);
        } else if (view.getId() == R.id.close) {
            dismiss();
        }
    }
}
