package com.xunlei.downloadprovider.task;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.WindowManager.BadTokenException;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.app.o;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovider.commonview.dialog.g;
import com.xunlei.downloadprovider.commonview.dialog.q;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.e;
import com.xunlei.downloadprovider.download.tasklist.list.xzb.report.XZBReporter.SaveToXZBEntry;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.lang.ref.WeakReference;
import java.util.List;

public class ThunderTask extends BaseActivity implements o {
    private static final int SHOW_REMOTE_TIP = 9000;
    private static final String TAG;
    private Handler handler;
    public boolean mBatch;
    private g mBatchCreateDialog;
    private int mBatchErrorCode;
    private long mBatchErrorTaskId;
    public int mBatchFail;
    public int mBatchNum;
    public int mBatchSucc;
    private XLAlarmDialog mCreateExsitTaskErrorDialog;
    private d mCreateTaskAlertDialog;
    private q mCreateTaskFailedDialog;

    static class a {
        int a;
        String b;
        String c;
        String d;
        String e;
        long f;
        String g;
        String h;
        com.xunlei.downloadprovider.model.g i;
        String j;
        int k;
        String l;
        Handler m;

        public a(int i, String str, String str2, String str3, String str4, long j, String str5, String str6, com.xunlei.downloadprovider.model.g gVar, String str7, int i2, String str8, Handler handler) {
            this.a = 0;
            this.a = i;
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = str4;
            this.f = j;
            this.g = str5;
            this.h = str6;
            this.i = gVar;
            this.j = str7;
            this.k = i2;
            this.l = str8;
            this.m = handler;
        }
    }

    private static class b extends Handler {
        private WeakReference<ThunderTask> a;

        public b(ThunderTask thunderTask) {
            this.a = null;
            this.a = new WeakReference(thunderTask);
        }

        public final void handleMessage(Message message) {
            if (((ThunderTask) this.a.get()) != null) {
            }
        }
    }

    static {
        TAG = ThunderTask.class.getSimpleName();
    }

    public ThunderTask() {
        this.mCreateExsitTaskErrorDialog = null;
        this.mCreateTaskFailedDialog = null;
        this.mBatch = false;
        this.mBatchNum = 0;
        this.mBatchSucc = 0;
        this.mBatchFail = 0;
        this.mBatchErrorCode = -1;
        this.mBatchErrorTaskId = -1;
        initHandler();
    }

    private void initHandler() {
        this.handler = new b(this);
    }

    public void onDestroy() {
        super.onDestroy();
        this.mCreateExsitTaskErrorDialog = null;
        this.mCreateTaskFailedDialog = null;
        this.mBatchCreateDialog = null;
    }

    public void onCreateTask(boolean z, int i) {
        if (5 != i && 18 != i && 16 != i && 17 != i && 13 != i && 19 != i && 26 != i && 30 != i && 37 != i && 38 != i && 29 != i && 28 != i && 41 != i && 2 != i && !(this instanceof DownloadCenterActivity)) {
            finish();
        }
    }

    public void createLocalTask(String str, com.xunlei.downloadprovider.model.protocol.c.b bVar, String str2, long j, String str3, String str4, String str5, int i, com.xunlei.downloadprovider.model.g gVar, Handler handler) {
        new StringBuilder("createLocalTask url=").append(str).append(",resourceId=").append(bVar.a);
        createLocalTaskImpl(0, str, str2, null, null, j, str3, str4, gVar, null, i, str5, handler, null);
    }

    public void createLocalTask(String str, long j, String str2, long j2, String str3, String str4, String str5, int i, com.xunlei.downloadprovider.model.g gVar, Handler handler) {
        new StringBuilder("createLocalTask url=").append(str).append(",resourceId=").append(j);
        createLocalTaskImpl(0, str, str2, null, null, j2, str3, str4, gVar, null, i, str5, handler, null);
    }

    public void createLocalTask(String str, String str2, long j, String str3, String str4, String str5, int i, com.xunlei.downloadprovider.model.g gVar, Handler handler, boolean z) {
        createLocalTaskImpl(0, str, str2, null, null, j, str3, str4, gVar, null, i, str5, handler, null);
    }

    public void createLocalTaskByCid(String str, long j, String str2, String str3, String str4, int i, com.xunlei.downloadprovider.model.g gVar, Handler handler, Boolean bool, String str5) {
        createLocalTaskImpl(1, str5, str, str2, str3, j, null, null, gVar, null, i, str4, handler, null);
    }

