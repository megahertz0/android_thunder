package com.xunlei.tdlive.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: ActionSheetDialog.java
public class a extends h implements OnClickListener {
    DialogInterface.OnClickListener a;
    String b;
    String c;
    String[] d;

    public a(Context context, String str, String str2, String... strArr) {
        super(context, R.style.ActionSheetDialogStyle);
        setCanceledOnTouchOutside(true);
        a(str);
        b(str2);
        a(strArr);
    }

    public a a(DialogInterface.OnClickListener onClickListener) {
        this.a = onClickListener;
        return this;
    }

    public a a(String str) {
        this.b = str;
        a();
        return this;
    }

    public a b(String str) {
        this.c = str;
        a();
        return this;
    }

    public a a(String... strArr) {
        this.d = strArr;
        a();
        return this;
    }

    public void b(DialogInterface.OnClickListener onClickListener) {
        a(onClickListener);
        setOnCancelListener(new b(this));
        show();
    }

    private void a() {
        int i = 1;
        int i2 = 0;
        Integer valueOf = Integer.valueOf(0);
        TextView textView = (TextView) findViewById(R.id.title);
        if (textView != null) {
            int i3;
            TextView textView2 = (TextView) findViewById(R.id.cancel);
            TextView textView3 = (TextView) findViewById(R.id.other1);
            View findViewById = findViewById(R.id.other_sep);
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
            textView2.setOnClickListener(this);
            int intValue = valueOf.intValue();
            if (this.c == null) {
                i3 = 0;
            } else {
                i3 = 1;
            }
            Integer valueOf2 = Integer.valueOf(i3 + intValue);
            if (this.d == null || this.d.length == 0 || this.d[0] == null) {
                textView3.setVisibility(XZBDevice.Wait);
                return;
            }
            textView3.setVisibility(0);
            textView3.setText(this.d[0]);
            textView3.setOnClickListener(this);
            Integer valueOf3 = Integer.valueOf(valueOf2.intValue() + 1);
            textView3.setTag(valueOf2);
            View[] viewArr = new View[linearLayout.getChildCount()];
            i3 = 0;
            for (intValue = 0; intValue < linearLayout.getChildCount(); intValue++) {
                View childAt = linearLayout.getChildAt(intValue);
                if (!childAt.equals(textView3) && !childAt.equals(findViewById)) {
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
            while (i < this.d.length) {
                if (this.d[i] != null) {
                    View view2 = new View(getContext());
                    if (VERSION.SDK_INT >= 16) {
                        view2.setBackground(findViewById.getBackground());
                    } else {
                        view2.setBackgroundColor(getContext().getResources().getColor(R.color.dialog_sheet_div));
                    }
                    view2.setLayoutParams(findViewById.getLayoutParams());
                    View textView4 = new TextView(getContext());
                    textView4.setBackgroundResource(R.drawable.xllive_action_sheet_dialog_item_selector);
                    textView4.setLayoutParams(textView3.getLayoutParams());
                    textView4.setGravity(textView3.getGravity());
                    textView4.setTextColor(textView3.getTextColors());
                    textView4.setTextSize(14.0f);
                    textView4.setText(this.d[i]);
                    textView4.setOnClickListener(this);
                    valueOf2 = Integer.valueOf(num.intValue() + 1);
                    textView4.setTag(num);
                    linearLayout.addView(view2);
                    linearLayout.addView(textView4);
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
        setContentView(R.layout.xllive_dialog_action_sheet);
        LayoutParams attributes = getWindow().getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        attributes.height = -2;
        attributes.width = -1;
        attributes.gravity = 80;
        getWindow().setAttributes(attributes);
        a();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.cancel) {
            cancel();
        } else if (this.a != null) {
            this.a.onClick(this, ((Integer) view.getTag()).intValue());
        }
    }
}
