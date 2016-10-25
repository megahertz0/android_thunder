package com.android.providers.downloads;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.util.h;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.TaskType;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.download.proguard.ah;
import com.xunlei.download.proguard.al;
import com.xunlei.download.proguard.am;
import com.xunlei.download.proguard.d;
import com.xunlei.download.proguard.e;
import com.xunlei.download.proguard.r;
import com.xunlei.download.proguard.s;
import com.xunlei.download.proguard.u;
import com.xunlei.download.proguard.v;
import com.xunlei.download.proguard.w;
import com.xunlei.download.proguard.y;
import com.xunlei.download.proguard.z;
import com.xunlei.downloadlib.XLDownloadManager;
import com.xunlei.downloadlib.parameter.BtIndexSet;
import com.xunlei.downloadlib.parameter.BtSubTaskDetail;
import com.xunlei.downloadlib.parameter.TorrentInfo;
import com.xunlei.downloadlib.parameter.XLTaskInfo;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.android.spdy.SpdyAgent;

public class ExtendEntryTask implements com.xunlei.download.proguard.k.a {
    private static final int a = 80;
    private static final Object b;

    static class a {
        private long a;
        private long b;
        private long c;
        private long d;
        private long e;

        a() {
        }
    }

    class b extends w {
        HashMap<Integer, Byte> q;
        HashMap<Integer, a> r;
        TorrentInfo s;
        int[] t;

