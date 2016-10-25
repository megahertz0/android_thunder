package com.inmobi.ads;

import android.graphics.Camera;
import android.graphics.Matrix;
import android.support.v4.widget.AutoScrollHelper;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Transformation;
import com.inmobi.ads.InMobiBanner.AnimationType;

// compiled from: AnimationController.java
class j {

    // compiled from: AnimationController.java
    static class a extends Animation {
        private final float a;
        private final float b;
        private final float c;
        private final float d;
        private final float e;
        private final boolean f;
        private Camera g;

        public a(float f, float f2, float f3, float f4, float f5, boolean z) {
            this.a = f;
            this.b = f2;
            this.c = f3;
            this.d = f4;
            this.e = f5;
            this.f = z;
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.g = new Camera();
        }

        protected void applyTransformation(float f, Transformation transformation) {
            float f2 = this.a;
            f2 += (this.b - f2) * f;
            float f3 = this.c;
            float f4 = this.d;
            Camera camera = this.g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f) {
                camera.translate(AutoScrollHelper.RELATIVE_UNSPECIFIED, AutoScrollHelper.RELATIVE_UNSPECIFIED, this.e * f);
            } else {
                camera.translate(AutoScrollHelper.RELATIVE_UNSPECIFIED, AutoScrollHelper.RELATIVE_UNSPECIFIED, this.e * (1.0f - f));
            }
            camera.rotateX(f2);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f3, -f4);
            matrix.postTranslate(f3, f4);
        }
    }

    // compiled from: AnimationController.java
    static class b extends Animation {
        private final float a;
        private final float b;
        private final float c;
        private final float d;
        private final float e;
        private final boolean f;
        private Camera g;

        public b(float f, float f2, float f3, float f4, float f5, boolean z) {
            this.a = f;
            this.b = f2;
            this.c = f3;
            this.d = f4;
            this.e = f5;
            this.f = z;
        }

        public void initialize(int i, int i2, int i3, int i4) {
            super.initialize(i, i2, i3, i4);
            this.g = new Camera();
        }

        protected void applyTransformation(float f, Transformation transformation) {
            float f2 = this.a;
            f2 += (this.b - f2) * f;
            float f3 = this.c;
            float f4 = this.d;
            Camera camera = this.g;
            Matrix matrix = transformation.getMatrix();
            camera.save();
            if (this.f) {
                camera.translate(AutoScrollHelper.RELATIVE_UNSPECIFIED, AutoScrollHelper.RELATIVE_UNSPECIFIED, this.e * f);
            } else {
                camera.translate(AutoScrollHelper.RELATIVE_UNSPECIFIED, AutoScrollHelper.RELATIVE_UNSPECIFIED, this.e * (1.0f - f));
            }
            camera.rotateY(f2);
            camera.getMatrix(matrix);
            camera.restore();
            matrix.preTranslate(-f3, -f4);
            matrix.postTranslate(f3, f4);
        }
    }

    static Animation a(AnimationType animationType, float f, float f2) {
        Animation alphaAnimation;
        if (animationType == AnimationType.ANIMATION_ALPHA) {
            alphaAnimation = new AlphaAnimation(0.0f, 0.5f);
            alphaAnimation.setDuration(1000);
            alphaAnimation.setFillAfter(false);
            alphaAnimation.setInterpolator(new DecelerateInterpolator());
            return alphaAnimation;
        } else if (animationType == AnimationType.ROTATE_HORIZONTAL_AXIS) {
            alphaAnimation = new a(0.0f, 90.0f, f / 2.0f, f2 / 2.0f, 0.0f, true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(false);
            alphaAnimation.setInterpolator(new AccelerateInterpolator());
            return alphaAnimation;
        } else if (animationType != AnimationType.ROTATE_VERTICAL_AXIS) {
            return null;
        } else {
            alphaAnimation = new b(0.0f, 90.0f, f / 2.0f, f2 / 2.0f, 0.0f, true);
            alphaAnimation.setDuration(500);
            alphaAnimation.setFillAfter(false);
            alphaAnimation.setInterpolator(new AccelerateInterpolator());
            return alphaAnimation;
        }
    }
}
