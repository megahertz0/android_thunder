package com.xunlei.downloadprovider.download.tasklist;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.c;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.download.a.n;
import com.xunlei.downloadprovider.download.a.o;
import com.xunlei.downloadprovider.download.tasklist.a.b;
import com.xunlei.downloadprovider.download.tasklist.a.h;
import com.xunlei.downloadprovider.download.tasklist.list.a.a.m;
import com.xunlei.downloadprovider.download.tasklist.list.b.e;
import com.xunlei.downloadprovider.download.util.k;
import com.xunlei.downloadprovider.frame.user.bx;
import com.xunlei.downloadprovider.member.b.a.a;
import com.xunlei.xiazaibao.BuildConfig;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class TaskListPageFragment extends Fragment implements a {
    public com.xunlei.downloadprovider.download.a.a a;
    public int b;
    public RecyclerView c;
    public com.xunlei.downloadprovider.download.tasklist.list.a d;
    public a e;
    public Set<LOAD_TAG> f;
    public boolean g;
    public int h;
    public boolean i;
    public k j;
    public boolean k;
    public boolean l;
    public boolean m;
    com.xunlei.downloadprovider.member.b.a n;
    private b o;
    private o p;
    private c q;
    private b.c r;
    private ErrorView s;
    private boolean t;
    private int u;
    private bx v;
    private Handler w;
    private long x;
    private boolean y;

    public enum LOAD_TAG {
        LOAD_LIST_AD,
        LOAD_RECOMMEND_AD,
        LOAD_TASK;

        static {
            LOAD_LIST_AD = new com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG("LOAD_LIST_AD", 0);
            LOAD_RECOMMEND_AD = new com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG("LOAD_RECOMMEND_AD", 1);
            LOAD_TASK = new com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG("LOAD_TASK", 2);
            a = new com.xunlei.downloadprovider.download.tasklist.TaskListPageFragment.LOAD_TAG[]{LOAD_LIST_AD, LOAD_RECOMMEND_AD, LOAD_TASK};
        }
    }

    static /* synthetic */ int c(TaskListPageFragment taskListPageFragment) {
        int i = taskListPageFragment.u;
        taskListPageFragment.u = i + 1;
        return i;
    }

    static /* synthetic */ void g(TaskListPageFragment taskListPageFragment) {
        if (taskListPageFragment.x > 0) {
            int i;
            long j = taskListPageFragment.x;
            int a = taskListPageFragment.d.a(taskListPageFragment.x);
            if (a == taskListPageFragment.d.getItemCount()) {
                i = 1;
            } else {
                Object obj = null;
            }
            if (a >= 0) {
                taskListPageFragment.x = 0;
                if (i != 0) {
                    taskListPageFragment.c.getLayoutManager().c(a);
                } else {
                    ((LinearLayoutManager) taskListPageFragment.c.getLayoutManager()).e(a, 1);
                }
            }
            if (taskListPageFragment.y && j > 0) {
                com.xunlei.downloadprovider.download.tasklist.a.a a2 = h.a().a(j);
                if (a2 != null) {
                    com.xunlei.downloadprovider.download.a.a.a(taskListPageFragment.getActivity(), a2);
                }
            }
        }
    }

    public TaskListPageFragment() {
        this.b = 0;
        this.u = 0;
        this.f = new HashSet();
        this.h = 0;
        this.w = new Handler();
        this.j = new a(this, this.w);
        this.k = false;
        this.l = false;
        this.m = false;
    }

    public static TaskListPageFragment a(int i) {
        TaskListPageFragment taskListPageFragment = new TaskListPageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("page_index", i);
        taskListPageFragment.setArguments(bundle);
        return taskListPageFragment;
    }

    public final void a(boolean z) {
        if (this.t != z) {
            this.t = z;
            if (this.d != null) {
                this.d.a(z);
            }
        }
    }

    public final void a() {
        if (this.c != null && this.d.getItemCount() > 0) {
            this.c.scrollToPosition(0);
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f.clear();
        this.f.add(LOAD_TAG.LOAD_TASK);
        if (getArguments() != null) {
            this.b = getArguments().getInt("page_index");
        }
        this.r = new b(this);
        if (!m.a().b) {
            boolean z;
            m a = m.a();
            int i = this.b;
            if (this.x > 0) {
                z = true;
            } else {
                z = false;
            }
            a.c.put(Integer.valueOf(i), Boolean.valueOf(z));
        }
        this.o = h.a().b(this.b);
        if (this.o != null) {
            this.o.a(this.r);
        }
        this.d = new com.xunlei.downloadprovider.download.tasklist.list.a(getActivity(), this.b, this);
        this.d.setHasStableIds(true);
        this.d.m = this.a;
        this.d.a(this.t);
        this.d.g = new e(this);
        this.q = new g(this);
        this.d.registerAdapterDataObserver(this.q);
        if (this.o != null) {
            this.d.a(this.o.a());
            if (this.o.e && !this.d.f) {
                new StringBuilder("notifyDownloadTaskLoaded - PageIndex = ").append(this.b);
                this.d.n();
            }
            this.u = 1;
        }
        this.p = new h(this);
        if (this.a != null) {
            com.xunlei.downloadprovider.download.a.a.a(this.p);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        new StringBuilder("onCreateView: pageIndex = ").append(this.b);
        View inflate = layoutInflater.inflate(R.layout.fragment_task_list_page, viewGroup, false);
        this.c = (RecyclerView) inflate.findViewById(R.id.taskListRecyclerView);
        this.c.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.c.setAdapter(this.d);
        this.s = (ErrorView) inflate.findViewById(R.id.emptyView);
        this.u = 1;
        return inflate;
    }

    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        this.j.a(false);
        if (this.u > 0) {
            this.u = 0;
            if (this.d != null) {
                this.d.notifyDataSetChanged();
            }
        }
        new StringBuilder("onResume: pageIndex = ").append(this.b).append(" cost:").append(SystemClock.elapsedRealtime() - elapsedRealtime).append("ms");
        if (this.b == 0) {
            this.v = new bx();
            j jVar = new j(getActivity(), "vip_renew");
            String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            CharSequence b = jVar.b(new StringBuilder("dateAndUser").append(this.v.d).toString(), BuildConfig.VERSION_NAME);
            if (this.v != null) {
                if (TextUtils.equals(format + this.v.d, b)) {
                    this.l = false;
                } else {
                    this.l = true;
                }
            }
        }
        if (this.l && !this.m) {
            if (this.n == null) {
                this.n = com.xunlei.downloadprovider.member.b.b.a(this);
            }
            this.n.c("9");
        }
    }

    public final void a(long j, boolean z) {
        this.x = j;
        this.y = z;
        if (this.d != null && this.d.getItemCount() > 2) {
            this.w.postDelayed(new i(this), 100);
        }
    }

    public void onPause() {
        new StringBuilder("onPause: pageIndex = ").append(this.b);
        this.j.b();
        super.onPause();
    }

    public void onDestroyView() {
        new StringBuilder("onDestroyView: pageIndex = ").append(this.b);
        this.s = null;
        super.onDestroyView();
    }

    public void onDestroy() {
        new StringBuilder("onDestroy: pageIndex = ").append(this.b);
        if (!(this.q == null || this.d == null)) {
            this.d.unregisterAdapterDataObserver(this.q);
        }
        if (!(this.o == null || this.r == null)) {
            this.o.b(this.r);
        }
        if (!(this.a == null || this.p == null)) {
            n.a().a.unregisterObserver(this.p);
            this.p = null;
        }
        super.onDestroy();
    }

    public final void b() {
        if (this.n == null) {
            this.n = com.xunlei.downloadprovider.member.b.b.a(this);
        }
        com.xunlei.downloadprovider.member.b.c b = this.n.b("9");
        if (b != null && b.a()) {
            e eVar = new e(201, b, 0);
            if (this.d != null) {
                this.d.l();
                this.d.b(eVar);
                this.g = true;
            }
        }
    }

    static /* synthetic */ void a(TaskListPageFragment taskListPageFragment) {
        if (taskListPageFragment.isVisible() && taskListPageFragment.d != null && taskListPageFragment.k) {
            taskListPageFragment.k = false;
            taskListPageFragment.d.a(taskListPageFragment.o);
        }
    }
}
