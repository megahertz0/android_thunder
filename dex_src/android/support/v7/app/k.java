package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.appcompat.R;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: AlertDialog.java
public final class k extends aa implements DialogInterface {
    private a a;

    // compiled from: AlertDialog.java
    public static class a {
        public final android.support.v7.app.a.a a;
        public int b;

        public a(Context context) {
            this(context, k.a(context, 0));
        }

        private a(Context context, int i) {
            this.a = new android.support.v7.app.a.a(new ContextThemeWrapper(context, k.a(context, i)));
            this.b = i;
        }
    }

    public k(Context context, int i) {
        super(context, a(context, i));
        this.a = new a(getContext(), this, getWindow());
    }

    static int a(Context context, int i) {
        if (i >= 16777216) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    public final void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.a.a(charSequence);
    }

    protected final void onCreate(Bundle bundle) {
        int i;
        View view;
        int indexOfChild;
        super.onCreate(bundle);
        a aVar = this.a;
        if (aVar.G == 0 || aVar.L != 1) {
            i = aVar.F;
        } else {
            i = aVar.G;
        }
        aVar.b.setContentView(i);
        View findViewById = aVar.c.findViewById(R.id.parentPanel);
        View findViewById2 = findViewById.findViewById(R.id.topPanel);
        View findViewById3 = findViewById.findViewById(R.id.contentPanel);
        View findViewById4 = findViewById.findViewById(R.id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) findViewById.findViewById(R.id.customPanel);
        if (aVar.g != null) {
            view = aVar.g;
        } else if (aVar.h != 0) {
            view = LayoutInflater.from(aVar.a).inflate(aVar.h, viewGroup, false);
        } else {
            view = null;
        }
        Object obj = view != null ? 1 : null;
        if (obj == null || !a.a(view)) {
            aVar.c.setFlags(AccessibilityNodeInfoCompat.ACTION_SET_SELECTION, AccessibilityNodeInfoCompat.ACTION_SET_SELECTION);
        }
        if (obj != null) {
            FrameLayout frameLayout = (FrameLayout) aVar.c.findViewById(R.id.custom);
            frameLayout.addView(view, new LayoutParams(-1, -1));
            if (aVar.m) {
                frameLayout.setPadding(aVar.i, aVar.j, aVar.k, aVar.l);
            }
            if (aVar.f != null) {
                ((LinearLayout.LayoutParams) viewGroup.getLayoutParams()).weight = 0.0f;
            }
        } else {
            viewGroup.setVisibility(XZBDevice.Wait);
        }
        View findViewById5 = viewGroup.findViewById(R.id.topPanel);
        view = viewGroup.findViewById(R.id.contentPanel);
        View findViewById6 = viewGroup.findViewById(R.id.buttonPanel);
        ViewGroup a = a.a(findViewById5, findViewById2);
        ViewGroup a2 = a.a(view, findViewById3);
        ViewGroup a3 = a.a(findViewById6, findViewById4);
        aVar.w = (NestedScrollView) aVar.c.findViewById(R.id.scrollView);
        aVar.w.setFocusable(false);
        aVar.w.setNestedScrollingEnabled(false);
        aVar.B = (TextView) a2.findViewById(16908299);
        if (aVar.B != null) {
            if (aVar.e != null) {
                aVar.B.setText(aVar.e);
            } else {
                aVar.B.setVisibility(XZBDevice.Wait);
                aVar.w.removeView(aVar.B);
                if (aVar.f != null) {
                    ViewGroup viewGroup2 = (ViewGroup) aVar.w.getParent();
                    indexOfChild = viewGroup2.indexOfChild(aVar.w);
                    viewGroup2.removeViewAt(indexOfChild);
                    viewGroup2.addView(aVar.f, indexOfChild, new LayoutParams(-1, -1));
                } else {
                    a2.setVisibility(XZBDevice.Wait);
                }
            }
        }
        indexOfChild = 0;
        aVar.n = (Button) a3.findViewById(16908313);
        aVar.n.setOnClickListener(aVar.N);
        if (TextUtils.isEmpty(aVar.o)) {
            aVar.n.setVisibility(XZBDevice.Wait);
        } else {
            aVar.n.setText(aVar.o);
            aVar.n.setVisibility(0);
            indexOfChild = 1;
        }
        aVar.q = (Button) a3.findViewById(16908314);
        aVar.q.setOnClickListener(aVar.N);
        if (TextUtils.isEmpty(aVar.r)) {
            aVar.q.setVisibility(XZBDevice.Wait);
        } else {
            aVar.q.setText(aVar.r);
            aVar.q.setVisibility(0);
            indexOfChild |= 2;
        }
        aVar.t = (Button) a3.findViewById(16908315);
        aVar.t.setOnClickListener(aVar.N);
        if (TextUtils.isEmpty(aVar.u)) {
            aVar.t.setVisibility(XZBDevice.Wait);
        } else {
            aVar.t.setText(aVar.u);
            aVar.t.setVisibility(0);
            indexOfChild |= 4;
        }
        if ((indexOfChild != 0 ? 1 : null) == null) {
            a3.setVisibility(XZBDevice.Wait);
        }
        if (aVar.C != null) {
            a.addView(aVar.C, 0, new LayoutParams(-1, -2));
            aVar.c.findViewById(R.id.title_template).setVisibility(XZBDevice.Wait);
        } else {
            aVar.z = (ImageView) aVar.c.findViewById(16908294);
            if ((!TextUtils.isEmpty(aVar.d) ? 1 : null) != null) {
                aVar.A = (TextView) aVar.c.findViewById(R.id.alertTitle);
                aVar.A.setText(aVar.d);
                if (aVar.x != 0) {
                    aVar.z.setImageResource(aVar.x);
                } else if (aVar.y != null) {
                    aVar.z.setImageDrawable(aVar.y);
                } else {
                    aVar.A.setPadding(aVar.z.getPaddingLeft(), aVar.z.getPaddingTop(), aVar.z.getPaddingRight(), aVar.z.getPaddingBottom());
                    aVar.z.setVisibility(XZBDevice.Wait);
                }
            } else {
                aVar.c.findViewById(R.id.title_template).setVisibility(XZBDevice.Wait);
                aVar.z.setVisibility(XZBDevice.Wait);
                a.setVisibility(XZBDevice.Wait);
            }
        }
        if (viewGroup == null || viewGroup.getVisibility() == 8) {
            Object obj2 = null;
        } else {
            indexOfChild = 1;
        }
        if (a == null || a.getVisibility() == 8) {
            obj = null;
        } else {
            int i2 = 1;
        }
        if (a3 == null || a3.getVisibility() == 8) {
            Object obj3 = null;
        } else {
            int i3 = 1;
        }
        if (obj3 == null && a2 != null) {
            findViewById = a2.findViewById(R.id.textSpacerNoButtons);
            if (findViewById != null) {
                findViewById.setVisibility(0);
            }
        }
        if (!(obj == null || aVar.w == null)) {
            aVar.w.setClipToPadding(true);
        }
        if (obj2 == null) {
            view = aVar.f != null ? aVar.f : aVar.w;
            if (view != null) {
                if (obj != null) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                i3 = i2 | (obj3 != null ? XZBDevice.DOWNLOAD_LIST_RECYCLE : 0);
                findViewById5 = aVar.c.findViewById(R.id.scrollIndicatorUp);
                findViewById = aVar.c.findViewById(R.id.scrollIndicatorDown);
                if (VERSION.SDK_INT >= 23) {
                    ViewCompat.setScrollIndicators(view, i3, XZBDevice.DOWNLOAD_LIST_FAILED);
                    if (findViewById5 != null) {
                        a2.removeView(findViewById5);
                    }
                    if (findViewById != null) {
                        a2.removeView(findViewById);
                    }
                } else {
                    if (findViewById5 != null && (i3 & 1) == 0) {
                        a2.removeView(findViewById5);
                        findViewById5 = null;
                    }
                    if (findViewById != null && (i3 & 2) == 0) {
                        a2.removeView(findViewById);
                        findViewById = null;
                    }
                    if (!(findViewById5 == null && findViewById == null)) {
                        if (aVar.e != null) {
                            aVar.w.setOnScrollChangeListener(new c(aVar, findViewById5, findViewById));
                            aVar.w.post(new d(aVar, findViewById5, findViewById));
                        } else if (aVar.f != null) {
                            aVar.f.setOnScrollListener(new e(aVar, findViewById5, findViewById));
                            aVar.f.post(new f(aVar, findViewById5, findViewById));
                        } else {
                            if (findViewById5 != null) {
                                a2.removeView(findViewById5);
                            }
                            if (findViewById != null) {
                                a2.removeView(findViewById);
                            }
                        }
                    }
                }
            }
        }
        ListView listView = aVar.f;
        if (listView != null && aVar.D != null) {
            listView.setAdapter(aVar.D);
            i2 = aVar.E;
            if (i2 >= 0) {
                listView.setItemChecked(i2, true);
                listView.setSelection(i2);
            }
        }
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z;
        a aVar = this.a;
        if (aVar.w == null || !aVar.w.executeKeyEvent(keyEvent)) {
            Object obj = null;
        } else {
            z = true;
        }
        return z ? true : super.onKeyDown(i, keyEvent);
    }

    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        boolean z;
        a aVar = this.a;
        if (aVar.w == null || !aVar.w.executeKeyEvent(keyEvent)) {
            Object obj = null;
        } else {
            z = true;
        }
        return z ? true : super.onKeyUp(i, keyEvent);
    }
}
