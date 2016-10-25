package android.support.v7.widget;

import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.support.v7.a.a.a;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.reflect.Field;
import org.android.spdy.SpdyAgent;

// compiled from: DrawableUtils.java
public final class ao {
    public static final Rect a;
    private static Class<?> b;

    static {
        a = new Rect();
        if (VERSION.SDK_INT >= 18) {
            try {
                b = Class.forName("android.graphics.Insets");
            } catch (ClassNotFoundException e) {
            }
        }
    }

    public static Rect a(Drawable drawable) {
        if (b != null) {
            try {
                Drawable unwrap = DrawableCompat.unwrap(drawable);
                Object invoke = unwrap.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(unwrap, new Object[0]);
                if (invoke != null) {
                    Rect rect = new Rect();
                    Field[] fields = b.getFields();
                    int length = fields.length;
                    for (int i = 0; i < length; i++) {
                        Field field = fields[i];
                        String name = field.getName();
                        Object obj = -1;
                        switch (name.hashCode()) {
                            case -1383228885:
                                if (name.equals("bottom")) {
                                    obj = XZBDevice.DOWNLOAD_LIST_FAILED;
                                }
                                break;
                            case 115029:
                                if (name.equals("top")) {
                                    obj = 1;
                                }
                                break;
                            case 3317767:
                                if (name.equals("left")) {
                                    obj = null;
                                }
                                break;
                            case 108511772:
                                if (name.equals("right")) {
                                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                                }
                                break;
                        }
                        switch (obj) {
                            case SpdyAgent.ACCS_TEST_SERVER:
                                rect.left = field.getInt(invoke);
                                break;
                            case SpdyAgent.ACCS_ONLINE_SERVER:
                                rect.top = field.getInt(invoke);
                                break;
                            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                                rect.right = field.getInt(invoke);
                                break;
                            case XZBDevice.DOWNLOAD_LIST_FAILED:
                                rect.bottom = field.getInt(invoke);
                                break;
                            default:
                                break;
                        }
                    }
                    return rect;
                }
            } catch (Exception e) {
            }
        }
        return a;
    }

    static void b(Drawable drawable) {
        if (VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName())) {
            int[] state = drawable.getState();
            if (state == null || state.length == 0) {
                drawable.setState(ci.e);
            } else {
                drawable.setState(ci.h);
            }
            drawable.setState(state);
        }
    }

    public static boolean c(Drawable drawable) {
        Drawable drawable2 = drawable;
        while (!(drawable2 instanceof LayerDrawable)) {
            if (drawable2 instanceof InsetDrawable) {
                return VERSION.SDK_INT >= 14;
            } else {
                if (drawable2 instanceof StateListDrawable) {
                    return VERSION.SDK_INT >= 8;
                } else {
                    if (drawable2 instanceof GradientDrawable) {
                        return VERSION.SDK_INT >= 14;
                    } else {
                        if (drawable2 instanceof DrawableContainer) {
                            ConstantState constantState = drawable2.getConstantState();
                            if (constantState instanceof DrawableContainerState) {
                                Drawable[] children = ((DrawableContainerState) constantState).getChildren();
                                int length = children.length;
                                for (int i = 0; i < length; i++) {
                                    if (!c(children[i])) {
                                        return false;
                                    }
                                }
                            }
                        } else if (drawable2 instanceof DrawableWrapper) {
                            drawable2 = ((DrawableWrapper) drawable2).getWrappedDrawable();
                        } else if (drawable2 instanceof a) {
                            drawable2 = ((a) drawable2).m;
                        } else if (drawable2 instanceof ScaleDrawable) {
                            drawable2 = ((ScaleDrawable) drawable2).getDrawable();
                        }
                        return true;
                    }
                }
            }
        }
        return VERSION.SDK_INT >= 16;
    }

    static Mode a(int i) {
        switch (i) {
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                return Mode.SRC_OVER;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                return Mode.SRC_IN;
            case XZBDevice.Pause:
                return Mode.SRC_ATOP;
            case XZBDevice.Predownload:
                return Mode.MULTIPLY;
            case XZBDevice.Delete:
                return Mode.SCREEN;
            case R.styleable.Toolbar_titleMarginBottom:
                return VERSION.SDK_INT >= 11 ? Mode.valueOf("ADD") : null;
            default:
                return null;
        }
    }
}
