package com.umeng.socialize.common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.umeng.socialize.common.SocialResHelper.BindDrawableListener;
import com.umeng.socialize.common.SocialResHelper.FetchLocale;
import com.umeng.socialize.common.SocialResHelper.LoadMode;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.net.utils.UResponse$STATUS;
import com.umeng.socialize.utils.DeviceConfig;
import com.umeng.socialize.utils.Log;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Stack;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class SocialResHelper {
    private static final String CACHE_PATH = "/download/.um/";
    private static final long EXTERNAL_CACHE_SIZE = 104857600;
    private static final long INTERNAL_CACHE_SIZE = 10485760;
    public static boolean RESUTIL_V2_DUBUG = false;
    private static final String TAG = "SocialResHelper";

    /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] $SwitchMap$com$umeng$socialize$common$SocialResHelper$LoadMode;

        static {
            $SwitchMap$com$umeng$socialize$common$SocialResHelper$LoadMode = new int[LoadMode.values().length];
            try {
                $SwitchMap$com$umeng$socialize$common$SocialResHelper$LoadMode[LoadMode.LOAD_CACHE_ONLY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$umeng$socialize$common$SocialResHelper$LoadMode[LoadMode.LOAD_CACHE_ELSE_NETWORK.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$umeng$socialize$common$SocialResHelper$LoadMode[LoadMode.LOAD_NETWORK_ELSE_CACHE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public static interface BindDrawableListener {
        void onEnd(UResponse$STATUS uResponse$STATUS, File file, Drawable drawable);

        void onFetchStart(FetchLocale fetchLocale);

        void onStart(LoadMode loadMode);
    }

    public static class Builder {
        boolean isBackground;
        Animation mBindAnimation;
        BindDrawableListener mBindListener;
        Context mContext;
        int mDefaultRid;
        ImageView mImageView;
        LoadMode mLoadMode;
        boolean mTransRoundCorner;
        String mUrl;

        public Builder(Context context, ImageView imageView, String str) {
            this.mDefaultRid = -1;
            this.mLoadMode = LoadMode.LOAD_CACHE_ELSE_NETWORK;
            this.mTransRoundCorner = false;
            this.isBackground = false;
            if (context != null && imageView != null && !TextUtils.isEmpty(str)) {
                this.mContext = context;
                this.mImageView = imageView;
                this.mUrl = str;
            }
        }

        public com.umeng.socialize.common.SocialResHelper.Builder setBindListener(BindDrawableListener bindDrawableListener) {
            this.mBindListener = bindDrawableListener;
            return this;
        }

        public com.umeng.socialize.common.SocialResHelper.Builder setBindAnimation(Animation animation) {
            this.mBindAnimation = animation;
            return this;
        }

        public com.umeng.socialize.common.SocialResHelper.Builder setRoundCorner(boolean z) {
            this.mTransRoundCorner = z;
            return this;
        }

        public com.umeng.socialize.common.SocialResHelper.Builder setBindBackground(boolean z) {
            this.isBackground = z;
            return this;
        }

        public com.umeng.socialize.common.SocialResHelper.Builder setDefaultImg(int i) {
            this.mDefaultRid = i;
            return this;
        }

        public com.umeng.socialize.common.SocialResHelper.Builder setLoadMode(LoadMode loadMode) {
            this.mLoadMode = loadMode;
            return this;
        }

        public void doBindTask() {
            try {
                File cachedFile = SocialResHelper.getCachedFile(this.mContext, this.mUrl);
            } catch (Exception e) {
                Log.e(TAG, "can't get from cache.", e);
                if (this.mBindListener != null) {
                    this.mBindListener.onEnd(UResponse$STATUS.FAIL, null, null);
                }
                cachedFile = null;
            }
            Drawable createFromPath;
            switch (AnonymousClass_1.$SwitchMap$com$umeng$socialize$common$SocialResHelper$LoadMode[this.mLoadMode.ordinal()]) {
                case SimpleLog.LOG_LEVEL_TRACE:
                    if (this.mBindListener != null) {
                        this.mBindListener.onStart(LoadMode.LOAD_CACHE_ONLY);
                        this.mBindListener.onFetchStart(FetchLocale.FETCH_FROM_LOCALE_CACHE);
                    }
                    if (cachedFile == null || !cachedFile.exists()) {
                        Log.e(TAG, "cache is not exists");
                        return;
                    }
                    createFromPath = Drawable.createFromPath(cachedFile.getAbsolutePath());
                    if (createFromPath == null) {
                        cachedFile.delete();
                    }
                    doBind(this.mContext, this.mImageView, createFromPath, this.isBackground, this.mBindListener, this.mBindAnimation, this.mTransRoundCorner, this.mDefaultRid);
                case SimpleLog.LOG_LEVEL_DEBUG:
                    if (this.mBindListener != null) {
                        this.mBindListener.onStart(LoadMode.LOAD_CACHE_ELSE_NETWORK);
                        this.mBindListener.onFetchStart(FetchLocale.FETCH_FROM_LOCALE_CACHE);
                    }
                    if (cachedFile == null || !cachedFile.exists()) {
                        fetchNetElsCache(null);
                        return;
                    }
                    createFromPath = Drawable.createFromPath(cachedFile.getAbsolutePath());
                    if (createFromPath == null) {
                        cachedFile.delete();
                    }
                    doBind(this.mContext, this.mImageView, createFromPath, this.isBackground, this.mBindListener, this.mBindAnimation, this.mTransRoundCorner, this.mDefaultRid);
                case MqttConnectOptions.MQTT_VERSION_3_1:
                    if (this.mBindListener != null) {
                        this.mBindListener.onStart(LoadMode.LOAD_NETWORK_ELSE_CACHE);
                    }
                    fetchNetElsCache(null);
                default:
                    break;
            }
        }

        private void fetchNetElsCache(Drawable drawable) {
            if (drawable == null) {
                new AsyncTask<Object, Integer, Drawable>() {
                    protected void onPostExecute(Drawable drawable) {
                        com.umeng.socialize.common.SocialResHelper.Builder.this.doBind(com.umeng.socialize.common.SocialResHelper.Builder.this.mContext, com.umeng.socialize.common.SocialResHelper.Builder.this.mImageView, drawable, com.umeng.socialize.common.SocialResHelper.Builder.this.isBackground, com.umeng.socialize.common.SocialResHelper.Builder.this.mBindListener, com.umeng.socialize.common.SocialResHelper.Builder.this.mBindAnimation, com.umeng.socialize.common.SocialResHelper.Builder.this.mTransRoundCorner, com.umeng.socialize.common.SocialResHelper.Builder.this.mDefaultRid);
                    }

                    protected void onProgressUpdate(Integer... numArr) {
                        super.onProgressUpdate(numArr);
                        if (numArr != null && numArr.length > 0) {
                            switch (numArr[0].intValue()) {
                                case MqttConnectOptions.MQTT_VERSION_DEFAULT:
                                    if (com.umeng.socialize.common.SocialResHelper.Builder.this.mBindListener != null) {
                                        com.umeng.socialize.common.SocialResHelper.Builder.this.mBindListener.onFetchStart(FetchLocale.FETCH_FROM_NETWORK);
                                    }
                                case SimpleLog.LOG_LEVEL_TRACE:
                                    if (com.umeng.socialize.common.SocialResHelper.Builder.this.mBindListener != null) {
                                        com.umeng.socialize.common.SocialResHelper.Builder.this.mBindListener.onFetchStart(FetchLocale.FETCH_FROM_LOCALE_CACHE);
                                    }
                                default:
                                    break;
                            }
                        }
                    }

                    protected Drawable doInBackground(Object... objArr) {
                        try {
                            if (RESUTIL_V2_DUBUG) {
                                Thread.sleep(3000);
                            }
                        } catch (InterruptedException e) {
                        }
                        publishProgress(new Integer[]{Integer.valueOf(0)});
                        SocialResHelper.getResource(com.umeng.socialize.common.SocialResHelper.Builder.this.mContext, com.umeng.socialize.common.SocialResHelper.Builder.this.mUrl);
                        Drawable drawable = null;
                        try {
                            publishProgress(new Integer[]{Integer.valueOf(1)});
                            File cachedFile = SocialResHelper.getCachedFile(com.umeng.socialize.common.SocialResHelper.Builder.this.mContext, com.umeng.socialize.common.SocialResHelper.Builder.this.mUrl);
                            if (cachedFile != null && cachedFile.exists()) {
                                drawable = Drawable.createFromPath(cachedFile.getAbsolutePath());
                                if (drawable == null) {
                                    cachedFile.delete();
                                }
                            }
                        } catch (IOException e2) {
                            Log.w(TAG, e2.toString());
                        }
                        return drawable;
                    }
                }.execute(new Object[0]);
                return;
            }
            doBind(this.mContext, this.mImageView, drawable, this.isBackground, this.mBindListener, this.mBindAnimation, this.mTransRoundCorner, this.mDefaultRid);
        }

        private void doBind(Context context, ImageView imageView, Drawable drawable, boolean z, BindDrawableListener bindDrawableListener, Animation animation, boolean z2, int i) {
            if (drawable == null || imageView == null) {
                if (imageView != null && i > 0) {
                    imageView.setImageResource(i);
                }
                if (bindDrawableListener != null) {
                    bindDrawableListener.onEnd(UResponse$STATUS.FAIL, null, drawable);
                }
                Log.w(TAG, new StringBuilder("bind drawable failed. drawable [").append(drawable).append("]  imageView[+").append(imageView).append("+]").toString());
                return;
            }
            if (z2) {
                drawable = new BitmapDrawable(context.getResources(), SocialResHelper.getRoundedCornerBitmap(((BitmapDrawable) drawable).getBitmap()));
            }
            if (z) {
                imageView.setBackground(drawable);
            } else {
                imageView.setImageDrawable(drawable);
            }
            if (animation != null) {
                imageView.startAnimation(animation);
            }
            try {
                File cachedFile = SocialResHelper.getCachedFile(this.mContext, this.mUrl);
            } catch (IOException e) {
                e.printStackTrace();
                cachedFile = null;
            }
            if (bindDrawableListener != null) {
                bindDrawableListener.onEnd(UResponse$STATUS.SUCCESS, cachedFile, drawable);
            }
        }
    }

    public enum FetchLocale {
        FETCH_FROM_LOCALE_CACHE,
        FETCH_FROM_NETWORK
    }

    public enum LoadMode {
        LOAD_CACHE_ELSE_NETWORK,
        LOAD_CACHE_ONLY,
        LOAD_NETWORK_ELSE_CACHE
    }

    static {
        RESUTIL_V2_DUBUG = false;
    }

    private static String getMd5FileName(String str) {
        return AesHelper.md5(str);
    }

    public static String getResource(Context context, String str) {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String canonicalPath;
            long j;
            String str3 = getMd5FileName(str) + ".tmp";
            if (DeviceConfig.isSdCardWrittenable()) {
                canonicalPath = Environment.getExternalStorageDirectory().getCanonicalPath();
                j = EXTERNAL_CACHE_SIZE;
            } else {
                canonicalPath = context.getCacheDir().getCanonicalPath();
                j = INTERNAL_CACHE_SIZE;
            }
            File file = new File(canonicalPath + CACHE_PATH);
            if (file.exists()) {
                if (dirSize(file.getCanonicalFile()) > j) {
                    cleanDir(file);
                }
            } else if (!file.mkdirs()) {
                Log.e(TAG, new StringBuilder("Failed to create directory").append(file.getAbsolutePath()).append(". Check permission. Make sure WRITE_EXTERNAL_STORAGE is added in your Manifest.xml").toString());
            }
            File file2 = new File(file, str3);
            try {
                file2.createNewFile();
                persistenceUrlData(str, file2);
                File file3 = new File(file2.getParent(), file2.getName().replace(".tmp", BuildConfig.VERSION_NAME));
                file2.renameTo(file3);
                Log.i(TAG, new StringBuilder("download img[").append(str).append("]  to ").append(file3.getCanonicalPath()).toString());
                return file3.getCanonicalPath();
            } catch (Exception e) {
                Exception e2 = e;
            }
        } catch (Exception e3) {
            e2 = e3;
            file2 = null;
            Log.i(TAG, e2.getStackTrace().toString() + "\t url:\t" + str);
            if (file2 == null || !file2.exists()) {
                return str2;
            }
            file2.deleteOnExit();
            return str2;
        }
    }

    private static void persistenceUrlData(String str, File file) {
        Throwable th;
        InputStream inputStream = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                InputStream inputStream2 = (InputStream) new URL(str).openConnection().getContent();
                int i = SpdyProtocol.SLIGHTSSL_0_RTT_MODE;
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = inputStream2.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    fileOutputStream.flush();
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                            try {
                                fileOutputStream.close();
                            } catch (IOException e) {
                                Log.d(TAG, e.getMessage());
                            }
                        } catch (IOException e2) {
                            try {
                                Log.d(TAG, e2.getMessage());
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e22) {
                                    Log.d(TAG, e22.getMessage());
                                }
                            } catch (Throwable th2) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                    Log.d(TAG, e3.getMessage());
                                }
                            }
                        }
                    }
                } catch (Throwable e4) {
                    Throwable th3 = e4;
                    inputStream = inputStream2;
                    th = th3;
                    try {
                        throw new RuntimeException(th);
                    } catch (Throwable th4) {
                        th = th4;
                    }
                } catch (Throwable e42) {
                    th3 = e42;
                    inputStream = inputStream2;
                    th = th3;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e32) {
                                    Log.d(TAG, e32.getMessage());
                                }
                            }
                        } catch (IOException e322) {
                            try {
                                Log.d(TAG, e322.getMessage());
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e3222) {
                                        Log.d(TAG, e3222.getMessage());
                                    }
                                }
                            } catch (Throwable th5) {
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e32222) {
                                        Log.d(TAG, e32222.getMessage());
                                    }
                                }
                            }
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                th = e5;
                throw new RuntimeException(th);
            }
        } catch (Exception e6) {
            th = e6;
            fileOutputStream = null;
            throw new RuntimeException(th);
        } catch (Throwable th6) {
            th = th6;
            fileOutputStream = null;
            if (inputStream != null) {
                inputStream.close();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            }
            throw th;
        }
    }

    private static long dirSize(File file) {
        long j = 0;
        if (file != null && file.exists() && file.isDirectory()) {
            Stack stack = new Stack();
            stack.clear();
            stack.push(file);
            while (!stack.isEmpty()) {
                File[] listFiles = ((File) stack.pop()).listFiles();
                for (int i = 0; i < listFiles.length; i++) {
                    if (listFiles[i].isDirectory()) {
                        stack.push(listFiles[i]);
                    } else {
                        j += listFiles[i].length();
                    }
                }
            }
        }
        return j;
    }

    private static void cleanDir(File file) {
        if (file != null && file.exists() && file.canWrite() && file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (int i = 0; i < listFiles.length; i++) {
                if (listFiles[i].isDirectory()) {
                    cleanDir(listFiles[i]);
                } else if (new Date().getTime() - listFiles[i].lastModified() > 1800) {
                    listFiles[i].delete();
                }
            }
        }
    }

    protected static File getCachedFile(Context context, String str) throws IOException {
        String canonicalPath;
        String md5FileName = getMd5FileName(str);
        if (DeviceConfig.isSdCardWrittenable()) {
            canonicalPath = Environment.getExternalStorageDirectory().getCanonicalPath();
        } else {
            canonicalPath = context.getCacheDir().getCanonicalPath();
        }
        File file = new File(new File(canonicalPath + CACHE_PATH), md5FileName);
        return file.exists() ? file : null;
    }

    private static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        if (RESUTIL_V2_DUBUG) {
            canvas.drawRoundRect(rectF, (float) (bitmap.getWidth() / 2), (float) (bitmap.getHeight() / 2), paint);
        } else {
            canvas.drawRoundRect(rectF, (float) (bitmap.getWidth() / 6), (float) (bitmap.getHeight() / 6), paint);
        }
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        bitmap.recycle();
        return createBitmap;
    }
}
