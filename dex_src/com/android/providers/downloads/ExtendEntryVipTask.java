package com.android.providers.downloads;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.umeng.message.MsgConstant;
import com.xunlei.androidvip.XLAndroidVipManager;
import com.xunlei.androidvip.parameter.AndroidVipGetTaskId;
import com.xunlei.androidvip.parameter.AndroidVipHighSpeedBillingReqParam;
import com.xunlei.androidvip.parameter.AndroidVipHighSpeedBillingResponse;
import com.xunlei.androidvip.parameter.AndroidVipHighSpeedTaskReqParam;
import com.xunlei.androidvip.parameter.AndroidVipHighSpeedTaskResponse;
import com.xunlei.androidvip.parameter.AndroidVipOfflineBtCommitReqParam;
import com.xunlei.androidvip.parameter.AndroidVipOfflineBtCommitResponse;
import com.xunlei.androidvip.parameter.AndroidVipOfflineCommitReqParam;
import com.xunlei.androidvip.parameter.AndroidVipOfflineCommitResponse;
import com.xunlei.androidvip.parameter.AndroidVipSubOfflineBtCommitTaskInfo;
import com.xunlei.androidvip.parameter.AndroidVipSubOfflineCommitTaskInfo;
import com.xunlei.androidvip.parameter.VipTryCommitResult;
import com.xunlei.androidvip.parameter.VipTryParam;
import com.xunlei.androidvip.parameter.VipTryQueryResult;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.Property;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.download.proguard.ab;
import com.xunlei.download.proguard.am;
import com.xunlei.download.proguard.d;
import com.xunlei.download.proguard.k.b;
import com.xunlei.download.proguard.n;
import com.xunlei.download.proguard.w;
import com.xunlei.download.proguard.z;
import com.xunlei.downloadlib.XLDownloadManager;
import com.xunlei.downloadlib.parameter.PeerResourceParam;
import com.xunlei.downloadlib.parameter.ServerResourceParam;
import com.xunlei.downloadlib.parameter.TorrentInfo;
import com.xunlei.downloadlib.parameter.XLTaskInfo;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.HashMap;

public class ExtendEntryVipTask implements b {
    static final int a = 1000;
    static final int b = 0;
    static final int c = 1;
    static final int d = 2;
    static final String e = "HighSpeedTaskRequetErrorCode";
    static final String f = "HighSpeedTaskBillingErrorCode";
    static final String g = "OfflineTaskRequetErrorCode";
    static final String h = "OfflineBtTaskRequetErrorCode";
    static final int i = 20002;
    static final int j = 40002;
    static final int k = 40004;
    static final int l = 40003;
    static final int m = 0;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;
        static final /* synthetic */ int[] b;
        static final /* synthetic */ int[] c;

