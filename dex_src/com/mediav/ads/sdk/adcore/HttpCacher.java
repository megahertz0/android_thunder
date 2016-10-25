package com.mediav.ads.sdk.adcore;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Process;
import com.mediav.ads.sdk.log.MVLog;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.android.agoo.message.MessageService;
import org.json.JSONArray;
import org.json.JSONObject;

public class HttpCacher {
    private static final int MAX_COUNT = Integer.MAX_VALUE;
    private static final int MAX_SIZE = 50000000;
    public static final int TIME_DAY = 86400;
    public static final int TIME_HOUR = 3600;
    private static Map<String, HttpCacher> mInstanceMap;
    private ACacheManager mCache;

    public class ACacheManager {
        private final AtomicInteger cacheCount;
        protected File cacheDir;
        private final AtomicLong cacheSize;
        private final int countLimit;
        private final Map<File, Long> lastUsageDates;
        private final long sizeLimit;

        private ACacheManager(File file, long j, int i) {
            this.lastUsageDates = Collections.synchronizedMap(new HashMap());
            this.cacheDir = file;
            this.sizeLimit = j;
            this.countLimit = i;
            this.cacheSize = new AtomicLong();
            this.cacheCount = new AtomicInteger();
            calculateCacheSizeAndCacheCount();
        }

        private void calculateCacheSizeAndCacheCount() {
            new Thread(new Runnable() {
                public void run() {
                    int i = 0;
                    File[] listFiles = com.mediav.ads.sdk.adcore.HttpCacher.ACacheManager.this.cacheDir.listFiles();
                    if (listFiles != null) {
                        int length = listFiles.length;
                        int i2 = 0;
                        int i3 = 0;
                        while (i < length) {
                            File file = listFiles[i];
                            i3 = (int) (((long) i3) + com.mediav.ads.sdk.adcore.HttpCacher.ACacheManager.this.calculateSize(file));
                            i2++;
                            com.mediav.ads.sdk.adcore.HttpCacher.ACacheManager.this.lastUsageDates.put(file, Long.valueOf(file.lastModified()));
                            i++;
                        }
                        com.mediav.ads.sdk.adcore.HttpCacher.ACacheManager.this.cacheSize.set((long) i3);
                        com.mediav.ads.sdk.adcore.HttpCacher.ACacheManager.this.cacheCount.set(i2);
                    }
                }
            }).start();
        }

        private void put(File file) {
            int i = this.cacheCount.get();
            while (i + 1 > this.countLimit) {
                this.cacheSize.addAndGet(-removeNext());
                i = this.cacheCount.addAndGet(-1);
            }
            this.cacheCount.addAndGet(1);
            long calculateSize = calculateSize(file);
            long j = this.cacheSize.get();
            while (j + calculateSize > this.sizeLimit) {
                j = this.cacheSize.addAndGet(-removeNext());
            }
            this.cacheSize.addAndGet(calculateSize);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.lastUsageDates.put(file, valueOf);
        }

        private File get(String str) {
            File newFile = newFile(str);
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            newFile.setLastModified(valueOf.longValue());
            this.lastUsageDates.put(newFile, valueOf);
            return newFile;
        }

        private File newFile(String str) {
            return new File(this.cacheDir, str.hashCode());
        }

        private boolean remove(String str) {
            return get(str).delete();
        }

