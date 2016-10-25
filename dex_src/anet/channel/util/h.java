package anet.channel.util;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.statist.ExceptionStatistic;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.UUID;

// compiled from: Taobao
public class h {
    public static File a(String str) {
        return new File(GlobalAppRuntimeInfo.getContext().getExternalCacheDir(), str);
    }

    public static synchronized void a(Serializable serializable, File file) {
        Throwable e;
        Object obj = 1;
        File file2 = null;
        synchronized (h.class) {
            long currentTimeMillis = System.currentTimeMillis();
            try {
                FileOutputStream fileOutputStream;
                File a = a(UUID.randomUUID().toString().replace(SocializeConstants.OP_DIVIDER_MINUS, a.d));
                try {
                    fileOutputStream = new FileOutputStream(a);
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(fileOutputStream));
                        objectOutputStream.writeObject(serializable);
                        objectOutputStream.flush();
                        objectOutputStream.close();
                        try {
                            fileOutputStream.close();
                            file2 = a;
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            file2 = a;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        file2 = a;
                        ALog.e("awcn.SerializeHelper", "persist fail. ", null, e, "file", file.getName());
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                            obj = null;
                        } else {
                            obj = null;
                        }
                        if (obj != null) {
                            if (file2.renameTo(file)) {
                                ALog.i("awcn.SerializeHelper", "persist end.", null, "file", file.getAbsoluteFile(), "cost", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                            } else {
                                ALog.e("awcn.SerializeHelper", "rename failed.", null, new Object[0]);
                                AppMonitor.getInstance().commitStat(new ExceptionStatistic(-106, null, "rt"));
                            }
                        }
                    }
                } catch (Exception e4) {
                    e = e4;
                    fileOutputStream = null;
                    file2 = a;
                    ALog.e("awcn.SerializeHelper", "persist fail. ", null, e, "file", file.getName());
                    if (fileOutputStream != null) {
                        obj = null;
                    } else {
                        fileOutputStream.close();
                        obj = null;
                    }
                    if (obj != null) {
                        if (file2.renameTo(file)) {
                            ALog.i("awcn.SerializeHelper", "persist end.", null, "file", file.getAbsoluteFile(), "cost", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                        } else {
                            ALog.e("awcn.SerializeHelper", "rename failed.", null, new Object[0]);
                            AppMonitor.getInstance().commitStat(new ExceptionStatistic(-106, null, "rt"));
                        }
                    }
                } catch (Throwable th) {
                    e = th;
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    throw e;
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream = null;
                try {
                    ALog.e("awcn.SerializeHelper", "persist fail. ", null, e, "file", file.getName());
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                            obj = null;
                        } catch (IOException e6) {
                            e6.printStackTrace();
                            obj = null;
                        }
                    } else {
                        obj = null;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw e;
                }
                if (obj != null) {
                    if (file2.renameTo(file)) {
                        ALog.e("awcn.SerializeHelper", "rename failed.", null, new Object[0]);
                        AppMonitor.getInstance().commitStat(new ExceptionStatistic(-106, null, "rt"));
                    } else {
                        ALog.i("awcn.SerializeHelper", "persist end.", null, "file", file.getAbsoluteFile(), "cost", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                    }
                }
            } catch (Throwable th3) {
                e = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e222) {
                        e222.printStackTrace();
                    }
                }
                throw e;
            }
            if (obj != null) {
                if (file2.renameTo(file)) {
                    ALog.i("awcn.SerializeHelper", "persist end.", null, "file", file.getAbsoluteFile(), "cost", Long.valueOf(System.currentTimeMillis() - currentTimeMillis));
                } else {
                    ALog.e("awcn.SerializeHelper", "rename failed.", null, new Object[0]);
                    AppMonitor.getInstance().commitStat(new ExceptionStatistic(-106, null, "rt"));
                }
            }
        }
    }

    public static synchronized <T> T a(File file) {
        FileInputStream fileInputStream;
        Throwable th;
        T t = null;
        synchronized (h.class) {
            try {
                if (file.exists()) {
                    fileInputStream = new FileInputStream(file);
                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(fileInputStream));
                        t = objectInputStream.readObject();
                        objectInputStream.close();
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (ALog.isPrintLog()) {
                            ALog.e("awcn.SerializeHelper", "restore file fail.", null, th, new Object[0]);
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return t;
                    }
                } else if (ALog.isPrintLog()) {
                    ALog.w("awcn.SerializeHelper", "file not exist.", null, "file", file.getName());
                }
            } catch (Throwable th3) {
                fileInputStream = null;
                th = th3;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return t;
    }
}