    public void createLocalTaskByGcid(String str, long j, String str2, String str3, String str4, int i, com.xunlei.downloadprovider.model.g gVar, Handler handler) {
        createLocalTaskImpl(1, null, str, str2, str3, j, null, null, gVar, null, i, str4, handler, null);
    }

    public void createLocalTaskByGcid(String str, long j, String str2, String str3, String str4, int i, com.xunlei.downloadprovider.model.g gVar, Handler handler, boolean z) {
        createLocalTaskImpl(1, null, str, str2, str3, j, null, null, gVar, null, i, str4, handler, null);
    }

    protected void createLocalTaskImpl(int i, String str, String str2, String str3, String str4, long j, String str5, String str6, com.xunlei.downloadprovider.model.g gVar, String str7, int i2, String str8, Handler handler, com.xunlei.downloadprovider.service.downloads.task.b bVar) {
        if (DownloadService.a() != null) {
            String str9;
            String str10;
            if (gVar == null || !TextUtils.isEmpty(str7)) {
                str9 = str7;
            } else {
                if (TextUtils.isEmpty(gVar.d)) {
                    gVar.d = com.xunlei.downloadprovider.model.g.a(gVar.e);
                }
                str9 = gVar.d;
            }
            autoChangeDownloadPath();
            if (TextUtils.isEmpty(str)) {
                str10 = str5;
            } else {
                str10 = str;
            }
            com.xunlei.downloadprovider.download.tasklist.list.xzb.a aVar = new com.xunlei.downloadprovider.download.tasklist.list.xzb.a();
            aVar.a = str10;
            aVar.b = str2;
            e.a();
            if (e.b() == 2) {
                e.a().a((Context) this, aVar, SaveToXZBEntry.other);
                createLocalTaskImplEx(i, str, str2, str3, str4, j, str5, str6, gVar, str9, i2, str8, handler, bVar);
                return;
            }
            e.a();
            if (e.b() == 1) {
                e.a().a((Context) this, aVar, SaveToXZBEntry.other);
                return;
            }
            e.a();
            if (e.b() == 0) {
                createLocalTaskImplEx(i, str, str2, str3, str4, j, str5, str6, gVar, str9, i2, str8, handler, bVar);
            }
        }
    }

    protected void createLocalTaskImplEx(int i, String str, String str2, String str3, String str4, long j, String str5, String str6, com.xunlei.downloadprovider.model.g gVar, String str7, int i2, String str8, Handler handler, com.xunlei.downloadprovider.service.downloads.task.b bVar) {
        String str9;
        if (gVar == null || !TextUtils.isEmpty(str7)) {
            str9 = str7;
        } else {
            if (TextUtils.isEmpty(gVar.d)) {
                gVar.d = com.xunlei.downloadprovider.model.g.a(gVar.e);
            }
            str9 = gVar.d;
        }
        BrothersApplication.a().getApplicationContext();
        if (com.xunlei.downloadprovider.businessutil.a.b()) {
            Handler handler2;
            if (handler == null) {
                handler2 = BrothersApplication.a().e;
            } else {
                handler2 = handler;
            }
            if (!com.xunlei.xllib.a.b.a(BrothersApplication.a().getApplicationContext())) {
                if (i == 0) {
                    DownloadService.a().a(str, str2, j, str5, str6, true, gVar.a, str9, i2, handler2, bVar);
                } else if (str3 != null || str4 == null) {
                    DownloadService.a().a(str2, str6, str3, j, str4, gVar.a, str9, i2, handler2);
                } else {
                    DownloadService.a().a(str2, str6, str4, j, gVar.a, str9, i2, handler2);
                }
                onCreateTask(true, gVar.a);
                return;
            } else if (com.xunlei.xllib.a.b.h(BrothersApplication.a().getApplicationContext()) || isBatch()) {
                if (i == 0) {
                    DownloadService.a().a(str, str2, j, str5, str6, false, gVar.a, str9, i2, handler2, bVar);
                } else if (str3 != null || str4 == null) {
                    DownloadService.a().a(str2, str6, str3, j, str4, gVar.a, str9, i2, handler2);
                } else {
                    DownloadService.a().a(str2, str6, str4, j, gVar.a, str9, i2, handler2);
                }
                onCreateTask(true, gVar.a);
                return;
            } else {
                createLocalTaskImplForMobileNetwork(i, str, str2, str3, str4, j, str5, str6, gVar, str9, i2, str8, handler2, bVar);
                return;
            }
        }
        onCreateTask(false, gVar.a);
        XLToast.a(getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, getString(2131233234));
        if (this.mBatchCreateDialog != null) {
            this.mBatchCreateDialog.dismiss();
        }
    }

