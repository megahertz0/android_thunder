package com.xunlei.downloadprovider.dlnaplugin;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import com.xunlei.tdlive.R;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashSet;

public class ApkFileParser {
    public static Signature[] getSignaure(String str) {
        try {
            Class forName = Class.forName("android.content.pm.PackageParser");
            Class forName2 = Class.forName("android.content.pm.PackageParser$Package");
            Constructor constructor = forName.getConstructor(new Class[]{String.class});
            Method method = forName.getMethod("parsePackage", new Class[]{File.class, String.class, DisplayMetrics.class, Integer.TYPE});
            Method method2 = forName.getMethod("collectCertificates", new Class[]{forName2, Integer.TYPE});
            Field field = forName2.getField("mSignatures");
            Object newInstance = constructor.newInstance(new Object[]{str});
            new DisplayMetrics().setToDefaults();
            Object invoke = method.invoke(newInstance, new Object[]{new File(str), str, r4, Integer.valueOf(0)});
            if (invoke == null) {
                return null;
            }
            method2.invoke(newInstance, new Object[]{invoke, Integer.valueOf(R.styleable.AppCompatTheme_imageButtonStyle)});
            Object obj = field.get(invoke);
            if (obj != null && (obj instanceof Signature[])) {
                return (Signature[]) obj;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isSignaturesSame(Signature[] signatureArr, Signature[] signatureArr2) {
        if (signatureArr == null || signatureArr2 == null) {
            return true;
        }
        int i;
        HashSet hashSet = new HashSet();
        int length = signatureArr.length;
        for (i = 0; i < length; i++) {
            hashSet.add(signatureArr[i]);
        }
        HashSet hashSet2 = new HashSet();
        int length2 = signatureArr2.length;
        for (i = 0; i < length2; i++) {
            hashSet2.add(signatureArr2[i]);
        }
        return hashSet.equals(hashSet2);
    }

    public static PackageInfo getPackageInfo(Context context, String str, int i) {
        try {
            return context.getPackageManager().getPackageArchiveInfo(str, i);
        } catch (Exception e) {
            return null;
        }
    }

    public static Drawable getAPKIcon(Context context, String str) {
        try {
            PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
            if (packageArchiveInfo == null || packageArchiveInfo.applicationInfo == null) {
                return null;
            }
            ApplicationInfo applicationInfo = packageArchiveInfo.applicationInfo;
            Class forName = Class.forName("android.content.res.AssetManager");
            AssetManager assetManager = (AssetManager) forName.getConstructor(null).newInstance(null);
            forName.getDeclaredMethod("addAssetPath", new Class[]{String.class}).invoke(assetManager, new Object[]{str});
            DisplayMetrics displayMetrics = new DisplayMetrics();
            displayMetrics.setToDefaults();
            Resources resources = new Resources(assetManager, displayMetrics, context.getResources().getConfiguration());
            if (applicationInfo.icon != 0) {
                return resources.getDrawable(applicationInfo.icon);
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static boolean isApkFileBroken(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, R.styleable.AppCompatTheme_imageButtonStyle);
        return packageArchiveInfo == null || packageArchiveInfo.applicationInfo == null;
    }
}
