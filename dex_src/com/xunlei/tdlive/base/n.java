package com.xunlei.tdlive.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;
import com.xunlei.tdlive.R;

// compiled from: ToastHelper.java
public class n {

    // compiled from: ToastHelper.java
    private static abstract class a {
        protected Context a;

        public abstract Toast a(String str);

        public abstract void a(Toast toast, String str);

        public a(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("context cannot be null!");
            }
            this.a = context;
        }
    }

    // compiled from: ToastHelper.java
    private static class b extends a {
        public b(Context context) {
            super(context);
        }

        @SuppressLint({"InflateParams"})
        public Toast a(String str) {
            Toast makeText = Toast.makeText(this.a, str, 0);
            makeText.setView(LayoutInflater.from(this.a).inflate(R.layout.xllive_common_toast, null));
            makeText.setGravity(com.xunlei.xllib.R.styleable.Toolbar_maxButtonHeight, 0, 0);
            return makeText;
        }

        public void a(Toast toast, String str) {
            ((TextView) toast.getView().findViewById(R.id.text)).setText(str);
        }
    }

    // compiled from: ToastHelper.java
    private static class c {
        private static Toast a;

        private c() {
        }

        static {
            a = null;
        }

        public void a(a aVar, String str) {
            if (a == null) {
                a = aVar.a(str);
            }
            aVar.a(a, str);
            a.show();
        }
    }

    public static void a(Context context, String str) {
        new c().a(new b(context), str);
    }
}
