package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.LayerDrawable;
import android.os.Build.VERSION;
import android.support.a.a.g;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.LongSparseArray;
import android.support.v4.util.LruCache;
import android.support.v7.appcompat.R;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.util.Xml;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// compiled from: AppCompatDrawableManager.java
public final class r {
    private static final Mode a;
    private static r b;
    private static final b c;
    private static final int[] d;
    private static final int[] e;
    private static final int[] f;
    private static final int[] g;
    private static final int[] h;
    private static final int[] i;
    private WeakHashMap<Context, SparseArray<ColorStateList>> j;
    private ArrayMap<String, c> k;
    private SparseArray<String> l;
    private final Object m;
    private final WeakHashMap<Context, LongSparseArray<WeakReference<ConstantState>>> n;
    private TypedValue o;

    // compiled from: AppCompatDrawableManager.java
    private static interface c {
        Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme);
    }

    // compiled from: AppCompatDrawableManager.java
    private static class a implements c {
        private a() {
        }

        public final Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) {
            try {
                return android.support.a.a.b.a(context, context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                return null;
            }
        }
    }

    // compiled from: AppCompatDrawableManager.java
    private static class b extends LruCache<Integer, PorterDuffColorFilter> {
        public b() {
            super(6);
        }

        static int a(int i, Mode mode) {
            return ((i + 31) * 31) + mode.hashCode();
        }
    }

    // compiled from: AppCompatDrawableManager.java
    private static class d implements c {
        private d() {
        }

        public final Drawable a(Context context, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) {
            try {
                return g.a(context.getResources(), xmlPullParser, attributeSet, theme);
            } catch (Exception e) {
                return null;
            }
        }
    }

    public r() {
        this.m = new Object();
        this.n = new WeakHashMap(0);
    }

    static {
        a = Mode.SRC_IN;
        c = new b();
        d = new int[]{R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
        e = new int[]{R.drawable.abc_ic_ab_back_mtrl_am_alpha, R.drawable.abc_ic_go_search_api_mtrl_alpha, R.drawable.abc_ic_search_api_mtrl_alpha, R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_ic_clear_mtrl_alpha, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha, R.drawable.abc_ic_menu_moreoverflow_mtrl_alpha, R.drawable.abc_ic_voice_search_api_mtrl_alpha};
        f = new int[]{R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material};
        g = new int[]{R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
        h = new int[]{R.drawable.abc_edit_text_material, R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material, R.drawable.abc_spinner_mtrl_am_alpha, R.drawable.abc_spinner_textfield_background_material, R.drawable.abc_ratingbar_full_material, R.drawable.abc_switch_track_mtrl_alpha, R.drawable.abc_switch_thumb_material, R.drawable.abc_btn_default_mtrl_shape, R.drawable.abc_btn_borderless_material};
        i = new int[]{R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material};
    }

    public static r a() {
        if (b == null) {
            r rVar = new r();
            b = rVar;
            int i = VERSION.SDK_INT;
            if (i < 23) {
                rVar.a("vector", new d());
                if (i >= 11) {
                    rVar.a("animated-vector", new a());
                }
            }
        }
        return b;
    }

    public final Drawable a(Context context, int i, boolean z) {
        Drawable a = a(context, i);
        if (a == null) {
            if (this.o == null) {
                this.o = new TypedValue();
            }
            TypedValue typedValue = this.o;
            context.getResources().getValue(i, typedValue, true);
            long a2 = a(typedValue);
            a = a(context, a2);
            if (a == null) {
                if (i == R.drawable.abc_cab_background_top_material) {
                    a = new LayerDrawable(new Drawable[]{a(context, R.drawable.abc_cab_background_internal_bg, false), a(context, R.drawable.abc_cab_background_top_mtrl_alpha, false)});
                }
                if (a != null) {
                    a.setChangingConfigurations(typedValue.changingConfigurations);
                    a(context, a2, a);
                }
            }
        }
        if (a == null) {
            a = ContextCompat.getDrawable(context, i);
        }
        if (a != null) {
            a = a(context, i, z, a);
        }
        if (a != null) {
            ao.b(a);
        }
        return a;
    }

    private static long a(TypedValue typedValue) {
        return (((long) typedValue.assetCookie) << 32) | ((long) typedValue.data);
    }

    final Drawable a(Context context, int i, boolean z, Drawable drawable) {
        Mode mode = null;
        ColorStateList b = b(context, i);
        if (b != null) {
            if (ao.c(drawable)) {
                drawable = drawable.mutate();
            }
            drawable = DrawableCompat.wrap(drawable);
            DrawableCompat.setTintList(drawable, b);
            if (i == R.drawable.abc_switch_thumb_material) {
                mode = Mode.MULTIPLY;
            }
            if (mode == null) {
                return drawable;
            }
            DrawableCompat.setTintMode(drawable, mode);
            return drawable;
        } else if (i == R.drawable.abc_seekbar_track_material) {
            r0 = (LayerDrawable) drawable;
            a(r0.findDrawableByLayerId(16908288), ci.a(context, R.attr.colorControlNormal), a);
            a(r0.findDrawableByLayerId(16908303), ci.a(context, R.attr.colorControlNormal), a);
            a(r0.findDrawableByLayerId(16908301), ci.a(context, R.attr.colorControlActivated), a);
            return drawable;
        } else if (i != R.drawable.abc_ratingbar_indicator_material && i != R.drawable.abc_ratingbar_small_material) {
            return (a(context, i, drawable) || !z) ? drawable : null;
        } else {
            r0 = (LayerDrawable) drawable;
            a(r0.findDrawableByLayerId(16908288), ci.c(context, R.attr.colorControlNormal), a);
            a(r0.findDrawableByLayerId(16908303), ci.a(context, R.attr.colorControlActivated), a);
            a(r0.findDrawableByLayerId(16908301), ci.a(context, R.attr.colorControlActivated), a);
            return drawable;
        }
    }

    final Drawable a(Context context, int i) {
        if (this.k == null || this.k.isEmpty()) {
            return null;
        }
        String str;
        if (this.l != null) {
            str = (String) this.l.get(i);
            if ("appcompat_skip_skip".equals(str) || (str != null && this.k.get(str) == null)) {
                return null;
            }
        }
        this.l = new SparseArray();
        if (this.o == null) {
            this.o = new TypedValue();
        }
        TypedValue typedValue = this.o;
        Resources resources = context.getResources();
        resources.getValue(i, typedValue, true);
        long a = a(typedValue);
        Drawable a2 = a(context, a);
        if (a2 != null) {
            return a2;
        }
        Drawable drawable;
        if (typedValue.string != null && typedValue.string.toString().endsWith(".xml")) {
            XmlPullParser xml;
            AttributeSet asAttributeSet;
            int next;
            try {
                xml = resources.getXml(i);
                asAttributeSet = Xml.asAttributeSet(xml);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
            } catch (Exception e) {
            }
            if (next != 2) {
                throw new XmlPullParserException("No start tag found");
            }
            str = xml.getName();
            this.l.append(i, str);
            c cVar = (c) this.k.get(str);
            if (cVar != null) {
                a2 = cVar.a(context, xml, asAttributeSet, context.getTheme());
            }
            if (a2 != null) {
                a2.setChangingConfigurations(typedValue.changingConfigurations);
                a(context, a, a2);
            }
            drawable = a2;
            if (drawable == null) {
                return drawable;
            }
            this.l.append(i, "appcompat_skip_skip");
            return drawable;
        }
        drawable = a2;
        if (drawable == null) {
            return drawable;
        }
        this.l.append(i, "appcompat_skip_skip");
        return drawable;
    }

    private Drawable a(Context context, long j) {
        synchronized (this.m) {
            LongSparseArray longSparseArray = (LongSparseArray) this.n.get(context);
            if (longSparseArray == null) {
                return null;
            }
            WeakReference weakReference = (WeakReference) longSparseArray.get(j);
            if (weakReference != null) {
                ConstantState constantState = (ConstantState) weakReference.get();
                if (constantState != null) {
                    Drawable newDrawable = constantState.newDrawable(context.getResources());
                    return newDrawable;
                }
                longSparseArray.delete(j);
            }
            return null;
        }
    }

    private boolean a(Context context, long j, Drawable drawable) {
        ConstantState constantState = drawable.getConstantState();
        if (constantState == null) {
            return false;
        }
        synchronized (this.m) {
            LongSparseArray longSparseArray = (LongSparseArray) this.n.get(context);
            if (longSparseArray == null) {
                longSparseArray = new LongSparseArray();
                this.n.put(context, longSparseArray);
            }
            longSparseArray.put(j, new WeakReference(constantState));
        }
        return true;
    }

    static boolean a(Context context, int i, Drawable drawable) {
        int i2;
        Mode mode;
        boolean z;
        int i3;
        Mode mode2 = a;
        if (a(d, i)) {
            i2 = R.attr.colorControlNormal;
            mode = mode2;
            z = true;
            i3 = -1;
        } else if (a(f, i)) {
            i2 = R.attr.colorControlActivated;
            mode = mode2;
            z = true;
            i3 = -1;
        } else if (a(g, i)) {
            z = true;
            mode = Mode.MULTIPLY;
            i2 = 16842801;
            i3 = -1;
        } else if (i == R.drawable.abc_list_divider_mtrl_alpha) {
            i2 = 16842800;
            i3 = Math.round(40.8f);
            mode = mode2;
            z = true;
        } else {
            i3 = -1;
            i2 = 0;
            mode = mode2;
            Object obj = null;
        }
        if (!z) {
            return false;
        }
        if (ao.c(drawable)) {
            drawable = drawable.mutate();
        }
        drawable.setColorFilter(a(ci.a(context, i2), mode));
        if (i3 == -1) {
            return true;
        }
        drawable.setAlpha(i3);
        return true;
    }

    private void a(String str, c cVar) {
        if (this.k == null) {
            this.k = new ArrayMap();
        }
        this.k.put(str, cVar);
    }

    private static boolean a(int[] iArr, int i) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (iArr[i2] == i) {
                return true;
            }
        }
        return false;
    }

    public final ColorStateList b(Context context, int i) {
        SparseArray sparseArray;
        ColorStateList colorStateList;
        if (this.j != null) {
            sparseArray = (SparseArray) this.j.get(context);
            if (sparseArray != null) {
                colorStateList = (ColorStateList) sparseArray.get(i);
            } else {
                colorStateList = null;
            }
        } else {
            colorStateList = null;
        }
        if (colorStateList != null) {
            return colorStateList;
        }
        ColorStateList b;
        int[][] iArr;
        int[] iArr2;
        Object colorStateList2;
        if (i == R.drawable.abc_edit_text_material) {
            iArr = new int[3][];
            iArr2 = new int[]{ci.a, ci.c(context, R.attr.colorControlNormal), ci.g};
            iArr2[1] = ci.a(context, R.attr.colorControlNormal);
            iArr[2] = ci.h;
            iArr2[2] = ci.a(context, R.attr.colorControlActivated);
            colorStateList2 = new ColorStateList(iArr, iArr2);
        } else if (i == R.drawable.abc_switch_track_mtrl_alpha) {
            iArr = new int[3][];
            iArr2 = new int[]{ci.a, ci.a(context, 16842800, 0.1f), ci.e};
            iArr2[1] = ci.a(context, R.attr.colorControlActivated, 0.3f);
            iArr[2] = ci.h;
            iArr2[2] = ci.a(context, 16842800, 0.3f);
            colorStateList2 = new ColorStateList(iArr, iArr2);
        } else if (i == R.drawable.abc_switch_thumb_material) {
            iArr = new int[3][];
            iArr2 = new int[3];
            b = ci.b(context, R.attr.colorSwitchThumbNormal);
            if (b == null || !b.isStateful()) {
                iArr[0] = ci.a;
                iArr2[0] = ci.c(context, R.attr.colorSwitchThumbNormal);
                iArr[1] = ci.e;
                iArr2[1] = ci.a(context, R.attr.colorControlActivated);
                iArr[2] = ci.h;
                iArr2[2] = ci.a(context, R.attr.colorSwitchThumbNormal);
            } else {
                iArr[0] = ci.a;
                iArr2[0] = b.getColorForState(iArr[0], 0);
                iArr[1] = ci.e;
                iArr2[1] = ci.a(context, R.attr.colorControlActivated);
                iArr[2] = ci.h;
                iArr2[2] = b.getDefaultColor();
            }
            colorStateList2 = new ColorStateList(iArr, iArr2);
        } else if (i == R.drawable.abc_btn_default_mtrl_shape) {
            colorStateList2 = c(context, ci.a(context, R.attr.colorButtonNormal));
        } else if (i == R.drawable.abc_btn_borderless_material) {
            colorStateList2 = c(context, 0);
        } else if (i == R.drawable.abc_btn_colored_material) {
            colorStateList2 = c(context, ci.a(context, R.attr.colorAccent));
        } else if (i == R.drawable.abc_spinner_mtrl_am_alpha || i == R.drawable.abc_spinner_textfield_background_material) {
            iArr = new int[3][];
            iArr2 = new int[]{ci.a, ci.c(context, R.attr.colorControlNormal), ci.g};
            iArr2[1] = ci.a(context, R.attr.colorControlNormal);
            iArr[2] = ci.h;
            iArr2[2] = ci.a(context, R.attr.colorControlActivated);
            colorStateList2 = new ColorStateList(iArr, iArr2);
        } else if (a(e, i)) {
            colorStateList2 = ci.b(context, R.attr.colorControlNormal);
        } else if (a(h, i)) {
            int a = ci.a(context, R.attr.colorControlNormal);
            int a2 = ci.a(context, R.attr.colorControlActivated);
            r2 = new int[7][];
            int[] iArr3 = new int[]{ci.a, ci.c(context, R.attr.colorControlNormal), ci.b, a2, ci.c, a2, ci.d};
            iArr3[3] = a2;
            r2[4] = ci.e;
            iArr3[4] = a2;
            r2[5] = ci.f;
            iArr3[5] = a2;
            r2[6] = ci.h;
            iArr3[6] = a;
            colorStateList2 = new ColorStateList(r2, iArr3);
        } else if (a(i, i)) {
            iArr = new int[3][];
            iArr2 = new int[]{ci.a, ci.c(context, R.attr.colorControlNormal), ci.e};
            iArr2[1] = ci.a(context, R.attr.colorControlActivated);
            iArr[2] = ci.h;
            iArr2[2] = ci.a(context, R.attr.colorControlNormal);
            colorStateList2 = new ColorStateList(iArr, iArr2);
        } else if (i == R.drawable.abc_seekbar_thumb_material) {
            iArr = new int[2][];
            iArr2 = new int[]{ci.a, ci.c(context, R.attr.colorControlActivated)};
            iArr[1] = ci.h;
            iArr2[1] = ci.a(context, R.attr.colorControlActivated);
            colorStateList2 = new ColorStateList(iArr, iArr2);
        } else {
            b = colorStateList;
        }
        if (b != null) {
            if (this.j == null) {
                this.j = new WeakHashMap();
            }
            sparseArray = (SparseArray) this.j.get(context);
            if (sparseArray == null) {
                sparseArray = new SparseArray();
                this.j.put(context, sparseArray);
            }
            sparseArray.append(i, b);
        }
        return b;
    }

    private static ColorStateList c(Context context, int i) {
        r0 = new int[4][];
        r1 = new int[4];
        int a = ci.a(context, R.attr.colorControlHighlight);
        r0[0] = ci.a;
        r1[0] = ci.c(context, R.attr.colorButtonNormal);
        r0[1] = ci.d;
        r1[1] = ColorUtils.compositeColors(a, i);
        r0[2] = ci.b;
        r1[2] = ColorUtils.compositeColors(a, i);
        r0[3] = ci.h;
        r1[3] = i;
        return new ColorStateList(r0, r1);
    }

    public static void a(Drawable drawable, ck ckVar, int[] iArr) {
        ColorFilter colorFilter = null;
        if (!ao.c(drawable) || drawable.mutate() == drawable) {
            if (ckVar.d || ckVar.c) {
                ColorStateList colorStateList;
                Mode mode;
                if (ckVar.d) {
                    colorStateList = ckVar.a;
                } else {
                    colorStateList = null;
                }
                if (ckVar.c) {
                    mode = ckVar.b;
                } else {
                    mode = a;
                }
                if (!(colorStateList == null || mode == null)) {
                    colorFilter = a(colorStateList.getColorForState(iArr, 0), mode);
                }
                drawable.setColorFilter(colorFilter);
            } else {
                drawable.clearColorFilter();
            }
            if (VERSION.SDK_INT <= 23) {
                drawable.invalidateSelf();
            }
        }
    }

    public static PorterDuffColorFilter a(int i, Mode mode) {
        PorterDuffColorFilter porterDuffColorFilter = (PorterDuffColorFilter) c.get(Integer.valueOf(b.a(i, mode)));
        if (porterDuffColorFilter != null) {
            return porterDuffColorFilter;
        }
        porterDuffColorFilter = new PorterDuffColorFilter(i, mode);
        c.put(Integer.valueOf(b.a(i, mode)), porterDuffColorFilter);
        return porterDuffColorFilter;
    }

    private static void a(Drawable drawable, int i, Mode mode) {
        if (ao.c(drawable)) {
            drawable = drawable.mutate();
        }
        if (mode == null) {
            mode = a;
        }
        drawable.setColorFilter(a(i, mode));
    }
}
