package com.xunlei.downloadprovider.filemanager.model;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video.Media;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.a.c;
import com.xunlei.downloadprovider.a.h;
import com.xunlei.downloadprovider.a.j;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.filemanager.ui.g.a;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.android.spdy.SpdyAgent;

// compiled from: FileManagerUtil.java
public final class b {
    public static final int a;
    public static final int b;
    public static final int c;
    public static final int d;
    public static final EFileCategoryType[] e;
    public static final String[] f;
    public static final List<Object> g;

    static /* synthetic */ void c(List list, Handler handler) {
        List arrayList = new ArrayList();
        Context a = BrothersApplication.a();
        for (int i = 0; i < list.size(); i++) {
            i iVar = (i) list.get(i);
            a(iVar);
            File file = new File(iVar.g);
            boolean z = true;
            if (file.exists()) {
                z = file.delete();
            }
            if (z) {
                arrayList.add(iVar);
                a(iVar, a);
                handler.obtainMessage(a, i, list.size(), iVar).sendToTarget();
                for (int i2 = 0; i2 < g.size(); i2++) {
                    g.get(i2);
                }
            } else {
                handler.obtainMessage(b, i, list.size(), iVar).sendToTarget();
            }
        }
        a.a().a(arrayList);
        handler.obtainMessage(c, list).sendToTarget();
    }

    static {
        a = h.a();
        b = h.a();
        c = h.a();
        d = h.a();
        e = new EFileCategoryType[]{EFileCategoryType.E_VIDEO_CATEGORY, EFileCategoryType.E_PICTURE_CATEGORY, EFileCategoryType.E_MUSIC_CATEGORY, EFileCategoryType.E_BOOK_CATEGORY, EFileCategoryType.E_ZIP_CATEGORY, EFileCategoryType.E_SOFTWARE_CATEGORY, EFileCategoryType.E_TORRENT_CATEGORY, EFileCategoryType.E_XLFILES_DOWNLOAD_CATEGORY, EFileCategoryType.E_XLFILES_PC_TRANSFER_CATEGORY};
        f = new String[]{"VIDEO", "PICTURE", "MUSIC", "DOC", "ZIP", "SOFTWARE", "TORRENT", "APPLICATION", "PC_TRANSFER_DOWNLOAD"};
        g = new ArrayList();
    }

    public static final String a(int i) {
        for (int i2 = 0; i2 < e.length; i2++) {
            if (e[i2].ordinal() == i) {
                return f[i2];
            }
        }
        return null;
    }

    public static int a(Collection collection) {
        if (collection == null) {
            return 0;
        }
        int i = 0;
        for (SelectableItem selectableItem : collection) {
            if (selectableItem.a) {
                i++;
            }
        }
        return i;
    }

    public static void b(Collection collection) {
        if (collection != null) {
            for (SelectableItem selectableItem : collection) {
                selectableItem.a = false;
            }
        }
    }

    public static void a(SelectableItem selectableItem) {
        selectableItem.a = !selectableItem.a;
    }

    public static List c(Collection collection) {
        List arrayList = new ArrayList();
        for (SelectableItem selectableItem : collection) {
            if (selectableItem.a) {
                arrayList.add(selectableItem);
            }
        }
        return arrayList;
    }

    public static int a(List<i> list, Handler handler) {
        new c(list, handler).start();
        return 0;
    }

    public static int b(List<i> list, Handler handler) {
        new d(list, handler).start();
        return 0;
    }

    public static final boolean a(a aVar, Handler handler) {
        new e(aVar, handler).start();
        return true;
    }

    public static final boolean b(a aVar, Handler handler) {
        new f(aVar, handler).start();
        return true;
    }

    private static final boolean a(String str, String str2) {
        Context a = BrothersApplication.a();
        a.a().a(str, str2);
        boolean a2 = a(a, str, str2);
        for (int i = 0; i < g.size(); i++) {
            g.get(i);
            new i().a(str);
            new i().a(str2);
        }
        return a2;
    }

    public static final void a(List<String> list) {
        new g(list).start();
    }

