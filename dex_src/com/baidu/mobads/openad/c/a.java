package com.baidu.mobads.openad.c;

import android.content.Context;
import android.os.Environment;
import com.baidu.mobads.AdSettings;
import com.baidu.mobads.AdSettings.b;
import com.baidu.mobads.interfaces.utils.IXAdSystemUtils;
import com.baidu.mobads.j.m;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import com.xunlei.tdlive.R;
import java.io.File;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class a extends Observable implements IOAdDownloader, Runnable {
    protected Context a;
    protected URL b;
    protected URL c;
    protected String d;
    protected int e;
    protected Boolean f;
    protected String g;
    protected int h;
    protected DownloadStatus i;
    protected volatile int j;
    protected int k;
    protected ArrayList<a> l;
    g m;
    private String n;
    private String o;

    protected class a implements Runnable {
        protected int a;
        protected URL b;
        protected String c;
        protected int d;
        protected int e;
        protected int f;
        protected boolean g;
        protected Thread h;
        private volatile boolean j;
        private volatile int k;
        private HttpURLConnection l;

        public a(int i, URL url, String str, int i2, int i3, int i4) {
            this.j = false;
            this.k = 0;
            this.a = i;
            this.b = url;
            this.c = str;
            this.d = i2;
            this.e = i3;
            this.f = i4;
            this.g = false;
        }

        public boolean a() {
            return this.g;
        }

        public synchronized void b() {
            this.j = false;
            this.h = new Thread(this);
            this.h.start();
        }

        public synchronized void c() {
            this.j = true;
            this.k++;
        }

        public void a(HttpURLConnection httpURLConnection) {
            this.l = httpURLConnection;
        }

        public void d() {
            if (this.h != null) {
                this.h.join();
                return;
            }
            m.a().f().w("DownloadThread", "Warning: mThread in DownloadThread.waitFinish is null");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.openad.c.a.a.run():void");
            /*
            this = this;
            r2 = 0;
            r11 = 2;
            r10 = 1;
            r9 = 0;
            r5 = r12.k;
            r1 = 0;
            r3 = 0;
            r0 = r12.d;	 Catch:{ Exception -> 0x04e0, all -> 0x04c3 }
            r4 = r12.f;	 Catch:{ Exception -> 0x04e0, all -> 0x04c3 }
            r0 = r0 + r4;
            r4 = r12.e;	 Catch:{ Exception -> 0x04e0, all -> 0x04c3 }
            if (r0 < r4) goto L_0x005f;
        L_0x0011:
            r0 = 1;
            r12.g = r0;	 Catch:{ Exception -> 0x04e0, all -> 0x04c3 }
            r1 = r2;
            r3 = r2;
            r4 = r2;
        L_0x0017:
            r0 = com.baidu.mobads.j.m.a();
            r0 = r0.f();
            r2 = "DownloadThread";
            r6 = new java.lang.StringBuilder;
            r7 = "Thread[";
            r6.<init>(r7);
            r7 = r12.a;
            r6 = r6.append(r7);
            r7 = "] ver(";
            r6 = r6.append(r7);
            r5 = r6.append(r5);
            r6 = ") executed end; isFinished=";
            r5 = r5.append(r6);
            r6 = r12.g;
            r5 = r5.append(r6);
            r5 = r5.toString();
            r0.d(r2, r5);
            if (r1 == 0) goto L_0x0054;
        L_0x0051:
            r1.close();	 Catch:{ IOException -> 0x03d3 }
        L_0x0054:
            if (r3 == 0) goto L_0x0059;
        L_0x0056:
            r3.close();	 Catch:{ IOException -> 0x03ee }
        L_0x0059:
            if (r4 == 0) goto L_0x005e;
        L_0x005b:
            r4.disconnect();	 Catch:{ Exception -> 0x0409 }
        L_0x005e:
            return;
        L_0x005f:
            r0 = r12.l;	 Catch:{ Exception -> 0x04e0, all -> 0x04c3 }
            if (r0 != 0) goto L_0x02f8;
        L_0x0063:
            r0 = r12.b;	 Catch:{ Exception -> 0x04e0, all -> 0x04c3 }
            r0 = r0.openConnection();	 Catch:{ Exception -> 0x04e0, all -> 0x04c3 }
            r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x04e0, all -> 0x04c3 }
            r4 = com.baidu.mobads.openad.c.a.this;	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4 = r4.f;	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4 = r4.booleanValue();	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            if (r4 == 0) goto L_0x0119;
        L_0x0075:
            r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4.<init>();	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r6 = r12.d;	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r7 = r12.f;	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r6 = r6 + r7;
            r4 = r4.append(r6);	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r6 = "-";
            r4 = r4.append(r6);	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r6 = r12.e;	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4 = r4.append(r6);	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4 = r4.toString();	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r6 = "Range";
            r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r8 = "bytes=";
            r7.<init>(r8);	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4 = r7.append(r4);	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4 = r4.toString();	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r0.setRequestProperty(r6, r4);	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
        L_0x00aa:
            r0.connect();	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4 = r0.getResponseCode();	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r6 = r12.k;	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            if (r5 == r6) goto L_0x019d;
        L_0x00b5:
            r4 = com.baidu.mobads.j.m.a();
            r4 = r4.f();
            r6 = "DownloadThread";
            r7 = new java.lang.StringBuilder;
            r8 = "Thread[";
            r7.<init>(r8);
            r8 = r12.a;
            r7 = r7.append(r8);
            r8 = "] ver(";
            r7 = r7.append(r8);
            r5 = r7.append(r5);
            r7 = ") executed end; isFinished=";
            r5 = r5.append(r7);
            r7 = r12.g;
            r5 = r5.append(r7);
            r5 = r5.toString();
            r4.d(r6, r5);
            if (r2 == 0) goto L_0x00f2;
        L_0x00ef:
            r3.close();	 Catch:{ IOException -> 0x0421 }
        L_0x00f2:
            if (r2 == 0) goto L_0x00f7;
        L_0x00f4:
            r1.close();	 Catch:{ IOException -> 0x043c }
        L_0x00f7:
            if (r0 == 0) goto L_0x005e;
        L_0x00f9:
            r0.disconnect();	 Catch:{ Exception -> 0x00fe }
            goto L_0x005e;
        L_0x00fe:
            r0 = move-exception;
            r1 = com.baidu.mobads.j.m.a();
            r1 = r1.f();
            r2 = new java.lang.Object[r11];
            r3 = "DownloadThread";
            r2[r9] = r3;
            r0 = r0.getMessage();
            r2[r10] = r0;
        L_0x0114:
            r1.w(r2);
            goto L_0x005e;
        L_0x0119:
            r4 = 0;
            r12.f = r4;	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            goto L_0x00aa;
        L_0x011d:
            r1 = move-exception;
            r3 = r0;
            r0 = r1;
            r1 = r2;
        L_0x0121:
            r4 = com.baidu.mobads.j.m.a();	 Catch:{ all -> 0x04da }
            r4 = r4.f();	 Catch:{ all -> 0x04da }
            r6 = "DownloadThread";
            r0 = r0.getMessage();	 Catch:{ all -> 0x04da }
            r4.d(r6, r0);	 Catch:{ all -> 0x04da }
            r0 = r12.k;	 Catch:{ all -> 0x04da }
            if (r5 != r0) goto L_0x013c;
        L_0x0137:
            r0 = com.baidu.mobads.openad.c.a.this;	 Catch:{ all -> 0x04da }
            r0.b();	 Catch:{ all -> 0x04da }
        L_0x013c:
            r0 = com.baidu.mobads.j.m.a();
            r0 = r0.f();
            r4 = "DownloadThread";
            r6 = new java.lang.StringBuilder;
            r7 = "Thread[";
            r6.<init>(r7);
            r7 = r12.a;
            r6 = r6.append(r7);
            r7 = "] ver(";
            r6 = r6.append(r7);
            r5 = r6.append(r5);
            r6 = ") executed end; isFinished=";
            r5 = r5.append(r6);
            r6 = r12.g;
            r5 = r5.append(r6);
            r5 = r5.toString();
            r0.d(r4, r5);
            if (r1 == 0) goto L_0x0179;
        L_0x0176:
            r1.close();	 Catch:{ IOException -> 0x039d }
        L_0x0179:
            if (r2 == 0) goto L_0x017e;
        L_0x017b:
            r2.close();	 Catch:{ IOException -> 0x03b8 }
        L_0x017e:
            if (r3 == 0) goto L_0x005e;
        L_0x0180:
            r3.disconnect();	 Catch:{ Exception -> 0x0185 }
            goto L_0x005e;
        L_0x0185:
            r0 = move-exception;
            r1 = com.baidu.mobads.j.m.a();
            r1 = r1.f();
            r2 = new java.lang.Object[r11];
            r3 = "DownloadThread";
            r2[r9] = r3;
            r0 = r0.getMessage();
            r2[r10] = r0;
            goto L_0x0114;
        L_0x019d:
            r4 = r4 / 100;
            if (r4 == r11) goto L_0x0207;
        L_0x01a1:
            r4 = com.baidu.mobads.openad.c.a.this;	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4.b();	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4 = com.baidu.mobads.j.m.a();
            r4 = r4.f();
            r6 = "DownloadThread";
            r7 = new java.lang.StringBuilder;
            r8 = "Thread[";
            r7.<init>(r8);
            r8 = r12.a;
            r7 = r7.append(r8);
            r8 = "] ver(";
            r7 = r7.append(r8);
            r5 = r7.append(r5);
            r7 = ") executed end; isFinished=";
            r5 = r5.append(r7);
            r7 = r12.g;
            r5 = r5.append(r7);
            r5 = r5.toString();
            r4.d(r6, r5);
            if (r2 == 0) goto L_0x01e3;
        L_0x01e0:
            r3.close();	 Catch:{ IOException -> 0x0457 }
        L_0x01e3:
            if (r2 == 0) goto L_0x01e8;
        L_0x01e5:
            r1.close();	 Catch:{ IOException -> 0x0472 }
        L_0x01e8:
            if (r0 == 0) goto L_0x005e;
        L_0x01ea:
            r0.disconnect();	 Catch:{ Exception -> 0x01ef }
            goto L_0x005e;
        L_0x01ef:
            r0 = move-exception;
            r1 = com.baidu.mobads.j.m.a();
            r1 = r1.f();
            r2 = new java.lang.Object[r11];
            r3 = "DownloadThread";
            r2[r9] = r3;
            r0 = r0.getMessage();
            r2[r10] = r0;
            goto L_0x0114;
        L_0x0207:
            r4 = r0.getContentType();	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r6 = "text/html";
            r4 = r4.equals(r6);	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            if (r4 == 0) goto L_0x027a;
        L_0x0214:
            r4 = com.baidu.mobads.openad.c.a.this;	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4.b();	 Catch:{ Exception -> 0x011d, all -> 0x04c8 }
            r4 = com.baidu.mobads.j.m.a();
            r4 = r4.f();
            r6 = "DownloadThread";
            r7 = new java.lang.StringBuilder;
            r8 = "Thread[";
            r7.<init>(r8);
            r8 = r12.a;
            r7 = r7.append(r8);
            r8 = "] ver(";
            r7 = r7.append(r8);
            r5 = r7.append(r5);
            r7 = ") executed end; isFinished=";
            r5 = r5.append(r7);
            r7 = r12.g;
            r5 = r5.append(r7);
            r5 = r5.toString();
            r4.d(r6, r5);
            if (r2 == 0) goto L_0x0256;
        L_0x0253:
            r3.close();	 Catch:{ IOException -> 0x048d }
        L_0x0256:
            if (r2 == 0) goto L_0x025b;
        L_0x0258:
            r1.close();	 Catch:{ IOException -> 0x04a8 }
        L_0x025b:
            if (r0 == 0) goto L_0x005e;
        L_0x025d:
            r0.disconnect();	 Catch:{ Exception -> 0x0262 }
            goto L_0x005e;
        L_0x0262:
            r0 = move-exception;
            r1 = com.baidu.mobads.j.m.a();
            r1 = r1.f();
            r2 = new java.lang.Object[r11];
            r3 = "DownloadThread";
            r2[r9] = r3;
            r0 = r0.getMessage();
            r2[r10] = r0;
            goto L_0x0114;
        L_0x027a:
            r4 = r0;
        L_0x027b:
            r3 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x04ea, all -> 0x04d3 }
            r0 = r4.getInputStream();	 Catch:{ Exception -> 0x04ea, all -> 0x04d3 }
            r3.<init>(r0);	 Catch:{ Exception -> 0x04ea, all -> 0x04d3 }
            r0 = r12.d;	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r1 = r12.f;	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r0 = r0 + r1;
            r1 = com.baidu.mobads.j.m.a();	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r1 = r1.f();	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r6 = "DownloadThread";
            r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r8 = "tmpStartByte = ";
            r7.<init>(r8);	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r7 = r7.append(r0);	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r7 = r7.toString();	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r1.d(r6, r7);	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r1 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r6 = r12.c;	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r7 = "rw";
            r1.<init>(r6, r7);	 Catch:{ Exception -> 0x04ef, all -> 0x04d7 }
            r6 = (long) r0;
            r1.seek(r6);	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            r2 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;
            r2 = new byte[r2];	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
        L_0x02ba:
            r6 = com.baidu.mobads.openad.c.a.this;	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            r6 = com.baidu.mobads.openad.c.a.this;	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            r7 = com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus.DOWNLOADING;	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            if (r6 != r7) goto L_0x02ea;
        L_0x02c2:
            r6 = 0;
            r7 = 102400; // 0x19000 float:1.43493E-40 double:5.05923E-319;
            r6 = r3.read(r2, r6, r7);	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            r7 = -1;
            if (r6 == r7) goto L_0x02ea;
        L_0x02cd:
            r7 = r12.e;	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            if (r0 >= r7) goto L_0x02ea;
        L_0x02d1:
            r7 = r12.k;	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            if (r5 != r7) goto L_0x02ea;
        L_0x02d5:
            r7 = 0;
            r1.write(r2, r7, r6);	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            r7 = r12.f;	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            r7 = r7 + r6;
            r12.f = r7;	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            r0 = r0 + r6;
            r7 = com.baidu.mobads.openad.c.a.this;	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            r7.a(r6);	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            monitor-enter(r12);	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            r6 = r12.j;	 Catch:{ all -> 0x0302 }
            if (r6 == 0) goto L_0x0300;
        L_0x02e9:
            monitor-exit(r12);	 Catch:{ all -> 0x0302 }
        L_0x02ea:
            r2 = r12.e;	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            if (r0 < r2) goto L_0x0017;
        L_0x02ee:
            r0 = 1;
            r12.g = r0;	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
            goto L_0x0017;
        L_0x02f3:
            r0 = move-exception;
            r2 = r3;
            r3 = r4;
            goto L_0x0121;
        L_0x02f8:
            r1 = r12.l;	 Catch:{ Exception -> 0x04e0, all -> 0x04c3 }
            r0 = 0;
            r12.l = r0;	 Catch:{ Exception -> 0x04e5, all -> 0x04ce }
            r4 = r1;
            goto L_0x027b;
        L_0x0300:
            monitor-exit(r12);	 Catch:{ all -> 0x0302 }
            goto L_0x02ba;
        L_0x0302:
            r0 = move-exception;
            monitor-exit(r12);	 Catch:{ all -> 0x0302 }
            throw r0;	 Catch:{ Exception -> 0x02f3, all -> 0x0305 }
        L_0x0305:
            r0 = move-exception;
            r2 = r1;
        L_0x0307:
            r1 = com.baidu.mobads.j.m.a();
            r1 = r1.f();
            r6 = "DownloadThread";
            r7 = new java.lang.StringBuilder;
            r8 = "Thread[";
            r7.<init>(r8);
            r8 = r12.a;
            r7 = r7.append(r8);
            r8 = "] ver(";
            r7 = r7.append(r8);
            r5 = r7.append(r5);
            r7 = ") executed end; isFinished=";
            r5 = r5.append(r7);
            r7 = r12.g;
            r5 = r5.append(r7);
            r5 = r5.toString();
            r1.d(r6, r5);
            if (r2 == 0) goto L_0x0344;
        L_0x0341:
            r2.close();	 Catch:{ IOException -> 0x034f }
        L_0x0344:
            if (r3 == 0) goto L_0x0349;
        L_0x0346:
            r3.close();	 Catch:{ IOException -> 0x0369 }
        L_0x0349:
            if (r4 == 0) goto L_0x034e;
        L_0x034b:
            r4.disconnect();	 Catch:{ Exception -> 0x0383 }
        L_0x034e:
            throw r0;
        L_0x034f:
            r1 = move-exception;
            r2 = com.baidu.mobads.j.m.a();
            r2 = r2.f();
            r5 = new java.lang.Object[r11];
            r6 = "DownloadThread";
            r5[r9] = r6;
            r1 = r1.getMessage();
            r5[r10] = r1;
            r2.w(r5);
            goto L_0x0344;
        L_0x0369:
            r1 = move-exception;
            r2 = com.baidu.mobads.j.m.a();
            r2 = r2.f();
            r3 = new java.lang.Object[r11];
            r5 = "DownloadThread";
            r3[r9] = r5;
            r1 = r1.getMessage();
            r3[r10] = r1;
            r2.w(r3);
            goto L_0x0349;
        L_0x0383:
            r1 = move-exception;
            r2 = com.baidu.mobads.j.m.a();
            r2 = r2.f();
            r3 = new java.lang.Object[r11];
            r4 = "DownloadThread";
            r3[r9] = r4;
            r1 = r1.getMessage();
            r3[r10] = r1;
            r2.w(r3);
            goto L_0x034e;
        L_0x039d:
            r0 = move-exception;
            r1 = com.baidu.mobads.j.m.a();
            r1 = r1.f();
            r4 = new java.lang.Object[r11];
            r5 = "DownloadThread";
            r4[r9] = r5;
            r0 = r0.getMessage();
            r4[r10] = r0;
            r1.w(r4);
            goto L_0x0179;
        L_0x03b8:
            r0 = move-exception;
            r1 = com.baidu.mobads.j.m.a();
            r1 = r1.f();
            r2 = new java.lang.Object[r11];
            r4 = "DownloadThread";
            r2[r9] = r4;
            r0 = r0.getMessage();
            r2[r10] = r0;
            r1.w(r2);
            goto L_0x017e;
        L_0x03d3:
            r0 = move-exception;
            r1 = com.baidu.mobads.j.m.a();
            r1 = r1.f();
            r2 = new java.lang.Object[r11];
            r5 = "DownloadThread";
            r2[r9] = r5;
            r0 = r0.getMessage();
            r2[r10] = r0;
            r1.w(r2);
            goto L_0x0054;
        L_0x03ee:
            r0 = move-exception;
            r1 = com.baidu.mobads.j.m.a();
            r1 = r1.f();
            r2 = new java.lang.Object[r11];
            r3 = "DownloadThread";
            r2[r9] = r3;
            r0 = r0.getMessage();
            r2[r10] = r0;
            r1.w(r2);
            goto L_0x0059;
        L_0x0409:
            r0 = move-exception;
            r1 = com.baidu.mobads.j.m.a();
            r1 = r1.f();
            r2 = new java.lang.Object[r11];
            r3 = "DownloadThread";
            r2[r9] = r3;
            r0 = r0.getMessage();
            r2[r10] = r0;
            goto L_0x0114;
        L_0x0421:
            r3 = move-exception;
            r4 = com.baidu.mobads.j.m.a();
            r4 = r4.f();
            r5 = new java.lang.Object[r11];
            r6 = "DownloadThread";
            r5[r9] = r6;
            r3 = r3.getMessage();
            r5[r10] = r3;
            r4.w(r5);
            goto L_0x00f2;
        L_0x043c:
            r1 = move-exception;
            r2 = com.baidu.mobads.j.m.a();
            r2 = r2.f();
            r3 = new java.lang.Object[r11];
            r4 = "DownloadThread";
            r3[r9] = r4;
            r1 = r1.getMessage();
            r3[r10] = r1;
            r2.w(r3);
            goto L_0x00f7;
        L_0x0457:
            r3 = move-exception;
            r4 = com.baidu.mobads.j.m.a();
            r4 = r4.f();
            r5 = new java.lang.Object[r11];
            r6 = "DownloadThread";
            r5[r9] = r6;
            r3 = r3.getMessage();
            r5[r10] = r3;
            r4.w(r5);
            goto L_0x01e3;
        L_0x0472:
            r1 = move-exception;
            r2 = com.baidu.mobads.j.m.a();
            r2 = r2.f();
            r3 = new java.lang.Object[r11];
            r4 = "DownloadThread";
            r3[r9] = r4;
            r1 = r1.getMessage();
            r3[r10] = r1;
            r2.w(r3);
            goto L_0x01e8;
        L_0x048d:
            r3 = move-exception;
            r4 = com.baidu.mobads.j.m.a();
            r4 = r4.f();
            r5 = new java.lang.Object[r11];
            r6 = "DownloadThread";
            r5[r9] = r6;
            r3 = r3.getMessage();
            r5[r10] = r3;
            r4.w(r5);
            goto L_0x0256;
        L_0x04a8:
            r1 = move-exception;
            r2 = com.baidu.mobads.j.m.a();
            r2 = r2.f();
            r3 = new java.lang.Object[r11];
            r4 = "DownloadThread";
            r3[r9] = r4;
            r1 = r1.getMessage();
            r3[r10] = r1;
            r2.w(r3);
            goto L_0x025b;
        L_0x04c3:
            r0 = move-exception;
            r3 = r2;
            r4 = r2;
            goto L_0x0307;
        L_0x04c8:
            r1 = move-exception;
            r3 = r2;
            r4 = r0;
            r0 = r1;
            goto L_0x0307;
        L_0x04ce:
            r0 = move-exception;
            r3 = r2;
            r4 = r1;
            goto L_0x0307;
        L_0x04d3:
            r0 = move-exception;
            r3 = r2;
            goto L_0x0307;
        L_0x04d7:
            r0 = move-exception;
            goto L_0x0307;
        L_0x04da:
            r0 = move-exception;
            r4 = r3;
            r3 = r2;
            r2 = r1;
            goto L_0x0307;
        L_0x04e0:
            r0 = move-exception;
            r1 = r2;
            r3 = r2;
            goto L_0x0121;
        L_0x04e5:
            r0 = move-exception;
            r3 = r1;
            r1 = r2;
            goto L_0x0121;
        L_0x04ea:
            r0 = move-exception;
            r1 = r2;
            r3 = r4;
            goto L_0x0121;
        L_0x04ef:
            r0 = move-exception;
            r1 = r2;
            r2 = r3;
            r3 = r4;
            goto L_0x0121;
            */
        }
    }

    public a(Context context, URL url, String str, String str2, int i, String str3, String str4) {
        this.f = Boolean.valueOf(true);
        this.m = null;
        this.a = context;
        this.b = url;
        this.d = str;
        this.e = i;
        if (str2 == null || str2.trim().length() <= 0) {
            String file = url.getFile();
            this.g = file.substring(file.lastIndexOf(R.styleable.AppCompatTheme_spinnerDropDownItemStyle) + 1);
        } else {
            this.g = str2;
        }
        this.h = -1;
        this.i = DownloadStatus.NONE;
        this.j = 0;
        this.k = 0;
        this.n = str3;
        this.o = str4;
        this.l = new ArrayList();
    }

    public void pause() {
        try {
            m.a().f().d("Downloader", new StringBuilder("execute Pause; state = ").append(this.i).toString());
            if (this.i == DownloadStatus.DOWNLOADING || this.i == DownloadStatus.ERROR || this.i == DownloadStatus.NONE) {
                if (this.l != null) {
                    for (int i = 0; i < this.l.size(); i++) {
                        if (!((a) this.l.get(i)).a()) {
                            ((a) this.l.get(i)).c();
                        }
                    }
                }
                a(DownloadStatus.PAUSED);
            }
        } catch (Exception e) {
            m.a().f().d("Downloader", "pause exception");
            com.baidu.mobads.c.a.a().a(new StringBuilder("apk download pause failed: ").append(e.toString()).toString());
        }
    }

    public void resume() {
        try {
            m.a().f().d("Downloader", new StringBuilder("execute Resume; state = ").append(this.i).toString());
            if (this.i == DownloadStatus.PAUSED || this.i == DownloadStatus.ERROR || this.i == DownloadStatus.CANCELLED) {
                a(DownloadStatus.INITING);
                new Thread(this).start();
            }
        } catch (Exception e) {
            m.a().f().d("Downloader", "resume exception");
            com.baidu.mobads.c.a.a().a(new StringBuilder("apk download resume failed: ").append(e.toString()).toString());
        }
    }

    public void cancel() {
        try {
            m.a().f().d("Downloader", new StringBuilder("execute Cancel; state = ").append(this.i).toString());
            if (this.i == DownloadStatus.PAUSED || this.i == DownloadStatus.DOWNLOADING) {
                if (this.l != null) {
                    for (int i = 0; i < this.l.size(); i++) {
                        if (!((a) this.l.get(i)).a()) {
                            ((a) this.l.get(i)).c();
                        }
                    }
                }
                a(DownloadStatus.CANCELLED);
            }
        } catch (Exception e) {
            m.a().f().d("Downloader", "cancel exception");
            com.baidu.mobads.c.a.a().a(new StringBuilder("apk download cancel failed: ").append(e.toString()).toString());
        }
    }

    public String getURL() {
        return this.b.toString();
    }

    public int getFileSize() {
        return this.h;
    }

    public float getProgress() {
        return Math.abs((((float) this.j) / ((float) this.h)) * 100.0f);
    }

    public String getTitle() {
        return this.n;
    }

    public String getPackageName() {
        return this.o;
    }

    public DownloadStatus getState() {
        return this.i;
    }

    protected void a(DownloadStatus downloadStatus) {
        this.i = downloadStatus;
        a();
    }

    public void start() {
        m.a().f().d("Downloader", new StringBuilder("execute Start; state = ").append(this.i).toString());
        if (this.i == DownloadStatus.NONE) {
            a(DownloadStatus.INITING);
            new Thread(this).start();
        }
    }

    protected synchronized void a(int i) {
        this.j += i;
        int progress = (int) getProgress();
        if (this.k < progress) {
            this.k = progress;
            a();
        }
    }

    protected void a() {
        setChanged();
        notifyObservers();
    }

    public String getOutputPath() {
        return this.d + this.g;
    }

    protected synchronized void b() {
        this.i = DownloadStatus.ERROR;
        for (int i = 0; i < this.l.size(); i++) {
            if (!((a) this.l.get(i)).a()) {
                ((a) this.l.get(i)).c();
            }
        }
    }

    protected void a(HttpURLConnection httpURLConnection) {
        Throwable th;
        RandomAccessFile randomAccessFile;
        List arrayList;
        int i;
        int round;
        int i2;
        Object obj;
        List arrayList2;
        Iterator it;
        a aVar;
        String toString = this.c.toString();
        String str = (this.d + this.g) + ".tmp";
        if (this.l.size() == 0) {
            int i3;
            a aVar2;
            File file = new File(this.d);
            if (!file.exists()) {
                file.mkdirs();
            }
            List list = null;
            File file2 = new File(str);
            if (this.f.booleanValue() && file2.exists() && file2.length() == ((long) this.h)) {
                List<h> b;
                List list2;
                try {
                    this.m = new g(this.a);
                    b = this.m.b(toString, str);
                } catch (Throwable e) {
                    Throwable th2 = e;
                    list2 = null;
                    th = th2;
                    m.a().f().d("Downloader", th);
                    list = list2;
                    if (file2.exists()) {
                        file2.delete();
                    }
                    file2.createNewFile();
                    randomAccessFile = new RandomAccessFile(file2, "rwd");
                    randomAccessFile.setLength((long) this.h);
                    randomAccessFile.close();
                    m.a().f().d(new StringBuilder("Downloader.init():  \u5efa\u7acb\u5b8crandom\u6587\u4ef6 ts:").append(System.currentTimeMillis()).toString());
                    arrayList = new ArrayList();
                    i3 = -1;
                    i = 0;
                    if (this.e > 1) {
                        arrayList.add(new h(1, toString, str, 0, this.h, 0));
                        list = arrayList;
                    } else {
                        round = Math.round((((float) this.h) / ((float) this.e)) / 102400.0f) * 102400;
                        while (i3 < this.h) {
                            i2 = i3 + 1;
                            if (i3 + round < this.h) {
                                i3 = this.h;
                            } else {
                                i3 += round;
                            }
                            i++;
                            arrayList.add(new h(i, toString, str, i2, i3, 0));
                        }
                        list = arrayList;
                    }
                    for (h hVar : r2) {
                        aVar2 = new a(hVar.c(), this.c, hVar.f(), hVar.d(), hVar.e(), hVar.a());
                        if (hVar.d() == 0) {
                        }
                        this.l.add(aVar2);
                    }
                    if (this.f.booleanValue()) {
                        i2 = 0;
                    } else {
                        i2 = 0;
                        for (i = 0; i < this.l.size(); i++) {
                            i2 += ((a) this.l.get(i)).f;
                        }
                    }
                    this.j = i2;
                    this.k = (int) getProgress();
                    a(DownloadStatus.DOWNLOADING);
                    m.a().f().d("Downloader", "Downloader starts unfinished threads and waits threads end");
                    for (i = 0; i < this.l.size(); i++) {
                        if (((a) this.l.get(i)).a()) {
                            ((a) this.l.get(i)).b();
                        }
                    }
                    for (i = 0; i < this.l.size(); i++) {
                        if (((a) this.l.get(i)).a()) {
                            ((a) this.l.get(i)).d();
                        }
                    }
                    if (this.i != DownloadStatus.DOWNLOADING) {
                        while (i < this.l.size()) {
                            if (((a) this.l.get(i)).a()) {
                                obj = 1;
                                break;
                                if (obj != null) {
                                    a(this.l);
                                    a(DownloadStatus.COMPLETED);
                                } else {
                                    a(DownloadStatus.ERROR);
                                }
                            } else {
                            }
                        }
                        obj = null;
                        if (obj != null) {
                            a(DownloadStatus.ERROR);
                        } else {
                            a(this.l);
                            a(DownloadStatus.COMPLETED);
                        }
                    } else if (this.i != DownloadStatus.ERROR) {
                        a(DownloadStatus.ERROR);
                    } else if (this.i != DownloadStatus.CANCELLED) {
                        m.a().f().d("Downloader", "Downloader is cancelled");
                    } else if (this.i == DownloadStatus.PAUSED) {
                        m.a().f().d("Downloader", "Downloader is paused");
                    }
                    if (this.i == DownloadStatus.COMPLETED) {
                        m.a().f().d("Downloader", "save database now");
                        if (!this.f.booleanValue()) {
                            if (this.m == null) {
                                this.m = new g(this.a);
                            }
                            arrayList2 = new ArrayList();
                            it = this.l.iterator();
                            while (it.hasNext()) {
                                aVar = (a) it.next();
                                arrayList2.add(new h(aVar.a, toString, str, aVar.d, aVar.e, aVar.f));
                                m.a().f().d("Downloader", new StringBuilder("save to db: start=").append(aVar.d).append(";end =").append(aVar.e).append(";complete=").append(aVar.f).toString());
                            }
                            if (this.m.a(toString, str)) {
                                this.m.a(arrayList2);
                            } else {
                                this.m.b(arrayList2);
                            }
                        }
                    }
                }
                if (b != null && b.size() > 0) {
                    list2 = new ArrayList();
                    try {
                        HashSet hashSet = new HashSet();
                        for (h hVar2 : b) {
                            if (!hashSet.contains(Integer.valueOf(hVar2.c()))) {
                                hashSet.add(Integer.valueOf(hVar2.c()));
                                list2.add(hVar2);
                                m.a().f().d("Downloader", new StringBuilder("resume from db: start=").append(hVar2.d()).append(";end =").append(hVar2.e()).append(";complete=").append(hVar2.a()).toString());
                            }
                        }
                    } catch (Exception e2) {
                        th = e2;
                        m.a().f().d("Downloader", th);
                        list = list2;
                        if (file2.exists()) {
                            file2.delete();
                        }
                        file2.createNewFile();
                        randomAccessFile = new RandomAccessFile(file2, "rwd");
                        randomAccessFile.setLength((long) this.h);
                        randomAccessFile.close();
                        m.a().f().d(new StringBuilder("Downloader.init():  \u5efa\u7acb\u5b8crandom\u6587\u4ef6 ts:").append(System.currentTimeMillis()).toString());
                        arrayList = new ArrayList();
                        i3 = -1;
                        i = 0;
                        if (this.e > 1) {
                            round = Math.round((((float) this.h) / ((float) this.e)) / 102400.0f) * 102400;
                            while (i3 < this.h) {
                                i2 = i3 + 1;
                                if (i3 + round < this.h) {
                                    i3 += round;
                                } else {
                                    i3 = this.h;
                                }
                                i++;
                                arrayList.add(new h(i, toString, str, i2, i3, 0));
                            }
                            list = arrayList;
                        } else {
                            arrayList.add(new h(1, toString, str, 0, this.h, 0));
                            list = arrayList;
                        }
                        for (h hVar22 : r2) {
                            aVar2 = new a(hVar22.c(), this.c, hVar22.f(), hVar22.d(), hVar22.e(), hVar22.a());
                            if (hVar22.d() == 0) {
                            }
                            this.l.add(aVar2);
                        }
                        if (this.f.booleanValue()) {
                            i2 = 0;
                            for (i = 0; i < this.l.size(); i++) {
                                i2 += ((a) this.l.get(i)).f;
                            }
                        } else {
                            i2 = 0;
                        }
                        this.j = i2;
                        this.k = (int) getProgress();
                        a(DownloadStatus.DOWNLOADING);
                        m.a().f().d("Downloader", "Downloader starts unfinished threads and waits threads end");
                        for (i = 0; i < this.l.size(); i++) {
                            if (((a) this.l.get(i)).a()) {
                                ((a) this.l.get(i)).b();
                            }
                        }
                        for (i = 0; i < this.l.size(); i++) {
                            if (((a) this.l.get(i)).a()) {
                                ((a) this.l.get(i)).d();
                            }
                        }
                        if (this.i != DownloadStatus.DOWNLOADING) {
                            while (i < this.l.size()) {
                                if (((a) this.l.get(i)).a()) {
                                    obj = 1;
                                    break;
                                    if (obj != null) {
                                        a(this.l);
                                        a(DownloadStatus.COMPLETED);
                                    } else {
                                        a(DownloadStatus.ERROR);
                                    }
                                } else {
                                }
                            }
                            obj = null;
                            if (obj != null) {
                                a(DownloadStatus.ERROR);
                            } else {
                                a(this.l);
                                a(DownloadStatus.COMPLETED);
                            }
                        } else if (this.i != DownloadStatus.ERROR) {
                            a(DownloadStatus.ERROR);
                        } else if (this.i != DownloadStatus.CANCELLED) {
                            m.a().f().d("Downloader", "Downloader is cancelled");
                        } else if (this.i == DownloadStatus.PAUSED) {
                            m.a().f().d("Downloader", "Downloader is paused");
                        }
                        if (this.i == DownloadStatus.COMPLETED) {
                            m.a().f().d("Downloader", "save database now");
                            if (!this.f.booleanValue()) {
                                if (this.m == null) {
                                    this.m = new g(this.a);
                                }
                                arrayList2 = new ArrayList();
                                it = this.l.iterator();
                                while (it.hasNext()) {
                                    aVar = (a) it.next();
                                    arrayList2.add(new h(aVar.a, toString, str, aVar.d, aVar.e, aVar.f));
                                    m.a().f().d("Downloader", new StringBuilder("save to db: start=").append(aVar.d).append(";end =").append(aVar.e).append(";complete=").append(aVar.f).toString());
                                }
                                if (this.m.a(toString, str)) {
                                    this.m.b(arrayList2);
                                } else {
                                    this.m.a(arrayList2);
                                }
                            }
                        }
                    }
                    list = list2;
                }
            }
            if (list == null || list.size() <= 0) {
                if (file2.exists()) {
                    file2.delete();
                }
                try {
                    file2.createNewFile();
                    randomAccessFile = new RandomAccessFile(file2, "rwd");
                    randomAccessFile.setLength((long) this.h);
                    randomAccessFile.close();
                    m.a().f().d(new StringBuilder("Downloader.init():  \u5efa\u7acb\u5b8crandom\u6587\u4ef6 ts:").append(System.currentTimeMillis()).toString());
                    arrayList = new ArrayList();
                    i3 = -1;
                    i = 0;
                    if (this.e > 1) {
                        round = Math.round((((float) this.h) / ((float) this.e)) / 102400.0f) * 102400;
                        while (i3 < this.h) {
                            i2 = i3 + 1;
                            if (i3 + round < this.h) {
                                i3 += round;
                            } else {
                                i3 = this.h;
                            }
                            i++;
                            arrayList.add(new h(i, toString, str, i2, i3, 0));
                        }
                        list = arrayList;
                    } else {
                        arrayList.add(new h(1, toString, str, 0, this.h, 0));
                        list = arrayList;
                    }
                } catch (Exception e3) {
                    m.a().f().d("Downloader", " \u5efa\u7acb\u6587\u4ef6\u5931\u8d25:");
                    a(DownloadStatus.ERROR);
                }
            }
            for (h hVar222 : r2) {
                aVar2 = new a(hVar222.c(), this.c, hVar222.f(), hVar222.d(), hVar222.e(), hVar222.a());
                if (hVar222.d() == 0 && hVar222.a() == 0) {
                    aVar2.a(httpURLConnection);
                }
                this.l.add(aVar2);
            }
        }
        if (this.f.booleanValue()) {
            i2 = 0;
            for (i = 0; i < this.l.size(); i++) {
                i2 += ((a) this.l.get(i)).f;
            }
        } else {
            i2 = 0;
        }
        this.j = i2;
        this.k = (int) getProgress();
        a(DownloadStatus.DOWNLOADING);
        m.a().f().d("Downloader", "Downloader starts unfinished threads and waits threads end");
        for (i = 0; i < this.l.size(); i++) {
            if (((a) this.l.get(i)).a()) {
                ((a) this.l.get(i)).b();
            }
        }
        for (i = 0; i < this.l.size(); i++) {
            if (((a) this.l.get(i)).a()) {
                ((a) this.l.get(i)).d();
            }
        }
        if (this.i != DownloadStatus.DOWNLOADING) {
            for (i = 0; i < this.l.size(); i++) {
                if (((a) this.l.get(i)).a()) {
                    obj = 1;
                    break;
                }
            }
            obj = null;
            if (obj != null) {
                a(DownloadStatus.ERROR);
            } else {
                a(this.l);
                a(DownloadStatus.COMPLETED);
            }
        } else if (this.i != DownloadStatus.ERROR) {
            a(DownloadStatus.ERROR);
        } else if (this.i != DownloadStatus.CANCELLED) {
            m.a().f().d("Downloader", "Downloader is cancelled");
        } else if (this.i == DownloadStatus.PAUSED) {
            m.a().f().d("Downloader", "Downloader is paused");
        }
        if (this.i == DownloadStatus.COMPLETED) {
            m.a().f().d("Downloader", "save database now");
            if (!this.f.booleanValue()) {
                try {
                    if (this.m == null) {
                        this.m = new g(this.a);
                    }
                    arrayList2 = new ArrayList();
                    it = this.l.iterator();
                    while (it.hasNext()) {
                        aVar = (a) it.next();
                        arrayList2.add(new h(aVar.a, toString, str, aVar.d, aVar.e, aVar.f));
                        m.a().f().d("Downloader", new StringBuilder("save to db: start=").append(aVar.d).append(";end =").append(aVar.e).append(";complete=").append(aVar.f).toString());
                    }
                    if (this.m.a(toString, str)) {
                        this.m.b(arrayList2);
                    } else {
                        this.m.a(arrayList2);
                    }
                } catch (Throwable th3) {
                    m.a().f().d("Downloader", th3);
                }
            }
        }
    }

    public void run() {
        Throwable th;
        HttpURLConnection httpURLConnection;
        Throwable th2;
        HttpURLConnection httpURLConnection2 = null;
        if (this.c == null || this.h <= 0) {
            try {
                httpURLConnection2 = m.a().i().getHttpURLConnection(this.b);
                try {
                    httpURLConnection2.setRequestProperty("Range", "bytes=0-");
                    httpURLConnection2.setConnectTimeout(10000);
                    if (AdSettings.getSupportHttps().equals(b.c.a())) {
                        httpURLConnection2.setInstanceFollowRedirects(false);
                        httpURLConnection2 = b(httpURLConnection2);
                    } else {
                        httpURLConnection2.setInstanceFollowRedirects(true);
                        httpURLConnection2.connect();
                    }
                    if (httpURLConnection2.getResponseCode() / 100 != 2) {
                        a(DownloadStatus.ERROR);
                        if (httpURLConnection2 == null) {
                            return;
                        }
                    } else if (httpURLConnection2.getContentType().equals("text/html")) {
                        a(DownloadStatus.ERROR);
                        if (httpURLConnection2 == null) {
                            return;
                        }
                    } else {
                        int contentLength = httpURLConnection2.getContentLength();
                        if (contentLength <= 0) {
                            a(DownloadStatus.ERROR);
                            if (httpURLConnection2 == null) {
                                return;
                            }
                        } else {
                            if (contentLength < 5120000) {
                                this.e = 1;
                            }
                            this.c = httpURLConnection2.getURL();
                            if ("mounted".equals(Environment.getExternalStorageState())) {
                                if (httpURLConnection2.getHeaderField("Content-Range") == null) {
                                    if (httpURLConnection2.getHeaderField("Accept-Ranges") == null || httpURLConnection2.getHeaderField("Accept-Ranges").equalsIgnoreCase(IXAdSystemUtils.NT_NONE)) {
                                        this.f = Boolean.valueOf(false);
                                        this.e = 1;
                                    }
                                }
                                if (this.h == -1) {
                                    this.h = contentLength;
                                }
                                a(httpURLConnection2);
                                if (httpURLConnection2 == null) {
                                    return;
                                }
                            } else {
                                a(DownloadStatus.ERROR);
                                if (httpURLConnection2 == null) {
                                    return;
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    try {
                        a(DownloadStatus.ERROR);
                        if (httpURLConnection2 == null) {
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        httpURLConnection = httpURLConnection2;
                        th2 = th;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        throw th2;
                    }
                }
            } catch (Exception e2) {
                try {
                    a(DownloadStatus.ERROR);
                    if (httpURLConnection2 == null) {
                    }
                } catch (Throwable th32) {
                    th = th32;
                    httpURLConnection = httpURLConnection2;
                    th2 = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th2;
                }
            } catch (Throwable th322) {
                th = th322;
                httpURLConnection = null;
                th2 = th;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th2;
            }
            httpURLConnection2.disconnect();
            return;
        }
        try {
            a(null);
        } catch (Throwable th22) {
            a(DownloadStatus.ERROR);
            m.a().f().d("Downloader", th22);
        }
    }

    protected void a(ArrayList<a> arrayList) {
        m.a().k().renameFile(this.d + this.g + ".tmp", this.d + this.g);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.net.HttpURLConnection b(java.net.HttpURLConnection r5) {
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.mobads.openad.c.a.b(java.net.HttpURLConnection):java.net.HttpURLConnection");
        /*
        this = this;
        r1 = r5;
    L_0x0001:
        r0 = r1.getResponseCode();	 Catch:{ Exception -> 0x0039 }
        r2 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r0 == r2) goto L_0x000d;
    L_0x0009:
        r2 = 301; // 0x12d float:4.22E-43 double:1.487E-321;
        if (r0 != r2) goto L_0x0037;
    L_0x000d:
        r0 = "Location";
        r0 = r1.getHeaderField(r0);	 Catch:{ Exception -> 0x0039 }
        r2 = new java.net.URL;	 Catch:{ Exception -> 0x0039 }
        r2.<init>(r0);	 Catch:{ Exception -> 0x0039 }
        r4.b = r2;	 Catch:{ Exception -> 0x0039 }
        r0 = r4.b;	 Catch:{ Exception -> 0x0039 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x0039 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0039 }
        r1 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r0.setConnectTimeout(r1);	 Catch:{ Exception -> 0x0041 }
        r1 = 0;
        r0.setInstanceFollowRedirects(r1);	 Catch:{ Exception -> 0x0041 }
        r1 = "Range";
        r2 = "bytes=0-";
        r0.setRequestProperty(r1, r2);	 Catch:{ Exception -> 0x0041 }
        r1 = r0;
        goto L_0x0001;
    L_0x0037:
        r0 = r1;
    L_0x0038:
        return r0;
    L_0x0039:
        r0 = move-exception;
        r3 = r0;
        r0 = r1;
        r1 = r3;
    L_0x003d:
        r1.printStackTrace();
        goto L_0x0038;
    L_0x0041:
        r1 = move-exception;
        goto L_0x003d;
        */
    }

    public void removeObservers() {
        deleteObservers();
    }

    public String getTargetURL() {
        return this.c == null ? null : this.c.toString();
    }
}
