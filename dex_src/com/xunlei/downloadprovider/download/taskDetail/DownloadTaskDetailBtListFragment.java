package com.xunlei.downloadprovider.download.taskDetail;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.umeng.message.proguard.j;
import com.xunlei.download.DownloadManager;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.c$a;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.download.taskDetail.bt.BtTaskItemFileInfo;
import com.xunlei.downloadprovider.download.taskDetail.widget.DownloadTaskNameAndIconView;
import com.xunlei.downloadprovider.download.taskDetail.widget.TaskDetailShareBar;
import com.xunlei.downloadprovider.download.taskDetail.widget.TaskDetailSpeedInfoView;
import com.xunlei.downloadprovider.service.downloads.task.a.k;
import com.xunlei.downloadprovider.service.downloads.task.b.c;
import com.xunlei.downloadprovider.util.r;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

public class DownloadTaskDetailBtListFragment extends Fragment implements LoaderCallbacks<Cursor> {
    private boolean A;
    private Handler B;
    private final k C;
    com.xunlei.downloadprovider.download.a.a a;
    a b;
    TaskDetailSpeedInfoView c;
    com.xunlei.downloadprovider.download.tasklist.a.a d;
    ArrayList<BtTaskItemFileInfo> e;
    boolean f;
    boolean g;
    c h;
    private int i;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private final int r;
    private final int s;
    private ListView t;
    private BtTaskItemFileInfo u;
    private BtTaskItemFileInfo v;
    private BtTaskItemFileInfo w;
    private a x;
    private boolean y;
    private boolean z;

    class a extends BaseAdapter {
        a() {
        }

        public final int getCount() {
            return DownloadTaskDetailBtListFragment.this.e != null ? DownloadTaskDetailBtListFragment.this.e.size() : 0;
        }

