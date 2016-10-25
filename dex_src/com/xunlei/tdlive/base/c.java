package com.xunlei.tdlive.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: AlertDialog.java
public class c extends h implements OnClickListener {
    DialogInterface.OnClickListener a;
    String b;
    String c;
    String d;
    String[] e;

    public c(Context context, String str, String str2, String str3, String... strArr) {
        super(context, R.style.TransparentDialogStyle);
        setCanceledOnTouchOutside(true);
        a(str);
        b(str2);
        c(str3);
        a(strArr);
    }

    public c a(DialogInterface.OnClickListener onClickListener) {
        this.a = onClickListener;
        return this;
    }

    public c a(String str) {
        this.b = str;
        a();
        return this;
    }

    public c b(String str) {
        this.c = str;
        a();
        return this;
    }

    public c c(String str) {
        this.d = str;
        a();
        return this;
    }

    public c a(String... strArr) {
        this.e = strArr;
        a();
        return this;
    }

    public void b(DialogInterface.OnClickListener onClickListener) {
        a(onClickListener);
        setOnCancelListener(new d(this));
        show();
    }

    private void a() {
        int i = 1;
        int i2 = 0;
        Integer valueOf = Integer.valueOf(0);
        TextView textView = (TextView) findViewById(R.id.title);
        if (textView != null) {
            int i3;
            TextView textView2 = (TextView) findViewById(R.id.message);
            TextView textView3 = (TextView) findViewById(R.id.cancel);
            View findViewById = findViewById(R.id.cancel_sep);
            TextView textView4 = (TextView) findViewById(R.id.other1);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.button_layout);
            textView.setVisibility(this.b == null ? 8 : 0);
            textView.setText(this.b);
            if (this.c == null) {
                i3 = 8;
            } else {
                i3 = 0;
            }
            textView2.setVisibility(i3);
            textView2.setText(this.c);
            if (this.d == null) {
                i3 = 8;
            } else {
                i3 = 0;
            }
            findViewById.setVisibility(i3);
            if (this.d == null) {
                i3 = 8;
            } else {
                i3 = 0;
            }
            textView3.setVisibility(i3);
            textView3.setText(this.d);
            textView3.setOnClickListener(this);
            int intValue = valueOf.intValue();
            if (this.d == null) {
                i3 = 0;
            } else {
                i3 = 1;
            }
            Integer valueOf2 = Integer.valueOf(i3 + intValue);
            if (this.e == null || this.e.length == 0 || this.e[0] == null) {
                textView4.setVisibility(XZBDevice.Wait);
                return;
            }
            textView4.setVisibility(0);
            textView4.setText(this.e[0]);
            textView4.setOnClickListener(this);
            Integer valueOf3 = Integer.valueOf(valueOf2.intValue() + 1);
            textView4.setTag(valueOf2);
            View[] viewArr = new View[linearLayout.getChildCount()];
            i3 = 0;
            for (intValue = 0; intValue < linearLayout.getChildCount(); intValue++) {
                View childAt = linearLayout.getChildAt(intValue);
                if (!childAt.equals(findViewById) && !childAt.equals(textView3) && !childAt.equals(textView4)) {
                    int i4 = i3 + 1;
                    viewArr[i3] = childAt;
                    i3 = i4;
                }
            }
            i3 = viewArr.length;
            while (i2 < i3) {
                View view = viewArr[i2];
                if (view != null) {
                    linearLayout.removeView(view);
                }
                i2++;
            }
            Integer num = valueOf3;
            while (i < this.e.length) {
                if (this.e[i] != null) {
                    View view2 = new View(getContext());
                    view2.setBackground(findViewById.getBackground());
                    view2.setLayoutParams(findViewById.getLayoutParams());
                    View textView5 = new TextView(getContext());
                    textView5.setLayoutParams(textView4.getLayoutParams());
                    textView5.setGravity(textView4.getGravity());
                    textView5.setTextColor(textView4.getTextColors());
                    textView5.setTextSize(18.0f);
                    textView5.setText(this.e[i]);
                    textView5.setOnClickListener(this);
                    valueOf2 = Integer.valueOf(num.intValue() + 1);
                    textView5.setTag(num);
                    linearLayout.addView(view2);
                    linearLayout.addView(textView5);
                } else {
                    valueOf2 = num;
                }
                i++;
                num = valueOf2;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.xllive_dialog_alert);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.height = -2;
        attributes.width = -2;
        attributes.gravity = 17;
        attributes.dimAmount = 0.5f;
        getWindow().setAttributes(attributes);
        getWindow().addFlags(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        a();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.cancel) {
            cancel();
        } else if (this.a != null) {
            this.a.onClick(this, ((Integer) view.getTag()).intValue());
        } else {
            dismiss();
        }
    }
}
