package com.xunlei.downloadprovider.homepage;

import android.app.Activity;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

// compiled from: GoBackGestureDetector.java
final class c extends SimpleOnGestureListener {
    final /* synthetic */ float a;
    final /* synthetic */ float b;
    final /* synthetic */ Activity c;
    final /* synthetic */ b d;

    c(b bVar, float f, float f2, Activity activity) {
        this.d = bVar;
        this.a = f;
        this.b = f2;
        this.c = activity;
    }

    public final boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        if (motionEvent == null || motionEvent2 == null) {
            return false;
        }
        float rawY = motionEvent2.getRawY() - motionEvent.getRawY();
        float rawX = motionEvent2.getRawX() - motionEvent.getRawX();
        if (Math.abs(rawY) > this.a || rawX <= 0.0f || 2.0f * Math.abs(rawX) < Math.abs(rawY) * 3.0f || rawX <= this.b || f <= 500.0f) {
            return false;
        }
        if (this.d.a == null) {
            this.c.finish();
        } else {
            this.d.a.a();
        }
        return true;
    }
}
