package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.content.Context;
import android.widget.ListView;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.a;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import com.xunlei.downloadprovider.player.q;

// compiled from: FeedVideoAdapter.java
public final class aj extends a<ao> {
    boolean a;
    private Context b;
    private ListView c;
    private com.xunlei.downloadprovider.homepage.a g;

    public aj(Context context, ListView listView, com.xunlei.downloadprovider.homepage.a aVar) {
        super(context);
        this.b = context;
        this.c = listView;
        this.g = aVar;
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.view.View getView(int r12, android.view.View r13, android.view.ViewGroup r14) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.homepage.recommend.feed.aj.getView(int, android.view.View, android.view.ViewGroup):android.view.View");
        /*
        this = this;
        r10 = 1;
        r9 = 8;
        r8 = 0;
        super.getView(r12, r13, r14);
        if (r13 != 0) goto L_0x0361;
    L_0x0009:
        r7 = new com.xunlei.downloadprovider.homepage.recommend.feed.ap;
        r0 = r11.b;
        r1 = r11.g;
        r7.<init>(r0, r1);
        r0 = new android.widget.AbsListView$LayoutParams;
        r1 = -1;
        r2 = -2;
        r0.<init>(r1, r2);
        r7.setLayoutParams(r0);
    L_0x001c:
        r0 = r11.getItem(r12);
        r0 = (com.xunlei.downloadprovider.homepage.recommend.feed.ao) r0;
        r6 = r7;
        r6 = (com.xunlei.downloadprovider.homepage.recommend.feed.ap) r6;
        r6.setFeedVideoAdapter(r11);
        r1 = java.lang.Integer.valueOf(r12);
        r6.setTag(r1);
        r6.s = r12;
        r1 = r6.g;
        r1.setFeedVideoItemModel(r0);
        r1 = new java.lang.StringBuilder;
        r2 = "setFeedVideoModel--model=";
        r1.<init>(r2);
        r1 = r1.append(r0);
        r2 = "|player=";
        r1 = r1.append(r2);
        r2 = r6.h;
        r1 = r1.append(r2);
        r2 = "|this=";
        r1 = r1.append(r2);
        r2 = java.lang.System.identityHashCode(r6);
        r2 = java.lang.Integer.toHexString(r2);
        r1.append(r2);
        r1 = r6.g;
        r1.setIsFeedType(r10);
        r1 = r6.a;
        if (r1 == r0) goto L_0x0094;
    L_0x006a:
        r6.a = r0;
        r0 = com.nostra13.universalimageloader.core.d.a();
        r1 = r6.c;
        r0.a(r1);
        r0 = com.nostra13.universalimageloader.core.d.a();
        r1 = r6.o;
        r0.a(r1);
        r0 = r6.h;
        if (r0 == 0) goto L_0x008c;
    L_0x0082:
        r0 = r6.h;
        r0 = r0.e;
        r0.a();
        r0 = 0;
        r6.h = r0;
    L_0x008c:
        r0 = r6.b;
        r0.removeAllViews();
        r6.c();
    L_0x0094:
        r0 = com.xunlei.downloadprovider.homepage.recommend.c.c.a();
        r1 = r6.a;
        r1 = r1.a;
        r0 = r0.a(r1);
        if (r0 != 0) goto L_0x00b3;
    L_0x00a2:
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r0 = r6.a;
        r0 = r0.a;
        r0 = java.lang.String.valueOf(r0);
        r0 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.a(r0);
        if (r0 == 0) goto L_0x00ca;
    L_0x00b3:
        r0 = r6.a;
        r0.e = r10;
        r0 = r6.a;
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r1 = r6.a;
        r1 = r1.a;
        r1 = java.lang.String.valueOf(r1);
        r1 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.b(r1);
        r0.h = r1;
    L_0x00ca:
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r0 = r6.a;
        r0 = r0.a;
        r0 = java.lang.String.valueOf(r0);
        r0 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.d(r0);
        if (r0 == 0) goto L_0x00ee;
    L_0x00db:
        r0 = r6.a;
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r1 = r6.a;
        r1 = r1.a;
        r1 = java.lang.String.valueOf(r1);
        r1 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.c(r1);
        r0.w = r1;
    L_0x00ee:
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r0 = r6.a;
        r0 = r0.r;
        r0 = java.lang.String.valueOf(r0);
        r0 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.f(r0);
        if (r0 == 0) goto L_0x0112;
    L_0x00ff:
        r0 = r6.a;
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r1 = r6.a;
        r2 = r1.r;
        r1 = java.lang.String.valueOf(r2);
        r2 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.e(r1);
        r0.u = r2;
    L_0x0112:
        r0 = r6.a;
        if (r0 != 0) goto L_0x0145;
    L_0x0116:
        r0 = r6.d;
        r1 = "";
        r0.setText(r1);
        r0 = r6.e;
        r1 = "";
        r0.setText(r1);
        r0 = r6.f;
        r1 = "";
        r0.setText(r1);
        r0 = r6.i;
        r0.setVisibility(r9);
        r0 = r6.g;
        r0.setVisibility(r9);
    L_0x0138:
        if (r12 != 0) goto L_0x02f5;
    L_0x013a:
        r6.setIsFirstElement(r10);
    L_0x013d:
        r0 = r6.a;
        if (r0 != 0) goto L_0x02fa;
    L_0x0141:
        r6.a();
    L_0x0144:
        return r7;
    L_0x0145:
        r0 = r6.n;
        r0 = r0.getCommentNumTextView();
        r1 = "";
        r0.setText(r1);
        r0 = r6.n;
        r0 = r0.getSubjectNameTextView();
        r1 = r6.a;
        r1 = r1.n;
        r0.setText(r1);
        r0 = r6.d;
        r1 = r6.a;
        r1 = r1.b;
        r0.setText(r1);
        r0 = r6.h;
        if (r0 != 0) goto L_0x026d;
    L_0x016b:
        r0 = r6.a;
        r0 = r0.i;
        if (r0 != 0) goto L_0x024a;
    L_0x0171:
        r0 = r6.f;
        r1 = "";
        r0.setText(r1);
    L_0x0179:
        r0 = r6.e;
        r1 = r6.a;
        r1 = r1.f;
        r1 = com.xunlei.downloadprovider.player.u.a(r1);
        r0.setText(r1);
    L_0x0186:
        r0 = r6.a;
        r0 = r0.s;
        r1 = r6.a;
        r1 = r1.t;
        r2 = r6.a;
        r2 = r2.u;
        r4 = r6.a;
        r4 = r4.v;
        if (r0 == 0) goto L_0x027f;
    L_0x0198:
        if (r1 == 0) goto L_0x027f;
    L_0x019a:
        r5 = r6.i;
        r5.setVisibility(r8);
        r5 = r6.n;
        r5 = r5.getHotCommentArrowImage();
        r5.setVisibility(r8);
        r5 = r6.j;
        r5.setText(r0);
        r0 = r6.k;
        r0.setText(r1);
        r0 = r6.m;
        r1 = java.lang.String.valueOf(r2);
        r0.setText(r1);
        r0 = r6.l;
        r1 = com.xunlei.downloadprovider.homepage.recommend.feed.ap.getSujectIconDisplayOptions();
        r6.a(r4, r0, r1);
    L_0x01c4:
        r0 = r6.a;
        r0 = r0.w;
        if (r0 != 0) goto L_0x028f;
    L_0x01ca:
        r0 = r6.n;
        r0 = r0.getCommentNumTextView();
        r0.setVisibility(r9);
    L_0x01d3:
        r0 = r6.a;
        r0 = r0.h;
        r6.p = r0;
        r0 = r6.a;
        r0 = r0.e;
        if (r0 != 0) goto L_0x01eb;
    L_0x01df:
        r0 = r6.q;
        r1 = r6.a;
        r1 = r1.a;
        r0 = r0.contains(r1);
        if (r0 == 0) goto L_0x02b7;
    L_0x01eb:
        r0 = r6.n;
        r0 = r0.getClickNiceTextView();
        r0.setSelected(r10);
        r0 = r6.n;
        r0 = r0.getClickNiceImageView();
        r0.setSelected(r10);
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r0 = r6.a;
        r0 = r0.a;
        r0 = java.lang.String.valueOf(r0);
        r0 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.b(r0);
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r1 = r6.a;
        r1 = r1.a;
        r1 = java.lang.String.valueOf(r1);
        r1 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.a(r1);
        if (r1 == 0) goto L_0x02a7;
    L_0x021d:
        r1 = r6.q;
        r2 = r6.a;
        r2 = r2.a;
        r1 = r1.contains(r2);
        if (r1 != 0) goto L_0x02a7;
    L_0x0229:
        r1 = r6.n;
        r1 = r1.getClickNiceTextView();
        r0 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r0);
        r1.setText(r0);
    L_0x0236:
        r0 = new java.lang.StringBuilder;
        r1 = "mFeedVideoItemModel.getMovieId() == ";
        r0.<init>(r1);
        r1 = r6.a;
        r1 = r1.a;
        r0.append(r1);
        r6.b();
        goto L_0x0138;
    L_0x024a:
        r0 = r6.f;
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = r6.a;
        r2 = r2.i;
        r2 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r2);
        r1 = r1.append(r2);
        r2 = "\u6b21\u64ad\u653e";
        r1 = r1.append(r2);
        r1 = r1.toString();
        r0.setText(r1);
        goto L_0x0179;
    L_0x026d:
        r0 = r6.e;
        r1 = "";
        r0.setText(r1);
        r0 = r6.f;
        r1 = "";
        r0.setText(r1);
        goto L_0x0186;
    L_0x027f:
        r0 = r6.i;
        r0.setVisibility(r9);
        r0 = r6.n;
        r0 = r0.getHotCommentArrowImage();
        r0.setVisibility(r9);
        goto L_0x01c4;
    L_0x028f:
        r1 = r6.n;
        r1 = r1.getCommentNumTextView();
        r1.setVisibility(r8);
        r1 = r6.n;
        r1 = r1.getCommentNumTextView();
        r0 = java.lang.String.valueOf(r0);
        r1.setText(r0);
        goto L_0x01d3;
    L_0x02a7:
        r0 = r6.n;
        r0 = r0.getClickNiceTextView();
        r1 = r6.p;
        r1 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r1);
        r0.setText(r1);
        goto L_0x0236;
    L_0x02b7:
        r0 = r6.p;
        if (r0 != 0) goto L_0x02d2;
    L_0x02bb:
        r0 = r6.n;
        r0 = r0.getClickNiceTextView();
        r1 = "";
        r0.setText(r1);
        r0 = r6.n;
        r0 = r0.getClickNiceImageView();
        r0.setSelected(r8);
        goto L_0x0236;
    L_0x02d2:
        r0 = r6.n;
        r0 = r0.getClickNiceTextView();
        r1 = r6.p;
        r1 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r1);
        r0.setText(r1);
        r0 = r6.n;
        r0 = r0.getClickNiceTextView();
        r0.setSelected(r8);
        r0 = r6.n;
        r0 = r0.getClickNiceImageView();
        r0.setSelected(r8);
        goto L_0x0236;
    L_0x02f5:
        r6.setIsFirstElement(r8);
        goto L_0x013d;
    L_0x02fa:
        r0 = r6.a;
        r0 = r0.l;
        r1 = com.xunlei.downloadprovider.homepage.recommend.feed.ap.getSujectIconDisplayOptions();
        if (r0 != 0) goto L_0x0352;
    L_0x0304:
        r0 = r6.n;
        r0 = r0.getSubjectIconImageView();
        r1 = r6.getResources();
        r2 = 2130838200; // 0x7f0202b8 float:1.7281376E38 double:1.0527739515E-314;
        r1 = r1.getDrawable(r2);
        r0.setImageDrawable(r1);
    L_0x0318:
        r0 = r6.a;
        r0 = r0.d;
        r1 = r6.a;
        r1 = r1.j;
        r2 = r6.a;
        r2 = r2.k;
        r3 = r6.c;
        r4 = com.xunlei.downloadprovider.app.BrothersApplication.a;
        r4 = com.xunlei.xllib.a.d.a(r4);
        r5 = r6.r;
        r0 = com.xunlei.downloadprovider.util.a.a(r0, r1, r2, r3, r4, r5);
        r1 = r6.c;
        r2 = r6.a;
        r2 = r2.j;
        r3 = r6.a;
        r3 = r3.k;
        com.xunlei.downloadprovider.homepage.choiceness.a.a(r0, r1, r2, r3);
        r0 = r6.a;
        r0 = r0.A;
        r1 = r6.n;
        r1 = r1.getImgVthumb();
        if (r1 == 0) goto L_0x0144;
    L_0x034b:
        if (r0 <= 0) goto L_0x035c;
    L_0x034d:
        r1.setVisibility(r8);
        goto L_0x0144;
    L_0x0352:
        r2 = r6.n;
        r2 = r2.getSubjectIconImageView();
        r6.a(r0, r2, r1);
        goto L_0x0318;
    L_0x035c:
        r1.setVisibility(r9);
        goto L_0x0144;
    L_0x0361:
        r7 = r13;
        goto L_0x001c;
        */
    }

    public final void a(boolean z) {
        super.a(z);
        this.a = false;
        if (!z) {
            b(false);
        }
    }

    public final void a() {
        super.a();
        if (!this.a) {
            q.a().a("feed_player");
        }
        VideoFeedReporter.c();
        VideoFeedReporter.b();
    }

    protected final void b() {
        b(true);
    }

    protected final void a(int i, boolean z) {
        super.a(i, z);
        ao aoVar = (ao) getItem(i);
        if (aoVar != null) {
            VideoFeedReporter.a(z, aoVar);
        }
    }

    private void b(boolean z) {
        int firstVisiblePosition = this.c.getFirstVisiblePosition() - this.c.getHeaderViewsCount();
        int lastVisiblePosition = this.c.getLastVisiblePosition() - this.c.getHeaderViewsCount();
        int count = getCount();
        int i = firstVisiblePosition;
        while (i <= lastVisiblePosition) {
            if (i >= 0 && i < count) {
                VideoFeedReporter.a(z, (ao) getItem(i));
            }
            i++;
        }
    }
}
