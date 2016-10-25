package android.support.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.alipay.sdk.util.h;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

// compiled from: MultiDex.java
public final class a {
    private static final String a;
    private static final Set<String> b;
    private static final boolean c;

    // compiled from: MultiDex.java
    private static final class a {
        static void a(ClassLoader classLoader, List<File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException {
            int size = list.size();
            Field a = a.b(classLoader, "path");
            StringBuilder stringBuilder = new StringBuilder((String) a.get(classLoader));
            Object[] objArr = new Object[size];
            Object[] objArr2 = new Object[size];
            Object[] objArr3 = new Object[size];
            Object[] objArr4 = new Object[size];
            ListIterator listIterator = list.listIterator();
            while (listIterator.hasNext()) {
                File file = (File) listIterator.next();
                String absolutePath = file.getAbsolutePath();
                stringBuilder.append(':').append(absolutePath);
                int previousIndex = listIterator.previousIndex();
                objArr[previousIndex] = absolutePath;
                objArr2[previousIndex] = file;
                objArr3[previousIndex] = new ZipFile(file);
                objArr4[previousIndex] = DexFile.loadDex(absolutePath, absolutePath + ".dex", 0);
            }
            a.set(classLoader, stringBuilder.toString());
            a.a((Object) classLoader, "mPaths", objArr);
            a.a((Object) classLoader, "mFiles", objArr2);
            a.a((Object) classLoader, "mZips", objArr3);
            a.a((Object) classLoader, "mDexs", objArr4);
        }
    }

    static /* synthetic */ void a(Object obj, String str, Object[] objArr) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field b = b(obj, str);
        Object[] objArr2 = (Object[]) b.get(obj);
        Object[] objArr3 = (Object[]) Array.newInstance(objArr2.getClass().getComponentType(), objArr2.length + objArr.length);
        System.arraycopy(objArr2, 0, objArr3, 0, objArr2.length);
        System.arraycopy(objArr, 0, objArr3, objArr2.length, objArr.length);
        b.set(obj, objArr3);
    }

    static {
        a = new StringBuilder("code_cache").append(File.separator).append("secondary-dexes").toString();
        b = new HashSet();
        c = a(System.getProperty("java.vm.version"));
    }

    public static void a(Context context) {
        if (!c) {
            if (VERSION.SDK_INT < 4) {
                throw new RuntimeException(new StringBuilder("Multi dex installation failed. SDK ").append(VERSION.SDK_INT).append(" is unsupported. Min SDK version is 4.").toString());
            }
            try {
                ApplicationInfo b = b(context);
                if (b != null) {
                    synchronized (b) {
                        String str = b.sourceDir;
                        if (b.contains(str)) {
                            return;
                        }
                        b.add(str);
                        if (VERSION.SDK_INT > 20) {
                            new StringBuilder("MultiDex is not guaranteed to work in SDK version ").append(VERSION.SDK_INT).append(": SDK version higher than 20 should be backed by runtime with built-in multidex capabilty but it's not the case here: java.vm.version=\"").append(System.getProperty("java.vm.version")).append(h.f);
                        }
                        try {
                            ClassLoader classLoader = context.getClassLoader();
                            if (classLoader == null) {
                                return;
                            }
                            try {
                                c(context);
                            } catch (Throwable th) {
                            }
                            File file = new File(b.dataDir, a);
                            List a = b.a(context, b, file, false);
                            if (a(a)) {
                                a(classLoader, file, a);
                            } else {
                                List a2 = b.a(context, b, file, true);
                                if (a(a2)) {
                                    a(classLoader, file, a2);
                                } else {
                                    throw new RuntimeException("Zip files were not valid.");
                                }
                            }
                        } catch (RuntimeException e) {
                        }
                    }
                }
            } catch (Exception e2) {
                throw new RuntimeException(new StringBuilder("Multi dex installation failed (").append(e2.getMessage()).append(").").toString());
            }
        }
    }

