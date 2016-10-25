package com.xunlei.downloadprovider.download.tasklist.list.c;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.support.v4.widget.AutoScrollHelper;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.d;
import com.tencent.open.yyb.TitleBar;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.download.a.n;
import com.xunlei.downloadprovider.download.center.widget.v;
import com.xunlei.downloadprovider.download.create.DownloadBtFileExplorerActivity;
import com.xunlei.downloadprovider.download.tasklist.a.a;
import com.xunlei.downloadprovider.download.tasklist.list.b.f;
import com.xunlei.downloadprovider.download.tasklist.list.f.e;
import com.xunlei.downloadprovider.download.util.DownloadError;
import com.xunlei.downloadprovider.download.util.DownloadError.FailureCode;
import com.xunlei.downloadprovider.service.downloads.task.info.TaskRunningInfo;
import com.xunlei.downloadprovider.xlui.widget.ZHTextView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import org.android.spdy.SpdyAgent;

// compiled from: TaskDownloadCardViewHolder.java
public final class g extends f {
    public static boolean a;
    private TextView A;
    private TextView B;
    private View C;
    private d D;
    private a E;
    private e F;
    private View G;
    private ImageView H;
    private a I;
    private a J;
    private int K;
    private boolean L;
    private com.xunlei.downloadprovider.download.tasklist.list.b.e M;
    private boolean N;
    private Context O;
    private int P;
    private j Q;
    private j R;
    private OnClickListener S;
    private OnClickListener T;
    private OnClickListener U;
    private OnClickListener V;
    private OnClickListener W;
    private OnClickListener X;
    private OnClickListener Y;
    private OnClickListener Z;
    public int b;
    d c;
    private com.xunlei.downloadprovider.download.tasklist.list.a d;
    private View e;
    private View f;
    private View g;
    private View h;
    private ImageView k;
    private ZHTextView l;
    private ProgressBar m;
    private TextView n;
    private TextView o;
    private TextView p;
    private ImageView q;
    private TextView r;
    private TextView s;
    private View t;
    private TextView u;
    private View v;
    private TextView w;
    private LinearLayout x;
    private TextView y;
    private View z;

    public static g a(Context context, com.xunlei.downloadprovider.download.a.a aVar, com.xunlei.downloadprovider.download.tasklist.list.a aVar2, int i) {
        g gVar = new g(View.inflate(context, 2130968845, null));
        gVar.a(aVar);
        gVar.d = aVar2;
        gVar.O = context;
        gVar.P = i;
        return gVar;
    }

    public final void a(com.xunlei.downloadprovider.download.a.a aVar) {
        super.a(aVar);
        if (this.D != null) {
            this.D.h = aVar;
        }
    }

    private g(View view) {
        super(view);
        this.K = 0;
        this.L = false;
        this.N = false;
        this.b = 1;
        this.c = d.a();
        this.S = new h(this);
        this.T = new n(this);
        this.U = new o(this);
        this.V = new p(this);
        this.W = new q(this);
        this.X = new r(this);
        this.Y = new s(this);
        this.Z = new t(this);
        this.e = view.findViewById(2131756348);
        this.f = view.findViewById(2131756345);
        this.D = new d(c(), (ViewStub) view.findViewById(2131756357));
        this.D.d = new u(this);
        if (h() != null) {
            this.D.h = h();
        }
        this.k = (ImageView) view.findViewById(2131755766);
        this.l = (ZHTextView) view.findViewById(2131755768);
        this.m = (ProgressBar) view.findViewById(2131755776);
        this.q = (ImageView) view.findViewById(2131756349);
        this.r = (TextView) view.findViewById(2131755775);
        this.n = (TextView) view.findViewById(2131755774);
        this.o = (TextView) view.findViewById(2131756346);
        this.p = (TextView) view.findViewById(2131756347);
        this.s = (TextView) view.findViewById(2131755773);
        this.t = view.findViewById(2131756342);
        this.u = (TextView) view.findViewById(2131755772);
        this.v = view.findViewById(2131756337);
        this.w = (TextView) view.findViewById(2131756343);
        this.x = (LinearLayout) view.findViewById(2131756276);
        this.g = view.findViewById(2131756353);
        this.h = view.findViewById(2131756350);
        this.y = (TextView) view.findViewById(2131756352);
        this.z = view.findViewById(2131756356);
        this.A = (TextView) view.findViewById(2131756354);
        this.B = (TextView) view.findViewById(2131756355);
        this.C = view.findViewById(2131756351);
        this.G = view.findViewById(2131756341);
        this.H = (ImageView) view.findViewById(2131755764);
        this.E = new a(c(), (ViewStub) view.findViewById(2131756358));
        this.F = new e(c(), (ViewStub) view.findViewById(2131756359));
        this.H.setOnClickListener(new i(this));
        view.setOnClickListener(new j(this));
        view.setOnLongClickListener(new k(this));
    }

    private String a() {
        String str = com.umeng.a.d;
        if (this.P == 0) {
            return "total";
        }
        if (this.P == 1) {
            return "downloading";
        }
        return this.P == 2 ? "finish" : str;
    }

    private void b() {
        if (this.J != null && this.J.mSeen == 0 && this.J.mTaskStatus == 8) {
            this.J.mSeen = 1;
            a aVar = this.J;
            aVar.mRevision++;
            if (this.d != null) {
                this.d.notifyItemChanged(getAdapterPosition());
            }
            c(this.J);
            com.xunlei.downloadprovider.service.downloads.task.d.a();
            com.xunlei.downloadprovider.service.downloads.task.d.a(this.J);
        }
    }

