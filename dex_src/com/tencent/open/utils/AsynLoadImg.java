package com.tencent.open.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tencent.open.a.f;
import com.umeng.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

// compiled from: ProGuard
public class AsynLoadImg {
    private static String c;
    private String a;
    private AsynLoadImgBack b;
    private long d;
    private Handler e;
    private Runnable f;

    // compiled from: ProGuard
    class AnonymousClass_1 extends Handler {
        AnonymousClass_1(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            f.a("AsynLoadImg", new StringBuilder("handleMessage:").append(message.arg1).toString());
            if (message.arg1 == 0) {
                AsynLoadImg.this.b.saved(message.arg1, (String) message.obj);
            } else {
                AsynLoadImg.this.b.saved(message.arg1, null);
            }
        }
    }

    public AsynLoadImg(Activity activity) {
        this.f = new Runnable() {
            public void run() {
                f.a("AsynLoadImg", "saveFileRunnable:");
                String toString = new StringBuilder("share_qq_").append(Util.encrypt(AsynLoadImg.this)).append(".jpg").toString();
                String str = c + toString;
                File file = new File(str);
                Message obtainMessage = AsynLoadImg.this.e.obtainMessage();
                if (file.exists()) {
                    obtainMessage.arg1 = 0;
                    obtainMessage.obj = str;
                    f.a("AsynLoadImg", new StringBuilder("file exists: time:").append(System.currentTimeMillis() - AsynLoadImg.this.d).toString());
                } else {
                    boolean saveFile;
                    Bitmap bitmap = AsynLoadImg.getbitmap(AsynLoadImg.this);
                    if (bitmap != null) {
                        saveFile = AsynLoadImg.this.saveFile(bitmap, toString);
                    } else {
                        f.a("AsynLoadImg", "saveFileRunnable:get bmp fail---");
                        saveFile = false;
                    }
                    if (saveFile) {
                        obtainMessage.arg1 = 0;
                        obtainMessage.obj = str;
                    } else {
                        obtainMessage.arg1 = 1;
                    }
                    f.a("AsynLoadImg", new StringBuilder("file not exists: download time:").append(System.currentTimeMillis() - AsynLoadImg.this.d).toString());
                }
                AsynLoadImg.this.e.sendMessage(obtainMessage);
            }
        };
        this.e = new AnonymousClass_1(activity.getMainLooper());
    }

    public void save(String str, AsynLoadImgBack asynLoadImgBack) {
        f.a("AsynLoadImg", "--save---");
        if (str == null || str.equals(a.d)) {
            asynLoadImgBack.saved(1, null);
        } else if (Util.hasSDCard()) {
            c = Environment.getExternalStorageDirectory() + "/tmp/";
            this.d = System.currentTimeMillis();
            this.a = str;
            this.b = asynLoadImgBack;
            new Thread(this.f).start();
        } else {
            asynLoadImgBack.saved(XZBDevice.DOWNLOAD_LIST_RECYCLE, null);
        }
    }

    public boolean saveFile(Bitmap bitmap, String str) {
        Throwable e;
        String str2 = c;
        BufferedOutputStream bufferedOutputStream = null;
        try {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdir();
            }
            str2 = str2 + str;
            f.a("AsynLoadImg", new StringBuilder("saveFile:").append(str).toString());
            OutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(new File(str2)));
            try {
                bitmap.compress(CompressFormat.JPEG, R.styleable.AppCompatTheme_panelMenuListTheme, bufferedOutputStream2);
                bufferedOutputStream2.flush();
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                return true;
            } catch (IOException e3) {
                e = e3;
                OutputStream outputStream = bufferedOutputStream2;
                e.printStackTrace();
                f.b("AsynLoadImg", "saveFile bmp fail---", e);
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return false;
            } catch (Throwable th) {
                e = th;
                outputStream = bufferedOutputStream2;
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw e;
            }
        } catch (IOException e4) {
            e = e4;
            try {
                e.printStackTrace();
                f.b("AsynLoadImg", "saveFile bmp fail---", e);
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
                return false;
            } catch (Throwable th2) {
                e = th2;
                if (bufferedOutputStream != null) {
                    try {
                        bufferedOutputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw e;
            }
        }
    }

    public static Bitmap getbitmap(String str) {
        f.a("AsynLoadImg", new StringBuilder("getbitmap:").append(str).toString());
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
            inputStream.close();
            f.a("AsynLoadImg", new StringBuilder("image download finished.").append(str).toString());
            return decodeStream;
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            f.a("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            f.a("AsynLoadImg", "getbitmap bmp fail---");
            return null;
        }
    }
}
