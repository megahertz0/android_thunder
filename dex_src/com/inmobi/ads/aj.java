package com.inmobi.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build.VERSION;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.inmobi.ads.NativeStrandAsset.AssetType;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.Builder;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import org.android.spdy.SpdyAgent;

// compiled from: NativeStrandViewFactory.java
class aj {
    private static final String a;
    private static final Map<Class, c> c;
    private static volatile WeakReference<aj> e;
    private static volatile Picasso f;
    private int b;
    private Map<c, b> d;

    // compiled from: NativeStrandViewFactory.java
    private abstract class b {
        private LinkedList<View> a;
        private int c;
        private int d;

        protected abstract View a(Context context);

        protected abstract void a(View view, NativeStrandAsset nativeStrandAsset);

        public b() {
            this.a = new LinkedList();
            this.c = 0;
            this.d = 0;
        }

        public boolean a(View view) {
            aj.d(view);
            view.setOnClickListener(null);
            this.a.addLast(view);
            aj.a(aj.this);
            return true;
        }

        public View a(Context context, NativeStrandAsset nativeStrandAsset) {
            View a;
            if (this.a.isEmpty()) {
                this.c++;
                a = a(context);
            } else {
                this.d++;
                a = (View) this.a.removeFirst();
                aj.b(aj.this);
            }
            a(a, nativeStrandAsset);
            return a;
        }

        public int a() {
            return this.a.size();
        }

        public void b() {
            if (this.a.size() > 0) {
                this.a.removeFirst();
            }
        }

        public String toString() {
            return new StringBuilder("Size:").append(this.a.size()).append(" Miss Count:").append(this.c).append(" Hit Count:").append(this.d).toString();
        }
    }

    // compiled from: NativeStrandViewFactory.java
    /* synthetic */ class AnonymousClass_7 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;

