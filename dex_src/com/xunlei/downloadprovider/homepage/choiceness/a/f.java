package com.xunlei.downloadprovider.homepage.choiceness.a;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.a;
import com.xunlei.downloadprovider.homepage.choiceness.a.a.c;
import com.xunlei.downloadprovider.homepage.recommend.feed.o;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.List;

// compiled from: ChoicenessDbHelper.java
final class f extends SQLiteOpenHelper {
    public f(Context context) {
        super(context, "choiceness.db", null, 6);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists choiceness (_id integer primary key autoincrement, displayType integer, resType text, resId text, gcid text, sortId integer, resCoverUrl text, subCategory text, subDisplayType text, resTitle text, subjectCount text, playCount integer, likeCount integer, tag text, doubanScore text, jumpUrl text, introduction text, play_url text, duration integer, is_on_the_top integer, s_ab text, params text)");
        sQLiteDatabase.execSQL("create table if not exists subChoiceness (_id integer primary key autoincrement, res_type text, parent_res_type text, res_id text, parent_res_id text, title text ,cover_url text, like_count integer, douban_score text, play_url text, jump_url text)");
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS subChoiceness");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS choiceness");
        onCreate(sQLiteDatabase);
        o a = o.a();
        if (a.b != null) {
            Editor edit = a.b.edit();
            edit.remove("refresh_t1");
            edit.remove("refresh_t2");
            edit.remove("nextpage_t1");
            edit.remove("nextpage_t2");
            edit.remove("ts");
            edit.remove("newest_feed_video_item_list");
            edit.commit();
        }
        m.a().c(0);
        m.a().b(0);
        m.a().a(0);
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new SQLiteException(new StringBuilder("Can't downgrade database from version ").append(i).append(" to ").append(i2).toString());
    }

    static void a(SQLiteDatabase sQLiteDatabase, List<a> list) {
        for (a aVar : list) {
            int i;
            ContentValues contentValues = new ContentValues();
            contentValues.put("resType", aVar.c);
            contentValues.put("displayType", Integer.valueOf(aVar.b));
            contentValues.put("doubanScore", Float.valueOf(aVar.q));
            contentValues.put("duration", Integer.valueOf(aVar.t));
            contentValues.put("play_url", aVar.v);
            contentValues.put("introduction", aVar.s);
            contentValues.put("jumpUrl", aVar.r);
            contentValues.put("playCount", Integer.valueOf(aVar.n));
            contentValues.put("likeCount", Integer.valueOf(aVar.o));
            contentValues.put("tag", aVar.p);
            contentValues.put("resCoverUrl", aVar.f);
            contentValues.put("resId", aVar.d);
            contentValues.put(SHubBatchQueryKeys.gcid, aVar.e);
            contentValues.put("sortId", Long.valueOf(aVar.u));
            contentValues.put("resTitle", aVar.k);
            contentValues.put("subCategory", aVar.i);
            contentValues.put("subDisplayType", aVar.j);
            contentValues.put("subjectCount", Integer.valueOf(aVar.m));
            String str = "is_on_the_top";
            if (aVar.a) {
                i = 1;
            } else {
                i = 0;
            }
            contentValues.put(str, Integer.valueOf(i));
            contentValues.put("s_ab", aVar.w);
            contentValues.put("params", aVar.f());
            sQLiteDatabase.insert("choiceness", null, contentValues);
            if (aVar.m > 0 && aVar.l != null) {
                for (c cVar : aVar.l) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put("parent_res_type", aVar.c);
                    contentValues2.put("parent_res_id", aVar.d);
                    contentValues2.put(SetKey.TITLE, cVar.a);
                    contentValues2.put("cover_url", cVar.c);
                    contentValues2.put("res_type", cVar.d);
                    contentValues2.put("res_id", cVar.b);
                    contentValues2.put("douban_score", Float.valueOf(cVar.f));
                    contentValues2.put("like_count", Integer.valueOf(cVar.e));
                    contentValues2.put("jump_url", cVar.i);
                    contentValues2.put("play_url", cVar.j);
                    sQLiteDatabase.insert("subChoiceness", null, contentValues2);
                }
            }
        }
    }
}