        public final int getItemViewType(int i) {
            BtTaskItemFileInfo btTaskItemFileInfo = (BtTaskItemFileInfo) getItem(i);
            return btTaskItemFileInfo != null ? btTaskItemFileInfo.itemType : 0;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            try {
                BtTaskItemFileInfo btTaskItemFileInfo = (BtTaskItemFileInfo) getItem(i);
                new StringBuilder("view Type: ").append(btTaskItemFileInfo.itemType);
                View downloadTaskNameAndIconView;
                if (btTaskItemFileInfo.itemType == 2) {
                    if (view == null || !(view instanceof DownloadTaskNameAndIconView)) {
                        downloadTaskNameAndIconView = new DownloadTaskNameAndIconView(DownloadTaskDetailBtListFragment.this.getActivity());
                    } else {
                        downloadTaskNameAndIconView = view;
                    }
                    DownloadTaskNameAndIconView downloadTaskNameAndIconView2 = (DownloadTaskNameAndIconView) downloadTaskNameAndIconView;
                    downloadTaskNameAndIconView2.a(DownloadTaskDetailBtListFragment.this.d);
                    if (DownloadTaskDetailBtListFragment.this.g) {
                        downloadTaskNameAndIconView2.a(true);
                        return downloadTaskNameAndIconView;
                    }
                    downloadTaskNameAndIconView2.a(false);
                    return downloadTaskNameAndIconView;
                } else if (btTaskItemFileInfo.itemType == 1) {
                    if (view == null || !(view instanceof TaskDetailSpeedInfoView)) {
                        TaskDetailSpeedInfoView taskDetailSpeedInfoView = new TaskDetailSpeedInfoView(DownloadTaskDetailBtListFragment.this.getActivity());
                    } else {
                        downloadTaskNameAndIconView = view;
                    }
                    if (!(downloadTaskNameAndIconView instanceof TaskDetailSpeedInfoView)) {
                        downloadTaskNameAndIconView = new TaskDetailSpeedInfoView(DownloadTaskDetailBtListFragment.this.getActivity());
                    }
                    TaskDetailSpeedInfoView taskDetailSpeedInfoView2 = (TaskDetailSpeedInfoView) downloadTaskNameAndIconView;
                    taskDetailSpeedInfoView2.setControl(DownloadTaskDetailBtListFragment.this);
                    if (DownloadTaskDetailBtListFragment.this.y) {
                        taskDetailSpeedInfoView2.setTaskCountInfo(DownloadTaskDetailBtListFragment.this.h);
                        taskDetailSpeedInfoView2.a(DownloadTaskDetailBtListFragment.this.d);
                        DownloadTaskDetailBtListFragment.this.y = false;
                    }
                    taskDetailSpeedInfoView2.b(DownloadTaskDetailBtListFragment.this.d);
                    DownloadTaskDetailBtListFragment.this.c = taskDetailSpeedInfoView2;
                    if (DownloadTaskDetailBtListFragment.this.g) {
                        DownloadTaskDetailBtListFragment.this.c.a(true);
                        return downloadTaskNameAndIconView;
                    }
                    DownloadTaskDetailBtListFragment.this.c.a(false);
                    return downloadTaskNameAndIconView;
                } else if (btTaskItemFileInfo.itemType == 3) {
                    if (view == null || !(view instanceof TaskDetailShareBar)) {
                        downloadTaskNameAndIconView = new TaskDetailShareBar(DownloadTaskDetailBtListFragment.this.getActivity());
                    } else {
                        downloadTaskNameAndIconView = view;
                    }
                    TaskDetailShareBar taskDetailShareBar = (TaskDetailShareBar) downloadTaskNameAndIconView;
                    taskDetailShareBar.setCurrentTask(DownloadTaskDetailBtListFragment.this.d);
                    taskDetailShareBar.setEditMode(DownloadTaskDetailBtListFragment.this.g);
                    return downloadTaskNameAndIconView;
                } else {
                    View view2;
                    b bVar;
                    if ((view instanceof TaskDetailSpeedInfoView) || (view instanceof TaskDetailShareBar) || (view instanceof DownloadTaskNameAndIconView)) {
                        ViewGroup viewGroup2 = null;
                    } else {
                        view2 = view;
                    }
                    if (view2 == null) {
                        view2 = LayoutInflater.from(DownloadTaskDetailBtListFragment.this.getActivity()).inflate(R.layout.download_center_task_detail_btl_list, null);
                        bVar = new b();
                        bVar.b = (TextView) view2.findViewById(R.id.titleTextView);
                        bVar.c = (ImageView) view2.findViewById(R.id.iconImageView);
                        bVar.d = (TextView) view2.findViewById(R.id.tagSize);
                        bVar.e = (TextView) view2.findViewById(R.id.open_text);
                        bVar.j = (ImageView) view2.findViewById(R.id.open_icon);
                        bVar.k = view2.findViewById(R.id.openContainer);
                        bVar.h = view2.findViewById(R.id.select_btn_container);
                        bVar.l = view2.findViewById(R.id.taskItemLayout);
                        bVar.m = (TextView) view2.findViewById(R.id.play_flag);
                        bVar.f = (ProgressBar) view2.findViewById(R.id.progressBar);
                        bVar.g = (ImageView) view2.findViewById(R.id.edit_mode_select_btn);
                        bVar.i = (TextView) view2.findViewById(R.id.statusTextView);
                        bVar.n = view2.findViewById(R.id.bt_expand_white_space);
                        view2.setTag(bVar);
                    }
                    btTaskItemFileInfo = (BtTaskItemFileInfo) getItem(i);
                    bVar = (b) view2.getTag();
                    if (bVar != null) {
                        DownloadTaskDetailBtListFragment.this = i;
                        DownloadTaskDetailBtListFragment.a(DownloadTaskDetailBtListFragment.this, btTaskItemFileInfo, bVar);
                        if (bVar.n != null) {
                            if (i == getCount() - 1) {
                                bVar.n.setVisibility(0);
                            } else {
                                bVar.n.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                            }
                        }
                    }
                    return view2;
                }
            } catch (RuntimeException e) {
                return LayoutInflater.from(BrothersApplication.a()).inflate(R.layout.layout_task_empty, null);
            }
        }

        public final Object getItem(int i) {
            return (!d.a(DownloadTaskDetailBtListFragment.this.e) && i < DownloadTaskDetailBtListFragment.this.e.size()) ? DownloadTaskDetailBtListFragment.this.e.get(i) : null;
        }

        public final int getViewTypeCount() {
            return MqttConnectOptions.MQTT_VERSION_3_1_1;
        }

        public final long getItemId(int i) {
            return 0;
        }
    }