        public b(Context context, u uVar, d dVar, s sVar, e eVar) {
            super(context, uVar, dVar, sVar, eVar);
            this.q = new HashMap();
            this.r = new HashMap();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        protected long a(java.lang.String r25, java.lang.String r26, java.lang.String r27, boolean r28) throws com.xunlei.download.proguard.r {
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.ExtendEntryTask.b.a(java.lang.String, java.lang.String, java.lang.String, boolean):long");
            /*
            this = this;
            r4 = new java.lang.StringBuilder;
            r5 = "create xl task: name = ";
            r4.<init>(r5);
            r0 = r26;
            r4 = r4.append(r0);
            r5 = ", path = ";
            r4 = r4.append(r5);
            r0 = r25;
            r4 = r4.append(r0);
            r5 = ", resuming = ";
            r4 = r4.append(r5);
            r0 = r28;
            r4 = r4.append(r0);
            r5 = ", uri = ";
            r4 = r4.append(r5);
            r0 = r27;
            r4 = r4.append(r0);
            r4 = r4.toString();
            r0 = r24;
            r0.a(r4);
            r0 = r24;
            r0 = r0.l;
            r22 = r0;
            r0 = r24;
            r4 = r0.g;
            r23 = com.xunlei.downloadlib.XLDownloadManager.getInstance(r4);
            r4 = com.xunlei.downloadlib.parameter.XLConstant.XLCreateTaskMode.NEW_TASK;
            r4 = r4.ordinal();
            if (r28 == 0) goto L_0x0673;
        L_0x0054:
            r4 = com.xunlei.downloadlib.parameter.XLConstant.XLCreateTaskMode.CONTINUE_TASK;
            r4 = r4.ordinal();
            r16 = r4;
        L_0x005c:
            r0 = r24;
            r4 = r0.d;
            r4 = r4.U;
            r5 = com.xunlei.download.DownloadManager.TaskType.MAGNET;
            if (r4 != r5) goto L_0x00a1;
        L_0x0066:
            r4 = new com.xunlei.downloadlib.parameter.MagnetTaskParam;
            r4.<init>();
            r0 = r26;
            r4.mFileName = r0;
            r0 = r25;
            r4.mFilePath = r0;
            r0 = r27;
            r4.mUrl = r0;
            r0 = r23;
            r1 = r22;
            r4 = r0.createBtMagnetTask(r4, r1);
            r5 = 9000; // 0x2328 float:1.2612E-41 double:4.4466E-320;
            if (r4 == r5) goto L_0x03ab;
        L_0x0083:
            r5 = new com.xunlei.download.proguard.r;
            r6 = com.android.providers.downloads.XlTaskHelper.b(r4);
            r7 = new java.lang.StringBuilder;
            r8 = "create task failed: ";
            r7.<init>(r8);
            r4 = com.android.providers.downloads.XlTaskHelper.a(r4);
            r4 = r7.append(r4);
            r4 = r4.toString();
            r5.<init>(r6, r4);
            throw r5;
        L_0x00a1:
            r0 = r24;
            r4 = r0.d;
            r4 = r4.U;
            r5 = com.xunlei.download.DownloadManager.TaskType.BT;
            if (r4 != r5) goto L_0x0403;
        L_0x00ab:
            r4 = new com.xunlei.downloadlib.parameter.TorrentInfo;
            r4.<init>();
            r0 = r24;
            r0.s = r4;
            r4 = android.net.Uri.parse(r27);
            r4 = r4.getPath();
            r5 = new java.io.File;
            r5.<init>(r4);
            r5 = r5.exists();
            if (r5 != 0) goto L_0x0670;
        L_0x00c7:
            r0 = r24;
            r4 = r0.d;
            r4 = r4.g;
            r0 = r24;
            r5 = r0.d;
            r5 = r5.x;
            r4 = com.xunlei.download.proguard.z.a(r4, r5);
            r10 = r4;
        L_0x00d8:
            r0 = r24;
            r4 = r0.s;
            r0 = r23;
            r4 = r0.getTorrentInfo(r10, r4);
            r5 = 9000; // 0x2328 float:1.2612E-41 double:4.4466E-320;
            if (r4 == r5) goto L_0x0104;
        L_0x00e6:
            r5 = new com.xunlei.download.proguard.r;
            r6 = com.android.providers.downloads.XlTaskHelper.b(r4);
            r7 = new java.lang.StringBuilder;
            r8 = "create task failed: ";
            r7.<init>(r8);
            r4 = com.android.providers.downloads.XlTaskHelper.a(r4);
            r4 = r7.append(r4);
            r4 = r4.toString();
            r5.<init>(r6, r4);
            throw r5;
        L_0x0104:
            r0 = r24;
            r4 = r0.e;
            r4 = r4.b;
            if (r4 != 0) goto L_0x0261;
        L_0x010c:
            r0 = r24;
            r4 = r0.e;
            r5 = "";
            r4.c = r5;
            r0 = r24;
            r4 = r0.d;
            r4 = r4.i;
            r5 = 4;
            if (r4 != r5) goto L_0x0180;
        L_0x011e:
            r0 = r24;
            r4 = r0.e;
            r0 = r24;
            r5 = r0.d;
            r5 = r5.f;
            r5 = android.net.Uri.parse(r5);
            r5 = r5.getPath();
            r4.b = r5;
            r0 = r24;
            r4 = r0.e;
            r4 = r4.b;
            r5 = "{filename}";
            r4 = r4.endsWith(r5);
            if (r4 == 0) goto L_0x015e;
        L_0x0141:
            r0 = r24;
            r4 = r0.e;
            r0 = r24;
            r5 = r0.e;
            r5 = r5.b;
            r6 = 0;
            r0 = r24;
            r7 = r0.e;
            r7 = r7.b;
            r7 = r7.length();
            r7 = r7 + -10;
            r5 = r5.substring(r6, r7);
            r4.b = r5;
        L_0x015e:
            r4 = new java.io.File;
            r0 = r24;
            r5 = r0.e;
            r5 = r5.b;
            r4.<init>(r5);
            r5 = r4.exists();
            if (r5 != 0) goto L_0x01a8;
        L_0x016f:
            r5 = r4.mkdirs();
            if (r5 != 0) goto L_0x01a8;
        L_0x0175:
            r4 = new com.xunlei.download.proguard.r;
            r5 = 492; // 0x1ec float:6.9E-43 double:2.43E-321;
            r6 = "create dir failed";
            r4.<init>(r5, r6);
            throw r4;
        L_0x0180:
            r0 = r24;
            r4 = r0.e;	 Catch:{ IOException -> 0x019c }
            r0 = r24;
            r5 = r0.k;	 Catch:{ IOException -> 0x019c }
            r6 = 0;
            r0 = r24;
            r7 = r0.d;	 Catch:{ IOException -> 0x019c }
            r7 = r7.i;	 Catch:{ IOException -> 0x019c }
            r8 = 0;
            r5 = r5.a(r6, r7, r8);	 Catch:{ IOException -> 0x019c }
            r5 = r5.getCanonicalPath();	 Catch:{ IOException -> 0x019c }
            r4.b = r5;	 Catch:{ IOException -> 0x019c }
            goto L_0x015e;
        L_0x019c:
            r4 = move-exception;
            r4 = new com.xunlei.download.proguard.r;
            r5 = 492; // 0x1ec float:6.9E-43 double:2.43E-321;
            r6 = "path not exist";
            r4.<init>(r5, r6);
            throw r4;
        L_0x01a8:
            r4 = r4.isDirectory();
            if (r4 != 0) goto L_0x01b9;
        L_0x01ae:
            r4 = new com.xunlei.download.proguard.r;
            r5 = 492; // 0x1ec float:6.9E-43 double:2.43E-321;
            r6 = "dir not exist";
            r4.<init>(r5, r6);
            throw r4;
        L_0x01b9:
            r0 = r24;
            r4 = r0.s;
            r4 = r4.mMultiFileBaseFolder;
            r4 = android.text.TextUtils.isEmpty(r4);
            if (r4 == 0) goto L_0x01dc;
        L_0x01c5:
            r0 = r24;
            r4 = r0.s;
            r0 = r24;
            r5 = r0.s;
            r5 = r5.mSubFileInfo;
            r6 = 0;
            r5 = r5[r6];
            r5 = r5.mFileName;
            r0 = r24;
            r5 = r0.c(r5);
            r4.mMultiFileBaseFolder = r5;
        L_0x01dc:
            r0 = r24;
            r4 = r0.s;
            r0 = r24;
            r5 = r0.s;
            r5 = r5.mMultiFileBaseFolder;
            r0 = r24;
            r5 = r0.b(r5);
            r4.mMultiFileBaseFolder = r5;
            r4 = 1;
            r5 = new java.io.File;
            r0 = r24;
            r6 = r0.e;
            r6 = r6.b;
            r0 = r24;
            r7 = r0.s;
            r7 = r7.mMultiFileBaseFolder;
            r5.<init>(r6, r7);
        L_0x0200:
            r6 = r5.exists();
            if (r6 != 0) goto L_0x0217;
        L_0x0206:
            r6 = r5.mkdirs();
            if (r6 != 0) goto L_0x0217;
        L_0x020c:
            r4 = new com.xunlei.download.proguard.r;
            r5 = 492; // 0x1ec float:6.9E-43 double:2.43E-321;
            r6 = "create dir failed";
            r4.<init>(r5, r6);
            throw r4;
        L_0x0217:
            r6 = r5.isDirectory();
            if (r6 != 0) goto L_0x024a;
        L_0x021d:
            r6 = new java.io.File;
            r0 = r24;
            r5 = r0.e;
            r7 = r5.b;
            r5 = new java.lang.StringBuilder;
            r5.<init>();
            r0 = r24;
            r8 = r0.s;
            r8 = r8.mMultiFileBaseFolder;
            r5 = r5.append(r8);
            r8 = "-";
            r8 = r5.append(r8);
            r5 = r4 + 1;
            r4 = r8.append(r4);
            r4 = r4.toString();
            r6.<init>(r7, r4);
            r4 = r5;
            r5 = r6;
        L_0x024a:
            r6 = r5.isDirectory();
            if (r6 == 0) goto L_0x0200;
        L_0x0250:
            r0 = r24;
            r4 = r0.e;
            r5 = r5.getAbsolutePath();
            r4.b = r5;
            r0 = r24;
            r4 = r0.e;
            r4.a();
        L_0x0261:
            if (r28 == 0) goto L_0x0330;
        L_0x0263:
            r11 = 0;
            r0 = r24;
            r4 = r0.g;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r4 = r4.getContentResolver();	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r5 = com.android.providers.downloads.DownloadProvider.c;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r6 = 6;
            r6 = new java.lang.String[r6];	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r7 = 0;
            r8 = "bt_sub_index";
            r6[r7] = r8;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r7 = 1;
            r8 = "vip_receive_size";
            r6[r7] = r8;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r7 = 2;
            r8 = "lx_receive_size";
            r6[r7] = r8;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r7 = 3;
            r8 = "p2p_receive_size";
            r6[r7] = r8;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r7 = 4;
            r8 = "p2s_receive_size";
            r6[r7] = r8;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r7 = 5;
            r8 = "origin_receive_size";
            r6[r7] = r8;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r7 = "bt_parent_id=?";
            r8 = 1;
            r8 = new java.lang.String[r8];	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r9 = 0;
            r12 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r12.<init>();	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r0 = r24;
            r13 = r0.d;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r14 = r13.c;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r12 = r12.append(r14);	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r12 = r12.toString();	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r8[r9] = r12;	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r9 = 0;
            r5 = r4.query(r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0662, all -> 0x0389 }
            r5.moveToFirst();	 Catch:{ Exception -> 0x0324 }
        L_0x02b9:
            r4 = r5.isAfterLast();	 Catch:{ Exception -> 0x0324 }
            if (r4 != 0) goto L_0x065b;
        L_0x02bf:
            r4 = new com.android.providers.downloads.ExtendEntryTask$a;	 Catch:{ Exception -> 0x0324 }
            r4.<init>();	 Catch:{ Exception -> 0x0324 }
            r6 = "bt_sub_index";
            r6 = r5.getColumnIndex(r6);	 Catch:{ Exception -> 0x0324 }
            r6 = r5.getInt(r6);	 Catch:{ Exception -> 0x0324 }
            r7 = "vip_receive_size";
            r7 = r5.getColumnIndex(r7);	 Catch:{ Exception -> 0x0324 }
            r8 = r5.getLong(r7);	 Catch:{ Exception -> 0x0324 }
            r4.a = r8;	 Catch:{ Exception -> 0x0324 }
            r7 = "lx_receive_size";
            r7 = r5.getColumnIndex(r7);	 Catch:{ Exception -> 0x0324 }
            r8 = r5.getLong(r7);	 Catch:{ Exception -> 0x0324 }
            r4.b = r8;	 Catch:{ Exception -> 0x0324 }
            r7 = "p2p_receive_size";
            r7 = r5.getColumnIndex(r7);	 Catch:{ Exception -> 0x0324 }
            r8 = r5.getLong(r7);	 Catch:{ Exception -> 0x0324 }
            r4.d = r8;	 Catch:{ Exception -> 0x0324 }
            r7 = "p2s_receive_size";
            r7 = r5.getColumnIndex(r7);	 Catch:{ Exception -> 0x0324 }
            r8 = r5.getLong(r7);	 Catch:{ Exception -> 0x0324 }
            r4.c = r8;	 Catch:{ Exception -> 0x0324 }
            r7 = "origin_receive_size";
            r7 = r5.getColumnIndex(r7);	 Catch:{ Exception -> 0x0324 }
            r8 = r5.getLong(r7);	 Catch:{ Exception -> 0x0324 }
            r4.e = r8;	 Catch:{ Exception -> 0x0324 }
            r0 = r24;
            r7 = r0.r;	 Catch:{ Exception -> 0x0324 }
            r6 = java.lang.Integer.valueOf(r6);	 Catch:{ Exception -> 0x0324 }
            r7.put(r6, r4);	 Catch:{ Exception -> 0x0324 }
            r5.moveToNext();	 Catch:{ Exception -> 0x0324 }
            goto L_0x02b9;
        L_0x0324:
            r4 = move-exception;
        L_0x0325:
            r4.printStackTrace();	 Catch:{ all -> 0x065f }
            com.xunlei.download.proguard.am.a(r4);	 Catch:{ all -> 0x065f }
            if (r5 == 0) goto L_0x0330;
        L_0x032d:
            r5.close();
        L_0x0330:
            r4 = new com.xunlei.downloadlib.parameter.BtTaskParam;
            r4.<init>();
            r4.mTorrentPath = r10;
            r0 = r24;
            r5 = r0.e;
            r5 = r5.b;
            r4.mFilePath = r5;
            r0 = r16;
            r4.mCreateMode = r0;
            r0 = r24;
            r5 = r0.g;
            r5 = com.xunlei.download.DownloadManager.getInstanceFor(r5);
            r5 = r5.getRecommandMaxConcurrentBtSubDownloads();
            r4.mMaxConcurrent = r5;
            r0 = r24;
            r6 = r0.j;
            r0 = r24;
            r1 = r27;
            r5 = r0.a(r6, r1);
            r4.mSeqId = r5;
            r0 = r23;
            r1 = r22;
            r4 = r0.createBtTask(r4, r1);
            r5 = 9000; // 0x2328 float:1.2612E-41 double:4.4466E-320;
            if (r4 == r5) goto L_0x0391;
        L_0x036b:
            r5 = new com.xunlei.download.proguard.r;
            r6 = com.android.providers.downloads.XlTaskHelper.b(r4);
            r7 = new java.lang.StringBuilder;
            r8 = "create task failed: ";
            r7.<init>(r8);
            r4 = com.android.providers.downloads.XlTaskHelper.a(r4);
            r4 = r7.append(r4);
            r4 = r4.toString();
            r5.<init>(r6, r4);
            throw r5;
        L_0x0389:
            r4 = move-exception;
            r5 = r11;
        L_0x038b:
            if (r5 == 0) goto L_0x0390;
        L_0x038d:
            r5.close();
        L_0x0390:
            throw r4;
        L_0x0391:
            r4 = r22.getTaskId();
            r0 = r24;
            r0.a(r4);
            r0 = r24;
            r4 = r0.d;
            r4 = r4.y;
            r0 = r24;
            r4 = r0.a(r4);
            if (r4 == 0) goto L_0x03ab;
        L_0x03a8:
            r24.b();
        L_0x03ab:
            r6 = r22.getTaskId();
            r0 = r24;
            r4 = r0.d;
            r4 = r4.ac;
            r5 = android.text.TextUtils.isEmpty(r4);
            if (r5 == 0) goto L_0x03c1;
        L_0x03bb:
            r0 = r24;
            r4 = r0.d;
            r4 = r4.p;
        L_0x03c1:
            r0 = r23;
            r0.setDownloadTaskOrigin(r6, r4);
            r4 = android.text.TextUtils.isEmpty(r26);
            if (r4 == 0) goto L_0x03db;
        L_0x03cc:
            r4 = android.text.TextUtils.isEmpty(r25);
            if (r4 == 0) goto L_0x03db;
        L_0x03d2:
            r0 = r24;
            r1 = r23;
            r2 = r28;
            r0.a(r1, r2, r6);
        L_0x03db:
            r0 = r23;
            r4 = r0.startTask(r6);
            r5 = 9000; // 0x2328 float:1.2612E-41 double:4.4466E-320;
            if (r4 == r5) goto L_0x0659;
        L_0x03e5:
            r5 = new com.xunlei.download.proguard.r;
            r6 = com.android.providers.downloads.XlTaskHelper.b(r4);
            r7 = new java.lang.StringBuilder;
            r8 = "start task failed:";
            r7.<init>(r8);
            r4 = com.android.providers.downloads.XlTaskHelper.a(r4);
            r4 = r7.append(r4);
            r4 = r4.toString();
            r5.<init>(r6, r4);
            throw r5;
        L_0x0403:
            r0 = r24;
            r4 = r0.d;
            r4 = r4.U;
            r5 = com.xunlei.download.DownloadManager.TaskType.ED2K;
            if (r4 != r5) goto L_0x0450;
        L_0x040d:
            r4 = new com.xunlei.downloadlib.parameter.EmuleTaskParam;
            r0 = r24;
            r6 = r0.j;
            r0 = r24;
            r1 = r27;
            r9 = r0.a(r6, r1);
            r5 = r26;
            r6 = r25;
            r7 = r27;
            r8 = r16;
            r4.<init>(r5, r6, r7, r8, r9);
            r0 = r23;
            r1 = r22;
            r4 = r0.createEmuleTask(r4, r1);
            r5 = 9000; // 0x2328 float:1.2612E-41 double:4.4466E-320;
            if (r4 == r5) goto L_0x03ab;
        L_0x0432:
            r5 = new com.xunlei.download.proguard.r;
            r6 = com.android.providers.downloads.XlTaskHelper.b(r4);
            r7 = new java.lang.StringBuilder;
            r8 = "create task failed: ";
            r7.<init>(r8);
            r4 = com.android.providers.downloads.XlTaskHelper.a(r4);
            r4 = r7.append(r4);
            r4 = r4.toString();
            r5.<init>(r6, r4);
            throw r5;
        L_0x0450:
            r0 = r24;
            r4 = r0.d;
            r4 = r4.U;
            r5 = com.xunlei.download.DownloadManager.TaskType.CID;
            if (r4 != r5) goto L_0x0654;
        L_0x045a:
            r4 = "\\|";
            r0 = r27;
            r10 = r0.split(r4);
            r21 = "";
            r20 = "";
            r17 = "";
            r15 = "";
            r18 = -1;
            r4 = 0;
        L_0x0472:
            if (r10 == 0) goto L_0x0504;
        L_0x0474:
            r5 = r10.length;
            if (r4 >= r5) goto L_0x0504;
        L_0x0477:
            r5 = r10[r4];
            r6 = "cid://";
            r5 = r5.startsWith(r6);
            if (r5 == 0) goto L_0x049b;
        L_0x0482:
            r5 = r10[r4];
            r6 = 6;
            r5 = r5.substring(r6);
            r6 = r18;
            r8 = r20;
            r9 = r5;
            r5 = r17;
        L_0x0490:
            r4 = r4 + 1;
            r18 = r6;
            r17 = r5;
            r20 = r8;
            r21 = r9;
            goto L_0x0472;
        L_0x049b:
            r5 = r10[r4];
            r6 = "size";
            r5 = r5.equals(r6);
            if (r5 == 0) goto L_0x04bf;
        L_0x04a6:
            r4 = r4 + 1;
            r5 = r10[r4];	 Catch:{ Exception -> 0x04b5 }
            r6 = java.lang.Long.parseLong(r5);	 Catch:{ Exception -> 0x04b5 }
            r5 = r17;
            r8 = r20;
            r9 = r21;
            goto L_0x0490;
        L_0x04b5:
            r5 = move-exception;
            r6 = -1;
            r5 = r17;
            r8 = r20;
            r9 = r21;
            goto L_0x0490;
        L_0x04bf:
            r5 = r10[r4];
            r6 = "gcid";
            r5 = r5.equals(r6);
            if (r5 == 0) goto L_0x04d6;
        L_0x04ca:
            r4 = r4 + 1;
            r5 = r10[r4];
            r6 = r18;
            r8 = r5;
            r9 = r21;
            r5 = r17;
            goto L_0x0490;
        L_0x04d6:
            r5 = r10[r4];
            r6 = "bcid";
            r5 = r5.equals(r6);
            if (r5 == 0) goto L_0x04ec;
        L_0x04e1:
            r4 = r4 + 1;
            r5 = r10[r4];
            r6 = r18;
            r8 = r20;
            r9 = r21;
            goto L_0x0490;
        L_0x04ec:
            r5 = r10[r4];
            r6 = "file";
            r5 = r5.equals(r6);
            if (r5 == 0) goto L_0x0666;
        L_0x04f7:
            r4 = r4 + 1;
            r15 = r10[r4];
            r6 = r18;
            r5 = r17;
            r8 = r20;
            r9 = r21;
            goto L_0x0490;
        L_0x0504:
            r4 = "";
            r0 = r21;
            r4 = r0.equals(r4);
            if (r4 != 0) goto L_0x0515;
        L_0x050f:
            r4 = 0;
            r4 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1));
            if (r4 > 0) goto L_0x0520;
        L_0x0515:
            r4 = new com.xunlei.download.proguard.r;
            r5 = 491; // 0x1eb float:6.88E-43 double:2.426E-321;
            r6 = "unsupport url, cid and size must large than 0";
            r4.<init>(r5, r6);
            throw r4;
        L_0x0520:
            r0 = r24;
            r4 = r0.e;
            r4 = r4.n;
            r4 = android.text.TextUtils.isEmpty(r4);
            if (r4 == 0) goto L_0x0534;
        L_0x052c:
            r0 = r24;
            r4 = r0.e;
            r0 = r21;
            r4.n = r0;
        L_0x0534:
            r0 = r24;
            r4 = r0.e;
            r4 = r4.o;
            r4 = android.text.TextUtils.isEmpty(r4);
            if (r4 == 0) goto L_0x054e;
        L_0x0540:
            r4 = android.text.TextUtils.isEmpty(r20);
            if (r4 != 0) goto L_0x054e;
        L_0x0546:
            r0 = r24;
            r4 = r0.e;
            r0 = r20;
            r4.o = r0;
        L_0x054e:
            r0 = r24;
            r4 = r0.e;
            r4 = r4.b;
            if (r4 != 0) goto L_0x05e6;
        L_0x0556:
            r0 = r24;
            r4 = r0.e;
            r4 = r4.c;
            if (r4 != 0) goto L_0x0568;
        L_0x055e:
            r0 = r24;
            r4 = r0.e;
            r5 = com.xunlei.download.proguard.al.b(r15);
            r4.c = r5;
        L_0x0568:
            r4 = 0;
            r4 = (r18 > r4 ? 1 : (r18 == r4 ? 0 : -1));
            if (r4 < 0) goto L_0x063f;
        L_0x056e:
            r0 = r24;
            r4 = r0.e;
            r0 = r18;
            r4.g = r0;
        L_0x0576:
            r0 = r24;
            r4 = r0.e;
            r0 = r24;
            r5 = r0.g;
            r0 = r24;
            r6 = r0.d;
            r6 = r6.d;
            r0 = r24;
            r7 = r0.d;
            r7 = r7.f;
            r8 = 0;
            r9 = 0;
            r0 = r24;
            r10 = r0.e;
            r10 = r10.c;
            r0 = r24;
            r11 = r0.d;
            r11 = r11.i;
            r0 = r24;
            r12 = r0.e;
            r12 = r12.g;
            r0 = r24;
            r14 = r0.k;
            r5 = com.xunlei.download.proguard.m.a(r5, r6, r7, r8, r9, r10, r11, r12, r14, r15);
            r4.b = r5;
            r0 = r24;
            r4 = r0.e;
            r4.a();
            r0 = r24;
            r4 = r0.d;
            r4 = r4.y;
            r0 = r24;
            r4 = r0.a(r4);
            if (r4 == 0) goto L_0x05c0;
        L_0x05bd:
            r24.b();
        L_0x05c0:
            r0 = r24;
            r4 = r0.e;
            r4 = r4.b;
            r5 = java.io.File.separator;
            r4 = r4.lastIndexOf(r5);
            r5 = -1;
            if (r4 == r5) goto L_0x0649;
        L_0x05cf:
            r0 = r24;
            r5 = r0.e;
            r5 = r5.b;
            r6 = 0;
            r25 = r5.substring(r6, r4);
            r0 = r24;
            r5 = r0.e;
            r5 = r5.b;
            r4 = r4 + 1;
            r26 = r5.substring(r4);
        L_0x05e6:
            r4 = new com.xunlei.downloadlib.parameter.CIDTaskParam;
            r4.<init>();
            r0 = r21;
            r4.mCid = r0;
            r0 = r20;
            r4.mGcid = r0;
            r0 = r17;
            r4.mBcid = r0;
            r0 = r25;
            r4.mFilePath = r0;
            r0 = r26;
            r4.mFileName = r0;
            r0 = r18;
            r4.mFileSize = r0;
            r0 = r16;
            r4.mCreateMode = r0;
            r0 = r24;
            r6 = r0.j;
            r0 = r24;
            r1 = r27;
            r5 = r0.a(r6, r1);
            r4.mSeqId = r5;
            r0 = r23;
            r1 = r22;
            r4 = r0.createCIDTask(r4, r1);
            r5 = 9000; // 0x2328 float:1.2612E-41 double:4.4466E-320;
            if (r4 == r5) goto L_0x03ab;
        L_0x0621:
            r5 = new com.xunlei.download.proguard.r;
            r6 = com.android.providers.downloads.XlTaskHelper.b(r4);
            r7 = new java.lang.StringBuilder;
            r8 = "create task failed: ";
            r7.<init>(r8);
            r4 = com.android.providers.downloads.XlTaskHelper.a(r4);
            r4 = r7.append(r4);
            r4 = r4.toString();
            r5.<init>(r6, r4);
            throw r5;
        L_0x063f:
            r0 = r24;
            r4 = r0.e;
            r6 = -1;
            r4.g = r6;
            goto L_0x0576;
        L_0x0649:
            r4 = new com.xunlei.download.proguard.r;
            r5 = 492; // 0x1ec float:6.9E-43 double:2.43E-321;
            r6 = "invalid save path!";
            r4.<init>(r5, r6);
            throw r4;
        L_0x0654:
            r4 = super.a(r25, r26, r27, r28);
        L_0x0658:
            return r4;
        L_0x0659:
            r4 = r6;
            goto L_0x0658;
        L_0x065b:
            if (r5 == 0) goto L_0x0330;
        L_0x065d:
            goto L_0x032d;
        L_0x065f:
            r4 = move-exception;
            goto L_0x038b;
        L_0x0662:
            r4 = move-exception;
            r5 = r11;
            goto L_0x0325;
        L_0x0666:
            r6 = r18;
            r5 = r17;
            r8 = r20;
            r9 = r21;
            goto L_0x0490;
        L_0x0670:
            r10 = r4;
            goto L_0x00d8;
        L_0x0673:
            r16 = r4;
            goto L_0x005c;
            */
        }

        protected void h() throws r {
            if (this.d.U != TaskType.BT && this.d.U != TaskType.CID) {
                a("queryTaskInfoFromDownloadLib");
                super.h();
            }
        }

        public void f() {
            super.f();
            this.q.clear();
            this.s = null;
            this.t = null;
            if (this.d.U == TaskType.BT) {
                v.a().d(this.m);
            }
        }

        private int a(int i, int i2) {
            int i3 = i2 == 192 ? Impl.STATUS_PENDING : i2;
            if (i == 200 || i3 == 200) {
                return 200;
            }
            switch (i) {
                case SpdyAgent.ACCS_TEST_SERVER:
                    return i3;
                case Impl.STATUS_PENDING:
                    return i;
                default:
                    if (16 != DownloadManager.translateStatus(i3)) {
                        return i3;
                    }
                    return i != i3 ? y.a : i;
            }
        }

        protected void a(int i, XLTaskInfo xLTaskInfo) {
            super.a(i, xLTaskInfo);
            if (this.d.U == TaskType.BT && xLTaskInfo != null) {
                boolean a = a(xLTaskInfo.mTaskId);
                if ((i == 192 || i == 200) && this.t != null && this.t.length > 0) {
                    int i2 = 0;
                    int i3 = 0;
                    int[] iArr = this.t;
                    int length = iArr.length;
                    int i4 = 0;
                    while (i4 < length) {
                        int i5;
                        int i6 = iArr[i4];
                        if (((Byte) this.q.get(Integer.valueOf(i6))) == null) {
                            BtSubTaskDetail btSubTaskDetail = new BtSubTaskDetail();
                            if (XLDownloadManager.getInstance(this.g).getBtSubTaskInfo(xLTaskInfo.mTaskId, i6, btSubTaskDetail) == 9000) {
                                if (btSubTaskDetail.mTaskInfo.mTaskStatus == 2) {
                                    i5 = Impl.STATUS_SUCCESS;
                                } else if (btSubTaskDetail.mTaskInfo.mTaskStatus == 3) {
                                    i5 = btSubTaskDetail.mTaskInfo.mErrorCode;
                                } else if (btSubTaskDetail.mTaskInfo.mTaskStatus == 4) {
                                    i5 = Impl.STATUS_PAUSED_BY_APP;
                                } else if (btSubTaskDetail.mTaskInfo.mTaskStatus == 0) {
                                    i5 = Impl.STATUS_PENDING;
                                } else if (btSubTaskDetail.mTaskInfo.mTaskStatus == 1) {
                                    i5 = Impl.STATUS_RUNNING;
                                    btSubTaskDetail.mTaskInfo.mTaskId = xLTaskInfo.mTaskId;
                                    i2 = a(i2, this.c.b(btSubTaskDetail.mTaskInfo, i6, a));
                                    i3 = a(i3, this.c.a(btSubTaskDetail.mTaskInfo, i6, a));
                                } else {
                                    i5 = y.a;
                                }
                                if (i5 == 200 || i5 == 491) {
                                    this.q.put(Integer.valueOf(i6), Byte.valueOf((byte) 1));
                                    v.a().b(this.d.c, (long) i6);
                                } else {
                                    long b = v.a().b(this.d.c);
                                    if (b >= 0 && ((long) i6) == b) {
                                        v.a().a(this.g, xLTaskInfo.mTaskId, i6);
                                    }
                                }
                                ContentValues contentValues = new ContentValues();
                                contentValues.put(d.b, Long.valueOf(btSubTaskDetail.mTaskInfo.mFileSize));
                                contentValues.put(Impl.COLUMN_CURRENT_BYTES, Long.valueOf(btSubTaskDetail.mTaskInfo.mDownloadSize));
                                contentValues.put(y.i, Long.valueOf(btSubTaskDetail.mTaskInfo.mDownloadSpeed));
                                contentValues.put(y.j, Long.valueOf(btSubTaskDetail.mTaskInfo.mP2SSpeed));
                                contentValues.put(y.e, Long.valueOf(btSubTaskDetail.mTaskInfo.mOriginSpeed));
                                contentValues.put(Impl.COLUMN_ADDITION_VIP_SPEED, Long.valueOf(btSubTaskDetail.mTaskInfo.mAdditionalResPeerSpeed));
                                contentValues.put(Impl.COLUMN_CID, btSubTaskDetail.mTaskInfo.mCid);
                                contentValues.put(Impl.COLUMN_GCID, btSubTaskDetail.mTaskInfo.mGcid);
                                contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(i5));
                                contentValues.put(Impl.COLUMN_BT_SUB_IS_SELECTED, Boolean.valueOf(btSubTaskDetail.mIsSelect));
                                a aVar = (a) this.r.get(Integer.valueOf(i6));
                                if (aVar != null) {
                                    contentValues.put(Impl.COLUMN_VIP_RECEIVE_SIZE, Long.valueOf(aVar.a + btSubTaskDetail.mTaskInfo.mAdditionalResPeerBytes));
                                    contentValues.put(Impl.COLUMN_LX_RECEIVE_SIZE, Long.valueOf(aVar.b + btSubTaskDetail.mTaskInfo.mAdditionalResVipRecvBytes));
                                    contentValues.put(Impl.COLUMN_P2P_RECEIVE_SIZE, Long.valueOf(aVar.d + btSubTaskDetail.mTaskInfo.mP2PRecvBytes));
                                    contentValues.put(Impl.COLUMN_P2S_RECEIVE_SIZE, Long.valueOf(aVar.c + btSubTaskDetail.mTaskInfo.mP2SRecvBytes));
                                    contentValues.put(Impl.COLUMN_ORIGIN_RECEIVE_SIZE, Long.valueOf(aVar.e + btSubTaskDetail.mTaskInfo.mOriginRecvBytes));
                                } else {
                                    contentValues.put(Impl.COLUMN_VIP_RECEIVE_SIZE, Long.valueOf(btSubTaskDetail.mTaskInfo.mAdditionalResPeerBytes));
                                    contentValues.put(Impl.COLUMN_LX_RECEIVE_SIZE, Long.valueOf(btSubTaskDetail.mTaskInfo.mAdditionalResVipRecvBytes));
                                    contentValues.put(Impl.COLUMN_P2P_RECEIVE_SIZE, Long.valueOf(btSubTaskDetail.mTaskInfo.mP2PRecvBytes));
                                    contentValues.put(Impl.COLUMN_P2S_RECEIVE_SIZE, Long.valueOf(btSubTaskDetail.mTaskInfo.mP2SRecvBytes));
                                    contentValues.put(Impl.COLUMN_ORIGIN_RECEIVE_SIZE, Long.valueOf(btSubTaskDetail.mTaskInfo.mOriginRecvBytes));
                                }
                                contentValues.put(Impl.COLUMN_ADDITION_LX_SPEED, Long.valueOf(btSubTaskDetail.mTaskInfo.mAdditionalResVipSpeed));
                                contentValues.put(Impl.COLUMN_P2P_SPEED, Long.valueOf(btSubTaskDetail.mTaskInfo.mP2PSpeed));
                                try {
                                    this.g.getContentResolver().update(DownloadProvider.c, contentValues, "bt_parent_id=? AND bt_sub_index=?", new String[]{this.d.c, String.valueOf(i6)});
                                    i5 = i3;
                                    i3 = i2;
                                } catch (Throwable e) {
                                    e.printStackTrace();
                                    am.a(e);
                                }
                                i4++;
                                i2 = i3;
                                i3 = i5;
                            }
                        }
                        i5 = i3;
                        i3 = i2;
                        i4++;
                        i2 = i3;
                        i3 = i5;
                    }
                    if (i2 != 0) {
                        this.e.w = i2;
                    }
                    if (i3 != 0) {
                        this.e.t = i3;
                    }
                }
            }
        }

