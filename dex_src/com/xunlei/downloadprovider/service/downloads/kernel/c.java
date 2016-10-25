package com.xunlei.downloadprovider.service.downloads.kernel;

import android.content.ContentResolver;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.DownloadManagerException;
import com.xunlei.downloadprovider.businessutil.b;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.android.spdy.SpdyAgent;

// compiled from: DownloadKernel.java
public final class c {
    private static c e;
    public DownloadManager a;
    public Context b;
    ConnectivityManager c;
    ContentResolver d;

    static {
        e = new c();
    }

    private c() {
        this.a = null;
    }

    public static c a() {
        return e;
    }

    public final synchronized void b(Context context) {
        this.b = context.getApplicationContext();
        if (this.a == null) {
            Object obj;
            if ("mounted".equals(Environment.getExternalStorageState())) {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj != null) {
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/xunlei/ThunderdownDB");
                if (!(file.exists() && file.isDirectory())) {
                    file.mkdirs();
                }
                this.a = DownloadManager.getInstanceFor(context, null, new File(file, "xl_downloads.db"));
            } else {
                this.a = DownloadManager.getInstanceFor(context);
            }
            int j = b.a().j();
            if (j <= 0 || j > 5) {
                j = XZBDevice.DOWNLOAD_LIST_FAILED;
            }
            this.a.setRecommandMaxConcurrentDownloads(j);
        }
        if (this.d == null) {
            this.d = context.getApplicationContext().getContentResolver();
        }
        if (this.c == null) {
            this.c = (ConnectivityManager) context.getApplicationContext().getSystemService("connectivity");
        }
    }

