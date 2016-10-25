package com.xunlei.downloadprovider.web.base;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import org.android.spdy.SpdyProtocol;

// compiled from: CommentMenuDialog.java
public final class d extends Dialog {
    public c a;
    private View b;
    private Button c;
    private Button d;
    private Button e;
    private Button f;

    public d(Context context) {
        super(context);
        this.b = LayoutInflater.from(context).inflate(R.layout.menu_dialog, null);
        this.c = (Button) this.b.findViewById(R.id.btn_copy);
        this.d = (Button) this.b.findViewById(R.id.btn_report);
        this.f = (Button) this.b.findViewById(R.id.btn_delete);
        this.e = (Button) this.b.findViewById(R.id.btn_cancel);
        this.e.setOnClickListener(new e(this));
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCanceledOnTouchOutside(true);
        getContext();
        setContentView(this.b);
        Window window = getWindow();
        window.setGravity(com.xunlei.xllib.R.styleable.AppCompatTheme_listChoiceBackgroundIndicator);
        window.setWindowAnimations(com.xunlei.downloadprovidershare.R.style.bottom_dialog_animation);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    public final void show() {
        super.show();
    }

    public final void a(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public final void b(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    public final void c(OnClickListener onClickListener) {
        this.f.setOnClickListener(onClickListener);
    }

    public final void d(OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    public final void a(c cVar) {
        int i = SpdyProtocol.PUBKEY_SEQ_ADASH;
        if (cVar == null) {
            throw new IllegalArgumentException("target comment info is NULL");
        }
        Button button;
        this.a = cVar;
        LoginHelper.a();
        if (LoginHelper.c()) {
            int i2;
            long j = LoginHelper.a().j;
            Button button2 = this.f;
            if (j == this.a.i) {
                i2 = 0;
            } else {
                i2 = 8;
            }
            button2.setVisibility(i2);
            button = this.d;
            if (j == this.a.i) {
                button.setVisibility(i);
            }
        }
        this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        button = this.d;
        i = 0;
        button.setVisibility(i);
    }
}
