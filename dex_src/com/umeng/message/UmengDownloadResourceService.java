package com.umeng.message;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.text.TextUtils;
import com.umeng.common.UmLog;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.common.SocializeConstants;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import org.json.JSONObject;

public class UmengDownloadResourceService extends Service {
    public static final String TAG;
    private static final String d = ".tmp";
    private static final String e = "RETRY_TIME";
    private static final String f = "OPERATIOIN";
    private static final int g = 1;
    private static final int h = 2;
    private static final long i = 1048576;
    private static final long j = 86400000;
    private static final int k = 300000;
    private static final int l = 3;
    private static Thread m;
    ScheduledThreadPoolExecutor a;
    Context b;
    ArrayList<String> c;

    final class AnonymousClass_1 implements Runnable {
        final /* synthetic */ File a;
        final /* synthetic */ long b;

        AnonymousClass_1(File file, long j) {
            this.a = file;
            this.b = j;
        }

        public final void run() {
            UmengDownloadResourceService.b(this.a, this.b);
            m = null;
        }
    }

    public class DownloadResourceTask extends AsyncTask<Void, Void, Boolean> {
        UMessage a;
        ArrayList<String> b;
        int c;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Boolean) obj);
        }

        public DownloadResourceTask(UMessage uMessage, int i) {
            this.a = uMessage;
            this.b = new ArrayList();
            if (uMessage.isLargeIconFromInternet()) {
                this.b.add(uMessage.img);
            }
            if (uMessage.isSoundFromInternet()) {
                this.b.add(uMessage.sound);
            }
            this.c = i;
        }

        protected Boolean a(Void... voidArr) {
            Iterator it = this.b.iterator();
            boolean z = true;
            while (it.hasNext()) {
                z = download((String) it.next()) & z;
            }
            return Boolean.valueOf(z);
        }

        protected void a(Boolean bool) {
            super.onPostExecute(bool);
            UmengDownloadResourceService.this.c.remove(this.a.msg_id);
            if (bool.booleanValue() || this.c <= 0) {
                MessageSharedPrefs.getInstance(UmengDownloadResourceService.this.b).setMessageResourceDownloaded(this.a.msg_id);
                String toString = this.a.getRaw().toString();
                Intent intent = new Intent(UmengDownloadResourceService.this.b, UmengDownloadResourceService.class);
                intent.putExtra("body", toString);
                intent.putExtra(SocializeConstants.WEIBO_ID, this.a.message_id);
                intent.putExtra("task_id", this.a.task_id);
                intent.putExtra(f, g);
                intent.putExtra(e, this.c);
                UmengDownloadResourceService.this.startService(intent);
            } else if (UmengDownloadResourceService.this.c.size() == 0) {
                UmengDownloadResourceService.this.stopSelf();
            }
        }

        public boolean download(String str) {
            Closeable fileOutputStream;
            Throwable th;
            UmengDownloadResourceService umengDownloadResourceService = null;
            Closeable closeable = null;
            if (TextUtils.isEmpty(str)) {
                return true;
            }
            try {
                String str2 = str.hashCode();
                String messageResourceFolder = UmengDownloadResourceService.getMessageResourceFolder(UmengDownloadResourceService.this.b, this.a);
                File file = new File(messageResourceFolder, str2 + d);
                File file2 = new File(messageResourceFolder, str2);
                if (file2.exists()) {
                    UmengDownloadResourceService.this.close(null);
                    UmengDownloadResourceService.this.close(null);
                    return true;
                }
                File file3 = new File(messageResourceFolder);
                if (!file3.exists()) {
                    file3.mkdirs();
                }
                if (file.exists()) {
                    file.delete();
                }
                Closeable openStream = new URL(new URI(str).toASCIIString()).openStream();
                try {
                    fileOutputStream = new FileOutputStream(file);
                    int i = 10240;
                } catch (Exception e) {
                    e = e;
                    fileOutputStream = openStream;
                    e.printStackTrace();
                    UmengDownloadResourceService.this.close(fileOutputStream);
                    UmengDownloadResourceService.this.close(closeable);
                    return umengDownloadResourceService;
                } catch (Throwable th2) {
                    th = th2;
                    UmengDownloadResourceService.this.close(openStream);
                    UmengDownloadResourceService.this.close(closeable);
                    throw th;
                }
                try {
                    byte[] bArr = new byte[10240];
                    while (true) {
                        int read = openStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            file.renameTo(file2);
                            UmengDownloadResourceService.this.close(openStream);
                            umengDownloadResourceService = UmengDownloadResourceService.this;
                            umengDownloadResourceService.close(fileOutputStream);
                            return true;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    closeable = fileOutputStream;
                    fileOutputStream = openStream;
                } catch (Throwable th3) {
                    th = th3;
                    closeable = fileOutputStream;
                    UmengDownloadResourceService.this.close(openStream);
                    UmengDownloadResourceService.this.close(closeable);
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
                try {
                    Exception e4;
                    e4.printStackTrace();
                    UmengDownloadResourceService.this.close(fileOutputStream);
                    UmengDownloadResourceService.this.close(closeable);
                    return umengDownloadResourceService;
                } catch (Throwable th4) {
                    th = th4;
                    openStream = fileOutputStream;
                }
            } catch (Throwable th5) {
                th = th5;
                openStream = null;
                UmengDownloadResourceService.this.close(openStream);
                UmengDownloadResourceService.this.close(closeable);
                throw th;
            }
        }
    }

    static {
        TAG = UmengDownloadResourceService.class.getSimpleName();
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        this.a = new ScheduledThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 4);
        this.b = this;
        this.c = new ArrayList();
    }

    @SuppressLint({"NewApi"})
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent == null) {
            return super.onStartCommand(intent, i, i2);
        }
        int intExtra = intent.getIntExtra(f, h);
        int intExtra2 = intent.getIntExtra(e, l);
        try {
            UMessage uMessage = new UMessage(new JSONObject(intent.getStringExtra("body")));
            uMessage.message_id = intent.getStringExtra(SocializeConstants.WEIBO_ID);
            uMessage.task_id = intent.getStringExtra("task_id");
            if (this.c.contains(uMessage.msg_id)) {
                return super.onStartCommand(intent, i, i2);
            }
            this.c.add(uMessage.msg_id);
            switch (intExtra) {
                case g:
                    deleteAlarm(uMessage, intExtra2);
                    UmLog.i(TAG, "Show Notification After Downloaded Resource");
                    notification(uMessage);
                    this.c.remove(uMessage.msg_id);
                    if (this.c.size() == 0) {
                        stopSelf();
                    }
                    break;
                case h:
                    UmLog.i(TAG, "Start Download Resource");
                    intExtra = intExtra2 - 1;
                    setAlarm(uMessage, intExtra);
                    checkCache();
                    downloadResource(uMessage, intExtra);
                    break;
            }
            return super.onStartCommand(intent, i, i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void notification(UMessage uMessage) {
        UHandler messageHandler = PushAgent.getInstance(this).getMessageHandler();
        if (messageHandler == null) {
            return;
        }
        if (TextUtils.equals(UMessage.DISPLAY_TYPE_AUTOUPDATE, uMessage.display_type)) {
            UmengMessageHandler umengMessageHandler = (UmengMessageHandler) PushAgent.getInstance(this.b).getMessageHandler();
            if (umengMessageHandler != null) {
                umengMessageHandler.dealWithNotificationMessage(this.b, uMessage);
                return;
            }
            return;
        }
        messageHandler.handleMessage(this, uMessage);
    }

    @SuppressLint({"NewApi"})
    public void downloadResource(UMessage uMessage, int i) {
        DownloadResourceTask downloadResourceTask = new DownloadResourceTask(uMessage, i);
        if (VERSION.SDK_INT >= 11) {
            downloadResourceTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            downloadResourceTask.execute(new Void[0]);
        }
    }

    public void setAlarm(UMessage uMessage, int i) {
        UmLog.i(TAG, "setAlarm");
        ((AlarmManager) getSystemService(NotificationCompatApi21.CATEGORY_ALARM)).set(g, System.currentTimeMillis() + 300000, a(uMessage, i));
    }

    public void deleteAlarm(UMessage uMessage, int i) {
        UmLog.i(TAG, "deleteAlarm");
        ((AlarmManager) getSystemService(NotificationCompatApi21.CATEGORY_ALARM)).cancel(a(uMessage, i));
    }

    private PendingIntent a(UMessage uMessage, int i) {
        String toString = uMessage.getRaw().toString();
        int hashCode = uMessage.msg_id.hashCode();
        Intent intent = new Intent(this.b, UmengDownloadResourceService.class);
        intent.putExtra("body", toString);
        intent.putExtra(SocializeConstants.WEIBO_ID, uMessage.message_id);
        intent.putExtra("task_id", uMessage.task_id);
        intent.putExtra(f, h);
        intent.putExtra(e, i);
        PendingIntent service = PendingIntent.getService(this.b, hashCode, intent, 134217728);
        UmLog.i(TAG, new StringBuilder("PendingIntent: msgId:").append(uMessage.msg_id).append(",requestCode:").append(hashCode).append(",retryTime:").append(i).toString());
        return service;
    }

    public void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void checkCache() {
        try {
            checkDir(new File(getMessageResourceFolder(this.b, null)), i, j);
        } catch (Throwable th) {
        }
    }

    public static void checkDir(File file, long j, long j2) throws IOException {
        if (file.exists() && a(file.getCanonicalFile()) > j) {
            if (m == null) {
                m = new Thread(new AnonymousClass_1(file, j2));
            }
            synchronized (m) {
                m.start();
            }
        }
    }

    private static long a(File file) {
        if (file == null || !file.exists() || !file.isDirectory()) {
            return 0;
        }
        Stack stack = new Stack();
        stack.clear();
        stack.push(file);
        long j = 0;
        while (!stack.isEmpty()) {
            File[] listFiles = ((File) stack.pop()).listFiles();
            int length = listFiles.length;
            long j2 = j;
            for (int i = 0; i < length; i++) {
                File file2 = listFiles[i];
                if (!file2.isDirectory()) {
                    j2 += file2.length();
                }
            }
            j = j2;
        }
        return j;
    }

    private static void b(File file, long j) {
        if (file != null && file.exists() && file.canWrite() && file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                if (!file2.isDirectory() && System.currentTimeMillis() - file2.lastModified() > j) {
                    file2.delete();
                }
            }
        }
    }

    public static String getMessageResourceFolder(Context context, UMessage uMessage) {
        String str = context.getCacheDir() + "/umeng_push/";
        return (uMessage == null || uMessage.msg_id == null) ? str : str + uMessage.msg_id + "/";
    }
}
