package com.xunlei.downloadprovider.discovery;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;

public class DiscoveryEmptyView extends FrameLayout {
    public Button a;
    private TextView b;

    static interface a {
        void a();
    }

    public DiscoveryEmptyView(Context context) {
        super(context);
        a(context);
    }

    public DiscoveryEmptyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        inflate(context, R.layout.discovery_empty_view, this);
        this.b = (TextView) findViewById(R.id.cloud_list_empty_title);
        this.a = (Button) findViewById(R.id.refreshBtn);
    }

    public void setOnRefreshListener(a aVar) {
        this.a.setOnClickListener(new a(this, aVar));
    }

    public void setResult(String str) {
        if (this.b != null) {
            this.b.setText(str);
        }
    }
}
