package com.xunlei.downloadprovider.homepage.recommend;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.xunlei.downloadprovider.frame.BasePageFragment;
import com.xunlei.downloadprovider.frame.af;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.j;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter.RefreshType;
import com.xunlei.downloadprovider.homepage.recommend.d.a;
import com.xunlei.downloadprovider.homepage.recommend.feed.aa;
import com.xunlei.downloadprovider.homepage.recommend.feed.ao;
import com.xunlei.downloadprovider.homepage.recommend.feed.ap;
import com.xunlei.downloadprovider.homepage.recommend.feed.o;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.Collection;
import org.android.spdy.SpdyProtocol;

public class SummaryMoviesListFragment extends BasePageFragment implements af {
    private e a;
    private boolean b;

    public SummaryMoviesListFragment() {
        this.a = null;
    }

    public void onDestroy() {
        super.onDestroy();
        FragmentActivity activity = getActivity();
        if (a.a == null) {
            a.a = new a(activity);
        }
        a.a.b.clear();
        o.a().b();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        o a = o.a();
        if (a.b != null) {
            a.f = a.b.getLong("ts", 0);
            a.g = a.b.getLong("refresh_t1", 0);
            a.h = a.b.getLong("refresh_t2", 0);
            a.i = a.b.getLong("nextpage_t1", 0);
            a.j = a.b.getLong("nextpage_t2", 0);
            a.k = a.i;
            a.l = a.j;
            Collection a2 = ao.a(a.b.getString("newest_feed_video_item_list", BuildConfig.VERSION_NAME));
            a.d.clear();
            a.e.clear();
            if (a2 != null) {
                a.d.addAll(a2);
                a.e.addAll(a2);
            }
        }
    }

    public void onUserVisible(boolean z) {
        super.onUserVisible(z);
        j.a();
        if (this.a != null) {
            e eVar = this.a;
            boolean z2 = this.b;
            if (eVar.b != null) {
                eVar.b.a(z2);
            }
            if (!(eVar.a == null || eVar.a.getRefreshableView() == null)) {
                ListView listView = (ListView) eVar.a.getRefreshableView();
                for (int i = 0; i < listView.getChildCount(); i++) {
                    View childAt = listView.getChildAt(i);
                    if (childAt instanceof ap) {
                        ap apVar = (ap) childAt;
                        if (apVar.a != null) {
                            int b;
                            if (apVar.a != null && apVar.a.e) {
                                apVar.p = apVar.a.h;
                                aa.a();
                                b = aa.b(String.valueOf(apVar.a.a));
                                if (apVar.p == 0) {
                                    apVar.n.getClickNiceTextView().setText(b > apVar.p ? com.xunlei.downloadprovider.homepage.choiceness.a.a(b) : BuildConfig.VERSION_NAME);
                                } else if (b > apVar.p) {
                                    apVar.n.getClickNiceTextView().setText(com.xunlei.downloadprovider.homepage.choiceness.a.a(b));
                                } else {
                                    apVar.n.getClickNiceTextView().setText(com.xunlei.downloadprovider.homepage.choiceness.a.a(apVar.p));
                                }
                                apVar.n.getClickNiceTextView().setSelected(true);
                                apVar.n.getClickNiceImageView().setSelected(true);
                            }
                            if (apVar.a != null) {
                                b = apVar.a.w;
                                if (b > 0) {
                                    apVar.n.getCommentNumTextView().setVisibility(0);
                                    apVar.n.getCommentNumTextView().setText(com.xunlei.downloadprovider.homepage.choiceness.a.a(apVar.a.w));
                                    aa.a();
                                    aa.b(String.valueOf(apVar.a.a), b);
                                } else if (b == 0) {
                                    apVar.n.getCommentNumTextView().setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                                    aa.a();
                                    aa.b(String.valueOf(apVar.a.a), b);
                                }
                            }
                            if (apVar.a != null) {
                                long j = apVar.a.u;
                                apVar.m.setVisibility(0);
                                apVar.m.setText(String.valueOf(j));
                                aa.a();
                                aa.a(String.valueOf(apVar.a.r), j);
                            }
                        }
                    }
                }
            }
            if (!eVar.b.isEmpty() && eVar.d) {
                if (eVar.e) {
                    VideoFeedReporter.d();
                }
                if (z) {
                    q.a();
                }
            }
            eVar.f = true;
            this.b = false;
        }
    }

    public void onResume() {
        super.onResume();
        if (this.a != null) {
            this.a.c.a();
        }
    }

    public void onPageOff() {
        super.onPageOff();
        q.a().a("feed_player");
    }

    public void onPageSelected() {
        super.onPageSelected();
        if (this.a != null) {
            this.a.c.a();
        }
    }

    public void onUserInvisible(boolean z) {
        super.onUserInvisible(z);
        if (this.a != null) {
            e eVar = this.a;
            if (eVar.b != null) {
                eVar.b.a();
            }
            eVar.f = false;
        }
    }

    public void onMainTabClick(boolean z) {
        super.onMainTabClick(z);
        this.b = z;
        if (z) {
            q.a().a("feed_player");
        } else if (this.a != null) {
            this.a.setRefreshType(RefreshType.single_click_bottom_rec);
            this.a.a();
            ChoicenessReporter.a(ChoicenessReporter.RefreshType.single_click_bottom_rec);
        }
    }

    public boolean onBackPressed() {
        VideoFeedReporter.c();
        return super.onBackPressed();
    }

    public final void a() {
        if (this.a != null) {
            this.a.setRefreshType(RefreshType.single_click_top_tab);
            this.a.a();
        }
    }

    protected void onFullScreenChange(boolean z) {
        super.onFullScreenChange(z);
        if (this.a != null) {
            e eVar = this.a;
            if (eVar.b != null) {
                eVar.b.d = System.currentTimeMillis();
            }
        }
    }

    protected View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.a = new e(viewGroup.getContext(), this);
        return this.a;
    }
}
