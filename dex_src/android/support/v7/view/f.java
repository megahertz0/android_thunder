package android.support.v7.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.util.SimpleArrayMap;
import android.support.v7.view.menu.o;
import android.view.ActionMode;
import android.view.ActionMode.Callback;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import java.util.ArrayList;

@TargetApi(11)
// compiled from: SupportActionModeWrapper.java
public final class f extends ActionMode {
    final Context a;
    final b b;

    // compiled from: SupportActionModeWrapper.java
    public static class a implements android.support.v7.view.b.a {
        final Callback a;
        final Context b;
        final ArrayList<f> c;
        final SimpleArrayMap<Menu, Menu> d;

        public a(Context context, Callback callback) {
            this.b = context;
            this.a = callback;
            this.c = new ArrayList();
            this.d = new SimpleArrayMap();
        }

        public final boolean a(b bVar, Menu menu) {
            return this.a.onCreateActionMode(b(bVar), a(menu));
        }

        public final boolean b(b bVar, Menu menu) {
            return this.a.onPrepareActionMode(b(bVar), a(menu));
        }

        public final boolean a(b bVar, MenuItem menuItem) {
            return this.a.onActionItemClicked(b(bVar), o.a(this.b, (SupportMenuItem) menuItem));
        }

        public final void a(b bVar) {
            this.a.onDestroyActionMode(b(bVar));
        }

        private Menu a(Menu menu) {
            Menu menu2 = (Menu) this.d.get(menu);
            if (menu2 != null) {
                return menu2;
            }
            menu2 = o.a(this.b, (SupportMenu) menu);
            this.d.put(menu, menu2);
            return menu2;
        }

        public final ActionMode b(b bVar) {
            int size = this.c.size();
            for (int i = 0; i < size; i++) {
                f fVar = (f) this.c.get(i);
                if (fVar != null && fVar.b == bVar) {
                    return fVar;
                }
            }
            ActionMode fVar2 = new f(this.b, bVar);
            this.c.add(fVar2);
            return fVar2;
        }
    }

    public f(Context context, b bVar) {
        this.a = context;
        this.b = bVar;
    }

    public final Object getTag() {
        return this.b.b;
    }

    public final void setTag(Object obj) {
        this.b.b = obj;
    }

    public final void setTitle(CharSequence charSequence) {
        this.b.b(charSequence);
    }

    public final void setSubtitle(CharSequence charSequence) {
        this.b.a(charSequence);
    }

    public final void invalidate() {
        this.b.d();
    }

    public final void finish() {
        this.b.c();
    }

    public final Menu getMenu() {
        return o.a(this.a, (SupportMenu) this.b.b());
    }

    public final CharSequence getTitle() {
        return this.b.f();
    }

    public final void setTitle(int i) {
        this.b.a(i);
    }

    public final CharSequence getSubtitle() {
        return this.b.g();
    }

    public final void setSubtitle(int i) {
        this.b.b(i);
    }

    public final View getCustomView() {
        return this.b.i();
    }

    public final void setCustomView(View view) {
        this.b.a(view);
    }

    public final MenuInflater getMenuInflater() {
        return this.b.a();
    }

    public final boolean getTitleOptionalHint() {
        return this.b.c;
    }

    public final void setTitleOptionalHint(boolean z) {
        this.b.a(z);
    }

    public final boolean isTitleOptional() {
        return this.b.h();
    }
}
