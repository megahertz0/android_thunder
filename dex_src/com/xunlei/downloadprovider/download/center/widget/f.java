package com.xunlei.downloadprovider.download.center.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.xlui.widget.KeyLinearLayout;
import org.android.spdy.TnetStatusCode;

// compiled from: DownloadCenterMenuPopWindow.java
public final class f extends q {
    public TextView a;
    public TextView b;
    public TextView c;
    public TextView d;
    public TextView e;
    private Context i;

    public f(Context context) {
        super(context);
        this.i = context;
        KeyLinearLayout keyLinearLayout = (KeyLinearLayout) LayoutInflater.from(context).inflate(R.layout.download_list_batch_pw, null);
        setContentView(keyLinearLayout);
        setWidth(g.a(context, 126.0f));
        setHeight(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL);
        this.a = (TextView) keyLinearLayout.findViewById(R.id.batch_start);
        this.b = (TextView) keyLinearLayout.findViewById(R.id.batch_pause);
        this.c = (TextView) keyLinearLayout.findViewById(R.id.batch_switch_create);
        this.d = (TextView) keyLinearLayout.findViewById(R.id.multi_select_operation);
        this.e = (TextView) keyLinearLayout.findViewById(R.id.multi_select_operation_disable);
    }
}
