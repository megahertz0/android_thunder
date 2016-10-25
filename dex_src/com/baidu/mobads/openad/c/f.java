package com.baidu.mobads.openad.c;

import android.content.Context;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import com.xunlei.tdlive.R;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Observable;

public class f extends Observable implements IOAdDownloader, Runnable {
    protected Context a;
    protected URL b;
    protected String c;
    protected String d;
    protected int e;
    protected DownloadStatus f;
    protected int g;
    protected int h;
    private boolean i;

    public f(Context context, URL url, String str, String str2, boolean z) {
        this.i = false;
        this.a = context;
        this.b = url;
        this.c = str;
        this.i = z;
        if (str2 == null || str2.trim().length() <= 0) {
            String file = url.getFile();
            this.d = file.substring(file.lastIndexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle) + 1);
        } else {
            this.d = str2;
        }
        this.e = -1;
        this.f = DownloadStatus.DOWNLOADING;
        this.g = 0;
        this.h = 0;
    }

    @Deprecated
    public void pause() {
    }

    @Deprecated
    public void cancel() {
    }

    @Deprecated
    public void resume() {
    }

    public void start() {
        a(DownloadStatus.DOWNLOADING);
        b();
    }

    public String getURL() {
        return this.b.toString();
    }

    public int getFileSize() {
        return this.e;
    }

    public float getProgress() {
        return Math.abs((((float) this.g) / ((float) this.e)) * 100.0f);
    }

    public DownloadStatus getState() {
        return this.f;
    }

    protected void a(DownloadStatus downloadStatus) {
        this.f = downloadStatus;
        c();
    }

    protected void b() {
        new Thread(this).start();
    }

    protected void a(int i, float f) {
        this.g += i;
        c();
    }

    protected void c() {
        setChanged();
        notifyObservers();
    }

    public String getOutputPath() {
        return this.c + this.d;
    }

    private void d() {
        a(DownloadStatus.ERROR);
    }

    public void run() {
        BufferedOutputStream bufferedOutputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        BufferedOutputStream bufferedOutputStream2;
        BufferedInputStream bufferedInputStream;
        HttpURLConnection httpURLConnection;
        Exception exception;
        HttpURLConnection httpURLConnection2;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            BufferedInputStream bufferedInputStream2;
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) this.b.openConnection();
            try {
                httpURLConnection3.setConnectTimeout(10000);
                httpURLConnection3.setInstanceFollowRedirects(true);
                httpURLConnection3.connect();
                if (httpURLConnection3.getResponseCode() / 100 != 2) {
                    d();
                }
                int contentLength = httpURLConnection3.getContentLength();
                if (contentLength > 0) {
                    this.e = contentLength;
                }
                File file = new File(this.c);
                if (!file.exists()) {
                    file.mkdirs();
                }
                bufferedInputStream2 = new BufferedInputStream(httpURLConnection3.getInputStream());
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(getOutputPath() + ".tmp"));
                    try {
                        byte[] bArr = new byte[10240];
                        if (this.i) {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                        } else {
                            byteArrayOutputStream = null;
                        }
                        int i = 0;
                        while (this.f == DownloadStatus.DOWNLOADING) {
                            try {
                                int read = bufferedInputStream2.read(bArr, 0, 10240);
                                if (read != -1) {
                                    bufferedOutputStream.write(bArr, 0, read);
                                    if (byteArrayOutputStream != null) {
                                        byteArrayOutputStream.write(bArr, 0, read);
                                    }
                                    i += read;
                                    a(read, ((float) i) / ((float) this.e));
                                }
                            } catch (Exception e) {
                                Exception exception2 = e;
                                byteArrayOutputStream2 = byteArrayOutputStream;
                                bufferedOutputStream2 = bufferedOutputStream;
                                bufferedInputStream = bufferedInputStream2;
                                httpURLConnection = httpURLConnection3;
                                exception = exception2;
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                byteArrayOutputStream2 = byteArrayOutputStream;
                                httpURLConnection2 = httpURLConnection3;
                                th = th3;
                            }
                        }
                        break;
                        if (this.f == DownloadStatus.DOWNLOADING) {
                            a();
                            a(DownloadStatus.COMPLETED);
                        } else {
                            DownloadStatus downloadStatus = DownloadStatus.ERROR;
                        }
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e2) {
                                m.a().f().e("OAdSimpleFileDownloader", e2.getMessage());
                            }
                        }
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e3) {
                                m.a().f().e("OAdSimpleFileDownloader", e3.getMessage());
                            }
                        }
                        if (bufferedInputStream2 != null) {
                            try {
                                bufferedInputStream2.close();
                            } catch (IOException e32) {
                                m.a().f().e("OAdSimpleFileDownloader", e32.getMessage());
                            }
                        }
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                    } catch (Exception e4) {
                        exception2 = e4;
                        bufferedOutputStream2 = bufferedOutputStream;
                        bufferedInputStream = bufferedInputStream2;
                        httpURLConnection = httpURLConnection3;
                        exception = exception2;
                        m.a().f().e("OAdSimpleFileDownloader", exception.getMessage());
                        d();
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        if (byteArrayOutputStream2 != null) {
                            byteArrayOutputStream2.close();
                        }
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (httpURLConnection == null) {
                            httpURLConnection.disconnect();
                        }
                    } catch (Throwable th4) {
                        th3 = th4;
                        httpURLConnection2 = httpURLConnection3;
                        th = th3;
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        if (byteArrayOutputStream2 != null) {
                            byteArrayOutputStream2.close();
                        }
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw th;
                    }
                } catch (Exception e42) {
                    bufferedInputStream = bufferedInputStream2;
                    httpURLConnection = httpURLConnection3;
                    exception = e42;
                    bufferedOutputStream2 = null;
                    m.a().f().e("OAdSimpleFileDownloader", exception.getMessage());
                    d();
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (httpURLConnection == null) {
                        httpURLConnection.disconnect();
                    }
                } catch (Throwable th42) {
                    bufferedOutputStream = null;
                    th3 = th42;
                    httpURLConnection2 = httpURLConnection3;
                    th = th3;
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (byteArrayOutputStream2 != null) {
                        byteArrayOutputStream2.close();
                    }
                    if (bufferedInputStream2 != null) {
                        bufferedInputStream2.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e422) {
                bufferedInputStream = null;
                httpURLConnection = httpURLConnection3;
                exception = e422;
                bufferedOutputStream2 = null;
                m.a().f().e("OAdSimpleFileDownloader", exception.getMessage());
                d();
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (httpURLConnection == null) {
                    httpURLConnection.disconnect();
                }
            } catch (Throwable th422) {
                bufferedOutputStream = null;
                bufferedInputStream2 = null;
                th3 = th422;
                httpURLConnection2 = httpURLConnection3;
                th = th3;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                if (byteArrayOutputStream2 != null) {
                    byteArrayOutputStream2.close();
                }
                if (bufferedInputStream2 != null) {
                    bufferedInputStream2.close();
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (Exception e5) {
            exception = e5;
            bufferedOutputStream2 = null;
            bufferedInputStream = null;
            httpURLConnection = null;
            try {
                m.a().f().e("OAdSimpleFileDownloader", exception.getMessage());
                d();
                if (bufferedOutputStream2 != null) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException e6) {
                        m.a().f().e("OAdSimpleFileDownloader", e6.getMessage());
                    }
                }
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (IOException e62) {
                        m.a().f().e("OAdSimpleFileDownloader", e62.getMessage());
                    }
                }
                if (bufferedInputStream != null) {
                    try {
                        bufferedInputStream.close();
                    } catch (IOException e622) {
                        m.a().f().e("OAdSimpleFileDownloader", e622.getMessage());
                    }
                }
                if (httpURLConnection == null) {
                    httpURLConnection.disconnect();
                }
            } catch (Throwable th5) {
                th = th5;
                BufferedOutputStream bufferedOutputStream3 = bufferedOutputStream2;
                httpURLConnection2 = httpURLConnection;
                bufferedInputStream2 = bufferedInputStream;
                bufferedOutputStream = bufferedOutputStream3;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e7) {
                        m.a().f().e("OAdSimpleFileDownloader", e7.getMessage());
                    }
                }
                if (byteArrayOutputStream2 != null) {
                    try {
                        byteArrayOutputStream2.close();
                    } catch (IOException e22) {
                        m.a().f().e("OAdSimpleFileDownloader", e22.getMessage());
                    }
                }
                if (bufferedInputStream2 != null) {
                    try {
                        bufferedInputStream2.close();
                    } catch (IOException e222) {
                        m.a().f().e("OAdSimpleFileDownloader", e222.getMessage());
                    }
                }
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            bufferedOutputStream = null;
            bufferedInputStream2 = null;
            httpURLConnection2 = null;
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
            if (byteArrayOutputStream2 != null) {
                byteArrayOutputStream2.close();
            }
            if (bufferedInputStream2 != null) {
                bufferedInputStream2.close();
            }
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th;
        }
    }

    public void a() {
        m.a().k().renameFile(this.c + this.d + ".tmp", this.c + this.d);
    }

    @Deprecated
    public String getTitle() {
        return null;
    }

    @Deprecated
    public String getPackageName() {
        return null;
    }

    public void removeObservers() {
    }

    @Deprecated
    public String getTargetURL() {
        return null;
    }
}
