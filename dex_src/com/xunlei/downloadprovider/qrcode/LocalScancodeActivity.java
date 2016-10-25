package com.xunlei.downloadprovider.qrcode;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore.Images.Media;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.zxing.common.i;
import com.google.zxing.e;
import com.google.zxing.j;
import com.google.zxing.m;
import com.google.zxing.n;
import com.sina.weibo.sdk.component.GameManager;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.xunlei.download.Downloads.Impl;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.commonview.dialog.XLAlarmDialog;
import com.xunlei.downloadprovider.commonview.dialog.XLBaseDialog;
import com.xunlei.downloadprovider.download.center.DownloadCenterActivity;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.model.protocol.g.k;
import com.xunlei.downloadprovider.model.protocol.g.l;
import com.xunlei.downloadprovider.qrcode.view.ScancodeResultUrlView;
import com.xunlei.downloadprovider.qrcode.view.ScancodeResultUrlView.TXTVIEW_TYPE;
import com.xunlei.downloadprovider.qrcode.view.b;
import com.xunlei.downloadprovider.qrcode.view.c;
import com.xunlei.downloadprovider.qrcode.view.d;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.task.ThunderTask;
import com.xunlei.downloadprovider.web.BrowserUtil;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import com.xunlei.xiazaibao.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.lang.Thread.State;
import java.net.URLDecoder;
import org.android.spdy.SpdyAgent;

public class LocalScancodeActivity extends ThunderTask implements OnClickListener {
    private Handler A;
    private Handler B;
    private k C;
    private l D;
    private int E;
    private long F;
    private View a;
    private c b;
    private ScancodeResultUrlView c;
    private d d;
    private b e;
    private RelativeLayout f;
    private Button g;
    private ImageView h;
    private ImageView i;
    private View j;
    private TextView k;
    private int l;
    private int m;
    private String n;
    private boolean o;
    private String p;
    private String q;
    private long r;
    private String s;
    private String t;
    private String u;
    private a v;
    private com.xunlei.downloadprovider.qrcode.b.c w;
    private long x;
    private XLBaseDialog y;
    private k z;

    private class a extends Thread {
        Handler a;
        private boolean c;

        private a() {
            this.c = false;
        }

        public final void destroy() {
        }

        public final void run() {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            try {
                BitmapFactory.decodeFile(LocalScancodeActivity.this.u, options);
                Object a = LocalScancodeActivity.a(LocalScancodeActivity.this.u, options.outWidth, options.outHeight);
                if (this.a != null) {
                    Message obtainMessage = this.a.obtainMessage();
                    obtainMessage.what = 2;
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("bitmap", a);
                    obtainMessage.setData(bundle);
                    if (this.a != null) {
                        this.a.sendMessage(obtainMessage);
                        String a2 = a(a);
                        if (a2 == null || a2.equals(com.umeng.a.d)) {
                            if (this.a != null) {
                                this.a.sendEmptyMessage(1);
                            } else {
                                return;
                            }
                        } else if (this.a != null) {
                            obtainMessage = this.a.obtainMessage();
                            obtainMessage.what = 0;
                            bundle = new Bundle();
                            bundle.putString("scancode_result", a2);
                            obtainMessage.setData(bundle);
                            if (this.a != null) {
                                this.a.sendMessage(obtainMessage);
                            } else {
                                return;
                            }
                        } else {
                            return;
                        }
                        super.run();
                    }
                }
            } catch (Exception e) {
                if (this.a != null) {
                    this.a.sendEmptyMessage(XZBDevice.DOWNLOAD_LIST_RECYCLE);
                }
            }
        }

