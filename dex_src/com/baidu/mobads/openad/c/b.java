package com.baidu.mobads.openad.c;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.baidu.mobads.AppActivity;
import com.baidu.mobads.command.a;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.baidu.mobads.interfaces.download.activate.IXAppInfo;
import com.baidu.mobads.interfaces.utils.IXAdPackageUtils.ApkInfo;
import com.baidu.mobads.j.j;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import com.umeng.message.entity.UMessage;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

public class b implements Observer {
    private static NotificationManager a;
    private static int b;
    private static HashMap<String, b> g;
    private a c;
    private Context d;
    private com.baidu.mobads.b.b e;
    private String f;
    private Handler h;

    static {
        b = 10091;
        g = new HashMap();
    }

    public a a() {
        return this.c;
    }

    public static synchronized b a(String str) {
        b bVar;
        synchronized (b.class) {
            bVar = (b) g.get(str);
        }
        return bVar;
    }

    public static synchronized b b(String str) {
        b bVar;
        synchronized (b.class) {
            bVar = (b) g.remove(str);
        }
        return bVar;
    }

    public static synchronized void a(String str, b bVar) {
        synchronized (b.class) {
            g.put(str, bVar);
        }
    }

    public static synchronized int c(String str) {
        int i;
        synchronized (b.class) {
            b bVar = (b) g.get(str);
            if (bVar == null || bVar.a() == null) {
                i = b;
                b = i + 1;
            } else {
                i = bVar.a().f;
            }
        }
        return i;
    }

    public b(Context context, a aVar) {
        this.c = null;
        this.e = null;
        this.f = com.umeng.a.d;
        this.h = new Handler(Looper.getMainLooper());
        m.a().f().d("OAdApkDownloaderObserver", "observer created");
        if (a == null) {
            a = (NotificationManager) context.getSystemService(UMessage.DISPLAY_TYPE_NOTIFICATION);
        }
        this.d = context.getApplicationContext();
        this.c = aVar;
        a(this.c.i, this);
    }

    public void update(Observable observable, Object obj) {
        IOAdDownloader iOAdDownloader = (IOAdDownloader) observable;
        this.c.g = iOAdDownloader.getState();
        if (this.c.g == DownloadStatus.DOWNLOADING) {
            if (this.c.d < 0) {
                m.a().f().d("OAdApkDownloaderObserver", "download update---mExtraInfo.contentLength < 0");
                this.c.d = (long) iOAdDownloader.getFileSize();
                this.c.k = iOAdDownloader.getTargetURL();
                this.c.a(this.d);
                this.f = String.format(Locale.CHINA, "%.1fM", new Object[]{Float.valueOf(((float) this.c.d) / 1048576.0f)});
            }
            if (iOAdDownloader.getProgress() > 0.0f) {
                int progress = (int) iOAdDownloader.getProgress();
                if (progress > this.c.e) {
                    this.c.e = progress;
                    b();
                    return;
                }
                return;
            }
            return;
        }
        if (this.c.g == DownloadStatus.COMPLETED) {
            m.a().f().d("OAdApkDownloaderObserver", new StringBuilder("download success-->>").append(iOAdDownloader.getOutputPath()).toString());
            boolean z = this.c.l;
            m.a().f().d("OAdApkDownloaderObserver", "launch installing .............");
            String str = this.c.c + this.c.b;
            if (!this.c.i.contains(".")) {
                ApkInfo localApkFileInfo = m.a().l().getLocalApkFileInfo(this.d, str);
                this.c.i = localApkFileInfo.packageName;
            }
            if (this.e == null) {
                BroadcastReceiver aVar = new com.baidu.mobads.b.a(this.c);
                this.e = new com.baidu.mobads.b.b(this.d, this.c.i, new File(str), z);
                this.e.a(aVar);
                this.e.a();
            }
            com.baidu.mobads.c.a.a().a(this.d, this.c);
            IXAppInfo a = com.baidu.mobads.command.a.a.a(this.c);
            if (a != null) {
                com.baidu.mobads.production.a.b().getXMonitorActivation(this.d, j.a()).addAppInfoForMonitor(a);
            }
        } else if (this.c.g == DownloadStatus.ERROR) {
            this.c.k = iOAdDownloader.getTargetURL();
            m.a().f().e("OAdApkDownloaderObserver", new StringBuilder("download failed-->>").append(iOAdDownloader.getOutputPath()).toString());
            com.baidu.mobads.c.a.a().a(this.c);
        } else if (iOAdDownloader.getState() == DownloadStatus.INITING) {
            a aVar2 = this.c;
            aVar2.q++;
        }
        b();
        this.c.a(this.d);
    }