        static {
            c = new int[com.xunlei.download.proguard.n.a.values().length];
            try {
                c[com.xunlei.download.proguard.n.a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                c[com.xunlei.download.proguard.n.a.b.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                c[com.xunlei.download.proguard.n.a.c.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            b = new int[f.values().length];
            try {
                b[f.b.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[f.a.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[f.c.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[f.f.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[f.d.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                b[f.e.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            a = new int[e.values().length];
            try {
                a[e.a.ordinal()] = 1;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[e.b.ordinal()] = 2;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[e.c.ordinal()] = 3;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    static class a extends com.xunlei.download.proguard.w.b {
        static final String a = "XlDownloadTask2";
        static final int b = 8;
        HashMap<String, a> c;
        com.xunlei.download.proguard.d d;
        com.xunlei.download.proguard.w.a e;
        Context f;
        TorrentInfo g;
        c h;

        static abstract class a {
            protected f a;
            protected int b;
            private long c;
            private long d;

            abstract f a(XLTaskInfo xLTaskInfo, int i);

            abstract void g();

            protected a() {
                e();
            }

            protected final e a() {
                if (SystemClock.elapsedRealtime() - this.c <= (this.d - 1) * 10000) {
                    return e.b;
                }
                if (this.d > 8) {
                    am.b(a, "Retry, failed more than 3 times");
                    if (16 != DownloadManager.translateStatus(this.b)) {
                        this.b = 491;
                    }
                    am.b(a, new StringBuilder("Retry, failed more than 3 times, mTaskStatus = ").append(this.b).toString());
                    return e.c;
                }
                am.b(a, new StringBuilder("Retry, mRetryStep=").append(this.d - 1).toString());
                this.d *= 2;
                return e.a;
            }

            protected final long b() {
                return this.c;
            }

            protected final boolean c() {
                boolean z = this.d > 8;
                if (z) {
                    am.b(a, "Retry, out of step");
                }
                return z;
            }

            final boolean d() {
                return this.a == f.f;
            }

            final void e() {
                this.a = f.a;
                this.c = SystemClock.elapsedRealtime();
                this.d = 2;
                this.b = 190;
            }

            final int f() {
                return this.b;
            }

            public void a(long j, int i) {
            }
        }

        static class b extends a {
            b() {
            }

            f a(XLTaskInfo xLTaskInfo, int i) {
                return null;
            }

            void g() {
            }
        }

        static class c {
            long a;
            long b;
            long c;
            AndroidVipHighSpeedTaskResponse d;
            VipTryQueryResult e;
            VipTryCommitResult f;
            int g;
            int h;
            int i;
            int j;
            int k;
            int l;
            b m;
            b n;
            b o;
            TorrentInfo p;
            Context q;
            long r;
            long s;
            long t;
            private int u;
            private int v;
            private int w;

            c(Context context, long j) {
                this.a = 0;
                this.b = 0;
                this.c = 0;
                this.d = null;
                this.e = null;
                this.f = null;
                this.g = 190;
                this.h = 190;
                this.i = 190;
                this.j = 190;
                this.k = 0;
                this.l = 0;
                this.m = null;
                this.n = null;
                this.o = null;
                this.p = null;
                this.s = -1;
                this.t = -1;
                this.u = 0;
                this.v = 0;
                this.w = 0;
                this.q = context.getApplicationContext();
                this.r = j;
            }

            void a(String str) {
                am.b(a, new StringBuilder("id=").append(this.r).append(", ").append(str).toString());
            }

            private AndroidVipHighSpeedTaskResponse a(XLTaskInfo xLTaskInfo, com.xunlei.download.proguard.w.a aVar) {
                if (this.d != null) {
                    return this.d;
                }
                a(new StringBuilder("getResourceInfo mFindResourceStatus=").append(this.g).toString());
                if (this.g != 190) {
                    return null;
                }
                if (this.a == 0) {
                    if (this.m == null) {
                        this.m = new b();
                    }
                    b(xLTaskInfo, aVar);
                    return null;
                }
                c();
                return this.d;
            }

            private VipTryQueryResult b(XLTaskInfo xLTaskInfo, com.xunlei.download.proguard.d dVar, com.xunlei.download.proguard.w.a aVar) {
                if (this.e != null) {
                    return this.e;
                }
                a(new StringBuilder("getCheckTrialInfo mCheckTrialStatus=").append(this.h).toString());
                if (this.h != 190) {
                    return null;
                }
                if (a(xLTaskInfo, aVar) == null) {
                    if (this.g == 190) {
                        return null;
                    }
                    n.a().a(this.r, (int) b);
                    this.h = this.g;
                    return null;
                } else if (this.b == 0) {
                    if (this.n == null) {
                        this.n = new b();
                    }
                    d(xLTaskInfo, dVar, aVar);
                    return null;
                } else {
                    d();
                    return this.e;
                }
            }

            private VipTryCommitResult c(XLTaskInfo xLTaskInfo, com.xunlei.download.proguard.d dVar, com.xunlei.download.proguard.w.a aVar) {
                if (this.f != null) {
                    return this.f;
                }
                a(new StringBuilder("getEnterTrialInfo mEnterTrialStatus=").append(this.i).toString());
                if (this.i != 190) {
                    return null;
                }
                if (b(xLTaskInfo, dVar, aVar) == null) {
                    if (this.h == 190) {
                        return null;
                    }
                    this.i = this.h;
                    n.a().b(this.r, b);
                    return null;
                } else if (this.c == 0) {
                    if (this.o == null) {
                        this.o = new b();
                    }
                    a(xLTaskInfo, dVar, aVar);
                    return null;
                } else {
                    a();
                    return this.f;
                }
            }

            private int a(XLTaskInfo xLTaskInfo, com.xunlei.download.proguard.d dVar, com.xunlei.download.proguard.w.a aVar, int i) {
                VipTryCommitResult c = c(xLTaskInfo, dVar, aVar);
                if (c == null) {
                    this.j = this.i;
                    return this.i;
                } else if (this.s == xLTaskInfo.mTaskId) {
                    return this.j;
                } else {
                    this.s = xLTaskInfo.mTaskId;
                    String b = ab.b(this.q);
                    if (TextUtils.isEmpty(b)) {
                        b = "1.0.1";
                    }
                    int i2 = 0;
                    for (int i3 = 0; i3 < this.d.mPeerCount; i3++) {
                        PeerResourceParam peerResourceParam = new PeerResourceParam();
                        peerResourceParam.mUserId = 0;
                        peerResourceParam.mJmpKey = com.umeng.a.d;
                        peerResourceParam.mVipCdnAuth = c.mVerifyInfo + "&tm=android&ver=" + b;
                        peerResourceParam.mUdpPort = this.d.mPeerRes[i3].mUdpPort;
                        peerResourceParam.mTcpPort = this.d.mPeerRes[i3].mTcpPort;
                        peerResourceParam.mInternalIp = (int) this.d.mPeerRes[i3].mIp;
                        peerResourceParam.mPeerId = this.d.mPeerRes[i3].mPeerId;
                        peerResourceParam.mResLevel = this.d.mPeerRes[i3].mResourceLevel;
                        peerResourceParam.mResPriority = this.d.mPeerRes[i3].mResourceUseLevel;
                        peerResourceParam.mCapabilityFlag = this.d.mPeerRes[i3].mCapability;
                        peerResourceParam.mResType = this.d.mPeerRes[i3].mCdnType;
                        int btAddPeerResource = XLDownloadManager.getInstance(this.q).btAddPeerResource(xLTaskInfo.mTaskId, i, peerResourceParam);
                        i2 |= btAddPeerResource == 9000 ? c : 0;
                        a(new StringBuilder("btAddPeerResource ret = ").append(btAddPeerResource).append(",peerId=").append(peerResourceParam.mPeerId).toString());
                    }
                    if (i2 != 0) {
                        this.j = 200;
                    } else {
                        this.j = 491;
                    }
                    return this.j;
                }
            }

            private void a(long j, int i) {
                a(new StringBuilder("HispeedTrial remove resource, id=").append(j).append(", idx=").append(i).toString());
                XLDownloadManager.getInstance(this.q).btRemoveAddedResource(j, i, XZBDevice.DOWNLOAD_LIST_FAILED);
                this.j = 190;
            }

            private void b(XLTaskInfo xLTaskInfo, com.xunlei.download.proguard.w.a aVar) {
                AndroidVipGetTaskId androidVipGetTaskId = new AndroidVipGetTaskId();
                AndroidVipHighSpeedTaskReqParam androidVipHighSpeedTaskReqParam = new AndroidVipHighSpeedTaskReqParam();
                androidVipHighSpeedTaskReqParam.mUserId = 0;
                androidVipHighSpeedTaskReqParam.mCid = xLTaskInfo.mCid;
                androidVipHighSpeedTaskReqParam.mGcid = xLTaskInfo.mGcid;
                androidVipHighSpeedTaskReqParam.mFileSize = this.t;
                int AndroidVipCreateEnterHighSpeedTask = XLAndroidVipManager.getInstance(this.q).AndroidVipCreateEnterHighSpeedTask(androidVipGetTaskId, androidVipHighSpeedTaskReqParam);
                if (AndroidVipCreateEnterHighSpeedTask == 0) {
                    XLAndroidVipManager.getInstance(this.q).vipSetTaskRetryFlag(androidVipGetTaskId.getTaskId(), this.u);
                    if (this.u == 0) {
                        this.u = 1;
                    }
                    this.a = androidVipGetTaskId.getTaskId();
                    a(new StringBuilder("createFindResourceTask mFindResourceId= ").append(this.a).toString());
                    return;
                }
                this.g = 491;
                a(new StringBuilder("createFindResourceTask ret= ").append(AndroidVipCreateEnterHighSpeedTask).toString());
            }

            private void c() {
                AndroidVipHighSpeedTaskResponse androidVipHighSpeedTaskResponse = new AndroidVipHighSpeedTaskResponse();
                int AndroidVipGetHighSpeedTaskResp = XLAndroidVipManager.getInstance().AndroidVipGetHighSpeedTaskResp(this.a, androidVipHighSpeedTaskResponse);
                if (AndroidVipGetHighSpeedTaskResp == 0 && androidVipHighSpeedTaskResponse.mResult == 0) {
                    if (androidVipHighSpeedTaskResponse.mPeerCount <= 0) {
                        this.g = 500;
                        n.a().a(this.r, false);
                        a(",queryFindResourceResult, have no peer count, entry highspeed error");
                    } else {
                        this.g = 200;
                        this.d = androidVipHighSpeedTaskResponse;
                        a(",queryFindResourceResult, success");
                        n.a().a(this.r, true);
                    }
                    e();
                } else if (AndroidVipGetHighSpeedTaskResp == 7002) {
                    a(",queryFindResourceResult, 7002");
                    switch (AnonymousClass_1.a[this.m.a().ordinal()]) {
                        case c:
                            e();
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            n.a().a(this.r, false);
                            this.g = 491;
                            e();
                        default:
                            break;
                    }
                } else {
                    a(new StringBuilder("queryFindResourceResult, error Resp.mResult=").append(androidVipHighSpeedTaskResponse.mResult).append(", ret = ").append(AndroidVipGetHighSpeedTaskResp).toString());
                    this.g = a.b(androidVipHighSpeedTaskResponse.mResult);
                    n.a().a(this.r, false);
                    e();
                }
            }

            private void d(XLTaskInfo xLTaskInfo, com.xunlei.download.proguard.d dVar, com.xunlei.download.proguard.w.a aVar) {
                VipTryParam vipTryParam = new VipTryParam();
                vipTryParam.mCurrentSpeed = xLTaskInfo.mDownloadSpeed;
                vipTryParam.mBandwidth = -1;
                vipTryParam.mGcid = xLTaskInfo.mGcid;
                vipTryParam.mCid = xLTaskInfo.mCid;
                vipTryParam.mFileSize = this.t;
                vipTryParam.mFileName = com.umeng.a.d;
                if (!TextUtils.isEmpty(aVar.b)) {
                    vipTryParam.mFileName = aVar.b.substring(aVar.b.lastIndexOf(File.separator) + 1);
                }
                if (dVar.U == TaskType.BT) {
                    TorrentInfo a = a(dVar);
                    if (a == null) {
                        this.h = 491;
                        a("createCheckTrialTask, not find info hash ");
                        return;
                    }
                    vipTryParam.mResId = a.mInfoHash;
                    vipTryParam.mUrl = com.umeng.a.d;
                    vipTryParam.mResType = 1;
                    vipTryParam.mFileIndex = this.k;
                } else {
                    vipTryParam.mResId = com.umeng.a.d;
                    vipTryParam.mUrl = dVar.d;
                    vipTryParam.mResType = 0;
                    vipTryParam.mFileIndex = 0;
                }
                vipTryParam.mTrialKey = com.umeng.a.d;
                AndroidVipGetTaskId androidVipGetTaskId = new AndroidVipGetTaskId();
                int vipCreateHighSpeedTryTask = XLAndroidVipManager.getInstance(this.q).vipCreateHighSpeedTryTask(vipTryParam, androidVipGetTaskId);
                if (vipCreateHighSpeedTryTask == 0) {
                    XLAndroidVipManager.getInstance(this.q).vipSetTaskRetryFlag(androidVipGetTaskId.getTaskId(), this.v);
                    if (this.v == 0) {
                        this.v = 1;
                    }
                    this.b = androidVipGetTaskId.getTaskId();
                    a(new StringBuilder("createCheckTrialTask mCheckTrialId= ").append(this.b).toString());
                    return;
                }
                this.h = 491;
                a(new StringBuilder("createCheckTrialTask err ,ret= ").append(vipCreateHighSpeedTryTask).toString());
            }

            private void d() {
                VipTryQueryResult vipTryQueryResult = new VipTryQueryResult();
                int vipGetHighSpeedTryResult = XLAndroidVipManager.getInstance(this.q).vipGetHighSpeedTryResult(this.b, vipTryQueryResult);
                if (vipGetHighSpeedTryResult == 0 && vipTryQueryResult.mResult == 0) {
                    if (vipTryQueryResult.mTrialRemain > 0) {
                        this.e = vipTryQueryResult;
                        this.h = 200;
                        n.a().a(this.r, vipTryQueryResult.mTrialRemain);
                    } else {
                        this.h = 491;
                        n.a().a(this.r, (int) b);
                    }
                    a(new StringBuilder("queryCheckTrialResult, mHasTrial=").append(vipTryQueryResult.mHasTrial).append(", mTrialKey=").append(vipTryQueryResult.mTrialKey).append(", mTrialRemain=").append(vipTryQueryResult.mTrialRemain).toString());
                    f();
                } else if (vipGetHighSpeedTryResult == 7002) {
                    a("queryCheckTrialResult,7002");
                    switch (AnonymousClass_1.a[this.n.a().ordinal()]) {
                        case c:
                            f();
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            f();
                            this.h = 491;
                            n.a().a(this.r, (int) b);
                        default:
                            break;
                    }
                } else {
                    this.h = a.b(vipTryQueryResult.mResult);
                    n.a().a(this.r, (int) b);
                    f();
                    a(new StringBuilder("queryCheckTrialResult, err Resp.mResult = ").append(vipTryQueryResult.mResult).append(", ret=").append(vipGetHighSpeedTryResult).toString());
                }
            }

            void a(XLTaskInfo xLTaskInfo, com.xunlei.download.proguard.d dVar, com.xunlei.download.proguard.w.a aVar) {
                AndroidVipGetTaskId androidVipGetTaskId = new AndroidVipGetTaskId();
                VipTryParam vipTryParam = new VipTryParam();
                vipTryParam.mCurrentSpeed = xLTaskInfo.mDownloadSpeed;
                vipTryParam.mBandwidth = -1;
                vipTryParam.mGcid = xLTaskInfo.mGcid;
                vipTryParam.mCid = xLTaskInfo.mCid;
                vipTryParam.mFileSize = this.t;
                vipTryParam.mFileName = com.umeng.a.d;
                if (!TextUtils.isEmpty(aVar.b)) {
                    vipTryParam.mFileName = aVar.b.substring(aVar.b.lastIndexOf(File.separator) + 1);
                }
                if (dVar.U == TaskType.BT) {
                    TorrentInfo a = a(dVar);
                    if (a == null) {
                        this.i = 491;
                        a("createEnterTrialTask err, not find info hash ");
                        return;
                    }
                    vipTryParam.mResId = a.mInfoHash;
                    vipTryParam.mUrl = com.umeng.a.d;
                    vipTryParam.mResType = 1;
                    vipTryParam.mFileIndex = this.k;
                } else {
                    vipTryParam.mResId = com.umeng.a.d;
                    vipTryParam.mUrl = dVar.d;
                    vipTryParam.mResType = 0;
                    vipTryParam.mFileIndex = 0;
                }
                vipTryParam.mTrialKey = this.e.mTrialKey;
                if (dVar.U != TaskType.BT) {
                    XLDownloadManager.getInstance(this.q).setTaskGsState(xLTaskInfo.mTaskId, b, d);
                } else {
                    XLDownloadManager.getInstance(this.q).setTaskGsState(xLTaskInfo.mTaskId, this.k, d);
                }
                int vipCreateHighSpeedTryCommitTask = XLAndroidVipManager.getInstance(this.q).vipCreateHighSpeedTryCommitTask(vipTryParam, androidVipGetTaskId);
                if (vipCreateHighSpeedTryCommitTask == 0) {
                    XLAndroidVipManager.getInstance(this.q).vipSetTaskRetryFlag(androidVipGetTaskId.getTaskId(), this.w);
                    if (this.w == 0) {
                        this.w = 1;
                    }
                    this.c = androidVipGetTaskId.getTaskId();
                    a(new StringBuilder("createEnterTrialTask mEnterTrialId= ").append(this.c).toString());
                    return;
                }
                this.i = 491;
                a(new StringBuilder("createEnterTrialTask err, ret= ").append(vipCreateHighSpeedTryCommitTask).toString());
            }

            void a() {
                VipTryCommitResult vipTryCommitResult = new VipTryCommitResult();
                int vipGetHighSpeedTryCommitResult = XLAndroidVipManager.getInstance(this.q).vipGetHighSpeedTryCommitResult(this.c, vipTryCommitResult);
                if (vipGetHighSpeedTryCommitResult == 0 && vipTryCommitResult.mResult == 0) {
                    this.f = vipTryCommitResult;
                    this.i = 200;
                    n.a().b(this.r, vipTryCommitResult.mTrialDuration);
                    this.l = ((int) (SystemClock.elapsedRealtime() / 1000)) + vipTryCommitResult.mTrialDuration;
                    n.a().b(this.r, this.l);
                    g();
                    a(new StringBuilder("queryEnterTrialResult success, mTrialDuration = ").append(vipTryCommitResult.mTrialDuration).append(", mVerifyInfo = ").append(vipTryCommitResult.mVerifyInfo).toString());
                } else if (vipGetHighSpeedTryCommitResult == 7002) {
                    a("queryEnterTrialResult 7002");
                    switch (AnonymousClass_1.a[this.o.a().ordinal()]) {
                        case c:
                            g();
                        case XZBDevice.DOWNLOAD_LIST_FAILED:
                            g();
                            n.a().b(this.r, b);
                            this.i = 491;
                        default:
                            break;
                    }
                } else {
                    this.i = a.b(vipTryCommitResult.mResult);
                    n.a().b(this.r, b);
                    g();
                    a(new StringBuilder("queryEnterTrialResult Resp.mResult=").append(vipTryCommitResult.mResult).append(", ret=").append(vipGetHighSpeedTryCommitResult).toString());
                }
            }

            TorrentInfo a(com.xunlei.download.proguard.d dVar) {
                if (this.p == null) {
                    this.p = new TorrentInfo();
                    String path = Uri.parse(dVar.d).getPath();
                    if (!new File(path).exists()) {
                        path = z.a(dVar.g, dVar.x);
                    }
                    int torrentInfo = XLDownloadManager.getInstance(this.q).getTorrentInfo(path, this.p);
                    if (torrentInfo != 9000) {
                        this.p = null;
                        am.b(a, new StringBuilder("getTorrentInfo ret=").append(torrentInfo).toString());
                        return null;
                    }
                }
                return this.p;
            }

            private final void e() {
                if (this.a != 0) {
                    a("stopFindResource");
                    XLAndroidVipManager.getInstance(this.q).AndroidVipDestroyHighSpeedTask(this.a);
                    this.a = 0;
                }
            }

            private final void f() {
                if (this.b != 0) {
                    a("stopCheckTrial");
                    XLAndroidVipManager.getInstance(this.q).vipDestoryHighSpeedTryTask(this.b);
                    this.b = 0;
                }
            }

            private final void g() {
                if (this.c != 0) {
                    a("stopEnterTrial");
                    XLAndroidVipManager.getInstance(this.q).vipDestoryHighSpeedTryCommitTask(this.c);
                    this.c = 0;
                    this.p = null;
                }
            }

            void b() {
                e();
                f();
                g();
                this.j = 190;
            }

            int a(XLTaskInfo xLTaskInfo) {
                if (this.j != 200 || ((int) (SystemClock.elapsedRealtime() / 1000)) <= this.l) {
                    return this.j;
                }
                a(xLTaskInfo.mTaskId, this.k);
                a("addResource timeout");
                this.j = 501;
                return this.j;
            }
        }

        class d extends a {
            long c;
            private long e;
            private boolean f;
            private boolean g;
            private boolean h;
            private int i;
            private int j;
            private long k;

            d(boolean z) {
                this.c = -1;
                this.h = false;
                this.i = 0;
                this.j = 1;
                this.k = 0;
                this.e = 0;
                this.f = z;
                this.g = false;
            }

            public void g() {
                if (this.e != 0) {
                    if (this.f) {
                        XLAndroidVipManager.getInstance(a.this.f).AndroidVipDestroyOfflineBtCommitReq(this.e);
                    } else {
                        XLAndroidVipManager.getInstance(a.this.f).AndroidVipDestroyOfflineCommitReq(this.e);
                    }
                    a.this.a(new StringBuilder("LixianChannelTask stopChannel id=").append(this.e).toString());
                    this.e = 0;
                }
            }

            public f a(XLTaskInfo xLTaskInfo, int i) {
                f fVar = this.a;
                switch (AnonymousClass_1.b[this.a.ordinal()]) {
                    case c:
                        if (this.j > 4) {
                            g();
                            this.a = f.f;
                            a.this.a("enterChannel retry for pending or running stop for timeout");
                        } else if (this.k == 0) {
                            a.this.a(new StringBuilder("enterChannel retry for pending or running after ").append(this.j).append(" minutes").toString());
                            g();
                            this.k = SystemClock.elapsedRealtime() + ((long) (this.j * 30000));
                        } else if (SystemClock.elapsedRealtime() > this.k) {
                            e();
                            this.h = true;
                            this.k = 0;
                            this.j *= 2;
                            a.this.a("enterChannel retry for pending or running begin");
                        }
                        break;
                    case d:
                        g();
                        Object a = a.this.a(xLTaskInfo);
                        Object b = a.this.b(xLTaskInfo);
                        if (TextUtils.isEmpty(a) || TextUtils.isEmpty(b)) {
                            if (SystemClock.elapsedRealtime() - b() < 1000) {
                                a.this.a("wait for cid or gcid");
                                return this.a;
                            }
                            this.g = false;
                        }
                        this.e = a(i, xLTaskInfo, a, b);
                        if (this.e > 0) {
                            this.a = f.c;
                            a.this.a(new StringBuilder("LixianChannelTask enterChannel mLixianTaskId=").append(this.e).toString());
                        } else {
                            this.a = f.f;
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        switch (AnonymousClass_1.a[a().ordinal()]) {
                            case c:
                                this.h = false;
                                this.a = f.a;
                                a(xLTaskInfo, i);
                                break;
                            case d:
                                this.a = b(xLTaskInfo.mTaskId, i);
                                break;
                            default:
                                g();
                                this.a = f.f;
                                break;
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        g();
                        break;
                    default:
                        g();
                        this.a = f.f;
                        break;
                }
                a.this.a(new StringBuilder("LixianChannelTask enterChannel oldState = ").append(fVar).append(", newState = ").append(this.a).toString());
                return this.a;
            }

            private long a(int i, XLTaskInfo xLTaskInfo, String str, String str2) {
                int AndroidVipOfflineCommitReq;
                AndroidVipGetTaskId androidVipGetTaskId = new AndroidVipGetTaskId();
                String property = DownloadManager.getInstanceFor(a.this.f).getProperty(Property.PROP_USER_ID, com.umeng.a.d);
                String property2 = DownloadManager.getInstanceFor(a.this.f).getProperty(Property.PROP_JUMP_KEY, com.umeng.a.d);
                String str3;
                if (a.this.U != TaskType.BT) {
                    AndroidVipOfflineCommitReqParam androidVipOfflineCommitReqParam = new AndroidVipOfflineCommitReqParam();
                    if (a.this.U != TaskType.CID) {
                        if (str == null) {
                            str = com.umeng.a.d;
                        }
                        androidVipOfflineCommitReqParam.mCid = str;
                        if (str2 == null) {
                            str2 = com.umeng.a.d;
                        }
                        androidVipOfflineCommitReqParam.mGcid = str2;
                        androidVipOfflineCommitReqParam.mUrl = a.this;
                    } else if (TextUtils.isEmpty(str)) {
                        return 0;
                    } else {
                        if (TextUtils.isEmpty(str2)) {
                            return 0;
                        }
                        androidVipOfflineCommitReqParam.mCid = str;
                        androidVipOfflineCommitReqParam.mGcid = str2;
                        androidVipOfflineCommitReqParam.mUrl = com.umeng.a.d;
                    }
                    androidVipOfflineCommitReqParam.mAutoCharge = (byte) 0;
                    androidVipOfflineCommitReqParam.mUserId = Long.valueOf(property).longValue();
                    androidVipOfflineCommitReqParam.mKey = property2;
                    androidVipOfflineCommitReqParam.mRefUrl = a.this.u == null ? com.umeng.a.d : a.this.u;
                    androidVipOfflineCommitReqParam.mCookie = a.this.s == null ? com.umeng.a.d : a.this.s;
                    str3 = com.umeng.a.d;
                    if (!TextUtils.isEmpty(a.this.e.b)) {
                        str3 = a.this.e.b.substring(a.this.e.b.lastIndexOf(File.separator) + 1);
                    }
                    androidVipOfflineCommitReqParam.mTaskName = str3;
                    androidVipOfflineCommitReqParam.mFileSize = this.c;
                    androidVipOfflineCommitReqParam.mFileType = 0;
                    androidVipOfflineCommitReqParam.mVipLevel = (byte) 0;
                    AndroidVipOfflineCommitReq = XLAndroidVipManager.getInstance(a.this.f).AndroidVipOfflineCommitReq(androidVipGetTaskId, androidVipOfflineCommitReqParam);
                    a.this.a(new StringBuilder("AndroidVipOfflineCommitReq, ret = ").append(AndroidVipOfflineCommitReq).append(", mCid= ").append(androidVipOfflineCommitReqParam.mCid).append(", taskParam.mGcid=").append(androidVipOfflineCommitReqParam.mGcid).toString());
                } else {
                    if (a.this.g == null) {
                        a.this.g = new TorrentInfo();
                        str3 = Uri.parse(a.this).getPath();
                        if (!new File(str3).exists()) {
                            str3 = z.a(a.this.g, a.this.x);
                        }
                        AndroidVipOfflineCommitReq = XLDownloadManager.getInstance(a.this.f).getTorrentInfo(str3, a.this.g);
                        if (AndroidVipOfflineCommitReq != 9000) {
                            a.this.g = null;
                            am.b(a, new StringBuilder("getTorrentInfo ret=").append(AndroidVipOfflineCommitReq).toString());
                            return 0;
                        }
                    }
                    AndroidVipOfflineBtCommitReqParam androidVipOfflineBtCommitReqParam = new AndroidVipOfflineBtCommitReqParam();
                    androidVipOfflineBtCommitReqParam.mAutoCharge = (byte) 0;
                    androidVipOfflineBtCommitReqParam.mUserId = Long.valueOf(property).longValue();
                    androidVipOfflineBtCommitReqParam.mKey = property2;
                    androidVipOfflineBtCommitReqParam.mInfoHash = a.this.g.mInfoHash;
                    androidVipOfflineBtCommitReqParam.mRefUrl = a.this.u == null ? com.umeng.a.d : a.this.u;
                    str3 = a.this.g.mSubFileInfo[i].mFileName;
                    if (TextUtils.isEmpty(a.this.e.b)) {
                        str3 = a.this.g.mMultiFileBaseFolder;
                    }
                    androidVipOfflineBtCommitReqParam.mBtTitle = str3;
                    androidVipOfflineBtCommitReqParam.mVipLevel = (byte) 0;
                    androidVipOfflineBtCommitReqParam.mFilePath = com.umeng.a.d;
                    androidVipOfflineBtCommitReqParam.mFileListNum = 1;
                    androidVipOfflineBtCommitReqParam.mFileList = new int[1];
                    androidVipOfflineBtCommitReqParam.mFileList[0] = a.this.g.mSubFileInfo[i].mRealIndex;
                    AndroidVipOfflineCommitReq = XLAndroidVipManager.getInstance(a.this.f).AndroidVipOfflineBtCommitReq(androidVipGetTaskId, androidVipOfflineBtCommitReqParam);
                }
                if (AndroidVipOfflineCommitReq == 0) {
                    if (a.this.U != TaskType.BT) {
                        XLDownloadManager.getInstance(a.this.f).setTaskLxState(xLTaskInfo.mTaskId, b, c);
                    } else if (a.this.g.mSubFileInfo != null) {
                        XLDownloadManager.getInstance(a.this.f).setTaskLxState(xLTaskInfo.mTaskId, i, c);
                    }
                    a.this.g = null;
                    XLAndroidVipManager.getInstance(a.this.f).vipSetTaskRetryFlag(androidVipGetTaskId.getTaskId(), this.i);
                    if (this.i == 0) {
                        this.i = 1;
                    }
                    return androidVipGetTaskId.getTaskId();
                }
                this.b = 491;
                a.this.a(new StringBuilder("AndroidVipOfflineCommitReq,error ret=").append(AndroidVipOfflineCommitReq).toString());
                a.this.g = null;
                return 0;
            }

            f a(int i) {
                f fVar = f.f;
                switch (i) {
                    case b:
                    case c:
                        this.b = 192;
                        return f.b;
                    case d:
                        this.b = 200;
                        return f.f;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        this.b = a.b((int) l);
                        return f.f;
                    default:
                        return fVar;
                }
            }

            private f b(long j, int i) {
                int AndroidVipGetOfflineCommitResp;
                int i2;
                String str = com.umeng.a.d;
                f a;
                if (a.this.U != TaskType.BT) {
                    AndroidVipOfflineCommitResponse androidVipOfflineCommitResponse = new AndroidVipOfflineCommitResponse();
                    AndroidVipGetOfflineCommitResp = XLAndroidVipManager.getInstance(a.this.f).AndroidVipGetOfflineCommitResp(this.e, androidVipOfflineCommitResponse);
                    if (AndroidVipGetOfflineCommitResp == 7002) {
                        if (!this.h) {
                            XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, g, AndroidVipGetOfflineCommitResp);
                        }
                        return f.c;
                    }
                    if (AndroidVipGetOfflineCommitResp != 0 || androidVipOfflineCommitResponse.mResult != 0) {
                        i2 = androidVipOfflineCommitResponse.mResult;
                        str = androidVipOfflineCommitResponse.mMessage;
                    } else if (androidVipOfflineCommitResponse.mTaskinfo == null || androidVipOfflineCommitResponse.mTaskinfo.length <= 0) {
                        i2 = j;
                    } else {
                        AndroidVipSubOfflineCommitTaskInfo androidVipSubOfflineCommitTaskInfo = androidVipOfflineCommitResponse.mTaskinfo[0];
                        a.this.e.x = (long) androidVipSubOfflineCommitTaskInfo.mProgress;
                        a = a(androidVipSubOfflineCommitTaskInfo.mDownloadStatus);
                        if (this.b == 200 && !TextUtils.isEmpty(androidVipSubOfflineCommitTaskInfo.mLixianUrl)) {
                            XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, g, (int) b);
                            if (!a(a(androidVipSubOfflineCommitTaskInfo), j, i)) {
                                this.b = 491;
                            }
                        } else if (this.b == 192) {
                            XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, g, (int) k);
                        } else {
                            XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, g, (int) l);
                        }
                        a.this.a(new StringBuilder("queryLixian, AndroidVipSubOfflineCommitTaskInfo, progress = ").append(androidVipSubOfflineCommitTaskInfo.mProgress).append(", mDownloadStatus=").append(androidVipSubOfflineCommitTaskInfo.mDownloadStatus).toString());
                        return a;
                    }
                    XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, g, i2);
                } else {
                    AndroidVipOfflineBtCommitResponse androidVipOfflineBtCommitResponse = new AndroidVipOfflineBtCommitResponse();
                    AndroidVipGetOfflineCommitResp = XLAndroidVipManager.getInstance(a.this.f).AndroidVipGetOfflineBtCommitResp(this.e, androidVipOfflineBtCommitResponse);
                    if (AndroidVipGetOfflineCommitResp == 7002) {
                        XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, h, AndroidVipGetOfflineCommitResp);
                        return f.c;
                    }
                    if (AndroidVipGetOfflineCommitResp != 0 || androidVipOfflineBtCommitResponse.mResult != 0) {
                        i2 = androidVipOfflineBtCommitResponse.mResult;
                        str = androidVipOfflineBtCommitResponse.mMessage;
                    } else if (androidVipOfflineBtCommitResponse.mTaskinfo == null || androidVipOfflineBtCommitResponse.mTaskinfo.length <= 0) {
                        i2 = j;
                    } else {
                        AndroidVipSubOfflineBtCommitTaskInfo androidVipSubOfflineBtCommitTaskInfo = androidVipOfflineBtCommitResponse.mTaskinfo[0];
                        a.this.e.x = (long) androidVipSubOfflineBtCommitTaskInfo.mProgress;
                        a = a(androidVipSubOfflineBtCommitTaskInfo.mDownloadStatus);
                        if (this.b == 200 && !TextUtils.isEmpty(androidVipSubOfflineBtCommitTaskInfo.mLixianUrl)) {
                            XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, h, (int) b);
                            if (a(a(androidVipSubOfflineBtCommitTaskInfo), j, i)) {
                                return a;
                            }
                            this.b = 491;
                            a.this.a(new StringBuilder("queryLixian, addLixianResource Failed, xlTaskId = ").append(j).toString());
                            return a;
                        } else if (this.b == 192) {
                            XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, h, (int) k);
                            return a;
                        } else {
                            XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, g, (int) l);
                            return a;
                        }
                    }
                    XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, h, i2);
                }
                a.this.a(new StringBuilder("queryLixian, result = ").append(i2).append(", Message = ").append(str).append(", ret = ").append(AndroidVipGetOfflineCommitResp).toString());
                this.b = a.b(i2);
                return f.f;
            }

            private ServerResourceParam a(AndroidVipSubOfflineCommitTaskInfo androidVipSubOfflineCommitTaskInfo) {
                ServerResourceParam serverResourceParam = new ServerResourceParam();
                serverResourceParam.mCookie = androidVipSubOfflineCommitTaskInfo.mCookie;
                serverResourceParam.mUrl = androidVipSubOfflineCommitTaskInfo.mLixianUrl;
                serverResourceParam.mRefUrl = androidVipSubOfflineCommitTaskInfo.mRefUrl;
                return serverResourceParam;
            }

            private ServerResourceParam a(AndroidVipSubOfflineBtCommitTaskInfo androidVipSubOfflineBtCommitTaskInfo) {
                ServerResourceParam serverResourceParam = new ServerResourceParam();
                serverResourceParam.mCookie = androidVipSubOfflineBtCommitTaskInfo.mCookie;
                serverResourceParam.mUrl = androidVipSubOfflineBtCommitTaskInfo.mLixianUrl;
                serverResourceParam.mRefUrl = androidVipSubOfflineBtCommitTaskInfo.mRefUrl;
                return serverResourceParam;
            }

            private boolean a(ServerResourceParam serverResourceParam, long j, int i) {
                int addServerResource;
                String b = ab.b(a.this.f);
                if (TextUtils.isEmpty(b)) {
                    b = "1.0.1";
                }
                serverResourceParam.mUrl += "&tm=android&ver=" + b;
                if (a.this.U != TaskType.BT) {
                    addServerResource = XLDownloadManager.getInstance(a.this.f).addServerResource(j, serverResourceParam);
                } else {
                    addServerResource = XLDownloadManager.getInstance(a.this.f).btAddServerResource(j, i, serverResourceParam);
                }
                if (addServerResource == 9000) {
                    return true;
                }
                a.this.a(new StringBuilder("queryLixian, addLixianResource Failed, xlTaskId = ").append(j).append(", ret = ").append(addServerResource).toString());
                return false;
            }

            public void a(long j, int i) {
                a.this.a(new StringBuilder("lixian remove resource, id=").append(j).append(", idx=").append(i).toString());
                XLDownloadManager.getInstance(a.this.f).btRemoveAddedResource(j, i, XZBDevice.DOWNLOAD_LIST_ALL);
            }
        }

        enum e {
            OK,
            PENDING,
            TIME_OUT;

            static {
                a = new e("OK", 0);
                b = new e("PENDING", 1);
                c = new e("TIME_OUT", 2);
                d = new e[]{a, b, c};
            }
        }

        enum f {
            COMMIT,
            COMMIT_RUNNING,
            QUERY,
            COMMIT_BILLING,
            QUERY_BILLING,
            STOP;

            static {
                a = new f("COMMIT", 0);
                b = new f("COMMIT_RUNNING", 1);
                c = new f("QUERY", 2);
                d = new f("COMMIT_BILLING", 3);
                e = new f("QUERY_BILLING", 4);
                f = new f("STOP", 5);
                g = new f[]{a, b, c, d, e, f};
            }
        }

        class g extends a {
            long c;
            private long e;
            private long f;
            private int g;
            private int h;

            g() {
                this.c = -1;
                this.g = 0;
                this.h = 0;
                this.e = 0;
                this.f = 0;
            }

            public f a(XLTaskInfo xLTaskInfo, int i) {
                f fVar = this.a;
                switch (AnonymousClass_1.b[this.a.ordinal()]) {
                    case d:
                        g();
                        this.e = b(xLTaskInfo, i);
                        if (this.e > 0) {
                            this.a = f.c;
                            a.this.a(new StringBuilder("VipChannelTask enterChannel mVipChannelTaskId=").append(this.e).toString());
                        } else {
                            this.a = f.f;
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        switch (AnonymousClass_1.a[a().ordinal()]) {
                            case c:
                                this.a = f.a;
                                a(xLTaskInfo, i);
                                break;
                            case d:
                                this.a = c(xLTaskInfo.mTaskId, i);
                                if (this.a == f.d) {
                                    a(xLTaskInfo, i);
                                }
                                break;
                            default:
                                g();
                                this.a = f.f;
                                break;
                        }
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        g();
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        h();
                        this.f = a(a.this.a(xLTaskInfo), a.this.b(xLTaskInfo), this.c);
                        if (this.f > 0) {
                            e();
                            this.a = f.e;
                            a.this.a(new StringBuilder("VipChannelTask enterChannel mBillingTaskId=").append(this.f).toString());
                        } else {
                            this.a = f.f;
                        }
                        break;
                    case R.styleable.Toolbar_contentInsetEnd:
                        switch (AnonymousClass_1.a[a().ordinal()]) {
                            case c:
                                this.a = f.d;
                                a(xLTaskInfo, i);
                                break;
                            case d:
                                this.a = b(xLTaskInfo.mTaskId, i);
                                break;
                            default:
                                g();
                                this.a = f.f;
                                break;
                        }
                        break;
                }
                a.this.a(new StringBuilder("VipChannelTask enterChannel oldState = ").append(fVar).append(", newState = ").append(this.a).toString());
                return this.a;
            }

            public void g() {
                if (this.e != 0) {
                    XLAndroidVipManager.getInstance(a.this.f).AndroidVipDestroyHighSpeedTask(this.e);
                    this.e = 0;
                    a.this.a(new StringBuilder("VipChannelTask stopChannel id=").append(this.e).toString());
                }
                h();
            }

            private void h() {
                if (this.f != 0) {
                    XLAndroidVipManager.getInstance(a.this.f).AndroidVipDestroyHighSpeedBillingTask(this.f);
                    this.f = 0;
                    a.this.a(new StringBuilder("VipChannelTask stopBillingTask id=").append(this.f).toString());
                }
            }

            private long b(XLTaskInfo xLTaskInfo, int i) {
                String property = DownloadManager.getInstanceFor(a.this.f).getProperty(Property.PROP_USER_ID, com.umeng.a.d);
                AndroidVipGetTaskId androidVipGetTaskId = new AndroidVipGetTaskId();
                AndroidVipHighSpeedTaskReqParam androidVipHighSpeedTaskReqParam = new AndroidVipHighSpeedTaskReqParam();
                androidVipHighSpeedTaskReqParam.mUserId = Long.valueOf(property).longValue();
                androidVipHighSpeedTaskReqParam.mCid = xLTaskInfo.mCid;
                androidVipHighSpeedTaskReqParam.mGcid = xLTaskInfo.mGcid;
                androidVipHighSpeedTaskReqParam.mFileSize = this.c;
                int AndroidVipCreateEnterHighSpeedTask = XLAndroidVipManager.getInstance(a.this.f).AndroidVipCreateEnterHighSpeedTask(androidVipGetTaskId, androidVipHighSpeedTaskReqParam);
                if (AndroidVipCreateEnterHighSpeedTask == 0) {
                    if (a.this.U != TaskType.BT) {
                        XLDownloadManager.getInstance(a.this.f).setTaskGsState(xLTaskInfo.mTaskId, b, c);
                    } else {
                        XLDownloadManager.getInstance(a.this.f).setTaskGsState(xLTaskInfo.mTaskId, i, c);
                    }
                    this.b = 190;
                    XLAndroidVipManager.getInstance(a.this.f).vipSetTaskRetryFlag(androidVipGetTaskId.getTaskId(), this.g);
                    if (this.g == 0) {
                        this.g = 1;
                    }
                    return androidVipGetTaskId.getTaskId();
                }
                this.b = 491;
                a.this.a(new StringBuilder("createVipChannel ret= ").append(AndroidVipCreateEnterHighSpeedTask).toString());
                return 0;
            }

            private f c(long j, int i) {
                AndroidVipHighSpeedTaskResponse androidVipHighSpeedTaskResponse = new AndroidVipHighSpeedTaskResponse();
                int AndroidVipGetHighSpeedTaskResp = XLAndroidVipManager.getInstance(a.this.f).AndroidVipGetHighSpeedTaskResp(this.e, androidVipHighSpeedTaskResponse);
                if (AndroidVipGetHighSpeedTaskResp == 0 && androidVipHighSpeedTaskResponse.mResult == 0) {
                    if (androidVipHighSpeedTaskResponse.mPeerCount <= 0) {
                        XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, e, (int) i);
                        this.b = 500;
                        a.this.a("have no peer count, entry highspeed error");
                        return f.f;
                    }
                    XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, e, (int) b);
                    return f.d;
                } else if (AndroidVipGetHighSpeedTaskResp == 7002) {
                    XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, e, AndroidVipGetHighSpeedTaskResp);
                    return f.c;
                } else {
                    XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, e, androidVipHighSpeedTaskResponse.mResult);
                    this.b = a.b(androidVipHighSpeedTaskResponse.mResult);
                    a.this.a(new StringBuilder("queryVipChannel, error Resp.mResult=").append(androidVipHighSpeedTaskResponse.mResult).append(", ret = ").append(AndroidVipGetHighSpeedTaskResp).toString());
                    return f.f;
                }
            }

            long a(String str, String str2, long j) {
                String property = DownloadManager.getInstanceFor(a.this.f).getProperty(Property.PROP_USER_ID, com.umeng.a.d);
                String property2 = DownloadManager.getInstanceFor(a.this.f).getProperty(Property.PROP_JUMP_KEY, com.umeng.a.d);
                AndroidVipGetTaskId androidVipGetTaskId = new AndroidVipGetTaskId();
                AndroidVipHighSpeedBillingReqParam androidVipHighSpeedBillingReqParam = new AndroidVipHighSpeedBillingReqParam();
                androidVipHighSpeedBillingReqParam.mUserId = Long.valueOf(property).longValue();
                androidVipHighSpeedBillingReqParam.mKey = property2;
                androidVipHighSpeedBillingReqParam.mCid = str;
                androidVipHighSpeedBillingReqParam.mGcid = str2;
                androidVipHighSpeedBillingReqParam.mFileSize = j;
                androidVipHighSpeedBillingReqParam.mFileIndex = 0;
                androidVipHighSpeedBillingReqParam.mBussinessFlag = 0;
                androidVipHighSpeedBillingReqParam.mResId = com.umeng.a.d;
                androidVipHighSpeedBillingReqParam.mResType = 0;
                androidVipHighSpeedBillingReqParam.mFileName = com.umeng.a.d;
                if (!TextUtils.isEmpty(a.this.e.b)) {
                    androidVipHighSpeedBillingReqParam.mFileName = a.this.e.b.substring(a.this.e.b.lastIndexOf(File.separator) + 1);
                }
                int AndroidVipCreateHighSpeedBillingTask = XLAndroidVipManager.getInstance(a.this.f).AndroidVipCreateHighSpeedBillingTask(androidVipGetTaskId, androidVipHighSpeedBillingReqParam);
                if (AndroidVipCreateHighSpeedBillingTask == 0) {
                    XLAndroidVipManager.getInstance(a.this.f).vipSetTaskRetryFlag(androidVipGetTaskId.getTaskId(), this.h);
                    if (this.h == 0) {
                        this.h = 1;
                    }
                    return Long.valueOf(androidVipGetTaskId.getTaskId()).longValue();
                }
                this.b = 491;
                a.this.a(new StringBuilder("AndroidVipCreateHighSpeedBillingTask, ret = ").append(AndroidVipCreateHighSpeedBillingTask).toString());
                return 0;
            }

            f b(long j, int i) {
                String property = DownloadManager.getInstanceFor(a.this.f).getProperty(Property.PROP_USER_ID, com.umeng.a.d);
                String property2 = DownloadManager.getInstanceFor(a.this.f).getProperty(Property.PROP_JUMP_KEY, com.umeng.a.d);
                AndroidVipHighSpeedBillingResponse androidVipHighSpeedBillingResponse = new AndroidVipHighSpeedBillingResponse();
                int AndroidVipGetHighSpeedBillingTaskResp = XLAndroidVipManager.getInstance(a.this.f).AndroidVipGetHighSpeedBillingTaskResp(Long.valueOf(this.f).longValue(), androidVipHighSpeedBillingResponse);
                if (AndroidVipGetHighSpeedBillingTaskResp == 0 && androidVipHighSpeedBillingResponse.mResult == 0) {
                    XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, f, (int) b);
                    a.this.a(new StringBuilder("queryBillingResult, mCapacity = ").append(androidVipHighSpeedBillingResponse.mCapacity).append(", Resp.mRemain = ").append(androidVipHighSpeedBillingResponse.mRemain).toString());
                    String b = ab.b(a.this.f);
                    if (TextUtils.isEmpty(b)) {
                        b = "1.0.1";
                    }
                    AndroidVipHighSpeedTaskResponse androidVipHighSpeedTaskResponse = new AndroidVipHighSpeedTaskResponse();
                    XLAndroidVipManager.getInstance(a.this.f).AndroidVipGetHighSpeedTaskResp(this.e, androidVipHighSpeedTaskResponse);
                    int i2 = 0;
                    for (int i3 = 0; i3 < androidVipHighSpeedTaskResponse.mPeerCount; i3++) {
                        PeerResourceParam peerResourceParam = new PeerResourceParam();
                        peerResourceParam.mUserId = Long.valueOf(property).longValue();
                        peerResourceParam.mJmpKey = property2;
                        peerResourceParam.mVipCdnAuth = androidVipHighSpeedBillingResponse.mVipCdnAuthStr + "&tm=android&ver=" + b;
                        peerResourceParam.mUdpPort = androidVipHighSpeedTaskResponse.mPeerRes[i3].mUdpPort;
                        peerResourceParam.mTcpPort = androidVipHighSpeedTaskResponse.mPeerRes[i3].mTcpPort;
                        peerResourceParam.mInternalIp = (int) androidVipHighSpeedTaskResponse.mPeerRes[i3].mIp;
                        peerResourceParam.mPeerId = androidVipHighSpeedTaskResponse.mPeerRes[i3].mPeerId;
                        peerResourceParam.mResLevel = androidVipHighSpeedTaskResponse.mPeerRes[i3].mResourceLevel;
                        peerResourceParam.mResPriority = androidVipHighSpeedTaskResponse.mPeerRes[i3].mResourceUseLevel;
                        peerResourceParam.mCapabilityFlag = androidVipHighSpeedTaskResponse.mPeerRes[i3].mCapability;
                        peerResourceParam.mResType = androidVipHighSpeedTaskResponse.mPeerRes[i3].mCdnType;
                        int btAddPeerResource = XLDownloadManager.getInstance(a.this.f).btAddPeerResource(j, i, peerResourceParam);
                        i2 |= btAddPeerResource == 9000 ? c : 0;
                        a.this.a(new StringBuilder("btAddPeerResource ret = ").append(btAddPeerResource).toString());
                    }
                    if (i2 != 0) {
                        this.b = 200;
                    } else {
                        this.b = 491;
                    }
                    return f.f;
                } else if (AndroidVipGetHighSpeedBillingTaskResp == 7002) {
                    XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, f, AndroidVipGetHighSpeedBillingTaskResp);
                    return f.e;
                } else {
                    XLDownloadManager.getInstance(a.this.f).statExternalInfo(j, i, f, androidVipHighSpeedBillingResponse.mResult);
                    this.b = a.b(androidVipHighSpeedBillingResponse.mResult);
                    a.this.a(new StringBuilder("queryBillingResult ret=").append(AndroidVipGetHighSpeedBillingTaskResp).append(", mResult = ").append(androidVipHighSpeedBillingResponse.mResult).append(", msg=").append(androidVipHighSpeedBillingResponse.mMessage).toString());
                    return f.f;
                }
            }

            public void a(long j, int i) {
                a.this.a(new StringBuilder("vip remove resource, id=").append(j).append(", idx=").append(i).toString());
                XLDownloadManager.getInstance(a.this.f).btRemoveAddedResource(j, i, XZBDevice.DOWNLOAD_LIST_FAILED);
            }
        }

        private void a(String str) {
            am.b(a, new StringBuilder("id=").append(this.d.c).append(", ").append(str).toString());
        }

        public a(Context context, com.xunlei.download.proguard.d dVar, com.xunlei.download.proguard.w.a aVar) {
            super(context, dVar, aVar);
            this.c = new HashMap();
            this.h = null;
            this.f = context;
            this.d = dVar;
            this.e = aVar;
        }

        public int b(XLTaskInfo xLTaskInfo, int i, boolean z) {
            if (xLTaskInfo == null) {
                a("info=null");
                return 190;
            }
            String str = xLTaskInfo.mTaskId + "_LX_" + i;
            d dVar = (d) this.c.get(str);
            String property = DownloadManager.getInstanceFor(this.f).getProperty(Property.PROP_USER_ID, com.umeng.a.d);
            String property2 = DownloadManager.getInstanceFor(this.f).getProperty(Property.PROP_JUMP_KEY, com.umeng.a.d);
            if (property.equals(com.umeng.a.d) || property2.equals(com.umeng.a.d) || !this.d.P) {
                if (dVar != null) {
                    dVar.a(xLTaskInfo.mTaskId, i);
                    dVar.g();
                    this.c.remove(str);
                }
                a("lx,uid=null or jmpkey=null");
                return 190;
            }
            if (dVar == null) {
                d dVar2 = new d(this.d.U != TaskType.BT);
                this.c.put(str, dVar2);
                a("new Channel");
                dVar = dVar2;
            }
            if (xLTaskInfo.mFileSize > 0) {
                dVar.c = xLTaskInfo.mFileSize;
            }
            if (z) {
                dVar.e();
                a(new StringBuilder("tryEnterLXChannel reEnter, mTaskId=").append(xLTaskInfo.mTaskId).toString());
            }
            if (!dVar.d()) {
                dVar.a(xLTaskInfo, i);
                a(new StringBuilder("tryEnterLXChannel mTaskId=").append(xLTaskInfo.mTaskId).toString());
            }
            return dVar.f();
        }

        public int a(XLTaskInfo xLTaskInfo, int i, boolean z) {
            if (TextUtils.isEmpty(a(xLTaskInfo)) || TextUtils.isEmpty(b(xLTaskInfo))) {
                a("info=null or info.cid=null or info.gcid=null");
                return 190;
            }
            String str = xLTaskInfo.mTaskId + "_GS_" + i;
            g gVar = (g) this.c.get(str);
            if (gVar == null) {
                int a = a(xLTaskInfo, i);
                if (a != -1) {
                    return a;
                }
            }
            String property = DownloadManager.getInstanceFor(this.f).getProperty(Property.PROP_USER_ID, com.umeng.a.d);
            String property2 = DownloadManager.getInstanceFor(this.f).getProperty(Property.PROP_JUMP_KEY, com.umeng.a.d);
            if (property.equals(com.umeng.a.d) || property2.equals(com.umeng.a.d) || !this.d.O) {
                a("vip uid=null or jmpkey=null");
                if (gVar != null) {
                    gVar.a(xLTaskInfo.mTaskId, i);
                    gVar.g();
                    this.c.remove(str);
                }
                return 190;
            }
            if (gVar == null) {
                gVar = new g();
                this.c.put(str, gVar);
            }
            if (xLTaskInfo.mFileSize > 0) {
                gVar.c = xLTaskInfo.mFileSize;
            }
            if (gVar.c <= 0) {
                return 190;
            }
            a(new StringBuilder("channel.mTotalBytes = ").append(gVar.c).toString());
            if (z) {
                gVar.e();
                a(new StringBuilder("tryEnterVipChannel reEnter, mTaskId=").append(xLTaskInfo.mTaskId).toString());
            }
            if (!gVar.d()) {
                a(new StringBuilder("tryEnterVipChannel mTaskId=").append(xLTaskInfo.mTaskId).append(", state=").append(gVar.a).toString());
                gVar.a(xLTaskInfo, i);
            }
            return gVar.f();
        }

        public void a() {
            for (a aVar : this.c.values()) {
                aVar.g();
            }
            this.c.clear();
            if (this.h != null) {
                this.h.b();
                if (n.a().f(this.d.c) <= 0) {
                    n.a().i(this.d.c);
                }
            }
        }

        private int a(XLTaskInfo xLTaskInfo, int i) {
            if (this.h != null && n.a().g(this.d.c) == null) {
                a("enterHighspeedTrial stop and remove resource");
                if (this.d.U == TaskType.BT) {
                    i = this.h.k;
                }
                this.h.a(xLTaskInfo.mTaskId, i);
                this.h.b();
                this.h = null;
            }
            if (!n.a().b()) {
                return -1;
            }
            com.xunlei.download.proguard.n.a h = n.a().h(this.d.c);
            if (h == null) {
                return 190;
            }
            if (TextUtils.isEmpty(this.e.b)) {
                return 190;
            }
            if (this.h == null) {
                this.h = (c) n.a().g(this.d.c);
                if (this.h == null) {
                    if (this.d.U == TaskType.BT) {
                        this.h = new c(this.f, this.d.c);
                        this.h.k = i;
                        n.a().a(this.d.c, this.h);
                        a(new StringBuilder("setHiSpeedTrilObject BT idx=").append(i).toString());
                    } else {
                        this.h = new c(this.f, this.d.c);
                        n.a().a(this.d.c, this.h);
                    }
                }
            }
            if (this.d.U == TaskType.BT && this.h.k != i) {
                return Impl.STATUS_TIME_OUT;
            }
            if (xLTaskInfo.mFileSize > 0) {
                this.h.t = xLTaskInfo.mFileSize;
            }
            if (this.h.t <= 0) {
                return 190;
            }
            switch (AnonymousClass_1.c[h.ordinal()]) {
                case c:
                    a("get Resource info");
                    this.h.a(xLTaskInfo, this.e);
                    break;
                case d:
                    this.h.b(xLTaskInfo, this.d, this.e);
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    this.h.a(xLTaskInfo, this.d, this.e, i);
                    break;
            }
            return this.h.a(xLTaskInfo);
        }

        private String a(XLTaskInfo xLTaskInfo) {
            return (this.d.U != TaskType.CID || TextUtils.isEmpty(this.e.n)) ? xLTaskInfo.mCid : this.e.n;
        }

        private String b(XLTaskInfo xLTaskInfo) {
            return (this.d.U != TaskType.CID || TextUtils.isEmpty(this.e.o)) ? xLTaskInfo.mGcid : this.e.o;
        }

        private static int b(int i) {
            return i + 600;
        }
    }

    public w.b a(Context context, d dVar, com.xunlei.download.proguard.w.a aVar) {
        return new a(context, dVar, aVar);
    }

    public void a(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), b);
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            packageInfo = null;
        }
        String str = (packageInfo == null || packageInfo.versionName == null) ? MsgConstant.PROTOCOL_VERSION : packageInfo.versionName;
        am.b(DownloadManager.LOG_TAG, new StringBuilder("AndroidVipInit() ret=").append(XLAndroidVipManager.getInstance(context).AndroidVipInit(context, a, str)).toString());
    }

    public void b(Context context) {
        am.b(DownloadManager.LOG_TAG, new StringBuilder("AndroidVipUninit() ret=").append(XLAndroidVipManager.getInstance(context).AndroidVipUninit()).toString());
    }
}
