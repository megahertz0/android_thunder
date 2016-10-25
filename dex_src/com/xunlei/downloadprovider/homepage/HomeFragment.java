package com.xunlei.downloadprovider.homepage;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.report.DLCenterEntry;
import com.xunlei.downloadprovider.frame.BaseViewPagerFragment;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.frame.MainTabActivity.b;
import com.xunlei.downloadprovider.model.protocol.d.a.a;
import com.xunlei.downloadprovider.search.ui.widget.HomeTitleBar;
import com.xunlei.downloadprovider.search.ui.widget.HomeTopView;
import com.xunlei.downloadprovider.util.v;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class HomeFragment extends BaseViewPagerFragment implements AnimatorListener, a {
    public static int f;
    public i g;
    private HomeTitleBar h;
    private View i;
    private LinearLayout j;
    private RelativeLayout k;
    private HomeTopView l;
    private int m;
    private int n;
    private b o;
    private Handler p;
    private boolean q;
    private boolean r;
    private boolean s;
    private boolean t;
    private float u;
    private float v;
    private int w;

    public HomeFragment() {
        this.m = 0;
        this.n = 0;
        this.g = i.a();
        this.p = new Handler();
        this.q = false;
        this.r = false;
        this.s = false;
        this.t = false;
        this.u = 0.0f;
        this.v = 0.0f;
        this.w = 0;
    }

    static /* synthetic */ boolean a(HomeFragment homeFragment, MotionEvent motionEvent) {
        boolean z = true;
        if (!homeFragment.s) {
            int action = motionEvent.getAction();
            float x = motionEvent.getX();
            float y = motionEvent.getY();
            switch (action) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                    homeFragment.v = y;
                    homeFragment.u = x;
                    break;
                case SimpleLog.LOG_LEVEL_DEBUG:
                    boolean z2;
                    float abs = Math.abs(y - homeFragment.v);
                    float abs2 = Math.abs(x - homeFragment.u);
                    boolean z3 = y > homeFragment.v;
                    homeFragment.v = y;
                    homeFragment.u = x;
                    if (abs2 >= 8.0f || abs <= 8.0f || homeFragment.t || z3) {
                        z2 = false;
                    } else {
                        z2 = true;
                    }
                    homeFragment.r = z2;
                    if (abs2 >= 8.0f || abs <= 8.0f || !homeFragment.t || !z3) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    homeFragment.q = z3;
                    HomeTopView homeTopView;
                    if (homeFragment.r) {
                        float[] fArr = new float[]{0.0f, (float) (-homeFragment.e)};
                        homeTopView = homeFragment.l;
                        if (homeTopView.a != null) {
                            if (homeTopView.c != null) {
                                homeTopView.c.cancel();
                            } else {
                                homeTopView.c = AnimationUtils.loadAnimation(homeTopView.getContext(), R.anim.dowload_view_alpha_show);
                                homeTopView.c.setStartOffset(300);
                            }
                            homeTopView.a.setAlpha(1.0f);
                            homeTopView.a.startAnimation(homeTopView.c);
                        }
                        homeFragment.e();
                        homeFragment.a(homeFragment.j, homeFragment.m, homeFragment.n, 0, homeFragment.w, fArr, false);
                    } else if (homeFragment.q) {
                        float[] fArr2 = new float[]{(float) (-homeFragment.e), 0.0f};
                        homeTopView = homeFragment.l;
                        if (homeTopView.a != null) {
                            if (homeTopView.d != null) {
                                homeTopView.d.cancel();
                            } else {
                                homeTopView.d = AnimationUtils.loadAnimation(homeTopView.getContext(), R.anim.dowload_view_alpha_hide);
                            }
                            homeTopView.a.startAnimation(homeTopView.d);
                        }
                        homeFragment.a(homeFragment.j, homeFragment.n, homeFragment.m, homeFragment.w, 0, fArr2, true);
                    }
                    if (homeFragment.t) {
                        z = false;
                    }
                    homeFragment.t = z;
                    break;
            }
        }
        return false;
    }

    static {
        f = -1;
    }

    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.i = LayoutInflater.from(getActivity()).inflate(R.layout.main_home_fragment, viewGroup, false);
        a(this.i);
        return this.i;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.o = new d(this);
        MainTabActivity mainTabActivity = (MainTabActivity) getActivity();
        mainTabActivity.f.add(this.o);
    }

    protected final void a(View view) {
        super.a(view);
        this.j = (LinearLayout) view.findViewById(R.id.lin_tabHost);
        this.k = (RelativeLayout) view.findViewById(R.id.re_titleBar);
        this.l = (HomeTopView) view.findViewById(R.id.home_top_bar);
        HomeTitleBar homeTitleBar = (HomeTitleBar) view.findViewById(R.id.title_bar);
        homeTitleBar.setDlCenterEntry(DLCenterEntry.home);
        homeTitleBar.setOnTitleClickListener(new e(this));
        this.h = homeTitleBar;
        this.b.setPageMargin(getResources().getDimensionPixelOffset(R.dimen.search_line_space_normal));
        this.b.setPageMarginDrawable(getResouceDrawable(R.drawable.viewpage_space_drawable));
        LayoutParams layoutParams = (LayoutParams) this.j.getLayoutParams();
        layoutParams.topMargin = this.e;
        this.j.setLayoutParams(layoutParams);
    }

    protected final String[] b() {
        return this.g.c;
    }

    protected final Class<?>[] c() {
        return this.g.b;
    }

    public void onResume() {
        super.onResume();
        this.h.a();
        this.l.a();
        if (isAdded()) {
            v.a().a(System.currentTimeMillis(), "recommend");
            ((MainTabActivity) this.mActivity).a("thunder", SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
        if (f != -1) {
            int i = f;
            f = -1;
            this.p.postDelayed(new f(this, i), 100);
        }
    }

    public void onPause() {
        this.h.b();
        HomeTopView homeTopView = this.l;
        if (homeTopView.b != null) {
            homeTopView.b.b();
        }
        super.onPause();
    }

    public final void a(String str, String str2) {
        if (!TextUtils.isEmpty(str2) && this.h != null) {
            this.h.setHint(str2);
        }
    }

    protected final void a(int i, View view) {
        v a = v.a();
        if (!a.b()) {
            return;
        }
        if (a.b(e(i))) {
            view.setVisibility(0);
        } else {
            view.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    protected final void d(int i) {
        v.a().a(System.currentTimeMillis(), e(i));
    }

    public final String e(int i) {
        return (String) this.g.a.get(i);
    }

    public final void a(v vVar) {
        super.a(vVar);
        this.p.post(new g(this));
    }

    public void onAnimationStart(Animator animator) {
        this.s = true;
    }

    public void onAnimationEnd(Animator animator) {
        this.s = false;
    }

    public void onAnimationCancel(Animator animator) {
    }

    public void onAnimationRepeat(Animator animator) {
    }

    private void a(View view, int i, int i2, int i3, int i4, float[] fArr, boolean z) {
        ObjectAnimator ofFloat;
        AnimatorSet animatorSet = new AnimatorSet();
        e();
        if (z) {
            ofFloat = ObjectAnimator.ofFloat(this.j, "translationY", new float[]{(float) (-this.e), 0.0f});
        } else {
            ofFloat = ObjectAnimator.ofFloat(this.j, "translationY", new float[]{0.0f, (float) (-this.e)});
        }
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.k, "translationY", fArr);
        ValueAnimator.ofInt(new int[]{1, 100}).addUpdateListener(new h(this, view, i, i2, i3, i4));
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2, r12});
        animatorSet.setDuration(300);
        animatorSet.start();
        animatorSet.addListener(this);
    }

    private void e() {
        if (this.m == 0) {
            this.m = this.i.getWidth();
        }
        if (this.n == 0) {
            this.n = this.j.getWidth() - ((int) getResources().getDimension(R.dimen.channel_filter_mask_left));
        }
        if (this.w == 0) {
            this.w = (int) getResources().getDimension(R.dimen.frame_thunder_top_6);
        }
    }
}