    public void b() {
        this.h.post(new c(this));
    }

    private void d(String str) {
        Toast.makeText(this.d, str, 0).show();
    }

    @TargetApi(16)
    private Notification d() {
        CharSequence charSequence = this.c.a;
        CharSequence toString = new StringBuilder("\u6b63\u5728\u4e0b\u8f7d ").append(this.c.a).toString();
        CharSequence charSequence2 = com.umeng.a.d;
        int i = 17301633;
        String str;
        String str2;
        if (this.c.g == DownloadStatus.COMPLETED) {
            str = this.c.g.getMessage() + ": " + charSequence;
            str2 = " \u70b9\u51fb\u8fd9\u91cc\u5b89\u88c5\u5e94\u7528";
            i = 17301634;
        } else if (this.c.g == DownloadStatus.PAUSED) {
            str = this.c.g.getMessage() + ": " + charSequence;
            String str3 = "\u5df2\u4e3a\u60a8\u6682\u505c\u4e0b\u8f7d\uff0c \u70b9\u51fb\u901a\u77e5\u680f\u7ee7\u7eed\u4e0b\u8f7d";
            str2 = "\u76ee\u524d\u4e0d\u5728wifi\u7f51\u7edc\u4e0b\uff0c \u70b9\u51fb\u8fd9\u91cc\u7ee7\u7eed\u4e0b\u8f7d";
            i = 17301634;
        } else if (this.c.g == DownloadStatus.ERROR) {
            str = this.c.g.getMessage() + ": " + charSequence;
            str2 = " \u7a0d\u540e\u70b9\u51fb\u8fd9\u91cc\u91cd\u65b0\u4e0b\u8f7d";
            i = 17301634;
        } else if (this.c.g == DownloadStatus.DOWNLOADING) {
            str = this.c.g.getMessage() + ": " + charSequence;
            str2 = new StringBuilder("\u4e0b\u8f7d\u8fdb\u5ea6: ").append(this.c.e).append("%  \u5e94\u7528\u5927\u5c0f: ").append(this.f).toString();
        } else if (this.c.g == DownloadStatus.INITING) {
            str = this.c.g.getMessage() + ": " + charSequence;
            str2 = this.c.g.getMessage();
        }
        Intent intent = new Intent(this.d, AppActivity.class);
        intent.putExtra("dealWithDownload", true);
        intent.putExtra(Impl.COLUMN_STATUS, this.c.g.getCode());
        intent.putExtra(IXAdRequestInfo.PACKAGE, this.c.i);
        intent.putExtra("localApkPath", this.c.c + this.c.b);
        intent.putExtra(WebBrowserActivity.EXTRA_TITLE, charSequence);
        intent.addFlags(268435456);
        intent.setAction(Long.toString(System.currentTimeMillis()));
        PendingIntent activity = PendingIntent.getActivity(this.d, this.c.f, intent, 134217728);
        if (VERSION.SDK_INT >= 16) {
            if (this.c.h == null) {
                this.c.h = new Builder(this.d);
            }
            return ((Builder) this.c.h).setContentTitle(charSequence).setContentText(charSequence2).setTicker(toString).setSmallIcon(i).setContentIntent(activity).setAutoCancel(true).setProgress(R.styleable.AppCompatTheme_buttonStyle, this.c.e, false).build();
        }
        if (this.c.h == null) {
            this.c.h = new Notification();
        }
        Notification notification = (Notification) this.c.h;
        notification.icon = i;
        notification.flags |= 16;
        notification.tickerText = toString;
        notification.contentIntent = activity;
        try {
            notification.getClass().getMethod("setLatestEventInfo", new Class[]{Context.class, CharSequence.class, CharSequence.class, PendingIntent.class}).invoke(notification, new Object[]{this.d, charSequence, charSequence2, activity});
            return notification;
        } catch (NoSuchMethodException e) {
            return notification;
        } catch (IllegalAccessException e2) {
            return notification;
        } catch (IllegalArgumentException e3) {
            return notification;
        } catch (InvocationTargetException e4) {
            return notification;
        }
    }
}
