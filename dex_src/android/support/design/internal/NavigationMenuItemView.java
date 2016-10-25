package android.support.design.internal;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.StateListDrawable;
import android.support.design.R;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.view.menu.h;
import android.support.v7.view.menu.n.a;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class NavigationMenuItemView extends ForegroundLinearLayout implements a {
    private static final int[] e;
    final CheckedTextView c;
    FrameLayout d;
    private final int f;
    private h g;
    private ColorStateList h;

    static {
        e = new int[]{16842912};
    }

    public NavigationMenuItemView(Context context) {
        this(context, null);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setOrientation(0);
        LayoutInflater.from(context).inflate(R.layout.design_navigation_menu_item, this, true);
        this.f = context.getResources().getDimensionPixelSize(R.dimen.design_navigation_icon_size);
        this.c = (CheckedTextView) findViewById(R.id.design_menu_item_text);
        this.c.setDuplicateParentStateEnabled(true);
    }

    public final void a(h hVar) {
        this.g = hVar;
        setVisibility(hVar.isVisible() ? 0 : XZBDevice.Wait);
        if (getBackground() == null) {
            Drawable stateListDrawable;
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(R.attr.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(e, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            setBackgroundDrawable(stateListDrawable);
        }
        setCheckable(hVar.isCheckable());
        setChecked(hVar.isChecked());
        setEnabled(hVar.isEnabled());
        setTitle(hVar.getTitle());
        setIcon(hVar.getIcon());
        setActionView(hVar.getActionView());
    }

    private void setActionView(View view) {
        if (this.d == null) {
            this.d = (FrameLayout) ((ViewStub) findViewById(R.id.design_menu_item_action_area_stub)).inflate();
        }
        this.d.removeAllViews();
        if (view != null) {
            this.d.addView(view);
        }
    }

    public h getItemData() {
        return this.g;
    }

    public void setTitle(CharSequence charSequence) {
        this.c.setText(charSequence);
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
    }

    public void setChecked(boolean z) {
        refreshDrawableState();
        this.c.setChecked(z);
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            ConstantState constantState = drawable.getConstantState();
            if (constantState != null) {
                drawable = constantState.newDrawable();
            }
            drawable = DrawableCompat.wrap(drawable).mutate();
            drawable.setBounds(0, 0, this.f, this.f);
            DrawableCompat.setTintList(drawable, this.h);
        }
        TextViewCompat.setCompoundDrawablesRelative(this.c, drawable, null, null, null);
    }

    public final boolean a() {
        return false;
    }

    protected int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 1);
        if (this.g != null && this.g.isCheckable() && this.g.isChecked()) {
            mergeDrawableStates(onCreateDrawableState, e);
        }
        return onCreateDrawableState;
    }

    void setIconTintList(ColorStateList colorStateList) {
        this.h = colorStateList;
        if (this.g != null) {
            setIcon(this.g.getIcon());
        }
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.c.setTextColor(colorStateList);
    }
}
