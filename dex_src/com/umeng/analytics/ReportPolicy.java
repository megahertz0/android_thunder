package com.umeng.analytics;

import android.content.Context;
import com.taobao.accs.common.Constants;
import com.umeng.analytics.ReportPolicy.i;
import u.aly.de;
import u.aly.n;
import u.aly.t;

public class ReportPolicy {
    public static final int BATCH_AT_LAUNCH = 1;
    public static final int BATCH_BY_INTERVAL = 6;
    public static final int DAILY = 4;
    public static final int REALTIME = 0;
    public static final int SMART_POLICY = 8;
    public static final int WIFIONLY = 5;
    static final int a = 2;
    static final int b = 3;

    public static class i {
        public boolean a(boolean z) {
            return true;
        }

        public boolean a() {
            return true;
        }
    }

    public static class a extends i {
        private final long a;
        private u.aly.c b;

        public a(u.aly.c cVar) {
            this.a = 15000;
            this.b = cVar;
        }

        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b.c >= 15000;
        }
    }

    public static class b extends i {
        private n a;
        private u.aly.c b;

        public b(u.aly.c cVar, n nVar) {
            this.b = cVar;
            this.a = nVar;
        }

        public boolean a(boolean z) {
            long j;
            long currentTimeMillis = System.currentTimeMillis();
            switch (this.a.a) {
                case BATCH_AT_LAUNCH:
                    j = Constants.ST_UPLOAD_TIME_INTERVAL;
                    break;
                case a:
                    j = 28800000;
                    break;
                case b:
                    j = a.h;
                    break;
                default:
                    j = 0;
                    break;
            }
            return currentTimeMillis - this.b.c >= j;
        }

        public boolean a() {
            return this.a.a();
        }
    }

    public static class c extends i {
        private long a;
        private long b;

        public c(int i) {
            this.b = 0;
            this.a = (long) i;
            this.b = System.currentTimeMillis();
        }

        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b >= this.a;
        }

        public boolean a() {
            return System.currentTimeMillis() - this.b < this.a;
        }
    }

    public static class d extends i {
        public boolean a(boolean z) {
            return z;
        }
    }

    public static class e extends i {
        private static long a;
        private static long b;
        private long c;
        private u.aly.c d;

        static {
            a = 90000;
            b = 86400000;
        }

        public e(u.aly.c cVar, long j) {
            this.d = cVar;
            a(j);
        }

        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.d.c >= this.c;
        }

        public void a(long j) {
            if (j < a || j > b) {
                this.c = a;
            } else {
                this.c = j;
            }
        }

        public long b() {
            return this.c;
        }

        public static boolean a(int i) {
            return ((long) i) >= a;
        }
    }

    public static class f extends i {
        private final int a;
        private de b;

        public f(de deVar, int i) {
            this.a = i;
            this.b = deVar;
        }

        public boolean a(boolean z) {
            return this.b.a() > this.a;
        }
    }

    public static class g extends i {
        private long a;
        private u.aly.c b;

        public g(u.aly.c cVar) {
            this.a = 86400000;
            this.b = cVar;
        }

        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b.c >= this.a;
        }
    }

    public static class h extends i {
        public boolean a(boolean z) {
            return true;
        }
    }

    public static class j extends i {
        private Context a;

        public j(Context context) {
            this.a = null;
            this.a = context;
        }

        public boolean a(boolean z) {
            return t.f(this.a);
        }
    }

    public static class k extends i {
        private final long a;
        private u.aly.c b;

        public k(u.aly.c cVar) {
            this.a = 10800000;
            this.b = cVar;
        }

        public boolean a(boolean z) {
            return System.currentTimeMillis() - this.b.c >= 10800000;
        }
    }

    public static boolean a(int i) {
        switch (i) {
            case REALTIME:
            case BATCH_AT_LAUNCH:
            case a:
            case b:
            case DAILY:
            case WIFIONLY:
            case BATCH_BY_INTERVAL:
            case SMART_POLICY:
                return true;
            default:
                return false;
        }
    }
}
