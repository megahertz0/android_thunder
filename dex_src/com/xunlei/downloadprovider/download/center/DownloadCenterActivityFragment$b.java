package com.xunlei.downloadprovider.download.center;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import java.util.List;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

private class DownloadCenterActivityFragment$b extends FragmentPagerAdapter {
    protected TaskListPageFragment a;
    protected TaskListPageFragment b;
    protected TaskListPageFragment c;
    protected final int d;
    protected long e;
    protected boolean f;
    final /* synthetic */ DownloadCenterActivityFragment g;
    private boolean h;

    public DownloadCenterActivityFragment$b(DownloadCenterActivityFragment downloadCenterActivityFragment, FragmentManager fragmentManager) {
        this.g = downloadCenterActivityFragment;
        super(fragmentManager);
        this.d = 3;
        this.f = false;
    }

    public final void a(long j, boolean z) {
        this.e = j;
        this.f = z;
        if (this.a != null) {
            this.a.a(this.e, this.f);
            this.e = -1;
            this.f = false;
        }
    }

    public final TaskListPageFragment a() {
        return (TaskListPageFragment) getItem(DownloadCenterActivityFragment.q(this.g).getCurrentItem());
    }

    public final List<e> b() {
        return ((TaskListPageFragment) getItem(DownloadCenterActivityFragment.q(this.g).getCurrentItem())).d.k();
    }

    public final boolean c() {
        TaskListPageFragment taskListPageFragment = (TaskListPageFragment) getItem(DownloadCenterActivityFragment.q(this.g).getCurrentItem());
        return taskListPageFragment.d != null ? taskListPageFragment.d.e() : false;
    }

    public final void a(boolean z) {
        if (this.h != z) {
            this.h = z;
            if (this.a != null) {
                this.a.a(z);
            }
            if (this.b != null) {
                this.b.a(z);
            }
            if (this.c != null) {
                this.c.a(z);
            }
        }
    }

    public final Fragment getItem(int i) {
        if (i == 0) {
            if (this.a == null) {
                this.a = TaskListPageFragment.a(0);
                this.a.e = this.g;
                this.a.a = this.g.d;
                this.a.a(this.e, this.f);
                this.a.a(this.h);
            }
            return this.a;
        } else if (i == 1) {
            if (this.b == null) {
                this.b = TaskListPageFragment.a(1);
                this.b.e = this.g;
                this.b.a = this.g.d;
                this.b.a(this.h);
            }
            return this.b;
        } else if (i != 2) {
            return null;
        } else {
            if (this.c == null) {
                this.c = TaskListPageFragment.a((int) SimpleLog.LOG_LEVEL_DEBUG);
                this.c.e = this.g;
                this.c.a = this.g.d;
                this.c.a(this.h);
            }
            return this.c;
        }
    }

    public final int getCount() {
        return MqttConnectOptions.MQTT_VERSION_3_1;
    }

    public final CharSequence getPageTitle(int i) {
        if (i == 0) {
            return this.g.getString(R.string.download_list_title_all);
        }
        if (i == 1) {
            return this.g.getString(R.string.download_list_title_unfinished);
        }
        return i == 2 ? this.g.getString(R.string.download_list_title_finished) : super.getPageTitle(i);
    }
}
