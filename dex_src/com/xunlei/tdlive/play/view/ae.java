package com.xunlei.tdlive.play.view;

import android.app.Activity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.util.y;

// compiled from: ShareWindowHelper.java
public class ae extends b implements OnClickListener {
    private int e;

    // compiled from: ShareWindowHelper.java
    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;

        public a(String str, String str2, String str3, String str4, String str5, String str6) {
            this.a = str;
            this.b = str2;
            this.e = str3;
            this.f = str4;
            this.c = str5;
            this.d = str6;
        }
    }

    public ae(Activity activity) {
        super(activity);
    }

    protected View a(LayoutInflater layoutInflater) {
        View inflate = layoutInflater.inflate(R.layout.xllive_window_share, null);
        inflate.findViewById(R.id.xllive_share_close).setOnClickListener(this);
        inflate.findViewById(R.id.xllive_wx_share).setOnClickListener(this);
        inflate.findViewById(R.id.xllive_tl_share).setOnClickListener(this);
        inflate.findViewById(R.id.xllive_wb_share).setOnClickListener(this);
        inflate.findViewById(R.id.xllive_qz_share).setOnClickListener(this);
        inflate.findViewById(R.id.xllive_qq_share).setOnClickListener(this);
        return inflate;
    }

    public void onClick(View view) {
        Activity activity = (Activity) this.b.get();
        if (activity == null) {
            a();
            return;
        }
        String str = ((a) e()).a;
        String str2 = ((a) e()).b;
        String str3 = ((a) e()).f;
        String str4 = ((a) e()).e;
        String str5 = ((a) e()).d;
        String str6 = ((a) e()).c;
        if (!TextUtils.isEmpty(str)) {
            af afVar = new af(this, str5, activity, str6);
            int id = view.getId();
            if (id == R.id.xllive_wx_share) {
                this.e = 1;
                y.a(activity, SHARE_MEDIA.WEIXIN, str2, str, str4, str3, afVar);
            } else if (id == R.id.xllive_tl_share) {
                this.e = 0;
                y.a(activity, SHARE_MEDIA.WEIXIN_CIRCLE, str2, str, str4, str3, afVar);
            } else if (id == R.id.xllive_wb_share) {
                this.e = 4;
                y.a(activity, SHARE_MEDIA.SINA, str2, str, str4, str3, afVar);
            } else if (id == R.id.xllive_qz_share) {
                this.e = 2;
                y.a(activity, SHARE_MEDIA.QZONE, str2, str, str4, str3, afVar);
            } else if (id == R.id.xllive_qq_share) {
                this.e = 3;
                y.a(activity, SHARE_MEDIA.QQ, str2, str, str4, str3, afVar);
            }
            a();
        }
    }
}
