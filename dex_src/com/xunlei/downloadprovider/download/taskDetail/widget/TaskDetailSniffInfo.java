package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.util.n;

public class TaskDetailSniffInfo extends FrameLayout {
    public TextView a;
    public TextView b;
    public RelativeLayout c;
    public TextView d;
    public a e;
    public boolean f;
    private View g;
    private ImageView h;
    private RelativeLayout i;

    public void setCurrentTask(a aVar) {
        if (aVar == this.e) {
            this.f = false;
        } else {
            this.f = true;
        }
        this.e = aVar;
    }

    public TaskDetailSniffInfo(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f = true;
        a(context);
    }

    public TaskDetailSniffInfo(Context context) {
        super(context);
        this.f = true;
        a(context);
    }

    public TaskDetailSniffInfo(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = true;
        a(context);
    }

    private void a(Context context) {
        this.g = LayoutInflater.from(context).inflate(R.layout.download_detail_sniff_info, this);
        this.a = (TextView) this.g.findViewById(R.id.task_ref_url);
        this.b = (TextView) this.g.findViewById(R.id.web_site_name);
        this.h = (ImageView) this.g.findViewById(R.id.web_site_icon);
        this.i = (RelativeLayout) this.g.findViewById(R.id.keyword_web_container);
        this.c = (RelativeLayout) this.g.findViewById(R.id.keyword_container);
        this.d = (TextView) this.g.findViewById(R.id.key_word_tv);
    }

    public void setClickWebSiteListener(OnClickListener onClickListener) {
        if (this.i != null) {
            this.i.setOnClickListener(onClickListener);
        }
    }

    public void setClickSearchAgainListener(OnClickListener onClickListener) {
        if (this.d != null) {
            this.d.setOnClickListener(onClickListener);
        }
    }

    public static void a(String str, a aVar) {
        String str2;
        if (n.b(aVar)) {
            str2 = "dl_finish";
        } else {
            str2 = "dl_unfinish";
        }
        int i = 0;
        if (n.c(aVar)) {
            i = 1;
        }
        com.xunlei.downloadprovider.download.report.a.a(str, str2, i);
    }
}
