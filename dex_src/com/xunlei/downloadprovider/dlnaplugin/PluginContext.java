package com.xunlei.downloadprovider.dlnaplugin;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.view.ContextThemeWrapper;

class PluginContext extends ContextThemeWrapper {
    private AssetManager mAsset;
    private ClassLoader mClassLoader;
    private Resources mResources;
    private Theme mTheme;
    private int mThemeResId;

    private AssetManager getSelfAssets(String str) {
        AssetManager assetManager;
        try {
            assetManager = (AssetManager) AssetManager.class.newInstance();
            try {
                AssetManager.class.getDeclaredMethod("addAssetPath", new Class[]{String.class}).invoke(assetManager, new Object[]{str});
            } catch (Throwable th) {
                Throwable th2 = th;
                th2.printStackTrace();
                return assetManager;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            assetManager = null;
            th2 = th4;
            th2.printStackTrace();
            return assetManager;
        }
        return assetManager;
    }

    private Resources getSelfRes(Context context, AssetManager assetManager) {
        return new Resources(assetManager, context.getResources().getDisplayMetrics(), context.getResources().getConfiguration());
    }

    private Theme getSelfTheme(Resources resources) {
        Theme newTheme = resources.newTheme();
        this.mThemeResId = getInnerRIdValue("com.android.internal.R.style.Theme");
        newTheme.applyStyle(this.mThemeResId, true);
        return newTheme;
    }

    private int getInnerRIdValue(String str) {
        try {
            String substring = str.substring(0, str.indexOf(".R.") + 2);
            int lastIndexOf = str.lastIndexOf(".");
            String substring2 = str.substring(lastIndexOf + 1, str.length());
            String substring3 = str.substring(0, lastIndexOf);
            return Class.forName(substring + "$" + substring3.substring(substring3.lastIndexOf(".") + 1, substring3.length())).getDeclaredField(substring2).getInt(null);
        } catch (Throwable th) {
            th.printStackTrace();
            return -1;
        }
    }

    public Resources getRes() {
        return this.mResources;
    }

    public PluginContext(Context context, int i, String str, ClassLoader classLoader) {
        this(context, i, str, classLoader, null);
    }

    public PluginContext(Context context, int i, String str, ClassLoader classLoader, Resources resources) {
        super(context, i);
        this.mAsset = null;
        this.mResources = null;
        this.mTheme = null;
        this.mClassLoader = classLoader;
        if (resources != null) {
            this.mAsset = resources.getAssets();
            this.mResources = resources;
        } else {
            this.mAsset = getSelfAssets(str);
            this.mResources = getSelfRes(context, this.mAsset);
        }
        this.mTheme = getSelfTheme(this.mResources);
    }

    public Resources getResources() {
        return this.mResources;
    }

    public AssetManager getAssets() {
        return this.mAsset;
    }

    public Theme getTheme() {
        return this.mTheme;
    }

    public ClassLoader getClassLoader() {
        return this.mClassLoader != null ? this.mClassLoader : super.getClassLoader();
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.mClassLoader = classLoader;
    }
}
