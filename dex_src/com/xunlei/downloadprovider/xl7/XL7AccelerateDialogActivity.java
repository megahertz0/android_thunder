package com.xunlei.downloadprovider.xl7;

import android.os.Bundle;
import android.widget.ImageView;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.task.ThunderTask;

public class XL7AccelerateDialogActivity extends ThunderTask {
    b a;
    private ImageView b;
    private a c;

    public XL7AccelerateDialogActivity() {
        this.c = new c(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968626);
        this.a = new b(this.c);
        this.b = (ImageView) findViewById(2131755329);
        this.b.setOnClickListener(new d(this));
    }

    public void onBackPressed() {
    }
}
