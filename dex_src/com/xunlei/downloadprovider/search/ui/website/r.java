package com.xunlei.downloadprovider.search.ui.website;

import android.content.Context;
import android.os.Handler;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.member.login.a.a;
import com.xunlei.downloadprovider.model.b;
import com.xunlei.downloadprovider.model.o;
import com.xunlei.downloadprovider.model.p;
import com.xunlei.downloadprovider.search.bean.c;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.b.d;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

// compiled from: SearchWebsiteHelper.java
public final class r {
    public static r b;
    Handler a;
    boolean c;
    private Context d;
    private final int e;
    private final String f;

    public r(Context context, Handler handler) {
        this.e = 4;
        this.f = "hot_website_json_key";
        this.c = false;
        this.a = handler;
        this.d = context;
    }

    private static boolean a(Calendar calendar, Calendar calendar2) {
        return calendar.get(SimpleLog.LOG_LEVEL_ERROR) == calendar2.get(SimpleLog.LOG_LEVEL_ERROR) && calendar.get(SimpleLog.LOG_LEVEL_DEBUG) == calendar2.get(SimpleLog.LOG_LEVEL_DEBUG) && calendar.get(1) == calendar2.get(1);
    }

    static /* synthetic */ List a(r rVar) {
        List arrayList = new ArrayList();
        List<b> b = a.a().d.b();
        if (!d.a(b)) {
            for (b bVar : b) {
                int indexOf = b.indexOf(bVar);
                if (indexOf == 3 || indexOf == b.size() - 1) {
                    arrayList.add(new g(rVar.d, bVar));
                } else {
                    arrayList.add(new e(rVar.d, bVar));
                }
            }
        }
        return arrayList;
    }

    static /* synthetic */ w a(r rVar, int i) {
        switch (i) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                return new c(rVar.d);
            case SimpleLog.LOG_LEVEL_DEBUG:
                return new o(rVar.d);
            case SimpleLog.LOG_LEVEL_ERROR:
                return new j(rVar.d, rVar.a);
            default:
                return null;
        }
    }

    static /* synthetic */ List a(r rVar, String str) {
        List arrayList = new ArrayList();
        List<c> a = c.a(str);
        for (c cVar : a) {
            if (a.indexOf(cVar) == 3) {
                arrayList.add(new l(cVar, rVar.d));
            } else {
                arrayList.add(new m(cVar, rVar.d));
            }
        }
        return arrayList;
    }

    static /* synthetic */ List b(r rVar) {
        List arrayList = new ArrayList();
        BrothersApplication.a();
        List a = p.a().a("500");
        if (!d.a(a)) {
            int size = a.size();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM\u6708dd\u65e5");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy\u5e74MM\u6708dd\u65e5");
            Calendar gregorianCalendar = new GregorianCalendar();
            Calendar gregorianCalendar2 = new GregorianCalendar();
            gregorianCalendar2.set(SimpleLog.LOG_LEVEL_ERROR, gregorianCalendar.get(SimpleLog.LOG_LEVEL_ERROR) - 1);
            Object obj = BuildConfig.VERSION_NAME;
            int i = 0;
            while (i < size) {
                Object obj2;
                Date date = new Date(((o) a.get(i)).d);
                o oVar = (o) a.get(i);
                String format = simpleDateFormat2.format(date);
                String format2 = simpleDateFormat.format(date);
                if (format.equals(obj)) {
                    arrayList.add(new i(rVar.d, oVar));
                    obj2 = obj;
                } else {
                    Calendar instance = Calendar.getInstance();
                    instance.setTime(date);
                    if (a(gregorianCalendar, instance)) {
                        format2 = rVar.d.getString(R.string.search_history_today) + " " + format2;
                    } else if (a(gregorianCalendar2, instance)) {
                        format2 = rVar.d.getString(R.string.search_history_yesterday) + " " + format2;
                    }
                    arrayList.add(new v(rVar.d, format2));
                    arrayList.add(new i(rVar.d, oVar));
                    String str = format;
                }
                i++;
                obj = obj2;
            }
        }
        if (arrayList.size() != 0) {
            w wVar = (w) arrayList.get(arrayList.size() - 1);
            if (wVar instanceof i) {
                h hVar = new h(rVar.d, ((i) wVar).a);
                arrayList.remove(wVar);
                arrayList.add(hVar);
            }
        }
        return arrayList;
    }
}