        private void clear() {
            this.lastUsageDates.clear();
            this.cacheSize.set(0);
            File[] listFiles = this.cacheDir.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    file.delete();
                }
            }
        }

        private long removeNext() {
            File file = null;
            if (this.lastUsageDates.isEmpty()) {
                return 0;
            }
            Set<Entry> entrySet = this.lastUsageDates.entrySet();
            synchronized (this.lastUsageDates) {
                Long l = null;
                for (Entry entry : entrySet) {
                    if (file == null) {
                        file = (File) entry.getKey();
                        l = (Long) entry.getValue();
                    } else {
                        File file2;
                        Long l2 = (Long) entry.getValue();
                        if (l2.longValue() < l.longValue()) {
                            file2 = (File) entry.getKey();
                        } else {
                            file2 = file;
                            l2 = l;
                        }
                        file = file2;
                        l = l2;
                    }
                }
            }
            if (file == null) {
                return 0;
            }
            long calculateSize = calculateSize(file);
            if (!file.delete()) {
                return calculateSize;
            }
            this.lastUsageDates.remove(file);
            return calculateSize;
        }

        private long calculateSize(File file) {
            return file.length();
        }
    }

    private static class Utils {
        private static final char mSeparator = ' ';

        private Utils() {
        }

        private static boolean isDue(String str) throws Exception {
            return isDue(str.getBytes());
        }

        private static boolean isDue(byte[] bArr) throws Exception {
            String[] dateInfoFromDate = getDateInfoFromDate(bArr);
            if (dateInfoFromDate != null && dateInfoFromDate.length == 2) {
                String str = dateInfoFromDate[0];
                while (str.startsWith(MessageService.MSG_DB_READY_REPORT)) {
                    str = str.substring(1, str.length());
                }
                if (System.currentTimeMillis() > Long.valueOf(str).longValue() + (Long.valueOf(dateInfoFromDate[1]).longValue() * 1000)) {
                    return true;
                }
            }
            return false;
        }

        private static String newStringWithDateInfo(int i, String str) {
            return createDateInfo(i) + str;
        }

        private static byte[] newByteArrayWithDateInfo(int i, byte[] bArr) {
            Object bytes = createDateInfo(i).getBytes();
            Object obj = new Object[(bytes.length + bArr.length)];
            System.arraycopy(bytes, 0, obj, 0, bytes.length);
            System.arraycopy(bArr, 0, obj, bytes.length, bArr.length);
            return obj;
        }

        private static String clearDateInfo(String str) {
            return (str == null || !hasDateInfo(str.getBytes())) ? str : str.substring(str.indexOf(R.styleable.AppCompatTheme_actionModeCutDrawable) + 1, str.length());
        }

        private static byte[] clearDateInfo(byte[] bArr) throws Exception {
            return hasDateInfo(bArr) ? copyOfRange(bArr, indexOf(bArr, mSeparator) + 1, bArr.length) : bArr;
        }

        private static boolean hasDateInfo(byte[] bArr) {
            return bArr != null && bArr.length > 15 && bArr[13] == 45 && indexOf(bArr, mSeparator) > 14;
        }

        private static String[] getDateInfoFromDate(byte[] bArr) throws Exception {
            if (!hasDateInfo(bArr)) {
                return null;
            }
            String str = new String(copyOfRange(bArr, 0, XZBDevice.Upload));
            String str2 = new String(copyOfRange(bArr, XZBDevice.Predownload, indexOf(bArr, mSeparator)));
            return new String[]{str, str2};
        }

        private static int indexOf(byte[] bArr, char c) {
            for (int i = 0; i < bArr.length; i++) {
                if (bArr[i] == c) {
                    return i;
                }
            }
            return -1;
        }

        private static byte[] copyOfRange(byte[] bArr, int i, int i2) throws Exception {
            int i3 = i2 - i;
            if (i3 < 0) {
                throw new IllegalArgumentException(i + " > " + i2);
            }
            Object obj = new Object[i3];
            System.arraycopy(bArr, i, obj, 0, Math.min(bArr.length - i, i3));
            return obj;
        }

        private static String createDateInfo(int i) {
            String str = System.currentTimeMillis();
            while (str.length() < 13) {
                str = new StringBuilder(MessageService.MSG_DB_READY_REPORT).append(str).toString();
            }
            return str + SocializeConstants.OP_DIVIDER_MINUS + i + mSeparator;
        }

        private static byte[] Bitmap2Bytes(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(CompressFormat.PNG, R.styleable.AppCompatTheme_buttonStyle, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        private static Bitmap Bytes2Bimap(byte[] bArr) {
            return bArr.length == 0 ? null : BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }

        private static Bitmap drawable2Bitmap(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            int intrinsicWidth = drawable.getIntrinsicWidth();
            int intrinsicHeight = drawable.getIntrinsicHeight();
            Bitmap createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, drawable.getOpacity() != -1 ? Config.ARGB_8888 : Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            drawable.setBounds(0, 0, intrinsicWidth, intrinsicHeight);
            drawable.draw(canvas);
            return createBitmap;
        }

        private static Drawable bitmap2Drawable(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            new BitmapDrawable(bitmap).setTargetDensity(bitmap.getDensity());
            return new BitmapDrawable(bitmap);
        }
    }

    class xFileOutputStream extends FileOutputStream {
        File file;

        public xFileOutputStream(File file) throws FileNotFoundException {
            super(file);
            this.file = file;
        }

        public void close() throws IOException {
            super.close();
            HttpCacher.this.mCache.put(this.file);
        }
    }

    static {
        mInstanceMap = new HashMap();
    }

    private HttpCacher(File file, long j, int i) throws Exception {
        if (file.exists() || file.mkdirs()) {
            this.mCache = new ACacheManager(file, j, i, null);
            return;
        }
        throw new RuntimeException(new StringBuilder("can't make dirs in ").append(file.getAbsolutePath()).toString());
    }

    public static HttpCacher get(Context context) throws Exception {
        return get(context, "ACache");
    }

    public static HttpCacher get(Context context, String str) throws Exception {
        return get(new File(context.getCacheDir(), str), 50000000, (int) MAX_COUNT);
    }

    public static HttpCacher get(File file) throws Exception {
        return get(file, 50000000, (int) MAX_COUNT);
    }

    public static HttpCacher get(Context context, long j, int i) throws Exception {
        return get(new File(context.getCacheDir(), "ACache"), j, i);
    }

    public static HttpCacher get(File file, long j, int i) throws Exception {
        HttpCacher httpCacher = (HttpCacher) mInstanceMap.get(file.getAbsoluteFile() + myPid());
        if (httpCacher != null) {
            return httpCacher;
        }
        httpCacher = new HttpCacher(file, j, i);
        mInstanceMap.put(file.getAbsolutePath() + myPid(), httpCacher);
        return httpCacher;
    }

    private static String myPid() {
        return new StringBuilder("_").append(Process.myPid()).toString();
    }

    public void put(String str, String str2) {
        IOException e;
        File access$100 = this.mCache.newFile(str);
        BufferedWriter bufferedWriter = null;
        try {
            BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(access$100), 1024);
            try {
                bufferedWriter2.write(str2);
                try {
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                this.mCache.put(access$100);
            } catch (IOException e3) {
                e2 = e3;
                e2.printStackTrace();
                if (bufferedWriter2 != null) {
                    bufferedWriter2.flush();
                    bufferedWriter2.close();
                }
                this.mCache.put(access$100);
            }
        } catch (IOException e4) {
            e2 = e4;
            bufferedWriter2 = null;
            try {
                e2.printStackTrace();
                if (bufferedWriter2 != null) {
                    try {
                        bufferedWriter2.flush();
                        bufferedWriter2.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
                this.mCache.put(access$100);
            } catch (Throwable th) {
                Throwable th2 = th;
                bufferedWriter = bufferedWriter2;
                if (bufferedWriter != null) {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                }
                this.mCache.put(access$100);
                throw th2;
            }
        } catch (Throwable th3) {
            th2 = th3;
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.flush();
                    bufferedWriter.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            this.mCache.put(access$100);
            throw th2;
        }
    }

    public void put(String str, String str2, int i) {
        put(str, Utils.newStringWithDateInfo(i, str2));
    }

    public String getAsString(String str) {
        BufferedReader bufferedReader;
        Exception e;
        Throwable th;
        String str2 = null;
        File access$400 = this.mCache.get(str);
        if (access$400.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(access$400));
                try {
                    String str3 = a.d;
                    while (true) {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        str3 = str3 + readLine;
                    }
                    if (Utils.isDue(str3)) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        remove(str);
                    } else {
                        str2 = Utils.clearDateInfo(str3);
                        try {
                            bufferedReader.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    return str2;
                }
            } catch (Exception e4) {
                e = e4;
                bufferedReader = null;
                try {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
                return str2;
            } catch (Throwable th3) {
                bufferedReader = null;
                th = th3;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2222) {
                        e2222.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return str2;
    }

    public void put(String str, JSONObject jSONObject) {
        put(str, jSONObject.toString());
    }

    public void put(String str, JSONObject jSONObject, int i) {
        put(str, jSONObject.toString(), i);
    }

    public JSONObject getAsJSONObject(String str) {
        try {
            return new JSONObject(getAsString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void put(String str, JSONArray jSONArray) {
        put(str, jSONArray.toString());
    }

    public void put(String str, JSONArray jSONArray, int i) {
        put(str, jSONArray.toString(), i);
    }

    public JSONArray getAsJSONArray(String str) {
        try {
            return new JSONArray(getAsString(str));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void put(String str, byte[] bArr) {
        Exception e;
        File access$100 = this.mCache.newFile(str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(access$100);
            try {
                fileOutputStream.write(bArr);
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                this.mCache.put(access$100);
            } catch (Exception e3) {
                e = e3;
                e.printStackTrace();
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                this.mCache.put(access$100);
            }
        } catch (Exception e4) {
            e = e4;
            fileOutputStream = null;
            try {
                e.printStackTrace();
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
                this.mCache.put(access$100);
            } catch (Throwable th) {
                Throwable th2 = th;
                if (fileOutputStream != null) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                }
                this.mCache.put(access$100);
                throw th2;
            }
        } catch (Throwable th3) {
            th2 = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
            }
            this.mCache.put(access$100);
            throw th2;
        }
    }

    public OutputStream put(String str) throws FileNotFoundException {
        return new xFileOutputStream(this.mCache.newFile(str));
    }

    public InputStream get(String str) throws FileNotFoundException {
        File access$400 = this.mCache.get(str);
        return !access$400.exists() ? null : new FileInputStream(access$400);
    }

    public void put(String str, byte[] bArr, int i) {
        put(str, Utils.newByteArrayWithDateInfo(i, bArr));
    }

    public byte[] getAsBinary(String str) {
        byte[] bArr = null;
        try {
            File access$400 = this.mCache.get(str);
            if (access$400.exists()) {
                randomAccessFile = new RandomAccessFile(access$400, "r");
                try {
                    byte[] bArr2 = new byte[((int) randomAccessFile.length())];
                    randomAccessFile.read(bArr2);
                    if (Utils.isDue(bArr2)) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        remove(str);
                    } else {
                        bArr = Utils.clearDateInfo(bArr2);
                        try {
                            randomAccessFile.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    return bArr;
                }
            }
        } catch (Exception e4) {
            e = e4;
            randomAccessFile = null;
            try {
                RandomAccessFile randomAccessFile;
                Exception e5;
                e5.printStackTrace();
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th2;
            }
            return bArr;
        } catch (Throwable th3) {
            randomAccessFile = null;
            th2 = th3;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e222) {
                    e222.printStackTrace();
                }
            }
            throw th2;
        }
        return bArr;
    }

    public void put(String str, Serializable serializable) {
        put(str, serializable, -1);
    }

    public void put(String str, Serializable serializable, int i) {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(serializable);
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                if (i != -1) {
                    put(str, toByteArray, i);
                } else {
                    put(str, toByteArray);
                }
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    MVLog.e(e.getMessage());
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    Exception e3;
                    e3.printStackTrace();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e4) {
                            MVLog.e(e4.getMessage());
                        }
                    }
                } catch (Throwable th) {
                    Throwable th2 = th;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e5) {
                            MVLog.e(e5.getMessage());
                        }
                    }
                    throw th2;
                }
            }
        } catch (Exception e6) {
            e3 = e6;
            objectOutputStream = null;
            e3.printStackTrace();
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
        } catch (Throwable th3) {
            th2 = th3;
            objectOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            throw th2;
        }
    }

    public Object getAsObject(String str) {
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;
        Exception e;
        Throwable th;
        Object obj = null;
        byte[] asBinary = getAsBinary(str);
        if (asBinary != null) {
            try {
                byteArrayInputStream = new ByteArrayInputStream(asBinary);
                try {
                    objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    try {
                        obj = objectInputStream.readObject();
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        try {
                            objectInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    } catch (Exception e3) {
                        e = e3;
                        e.printStackTrace();
                        if (byteArrayInputStream != null) {
                            byteArrayInputStream.close();
                        }
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        return obj;
                    }
                } catch (Exception e4) {
                    e = e4;
                    objectInputStream = null;
                    e.printStackTrace();
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    return obj;
                } catch (Throwable th2) {
                    objectInputStream = null;
                    th = th2;
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
                objectInputStream = null;
                byteArrayInputStream = null;
                try {
                    e.printStackTrace();
                    if (byteArrayInputStream != null) {
                        try {
                            byteArrayInputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e2222) {
                            e2222.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (byteArrayInputStream != null) {
                        byteArrayInputStream.close();
                    }
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
                return obj;
            } catch (Throwable th22) {
                objectInputStream = null;
                byteArrayInputStream = null;
                th = th22;
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e22222) {
                        e22222.printStackTrace();
                    }
                }
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e222222) {
                        e222222.printStackTrace();
                    }
                }
                throw th;
            }
        }
        return obj;
    }

    public void put(String str, Bitmap bitmap) {
        put(str, Utils.Bitmap2Bytes(bitmap));
    }

    public void put(String str, Bitmap bitmap, int i) {
        put(str, Utils.Bitmap2Bytes(bitmap), i);
    }

    public Bitmap getAsBitmap(String str) {
        return getAsBinary(str) == null ? null : Utils.Bytes2Bimap(getAsBinary(str));
    }

    public void put(String str, Drawable drawable) {
        put(str, Utils.drawable2Bitmap(drawable));
    }

    public void put(String str, Drawable drawable, int i) {
        put(str, Utils.drawable2Bitmap(drawable), i);
    }

    public Drawable getAsDrawable(String str) {
        return getAsBinary(str) == null ? null : Utils.bitmap2Drawable(Utils.Bytes2Bimap(getAsBinary(str)));
    }

    public File file(String str) {
        File access$100 = this.mCache.newFile(str);
        return access$100.exists() ? access$100 : null;
    }

    public boolean remove(String str) {
        return this.mCache.remove(str);
    }

    public void clear() {
        this.mCache.clear();
    }
}
