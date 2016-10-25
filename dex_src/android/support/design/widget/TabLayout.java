package android.support.design.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout.a;
import android.support.design.widget.TabLayout.d;
import android.support.v4.util.Pools.Pool;
import android.support.v4.util.Pools.SimplePool;
import android.support.v4.util.Pools.SynchronizedPool;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.AutoScrollHelper;
import android.support.v4.widget.ExploreByTouchHelper;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.r;
import android.text.Layout;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import org.android.spdy.SpdyAgent;

public class TabLayout extends HorizontalScrollView {
    private static final Pool<d> a;
    private final Pool<f> A;
    private final ArrayList<d> b;
    private d c;
    private final c d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private ColorStateList j;
    private float k;
    private float l;
    private final int m;
    private int n;
    private final int o;
    private final int p;
    private final int q;
    private int r;
    private int s;
    private int t;
    private a u;
    private bf v;
    private ViewPager w;
    private PagerAdapter x;
    private DataSetObserver y;
    private e z;

    public static interface a {
        void a(d dVar);
    }

    private class b extends DataSetObserver {
        private b() {
        }

        public final void onChanged() {
            TabLayout.this.c();
        }

        public final void onInvalidated() {
            TabLayout.this.c();
        }
    }

    private class c extends LinearLayout {
        int a;
        float b;
        bf c;
        private int e;
        private final Paint f;
        private int g;
        private int h;

        c(Context context) {
            super(context);
            this.a = -1;
            this.g = -1;
            this.h = -1;
            setWillNotDraw(false);
            this.f = new Paint();
        }

