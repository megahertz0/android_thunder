package com.umeng.socialize.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.umeng.common.a;
import com.umeng.socialize.net.utils.AesHelper;
import com.umeng.socialize.net.utils.UResponse$STATUS;
import com.umeng.socialize.utils.ResUtil.BindDrawableListener;
import com.umeng.socialize.utils.ResUtil.BindMode;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;

public class ResUtil {
    private static final long EXTERNAL_CACHE_SIZE = 104857600;
    private static final Map<ImageView, String> IMAGE_VIEWS;
    private static final long INTERNAL_CACHE_SIZE = 10485760;
    public static boolean RESUTIL_DEBUG = false;
    private static final String TAG = "ResUtil";

    final class AnonymousClass_1 implements Runnable {
        final /* synthetic */ File val$dir;

        AnonymousClass_1(File file) {
            this.val$dir = file;
        }

        public final void run() {
            ResUtil.cleanDir(this.val$dir);
        }
    }

    public static interface BindDrawableListener {
        void onEnd(UResponse$STATUS uResponse$STATUS);

        void onStart(BindMode bindMode);
    }

    public enum BindMode {
        BIND_FORM_CACHE,
        BIND_FROM_NET
    }

    static class FetchTask extends AsyncTask<Object, Integer, Drawable> {
        private boolean isBackground;
        private Animation mBindAnim;
        private BindDrawableListener mBindListener;
        private File mCacheFile;
        private Context mContext;
        private ImageView mImageView;
        private BindMode mMode;
        private String mUrl;

        public FetchTask(Context context, ImageView imageView, String str, BindMode bindMode, File file, boolean z, BindDrawableListener bindDrawableListener, Animation animation) {
            this.mCacheFile = file;
            this.mContext = context;
            this.mUrl = str;
            this.mBindListener = bindDrawableListener;
            this.mMode = bindMode;
            this.isBackground = z;
            this.mBindAnim = animation;
            this.mImageView = imageView;
        }

        protected void onPreExecute() {
            super.onPreExecute();
            if (this.mBindListener != null) {
                this.mBindListener.onStart(this.mMode);
            }
        }

        protected void onPostExecute(Drawable drawable) {
            ResUtil.doBind(this.mContext, this.mImageView, drawable, this.isBackground, this.mBindListener, this.mBindAnim, this.mUrl);
        }

        protected Drawable doInBackground(Object... objArr) {
            if (RESUTIL_DEBUG) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Drawable drawable;
            if (this.mCacheFile == null || !this.mCacheFile.exists()) {
                try {
                    ResUtil.getResource(this.mContext, this.mUrl);
                    File cachedFile = ResUtil.getCachedFile(this.mContext, this.mUrl);
                    if (cachedFile == null || !cachedFile.exists()) {
                        drawable = null;
                    } else {
                        drawable = ResUtil.createFromPathBuffer(cachedFile.getAbsolutePath());
                    }
                    Log.d(TAG, "get drawable from net else file.");
                    return drawable;
                } catch (Exception e2) {
                    Log.w(TAG, e2.toString(), e2);
                    return null;
                }
            }
            drawable = ResUtil.createFromPathBuffer(this.mCacheFile.getAbsolutePath());
            if (drawable == null) {
                this.mCacheFile.delete();
            }
            Log.d(TAG, "get drawable from cacheFile.");
            return drawable;
        }
    }

    static {
        RESUTIL_DEBUG = false;
        IMAGE_VIEWS = Collections.synchronizedMap(new WeakHashMap());
    }

    private static String getMd5FileName(String str) {
        return AesHelper.md5(str);
    }

