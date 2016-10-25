package android.support.v7.view;

import android.content.Context;
import android.support.v7.view.menu.f;
import android.support.v7.view.menu.f.a;
import android.support.v7.widget.ActionBarContextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import com.xunlei.tdlive.R;
import java.lang.ref.WeakReference;

// compiled from: StandaloneActionMode.java
public final class e extends b implements a {
    private Context a;
    private ActionBarContextView d;
    private b.a e;
    private WeakReference<View> f;
    private boolean g;
    private boolean h;
    private f i;

    public e(Context context, ActionBarContextView actionBarContextView, b.a aVar, boolean z) {
        this.a = context;
        this.d = actionBarContextView;
        this.e = aVar;
        f fVar = new f(actionBarContextView.getContext());
        fVar.e = 1;
        this.i = fVar;
        this.i.a((a) this);
        this.h = z;
    }

    public final void b(CharSequence charSequence) {
        this.d.setTitle(charSequence);
    }

    public final void a(CharSequence charSequence) {
        this.d.setSubtitle(charSequence);
    }

    public final void a(int i) {
        b(this.a.getString(i));
    }

    public final void b(int i) {
        a(this.a.getString(i));
    }

    public final void a(boolean z) {
        super.a(z);
        this.d.setTitleOptional(z);
    }

    public final boolean h() {
        return this.d.h;
    }

    public final void a(View view) {
        this.d.setCustomView(view);
        this.f = view != null ? new WeakReference(view) : null;
    }

    public final void d() {
        this.e.b(this, this.i);
    }

    public final void c() {
        if (!this.g) {
            this.g = true;
            this.d.sendAccessibilityEvent(R.styleable.AppCompatTheme_actionModeCutDrawable);
            this.e.a(this);
        }
    }

    public final Menu b() {
        return this.i;
    }

    public final CharSequence f() {
        return this.d.getTitle();
    }

    public final CharSequence g() {
        return this.d.getSubtitle();
    }

    public final View i() {
        return this.f != null ? (View) this.f.get() : null;
    }

    public final MenuInflater a() {
        return new g(this.d.getContext());
    }

    public final boolean a(f fVar, MenuItem menuItem) {
        return this.e.a((b) this, menuItem);
    }

    public final void a(f fVar) {
        d();
        this.d.a();
    }
}
