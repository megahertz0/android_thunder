package com.xiaomi.push.log;

import android.content.Context;
import android.util.Log;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class f implements LoggerInterface {
    private static final SimpleDateFormat a;
    private static com.xiaomi.channel.commonutils.misc.f b;
    private static String c;
    private static List<Pair<String, Throwable>> f;
    private String d;
    private Context e;

    static {
        a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss aaa");
        b = new com.xiaomi.channel.commonutils.misc.f(true);
        c = "/MiPushLog";
        f = Collections.synchronizedList(new ArrayList());
    }

    public f(Context context) {
        this.e = context;
        if (context.getApplicationContext() != null) {
            this.e = context.getApplicationContext();
        }
        this.d = this.e.getPackageName();
    }

    private void b() {
        BufferedWriter bufferedWriter;
        Throwable th;
        BufferedWriter bufferedWriter2;
        FileLock fileLock;
        FileLock fileLock2 = null;
        RandomAccessFile randomAccessFile = null;
        FileLock fileLock3 = null;
        BufferedWriter bufferedWriter3 = null;
        try {
            RandomAccessFile randomAccessFile2;
            FileLock lock;
            File file = new File(this.e.getExternalFilesDir(null) + c);
            if ((file.exists() && file.isDirectory()) || file.mkdirs()) {
                File file2 = new File(file, "log.lock");
                if (!file2.exists() || file2.isDirectory()) {
                    file2.createNewFile();
                }
                randomAccessFile2 = new RandomAccessFile(file2, "rw");
                try {
                    lock = randomAccessFile2.getChannel().lock();
                    try {
                        bufferedWriter3 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(file, "log1.txt"), true)));
                        while (!f.isEmpty()) {
                            try {
                                Pair pair = (Pair) f.remove(0);
                                String str = (String) pair.first;
                                if (pair.second != null) {
                                    str = (str + "\n") + Log.getStackTraceString((Throwable) pair.second);
                                }
                                bufferedWriter3.write(str + "\n");
                            } catch (Exception e) {
                                bufferedWriter = bufferedWriter3;
                                fileLock2 = lock;
                                r3 = randomAccessFile2;
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedWriter2 = bufferedWriter3;
                            }
                        }
                        bufferedWriter3.flush();
                        bufferedWriter3.close();
                        bufferedWriter = null;
                        File file3 = new File(file, "log1.txt");
                        if (file3.length() >= 1048576) {
                            File file4 = new File(file, "log0.txt");
                            if (file4.exists() && file4.isFile()) {
                                file4.delete();
                            }
                            file3.renameTo(file4);
                        }
                        if (fileLock2 != null) {
                            try {
                                bufferedWriter.close();
                            } catch (IOException e2) {
                            }
                        }
                        if (lock != null && lock.isValid()) {
                            try {
                                lock.release();
                            } catch (IOException e3) {
                            }
                        }
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                            return;
                        }
                        return;
                    } catch (Exception e4) {
                        fileLock = fileLock2;
                        r3 = randomAccessFile2;
                        fileLock2 = lock;
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        fileLock2.release();
                        if (r3 == null) {
                            r3.close();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (bufferedWriter2 != null) {
                            bufferedWriter2.close();
                        }
                        lock.release();
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                        throw th;
                    }
                } catch (Exception e5) {
                    fileLock = fileLock2;
                    r3 = randomAccessFile2;
                    if (bufferedWriter != null) {
                        bufferedWriter.close();
                    }
                    fileLock2.release();
                    if (r3 == null) {
                        r3.close();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    lock = fileLock2;
                    if (bufferedWriter2 != null) {
                        bufferedWriter2.close();
                    }
                    lock.release();
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    throw th;
                }
            }
            if (fileLock2 != null) {
                try {
                    bufferedWriter3.close();
                } catch (IOException e6) {
                }
            }
            if (fileLock2 != null && fileLock2.isValid()) {
                try {
                    fileLock3.release();
                } catch (IOException e7) {
                }
            }
            if (fileLock2 != null) {
                randomAccessFile.close();
            }
        } catch (IOException e8) {
        }
    }

    public final void log(String str) {
        log(str, null);
    }

    public final void log(String str, Throwable th) {
        f.add(new Pair(String.format("%1$s %2$s %3$s ", new Object[]{a.format(new Date()), this.d, str}), th));
        b.a(new g(this));
    }

    public final void setTag(String str) {
        this.d = str;
    }
}