    public final long c() {
        if (this.a != null) {
            try {
                return this.a.getMaxDownloadSpeed();
            } catch (DownloadManagerException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public static long[] a(List<Long> list) {
        int size = list == null ? 0 : list.size();
        long[] jArr = new long[size];
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                jArr[i] = ((Long) list.get(i)).longValue();
            }
        }
        return jArr;
    }

    public final int a(long... jArr) {
        int i = 0;
        if (jArr == null || jArr.length == 0 || this.a == null) {
            return 0;
        }
        if (jArr.length <= 50) {
            return this.a.pauseDownload(jArr);
        }
        int i2 = 0;
        while (i < jArr.length) {
            int min = Math.min(R.styleable.AppCompatTheme_buttonBarStyle, jArr.length - i);
            if (min > 0) {
                i2 += this.a.pauseDownload(Arrays.copyOfRange(jArr, i, min + i));
            }
            i += 50;
        }
        return i2;
    }

    public final int a(boolean z, long... jArr) {
        int i = 0;
        if (jArr == null || jArr.length == 0 || this.a == null) {
            return 0;
        }
        int i2;
        boolean z2;
        if (this.c.getActiveNetworkInfo() == null) {
            i2 = 0;
            z2 = false;
        } else if (d() && z) {
            i2 = XZBDevice.DOWNLOAD_LIST_FAILED;
            z2 = true;
        } else {
            i2 = XZBDevice.DOWNLOAD_LIST_RECYCLE;
            z2 = false;
        }
        if (jArr.length > 50) {
            int i3 = 0;
            while (i < jArr.length) {
                int min = Math.min(R.styleable.AppCompatTheme_buttonBarStyle, jArr.length - i);
                if (min > 0) {
                    long[] copyOfRange = Arrays.copyOfRange(jArr, i, min + i);
                    this.a.setAllowedNetworkTypes(i2, copyOfRange);
                    i3 += this.a.resumeDownload(z2, copyOfRange);
                }
                i += 50;
            }
            return i3;
        }
        this.a.setAllowedNetworkTypes(i2, jArr);
        return this.a.resumeDownload(z2, jArr) + 0;
    }

    public final int b(boolean z, long... jArr) {
        int i = 0;
        if (jArr == null || jArr.length == 0 || this.a == null) {
            return 0;
        }
        if (jArr.length <= 50) {
            return this.a.remove(z, jArr);
        }
        int i2 = 0;
        while (i < jArr.length) {
            int min = Math.min(R.styleable.AppCompatTheme_buttonBarStyle, jArr.length - i);
            if (min > 0) {
                i2 += this.a.remove(z, Arrays.copyOfRange(jArr, i, min + i));
            }
            i += 50;
        }
        return i2;
    }

    public final int b(long... jArr) {
        int i = 0;
        if (jArr == null || jArr.length == 0 || this.a == null) {
            return 0;
        }
        if (jArr.length <= 50) {
            return this.a.openLXSpeedUp(jArr);
        }
        int i2 = 0;
        while (i < jArr.length) {
            int min = Math.min(R.styleable.AppCompatTheme_buttonBarStyle, jArr.length - i);
            if (min > 0) {
                i2 += this.a.openLXSpeedUp(Arrays.copyOfRange(jArr, i, min + i));
            }
            i += 50;
        }
        return i2;
    }

    public final int c(long... jArr) {
        int i = 0;
        if (jArr == null || jArr.length == 0 || this.a == null) {
            return 0;
        }
        if (jArr.length <= 50) {
            return this.a.openVIPSpeedUp(jArr);
        }
        int i2 = 0;
        while (i < jArr.length) {
            int min = Math.min(R.styleable.AppCompatTheme_buttonBarStyle, jArr.length - i);
            if (min > 0) {
                i2 += this.a.openVIPSpeedUp(Arrays.copyOfRange(jArr, i, min + i));
            }
            i += 50;
        }
        return i2;
    }

    public final String a(String str) {
        try {
            return this.a.getPlayUrl(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public final void a(long j) {
        if (this.a != null) {
            this.a.setPlayTask(j);
        }
    }

    public final void a(long j, long j2) {
        if (this.a != null) {
            new StringBuilder("setPlayTask taskId=").append(j).append(", index = ").append(j2).append(", res=").append(this.a.setPlayTask(j, j2));
        }
    }

    public final int c(boolean z, long... jArr) {
        if (jArr == null || jArr.length == 0 || this.a == null) {
            return 0;
        }
        if (this.c.getActiveNetworkInfo() != null) {
            if (d() && z) {
                a((int) XZBDevice.DOWNLOAD_LIST_FAILED, jArr);
            } else {
                a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, jArr);
            }
        }
        return this.a.restartDownload(jArr);
    }

    final int a(int i, long... jArr) {
        int i2 = 0;
        if (jArr == null || jArr.length == 0 || this.a == null) {
            return 0;
        }
        if (jArr.length <= 50) {
            return this.a.setAllowedNetworkTypes(i, jArr);
        }
        int i3 = 0;
        while (i2 < jArr.length) {
            int min = Math.min(R.styleable.AppCompatTheme_buttonBarStyle, jArr.length - i2);
            if (min > 0) {
                i3 += this.a.setAllowedNetworkTypes(i, Arrays.copyOfRange(jArr, i2, min + i2));
            }
            i2 += 50;
        }
        return i3;
    }

    final boolean d() {
        NetworkInfo activeNetworkInfo = this.c.getActiveNetworkInfo();
        if (activeNetworkInfo == null) {
            return false;
        }
        switch (activeNetworkInfo.getType()) {
            case SpdyAgent.ACCS_TEST_SERVER:
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
            case XZBDevice.DOWNLOAD_LIST_FAILED:
            case XZBDevice.DOWNLOAD_LIST_ALL:
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
            case XZBDevice.Stop:
            case XZBDevice.Success:
            case XZBDevice.Fail:
            case XZBDevice.Predownload:
                return true;
            default:
                return false;
        }
    }

    public static DownloadManager b() {
        return e.a;
    }

    public static DownloadManager a(Context context) {
        if (e.a == null) {
            e.b(context);
        }
        return e.a;
    }
}