        private String a(long j, String str, BtIndexSet btIndexSet, BtIndexSet btIndexSet2) {
            int i;
            int i2 = 0;
            StringBuilder stringBuilder = new StringBuilder();
            btIndexSet.mIndexSet = new int[this.s.mFileCount];
            HashSet hashSet = new HashSet();
            BtSubTaskDetail btSubTaskDetail = new BtSubTaskDetail();
            for (i = 0; i < btIndexSet.mIndexSet.length; i++) {
                btIndexSet.mIndexSet[i] = this.s.mSubFileInfo[i].mFileIndex;
                stringBuilder.append(btIndexSet.mIndexSet[i]).append(h.b);
                XLDownloadManager.getInstance(this.g).getBtSubTaskInfo(j, btIndexSet.mIndexSet[i], btSubTaskDetail);
                if (btSubTaskDetail.mIsSelect) {
                    hashSet.add(Integer.valueOf(btIndexSet.mIndexSet[i]));
                }
            }
            if (str == null || str.length() <= 0) {
                return stringBuilder.toString();
            }
            String[] split = str.split(h.b);
            if (split == null) {
                return stringBuilder.toString();
            }
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (i = 0; i < split.length; i++) {
                int intValue = Integer.valueOf(split[i]).intValue();
                if (intValue >= 0 && intValue < this.s.mFileCount) {
                    Integer valueOf = Integer.valueOf(split[i]);
                    arrayList.add(valueOf);
                    if (hashSet.contains(valueOf)) {
                        hashSet.remove(valueOf);
                    } else {
                        arrayList2.add(valueOf);
                    }
                }
            }
            if (hashSet.size() > 0) {
                btIndexSet2.mIndexSet = new int[hashSet.size()];
                Iterator it = hashSet.iterator();
                intValue = 0;
                while (it.hasNext()) {
                    int i3 = intValue + 1;
                    btIndexSet2.mIndexSet[intValue] = ((Integer) it.next()).intValue();
                    intValue = i3;
                }
            } else {
                btIndexSet2.mIndexSet = null;
            }
            if (arrayList2.size() > 0) {
                btIndexSet.mIndexSet = new int[arrayList2.size()];
                for (intValue = 0; intValue < arrayList2.size(); intValue++) {
                    btIndexSet.mIndexSet[intValue] = ((Integer) arrayList2.get(intValue)).intValue();
                }
            } else {
                btIndexSet.mIndexSet = null;
            }
            String str2 = com.umeng.a.d;
            while (i2 < arrayList.size()) {
                str2 = str2 + arrayList.get(i2) + h.b;
                i2++;
            }
            return str2;
        }

