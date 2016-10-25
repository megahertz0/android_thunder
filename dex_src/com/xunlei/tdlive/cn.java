package com.xunlei.tdlive;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import com.xunlei.tdlive.base.h;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: LivePublishCloseDialog.java
public class cn extends h implements OnClickListener {
    DialogInterface.OnClickListener a;
    boolean b;

    public cn(Context context) {
        this(context, false);
    }

    public cn(Context context, boolean z) {
        super(context, R.style.TransparentDialogStyle);
        this.b = z;
    }

    public void a(DialogInterface.OnClickListener onClickListener) {
        this.a = onClickListener;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_dialog_publish_close);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.height = -1;
        attributes.width = -1;
        attributes.gravity = 17;
        attributes.dimAmount = 0.5f;
        getWindow().setAttributes(attributes);
        getWindow().addFlags(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        getWindow().getDecorView().setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
        findViewById(R.id.root).setOnClickListener(this);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.ok).setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.root) {
            cancel();
        } else if (view.getId() == R.id.cancel) {
            cancel();
        } else if (view.getId() == R.id.ok) {
            dismiss();
            if (this.a != null) {
                this.a.onClick(this, 1);
            }
        }
    }
}
