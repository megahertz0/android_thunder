package com.xunlei.downloadprovider.ad.recommend.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.d;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.ad.common.a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.download.util.l;
import com.xunlei.downloadprovider.web.sniff.widget.SimpleCHNTextView;

public class RecommendAdItemView extends LinearLayout {
    private SimpleCHNTextView a;
    private RatingBar b;
    private TextView c;
    private TextView d;
    private ImageView e;
    private View f;

    public RecommendAdItemView(Context context) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        a(context);
    }

    public RecommendAdItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        a(context);
    }

    public RecommendAdItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(R.layout.layout_task_list_recommend_list_item, this, true);
        this.a = (SimpleCHNTextView) findViewById(R.id.name_tv);
        this.c = (TextView) findViewById(R.id.desc_tv);
        this.d = (TextView) findViewById(R.id.action_name_tv);
        this.e = (ImageView) findViewById(R.id.icon_iv);
        this.b = (RatingBar) findViewById(R.id.score_rb);
        this.f = findViewById(R.id.gray_line);
    }

    public void setData(a aVar) {
        CharSequence string;
        d.a().a(aVar.c(), this.e, l.a().a);
        this.a.setText(aVar.a());
        this.c.setText(aVar.b());
        TextView textView = this.d;
        if (aVar.e()) {
            string = BrothersApplication.a().getString(R.string.task_list_recommend_use_app_ad_action_name);
        } else {
            string = BrothersApplication.a().getString(R.string.task_list_recommend_use_web_ad_action_name);
        }
        textView.setText(string);
        float g = aVar.g();
        RatingBar ratingBar = this.b;
        if (g <= 0.0f) {
            g = 4.5f;
        }
        ratingBar.setRating(g);
    }

    public void setDivideLineVisibility(int i) {
        this.f.setVisibility(i);
    }
}