        private boolean a(long j) {
            boolean z;
            synchronized (b) {
                String str = this.d.M;
                z = (this.e.p == null || this.e.p.length() <= 0 || !(str == null || str.length() <= 0 || this.e.p.equals(str))) ? 1 : null;
                if (z) {
                    BtIndexSet btIndexSet = new BtIndexSet();
                    BtIndexSet btIndexSet2 = new BtIndexSet();
                    String a = a(j, str, btIndexSet, btIndexSet2);
                    String[] split = a.split(h.b);
                    ContentValues[] contentValuesArr = new ContentValues[split.length];
                    this.t = new int[split.length];
                    long j2 = 0;
                    for (int i = 0; i < split.length; i++) {
                        int parseInt = Integer.parseInt(split[i]);
                        j2 += this.s.mSubFileInfo[parseInt].mFileSize;
                        String str2 = this.e.b + File.separator;
                        if (this.s.mSubFileInfo[parseInt].mSubPath != null && !this.s.mSubFileInfo[parseInt].mSubPath.equals(com.umeng.a.d)) {
                            str2 = str2 + this.s.mSubFileInfo[parseInt].mSubPath + File.separator;
                        }
                        str2 = str2 + this.s.mSubFileInfo[parseInt].mFileName;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(Impl.COLUMN_BT_PARENT_ID, Long.valueOf(this.d.c));
                        contentValues.put(Impl.COLUMN_BT_SUB_INDEX, Integer.valueOf(parseInt));
                        contentValues.put(WebBrowserActivity.EXTRA_TITLE, this.s.mSubFileInfo[parseInt].mFileName);
                        contentValues.put(Impl._DATA, str2);
                        contentValues.put(Impl.COLUMN_MIME_TYPE, al.b(this.s.mSubFileInfo[parseInt].mFileName));
                        contentValues.put(d.b, Long.valueOf(this.s.mSubFileInfo[parseInt].mFileSize));
                        contentValues.put(Impl.COLUMN_CURRENT_BYTES, Integer.valueOf(0));
                        contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(Impl.STATUS_RUNNING));
                        contentValues.put(Impl.COLUMN_BT_SUB_IS_SELECTED, Integer.valueOf(1));
                        contentValuesArr[i] = contentValues;
                        this.t[i] = parseInt;
                    }
                    if (str.equals(this.d.M)) {
                        try {
                            am.b(DownloadManager.LOG_TAG, new StringBuilder("[").append(this.j).append("] retdelete=").append(this.g.getContentResolver().delete(DownloadProvider.c, "bt_parent_id=?", new String[]{this.d.c})).append(", retinsert").append(this.g.getContentResolver().bulkInsert(DownloadProvider.c, contentValuesArr)).toString());
                            this.q.clear();
                        } catch (Throwable e) {
                            e.printStackTrace();
                            am.a(e);
                        }
                        this.e.g = j2;
                        this.e.p = a;
                        if (btIndexSet.mIndexSet != null) {
                            XLDownloadManager.getInstance(this.g).selectBtSubTask(j, btIndexSet);
                            am.b(DownloadManager.LOG_TAG, new StringBuilder("[").append(this.j).append("] selectIndexSet.mIndexSet=").append(Arrays.toString(btIndexSet.mIndexSet)).toString());
                        }
                        if (btIndexSet2.mIndexSet != null) {
                            XLDownloadManager.getInstance(this.g).deselectBtSubTask(j, btIndexSet2);
                            am.b(DownloadManager.LOG_TAG, new StringBuilder("[").append(this.j).append("] deselectIndexSet.mIndexSet=").append(Arrays.toString(btIndexSet2.mIndexSet)).toString());
                        }
                        am.b(DownloadManager.LOG_TAG, new StringBuilder("[").append(this.j).append("] result=").append(a).append(", size=").append(j2).toString());
                    } else {
                        am.b(DownloadManager.LOG_TAG, new StringBuilder("[").append(this.j).append("] select change, mInfo.mBtSelectSet=").append(this.d.M).append(", btSelectSet").append(str).toString());
                        z = false;
                    }
                }
            }
            return z;
        }

