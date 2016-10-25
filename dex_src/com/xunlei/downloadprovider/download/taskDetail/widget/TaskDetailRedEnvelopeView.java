package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;

public class TaskDetailRedEnvelopeView extends FrameLayout {
    private a a;

    private static class a {
        public View a;
        public ImageView b;
        public TextView c;
        public TextView d;
        public TextView e;

        private a() {
        }
    }

    public TaskDetailRedEnvelopeView(Context context) {
        super(context);
        a(context);
    }

    public TaskDetailRedEnvelopeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public TaskDetailRedEnvelopeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_detail_red_envelope, this);
        this.a = new a();
        this.a.a = inflate.findViewById(R.id.red_envelope_container);
        this.a.b = (ImageView) inflate.findViewById(R.id.red_envelope_iv);
        this.a.c = (TextView) inflate.findViewById(R.id.red_envelope_title_tv);
        this.a.d = (TextView) inflate.findViewById(R.id.red_envelope_info_tv);
        this.a.e = (TextView) inflate.findViewById(R.id.red_envelope_open);
        this.a.c.setText(R.string.red_envelope_default_title);
        this.a.d.setText(R.string.red_envelope_default_info);
        this.a.a.setOnClickListener(new d(this));
        this.a.e.setOnClickListener(new e(this));
    }
}
