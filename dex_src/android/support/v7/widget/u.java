package android.support.v7.widget;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ClipDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.widget.ProgressBar;

// compiled from: AppCompatProgressBarHelper.java
class u {
    private static final int[] c;
    final r a;
    Bitmap b;
    private final ProgressBar d;

    static {
        c = new int[]{16843067, 16843068};
    }

    u(ProgressBar progressBar, r rVar) {
        this.d = progressBar;
        this.a = rVar;
    }

    void a(AttributeSet attributeSet, int i) {
        cm a = cm.a(this.d.getContext(), attributeSet, c, i);
        Drawable b = a.b(0);
        if (b != null) {
            ProgressBar progressBar = this.d;
            if (b instanceof AnimationDrawable) {
                AnimationDrawable animationDrawable = (AnimationDrawable) b;
                int numberOfFrames = animationDrawable.getNumberOfFrames();
                Drawable animationDrawable2 = new AnimationDrawable();
                animationDrawable2.setOneShot(animationDrawable.isOneShot());
                for (int i2 = 0; i2 < numberOfFrames; i2++) {
                    Drawable a2 = a(animationDrawable.getFrame(i2), true);
                    a2.setLevel(10000);
                    animationDrawable2.addFrame(a2, animationDrawable.getDuration(i2));
                }
                animationDrawable2.setLevel(10000);
                b = animationDrawable2;
            }
            progressBar.setIndeterminateDrawable(b);
        }
        b = a.b(1);
        if (b != null) {
            this.d.setProgressDrawable(a(b, false));
        }
        a.a.recycle();
    }

    private Drawable a(Drawable drawable, boolean z) {
        int i = 0;
        Drawable wrappedDrawable;
        if (drawable instanceof DrawableWrapper) {
            wrappedDrawable = ((DrawableWrapper) drawable).getWrappedDrawable();
            if (wrappedDrawable == null) {
                return drawable;
            }
            ((DrawableWrapper) drawable).setWrappedDrawable(a(wrappedDrawable, z));
            return drawable;
        } else if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            int numberOfLayers = layerDrawable.getNumberOfLayers();
            Drawable[] drawableArr = new Drawable[numberOfLayers];
            for (int i2 = 0; i2 < numberOfLayers; i2++) {
                boolean z2;
                int id = layerDrawable.getId(i2);
                Drawable drawable2 = layerDrawable.getDrawable(i2);
                if (id == 16908301 || id == 16908303) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                drawableArr[i2] = a(drawable2, z2);
            }
            wrappedDrawable = new LayerDrawable(drawableArr);
            while (i < numberOfLayers) {
                wrappedDrawable.setId(i, layerDrawable.getId(i));
                i++;
            }
            return wrappedDrawable;
        } else if (!(drawable instanceof BitmapDrawable)) {
            return drawable;
        } else {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
            if (this.b == null) {
                this.b = bitmap;
            }
            wrappedDrawable = new ShapeDrawable(new RoundRectShape(new float[]{5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, 5.0f}, null, null));
            wrappedDrawable.getPaint().setShader(new BitmapShader(bitmap, TileMode.REPEAT, TileMode.CLAMP));
            wrappedDrawable.getPaint().setColorFilter(bitmapDrawable.getPaint().getColorFilter());
            return z ? new ClipDrawable(wrappedDrawable, 3, 1) : wrappedDrawable;
        }
    }
}
