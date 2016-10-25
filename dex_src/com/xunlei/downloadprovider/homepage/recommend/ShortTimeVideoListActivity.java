package com.xunlei.downloadprovider.homepage.recommend;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.TextPaint;
import android.text.style.UnderlineSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.e;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter;
import com.xunlei.downloadprovider.model.protocol.report.ThunderReporter.g;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

public class ShortTimeVideoListActivity extends BaseActivity implements OnClickListener {
    public ListView a;
    public PullToRefreshListView b;
    public com.xunlei.downloadprovider.homepage.recommend.a.a c;
    com.xunlei.downloadprovider.homepage.recommend.a.a.a d;
    public UnifiedLoadingView e;
    public TextView f;
    public TextView g;
    public TextView h;
    public ImageView i;
    public View j;
    ImageView k;
    TextView l;
    ImageView m;
    private View n;
    private float o;
    private com.xunlei.downloadprovider.homepage.a p;
    private FrameLayout q;
    private com.xunlei.downloadprovider.homepage.a.a r;

    class a implements e {
        a() {
        }

        public final void onPullDownToRefresh(PullToRefreshBase pullToRefreshBase) {
        }

        public final void onPullUpToRefresh(PullToRefreshBase pullToRefreshBase) {
            ShortTimeVideoListActivity.this.c.a(1);
        }
    }

