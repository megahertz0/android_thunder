package com.xunlei.downloadprovider.filemanager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.xiaomi.mipush.sdk.MiPushClient;
import com.xunlei.downloadprovider.a.h;
import com.xunlei.downloadprovider.a.h.b;
import com.xunlei.downloadprovider.app.BaseActivity;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.d;
import com.xunlei.downloadprovider.commonview.dialog.x;
import com.xunlei.downloadprovider.filemanager.model.i;
import com.xunlei.downloadprovider.filemanager.ui.f;
import com.xunlei.downloadprovider.filemanager.ui.g;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class FileManagerBaseActivity extends BaseActivity {
    protected static final int a;
    protected f b;
    protected com.xunlei.downloadprovider.filemanager.ui.a c;
    protected g d;
    protected com.xunlei.downloadprovider.commonview.dialog.g e;
    protected x f;
    protected b g;
    protected boolean h;
    private a i;

    private final class a implements com.xunlei.downloadprovider.a.h.a {
        private int b;

        private a() {
            this.b = 0;
        }

        public final void a(Message message) {
            if (message.what == com.xunlei.downloadprovider.filemanager.model.b.c) {
                List list = null;
                if (FileManagerBaseActivity.this.e != null && FileManagerBaseActivity.this.e.isShowing()) {
                    FileManagerBaseActivity.this.e.dismiss();
                    list = (List) message.obj;
                    XLToast.a(FileManagerBaseActivity.this.getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, new StringBuilder("\u6210\u529f\u5220\u9664").append(list.size() - this.b).append("\u4e2a\u6587\u4ef6").toString());
                }
                this.b = 0;
                FileManagerBaseActivity.this.b(list);
            } else if (message.what == com.xunlei.downloadprovider.filemanager.model.b.a) {
                if (FileManagerBaseActivity.this.e != null && FileManagerBaseActivity.this.e.isShowing()) {
                    FileManagerBaseActivity.this.e.b((long) message.arg1);
                    FileManagerBaseActivity.this.e.a((long) message.arg2);
                }
            } else if (message.what == com.xunlei.downloadprovider.filemanager.model.b.b) {
                this.b++;
            } else if (message.what == 1100 || message.what == 1101) {
                FileManagerBaseActivity.this.a();
            } else if (message.what == com.xunlei.downloadprovider.filemanager.model.b.d) {
                FileManagerBaseActivity.this.b();
                FileManagerBaseActivity.this.a((com.xunlei.downloadprovider.filemanager.ui.g.a) message.obj);
            }
            FileManagerBaseActivity.c();
        }
    }

    public FileManagerBaseActivity() {
        this.h = false;
    }

    static {
        a = h.a();
    }

    protected void onCreate(Bundle bundle) {
        Intent intent = getIntent();
        if (intent != null) {
            this.h = intent.getBooleanExtra("just_share", false);
        }
        this.i = new a();
        this.g = new b(this.i);
        super.onCreate(bundle);
    }

    protected final void a(com.xunlei.downloadprovider.filemanager.ui.f.a aVar) {
        if (this.b == null) {
            this.b = new f(this);
        }
        this.b.e.setVisibility(0);
        this.b.f.setVisibility(0);
        this.b.h = aVar;
        if (!this.b.isShowing()) {
            this.b.show();
        }
    }

    protected final void a(i iVar, int i) {
        if (this.c == null) {
            this.c = new com.xunlei.downloadprovider.filemanager.ui.a(this);
        }
        com.xunlei.downloadprovider.filemanager.ui.a aVar = this.c;
        CharSequence a = iVar.a();
        CharSequence charSequence = iVar.g;
        long j = iVar.i;
        long j2 = iVar.h;
        aVar.e.setVisibility(0);
        aVar.f.setVisibility(XZBDevice.Wait);
        aVar.a.setText(a);
        aVar.b.setText(charSequence);
        aVar.c.setText(com.xunlei.downloadprovider.d.a.b(j));
        aVar.d.setText(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(j2)));
        aVar.e.setVisibility(XZBDevice.Wait);
        aVar.f.setVisibility(0);
        String str;
        File file;
        if (i == EFileCategoryType.E_XLDIR_CATEGORY.ordinal()) {
            aVar = this.c;
            str = iVar.g;
            file = new File(str);
            if (!file.exists()) {
                aVar.c.setText("\u6587\u4ef6\u4e0d\u5b58\u5728");
            } else if (file.isDirectory()) {
                aVar.c.setText("...");
                new com.xunlei.downloadprovider.filemanager.ui.b(aVar, str).start();
            } else {
                aVar.c.setText(com.xunlei.downloadprovider.d.a.b(file.length()));
            }
        } else {
            aVar = this.c;
            str = iVar.g;
            file = new File(str);
            if (!file.exists()) {
                aVar.c.setText(com.xunlei.downloadprovider.d.a.b(0));
            } else if (file.isDirectory()) {
                aVar.c.setText("...");
                new com.xunlei.downloadprovider.filemanager.a.b(new com.xunlei.downloadprovider.filemanager.a.a(i, str), aVar.g).start();
            } else {
                aVar.c.setText(com.xunlei.downloadprovider.d.a.b(file.length()));
            }
        }
        if (!this.c.isShowing()) {
            this.c.show();
        }
    }

    protected final void a(i iVar, Handler handler) {
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
        }
        this.d = new g(this, iVar, handler);
        this.d.show();
    }

    protected void a(com.xunlei.downloadprovider.filemanager.ui.g.a aVar) {
    }

    protected final void a(List<i> list) {
        if (com.xunlei.downloadprovider.filemanager.model.b.a((Collection) list) == 0) {
            XLToast.a(this, XLToastType.XLTOAST_TYPE_ALARM, "\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u4e2a\u6587\u4ef6\u6216\u8005\u6587\u4ef6\u5939");
            return;
        }
        int i = 0;
        int i2 = 0;
        for (i iVar : list) {
            if (iVar.a) {
                if (iVar.g()) {
                    i2++;
                } else {
                    i++;
                }
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("\u786e\u5b9a\u5220\u9664\u9009\u4e2d\u7684");
        if (i2 != 0) {
            stringBuffer.append(i2).append("\u4e2a\u6587\u4ef6\u5939");
            if (i != 0) {
                stringBuffer.append(MiPushClient.ACCEPT_TIME_SEPARATOR);
            }
        }
        if (i != 0) {
            stringBuffer.append(i).append("\u4e2a\u6587\u4ef6");
        }
        stringBuffer.append("\u5417?");
        d dVar = new d(this);
        dVar.setTitle((CharSequence) "\u63d0\u793a");
        dVar.a(stringBuffer.toString());
        dVar.b(new c(this, list));
        dVar.a(new d(this));
        dVar.show();
    }

    protected void b(List<i> list) {
    }

    protected final void a() {
        if (this.f == null) {
            this.f = new x(this);
            this.f.a(null);
        }
        if (!this.f.isShowing()) {
            this.f.show();
        }
    }

    protected final void b() {
        if (this.f != null && this.f.isShowing()) {
            this.f.dismiss();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected static void c() {
    }

    static /* synthetic */ void a(FileManagerBaseActivity fileManagerBaseActivity, Collection collection) {
        List c = com.xunlei.downloadprovider.filemanager.model.b.c(collection);
        if (fileManagerBaseActivity.e == null) {
            fileManagerBaseActivity.e = new com.xunlei.downloadprovider.commonview.dialog.g(fileManagerBaseActivity);
        }
        fileManagerBaseActivity.e.a("\u6b63\u5728\u5220\u9664\u6587\u4ef6\uff0c\u8bf7\u7a0d\u5019\uff01");
        fileManagerBaseActivity.e.a((long) c.size());
        fileManagerBaseActivity.e.b(0);
        fileManagerBaseActivity.e.setOnKeyListener(new e(fileManagerBaseActivity));
        fileManagerBaseActivity.e.show();
        com.xunlei.downloadprovider.filemanager.model.b.a(c, fileManagerBaseActivity.g);
    }

    static /* synthetic */ void a(FileManagerBaseActivity fileManagerBaseActivity, List list) {
        if (fileManagerBaseActivity.e == null) {
            fileManagerBaseActivity.e = new com.xunlei.downloadprovider.commonview.dialog.g(fileManagerBaseActivity);
        }
        fileManagerBaseActivity.e.a("\u6b63\u5728\u5220\u9664\u6587\u4ef6\uff0c\u8bf7\u7a0d\u5019\uff01");
        fileManagerBaseActivity.e.a((long) list.size());
        fileManagerBaseActivity.e.b(0);
        fileManagerBaseActivity.e.setOnKeyListener(new f(fileManagerBaseActivity));
        fileManagerBaseActivity.e.show();
        com.xunlei.downloadprovider.filemanager.model.b.b(list, fileManagerBaseActivity.g);
    }
}
