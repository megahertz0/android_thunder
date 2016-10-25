package com.xunlei.downloadprovider.model;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;
import com.umeng.a;
import com.xunlei.download.DownloadManager;
import com.xunlei.download.DownloadManager.Request;
import com.xunlei.download.DownloadManager.TaskImportRequest;
import com.xunlei.downloadlib.XLDownloadManager;
import com.xunlei.downloadlib.parameter.TorrentInfo;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;

// compiled from: DownloadEnginDbUpdate.java
public final class e {
    private String a;
    private Context b;

    public e(Context context) {
        this.a = getClass().getSimpleName();
        this.b = context;
    }

    private static File a(String str, String str2) {
        String str3 = ".td";
        File file = new File(str2 + str + str3);
        File file2 = new File(str2 + str);
        if (file2.isDirectory()) {
            File[] listFiles = file2.listFiles();
            int length = listFiles.length;
            for (int i = 0; i < length; i++) {
                File file3 = listFiles[i];
                if (file3.getName().endsWith(str3)) {
                    String absolutePath = file3.getAbsolutePath();
                    file3.renameTo(new File(absolutePath.substring(0, absolutePath.indexOf(str3))));
                }
            }
        } else if (file.exists()) {
            file.renameTo(file2);
        }
        return file2;
    }

    static /* synthetic */ void a(e eVar, File file) {
        File file2 = new File(file, "etm_task_store.db");
        new StringBuilder("import old database: ").append(file2.getAbsolutePath());
        SQLiteDatabase openDatabase = SQLiteDatabase.openDatabase(file2.getAbsolutePath(), null, 0);
        Cursor rawQuery = openDatabase.rawQuery("select url, file_name, file_path, download_data_size, file_size, type, state, seed_path from task_info,task_info_ext WHERE task_info.task_id = task_info_ext.task_id;", null);
        while (rawQuery.moveToNext()) {
            Uri fromFile;
            Object obj;
            String string = rawQuery.getString(0);
            String trim = rawQuery.getString(1).trim();
            String string2 = rawQuery.getString(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            long j = rawQuery.getLong(XZBDevice.DOWNLOAD_LIST_FAILED);
            long j2 = rawQuery.getLong(XZBDevice.DOWNLOAD_LIST_ALL);
            int i = rawQuery.getInt(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED);
            String string3 = rawQuery.getString(R.styleable.Toolbar_contentInsetLeft);
            Object obj2 = null;
            if (i == 1) {
                if (!new File(string2 + trim).isDirectory()) {
                    obj2 = 1;
                }
                File file3 = new File(string3);
                if (!TextUtils.isEmpty(string3) && file3.exists()) {
                    fromFile = Uri.fromFile(new File(string3));
                    obj = obj2;
                }
            } else {
                if (string != null && string.length() > 0 && string.startsWith("http://www.etm.xunlei.com/downloadmagnet.asp?magnet:?")) {
                    string = string.substring(string.indexOf("magnet:?"));
                }
                fromFile = Uri.parse(string);
                obj = null;
            }
            if (j2 <= 0) {
                j2 = -1;
            }
            if (i == 1) {
                i = 1;
            } else {
                Object obj3 = null;
            }
            a(trim, string2);
            if (fromFile != null) {
                Request request;
                DownloadManager instanceFor = DownloadManager.getInstanceFor(eVar.b);
                if (j == 0) {
                    request = new Request(fromFile);
                    request.setAllowedOverRoaming(false);
                    request.setAllowedNetworkTypes(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                    request.setDestinationUri(string2, a.d);
                    request.setTitle(trim);
                    request.setDownloadSpdy(true);
                    request.setDownloadDelay(false);
                } else {
                    request = new TaskImportRequest(fromFile, j, j2);
                    request.setDestinationUri(Uri.fromFile(new File(string2 + trim)));
                    request.setTitle(trim);
                    request.setAllowedNetworkTypes(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                }
                if (obj3 != null) {
                    TorrentInfo torrentInfo = new TorrentInfo();
                    if (XLDownloadManager.getInstance().getTorrentInfo(string3, torrentInfo) == 9000) {
                        Object obj4 = torrentInfo.mInfoHash;
                        if (!TextUtils.isEmpty(obj4)) {
                            request.setBtInfoHash(obj4);
                        }
                    }
                    if (obj != null) {
                        request.setDestinationUri(Uri.fromFile(new File(string2)));
                    }
                }
                try {
                    instanceFor.pauseDownload(instanceFor.enqueue(request));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        rawQuery.close();
        try {
            openDatabase.execSQL("DELETE FROM task_info");
            openDatabase.execSQL("DELETE FROM task_info_ext");
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
        openDatabase.close();
        try {
            if (file.listFiles() != null) {
                for (File file4 : file2.listFiles()) {
                    file4.delete();
                }
            }
            file2.delete();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
