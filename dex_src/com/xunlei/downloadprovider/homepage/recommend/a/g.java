package com.xunlei.downloadprovider.homepage.recommend.a;

import android.content.ContentValues;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.frame.user.a.a;
import com.xunlei.downloadprovider.model.protocol.e.a$c;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.ArrayList;
import java.util.Iterator;

// compiled from: ShortTimeVideoListAdapter.java
final class g implements Runnable {
    final /* synthetic */ boolean a;
    final /* synthetic */ ArrayList b;
    final /* synthetic */ a c;

    g(a aVar, boolean z, ArrayList arrayList) {
        this.c = aVar;
        this.a = z;
        this.b = arrayList;
    }

    public final void run() {
        SQLiteException e;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        if (this.a) {
            a.d(this.c);
        }
        a a = a.a(BrothersApplication.a);
        ArrayList arrayList = this.b;
        if (arrayList != null && arrayList.size() != 0) {
            SQLiteDatabase writableDatabase;
            try {
                writableDatabase = a.getWritableDatabase();
                try {
                    writableDatabase.beginTransaction();
                    ContentValues contentValues = new ContentValues();
                    contentValues.clear();
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        a$c com_xunlei_downloadprovider_model_protocol_e_a_c = (a$c) it.next();
                        contentValues.put("movieid", com_xunlei_downloadprovider_model_protocol_e_a_c.a);
                        contentValues.put(SHubBatchQueryKeys.gcid, com_xunlei_downloadprovider_model_protocol_e_a_c.b);
                        contentValues.put("duration", com_xunlei_downloadprovider_model_protocol_e_a_c.f);
                        contentValues.put("poster", com_xunlei_downloadprovider_model_protocol_e_a_c.e);
                        contentValues.put("thumbup_count", Integer.valueOf(com_xunlei_downloadprovider_model_protocol_e_a_c.g));
                        contentValues.put(SetKey.TITLE, com_xunlei_downloadprovider_model_protocol_e_a_c.c);
                        contentValues.put(SHubBatchQueryKeys.url, com_xunlei_downloadprovider_model_protocol_e_a_c.d);
                        contentValues.put("video_type", Integer.valueOf(com_xunlei_downloadprovider_model_protocol_e_a_c.h));
                        contentValues.put("icon_url", com_xunlei_downloadprovider_model_protocol_e_a_c.j);
                        contentValues.put("categoryTitle", com_xunlei_downloadprovider_model_protocol_e_a_c.k);
                        contentValues.put("playCount", Integer.valueOf(com_xunlei_downloadprovider_model_protocol_e_a_c.l));
                        contentValues.put("commentNum", Integer.valueOf(com_xunlei_downloadprovider_model_protocol_e_a_c.m));
                        contentValues.put("durationSec", Integer.valueOf(com_xunlei_downloadprovider_model_protocol_e_a_c.n));
                        contentValues.put("playUrl", com_xunlei_downloadprovider_model_protocol_e_a_c.i);
                        contentValues.put("poster_width", Integer.valueOf(com_xunlei_downloadprovider_model_protocol_e_a_c.o));
                        contentValues.put("poster_height", Integer.valueOf(com_xunlei_downloadprovider_model_protocol_e_a_c.p));
                        if (com_xunlei_downloadprovider_model_protocol_e_a_c.q) {
                            contentValues.put("have_favorite", Integer.valueOf(1));
                        } else {
                            contentValues.put("have_favorite", Integer.valueOf(0));
                        }
                        contentValues.put("uplineTime", Long.valueOf(com_xunlei_downloadprovider_model_protocol_e_a_c.s));
                        contentValues.put("stick", Integer.valueOf(com_xunlei_downloadprovider_model_protocol_e_a_c.t));
                        contentValues.put("channel_sub_title", com_xunlei_downloadprovider_model_protocol_e_a_c.x);
                        contentValues.put("VComment", com_xunlei_downloadprovider_model_protocol_e_a_c.w);
                        writableDatabase.insert("short_time_video_list", null, contentValues);
                    }
                    writableDatabase.setTransactionSuccessful();
                    if (writableDatabase != null && writableDatabase.isOpen()) {
                        writableDatabase.endTransaction();
                        writableDatabase.close();
                    }
                } catch (SQLiteConstraintException e2) {
                    sQLiteDatabase = writableDatabase;
                } catch (SQLiteException e3) {
                    e = e3;
                }
            } catch (SQLiteConstraintException e4) {
                if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
                    sQLiteDatabase.endTransaction();
                    sQLiteDatabase.close();
                }
            } catch (SQLiteException e5) {
                SQLiteException sQLiteException = e5;
                writableDatabase = null;
                e = sQLiteException;
                try {
                    e.printStackTrace();
                    if (writableDatabase != null && writableDatabase.isOpen()) {
                        writableDatabase.endTransaction();
                        writableDatabase.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    writableDatabase.endTransaction();
                    writableDatabase.close();
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                writableDatabase = null;
                th = th4;
                if (writableDatabase != null && writableDatabase.isOpen()) {
                    writableDatabase.endTransaction();
                    writableDatabase.close();
                }
                throw th;
            }
        }
    }
}