    private Drawable a(int i) {
        return VERSION.SDK_INT >= 21 ? this.itemView.getResources().getDrawable(i, null) : this.itemView.getResources().getDrawable(i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.xunlei.downloadprovider.download.tasklist.list.b.e r14) {
        throw new UnsupportedOperationException("Method not decompiled: com.xunlei.downloadprovider.download.tasklist.list.c.g.a(com.xunlei.downloadprovider.download.tasklist.list.b.e):void");
        /*
        this = this;
        super.a(r14);
        r13.M = r14;
        r0 = r14.b();
        r13.J = r0;
        r0 = r13.J;
        if (r0 != 0) goto L_0x0016;
    L_0x000f:
        r0 = 0;
        r13.I = r0;
        r0 = 0;
        r13.K = r0;
    L_0x0015:
        return;
    L_0x0016:
        r0 = r13.J;
        r0 = r0.mRunningInfo;
        r0 = r0.a();
        if (r0 == 0) goto L_0x0030;
    L_0x0020:
        r0 = r13.J;
        r0 = r0.mRunningInfo;
        r1 = -1;
        r0.a(r1);
        r0 = r13.J;
        r1 = r0.mRevision;
        r1 = r1 + 1;
        r0.mRevision = r1;
    L_0x0030:
        r0 = r13.J;
        r1 = r13.I;
        if (r0 != r1) goto L_0x004b;
    L_0x0036:
        r0 = r13.J;
        r0 = r0.mRevision;
        r1 = r13.K;
        if (r0 != r1) goto L_0x004b;
    L_0x003e:
        r0 = r13.K;
        if (r0 == 0) goto L_0x004b;
    L_0x0042:
        r0 = r13.M;
        r13.b(r0);
        r13.d();
        goto L_0x0015;
    L_0x004b:
        r0 = r13.E;
        r1 = 8;
        r0.a(r1);
        r0 = new java.lang.StringBuilder;
        r1 = "gone:";
        r0.<init>(r1);
        r1 = r13.J;
        r1 = r1.mFileName;
        r0.append(r1);
        r0 = r13.F;
        r1 = 8;
        r0.a(r1);
        r0 = new java.lang.StringBuilder;
        r1 = "fillData - ";
        r0.<init>(r1);
        r0 = r0.append(r13);
        r1 = " pageIndex:";
        r0 = r0.append(r1);
        r1 = r13.d;
        r1 = r1.a();
        r0.append(r1);
        r0 = r13.u;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = r13.t;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = 0;
        r13.N = r0;
        r0 = r13.C;
        r1 = 8;
        r0.setVisibility(r1);
        r13.d();
        r0 = r13.J;
        if (r0 == 0) goto L_0x014d;
    L_0x00a3:
        r0 = r13.J;
        r13.c(r0);
        r3 = r13.J;
        r2 = 0;
        r1 = 0;
        r4 = r3.mFileName;
        r5 = com.xunlei.downloadprovider.download.tasklist.list.c.g.c.b(r3);
        r6 = r13.k;
        r0 = r3.c;
        if (r0 == 0) goto L_0x02a8;
    L_0x00b8:
        r0 = 1061158912; // 0x3f400000 float:0.75 double:5.24282163E-315;
    L_0x00ba:
        r6.setAlpha(r0);
        r0 = r13.I;
        if (r0 != r3) goto L_0x00c5;
    L_0x00c1:
        r0 = r13.L;
        if (r0 != 0) goto L_0x00e4;
    L_0x00c5:
        r0 = 0;
        r13.L = r0;
        r0 = r3.mIconUrl;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x02ac;
    L_0x00d0:
        r0 = r13.k;
        r1 = 2130838396; // 0x7f02037c float:1.7281773E38 double:1.0527740483E-314;
        r0.setImageResource(r1);
        r0 = r13.c;
        r1 = r3.mIconUrl;
        r2 = r13.k;
        r0.a(r1, r2);
        r0 = 1;
        r13.L = r0;
    L_0x00e4:
        r0 = r13.J;
        r1 = com.xunlei.downloadprovider.download.tasklist.list.c.g.c.a(r0);
        r0 = r13.J;
        r2 = r0.mFileSize;
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x034e;
    L_0x00f4:
        r0 = r13.J;
        r2 = r0.mFileSize;
        r0 = com.xunlei.downloadprovider.download.util.a.b(r2);
        r2 = r13.s;
        r2.setText(r0);
    L_0x0101:
        r0 = r13.J;
        r0 = r0.a;
        r0 = android.text.TextUtils.isEmpty(r0);
        if (r0 != 0) goto L_0x011c;
    L_0x010b:
        if (r1 == 0) goto L_0x011c;
    L_0x010d:
        r0 = r13.u;
        r2 = 0;
        r0.setVisibility(r2);
        r0 = r13.u;
        r2 = r13.J;
        r2 = r2.a;
        r0.setText(r2);
    L_0x011c:
        r0 = 0;
        if (r1 == 0) goto L_0x0120;
    L_0x011f:
        r0 = 1;
    L_0x0120:
        if (r0 == 0) goto L_0x0358;
    L_0x0122:
        r0 = r13.J;
        r0 = r0.c;
        if (r0 != 0) goto L_0x0358;
    L_0x0128:
        r0 = r13.t;
        r1 = 0;
        r0.setVisibility(r1);
        r0 = r13.N;
        r1 = 1;
        if (r0 == r1) goto L_0x0136;
    L_0x0133:
        r0 = 1;
        r13.N = r0;
    L_0x0136:
        r0 = r13.J;
        r0 = r0.getTaskDownloadUrl();
        r0 = a(r0);
        r1 = android.text.TextUtils.isEmpty(r0);
        if (r1 == 0) goto L_0x0364;
    L_0x0146:
        r0 = r13.w;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x014d:
        r2 = r13.J;
        r0 = r2.mTaskStatus;
        r1 = 8;
        if (r0 != r1) goto L_0x0384;
    L_0x0155:
        r0 = r13.m;
        r1 = 8;
        r0.setVisibility(r1);
    L_0x015c:
        r0 = r2.mTaskStatus;
        r1 = 2;
        if (r0 != r1) goto L_0x0482;
    L_0x0161:
        r0 = r13.f;
        r1 = 0;
        r0.setVisibility(r1);
        r0 = r13.e;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = r2.mDownloadSpeed;
        r4 = 1;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x03c3;
    L_0x0176:
        r0 = r13.o;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = r13.n;
        r1 = 2131231234; // 0x7f080202 float:1.8078543E38 double:1.052968136E-314;
        r0.setText(r1);
    L_0x0185:
        r0 = r2.mDownloadRemainTime;
        r4 = 0;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x0479;
    L_0x018d:
        r0 = r13.c();
        r2 = r2.mDownloadRemainTime;
        r0 = com.xunlei.downloadprovider.download.util.a.a(r0, r2);
        r1 = r13.p;
        r2 = 0;
        r1.setVisibility(r2);
        r1 = r13.p;
        r1.setText(r0);
    L_0x01a2:
        r1 = r13.J;
        r0 = 0;
        r2 = r1.mTaskStatus;
        r3 = 8;
        if (r2 != r3) goto L_0x0850;
    L_0x01ab:
        r2 = r13.g;
        r3 = 0;
        r2.setVisibility(r3);
        r2 = 1;
        r13.a(r2);
        r2 = r13.h;
        r3 = 8;
        r2.setVisibility(r3);
    L_0x01bc:
        r2 = r13.C;
        r3 = 8;
        r2.setVisibility(r3);
        r2 = r1.mTaskStatus;
        r3 = 2;
        if (r2 == r3) goto L_0x01cd;
    L_0x01c8:
        r2 = r1.mTaskStatus;
        r3 = 1;
        if (r2 != r3) goto L_0x01e2;
    L_0x01cd:
        r2 = r1.mHasVipChannelSpeedup;
        if (r2 != 0) goto L_0x01e2;
    L_0x01d1:
        r2 = r1.mHasLixianSpeedup;
        if (r2 != 0) goto L_0x01e2;
    L_0x01d5:
        r2 = r13.C;
        r3 = 0;
        r2.setVisibility(r3);
        r2 = r13.C;
        r3 = r13.Z;
        r2.setOnClickListener(r3);
    L_0x01e2:
        r2 = r13.y;
        r3 = 0;
        r2.setOnClickListener(r3);
        r2 = r13.y;
        r3 = 0;
        r2.setPressed(r3);
        r2 = r1.mTaskStatus;
        r3 = 8;
        if (r2 != r3) goto L_0x086d;
    L_0x01f4:
        r2 = r13.z;
        r3 = 0;
        r2.setPressed(r3);
        r2 = r1.c;
        if (r2 == 0) goto L_0x0863;
    L_0x01fe:
        r2 = r13.z;
        r3 = 0;
        r2.setVisibility(r3);
        r2 = r13.z;
        r3 = r13.W;
        r2.setOnClickListener(r3);
    L_0x020b:
        if (r0 == 0) goto L_0x0910;
    L_0x020d:
        r0 = com.xunlei.downloadprovider.download.tasklist.list.c.g.c.b(r1);
        r2 = com.xunlei.downloadprovider.download.tasklist.list.c.g.AnonymousClass_1.b;
        r0 = r0.ordinal();
        r0 = r2[r0];
        switch(r0) {
            case 1: goto L_0x08b6;
            case 2: goto L_0x08cc;
            default: goto L_0x021c;
        };
    L_0x021c:
        r0 = com.xunlei.downloadprovider.download.tasklist.list.c.g.a.a;
        r1 = r13.S;
        r13.a(r0, r1);
    L_0x0223:
        r6 = r13.J;
        r0 = r13.D;
        r1 = 0;
        r0.c = r1;
        r0 = r13.D;
        r1 = 8;
        r0.a(r1);
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        r0 = r6.getTaskId();
        r2 = com.xunlei.downloadprovider.service.downloads.task.d.d(r0);
        if (r6 == 0) goto L_0x0240;
    L_0x023e:
        if (r2 != 0) goto L_0x0917;
    L_0x0240:
        r0 = r13.M;
        r13.b(r0);
        r0 = r13.J;
        if (r0 == 0) goto L_0x029c;
    L_0x0249:
        r0 = r13.J;
        r0 = r0.mRunningInfo;
        r0 = r0.c;
        if (r0 <= 0) goto L_0x029c;
    L_0x0251:
        r1 = r13.y;
        r2 = 0;
        r1.setOnClickListener(r2);
        r1 = r13.y;
        r2 = 1;
        r1.setPressed(r2);
        r1 = r13.z;
        r2 = 0;
        r1.setOnClickListener(r2);
        r1 = r13.z;
        r2 = 1;
        r1.setPressed(r2);
        r1 = r13.f;
        r2 = 8;
        r1.setVisibility(r2);
        r1 = r13.e;
        r2 = 0;
        r1.setVisibility(r2);
        r1 = r13.q;
        r2 = 8;
        r1.setVisibility(r2);
        r1 = r13.r;
        r2 = r13.c();
        r2 = r2.getResources();
        r3 = 2131689476; // 0x7f0f0004 float:1.9007968E38 double:1.0531945377E-314;
        r2 = r2.getColor(r3);
        r1.setTextColor(r2);
        r1 = 4;
        if (r0 != r1) goto L_0x0d4a;
    L_0x0294:
        r0 = r13.r;
        r1 = 2131231239; // 0x7f080207 float:1.8078553E38 double:1.0529681385E-314;
        r0.setText(r1);
    L_0x029c:
        r0 = r13.J;
        r13.I = r0;
        r0 = r13.J;
        r0 = r0.mRevision;
        r13.K = r0;
        goto L_0x0015;
    L_0x02a8:
        r0 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        goto L_0x00ba;
    L_0x02ac:
        r0 = r3.mTaskType;
        r6 = com.xunlei.download.DownloadManager.TaskType.BT;
        if (r0 != r6) goto L_0x02cb;
    L_0x02b2:
        r0 = 2130838400; // 0x7f020380 float:1.7281781E38 double:1.0527740503E-314;
        r12 = r1;
        r1 = r0;
        r0 = r12;
    L_0x02b8:
        if (r0 == 0) goto L_0x0347;
    L_0x02ba:
        r1 = 1;
        r13.L = r1;
        r1 = r13.k;
        r2 = android.widget.ImageView.ScaleType.CENTER_CROP;
        r1.setScaleType(r2);
        r1 = r13.k;
        r1.setImageDrawable(r0);
        goto L_0x00e4;
    L_0x02cb:
        r0 = r3.mTaskType;
        r6 = com.xunlei.download.DownloadManager.TaskType.MAGNET;
        if (r0 != r6) goto L_0x02d8;
    L_0x02d1:
        r0 = 2130838407; // 0x7f020387 float:1.7281795E38 double:1.0527740537E-314;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x02b8;
    L_0x02d8:
        if (r4 == 0) goto L_0x0d82;
    L_0x02da:
        r0 = r4.trim();
        r0 = com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.d(r0);
    L_0x02e2:
        r2 = 2130838415; // 0x7f02038f float:1.7281812E38 double:1.0527740577E-314;
        if (r0 == r2) goto L_0x02e9;
    L_0x02e7:
        if (r0 != 0) goto L_0x0d7d;
    L_0x02e9:
        r0 = com.xunlei.downloadprovider.download.tasklist.list.c.g.AnonymousClass_1.b;
        r2 = r5.ordinal();
        r0 = r0[r2];
        switch(r0) {
            case 1: goto L_0x02fb;
            case 2: goto L_0x031a;
            case 3: goto L_0x030c;
            case 4: goto L_0x0313;
            case 5: goto L_0x0330;
            case 6: goto L_0x0337;
            case 7: goto L_0x033f;
            default: goto L_0x02f4;
        };
    L_0x02f4:
        r0 = 2130838415; // 0x7f02038f float:1.7281812E38 double:1.0527740577E-314;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x02b8;
    L_0x02fb:
        r0 = 2130838410; // 0x7f02038a float:1.7281802E38 double:1.052774055E-314;
        if (r4 == 0) goto L_0x0d7d;
    L_0x0300:
        r0 = r4.trim();
        r0 = com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.d(r0);
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x02b8;
    L_0x030c:
        r0 = 2130838414; // 0x7f02038e float:1.728181E38 double:1.052774057E-314;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x02b8;
    L_0x0313:
        r0 = 2130838421; // 0x7f020395 float:1.7281824E38 double:1.0527740607E-314;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x02b8;
    L_0x031a:
        r2 = 2130838396; // 0x7f02037c float:1.7281773E38 double:1.0527740483E-314;
        r0 = r3.mTaskStatus;
        r4 = 8;
        if (r0 != r4) goto L_0x0d79;
    L_0x0323:
        r0 = com.xunlei.downloadprovider.model.protocol.a.a();
        r0 = r0.a(r3);
        r1 = 1;
        r13.L = r1;
        r1 = r2;
        goto L_0x02b8;
    L_0x0330:
        r0 = 2130838405; // 0x7f020385 float:1.7281791E38 double:1.0527740527E-314;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x02b8;
    L_0x0337:
        r0 = 2130838417; // 0x7f020391 float:1.7281816E38 double:1.0527740587E-314;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x02b8;
    L_0x033f:
        r0 = 2130838422; // 0x7f020396 float:1.7281826E38 double:1.052774061E-314;
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x02b8;
    L_0x0347:
        r0 = r13.k;
        r0.setImageResource(r1);
        goto L_0x00e4;
    L_0x034e:
        r0 = r13.s;
        r2 = 2131231241; // 0x7f080209 float:1.8078557E38 double:1.0529681395E-314;
        r0.setText(r2);
        goto L_0x0101;
    L_0x0358:
        r0 = r13.t;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = 0;
        r13.N = r0;
        goto L_0x0136;
    L_0x0364:
        r1 = r13.w;
        r2 = 0;
        r1.setVisibility(r2);
        r1 = r13.w;
        r1 = r1.getResources();
        r2 = 2131231228; // 0x7f0801fc float:1.8078531E38 double:1.052968133E-314;
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r3[r4] = r0;
        r0 = r1.getString(r2, r3);
        r1 = r13.w;
        r1.setText(r0);
        goto L_0x014d;
    L_0x0384:
        r0 = r13.m;
        r1 = 0;
        r0.setVisibility(r1);
        r0 = r2.mTaskStatus;
        r1 = 4;
        if (r0 != r1) goto L_0x03b3;
    L_0x038f:
        r0 = r13.m;
        r1 = 2130838152; // 0x7f020288 float:1.7281278E38 double:1.0527739278E-314;
        r1 = r13.a(r1);
        r0.setProgressDrawable(r1);
    L_0x039b:
        r0 = r2.mFileSize;
        r4 = 0;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x03c0;
    L_0x03a3:
        r0 = r2.mDownloadedSize;
        r4 = 100;
        r0 = r0 * r4;
        r4 = r2.mFileSize;
        r0 = r0 / r4;
    L_0x03ab:
        r0 = (int) r0;
        r1 = r13.m;
        r1.setProgress(r0);
        goto L_0x015c;
    L_0x03b3:
        r0 = r13.m;
        r1 = 2130838151; // 0x7f020287 float:1.7281276E38 double:1.0527739273E-314;
        r1 = r13.a(r1);
        r0.setProgressDrawable(r1);
        goto L_0x039b;
    L_0x03c0:
        r0 = 0;
        goto L_0x03ab;
    L_0x03c3:
        r0 = r2.mDownloadSpeed;
        r0 = com.xunlei.downloadprovider.download.util.a.a(r0);
        r1 = r13.n;
        r1.setText(r0);
        r0 = r2.mHasLixianSpeedup;
        if (r0 != 0) goto L_0x03de;
    L_0x03d2:
        r0 = r2.mHasVipChannelSpeedup;
        if (r0 != 0) goto L_0x03de;
    L_0x03d6:
        r0 = r2.mVipAcceleratedChannelSpeed;
        r4 = 100;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x0470;
    L_0x03de:
        r0 = r13.o;
        r1 = 0;
        r0.setVisibility(r1);
        r0 = com.xunlei.downloadprovider.download.util.DownloadError.b(r2);
        if (r0 == 0) goto L_0x0456;
    L_0x03ea:
        r4 = r2.mVipAcceleratedChannelSpeed;
        r6 = 100;
        r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r1 >= 0) goto L_0x0456;
    L_0x03f2:
        r1 = com.xunlei.downloadprovider.download.util.DownloadError.SpeedupFailureCode.SENSITIVE_RESOURCE_LIMITED;
        if (r0 != r1) goto L_0x0446;
    L_0x03f6:
        r0 = r13.c();
        r0 = r0.getResources();
        r1 = 2131231232; // 0x7f080200 float:1.807854E38 double:1.052968135E-314;
        r0 = r0.getString(r1);
    L_0x0405:
        r1 = new java.lang.StringBuilder;
        r3 = "(";
        r1.<init>(r3);
        r0 = r1.append(r0);
        r1 = ")";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = new android.text.SpannableString;
        r1.<init>(r0);
        r3 = new android.text.style.ForegroundColorSpan;
        r4 = r13.c();
        r4 = r4.getResources();
        r5 = 2131689475; // 0x7f0f0003 float:1.9007966E38 double:1.053194537E-314;
        r4 = r4.getColor(r5);
        r3.<init>(r4);
        r4 = 0;
        r0 = r0.length();
        r5 = 34;
        r1.setSpan(r3, r4, r0, r5);
        r0 = r13.o;
        r0.setText(r1);
        goto L_0x0185;
    L_0x0446:
        r0 = r13.c();
        r0 = r0.getResources();
        r1 = 2131231231; // 0x7f0801ff float:1.8078537E38 double:1.0529681346E-314;
        r0 = r0.getString(r1);
        goto L_0x0405;
    L_0x0456:
        r0 = r13.o;
        r1 = "(+ %s)";
        r3 = 1;
        r3 = new java.lang.Object[r3];
        r4 = 0;
        r6 = r2.mVipAcceleratedChannelSpeed;
        r5 = com.xunlei.downloadprovider.download.util.a.a(r6);
        r3[r4] = r5;
        r1 = java.lang.String.format(r1, r3);
        r0.setText(r1);
        goto L_0x0185;
    L_0x0470:
        r0 = r13.o;
        r1 = 8;
        r0.setVisibility(r1);
        goto L_0x0185;
    L_0x0479:
        r0 = r13.p;
        r1 = 8;
        r0.setVisibility(r1);
        goto L_0x01a2;
    L_0x0482:
        r0 = r13.f;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = r13.e;
        r1 = 0;
        r0.setVisibility(r1);
        r0 = r13.q;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = r2.mTaskStatus;
        r1 = 1;
        if (r0 != r1) goto L_0x04c7;
    L_0x049b:
        r0 = r13.r;
        r0 = r0.getContext();
        r0 = r0.getResources();
        r1 = 2131231240; // 0x7f080208 float:1.8078555E38 double:1.052968139E-314;
        r0 = r0.getString(r1);
        r1 = r13.r;
        r2 = r13.c();
        r2 = r2.getResources();
        r3 = 2131689476; // 0x7f0f0004 float:1.9007968E38 double:1.0531945377E-314;
        r2 = r2.getColor(r3);
        r1.setTextColor(r2);
        r1 = r13.r;
        r1.setText(r0);
        goto L_0x01a2;
    L_0x04c7:
        r0 = r2.mTaskStatus;
        r1 = 4;
        if (r0 != r1) goto L_0x052b;
    L_0x04cc:
        r0 = r13.r;
        r0 = r0.getContext();
        r0 = r0.getResources();
        r1 = 2131231235; // 0x7f080203 float:1.8078545E38 double:1.0529681366E-314;
        r0 = r0.getString(r1);
        r1 = r13.r;
        r3 = r13.c();
        r3 = r3.getResources();
        r4 = 2131689476; // 0x7f0f0004 float:1.9007968E38 double:1.0531945377E-314;
        r3 = r3.getColor(r4);
        r1.setTextColor(r3);
        r1 = r13.r;
        r1.setText(r0);
        r0 = r2.mFailureReason;
        r4 = 1006; // 0x3ee float:1.41E-42 double:4.97E-321;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x01a2;
    L_0x04fe:
        r0 = r13.r;
        r1 = r13.c();
        r1 = r1.getResources();
        r3 = 2131689473; // 0x7f0f0001 float:1.9007962E38 double:1.053194536E-314;
        r1 = r1.getColor(r3);
        r0.setTextColor(r1);
        r0 = r13.q;
        r1 = 0;
        r0.setVisibility(r1);
        r13.a(r2);
        r0 = r13.b(r2);
        if (r0 == 0) goto L_0x01a2;
    L_0x0521:
        r0 = r13.r;
        r1 = "\u4e0b\u8f7d\u5931\u8d25";
        r0.setText(r1);
        goto L_0x01a2;
    L_0x052b:
        r0 = r2.mTaskStatus;
        r1 = 8;
        if (r0 != r1) goto L_0x0677;
    L_0x0531:
        r0 = 1;
        r13.a(r0);
        r0 = r13.r;
        r1 = r13.c();
        r1 = r1.getResources();
        r3 = 2131689476; // 0x7f0f0004 float:1.9007968E38 double:1.0531945377E-314;
        r1 = r1.getColor(r3);
        r0.setTextColor(r1);
        r0 = com.xunlei.downloadprovider.download.tasklist.list.c.g.c.b(r2);
        r1 = com.xunlei.downloadprovider.download.tasklist.list.f.d.a();
        r1 = r1.b();
        r1 = com.xunlei.downloadprovider.download.tasklist.list.f.c.a(r1);
        if (r1 == 0) goto L_0x0566;
    L_0x055b:
        r3 = r1.size();
        r4 = 6;
        if (r3 != r4) goto L_0x0566;
    L_0x0562:
        r3 = 1;
        r1.get(r3);
    L_0x0566:
        r1 = r2.c;
        if (r1 == 0) goto L_0x0588;
    L_0x056a:
        r0 = r13.r;
        r1 = r13.c();
        r1 = r1.getResources();
        r2 = 2131689474; // 0x7f0f0002 float:1.9007964E38 double:1.0531945367E-314;
        r1 = r1.getColor(r2);
        r0.setTextColor(r1);
        r0 = r13.r;
        r1 = 2131231222; // 0x7f0801f6 float:1.8078519E38 double:1.05296813E-314;
        r0.setText(r1);
        goto L_0x01a2;
    L_0x0588:
        r1 = r13.r;
        r3 = r13.c();
        r3 = r3.getResources();
        r4 = 2131689474; // 0x7f0f0002 float:1.9007964E38 double:1.0531945367E-314;
        r3 = r3.getColor(r4);
        r1.setTextColor(r3);
        r1 = com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType.E_VIDEO_CATEGORY;
        if (r0 != r1) goto L_0x065f;
    L_0x05a0:
        r0 = r13.t;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = 0;
        r13.N = r0;
        r0 = r2.mConsumed;
        if (r0 != 0) goto L_0x05e2;
    L_0x05ae:
        r0 = r13.r;
        r1 = 2131231225; // 0x7f0801f9 float:1.8078525E38 double:1.0529681316E-314;
        r0.setText(r1);
    L_0x05b6:
        r0 = r2.k;
        if (r0 <= 0) goto L_0x01a2;
    L_0x05ba:
        r0 = r2.k;
        r1 = r2.j;
        if (r0 != r1) goto L_0x05ff;
    L_0x05c0:
        r0 = r2.j;
        if (r0 <= 0) goto L_0x05ff;
    L_0x05c4:
        r0 = r13.r;
        r1 = r13.c();
        r1 = r1.getResources();
        r2 = 2131689476; // 0x7f0f0004 float:1.9007968E38 double:1.0531945377E-314;
        r1 = r1.getColor(r2);
        r0.setTextColor(r1);
        r0 = r13.r;
        r1 = 2131231227; // 0x7f0801fb float:1.807853E38 double:1.0529681326E-314;
        r0.setText(r1);
        goto L_0x01a2;
    L_0x05e2:
        r0 = r13.r;
        r1 = r13.c();
        r1 = r1.getResources();
        r3 = 2131689476; // 0x7f0f0004 float:1.9007968E38 double:1.0531945377E-314;
        r1 = r1.getColor(r3);
        r0.setTextColor(r1);
        r0 = r13.r;
        r1 = 2131231236; // 0x7f080204 float:1.8078547E38 double:1.052968137E-314;
        r0.setText(r1);
        goto L_0x05b6;
    L_0x05ff:
        r0 = "0%";
        r1 = r2.j;
        if (r1 <= 0) goto L_0x0613;
    L_0x0606:
        r0 = r2.j;
        r0 = r0 * 100;
        r1 = r2.k;
        r0 = r0 / r1;
        r1 = 1;
        if (r0 > r1) goto L_0x0643;
    L_0x0610:
        r0 = "1%";
    L_0x0613:
        r1 = r13.r;
        r2 = r13.c();
        r2 = r2.getResources();
        r3 = 2131689474; // 0x7f0f0002 float:1.9007964E38 double:1.0531945367E-314;
        r2 = r2.getColor(r3);
        r1.setTextColor(r2);
        r1 = r13.r;
        r2 = r13.c();
        r2 = r2.getResources();
        r3 = 2131231226; // 0x7f0801fa float:1.8078527E38 double:1.052968132E-314;
        r4 = 1;
        r4 = new java.lang.Object[r4];
        r5 = 0;
        r4[r5] = r0;
        r0 = r2.getString(r3, r4);
        r1.setText(r0);
        goto L_0x01a2;
    L_0x0643:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = r2.j;
        r1 = r1 * 100;
        r2 = r2.k;
        r1 = r1 / r2;
        r0 = r0.append(r1);
        r1 = "%";
        r0 = r0.append(r1);
        r0 = r0.toString();
        goto L_0x0613;
    L_0x065f:
        r0 = r2.mConsumed;
        if (r0 == 0) goto L_0x066d;
    L_0x0663:
        r0 = r13.r;
        r1 = 2131231223; // 0x7f0801f7 float:1.807852E38 double:1.0529681306E-314;
        r0.setText(r1);
        goto L_0x01a2;
    L_0x066d:
        r0 = r13.r;
        r1 = 2131231236; // 0x7f080204 float:1.8078547E38 double:1.052968137E-314;
        r0.setText(r1);
        goto L_0x01a2;
    L_0x0677:
        r0 = r2.mTaskStatus;
        r1 = 16;
        if (r0 != r1) goto L_0x01a2;
    L_0x067d:
        r0 = r13.r;
        r1 = r13.c();
        r1 = r1.getResources();
        r3 = 2131689473; // 0x7f0f0001 float:1.9007962E38 double:1.053194536E-314;
        r1 = r1.getColor(r3);
        r0.setTextColor(r1);
        r1 = com.xunlei.downloadprovider.download.util.DownloadError.a(r2);
        r0 = r13.c();
        r3 = 2131231233; // 0x7f080201 float:1.8078541E38 double:1.0529681356E-314;
        r0 = r0.getString(r3);
        r0 = java.lang.String.valueOf(r0);
        if (r1 == 0) goto L_0x06b1;
    L_0x06a6:
        r3 = com.xunlei.downloadprovider.download.tasklist.list.c.g.AnonymousClass_1.a;
        r1 = r1.ordinal();
        r1 = r3[r1];
        switch(r1) {
            case 1: goto L_0x07c8;
            case 2: goto L_0x07b7;
            case 3: goto L_0x0773;
            case 4: goto L_0x06b1;
            case 5: goto L_0x07fb;
            case 6: goto L_0x0762;
            case 7: goto L_0x06df;
            case 8: goto L_0x07ea;
            case 9: goto L_0x07d9;
            case 10: goto L_0x06cf;
            case 11: goto L_0x06ef;
            case 12: goto L_0x06ff;
            case 13: goto L_0x070f;
            case 14: goto L_0x071f;
            case 15: goto L_0x072f;
            case 16: goto L_0x0740;
            case 17: goto L_0x0751;
            case 18: goto L_0x0784;
            case 19: goto L_0x0795;
            case 20: goto L_0x07a6;
            case 21: goto L_0x080c;
            case 22: goto L_0x081d;
            case 23: goto L_0x082e;
            case 24: goto L_0x083f;
            default: goto L_0x06b1;
        };
    L_0x06b1:
        r1 = r13.r;
        r1.setText(r0);
        r0 = r13.q;
        r1 = 0;
        r0.setVisibility(r1);
        r0 = r13.b(r2);
        if (r0 == 0) goto L_0x06ca;
    L_0x06c2:
        r0 = r13.r;
        r1 = "\u4e0b\u8f7d\u5931\u8d25";
        r0.setText(r1);
    L_0x06ca:
        r13.a(r2);
        goto L_0x01a2;
    L_0x06cf:
        r0 = r13.c();
        r1 = 2131231210; // 0x7f0801ea float:1.8078495E38 double:1.052968124E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x06df:
        r0 = r13.c();
        r1 = 2131231210; // 0x7f0801ea float:1.8078495E38 double:1.052968124E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x06ef:
        r0 = r13.c();
        r1 = 2131231221; // 0x7f0801f5 float:1.8078517E38 double:1.0529681296E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x06ff:
        r0 = r13.c();
        r1 = 2131231220; // 0x7f0801f4 float:1.8078515E38 double:1.052968129E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x070f:
        r0 = r13.c();
        r1 = 2131231202; // 0x7f0801e2 float:1.8078478E38 double:1.0529681203E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x071f:
        r0 = r13.c();
        r1 = 2131231200; // 0x7f0801e0 float:1.8078474E38 double:1.0529681193E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x072f:
        r0 = r13.c();
        r1 = 2131231203; // 0x7f0801e3 float:1.807848E38 double:1.0529681207E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x0740:
        r0 = r13.c();
        r1 = 2131231204; // 0x7f0801e4 float:1.8078482E38 double:1.052968121E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x0751:
        r0 = r13.c();
        r1 = 2131231201; // 0x7f0801e1 float:1.8078476E38 double:1.05296812E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x0762:
        r0 = r13.c();
        r1 = 2131231211; // 0x7f0801eb float:1.8078497E38 double:1.0529681247E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x0773:
        r0 = r13.c();
        r1 = 2131231207; // 0x7f0801e7 float:1.8078488E38 double:1.0529681227E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x0784:
        r0 = r13.c();
        r1 = 2131231208; // 0x7f0801e8 float:1.807849E38 double:1.052968123E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x0795:
        r0 = r13.c();
        r1 = 2131231222; // 0x7f0801f6 float:1.8078519E38 double:1.05296813E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x07a6:
        r0 = r13.c();
        r1 = 2131231209; // 0x7f0801e9 float:1.8078493E38 double:1.0529681237E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x07b7:
        r0 = r13.c();
        r1 = 2131231213; // 0x7f0801ed float:1.80785E38 double:1.0529681257E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x07c8:
        r0 = r13.c();
        r1 = 2131231214; // 0x7f0801ee float:1.8078503E38 double:1.052968126E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x07d9:
        r0 = r13.c();
        r1 = 2131231215; // 0x7f0801ef float:1.8078505E38 double:1.0529681267E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x07ea:
        r0 = r13.c();
        r1 = 2131231205; // 0x7f0801e5 float:1.8078484E38 double:1.0529681217E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x07fb:
        r0 = r13.c();
        r1 = 2131231206; // 0x7f0801e6 float:1.8078486E38 double:1.052968122E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x080c:
        r0 = r13.c();
        r1 = 2131231219; // 0x7f0801f3 float:1.8078513E38 double:1.0529681287E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x081d:
        r0 = r13.c();
        r1 = 2131231217; // 0x7f0801f1 float:1.8078509E38 double:1.0529681277E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x082e:
        r0 = r13.c();
        r1 = 2131231216; // 0x7f0801f0 float:1.8078507E38 double:1.052968127E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x083f:
        r0 = r13.c();
        r1 = 2131231218; // 0x7f0801f2 float:1.807851E38 double:1.052968128E-314;
        r0 = r0.getString(r1);
        r0 = java.lang.String.valueOf(r0);
        goto L_0x06b1;
    L_0x0850:
        r2 = 0;
        r13.a(r2);
        r2 = r13.g;
        r3 = 8;
        r2.setVisibility(r3);
        r2 = r13.h;
        r3 = 0;
        r2.setVisibility(r3);
        goto L_0x01bc;
    L_0x0863:
        r0 = 1;
        r2 = r13.z;
        r3 = 8;
        r2.setVisibility(r3);
        goto L_0x020b;
    L_0x086d:
        r2 = r1.mTaskStatus;
        r3 = 4;
        if (r2 != r3) goto L_0x087f;
    L_0x0872:
        r2 = r13.y;
        r3 = 0;
        r2.setVisibility(r3);
        r2 = com.xunlei.downloadprovider.download.tasklist.list.c.g.b.a;
        r13.b(r2);
        goto L_0x020b;
    L_0x087f:
        r2 = r1.mTaskStatus;
        r3 = 1;
        if (r2 != r3) goto L_0x0891;
    L_0x0884:
        r2 = r13.y;
        r3 = 0;
        r2.setVisibility(r3);
        r2 = com.xunlei.downloadprovider.download.tasklist.list.c.g.b.b;
        r13.b(r2);
        goto L_0x020b;
    L_0x0891:
        r2 = r1.mTaskStatus;
        r3 = 2;
        if (r2 != r3) goto L_0x08a3;
    L_0x0896:
        r2 = r13.y;
        r3 = 0;
        r2.setVisibility(r3);
        r2 = com.xunlei.downloadprovider.download.tasklist.list.c.g.b.b;
        r13.b(r2);
        goto L_0x020b;
    L_0x08a3:
        r2 = r1.mTaskStatus;
        r3 = 16;
        if (r2 != r3) goto L_0x020b;
    L_0x08a9:
        r2 = r13.y;
        r3 = 0;
        r2.setVisibility(r3);
        r2 = com.xunlei.downloadprovider.download.tasklist.list.c.g.b.c;
        r13.b(r2);
        goto L_0x020b;
    L_0x08b6:
        r0 = r1.mTaskStatus;
        r1 = 8;
        if (r0 != r1) goto L_0x08c5;
    L_0x08bc:
        r0 = com.xunlei.downloadprovider.download.tasklist.list.c.g.a.b;
        r1 = r13.U;
        r13.a(r0, r1);
        goto L_0x0223;
    L_0x08c5:
        r0 = 0;
        r1 = 0;
        r13.a(r0, r1);
        goto L_0x0223;
    L_0x08cc:
        r0 = com.xunlei.downloadprovider.download.tasklist.list.c.g.a.a;
        r2 = r13.c();
        r3 = r1.mLocalFileName;
        r2 = com.xunlei.downloadprovider.a.c.a(r2, r3);
        if (r2 == 0) goto L_0x0906;
    L_0x08da:
        r0 = r1.h;
        r4 = android.os.SystemClock.elapsedRealtime();
        r6 = r1.i;
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r3 == 0) goto L_0x08f2;
    L_0x08e8:
        r6 = r1.i;
        r6 = r4 - r6;
        r8 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r3 < 0) goto L_0x08fe;
    L_0x08f2:
        r0 = r13.c();
        r0 = com.xunlei.downloadprovider.a.c.a(r0, r2);
        r1.h = r0;
        r1.i = r4;
    L_0x08fe:
        r1 = 4;
        if (r0 == r1) goto L_0x0904;
    L_0x0901:
        r1 = 5;
        if (r0 != r1) goto L_0x090d;
    L_0x0904:
        r0 = com.xunlei.downloadprovider.download.tasklist.list.c.g.a.a;
    L_0x0906:
        r1 = r13.T;
        r13.a(r0, r1);
        goto L_0x0223;
    L_0x090d:
        r0 = com.xunlei.downloadprovider.download.tasklist.list.c.g.a.c;
        goto L_0x0906;
    L_0x0910:
        r0 = 0;
        r1 = 0;
        r13.a(r0, r1);
        goto L_0x0223;
    L_0x0917:
        r0 = new java.lang.StringBuilder;
        r1 = "time: ";
        r0.<init>(r1);
        r1 = r2.mFreeTrialTimes;
        r0 = r0.append(r1);
        r1 = " taskId: ";
        r0 = r0.append(r1);
        r4 = r6.getTaskId();
        r0.append(r4);
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r0 = r0.d;
        r4 = r6.getTaskId();
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x099c;
    L_0x0941:
        r0 = 1;
        r1 = r0;
    L_0x0943:
        if (r1 == 0) goto L_0x099f;
    L_0x0945:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r4 = r0.e();
        r8 = r2.mDownloadedSize;
        r0 = (r8 > r4 ? 1 : (r8 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x099f;
    L_0x0953:
        r0 = new java.lang.StringBuilder;
        r1 = "size: ";
        r0.<init>(r1);
        r2 = r2.mDownloadedSize;
        r0 = r0.append(r2);
        r1 = "   before: ";
        r0 = r0.append(r1);
        r0.append(r4);
        r0 = r13.D;
        r1 = 8;
        r0.a(r1);
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r0 = r0.b();
        if (r0 == 0) goto L_0x0983;
    L_0x097c:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r0.f();
    L_0x0983:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r2 = r6.getTaskId();
        r1 = com.xunlei.downloadprovider.download.util.g.c;
        r4 = 0;
        r0.a(r2, r1, r4);
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r2 = -1;
        r0.c(r2);
        goto L_0x0240;
    L_0x099c:
        r0 = 0;
        r1 = r0;
        goto L_0x0943;
    L_0x099f:
        com.xunlei.downloadprovider.member.login.LoginHelper.a();
        r0 = com.xunlei.downloadprovider.member.login.LoginHelper.c();
        if (r0 == 0) goto L_0x09b2;
    L_0x09a8:
        r0 = com.xunlei.downloadprovider.member.login.LoginHelper.a();
        r0 = r0.f();
        if (r0 != 0) goto L_0x0b8c;
    L_0x09b2:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r4 = r6.getTaskId();
        r3 = r0.f;
        if (r3 != 0) goto L_0x0b6c;
    L_0x09be:
        r0 = 1;
    L_0x09bf:
        if (r0 != 0) goto L_0x0b8c;
    L_0x09c1:
        r0 = 1;
    L_0x09c2:
        if (r0 == 0) goto L_0x0240;
    L_0x09c4:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r4 = r6.getTaskId();
        r0 = r0.a(r4);
        if (r0 == 0) goto L_0x0c59;
    L_0x09d2:
        r0 = r13.D;
        r1 = 0;
        r0.a(r1);
        r0 = r13.D;
        r1 = 1;
        r0.b(r1);
        r0 = r13.D;
        r0.c = r6;
        r1 = "";
        r4 = r6.mDownloadRemainTime;
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        r2 = r6.getTaskId();
        r0 = com.xunlei.downloadprovider.service.downloads.task.d.i(r2);
        r2 = 0;
        r8 = r6.mDownloadedSize;
        r10 = 0;
        r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r7 <= 0) goto L_0x0a12;
    L_0x09fc:
        r8 = r6.mDownloadDurationTime;
        r10 = 0;
        r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r7 <= 0) goto L_0x0a12;
    L_0x0a04:
        r2 = r6.mDownloadedSize;
        r2 = (float) r2;
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r2 = r2 * r3;
        r8 = r6.mDownloadDurationTime;
        r3 = (float) r8;
        r2 = r2 / r3;
        r3 = 1148846080; // 0x447a0000 float:1000.0 double:5.676053805E-315;
        r2 = r2 * r3;
        r2 = (long) r2;
    L_0x0a12:
        if (r0 != 0) goto L_0x0b8f;
    L_0x0a14:
        r0 = new com.xunlei.downloadprovider.service.downloads.task.b.c;
        r0.<init>();
        r8 = r6.mDownloadSpeed;
        r0.a = r8;
    L_0x0a1d:
        r8 = r0.a;
        r7 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1));
        if (r7 >= 0) goto L_0x0a2a;
    L_0x0a23:
        r8 = 3;
        r8 = r2 / r8;
        r2 = r2 + r8;
        r0.a = r2;
    L_0x0a2a:
        r2 = r0.a;
        r8 = 0;
        r0 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r0 <= 0) goto L_0x0d76;
    L_0x0a32:
        r8 = r6.mFileSize;
        r10 = r6.mDownloadedSize;
        r8 = r8 - r10;
        r2 = r8 / r2;
        r8 = 0;
        r0 = (r2 > r8 ? 1 : (r2 == r8 ? 0 : -1));
        if (r0 <= 0) goto L_0x0d76;
    L_0x0a3f:
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 > 0) goto L_0x0a47;
    L_0x0a45:
        r2 = 1;
    L_0x0a47:
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 <= 0) goto L_0x0a83;
    L_0x0a4d:
        r0 = 60;
        r0 = r2 / r0;
        r4 = 0;
        r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x0bb4;
    L_0x0a57:
        r4 = 99;
        r4 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r4 <= 0) goto L_0x0b9d;
    L_0x0a5d:
        r0 = com.xunlei.downloadprovider.a.f.c(r2);
        r2 = com.xunlei.downloadprovider.a.f.d(r2);
        r4 = 30;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x0a6e;
    L_0x0a6b:
        r2 = 1;
        r0 = r0 + r2;
    L_0x0a6e:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r2.append(r0);
        r1 = "\u5c0f\u65f6";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = r0;
    L_0x0a83:
        r0 = new java.lang.StringBuilder;
        r2 = " speed : ";
        r0.<init>(r2);
        r2 = r6.mVipAcceleratedChannelSpeed;
        r0 = r0.append(r2);
        r2 = "  ";
        r0 = r0.append(r2);
        r2 = r6.getTaskId();
        r0 = r0.append(r2);
        r2 = " statusCode: ";
        r0 = r0.append(r2);
        r2 = r6.mVipChannelStatus;
        r0 = r0.append(r2);
        r2 = " code: ";
        r0 = r0.append(r2);
        r2 = r6.mVipChannelStatusCode;
        r0.append(r2);
        r2 = com.xunlei.downloadprovider.download.util.n.g(r6);
        r0 = com.xunlei.downloadprovider.download.util.n.a(r6, r2);
        r4 = new java.lang.StringBuilder;
        r5 = "remainSize:  ";
        r4.<init>(r5);
        r4 = r4.append(r2);
        r5 = "   freeTrialOver: ";
        r4 = r4.append(r5);
        r4.append(r0);
        if (r0 == 0) goto L_0x0bce;
    L_0x0ad9:
        r4 = 0;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 > 0) goto L_0x0b1b;
    L_0x0adf:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r2 = r6.mTaskId;
        r4 = r0.f;
        if (r4 == 0) goto L_0x0bcb;
    L_0x0ae9:
        r0 = r0.f;
        r2 = java.lang.Long.valueOf(r2);
        r0 = r0.get(r2);
        r0 = (com.xunlei.downloadprovider.download.util.g.a) r0;
        if (r0 == 0) goto L_0x0bcb;
    L_0x0af7:
        r0 = r0.b;
        r2 = com.xunlei.downloadprovider.download.util.g.c;
        if (r0 != r2) goto L_0x0bcb;
    L_0x0afd:
        r0 = 1;
    L_0x0afe:
        if (r0 != 0) goto L_0x0b1b;
    L_0x0b00:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r2 = r6.mTaskId;
        r4 = com.xunlei.downloadprovider.download.util.g.c;
        r0.a(r2, r4);
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r0.f();
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r2 = -1;
        r0.c(r2);
    L_0x0b1b:
        r0 = r13.D;
        com.xunlei.downloadprovider.download.util.g.a();
        r2 = com.xunlei.downloadprovider.download.util.g.l();
        r0 = r0.g;
        r0.b(r2);
        com.xunlei.downloadprovider.download.util.g.a();
        r0 = com.xunlei.downloadprovider.download.util.g.a(r1);
        r1 = com.xunlei.downloadprovider.download.util.g.a();
        r2 = r6.getTaskId();
        r1 = r1.d(r2);
        if (r1 == 0) goto L_0x0b49;
    L_0x0b3e:
        r1 = com.xunlei.downloadprovider.download.util.g.a();
        r2 = r6.getTaskId();
        r1.e(r2);
    L_0x0b49:
        r1 = com.xunlei.downloadprovider.download.util.n.b(r6);
        if (r1 == 0) goto L_0x0b56;
    L_0x0b4f:
        r1 = r13.D;
        r2 = 8;
        r1.a(r2);
    L_0x0b56:
        r1 = r13.D;
        r1.a(r0);
    L_0x0b5b:
        r0 = r13.D;
        r0 = r0.a();
        if (r0 != 0) goto L_0x0240;
    L_0x0b63:
        r0 = r13.C;
        r1 = 8;
        r0.setVisibility(r1);
        goto L_0x0240;
    L_0x0b6c:
        r0 = r0.f;
        r3 = java.lang.Long.valueOf(r4);
        r0 = r0.get(r3);
        r0 = (com.xunlei.downloadprovider.download.util.g.a) r0;
        if (r0 == 0) goto L_0x0b89;
    L_0x0b7a:
        r3 = r0.d;
        r4 = com.xunlei.downloadprovider.download.util.g.a;
        if (r3 == r4) goto L_0x0b86;
    L_0x0b80:
        r0 = r0.d;
        r3 = com.xunlei.downloadprovider.download.util.g.c;
        if (r0 != r3) goto L_0x0b89;
    L_0x0b86:
        r0 = 1;
        goto L_0x09bf;
    L_0x0b89:
        r0 = 0;
        goto L_0x09bf;
    L_0x0b8c:
        r0 = 0;
        goto L_0x09c2;
    L_0x0b8f:
        r8 = r6.mDownloadSpeed;
        r10 = r0.a;
        r7 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r7 <= 0) goto L_0x0a1d;
    L_0x0b97:
        r8 = r6.mDownloadSpeed;
        r0.a = r8;
        goto L_0x0a1d;
    L_0x0b9d:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r2.append(r0);
        r1 = "\u5206\u949f";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = r0;
        goto L_0x0a83;
    L_0x0bb4:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r0 = r0.append(r2);
        r1 = "\u79d2";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r1 = r0;
        goto L_0x0a83;
    L_0x0bcb:
        r0 = 0;
        goto L_0x0afe;
    L_0x0bce:
        r0 = r6.mVipChannelStatus;
        r4 = 16;
        if (r0 == r4) goto L_0x0bdc;
    L_0x0bd4:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r0 = r0.e;
        if (r0 == 0) goto L_0x0bff;
    L_0x0bdc:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r0 = r0.e;
        if (r0 != 0) goto L_0x0beb;
    L_0x0be4:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r1 = 1;
        r0.e = r1;
    L_0x0beb:
        r0 = r13.D;
        r1 = 2;
        r0.b(r1);
        r0 = r13.D;
        com.xunlei.downloadprovider.download.util.g.a();
        r1 = com.xunlei.downloadprovider.download.util.g.j();
        r0.a(r1);
        goto L_0x0b5b;
    L_0x0bff:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r4 = r6.getTaskId();
        r0 = r0.f;
        r4 = java.lang.Long.valueOf(r4);
        r0 = r0.get(r4);
        r0 = (com.xunlei.downloadprovider.download.util.g.a) r0;
        if (r0 == 0) goto L_0x0c27;
    L_0x0c15:
        r0 = r0.d;
        r4 = com.xunlei.downloadprovider.download.util.g.a;
        if (r0 < r4) goto L_0x0c27;
    L_0x0c1b:
        r0 = 1;
    L_0x0c1c:
        if (r0 == 0) goto L_0x0c29;
    L_0x0c1e:
        r0 = r13.D;
        r1 = 8;
        r0.a(r1);
        goto L_0x0b5b;
    L_0x0c27:
        r0 = 0;
        goto L_0x0c1c;
    L_0x0c29:
        r0 = r6.mTaskStatus;
        r4 = 4;
        if (r0 != r4) goto L_0x0c3c;
    L_0x0c2e:
        r0 = r13.D;
        com.xunlei.downloadprovider.download.util.g.a();
        r1 = com.xunlei.downloadprovider.download.util.g.k();
        r0.a(r1);
        goto L_0x0b5b;
    L_0x0c3c:
        r4 = 10485760; // 0xa00000 float:1.469368E-38 double:5.180654E-317;
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 >= 0) goto L_0x0c4c;
    L_0x0c43:
        com.xunlei.downloadprovider.download.util.g.a();
        r0 = com.xunlei.downloadprovider.download.util.g.c(r1);
        goto L_0x0b56;
    L_0x0c4c:
        com.xunlei.downloadprovider.download.util.g.a();
        r0 = com.xunlei.downloadprovider.d.a.a(r2);
        r0 = com.xunlei.downloadprovider.download.util.g.b(r0);
        goto L_0x0b56;
    L_0x0c59:
        r0 = r2.mFreeTrialTimes;
        if (r0 > 0) goto L_0x0c5f;
    L_0x0c5d:
        if (r1 == 0) goto L_0x0b5b;
    L_0x0c5f:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r0 = r0.d;
        r2 = com.xunlei.downloadprovider.download.util.n.b(r6);
        if (r2 != 0) goto L_0x0c7b;
    L_0x0c6b:
        r2 = r6.mTaskStatus;
        r3 = 1;
        if (r2 == r3) goto L_0x0c7b;
    L_0x0c70:
        r2 = r6.mTaskStatus;
        r3 = 16;
        if (r2 == r3) goto L_0x0c7b;
    L_0x0c76:
        r2 = r6.mTaskStatus;
        r3 = 1;
        if (r2 != r3) goto L_0x0c92;
    L_0x0c7b:
        r0 = r6.mTaskId;
        r2 = com.xunlei.downloadprovider.download.util.g.a();
        r2 = r2.d;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 != 0) goto L_0x0b5b;
    L_0x0c87:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r2 = -1;
        r0.c(r2);
        goto L_0x0b5b;
    L_0x0c92:
        r2 = r6.mFileSize;
        r4 = 209715200; // 0xc800000 float:1.9721523E-31 double:1.036130757E-315;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 >= 0) goto L_0x0ca4;
    L_0x0c9b:
        r2 = r6.getTaskId();
        a(r2, r0);
        goto L_0x0b5b;
    L_0x0ca4:
        r2 = com.xunlei.downloadprovider.download.util.g.a();
        r4 = r6.getTaskId();
        r2 = r2.d(r4);
        if (r2 == 0) goto L_0x0b5b;
    L_0x0cb2:
        r2 = r6.mFileSize;
        r4 = 0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x0cd4;
    L_0x0cba:
        r2 = r6.mDownloadedSize;
        r2 = (float) r2;
        r3 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r2 = r2 * r3;
        r4 = r6.mFileSize;
        r3 = (float) r4;
        r2 = r2 / r3;
        r3 = 1060320051; // 0x3f333333 float:0.7 double:5.23867711E-315;
        r2 = (r2 > r3 ? 1 : (r2 == r3 ? 0 : -1));
        if (r2 <= 0) goto L_0x0cd4;
    L_0x0ccb:
        r2 = r6.getTaskId();
        a(r2, r0);
        goto L_0x0b5b;
    L_0x0cd4:
        r2 = r6.mFreeTrialTimes;
        r3 = new java.lang.StringBuilder;
        r4 = "time: ";
        r3.<init>(r4);
        r3 = r3.append(r2);
        r4 = " curentId: ";
        r3 = r3.append(r4);
        r3.append(r0);
        r4 = r6.getTaskId();
        r3 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1));
        if (r3 == 0) goto L_0x0cfc;
    L_0x0cf4:
        r4 = -1;
        r3 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r3 != 0) goto L_0x0b5b;
    L_0x0cfa:
        if (r2 <= 0) goto L_0x0b5b;
    L_0x0cfc:
        r3 = r13.D;
        r4 = 0;
        r3.a(r4);
        r4 = -1;
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x0d18;
    L_0x0d08:
        r0 = com.xunlei.downloadprovider.download.util.g.a();
        r4 = r6.getTaskId();
        r0.c(r4);
        r0 = r6.mDownloadSpeed;
        com.xunlei.downloadprovider.download.report.a.a(r0);
    L_0x0d18:
        r0 = r13.D;
        r1 = 0;
        r0.b(r1);
        com.xunlei.downloadprovider.download.util.g.a();
        r0 = com.xunlei.downloadprovider.download.util.g.i();
        r1 = r13.D;
        r1.c = r6;
        r1 = r13.D;
        r1.a(r0);
        r0 = new java.lang.StringBuilder;
        r1 = "the trial task: ";
        r0.<init>(r1);
        r4 = r6.getTaskId();
        r0 = r0.append(r4);
        r1 = " time: ";
        r0 = r0.append(r1);
        r0.append(r2);
        goto L_0x0b5b;
    L_0x0d4a:
        r1 = 2;
        if (r0 == r1) goto L_0x0d50;
    L_0x0d4d:
        r1 = 1;
        if (r0 != r1) goto L_0x0d5a;
    L_0x0d50:
        r0 = r13.r;
        r1 = 2131231238; // 0x7f080206 float:1.8078551E38 double:1.052968138E-314;
        r0.setText(r1);
        goto L_0x029c;
    L_0x0d5a:
        r1 = 17;
        if (r0 != r1) goto L_0x029c;
    L_0x0d5e:
        r0 = r13.r;
        r1 = 2131231237; // 0x7f080205 float:1.807855E38 double:1.0529681375E-314;
        r0.setText(r1);
        r0 = r13.h;
        r1 = 8;
        r0.setVisibility(r1);
        r0 = r13.g;
        r1 = 8;
        r0.setVisibility(r1);
        goto L_0x029c;
    L_0x0d76:
        r2 = r4;
        goto L_0x0a3f;
    L_0x0d79:
        r0 = r1;
        r1 = r2;
        goto L_0x02b8;
    L_0x0d7d:
        r12 = r1;
        r1 = r0;
        r0 = r12;
        goto L_0x02b8;
    L_0x0d82:
        r0 = r2;
        goto L_0x02e2;
        */
    }

    private void d() {
        int layoutPosition = getLayoutPosition();
        if (layoutPosition != this.d.getItemCount() - 1 || layoutPosition == 0) {
            this.itemView.setPadding(this.itemView.getPaddingLeft(), this.itemView.getPaddingTop(), this.itemView.getPaddingRight(), 0);
        } else {
            this.itemView.setPadding(this.itemView.getPaddingLeft(), this.itemView.getPaddingTop(), this.itemView.getPaddingRight(), this.itemView.getResources().getDimensionPixelSize(2131362322));
        }
    }

    private void e() {
        if (this.M != null) {
            a(this.M);
        }
    }

    private static String a(String str) {
        String[] strArr = new String[]{"thunder:"};
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        for (int i = 0; i <= 0; i++) {
            String str2 = strArr[0];
            if (str.startsWith(str2)) {
                return str.substring(str2.length());
            }
        }
        return str;
    }

    private void a(a aVar) {
        if (this.Q == null) {
            this.Q = new j(this.O, "sp_exception_close");
        }
        if (this.Q.b(new StringBuilder(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID).append(aVar.getTaskId()).toString(), 0) == 0) {
            String b = com.xunlei.downloadprovider.download.util.a.b(aVar, this.O);
            CharSequence replaceAll = b.replaceAll("[^\\u4e00-\\u9fa5]", com.umeng.a.d);
            FailureCode a = DownloadError.a(aVar);
            CharSequence charSequence = com.umeng.a.d;
            if (a != null) {
                switch (AnonymousClass_1.a[a.ordinal()]) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        charSequence = String.valueOf(c().getString(2131231192));
                        this.E.e = false;
                        this.E.g = false;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        charSequence = String.valueOf(c().getString(2131231194));
                        this.E.e = false;
                        this.E.g = false;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        charSequence = String.valueOf(c().getString(2131231194));
                        this.E.e = false;
                        this.E.g = false;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        charSequence = String.valueOf(c().getString(2131231194));
                        this.E.e = false;
                        this.E.g = false;
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        charSequence = String.valueOf(c().getString(2131231194));
                        this.E.e = false;
                        this.E.g = false;
                        break;
                    case R.styleable.Toolbar_contentInsetEnd:
                        charSequence = String.valueOf(c().getString(2131231196));
                        this.E.e = false;
                        this.E.g = false;
                        break;
                    case R.styleable.Toolbar_contentInsetLeft:
                        charSequence = String.valueOf(c().getString(2131231198));
                        this.E.e = false;
                        this.E.g = false;
                        break;
                    case XZBDevice.Wait:
                        charSequence = String.valueOf(c().getString(2131231198));
                        this.E.e = false;
                        this.E.g = false;
                        break;
                    case XZBDevice.Pause:
                        this.E.e = true;
                        this.E.g = false;
                        break;
                    case XZBDevice.Stop:
                        this.E.g = true;
                        this.E.e = false;
                        break;
                    default:
                        charSequence = String.valueOf(c().getString(2131231197));
                        this.E.e = false;
                        this.E.g = false;
                        break;
                }
            }
            if (!TextUtils.isEmpty(charSequence) && !TextUtils.isEmpty(replaceAll)) {
                this.D.a(XZBDevice.Wait);
                this.E.a(0);
                this.E.d = b;
                a aVar2 = this.E;
                if (!(aVar2.b == null || TextUtils.isEmpty(charSequence))) {
                    aVar2.b.a(charSequence);
                }
                this.E.f = true;
                this.E.c = this.J;
                aVar.n = true;
                aVar.b = b;
                if (this.R == null) {
                    this.R = new j(this.O, "sp_exception_show");
                }
                boolean z = aVar != null ? aVar.getTaskId() == this.R.b(new StringBuilder(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID).append(aVar.getTaskId()).toString(), -1) : false;
                if (!z) {
                    com.xunlei.downloadprovider.download.report.a.b("dl_fail");
                    if (this.R == null) {
                        this.R = new j(this.O, "sp_exception_show");
                    }
                    if (!(this.R == null || aVar == null)) {
                        this.R.a(new StringBuilder(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID).append(aVar.getTaskId()).toString(), aVar.getTaskId());
                    }
                }
                this.r.setText("\u4e0b\u8f7d\u5931\u8d25");
            }
        }
    }

