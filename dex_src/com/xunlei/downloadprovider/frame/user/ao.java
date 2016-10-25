package com.xunlei.downloadprovider.frame.user;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import com.xunlei.tdlive.R;

// compiled from: PersonalSpaceMenuDialog.java
public final class ao extends Dialog {
    Button a;
    private View b;
    private Button c;

    protected ao(Context context) {
        super(context);
        this.b = LayoutInflater.from(context).inflate(2130968911, null);
        this.a = (Button) this.b.findViewById(2131756551);
        this.c = (Button) this.b.findViewById(2131756553);
        this.c.setOnClickListener(new ap(this));
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setCanceledOnTouchOutside(true);
        getContext();
        setContentView(this.b);
        Window window = getWindow();
        window.setGravity(R.styleable.AppCompatTheme_listChoiceBackgroundIndicator);
        window.setWindowAnimations(R.style.bottom_dialog_animation);
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
}
