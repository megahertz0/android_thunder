package com.xunlei.downloadprovider.download.center.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.service.downloads.task.d;

public class DownloadStorageView extends FrameLayout {
    public a a;
    private ProgressBar b;
    private TextView c;
    private TextView d;

    static class a extends AsyncTask<Long, Long, b> {
        a() {
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a();
        }

        private static b a() {
            b bVar = new b();
            try {
                BrothersApplication.a();
                if (com.xunlei.downloadprovider.businessutil.a.b()) {
                    d.a();
                    bVar.a = d.n();
                    bVar.b = com.xunlei.downloadprovider.businessutil.a.c();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bVar;
        }
    }

    static class b {
        public long a;
        public long b;

        b() {
        }
    }

    public DownloadStorageView(Context context) {
        super(context);
        a(context);
    }

    public DownloadStorageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public DownloadStorageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    @TargetApi(21)
    public DownloadStorageView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_download_storage_view, this);
        this.b = (ProgressBar) inflate.findViewById(R.id.progressBar);
        this.c = (TextView) inflate.findViewById(R.id.textViewLeft);
        this.d = (TextView) inflate.findViewById(R.id.textViewRight);
        if (!isInEditMode()) {
            setProgress(0);
        }
    }

    public void setProgress(int i) {
        if (i < 0) {
            i = 0;
        }
        if (this.b != null) {
            this.b.setProgress(i);
        }
    }

    public void setRemainStorageText(CharSequence charSequence) {
        if (this.d != null) {
            this.d.setText(charSequence);
        }
    }

    public void setRemainStorageText(int i) {
        if (this.d != null) {
            this.d.setText(i);
        }
    }

    public void setUsedStorageText(CharSequence charSequence) {
        if (this.c != null) {
            this.c.setText(charSequence);
        }
    }

    public void setUsedStorageText(int i) {
        if (this.c != null) {
            this.c.setText(i);
        }
    }
}
