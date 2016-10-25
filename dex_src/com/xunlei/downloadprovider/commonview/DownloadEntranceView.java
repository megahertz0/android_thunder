package com.xunlei.downloadprovider.commonview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class DownloadEntranceView extends FrameLayout {
    public a a;
    public boolean b;
    private EntranceStyle c;
    private int d;
    private int e;

    public DownloadEntranceView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = EntranceStyle.STYLE_GRAY;
        this.b = false;
        this.d = 0;
        this.e = 0;
        a(attributeSet, i);
    }

    public DownloadEntranceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = EntranceStyle.STYLE_GRAY;
        this.b = false;
        this.d = 0;
        this.e = 0;
        a(attributeSet, 0);
    }

    public DownloadEntranceView(Context context) {
        super(context);
        this.c = EntranceStyle.STYLE_GRAY;
        this.b = false;
        this.d = 0;
        this.e = 0;
        a(null, 0);
    }

    public EntranceStyle getEntranceStyle() {
        return this.c;
    }

    public void setEntranceStyle(EntranceStyle entranceStyle) {
        if (entranceStyle == null) {
            entranceStyle = EntranceStyle.STYLE_GRAY;
        }
        this.c = entranceStyle;
    }

    private void a(AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.DownloadEntranceView, i, 0);
        if (obtainStyledAttributes.hasValue(R.styleable.DownloadEntranceView_entranceStyle)) {
            int integer = obtainStyledAttributes.getInteger(R.styleable.DownloadEntranceView_entranceStyle, 0);
            if (integer > 0) {
                this.c = EntranceStyle.values()[integer];
            }
        }
        obtainStyledAttributes.recycle();
        Context context = getContext();
        ViewGroup viewGroup;
        if (this.c == EntranceStyle.STYLE_WHITE_IN_BLUE) {
            viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.download_entrance_white_in_blue, this);
        } else {
            viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.download_entrance_gray, this);
        }
        this.a = new a(r0, this.c);
    }

    public final boolean a() {
        if (this.a != null) {
            boolean z;
            if (this.a.e.getVisibility() == 0) {
                z = true;
            } else {
                Object obj = null;
            }
            if (z) {
                return true;
            }
        }
        return false;
    }

    public void setNumTextAnimate(int i) {
        if (i > 0) {
            if (this.d <= 0) {
                setNumText(i);
                this.d = i;
                return;
            }
            boolean z;
            this.e = i;
            if (this.e > this.d) {
                z = true;
            } else {
                Object obj = null;
            }
            if (z) {
                this.b = true;
                Animation loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.download_entrance_diminish_to_normal);
                loadAnimation.setAnimationListener(new a(this));
                Animation loadAnimation2 = AnimationUtils.loadAnimation(getContext(), R.anim.download_entrance_diminish_enlarge);
                loadAnimation2.setAnimationListener(new b(this, loadAnimation));
                loadAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.download_entrance_diminish_little);
                loadAnimation.setAnimationListener(new c(this, loadAnimation2));
                loadAnimation2 = AnimationUtils.loadAnimation(getContext(), R.anim.download_entrance_enlarge);
                loadAnimation2.setAnimationListener(new d(this, loadAnimation));
                this.a.b.startAnimation(loadAnimation2);
                return;
            }
            setNumText(i);
        }
    }

    public void setNumText(int i) {
        a aVar = this.a;
        if (i > 0) {
            CharSequence valueOf = String.valueOf(i);
            int length = valueOf.length();
            if (length == 1) {
                aVar.b.setVisibility(0);
                aVar.b.setText(valueOf);
                aVar.c.setVisibility(XZBDevice.Wait);
                aVar.d.setVisibility(XZBDevice.Wait);
            } else if (length == 2) {
                aVar.b.setVisibility(XZBDevice.Wait);
                aVar.c.setVisibility(0);
                aVar.c.setText(valueOf);
                aVar.d.setVisibility(XZBDevice.Wait);
            } else if (length >= 3) {
                aVar.b.setVisibility(XZBDevice.Wait);
                aVar.c.setVisibility(XZBDevice.Wait);
                aVar.d.setVisibility(0);
            }
        }
    }

    public void setEntranceIconResource(int i) {
        this.a.a.setImageResource(i);
    }

    public void setEntranceNumberBackground(int i) {
        if (this.c != EntranceStyle.STYLE_WHITE_IN_BLUE) {
            this.a.b.setBackgroundResource(i);
        }
    }
}