    private void b(com.xunlei.downloadprovider.download.tasklist.list.b.e eVar) {
        int i = 0;
        if (k()) {
            this.g.setVisibility(XZBDevice.Wait);
            this.h.setVisibility(XZBDevice.Wait);
            this.G.setVisibility(0);
            if (eVar.a()) {
                this.H.setImageResource(2130837655);
                return;
            } else {
                this.H.setImageResource(2130837656);
                return;
            }
        }
        int i2;
        if (this.J == null || this.J.mTaskStatus != 8) {
            int i3 = 0;
        } else {
            Object obj = 1;
        }
        View view = this.g;
        if (i3 != 0) {
            i2 = 0;
        } else {
            i2 = 8;
        }
        view.setVisibility(i2);
        View view2 = this.h;
        if (i3 != 0) {
            i = 8;
        }
        view2.setVisibility(i);
        this.G.setVisibility(XZBDevice.Wait);
    }

    private boolean b(a aVar) {
        if (this.Q == null) {
            this.Q = new j(this.O, "sp_exception_close");
        }
        return aVar != null && aVar.getTaskId() == this.Q.b(new StringBuilder(DownloadBtFileExplorerActivity.EXTRA_KEY_NAME_TASK_ID).append(aVar.getTaskId()).toString(), -1);
    }

