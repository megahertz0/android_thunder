package android.support.v7.app;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.app.TaskStackBuilder.SupportParentable;
import android.support.v4.view.KeyEventCompat;
import android.support.v7.widget.cu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.taobao.accs.data.Message;

public class AppCompatActivity extends FragmentActivity implements SupportParentable, l {
    private m a;
    private int b;
    private boolean c;
    private Resources d;

    public AppCompatActivity() {
        this.b = 0;
    }

    protected void onCreate(Bundle bundle) {
        m b = b();
        b.h();
        b.a(bundle);
        if (b.i() && this.b != 0) {
            if (VERSION.SDK_INT >= 23) {
                onApplyThemeResource(getTheme(), this.b, false);
            } else {
                setTheme(this.b);
            }
        }
        super.onCreate(bundle);
    }

    public void setTheme(int i) {
        super.setTheme(i);
        this.b = i;
    }

    protected void onPostCreate(Bundle bundle) {
        super.onPostCreate(bundle);
        b().c();
    }

    public MenuInflater getMenuInflater() {
        return b().b();
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

    public void addContentView(View view, LayoutParams layoutParams) {
        b().b(view, layoutParams);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b().a(configuration);
        if (this.d != null) {
            this.d.updateConfiguration(configuration, super.getResources().getDisplayMetrics());
        }
    }

    protected void onStop() {
        super.onStop();
        b().d();
    }

    protected void onPostResume() {
        super.onPostResume();
        b().e();
    }

    public View findViewById(int i) {
        return b().a(i);
    }

    public final boolean onMenuItemSelected(int i, MenuItem menuItem) {
        if (super.onMenuItemSelected(i, menuItem)) {
            return true;
        }
        ActionBar a = b().a();
        return (menuItem.getItemId() != 16908332 || a == null || (a.a() & 4) == 0) ? false : a();
    }

    protected void onDestroy() {
        super.onDestroy();
        b().g();
    }

    protected void onTitleChanged(CharSequence charSequence, int i) {
        super.onTitleChanged(charSequence, i);
        b().a(charSequence);
    }

    public void supportInvalidateOptionsMenu() {
        b().f();
    }

    public void invalidateOptionsMenu() {
        b().f();
    }

    private boolean a() {
        Intent supportParentActivityIntent = getSupportParentActivityIntent();
        if (supportParentActivityIntent == null) {
            return false;
        }
        if (NavUtils.shouldUpRecreateTask(this, supportParentActivityIntent)) {
            TaskStackBuilder create = TaskStackBuilder.create(this);
            create.addParentStack((Activity) this);
            create.startActivities();
            try {
                ActivityCompat.finishAffinity(this);
            } catch (IllegalStateException e) {
                finish();
            }
        } else {
            NavUtils.navigateUpTo(this, supportParentActivityIntent);
        }
        return true;
    }

    public Intent getSupportParentActivityIntent() {
        return NavUtils.getParentActivityIntent(this);
    }

    public void onContentChanged() {
    }

    public boolean onMenuOpened(int i, Menu menu) {
        return super.onMenuOpened(i, menu);
    }

    public void onPanelClosed(int i, Menu menu) {
        super.onPanelClosed(i, menu);
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        b().b(bundle);
    }

    private m b() {
        if (this.a == null) {
            this.a = m.a((Activity) this, (l) this);
        }
        return this.a;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (KeyEventCompat.hasModifiers(keyEvent, Message.FLAG_ERR) && keyEvent.getUnicodeChar(keyEvent.getMetaState() & -28673) == 60) {
            int action = keyEvent.getAction();
            if (action == 0) {
                ActionBar a = b().a();
                if (a != null && a.b() && a.f()) {
                    this.c = true;
                    return true;
                }
            } else if (action == 1 && this.c) {
                this.c = false;
                return true;
            }
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    public Resources getResources() {
        if (this.d == null && cu.a()) {
            this.d = new cu(this, super.getResources());
        }
        return this.d == null ? super.getResources() : this.d;
    }
}
