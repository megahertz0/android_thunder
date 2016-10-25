package com.xunlei.downloadprovider.vod;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import com.xunlei.downloadprovider.vod.VodPlayerView.d;
import com.xunlei.tdlive.R;

// compiled from: VodPlayerView.java
final class at implements OnKeyListener {
    final /* synthetic */ VodPlayerView a;

    at(VodPlayerView vodPlayerView) {
        this.a = vodPlayerView;
    }

    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        d access$100;
        switch (i) {
            case R.styleable.Toolbar_subtitleTextColor:
                access$100 = this.a.mUIParams;
                access$100.h += 6.6666665f;
                if (this.a.mUIParams.h > 100.0f) {
                    this.a.mUIParams.h = 100.0f;
                }
                if (this.a.mEventListener != null) {
                    this.a.mEventListener.onVolumnChanged(this.a.mUIParams.h);
                }
                this.a.setMenuVolProgress((int) this.a.mUIParams.h, R.styleable.AppCompatTheme_buttonStyle);
                return true;
            case R.styleable.AppCompatTheme_actionMenuTextAppearance:
                access$100 = this.a.mUIParams;
                access$100.h -= 6.6666665f;
                if (this.a.mUIParams.h < 0.0f) {
                    this.a.mUIParams.h = 0.0f;
                }
                if (this.a.mEventListener != null) {
                    this.a.mEventListener.onVolumnChanged(this.a.mUIParams.h);
                }
                this.a.setMenuVolProgress((int) this.a.mUIParams.h, R.styleable.AppCompatTheme_buttonStyle);
                return true;
            default:
                return false;
        }
    }
}
