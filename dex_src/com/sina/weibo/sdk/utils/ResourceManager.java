package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sina.weibo.sdk.component.GameManager;
import com.umeng.a;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.http.util.EncodingUtils;

public class ResourceManager {
    private static final String DRAWABLE = "drawable";
    private static final String DRAWABLE_HDPI = "drawable-hdpi";
    private static final String DRAWABLE_LDPI = "drawable-ldpi";
    private static final String DRAWABLE_MDPI = "drawable-mdpi";
    private static final String DRAWABLE_XHDPI = "drawable-xhdpi";
    private static final String DRAWABLE_XXHDPI = "drawable-xxhdpi";
    private static final String[] PRE_INSTALL_DRAWBLE_PATHS;
    private static final String TAG;

    static {
        TAG = ResourceManager.class.getName();
        PRE_INSTALL_DRAWBLE_PATHS = new String[]{DRAWABLE_XXHDPI, DRAWABLE_XHDPI, DRAWABLE_HDPI, DRAWABLE_MDPI, DRAWABLE_LDPI, DRAWABLE};
    }

    public static String getString(Context context, String str, String str2, String str3) {
        Locale language = getLanguage();
        if (Locale.SIMPLIFIED_CHINESE.equals(language)) {
            return str2;
        }
        return Locale.TRADITIONAL_CHINESE.equals(language) ? str3 : str;
    }

    public static Drawable getDrawable(Context context, String str) {
        return getDrawableFromAssert(context, getAppropriatePathOfDrawable(context, str), false);
    }

    public static Drawable getNinePatchDrawable(Context context, String str) {
        return getDrawableFromAssert(context, getAppropriatePathOfDrawable(context, str), true);
    }

    public static Locale getLanguage() {
        Locale locale = Locale.getDefault();
        return (Locale.SIMPLIFIED_CHINESE.equals(locale) || Locale.TRADITIONAL_CHINESE.equals(locale)) ? locale : Locale.ENGLISH;
    }

