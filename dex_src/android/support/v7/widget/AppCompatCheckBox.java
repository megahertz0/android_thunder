package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.TintableCompoundButton;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.widget.CheckBox;

public class AppCompatCheckBox extends CheckBox implements TintableCompoundButton {
    private r a;
    private q b;

    public AppCompatCheckBox(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkboxStyle);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeSet, int i) {
        super(cj.a(context), attributeSet, i);
        this.a = r.a();
        this.b = new q(this, this.a);
        this.b.a(attributeSet, i);
    }

    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable(drawable);
        if (this.b != null) {
            this.b.a();
        }
    }

    public void setButtonDrawable(int i) {
        setButtonDrawable(this.a != null ? this.a.a(getContext(), i, false) : ContextCompat.getDrawable(getContext(), i));
    }

    public int getCompoundPaddingLeft() {
        int compoundPaddingLeft = super.getCompoundPaddingLeft();
        return this.b != null ? this.b.a(compoundPaddingLeft) : compoundPaddingLeft;
    }

    public void setSupportButtonTintList(ColorStateList colorStateList) {
        if (this.b != null) {
            this.b.a(colorStateList);
        }
    }

    public ColorStateList getSupportButtonTintList() {
        return this.b != null ? this.b.a : null;
    }

    public void setSupportButtonTintMode(Mode mode) {
        if (this.b != null) {
            this.b.a(mode);
        }
    }

    public Mode getSupportButtonTintMode() {
        return this.b != null ? this.b.b : null;
    }
}
