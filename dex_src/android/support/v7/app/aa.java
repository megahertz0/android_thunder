package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.appcompat.R;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

// compiled from: AppCompatDialog.java
public class aa extends Dialog implements l {
    private m a;

    public void onCreate(Bundle bundle) {
        b().h();
        super.onCreate(bundle);
        b().a(bundle);
    }

    public void setContentView(int i) {
        b().b(i);
    }

    public void setContentView(View view) {
        b().a(view);
    }

    public void setContentView(View view, LayoutParams layoutParams) {
        b().a(view, layoutParams);
    }

    public View findViewById(int i) {
        return b().a(i);
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        b().a(charSequence);
    }

    public void setTitle(int i) {
        super.setTitle(i);
        b().a(getContext().getString(i));
    }

    public void addContentView(View view, LayoutParams layoutParams) {
        b().b(view, layoutParams);
    }

    protected void onStop() {
        super.onStop();
        b().d();
    }

    public final boolean a() {
        return b().c(1);
    }

    public void invalidateOptionsMenu() {
        b().f();
    }

    private m b() {
        if (this.a == null) {
            this.a = m.a((Dialog) this, (l) this);
        }
        return this.a;
    }

    public aa(Context context, int i) {
        if (i == 0) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R.attr.dialogTheme, typedValue, true);
            i = typedValue.resourceId;
        }
        super(context, i);
        b().a(null);
        b().i();
    }
}
