package com.xunlei.downloadprovider.frame.user.account.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class UserAccountItem extends FrameLayout {
    private TextView a;
    private TextView b;
    private ImageView c;
    private ImageView d;

    public UserAccountItem(Context context) {
        super(context);
        a(context);
    }

    public UserAccountItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public UserAccountItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2130969027, null);
        this.a = (TextView) inflate.findViewById(2131757142);
        this.b = (TextView) inflate.findViewById(2131757144);
        this.c = (ImageView) inflate.findViewById(2131757145);
        this.d = (ImageView) inflate.findViewById(2131757143);
        addView(inflate);
    }

    public void setAccountItemName(int i) {
        this.a.setText(i);
    }

    public void setAccountItemTipPicVisible(int i) {
        this.c.setVisibility(i);
    }

    public ImageView getAccountItemTipPic() {
        return this.c;
    }

    public TextView getAccountItemTipText() {
        return this.b;
    }

    public ImageView getAccountItemTipIcon() {
        return this.d;
    }

    public void setAccountItemTipIcon(Drawable drawable) {
        this.d.setImageDrawable(drawable);
    }
}
