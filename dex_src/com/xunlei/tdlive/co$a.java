package com.xunlei.tdlive;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import android.widget.RelativeLayout;
import com.xunlei.tdlive.base.h;
import com.xunlei.tdlive.util.d;

// compiled from: LivePublishDialog.java
public class co$a extends h {
    final /* synthetic */ co a;

    public co$a(co coVar, Context context) {
        this.a = coVar;
        super(context, R.style.TransparentDialogStyle);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_dialog_publish_wrapper);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.width = -1;
        attributes.height = d.a(getOwnerActivity()).y;
        attributes.softInputMode = 36;
        getWindow().setAttributes(attributes);
        getWindow().getDecorView().setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    }
}