    public ShortTimeVideoListActivity() {
        this.r = new d(this);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968619);
        this.p = new com.xunlei.downloadprovider.homepage.a(this.r);
        this.o = getResources().getDimension(2131362234);
        this.d = new com.xunlei.downloadprovider.homepage.recommend.a.a.a();
        Intent intent = getIntent();
        this.d.a = intent.getIntExtra("video_type", 1);
        this.d.b = intent.getStringExtra(WebBrowserActivity.EXTRA_TITLE);
        this.d.d = intent.getStringExtra("icon_url");
        this.d.c = intent.getStringExtra("module_description");
        this.d.e = intent.getStringExtra("channel_description");
        this.d.f = intent.getStringExtra("from");
        String str = this.d.f;
        g gVar = new g();
        gVar.a = "android_videoChannel";
        gVar.b = "videoChannel_show";
        gVar.c = "videoChannel_show";
        Object obj = -1;
        switch (str.hashCode()) {
            case -1644189905:
                if (str.equals("feed_flow")) {
                    boolean z = true;
                }
                break;
            case 1382975024:
                if (str.equals("hotvideo_home")) {
                    obj = null;
                }
                break;
            case 1672657681:
                if (str.equals("shortvideo_detail")) {
                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                }
                break;
        }
        switch (z) {
            case SpdyAgent.ACCS_TEST_SERVER:
                gVar.a("from", "hotvideo_home", (int) XZBDevice.DOWNLOAD_LIST_FAILED);
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                gVar.a("from", "feed_flow", (int) XZBDevice.DOWNLOAD_LIST_FAILED);
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                gVar.a("from", "shortvideo_detail", (int) XZBDevice.DOWNLOAD_LIST_FAILED);
                break;
        }
        gVar.b("channelid", (long) this.d.a);
        ThunderReporter.a(gVar, true);
        this.n = findViewById(2131755279);
        this.n.setOnClickListener(this);
        this.k = (ImageView) findViewById(2131755281);
        this.k.setOnClickListener(this);
        this.l = (TextView) findViewById(2131755280);
        this.l.setText(this.d.b);
        this.m = (ImageView) findViewById(2131755282);
        this.m.setOnClickListener(this);
        this.b = (PullToRefreshListView) findViewById(2131755277);
        this.b.setBackgroundResource(2131689814);
        this.a = (ListView) this.b.getRefreshableView();
        this.j = findViewById(2131755278);
        this.j.setOnClickListener(this);
        this.j.setVisibility(XZBDevice.Wait);
        this.f = (TextView) findViewById(2131755658);
        this.g = (TextView) findViewById(2131755657);
        this.h = (TextView) findViewById(2131755659);
        this.h.setOnClickListener(this);
        this.i = (ImageView) findViewById(2131755656);
        this.b.setMode(Mode.DISABLED);
        this.b.setOnRefreshListener(new a());
        this.b.setOnScrollListener(new b(this));
        this.c = new com.xunlei.downloadprovider.homepage.recommend.a.a(this, this.d, (ListView) this.b.getRefreshableView(), this.p);
        this.b.setAdapter(this.c);
        this.e = (UnifiedLoadingView) findViewById(2131755208);
        b();
        this.q = (FrameLayout) findViewById(2131755283);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.c != null) {
            this.c.j = System.currentTimeMillis();
        }
    }

    private void b() {
        this.e.setType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.e.setBackgroundColor(Color.parseColor("#ffffff"));
        this.e.c();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        d.b().a(i, i2, intent);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onResume() {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.homepage.recommend.ShortTimeVideoListActivity.onResume():void");
        /*
        this = this;
        r2 = 1;
        r3 = 0;
        super.onResume();
        r0 = r13.b;
        if (r0 == 0) goto L_0x0011;
    L_0x0009:
        r0 = r13.b;
        r0 = r0.getRefreshableView();
        if (r0 != 0) goto L_0x001d;
    L_0x0011:
        r0 = r13.c;
        if (r0 == 0) goto L_0x001c;
    L_0x0015:
        r0 = r13.c;
        r0.h = r3;
        r0.b(r3);
    L_0x001c:
        return;
    L_0x001d:
        r0 = r13.c;
        if (r0 == 0) goto L_0x00bc;
    L_0x0021:
        r6 = r13.c;
        r0 = r6.g;
        r0 = r0.getFirstVisiblePosition();
        r1 = r6.g;
        r1 = r1.getHeaderViewsCount();
        r0 = r0 - r1;
        r1 = r6.g;
        r7 = r1.getLastVisiblePosition();
        r8 = r6.getCount();
        r5 = r0;
    L_0x003b:
        if (r5 >= r7) goto L_0x00bc;
    L_0x003d:
        if (r5 < 0) goto L_0x009a;
    L_0x003f:
        if (r5 >= r8) goto L_0x009a;
    L_0x0041:
        r0 = r6.a;
        r0 = r0.get(r5);
        r0 = r0 instanceof com.xunlei.downloadprovider.homepage.recommend.feed.ao;
        if (r0 == 0) goto L_0x009a;
    L_0x004b:
        r0 = r6.a;
        r0 = r0.get(r5);
        r0 = (com.xunlei.downloadprovider.homepage.recommend.feed.ao) r0;
        r1 = com.xunlei.downloadprovider.app.BrothersApplication.a;
        r9 = com.xunlei.downloadprovider.frame.user.a.a.a(r1);
        r1 = r0.e;
        if (r1 == 0) goto L_0x01c6;
    L_0x005d:
        r1 = r2;
    L_0x005e:
        r4 = 0;
        r4 = r9.getWritableDatabase();	 Catch:{ SQLiteException -> 0x009e, all -> 0x00af }
        r9 = new android.content.ContentValues;	 Catch:{ SQLiteException -> 0x01c2, all -> 0x00af }
        r9.<init>();	 Catch:{ SQLiteException -> 0x01c2, all -> 0x00af }
        r10 = "have_favorite";
        r1 = java.lang.Integer.valueOf(r1);	 Catch:{ SQLiteException -> 0x01c2, all -> 0x00af }
        r9.put(r10, r1);	 Catch:{ SQLiteException -> 0x01c2, all -> 0x00af }
        r1 = "thumbup_count";
        r10 = r0.h;	 Catch:{ SQLiteException -> 0x01c2, all -> 0x00af }
        r10 = java.lang.Integer.valueOf(r10);	 Catch:{ SQLiteException -> 0x01c2, all -> 0x00af }
        r9.put(r1, r10);	 Catch:{ SQLiteException -> 0x01c2, all -> 0x00af }
        r1 = "short_time_video_list";
        r10 = "movieid=?";
        r11 = 1;
        r11 = new java.lang.String[r11];	 Catch:{ SQLiteException -> 0x01c2, all -> 0x00af }
        r12 = 0;
        r0 = r0.a;	 Catch:{ SQLiteException -> 0x01c2, all -> 0x00af }
        r11[r12] = r0;	 Catch:{ SQLiteException -> 0x01c2, all -> 0x00af }
        r4.update(r1, r9, r10, r11);	 Catch:{ SQLiteException -> 0x01c2, all -> 0x00af }
        if (r4 == 0) goto L_0x009a;
    L_0x0091:
        r0 = r4.isOpen();
        if (r0 == 0) goto L_0x009a;
    L_0x0097:
        r4.close();
    L_0x009a:
        r0 = r5 + 1;
        r5 = r0;
        goto L_0x003b;
    L_0x009e:
        r0 = move-exception;
        r1 = r4;
    L_0x00a0:
        r0.printStackTrace();	 Catch:{ all -> 0x01be }
        if (r1 == 0) goto L_0x009a;
    L_0x00a5:
        r0 = r1.isOpen();
        if (r0 == 0) goto L_0x009a;
    L_0x00ab:
        r1.close();
        goto L_0x009a;
    L_0x00af:
        r0 = move-exception;
    L_0x00b0:
        if (r4 == 0) goto L_0x00bb;
    L_0x00b2:
        r1 = r4.isOpen();
        if (r1 == 0) goto L_0x00bb;
    L_0x00b8:
        r4.close();
    L_0x00bb:
        throw r0;
    L_0x00bc:
        r0 = r13.b;
        r0 = r0.getRefreshableView();
        r0 = (android.widget.ListView) r0;
        r4 = r3;
    L_0x00c5:
        r1 = r0.getChildCount();
        if (r4 >= r1) goto L_0x0011;
    L_0x00cb:
        r1 = r0.getChildAt(r4);
        r5 = r1 instanceof com.xunlei.downloadprovider.homepage.recommend.feed.a;
        if (r5 == 0) goto L_0x0175;
    L_0x00d3:
        r1 = (com.xunlei.downloadprovider.homepage.recommend.feed.a) r1;
        r5 = r1.a;
        if (r5 == 0) goto L_0x0175;
    L_0x00d9:
        r5 = r1.a;
        if (r5 == 0) goto L_0x011f;
    L_0x00dd:
        r5 = r1.a;
        r5 = r5.e;
        if (r5 == 0) goto L_0x011f;
    L_0x00e3:
        r5 = r1.a;
        r5 = r5.h;
        r1.p = r5;
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r5 = r1.a;
        r5 = r5.a;
        r5 = java.lang.String.valueOf(r5);
        r5 = com.xunlei.downloadprovider.homepage.recommend.feed.aa.b(r5);
        r6 = r1.p;
        if (r6 != 0) goto L_0x017e;
    L_0x00fc:
        r6 = r1.n;
        r6 = r6.getClickNiceTextView();
        r7 = r1.p;
        if (r5 <= r7) goto L_0x017a;
    L_0x0106:
        r5 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r5);
    L_0x010a:
        r6.setText(r5);
    L_0x010d:
        r5 = r1.n;
        r5 = r5.getClickNiceTextView();
        r5.setSelected(r2);
        r5 = r1.n;
        r5 = r5.getClickNiceImageView();
        r5.setSelected(r2);
    L_0x011f:
        r5 = r1.a;
        if (r5 == 0) goto L_0x0151;
    L_0x0123:
        r5 = r1.a;
        r5 = r5.w;
        if (r5 <= 0) goto L_0x01a2;
    L_0x0129:
        r6 = r1.n;
        r6 = r6.getCommentNumTextView();
        r6.setVisibility(r3);
        r6 = r1.n;
        r6 = r6.getCommentNumTextView();
        r7 = r1.a;
        r7 = r7.w;
        r7 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r7);
        r6.setText(r7);
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r6 = r1.a;
        r6 = r6.a;
        r6 = java.lang.String.valueOf(r6);
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.b(r6, r5);
    L_0x0151:
        r5 = r1.a;
        if (r5 == 0) goto L_0x0175;
    L_0x0155:
        r5 = r1.a;
        r6 = r5.u;
        r5 = r1.m;
        r5.setVisibility(r3);
        r5 = r1.m;
        r8 = java.lang.String.valueOf(r6);
        r5.setText(r8);
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r1 = r1.a;
        r8 = r1.r;
        r1 = java.lang.String.valueOf(r8);
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a(r1, r6);
    L_0x0175:
        r1 = r4 + 1;
        r4 = r1;
        goto L_0x00c5;
    L_0x017a:
        r5 = "";
        goto L_0x010a;
    L_0x017e:
        r6 = r1.p;
        if (r5 <= r6) goto L_0x0191;
    L_0x0182:
        r6 = r1.n;
        r6 = r6.getClickNiceTextView();
        r5 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r5);
        r6.setText(r5);
        goto L_0x010d;
    L_0x0191:
        r5 = r1.n;
        r5 = r5.getClickNiceTextView();
        r6 = r1.p;
        r6 = com.xunlei.downloadprovider.homepage.choiceness.a.a(r6);
        r5.setText(r6);
        goto L_0x010d;
    L_0x01a2:
        if (r5 != 0) goto L_0x0151;
    L_0x01a4:
        r6 = r1.n;
        r6 = r6.getCommentNumTextView();
        r7 = 8;
        r6.setVisibility(r7);
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.a();
        r6 = r1.a;
        r6 = r6.a;
        r6 = java.lang.String.valueOf(r6);
        com.xunlei.downloadprovider.homepage.recommend.feed.aa.b(r6, r5);
        goto L_0x0151;
    L_0x01be:
        r0 = move-exception;
        r4 = r1;
        goto L_0x00b0;
    L_0x01c2:
        r0 = move-exception;
        r1 = r4;
        goto L_0x00a0;
    L_0x01c6:
        r1 = r3;
        goto L_0x005e;
        */
    }

    protected void onPause() {
        if (this.c != null) {
            com.xunlei.downloadprovider.homepage.recommend.a.a aVar = this.c;
            if (!aVar.h) {
                q.a().a("channel_player");
            }
            a.a(aVar.f.f, String.valueOf(aVar.f.a));
            a.b();
        }
        super.onPause();
    }

    protected void onDestroy() {
        super.onDestroy();
        q.a().a("channel_player");
        this.c = null;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case 2131755281:
                finish();
            case 2131755282:
                a.d();
                Dialog dialog = new Dialog(this, 2131427577);
                dialog.requestWindowFeature(1);
                dialog.setContentView(2130968976);
                Window window = dialog.getWindow();
                LayoutParams attributes = window.getAttributes();
                attributes.width = -1;
                attributes.gravity = 80;
                window.setAttributes(attributes);
                window.findViewById(2131755709).setOnClickListener(new c(this, dialog));
                Spannable spannable = (Spannable) ((TextView) window.findViewById(2131756977)).getText();
                spannable.setSpan(new UnderlineSpan() {
                    public void updateDrawState(TextPaint textPaint) {
                        textPaint.setUnderlineText(false);
                    }
                }, 0, spannable.length(), R.styleable.Toolbar_maxButtonHeight);
                dialog.show();
                a.c();
            case 2131755659:
                b();
                com.xunlei.downloadprovider.homepage.recommend.a.a aVar = this.c;
                if (!aVar.d) {
                    aVar.d = true;
                    new com.xunlei.downloadprovider.model.protocol.e.a(aVar.e).a(0, aVar.c, aVar.k, aVar.f.a, 0);
                }
            default:
                break;
        }
    }

    public final void a() {
        this.g.setVisibility(0);
        this.g.setText(R.string.invalid_network);
        this.f.setText(getResources().getString(2131231032));
        this.f.setVisibility(0);
        this.h.setVisibility(0);
        this.i.setImageResource(R.drawable.bg_invalid_network);
        this.j.setVisibility(0);
    }

    public static void a(Context context, int i, String str, String str2, String str3) {
        Intent intent = new Intent();
        intent.setClass(context, ShortTimeVideoListActivity.class);
        intent.putExtra("video_type", i);
        intent.putExtra("icon_url", str);
        intent.putExtra("module_description", null);
        intent.putExtra("channel_description", null);
        intent.putExtra(WebBrowserActivity.EXTRA_TITLE, str2);
        intent.putExtra("from", str3);
        context.startActivity(intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_ALL:
                if (!q.a().b()) {
                    finish();
                }
                return true;
            default:
                return super.onKeyDown(i, keyEvent);
        }
    }
}
