package com.xunlei.downloadprovider.player;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.util.SparseArray;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.homepage.recommend.WifiPopWindowReporter;
import com.xunlei.downloadprovider.homepage.recommend.WifiPopWindowReporter.PageFrom;
import com.xunlei.downloadprovider.homepage.recommend.feed.y;
import com.xunlei.downloadprovider.homepage.recommend.feed.z;
import com.xunlei.downloadprovider.player.u.a;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.xllib.a.b;
import java.util.ArrayList;
import java.util.List;

// compiled from: MediaPlayerManager.java
public class q {
    private static q c;
    public SparseArray<ab> a;
    public SparseArray<b> b;
    private ab d;
    private boolean e;
    private BroadcastReceiver f;

    private q() {
        this.a = new SparseArray();
        this.b = new SparseArray();
        this.f = new r(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        BrothersApplication.a().registerReceiver(this.f, intentFilter);
    }

    public static q a() {
        if (c == null) {
            synchronized (q.class) {
                if (c == null) {
                    c = new q();
                }
            }
        }
        return c;
    }

    public final ab a(Context context, b bVar, int i) {
        return a(context, bVar, (ab) this.a.get(i));
    }

    public final ab a(Context context, b bVar, String str) {
        ab a = a(context, bVar, b(str));
        a.b = str;
        return a;
    }

    private ab a(Context context, b bVar, ab abVar) {
        if (abVar == null) {
            abVar = new ab(context);
            this.a.put(abVar.a, abVar);
        } else {
            b(abVar);
            abVar.d = context;
        }
        new StringBuilder("apply success --player=").append(abVar);
        this.b.put(abVar.a, bVar);
        bVar.a(abVar);
        this.d = abVar;
        return abVar;
    }

    private void b(ab abVar) {
        new StringBuilder("removePlayerAcceptor--player=").append(abVar);
        int i = abVar.a;
        abVar.o();
        b bVar = (b) this.b.get(i);
        if (bVar != null) {
            this.b.remove(i);
            bVar.b(abVar);
        }
    }

    public final boolean b() {
        return this.d == null ? false : this.d.r();
    }

    public final void a(ab abVar) {
        new StringBuilder("releaseMediaPlayer--mediaPlayer=").append(abVar);
        if (abVar != null) {
            if (this.d == abVar) {
                this.d = null;
            }
            abVar.b();
            this.a.remove(abVar.a);
            b(abVar);
        }
    }

    public final ab b(String str) {
        if (this.d != null && this.d.b.equals(str)) {
            return this.d;
        }
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ab abVar = (ab) this.a.valueAt(i);
            if (abVar.b.equals(str)) {
                return abVar;
            }
        }
        return null;
    }

    public final boolean a(String str, Context context, a aVar) {
        if (this.e) {
            return true;
        }
        if (context instanceof ShortMovieDetailActivity) {
            WifiPopWindowReporter.a(PageFrom.VIDEO_DEITAIL, b.d(BrothersApplication.a), str);
        } else if (context instanceof MainTabActivity) {
            WifiPopWindowReporter.a(PageFrom.FEED_FLOW, b.d(BrothersApplication.a), str);
        }
        s sVar = new s(this, aVar, context, str);
        CharSequence string = context.getString(2131231398);
        CharSequence string2 = context.getString(2131231397);
        v vVar = new v(sVar);
        CharSequence string3 = context.getString(2131231396);
        w wVar = new w(sVar);
        x xVar = new x(sVar);
        if (!(y.a != null && y.a.isShowing() && context == y.a.getOwnerActivity())) {
            if (y.a == null || y.a.getOwnerActivity() != context) {
                y.a = new XLAlarmDialog(context);
                if (context instanceof Activity) {
                    y.a.setOwnerActivity((Activity) context);
                }
            }
            y.a.setCancelable(false);
            y.a.setCanceledOnTouchOutside(false);
            y.a.setContent(string);
            y.a.setCancelButtonText(string2);
            y.a.setOnClickCancelButtonListener(vVar);
            y.a.setConfirmButtonText(string3);
            y.a.setOnClickConfirmButtonListener(wVar);
            y.a.setOnCancelListener(xVar);
            y.a.setCancelable(true);
            y.a.setOnShowListener(new z(context));
            if (!y.a.isShowing()) {
                y.a.show();
            }
        }
        return false;
    }

    public final void a(String str) {
        List<ab> arrayList = new ArrayList();
        int size = this.a.size();
        for (int i = 0; i < size; i++) {
            ab abVar = (ab) this.a.valueAt(i);
            if (abVar.b.equals(str)) {
                arrayList.add(abVar);
            }
        }
        for (ab abVar2 : arrayList) {
            a(abVar2);
        }
    }
}
