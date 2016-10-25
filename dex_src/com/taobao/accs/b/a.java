package com.taobao.accs.b;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;
import android.os.StatFs;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.mobads.interfaces.IXAdRequestInfo;
import com.sina.weibo.sdk.component.GameManager;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UT;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.concurrent.locks.ReentrantLock;
import org.android.agoo.message.MessageService;

// compiled from: Taobao
public class a implements Callback {
    public static final int ACT_START = 0;
    public static final int ACT_STOP = -1;
    public static final String AGOO_PID = "agoo.pid";
    public static final String EX_FILE_NAME = "DaemonServer";
    public static final String PROCESS_NAME = "runServer";
    public static String a;
    private static final String c;
    private static int g;
    private static int h;
    private static final ReentrantLock i;
    private static a j;
    public boolean b;
    private Context d;
    private String e;
    private int f;
    private String k;
    private String l;
    private String m;
    private String n;
    private int o;
    private String p;
    private String q;
    private int r;
    private boolean s;
    private Handler t;
    private HandlerThread u;

    static {
        c = a.class.getName();
        a = "startservice -n {packname}/com.taobao.accs.ChannelService";
        g = 7200;
        h = 2500;
        i = new ReentrantLock();
        j = null;
    }

    public a(Context context, int i, boolean z) {
        this.d = null;
        this.f = 1800;
        this.b = false;
        this.k = "100001";
        this.l = "tb_accs_eudemon_1.1.3";
        this.m = com.umeng.a.d;
        this.n = "21646297";
        this.o = 0;
        this.p = "100.69.165.28";
        this.q = "http://100.69.165.28/agoo/report";
        this.r = 80;
        this.s = true;
        this.t = null;
        this.u = null;
        b();
        a = "startservice -n {packname}/com.taobao.accs.ChannelService";
        this.d = context;
        this.f = i;
        this.b = z;
        this.e = a(new Build(), "CPU_ABI");
        this.m = new StringBuilder("/data/data/").append(context.getPackageName()).toString();
        this.o = 212;
        this.n = UtilityImpl.getAppkey(this.d);
        if (UtilityImpl.isReleaseMode(context)) {
            this.p = "agoodm.m.taobao.com";
            this.r = 80;
            this.q = "http://agoodm.m.taobao.com/agoo/report";
            this.k = "1009527";
        } else if (UtilityImpl.isPreviewMode(context)) {
            this.p = "110.75.98.154";
            this.r = 80;
            this.q = "http://agoodm.wapa.taobao.com/agoo/report";
            this.k = "1009527";
        } else {
            this.p = "100.69.168.33";
            this.r = 80;
            this.q = "http://100.69.168.33/agoo/report";
            this.f = 60;
            this.k = "9527";
        }
    }

    private void b() {
        this.u = new HandlerThread("soManager-threads");
        this.u.start();
        this.t = new Handler(this.u.getLooper(), this);
    }

    private String c() {
        return this.e.startsWith("arm") ? "armeabi/" : this.e + "/";
    }

    private static String a(Build build, String str) {
        try {
            return Build.class.getField(str).get(build).toString();
        } catch (Throwable th) {
            return "Unknown";
        }
    }

    public static a a(Context context, int i, boolean z) {
        try {
            i.lock();
            if (j == null) {
                j = new a(context, i, z);
            }
            i.unlock();
        } catch (Throwable e) {
            ALog.e(c, "getInstance", e, new Object[0]);
            i.unlock();
        }
        return j;
    }