        static {
            b = new int[a.values().length];
            try {
                b[a.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[a.c.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[a.d.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[a.e.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            a = new int[AssetType.values().length];
            try {
                a[AssetType.ASSET_TYPE_TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[AssetType.ASSET_TYPE_IMAGE.ordinal()] = 2;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[AssetType.ASSET_TYPE_ICON.ordinal()] = 3;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[AssetType.ASSET_TYPE_CTA.ordinal()] = 4;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    // compiled from: NativeStrandViewFactory.java
    private static final class a extends TextView {
        public a(Context context) {
            super(context);
        }

        public final boolean onTouchEvent(MotionEvent motionEvent) {
            return false;
        }
    }

    // compiled from: NativeStrandViewFactory.java
    private enum c {
        ROOT_CONTAINER,
        SCROLLABLE_DECK_HORIZONTAL,
        CONTAINER,
        TEXT,
        BUTTON,
        IMAGE;

        static {
            a = new c("ROOT_CONTAINER", 0);
            b = new c("SCROLLABLE_DECK_HORIZONTAL", 1);
            c = new c("CONTAINER", 2);
            d = new c("TEXT", 3);
            e = new c("BUTTON", 4);
            f = new c("IMAGE", 5);
            g = new c[]{a, b, c, d, e, f};
        }
    }

    static /* synthetic */ int a(aj ajVar) {
        int i = ajVar.b;
        ajVar.b = i + 1;
        return i;
    }

    static /* synthetic */ int b(aj ajVar) {
        int i = ajVar.b;
        ajVar.b = i - 1;
        return i;
    }

    static {
        a = aj.class.getSimpleName();
        Map hashMap = new HashMap();
        c = hashMap;
        hashMap.put(ag.class, c.a);
        c.put(ao.class, c.b);
        c.put(NativeStrandContainerLayout.class, c.c);
        c.put(ImageView.class, c.f);
        c.put(a.class, c.d);
        c.put(Button.class, c.e);
    }

    private aj() {
        this.d = new HashMap();
        this.d.put(c.a, new b() {
            protected View a(Context context) {
                return new ag(context.getApplicationContext());
            }

            protected void a(View view, NativeStrandAsset nativeStrandAsset) {
                aj.a(view, nativeStrandAsset.b());
            }
        });
        this.d.put(c.c, new b() {
            protected View a(Context context) {
                return new NativeStrandContainerLayout(context.getApplicationContext());
            }

            protected void a(View view, NativeStrandAsset nativeStrandAsset) {
                aj.a(view, nativeStrandAsset.b());
            }
        });
        this.d.put(c.b, new b() {
            protected View a(Context context) {
                return new ao(context.getApplicationContext());
            }

            protected void a(View view, NativeStrandAsset nativeStrandAsset) {
                aj.a(view, nativeStrandAsset.b());
            }

            public boolean a(View view) {
                ((ao) view).a();
                return super.a(view);
            }
        });
        this.d.put(c.f, new b() {
            protected View a(Context context) {
                return new ImageView(context.getApplicationContext());
            }

            protected void a(View view, NativeStrandAsset nativeStrandAsset) {
                aj.this.a((ImageView) view, nativeStrandAsset);
            }

            public boolean a(View view) {
                if (!(view instanceof ImageView)) {
                    return false;
                }
                ((ImageView) view).setImageDrawable(null);
                return super.a(view);
            }
        });
        this.d.put(c.d, new b() {
            protected View a(Context context) {
                return new a(context);
            }

            protected void a(View view, NativeStrandAsset nativeStrandAsset) {
                aj.this.a((TextView) view, nativeStrandAsset);
            }

            public boolean a(View view) {
                if (!(view instanceof TextView)) {
                    return false;
                }
                aj.b((TextView) view);
                return super.a(view);
            }
        });
        this.d.put(c.e, new b() {
            protected View a(Context context) {
                return new Button(context.getApplicationContext());
            }

            protected void a(View view, NativeStrandAsset nativeStrandAsset) {
                aj.b((Button) view, nativeStrandAsset);
            }

            public boolean a(View view) {
                if (!(view instanceof Button)) {
                    return false;
                }
                aj.b((Button) view);
                return super.a(view);
            }
        });
    }

    public static aj a(Context context) {
        aj ajVar;
        if (e == null) {
            ajVar = null;
        } else {
            ajVar = (aj) e.get();
        }
        if (ajVar == null) {
            synchronized (aj.class) {
                ajVar = e == null ? null : (aj) e.get();
                if (ajVar == null) {
                    ajVar = new aj();
                    e = new WeakReference(ajVar);
                    b(context.getApplicationContext());
                }
            }
        }
        return ajVar;
    }

    private static void b(Context context) {
        if (f == null) {
            synchronized (aj.class) {
                if (f == null) {
                    f = new Builder(context.getApplicationContext()).build();
                }
            }
        }
    }

    public View a(Context context, NativeStrandAsset nativeStrandAsset) {
        c a = a(nativeStrandAsset);
        if (a != null) {
            return ((b) this.d.get(a)).a(context, nativeStrandAsset);
        }
        Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Unsupported AssetType:").append(nativeStrandAsset.a().toString()).append(" failed to instantiate view ").toString());
        return null;
    }

    public void a(ViewGroup viewGroup) {
        for (int childCount = viewGroup.getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = viewGroup.getChildAt(childCount);
            viewGroup.removeViewAt(childCount);
            a(childAt);
        }
    }

    public void a(View view) {
        if ((view instanceof ag) || (view instanceof NativeStrandContainerLayout)) {
            NativeStrandContainerLayout nativeStrandContainerLayout = (NativeStrandContainerLayout) view;
            if (nativeStrandContainerLayout.getChildCount() != 0) {
                Stack stack = new Stack();
                stack.push(nativeStrandContainerLayout);
                while (!stack.isEmpty()) {
                    nativeStrandContainerLayout = (NativeStrandContainerLayout) stack.pop();
                    for (int childCount = nativeStrandContainerLayout.getChildCount() - 1; childCount >= 0; childCount--) {
                        View childAt = nativeStrandContainerLayout.getChildAt(childCount);
                        nativeStrandContainerLayout.removeViewAt(childCount);
                        if (childAt instanceof NativeStrandContainerLayout) {
                            stack.push((NativeStrandContainerLayout) childAt);
                        } else {
                            c(childAt);
                        }
                    }
                    c(nativeStrandContainerLayout);
                }
                return;
            }
        }
        c(view);
    }

    private c a(NativeStrandAsset nativeStrandAsset) {
        if (nativeStrandAsset instanceof v) {
            v vVar = (v) nativeStrandAsset;
            if (vVar.o()) {
                return c.a;
            }
            return vVar.p() ? c.b : c.c;
        } else {
            switch (AnonymousClass_7.a[nativeStrandAsset.a().ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    return c.d;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    return c.f;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    return c.e;
                default:
                    return null;
            }
        }
    }

    private void c(View view) {
        c cVar = (c) c.get(view.getClass());
        if (cVar == null) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("View type unknown, ignoring recycle:").append(view).toString());
            return;
        }
        b bVar = (b) this.d.get(cVar);
        if (bVar == null) {
            Logger.a(InternalLogLevel.INTERNAL, a, new StringBuilder("Unsupported AssetType:").append(cVar).append(" failed to recycle view").toString());
            return;
        }
        if (this.b >= 300) {
            a();
        }
        bVar.a(view);
    }

    private void a() {
        b b = b();
        if (b != null) {
            b.b();
        }
    }

    private b b() {
        Object obj = null;
        b bVar = null;
        for (Entry entry : this.d.entrySet()) {
            b bVar2;
            int a;
            if (((b) entry.getValue()).a() > r3) {
                bVar2 = (b) entry.getValue();
                a = bVar2.a();
            } else {
                bVar2 = bVar;
                a = r3;
            }
            bVar = bVar2;
            int i = a;
        }
        return bVar;
    }

    private void a(ImageView imageView, NativeStrandAsset nativeStrandAsset) {
        String str = (String) nativeStrandAsset.d();
        if (str != null) {
            int i = nativeStrandAsset.b().a().x;
            int i2 = nativeStrandAsset.b().a().y;
            if (i > 0 && i2 > 0 && str.length() != 0) {
                f.load(str).resize(i, i2).into(imageView);
            }
            a((View) imageView, nativeStrandAsset);
            a((View) imageView, nativeStrandAsset.b());
        }
    }

    @TargetApi(17)
    private void a(TextView textView, NativeStrandAsset nativeStrandAsset) {
        t tVar = (a) nativeStrandAsset.b();
        textView.setLayoutParams(new LayoutParams(tVar.a().x, tVar.a().y));
        textView.setText((CharSequence) nativeStrandAsset.d());
        textView.setTypeface(Typeface.DEFAULT);
        textView.setTextSize(1, (float) tVar.h());
        int parseColor = Color.parseColor("#ff000000");
        try {
            parseColor = Color.parseColor(tVar.i());
        } catch (IllegalArgumentException e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error parsing color for text asset! Will fallback to default text color");
        }
        textView.setTextColor(parseColor);
        parseColor = Color.parseColor("#00000000");
        try {
            parseColor = Color.parseColor(tVar.g());
        } catch (IllegalArgumentException e2) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error parsing background color for text asset! Will fallback to default background color");
        }
        textView.setBackgroundColor(parseColor);
        if (VERSION.SDK_INT >= 17) {
            textView.setTextAlignment(1);
        }
        textView.setGravity(R.styleable.Toolbar_titleMarginBottom);
        a(textView, tVar.k());
        if (tVar.j() != Integer.MAX_VALUE) {
            textView.setEllipsize(TruncateAt.MARQUEE);
        }
        a((View) textView, tVar);
    }

    private static void a(TextView textView, a[] aVarArr) {
        int paintFlags = textView.getPaintFlags();
        int length = aVarArr.length;
        int i = paintFlags;
        paintFlags = 0;
        int i2 = i;
        for (int i3 = 0; i3 < length; i3++) {
            switch (AnonymousClass_7.b[aVarArr[i3].ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    paintFlags |= 1;
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    paintFlags |= 2;
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    i2 |= 16;
                    break;
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    i2 |= 8;
                    break;
                default:
                    break;
            }
        }
        textView.setTypeface(Typeface.DEFAULT, paintFlags);
        textView.setPaintFlags(i2);
    }

    @TargetApi(17)
    private static Button b(Button button, NativeStrandAsset nativeStrandAsset) {
        t tVar = (a) nativeStrandAsset.b();
        button.setLayoutParams(new LayoutParams(tVar.a().x, tVar.a().y));
        button.setText((CharSequence) nativeStrandAsset.d());
        button.setTextSize(1, (float) tVar.h());
        int parseColor = Color.parseColor("#ff000000");
        try {
            parseColor = Color.parseColor(tVar.i());
        } catch (IllegalArgumentException e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error parsing color for CTA asset! Will fallback to default text color");
        }
        button.setTextColor(parseColor);
        parseColor = Color.parseColor("#00000000");
        try {
            parseColor = Color.parseColor(tVar.g());
        } catch (IllegalArgumentException e2) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error parsing background color for CTA asset! Will fallback to default background color");
        }
        button.setBackgroundColor(parseColor);
        if (VERSION.SDK_INT >= 17) {
            button.setTextAlignment(XZBDevice.DOWNLOAD_LIST_ALL);
        }
        button.setGravity(R.styleable.Toolbar_maxButtonHeight);
        a((TextView) button, tVar.k());
        a((View) button, tVar);
        return button;
    }

    private void a(View view, NativeStrandAsset nativeStrandAsset) {
        int i;
        int i2;
        int i3 = 1;
        NativeStrandAsset k = nativeStrandAsset.k();
        int i4 = 0;
        if (k == null || b.b != k.b().c()) {
            i3 = 0;
            i = 0;
            i2 = 0;
        } else {
            int i5;
            if (k.b().b().x == nativeStrandAsset.b().b().x) {
                i2 = 1;
            } else {
                i2 = 0;
            }
            if (k.b().a().x == nativeStrandAsset.b().a().x + nativeStrandAsset.b().b().x) {
                i5 = 1;
            } else {
                i5 = 0;
            }
            if (k.b().b().y == nativeStrandAsset.b().b().y) {
                i = 1;
            } else {
                i = 0;
            }
            if (k.b().a().y == nativeStrandAsset.b().a().y + nativeStrandAsset.b().b().y) {
                i4 = 1;
            }
            if (k.b().a().x == nativeStrandAsset.b().a().x) {
                i2 = 1;
            } else {
                i3 = i5;
            }
        }
        if (VERSION.SDK_INT < 17) {
            view.setPadding(i2, i, i3, i4);
        } else {
            view.setPaddingRelative(i2, i, i3, i4);
        }
    }

    @TargetApi(21)
    static void a(View view, t tVar) {
        int parseColor = Color.parseColor("#00000000");
        try {
            parseColor = Color.parseColor(tVar.g());
        } catch (IllegalArgumentException e) {
            Logger.a(InternalLogLevel.INTERNAL, a, "Error parsing background color for container! Will fallback to default background color");
        }
        view.setBackgroundColor(parseColor);
        if (b.b == tVar.c()) {
            Drawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(parseColor);
            if (a.a == tVar.d()) {
                gradientDrawable.setCornerRadius(tVar.e());
            }
            parseColor = Color.parseColor("#ff000000");
            try {
                parseColor = Color.parseColor(tVar.f());
            } catch (IllegalArgumentException e2) {
                Logger.a(InternalLogLevel.INTERNAL, a, "Error parsing border color for container! Will fallback to default border color");
            }
            gradientDrawable.setStroke(1, parseColor);
            if (VERSION.SDK_INT < 16) {
                view.setBackgroundDrawable(gradientDrawable);
            } else {
                view.setBackground(gradientDrawable);
            }
        }
    }

    private static void b(TextView textView) {
        textView.setTypeface(Typeface.DEFAULT, 0);
        textView.setPaintFlags(textView.getPaintFlags() & -17);
        textView.setPaintFlags(textView.getPaintFlags() & -9);
    }

    private static void d(View view) {
        if (VERSION.SDK_INT < 16) {
            view.setBackgroundDrawable(null);
        } else {
            view.setBackground(null);
        }
    }

    public static LayoutParams a(NativeStrandAsset nativeStrandAsset, ViewGroup viewGroup) {
        Point a = nativeStrandAsset.b().a();
        Point b = nativeStrandAsset.b().b();
        LayoutParams layoutParams = new LayoutParams(a.x, a.y);
        if (viewGroup instanceof NativeStrandContainerLayout) {
            layoutParams = new com.inmobi.ads.NativeStrandContainerLayout.a(a.x, a.y);
            ((com.inmobi.ads.NativeStrandContainerLayout.a) layoutParams).a(b.x, b.y);
            return layoutParams;
        } else if (viewGroup instanceof LinearLayout) {
            layoutParams = new LinearLayout.LayoutParams(a.x, a.y);
            ((LinearLayout.LayoutParams) layoutParams).setMargins(b.x, b.y, 0, 0);
            return layoutParams;
        } else if (viewGroup instanceof AbsListView) {
            return new AbsListView.LayoutParams(a.x, a.y);
        } else {
            if (viewGroup instanceof FrameLayout) {
                layoutParams = new FrameLayout.LayoutParams(a.x, a.y);
                ((FrameLayout.LayoutParams) layoutParams).setMargins(b.x, b.y, 0, 0);
                return layoutParams;
            }
            String str;
            InternalLogLevel internalLogLevel = InternalLogLevel.INTERNAL;
            String str2 = a;
            StringBuilder stringBuilder = new StringBuilder("Could not set layout params for Parent:");
            if (viewGroup == null) {
                str = "null";
            } else {
                str = viewGroup.getClass().getSimpleName();
            }
            Logger.a(internalLogLevel, str2, stringBuilder.append(str).toString());
            return layoutParams;
        }
    }
}
