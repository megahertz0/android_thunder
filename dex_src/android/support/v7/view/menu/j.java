package android.support.v7.view.menu;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenuItem;
import android.support.v4.view.ActionProvider;
import android.support.v4.view.MenuItemCompat;
import android.view.CollapsibleActionView;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

@TargetApi(14)
// compiled from: MenuItemWrapperICS.java
public class j extends c<SupportMenuItem> implements MenuItem {
    public Method e;

    // compiled from: MenuItemWrapperICS.java
    class a extends ActionProvider {
        final android.view.ActionProvider a;

        public a(Context context, android.view.ActionProvider actionProvider) {
            super(context);
            this.a = actionProvider;
        }

        public View onCreateActionView() {
            return this.a.onCreateActionView();
        }

        public boolean onPerformDefaultAction() {
            return this.a.onPerformDefaultAction();
        }

        public boolean hasSubMenu() {
            return this.a.hasSubMenu();
        }

        public void onPrepareSubMenu(SubMenu subMenu) {
            this.a.onPrepareSubMenu(j.this.a(subMenu));
        }
    }

    // compiled from: MenuItemWrapperICS.java
    static class b extends FrameLayout implements android.support.v7.view.c {
        final CollapsibleActionView a;

        b(View view) {
            super(view.getContext());
            this.a = (CollapsibleActionView) view;
            addView(view);
        }

        public final void a() {
            this.a.onActionViewExpanded();
        }

        public final void b() {
            this.a.onActionViewCollapsed();
        }
    }

    // compiled from: MenuItemWrapperICS.java
    private class c extends d<OnActionExpandListener> implements MenuItemCompat.OnActionExpandListener {
        c(OnActionExpandListener onActionExpandListener) {
            super(onActionExpandListener);
        }

        public final boolean onMenuItemActionExpand(MenuItem menuItem) {
            return ((OnActionExpandListener) this.d).onMenuItemActionExpand(j.this.a(menuItem));
        }

        public final boolean onMenuItemActionCollapse(MenuItem menuItem) {
            return ((OnActionExpandListener) this.d).onMenuItemActionCollapse(j.this.a(menuItem));
        }
    }

    // compiled from: MenuItemWrapperICS.java
    private class d extends d<OnMenuItemClickListener> implements OnMenuItemClickListener {
        d(OnMenuItemClickListener onMenuItemClickListener) {
            super(onMenuItemClickListener);
        }

        public final boolean onMenuItemClick(MenuItem menuItem) {
            return ((OnMenuItemClickListener) this.d).onMenuItemClick(j.this.a(menuItem));
        }
    }

    j(Context context, SupportMenuItem supportMenuItem) {
        super(context, supportMenuItem);
    }

    public int getItemId() {
        return ((SupportMenuItem) this.d).getItemId();
    }

    public int getGroupId() {
        return ((SupportMenuItem) this.d).getGroupId();
    }

    public int getOrder() {
        return ((SupportMenuItem) this.d).getOrder();
    }

    public MenuItem setTitle(CharSequence charSequence) {
        ((SupportMenuItem) this.d).setTitle(charSequence);
        return this;
    }

    public MenuItem setTitle(int i) {
        ((SupportMenuItem) this.d).setTitle(i);
        return this;
    }

    public CharSequence getTitle() {
        return ((SupportMenuItem) this.d).getTitle();
    }

    public MenuItem setTitleCondensed(CharSequence charSequence) {
        ((SupportMenuItem) this.d).setTitleCondensed(charSequence);
        return this;
    }

    public CharSequence getTitleCondensed() {
        return ((SupportMenuItem) this.d).getTitleCondensed();
    }

    public MenuItem setIcon(Drawable drawable) {
        ((SupportMenuItem) this.d).setIcon(drawable);
        return this;
    }

    public MenuItem setIcon(int i) {
        ((SupportMenuItem) this.d).setIcon(i);
        return this;
    }

    public Drawable getIcon() {
        return ((SupportMenuItem) this.d).getIcon();
    }

    public MenuItem setIntent(Intent intent) {
        ((SupportMenuItem) this.d).setIntent(intent);
        return this;
    }

    public Intent getIntent() {
        return ((SupportMenuItem) this.d).getIntent();
    }

