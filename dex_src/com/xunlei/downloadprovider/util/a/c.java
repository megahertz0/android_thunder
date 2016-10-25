package com.xunlei.downloadprovider.util.a;

import com.sina.weibo.sdk.component.GameManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

// compiled from: OnlineFileConfigure.java
public abstract class c<T> {
    protected int o;

    public abstract File a();

    public abstract void d();

    public abstract void g();

    public c() {
        this.o = 0;
    }

    public final int h() {
        return this.o;
    }

    public final void a(int i) {
        this.o = i;
    }

    public static String a(File file) {
        Exception e;
        InputStreamReader inputStreamReader;
        Throwable th;
        String str = null;
        if (file.exists()) {
            InputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
            } catch (Exception e2) {
                e2.printStackTrace();
                fileInputStream = null;
            }
            if (fileInputStream != null) {
                try {
                    inputStreamReader = new InputStreamReader(fileInputStream, GameManager.DEFAULT_CHARSET);
                    try {
                        StringBuilder stringBuilder = new StringBuilder(1024);
                        char[] cArr = new char[4096];
                        for (int read = inputStreamReader.read(cArr); read > 0; read = inputStreamReader.read(cArr)) {
                            stringBuilder.append(cArr, 0, read);
                        }
                        str = stringBuilder.toString();
                        try {
                            fileInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        try {
                            inputStreamReader.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    } catch (Exception e4) {
                        e2 = e4;
                        e2.printStackTrace();
                        try {
                            fileInputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        return str;
                    }
                } catch (Exception e5) {
                    e2 = e5;
                    inputStreamReader = null;
                    try {
                        e2.printStackTrace();
                        fileInputStream.close();
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (IOException e3222) {
                                e3222.printStackTrace();
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        fileInputStream.close();
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        throw th;
                    }
                    return str;
                } catch (Throwable th3) {
                    inputStreamReader = null;
                    th = th3;
                    try {
                        fileInputStream.close();
                    } catch (IOException e32222) {
                        e32222.printStackTrace();
                    }
                    if (inputStreamReader != null) {
                        try {
                            inputStreamReader.close();
                        } catch (IOException e322222) {
                            e322222.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        }
        return str;
    }
}
