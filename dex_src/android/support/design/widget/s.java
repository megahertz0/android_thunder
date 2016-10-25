package android.support.design.widget;

import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableContainer.DrawableContainerState;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// compiled from: DrawableUtils.java
final class s {
    private static Method a;
    private static boolean b;
    private static Field c;
    private static boolean d;

    static boolean a(DrawableContainer drawableContainer, ConstantState constantState) {
        if (!b) {
            try {
                Method declaredMethod = DrawableContainer.class.getDeclaredMethod("setConstantState", new Class[]{DrawableContainerState.class});
                a = declaredMethod;
                declaredMethod.setAccessible(true);
            } catch (NoSuchMethodException e) {
            }
            b = true;
        }
        if (a != null) {
            try {
                a.invoke(drawableContainer, new Object[]{constantState});
                return true;
            } catch (Exception e2) {
            }
        }
        return false;
    }

    static boolean b(DrawableContainer drawableContainer, ConstantState constantState) {
        if (!d) {
            try {
                Field declaredField = DrawableContainer.class.getDeclaredField("mDrawableContainerStateField");
                c = declaredField;
                declaredField.setAccessible(true);
            } catch (NoSuchFieldException e) {
            }
            d = true;
        }
        if (c != null) {
            try {
                c.set(drawableContainer, constantState);
                return true;
            } catch (Exception e2) {
            }
        }
        return false;
    }
}
