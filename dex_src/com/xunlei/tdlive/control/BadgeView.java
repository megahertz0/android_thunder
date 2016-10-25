package com.xunlei.tdlive.control;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.TabWidget;
import android.widget.TextView;
import org.android.spdy.SpdyProtocol;

public class BadgeView extends TextView {
    public static final int POSITION_BOTTOM_LEFT = 3;
    public static final int POSITION_BOTTOM_RIGHT = 4;
    public static final int POSITION_CENTER = 5;
    public static final int POSITION_TOP_LEFT = 1;
    public static final int POSITION_TOP_RIGHT = 2;
    private static final int a;
    private static Animation b;
    private static Animation c;
    private Context d;
    private View e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private ShapeDrawable k;
    private int l;

    static {
        a = Color.parseColor("#CCFF0000");
    }

    public BadgeView(Context context) {
        this(context, null, 16842884);
    }

    public BadgeView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 16842884);
    }

    public BadgeView(Context context, View view) {
        this(context, null, 16842884, view, 0);
    }

    public BadgeView(Context context, TabWidget tabWidget, int i) {
        this(context, null, 16842884, tabWidget, i);
    }

    public BadgeView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet, i, null, 0);
    }

    public BadgeView(Context context, AttributeSet attributeSet, int i, View view, int i2) {
        super(context, attributeSet, i);
        a(context, view, i2);
    }

    private void a(Context context, View view, int i) {
        this.d = context;
        this.e = view;
        this.l = i;
        this.f = 5;
        this.g = a((int) POSITION_CENTER);
        this.h = this.g;
        this.i = a;
        int a = a((int) POSITION_CENTER);
        setPadding(a, 0, a, 0);
        setTextColor(-1);
        Animation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        b = alphaAnimation;
        alphaAnimation.setInterpolator(new DecelerateInterpolator());
        b.setDuration(200);
        alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        c = alphaAnimation;
        alphaAnimation.setInterpolator(new AccelerateInterpolator());
        c.setDuration(200);
        this.j = false;
        if (this.e != null) {
            a(this.e);
        } else {
            show();
        }
    }

    private void a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        ViewParent parent = view.getParent();
        View frameLayout = new FrameLayout(this.d);
        if (view instanceof TabWidget) {
            View childTabViewAt = ((TabWidget) view).getChildTabViewAt(this.l);
            this.e = childTabViewAt;
            ((ViewGroup) childTabViewAt).addView(frameLayout, new LayoutParams(-1, -1));
            setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            frameLayout.addView(this);
            return;
        }
        ViewGroup viewGroup = (ViewGroup) parent;
        int indexOfChild = viewGroup.indexOfChild(view);
        viewGroup.removeView(view);
        viewGroup.addView(frameLayout, indexOfChild, layoutParams);
        frameLayout.addView(view);
        setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        frameLayout.addView(this);
        viewGroup.invalidate();
    }

    public void show() {
        a(false, null);
    }

    public void show(boolean z) {
        a(z, b);
    }

    public void show(Animation animation) {
        a(true, animation);
    }

    public void hide() {
        b(false, null);
    }

    public void hide(boolean z) {
        b(z, c);
    }

    public void hide(Animation animation) {
        b(true, animation);
    }

    public void toggle() {
        a(false, null, null);
    }

    public void toggle(boolean z) {
        a(z, b, c);
    }

    public void toggle(Animation animation, Animation animation2) {
        a(true, animation, animation2);
    }

    private void a(boolean z, Animation animation) {
        if (getBackground() == null) {
            if (this.k == null) {
                this.k = getDefaultBackground();
            }
            setBackgroundDrawable(this.k);
        }
        a();
        if (z) {
            startAnimation(animation);
        }
        setVisibility(0);
        this.j = true;
    }

    private void b(boolean z, Animation animation) {
        setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (z) {
            startAnimation(animation);
        }
        this.j = false;
    }

    private void a(boolean z, Animation animation, Animation animation2) {
        boolean z2 = true;
        if (this.j) {
            if (!z || animation2 == null) {
                z2 = false;
            }
            b(z2, animation2);
            return;
        }
        if (!z || animation == null) {
            z2 = false;
        }
        a(z2, animation);
    }

    public int increment(int i) {
        int parseInt;
        CharSequence text = getText();
        if (text != null) {
            try {
                parseInt = Integer.parseInt(text.toString());
            } catch (NumberFormatException e) {
            }
            parseInt += i;
            setText(String.valueOf(parseInt));
            return parseInt;
        }
        parseInt = 0;
        parseInt += i;
        setText(String.valueOf(parseInt));
        return parseInt;
    }

    public int decrement(int i) {
        return increment(-i);
    }

    private ShapeDrawable getDefaultBackground() {
        int a = a((int) SpdyProtocol.PUBKEY_SEQ_ADASH);
        ShapeDrawable shapeDrawable = new ShapeDrawable(new RoundRectShape(new float[]{(float) a, (float) a, (float) a, (float) a, (float) a, (float) a, (float) a, (float) a}, null, null));
        shapeDrawable.getPaint().setColor(this.i);
        return shapeDrawable;
    }

    private void a() {
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        switch (this.f) {
            case POSITION_TOP_LEFT:
                layoutParams.gravity = 51;
                layoutParams.setMargins(this.g, this.h, 0, 0);
                break;
            case POSITION_TOP_RIGHT:
                layoutParams.gravity = 53;
                layoutParams.setMargins(0, this.h, this.g, 0);
                break;
            case POSITION_BOTTOM_LEFT:
                layoutParams.gravity = 83;
                layoutParams.setMargins(this.g, 0, 0, this.h);
                break;
            case POSITION_BOTTOM_RIGHT:
                layoutParams.gravity = 85;
                layoutParams.setMargins(0, 0, this.g, this.h);
                break;
            case POSITION_CENTER:
                layoutParams.gravity = 17;
                layoutParams.setMargins(0, 0, 0, 0);
                break;
        }
        setLayoutParams(layoutParams);
    }

    public View getTarget() {
        return this.e;
    }

    public boolean isShown() {
        return this.j;
    }

    public int getBadgePosition() {
        return this.f;
    }

    public void setBadgePosition(int i) {
        this.f = i;
    }

    public int getHorizontalBadgeMargin() {
        return this.g;
    }

    public int getVerticalBadgeMargin() {
        return this.h;
    }

    public void setBadgeMargin(int i) {
        this.g = i;
        this.h = i;
    }

    public void setBadgeMargin(int i, int i2) {
        this.g = i;
        this.h = i2;
    }

    public int getBadgeBackgroundColor() {
        return this.i;
    }

    public void setBadgeBackgroundColor(int i) {
        this.i = i;
        this.k = getDefaultBackground();
    }

    private int a(int i) {
        return (int) TypedValue.applyDimension(POSITION_TOP_LEFT, (float) i, getResources().getDisplayMetrics());
    }
}
