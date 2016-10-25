package android.support.a.a;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.VectorDrawable;
import android.os.Build.VERSION;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.util.Xml;
import com.tencent.open.wpa.WPA;
import com.tencent.open.yyb.AppbarJsBridge;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import org.android.spdy.SpdyAgent;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@TargetApi(21)
// compiled from: VectorDrawableCompat.java
public final class g extends f {
    static final Mode b;
    f c;
    boolean d;
    private PorterDuffColorFilter e;
    private ColorFilter f;
    private boolean g;
    private ConstantState h;
    private final float[] i;
    private final Matrix j;
    private final Rect k;

    // compiled from: VectorDrawableCompat.java
    private static class d {
        protected android.support.a.a.d.b[] m;
        String n;
        int o;

        public d() {
            this.m = null;
        }

        public d(d dVar) {
            this.m = null;
            this.n = dVar.n;
            this.o = dVar.o;
            this.m = d.a(dVar.m);
        }

        public final void a(Path path) {
            path.reset();
            if (this.m != null) {
                android.support.a.a.d.b[] bVarArr = this.m;
                float[] fArr = new float[6];
                int i = 0;
                int i2 = 109;
                while (i < bVarArr.length) {
                    int i3;
                    char c;
                    char c2 = bVarArr[i].a;
                    float[] fArr2 = bVarArr[i].b;
                    float f = fArr[0];
                    float f2 = fArr[1];
                    float f3 = fArr[2];
                    float f4 = fArr[3];
                    float f5 = fArr[4];
                    float f6 = fArr[5];
                    switch (c2) {
                        case R.styleable.AppCompatTheme_textAppearanceSearchResultTitle:
                        case R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle:
                            i3 = 7;
                            break;
                        case R.styleable.AppCompatTheme_textColorSearchUrl:
                        case R.styleable.AppCompatTheme_autoCompleteTextViewStyle:
                            i3 = 6;
                            break;
                        case R.styleable.AppCompatTheme_listPreferredItemPaddingLeft:
                        case R.styleable.AppCompatTheme_colorControlActivated:
                        case R.styleable.AppCompatTheme_editTextStyle:
                        case 'v':
                            i3 = 1;
                            break;
                        case R.styleable.AppCompatTheme_textAppearanceListItem:
                        case R.styleable.AppCompatTheme_textAppearanceListItemSmall:
                        case R.styleable.AppCompatTheme_colorAccent:
                        case R.styleable.AppCompatTheme_ratingBarStyleSmall:
                        case R.styleable.AppCompatTheme_seekBarStyle:
                        case 't':
                            i3 = 2;
                            break;
                        case R.styleable.AppCompatTheme_listChoiceBackgroundIndicator:
                        case R.styleable.AppCompatTheme_colorPrimaryDark:
                        case 'q':
                        case 's':
                            i3 = 4;
                            break;
                        case R.styleable.AppCompatTheme_controlBackground:
                        case 'z':
                            path.close();
                            path.moveTo(f5, f6);
                            f4 = f6;
                            f3 = f5;
                            f2 = f6;
                            f = f5;
                            i3 = 2;
                            break;
                        default:
                            i3 = 2;
                            break;
                    }
                    int i4 = 0;
                    float f7 = f6;
                    float f8 = f5;
                    float f9 = f2;
                    float f10 = f;
                    while (i4 < fArr2.length) {
                        float f11;
                        boolean z;
                        boolean z2;
                        switch (c2) {
                            case R.styleable.AppCompatTheme_textAppearanceSearchResultTitle:
                                f3 = fArr2[i4 + 5];
                                f5 = fArr2[i4 + 6];
                                f2 = fArr2[i4 + 0];
                                f = fArr2[i4 + 1];
                                f11 = fArr2[i4 + 2];
                                z = fArr2[i4 + 3] != 0.0f;
                                if (fArr2[i4 + 4] != 0.0f) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                android.support.a.a.d.b.a(path, f10, f9, f3, f5, f2, f, f11, z, z2);
                                f3 = fArr2[i4 + 5];
                                f4 = fArr2[i4 + 6];
                                f6 = f7;
                                f5 = f8;
                                f2 = f4;
                                f = f3;
                                break;
                            case R.styleable.AppCompatTheme_textColorSearchUrl:
                                path.cubicTo(fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3], fArr2[i4 + 4], fArr2[i4 + 5]);
                                f5 = fArr2[i4 + 4];
                                f6 = fArr2[i4 + 5];
                                f3 = fArr2[i4 + 2];
                                f4 = fArr2[i4 + 3];
                                f2 = f6;
                                f = f5;
                                f6 = f7;
                                f5 = f8;
                                break;
                            case R.styleable.AppCompatTheme_listPreferredItemPaddingLeft:
                                path.lineTo(fArr2[i4 + 0], f9);
                                f6 = f7;
                                f2 = f9;
                                f = fArr2[i4 + 0];
                                f5 = f8;
                                break;
                            case R.styleable.AppCompatTheme_textAppearanceListItem:
                                path.lineTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                                f2 = fArr2[i4 + 1];
                                f = fArr2[i4 + 0];
                                f6 = f7;
                                f5 = f8;
                                break;
                            case R.styleable.AppCompatTheme_textAppearanceListItemSmall:
                                f5 = fArr2[i4 + 0];
                                f6 = fArr2[i4 + 1];
                                if (i4 > 0) {
                                    path.lineTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                                    f2 = f6;
                                    f = f5;
                                    f6 = f7;
                                    f5 = f8;
                                } else {
                                    path.moveTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                                    f2 = f6;
                                    f = f5;
                                }
                                break;
                            case R.styleable.AppCompatTheme_listChoiceBackgroundIndicator:
                                path.quadTo(fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3]);
                                f3 = fArr2[i4 + 0];
                                f4 = fArr2[i4 + 1];
                                f2 = fArr2[i4 + 3];
                                f = fArr2[i4 + 2];
                                f6 = f7;
                                f5 = f8;
                                break;
                            case R.styleable.AppCompatTheme_colorPrimaryDark:
                                if (c == 'c' || c == 's' || c == 'C' || c == 'S') {
                                    f6 = (2.0f * f9) - f4;
                                    f4 = (2.0f * f10) - f3;
                                } else {
                                    f6 = f9;
                                    f4 = f10;
                                }
                                path.cubicTo(f4, f6, fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3]);
                                f3 = fArr2[i4 + 0];
                                f4 = fArr2[i4 + 1];
                                f2 = fArr2[i4 + 3];
                                f = fArr2[i4 + 2];
                                f6 = f7;
                                f5 = f8;
                                break;
                            case R.styleable.AppCompatTheme_colorAccent:
                                if (c == 'q' || c == 't' || c == 'Q' || c == 'T') {
                                    f10 = (2.0f * f10) - f3;
                                    f9 = (2.0f * f9) - f4;
                                }
                                path.quadTo(f10, f9, fArr2[i4 + 0], fArr2[i4 + 1]);
                                f4 = f9;
                                f3 = f10;
                                f2 = fArr2[i4 + 1];
                                f = fArr2[i4 + 0];
                                f6 = f7;
                                f5 = f8;
                                break;
                            case R.styleable.AppCompatTheme_colorControlActivated:
                                path.lineTo(f10, fArr2[i4 + 0]);
                                f5 = f8;
                                f2 = fArr2[i4 + 0];
                                f = f10;
                                f6 = f7;
                                break;
                            case R.styleable.AppCompatTheme_buttonBarNegativeButtonStyle:
                                f3 = fArr2[i4 + 5] + f10;
                                f5 = fArr2[i4 + 6] + f9;
                                f2 = fArr2[i4 + 0];
                                f = fArr2[i4 + 1];
                                f11 = fArr2[i4 + 2];
                                z = fArr2[i4 + 3] != 0.0f;
                                if (fArr2[i4 + 4] != 0.0f) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                android.support.a.a.d.b.a(path, f10, f9, f3, f5, f2, f, f11, z, z2);
                                f3 = f10 + fArr2[i4 + 5];
                                f4 = f9 + fArr2[i4 + 6];
                                f6 = f7;
                                f5 = f8;
                                f2 = f4;
                                f = f3;
                                break;
                            case R.styleable.AppCompatTheme_autoCompleteTextViewStyle:
                                path.rCubicTo(fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3], fArr2[i4 + 4], fArr2[i4 + 5]);
                                f3 = f10 + fArr2[i4 + 2];
                                f4 = f9 + fArr2[i4 + 3];
                                f2 = f9 + fArr2[i4 + 5];
                                f = f10 + fArr2[i4 + 4];
                                f6 = f7;
                                f5 = f8;
                                break;
                            case R.styleable.AppCompatTheme_editTextStyle:
                                path.rLineTo(fArr2[i4 + 0], AutoScrollHelper.RELATIVE_UNSPECIFIED);
                                f6 = f7;
                                f2 = f9;
                                f = f10 + fArr2[i4 + 0];
                                f5 = f8;
                                break;
                            case R.styleable.AppCompatTheme_ratingBarStyleSmall:
                                path.rLineTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                                f2 = f9 + fArr2[i4 + 1];
                                f = f10 + fArr2[i4 + 0];
                                f6 = f7;
                                f5 = f8;
                                break;
                            case R.styleable.AppCompatTheme_seekBarStyle:
                                f5 = f10 + fArr2[i4 + 0];
                                f6 = f9 + fArr2[i4 + 1];
                                if (i4 > 0) {
                                    path.rLineTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                                    f2 = f6;
                                    f = f5;
                                    f6 = f7;
                                    f5 = f8;
                                } else {
                                    path.rMoveTo(fArr2[i4 + 0], fArr2[i4 + 1]);
                                    f2 = f6;
                                    f = f5;
                                }
                                break;
                            case 'q':
                                path.rQuadTo(fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3]);
                                f3 = f10 + fArr2[i4 + 0];
                                f4 = f9 + fArr2[i4 + 1];
                                f2 = f9 + fArr2[i4 + 3];
                                f = f10 + fArr2[i4 + 2];
                                f6 = f7;
                                f5 = f8;
                                break;
                            case 's':
                                f6 = AutoScrollHelper.RELATIVE_UNSPECIFIED;
                                if (c == 'c' || c == 's' || c == 'C' || c == 'S') {
                                    f6 = f9 - f4;
                                    f4 = f10 - f3;
                                } else {
                                    f4 = 0.0f;
                                }
                                path.rCubicTo(f4, f6, fArr2[i4 + 0], fArr2[i4 + 1], fArr2[i4 + 2], fArr2[i4 + 3]);
                                f3 = f10 + fArr2[i4 + 0];
                                f4 = f9 + fArr2[i4 + 1];
                                f2 = f9 + fArr2[i4 + 3];
                                f = f10 + fArr2[i4 + 2];
                                f6 = f7;
                                f5 = f8;
                                break;
                            case 't':
                                float f12;
                                if (c == 'q' || c == 't' || c == 'Q' || c == 'T') {
                                    f12 = f9 - f4;
                                    f4 = f10 - f3;
                                } else {
                                    f12 = 0.0f;
                                    f4 = 0.0f;
                                }
                                path.rQuadTo(f4, f12, fArr2[i4 + 0], fArr2[i4 + 1]);
                                f3 = f10 + f4;
                                f4 = f9 + f12;
                                f2 = f9 + fArr2[i4 + 1];
                                f = f10 + fArr2[i4 + 0];
                                f6 = f7;
                                f5 = f8;
                                break;
                            case 'v':
                                path.rLineTo(AutoScrollHelper.RELATIVE_UNSPECIFIED, fArr2[i4 + 0]);
                                f5 = f8;
                                f2 = f9 + fArr2[i4 + 0];
                                f = f10;
                                f6 = f7;
                                break;
                            default:
                                f6 = f7;
                                f5 = f8;
                                f2 = f9;
                                f = f10;
                                break;
                        }
                        i4 += i3;
                        f7 = f6;
                        f8 = f5;
                        f9 = f2;
                        f10 = f;
                        c = c2;
                    }
                    fArr[0] = f10;
                    fArr[1] = f9;
                    fArr[2] = f3;
                    fArr[3] = f4;
                    fArr[4] = f8;
                    fArr[5] = f7;
                    char c3 = bVarArr[i].a;
                    i++;
                    c = c3;
                }
            }
        }

        public boolean a() {
            return false;
        }
    }

    // compiled from: VectorDrawableCompat.java
    private static class a extends d {
        public a(a aVar) {
            super(aVar);
        }

        final void a(TypedArray typedArray) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.n = string;
            }
            string = typedArray.getString(1);
            if (string != null) {
                this.m = d.a(string);
            }
        }

        public final boolean a() {
            return true;
        }
    }

    // compiled from: VectorDrawableCompat.java
    private static class b extends d {
        int a;
        float b;
        int c;
        float d;
        int e;
        float f;
        float g;
        float h;
        float i;
        Cap j;
        Join k;
        float l;
        private int[] p;

        public b() {
            this.a = 0;
            this.b = 0.0f;
            this.c = 0;
            this.d = 1.0f;
            this.f = 1.0f;
            this.g = 0.0f;
            this.h = 1.0f;
            this.i = 0.0f;
            this.j = Cap.BUTT;
            this.k = Join.MITER;
            this.l = 4.0f;
        }

        public b(b bVar) {
            super(bVar);
            this.a = 0;
            this.b = 0.0f;
            this.c = 0;
            this.d = 1.0f;
            this.f = 1.0f;
            this.g = 0.0f;
            this.h = 1.0f;
            this.i = 0.0f;
            this.j = Cap.BUTT;
            this.k = Join.MITER;
            this.l = 4.0f;
            this.p = bVar.p;
            this.a = bVar.a;
            this.b = bVar.b;
            this.d = bVar.d;
            this.c = bVar.c;
            this.e = bVar.e;
            this.f = bVar.f;
            this.g = bVar.g;
            this.h = bVar.h;
            this.i = bVar.i;
            this.j = bVar.j;
            this.k = bVar.k;
            this.l = bVar.l;
        }

        final void a(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.p = null;
            if (e.a(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.n = string;
                }
                string = typedArray.getString(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                if (string != null) {
                    this.m = d.a(string);
                }
                this.c = e.a(typedArray, xmlPullParser, "fillColor", 1, this.c);
                this.f = e.a(typedArray, xmlPullParser, "fillAlpha", (int) XZBDevice.Fail, this.f);
                int a = e.a(typedArray, xmlPullParser, "strokeLineCap", XZBDevice.Wait);
                Cap cap = this.j;
                switch (a) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        cap = Cap.BUTT;
                        break;
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        cap = Cap.ROUND;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        cap = Cap.SQUARE;
                        break;
                }
                this.j = cap;
                a = e.a(typedArray, xmlPullParser, "strokeLineJoin", XZBDevice.Pause);
                Join join = this.k;
                switch (a) {
                    case SpdyAgent.ACCS_TEST_SERVER:
                        join = Join.MITER;
                        break;
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        join = Join.ROUND;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        join = Join.BEVEL;
                        break;
                }
                this.k = join;
                this.l = e.a(typedArray, xmlPullParser, "strokeMiterLimit", (int) XZBDevice.Stop, this.l);
                this.a = e.a(typedArray, xmlPullParser, "strokeColor", (int) XZBDevice.DOWNLOAD_LIST_FAILED, this.a);
                this.d = e.a(typedArray, xmlPullParser, "strokeAlpha", (int) XZBDevice.Success, this.d);
                this.b = e.a(typedArray, xmlPullParser, "strokeWidth", (int) XZBDevice.DOWNLOAD_LIST_ALL, this.b);
                this.h = e.a(typedArray, xmlPullParser, "trimPathEnd", (int) R.styleable.Toolbar_contentInsetEnd, this.h);
                this.i = e.a(typedArray, xmlPullParser, "trimPathOffset", (int) R.styleable.Toolbar_contentInsetLeft, this.i);
                this.g = e.a(typedArray, xmlPullParser, "trimPathStart", (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, this.g);
            }
        }
    }

    // compiled from: VectorDrawableCompat.java
    private static class c {
        final Matrix a;
        final ArrayList<Object> b;
        float c;
        float d;
        float e;
        float f;
        float g;
        float h;
        float i;
        final Matrix j;
        int k;
        int[] l;
        String m;

        public c(c cVar, ArrayMap<String, Object> arrayMap) {
            this.a = new Matrix();
            this.b = new ArrayList();
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
            this.c = cVar.c;
            this.d = cVar.d;
            this.e = cVar.e;
            this.f = cVar.f;
            this.g = cVar.g;
            this.h = cVar.h;
            this.i = cVar.i;
            this.l = cVar.l;
            this.m = cVar.m;
            this.k = cVar.k;
            if (this.m != null) {
                arrayMap.put(this.m, this);
            }
            this.j.set(cVar.j);
            ArrayList arrayList = cVar.b;
            for (int i = 0; i < arrayList.size(); i++) {
                Object obj = arrayList.get(i);
                if (obj instanceof c) {
                    this.b.add(new c((c) obj, arrayMap));
                } else {
                    d bVar;
                    if (obj instanceof b) {
                        bVar = new b((b) obj);
                    } else if (obj instanceof a) {
                        bVar = new a((a) obj);
                    } else {
                        throw new IllegalStateException("Unknown object in the tree!");
                    }
                    this.b.add(bVar);
                    if (bVar.n != null) {
                        arrayMap.put(bVar.n, bVar);
                    }
                }
            }
        }

        public c() {
            this.a = new Matrix();
            this.b = new ArrayList();
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }
    }

    // compiled from: VectorDrawableCompat.java
    private static class e {
        private static final Matrix j;
        float a;
        float b;
        float c;
        float d;
        int e;
        String f;
        final ArrayMap<String, Object> g;
        private final Path h;
        private final Path i;
        private final Matrix k;
        private Paint l;
        private Paint m;
        private PathMeasure n;
        private int o;
        private final c p;

        static {
            j = new Matrix();
        }

        public e() {
            this.k = new Matrix();
            this.a = 0.0f;
            this.b = 0.0f;
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 255;
            this.f = null;
            this.g = new ArrayMap();
            this.p = new c();
            this.h = new Path();
            this.i = new Path();
        }

        public e(e eVar) {
            this.k = new Matrix();
            this.a = 0.0f;
            this.b = 0.0f;
            this.c = 0.0f;
            this.d = 0.0f;
            this.e = 255;
            this.f = null;
            this.g = new ArrayMap();
            this.p = new c(eVar.p, this.g);
            this.h = new Path(eVar.h);
            this.i = new Path(eVar.i);
            this.a = eVar.a;
            this.b = eVar.b;
            this.c = eVar.c;
            this.d = eVar.d;
            this.o = eVar.o;
            this.e = eVar.e;
            this.f = eVar.f;
            if (eVar.f != null) {
                this.g.put(eVar.f, this);
            }
        }

        public final void a(Canvas canvas, int i, int i2) {
            a(this.p, j, canvas, i, i2, null);
        }

        private void a(c cVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            cVar.a.set(matrix);
            cVar.a.preConcat(cVar.j);
            for (int i3 = 0; i3 < cVar.b.size(); i3++) {
                Object obj = cVar.b.get(i3);
                if (obj instanceof c) {
                    a((c) obj, cVar.a, canvas, i, i2, colorFilter);
                } else if (obj instanceof d) {
                    d dVar = (d) obj;
                    float f = ((float) i) / this.c;
                    float f2 = ((float) i2) / this.d;
                    float min = Math.min(f, f2);
                    Matrix matrix2 = cVar.a;
                    this.k.set(matrix2);
                    this.k.postScale(f, f2);
                    float[] fArr = new float[]{0.0f, 1.0f, 1.0f, 0.0f};
                    matrix2.mapVectors(fArr);
                    f2 = (float) Math.hypot((double) fArr[0], (double) fArr[1]);
                    float hypot = (float) Math.hypot((double) fArr[2], (double) fArr[3]);
                    float f3 = (fArr[3] * fArr[0]) - (fArr[1] * fArr[2]);
                    f2 = Math.max(f2, hypot);
                    f = AutoScrollHelper.RELATIVE_UNSPECIFIED;
                    if (f2 > 0.0f) {
                        f = Math.abs(f3) / f2;
                    }
                    if (f != 0.0f) {
                        dVar.a(this.h);
                        Path path = this.h;
                        this.i.reset();
                        if (dVar.a()) {
                            this.i.addPath(path, this.k);
                            canvas.clipPath(this.i, Op.REPLACE);
                        } else {
                            Paint paint;
                            b bVar = (b) dVar;
                            if (!(bVar.g == 0.0f && bVar.h == 1.0f)) {
                                hypot = (bVar.g + bVar.i) % 1.0f;
                                f3 = (bVar.h + bVar.i) % 1.0f;
                                if (this.n == null) {
                                    this.n = new PathMeasure();
                                }
                                this.n.setPath(this.h, false);
                                float length = this.n.getLength();
                                hypot *= length;
                                f3 *= length;
                                path.reset();
                                if (hypot > f3) {
                                    this.n.getSegment(hypot, length, path, true);
                                    this.n.getSegment(AutoScrollHelper.RELATIVE_UNSPECIFIED, f3, path, true);
                                } else {
                                    this.n.getSegment(hypot, f3, path, true);
                                }
                                path.rLineTo(AutoScrollHelper.RELATIVE_UNSPECIFIED, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                            }
                            this.i.addPath(path, this.k);
                            if (bVar.c != 0) {
                                if (this.m == null) {
                                    this.m = new Paint();
                                    this.m.setStyle(Style.FILL);
                                    this.m.setAntiAlias(true);
                                }
                                paint = this.m;
                                paint.setColor(((((int) (((float) Color.alpha(bVar.c)) * bVar.f)) << 24) | (16777215 & bVar.c)));
                                paint.setColorFilter(colorFilter);
                                canvas.drawPath(this.i, paint);
                            }
                            if (bVar.a != 0) {
                                if (this.l == null) {
                                    this.l = new Paint();
                                    this.l.setStyle(Style.STROKE);
                                    this.l.setAntiAlias(true);
                                }
                                paint = this.l;
                                if (bVar.k != null) {
                                    paint.setStrokeJoin(bVar.k);
                                }
                                if (bVar.j != null) {
                                    paint.setStrokeCap(bVar.j);
                                }
                                paint.setStrokeMiter(bVar.l);
                                paint.setColor(((((int) (((float) Color.alpha(bVar.a)) * bVar.d)) << 24) | (16777215 & bVar.d.c)));
                                paint.setColorFilter(colorFilter);
                                paint.setStrokeWidth((f * min) * bVar.b);
                                canvas.drawPath(this.i, paint);
                            }
                        }
                    }
                }
            }
        }
    }

    // compiled from: VectorDrawableCompat.java
    private static class f extends ConstantState {
        int a;
        e b;
        ColorStateList c;
        Mode d;
        boolean e;
        Bitmap f;
        ColorStateList g;
        Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        public f(f fVar) {
            this.c = null;
            this.d = b;
            if (fVar != null) {
                this.a = fVar.a;
                this.b = new e(fVar.b);
                if (fVar.b.m != null) {
                    this.b.m = new Paint(fVar.b.m);
                }
                if (fVar.b.l != null) {
                    this.b.l = new Paint(fVar.b.l);
                }
                this.c = fVar.c;
                this.d = fVar.d;
                this.e = fVar.e;
            }
        }

        public final void a(int i, int i2) {
            this.f.eraseColor(0);
            this.b.a(new Canvas(this.f), i, i2);
        }

        public f() {
            this.c = null;
            this.d = b;
            this.b = new e();
        }

        public final Drawable newDrawable() {
            return new g();
        }

        public final Drawable newDrawable(Resources resources) {
            return new g();
        }

        public final int getChangingConfigurations() {
            return this.a;
        }
    }

    // compiled from: VectorDrawableCompat.java
    private static class g extends ConstantState {
        private final ConstantState a;

        public g(ConstantState constantState) {
            this.a = constantState;
        }

        public final Drawable newDrawable() {
            Drawable gVar = new g();
            gVar.a = (VectorDrawable) this.a.newDrawable();
            return gVar;
        }

        public final Drawable newDrawable(Resources resources) {
            Drawable gVar = new g();
            gVar.a = (VectorDrawable) this.a.newDrawable(resources);
            return gVar;
        }

        public final Drawable newDrawable(Resources resources, Theme theme) {
            Drawable gVar = new g();
            gVar.a = (VectorDrawable) this.a.newDrawable(resources, theme);
            return gVar;
        }

        public final boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }

        public final int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }
    }

    public final /* bridge */ /* synthetic */ void applyTheme(Theme theme) {
        super.applyTheme(theme);
    }

    public final /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    public final /* bridge */ /* synthetic */ ColorFilter getColorFilter() {
        return super.getColorFilter();
    }

    public final /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    public final /* bridge */ /* synthetic */ int getLayoutDirection() {
        return super.getLayoutDirection();
    }

    public final /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    public final /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    public final /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    public final /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public final /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    public final /* bridge */ /* synthetic */ boolean isAutoMirrored() {
        return super.isAutoMirrored();
    }

    public final /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    public final /* bridge */ /* synthetic */ void setAutoMirrored(boolean z) {
        super.setAutoMirrored(z);
    }

    public final /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    public final /* bridge */ /* synthetic */ void setColorFilter(int i, Mode mode) {
        super.setColorFilter(i, mode);
    }

    public final /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    public final /* bridge */ /* synthetic */ void setHotspot(float f, float f2) {
        super.setHotspot(f, f2);
    }

    public final /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    public final /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    static {
        b = Mode.SRC_IN;
    }

    private g() {
        this.d = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.c = new f();
    }

    private g(f fVar) {
        this.d = true;
        this.i = new float[9];
        this.j = new Matrix();
        this.k = new Rect();
        this.c = fVar;
        this.e = a(fVar.c, fVar.d);
    }

    public final Drawable mutate() {
        if (this.a != null) {
            this.a.mutate();
        } else if (!this.g && super.mutate() == this) {
            this.c = new f(this.c);
            this.g = true;
        }
        return this;
    }

    public final ConstantState getConstantState() {
        if (this.a != null) {
            return new g(this.a.getConstantState());
        }
        this.c.a = getChangingConfigurations();
        return this.c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void draw(android.graphics.Canvas r12) {
        throw new UnsupportedOperationException("Method not decompiled: android.support.a.a.g.draw(android.graphics.Canvas):void");
        /*
        this = this;
        r10 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r2 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r9 = 0;
        r4 = 1;
        r5 = 0;
        r0 = r11.a;
        if (r0 == 0) goto L_0x0011;
    L_0x000b:
        r0 = r11.a;
        r0.draw(r12);
    L_0x0010:
        return;
    L_0x0011:
        r0 = r11.k;
        r11.copyBounds(r0);
        r0 = r11.k;
        r0 = r0.width();
        if (r0 <= 0) goto L_0x0010;
    L_0x001e:
        r0 = r11.k;
        r0 = r0.height();
        if (r0 <= 0) goto L_0x0010;
    L_0x0026:
        r0 = r11.f;
        if (r0 != 0) goto L_0x00e0;
    L_0x002a:
        r0 = r11.e;
    L_0x002c:
        r1 = r11.j;
        r12.getMatrix(r1);
        r1 = r11.j;
        r3 = r11.i;
        r1.getValues(r3);
        r1 = r11.i;
        r1 = r1[r5];
        r3 = java.lang.Math.abs(r1);
        r1 = r11.i;
        r6 = 4;
        r1 = r1[r6];
        r1 = java.lang.Math.abs(r1);
        r6 = r11.i;
        r6 = r6[r4];
        r6 = java.lang.Math.abs(r6);
        r7 = r11.i;
        r8 = 3;
        r7 = r7[r8];
        r7 = java.lang.Math.abs(r7);
        r6 = (r6 > r9 ? 1 : (r6 == r9 ? 0 : -1));
        if (r6 != 0) goto L_0x0062;
    L_0x005e:
        r6 = (r7 > r9 ? 1 : (r7 == r9 ? 0 : -1));
        if (r6 == 0) goto L_0x014a;
    L_0x0062:
        r1 = r2;
    L_0x0063:
        r3 = r11.k;
        r3 = r3.width();
        r3 = (float) r3;
        r2 = r2 * r3;
        r2 = (int) r2;
        r3 = r11.k;
        r3 = r3.height();
        r3 = (float) r3;
        r1 = r1 * r3;
        r1 = (int) r1;
        r2 = java.lang.Math.min(r10, r2);
        r3 = java.lang.Math.min(r10, r1);
        if (r2 <= 0) goto L_0x0010;
    L_0x007f:
        if (r3 <= 0) goto L_0x0010;
    L_0x0081:
        r6 = r12.save();
        r1 = r11.k;
        r1 = r1.left;
        r1 = (float) r1;
        r7 = r11.k;
        r7 = r7.top;
        r7 = (float) r7;
        r12.translate(r1, r7);
        r1 = r11.k;
        r1.offsetTo(r5, r5);
        r7 = r11.c;
        r1 = r7.f;
        if (r1 == 0) goto L_0x00b0;
    L_0x009d:
        r1 = r7.f;
        r1 = r1.getWidth();
        if (r2 != r1) goto L_0x00e4;
    L_0x00a5:
        r1 = r7.f;
        r1 = r1.getHeight();
        if (r3 != r1) goto L_0x00e4;
    L_0x00ad:
        r1 = r4;
    L_0x00ae:
        if (r1 != 0) goto L_0x00ba;
    L_0x00b0:
        r1 = android.graphics.Bitmap.Config.ARGB_8888;
        r1 = android.graphics.Bitmap.createBitmap(r2, r3, r1);
        r7.f = r1;
        r7.k = r4;
    L_0x00ba:
        r1 = r11.d;
        if (r1 != 0) goto L_0x00e6;
    L_0x00be:
        r1 = r11.c;
        r1.a(r2, r3);
    L_0x00c3:
        r2 = r11.c;
        r3 = r11.k;
        r1 = r2.b;
        r1 = r1.e;
        r7 = 255; // 0xff float:3.57E-43 double:1.26E-321;
        if (r1 >= r7) goto L_0x0127;
    L_0x00cf:
        r1 = r4;
    L_0x00d0:
        if (r1 != 0) goto L_0x0129;
    L_0x00d2:
        if (r0 != 0) goto L_0x0129;
    L_0x00d4:
        r0 = 0;
    L_0x00d5:
        r1 = r2.f;
        r2 = 0;
        r12.drawBitmap(r1, r2, r3, r0);
        r12.restoreToCount(r6);
        goto L_0x0010;
    L_0x00e0:
        r0 = r11.f;
        goto L_0x002c;
    L_0x00e4:
        r1 = r5;
        goto L_0x00ae;
    L_0x00e6:
        r1 = r11.c;
        r7 = r1.k;
        if (r7 != 0) goto L_0x0125;
    L_0x00ec:
        r7 = r1.g;
        r8 = r1.c;
        if (r7 != r8) goto L_0x0125;
    L_0x00f2:
        r7 = r1.h;
        r8 = r1.d;
        if (r7 != r8) goto L_0x0125;
    L_0x00f8:
        r7 = r1.j;
        r8 = r1.e;
        if (r7 != r8) goto L_0x0125;
    L_0x00fe:
        r7 = r1.i;
        r1 = r1.b;
        r1 = r1.e;
        if (r7 != r1) goto L_0x0125;
    L_0x0106:
        r1 = r4;
    L_0x0107:
        if (r1 != 0) goto L_0x00c3;
    L_0x0109:
        r1 = r11.c;
        r1.a(r2, r3);
        r1 = r11.c;
        r2 = r1.c;
        r1.g = r2;
        r2 = r1.d;
        r1.h = r2;
        r2 = r1.b;
        r2 = r2.e;
        r1.i = r2;
        r2 = r1.e;
        r1.j = r2;
        r1.k = r5;
        goto L_0x00c3;
    L_0x0125:
        r1 = r5;
        goto L_0x0107;
    L_0x0127:
        r1 = r5;
        goto L_0x00d0;
    L_0x0129:
        r1 = r2.l;
        if (r1 != 0) goto L_0x0139;
    L_0x012d:
        r1 = new android.graphics.Paint;
        r1.<init>();
        r2.l = r1;
        r1 = r2.l;
        r1.setFilterBitmap(r4);
    L_0x0139:
        r1 = r2.l;
        r4 = r2.b;
        r4 = r4.e;
        r1.setAlpha(r4);
        r1 = r2.l;
        r1.setColorFilter(r0);
        r0 = r2.l;
        goto L_0x00d5;
    L_0x014a:
        r2 = r3;
        goto L_0x0063;
        */
    }

    public final int getAlpha() {
        return this.a != null ? DrawableCompat.getAlpha(this.a) : this.c.b.e;
    }

    public final void setAlpha(int i) {
        if (this.a != null) {
            this.a.setAlpha(i);
        } else if (this.c.b.e != i) {
            this.c.b.e = i;
            invalidateSelf();
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.a != null) {
            this.a.setColorFilter(colorFilter);
            return;
        }
        this.f = colorFilter;
        invalidateSelf();
    }

    private PorterDuffColorFilter a(ColorStateList colorStateList, Mode mode) {
        return (colorStateList == null || mode == null) ? null : new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }

    public final void setTint(int i) {
        if (this.a != null) {
            DrawableCompat.setTint(this.a, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    public final void setTintList(ColorStateList colorStateList) {
        if (this.a != null) {
            DrawableCompat.setTintList(this.a, colorStateList);
            return;
        }
        f fVar = this.c;
        if (fVar.c != colorStateList) {
            fVar.c = colorStateList;
            this.e = a(colorStateList, fVar.d);
            invalidateSelf();
        }
    }

    public final void setTintMode(Mode mode) {
        if (this.a != null) {
            DrawableCompat.setTintMode(this.a, mode);
            return;
        }
        f fVar = this.c;
        if (fVar.d != mode) {
            fVar.d = mode;
            this.e = a(fVar.c, mode);
            invalidateSelf();
        }
    }

    public final boolean isStateful() {
        if (this.a != null) {
            return this.a.isStateful();
        }
        return super.isStateful() || !(this.c == null || this.c.c == null || !this.c.c.isStateful());
    }

    protected final boolean onStateChange(int[] iArr) {
        if (this.a != null) {
            return this.a.setState(iArr);
        }
        f fVar = this.c;
        if (fVar.c == null || fVar.d == null) {
            return false;
        }
        this.e = a(fVar.c, fVar.d);
        invalidateSelf();
        return true;
    }

    public final int getOpacity() {
        return this.a != null ? this.a.getOpacity() : AppbarJsBridge.Code_Java_Exception;
    }

    public final int getIntrinsicWidth() {
        return this.a != null ? this.a.getIntrinsicWidth() : (int) this.c.b.a;
    }

    public final int getIntrinsicHeight() {
        return this.a != null ? this.a.getIntrinsicHeight() : (int) this.c.b.b;
    }

    public final boolean canApplyTheme() {
        if (this.a != null) {
            DrawableCompat.canApplyTheme(this.a);
        }
        return false;
    }

    public static g a(Resources resources, int i, Theme theme) {
        if (VERSION.SDK_INT >= 23) {
            g gVar = new g();
            gVar.a = ResourcesCompat.getDrawable(resources, i, theme);
            gVar.h = new g(gVar.a.getConstantState());
            return gVar;
        }
        try {
            XmlPullParser xml = resources.getXml(i);
            AttributeSet asAttributeSet = Xml.asAttributeSet(xml);
            int next;
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return a(resources, xml, asAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (XmlPullParserException e) {
            return null;
        } catch (IOException e2) {
            return null;
        }
    }

    public static g a(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        g gVar = new g();
        gVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return gVar;
    }

    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        if (this.a != null) {
            this.a.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        if (this.a != null) {
            DrawableCompat.inflate(this.a, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        f fVar = this.c;
        fVar.b = new e();
        TypedArray a = a(resources, theme, attributeSet, a.a);
        f fVar2 = this.c;
        e eVar = fVar2.b;
        int a2 = e.a(a, xmlPullParser, "tintMode", R.styleable.Toolbar_contentInsetEnd);
        Mode mode = Mode.SRC_IN;
        switch (a2) {
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                mode = Mode.SRC_OVER;
                break;
            case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                mode = Mode.SRC_IN;
                break;
            case XZBDevice.Pause:
                mode = Mode.SRC_ATOP;
                break;
            case XZBDevice.Predownload:
                mode = Mode.MULTIPLY;
                break;
            case XZBDevice.Delete:
                mode = Mode.SCREEN;
                break;
            case R.styleable.Toolbar_titleMarginBottom:
                mode = Mode.ADD;
                break;
        }
        fVar2.d = mode;
        ColorStateList colorStateList = a.getColorStateList(1);
        if (colorStateList != null) {
            fVar2.c = colorStateList;
        }
        boolean z = fVar2.e;
        if (e.a(xmlPullParser, "autoMirrored")) {
            z = a.getBoolean(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, z);
        }
        fVar2.e = z;
        eVar.c = e.a(a, xmlPullParser, "viewportWidth", (int) R.styleable.Toolbar_contentInsetLeft, eVar.c);
        eVar.d = e.a(a, xmlPullParser, "viewportHeight", (int) XZBDevice.Wait, eVar.d);
        if (eVar.c <= 0.0f) {
            throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        } else if (eVar.d <= 0.0f) {
            throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        } else {
            eVar.a = a.getDimension(XZBDevice.DOWNLOAD_LIST_FAILED, eVar.a);
            eVar.b = a.getDimension(XZBDevice.DOWNLOAD_LIST_RECYCLE, eVar.b);
            if (eVar.a <= 0.0f) {
                throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires width > 0");
            } else if (eVar.b <= 0.0f) {
                throw new XmlPullParserException(a.getPositionDescription() + "<vector> tag requires height > 0");
            } else {
                eVar.e = (int) (e.a(a, xmlPullParser, "alpha", (int) XZBDevice.DOWNLOAD_LIST_ALL, ((float) eVar.e) / 255.0f) * 255.0f);
                String string = a.getString(0);
                if (string != null) {
                    eVar.f = string;
                    eVar.g.put(string, eVar);
                }
                a.recycle();
                fVar.a = getChangingConfigurations();
                fVar.k = true;
                b(resources, xmlPullParser, attributeSet, theme);
                this.e = a(fVar.c, fVar.d);
            }
        }
    }

    private void b(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        f fVar = this.c;
        e eVar = fVar.b;
        Object obj = 1;
        Stack stack = new Stack();
        stack.push(eVar.p);
        int eventType = xmlPullParser.getEventType();
        while (eventType != 1) {
            Object obj2;
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                c cVar = (c) stack.peek();
                if ("path".equals(name)) {
                    d bVar = new b();
                    TypedArray a = f.a(resources, theme, attributeSet, a.c);
                    bVar.a(a, xmlPullParser);
                    a.recycle();
                    cVar.b.add(bVar);
                    if (bVar.n != null) {
                        eVar.g.put(bVar.n, bVar);
                    }
                    obj2 = null;
                    fVar.a = bVar.o | fVar.a;
                } else if ("clip-path".equals(name)) {
                    d aVar = new a();
                    if (e.a(xmlPullParser, "pathData")) {
                        a = f.a(resources, theme, attributeSet, a.d);
                        aVar.a(a);
                        a.recycle();
                    }
                    cVar.b.add(aVar);
                    if (aVar.n != null) {
                        eVar.g.put(aVar.n, aVar);
                    }
                    fVar.a |= aVar.o;
                    obj2 = obj;
                } else {
                    if (WPA.CHAT_TYPE_GROUP.equals(name)) {
                        c cVar2 = new c();
                        a = f.a(resources, theme, attributeSet, a.b);
                        cVar2.l = null;
                        cVar2.c = e.a(a, xmlPullParser, "rotation", (int) XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED, cVar2.c);
                        cVar2.d = a.getFloat(1, cVar2.d);
                        cVar2.e = a.getFloat(XZBDevice.DOWNLOAD_LIST_RECYCLE, cVar2.e);
                        cVar2.f = e.a(a, xmlPullParser, "scaleX", (int) XZBDevice.DOWNLOAD_LIST_FAILED, cVar2.f);
                        cVar2.g = e.a(a, xmlPullParser, "scaleY", (int) XZBDevice.DOWNLOAD_LIST_ALL, cVar2.g);
                        cVar2.h = e.a(a, xmlPullParser, "translateX", (int) R.styleable.Toolbar_contentInsetEnd, cVar2.h);
                        cVar2.i = e.a(a, xmlPullParser, "translateY", (int) R.styleable.Toolbar_contentInsetLeft, cVar2.i);
                        String string = a.getString(0);
                        if (string != null) {
                            cVar2.m = string;
                        }
                        cVar2.j.reset();
                        cVar2.j.postTranslate(-cVar2.d, -cVar2.e);
                        cVar2.j.postScale(cVar2.f, cVar2.g);
                        cVar2.j.postRotate(cVar2.c, AutoScrollHelper.RELATIVE_UNSPECIFIED, AutoScrollHelper.RELATIVE_UNSPECIFIED);
                        cVar2.j.postTranslate(cVar2.h + cVar2.d, cVar2.i + cVar2.e);
                        a.recycle();
                        cVar.b.add(cVar2);
                        stack.push(cVar2);
                        if (cVar2.m != null) {
                            eVar.g.put(cVar2.m, cVar2);
                        }
                        fVar.a |= cVar2.k;
                    }
                    obj2 = obj;
                }
            } else {
                if (eventType == 3) {
                    if (WPA.CHAT_TYPE_GROUP.equals(xmlPullParser.getName())) {
                        stack.pop();
                    }
                }
                obj2 = obj;
            }
            obj = obj2;
            eventType = xmlPullParser.next();
        }
        if (obj != null) {
            StringBuffer stringBuffer = new StringBuffer();
            if (stringBuffer.length() > 0) {
                stringBuffer.append(" or ");
            }
            stringBuffer.append("path");
            throw new XmlPullParserException(new StringBuilder("no ").append(stringBuffer).append(" defined").toString());
        }
    }

    protected final void onBoundsChange(Rect rect) {
        if (this.a != null) {
            this.a.setBounds(rect);
        }
    }

    public final int getChangingConfigurations() {
        return this.a != null ? this.a.getChangingConfigurations() : super.getChangingConfigurations() | this.c.getChangingConfigurations();
    }

    public final void invalidateSelf() {
        if (this.a != null) {
            this.a.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    public final void scheduleSelf(Runnable runnable, long j) {
        if (this.a != null) {
            this.a.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    public final boolean setVisible(boolean z, boolean z2) {
        return this.a != null ? this.a.setVisible(z, z2) : super.setVisible(z, z2);
    }

    public final void unscheduleSelf(Runnable runnable) {
        if (this.a != null) {
            this.a.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }
}