        private static String a(Bitmap bitmap) {
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            int[] iArr = new int[(width * height)];
            bitmap.getPixels(iArr, 0, width, 0, 0, width, height);
            try {
                n a = new com.google.zxing.qrcode.a().a(new com.google.zxing.b(new i(new com.google.zxing.k(width, height, iArr))), null);
            } catch (j e) {
                m e2 = e;
                e2.printStackTrace();
                a = null;
                return a != null ? a.a : null;
            } catch (com.google.zxing.c e3) {
                e2 = e3;
                e2.printStackTrace();
                a = null;
                if (a != null) {
                }
            } catch (e e4) {
                e2 = e4;
                e2.printStackTrace();
                a = null;
                if (a != null) {
                }
            }
            if (a != null) {
            }
        }
    }

    public LocalScancodeActivity() {
        this.x = 0;
        this.A = new i(this);
        this.B = new j(this);
        this.E = -1;
        this.F = -1;
    }

    static /* synthetic */ Bitmap a(String str, int i, int i2) {
        int i3;
        if (i * i2 < 384000) {
            i3 = 1;
        } else {
            float f = ((float) i) / 800.0f;
            float f2 = ((float) i2) / 480.0f;
            if (f <= f2) {
                f = f2;
            }
            i3 = f > ((float) ((int) f)) ? ((int) f) + 1 : (int) f;
        }
        Options options = new Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = i3;
        Bitmap decodeFile = BitmapFactory.decodeFile(str, options);
        new StringBuilder("\u5bbd\u5ea61=").append(decodeFile.getWidth());
        new StringBuilder("\u9ad8\u5ea61=").append(decodeFile.getWidth());
        if (decodeFile.getWidth() <= 800 || decodeFile.getHeight() <= 480) {
            return decodeFile;
        }
        i3++;
        options = new Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = i3;
        return BitmapFactory.decodeFile(str, options);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968859);
        BrothersApplication.a().e.a = this;
        View findViewById = findViewById(2131756437);
        this.j = findViewById.findViewById(R.id.titlebar_left);
        this.k = (TextView) findViewById.findViewById(R.id.titlebar_title);
        this.k.setText("\u4ece\u76f8\u518c\u9009\u62e9\u4e8c\u7ef4\u7801");
        this.j.setOnClickListener(new g(this));
        findViewById.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        findViewById(2131756438).setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.a = findViewById(2131756440);
        this.i = (ImageView) findViewById(2131756439);
        this.i.setVisibility(XZBDevice.DOWNLOAD_LIST_ALL);
        this.h = (ImageView) findViewById(2131756841);
        this.b = new c(this, findViewById(2131756840));
        c cVar = this.b;
        cVar.b = (TextView) cVar.a.findViewById(2131756847);
        cVar.c = (TextView) cVar.a.findViewById(2131756848);
        this.c = new ScancodeResultUrlView(findViewById(2131756842));
        ScancodeResultUrlView scancodeResultUrlView = this.c;
        scancodeResultUrlView.e = (ImageView) scancodeResultUrlView.a.findViewById(2131756849);
        scancodeResultUrlView.b = (TextView) scancodeResultUrlView.a.findViewById(2131756851);
        scancodeResultUrlView.c = (TextView) scancodeResultUrlView.a.findViewById(2131756850);
        scancodeResultUrlView.d = (TextView) scancodeResultUrlView.a.findViewById(2131756852);
        this.d = new d(findViewById(2131756843));
        d dVar = this.d;
        dVar.b = (ImageView) dVar.a.findViewById(2131756853);
        dVar.c = (TextView) dVar.a.findViewById(2131756854);
        this.e = new b(findViewById(2131756844));
        b bVar = this.e;
        bVar.b = (ImageView) bVar.a.findViewById(2131756831);
        bVar.c = (TextView) bVar.a.findViewById(2131756832);
        this.f = (RelativeLayout) findViewById(2131756845);
        this.g = (Button) findViewById(2131756846);
        this.g.setOnClickListener(this);
        this.w = com.xunlei.downloadprovider.qrcode.b.c.a((Context) this);
        try {
            Intent intent = new Intent("android.intent.action.PICK", Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(intent, XZBDevice.DOWNLOAD_LIST_RECYCLE);
        } catch (Exception e) {
            XLToast.a(getApplicationContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u6253\u5f00\u56fe\u518c\u5931\u8d25");
            finish();
        }
    }

    public void onResume() {
        super.onResume();
    }

    public void logOutNotByUser(DialogInterface.OnClickListener onClickListener) {
    }

    public void onPause() {
        if (this.v != null) {
            this.v.a = null;
            this.v.destroy();
            this.v = null;
        }
        super.onPause();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent == null) {
            e();
            return;
        }
        findViewById(2131756437).setVisibility(0);
        findViewById(2131756438).setVisibility(0);
        this.i.setVisibility(0);
        this.i.setImageBitmap(null);
        this.u = com.umeng.a.d;
        this.a.setVisibility(0);
        this.l = 65;
        b();
        Uri data = intent.getData();
        this.u = Uri.decode(intent.getDataString());
        this.u = a(data, this.u);
        if (this.v == null) {
            this.v = new a();
            this.v.a = this.A;
            a aVar = this.v;
            if (State.NEW == aVar.getState()) {
                aVar.start();
            }
        }
    }

