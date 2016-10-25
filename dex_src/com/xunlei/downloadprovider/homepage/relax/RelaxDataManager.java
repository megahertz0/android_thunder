package com.xunlei.downloadprovider.homepage.relax;

import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType;
import com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.RelaxDataType;
import com.xunlei.downloadprovider.model.protocol.b.d;
import com.xunlei.downloadprovider.util.b;
import com.xunlei.tdlive.im.ChatMessage;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;

public class RelaxDataManager {
    private static final String b;
    private static RelaxDataManager c;
    private static String f;
    private static String g;
    public List<d> a;
    private HandlerThread d;
    private Handler e;

    public enum GuestureType {
        TOP,
        BOTTOM;

        static {
            TOP = new com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType("TOP", 0);
            BOTTOM = new com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType("BOTTOM", 1);
            a = new com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.GuestureType[]{TOP, BOTTOM};
        }
    }

    public enum RelaxDataType {
        RES,
        FAVOR;

        static {
            RES = new com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.RelaxDataType("RES", 0);
            FAVOR = new com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.RelaxDataType("FAVOR", 1);
            a = new com.xunlei.downloadprovider.homepage.relax.RelaxDataManager.RelaxDataType[]{RES, FAVOR};
        }
    }

    private static class a extends b<RelaxDataManager> {
        public final /* synthetic */ void a(Object obj, Message message) {
            RelaxDataManager relaxDataManager = (RelaxDataManager) obj;
            switch (message.what) {
                case ChatMessage.FLAG_SYS_NOTIFY:
                    com.xunlei.downloadprovider.homepage.relax.b.a aVar = (com.xunlei.downloadprovider.homepage.relax.b.a) message.obj;
                    if (aVar.a.equals(RelaxDataType.FAVOR)) {
                        List a = com.xunlei.downloadprovider.homepage.relax.a.a.a().a(com.xunlei.downloadprovider.homepage.relax.e.a.a(aVar.c), SimpleLog.LOG_LEVEL_DEBUG, aVar.e, aVar.g);
                        if (a.isEmpty()) {
                            aVar.a(1, d.a(aVar.c), aVar.a, aVar.b, null);
                            return;
                        }
                        aVar.a(0, d.a(aVar.c), aVar.a, aVar.b, com.xunlei.downloadprovider.homepage.relax.e.a.a(a, aVar.a, aVar.b, aVar.g));
                        a.clear();
                        return;
                    }
                    long j;
                    int i;
                    int i2;
                    List a2;
                    List list = null;
                    if (aVar.b.equals(GuestureType.BOTTOM)) {
                        int i3 = aVar.c;
                        long j2 = aVar.e;
                        int i4 = aVar.g;
                        List a3 = relaxDataManager.a;
                        if (a3 == null || a3.isEmpty()) {
                            list = null;
                        } else {
                            int size = a3.size();
                            j = ((d) a3.get(0)).a;
                            if (j2 <= ((d) a3.get(a3.size() - 1)).a || j2 > j) {
                                list = null;
                            } else {
                                List arrayList = new ArrayList();
                                i = 0;
                                i2 = 0;
                                while (i2 < size && i < i4) {
                                    d dVar = (d) a3.get(i2);
                                    if (dVar.a < j2) {
                                        arrayList.add(dVar);
                                        i3 = i + 1;
                                    } else {
                                        i3 = i;
                                    }
                                    i2++;
                                    i = i3;
                                }
                                list = arrayList;
                            }
                        }
                    }
                    if (list == null || list.isEmpty()) {
                        GuestureType guestureType = aVar.b;
                        i = aVar.c;
                        long j3 = aVar.e;
                        if (aVar.b == GuestureType.TOP && aVar.e == 0) {
                            j3 = RelaxDataManager.a(d.a(aVar.c));
                            b;
                        }
                        a2 = com.xunlei.downloadprovider.homepage.relax.e.a.a(com.xunlei.downloadprovider.homepage.relax.a.a.a().a(com.xunlei.downloadprovider.homepage.relax.e.a.a(i), guestureType == GuestureType.TOP ? 1 : SimpleLog.LOG_LEVEL_DEBUG, j3, aVar.g));
                    } else {
                        a2 = list;
                    }
                    if (a2 == null || a2.isEmpty()) {
                        aVar.j = new com.xunlei.downloadprovider.homepage.relax.b.b(aVar, aVar, aVar.i);
                        if (aVar.e < 1) {
                            aVar.h = 1;
                            i2 = 1;
                        } else {
                            if (aVar.b.equals(GuestureType.BOTTOM)) {
                                j = aVar.f - 1;
                            } else {
                                j = aVar.f + 1;
                            }
                            aVar.h = j;
                            i2 = 0;
                        }
                        if (!aVar.b.equals(GuestureType.BOTTOM) || aVar.h > 0) {
                            com.xunlei.downloadprovider.model.protocol.b.a.a(aVar.j, d.a(aVar.c), aVar.h, i2, Long.valueOf(aVar.h));
                            return;
                        } else {
                            aVar.a(1, d.a(aVar.c), aVar.a, aVar.b, null);
                            return;
                        }
                    }
                    aVar.a(0, d.a(aVar.c), aVar.a, aVar.b, com.xunlei.downloadprovider.homepage.relax.e.a.a(a2, aVar.a, aVar.b, aVar.g));
                default:
                    break;
            }
        }

        public a(RelaxDataManager relaxDataManager, Looper looper) {
            super(relaxDataManager, looper);
        }
    }

    static {
        b = RelaxDataManager.class.getSimpleName();
        f = "xl_relax";
        g = "last_";
    }

    private RelaxDataManager() {
        this.a = null;
        this.d = new HandlerThread(b);
        this.d.start();
        this.e = new a(this, this.d.getLooper());
    }

    public static synchronized RelaxDataManager a() {
        RelaxDataManager relaxDataManager;
        synchronized (RelaxDataManager.class) {
            if (c == null) {
                c = new RelaxDataManager();
            }
            relaxDataManager = c;
        }
        return relaxDataManager;
    }

    public final void a(RelaxDataType relaxDataType, GuestureType guestureType, com.xunlei.downloadprovider.homepage.relax.b.a.a aVar, long j, long j2) {
        this.e.obtainMessage(ChatMessage.FLAG_SYS_NOTIFY, new com.xunlei.downloadprovider.homepage.relax.b.a(relaxDataType, guestureType, aVar, j, j2, this.d.getLooper())).sendToTarget();
    }

    public static void a(String str, long j) {
        Editor edit = BrothersApplication.a().getSharedPreferences(f, 0).edit();
        edit.putLong(str, j);
        edit.commit();
    }

    public static long a(String str) {
        return BrothersApplication.a().getSharedPreferences(f, 0).getLong(str, 0);
    }
}
