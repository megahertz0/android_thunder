package com.xunlei.downloadprovider.commonview;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovidercommon.R;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;

class DownloadEntranceView$a {
    public ImageView a;
    public TextView b;
    public TextView c;
    public ImageView d;
    public View e;
    public FrameLayout f;
    protected View g;
    protected DownloadEntranceView$EntranceStyle h;

    public DownloadEntranceView$a(View view, DownloadEntranceView$EntranceStyle downloadEntranceView$EntranceStyle) {
        this.h = downloadEntranceView$EntranceStyle;
        this.g = view;
        this.f = (FrameLayout) view.findViewById(R.id.dl_bg_ly);
        this.a = (ImageView) view.findViewById(R.id.dl_entrance_icon);
        this.e = view.findViewById(R.id.dl_entrance_dot);
        this.b = (TextView) view.findViewById(R.id.dl_entrance_num);
        this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.c = (TextView) view.findViewById(R.id.dl_entrance_2_num);
        this.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.d = (ImageView) view.findViewById(R.id.dl_entrance_num_for_3length);
        this.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (!view.isInEditMode()) {
            this.b.setText(BuildConfig.VERSION_NAME);
            this.c.setText(BuildConfig.VERSION_NAME);
        }
    }
}
