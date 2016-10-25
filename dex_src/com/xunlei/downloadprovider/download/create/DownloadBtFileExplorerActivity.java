package com.xunlei.downloadprovider.download.create;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.xunlei.download.TorrentParser;
import com.xunlei.download.TorrentParser.OnTorrentParserListener;
import com.xunlei.download.TorrentParser.ParseResult;
import com.xunlei.download.TorrentParser.ParseResult.Code;
import com.xunlei.downloadlib.parameter.TorrentFileInfo;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.SimpleLoadingPageView;
import com.xunlei.downloadprovider.download.center.widget.DownloadCenterSelectFileTitleView;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.e;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.DownloadEngine;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.xlui.widget.ZHTextView;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DownloadBtFileExplorerActivity extends ThunderTask implements OnTorrentParserListener {
    public static final String EXTRA_KEY_CREATE_ORIGIN_FROM = "createOriginFrom";
    public static final String EXTRA_KEY_NAME_REPORT_TYPE = "reportType";
    public static final String EXTRA_KEY_NAME_TASK_ID = "taskId";
    static final int MSG_CREATETASK_FINISH = 1;
    static final int MSG_LOAD_BT_FINISH = 2;
    private com.xunlei.downloadprovider.a.h.b handler;
    private com.xunlei.downloadprovider.a.h.a listener;
    private a mAdapter;
    private String mBtTitle;
    private TextView mDownloadBtn;
    private int[] mDownloadingIndex;
    private String mInfoHash;
    private ListView mListView;
    private SimpleLoadingPageView mLoadingView;
    private TextView mMaskView;
    private View mProcess;
    private com.xunlei.downloadprovider.commonview.dialog.d mResumeTaskDialog;
    private List<c> mSeedInfos;
    private DownloadCenterSelectFileTitleView mSelectFileTitle;
    private List<c> mSelected;
    private TextView mStorageInfo;
    private long mTaskId;

    class a extends BaseAdapter {
        private ParseResult b;

        public final int getCount() {
            return this.b.torrentInfo.mFileCount;
        }

        public final Object getItem(int i) {
            return this.b.torrentInfo.mSubFileInfo[i];
        }

        public a(ParseResult parseResult) {
            this.b = parseResult;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(DownloadBtFileExplorerActivity.this).inflate(2130968815, null);
                b bVar = new b();
                bVar.b = (ZHTextView) view.findViewById(2131755768);
                bVar.c = (TextView) view.findViewById(2131755773);
                DownloadBtFileExplorerActivity.this = (ImageView) view.findViewById(2131755766);
                bVar.e = (TextView) view.findViewById(2131755772);
                bVar.d = (ImageView) view.findViewById(2131755764);
                view.setTag(bVar);
            }
            b bVar2 = (b) view.getTag();
            c cVar = (c) DownloadBtFileExplorerActivity.this.mSeedInfos.get(i);
            bVar2.b.setText(cVar.mFileName);
            bVar2.b.requestLayout();
            bVar2.c.setText(com.xunlei.downloadprovider.d.a.a(cVar.mFileSize));
            DownloadBtFileExplorerActivity.this.setImageResource(XLFileTypeUtil.d(cVar.mFileName));
            if (DownloadBtFileExplorerActivity.this.mSelected.contains(cVar)) {
                bVar2.d.setImageResource(2130837655);
            } else {
                bVar2.d.setImageResource(2130837656);
            }
            if (TextUtils.isEmpty(DownloadBtFileExplorerActivity.this)) {
                bVar2.e.setVisibility(XZBDevice.Wait);
            } else {
                bVar2.e.setVisibility(0);
                bVar2.e.setText(DownloadBtFileExplorerActivity.this);
            }
            return view;
        }

        public final long getItemId(int i) {
            return 0;
        }
    }

    class b {
        public ImageView a;
        public ZHTextView b;
        public TextView c;
        public ImageView d;
        public TextView e;

        b() {
        }
    }

    static class c extends TorrentFileInfo {
        public String a;

        c() {
        }
    }

    private class d {
        public long a;
        Uri b;
        String c;
        String d;
        String e;
        public long[] f;

        public d(long j, long[] jArr, String str) {
            this.a = -1;
            this.a = j;
            this.f = jArr;
            this.e = str;
        }

        public d(Uri uri, long[] jArr, String str, String str2, String str3) {
            this.a = -1;
            this.a = -1;
            this.b = uri;
            this.c = str;
            this.d = str2;
            this.f = jArr;
            this.e = str3;
        }

        final void a(DownloadService downloadService) {
            try {
                boolean z;
                if (this.a != -1) {
                    long j = this.a;
                    long[] jArr = this.f;
                    DownloadEngine downloadEngine = downloadService.d;
                    new StringBuilder("addBtSubTask  ").append(j).append(":").append(Arrays.toString(jArr));
                    downloadEngine.c();
                    downloadEngine.a(j, jArr);
                    z = true;
                } else {
                    e.a();
                    if (e.b() == 1) {
                        e.a().a(DownloadBtFileExplorerActivity.this, this.c, this.e, this.f, SaveToXZBEntry.other);
                        z = true;
                    } else {
                        e.a();
                        if (e.b() == 2) {
                            e.a().a(DownloadBtFileExplorerActivity.this, this.c, this.e, this.f, SaveToXZBEntry.other);
                            z = downloadService.a(this.b, this.f, this.c, this.d, this.e);
                        } else {
                            e.a();
                            if (e.b() == 0) {
                                z = downloadService.a(this.b, this.f, this.c, this.d, this.e);
                            } else {
                                z = true;
                            }
                        }
                    }
                }
                com.xunlei.downloadprovider.download.report.a.j(z ? "task_success" : "task_fail");
                StatReporter.reportOverallDownload("manual_downloadedlist");
                if (DownloadBtFileExplorerActivity.this.handler != null) {
                    DownloadBtFileExplorerActivity.this.handler.sendEmptyMessage(MSG_CREATETASK_FINISH);
                }
            } catch (Exception e) {
                try {
                    e.printStackTrace();
                    if (DownloadBtFileExplorerActivity.this.handler != null) {
                        DownloadBtFileExplorerActivity.this.handler.sendEmptyMessage(MSG_CREATETASK_FINISH);
                    }
                } catch (Throwable th) {
                    if (DownloadBtFileExplorerActivity.this.handler != null) {
                        DownloadBtFileExplorerActivity.this.handler.sendEmptyMessage(MSG_CREATETASK_FINISH);
                    }
                }
            }
        }
    }

    public DownloadBtFileExplorerActivity() {
        this.mTaskId = -1;
        this.mResumeTaskDialog = null;
        this.mSeedInfos = new ArrayList();
        this.mSelected = new ArrayList();
        this.listener = new k(this);
        this.handler = new com.xunlei.downloadprovider.a.h.b(this.listener);
    }

    public static void startSelf(Context context, String str, int i, String str2) {
        startSelf(context, str, -1, i, com.umeng.a.d);
    }

    public static void startSelf(Context context, String str, long j, int i, String str2) {
        Intent intent = new Intent();
        intent.setClass(context, DownloadBtFileExplorerActivity.class);
        intent.setData(Uri.parse(str));
        intent.putExtra(EXTRA_KEY_NAME_TASK_ID, j);
        intent.putExtra(EXTRA_KEY_NAME_REPORT_TYPE, i);
        String str3 = EXTRA_KEY_CREATE_ORIGIN_FROM;
        if (str2 == null) {
            str2 = com.umeng.a.d;
        }
        intent.putExtra(str3, str2);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968606);
        handleIntent();
        initUI();
        startParse();
    }

    private void handleIntent() {
        this.mTaskId = getIntent().getLongExtra(EXTRA_KEY_NAME_TASK_ID, -1);
    }

    private void initUI() {
        initDownloadBtn();
        initSelectFileTitle();
        initListView();
        initStorageInfo();
        this.mProcess = findViewById(2131755205);
        this.mLoadingView = (SimpleLoadingPageView) findViewById(2131755208);
        this.mMaskView = (TextView) findViewById(2131755207);
        this.mMaskView.setOnClickListener(new m(this));
    }

    private void startParse() {
        try {
            new TorrentParser(this, this).parse(new File(URI.create(getIntent().getDataString())), this.mTaskId, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.translate_between_interface_left_in, R.anim.translate_between_interface_right_out);
    }

    private void initSelectFileTitle() {
        this.mSelectFileTitle = (DownloadCenterSelectFileTitleView) findViewById(2131755200);
        this.mSelectFileTitle.setCancelListener(new n(this));
        this.mSelectFileTitle.setSelectAllListener(new o(this));
        this.mSelectFileTitle.b(false);
    }

    private void initListView() {
        this.mListView = (ListView) findViewById(2131755204);
        this.mListView.setOnItemClickListener(new p(this));
    }

    private void initStorageInfo() {
        this.mStorageInfo = (TextView) findViewById(2131755202);
        String a = com.xunlei.downloadprovider.d.a.a(com.xunlei.downloadprovider.businessutil.a.c());
        this.mStorageInfo.setText(getString(2131231161, new Object[]{"0B", a}));
    }

    private void initDownloadBtn() {
        this.mDownloadBtn = (TextView) findViewById(2131755203);
        this.mDownloadBtn.setOnClickListener(new q(this));
    }

    private void enableDownloadBtn(boolean z) {
        if (z) {
            this.mDownloadBtn.setClickable(true);
            this.mDownloadBtn.setBackgroundDrawable(getResources().getDrawable(2130839357));
            return;
        }
        this.mDownloadBtn.setClickable(false);
        this.mDownloadBtn.setBackgroundDrawable(getResources().getDrawable(2130839323));
    }

    protected void showResumeTaskAlarmDlg() {
        dismissResumeTaskDialog();
        this.mResumeTaskDialog = new com.xunlei.downloadprovider.commonview.dialog.d(this);
        this.mResumeTaskDialog.a(getString(2131231751));
        this.mResumeTaskDialog.d(getString(2131231746));
        this.mResumeTaskDialog.c(getString(2131231745));
        this.mResumeTaskDialog.setCanceledOnTouchOutside(true);
        this.mResumeTaskDialog.a(true);
        this.mResumeTaskDialog.b(new r(this));
        this.mResumeTaskDialog.a(new s(this));
        this.mResumeTaskDialog.show();
    }

    private void startLoading() {
        this.mLoadingView.setVisibility(0);
        this.mLoadingView.setTip("\u6b63\u5728\u521b\u5efa");
        this.mMaskView.setVisibility(0);
        this.mLoadingView.a();
    }

    private void stopLoading() {
        this.mLoadingView.setVisibility(XZBDevice.Wait);
        this.mMaskView.setVisibility(XZBDevice.Wait);
    }

    private void dismissResumeTaskDialog() {
        if (this.mResumeTaskDialog != null) {
            this.mResumeTaskDialog.dismiss();
            this.mResumeTaskDialog = null;
        }
    }

    private void updateStorageTxt() {
        String a = com.xunlei.downloadprovider.d.a.a(com.xunlei.downloadprovider.businessutil.a.c());
        if (this.mSelected.size() != 0) {
            String a2 = com.xunlei.downloadprovider.d.a.a(getSelectedFileSize());
            this.mSelectFileTitle.setTitle(new StringBuilder("\u5df2\u9009\u62e9").append(this.mSelected.size()).append("\u4e2a\u9879\u76ee").toString());
            this.mStorageInfo.setText(getString(2131231161, new Object[]{a2, a}));
            enableDownloadBtn(true);
        } else {
            this.mSelectFileTitle.setTitle("\u8bf7\u9009\u62e9\u6587\u4ef6");
            this.mStorageInfo.setText(getString(2131231161, new Object[]{"0B", a}));
            enableDownloadBtn(false);
        }
        if (this.mSelected.size() == this.mSeedInfos.size()) {
            this.mSelectFileTitle.a(false);
        } else {
            this.mSelectFileTitle.a(true);
        }
    }

    private long getSelectedFileSize() {
        long j = 0;
        for (c cVar : this.mSelected) {
            j = cVar.mFileSize + j;
        }
        return j;
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.mSeedInfos != null) {
            this.mSeedInfos.clear();
        }
        if (this.mSelected != null) {
            this.mSelected.clear();
        }
    }

    protected void onNewIntent(Intent intent) {
        clearUI();
        setIntent(intent);
    }

    private void clearUI() {
        this.mListView.setVisibility(XZBDevice.Wait);
        this.mProcess.setVisibility(0);
        updateStorageTxt();
    }

    public void onTorrentParseBegin() {
    }

    public void onTorrentParseCompleted(ParseResult parseResult) {
        if (parseResult.code == Code.NO_ERROR) {
            this.mInfoHash = parseResult.torrentInfo.mInfoHash;
            this.mDownloadingIndex = parseResult.selectedSet.mIndexSet;
            this.mBtTitle = parseResult.torrentInfo.mMultiFileBaseFolder;
            new t(this, parseResult.torrentInfo.mSubFileInfo, parseResult).start();
            return;
        }
        Toast.makeText(this, "\u79cd\u5b50\u6587\u4ef6\u89e3\u6790\u5931\u8d25", 0).show();
        finish();
    }

    private c copyFrom(TorrentFileInfo torrentFileInfo) {
        c cVar = new c();
        cVar.mFileIndex = torrentFileInfo.mFileIndex;
        cVar.mFileName = torrentFileInfo.mFileName;
        cVar.mFileSize = torrentFileInfo.mFileSize;
        cVar.mSubPath = torrentFileInfo.mSubPath;
        return cVar;
    }

    private void extractEpisodeInfo(c cVar) {
        if (cVar.a == null) {
            cVar.a = com.umeng.a.d;
            com.xunlei.downloadprovider.download.util.c.a a = com.xunlei.downloadprovider.download.util.c.a(cVar.mFileName);
            if (a != null) {
                Object obj;
                String str = com.umeng.a.d;
                if (a.b > 0) {
                    str = new StringBuilder("\u7b2c").append(a.b).append("\u96c6").toString();
                }
                if (!TextUtils.isEmpty(a.d)) {
                    obj = a.d + " " + obj;
                } else if (a.a > 0) {
                    obj = new StringBuilder("\u7b2c").append(com.xunlei.xllib.b.c.a(a.a)).append("\u5b63 ").append(obj).toString();
                }
                if (!TextUtils.isEmpty(obj)) {
                    cVar.a = obj;
                }
            }
        }
    }

    private void setDataSelected() {
        int i = 0;
        List arrayList = new ArrayList();
        long j = 0;
        long j2 = 0;
        for (int i2 = 0; i2 < this.mSeedInfos.size(); i2++) {
            c cVar = (c) this.mSeedInfos.get(i2);
            j2 += cVar.mFileSize;
            String toLowerCase = XLFileTypeUtil.f(cVar.mFileName).toLowerCase();
            if (!TextUtils.isEmpty(toLowerCase) && !toLowerCase.equals(".") && !toLowerCase.equals(".torrent") && !toLowerCase.equals(".url") && !toLowerCase.equals(".html") && !toLowerCase.equals(".htm") && !toLowerCase.equals(".mht") && !cVar.mFileName.startsWith("__padding_file") && !cVar.mFileName.equalsIgnoreCase("thumbs.db")) {
                EFileCategoryType a = XLFileTypeUtil.a(cVar.mFileName);
                if (a == EFileCategoryType.E_PICTURE_CATEGORY) {
                    arrayList.add(cVar);
                    j += cVar.mFileSize;
                } else if ((a != EFileCategoryType.E_BOOK_CATEGORY || cVar.mFileSize >= 15360) && !isBtSubIndexDownloading(cVar.mFileIndex)) {
                    this.mSelected.add(cVar);
                }
            }
        }
        if (((double) j) > 0.1d * ((double) j2)) {
            while (i < arrayList.size()) {
                this.mSelected.add(arrayList.get(i));
                i++;
            }
        }
    }

    private void setSelectAll(boolean z) {
        if (z) {
            this.mSelected.clear();
            for (c cVar : this.mSeedInfos) {
                if (!this.mSelected.contains(cVar)) {
                    this.mSelected.add(cVar);
                }
            }
        } else {
            this.mSelected.clear();
        }
        if (this.mAdapter != null) {
            this.mAdapter.notifyDataSetChanged();
        }
        updateStorageTxt();
    }

    protected void startDownload() {
        com.xunlei.downloadprovider.download.report.a.j("click");
        startLoading();
        new Thread(new l(this)).start();
    }

    private boolean isBtSubIndexDownloading(int i) {
        if (this.mTaskId == -1) {
            return false;
        }
        int[] iArr = this.mDownloadingIndex;
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (i == iArr[i2]) {
                return true;
            }
        }
        return false;
    }
}
