package com.xunlei.tdlive;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.EditText;
import com.xunlei.tdlive.base.h;
import com.xunlei.xiazaibao.BuildConfig;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: XDebugDialog.java
public class fz extends h {
    private String a;
    private EditText b;
    private a$a c;

    public fz(Context context, String str) {
        super(context, R.style.TransparentDialogStyle);
        this.a = BuildConfig.VERSION_NAME;
        this.b = null;
        this.c = new ga(this);
        setCanceledOnTouchOutside(true);
        this.a = str;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View editText = new EditText(getContext());
        this.b = editText;
        setContentView(editText);
        this.b.setFocusable(false);
        this.b.setTextColor(-1463751);
        this.b.setTextSize(12.0f);
        this.b.setGravity(SimpleLog.LOG_LEVEL_ERROR);
        this.b.setBackgroundColor(0);
        this.b.setSingleLine(false);
        this.b.setShadowLayer(1.0f, 1.0f, 1.0f, -16777216);
        this.b.setTypeface(Typeface.DEFAULT_BOLD);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 21;
        getWindow().setAttributes(attributes);
    }

    protected void onStart() {
        super.onStart();
        a.a(this.c, true);
        a.b(this.a);
    }

    protected void onStop() {
        super.onStop();
        a.a(this.c, false);
        a.b(null);
    }
}