    private static ApplicationInfo b(Context context) throws NameNotFoundException {
        try {
            PackageManager packageManager = context.getPackageManager();
            String packageName = context.getPackageName();
            return (packageManager == null || packageName == null) ? null : packageManager.getApplicationInfo(packageName, AccessibilityNodeInfoCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        } catch (RuntimeException e) {
            return null;
        }
    }

    private static boolean a(String str) {
        boolean z = false;
        if (str != null) {
            Matcher matcher = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(str);
            if (matcher.matches()) {
                try {
                    int parseInt = Integer.parseInt(matcher.group(1));
                    int parseInt2 = Integer.parseInt(matcher.group(XZBDevice.DOWNLOAD_LIST_RECYCLE));
                    if (parseInt > 2 || (parseInt == 2 && parseInt2 > 0)) {
                        z = true;
                    }
                } catch (NumberFormatException e) {
                }
            }
        }
        new StringBuilder("VM with version ").append(str).append(z ? " has multidex support" : " does not have multidex support");
        return z;
    }

    private static void a(ClassLoader classLoader, File file, List<File> list) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException {
        if (!list.isEmpty()) {
            Object obj;
            ArrayList arrayList;
            if (VERSION.SDK_INT >= 19) {
                obj = b(classLoader, "pathList").get(classLoader);
                ArrayList arrayList2 = new ArrayList();
                arrayList = new ArrayList(list);
                a(obj, "dexElements", (Object[]) a(obj, "makeDexElements", ArrayList.class, File.class, ArrayList.class).invoke(obj, new Object[]{arrayList, file, arrayList2}));
                if (arrayList2.size() > 0) {
                    Object obj2;
                    Iterator it = arrayList2.iterator();
                    while (it.hasNext()) {
                        it.next();
                    }
                    Field b = b(classLoader, "dexElementsSuppressedExceptions");
                    IOException[] iOExceptionArr = (IOException[]) b.get(classLoader);
                    if (iOExceptionArr == null) {
                        iOExceptionArr = (IOException[]) arrayList2.toArray(new IOException[arrayList2.size()]);
                    } else {
                        obj = new Object[(arrayList2.size() + iOExceptionArr.length)];
                        arrayList2.toArray(obj);
                        System.arraycopy(iOExceptionArr, 0, obj, arrayList2.size(), iOExceptionArr.length);
                        obj2 = obj;
                    }
                    b.set(classLoader, obj2);
                }
            } else if (VERSION.SDK_INT >= 14) {
                obj = b(classLoader, "pathList").get(classLoader);
                arrayList = new ArrayList(list);
                a(obj, "dexElements", (Object[]) a(obj, "makeDexElements", ArrayList.class, File.class).invoke(obj, new Object[]{arrayList, file}));
            } else {
                a.a(classLoader, list);
            }
        }
    }

    private static boolean a(List<File> list) {
        for (File file : list) {
            if (!b.a(file)) {
                return false;
            }
        }
        return true;
    }

    private static Field b(Object obj, String str) throws NoSuchFieldException {
        for (Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException e) {
            }
        }
        throw new NoSuchFieldException(new StringBuilder("Field ").append(str).append(" not found in ").append(obj.getClass()).toString());
    }

    private static Method a(Object obj, String str, Class<?>... clsArr) throws NoSuchMethodException {
        for (Class cls = obj.getClass(); cls != null; cls = cls.getSuperclass()) {
            try {
                Method declaredMethod = cls.getDeclaredMethod(str, clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException e) {
            }
        }
        throw new NoSuchMethodException(new StringBuilder("Method ").append(str).append(" with parameters ").append(Arrays.asList(clsArr)).append(" not found in ").append(obj.getClass()).toString());
    }

    private static void c(Context context) throws Exception {
        File file = new File(context.getFilesDir(), "secondary-dexes");
        if (file.isDirectory()) {
            new StringBuilder("Clearing old secondary dex dir (").append(file.getPath()).append(").");
            File[] listFiles = file.listFiles();
            if (listFiles == null) {
                new StringBuilder("Failed to list secondary dex dir content (").append(file.getPath()).append(").");
                return;
            }
            for (File file2 : listFiles) {
                new StringBuilder("Trying to delete old file ").append(file2.getPath()).append(" of size ").append(file2.length());
                if (file2.delete()) {
                    new StringBuilder("Deleted old file ").append(file2.getPath());
                } else {
                    new StringBuilder("Failed to delete old file ").append(file2.getPath());
                }
            }
            if (file.delete()) {
                new StringBuilder("Deleted old secondary dex dir ").append(file.getPath());
            } else {
                new StringBuilder("Failed to delete secondary dex dir ").append(file.getPath());
            }
        }
    }
}
