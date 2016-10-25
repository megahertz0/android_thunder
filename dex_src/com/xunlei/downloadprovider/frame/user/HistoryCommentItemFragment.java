package com.xunlei.downloadprovider.frame.user;

import android.content.BroadcastReceiver;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.c.a;
import com.xunlei.downloadprovider.c.a.c;
import com.xunlei.downloadprovider.c.a.n;
import com.xunlei.downloadprovider.commonview.ErrorView;
import com.xunlei.downloadprovider.commonview.UnifiedLoadingView;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.member.login.LoginHelper;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.downloadprovider.web.base.d;
import com.xunlei.downloadprovider.web.base.model.StateSyncManager;
import com.xunlei.downloadprovider.web.base.model.StateSyncManager.CommentSateInfo;
import com.xunlei.downloadprovider.web.base.model.StateSyncManager.SourceFrom;
import com.xunlei.downloadprovider.web.base.model.u;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import com.xunlei.xllib.a.b;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HistoryCommentItemFragment extends Fragment implements ah$a {
    private static final String a;
    private Context b;
    private RecyclerView c;
    private ah d;
    private a e;
    private long f;
    private UnifiedLoadingView g;
    private ErrorView h;
    private ArrayList<c> i;
    private boolean j;
    private LinearLayoutManager k;
    private int l;
    private int m;
    private ai<Void> n;
    private ColorDrawable o;
    private int p;
    private com.xunlei.downloadprovider.web.base.a q;
    private d r;
    private ClipboardManager s;
    private com.xunlei.downloadprovider.web.base.model.d t;
    private BroadcastReceiver u;

    static /* synthetic */ void a(HistoryCommentItemFragment historyCommentItemFragment, long j) {
        List<ai> arrayList = new ArrayList(1);
        for (ai aiVar : historyCommentItemFragment.d.a) {
            if (aiVar.a == 0) {
                c cVar = (c) aiVar.b;
                if (cVar.a == j) {
                    arrayList.add(aiVar);
                } else {
                    List list = cVar.q;
                    if (list != null && list.size() > 0) {
                        n nVar = (n) list.get(0);
                        if (nVar.a == j) {
                            nVar.a = -1;
                            historyCommentItemFragment.d.b(aiVar);
                        }
                    }
                }
            }
        }
        if (arrayList.size() > 0) {
            for (ai aiVar2 : arrayList) {
                historyCommentItemFragment.i.remove(aiVar2.b);
                historyCommentItemFragment.d.a(aiVar2);
            }
            if (historyCommentItemFragment.i.isEmpty()) {
                historyCommentItemFragment.h.setErrorType(0);
                historyCommentItemFragment.h.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
            }
        }
    }

    static {
        a = HistoryCommentItemFragment.class.getSimpleName();
    }

    public HistoryCommentItemFragment() {
        this.f = -1;
        this.j = true;
        this.l = 2;
        this.m = 0;
    }

    public static HistoryCommentItemFragment a(long j) {
        HistoryCommentItemFragment historyCommentItemFragment = new HistoryCommentItemFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("user_id_arg", j);
        historyCommentItemFragment.setArguments(bundle);
        return historyCommentItemFragment;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.f = arguments.getLong("user_id_arg", -1);
        }
        if (this.f == -1) {
            throw new IllegalArgumentException("User ID is INVALID");
        }
        this.s = (ClipboardManager) this.b.getSystemService("clipboard");
        this.t = new com.xunlei.downloadprovider.web.base.model.d(this.b);
        com.xunlei.downloadprovider.web.base.model.d dVar = this.t;
        dVar.c.a = this.f;
        dVar.d.submit(dVar.c);
        this.u = new h(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("action_comment_zan_state_changed");
        this.b.registerReceiver(this.u, intentFilter);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(2130968770, viewGroup, false);
        this.c = (RecyclerView) inflate.findViewById(R.id.list);
        this.k = new LinearLayoutManager(this.b);
        this.c.setLayoutManager(this.k);
        this.o = new ColorDrawable(Color.parseColor("#efeff0"));
        this.p = g.a(this.b, 8.0f);
        this.c.addItemDecoration(new n(this));
        this.g = (UnifiedLoadingView) inflate.findViewById(2131756035);
        this.g.setType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
        this.g.b();
        this.h = (ErrorView) inflate.findViewById(2131756034);
        this.h.setErrorType(0);
        this.h.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.n = new ai(null, 1);
        this.t.e = new o(this);
        this.c.setLayoutManager(new LinearLayoutManager(this.b));
        this.h.a("\u5237\u65b0", new p(this));
        this.h.setOnTouchListener(new q(this));
        this.g.setOnTouchListener(new r(this));
        this.c.addOnScrollListener(new s(this));
        this.r = new d(this.b);
        this.r.a(new t(this));
        this.r.b(new u(this));
        this.r.c(new i(this));
        this.r.d(new j(this));
        this.q = new com.xunlei.downloadprovider.web.base.a(this.b);
        this.q.a(new k(this));
        this.e = new a();
        this.e.a(null);
        this.d = new ah(this);
        this.c.setAdapter(this.d);
        this.i = new ArrayList();
        if (this.f < 0) {
            throw new IllegalArgumentException("user id is invalid");
        }
        if (b.a(this.b)) {
            c();
        } else {
            d();
        }
        return inflate;
    }

    public void onResume() {
        super.onResume();
        if (this.d == null) {
            this.d.notifyDataSetChanged();
        }
    }

    public final void a() {
        String trim = this.q.a().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            XLToast.b(this.b, XLToastType.XLTOAST_TYPE_NORMAL, "\u8bf7\u586b\u5199\u8bc4\u8bba\u5185\u5bb9");
        } else if (b.a(this.b)) {
            this.q.a(false);
            String k = com.xunlei.downloadprovider.a.b.k();
            c cVar = this.q.b;
            a(cVar);
            this.t.a(trim, k, cVar);
        } else {
            XLToast.b(this.b, XLToastType.XLTOAST_TYPE_ALARM, "\u65e0\u7f51\u7edc\u8fde\u63a5");
        }
    }

    private void a(c cVar) {
        u uVar = new u();
        uVar.a = cVar.e;
        uVar.g = cVar.f;
        this.t.a(uVar);
    }

    private void c() {
        this.g.a();
        this.h.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.m = 0;
        this.e.a(this.f, 0, new l(this));
    }

    private void d() {
        IOException e;
        ClassNotFoundException e2;
        Throwable th;
        File file = new File(this.b.getCacheDir(), new StringBuilder("comments_data_").append(this.f).toString());
        if (file.exists()) {
            ObjectInputStream objectInputStream = null;
            ObjectInputStream objectInputStream2;
            try {
                objectInputStream2 = new ObjectInputStream(new FileInputStream(file));
                try {
                    this.i = (ArrayList) objectInputStream2.readObject();
                    try {
                        objectInputStream2.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                } catch (IOException e4) {
                    e3 = e4;
                    new StringBuilder("serializeHistoryData IO error=>").append(e3.getMessage());
                    e3.printStackTrace();
                    if (objectInputStream2 != null) {
                        objectInputStream2.close();
                    }
                    if (this.i != null) {
                    }
                    this.h.setErrorType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    this.h.setVisibility(0);
                    this.m = 1;
                } catch (ClassNotFoundException e5) {
                    e2 = e5;
                    objectInputStream = objectInputStream2;
                    new StringBuilder("serializeHistoryData class error=>").append(e2.getMessage());
                    e2.printStackTrace();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (this.i != null) {
                    }
                    this.h.setErrorType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    this.h.setVisibility(0);
                    this.m = 1;
                }
            } catch (IOException e6) {
                e3 = e6;
                objectInputStream2 = null;
                try {
                    new StringBuilder("serializeHistoryData IO error=>").append(e3.getMessage());
                    e3.printStackTrace();
                    if (objectInputStream2 != null) {
                        try {
                            objectInputStream2.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    objectInputStream = objectInputStream2;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    throw th;
                }
                if (this.i != null) {
                }
                this.h.setErrorType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                this.h.setVisibility(0);
                this.m = 1;
            } catch (ClassNotFoundException e7) {
                e2 = e7;
                try {
                    new StringBuilder("serializeHistoryData class error=>").append(e2.getMessage());
                    e2.printStackTrace();
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e322) {
                            e322.printStackTrace();
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (objectInputStream != null) {
                        try {
                            objectInputStream.close();
                        } catch (IOException e8) {
                            e8.printStackTrace();
                        }
                    }
                    throw th;
                }
                if (this.i != null) {
                }
                this.h.setErrorType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                this.h.setVisibility(0);
                this.m = 1;
            }
        }
        if (this.i != null || this.i.isEmpty()) {
            this.h.setErrorType(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            this.h.setVisibility(0);
            this.m = 1;
        }
        this.m = 2;
        List arrayList = new ArrayList(this.i.size());
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            arrayList.add(new ai((c) it.next(), 0));
        }
        if (arrayList.size() < 20) {
            this.j = false;
        }
        this.d.a(arrayList);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.b = context;
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onDestroy() {
        IOException e;
        Throwable th;
        this.b.unregisterReceiver(this.u);
        this.t.d();
        this.t.e();
        if (this.f == LoginHelper.a().j && this.i != null && this.i.size() > 0) {
            File file = new File(this.b.getCacheDir(), new StringBuilder("comments_data_").append(this.f).toString());
            if (file.exists()) {
                file.delete();
            }
            ObjectOutputStream objectOutputStream;
            try {
                file.createNewFile();
                objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                try {
                    objectOutputStream.writeObject(this.i);
                    objectOutputStream.flush();
                    try {
                        objectOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (IOException e3) {
                    e2 = e3;
                    new StringBuilder("serializeHistoryData error=>").append(e2.getMessage());
                    e2.printStackTrace();
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    super.onDestroy();
                }
            } catch (IOException e4) {
                e2 = e4;
                objectOutputStream = null;
                try {
                    new StringBuilder("serializeHistoryData error=>").append(e2.getMessage());
                    e2.printStackTrace();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    throw th;
                }
                super.onDestroy();
            } catch (Throwable th3) {
                th = th3;
                objectOutputStream = null;
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e5) {
                        e5.printStackTrace();
                    }
                }
                throw th;
            }
        }
        super.onDestroy();
    }

    public final void a(int i, Object obj) {
        String str;
        com.xunlei.downloadprovidercommon.a.c a;
        if (i == 0) {
            if (obj != null) {
                this.t.d();
                this.t.e();
                u uVar = (u) obj;
                ShortMovieDetailActivity.a(this.b, uVar.n, uVar.i, From.PERSONAL_SPACE, uVar.a, uVar.g, uVar.b, uVar.e, uVar.e, uVar.c, uVar.l, -1);
                str = uVar.a;
                a = com.xunlei.downloadprovidercommon.a.a.a("android_personal_account", "personal_space_movie_click");
                a.b("movieid", str);
                com.xunlei.downloadprovidercommon.a.d.a(a);
            }
        } else if (i == 2) {
            if (obj != null) {
                r14 = (c) obj;
                if (r14.l) {
                    XLToast.b(this.b, XLToastType.XLTOAST_TYPE_ALARM, "\u6b64\u8bc4\u8bba\u6682\u65f6\u65e0\u6cd5\u56de\u590d");
                    return;
                }
                this.q.b(new StringBuilder("\u56de\u590d ").append(r14.j).toString());
                if (this.q == null) {
                    this.q = new com.xunlei.downloadprovider.web.base.a(this.b);
                }
                if (!this.q.isShowing()) {
                    this.q.show();
                }
                this.q.a(r14);
                long j = r14.a;
                com.xunlei.downloadprovidercommon.a.c a2 = com.xunlei.downloadprovidercommon.a.a.a("android_personal_account", "personal_space_discuss_show");
                a2.b("discussid", String.valueOf(j));
                com.xunlei.downloadprovidercommon.a.d.a(a2);
            }
        } else if (i == 3) {
            this.r.a((c) obj);
            this.r.show();
        } else if (i == 1) {
            if (obj != null) {
                r14 = (c) obj;
                a(r14);
                this.t.a(r14);
                CommentSateInfo commentSateInfo = new CommentSateInfo();
                commentSateInfo.a = r14.a;
                commentSateInfo.d = r14.f;
                commentSateInfo.b = r14.m;
                commentSateInfo.e = r14.i;
                commentSateInfo.c = r14.n;
                StateSyncManager.a(this.b, SourceFrom.PAGE_PERSONAL_SPACE, commentSateInfo);
                str = r14.s.a;
                long j2 = r14.a;
                a = com.xunlei.downloadprovidercommon.a.a.a("android_personal_account", "personal_space_discuss_zan");
                a.b("movieid", str);
                a.b("discussid", String.valueOf(j2));
                com.xunlei.downloadprovidercommon.a.d.a(a);
            }
        } else if (i == 4) {
            n nVar = (n) obj;
            if (nVar == null) {
                return;
            }
            if (nVar.c == this.f) {
                this.c.smoothScrollToPosition(0);
            } else {
                PersonalSpaceActivity.a(this.b, PersonalSpaceActivity.From.PERSONAL_SPACE, nVar.c, nVar.d, nVar.e);
            }
        }
    }

    static /* synthetic */ void a(HistoryCommentItemFragment historyCommentItemFragment, Intent intent) {
        if (((SourceFrom) SourceFrom.valueOf(SourceFrom.class, intent.getStringExtra("source_from"))) != SourceFrom.PAGE_PERSONAL_SPACE) {
            CommentSateInfo commentSateInfo = (CommentSateInfo) intent.getParcelableExtra("comment_info");
            if (commentSateInfo.e == historyCommentItemFragment.f) {
                for (ai aiVar : historyCommentItemFragment.d.a) {
                    if (aiVar.a == 0) {
                        c cVar = (c) aiVar.b;
                        if (cVar.a == commentSateInfo.a) {
                            cVar.m = commentSateInfo.b;
                            cVar.n = commentSateInfo.c;
                            historyCommentItemFragment.d.b(aiVar);
                            return;
                        }
                    }
                }
            }
        }
    }

    static /* synthetic */ void n(HistoryCommentItemFragment historyCommentItemFragment) {
        if (historyCommentItemFragment.m != 0 && historyCommentItemFragment.l != 0 && !historyCommentItemFragment.i.isEmpty()) {
            historyCommentItemFragment.l = 0;
            historyCommentItemFragment.e.a(historyCommentItemFragment.f, ((c) historyCommentItemFragment.i.get(historyCommentItemFragment.i.size() - 1)).a, new m(historyCommentItemFragment));
        }
    }

    static /* synthetic */ void u(HistoryCommentItemFragment historyCommentItemFragment) {
        File file = new File(historyCommentItemFragment.b.getCacheDir(), new StringBuilder("comments_data_").append(historyCommentItemFragment.f).toString());
        if (file.exists()) {
            file.delete();
        }
    }

    static /* synthetic */ void a(HistoryCommentItemFragment historyCommentItemFragment, List list) {
        List<com.xunlei.downloadprovider.web.base.model.a.a> list2 = historyCommentItemFragment.t.h;
        if (list2 != null && list2.size() > 0) {
            for (com.xunlei.downloadprovider.web.base.model.a.a aVar : list2) {
                for (c cVar : list) {
                    if (cVar.a == aVar.a) {
                        cVar.m = true;
                        if (!aVar.d) {
                            cVar.n++;
                        }
                    }
                }
            }
        }
    }
}
