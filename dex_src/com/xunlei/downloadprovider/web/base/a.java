package com.xunlei.downloadprovider.web.base;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.d;
import com.nostra13.universalimageloader.core.e;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.c.a.c;
import java.util.HashMap;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: CommentDialog.java
public final class a extends Dialog {
    EditText a;
    public c b;
    HashMap<Long, String> c;
    private d d;
    private com.nostra13.universalimageloader.core.c e;
    private View f;
    private Button g;
    private TextView h;
    private OnDismissListener i;
    private View j;
    private ImageView k;
    private TextView l;

    public a(Context context) {
        super(context);
        this.f = LayoutInflater.from(context).inflate(R.layout.comment_dialog, null);
        this.j = this.f.findViewById(R.id.lyt_target);
        this.k = (ImageView) this.f.findViewById(R.id.iv_avatar);
        this.l = (TextView) this.f.findViewById(R.id.tv_target_content);
        this.a = (EditText) this.f.findViewById(R.id.et_content);
        this.h = (TextView) this.f.findViewById(R.id.tv_left_count);
        this.a.addTextChangedListener(new b(this));
        this.g = (Button) this.f.findViewById(R.id.btn_send);
        this.d = d.a();
        this.d.a(e.a(context));
        com.nostra13.universalimageloader.core.c.a aVar = new com.nostra13.universalimageloader.core.c.a();
        aVar.a = 2130838392;
        aVar.b = 2130838392;
        aVar.c = 2130838392;
        aVar.m = true;
        aVar.h = true;
        aVar.a();
        aVar.q = new com.nostra13.universalimageloader.core.b.c(Integer.MAX_VALUE);
        this.e = aVar.b();
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCanceledOnTouchOutside(true);
        getContext();
        setContentView(this.f);
        Window window = getWindow();
        window.setGravity(com.xunlei.xllib.R.styleable.AppCompatTheme_listChoiceBackgroundIndicator);
        window.setWindowAnimations(com.xunlei.downloadprovidershare.R.style.bottom_dialog_animation);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
        this.c = new HashMap();
        super.setOnDismissListener(new c(this));
    }

    public final void show() {
        super.show();
        getWindow().setSoftInputMode(SimpleLog.LOG_LEVEL_ERROR);
    }

    public final void setOnDismissListener(OnDismissListener onDismissListener) {
        this.i = onDismissListener;
    }

    public final void a(OnClickListener onClickListener) {
        this.g.setOnClickListener(onClickListener);
    }

    public final CharSequence a() {
        return this.a.getText().toString();
    }

    public final void a(String str) {
        this.a.setText(str);
        if (!TextUtils.isEmpty(str)) {
            this.a.setSelection(str.length());
        }
    }

    public final void b(String str) {
        this.a.setHint(str);
    }

    public final void a(c cVar) {
        if (cVar != this.b) {
            if (cVar == null || this.b == null || cVar.a != this.b.a) {
                this.b = cVar;
                String str;
                if (this.b != null) {
                    str = (String) this.c.get(Long.valueOf(this.b.a));
                    this.j.setVisibility(0);
                    this.l.setText(this.b.b);
                    Object obj = this.b.k;
                    if (!TextUtils.isEmpty(obj)) {
                        this.d.a(obj, this.k, this.e);
                    }
                } else {
                    str = (String) this.c.get(Long.valueOf(-1));
                    this.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                }
                this.a.setText(r0);
                this.a.setSelection(r0 == null ? 0 : r0.length());
            }
        }
    }

    public final void a(boolean z) {
        this.g.setEnabled(z);
    }

    public final void a(long j) {
        if (j == -1) {
            this.c.remove(Long.valueOf(-1));
        } else {
            this.c.remove(Long.valueOf(j));
        }
    }
}