    private void a(boolean z) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (z) {
            layoutParams.rightMargin = com.xunlei.downloadprovider.a.g.a(this.O, 67.0f);
        } else {
            layoutParams.rightMargin = 0;
        }
        layoutParams.addRule(XZBDevice.DOWNLOAD_LIST_FAILED, 2131756336);
        this.x.setLayoutParams(layoutParams);
    }

    private static void a(long j, long j2) {
        if (j == j2) {
            com.xunlei.downloadprovider.download.util.g.a().c(-1);
            com.xunlei.downloadprovider.download.util.g.a().a(j, com.xunlei.downloadprovider.download.util.g.a, false);
        }
    }

    private void b(int i) {
        Drawable a;
        switch (AnonymousClass_1.c[i - 1]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.y.setText(2131231188);
                this.y.setOnClickListener(this.Y);
                this.y.setTextAppearance(this.y.getContext(), 2131427650);
                this.y.setBackgroundResource(2130839327);
                a = a(2130838130);
                if (a != null) {
                    a.setBounds(0, 0, a.getMinimumWidth(), a.getMinimumHeight());
                }
                this.y.setCompoundDrawables(a, null, null, null);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.y.setText(2131231184);
                this.y.setOnClickListener(this.V);
                this.y.setTextAppearance(this.y.getContext(), 2131427652);
                this.y.setBackgroundResource(2130839328);
                a = a(2130838128);
                if (a != null) {
                    a.setBounds(0, 0, a.getMinimumWidth(), a.getMinimumHeight());
                }
                this.y.setCompoundDrawables(a, null, null, null);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.y.setText(2131231187);
                this.y.setOnClickListener(this.X);
                this.y.setTextAppearance(this.y.getContext(), 2131427652);
                this.y.setBackgroundResource(2130839328);
                a = a(2130838131);
                if (a != null) {
                    a.setBounds(0, 0, a.getMinimumWidth(), a.getMinimumHeight());
                }
                this.y.setCompoundDrawables(a, null, null, null);
            default:
                break;
        }
    }

    private void a(int i, OnClickListener onClickListener) {
        if (i == 0) {
            this.A.setVisibility(XZBDevice.Wait);
            this.A.setOnClickListener(null);
            this.B.setVisibility(XZBDevice.Wait);
            this.B.setOnClickListener(null);
            return;
        }
        switch (AnonymousClass_1.d[i - 1]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.A.setVisibility(0);
                this.B.setVisibility(XZBDevice.Wait);
                this.A.setText(2131231183);
                this.A.setOnClickListener(onClickListener);
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                this.A.setVisibility(XZBDevice.Wait);
                this.B.setVisibility(0);
                this.B.setOnClickListener(onClickListener);
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                this.A.setVisibility(0);
                this.B.setVisibility(XZBDevice.Wait);
                this.A.setText(2131231182);
                this.A.setOnClickListener(onClickListener);
            default:
                break;
        }
    }

    @SuppressLint({"DefaultLocale"})
    private void c(a aVar) {
        boolean z = true;
        CharSequence a = com.xunlei.downloadprovider.download.util.a.a(aVar, c());
        if (aVar.mSeen == 0 && aVar.mTaskStatus == 8) {
            boolean z2 = true;
        } else {
            int i = 0;
        }
        if (aVar.c || (aVar.mConsumed && aVar.mTaskType == TaskType.MAGNET)) {
            i = 0;
        }
        ZHTextView zHTextView = this.l;
        if (aVar.c) {
            z = false;
        }
        zHTextView.setEnabled(z);
        if (i != 0) {
            this.v.setVisibility(0);
        } else {
            this.v.setVisibility(XZBDevice.Wait);
        }
        if (i != 0) {
            this.l.setTextIndentPadding((float) Math.max(this.v.getWidth(), com.xunlei.downloadprovider.a.g.a(c(), TitleBar.SHAREBTN_RIGHT_MARGIN)));
        } else {
            this.l.setTextIndentPadding(AutoScrollHelper.RELATIVE_UNSPECIFIED);
        }
        this.l.setText(a);
        this.l.requestLayout();
    }

    static /* synthetic */ void a(g gVar) {
        com.xunlei.downloadprovider.download.a.a h = gVar.h();
        if (h != null && gVar.J != null) {
            h.a(gVar.J, gVar.a());
            gVar.b();
        }
    }

    static /* synthetic */ void b(g gVar) {
        com.xunlei.downloadprovider.download.a.a h = gVar.h();
        if (h != null && gVar.J != null) {
            h.a(gVar.J, gVar.a());
            gVar.b();
        }
    }

    static /* synthetic */ void c(g gVar) {
        com.xunlei.downloadprovider.download.a.a h = gVar.h();
        if (h != null && gVar.J != null) {
            if (gVar.J.mTaskStatus == 8) {
                h.a(gVar.J, gVar.a());
                gVar.b();
                return;
            }
            h.a(gVar.J);
        }
    }

    static /* synthetic */ void a(g gVar, int i) {
        com.xunlei.downloadprovider.download.a.a h = gVar.h();
        if (h != null && gVar.J != null) {
            switch (i) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    com.xunlei.downloadprovider.download.a.a.b(gVar.J);
                    gVar.e();
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    h.d(gVar.J);
                    gVar.e();
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    h.c(gVar.J);
                    gVar.e();
                case XZBDevice.DOWNLOAD_LIST_ALL:
                    TaskRunningInfo taskRunningInfo = gVar.J;
                    n.a().c(taskRunningInfo, false);
                    com.xunlei.downloadprovider.download.util.g.a().a(taskRunningInfo);
                    gVar.e();
                case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                    h.a(gVar.J.mTaskId, "v_an_shoulei_hyzx_kt_ico");
                default:
                    break;
            }
        }
    }

    static /* synthetic */ void g(g gVar) {
        gVar.M.a(!gVar.M.a());
        gVar.b(gVar.M);
        gVar.d.b();
    }

    static /* synthetic */ void i(g gVar) {
        v vVar = new v(gVar.c(), gVar.J, gVar.h(), gVar.a());
        vVar.b = new l(gVar, vVar);
        vVar.show();
    }
}
