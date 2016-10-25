package com.xunlei.downloadprovider.download.center.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class DownloadTitleBarView extends FrameLayout {
    public View a;
    private LinearLayout b;
    private TextView c;
    private TextView d;
    private ImageView e;
    private ImageView f;
    private ImageView g;
    private ImageView h;
    private View i;
    private int j;
    private int k;

    public DownloadTitleBarView(Context context) {
        super(context);
        this.j = 0;
        this.k = -1;
        a(context);
    }

    public DownloadTitleBarView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.j = 0;
        this.k = -1;
        a(context);
    }

    public DownloadTitleBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.j = 0;
        this.k = -1;
        a(context);
    }

    private void a(Context context) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(2130968736, null);
        this.a = viewGroup.findViewById(R.id.content);
        this.e = (ImageView) viewGroup.findViewById(2131755793);
        this.f = (ImageView) viewGroup.findViewById(2131755799);
        this.b = (LinearLayout) viewGroup.findViewById(2131755796);
        this.c = (TextView) viewGroup.findViewById(2131755797);
        this.d = (TextView) viewGroup.findViewById(2131755794);
        this.g = (ImageView) viewGroup.findViewById(2131755801);
        this.h = (ImageView) viewGroup.findViewById(2131755803);
        this.i = viewGroup.findViewById(2131755798);
        addView(viewGroup);
    }

    public void setTipIcon(int i) {
        if (i == -1) {
            this.f.setVisibility(XZBDevice.Wait);
            return;
        }
        this.f.setVisibility(0);
        if (this.k != i) {
            this.k = i;
            this.f.setImageResource(i);
        }
    }

    public void setTipIconVisibility(int i) {
        this.f.setVisibility(i);
    }

    public void setLeftImageViewClickListener(OnClickListener onClickListener) {
        this.e.setOnClickListener(onClickListener);
    }

    public void setRightImageView1ClickListener(OnClickListener onClickListener) {
        this.g.setOnClickListener(onClickListener);
    }

    public void setRightImageView2ClickListener(OnClickListener onClickListener) {
        this.h.setOnClickListener(onClickListener);
    }

    public void setLeftImageViewImage(int i) {
        this.e.setImageResource(i);
    }

    public void setRightImageView1Image(int i) {
        this.g.setImageResource(i);
    }

    public void setRightImageView2Image(int i) {
        this.h.setImageResource(i);
    }

    public View getRightImageView2() {
        return this.h;
    }

    public int getRightImageView2Width() {
        return this.h.getWidth();
    }

    public int getRightImageView2Height() {
        return this.h.getHeight();
    }

    public void setTouchListener(OnClickListener onClickListener) {
        this.d.setOnClickListener(onClickListener);
    }

    public void setCenterTitle(String str) {
        if (TextUtils.isEmpty(str)) {
            a((int) XZBDevice.Wait, true);
        } else {
            a(0, true);
        }
        this.c.setText(str);
    }

    public void setCenterTitle(int i) {
        if (i == -1) {
            a((int) XZBDevice.Wait, true);
        } else {
            a(0, true);
        }
        this.c.setText(i);
    }

    public final boolean a(int i, boolean z) {
        boolean z2 = this.b.getVisibility() != i;
        Animation animation;
        if (!z) {
            this.b.setVisibility(i);
            if (i != 0) {
                animation = this.b.getAnimation();
                if (animation == null || animation.hasEnded() || this.j == 2) {
                    return z2;
                }
            }
        } else if (i == 0) {
            if (this.b.getVisibility() != 0) {
                Animation animation2 = this.b.getAnimation();
                if (animation2 == null || animation2.hasEnded() || this.j != 1) {
                    animation = AnimationUtils.loadAnimation(getContext(), 2131034128);
                    this.b.setVisibility(i);
                    if (animation != null) {
                        animation.setAnimationListener(new ad(this));
                        this.j = 0;
                        this.b.clearAnimation();
                        this.b.startAnimation(animation);
                    }
                }
            }
        } else if (this.b.getVisibility() == 0) {
            animation = this.b.getAnimation();
            if (animation == null || animation.hasEnded() || this.j != 2) {
                this.b.clearAnimation();
                this.j = 0;
                animation = AnimationUtils.loadAnimation(getContext(), 2131034129);
                if (animation != null) {
                    animation.setAnimationListener(new ae(this, i));
                    this.b.startAnimation(animation);
                } else {
                    this.b.setVisibility(i);
                }
            }
        }
        return z2;
    }

    public void setIconContainerListener(OnClickListener onClickListener) {
        if (onClickListener != null) {
            this.i.setOnClickListener(onClickListener);
            this.i.setClickable(true);
            return;
        }
        this.i.setClickable(false);
    }
}