    public static String getResource(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String canonicalPath;
            long j;
            String str2 = getMd5FileName(str) + ".tmp";
            if (DeviceConfig.isSdCardWrittenable()) {
                canonicalPath = Environment.getExternalStorageDirectory().getCanonicalPath();
                j = EXTERNAL_CACHE_SIZE;
            } else {
                canonicalPath = context.getCacheDir().getCanonicalPath();
                j = INTERNAL_CACHE_SIZE;
            }
            File file = new File(canonicalPath + a.a);
            if (file.exists()) {
                if (dirSize(file.getCanonicalFile()) > j) {
                    new Thread(new AnonymousClass_1(file)).start();
                }
            } else if (!file.mkdirs()) {
                Log.e(TAG, new StringBuilder("Failed to create directory").append(file.getAbsolutePath()).append(". Check permission. Make sure WRITE_EXTERNAL_STORAGE is added in your Manifest.xml").toString());
            }
            File file2 = new File(file, str2);
            try {
                file2.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file2);
                InputStream inputStream = (InputStream) new URL(str).openConnection().getContent();
                byte[] bArr = new byte[4096];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.flush();
                        inputStream.close();
                        fileOutputStream.close();
                        File file3 = new File(file2.getParent(), file2.getName().replace(".tmp", BuildConfig.VERSION_NAME));
                        file2.renameTo(file3);
                        Log.i(TAG, new StringBuilder("download img[").append(str).append("]  to ").append(file3.getCanonicalPath()).toString());
                        return file3.getCanonicalPath();
                    }
                }
            } catch (Exception e) {
                Exception e2 = e;
            }
        } catch (Exception e3) {
            e2 = e3;
            file2 = null;
            Log.i(TAG, e2.getStackTrace().toString() + "\t url:\t" + str);
            if (file2 != null && file2.exists()) {
                file2.deleteOnExit();
            }
            return null;
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
        File file = new File(new File(canonicalPath + a.a), md5FileName);
        return file.exists() ? file : null;
    }

    public static void bindDrawable(Context context, ImageView imageView, String str, boolean z, BindDrawableListener bindDrawableListener, Animation animation) {
        if (imageView != null) {
            IMAGE_VIEWS.put(imageView, str);
            try {
                File cachedFile = getCachedFile(context, str);
                if (cachedFile == null || !cachedFile.exists() || RESUTIL_DEBUG) {
                    new FetchTask(context, imageView, str, BindMode.BIND_FROM_NET, null, z, bindDrawableListener, animation).execute(new Object[0]);
                    return;
                }
                if (bindDrawableListener != null) {
                    bindDrawableListener.onStart(BindMode.BIND_FORM_CACHE);
                }
                Drawable createFromPathBuffer = createFromPathBuffer(cachedFile.getAbsolutePath());
                if (createFromPathBuffer == null) {
                    cachedFile.delete();
                }
                doBind(context, imageView, createFromPathBuffer, z, bindDrawableListener, animation, str);
            } catch (Exception e) {
                Log.e(TAG, BuildConfig.VERSION_NAME, e);
                if (bindDrawableListener != null) {
                    bindDrawableListener.onEnd(UResponse$STATUS.FAIL);
                }
            }
        }
    }

    private static boolean imageViewReused(ImageView imageView, String str) {
        String str2 = (String) IMAGE_VIEWS.get(imageView);
        return (str2 == null || str2.equals(str)) ? false : true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void doBind(android.content.Context r4, android.widget.ImageView r5, android.graphics.drawable.Drawable r6, boolean r7, com.umeng.socialize.utils.ResUtil.BindDrawableListener r8, android.view.animation.Animation r9, java.lang.String r10) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.utils.ResUtil.doBind(android.content.Context, android.widget.ImageView, android.graphics.drawable.Drawable, boolean, com.umeng.socialize.utils.ResUtil$BindDrawableListener, android.view.animation.Animation, java.lang.String):void");
        /*
        r1 = com.umeng.socialize.utils.ResUtil.class;
        monitor-enter(r1);
        if (r6 == 0) goto L_0x0014;
    L_0x0005:
        r6 = (android.graphics.drawable.BitmapDrawable) r6;	 Catch:{ Exception -> 0x0057 }
        r0 = r6.getBitmap();	 Catch:{ Exception -> 0x0057 }
        r0 = getRoundedCornerBitmap(r0);	 Catch:{ Exception -> 0x0057 }
        r6 = new android.graphics.drawable.BitmapDrawable;	 Catch:{ Exception -> 0x0057 }
        r6.<init>(r0);	 Catch:{ Exception -> 0x0057 }
    L_0x0014:
        if (r6 == 0) goto L_0x0018;
    L_0x0016:
        if (r5 != 0) goto L_0x0049;
    L_0x0018:
        if (r8 == 0) goto L_0x001f;
    L_0x001a:
        r0 = com.umeng.socialize.net.utils.UResponse$STATUS.FAIL;	 Catch:{ Exception -> 0x0057 }
        r8.onEnd(r0);	 Catch:{ Exception -> 0x0057 }
    L_0x001f:
        r0 = "ResUtil";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0057 }
        r3 = "bind drawable failed. drawable [";
        r2.<init>(r3);	 Catch:{ Exception -> 0x0057 }
        r2 = r2.append(r6);	 Catch:{ Exception -> 0x0057 }
        r3 = "]  imageView[+";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0057 }
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x0057 }
        r3 = "+]";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0057 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0057 }
        com.umeng.socialize.utils.Log.w(r0, r2);	 Catch:{ Exception -> 0x0057 }
    L_0x0047:
        monitor-exit(r1);
        return;
    L_0x0049:
        r0 = imageViewReused(r5, r10);	 Catch:{ Exception -> 0x0057 }
        if (r0 == 0) goto L_0x006c;
    L_0x004f:
        if (r8 == 0) goto L_0x0047;
    L_0x0051:
        r0 = com.umeng.socialize.net.utils.UResponse$STATUS.FAIL;	 Catch:{ Exception -> 0x0057 }
        r8.onEnd(r0);	 Catch:{ Exception -> 0x0057 }
        goto L_0x0047;
    L_0x0057:
        r0 = move-exception;
        r2 = "ResUtil";
        r3 = "bind failed";
        com.umeng.socialize.utils.Log.e(r2, r3, r0);	 Catch:{ all -> 0x0069 }
        if (r8 == 0) goto L_0x0047;
    L_0x0063:
        r0 = com.umeng.socialize.net.utils.UResponse$STATUS.FAIL;	 Catch:{ all -> 0x0069 }
        r8.onEnd(r0);	 Catch:{ all -> 0x0069 }
        goto L_0x0047;
    L_0x0069:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x006c:
        r0 = 1;
        if (r7 != r0) goto L_0x007f;
    L_0x006f:
        r5.setBackgroundDrawable(r6);	 Catch:{ Exception -> 0x0057 }
    L_0x0072:
        if (r9 == 0) goto L_0x0077;
    L_0x0074:
        r5.startAnimation(r9);	 Catch:{ Exception -> 0x0057 }
    L_0x0077:
        if (r8 == 0) goto L_0x0047;
    L_0x0079:
        r0 = com.umeng.socialize.net.utils.UResponse$STATUS.SUCCESS;	 Catch:{ Exception -> 0x0057 }
        r8.onEnd(r0);	 Catch:{ Exception -> 0x0057 }
        goto L_0x0047;
    L_0x007f:
        r5.setImageDrawable(r6);	 Catch:{ Exception -> 0x0057 }
        goto L_0x0072;
        */
    }

    private static Drawable createFromPathBuffer(String str) {
        try {
            return Drawable.createFromPath(str);
        } catch (OutOfMemoryError e) {
            Log.w(TAG, new StringBuilder("Resutil fetchImage OutOfMemoryError:").append(e.toString()).toString());
            return null;
        }
    }

    private static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            RectF rectF = new RectF(rect);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(-12434878);
            canvas.drawRoundRect(rectF, (float) (bitmap.getWidth() / 6), (float) (bitmap.getHeight() / 6), paint);
            paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            bitmap.recycle();
            return createBitmap;
        } catch (OutOfMemoryError e) {
            Log.w(TAG, "Cant`t create round corner bitmap. [OutOfMemoryError] ");
            return null;
        }
    }
}
