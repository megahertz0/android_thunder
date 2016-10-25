package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.util.n;
import com.xunlei.downloadprovider.xlui.widget.ZHTextView;
import org.android.spdy.SpdyProtocol;

public class DownloadTaskNameAndIconView extends FrameLayout {
    public ImageView a;
    public ZHTextView b;
    public TextView c;
    public View d;
    public TextView e;
    public TextView f;
    public View g;
    public TextView h;
    private View i;
    private View j;
    private Context k;

    public DownloadTaskNameAndIconView(Context context) {
        super(context);
        a(context);
    }

    public DownloadTaskNameAndIconView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public DownloadTaskNameAndIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        this.k = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_task_detail_title, this);
        this.j = inflate.findViewById(R.id.name_view_when_hide_all_data);
        this.i = inflate.findViewById(R.id.task_icon_title_layout);
        this.a = (ImageView) inflate.findViewById(R.id.iconImageView);
        this.b = (ZHTextView) inflate.findViewById(R.id.titleTextView);
        this.c = (TextView) inflate.findViewById(R.id.tagSize);
        this.f = (TextView) inflate.findViewById(R.id.tagEpisode);
        this.d = inflate.findViewById(R.id.tagPlay);
        this.e = (TextView) inflate.findViewById(R.id.tagPlay);
        this.g = inflate.findViewById(R.id.tagNew);
        this.h = (TextView) inflate.findViewById(R.id.tagDownloadFrom);
    }

    public final void a(a aVar) {
        if (aVar != null) {
            Context context = getContext();
            ZHTextView zHTextView = this.b;
            View view = this.g;
            CharSequence a = com.xunlei.downloadprovider.download.util.a.a(aVar, context);
            boolean b = n.b(aVar);
            if (b) {
                view.setVisibility(0);
            } else {
                view.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            }
            if (b) {
                zHTextView.setTextIndentPadding((float) Math.max(view.getWidth(), g.a(context, 14.0f)));
            } else {
                zHTextView.setTextIndentPadding(0.0f);
            }
            zHTextView.setText(a);
            zHTextView.requestLayout();
            ImageView imageView = this.a;
            if (n.d(aVar)) {
                Drawable a2 = com.xunlei.downloadprovider.model.protocol.a.a().a(aVar);
                if (a2 != null) {
                    imageView.setScaleType(ScaleType.CENTER_CROP);
                    imageView.setImageDrawable(a2);
                } else {
                    imageView.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_apk);
                }
            } else {
                imageView.setImageResource(n.c(aVar));
            }
            TextView textView = this.c;
            TextView textView2 = this.f;
            if (aVar.mFileSize > 0) {
                textView.setText(com.xunlei.downloadprovider.download.util.a.b(aVar.mFileSize));
            } else {
                textView.setText(R.string.download_item_task_unknown_filesize);
            }
            if (TextUtils.isEmpty(aVar.a)) {
                textView2.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            } else {
                textView2.setVisibility(0);
                textView2.setText(aVar.a);
            }
            this.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public final void a(boolean z) {
        if (z) {
            this.i.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.j.setVisibility(0);
            return;
        }
        this.i.setVisibility(0);
        this.j.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
    }
}