    private void createLocalTaskImplForMobileNetwork(int i, String str, String str2, String str3, String str4, long j, String str5, String str6, com.xunlei.downloadprovider.model.g gVar, String str7, int i2, String str8, Handler handler, com.xunlei.downloadprovider.service.downloads.task.b bVar) {
        String string = BrothersApplication.a().getApplicationContext().getString(2131231751);
        String string2 = BrothersApplication.a().getApplicationContext().getString(2131231746);
        String string3 = BrothersApplication.a().getApplicationContext().getString(2131231749);
        a aVar = new a(i, str2, str, str3, str4, j, str5, str6, gVar, str7, i2, str8, handler);
        showCreateTaskAlertDialogForMobileNetwork(string, string2, string3, new a(this, aVar, bVar), new b(this, aVar), true);
    }

    private void showCreateTaskAlertDialogForMobileNetwork(String str, String str2, String str3, OnClickListener onClickListener, OnClickListener onClickListener2, boolean z) {
        if (!isFinishing()) {
            if (this.mCreateTaskAlertDialog == null) {
                this.mCreateTaskAlertDialog = new d(this);
            }
            this.mCreateTaskAlertDialog.a((CharSequence) str);
            this.mCreateTaskAlertDialog.b(false);
            this.mCreateTaskAlertDialog.a(z);
            this.mCreateTaskAlertDialog.d(str2);
            this.mCreateTaskAlertDialog.b(onClickListener);
            this.mCreateTaskAlertDialog.c(str3);
            this.mCreateTaskAlertDialog.a(onClickListener2);
            if (!this.mCreateTaskAlertDialog.isShowing()) {
                try {
                    this.mCreateTaskAlertDialog.show();
                } catch (BadTokenException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private void autoChangeDownloadPath() {
        boolean z = false;
        com.xunlei.downloadprovider.service.downloads.task.d.a();
        if (com.xunlei.downloadprovider.service.downloads.task.d.b()) {
            String a;
            if ("##noexist##".equals(com.xunlei.downloadprovider.businessutil.a.a(-1, false))) {
                a = com.xunlei.downloadprovider.businessutil.a.a();
                com.xunlei.downloadprovider.service.downloads.task.d.a();
                com.xunlei.downloadprovider.service.downloads.task.d.a(a);
                return;
            }
            int c = com.xunlei.downloadprovider.businessutil.b.a().c();
            String b = k.b();
            String c2 = k.c();
            boolean z2 = TextUtils.isEmpty(c2) || c2.trim().length() == 0 || k.a(c2) == 0;
            if (TextUtils.isEmpty(b) || b.trim().length() == 0 || k.a(b) == 0) {
                z = true;
            }
            if (!z2 || !z) {
                if (z2) {
                    a = b + com.xunlei.downloadprovider.businessutil.a.a(1, true);
                    if (!(TextUtils.isEmpty(a) || a.endsWith("/"))) {
                        a = a + "/";
                    }
                    com.xunlei.downloadprovider.service.downloads.task.d.a();
                    com.xunlei.downloadprovider.service.downloads.task.d.a(a);
                } else if (z) {
                    a = c2 + com.xunlei.downloadprovider.businessutil.a.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, true);
                    if (!(TextUtils.isEmpty(a) || a.endsWith("/"))) {
                        a = a + "/";
                    }
                    com.xunlei.downloadprovider.service.downloads.task.d.a();
                    com.xunlei.downloadprovider.service.downloads.task.d.a(a);
                } else if (c == 2) {
                    a = c2 + com.xunlei.downloadprovider.businessutil.a.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE, true);
                    if (!(TextUtils.isEmpty(a) || a.endsWith("/"))) {
                        a = a + "/";
                    }
                    com.xunlei.downloadprovider.service.downloads.task.d.a();
                    com.xunlei.downloadprovider.service.downloads.task.d.a(a);
                } else if (c == 1) {
                    a = b + com.xunlei.downloadprovider.businessutil.a.a(1, true);
                    if (!(TextUtils.isEmpty(a) || a.endsWith("/"))) {
                        a = a + "/";
                    }
                    com.xunlei.downloadprovider.service.downloads.task.d.a();
                    com.xunlei.downloadprovider.service.downloads.task.d.a(a);
                }
            }
        }
    }

    public boolean handleTaskOperator(int i, int i2, long j, TaskInfo taskInfo) {
        if (i != 101) {
            return false;
        }
        showCreateTaskError(i2, j);
        return true;
    }

    public boolean isBatch() {
        return this.mBatch;
    }

    public void createTasks(List<DownData> list, Handler handler, int i, StartFromType startFromType) {
        createTasks(0, list, handler, i, startFromType);
    }

    public void createTasks(int i, List<DownData> list, Handler handler, int i2, StartFromType startFromType) {
        new StringBuilder("func createTasks time = ").append(System.currentTimeMillis());
        if (!isBatch() && list != null && !list.isEmpty()) {
            com.xunlei.downloadprovider.download.tasklist.list.xzb.a[] aVarArr = new com.xunlei.downloadprovider.download.tasklist.list.xzb.a[list.size()];
            int size = list.size();
            for (int i3 = 0; i3 < size; i3++) {
                com.xunlei.downloadprovider.download.tasklist.list.xzb.a aVar = new com.xunlei.downloadprovider.download.tasklist.list.xzb.a();
                aVar.a = ((DownData) list.get(i3)).e;
                aVar.b = ((DownData) list.get(i3)).a;
                aVarArr[i3] = aVar;
            }
            e.a();
            if (e.b() == 2) {
                e.a().a((Context) this, SaveToXZBEntry.other, aVarArr);
                if (DownloadService.a() != null) {
                    autoChangeDownloadPath();
                    createTasksImpl(i, list, handler, i2, startFromType);
                    reportDownSpecail(i);
                    return;
                }
                return;
            }
            e.a();
            if (e.b() == 1) {
                e.a().a((Context) this, SaveToXZBEntry.other, aVarArr);
                return;
            }
            e.a();
            if (e.b() == 0 && DownloadService.a() != null) {
                autoChangeDownloadPath();
                createTasksImpl(i, list, handler, i2, startFromType);
                reportDownSpecail(i);
            }
        }
    }

    private void createTasksImpl(int i, List<DownData> list, Handler handler, int i2, StartFromType startFromType) {
        int size = list.size();
        if (size > 1) {
            startBatchDialog(size);
        }
        BrowserUtil.a();
        String a = BrowserUtil.a(startFromType);
        com.xunlei.downloadprovider.notification.a.a(getApplicationContext()).f = size;
        for (int i3 = size - 1; i3 >= 0; i3--) {
            DownData downData = (DownData) list.get(i3);
            com.xunlei.downloadprovider.model.g gVar = new com.xunlei.downloadprovider.model.g(i, i2, downData.e, downData.s);
            gVar.d = com.xunlei.downloadprovider.model.g.a(i2);
            gVar.f = a;
            createTask(downData, handler, gVar, false);
        }
    }

    private String getNameWithoutSuffix(String str) {
        return (str == null || str.length() <= 0 || str.lastIndexOf(".") <= 0) ? str : str.substring(0, str.lastIndexOf("."));
    }

    public void createTask(DownData downData, Handler handler, com.xunlei.downloadprovider.model.g gVar, boolean z) {
        if (downData instanceof DownData) {
            new StringBuilder("func createTask : data = ").append(downData.a);
            if (TextUtils.isEmpty(downData.e) || isCidTaskDownData(downData)) {
                createLocalTaskByCid(downData.a, downData.r, downData.c, downData.d, downData.q, 1, gVar, handler, Boolean.valueOf(false), downData.e);
                return;
            }
            com.xunlei.downloadprovider.service.downloads.task.b bVar = new com.xunlei.downloadprovider.service.downloads.task.b();
            bVar.a = downData.B;
            bVar.b = downData.C;
            bVar.c = downData.D;
            createLocalTaskWithAdditionInfo(downData.e, downData.a, downData.r, downData.s, null, downData.q, 1, gVar, handler, false, bVar);
        }
    }

    public void createLocalTaskWithAdditionInfo(String str, String str2, long j, String str3, String str4, String str5, int i, com.xunlei.downloadprovider.model.g gVar, Handler handler, boolean z, com.xunlei.downloadprovider.service.downloads.task.b bVar) {
        createLocalTaskImpl(0, str, str2, null, null, j, str3, str4, gVar, null, i, str5, handler, bVar);
    }

    protected boolean isCidTaskDownData(DownData downData) {
        return !TextUtils.isEmpty(downData.c) && downData.r > 0;
    }

    protected void startBatchDialog(int i) {
        this.mBatch = true;
        this.mBatchNum = i;
        this.mBatchSucc = 0;
        this.mBatchFail = 0;
        this.mBatchCreateDialog = new g(this);
        this.mBatchCreateDialog.setCancelable(false);
        this.mBatchCreateDialog.a((long) this.mBatchNum);
        this.mBatchCreateDialog.b(0);
        this.mBatchCreateDialog.a(new StringBuilder("\u6b63\u5728\u521b\u5efa\u4efb\u52a10/").append(this.mBatchNum).toString());
        this.mBatchCreateDialog.show();
    }

    public void updateBatchDialog(boolean z, int i, long j, String str) {
        if (this.mBatch && this.mBatchCreateDialog != null) {
            if (z) {
                this.mBatchSucc++;
            } else {
                int i2 = this.mBatchFail;
                this.mBatchFail = i2 + 1;
                if (i2 == 0) {
                    this.mBatchErrorCode = i;
                    this.mBatchErrorTaskId = j;
                }
            }
            this.mBatchCreateDialog.b((long) (this.mBatchSucc + this.mBatchFail));
            g gVar = this.mBatchCreateDialog;
            TruncateAt truncateAt = TruncateAt.MIDDLE;
            if (!(gVar.a == null || truncateAt == null)) {
                gVar.a.setEllipsize(truncateAt);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\u6b63\u5728\u521b\u5efa\u4efb\u52a1\"");
            stringBuilder.append(str);
            stringBuilder.append("\"(");
            stringBuilder.append(this.mBatchSucc + this.mBatchFail);
            stringBuilder.append("/");
            stringBuilder.append(this.mBatchNum);
            stringBuilder.append(SocializeConstants.OP_CLOSE_PAREN);
            this.mBatchCreateDialog.a(stringBuilder.toString());
            if (this.mBatchSucc + this.mBatchFail == this.mBatchNum) {
                this.mBatch = false;
                this.mBatchCreateDialog.dismiss();
                if (this.mBatchFail > 0) {
                    showCreateTaskError(this.mBatchErrorCode, this.mBatchErrorTaskId);
                }
            }
        }
    }

    public final void showCreateTaskError(int i, long j) {
        if (!isFinishing()) {
            if (i == 102409) {
                if (this.mCreateExsitTaskErrorDialog == null) {
                    this.mCreateExsitTaskErrorDialog = new XLAlarmDialog(this);
                }
                if (!this.mCreateExsitTaskErrorDialog.isShowing()) {
                    this.mCreateExsitTaskErrorDialog.setContent(getString(2131232752));
                    this.mCreateExsitTaskErrorDialog.setConfirmButtonText(getString(2131232753));
                    this.mCreateExsitTaskErrorDialog.setOnClickConfirmButtonListener(new c(this, j));
                    this.mCreateExsitTaskErrorDialog.setOnClickCancelButtonListener(new d(this));
                    try {
                        this.mCreateExsitTaskErrorDialog.show();
                        return;
                    } catch (BadTokenException e) {
                        new StringBuilder("catched BadTokenException , msg : ").append(e.getMessage());
                    } catch (IllegalStateException e2) {
                        new StringBuilder("catched IllegalStateException , msg : ").append(e2.getMessage());
                    }
                }
                return;
            }
            if (this.mCreateTaskFailedDialog == null) {
                this.mCreateTaskFailedDialog = new q(this);
            }
            if (!this.mCreateTaskFailedDialog.isShowing()) {
                switch (i) {
                    case XZBDevice.Upload:
                        if (k.d()) {
                            this.mCreateTaskFailedDialog.a(getString(2131232751));
                        } else {
                            this.mCreateTaskFailedDialog.a(getString(2131232756));
                        }
                        break;
                    case 112:
                    case 3173:
                        this.mCreateTaskFailedDialog.a(getString(2131232757));
                        break;
                    case 102416:
                        this.mCreateTaskFailedDialog.a(getString(2131232754));
                        break;
                    case 102439:
                    case 102448:
                        this.mCreateTaskFailedDialog.a(getString(2131232755));
                        break;
                    default:
                        this.mCreateTaskFailedDialog.a(getString(2131232751));
                        break;
                }
                try {
                    this.mCreateTaskFailedDialog.show();
                } catch (BadTokenException e3) {
                    new StringBuilder("catched BadTokenException , msg : ").append(e3.getMessage());
                } catch (IllegalStateException e22) {
                    new StringBuilder("catched IllegalStateException , msg : ").append(e22.getMessage());
                }
            }
        }
    }

    private void reportDownSpecail(int i) {
        if ((i & 2048) == 2048) {
            StatReporter.reportChannelDownOrPlay("downlaod");
        }
    }

    public void onClickDialogCancel() {
    }
}