        private void a(File file, File file2) {
            if (file.exists() && file.isFile() && file.canRead()) {
                if (!file2.getParentFile().exists()) {
                    file2.getParentFile().mkdirs();
                }
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    FileOutputStream fileOutputStream = new FileOutputStream(file2);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read > 0) {
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            fileInputStream.close();
                            fileOutputStream.close();
                            return;
                        }
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                    am.a(e);
                } catch (Throwable e2) {
                    e2.printStackTrace();
                    am.a(e2);
                }
            }
        }

        public ContentValues a(Context context, ContentValues contentValues) {
            String asString = contentValues.getAsString(Impl.COLUMN_URI);
            int intValue = contentValues.getAsInteger(Impl.COLUMN_DESTINATION).intValue();
            String asString2 = contentValues.getAsString(Impl.COLUMN_FILE_NAME_HINT);
            String asString3 = contentValues.getAsString(Impl._DATA);
            if (!TextUtils.isEmpty(asString)) {
                XLDownloadManager instance = XLDownloadManager.getInstance(this.g);
                TorrentInfo torrentInfo = new TorrentInfo();
                this.s = torrentInfo;
                int torrentInfo2 = instance.getTorrentInfo(Uri.parse(asString).getPath(), torrentInfo);
                if (torrentInfo2 != 9000) {
                    am.d(DownloadManager.LOG_TAG, new StringBuilder("prepareTask failed: ").append(XlTaskHelper.a(torrentInfo2)).toString());
                    contentValues.put(Impl.COLUMN_ERROR_MSG, new StringBuilder("prepareTask failed: ").append(XlTaskHelper.a(torrentInfo2)).toString());
                    contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(XlTaskHelper.b(torrentInfo2)));
                } else {
                    File file;
                    contentValues.put(Impl.COLUMN_BT_INFO_HASH, torrentInfo.mInfoHash);
                    if (TextUtils.isEmpty(asString3)) {
                        if (intValue == 4) {
                            asString3 = Uri.parse(asString2).getPath();
                            if (asString3.endsWith("{filename}")) {
                                asString3 = asString3.substring(0, asString3.length() - 10);
                            }
                        } else {
                            try {
                                asString3 = new s(context).a(null, intValue, 0).getCanonicalPath();
                            } catch (IOException e) {
                                contentValues.put(Impl.COLUMN_ERROR_MSG, "path not exist");
                                contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(Impl.STATUS_FILE_ERROR));
                            } catch (Throwable e2) {
                                contentValues.put(Impl.COLUMN_ERROR_MSG, "path not exist,StopRequestException");
                                contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(Impl.STATUS_FILE_ERROR));
                                e2.printStackTrace();
                                am.a(e2);
                            }
                        }
                        file = new File(asString3);
                        if (!file.exists() && !file.mkdirs()) {
                            contentValues.put(Impl.COLUMN_ERROR_MSG, "create dir failed1");
                            contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(Impl.STATUS_FILE_ERROR));
                            am.b(DownloadManager.LOG_TAG, new StringBuilder("prepareSavePath create dir failed1, dir=").append(file.getAbsolutePath()).toString());
                        } else if (file.isDirectory()) {
                            if (TextUtils.isEmpty(torrentInfo.mMultiFileBaseFolder)) {
                                torrentInfo.mMultiFileBaseFolder = c(torrentInfo.mSubFileInfo[0].mFileName);
                            }
                            torrentInfo.mMultiFileBaseFolder = b(torrentInfo.mMultiFileBaseFolder);
                            intValue = 1;
                            File file2 = new File(asString3, torrentInfo.mMultiFileBaseFolder);
                            do {
                                if (!file2.exists() && !file2.mkdirs()) {
                                    contentValues.put(Impl.COLUMN_ERROR_MSG, "create dir failed2");
                                    contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(Impl.STATUS_FILE_ERROR));
                                    am.b(DownloadManager.LOG_TAG, new StringBuilder("prepareSavePath create dir failed2, dir=").append(file2.getAbsolutePath()).toString());
                                    break;
                                } else if (!file2.isDirectory()) {
                                    StringBuilder append = new StringBuilder().append(torrentInfo.mMultiFileBaseFolder).append(SocializeConstants.OP_DIVIDER_MINUS);
                                    int i = intValue + 1;
                                    File file3 = new File(asString3, append.append(intValue).toString());
                                    intValue = i;
                                    file2 = file3;
                                }
                            } while (!file2.isDirectory());
                            asString3 = file2.getAbsolutePath();
                            contentValues.put(Impl._DATA, asString3);
                            contentValues.put(WebBrowserActivity.EXTRA_TITLE, file2.getName());
                        } else {
                            contentValues.put(Impl.COLUMN_ERROR_MSG, "dir not exist");
                            contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(Impl.STATUS_FILE_ERROR));
                            am.b(DownloadManager.LOG_TAG, new StringBuilder("prepareSavePath dir not exist, dir=").append(file.getAbsolutePath()).toString());
                        }
                    }
                    file = new File(z.a(asString3, torrentInfo.mInfoHash));
                    if (!file.exists()) {
                        a(new File(Uri.parse(asString).getPath()), file);
                    }
                }
            }
            return contentValues;
        }

        public void a(Context context, long j, String str, String str2, String str3) {
            this.s = new TorrentInfo();
            int torrentInfo = XLDownloadManager.getInstance(this.g).getTorrentInfo(str3, this.s);
            if (torrentInfo != 9000) {
                this.s = null;
                am.b("XlDownloadTask2", new StringBuilder("prepareTask,getTorrentInfo ret=").append(torrentInfo).toString());
                return;
            }
            a(context, j, str, str2);
        }

        public void a(Context context, long j, String str, String str2) {
            am.b(DownloadManager.LOG_TAG, new StringBuilder("prepareTask id=").append(j).toString());
            TorrentInfo torrentInfo = this.s;
            if (torrentInfo != null) {
                synchronized (b) {
                    int i;
                    int[] iArr;
                    String str3;
                    am.b(DownloadManager.LOG_TAG, "prepareTask mPreparedBtSubTask begin");
                    StringBuilder stringBuilder = new StringBuilder();
                    BtIndexSet btIndexSet = new BtIndexSet();
                    btIndexSet.mIndexSet = new int[torrentInfo.mFileCount];
                    for (i = 0; i < btIndexSet.mIndexSet.length; i++) {
                        btIndexSet.mIndexSet[i] = torrentInfo.mSubFileInfo[i].mFileIndex;
                        stringBuilder.append(btIndexSet.mIndexSet[i]).append(h.b);
                    }
                    if (str == null || str.length() <= 0) {
                        iArr = btIndexSet.mIndexSet;
                    } else {
                        String[] split = str.split(h.b);
                        if (split != null) {
                            ArrayList arrayList = new ArrayList();
                            for (i = 0; i < split.length; i++) {
                                int intValue = Integer.valueOf(split[i]).intValue();
                                if (intValue >= 0 && intValue < torrentInfo.mFileCount) {
                                    arrayList.add(Integer.valueOf(split[i]));
                                }
                            }
                            String str4 = com.umeng.a.d;
                            btIndexSet.mIndexSet = new int[arrayList.size()];
                            str3 = str4;
                            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                                btIndexSet.mIndexSet[i2] = ((Integer) arrayList.get(i2)).intValue();
                                str3 = str3 + arrayList.get(i2) + h.b;
                            }
                        }
                        iArr = btIndexSet.mIndexSet;
                    }
                    try {
                        context.getContentResolver().delete(DownloadProvider.c, "bt_parent_id=?", new String[]{String.valueOf(j)});
                    } catch (Throwable e) {
                        e.printStackTrace();
                        am.a(e);
                    }
                    long j2 = 0;
                    int length = iArr.length;
                    int i3 = 0;
                    while (i3 < length) {
                        int i4 = iArr[i3];
                        ContentValues contentValues = new ContentValues();
                        if (str2 != null) {
                            str3 = str2 + File.separator;
                            if (!(torrentInfo.mSubFileInfo[i4].mSubPath == null || torrentInfo.mSubFileInfo[i4].mSubPath.equals(com.umeng.a.d))) {
                                str3 = str3 + torrentInfo.mSubFileInfo[i4].mSubPath + File.separator;
                            }
                            contentValues.put(Impl._DATA, str3 + torrentInfo.mSubFileInfo[i4].mFileName);
                        }
                        long j3 = torrentInfo.mSubFileInfo[i4].mFileSize + j2;
                        contentValues.put(Impl.COLUMN_BT_PARENT_ID, Long.valueOf(j));
                        contentValues.put(Impl.COLUMN_BT_SUB_INDEX, Integer.valueOf(i4));
                        contentValues.put(WebBrowserActivity.EXTRA_TITLE, torrentInfo.mSubFileInfo[i4].mFileName);
                        contentValues.put(Impl.COLUMN_MIME_TYPE, al.b(torrentInfo.mSubFileInfo[i4].mFileName));
                        contentValues.put(d.b, Long.valueOf(torrentInfo.mSubFileInfo[i4].mFileSize));
                        contentValues.put(Impl.COLUMN_CURRENT_BYTES, Integer.valueOf(0));
                        contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(Impl.STATUS_PENDING));
                        contentValues.put(Impl.COLUMN_BT_SUB_IS_SELECTED, Integer.valueOf(1));
                        try {
                            context.getContentResolver().insert(DownloadProvider.c, contentValues);
                        } catch (Throwable e2) {
                            e2.printStackTrace();
                            am.a(e2);
                        }
                        i3++;
                        j2 = j3;
                    }
                    Uri downloadUri = DownloadManager.getInstanceFor(context).getDownloadUri(j);
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put(d.b, Long.valueOf(j2));
                    try {
                        new s(context).b(XZBDevice.DOWNLOAD_LIST_ALL, str2, j2);
                    } catch (Throwable e22) {
                        e22.printStackTrace();
                        am.a(e22);
                        contentValues2.put(Impl.COLUMN_STATUS, Integer.valueOf(e22.getFinalStatus()));
                        contentValues2.put(Impl.COLUMN_ERROR_MSG, e22.getMessage());
                    }
                    try {
                        context.getContentResolver().update(downloadUri, contentValues2, null, null);
                    } catch (Throwable e222) {
                        e222.printStackTrace();
                        am.a(e222);
                    }
                    am.b(DownloadManager.LOG_TAG, "prepareTask mPreparedBtSubTask end");
                }
            }
        }

        private String b(String str) {
            if (str.length() > 80) {
                str = str.substring(0, a).trim();
            }
            return TextUtils.isEmpty(str) ? "BT" : str;
        }

        private String c(String str) {
            int lastIndexOf = str.lastIndexOf(R.styleable.AppCompatTheme_dropdownListPreferredItemHeight);
            return lastIndexOf > 0 ? str.substring(0, lastIndexOf) : str;
        }
    }

    static class c extends w {
        private static final String q = "DownloadManager.XlTaskGroup";
        private static final HashMap<Integer, Integer> w;
        private long r;
        private DownloadManager s;
        private boolean t;
        private boolean u;
        private ContentResolver v;

        static final class a {
            private static String[] a;

            private a() {
            }

            static {
                a = new String[]{Impl.COLUMN_CURRENT_BYTES, d.b, Impl.COLUMN_VIP_RECEIVE_SIZE, Impl.COLUMN_LX_RECEIVE_SIZE, Impl.COLUMN_P2P_RECEIVE_SIZE, Impl.COLUMN_P2S_RECEIVE_SIZE, Impl.COLUMN_ORIGIN_RECEIVE_SIZE, y.e, y.i, y.j, Impl.COLUMN_P2P_SPEED, Impl.COLUMN_ADDITION_VIP_SPEED, Impl.COLUMN_ADDITION_LX_SPEED, Impl.COLUMN_RES_TOTAL, Impl.COLUMN_RES_USED_TOTAL};
            }

            public static ContentValues a(Cursor cursor) {
                int i;
                int i2 = 0;
                ContentValues contentValues = new ContentValues();
                long[] jArr = new long[a.length];
                int[] iArr = new int[a.length];
                for (i = 0; i < a.length; i++) {
                    iArr[i] = cursor.getColumnIndexOrThrow(a[i]);
                }
                while (cursor.moveToNext()) {
                    for (i = 0; i < a.length; i++) {
                        long j = cursor.getLong(iArr[i]);
                        if (j > 0) {
                            jArr[i] = j + jArr[i];
                        }
                    }
                }
                while (i2 < jArr.length) {
                    contentValues.put(a[i2], Long.valueOf(jArr[i2]));
                    i2++;
                }
                return contentValues;
            }
        }

        public c(Context context, u uVar, d dVar, s sVar, e eVar) {
            super(context, uVar, dVar, sVar, eVar);
            this.r = 0;
            this.t = false;
            this.u = false;
            this.s = DownloadManager.getInstanceFor(this.g);
            this.v = context.getContentResolver();
        }

        public void run() {
            int i = Impl.STATUS_RUNNING;
            this.o = this.d.V;
            this.p = SystemClock.elapsedRealtime();
            this.r = 0;
            try {
                b();
                q();
                j();
                boolean z = true;
                while (true) {
                    i();
                    o();
                    long elapsedRealtime = SystemClock.elapsedRealtime();
                    if (elapsedRealtime - this.r > 500) {
                        this.r = elapsedRealtime;
                        n();
                        if (this.u || this.t) {
                            r();
                        }
                        z = e();
                        i = this.e.d;
                    }
                    if (z) {
                        Thread.sleep(100);
                    } else {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(i));
                        contentValues.put(Impl.COLUMN_ERROR_MSG, null);
                        contentValues.put(y.j, Integer.valueOf(0));
                        contentValues.put(Impl.COLUMN_P2P_SPEED, Integer.valueOf(0));
                        contentValues.put(y.e, Integer.valueOf(0));
                        contentValues.put(y.i, Integer.valueOf(0));
                        contentValues.put(Impl.COLUMN_ADDITION_VIP_SPEED, Integer.valueOf(0));
                        contentValues.put(Impl.COLUMN_ADDITION_LX_SPEED, Integer.valueOf(0));
                        contentValues.put(Impl.COLUMN_RES_TOTAL, Integer.valueOf(0));
                        contentValues.put(Impl.COLUMN_RES_USED_TOTAL, Integer.valueOf(0));
                        a(contentValues, null, null);
                        return;
                    }
                }
            } catch (Throwable e) {
                e.printStackTrace();
                am.a(e);
                i = e.getFinalStatus();
                String message = e.getMessage();
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put(Impl.COLUMN_STATUS, Integer.valueOf(i));
                contentValues2.put(Impl.COLUMN_ERROR_MSG, message);
                contentValues2.put(y.j, Integer.valueOf(0));
                contentValues2.put(Impl.COLUMN_P2P_SPEED, Integer.valueOf(0));
                contentValues2.put(y.e, Integer.valueOf(0));
                contentValues2.put(y.i, Integer.valueOf(0));
                contentValues2.put(Impl.COLUMN_ADDITION_VIP_SPEED, Integer.valueOf(0));
                contentValues2.put(Impl.COLUMN_ADDITION_LX_SPEED, Integer.valueOf(0));
                contentValues2.put(Impl.COLUMN_RES_TOTAL, Integer.valueOf(0));
                message = Impl.COLUMN_RES_USED_TOTAL;
                i = Integer.valueOf(0);
                contentValues2.put(message, i);
                a(contentValues2, null, null);
            } catch (Throwable e2) {
                e2.printStackTrace();
                am.a(e2);
                message = e2.getMessage();
                contentValues2 = new ContentValues();
                contentValues2.put(Impl.COLUMN_STATUS, Integer.valueOf(y.a));
                contentValues2.put(Impl.COLUMN_ERROR_MSG, message);
                contentValues2.put(y.j, Integer.valueOf(0));
                contentValues2.put(Impl.COLUMN_P2P_SPEED, Integer.valueOf(0));
                contentValues2.put(y.e, Integer.valueOf(0));
                contentValues2.put(y.i, Integer.valueOf(0));
                contentValues2.put(Impl.COLUMN_ADDITION_VIP_SPEED, Integer.valueOf(0));
                contentValues2.put(Impl.COLUMN_ADDITION_LX_SPEED, Integer.valueOf(0));
                contentValues2.put(Impl.COLUMN_RES_TOTAL, Integer.valueOf(0));
                message = Impl.COLUMN_RES_USED_TOTAL;
                i = Integer.valueOf(0);
                contentValues2.put(message, i);
                a(contentValues2, null, null);
            }
        }

        static {
            HashMap hashMap = new HashMap();
            w = hashMap;
            hashMap.put(Integer.valueOf(Impl.STATUS_RUNNING), Integer.valueOf(0));
            w.put(Integer.valueOf(Impl.STATUS_PENDING), Integer.valueOf(1));
            w.put(Integer.valueOf(Impl.STATUS_WAITING_TO_RETRY), Integer.valueOf(XZBDevice.DOWNLOAD_LIST_RECYCLE));
            w.put(Integer.valueOf(Impl.STATUS_QUEUED_FOR_WIFI), Integer.valueOf(XZBDevice.DOWNLOAD_LIST_FAILED));
            w.put(Integer.valueOf(Impl.STATUS_WAITING_FOR_NETWORK), Integer.valueOf(XZBDevice.DOWNLOAD_LIST_ALL));
            w.put(Integer.valueOf(Impl.STATUS_PAUSED_BY_APP), Integer.valueOf(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED));
            w.put(Integer.valueOf(y.a), Integer.valueOf(R.styleable.Toolbar_contentInsetEnd));
            w.put(Integer.valueOf(Impl.STATUS_SUCCESS), Integer.valueOf(R.styleable.Toolbar_contentInsetLeft));
        }

        public boolean e() {
            try {
                Cursor query = this.g.getContentResolver().query(p(), null, null, null, null);
                try {
                    int i;
                    int i2;
                    ContentValues a = a.a(query);
                    this.e.g = a.getAsLong(d.b).longValue();
                    int columnIndexOrThrow = query.getColumnIndexOrThrow(Impl.COLUMN_STATUS);
                    query.moveToFirst();
                    int i3 = 200;
                    while (true) {
                        i = query.getInt(columnIndexOrThrow);
                        if (w.get(Integer.valueOf(i)) == null) {
                            i = y.a;
                        }
                        if (((Integer) w.get(Integer.valueOf(i))).intValue() < ((Integer) w.get(Integer.valueOf(i3))).intValue()) {
                            i2 = i;
                        } else {
                            i2 = i3;
                        }
                        if (!query.moveToNext()) {
                            break;
                        }
                        i3 = i2;
                    }
                    i = query.getCount();
                    if (query != null) {
                        query.close();
                    }
                    if (i == 0) {
                        i2 = 192;
                    }
                    if (i2 == 190) {
                        i2 = 192;
                    }
                    a(a, null, null);
                    if (Impl.isStatusCompleted(i2)) {
                        this.d.c();
                    }
                    if (this.e.d != i2) {
                        this.d.a(i2);
                    }
                    this.e.d = i2;
                    return i2 == 192;
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    am.a(e);
                    query.getCount();
                    if (query != null) {
                        query.close();
                    }
                    return false;
                }
            } catch (Exception e2) {
                e = e2;
                query = null;
                try {
                    Throwable e3;
                    e3.printStackTrace();
                    am.a(e3);
                    query.getCount();
                    if (query != null) {
                        query.close();
                    }
                    return false;
                } catch (Throwable th) {
                    e3 = th;
                    query.getCount();
                    if (query != null) {
                        query.close();
                    }
                    throw e3;
                }
            } catch (Throwable th2) {
                e3 = th2;
                query = null;
                query.getCount();
                if (query != null) {
                    query.close();
                }
                throw e3;
            }
        }

        private void i() throws r {
            synchronized (this.d) {
                if (this.d.k == 1) {
                    k();
                    throw new r(193, "download paused by owner");
                } else if (this.d.k == 10) {
                    l();
                    throw new r(190, "greater than max downloading num");
                } else if (this.d.l == 490 || this.d.A) {
                    m();
                    throw new r(490, "download canceled");
                }
            }
        }

        private void j() {
            boolean z = true;
            DownloadManager downloadManager = this.s;
            if (this.d.I != 1) {
                z = false;
            }
            a(new StringBuilder("startSubTasks() count = ").append(downloadManager.resumeDownload(z, s())).toString());
        }

        private void k() {
            a(new StringBuilder("pauseSubTasks() count = ").append(this.s.pauseDownload(s())).toString());
        }

        private void l() {
            a(new StringBuilder("pendSubTasks() count = ").append(this.s.suspendDownload(s())).toString());
        }

        private void m() {
            ContentValues contentValues = new ContentValues();
            if (this.d.A) {
                contentValues.put(Impl.COLUMN_DELETED, Integer.valueOf(1));
            } else {
                contentValues.put(Impl.COLUMN_STATUS, Integer.valueOf(Impl.STATUS_CANCELED));
            }
            int i = 0;
            try {
                i = this.g.getContentResolver().update(p(), contentValues, null, null);
            } catch (Throwable e) {
                e.printStackTrace();
                am.a(e);
            }
            a(new StringBuilder("subtaskOnDelete() count = ").append(i).toString());
        }

        private void n() {
            Throwable e;
            String[] strArr = new String[]{DownloadManager.COLUMN_ID, Impl.COLUMN_STATUS, d.b, Impl.COLUMN_BYPASS_RECOMMENDED_SIZE_LIMIT};
            int maxConcurrentSubDownloads = this.s.getMaxConcurrentSubDownloads();
            HashSet hashSet = new HashSet();
            try {
                HashSet hashSet2;
                Cursor query = this.g.getContentResolver().query(p(), strArr, null, null, null);
                try {
                    int columnIndexOrThrow = query.getColumnIndexOrThrow(DownloadManager.COLUMN_ID);
                    int columnIndexOrThrow2 = query.getColumnIndexOrThrow(Impl.COLUMN_STATUS);
                    HashSet hashSet3 = new HashSet();
                    while (query.moveToNext()) {
                        long j = query.getLong(columnIndexOrThrow);
                        int i = query.getInt(columnIndexOrThrow2);
                        if (!Impl.isStatusCompleted(i)) {
                            if (i == 190 || i == 192) {
                                hashSet3.add(Long.valueOf(j));
                            }
                        }
                    }
                    a(new StringBuilder("updateSelect() okSet = ").append(hashSet3.toString()).toString());
                    Iterator it = this.d.N.iterator();
                    while (it.hasNext()) {
                        j = ((Long) it.next()).longValue();
                        if (hashSet3.contains(Long.valueOf(j))) {
                            hashSet.add(Long.valueOf(j));
                        }
                    }
                    if (hashSet.size() > maxConcurrentSubDownloads) {
                        hashSet3 = new HashSet();
                        it = hashSet.iterator();
                        while (it.hasNext()) {
                            hashSet3.add(Long.valueOf(((Long) it.next()).longValue()));
                            if (hashSet3.size() >= maxConcurrentSubDownloads) {
                                break;
                            }
                        }
                        hashSet2 = hashSet3;
                    } else {
                        if (hashSet.size() < maxConcurrentSubDownloads) {
                            it = hashSet3.iterator();
                            while (it.hasNext()) {
                                j = ((Long) it.next()).longValue();
                                if (!hashSet.contains(Long.valueOf(j)) && hashSet3.contains(Long.valueOf(j))) {
                                    hashSet.add(Long.valueOf(j));
                                }
                                if (hashSet.size() >= maxConcurrentSubDownloads) {
                                    break;
                                }
                            }
                        }
                        hashSet2 = hashSet;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        e.printStackTrace();
                        am.a(e);
                        if (query == null) {
                            return;
                        }
                    } catch (Throwable th) {
                        e = th;
                        if (query != null) {
                            query.close();
                        }
                        throw e;
                    }
                    query.close();
                }
                if (query.moveToFirst()) {
                    int i2;
                    List<Long> arrayList = new ArrayList();
                    do {
                        long j2 = query.getLong(columnIndexOrThrow);
                        i2 = query.getInt(columnIndexOrThrow2);
                        if (!this.d.b(j2) && i2 == 192) {
                            arrayList.add(Long.valueOf(j2));
                        }
                    } while (query.moveToNext());
                    if (arrayList.size() > 0) {
                        long[] jArr = new long[arrayList.size()];
                        i2 = 0;
                        for (Long l : arrayList) {
                            jArr[i2] = l.longValue();
                            i2++;
                        }
                        this.s.suspendDownload(jArr);
                    }
                    if (query != null) {
                        query.close();
                    }
                    if (!a(hashSet2, this.d.N)) {
                        this.d.N = hashSet2;
                        String a = ah.a(this.d.N);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put(Impl.COLUMN_BT_SELECT_SET, a);
                        a(contentValues, null, null);
                        a(new StringBuilder("select info changed. ").append(a).toString());
                    }
                } else if (query != null) {
                    query.close();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                e.printStackTrace();
                am.a(e);
                if (query == null) {
                    return;
                }
                query.close();
            } catch (Throwable th2) {
                e = th2;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw e;
            }
        }

        private boolean a(HashSet<Long> hashSet, HashSet<Long> hashSet2) {
            if (hashSet == null || hashSet2 == null) {
                return true;
            }
            if (hashSet.size() != hashSet2.size()) {
                return false;
            }
            Iterator it;
            if (hashSet.size() > hashSet2.size()) {
                it = hashSet.iterator();
                while (it.hasNext()) {
                    if (!hashSet2.contains(Long.valueOf(((Long) it.next()).longValue()))) {
                        return false;
                    }
                }
            }
            it = hashSet2.iterator();
            while (it.hasNext()) {
                if (!hashSet.contains(Long.valueOf(((Long) it.next()).longValue()))) {
                    return false;
                }
            }
            return true;
        }

        private void o() {
            if (this.d.P != this.t || this.d.O != this.u) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(Impl.COLUMN_IS_LX_SPEEDUP, Boolean.valueOf(this.d.P));
                contentValues.put(Impl.COLUMN_IS_VIP_SPEEDUP, Boolean.valueOf(this.d.O));
                try {
                    this.g.getContentResolver().update(p(), contentValues, null, null);
                } catch (Throwable e) {
                    e.printStackTrace();
                    am.a(e);
                }
                this.t = this.d.P;
                this.u = this.d.O;
            }
        }

        private Uri p() {
            return this.s.getTaskGroupUri(this.d.c);
        }

        private void q() throws r {
            if (this.d.G == null) {
                throw new r(491, "mTitle unknown. ");
            }
            File file;
            if (this.e.b == null) {
                if (this.d.i == 4) {
                    file = new File(Uri.parse(this.d.f).getPath());
                } else {
                    file = this.k.a(null, this.d.i, 0);
                }
                File file2 = new File(file, this.d.G);
                this.e.b = file2.getPath();
                ContentValues contentValues = new ContentValues();
                contentValues.put(Impl._DATA, this.e.b);
                a(contentValues, null, null);
            }
            file = new File(this.e.b);
            if (file.exists()) {
                if (file.isFile()) {
                    throw new r(491, new StringBuilder("location is a file. ").append(file.getPath()).toString());
                }
            } else if (!file.mkdirs()) {
                throw new r(491, new StringBuilder("create folder failed. ").append(file.getPath()).toString());
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void r() {
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.ExtendEntryTask.c.r():void");
            /*
            this = this;
            r6 = 190; // 0xbe float:2.66E-43 double:9.4E-322;
            r7 = 0;
            r1 = 2;
            r13 = 1;
            r8 = 0;
            r0 = 3;
            r2 = new java.lang.String[r0];
            r0 = "vip_status";
            r2[r8] = r0;
            r0 = "lx_status";
            r2[r13] = r0;
            r0 = "lx_progress";
            r2[r1] = r0;
            r9 = new int[r1];
            r10 = new int[r1];
            r11 = new int[r1];
            r12 = new int[r1];
            r0 = r14.g;	 Catch:{ Exception -> 0x00b2, all -> 0x00be }
            r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x00b2, all -> 0x00be }
            r1 = r14.p();	 Catch:{ Exception -> 0x00b2, all -> 0x00be }
            r3 = 0;
            r4 = 0;
            r5 = 0;
            r1 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x00b2, all -> 0x00be }
            r3 = r1.getCount();	 Catch:{ Exception -> 0x0131 }
            if (r3 != 0) goto L_0x0136;
        L_0x0037:
            if (r1 == 0) goto L_0x003c;
        L_0x0039:
            r1.close();
        L_0x003c:
            return;
        L_0x003d:
            r0 = r1.moveToNext();	 Catch:{ Exception -> 0x0131 }
            if (r0 == 0) goto L_0x00c6;
        L_0x0043:
            r0 = r14.u;	 Catch:{ Exception -> 0x0131 }
            if (r0 == 0) goto L_0x0076;
        L_0x0047:
            r0 = 0;
            r0 = r1.getInt(r0);	 Catch:{ Exception -> 0x0131 }
            if (r0 != r6) goto L_0x0055;
        L_0x004e:
            r4 = 0;
            r5 = r9[r4];	 Catch:{ Exception -> 0x0131 }
            r5 = r5 + 1;
            r9[r4] = r5;	 Catch:{ Exception -> 0x0131 }
        L_0x0055:
            r4 = 192; // 0xc0 float:2.69E-43 double:9.5E-322;
            if (r0 != r4) goto L_0x0060;
        L_0x0059:
            r4 = 0;
            r5 = r10[r4];	 Catch:{ Exception -> 0x0131 }
            r5 = r5 + 1;
            r10[r4] = r5;	 Catch:{ Exception -> 0x0131 }
        L_0x0060:
            r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r0 != r4) goto L_0x006b;
        L_0x0064:
            r4 = 0;
            r5 = r11[r4];	 Catch:{ Exception -> 0x0131 }
            r5 = r5 + 1;
            r11[r4] = r5;	 Catch:{ Exception -> 0x0131 }
        L_0x006b:
            r4 = 491; // 0x1eb float:6.88E-43 double:2.426E-321;
            if (r0 != r4) goto L_0x0076;
        L_0x006f:
            r0 = 0;
            r4 = r12[r0];	 Catch:{ Exception -> 0x0131 }
            r4 = r4 + 1;
            r12[r0] = r4;	 Catch:{ Exception -> 0x0131 }
        L_0x0076:
            r0 = r14.t;	 Catch:{ Exception -> 0x0131 }
            if (r0 == 0) goto L_0x003d;
        L_0x007a:
            r0 = 1;
            r4 = r1.getInt(r0);	 Catch:{ Exception -> 0x0131 }
            if (r4 != r6) goto L_0x0088;
        L_0x0081:
            r0 = 1;
            r5 = r9[r0];	 Catch:{ Exception -> 0x0131 }
            r5 = r5 + 1;
            r9[r0] = r5;	 Catch:{ Exception -> 0x0131 }
        L_0x0088:
            r0 = 192; // 0xc0 float:2.69E-43 double:9.5E-322;
            if (r4 != r0) goto L_0x0093;
        L_0x008c:
            r0 = 1;
            r5 = r10[r0];	 Catch:{ Exception -> 0x0131 }
            r5 = r5 + 1;
            r10[r0] = r5;	 Catch:{ Exception -> 0x0131 }
        L_0x0093:
            r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
            if (r4 != r0) goto L_0x0133;
        L_0x0097:
            r0 = 1;
            r5 = r11[r0];	 Catch:{ Exception -> 0x0131 }
            r5 = r5 + 1;
            r11[r0] = r5;	 Catch:{ Exception -> 0x0131 }
            r0 = 2;
            r0 = r1.getInt(r0);	 Catch:{ Exception -> 0x0131 }
            if (r0 <= r2) goto L_0x0133;
        L_0x00a5:
            r2 = 491; // 0x1eb float:6.88E-43 double:2.426E-321;
            if (r4 != r2) goto L_0x00b0;
        L_0x00a9:
            r2 = 1;
            r4 = r12[r2];	 Catch:{ Exception -> 0x0131 }
            r4 = r4 + 1;
            r12[r2] = r4;	 Catch:{ Exception -> 0x0131 }
        L_0x00b0:
            r2 = r0;
            goto L_0x003d;
        L_0x00b2:
            r0 = move-exception;
            r1 = r7;
        L_0x00b4:
            r0.printStackTrace();	 Catch:{ all -> 0x012f }
            com.xunlei.download.proguard.am.a(r0);	 Catch:{ all -> 0x012f }
            if (r1 == 0) goto L_0x003c;
        L_0x00bc:
            goto L_0x0039;
        L_0x00be:
            r0 = move-exception;
            r1 = r7;
        L_0x00c0:
            if (r1 == 0) goto L_0x00c5;
        L_0x00c2:
            r1.close();
        L_0x00c5:
            throw r0;
        L_0x00c6:
            if (r1 == 0) goto L_0x00cb;
        L_0x00c8:
            r1.close();
        L_0x00cb:
            r0 = r11[r8];
            if (r0 <= 0) goto L_0x0109;
        L_0x00cf:
            r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        L_0x00d1:
            r1 = r11[r13];
            if (r1 <= 0) goto L_0x011d;
        L_0x00d5:
            r6 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        L_0x00d7:
            r1 = new android.content.ContentValues;
            r1.<init>();
            r3 = r14.u;
            if (r3 == 0) goto L_0x00ea;
        L_0x00e0:
            r3 = "vip_status";
            r0 = java.lang.Integer.valueOf(r0);
            r1.put(r3, r0);
        L_0x00ea:
            r0 = r14.u;
            if (r0 == 0) goto L_0x0104;
        L_0x00ee:
            r0 = "lx_status";
            r3 = java.lang.Integer.valueOf(r6);
            r1.put(r0, r3);
            if (r2 <= 0) goto L_0x0104;
        L_0x00fa:
            r0 = "lx_progress";
            r2 = java.lang.Integer.valueOf(r2);
            r1.put(r0, r2);
        L_0x0104:
            r14.a(r1, r7, r7);
            goto L_0x003c;
        L_0x0109:
            r0 = r10[r8];
            if (r0 <= 0) goto L_0x0110;
        L_0x010d:
            r0 = 192; // 0xc0 float:2.69E-43 double:9.5E-322;
            goto L_0x00d1;
        L_0x0110:
            r0 = r9[r8];
            if (r0 > 0) goto L_0x011b;
        L_0x0114:
            r0 = r12[r8];
            if (r0 != r3) goto L_0x011b;
        L_0x0118:
            r0 = 491; // 0x1eb float:6.88E-43 double:2.426E-321;
            goto L_0x00d1;
        L_0x011b:
            r0 = r6;
            goto L_0x00d1;
        L_0x011d:
            r1 = r10[r13];
            if (r1 <= 0) goto L_0x0124;
        L_0x0121:
            r6 = 192; // 0xc0 float:2.69E-43 double:9.5E-322;
            goto L_0x00d7;
        L_0x0124:
            r1 = r9[r13];
            if (r1 > 0) goto L_0x00d7;
        L_0x0128:
            r1 = r12[r13];
            if (r1 != r3) goto L_0x00d7;
        L_0x012c:
            r6 = 491; // 0x1eb float:6.88E-43 double:2.426E-321;
            goto L_0x00d7;
        L_0x012f:
            r0 = move-exception;
            goto L_0x00c0;
        L_0x0131:
            r0 = move-exception;
            goto L_0x00b4;
        L_0x0133:
            r0 = r2;
            goto L_0x00a5;
        L_0x0136:
            r2 = r8;
            goto L_0x003d;
            */
        }

        private void a(ContentValues contentValues, String str, String[] strArr) {
            if (contentValues != null && contentValues.size() > 0) {
                contentValues.put(Impl.COLUMN_LAST_MODIFICATION, Long.valueOf(this.h.a()));
                contentValues.put(Impl.COLUMN_DOWNLOAD_DURATION, Long.valueOf((SystemClock.elapsedRealtime() - this.p) + this.o));
                try {
                    this.g.getContentResolver().update(this.d.j(), contentValues, null, null);
                } catch (Throwable e) {
                    e.printStackTrace();
                    am.a(e);
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private long[] s() {
            throw new UnsupportedOperationException("Method not decompiled: com.android.providers.downloads.ExtendEntryTask.c.s():long[]");
            /*
            this = this;
            r6 = 0;
            r8 = 0;
            r0 = 1;
            r2 = new java.lang.String[r0];
            r0 = "_id";
            r2[r8] = r0;
            r7 = new long[r8];
            r0 = r9.v;	 Catch:{ Exception -> 0x0030, all -> 0x0040 }
            r1 = r9.p();	 Catch:{ Exception -> 0x0030, all -> 0x0040 }
            r3 = 0;
            r4 = 0;
            r5 = 0;
            r2 = r0.query(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0030, all -> 0x0040 }
            r0 = r2.getCount();	 Catch:{ Exception -> 0x004d }
            r0 = new long[r0];	 Catch:{ Exception -> 0x004d }
            r1 = r8;
        L_0x0020:
            r3 = r2.moveToNext();	 Catch:{ Exception -> 0x0051 }
            if (r3 == 0) goto L_0x0048;
        L_0x0026:
            r3 = 0;
            r4 = r2.getLong(r3);	 Catch:{ Exception -> 0x0051 }
            r0[r1] = r4;	 Catch:{ Exception -> 0x0051 }
            r1 = r1 + 1;
            goto L_0x0020;
        L_0x0030:
            r0 = move-exception;
            r1 = r0;
            r2 = r6;
            r0 = r7;
        L_0x0034:
            r1.printStackTrace();	 Catch:{ all -> 0x004b }
            com.xunlei.download.proguard.am.a(r1);	 Catch:{ all -> 0x004b }
            if (r2 == 0) goto L_0x003f;
        L_0x003c:
            r2.close();
        L_0x003f:
            return r0;
        L_0x0040:
            r0 = move-exception;
            r2 = r6;
        L_0x0042:
            if (r2 == 0) goto L_0x0047;
        L_0x0044:
            r2.close();
        L_0x0047:
            throw r0;
        L_0x0048:
            if (r2 == 0) goto L_0x003f;
        L_0x004a:
            goto L_0x003c;
        L_0x004b:
            r0 = move-exception;
            goto L_0x0042;
        L_0x004d:
            r0 = move-exception;
            r1 = r0;
            r0 = r7;
            goto L_0x0034;
        L_0x0051:
            r1 = move-exception;
            goto L_0x0034;
            */
        }

        protected void a(String str) {
            am.b(q, new StringBuilder("[").append(this.d.c).append("]").append(str).toString());
        }
    }

    static {
        b = new Object();
    }

    public w a(Context context, u uVar, d dVar, s sVar, e eVar) {
        return (dVar == null || dVar.U != TaskType.GROUP) ? new b(context, uVar, dVar, sVar, eVar) : new c(context, uVar, dVar, sVar, eVar);
    }

    public void a(Context context) {
    }

    public void b(Context context) {
    }
}