        final void a(int i) {
            if (this.f.getColor() != i) {
                this.f.setColor(i);
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        final void b(int i) {
            if (this.e != i) {
                this.e = i;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        protected final void onMeasure(int i, int i2) {
            Object obj = null;
            super.onMeasure(i, i2);
            if (MeasureSpec.getMode(i) == 1073741824 && TabLayout.this.t == 1 && TabLayout.this.s == 1) {
                int max;
                int childCount = getChildCount();
                int i3 = 0;
                int i4 = 0;
                while (i3 < childCount) {
                    View childAt = getChildAt(i3);
                    if (childAt.getVisibility() == 0) {
                        max = Math.max(i4, childAt.getMeasuredWidth());
                    } else {
                        max = i4;
                    }
                    i3++;
                    i4 = max;
                }
                if (i4 > 0) {
                    if (i4 * childCount <= getMeasuredWidth() - (TabLayout.this.b((int) R.styleable.Toolbar_titleMarginBottom) * 2)) {
                        i3 = 0;
                        while (i3 < childCount) {
                            Object obj2;
                            LayoutParams layoutParams = (LayoutParams) getChildAt(i3).getLayoutParams();
                            if (layoutParams.width == i4 && layoutParams.weight == 0.0f) {
                                obj2 = obj;
                            } else {
                                layoutParams.width = i4;
                                layoutParams.weight = 0.0f;
                                max = 1;
                            }
                            i3++;
                            obj = obj2;
                        }
                    } else {
                        TabLayout.this.s = 0;
                        TabLayout.this.a(false);
                        int i5 = 1;
                    }
                    if (obj != null) {
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            if (this.c == null || !this.c.a.b()) {
                a();
                return;
            }
            this.c.a.e();
            a(this.a, Math.round(((float) this.c.a.g()) * (1.0f - this.c.a.f())));
        }

        final void a() {
            int i;
            int i2;
            View childAt = getChildAt(this.a);
            if (childAt == null || childAt.getWidth() <= 0) {
                i = -1;
                i2 = -1;
            } else {
                i = childAt.getLeft();
                i2 = childAt.getRight();
                if (this.b > 0.0f && this.a < getChildCount() - 1) {
                    View childAt2 = getChildAt(this.a + 1);
                    i = (int) ((((float) i) * (1.0f - this.b)) + (this.b * ((float) childAt2.getLeft())));
                    i2 = (int) ((((float) i2) * (1.0f - this.b)) + (((float) childAt2.getRight()) * this.b));
                }
            }
            b(i, i2);
        }

        private void b(int i, int i2) {
            if (i != this.g || i2 != this.h) {
                this.g = i;
                this.h = i2;
                ViewCompat.postInvalidateOnAnimation(this);
            }
        }

        final void a(int i, int i2) {
            if (this.c != null && this.c.a.b()) {
                this.c.a.e();
            }
            if (ViewCompat.getLayoutDirection(this) == 1) {
                int i3 = 1;
            } else {
                Object obj = null;
            }
            View childAt = getChildAt(i);
            if (childAt == null) {
                a();
                return;
            }
            int i4;
            int i5;
            int left = childAt.getLeft();
            int right = childAt.getRight();
            if (Math.abs(i - this.a) <= 1) {
                i4 = this.g;
                i5 = this.h;
            } else {
                int a = TabLayout.this.b((int) R.styleable.Toolbar_subtitleTextColor);
                if (i < this.a) {
                    if (obj == null) {
                        i5 = right + a;
                        i4 = i5;
                    }
                } else if (obj != null) {
                    i5 = right + a;
                    i4 = i5;
                }
                i5 = left - a;
                i4 = i5;
            }
            if (i4 != left || i5 != right) {
                bf a2 = bq.a();
                this.c = a2;
                a2.a(a.b);
                a2.a(i2);
                a2.a((float) AutoScrollHelper.RELATIVE_UNSPECIFIED, 1.0f);
                a2.a(new ax(this, i4, left, i5, right));
                a2.a.a(new bh(a2, new ay(this, i)));
                a2.a.a();
            }
        }

        public final void draw(Canvas canvas) {
            super.draw(canvas);
            if (this.g >= 0 && this.h > this.g) {
                canvas.drawRect((float) this.g, (float) (getHeight() - this.e), (float) this.h, (float) getHeight(), this.f);
            }
        }
    }

    public static final class d {
        Object a;
        Drawable b;
        CharSequence c;
        CharSequence d;
        int e;
        View f;
        TabLayout g;
        f h;

        private d() {
            this.e = -1;
        }

        public final android.support.design.widget.TabLayout.d a(CharSequence charSequence) {
            this.c = charSequence;
            b();
            return this;
        }

        public final android.support.design.widget.TabLayout.d a(int i) {
            if (this.g != null) {
                return a(this.g.getResources().getText(i));
            }
            throw new IllegalArgumentException("Tab not attached to a TabLayout");
        }

        public final void a() {
            if (this.g == null) {
                throw new IllegalArgumentException("Tab not attached to a TabLayout");
            }
            this.g.a(this, true);
        }

        final void b() {
            if (this.h != null) {
                this.h.a();
            }
        }
    }

    public static class e implements OnPageChangeListener {
        private final WeakReference<TabLayout> a;
        private int b;
        private int c;

        public e(TabLayout tabLayout) {
            this.a = new WeakReference(tabLayout);
        }

        public final void onPageScrollStateChanged(int i) {
            this.b = this.c;
            this.c = i;
        }

        public final void onPageScrolled(int i, float f, int i2) {
            boolean z = false;
            TabLayout tabLayout = (TabLayout) this.a.get();
            if (tabLayout != null) {
                boolean z2 = this.c != 2 || this.b == 1;
                if (!(this.c == 2 && this.b == 0)) {
                    z = true;
                }
                tabLayout.a(i, f, z2, z);
            }
        }

        public final void onPageSelected(int i) {
            TabLayout tabLayout = (TabLayout) this.a.get();
            if (tabLayout != null && tabLayout.getSelectedTabPosition() != i) {
                boolean z = this.c == 0 || (this.c == 2 && this.b == 0);
                tabLayout.a(tabLayout.a(i), z);
            }
        }

        static /* synthetic */ void a(android.support.design.widget.TabLayout.e eVar) {
            eVar.c = 0;
            eVar.b = 0;
        }
    }

    class f extends LinearLayout implements OnLongClickListener {
        private d b;
        private TextView c;
        private ImageView d;
        private View e;
        private TextView f;
        private ImageView g;
        private int h;

        public f(Context context) {
            super(context);
            this.h = 2;
            if (TabLayout.this.m != 0) {
                setBackgroundDrawable(r.a().a(context, TabLayout.this.m, false));
            }
            ViewCompat.setPaddingRelative(this, TabLayout.this.e, TabLayout.this.f, TabLayout.this.g, TabLayout.this.h);
            setGravity(R.styleable.Toolbar_maxButtonHeight);
            setOrientation(1);
            setClickable(true);
        }

        public final boolean performClick() {
            boolean performClick = super.performClick();
            if (this.b == null) {
                return performClick;
            }
            this.b.a();
            return true;
        }

        public final void setSelected(boolean z) {
            Object obj = isSelected() != z ? 1 : null;
            super.setSelected(z);
            if (obj != null && z) {
                sendAccessibilityEvent(XZBDevice.DOWNLOAD_LIST_ALL);
                if (this.c != null) {
                    this.c.setSelected(z);
                }
                if (this.d != null) {
                    this.d.setSelected(z);
                }
            }
        }

        @TargetApi(14)
        public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
            super.onInitializeAccessibilityEvent(accessibilityEvent);
            accessibilityEvent.setClassName(android.support.v7.app.ActionBar.a.class.getName());
        }

        @TargetApi(14)
        public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
            super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
            accessibilityNodeInfo.setClassName(android.support.v7.app.ActionBar.a.class.getName());
        }

        public final void onMeasure(int i, int i2) {
            Object obj = 1;
            int size = MeasureSpec.getSize(i);
            int mode = MeasureSpec.getMode(i);
            int f = TabLayout.this.getTabMaxWidth();
            if (f > 0) {
                if (mode == 0 || size > f) {
                    i = MeasureSpec.makeMeasureSpec(TabLayout.this.n, ExploreByTouchHelper.INVALID_ID);
                }
            }
            super.onMeasure(i, i2);
            if (this.c != null) {
                getResources();
                float h = TabLayout.this.k;
                size = this.h;
                if (this.d != null && this.d.getVisibility() == 0) {
                    size = 1;
                } else if (this.c != null && this.c.getLineCount() > 1) {
                    h = TabLayout.this.l;
                }
                float textSize = this.c.getTextSize();
                int lineCount = this.c.getLineCount();
                int maxLines = TextViewCompat.getMaxLines(this.c);
                if (h != textSize || (maxLines >= 0 && size != maxLines)) {
                    int i3;
                    if (TabLayout.this.t == 1 && h > textSize && lineCount == 1) {
                        Layout layout = this.c.getLayout();
                        if (layout == null || layout.getLineWidth(0) * (h / layout.getPaint().getTextSize()) > ((float) layout.getWidth())) {
                            i3 = 0;
                        }
                    }
                    if (i3 != 0) {
                        this.c.setTextSize(0, h);
                        this.c.setMaxLines(size);
                        super.onMeasure(i, i2);
                    }
                }
            }
        }

        private void a(d dVar) {
            if (dVar != this.b) {
                this.b = dVar;
                a();
            }
        }

        final void a() {
            View view;
            d dVar = this.b;
            if (dVar != null) {
                view = dVar.f;
            } else {
                view = null;
            }
            if (view != null) {
                f parent = view.getParent();
                if (parent != this) {
                    if (parent != null) {
                        parent.removeView(view);
                    }
                    addView(view);
                }
                this.e = view;
                if (this.c != null) {
                    this.c.setVisibility(XZBDevice.Wait);
                }
                if (this.d != null) {
                    this.d.setVisibility(XZBDevice.Wait);
                    this.d.setImageDrawable(null);
                }
                this.f = (TextView) view.findViewById(16908308);
                if (this.f != null) {
                    this.h = TextViewCompat.getMaxLines(this.f);
                }
                this.g = (ImageView) view.findViewById(16908294);
            } else {
                if (this.e != null) {
                    removeView(this.e);
                    this.e = null;
                }
                this.f = null;
                this.g = null;
            }
            if (this.e == null) {
                if (this.d == null) {
                    ImageView imageView = (ImageView) LayoutInflater.from(getContext()).inflate(android.support.design.R.layout.design_layout_tab_icon, this, false);
                    addView(imageView, 0);
                    this.d = imageView;
                }
                if (this.c == null) {
                    TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(android.support.design.R.layout.design_layout_tab_text, this, false);
                    addView(textView);
                    this.c = textView;
                    this.h = TextViewCompat.getMaxLines(this.c);
                }
                this.c.setTextAppearance(getContext(), TabLayout.this.i);
                if (TabLayout.this.j != null) {
                    this.c.setTextColor(TabLayout.this.j);
                }
                a(this.c, this.d);
            } else if (this.f != null || this.g != null) {
                a(this.f, this.g);
            }
        }

        private void a(TextView textView, ImageView imageView) {
            Drawable drawable;
            CharSequence charSequence;
            CharSequence charSequence2;
            if (this.b != null) {
                drawable = this.b.b;
            } else {
                drawable = null;
            }
            if (this.b != null) {
                charSequence = this.b.c;
            } else {
                charSequence = null;
            }
            if (this.b != null) {
                charSequence2 = this.b.d;
            } else {
                charSequence2 = null;
            }
            if (imageView != null) {
                if (drawable != null) {
                    imageView.setImageDrawable(drawable);
                    imageView.setVisibility(0);
                    setVisibility(0);
                } else {
                    imageView.setVisibility(XZBDevice.Wait);
                    imageView.setImageDrawable(null);
                }
                imageView.setContentDescription(charSequence2);
            }
            if (TextUtils.isEmpty(charSequence)) {
                boolean z = false;
            } else {
                int i = 1;
            }
            if (textView != null) {
                if (z) {
                    textView.setText(charSequence);
                    textView.setVisibility(0);
                    setVisibility(0);
                } else {
                    textView.setVisibility(XZBDevice.Wait);
                    textView.setText(null);
                }
                textView.setContentDescription(charSequence2);
            }
            if (imageView != null) {
                int a;
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) imageView.getLayoutParams();
                if (z && imageView.getVisibility() == 0) {
                    a = TabLayout.this.b((int) XZBDevice.Wait);
                } else {
                    a = 0;
                }
                if (a != marginLayoutParams.bottomMargin) {
                    marginLayoutParams.bottomMargin = a;
                    imageView.requestLayout();
                }
            }
            if (z || TextUtils.isEmpty(charSequence2)) {
                setOnLongClickListener(null);
                setLongClickable(false);
                return;
            }
            setOnLongClickListener(this);
        }

        public final boolean onLongClick(View view) {
            int[] iArr = new int[2];
            getLocationOnScreen(iArr);
            Context context = getContext();
            int width = getWidth();
            int height = getHeight();
            int i = context.getResources().getDisplayMetrics().widthPixels;
            Toast makeText = Toast.makeText(context, this.b.d, 0);
            makeText.setGravity(R.styleable.AppCompatTheme_actionButtonStyle, (iArr[0] + (width / 2)) - (i / 2), height);
            makeText.show();
            return true;
        }

        static /* synthetic */ void a(f fVar) {
            fVar.a(null);
            fVar.setSelected(false);
        }
    }

    public static class g implements a {
        private final ViewPager a;

        public g(ViewPager viewPager) {
            this.a = viewPager;
        }

        public final void a(d dVar) {
            this.a.setCurrentItem(dVar.e);
        }
    }

    static {
        a = new SynchronizedPool(16);
    }

    public TabLayout(Context context) {
        this(context, null);
    }

    public TabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = new ArrayList();
        this.n = Integer.MAX_VALUE;
        this.A = new SimplePool(12);
        be.a(context);
        setHorizontalScrollBarEnabled(false);
        this.d = new c(context);
        super.addView(this.d, 0, new FrameLayout.LayoutParams(-2, -1));
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, android.support.design.R.styleable.TabLayout, i, android.support.design.R.style.Widget_Design_TabLayout);
        this.d.b(obtainStyledAttributes.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabIndicatorHeight, 0));
        this.d.a(obtainStyledAttributes.getColor(android.support.design.R.styleable.TabLayout_tabIndicatorColor, 0));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabPadding, 0);
        this.h = dimensionPixelSize;
        this.g = dimensionPixelSize;
        this.f = dimensionPixelSize;
        this.e = dimensionPixelSize;
        this.e = obtainStyledAttributes.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabPaddingStart, this.e);
        this.f = obtainStyledAttributes.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabPaddingTop, this.f);
        this.g = obtainStyledAttributes.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabPaddingEnd, this.g);
        this.h = obtainStyledAttributes.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabPaddingBottom, this.h);
        this.i = obtainStyledAttributes.getResourceId(android.support.design.R.styleable.TabLayout_tabTextAppearance, android.support.design.R.style.TextAppearance_Design_Tab);
        TypedArray obtainStyledAttributes2 = context.obtainStyledAttributes(this.i, android.support.design.R.styleable.TextAppearance);
        this.k = (float) obtainStyledAttributes2.getDimensionPixelSize(android.support.design.R.styleable.TextAppearance_android_textSize, 0);
        this.j = obtainStyledAttributes2.getColorStateList(android.support.design.R.styleable.TextAppearance_android_textColor);
        obtainStyledAttributes2.recycle();
        obtainStyledAttributes2 = obtainStyledAttributes.hasValue(android.support.design.R.styleable.TabLayout_tabTextColor);
        if (obtainStyledAttributes2 != null) {
            this.j = obtainStyledAttributes.getColorStateList(android.support.design.R.styleable.TabLayout_tabTextColor);
        }
        if (obtainStyledAttributes.hasValue(android.support.design.R.styleable.TabLayout_tabSelectedTextColor)) {
            dimensionPixelSize = obtainStyledAttributes.getColor(android.support.design.R.styleable.TabLayout_tabSelectedTextColor, 0);
            int defaultColor = this.j.getDefaultColor();
            r3 = new int[2][];
            int[] iArr = new int[]{SELECTED_STATE_SET, dimensionPixelSize};
            r3[1] = EMPTY_STATE_SET;
            iArr[1] = defaultColor;
            this.j = new ColorStateList(r3, iArr);
        }
        this.o = obtainStyledAttributes.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabMinWidth, -1);
        this.p = obtainStyledAttributes.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabMaxWidth, -1);
        this.m = obtainStyledAttributes.getResourceId(android.support.design.R.styleable.TabLayout_tabBackground, 0);
        this.r = obtainStyledAttributes.getDimensionPixelSize(android.support.design.R.styleable.TabLayout_tabContentStart, 0);
        this.t = obtainStyledAttributes.getInt(android.support.design.R.styleable.TabLayout_tabMode, 1);
        this.s = obtainStyledAttributes.getInt(android.support.design.R.styleable.TabLayout_tabGravity, 0);
        obtainStyledAttributes.recycle();
        Resources resources = getResources();
        this.l = (float) resources.getDimensionPixelSize(android.support.design.R.dimen.design_tab_text_size_2line);
        this.q = resources.getDimensionPixelSize(android.support.design.R.dimen.design_tab_scrollable_min_width);
        d();
    }

    public void setSelectedTabIndicatorColor(int i) {
        this.d.a(i);
    }

    public void setSelectedTabIndicatorHeight(int i) {
        this.d.b(i);
    }

    private void setScrollPosition$4867b5c2(int i) {
        a(i, AutoScrollHelper.RELATIVE_UNSPECIFIED, true, true);
    }

    private void a(int i, float f, boolean z, boolean z2) {
        int round = Math.round(((float) i) + f);
        if (round >= 0 && round < this.d.getChildCount()) {
            if (z2) {
                c cVar = this.d;
                if (cVar.c != null && cVar.c.a.b()) {
                    cVar.c.a.e();
                }
                cVar.a = i;
                cVar.b = f;
                cVar.a();
            }
            if (this.v != null && this.v.a.b()) {
                this.v.a.e();
            }
            scrollTo(a(i, f), 0);
            if (z) {
                setSelectedTabView(round);
            }
        }
    }

    private float getScrollPosition() {
        c cVar = this.d;
        return cVar.b + ((float) cVar.a);
    }

    public void setOnTabSelectedListener(a aVar) {
        this.u = aVar;
    }

    private d a() {
        d dVar;
        d dVar2 = (d) a.acquire();
        if (dVar2 == null) {
            dVar = new d();
        } else {
            dVar = dVar2;
        }
        dVar.g = this;
        f fVar = this.A != null ? (f) this.A.acquire() : null;
        if (fVar == null) {
            fVar = new f(getContext());
        }
        fVar.a(dVar);
        fVar.setFocusable(true);
        fVar.setMinimumWidth(getTabMinWidth());
        dVar.h = fVar;
        return dVar;
    }

    public int getTabCount() {
        return this.b.size();
    }

    public final d a(int i) {
        return (d) this.b.get(i);
    }

    public int getSelectedTabPosition() {
        return this.c != null ? this.c.e : -1;
    }

    private void b() {
        for (int childCount = this.d.getChildCount() - 1; childCount >= 0; childCount--) {
            f fVar = (f) this.d.getChildAt(childCount);
            this.d.removeViewAt(childCount);
            if (fVar != null) {
                f.a(fVar);
                this.A.release(fVar);
            }
            requestLayout();
        }
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            d dVar = (d) it.next();
            it.remove();
            dVar.g = null;
            dVar.h = null;
            dVar.a = null;
            dVar.b = null;
            dVar.c = null;
            dVar.d = null;
            dVar.e = -1;
            dVar.f = null;
            a.release(dVar);
        }
        this.c = null;
    }

    public void setTabMode(int i) {
        if (i != this.t) {
            this.t = i;
            d();
        }
    }

    public int getTabMode() {
        return this.t;
    }

    public void setTabGravity(int i) {
        if (this.s != i) {
            this.s = i;
            d();
        }
    }

    public int getTabGravity() {
        return this.s;
    }

    public void setTabTextColors(ColorStateList colorStateList) {
        if (this.j != colorStateList) {
            this.j = colorStateList;
            int size = this.b.size();
            for (int i = 0; i < size; i++) {
                ((d) this.b.get(i)).b();
            }
        }
    }

    public ColorStateList getTabTextColors() {
        return this.j;
    }

    public void setupWithViewPager(ViewPager viewPager) {
        if (!(this.w == null || this.z == null)) {
            this.w.removeOnPageChangeListener(this.z);
        }
        if (viewPager != null) {
            PagerAdapter adapter = viewPager.getAdapter();
            if (adapter == null) {
                throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set");
            }
            this.w = viewPager;
            if (this.z == null) {
                this.z = new e(this);
            }
            e.a(this.z);
            viewPager.addOnPageChangeListener(this.z);
            setOnTabSelectedListener(new g(viewPager));
            a(adapter, true);
            return;
        }
        this.w = null;
        setOnTabSelectedListener(null);
        a(null, true);
    }

    @Deprecated
    public void setTabsFromPagerAdapter(PagerAdapter pagerAdapter) {
        a(pagerAdapter, false);
    }

    public boolean shouldDelayChildPressedState() {
        return getTabScrollRange() > 0;
    }

    private int getTabScrollRange() {
        return Math.max(0, ((this.d.getWidth() - getWidth()) - getPaddingLeft()) - getPaddingRight());
    }

    private void a(PagerAdapter pagerAdapter, boolean z) {
        if (!(this.x == null || this.y == null)) {
            this.x.unregisterDataSetObserver(this.y);
        }
        this.x = pagerAdapter;
        if (z && pagerAdapter != null) {
            if (this.y == null) {
                this.y = new b();
            }
            pagerAdapter.registerDataSetObserver(this.y);
        }
        c();
    }

    private void c() {
        b();
        if (this.x != null) {
            int i;
            int count = this.x.getCount();
            for (i = 0; i < count; i++) {
                b(a().a(this.x.getPageTitle(i)), false);
            }
            if (this.w != null && count > 0) {
                i = this.w.getCurrentItem();
                if (i != getSelectedTabPosition() && i < getTabCount()) {
                    a(a(i), true);
                    return;
                }
                return;
            }
            return;
        }
        b();
    }

    public void addView(View view) {
        a(view);
    }

    public void addView(View view, int i) {
        a(view);
    }

    public void addView(View view, ViewGroup.LayoutParams layoutParams) {
        a(view);
    }

    public void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        a(view);
    }

    private void a(View view) {
        if (view instanceof TabItem) {
            TabItem tabItem = (TabItem) view;
            d a = a();
            if (tabItem.a != null) {
                a.a(tabItem.a);
            }
            if (tabItem.b != null) {
                a.b = tabItem.b;
                a.b();
            }
            if (tabItem.c != 0) {
                a.f = LayoutInflater.from(a.h.getContext()).inflate(tabItem.c, a.h, false);
                a.b();
            }
            b(a, this.b.isEmpty());
            return;
        }
        throw new IllegalArgumentException("Only TabItem instances can be added to TabLayout");
    }

    private void a(LayoutParams layoutParams) {
        if (this.t == 1 && this.s == 0) {
            layoutParams.width = 0;
            layoutParams.weight = 1.0f;
            return;
        }
        layoutParams.width = -2;
        layoutParams.weight = 0.0f;
    }

    private int b(int i) {
        return Math.round(getResources().getDisplayMetrics().density * ((float) i));
    }

    protected void onMeasure(int i, int i2) {
        Object obj = 1;
        int b = (b(getDefaultHeight()) + getPaddingTop()) + getPaddingBottom();
        switch (MeasureSpec.getMode(i2)) {
            case ExploreByTouchHelper.INVALID_ID:
                i2 = MeasureSpec.makeMeasureSpec(Math.min(b, MeasureSpec.getSize(i2)), 1073741824);
                break;
            case SpdyAgent.ACCS_TEST_SERVER:
                i2 = MeasureSpec.makeMeasureSpec(b, 1073741824);
                break;
        }
        b = MeasureSpec.getSize(i);
        if (MeasureSpec.getMode(i) != 0) {
            if (this.p > 0) {
                b = this.p;
            } else {
                b -= b((int) R.styleable.AppCompatTheme_dividerHorizontal);
            }
            this.n = b;
        }
        super.onMeasure(i, i2);
        if (getChildCount() == 1) {
            View childAt = getChildAt(0);
            switch (this.t) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    b = childAt.getMeasuredWidth() < getMeasuredWidth() ? 1 : 0;
                    break;
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    int i3;
                    if (childAt.getMeasuredWidth() == getMeasuredWidth()) {
                        i3 = 0;
                    }
                    b = i3;
                    break;
                default:
                    b = 0;
                    break;
            }
            if (b != 0) {
                childAt.measure(MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824), getChildMeasureSpec(i2, getPaddingTop() + getPaddingBottom(), childAt.getLayoutParams().height));
            }
        }
    }

    private void c(int i) {
        Object obj = null;
        if (i != -1) {
            if (getWindowToken() != null && ViewCompat.isLaidOut(this)) {
                int i2;
                c cVar = this.d;
                int childCount = cVar.getChildCount();
                for (i2 = 0; i2 < childCount; i2++) {
                    if (cVar.getChildAt(i2).getWidth() <= 0) {
                        obj = 1;
                        break;
                    }
                }
                if (obj == null) {
                    int scrollX = getScrollX();
                    i2 = a(i, (float) AutoScrollHelper.RELATIVE_UNSPECIFIED);
                    if (scrollX != i2) {
                        if (this.v == null) {
                            this.v = bq.a();
                            this.v.a(a.b);
                            this.v.a((int) XLErrorCode.OAUTH_PARAM_ERROR);
                            this.v.a(new aw(this));
                        }
                        this.v.a(scrollX, i2);
                        this.v.a.a();
                    }
                    this.d.a(i, (int) XLErrorCode.OAUTH_PARAM_ERROR);
                    return;
                }
            }
            setScrollPosition$4867b5c2(i);
        }
    }

    private void setSelectedTabView(int i) {
        int childCount = this.d.getChildCount();
        if (i < childCount && !this.d.getChildAt(i).isSelected()) {
            for (int i2 = 0; i2 < childCount; i2++) {
                boolean z;
                View childAt = this.d.getChildAt(i2);
                if (i2 == i) {
                    z = true;
                } else {
                    z = false;
                }
                childAt.setSelected(z);
            }
        }
    }

    final void a(d dVar, boolean z) {
        if (this.c != dVar) {
            if (z) {
                int i;
                if (dVar != null) {
                    i = dVar.e;
                } else {
                    i = -1;
                }
                if (i != -1) {
                    setSelectedTabView(i);
                }
                if ((this.c == null || this.c.e == -1) && i != -1) {
                    setScrollPosition$4867b5c2(i);
                } else {
                    c(i);
                }
            }
            this.c = dVar;
            if (this.c != null && this.u != null) {
                this.u.a(this.c);
            }
        } else if (this.c != null) {
            c(dVar.e);
        }
    }

    private int a(int i, float f) {
        int i2 = 0;
        if (this.t != 0) {
            return 0;
        }
        int width;
        View childAt = this.d.getChildAt(i);
        View childAt2 = i + 1 < this.d.getChildCount() ? this.d.getChildAt(i + 1) : null;
        if (childAt != null) {
            width = childAt.getWidth();
        } else {
            width = 0;
        }
        if (childAt2 != null) {
            i2 = childAt2.getWidth();
        }
        return ((((int) ((((float) (i2 + width)) * f) * 0.5f)) + childAt.getLeft()) + (childAt.getWidth() / 2)) - (getWidth() / 2);
    }

    private void d() {
        int max;
        if (this.t == 0) {
            max = Math.max(0, this.r - this.e);
        } else {
            max = 0;
        }
        ViewCompat.setPaddingRelative(this.d, max, 0, 0, 0);
        switch (this.t) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.d.setGravity(GravityCompat.START);
                break;
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.d.setGravity(1);
                break;
        }
        a(true);
    }

    private void a(boolean z) {
        for (int i = 0; i < this.d.getChildCount(); i++) {
            View childAt = this.d.getChildAt(i);
            childAt.setMinimumWidth(getTabMinWidth());
            a((LayoutParams) childAt.getLayoutParams());
            if (z) {
                childAt.requestLayout();
            }
        }
    }

    private int getDefaultHeight() {
        Object obj;
        int size = this.b.size();
        for (int i = 0; i < size; i++) {
            d dVar = (d) this.b.get(i);
            if (dVar != null && dVar.b != null && !TextUtils.isEmpty(dVar.c)) {
                obj = 1;
                break;
            }
        }
        obj = null;
        return obj != null ? R.styleable.AppCompatTheme_listPreferredItemPaddingLeft : R.styleable.AppCompatTheme_homeAsUpIndicator;
    }

    private int getTabMinWidth() {
        if (this.o != -1) {
            return this.o;
        }
        return this.t == 0 ? this.q : 0;
    }

    public FrameLayout.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return generateDefaultLayoutParams();
    }

    private int getTabMaxWidth() {
        return this.n;
    }

    private void b(d dVar, boolean z) {
        if (dVar.g != this) {
            throw new IllegalArgumentException("Tab belongs to a different TabLayout.");
        }
        View view = dVar.h;
        c cVar = this.d;
        LayoutParams layoutParams = new LayoutParams(-2, -1);
        a(layoutParams);
        cVar.addView(view, layoutParams);
        if (z) {
            view.setSelected(true);
        }
        a(dVar, this.b.size());
        if (z) {
            dVar.a();
        }
    }

    private void a(d dVar, int i) {
        dVar.e = i;
        this.b.add(i, dVar);
        int size = this.b.size();
        for (int i2 = i + 1; i2 < size; i2++) {
            ((d) this.b.get(i2)).e = i2;
        }
    }
}
