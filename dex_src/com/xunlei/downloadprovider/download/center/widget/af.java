package com.xunlei.downloadprovider.download.center.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.util.n;

// compiled from: TaskQrDialog.java
public final class af extends Dialog {
    private ImageView a;
    private TextView b;
    private ImageView c;
    private View d;
    private ImageView e;

    public af(Context context) {
        super(context, 2131427871);
        this.a = null;
        this.d = null;
        this.d = getLayoutInflater().inflate(2130968999, null);
        this.a = (ImageView) this.d.findViewById(2131757050);
        this.b = (TextView) this.d.findViewById(2131757048);
        this.c = (ImageView) this.d.findViewById(2131757047);
        this.e = (ImageView) this.d.findViewById(2131757049);
        this.e.setOnClickListener(new ag(this));
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        setContentView(this.d);
    }

    public final void a(Bitmap bitmap, a aVar) {
        this.b.setText(aVar.mFileName);
        this.c.setImageResource(n.c(aVar));
        if (bitmap == null) {
            this.a.setImageResource(2130838827);
        } else {
            this.a.setImageBitmap(bitmap);
        }
        show();
    }
}
