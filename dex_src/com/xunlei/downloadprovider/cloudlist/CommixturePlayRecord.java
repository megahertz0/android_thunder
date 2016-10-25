package com.xunlei.downloadprovider.cloudlist;

import android.text.TextUtils;
import com.xunlei.common.yunbo.XLYB_VODINFO;
import com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.RecodeType;
import com.xunlei.downloadprovider.vod.b.b.a;
import com.xunlei.mediaserver.Utility;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;

public final class CommixturePlayRecord {
    public XLYB_VODINFO a;
    public a b;
    public RecodeType c;
    public String d;

    static /* synthetic */ class AnonymousClass_1 {
        public static final /* synthetic */ int[] a;

        static {
            a = new int[RecodeType.values().length];
            try {
                a[RecodeType.vodInfo.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[RecodeType.playRecord.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum LastPlayTime {
        today,
        yesterday,
        threedaysago,
        none;

        static {
            today = new com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.LastPlayTime("today", 0);
            yesterday = new com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.LastPlayTime("yesterday", 1);
            threedaysago = new com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.LastPlayTime("threedaysago", 2);
            none = new com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.LastPlayTime(Utility.NETWORK_NONE, 3);
            a = new com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.LastPlayTime[]{today, yesterday, threedaysago, none};
        }
    }

    public enum RecodeType {
        vodInfo,
        playRecord;

        static {
            vodInfo = new com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.RecodeType("vodInfo", 0);
            playRecord = new com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.RecodeType("playRecord", 1);
            a = new com.xunlei.downloadprovider.cloudlist.CommixturePlayRecord.RecodeType[]{vodInfo, playRecord};
        }
    }

    public static long a(String str) {
        if (TextUtils.isEmpty(str)) {
            return Long.MAX_VALUE;
        }
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).getTime();
        } catch (ParseException e) {
            return Long.MAX_VALUE;
        }
    }

    public final long a() {
        switch (AnonymousClass_1.a[this.c.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return ("0".equals(this.a.playtime) || BuildConfig.VERSION_NAME.equals(this.a.playtime)) ? Long.MAX_VALUE : a(this.a.playtime);
            case SimpleLog.LOG_LEVEL_DEBUG:
                return 0 != this.b.c ? this.b.c : Long.MAX_VALUE;
            default:
                return Long.MAX_VALUE;
        }
    }

    public static LastPlayTime a(long j) {
        Date date = new Date(j);
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance2.set(1, instance.get(1));
        instance2.set(SimpleLog.LOG_LEVEL_DEBUG, instance.get(SimpleLog.LOG_LEVEL_DEBUG));
        instance2.set(SimpleLog.LOG_LEVEL_ERROR, instance.get(SimpleLog.LOG_LEVEL_ERROR));
        instance2.set(SpdyProtocol.PUBKEY_PSEQ_OPEN, 0);
        instance2.set(R.styleable.Toolbar_titleMargins, 0);
        instance2.set(R.styleable.Toolbar_titleMarginStart, 0);
        Calendar instance3 = Calendar.getInstance();
        instance3.set(1, instance.get(1));
        instance3.set(SimpleLog.LOG_LEVEL_DEBUG, instance.get(SimpleLog.LOG_LEVEL_DEBUG));
        instance3.set(SimpleLog.LOG_LEVEL_ERROR, instance.get(SimpleLog.LOG_LEVEL_ERROR) - 1);
        instance3.set(SpdyProtocol.PUBKEY_PSEQ_OPEN, 0);
        instance3.set(R.styleable.Toolbar_titleMargins, 0);
        instance3.set(R.styleable.Toolbar_titleMarginStart, 0);
        instance.setTime(date);
        if (instance.after(instance2)) {
            return LastPlayTime.today;
        }
        return (instance.before(instance2) && instance.after(instance3)) ? LastPlayTime.yesterday : LastPlayTime.threedaysago;
    }

    public final int b() {
        switch (AnonymousClass_1.a[this.c.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return this.a.tasktype;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return this.b.d;
            default:
                return -1;
        }
    }

    public final String c() {
        return this.b != null ? this.b.q : null;
    }

    public final String d() {
        String str = BuildConfig.VERSION_NAME;
        switch (AnonymousClass_1.a[this.c.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return this.a.filename;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return this.b.b;
            default:
                return str;
        }
    }

    public final long e() {
        switch (AnonymousClass_1.a[this.c.ordinal()]) {
            case SimpleLog.LOG_LEVEL_TRACE:
                return this.a.filesize;
            case SimpleLog.LOG_LEVEL_DEBUG:
                return this.b.j;
            default:
                return 0;
        }
    }
}