    static class b {
        public int a;
        public TextView b;
        public ImageView c;
        public TextView d;
        public TextView e;
        public ProgressBar f;
        public ImageView g;
        public View h;
        public TextView i;
        public ImageView j;
        public View k;
        public View l;
        public TextView m;
        public View n;

        b() {
        }
    }

    static /* synthetic */ void a(DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment, Cursor cursor) {
        if (downloadTaskDetailBtListFragment.e == null) {
            downloadTaskDetailBtListFragment.e = new ArrayList();
        }
        int size = downloadTaskDetailBtListFragment.e.size();
        List a = downloadTaskDetailBtListFragment.a();
        downloadTaskDetailBtListFragment.e = new ArrayList();
        downloadTaskDetailBtListFragment.e.add(downloadTaskDetailBtListFragment.u);
        downloadTaskDetailBtListFragment.e.add(downloadTaskDetailBtListFragment.v);
        if (cursor != null) {
            if (cursor != null) {
                downloadTaskDetailBtListFragment.i = cursor.getColumnIndex(j.g);
                downloadTaskDetailBtListFragment.o = cursor.getColumnIndex("bt_sub_index");
                downloadTaskDetailBtListFragment.j = cursor.getColumnIndex(SetKey.TITLE);
                downloadTaskDetailBtListFragment.k = cursor.getColumnIndex("total_bytes");
                downloadTaskDetailBtListFragment.l = cursor.getColumnIndex("current_bytes");
                downloadTaskDetailBtListFragment.m = cursor.getColumnIndex("_data");
                downloadTaskDetailBtListFragment.n = cursor.getColumnIndex("status");
                downloadTaskDetailBtListFragment.p = cursor.getColumnIndex(SHubBatchQueryKeys.cid);
                downloadTaskDetailBtListFragment.q = cursor.getColumnIndex(SHubBatchQueryKeys.gcid);
            }
            while (cursor.moveToNext()) {
                long j;
                BtTaskItemFileInfo btTaskItemFileInfo = new BtTaskItemFileInfo();
                if (downloadTaskDetailBtListFragment.d == null) {
                    j = -1;
                } else {
                    j = downloadTaskDetailBtListFragment.d.mTaskId;
                }
                btTaskItemFileInfo.mParentTaskId = j;
                btTaskItemFileInfo.mTaskId = cursor.getLong(downloadTaskDetailBtListFragment.i);
                btTaskItemFileInfo.mBTSubIndex = cursor.getInt(downloadTaskDetailBtListFragment.o);
                btTaskItemFileInfo.mTitle = cursor.getString(downloadTaskDetailBtListFragment.j);
                btTaskItemFileInfo.mFileSize = cursor.getLong(downloadTaskDetailBtListFragment.k);
                btTaskItemFileInfo.mDownloadedSize = cursor.getLong(downloadTaskDetailBtListFragment.l);
                btTaskItemFileInfo.mLocalFileName = cursor.getString(downloadTaskDetailBtListFragment.m);
                int i = cursor.getInt(downloadTaskDetailBtListFragment.n);
                btTaskItemFileInfo.mOriginalStatusCode = i;
                btTaskItemFileInfo.mTaskStatus = DownloadManager.translateStatus(i);
                if (downloadTaskDetailBtListFragment.p != -1) {
                    btTaskItemFileInfo.mCID = cursor.getString(downloadTaskDetailBtListFragment.p);
                }
                if (downloadTaskDetailBtListFragment.q != -1) {
                    btTaskItemFileInfo.mGCID = cursor.getString(downloadTaskDetailBtListFragment.q);
                }
                btTaskItemFileInfo.isSelected = a(btTaskItemFileInfo.mTaskId, a);
                downloadTaskDetailBtListFragment.e.add(btTaskItemFileInfo);
            }
        }
        if (downloadTaskDetailBtListFragment.A) {
            downloadTaskDetailBtListFragment.e.add(downloadTaskDetailBtListFragment.w);
        }
        if (size != downloadTaskDetailBtListFragment.e.size()) {
            Object obj = 1;
        } else {
            boolean z = false;
        }
        if (!(downloadTaskDetailBtListFragment.e == null || downloadTaskDetailBtListFragment.e.isEmpty())) {
            downloadTaskDetailBtListFragment.C.execute(new af(downloadTaskDetailBtListFragment, new ArrayList(downloadTaskDetailBtListFragment.e)));
        }
        if (downloadTaskDetailBtListFragment.z || r0) {
            if (downloadTaskDetailBtListFragment.z) {
                downloadTaskDetailBtListFragment.z = false;
            }
            downloadTaskDetailBtListFragment.c();
            return;
        }
        downloadTaskDetailBtListFragment.B.sendEmptyMessageDelayed(0, 2000);
    }

