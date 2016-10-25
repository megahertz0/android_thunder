package android.support.a.a;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.Callback;
import android.graphics.drawable.Drawable.ConstantState;
import android.os.Build.VERSION;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import com.taobao.accs.common.Constants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

@TargetApi(21)
// compiled from: AnimatedVectorDrawableCompat.java
public final class b extends f implements Animatable {
    private a b;
    private Context c;
    private ArgbEvaluator d;
    private final Callback e;

    // compiled from: AnimatedVectorDrawableCompat.java
    private static class a extends ConstantState {
        int a;
        g b;
        ArrayList<Animator> c;
        ArrayMap<Animator, String> d;

        public final Drawable newDrawable() {
            throw new IllegalStateException("No constant state support for SDK < 23.");
        }

        public final Drawable newDrawable(Resources resources) {
            throw new IllegalStateException("No constant state support for SDK < 23.");
        }

        public final int getChangingConfigurations() {
            return this.a;
        }
    }

    // compiled from: AnimatedVectorDrawableCompat.java
    private static class b extends ConstantState {
        private final ConstantState a;

        public b(ConstantState constantState) {
            this.a = constantState;
        }

        public final Drawable newDrawable() {
            b bVar = new b();
            bVar.a = this.a.newDrawable();
            bVar.a.setCallback(bVar.e);
            return bVar;
        }

        public final Drawable newDrawable(Resources resources) {
            b bVar = new b();
            bVar.a = this.a.newDrawable(resources);
            bVar.a.setCallback(bVar.e);
            return bVar;
        }

        public final Drawable newDrawable(Resources resources, Theme theme) {
            b bVar = new b();
            bVar.a = this.a.newDrawable(resources, theme);
            bVar.a.setCallback(bVar.e);
            return bVar;
        }

        public final boolean canApplyTheme() {
            return this.a.canApplyTheme();
        }

        public final int getChangingConfigurations() {
            return this.a.getChangingConfigurations();
        }
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

    private b() {
        this(null, (byte) 0);
    }

    private b(Context context) {
        this(context, (byte) 0);
    }

    private b(Context context, byte b) {
        this.d = null;
        this.e = new c(this);
        this.c = context;
        this.b = new a();
    }

    public final Drawable mutate() {
        if (this.a != null) {
            this.a.mutate();
            return this;
        }
        throw new IllegalStateException("Mutate() is not supported for older platform");
    }

    public static b a(Context context, Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        b bVar = new b(context);
        bVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return bVar;
    }

    public final ConstantState getConstantState() {
        return this.a != null ? new b(this.a.getConstantState()) : null;
    }

    public final int getChangingConfigurations() {
        return this.a != null ? this.a.getChangingConfigurations() : super.getChangingConfigurations() | this.b.a;
    }

    public final void draw(Canvas canvas) {
        if (this.a != null) {
            this.a.draw(canvas);
            return;
        }
        this.b.b.draw(canvas);
        if (a()) {
            invalidateSelf();
        }
    }

    protected final void onBoundsChange(Rect rect) {
        if (this.a != null) {
            this.a.setBounds(rect);
        } else {
            this.b.b.setBounds(rect);
        }
    }

    protected final boolean onStateChange(int[] iArr) {
        return this.a != null ? this.a.setState(iArr) : this.b.b.setState(iArr);
    }

    protected final boolean onLevelChange(int i) {
        return this.a != null ? this.a.setLevel(i) : this.b.b.setLevel(i);
    }

    public final int getAlpha() {
        return this.a != null ? DrawableCompat.getAlpha(this.a) : this.b.b.getAlpha();
    }

    public final void setAlpha(int i) {
        if (this.a != null) {
            this.a.setAlpha(i);
        } else {
            this.b.b.setAlpha(i);
        }
    }

    public final void setColorFilter(ColorFilter colorFilter) {
        if (this.a != null) {
            this.a.setColorFilter(colorFilter);
        } else {
            this.b.b.setColorFilter(colorFilter);
        }
    }

    public final void setTint(int i) {
        if (this.a != null) {
            DrawableCompat.setTint(this.a, i);
        } else {
            this.b.b.setTint(i);
        }
    }

    public final void setTintList(ColorStateList colorStateList) {
        if (this.a != null) {
            DrawableCompat.setTintList(this.a, colorStateList);
        } else {
            this.b.b.setTintList(colorStateList);
        }
    }

    public final void setTintMode(Mode mode) {
        if (this.a != null) {
            DrawableCompat.setTintMode(this.a, mode);
        } else {
            this.b.b.setTintMode(mode);
        }
    }

    public final boolean setVisible(boolean z, boolean z2) {
        if (this.a != null) {
            return this.a.setVisible(z, z2);
        }
        this.b.b.setVisible(z, z2);
        return super.setVisible(z, z2);
    }

