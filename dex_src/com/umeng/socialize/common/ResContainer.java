package com.umeng.socialize.common;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.umeng.a;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public final class ResContainer {
    private static ResContainer R;
    private static String mPackageName;
    private Context context;
    private Map<String, SocializeResource> mResources;
    private Map<String, Integer> map;

    static {
        R = null;
        mPackageName = a.d;
    }

    private ResContainer(Context context) {
        this.map = new HashMap();
        this.context = null;
        this.context = context.getApplicationContext();
    }

    public static synchronized ResContainer get(Context context) {
        ResContainer resContainer;
        synchronized (ResContainer.class) {
            if (R == null) {
                R = new ResContainer(context);
            }
            resContainer = R;
        }
        return resContainer;
    }

    public final int layout(String str) {
        return getResourceId(this.context, "layout", str);
    }

    public final int id(String str) {
        return getResourceId(this.context, SocializeConstants.WEIBO_ID, str);
    }

    public final int drawable(String str) {
        return getResourceId(this.context, "drawable", str);
    }

    public final int style(String str) {
        return getResourceId(this.context, "style", str);
    }

    public final int string(String str) {
        return getResourceId(this.context, "string", str);
    }

    public final int color(String str) {
        return getResourceId(this.context, "color", str);
    }

    public final int dimen(String str) {
        return getResourceId(this.context, "dimen", str);
    }

    public final int raw(String str) {
        return getResourceId(this.context, "raw", str);
    }

    public final int anim(String str) {
        return getResourceId(this.context, "anim", str);
    }

    public final int styleable(String str) {
        return getResourceId(this.context, "styleable", str);
    }

    public ResContainer(Context context, Map<String, SocializeResource> map) {
        this.map = new HashMap();
        this.context = null;
        this.mResources = map;
        this.context = context;
    }

    public static int getResourceId(Context context, String str, String str2) {
        Resources resources = context.getResources();
        if (TextUtils.isEmpty(mPackageName)) {
            mPackageName = context.getPackageName();
        }
        int identifier = resources.getIdentifier(str2, str, mPackageName);
        if (identifier > 0) {
            return identifier;
        }
        throw new RuntimeException(new StringBuilder("\u83b7\u53d6\u8d44\u6e90ID\u5931\u8d25:(packageName=").append(mPackageName).append(" type=").append(str).append(" name=").append(str2).toString());
    }

    public static String getString(Context context, String str) {
        return context.getString(getResourceId(context, "string", str));
    }

    public final synchronized Map<String, SocializeResource> batch() {
        Map<String, SocializeResource> map;
        if (this.mResources == null) {
            map = this.mResources;
        } else {
            for (String str : this.mResources.keySet()) {
                SocializeResource socializeResource = (SocializeResource) this.mResources.get(str);
                socializeResource.mId = getResourceId(this.context, socializeResource.mType, socializeResource.mName);
                socializeResource.mIsCompleted = true;
            }
            map = this.mResources;
        }
        return map;
    }

    public static int[] getStyleableArrts(Context context, String str) {
        return getResourceDeclareStyleableIntArray(context, str);
    }

    private static final int[] getResourceDeclareStyleableIntArray(Context context, String str) {
        try {
            for (Field field : Class.forName(context.getPackageName() + ".R$styleable").getFields()) {
                if (field.getName().equals(str)) {
                    return (int[]) field.get(null);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return null;
    }
}