    static /* synthetic */ void a(DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment, BtTaskItemFileInfo btTaskItemFileInfo, b bVar) {
        String str;
        PackageInfo packageArchiveInfo;
        String str2 = null;
        TextView textView = bVar.b;
        FragmentActivity activity = downloadTaskDetailBtListFragment.getActivity();
        CharSequence charSequence = BuildConfig.VERSION_NAME;
        if (btTaskItemFileInfo == null || TextUtils.isEmpty(btTaskItemFileInfo.mLocalFileName)) {
            charSequence = BuildConfig.VERSION_NAME;
        } else if (XLFileTypeUtil.a(btTaskItemFileInfo.mLocalFileName) == EFileCategoryType.E_SOFTWARE_CATEGORY && btTaskItemFileInfo.mTaskStatus == 8) {
            c$a a = com.xunlei.downloadprovider.a.c.a(activity, btTaskItemFileInfo.mLocalFileName);
            if (a != null) {
                CharSequence a2 = a.a();
                if (a2 != null) {
                    charSequence = a2 + ".apk";
                }
            }
        } else {
            charSequence = btTaskItemFileInfo.mTitle;
        }
        textView.setText(charSequence);
        textView.requestLayout();
        ImageView imageView = bVar.c;
        XLFileTypeUtil.a(btTaskItemFileInfo.mLocalFileName);
        int d = XLFileTypeUtil.d(btTaskItemFileInfo.mLocalFileName);
        if (d == 2130838396) {
            Drawable loadIcon;
            String str3 = btTaskItemFileInfo.mLocalFileName;
            PackageManager packageManager = downloadTaskDetailBtListFragment.getActivity().getPackageManager();
            PackageInfo packageArchiveInfo2 = packageManager.getPackageArchiveInfo(str3, 1);
            if (packageArchiveInfo2 != null) {
                ApplicationInfo applicationInfo = packageArchiveInfo2.applicationInfo;
                applicationInfo.sourceDir = str3;
                applicationInfo.publicSourceDir = str3;
                loadIcon = applicationInfo.loadIcon(packageManager);
            } else {
                loadIcon = null;
            }
            if (loadIcon != null) {
                imageView.setImageDrawable(loadIcon);
                textView = bVar.d;
                if (btTaskItemFileInfo.mFileSize != 0) {
                    charSequence = btTaskItemFileInfo.mTaskStatus != 8 ? "0B" : "\u672a\u77e5\u5927\u5c0f";
                } else {
                    charSequence = com.xunlei.downloadprovider.d.a.a(btTaskItemFileInfo.mFileSize);
                    if ((btTaskItemFileInfo.mTaskStatus == 8 || btTaskItemFileInfo.mTaskStatus == 16) && btTaskItemFileInfo.mTaskStatus == 8 && btTaskItemFileInfo.mTitle.endsWith(".apk")) {
                        str = btTaskItemFileInfo.mLocalFileName;
                        packageArchiveInfo = downloadTaskDetailBtListFragment.getActivity().getPackageManager().getPackageArchiveInfo(str, 1);
                        if (packageArchiveInfo != null) {
                            ApplicationInfo applicationInfo2 = packageArchiveInfo.applicationInfo;
                            applicationInfo2.sourceDir = str;
                            applicationInfo2.publicSourceDir = str;
                            str2 = packageArchiveInfo.versionName;
                        }
                        if (str2 != null) {
                            charSequence = new StringBuilder("\u7248\u672c:").append(str2).append("  ").append(charSequence).toString();
                        }
                    }
                }
                textView.setText(charSequence);
                d(btTaskItemFileInfo, bVar);
                a(btTaskItemFileInfo, bVar.i);
                downloadTaskDetailBtListFragment.a(btTaskItemFileInfo, bVar);
                downloadTaskDetailBtListFragment.c(btTaskItemFileInfo, bVar);
            }
        }
        imageView.setImageResource(d);
        textView = bVar.d;
        if (btTaskItemFileInfo.mFileSize != 0) {
            charSequence = com.xunlei.downloadprovider.d.a.a(btTaskItemFileInfo.mFileSize);
            str = btTaskItemFileInfo.mLocalFileName;
            packageArchiveInfo = downloadTaskDetailBtListFragment.getActivity().getPackageManager().getPackageArchiveInfo(str, 1);
            if (packageArchiveInfo != null) {
                ApplicationInfo applicationInfo22 = packageArchiveInfo.applicationInfo;
                applicationInfo22.sourceDir = str;
                applicationInfo22.publicSourceDir = str;
                str2 = packageArchiveInfo.versionName;
            }
            if (str2 != null) {
                charSequence = new StringBuilder("\u7248\u672c:").append(str2).append("  ").append(charSequence).toString();
            }
        } else if (btTaskItemFileInfo.mTaskStatus != 8) {
        }
        textView.setText(charSequence);
        d(btTaskItemFileInfo, bVar);
        a(btTaskItemFileInfo, bVar.i);
        downloadTaskDetailBtListFragment.a(btTaskItemFileInfo, bVar);
        downloadTaskDetailBtListFragment.c(btTaskItemFileInfo, bVar);
    }