    public final boolean isStateful() {
        return this.a != null ? this.a.isStateful() : this.b.b.isStateful();
    }

    public final int getOpacity() {
        return this.a != null ? this.a.getOpacity() : this.b.b.getOpacity();
    }

    public final int getIntrinsicWidth() {
        return this.a != null ? this.a.getIntrinsicWidth() : this.b.b.getIntrinsicWidth();
    }

    public final int getIntrinsicHeight() {
        return this.a != null ? this.a.getIntrinsicHeight() : this.b.b.getIntrinsicHeight();
    }

    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Theme theme) throws XmlPullParserException, IOException {
        if (this.a != null) {
            DrawableCompat.inflate(this.a, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        int eventType = xmlPullParser.getEventType();
        while (eventType != 1) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                TypedArray obtainAttributes;
                if ("animated-vector".equals(name)) {
                    int[] iArr = a.e;
                    if (theme == null) {
                        obtainAttributes = resources.obtainAttributes(attributeSet, iArr);
                    } else {
                        obtainAttributes = theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
                    }
                    int resourceId = obtainAttributes.getResourceId(0, 0);
                    if (resourceId != 0) {
                        g a = g.a(resources, resourceId, theme);
                        a.d = false;
                        a.setCallback(this.e);
                        if (this.b.b != null) {
                            this.b.b.setCallback(null);
                        }
                        this.b.b = a;
                    }
                    obtainAttributes.recycle();
                } else if (Constants.KEY_TARGET.equals(name)) {
                    obtainAttributes = resources.obtainAttributes(attributeSet, a.f);
                    String string = obtainAttributes.getString(0);
                    int resourceId2 = obtainAttributes.getResourceId(1, 0);
                    if (resourceId2 != 0) {
                        if (this.c != null) {
                            Animator loadAnimator = AnimatorInflater.loadAnimator(this.c, resourceId2);
                            loadAnimator.setTarget(this.b.b.c.b.g.get(string));
                            if (VERSION.SDK_INT < 21) {
                                a(loadAnimator);
                            }
                            if (this.b.c == null) {
                                this.b.c = new ArrayList();
                                this.b.d = new ArrayMap();
                            }
                            this.b.c.add(loadAnimator);
                            this.b.d.put(loadAnimator, string);
                        } else {
                            throw new IllegalStateException("Context can't be null when inflating animators");
                        }
                    }
                    obtainAttributes.recycle();
                } else {
                    continue;
                }
            }
            eventType = xmlPullParser.next();
        }
    }

    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        inflate(resources, xmlPullParser, attributeSet, null);
    }

    public final void applyTheme(Theme theme) {
        if (this.a != null) {
            DrawableCompat.applyTheme(this.a, theme);
        }
    }

    public final boolean canApplyTheme() {
        return this.a != null ? DrawableCompat.canApplyTheme(this.a) : false;
    }

    private void a(Animator animator) {
        if (animator instanceof AnimatorSet) {
            List childAnimations = ((AnimatorSet) animator).getChildAnimations();
            if (childAnimations != null) {
                for (int i = 0; i < childAnimations.size(); i++) {
                    a((Animator) childAnimations.get(i));
                }
            }
        }
        if (animator instanceof ObjectAnimator) {
            ObjectAnimator objectAnimator = (ObjectAnimator) animator;
            String propertyName = objectAnimator.getPropertyName();
            if ("fillColor".equals(propertyName) || "strokeColor".equals(propertyName)) {
                if (this.d == null) {
                    this.d = new ArgbEvaluator();
                }
                objectAnimator.setEvaluator(this.d);
            }
        }
    }

    public final boolean isRunning() {
        if (this.a != null) {
            return ((AnimatedVectorDrawable) this.a).isRunning();
        }
        ArrayList arrayList = this.b.c;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (((Animator) arrayList.get(i)).isRunning()) {
                return true;
            }
        }
        return false;
    }

    private boolean a() {
        ArrayList arrayList = this.b.c;
        if (arrayList == null) {
            return false;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (((Animator) arrayList.get(i)).isRunning()) {
                return true;
            }
        }
        return false;
    }

    public final void start() {
        if (this.a != null) {
            ((AnimatedVectorDrawable) this.a).start();
        } else if (!a()) {
            ArrayList arrayList = this.b.c;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Animator) arrayList.get(i)).start();
            }
            invalidateSelf();
        }
    }

    public final void stop() {
        if (this.a != null) {
            ((AnimatedVectorDrawable) this.a).stop();
            return;
        }
        ArrayList arrayList = this.b.c;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((Animator) arrayList.get(i)).end();
        }
    }
}
