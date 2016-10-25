package com.xunlei.download;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import com.alipay.sdk.util.h;
import com.android.providers.downloads.XlTaskHelper;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.download.TorrentParser.ParseResult;
import com.xunlei.download.proguard.am;
import com.xunlei.downloadlib.XLDownloadManager;
import com.xunlei.downloadlib.parameter.BtIndexSet;
import com.xunlei.downloadlib.parameter.TorrentInfo;
import java.io.File;
import java.util.ArrayList;

public class TorrentParser implements Runnable {
    private static final String a = "TorrentParser";
    private static final int b = 6536;
    private static final int c = 6537;
    private OnTorrentParserListener d;
    private Context e;
    private File f;
    private Thread g;
    private long h;
    private boolean i;
    @SuppressLint({"HandlerLeak"})
    private Handler j;

    public static interface OnTorrentParserListener {
        void onTorrentParseBegin();

        void onTorrentParseCompleted(ParseResult parseResult);
    }

    public static class ParseResult {
        public Code code;
        public long mTaskId;
        public BtIndexSet selectedSet;
        public TorrentInfo torrentInfo;

        public enum Code {
            NO_ERROR,
            UNKNOWN_ERROR,
            PARAM_ERROR,
            USER_CANCEL,
            TORRENT_FILE_NOT_EXIST,
            TORRENT_FILE_INVALID;

            static {
                NO_ERROR = new com.xunlei.download.TorrentParser.ParseResult.Code("NO_ERROR", 0);
                UNKNOWN_ERROR = new com.xunlei.download.TorrentParser.ParseResult.Code("UNKNOWN_ERROR", 1);
                PARAM_ERROR = new com.xunlei.download.TorrentParser.ParseResult.Code("PARAM_ERROR", 2);
                USER_CANCEL = new com.xunlei.download.TorrentParser.ParseResult.Code("USER_CANCEL", 3);
                TORRENT_FILE_NOT_EXIST = new com.xunlei.download.TorrentParser.ParseResult.Code("TORRENT_FILE_NOT_EXIST", 4);
                TORRENT_FILE_INVALID = new com.xunlei.download.TorrentParser.ParseResult.Code("TORRENT_FILE_INVALID", 5);
                a = new com.xunlei.download.TorrentParser.ParseResult.Code[]{NO_ERROR, UNKNOWN_ERROR, PARAM_ERROR, USER_CANCEL, TORRENT_FILE_NOT_EXIST, TORRENT_FILE_INVALID};
            }
        }

        ParseResult(Code code, TorrentInfo torrentInfo, BtIndexSet btIndexSet) {
            this(code, torrentInfo, btIndexSet, -1);
        }

        ParseResult(Code code, TorrentInfo torrentInfo, BtIndexSet btIndexSet, long j) {
            this.code = code;
            this.torrentInfo = torrentInfo;
            this.selectedSet = btIndexSet;
            this.mTaskId = j;
        }
    }

    public TorrentParser(Context context, OnTorrentParserListener onTorrentParserListener) {
        this.j = new AnonymousClass_1(this);
        this.e = context;
        this.d = onTorrentParserListener;
        this.h = -1;
    }

    public void parse(File file) {
        parse(file, -1);
    }

    public void parse(File file, long j) {
        parse(file, j, false);
    }

    public void parse(File file, long j, boolean z) {
        if (this.g != null) {
            this.g.interrupt();
            this.g = null;
        }
        this.h = j;
        this.i = z;
        this.f = file;
        this.g = new Thread(this, "TorrentParserThread");
        this.g.start();
        this.j.obtainMessage(b).sendToTarget();
    }

    public void cancel() {
        if (this.g != null) {
            this.g.interrupt();
            this.g = null;
        }
    }