    private boolean a() {
        if (this.o) {
            String toString;
            if (this.r > 0) {
                toString = new StringBuilder("\u6587\u4ef6\u5927\u5c0f: ").append(com.xunlei.downloadprovider.d.a.a(this.r, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString();
            } else {
                toString = "\u6587\u4ef6\u5927\u5c0f: \u672a\u77e5";
            }
            this.c.c(this.m);
            this.c.a(TXTVIEW_TYPE.File_Name, this.q);
            this.c.a(TXTVIEW_TYPE.File_Size, toString);
            this.c.a(TXTVIEW_TYPE.File_Url, this.s);
            this.c.b(0);
        } else {
            this.c.a(TXTVIEW_TYPE.File_Name, this.s);
            this.c.c(this.m);
            this.c.b(1);
        }
        this.c.a(0);
        this.f.setVisibility(0);
        if ((this.l & 1) == 1) {
            this.g.setText("\u786e\u8ba4\u4e0b\u8f7d");
        } else {
            this.g.setText("\u6253\u5f00\u94fe\u63a5");
        }
        return true;
    }

    private boolean b() {
        if ((this.l & 1) == 1) {
            this.d.a(1);
            this.d.a("\u4e8c\u7ef4\u7801\u89e3\u6790\u4e2d\uff0c\u8bf7\u7a0d\u5019");
            this.f.setVisibility(XZBDevice.Wait);
        } else {
            this.d.a((int) XZBDevice.DOWNLOAD_LIST_RECYCLE);
            this.d.a("\u4fe1\u606f\u83b7\u53d6\u5931\u8d25");
            this.g.setText("\u91cd\u8bd5");
            this.f.setVisibility(0);
        }
        this.d.b(0);
        return true;
    }

    private boolean c() {
        c cVar = this.b;
        CharSequence charSequence = this.p;
        cVar.c.setMovementMethod(ScrollingMovementMethod.getInstance());
        cVar.c.scrollTo(0, 0);
        try {
            charSequence = URLDecoder.decode(charSequence, GameManager.DEFAULT_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cVar.c.setText(charSequence);
        this.b.b.setText("\u6587\u672c\u5185\u5bb9");
        this.b.a(0);
        this.f.setVisibility(0);
        this.g.setText("\u590d\u5236\u6587\u672c");
        return true;
    }

    private void d() {
        if (this.a.getVisibility() != 8) {
            this.a.setAnimation(AnimationUtils.loadAnimation(this, 2131034183));
            this.a.setVisibility(XZBDevice.Wait);
        }
    }

    private void a(boolean z) {
        boolean c;
        this.e.a(XZBDevice.Wait);
        this.b.a(XZBDevice.Wait);
        this.c.a(XZBDevice.Wait);
        this.d.b(XZBDevice.Wait);
        this.h.setVisibility(XZBDevice.Wait);
        if ((this.l & 16) == 16 && (this.l & 32) == 32) {
            this.h.setVisibility(0);
        }
        if ((this.l & 16) == 16) {
            c = c();
        } else {
            c = false;
        }
        if ((this.l & 32) == 32) {
            c = a();
        }
        if ((this.l & 64) == 64) {
            c = b();
        }
        if ((this.l & 128) == 128) {
            if ((this.l & 1) == 1) {
                this.e.b.setBackgroundResource(com.xunlei.tdlive.R.drawable.dlg_icon_fail);
                b bVar = this.e;
                bVar.c.setText(this.t);
                this.e.a(0);
            } else if ((this.l & 2) == 2) {
                this.a.setVisibility(XZBDevice.Wait);
                a("\u89e3\u6790\u4e8c\u7ef4\u7801\u5931\u8d25");
                c = false;
            }
            c = true;
        }
        if (c) {
            if (z) {
                Animation loadAnimation = AnimationUtils.loadAnimation(this, 2131034184);
                loadAnimation.setInterpolator(new DecelerateInterpolator());
                loadAnimation.setAnimationListener(new h(this));
                this.a.setAnimation(loadAnimation);
            }
            this.a.setVisibility(0);
            return;
        }
        this.a.setVisibility(XZBDevice.Wait);
    }

    private String a(Uri uri, String str) {
        String str2 = null;
        if (uri == null) {
            return null;
        }
        if (uri.getScheme().toString().compareTo(ParamKey.CONTENT) != 0) {
            return (uri.getScheme().compareTo("file") != 0 || str == null) ? null : str.replace("file://", com.umeng.a.d);
        } else {
            Cursor query = getContentResolver().query(uri, null, null, null, null);
            try {
                int columnIndexOrThrow = query.getColumnIndexOrThrow(Impl._DATA);
                if (columnIndexOrThrow >= 0) {
                    query.moveToFirst();
                    str2 = query.getString(columnIndexOrThrow);
                }
                query.close();
                return str2;
            } catch (Exception e) {
                e.getMessage();
                return null;
            }
        }
    }

    private void a(String str, String str2, int i) {
        if (DownloadService.a() != null) {
            if (this.C != null) {
                com.xunlei.downloadprovider.a.b.d();
                String str3 = this.C.k;
            }
            createLocalTask(str, null, 0, null, null, null, 0, new g(i, str, str2), null, false);
        }
    }

    protected void onCreateTask(boolean z, int i) {
        if (z && this.C != null && this.C.j != null) {
            if (12 == i || 15 == i) {
                new com.xunlei.downloadprovider.model.protocol.g.e(null, null).a(this.C.j);
            }
        }
    }

    public boolean handleTaskOperator(int i, int i2, long j, TaskInfo taskInfo) {
        this.F = j;
        if (i != 101) {
            DownloadCenterActivity.a(this, j, com.umeng.a.d);
        }
        return super.handleTaskOperator(i, i2, j, taskInfo);
    }

    private void e() {
        if (this.v != null) {
            this.v.a = null;
        }
        finish();
    }

    public void onClick(View view) {
        int i = this.l & 15;
        if ((this.l & 32) == 32) {
            if (i == 1) {
                if (this.D == null) {
                    a(this.s, null, 1);
                } else if (this.C != null) {
                    a(this.C.b, this.C.e, (int) XZBDevice.Fail);
                } else {
                    a(this.s, null, (int) XZBDevice.Fail);
                }
                d();
            } else if (i == 2) {
                BrowserUtil.a();
                BrowserUtil.a((Context) this, this.s.trim(), StartFromType.scan_qrcode);
            }
        } else if ((this.l & 16) == 16) {
            ((ClipboardManager) getSystemService("clipboard")).setText(this.p);
            XLToast.a(getApplicationContext(), XLToastType.XLTOAST_TYPE_SMILE, "\u5df2\u590d\u5236\u5230\u526a\u8d34\u7248");
            d();
        } else if ((this.l & 64) == 64) {
            this.l = 64;
            this.l = 65;
            a(false);
            com.xunlei.downloadprovider.model.protocol.c.a();
            this.E = com.xunlei.downloadprovider.model.protocol.c.b(this.n, this.B, com.umeng.a.d);
            this.x = System.currentTimeMillis();
        }
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (4 != i) {
            return super.onKeyUp(i, keyEvent);
        }
        e();
        return true;
    }

    public final void a(String str) {
        XLAlarmDialog xLAlarmDialog = new XLAlarmDialog(this);
        xLAlarmDialog.setContent(str);
        xLAlarmDialog.setConfirmButtonText("\u91cd\u65b0\u9009\u62e9");
        xLAlarmDialog.setOnClickCancelButtonListener(new k(this));
        xLAlarmDialog.setOnClickConfirmButtonListener(new l(this));
        xLAlarmDialog.show();
    }

    private void b(String str) {
        XLBaseDialog iVar = new com.xunlei.downloadprovider.qrcode.a.i(this, this.B, str, com.xunlei.downloadprovider.util.c.a.b(str));
        this.y = iVar;
        iVar.show();
    }

    private void a(String str, String str2) {
        new StringBuilder("createNewTaskByUrl url=").append(str).append(",refUrl=").append(null);
        if (DownloadService.a() != null) {
            if (this.z != null) {
                String str3 = this.z.k;
            }
            g gVar = new g(1, str, null);
            gVar.d = "manual/manual_codeScan";
            createLocalTask(str, str2, 0, null, null, null, 0, gVar, null, false);
        }
    }

    static /* synthetic */ void b(LocalScancodeActivity localScancodeActivity, String str) {
        if (!TextUtils.isEmpty(str)) {
            XLBaseDialog aVar;
            switch (com.xunlei.downloadprovider.util.c.a.b(str)) {
                case SpdyAgent.ACCS_ONLINE_SERVER:
                    localScancodeActivity.b(str);
                case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                    if (l.a(str) == null) {
                        localScancodeActivity.b(str);
                    } else if (com.xunlei.xllib.a.b.h(localScancodeActivity) || com.xunlei.xllib.a.b.g(localScancodeActivity)) {
                        aVar = new com.xunlei.downloadprovider.qrcode.a.a(localScancodeActivity, localScancodeActivity.B, str);
                        localScancodeActivity.y = aVar;
                        aVar.show();
                        aVar.a();
                    } else {
                        aVar = new com.xunlei.downloadprovider.qrcode.a.a(localScancodeActivity, localScancodeActivity.B, null);
                        localScancodeActivity.y = aVar;
                        aVar.b();
                        aVar.show();
                    }
                case XZBDevice.DOWNLOAD_LIST_FAILED:
                    aVar = new com.xunlei.downloadprovider.qrcode.a.e(localScancodeActivity, localScancodeActivity.B, str);
                    localScancodeActivity.y = aVar;
                    aVar.show();
                default:
                    break;
            }
        }
    }

    static /* synthetic */ void e(LocalScancodeActivity localScancodeActivity, String str) {
        if (str.startsWith("fileName=")) {
            String substring;
            try {
                substring = str.substring(XZBDevice.Pause, str.indexOf(";h"));
                try {
                    str = str.substring(str.indexOf(";h") + 1, str.length());
                } catch (Exception e) {
                }
            } catch (Exception e2) {
                substring = null;
            }
            com.xunlei.downloadprovider.businessutil.d.a a = com.xunlei.downloadprovider.businessutil.d.a(str);
            if (a == null || DownloadService.a() == null) {
                localScancodeActivity.a(str, substring);
                return;
            }
            LocalScancodeActivity localScancodeActivity2 = localScancodeActivity;
            localScancodeActivity2.createLocalTaskByGcid(a.b, a.e, a.c, a.d, null, 1, new g(1, str, null), null);
            return;
        }
        localScancodeActivity.a(str, null);
    }
}