    private String d() throws IOException {
        InputStream inputStream = null;
        File file = new File(this.d.getFilesDir(), EX_FILE_NAME);
        if (file.exists()) {
            file.delete();
        }
        ALog.w(c, new StringBuilder("open assets from = ").append(c()).append(EX_FILE_NAME).toString(), new Object[0]);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            if (this.b) {
                inputStream = this.d.getAssets().open(c() + EX_FILE_NAME);
                byte[] bArr = new byte[100];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, ACT_START, read);
                }
            } else {
                a(fileOutputStream, file);
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Throwable e) {
                    ALog.e(c, "error in close input file", e, new Object[0]);
                }
            }
            try {
                fileOutputStream.close();
            } catch (Throwable e2) {
                ALog.e(c, "error in close io", e2, new Object[0]);
            }
        } catch (Throwable e22) {
            try {
                ALog.e(c, "error in copy daemon files", e22, new Object[0]);
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e222) {
                        ALog.e(c, "error in close input file", e222, new Object[0]);
                    }
                }
                try {
                    fileOutputStream.close();
                } catch (Throwable e2222) {
                    ALog.e(c, "error in close io", e2222, new Object[0]);
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e3) {
                        ALog.e(c, "error in close input file", e3, new Object[0]);
                    }
                }
                try {
                    fileOutputStream.close();
                } catch (Throwable e32) {
                    ALog.e(c, "error in close io", e32, new Object[0]);
                }
            }
        }
        return file.getCanonicalPath();
    }

    private void a(FileOutputStream fileOutputStream, File file) throws IOException {
        ByteArrayInputStream byteArrayInputStream;
        Throwable e;
        String a = b.a(this.e);
        ALog.d(c, new StringBuilder(">>>soDataSize:datasize:").append(a.length()).toString(), new Object[0]);
        byte[] decode = Base64.decode(a.getBytes(), ACT_START);
        ALog.d(c, new StringBuilder(">>>soDataSize:").append(decode.length).toString(), new Object[0]);
        if (decode.length > 0 && fileOutputStream != null) {
            StatFs statFs = new StatFs(file.getCanonicalPath());
            if (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize()) >= ((long) decode.length)) {
                try {
                    byteArrayInputStream = new ByteArrayInputStream(decode);
                    try {
                        decode = new byte[100];
                        while (true) {
                            int read = byteArrayInputStream.read(decode, ACT_START, R.styleable.AppCompatTheme_buttonStyle);
                            if (read >= 0) {
                                fileOutputStream.write(decode, ACT_START, read);
                            } else {
                                fileOutputStream.getFD().sync();
                                try {
                                    byteArrayInputStream.close();
                                    return;
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                        try {
                            ALog.e(c, "error in write files", e, new Object[0]);
                            fileOutputStream.getFD().sync();
                            if (byteArrayInputStream != null) {
                                try {
                                    byteArrayInputStream.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                        } catch (Throwable th) {
                            e = th;
                            fileOutputStream.getFD().sync();
                            if (byteArrayInputStream != null) {
                                try {
                                    byteArrayInputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            throw e;
                        }
                    }
                } catch (IOException e5) {
                    e = e5;
                    byteArrayInputStream = null;
                    ALog.e(c, "error in write files", e, new Object[0]);
                    fileOutputStream.getFD().sync();
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                } catch (Throwable th2) {
                    e = th2;
                    byteArrayInputStream = null;
                    fileOutputStream.getFD().sync();
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    throw e;
                }
            }
        }
    }

    private void a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        a(com.umeng.a.d, new StringBuilder("chmod 500 ").append(str).toString(), stringBuilder);
        a(com.umeng.a.d, str + " " + e(), stringBuilder);
        ALog.w(c, str + " " + e(), new Object[0]);
    }

    private String e() {
        StringBuilder stringBuilder = new StringBuilder();
        String toString = new StringBuilder("/data/data/").append(this.d.getPackageName()).toString();
        stringBuilder.append(new StringBuilder("-s \"").append(toString).append("/lib/\" ").toString());
        stringBuilder.append("-n \"runServer\" ");
        stringBuilder.append(new StringBuilder("-p \"").append(g()).append("\" ").toString());
        stringBuilder.append(new StringBuilder("-f \"").append(toString).append("\" ").toString());
        stringBuilder.append(new StringBuilder("-t \"").append(this.f).append("\" ").toString());
        stringBuilder.append("-c \"agoo.pid\" ");
        if (this.m != null) {
            stringBuilder.append(new StringBuilder("-P ").append(this.m).append(" ").toString());
        }
        if (this.k != null) {
            stringBuilder.append(new StringBuilder("-K ").append(this.k).append(" ").toString());
        }
        if (this.l != null) {
            stringBuilder.append(new StringBuilder("-U ").append(this.l).append(" ").toString());
        }
        if (this.q != null) {
            stringBuilder.append(new StringBuilder("-L ").append(this.q).append(" ").toString());
        }
        stringBuilder.append(new StringBuilder("-D ").append(f()).append(" ").toString());
        if (this.p != null) {
            stringBuilder.append(new StringBuilder("-I ").append(this.p).append(" ").toString());
        }
        if (this.r > 0) {
            stringBuilder.append(new StringBuilder("-O ").append(this.r).append(" ").toString());
        }
        toString = UtilityImpl.getProxyHost(this.d);
        int proxyPort = UtilityImpl.getProxyPort(this.d);
        if (toString != null && proxyPort > 0) {
            stringBuilder.append(new StringBuilder("-X ").append(toString).append(" ").toString());
            stringBuilder.append(new StringBuilder("-Y ").append(proxyPort).append(" ").toString());
        }
        if (this.s) {
            stringBuilder.append("-T ");
        }
        stringBuilder.append("-Z ");
        return stringBuilder.toString();
    }

    private String f() {
        String deviceId = UtilityImpl.getDeviceId(this.d);
        if (TextUtils.isEmpty(deviceId)) {
            deviceId = "null";
        }
        deviceId = new StringBuilder("{\"package\":\"").append(this.d.getPackageName()).append("\",\"appKey\":\"").append(this.n).append("\",\"utdid\":\"").append(deviceId).append("\",\"sdkVersion\":\"").append(this.o).append("\"}").toString();
        try {
            return URLEncoder.encode(deviceId, GameManager.DEFAULT_CHARSET);
        } catch (Throwable th) {
            ALog.e(c, new StringBuilder("getReportData failed for url encode, data:").append(deviceId).toString(), new Object[0]);
            return deviceId;
        }
    }

    private String g() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(a.replaceAll("\\{packname\\}", this.d.getApplicationContext().getPackageName()));
        if (VERSION.SDK_INT > 15) {
            stringBuilder.append(" --user 0");
        }
        return stringBuilder.toString();
    }

    private void a(String str, int i, int i2, String str2, String str3, int i3) {
        UTMini.getInstance().commitEvent(UT.EVENT_ID, "EUDEMON_ENDSTAT", new StringBuilder("AndroidVer=").append(VERSION.RELEASE).append("&Model=").append(Build.MODEL).append("&AndroidSdk=").append(VERSION.SDK_INT).append("&AccsVer=212&Appkey=").append(UtilityImpl.getAppkey(this.d)).append("&PullCount=").append(str2).append("&Pid=").append(str).append("&StartTime=").append(i).append("&EndTime=").append(i2).append("&ExitCode=").append(str3).append("&AliveTime=").append(i3).toString());
    }

    private void h() {
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        String toString = new StringBuilder("/data/data/").append(this.d.getPackageName()).append("/eudemon").toString();
        File file = new File(toString);
        if (file.exists()) {
            FileOutputStream fileOutputStream2 = null;
            InputStreamReader inputStreamReader2 = null;
            BufferedReader bufferedReader2 = null;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    String str;
                    FileOutputStream fileOutputStream3;
                    inputStreamReader = new InputStreamReader(fileInputStream);
                    try {
                        bufferedReader = new BufferedReader(inputStreamReader);
                        try {
                            str = com.umeng.a.d;
                            while (true) {
                                String readLine = bufferedReader.readLine();
                                if (readLine == null) {
                                    break;
                                }
                                String[] split = readLine.split("\\|");
                                if (split.length == 5) {
                                    String trim = split[0].trim();
                                    int intValue = Integer.valueOf(split[1].trim()).intValue();
                                    int intValue2 = Integer.valueOf(split[2].trim()).intValue();
                                    int i = intValue2 - intValue;
                                    String trim2 = split[3].trim();
                                    String trim3 = split[4].trim();
                                    if (trim3.equals(MessageService.MSG_DB_READY_REPORT)) {
                                        File file2 = new File("/proc", trim);
                                        new StringBuilder("pidfile:").append(file2);
                                        if (file2.exists()) {
                                            str = str + readLine + "\n";
                                        } else {
                                            i += this.f / 2;
                                        }
                                    }
                                    a(trim, intValue, intValue2, trim2, trim3, i);
                                }
                            }
                            fileOutputStream3 = new FileOutputStream(new File(toString));
                        } catch (Exception e) {
                            bufferedReader2 = bufferedReader;
                            inputStreamReader2 = inputStreamReader;
                            fileOutputStream = null;
                            fileInputStream2 = fileInputStream;
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (Throwable th2) {
                                }
                            }
                            if (inputStreamReader2 != null) {
                                try {
                                    inputStreamReader2.close();
                                } catch (Throwable th3) {
                                }
                            }
                            if (fileInputStream2 != null) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e2) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                }
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Throwable th5) {
                                }
                            }
                            if (inputStreamReader != null) {
                                try {
                                    inputStreamReader.close();
                                } catch (Throwable th6) {
                                }
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e4) {
                                }
                            }
                            if (fileOutputStream2 != null) {
                                try {
                                    fileOutputStream2.close();
                                } catch (IOException e5) {
                                }
                            }
                            throw th;
                        }
                    } catch (Exception e6) {
                        inputStreamReader2 = inputStreamReader;
                        fileOutputStream = null;
                        fileInputStream2 = fileInputStream;
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        if (inputStreamReader2 != null) {
                            inputStreamReader2.close();
                        }
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th7) {
                        bufferedReader = null;
                        th = th7;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        throw th;
                    }
                    try {
                        fileOutputStream3.write(str.getBytes());
                        bufferedReader.close();
                        try {
                            bufferedReader.close();
                        } catch (Throwable th8) {
                        }
                        try {
                            inputStreamReader.close();
                        } catch (Throwable th9) {
                        }
                        try {
                            fileInputStream.close();
                        } catch (IOException e7) {
                        }
                        try {
                            fileOutputStream3.close();
                        } catch (IOException e8) {
                        }
                    } catch (Exception e9) {
                        inputStreamReader2 = inputStreamReader;
                        fileOutputStream = fileOutputStream3;
                        fileInputStream2 = fileInputStream;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        if (inputStreamReader2 != null) {
                            inputStreamReader2.close();
                        }
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th72) {
                        fileOutputStream2 = fileOutputStream3;
                        th = th72;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        throw th;
                    }
                } catch (Exception e10) {
                    fileOutputStream = null;
                    fileInputStream2 = fileInputStream;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    if (inputStreamReader2 != null) {
                        inputStreamReader2.close();
                    }
                    if (fileInputStream2 != null) {
                        fileInputStream2.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Throwable th10) {
                    bufferedReader = null;
                    inputStreamReader = null;
                    th = th10;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    if (fileOutputStream2 != null) {
                        fileOutputStream2.close();
                    }
                    throw th;
                }
            } catch (Exception e11) {
                fileInputStream2 = null;
                fileOutputStream = null;
                if (bufferedReader2 != null) {
                    bufferedReader2.close();
                }
                if (inputStreamReader2 != null) {
                    inputStreamReader2.close();
                }
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th11) {
                bufferedReader = null;
                inputStreamReader = null;
                fileInputStream = null;
                th = th11;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        }
    }

    public void a() {
        Message message = new Message();
        message.what = 0;
        this.t.sendMessage(message);
    }

    private void i() {
        ALog.d(c, new StringBuilder("api level is:").append(VERSION.SDK_INT).toString(), new Object[0]);
        b(this.d);
        if (VERSION.SDK_INT < 20) {
            try {
                String d = d();
                h();
                a(d);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        UTMini.getInstance().commitEvent(UT.EVENT_ID, "EUDEMON_START", com.umeng.a.d);
    }

    private void j() {
        File file = new File(new StringBuilder("/data/data/").append(this.d.getPackageName()).toString(), "daemonserver.pid");
        if (file.exists()) {
            file.delete();
        }
    }

    public static final PendingIntent a(Context context) {
        Intent intent = new Intent();
        intent.setAction(context.getApplicationContext().getPackageName() + ".intent.action.COCKROACH");
        intent.putExtra("cockroach", "cockroach-PPreotect");
        intent.putExtra("pack", context.getApplicationContext().getPackageName());
        return PendingIntent.getService(context, ACT_START, intent, 134217728);
    }

    public static void b(Context context) {
        int i = Calendar.getInstance().get(XZBDevice.Success);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompatApi21.CATEGORY_ALARM);
        if (alarmManager != null) {
            PendingIntent a = a(context);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (i > 23 || i < 6) {
                ALog.w(c, "time is night, do not wakeup cpu", new Object[0]);
                b(alarmManager, a, elapsedRealtime);
                return;
            }
            ALog.w(c, "time is daytime, wakeup cpu for keeping connecntion", new Object[0]);
            a(alarmManager, a, elapsedRealtime);
        }
    }

    private static void a(AlarmManager alarmManager, PendingIntent pendingIntent, long j) {
        if (pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
            alarmManager.setRepeating(XZBDevice.DOWNLOAD_LIST_RECYCLE, ((long) (h * 1000)) + j, (long) (h * 1000), pendingIntent);
        }
    }

    private static void b(AlarmManager alarmManager, PendingIntent pendingIntent, long j) {
        alarmManager.cancel(pendingIntent);
        alarmManager.setRepeating(XZBDevice.DOWNLOAD_LIST_FAILED, ((long) (g * 1000)) + j, (long) (g * 1000), pendingIntent);
    }

    public static void c(Context context) {
        Throwable th;
        try {
            File file = new File(context.getFilesDir(), AGOO_PID);
            ALog.d(c, new StringBuilder("pid path:").append(file.getAbsolutePath()).toString(), new Object[0]);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            try {
                int myPid = Process.myPid();
                FileWriter fileWriter = new FileWriter(file);
                try {
                    fileWriter.write(String.valueOf(myPid).toCharArray());
                    try {
                        fileWriter.close();
                    } catch (Throwable th2) {
                        ALog.e(c, "error", th2, new Object[0]);
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    ALog.e(c, "save pid error", th2, new Object[0]);
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                }
            } catch (Throwable th4) {
                th2 = th4;
                fileWriter = null;
                if (fileWriter != null) {
                    try {
                        fileWriter.close();
                    } catch (Throwable th5) {
                        ALog.e(c, "error", th5, new Object[0]);
                    }
                }
                throw th2;
            }
        } catch (Throwable th22) {
            ALog.e(c, "error in create file", th22, new Object[0]);
        }
    }

    public boolean handleMessage(Message message) {
        try {
            if (message.what == 0) {
                i();
            } else if (message.what == -1) {
                j();
            }
        } catch (Throwable th) {
            ALog.e(c, "handleMessage error", th, new Object[0]);
        }
        return true;
    }

    public static boolean a(String str, String str2, StringBuilder stringBuilder) {
        try {
            Process exec = Runtime.getRuntime().exec(IXAdRequestInfo.SCREEN_HEIGHT);
            DataInputStream dataInputStream = new DataInputStream(exec.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(exec.getOutputStream());
            if (!(str == null || com.umeng.a.d.equals(str.trim()))) {
                dataOutputStream.writeBytes(new StringBuilder("cd ").append(str).append("\n").toString());
            }
            dataOutputStream.writeBytes(str2 + " &\n");
            dataOutputStream.writeBytes("exit \n");
            dataOutputStream.flush();
            exec.waitFor();
            byte[] bArr = new byte[dataInputStream.available()];
            dataInputStream.read(bArr);
            String str3 = new String(bArr);
            if (str3.length() != 0) {
                stringBuilder.append(str3);
            }
            return true;
        } catch (Exception e) {
            stringBuilder.append(new StringBuilder("Exception:").append(e.getMessage()).toString());
            return false;
        }
    }
}
