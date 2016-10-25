package com.xunlei.tdlive;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.xunlei.tdlive.RankActivity.b;
import com.xunlei.tdlive.a.s;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.base.h;
import com.xunlei.tdlive.base.i;
import com.xunlei.tdlive.control.RankTabIndicator;
import com.xunlei.tdlive.user.f;
import com.xunlei.tdlive.util.q;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class RankActivity extends BaseActivity {

    static class a extends h {
        private Fragment a;

        public a(Context context, Bundle bundle) {
            super(context, 16973837);
            setCanceledOnTouchOutside(false);
            this.a = b.a(this, bundle);
        }

        protected void onCreate(Bundle bundle) {
            super.onCreate(bundle);
            View onCreateView = this.a.onCreateView(getLayoutInflater(), null, bundle);
            this.a.onViewCreated(onCreateView, bundle);
            setContentView(onCreateView);
            LayoutParams attributes = getWindow().getAttributes();
            attributes.x = 0;
            attributes.y = 0;
            attributes.height = -1;
            attributes.width = -1;
            getWindow().setAttributes(attributes);
        }
    }

    public static class b extends i implements OnPageChangeListener, OnClickListener {
        private h k;
        private a l;
        private ViewPager m;
        private RankTabIndicator n;
        private RankTabIndicator o;
        private RankTabIndicator p;
        private View q;
        private TextView r;
        private TextView s;
        private TextView t;
        private TextView u;
        private ImageView v;
        private View w;
        private int x;
        private String y;

        public static Fragment a(h hVar, Bundle bundle) {
            Fragment bVar = new com.xunlei.tdlive.RankActivity.b();
            bVar.setArguments(bundle);
            bVar.k = hVar;
            return bVar;
        }

        public void setArguments(Bundle bundle) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            super.setArguments(bundle);
        }

        public View getView() {
            return super.getView() == null ? this.q : super.getView();
        }

        public Context getContext() {
            return this.k != null ? this.k.getContext() : getActivity();
        }

        public Resources b(boolean z) {
            return this.k != null ? this.k.getContext().getResources() : super.getResources();
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            View inflate = layoutInflater.inflate(R.layout.xllive_activity_rank, viewGroup, false);
            this.q = inflate;
            return inflate;
        }

        public void onViewCreated(View view, Bundle bundle) {
            try {
                super.onViewCreated(view, bundle);
            } catch (Exception e) {
            }
            this.y = getArguments().getString("userid");
            this.x = getArguments().getInt("rank_type", 0);
            a("\u4eba\u6c14\u699c", 18.0f, ViewCompat.MEASURED_STATE_MASK);
            d(true);
            b(this);
            a(b(false).getDrawable(R.drawable.xllive_ic_back_dark));
            c(true);
            e(17170443);
            a(this);
            this.s = (TextView) b(R.id.rank_sub_title);
            this.t = (TextView) b(R.id.rankEmptyHit);
            this.u = (TextView) b(R.id.sendGift);
            this.r = (TextView) b(R.id.tip);
            this.v = (ImageView) b(R.id.rank_banner_image);
            this.n = (RankTabIndicator) b(R.id.day_rank);
            this.n.setTitle("\u65e5\u699c");
            this.n.setOnClickListener(this);
            this.o = (RankTabIndicator) b(R.id.week_rank);
            this.o.setTitle("\u5468\u699c");
            this.o.setOnClickListener(this);
            this.p = (RankTabIndicator) b(R.id.month_rank);
            this.p.setTitle("\u6708\u699c");
            this.p.setOnClickListener(this);
            this.w = b(R.id.emptyView);
            this.m = (ViewPager) b(R.id.view_pager);
            this.m.setOnPageChangeListener(this);
            ViewPager viewPager = this.m;
            a aVar = new a(this);
            this.l = aVar;
            viewPager.setAdapter(aVar);
            onPageSelected(this.m.getCurrentItem());
            if (this.x != 0) {
                this.r.setText("\u9001\u793c\u548c\u70b9\u8d5e\u53ef\u4ee5\u4e3a\u4e3b\u64ad\u8d21\u732e\u4eba\u6c14\u54e6~");
                if (getArguments().getBoolean("publish", false) || getArguments().getBoolean("replay", false)) {
                    this.t.setText("\u4eca\u65e5\u8fd8\u6ca1\u6709\u4eba\u767b\u699c");
                    this.u.setVisibility(XZBDevice.Wait);
                } else {
                    this.t.setText("\u4eca\u65e5\u8fd8\u6ca1\u6709\u4eba\u767b\u699c\uff0c\u5feb\u53bb\u9001\u793c\u5427\uff01");
                    this.u.setVisibility(0);
                }
                this.u.setOnClickListener(this);
                this.p.setTitle("\u603b\u699c");
            } else {
                b(R.id.title_banner).setVisibility(XZBDevice.Wait);
            }
            String string = getArguments().getString("rank_title");
            if (string != null) {
                a(string, 18.0f, ViewCompat.MEASURED_STATE_MASK);
            }
            CharSequence string2 = getArguments().getString("rank_sub_title");
            if (string2 != null) {
                this.s.setText(string2);
            }
            string = getArguments().getString("rank_banner");
            if (string == null || string.length() <= 0) {
                string = f.a(getContext()).o();
            }
            if (string != null && string.length() > 0) {
                com.xunlei.tdlive.util.a.a(getContext()).a(this.v, string);
            }
        }

        public void onResume() {
            super.onResume();
            s sVar = this.l.a[this.m.getCurrentItem()];
            if (sVar != null) {
                sVar.a(com.umeng.a.d);
            }
        }

        public void onClick(View view) {
            if (view.getId() == R.id.day_rank) {
                this.m.setCurrentItem(0);
            } else if (view.getId() == R.id.week_rank) {
                this.m.setCurrentItem(1);
            } else if (view.getId() == R.id.month_rank) {
                this.m.setCurrentItem(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            } else if (view == this.a) {
                ListView listView = this.l.b[this.m.getCurrentItem()];
                if (listView != null) {
                    listView.smoothScrollToPosition(0);
                }
            } else if (view == this.c) {
                if (this.k != null) {
                    this.k.dismiss();
                } else {
                    getActivity().finish();
                }
            } else if (view == this.u) {
                getContext().sendBroadcast(new Intent("com.xunlei.tdlive.ACTION_SHOW_GIFT_DIALOG"));
                if (this.k != null) {
                    this.k.dismiss();
                } else {
                    getActivity().finish();
                }
            }
        }

        public void onPageScrollStateChanged(int i) {
        }

        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            TextView textView;
            StringBuilder append;
            String str;
            String[] strArr = new String[]{"\u4eca\u65e5", "\u672c\u5468", com.umeng.a.d};
            String[] strArr2 = new String[]{"\u6bcf\u65e5", "\u6bcf\u5468", "\u6bcf\u6708"};
            RankTabIndicator[] rankTabIndicatorArr = new RankTabIndicator[]{this.n, this.o, this.p};
            for (int i2 = 0; i2 < 3; i2++) {
                rankTabIndicatorArr[i2].select(false);
            }
            rankTabIndicatorArr[i].select(true);
            if (this.x == 0) {
                textView = this.t;
                append = new StringBuilder("\u672c\u699c").append(strArr2[i]);
                str = "\u4e00\u6e05\uff0c\u6682\u65f6\u6728\u6709\u4e3b\u64ad\u767b\u699c~";
            } else {
                textView = this.t;
                append = new StringBuilder().append(strArr[i]).append("\u8fd8\u6ca1\u6709\u4eba\u767b\u699c");
                str = this.u.getVisibility() == 0 ? "\uff0c\u5feb\u53bb\u9001\u793c\u5427\uff01" : com.umeng.a.d;
            }
            textView.setText(append.append(str).toString());
            s sVar = this.l.a[i];
            if (sVar != null) {
                this.w.setVisibility(sVar.isEmpty() ? 0 : XZBDevice.Wait);
                sVar.a(com.umeng.a.d);
            }
        }
    }

    public static void a(Context context, boolean z, Intent intent) {
        if (intent == null) {
            intent = new Intent(context, RankActivity.class);
        } else {
            intent.setClass(context, RankActivity.class);
        }
        if (z) {
            new a(context, intent.getExtras()).show();
        } else {
            context.startActivity(intent);
        }
        q.e("ranklist_show").b(new String[0]);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        View frameLayout = new FrameLayout(this);
        frameLayout.setId(16908312);
        setContentView(frameLayout);
        getSupportFragmentManager().beginTransaction().replace(16908312, b.a(null, getIntent().getExtras())).commit();
    }
}
