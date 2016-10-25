package com.xunlei.downloadprovider.vod;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import org.android.spdy.SpdyProtocol;

public class VodPlayerFirstLoadingView extends RelativeLayout implements OnClickListener {
    private static long g;
    private static long h;
    private static long i;
    private TextView a;
    private View b;
    private View c;
    private TextView d;
    private TextView e;
    private a f;
    private int j;
    private int k;
    private Runnable l;

    public static interface a {
    }

    static /* synthetic */ int a(VodPlayerFirstLoadingView vodPlayerFirstLoadingView, long j) {
        int i = (int) (((long) vodPlayerFirstLoadingView.k) + j);
        vodPlayerFirstLoadingView.k = i;
        return i;
    }

    static {
        g = 1000;
        h = 15;
        i = 100 / (g / h);
    }

    public VodPlayerFirstLoadingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.j = 0;
        this.k = 0;
        this.l = new al(this);
    }

    public VodPlayerFirstLoadingView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.j = 0;
        this.k = 0;
        this.l = new al(this);
    }

    public VodPlayerFirstLoadingView(Context context) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.j = 0;
        this.k = 0;
        this.l = new al(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (TextView) findViewById(R.id.vod_player_first_loading_center_text);
        this.b = findViewById(R.id.vod_player_first_loading_progress_bar);
        this.c = findViewById(R.id.vod_player_first_loading_btn_back);
        this.c.setOnClickListener(this);
        this.d = (TextView) findViewById(R.id.vod_player_first_loading_title);
        this.e = (TextView) findViewById(R.id.vod_player_first_loading_percent_txt);
    }

    public void setTitleText(CharSequence charSequence) {
        this.d.setText(charSequence);
    }

    @SuppressLint({"DefaultLocale"})
    private void setPercent(int i) {
        int i2;
        int i3 = com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle;
        if (i < 0) {
            i2 = 0;
        } else {
            i2 = i;
        }
        if (i2 <= 100) {
            i3 = i2;
        }
        this.e.setText(String.format("%1$d%%", new Object[]{Integer.valueOf(i3)}));
    }

    public void setLoadingProgress(int i) {
        if (i > this.j) {
            this.j = i;
            removeCallbacks(this.l);
            if (i < 100) {
                post(this.l);
            } else {
                setPercent(i);
            }
        }
    }

    public void setProgressBarVisible(boolean z) {
        if (z) {
            this.b.setVisibility(0);
            this.e.setVisibility(0);
            this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            return;
        }
        this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.a.setVisibility(0);
    }

    public void onClick(View view) {
        view.getId();
    }
}
