package com.xunlei.common.stat.a;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xunlei.common.base.XLLog;
import com.xunlei.common.stat.XLStatPack;
import com.xunlei.common.stat.XLStatUtil;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import java.util.ArrayList;
import java.util.List;

// compiled from: XLStatDBManager.java
public final class c {
    private b a;
    private SQLiteDatabase b;
    private boolean c;

    public c(Context context) {
        this.c = false;
        this.a = new b(context);
        this.b = this.a.getWritableDatabase();
        this.c = true;
    }

    public final void a(List<XLStatPack> list) {
        if (this.c) {
            this.b.beginTransaction();
            try {
                for (XLStatPack xLStatPack : list) {
                    this.b.execSQL("INSERT INTO xl_acc_stat_list VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new Object[]{xLStatPack.mReqUrl, Integer.valueOf(xLStatPack.mErrorCode), Double.valueOf(xLStatPack.mRespTime), Integer.valueOf(xLStatPack.mRetryNum), xLStatPack.mSvrIp, xLStatPack.mSvrDomain, Integer.valueOf(xLStatPack.mCommandID), Integer.valueOf(XLStatUtil.getInstance().getBusinessType()), xLStatPack.mReportDate, Integer.valueOf(xLStatPack.mUserId)});
                }
                this.b.setTransactionSuccessful();
                this.b.endTransaction();
            } catch (Exception e) {
                XLLog.v("XLStatDBManager", new StringBuilder("save report list to database error = ").append(e.getMessage()).toString());
                this.b.endTransaction();
            }
        }
    }

    public final void a() {
        if (this.c) {
            this.b.execSQL(new StringBuilder("DELETE FROM xl_acc_stat_list WHERE bt=").append(String.valueOf(XLStatUtil.getInstance().getBusinessType())).toString());
        }
    }

    public final List<XLStatPack> b() {
        Object arrayList = new ArrayList();
        if (this.c) {
            Cursor d = super.d();
            while (d.moveToNext()) {
                XLStatPack xLStatPack = new XLStatPack();
                xLStatPack.mReqUrl = d.getString(d.getColumnIndex(SHubBatchQueryKeys.url));
                xLStatPack.mErrorCode = d.getInt(d.getColumnIndex(XiaomiOAuthConstants.EXTRA_ERROR_CODE_2));
                xLStatPack.mRespTime = d.getDouble(d.getColumnIndex("respt"));
                xLStatPack.mRetryNum = d.getInt(d.getColumnIndex("retry"));
                xLStatPack.mSvrIp = d.getString(d.getColumnIndex("ip"));
                xLStatPack.mSvrDomain = d.getString(d.getColumnIndex("domain"));
                xLStatPack.mCommandID = d.getInt(d.getColumnIndex("cmd"));
                xLStatPack.mReportDate = d.getString(d.getColumnIndex("date"));
                xLStatPack.mUserId = d.getInt(d.getColumnIndex(com.xunlei.download.proguard.c.f));
                arrayList.add(xLStatPack);
            }
            d.close();
        }
        return arrayList;
    }

    public final void c() {
        if (this.c) {
            this.b.close();
            this.c = false;
        }
    }

    private Cursor d() {
        return this.b.rawQuery(new StringBuilder("SELECT * FROM xl_acc_stat_list WHERE bt=").append(String.valueOf(XLStatUtil.getInstance().getBusinessType())).toString(), null);
    }
}
