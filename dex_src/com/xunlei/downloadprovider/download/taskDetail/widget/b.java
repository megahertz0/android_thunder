package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.d;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.download.taskDetail.a.h;
import com.xunlei.downloadprovider.download.util.l;
import com.xunlei.downloadprovider.web.sniff.widget.SimpleCHNTextView;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Random;
import org.android.spdy.SpdyProtocol;

// compiled from: TaskDetailImageAdView.java
public class b extends a {
    private static final String a;
    private Context b;
    private View c;
    private View d;
    private RelativeLayout e;
    private ImageView f;
    private SimpleCHNTextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private RatingBar k;
    private h l;

    static {
        a = b.class.getSimpleName();
    }

    public b(Context context) {
        super(context);
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.b = context;
        this.c = LayoutInflater.from(this.b).inflate(R.layout.download_detail_image_ad_02, this);
        this.d = this.c.findViewById(R.id.parent_view);
        this.e = (RelativeLayout) this.c.findViewById(R.id.bg_rl);
        this.f = (ImageView) this.c.findViewById(R.id.icon_iv);
        this.g = (SimpleCHNTextView) this.c.findViewById(R.id.title_tv);
        this.h = (TextView) this.c.findViewById(R.id.desc_tv);
        this.k = (RatingBar) this.c.findViewById(R.id.score_rb);
        this.i = (TextView) this.c.findViewById(R.id.action_name_tv);
        this.j = (TextView) this.c.findViewById(R.id.extend_tv);
        setOnClickListener(new c(this));
    }

    public final void a() {
        this.l = null;
        setLayoutVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        this.f.setImageResource(R.drawable.download_ad_background);
        this.g.setText(BuildConfig.VERSION_NAME);
        this.k.setRating(4.5f);
        this.h.setText(BuildConfig.VERSION_NAME);
        this.i.setText(getResources().getString(R.string.task_detail_app_ad_action_name));
    }

    public final void a(h hVar) {
        this.l = hVar;
        if (this.l == null) {
            a();
            return;
        }
        String str;
        CharSequence string;
        int randomBgResource02 = getRandomBgResource02();
        h hVar2 = this.l;
        switch (randomBgResource02) {
            case R.drawable.task_detail_image_ad_bg_02:
                str = c.e;
                break;
            case R.drawable.task_detail_image_ad_bg_03:
                str = c.c;
                break;
            default:
                str = c.e;
                break;
        }
        hVar2.a(str);
        this.e.setBackgroundResource(randomBgResource02);
        this.g.setText(this.l.e());
        this.k.setRating(this.l.i());
        this.h.setText(this.l.c());
        TextView textView = this.i;
        if (this.l.b()) {
            string = BrothersApplication.a().getString(R.string.task_detail_app_ad_action_name);
        } else {
            string = BrothersApplication.a().getString(R.string.task_detail_web_ad_action_name);
        }
        textView.setText(string);
        this.j.setText(hVar.h() + getResources().getString(R.string.task_detail_ad_extend_suffix));
        d.a().a(this.l.g(), this.f, l.a().b);
        setLayoutVisibility(0);
        this.l.a((View) this);
    }

    private void setLayoutVisibility(int i) {
        this.d.setVisibility(i);
    }

    private int getRandomBgResource02() {
        return new Random().nextInt(com.xunlei.xllib.R.styleable.AppCompatTheme_buttonStyle) < 50 ? R.drawable.task_detail_image_ad_bg_02 : R.drawable.task_detail_image_ad_bg_03;
    }
}
