package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.member.b.c;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.member.payment.external.h;
import com.xunlei.downloadprovider.member.payment.external.i;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChoicenessVipRenewalRemindItemView extends FrameLayout {
    private c a;
    private a b;
    private Context c;
    private j d;
    private bx e;
    private PullToRefreshListView f;
    private String g;
    private HomeChoicenessFragment h;
    private com.xunlei.downloadprovider.member.b.a i;
    private i j;
    private final OnClickListener k;

    private static class a {
        TextView a;
        TextView b;
        TextView c;
        TextView d;
        ImageView e;
        RelativeLayout f;

        private a() {
        }
    }

    public ChoicenessVipRenewalRemindItemView(Context context, c cVar) {
        super(context);
        this.e = new bx();
        this.j = new l(this);
        this.k = new m(this);
        a(context);
        if (cVar != null) {
            if (this.a == null) {
                this.a = cVar;
            }
            if (this.b == null) {
                this.b = (a) getTag();
            }
            this.b.a.setText(this.a.b());
            this.b.c.setText(this.a.c());
            this.b.d.setText(this.a.d());
            if (this.e == null) {
                this.e = new bx();
            }
            if (this.a != null) {
                int e = this.a.e();
                if (this.e != null) {
                    if (e >= -10 && e <= -8) {
                        this.g = "v_an_shoulei_push_sy_before10";
                    } else if (e >= -3 && e < 0) {
                        this.g = "v_an_shoulei_push_sy_before3";
                    } else if (e == 0) {
                        this.g = "v_an_shoulei_push_sy_justout";
                    } else if (e > 0 && e <= 4) {
                        this.g = "v_an_shoulei_push_sy_aftervip";
                    }
                }
            }
        }
        if (this.b == null) {
            this.b = (a) getTag();
        }
        this.b.d.setOnClickListener(this.k);
        this.b.f.setOnClickListener(this.k);
        setOnClickListener(new j(this));
        LoginHelper.a().a(new k(this));
        h.a().addObserver(this.j);
    }

    public ChoicenessVipRenewalRemindItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = new bx();
        this.j = new l(this);
        this.k = new m(this);
        a(context);
    }

    public ChoicenessVipRenewalRemindItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new bx();
        this.j = new l(this);
        this.k = new m(this);
        a(context);
    }

    public void setListView(PullToRefreshListView pullToRefreshListView) {
        if (this.f == null) {
            this.f = pullToRefreshListView;
        }
    }

    public void setmFragment(HomeChoicenessFragment homeChoicenessFragment) {
        if (this.h == null) {
            this.h = homeChoicenessFragment;
        }
    }

    public final void a() {
        if (this.f != null) {
            HomeChoicenessFragment homeChoicenessFragment = this.h;
            homeChoicenessFragment.d = false;
            if (homeChoicenessFragment.a != null) {
                homeChoicenessFragment.a.d = System.currentTimeMillis();
            }
            ((ListView) this.f.getRefreshableView()).removeHeaderView(this);
        }
    }

    private void a(Context context) {
        if (this.c == null) {
            this.c = context;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.choiceness_renewal_item, this, true);
        if (this.b == null) {
            this.b = new a();
        }
        this.b.a = (TextView) inflate.findViewById(R.id.item_main_title);
        this.b.b = (TextView) inflate.findViewById(R.id.on_the_top_mask);
        this.b.c = (TextView) inflate.findViewById(R.id.item_sub_title);
        this.b.d = (TextView) inflate.findViewById(R.id.item_btn_renew);
        this.b.e = (ImageView) inflate.findViewById(R.id.item_delete_icon);
        this.b.f = (RelativeLayout) inflate.findViewById(R.id.rl_btn_delete);
        setTag(this.b);
    }

    static /* synthetic */ void d(ChoicenessVipRenewalRemindItemView choicenessVipRenewalRemindItemView) {
        if (choicenessVipRenewalRemindItemView.d == null) {
            choicenessVipRenewalRemindItemView.d = new j(choicenessVipRenewalRemindItemView.c, "vip_renew_homePage");
        }
        String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        if (choicenessVipRenewalRemindItemView.e != null) {
            choicenessVipRenewalRemindItemView.d.a(new StringBuilder("dateAndUser").append(choicenessVipRenewalRemindItemView.e.d).toString(), format + choicenessVipRenewalRemindItemView.e.d);
        }
    }
}
