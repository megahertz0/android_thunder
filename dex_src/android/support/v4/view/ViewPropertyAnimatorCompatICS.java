package android.support.v4.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.Interpolator;

class ViewPropertyAnimatorCompatICS {

    final class AnonymousClass_1 extends AnimatorListenerAdapter {
        final /* synthetic */ ViewPropertyAnimatorListener val$listener;
        final /* synthetic */ View val$view;

        AnonymousClass_1(ViewPropertyAnimatorListener viewPropertyAnimatorListener, View view) {
            this.val$listener = viewPropertyAnimatorListener;
            this.val$view = view;
        }

        public final void onAnimationCancel(Animator animator) {
            this.val$listener.onAnimationCancel(this.val$view);
        }

        public final void onAnimationEnd(Animator animator) {
            this.val$listener.onAnimationEnd(this.val$view);
        }

        public final void onAnimationStart(Animator animator) {
            this.val$listener.onAnimationStart(this.val$view);
        }
    }

    ViewPropertyAnimatorCompatICS() {
    }

    public static void setDuration(View view, long j) {
        view.animate().setDuration(j);
    }

    public static void alpha(View view, float f) {
        view.animate().alpha(f);
    }

    public static void translationX(View view, float f) {
        view.animate().translationX(f);
    }

    public static void translationY(View view, float f) {
        view.animate().translationY(f);
    }

    public static long getDuration(View view) {
        return view.animate().getDuration();
    }

    public static void setInterpolator(View view, Interpolator interpolator) {
        view.animate().setInterpolator(interpolator);
    }

    public static void setStartDelay(View view, long j) {
        view.animate().setStartDelay(j);
    }

    public static long getStartDelay(View view) {
        return view.animate().getStartDelay();
    }

    public static void alphaBy(View view, float f) {
        view.animate().alphaBy(f);
    }

    public static void rotation(View view, float f) {
        view.animate().rotation(f);
    }

    public static void rotationBy(View view, float f) {
        view.animate().rotationBy(f);
    }

    public static void rotationX(View view, float f) {
        view.animate().rotationX(f);
    }

    public static void rotationXBy(View view, float f) {
        view.animate().rotationXBy(f);
    }

    public static void rotationY(View view, float f) {
        view.animate().rotationY(f);
    }

    public static void rotationYBy(View view, float f) {
        view.animate().rotationYBy(f);
    }

    public static void scaleX(View view, float f) {
        view.animate().scaleX(f);
    }

    public static void scaleXBy(View view, float f) {
        view.animate().scaleXBy(f);
    }

    public static void scaleY(View view, float f) {
        view.animate().scaleY(f);
    }

    public static void scaleYBy(View view, float f) {
        view.animate().scaleYBy(f);
    }

    public static void cancel(View view) {
        view.animate().cancel();
    }

    public static void x(View view, float f) {
        view.animate().x(f);
    }

    public static void xBy(View view, float f) {
        view.animate().xBy(f);
    }

    public static void y(View view, float f) {
        view.animate().y(f);
    }

    public static void yBy(View view, float f) {
        view.animate().yBy(f);
    }

    public static void translationXBy(View view, float f) {
        view.animate().translationXBy(f);
    }

    public static void translationYBy(View view, float f) {
        view.animate().translationYBy(f);
    }

    public static void start(View view) {
        view.animate().start();
    }

    public static void setListener(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        if (viewPropertyAnimatorListener != null) {
            view.animate().setListener(new AnonymousClass_1(viewPropertyAnimatorListener, view));
        } else {
            view.animate().setListener(null);
        }
    }
}