    public MenuItem setShortcut(char c, char c2) {
        ((SupportMenuItem) this.d).setShortcut(c, c2);
        return this;
    }

    public MenuItem setNumericShortcut(char c) {
        ((SupportMenuItem) this.d).setNumericShortcut(c);
        return this;
    }

    public char getNumericShortcut() {
        return ((SupportMenuItem) this.d).getNumericShortcut();
    }

    public MenuItem setAlphabeticShortcut(char c) {
        ((SupportMenuItem) this.d).setAlphabeticShortcut(c);
        return this;
    }

    public char getAlphabeticShortcut() {
        return ((SupportMenuItem) this.d).getAlphabeticShortcut();
    }

    public MenuItem setCheckable(boolean z) {
        ((SupportMenuItem) this.d).setCheckable(z);
        return this;
    }

    public boolean isCheckable() {
        return ((SupportMenuItem) this.d).isCheckable();
    }

    public MenuItem setChecked(boolean z) {
        ((SupportMenuItem) this.d).setChecked(z);
        return this;
    }

    public boolean isChecked() {
        return ((SupportMenuItem) this.d).isChecked();
    }

    public MenuItem setVisible(boolean z) {
        return ((SupportMenuItem) this.d).setVisible(z);
    }

    public boolean isVisible() {
        return ((SupportMenuItem) this.d).isVisible();
    }

    public MenuItem setEnabled(boolean z) {
        ((SupportMenuItem) this.d).setEnabled(z);
        return this;
    }

    public boolean isEnabled() {
        return ((SupportMenuItem) this.d).isEnabled();
    }

    public boolean hasSubMenu() {
        return ((SupportMenuItem) this.d).hasSubMenu();
    }

    public SubMenu getSubMenu() {
        return a(((SupportMenuItem) this.d).getSubMenu());
    }

    public MenuItem setOnMenuItemClickListener(OnMenuItemClickListener onMenuItemClickListener) {
        ((SupportMenuItem) this.d).setOnMenuItemClickListener(onMenuItemClickListener != null ? new d(onMenuItemClickListener) : null);
        return this;
    }

    public ContextMenuInfo getMenuInfo() {
        return ((SupportMenuItem) this.d).getMenuInfo();
    }

    public void setShowAsAction(int i) {
        ((SupportMenuItem) this.d).setShowAsAction(i);
    }

    public MenuItem setShowAsActionFlags(int i) {
        ((SupportMenuItem) this.d).setShowAsActionFlags(i);
        return this;
    }

    public MenuItem setActionView(View view) {
        if (view instanceof CollapsibleActionView) {
            view = new b(view);
        }
        ((SupportMenuItem) this.d).setActionView(view);
        return this;
    }

    public MenuItem setActionView(int i) {
        ((SupportMenuItem) this.d).setActionView(i);
        View actionView = ((SupportMenuItem) this.d).getActionView();
        if (actionView instanceof CollapsibleActionView) {
            ((SupportMenuItem) this.d).setActionView(new b(actionView));
        }
        return this;
    }

    public View getActionView() {
        View actionView = ((SupportMenuItem) this.d).getActionView();
        return actionView instanceof b ? (View) ((b) actionView).a : actionView;
    }

    public MenuItem setActionProvider(android.view.ActionProvider actionProvider) {
        ((SupportMenuItem) this.d).setSupportActionProvider(actionProvider != null ? a(actionProvider) : null);
        return this;
    }

    public android.view.ActionProvider getActionProvider() {
        ActionProvider supportActionProvider = ((SupportMenuItem) this.d).getSupportActionProvider();
        return supportActionProvider instanceof a ? ((a) supportActionProvider).a : null;
    }

    public boolean expandActionView() {
        return ((SupportMenuItem) this.d).expandActionView();
    }

    public boolean collapseActionView() {
        return ((SupportMenuItem) this.d).collapseActionView();
    }

    public boolean isActionViewExpanded() {
        return ((SupportMenuItem) this.d).isActionViewExpanded();
    }

    public MenuItem setOnActionExpandListener(OnActionExpandListener onActionExpandListener) {
        ((SupportMenuItem) this.d).setSupportOnActionExpandListener(onActionExpandListener != null ? new c(onActionExpandListener) : null);
        return this;
    }

    a a(android.view.ActionProvider actionProvider) {
        return new a(this.a, actionProvider);
    }
}
