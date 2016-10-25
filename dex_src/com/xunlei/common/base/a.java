package com.xunlei.common.base;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.open.SocialConstants;
import com.xunlei.common.stat.XLStatPack;
import com.xunlei.common.stat.XLStatUtil;
import com.xunlei.common.stat.a.b;
import java.util.ArrayList;
import java.util.List;

// compiled from: XLResPackageResolver.java
public class a {
    private b a;
    private SQLiteDatabase b;
    private boolean c;

    private static int a(Context context, String str, String str2) {
        try {
            Class[] classes = Class.forName(context.getPackageName() + ".R").getClasses();
            Class cls = null;
            for (int i = 0; i < classes.length; i++) {
                if (classes[i].getName().split("\\$")[1].equals(str)) {
                    cls = classes[i];
                    break;
                }
            }
            return cls != null ? cls.getField(str2).getInt(cls) : 0;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
            return 0;
        } catch (SecurityException e3) {
            e3.printStackTrace();
            return 0;
        } catch (IllegalAccessException e4) {
            e4.printStackTrace();
            return 0;
        } catch (NoSuchFieldException e5) {
            e5.printStackTrace();
            return 0;
        }
    }

    public a(Context context) {
        this.c = false;
        this.a = new b(context);
        this.b = this.a.getWritableDatabase();
        this.c = true;
    }

    public void a(List<XLStatPack> list) {
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

    public void a() {
        if (this.c) {
            this.b.execSQL(new StringBuilder("DELETE FROM xl_acc_stat_list WHERE bt=").append(String.valueOf(XLStatUtil.getInstance().getBusinessType())).toString());
        }
    }

    public List<XLStatPack> b() {
        Object arrayList = new ArrayList();
        if (this.c) {
            Cursor rawQuery = this.b.rawQuery(new StringBuilder("SELECT * FROM xl_acc_stat_list WHERE bt=").append(String.valueOf(XLStatUtil.getInstance().getBusinessType())).toString(), null);
            while (rawQuery.moveToNext()) {
                XLStatPack xLStatPack = new XLStatPack();
                xLStatPack.mReqUrl = rawQuery.getString(rawQuery.getColumnIndex(SocialConstants.PARAM_URL));
                xLStatPack.mErrorCode = rawQuery.getInt(rawQuery.getColumnIndex("error"));
                xLStatPack.mRespTime = rawQuery.getDouble(rawQuery.getColumnIndex("respt"));
                xLStatPack.mRetryNum = rawQuery.getInt(rawQuery.getColumnIndex("retry"));
                xLStatPack.mSvrIp = rawQuery.getString(rawQuery.getColumnIndex("ip"));
                xLStatPack.mSvrDomain = rawQuery.getString(rawQuery.getColumnIndex(anet.channel.strategy.dispatch.a.DOMAIN));
                xLStatPack.mCommandID = rawQuery.getInt(rawQuery.getColumnIndex(com.taobao.agoo.a.a.b.JSON_CMD));
                xLStatPack.mReportDate = rawQuery.getString(rawQuery.getColumnIndex("date"));
                xLStatPack.mUserId = rawQuery.getInt(rawQuery.getColumnIndex(ParamKey.UID));
                arrayList.add(xLStatPack);
            }
            rawQuery.close();
        }
        return arrayList;
    }

    public void c() {
        if (this.c) {
            this.b.close();
            this.c = false;
        }
    }

    private Cursor d() {
        return this.b.rawQuery(new StringBuilder("SELECT * FROM xl_acc_stat_list WHERE bt=").append(String.valueOf(XLStatUtil.getInstance().getBusinessType())).toString(), null);
    }
}