    private static boolean a(i iVar, Context context) {
        boolean z;
        Uri uri = null;
        String replace = iVar.g.replace("'", "''");
        switch (AnonymousClass_1.a[iVar.d().ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                replace = new StringBuilder("_data='").append(replace).append("'").toString();
                uri = Media.EXTERNAL_CONTENT_URI;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                replace = new StringBuilder("_data='").append(replace).append("'").toString();
                uri = Audio.Media.EXTERNAL_CONTENT_URI;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                replace = new StringBuilder("_data='").append(replace).append("'").toString();
                uri = Images.Media.EXTERNAL_CONTENT_URI;
                break;
            default:
                replace = null;
                break;
        }
        if (uri != null) {
            try {
                if (context.getContentResolver().delete(uri, replace, null) > 0) {
                    Object obj = 1;
                } else {
                    z = false;
                }
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
                return false;
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        z = false;
        return z;
    }

    private static boolean a(Context context, String str, String str2) {
        String str3;
        Uri uri = null;
        int i = 1;
        EFileCategoryType a = XLFileTypeUtil.a(str);
        ContentValues contentValues = new ContentValues();
        String[] strArr = new String[]{str};
        switch (AnonymousClass_1.a[a.ordinal()]) {
            case SpdyAgent.ACCS_ONLINE_SERVER:
                contentValues.put(Impl._DATA, str2);
                str3 = "_data=?";
                uri = Media.EXTERNAL_CONTENT_URI;
                break;
            case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                contentValues.put(Impl._DATA, str2);
                str3 = "_data=?";
                uri = Audio.Media.EXTERNAL_CONTENT_URI;
                break;
            case XZBDevice.DOWNLOAD_LIST_FAILED:
                contentValues.put(Impl._DATA, str2);
                str3 = "_data=?";
                uri = Images.Media.EXTERNAL_CONTENT_URI;
                break;
            default:
                str3 = null;
                break;
        }
        if (uri != null) {
            try {
                if (context.getContentResolver().update(uri, contentValues, str3, strArr) <= 0) {
                    i = 0;
                }
            } catch (UnsupportedOperationException e) {
                e.printStackTrace();
                return false;
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        i = 0;
        return r0;
    }

    public static void a(String str, List<i> list) {
        File file = new File(str);
        if (file.isDirectory()) {
            String[] list2 = file.list();
            if (list2 != null) {
                for (String str2 : list2) {
                    if (!str.endsWith("/")) {
                        str = str + "/";
                    }
                    a(str + str2, (List) list);
                }
            }
            i iVar = new i();
            iVar.a(str);
            list.add(iVar);
            return;
        }
        iVar = new i();
        iVar.a(str);
        list.add(iVar);
    }

    private static void a(i iVar) {
        if (iVar != null && iVar.d() == EFileCategoryType.E_SOFTWARE_CATEGORY) {
            c.a a = c.a(BrothersApplication.a, iVar.g);
            if (a != null && Looper.myLooper() == Looper.getMainLooper()) {
                com.xunlei.downloadprovider.notification.a.a(BrothersApplication.a).a(j.a(BrothersApplication.a, "clearApkNotification", a.c()));
            }
        }
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        Object c = k.c();
        return (TextUtils.isEmpty(c) || !str.startsWith(c)) && str.startsWith(k.b());
    }

    static /* synthetic */ void a(List list, List list2) {
        Context a = BrothersApplication.a();
        a.a().a(list, list2);
        for (int i = 0; i < list.size(); i++) {
            String str = (String) list.get(i);
            String str2 = (String) list2.get(i);
            EFileCategoryType a2 = XLFileTypeUtil.a(str);
            ContentValues contentValues = new ContentValues();
            String[] strArr = new String[]{str};
            switch (AnonymousClass_1.a[a2.ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    contentValues.put(Impl._DATA, str2);
                    a.getContentResolver().update(Media.EXTERNAL_CONTENT_URI, contentValues, "_data=?", strArr);
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    contentValues.put(Impl._DATA, str2);
                    a.getContentResolver().delete(Audio.Media.EXTERNAL_CONTENT_URI, "_data=?", strArr);
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    contentValues.put(Impl._DATA, str2);
                    a.getContentResolver().delete(Images.Media.EXTERNAL_CONTENT_URI, "_data=?", strArr);
                    break;
            }
            for (int i2 = 0; i2 < g.size(); i2++) {
                g.get(i2);
                new i().a(str);
                new i().a(str2);
            }
        }
    }

    static /* synthetic */ boolean c(a aVar, Handler handler) {
        a(aVar.d);
        boolean renameTo = new File(aVar.b).renameTo(new File(aVar.c));
        if (renameTo) {
            a(aVar.b, aVar.c);
        } else {
            handler.obtainMessage(d, -1, 0, aVar);
        }
        return renameTo;
    }

    static /* synthetic */ void b(List list) {
        Context a = BrothersApplication.a();
        a.a().b(list);
        for (int i = 0; i < list.size(); i++) {
            Uri uri;
            i iVar = (i) list.get(i);
            ContentValues contentValues = new ContentValues();
            String mimeTypeFromExtension = MimeTypeMap.getSingleton().getMimeTypeFromExtension(com.xunlei.downloadprovider.d.c.c(iVar.g));
            switch (AnonymousClass_1.a[iVar.d().ordinal()]) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    contentValues.put("_size", Long.valueOf(iVar.i));
                    contentValues.put("_display_name", iVar.a());
                    contentValues.put("date_modified", Long.valueOf(iVar.h));
                    contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
                    contentValues.put(WebBrowserActivity.EXTRA_TITLE, iVar.g);
                    contentValues.put("mime_type", mimeTypeFromExtension);
                    contentValues.put(Impl._DATA, iVar.g);
                    uri = Media.EXTERNAL_CONTENT_URI;
                    break;
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    contentValues.put("_size", Long.valueOf(iVar.i));
                    contentValues.put("_display_name", iVar.a());
                    contentValues.put("date_modified", Long.valueOf(iVar.h));
                    contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
                    contentValues.put(WebBrowserActivity.EXTRA_TITLE, iVar.g);
                    contentValues.put("mime_type", mimeTypeFromExtension);
                    contentValues.put(Impl._DATA, iVar.g);
                    uri = Audio.Media.EXTERNAL_CONTENT_URI;
                    break;
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    contentValues.put("_size", Long.valueOf(iVar.i));
                    contentValues.put("_display_name", iVar.a());
                    contentValues.put("date_modified", Long.valueOf(iVar.h));
                    contentValues.put("date_added", Long.valueOf(System.currentTimeMillis()));
                    contentValues.put(WebBrowserActivity.EXTRA_TITLE, iVar.g);
                    contentValues.put("mime_type", mimeTypeFromExtension);
                    contentValues.put(Impl._DATA, iVar.g);
                    uri = Images.Media.EXTERNAL_CONTENT_URI;
                    break;
                default:
                    uri = null;
                    break;
            }
            if (uri != null) {
                try {
                    a.getContentResolver().insert(uri, contentValues);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
