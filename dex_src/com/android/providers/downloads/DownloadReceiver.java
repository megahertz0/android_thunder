package com.android.providers.downloads;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.widget.Toast;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.download.proguard.ag;
import com.xunlei.download.proguard.am;
import com.xunlei.download.proguard.p;
import com.xunlei.download.proguard.q;
import com.xunlei.download.proguard.u;
import java.util.List;

public class DownloadReceiver extends BroadcastReceiver {
    private static final String b = "DownloadReceiver";
    private static Handler c;
    u a;

    public DownloadReceiver() {
        this.a = null;
    }

    static {
        HandlerThread handlerThread = new HandlerThread(b);
        handlerThread.start();
        c = new Handler(handlerThread.getLooper());
    }

    public void onReceive(Context context, Intent intent) {
        if (this.a == null) {
            this.a = new q(context);
        }
        String action = intent.getAction();
        if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
            a(context);
        } else if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
            a(context);
        } else if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            am.e(DownloadManager.LOG_TAG, new StringBuilder("network changed. ").append(activeNetworkInfo).toString());
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                a(context);
            }
        } else if (!"android.intent.action.UID_REMOVED".equals(action)) {
            if ("android.intent.action.DOWNLOAD_WAKEUP".equals(action)) {
                a(context);
            } else if ("android.intent.action.DOWNLOAD_OPEN".equals(action) || "android.intent.action.DOWNLOAD_LIST".equals(action) || "android.intent.action.DOWNLOAD_HIDE".equals(action)) {
                b(context, intent);
            }
        }
    }

    private void a(Context context, Intent intent) {
        ContentResolver contentResolver = context.getContentResolver();
        int intExtra = intent.getIntExtra("android.intent.extra.UID", -1);
        try {
            int delete = contentResolver.delete(DownloadManager.getInstanceFor(context).getDownloadUri(), new StringBuilder("uid=").append(intExtra).toString(), null);
        } catch (Throwable e) {
            e.printStackTrace();
            am.a(e);
            delete = 0;
        }
        if (delete > 0) {
            am.b(b, new StringBuilder("Deleted ").append(delete).append(" downloads owned by UID ").append(intExtra).toString());
        }
    }

    private void b(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.DOWNLOAD_LIST".equals(action)) {
            a(context, intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS));
        } else if ("android.intent.action.DOWNLOAD_OPEN".equals(action)) {
            long parseId = ContentUris.parseId(intent.getData());
            b(context, parseId);
            a(context, parseId);
        } else if ("android.intent.action.DOWNLOAD_HIDE".equals(action)) {
            a(context, ContentUris.parseId(intent.getData()));
        }
    }

    private void a(Context context, long j) {
        Throwable th;
        Throwable e;
        int i;
        int i2;
        Uri downloadUri = DownloadManager.getInstanceFor(context).getDownloadUri(j);
        try {
            ContentValues contentValues;
            Cursor query = context.getContentResolver().query(downloadUri, null, null, null, null);
            try {
            } catch (Throwable e2) {
                th = e2;
                i = 190;
                th.printStackTrace();
                am.a(th);
                if (query != null) {
                    query.close();
                }
                i2 = i;
                i = 1;
                if (Impl.isStatusCompleted(i2)) {
                    if (i != 1) {
                    }
                    contentValues = new ContentValues();
                    contentValues.put(Impl.COLUMN_VISIBILITY, Integer.valueOf(0));
                    context.getContentResolver().update(downloadUri, contentValues, null, null);
                }
            }
            if (query.moveToFirst()) {
                i2 = b(query, Impl.COLUMN_STATUS);
                try {
                    i = b(query, Impl.COLUMN_VISIBILITY);
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable e22) {
                    Throwable th2 = e22;
                    i = i2;
                    th = th2;
                    th.printStackTrace();
                    am.a(th);
                    if (query != null) {
                        query.close();
                    }
                    i2 = i;
                    i = 1;
                    if (Impl.isStatusCompleted(i2)) {
                        if (i != 1) {
                        }
                        contentValues = new ContentValues();
                        contentValues.put(Impl.COLUMN_VISIBILITY, Integer.valueOf(0));
                        context.getContentResolver().update(downloadUri, contentValues, null, null);
                    }
                }
                if (Impl.isStatusCompleted(i2)) {
                }
                if (i != 1 || i == 3) {
                    contentValues = new ContentValues();
                    contentValues.put(Impl.COLUMN_VISIBILITY, Integer.valueOf(0));
                    try {
                        context.getContentResolver().update(downloadUri, contentValues, null, null);
                    } catch (Throwable e222) {
                        e222.printStackTrace();
                        am.a(e222);
                    }
                }
                return;
            }
            am.c(b, new StringBuilder("Missing details for download ").append(j).toString());
            if (query != null) {
                query.close();
            }
        } catch (Throwable e2222) {
            th = e2222;
            query = null;
            i = 190;
            try {
                th.printStackTrace();
                am.a(th);
                if (query != null) {
                    query.close();
                }
                i2 = i;
                i = 1;
            } catch (Throwable th3) {
                e2222 = th3;
                if (query != null) {
                    query.close();
                }
                throw e2222;
            }
            if (Impl.isStatusCompleted(i2)) {
                if (i != 1) {
                }
                contentValues = new ContentValues();
                contentValues.put(Impl.COLUMN_VISIBILITY, Integer.valueOf(0));
                context.getContentResolver().update(downloadUri, contentValues, null, null);
            }
        } catch (Throwable th4) {
            e2222 = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e2222;
        }
    }

    private void b(Context context, long j) {
        if (!p.a(context, j, 268435456)) {
            Toast.makeText(context, ag.a(context).d("download_no_application_title"), 0).show();
        }
    }

    private void a(Context context, long[] jArr) {
        Throwable e;
        String str;
        Intent intent;
        Intent intent2;
        Uri withAppendedId = ContentUris.withAppendedId(DownloadManager.getInstanceFor(context).getDownloadUri(), jArr[0]);
        try {
            List queryBroadcastReceivers;
            ResolveInfo resolveActivity;
            Cursor query = context.getContentResolver().query(withAppendedId, null, null, null, null);
            try {
            } catch (Throwable e2) {
                r2 = e2;
                Object obj = null;
                r2.printStackTrace();
                am.a(r2);
                if (query != null) {
                    query.close();
                }
                str = r0;
                r0 = null;
                if (TextUtils.isEmpty(str)) {
                    if (TextUtils.isEmpty(r0)) {
                        intent = new Intent(Impl.ACTION_NOTIFICATION_CLICKED);
                        queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent.setPackage(str), 0);
                        if (queryBroadcastReceivers != null) {
                        }
                        resolveActivity = context.getPackageManager().resolveActivity(new Intent("android.intent.action.MAIN").setPackage(str), 0);
                        if (resolveActivity != null) {
                            r0 = resolveActivity.activityInfo.name;
                        }
                    }
                    intent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                    intent.setClassName(str, r0);
                    intent.putExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, jArr);
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                    return;
                }
                am.c(b, "Missing package; skipping broadcast");
            }
            if (query.moveToFirst()) {
                str = a(query, Impl.COLUMN_NOTIFICATION_PACKAGE);
                try {
                    r0 = a(query, Impl.COLUMN_NOTIFICATION_CLASS);
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable e22) {
                    Throwable th = e22;
                    r0 = str;
                    r2 = th;
                    r2.printStackTrace();
                    am.a(r2);
                    if (query != null) {
                        query.close();
                    }
                    str = r0;
                    r0 = null;
                    if (TextUtils.isEmpty(str)) {
                        if (TextUtils.isEmpty(r0)) {
                            intent = new Intent(Impl.ACTION_NOTIFICATION_CLICKED);
                            queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent.setPackage(str), 0);
                            if (queryBroadcastReceivers != null) {
                            }
                            resolveActivity = context.getPackageManager().resolveActivity(new Intent("android.intent.action.MAIN").setPackage(str), 0);
                            if (resolveActivity != null) {
                                r0 = resolveActivity.activityInfo.name;
                            }
                        }
                        intent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                        intent.setClassName(str, r0);
                        intent.putExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, jArr);
                        intent.setFlags(268435456);
                        context.startActivity(intent);
                        return;
                    }
                    am.c(b, "Missing package; skipping broadcast");
                }
                if (TextUtils.isEmpty(str)) {
                    am.c(b, "Missing package; skipping broadcast");
                }
                if (TextUtils.isEmpty(r0)) {
                    intent = new Intent(Impl.ACTION_NOTIFICATION_CLICKED);
                    queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent.setPackage(str), 0);
                    if (queryBroadcastReceivers != null || queryBroadcastReceivers.isEmpty()) {
                        try {
                            resolveActivity = context.getPackageManager().resolveActivity(new Intent("android.intent.action.MAIN").setPackage(str), 0);
                            if (resolveActivity != null) {
                                r0 = resolveActivity.activityInfo.name;
                            }
                        } catch (Throwable e222) {
                            e222.printStackTrace();
                            am.a(e222);
                        }
                    }
                    intent.setClassName(str, ((ResolveInfo) queryBroadcastReceivers.get(0)).activityInfo.name);
                    intent.putExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, jArr);
                    context.sendBroadcast(intent);
                    return;
                }
                try {
                    intent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                    intent.setClassName(str, r0);
                    intent.putExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, jArr);
                    intent.setFlags(268435456);
                    context.startActivity(intent);
                    return;
                } catch (Throwable e2222) {
                    e2222.printStackTrace();
                    am.a(e2222);
                    intent2 = new Intent(Impl.ACTION_NOTIFICATION_CLICKED);
                    intent2.setPackage(str);
                    intent2.putExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, jArr);
                    if (jArr.length == 1) {
                        intent2.setData(withAppendedId);
                    } else {
                        intent2.setData(DownloadManager.getInstanceFor(context).getDownloadUri());
                    }
                    this.a.a(intent2);
                }
            }
            am.c(b, new StringBuilder("Missing details for download ").append(jArr[0]).toString());
            if (query != null) {
                query.close();
            }
        } catch (Throwable e22222) {
            r2 = e22222;
            query = null;
            obj = null;
            try {
                Throwable th2;
                String str2;
                th2.printStackTrace();
                am.a(th2);
                if (query != null) {
                    query.close();
                }
                str = str2;
                str2 = null;
            } catch (Throwable th3) {
                e22222 = th3;
                if (query != null) {
                    query.close();
                }
                throw e22222;
            }
            if (TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(str2)) {
                    intent = new Intent(Impl.ACTION_NOTIFICATION_CLICKED);
                    queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent.setPackage(str), 0);
                    if (queryBroadcastReceivers != null) {
                    }
                    resolveActivity = context.getPackageManager().resolveActivity(new Intent("android.intent.action.MAIN").setPackage(str), 0);
                    if (resolveActivity != null) {
                        str2 = resolveActivity.activityInfo.name;
                    }
                }
                intent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
                intent.setClassName(str, str2);
                intent.putExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS, jArr);
                intent.setFlags(268435456);
                context.startActivity(intent);
                return;
            }
            am.c(b, "Missing package; skipping broadcast");
        } catch (Throwable th4) {
            e22222 = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e22222;
        }
    }

    private static String a(Cursor cursor, String str) {
        return cursor.getString(cursor.getColumnIndexOrThrow(str));
    }

    private static int b(Cursor cursor, String str) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(str));
    }

    private void a(Context context) {
        context.startService(new Intent(context, DownloadService.class));
    }
}
