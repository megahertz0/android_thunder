package com.xunlei.downloadprovider.player;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class MediaPlayerCompletionView extends FrameLayout implements t {
    ab a;
    private ImageView b;
    private ImageView c;
    private y d;
    private OnClickListener e;

    public MediaPlayerCompletionView(Context context) {
        super(context);
        a(context);
    }

    public MediaPlayerCompletionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public MediaPlayerCompletionView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(2130968868, this, true);
        inflate.findViewById(2131756525).setOnClickListener(new c(this));
        this.b = (ImageView) inflate.findViewById(2131756526);
        this.b.setOnClickListener(new d(this));
        this.c = (ImageView) inflate.findViewById(2131756527);
        this.c.setOnClickListener(new e(this));
        setVisibility(XZBDevice.Wait);
    }

    public final void a(MediaPlayerState mediaPlayerState, MediaPlayerState mediaPlayerState2) {
        switch (AnonymousClass_1.a[mediaPlayerState2.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                setVisibility(0);
            default:
                setVisibility(XZBDevice.Wait);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        return true;
    }

    public void setPrevPlayBtnVisiable(boolean z) {
        if (z) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(XZBDevice.Wait);
        }
    }

    public void setNextPlayBtnVisiable(boolean z) {
        if (z) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(XZBDevice.Wait);
        }
    }

    public void setOnControllerClickListener(y yVar) {
        this.d = yVar;
    }

    public void setReplayClickListener(OnClickListener onClickListener) {
        this.e = onClickListener;
    }
}
