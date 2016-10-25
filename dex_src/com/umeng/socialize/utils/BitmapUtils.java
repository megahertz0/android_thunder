package com.umeng.socialize.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.net.utils.AesHelper;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.R;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;

public class BitmapUtils {
    private static final int CACHE_SIZE = 10;
    public static final int COMPRESS_FLAG = 3145728;
    public static final String FOLDER = "umeng_cache";
    private static final int FREE_SD_SPACE = 40;
    public static int MAX_HEIGHT = 0;
    public static int MAX_WIDTH = 0;
    private static final int MB = 1048576;
    public static String PATH = null;
    private static final String TAG = "BitmapUtils";

    private static class FileLastModifSort implements Comparator<File> {
        private FileLastModifSort() {
        }

        public int compare(File file, File file2) {
            if (file.lastModified() > file2.lastModified()) {
                return 1;
            }
            return file.lastModified() == file2.lastModified() ? 0 : -1;
        }
    }

    static {
        PATH = "/mnt/sdcard/";
        init();
        MAX_WIDTH = 768;
        MAX_HEIGHT = 1024;
    }

    public static void init() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + FOLDER + File.separator;
        } else {
            PATH = Environment.getDataDirectory().getPath() + File.separator + FOLDER + File.separator;
        }
        File file = new File(PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        try {
            remove40PercentCache(PATH);
        } catch (Exception e) {
            Log.d(TAG, new StringBuilder("\u6e05\u9664\u7f13\u5b58\u629b\u51fa\u5f02\u5e38 \uff1a ").append(e.toString()).toString());
        }
        System.gc();
    }

    private static Options getScaleBitmapOptions(String str, int i, int i2) {
        Options options = null;
        Log.d("bitmapOptions", str);
        InputStream bitmapStream = getBitmapStream(str);
        if (bitmapStream != null) {
            options = new Options();
            options.inJustDecodeBounds = true;
            try {
                BitmapFactory.decodeStream(bitmapStream, null, options);
                int ceil = (int) Math.ceil((double) (options.outHeight / i2));
                int ceil2 = (int) Math.ceil((double) (options.outWidth / i));
                if (ceil > 1 && ceil2 > 1) {
                    if (ceil > ceil2) {
                        options.inSampleSize = ceil;
                    } else {
                        options.inSampleSize = ceil2;
                    }
                }
                options.inJustDecodeBounds = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            closeInputStream(bitmapStream);
        }
        return options;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.io.InputStream getBitmapStream(java.lang.String r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.socialize.utils.BitmapUtils.getBitmapStream(java.lang.String):java.io.InputStream");
        /*
        r1 = 0;
        r0 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0036 }
        r2 = new java.io.File;	 Catch:{ Exception -> 0x0036 }
        r3 = getFileName(r6);	 Catch:{ Exception -> 0x0036 }
        r2.<init>(r3);	 Catch:{ Exception -> 0x0036 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x0036 }
    L_0x000f:
        if (r0 == 0) goto L_0x0017;
    L_0x0011:
        r1 = r0.available();	 Catch:{ Exception -> 0x005b }
        if (r1 > 0) goto L_0x0035;
    L_0x0017:
        r1 = new java.net.URL;	 Catch:{ Exception -> 0x005b }
        r1.<init>(r6);	 Catch:{ Exception -> 0x005b }
        r1 = r1.openStream();	 Catch:{ Exception -> 0x005b }
        r0 = getFileName(r6);	 Catch:{ Exception -> 0x005d }
        saveInputStream(r0, r1);	 Catch:{ Exception -> 0x005d }
        r0 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x005d }
        r2 = new java.io.File;	 Catch:{ Exception -> 0x005d }
        r3 = getFileName(r6);	 Catch:{ Exception -> 0x005d }
        r2.<init>(r3);	 Catch:{ Exception -> 0x005d }
        r0.<init>(r2);	 Catch:{ Exception -> 0x005d }
    L_0x0035:
        return r0;
    L_0x0036:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ Exception -> 0x003c }
        r0 = r1;
        goto L_0x000f;
    L_0x003c:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x0040:
        r2 = "BitmapUtil";
        r3 = new java.lang.StringBuilder;
        r4 = "\u8bfb\u53d6\u56fe\u7247\u6d41\u51fa\u9519";
        r3.<init>(r4);
        r1 = r1.toString();
        r1 = r3.append(r1);
        r1 = r1.toString();
        com.umeng.socialize.utils.Log.e(r2, r1);
        goto L_0x0035;
    L_0x005b:
        r1 = move-exception;
        goto L_0x0040;
    L_0x005d:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
        goto L_0x0040;
        */
    }

    private static void saveInputStream(String str, InputStream inputStream) {
        FileNotFoundException e;
        IOException e2;
        Throwable th;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.flush();
                        try {
                            fileOutputStream.close();
                            return;
                        } catch (IOException e3) {
                        }
                    }
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e5) {
                    }
                }
            } catch (IOException e6) {
                e2 = e6;
                e2.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e7) {
                    }
                }
            }
        } catch (Throwable th2) {
            th = th2;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e8) {
                }
            }
            throw th;
        }
    }

    public static Bitmap loadImage(String str, int i, int i2) {
        Bitmap bitmap = null;
        Log.d("loadImageUrl", str);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            InputStream bitmapStream = getBitmapStream(str);
            try {
                bitmap = BitmapFactory.decodeStream(bitmapStream, null, getScaleBitmapOptions(str, i, i2));
                closeInputStream(bitmapStream);
                return bitmap;
            } catch (Exception e) {
                e = e;
            }
        } catch (Exception e2) {
            e = e2;
            bitmapStream = null;
            try {
                Exception e3;
                e3.printStackTrace();
                closeInputStream(bitmapStream);
                return bitmap;
            } catch (Throwable th) {
                Throwable th2 = th;
            }
        } catch (Throwable th3) {
            bitmapStream = null;
            th2 = th3;
            closeInputStream(bitmapStream);
            throw th2;
        }
    }

    public static boolean isFileExist(String str) {
        return !TextUtils.isEmpty(str) && new File(getFileName(str)).getAbsoluteFile().exists();
    }

    public static boolean isNeedScale(String str, int i) {
        File file = new File(getFileName(str));
        return file.exists() && file.length() >= ((long) i);
    }

    public static Bitmap getBitmapFromFile(String str) {
        InputStream bitmapStream = getBitmapStream(str);
        if (bitmapStream == null) {
            return null;
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(bitmapStream, null, null);
        closeInputStream(bitmapStream);
        return decodeStream;
    }

    public static Bitmap getBitmapFromFile(String str, int i, int i2) {
        InputStream bitmapStream = getBitmapStream(str);
        if (bitmapStream == null) {
            return null;
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(bitmapStream, null, getScaleBitmapOptions(str, i, i2));
        closeInputStream(bitmapStream);
        return decodeStream;
    }

    public static void saveBitmap(String str, Bitmap bitmap) {
        try {
            OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(getFileName(str)));
            int i = R.styleable.AppCompatTheme_buttonStyle;
            if (bitmap.getRowBytes() * bitmap.getHeight() > 3145728) {
                i = R.styleable.AppCompatTheme_panelMenuListTheme;
            }
            bitmap.compress(CompressFormat.PNG, i, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void closeInputStream(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
                Log.d(TAG, e.toString());
            }
        }
    }

    public static String getFileName(String str) {
        if (TextUtils.isEmpty(str)) {
            return BuildConfig.VERSION_NAME;
        }
        return (str.startsWith("http://") || str.startsWith("https://")) ? PATH + AesHelper.md5(str) : str;
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream;
        Exception e;
        Throwable th;
        byte[] bArr = null;
        if (bitmap == null || bitmap.isRecycled()) {
            Log.d(TAG, "bitmap2Bytes  ==> bitmap == null or bitmap.isRecycled()");
        } else {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e2) {
                e = e2;
                byteArrayOutputStream = null;
                try {
                    Log.e(TAG, e.toString());
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e3) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw th;
                }
                return bArr;
            } catch (Throwable th3) {
                byteArrayOutputStream = null;
                th = th3;
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e4) {
                    }
                }
                throw th;
            }
            try {
                int rowBytes = (bitmap.getRowBytes() * bitmap.getHeight()) / 1024;
                int i = R.styleable.AppCompatTheme_buttonStyle;
                if (((float) rowBytes) > Config.imageSize) {
                    i = (int) ((Config.imageSize / ((float) rowBytes)) * 100.0f);
                }
                Log.d("BitmapUtil", new StringBuilder("compress quality:").append(i).toString());
                bitmap.compress(CompressFormat.JPEG, i, byteArrayOutputStream);
                bArr = byteArrayOutputStream.toByteArray();
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e5) {
                }
            } catch (Exception e6) {
                e = e6;
                Log.e(TAG, e.toString());
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                return bArr;
            }
        }
        return bArr;
    }

    public static int calculateInSampleSize(Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            i3 /= 2;
            i4 /= 2;
            while (i3 / i5 > i2 && i4 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static Bitmap decodeSampledBitmapFromResource(Resources resources, int i, int i2, int i3) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources, i, options);
    }

    public static Options getBitmapOptions(byte[] bArr) {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        int ceil = (int) Math.ceil((double) (options.outWidth / MAX_WIDTH));
        int ceil2 = (int) Math.ceil((double) (options.outHeight / MAX_HEIGHT));
        if (ceil2 <= 1 || ceil <= 1) {
            if (ceil2 > 2) {
                options.inSampleSize = ceil2;
            } else if (ceil > 2) {
                options.inSampleSize = ceil;
            }
        } else if (ceil2 > ceil) {
            options.inSampleSize = ceil2;
        } else {
            options.inSampleSize = ceil;
        }
        options.inJustDecodeBounds = false;
        return options;
    }

    private static int freeSpaceOnSd() {
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (int) ((((double) statFs.getBlockSize()) * ((double) statFs.getAvailableBlocks())) / 1048576.0d);
    }

    private static void remove40PercentCache(String str) {
        int i = 0;
        File[] listFiles = new File(str).listFiles();
        if (listFiles.length != 0) {
            int i2;
            int i3 = 0;
            for (i2 = 0; i2 < listFiles.length; i2++) {
                i3 = (int) (((long) i3) + listFiles[i2].length());
            }
            if (i3 > 10485760 || 40 > freeSpaceOnSd()) {
                i2 = (int) ((0.4d * ((double) listFiles.length)) + 1.0d);
                Arrays.sort(listFiles, new FileLastModifSort());
                while (i < i2) {
                    listFiles[i].delete();
                    i++;
                }
            }
        }
    }

    public static void cleanCache() {
        init();
    }

    public static byte[] compressBitmap(byte[] bArr, int i) {
        if (bArr != null && bArr.length >= i) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            int i2 = 0;
            int i3 = 1;
            while (i2 == 0 && i3 <= 10) {
                int pow = (int) (Math.pow(0.8d, (double) i3) * 100.0d);
                Log.d(TAG, new StringBuilder("quality = ").append(pow).toString());
                decodeByteArray.compress(CompressFormat.JPEG, pow, byteArrayOutputStream);
                Log.d(TAG, new StringBuilder("WeiXin Thumb Size = ").append(byteArrayOutputStream.toByteArray().length / 1024).append(" KB").toString());
                if (byteArrayOutputStream.size() < i) {
                    i2 = 1;
                } else {
                    byteArrayOutputStream.reset();
                    i3++;
                }
            }
            bArr = byteArrayOutputStream.toByteArray();
            if (!decodeByteArray.isRecycled()) {
                decodeByteArray.recycle();
            }
            if (bArr != null && bArr.length <= 0) {
                Log.e(TAG, "### \u60a8\u7684\u539f\u59cb\u56fe\u7247\u592a\u5927,\u5bfc\u81f4\u7f29\u7565\u56fe\u538b\u7f29\u8fc7\u540e\u8fd8\u5927\u4e8e32KB,\u8bf7\u5c06\u5206\u4eab\u5230\u5fae\u4fe1\u7684\u56fe\u7247\u8fdb\u884c\u9002\u5f53\u7f29\u5c0f.");
            }
        }
        return bArr;
    }

    public static Bitmap createThumb(Bitmap bitmap, float f) {
        if (bitmap == null || bitmap.isRecycled()) {
            return null;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f2 = 1.0f;
        if (width < 200 || height < 200) {
            if (width < height) {
                f2 = f / ((float) width);
            } else {
                f2 = f / ((float) height);
            }
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) (((float) width) * f2), (int) (f2 * ((float) height)), true);
        return createScaledBitmap == null ? bitmap : createScaledBitmap;
    }
}