    private static String getAppropriatePathOfDrawable(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e(TAG, "id is NOT correct!");
            return null;
        }
        String currentDpiFolder = getCurrentDpiFolder(context);
        LogUtil.d(TAG, "find Appropriate path...");
        int i = 0;
        int i2 = -1;
        int i3 = -1;
        while (i < PRE_INSTALL_DRAWBLE_PATHS.length) {
            if (PRE_INSTALL_DRAWBLE_PATHS[i].equals(currentDpiFolder)) {
                i2 = i;
            }
            String toString = new StringBuilder(String.valueOf(PRE_INSTALL_DRAWBLE_PATHS[i])).append("/").append(str).toString();
            if (isFileExisted(context, toString)) {
                if (i2 != i) {
                    if (i2 >= 0) {
                        break;
                    }
                    i3 = i;
                } else {
                    return toString;
                }
            }
            i++;
        }
        i = -1;
        if (i3 <= 0 || i <= 0) {
            if (i3 <= 0 || i >= 0) {
                if (i3 >= 0 || i <= 0) {
                    LogUtil.e(TAG, "Not find the appropriate path for drawable");
                    i3 = -1;
                } else {
                    i3 = i;
                }
            }
        } else if (Math.abs(i2 - i) <= Math.abs(i2 - i3)) {
            i3 = i;
        }
        if (i3 >= 0) {
            return new StringBuilder(String.valueOf(PRE_INSTALL_DRAWBLE_PATHS[i3])).append("/").append(str).toString();
        }
        LogUtil.e(TAG, "Not find the appropriate path for drawable");
        return null;
    }

    private static Drawable getDrawableFromAssert(Context context, String str, boolean z) {
        Drawable ninePatchDrawable;
        IOException e;
        InputStream inputStream;
        Throwable th;
        try {
            InputStream open = context.getAssets().open(str);
            if (open != null) {
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(open);
                    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                    if (z) {
                        ninePatchDrawable = new NinePatchDrawable(new Resources(context.getAssets(), displayMetrics, context.getResources().getConfiguration()), decodeStream, decodeStream.getNinePatchChunk(), new Rect(0, 0, 0, 0), null);
                    } else {
                        decodeStream.setDensity(displayMetrics.densityDpi);
                        ninePatchDrawable = new BitmapDrawable(context.getResources(), decodeStream);
                    }
                } catch (IOException e2) {
                    e = e2;
                    inputStream = open;
                    try {
                        e.printStackTrace();
                        if (inputStream != null) {
                            return null;
                        }
                        try {
                            inputStream.close();
                            return null;
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            return null;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        open = inputStream;
                        if (open != null) {
                            open.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
            ninePatchDrawable = null;
            if (open == null) {
                return ninePatchDrawable;
            }
            try {
                open.close();
                return ninePatchDrawable;
            } catch (IOException e42) {
                e42.printStackTrace();
                return ninePatchDrawable;
            }
        } catch (IOException e5) {
            e3 = e5;
            inputStream = null;
            e3.printStackTrace();
            if (inputStream != null) {
                return null;
            }
            inputStream.close();
            return null;
        } catch (Throwable th4) {
            th = th4;
            open = null;
            if (open != null) {
                open.close();
            }
            throw th;
        }
    }

    private static boolean isFileExisted(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            return false;
        }
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(str);
            LogUtil.d(TAG, new StringBuilder("file [").append(str).append("] existed").toString());
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return true;
        } catch (IOException e2) {
            try {
                LogUtil.d(TAG, new StringBuilder("file [").append(str).append("] NOT existed").toString());
                if (inputStream == null) {
                    return false;
                }
                try {
                    inputStream.close();
                    return false;
                } catch (IOException e3) {
                    e3.printStackTrace();
                    return false;
                }
            } catch (Throwable th) {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e32) {
                        e32.printStackTrace();
                    }
                }
            }
        }
    }

    private static String getCurrentDpiFolder(Context context) {
        int i = context.getResources().getDisplayMetrics().densityDpi;
        if (i <= 120) {
            return DRAWABLE_LDPI;
        }
        if (i > 120 && i <= 160) {
            return DRAWABLE_MDPI;
        }
        if (i <= 160 || i > 240) {
            return (i <= 240 || i > 320) ? DRAWABLE_XXHDPI : DRAWABLE_XHDPI;
        } else {
            return DRAWABLE_HDPI;
        }
    }

    private static View extractView(Context context, String str, ViewGroup viewGroup) throws Exception {
        return ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(context.getAssets().openXmlResourceParser(str), viewGroup);
    }

    private static Drawable extractDrawable(Context context, String str) throws Exception {
        InputStream open = context.getAssets().open(str);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        TypedValue typedValue = new TypedValue();
        typedValue.density = displayMetrics.densityDpi;
        Drawable createFromResourceStream = Drawable.createFromResourceStream(context.getResources(), typedValue, open, str);
        open.close();
        return createFromResourceStream;
    }

    public static int dp2px(Context context, int i) {
        return (int) (((double) (context.getResources().getDisplayMetrics().density * ((float) i))) + 0.5d);
    }

    public static ColorStateList createColorStateList(int i, int i2) {
        int[] iArr = new int[]{i2, i2, i2, i};
        int[][] iArr2 = new int[4][];
        iArr2[0] = new int[]{16842919};
        iArr2[1] = new int[]{16842913};
        iArr2[2] = new int[]{16842908};
        iArr2[3] = StateSet.WILD_CARD;
        return new ColorStateList(iArr2, iArr);
    }

    public static StateListDrawable createStateListDrawable(Context context, String str, String str2) {
        Drawable ninePatchDrawable;
        Drawable ninePatchDrawable2;
        if (str.indexOf(".9") >= 0) {
            ninePatchDrawable = getNinePatchDrawable(context, str);
        } else {
            ninePatchDrawable = getDrawable(context, str);
        }
        if (str2.indexOf(".9") >= 0) {
            ninePatchDrawable2 = getNinePatchDrawable(context, str2);
        } else {
            ninePatchDrawable2 = getDrawable(context, str2);
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, ninePatchDrawable2);
        stateListDrawable.addState(new int[]{16842913}, ninePatchDrawable2);
        stateListDrawable.addState(new int[]{16842908}, ninePatchDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, ninePatchDrawable);
        return stateListDrawable;
    }

    public static StateListDrawable createStateListDrawable(Context context, String str, String str2, String str3) {
        Drawable ninePatchDrawable;
        Drawable ninePatchDrawable2;
        Drawable ninePatchDrawable3;
        if (str.indexOf(".9") >= 0) {
            ninePatchDrawable = getNinePatchDrawable(context, str);
        } else {
            ninePatchDrawable = getDrawable(context, str);
        }
        if (str3.indexOf(".9") >= 0) {
            ninePatchDrawable2 = getNinePatchDrawable(context, str3);
        } else {
            ninePatchDrawable2 = getDrawable(context, str3);
        }
        if (str2.indexOf(".9") >= 0) {
            ninePatchDrawable3 = getNinePatchDrawable(context, str2);
        } else {
            ninePatchDrawable3 = getDrawable(context, str2);
        }
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, ninePatchDrawable3);
        stateListDrawable.addState(new int[]{16842913}, ninePatchDrawable3);
        stateListDrawable.addState(new int[]{16842908}, ninePatchDrawable3);
        stateListDrawable.addState(new int[]{16842766}, ninePatchDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, ninePatchDrawable);
        return stateListDrawable;
    }

    public static String readCountryFromAsset(Context context, String str) {
        String str2 = a.d;
        try {
            InputStream open = context.getAssets().open(str);
            if (open == null) {
                return str2;
            }
            DataInputStream dataInputStream = new DataInputStream(open);
            byte[] bArr = new byte[dataInputStream.available()];
            dataInputStream.read(bArr);
            str2 = EncodingUtils.getString(bArr, GameManager.DEFAULT_CHARSET);
            open.close();
            return str2;
        } catch (IOException e) {
            e.printStackTrace();
            return str2;
        }
    }
}