    public /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
        Cursor cursor = (Cursor) obj;
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new ae(this, loader, cursor));
        }
    }

    final boolean a(BtTaskItemFileInfo btTaskItemFileInfo) {
        return btTaskItemFileInfo == this.u || btTaskItemFileInfo == this.v || btTaskItemFileInfo == this.w;
    }

    public final List<BtTaskItemFileInfo> a() {
        List arrayList = new ArrayList();
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            BtTaskItemFileInfo btTaskItemFileInfo = (BtTaskItemFileInfo) it.next();
            if (!a(btTaskItemFileInfo) && btTaskItemFileInfo.isSelected) {
                arrayList.add(btTaskItemFileInfo);
            }
        }
        return arrayList;
    }

    public final List<BtTaskItemFileInfo> b() {
        List<BtTaskItemFileInfo> arrayList = new ArrayList();
        Iterator it = this.e.iterator();
        while (it.hasNext()) {
            BtTaskItemFileInfo btTaskItemFileInfo = (BtTaskItemFileInfo) it.next();
            if (!a(btTaskItemFileInfo)) {
                arrayList.add(btTaskItemFileInfo);
            }
        }
        return arrayList;
    }

    public void onResume() {
        super.onResume();
        if (this.C != null) {
            try {
                if (!this.C.isAlive()) {
                    this.C.start();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onDestroy() {
        if (this.C != null && this.C.isAlive()) {
            try {
                this.C.a.sendEmptyMessage(1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }

    public Loader onCreateLoader(int i, Bundle bundle) {
        try {
            return new com.xunlei.downloadprovider.service.downloads.kernel.b(getActivity(), com.xunlei.downloadprovider.service.downloads.kernel.c.a(getActivity()).getBtSubTaskUri(), null, "bt_parent_id=?", new String[]{this.d.getTaskId()}, null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    final void c() {
        if (this.x != null) {
            this.x.notifyDataSetChanged();
        }
    }

    public void onLoaderReset(Loader loader) {
        if (loader != null) {
            try {
                loader.stopLoading();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public DownloadTaskDetailBtListFragment() {
        this.r = 0;
        this.s = 1;
        this.u = new BtTaskItemFileInfo();
        this.v = new BtTaskItemFileInfo();
        this.w = new BtTaskItemFileInfo();
        this.A = true;
        this.B = new ac(this);
        this.C = new k("BTTaskDetail", new ad(this));
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.task_detail_bt_list_layout, null);
        this.A = r.c().m.a();
        this.u.itemType = 2;
        this.v.itemType = 1;
        this.w.itemType = 3;
        this.x = new a();
        this.t = (ListView) inflate.findViewById(R.id.task_bt_list_view);
        this.t.setAdapter(this.x);
        this.t.setOnItemClickListener(new ag(this));
        this.t.setOnItemLongClickListener(new ah(this));
        if (this.e == null) {
            this.e = new ArrayList();
        }
        return inflate;
    }

    public final void a(com.xunlei.downloadprovider.download.tasklist.a.a aVar) {
        if (aVar != null) {
            this.d = aVar;
            this.z = true;
            this.f = true;
            this.y = true;
            if (d.a(this.e)) {
                this.e.add(this.u);
                this.e.add(this.v);
                if (this.A) {
                    this.e.add(this.w);
                }
            }
            c();
            getLoaderManager().initLoader(1, null, this);
            this.B.postDelayed(new ai(this), 200);
        }
    }

    private static boolean a(long j, List<BtTaskItemFileInfo> list) {
        for (BtTaskItemFileInfo btTaskItemFileInfo : list) {
            if (btTaskItemFileInfo.mTaskId == j) {
                return btTaskItemFileInfo.isSelected;
            }
        }
        return false;
    }

    private void a(BtTaskItemFileInfo btTaskItemFileInfo, b bVar) {
        bVar.k.setBackgroundResource(R.drawable.task_detail_play_downloading_btn_bg);
        bVar.m.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        if (e(btTaskItemFileInfo)) {
            if (btTaskItemFileInfo.mIsFileMissing) {
                bVar.m.setText("\u6587\u4ef6\u5df2\u79fb\u9664");
                bVar.m.setVisibility(0);
                bVar.m.setTextColor(getResources().getColor(R.color.DownloadTaskItemStatusTextColor));
            } else if (b(btTaskItemFileInfo)) {
                b(btTaskItemFileInfo, bVar);
                return;
            } else {
                return;
            }
        } else if (b(btTaskItemFileInfo)) {
            b(btTaskItemFileInfo, bVar);
            return;
        }
        bVar.k.setBackgroundResource(R.drawable.task_detail_play_downloading_btn_bg_gray);
    }

    private void b(BtTaskItemFileInfo btTaskItemFileInfo, b bVar) {
        if (e(btTaskItemFileInfo)) {
            com.xunlei.downloadprovider.vod.b.b.a b = com.xunlei.downloadprovider.vod.b.b.a().b(btTaskItemFileInfo.mLocalFileName);
            if (b != null) {
                bVar.m.setVisibility(0);
                btTaskItemFileInfo.mVideoDuration = b.l;
                btTaskItemFileInfo.mVideoPlayedTime = b.k;
                if (b.l != b.k || b.k <= 0) {
                    String str = "0%";
                    if (b.k > 0) {
                        if ((btTaskItemFileInfo.mVideoPlayedTime * 100) / btTaskItemFileInfo.mVideoDuration <= 1) {
                            str = "1%";
                        } else {
                            str = ((btTaskItemFileInfo.mVideoPlayedTime * 100) / btTaskItemFileInfo.mVideoDuration) + "%";
                        }
                    }
                    bVar.m.setTextColor(getResources().getColor(R.color.DownloadTaskItemHintStatusTextColor));
                    bVar.m.setText(getResources().getString(R.string.download_item_task_play_at, new Object[]{str}));
                    return;
                }
                bVar.m.setTextColor(getResources().getColor(R.color.DownloadTaskItemStatusTextColor));
                bVar.m.setText(R.string.download_item_task_played);
            }
        }
    }

    private void c(BtTaskItemFileInfo btTaskItemFileInfo, b bVar) {
        boolean z = false;
        if (this.g) {
            bVar.h.setVisibility(0);
            bVar.k.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            if (btTaskItemFileInfo.isSelected) {
                bVar.g.setImageResource(R.drawable.big_selected);
                return;
            } else {
                bVar.g.setImageResource(R.drawable.big_unselected);
                return;
            }
        }
        bVar.h.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        bVar.k.setVisibility(0);
        bVar.k.setClickable(false);
        bVar.k.setEnabled(false);
        if (c(btTaskItemFileInfo) == EFileCategoryType.E_SOFTWARE_CATEGORY) {
            z = true;
        }
        if (z) {
            bVar.e.setText("\u5b89\u88c5");
            bVar.j.setImageResource(R.drawable.download_detail_install);
        } else if (b(btTaskItemFileInfo)) {
            bVar.e.setText("\u64ad\u653e");
            bVar.j.setImageResource(R.drawable.download_detail_play);
        } else {
            bVar.e.setText("\u6253\u5f00");
            bVar.j.setImageResource(R.drawable.download_detail_open);
        }
    }

    private static boolean e(BtTaskItemFileInfo btTaskItemFileInfo) {
        return btTaskItemFileInfo != null && btTaskItemFileInfo.mTaskStatus == 8;
    }

    private static void d(BtTaskItemFileInfo btTaskItemFileInfo, b bVar) {
        if (btTaskItemFileInfo != null) {
            if (e(btTaskItemFileInfo)) {
                bVar.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                return;
            }
            bVar.f.setVisibility(0);
            float f = 0.0f;
            if (btTaskItemFileInfo.mFileSize > 0) {
                f = ((float) btTaskItemFileInfo.mDownloadedSize) / ((float) btTaskItemFileInfo.mFileSize);
            }
            bVar.f.setProgress((int) (f * 100.0f));
        }
    }

    private static void a(BtTaskItemFileInfo btTaskItemFileInfo, TextView textView) {
        CharSequence charSequence;
        String str = BuildConfig.VERSION_NAME;
        switch (btTaskItemFileInfo.mTaskStatus) {
            case SimpleLog.LOG_LEVEL_TRACE:
                str = "\u7b49\u5f85\u4e0b\u8f7d";
                break;
            case MqttConnectOptions.MQTT_VERSION_3_1_1:
                str = "\u4e0b\u8f7d\u6682\u505c";
                break;
            case SpdyProtocol.CUSTOM:
                str = "\u4e0b\u8f7d\u5931\u8d25";
                break;
        }
        if (btTaskItemFileInfo.mTaskStatus == 2 && btTaskItemFileInfo.mFileSize != 0 && btTaskItemFileInfo.mDownloadedSize >= btTaskItemFileInfo.mFileSize) {
            str = "\u6821\u9a8c\u4e2d";
        }
        textView.setTextColor(Color.parseColor("#94969f"));
        if (!TextUtils.isEmpty(btTaskItemFileInfo.mLocalFileName) && btTaskItemFileInfo.mIsFileMissing && btTaskItemFileInfo.mTaskStatus == 8) {
            charSequence = BuildConfig.VERSION_NAME;
        }
        if (TextUtils.isEmpty(charSequence)) {
            textView.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else {
            textView.setVisibility(0);
        }
        textView.setText(charSequence);
    }

    public static boolean b(BtTaskItemFileInfo btTaskItemFileInfo) {
        return c(btTaskItemFileInfo) == EFileCategoryType.E_VIDEO_CATEGORY;
    }

    public static EFileCategoryType c(BtTaskItemFileInfo btTaskItemFileInfo) {
        if (btTaskItemFileInfo == null) {
            return null;
        }
        if (btTaskItemFileInfo.mFileCategoryType != null && btTaskItemFileInfo.mFileCategoryType != EFileCategoryType.E_OTHER_CATEGORY) {
            return btTaskItemFileInfo.mFileCategoryType;
        }
        EFileCategoryType a;
        if (TextUtils.isEmpty(btTaskItemFileInfo.mLocalFileName)) {
            a = XLFileTypeUtil.a(btTaskItemFileInfo.mTitle);
        } else {
            a = XLFileTypeUtil.a(btTaskItemFileInfo.mLocalFileName);
        }
        btTaskItemFileInfo.mFileCategoryType = a;
        return a;
    }

    static /* synthetic */ void a(DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment, ListView listView, a aVar) {
        if (downloadTaskDetailBtListFragment.isVisible() && downloadTaskDetailBtListFragment.getActivity() != null) {
            for (int i = 0; i < listView.getChildCount(); i++) {
                b bVar = (b) listView.getChildAt(i).getTag();
                if (bVar != null) {
                    BtTaskItemFileInfo btTaskItemFileInfo = (BtTaskItemFileInfo) aVar.getItem(bVar.a);
                    if (btTaskItemFileInfo != null) {
                        d(btTaskItemFileInfo, bVar);
                        a(btTaskItemFileInfo, bVar.i);
                        downloadTaskDetailBtListFragment.c(btTaskItemFileInfo, bVar);
                        downloadTaskDetailBtListFragment.a(btTaskItemFileInfo, bVar);
                    }
                }
            }
        }
    }

    static /* synthetic */ boolean a(DownloadTaskDetailBtListFragment downloadTaskDetailBtListFragment, int i) {
        return ((BtTaskItemFileInfo) downloadTaskDetailBtListFragment.x.getItem(i)).mTaskId > 0;
    }
}
