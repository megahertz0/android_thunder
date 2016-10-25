package com.xunlei.downloadprovider.homepage.recommend.feed;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.view.View;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.frame.user.a.a;
import com.xunlei.downloadprovider.homepage.recommend.VideoFeedReporter;
import com.xunlei.downloadprovider.homepage.recommend.c.c;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.downloadprovidershare.d;
import com.xunlei.xllib.a.b;

// compiled from: ChannelFeedVideoItemView.java
final class i implements FeedVideoItemBottomView$a {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    i(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    public final void a() {
    }

    public final void a(View view) {
        if (!(a.c(this.b) == null || a.c(this.b).e)) {
            a a = a.a(BrothersApplication.a);
            String str = a.c(this.b).a;
            SQLiteDatabase sQLiteDatabase = null;
            try {
                sQLiteDatabase = a.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put("have_favorite", Integer.valueOf(1));
                sQLiteDatabase.update("short_time_video_list", contentValues, "movieid=?", new String[]{str});
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.close();
                }
            } catch (SQLiteException e) {
                try {
                    e.printStackTrace();
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                } catch (Throwable th) {
                    if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                        sQLiteDatabase.close();
                    }
                }
            }
        }
        if (b.a(this.a)) {
            a.a(this.b, view, this.a);
            if (a.c(this.b) != null) {
                a.d(this.b);
            }
        } else if (a.c(this.b) != null) {
            c.a().a(a.c(this.b).b, a.c(this.b).c, a.c(this.b).a, a.c(this.b).q);
            new StringBuilder("\u6dfb\u52a0\u70b9\u8d5e\u5230\u6570\u636e\u5e93 title, name, movieId == ").append(a.c(this.b).b).append(",").append(a.c(this.b).c).append(",").append(a.c(this.b).a);
            a.a(this.b, view, this.a);
            a.d(this.b);
        }
    }

    public final void b() {
        a.a(this.b, From.VIDEO_CHANNEL);
        if (a.c(this.b) != null) {
            com.xunlei.downloadprovider.homepage.recommend.a.b(a.c(this.b).a);
        }
    }

    public final void c() {
        if (a.c(this.b) != null) {
            d.b().a((Activity) this.a, a.e(this.b), this.b.u);
            VideoFeedReporter.a(a.c(this.b).a, "foot");
        }
    }
}
