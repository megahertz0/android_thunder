package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.menu.f.a;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;

// compiled from: SubMenuBuilder.java
public class q extends f implements SubMenu {
    public f m;
    private h n;

    public q(Context context, f fVar, h hVar) {
        super(context);
        this.m = fVar;
        this.n = hVar;
    }

    public void setQwertyMode(boolean z) {
        this.m.setQwertyMode(z);
    }

    public final boolean b() {
        return this.m.b();
    }

    public final boolean c() {
        return this.m.c();
    }

    public MenuItem getItem() {
        return this.n;
    }

    public final void a(a aVar) {
        this.m.a(aVar);
    }

    public final f k() {
        return this.m;
    }

    final boolean a(f fVar, MenuItem menuItem) {
        return super.a(fVar, menuItem) || this.m.a(fVar, menuItem);
    }

    public SubMenu setIcon(Drawable drawable) {
        this.n.setIcon(drawable);
        return this;
    }

    public SubMenu setIcon(int i) {
        this.n.setIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable) {
        super.a(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charSequence) {
        super.a(charSequence);
        return this;
    }

    public SubMenu setHeaderView(View view) {
        super.a(null, null, view);
        return this;
    }

    public final boolean a(h hVar) {
        return this.m.a(hVar);
    }

    public final boolean b(h hVar) {
        return this.m.b(hVar);
    }

    public final String a() {
        int itemId = this.n != null ? this.n.getItemId() : 0;
        return itemId == 0 ? null : super.a() + ":" + itemId;
    }

    public SubMenu setHeaderIcon(int i) {
        super.a(ContextCompat.getDrawable(this.a, i));
        return this;
    }

    public SubMenu setHeaderTitle(int i) {
        super.a(this.a.getResources().getString(i));
        return this;
    }
}