    public void run() {
        Cursor query;
        Throwable e;
        if (this.f == null || this.e == null || this.d == null) {
            this.j.obtainMessage(c, 0, 0, new ParseResult(Code.PARAM_ERROR, null, null)).sendToTarget();
        } else if (this.f.isFile()) {
            BtIndexSet btIndexSet = new BtIndexSet();
            TorrentInfo torrentInfo = new TorrentInfo();
            int torrentInfo2 = XLDownloadManager.getInstance(this.e).getTorrentInfo(this.f.getAbsolutePath(), torrentInfo);
            if (torrentInfo2 != 9000) {
                if (torrentInfo2 == 9302) {
                    this.j.obtainMessage(c, 0, 0, new ParseResult(Code.TORRENT_FILE_INVALID, null, null)).sendToTarget();
                } else {
                    this.j.obtainMessage(c, 0, 0, new ParseResult(Code.UNKNOWN_ERROR, null, null)).sendToTarget();
                }
                am.b(a, new StringBuilder("getTorrentInfo ret=").append(XlTaskHelper.a(torrentInfo2)).toString());
                return;
            }
            btIndexSet.mIndexSet = new int[torrentInfo.mFileCount];
            for (torrentInfo2 = 0; torrentInfo2 < torrentInfo.mFileCount; torrentInfo2++) {
                btIndexSet.mIndexSet[torrentInfo2] = torrentInfo.mSubFileInfo[torrentInfo2].mFileIndex;
            }
            try {
                Uri downloadUri;
                if (this.h != -1) {
                    downloadUri = DownloadManager.getInstanceFor(this.e).getDownloadUri(this.h);
                    query = this.e.getContentResolver().query(downloadUri, new String[]{Impl.COLUMN_BT_SELECT_SET}, "deleted != '1'", null, null);
                } else if (this.i) {
                    downloadUri = DownloadManager.getInstanceFor(this.e).getDownloadUri();
                    String toString = new StringBuilder("(etag='").append(torrentInfo.mInfoHash).append("') AND (deleted != '1')").toString();
                    query = this.e.getContentResolver().query(downloadUri, new String[]{Impl.COLUMN_BT_SELECT_SET, DownloadManager.COLUMN_ID}, toString, null, null);
                } else {
                    query = null;
                }
                try {
                    if (query.moveToFirst()) {
                        String string = query.getString(query.getColumnIndex(Impl.COLUMN_BT_SELECT_SET));
                        if (this.h == -1) {
                            this.h = query.getLong(query.getColumnIndex(DownloadManager.COLUMN_ID));
                        }
                        if (string != null && string.length() > 0) {
                            String[] split = string.split(h.b);
                            if (split != null) {
                                ArrayList arrayList = new ArrayList();
                                for (torrentInfo2 = 0; torrentInfo2 < split.length; torrentInfo2++) {
                                    int intValue = Integer.valueOf(split[torrentInfo2]).intValue();
                                    if (intValue >= 0 && intValue < torrentInfo.mFileCount) {
                                        arrayList.add(Integer.valueOf(split[torrentInfo2]));
                                    }
                                }
                                btIndexSet.mIndexSet = new int[arrayList.size()];
                                for (int i = 0; i < btIndexSet.mIndexSet.length; i++) {
                                    btIndexSet.mIndexSet[i] = ((Integer) arrayList.get(i)).intValue();
                                }
                            }
                        }
                    }
                    if (query != null) {
                        query.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                    e.printStackTrace();
                    am.a(e);
                    if (query != null) {
                        query.close();
                    }
                    this.j.obtainMessage(c, 0, 0, new ParseResult(Code.NO_ERROR, torrentInfo, btIndexSet, this.h)).sendToTarget();
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                try {
                    e.printStackTrace();
                    am.a(e);
                    if (query != null) {
                        query.close();
                    }
                } catch (Throwable th) {
                    e = th;
                    if (query != null) {
                        query.close();
                    }
                    throw e;
                }
                this.j.obtainMessage(c, 0, 0, new ParseResult(Code.NO_ERROR, torrentInfo, btIndexSet, this.h)).sendToTarget();
            } catch (Throwable th2) {
                e = th2;
                query = null;
                if (query != null) {
                    query.close();
                }
                throw e;
            }
            this.j.obtainMessage(c, 0, 0, new ParseResult(Code.NO_ERROR, torrentInfo, btIndexSet, this.h)).sendToTarget();
        } else {
            this.j.obtainMessage(c, 0, 0, new ParseResult(Code.TORRENT_FILE_NOT_EXIST, null, null)).sendToTarget();
        }
    }
}
