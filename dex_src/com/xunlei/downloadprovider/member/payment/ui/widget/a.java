package com.xunlei.downloadprovider.member.payment.ui.widget;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.xunlei.tdlive.R;

// compiled from: PaymentTypeWindow.java
public final class a extends AlertDialog implements OnClickListener {
    public a a;
    private View b;
    private View c;
    private View d;

    // compiled from: PaymentTypeWindow.java
    public static interface a {
        void a();

        void b();
    }

    public a(Context context) {
        super(context);
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCanceledOnTouchOutside(true);
        this.b = LayoutInflater.from(getContext()).inflate(2130968910, null);
        this.c = this.b.findViewById(2131756150);
        this.d = this.b.findViewById(2131756145);
        setContentView(this.b);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        Window window = getWindow();
        window.setGravity(R.styleable.AppCompatTheme_listChoiceBackgroundIndicator);
        window.setWindowAnimations(2131427936);
        window.setBackgroundDrawable(new ColorDrawable(-1342177280));
    }

    public final void show() {
        super.show();
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        getWindow().setAttributes(attributes);
    }

    public final void onClick(View view) {
        if (this.a != null) {
            switch (view.getId()) {
                case 2131756145:
                    this.a.a();
                case 2131756150:
                    this.a.b();
                default:
                    break;
            }
        }
    }
}
