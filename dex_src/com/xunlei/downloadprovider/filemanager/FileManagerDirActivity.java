package com.xunlei.downloadprovider.filemanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.filemanager.model.b;
import com.xunlei.downloadprovider.filemanager.model.h;
import com.xunlei.downloadprovider.filemanager.model.i;
import com.xunlei.downloadprovider.filemanager.ui.FileManagerDirView;
import com.xunlei.downloadprovider.filemanager.ui.FileManagerDirView.d;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileManagerDirActivity extends FileManagerBaseActivity implements OnClickListener, OnItemClickListener {
    private int i;
    private FileManagerDirView j;
    private TextView k;
    private ImageView l;
    private ImageView m;
    private ImageView n;
    private TextView o;
    private ListView p;
    private LayoutInflater q;
    private PopupWindow r;
    private a s;
    private String t;
    private RelativeLayout u;
    private String v;
    private String w;
    private int x;
    private d y;
    private com.xunlei.downloadprovider.filemanager.ui.f.a z;

    public class a extends BaseAdapter {
        private List<com.xunlei.downloadprovider.filemanager.ui.FileManagerDirView.a> b;
        private String c;
        private String d;

        public a() {
            this.c = k.b();
            this.d = k.c();
        }

        public final int getCount() {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            return this.b.size();
        }

        public final Object getItem(int i) {
            if (this.b == null) {
                this.b = new ArrayList();
            }
            return this.b.get(i);
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (this.b == null) {
                this.b = new ArrayList();
            }
            if (view == null) {
                view = FileManagerDirActivity.this.q.inflate(2130968897, null);
                aVar = new a(this, (byte) 0);
                FileManagerDirActivity.this = (TextView) view.findViewById(2131756710);
                aVar.b = (ImageView) view.findViewById(2131756709);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            String str = FileManagerDirActivity.this;
            if ("#*sdcard.choose@!~".equals(str)) {
                FileManagerDirActivity.this.setText(FileManagerDirActivity.this.getString(2131232401));
            } else if (str.equalsIgnoreCase(this.c)) {
                FileManagerDirActivity.this.setText(FileManagerDirActivity.this.getString(2131231974));
            } else if (str.equalsIgnoreCase(this.d)) {
                FileManagerDirActivity.this.setText(FileManagerDirActivity.this.getString(2131232399));
            } else {
                if (str.endsWith(File.separator)) {
                    str = str.substring(0, str.length() - 1);
                }
                FileManagerDirActivity.this.setText(str.substring(str.lastIndexOf(File.separator) + 1, str.length()));
            }
            if ("#*sdcard.choose@!~".equals(str) || str.equalsIgnoreCase(this.c) || str.equalsIgnoreCase(this.d)) {
                aVar.b.setBackgroundResource(2130839186);
            } else {
                aVar.b.setBackgroundResource(2130838773);
            }
            return view;
        }
    }

    public FileManagerDirActivity() {
        this.i = 32769;
        this.x = -1;
        this.y = new h(this);
        this.z = new i(this);
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent();
        intent.putExtra("rootPath", str);
        intent.putExtra("just_share", false);
        intent.setClass(context, FileManagerDirActivity.class);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, 36864);
        } else {
            context.startActivity(intent);
        }
        com.xunlei.downloadprovider.commonview.a.a.c((Activity) context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2130968754);
        this.v = k.b();
        this.w = k.c();
        this.t = getIntent().getStringExtra("rootPath");
        this.k = (TextView) findViewById(2131755899);
        this.k.setOnClickListener(this);
        this.o = (TextView) findViewById(2131755910);
        this.m = (ImageView) findViewById(2131755408);
        this.m.setOnClickListener(this);
        this.m.setImageResource(R.drawable.common_close_icon_selector);
        this.l = (ImageView) findViewById(2131755911);
        this.l.setOnClickListener(this);
        this.u = (RelativeLayout) findViewById(2131755901);
        this.u.setOnClickListener(this);
        this.u.setEnabled(false);
        this.n = (ImageView) findViewById(2131755912);
        this.n.setOnClickListener(this);
        this.j = (FileManagerDirView) findViewById(2131755909);
        this.j.setOnFileItemClickListener(this.y);
        this.j.setSDCardName(d());
        this.j.setOnDirViewStateChangeListener(new g(this));
        this.j.setRootPath(this.t);
        if (b.a(this.t)) {
            this.j.setLimitPath(this.v);
        } else {
            this.j.setLimitPath(this.w);
        }
        this.j.d();
        this.j.a();
        if (this.h) {
            this.j.setCanEditable(XZBDevice.DOWNLOAD_LIST_RECYCLE);
            this.l.setVisibility(XZBDevice.Wait);
            this.n.setVisibility(0);
            this.u.setVisibility(XZBDevice.Wait);
            this.k.setClickable(false);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    public void onClick(View view) {
        int i = 0;
        int a;
        switch (view.getId()) {
            case 2131755408:
                if (this.j.getState() == 1 || this.h) {
                    onBackPressed();
                } else {
                    e();
                }
            case 2131755899:
                List browserRecords = this.j.getBrowserRecords();
                new StringBuilder("pathList == ").append(browserRecords.size());
                if (this.j.f()) {
                    if (this.r == null) {
                        this.q = (LayoutInflater) getSystemService("layout_inflater");
                        ViewGroup viewGroup = (ViewGroup) this.q.inflate(2130968758, null, true);
                        this.p = (ListView) viewGroup.findViewById(2131755923);
                        this.p.setOnItemClickListener(this);
                        this.s = new a();
                        this.p.setAdapter(this.s);
                        this.r = new PopupWindow(viewGroup, -2, -2, true);
                        this.r.setBackgroundDrawable(new ColorDrawable(0));
                        this.r.setOutsideTouchable(true);
                        this.r.setFocusable(true);
                    }
                    List arrayList = new ArrayList();
                    while (i < browserRecords.size() - 1) {
                        arrayList.add(browserRecords.get(i));
                        i++;
                    }
                    this.s.b = arrayList;
                    this.s.notifyDataSetChanged();
                    a = g.a(this, 8.0f);
                    int[] iArr = new int[2];
                    this.k.getLocationOnScreen(iArr);
                    this.r.showAtLocation(this.k, R.styleable.AppCompatTheme_selectableItemBackgroundBorderless, a, iArr[1] + this.k.getHeight());
                }
            case 2131755901:
                a(this.j.getData());
            case 2131755911:
                this.j.setCanEditable(XZBDevice.DOWNLOAD_LIST_FAILED);
                this.l.setVisibility(XZBDevice.Wait);
                this.n.setVisibility(0);
                this.u.setVisibility(0);
                this.k.setClickable(false);
                StatReporter.reportClick(XLErrorCode.ALI_AUTH_USER_CANCLE, "delete", d());
            case 2131755912:
                i iVar;
                boolean z;
                FileManagerDirView fileManagerDirView = this.j;
                int i2;
                if (fileManagerDirView.a == 3) {
                    for (i2 = 0; i2 < fileManagerDirView.b.size(); i2++) {
                        iVar = (i) fileManagerDirView.b.get(i2);
                        if (!iVar.a && iVar.j != EFileCategoryType.E_XLFILE_UPPER) {
                            a = 0;
                        }
                    }
                    z = true;
                } else {
                    if (fileManagerDirView.a == 2) {
                        for (i2 = 0; i2 < fileManagerDirView.b.size(); i2++) {
                            iVar = (i) fileManagerDirView.b.get(i2);
                            if (iVar.g() || iVar.a || iVar.j == EFileCategoryType.E_XLFILE_UPPER) {
                            }
                        }
                        z = true;
                    }
                    a = 0;
                }
                if (z) {
                    b.b(this.j.b);
                } else {
                    FileManagerDirView fileManagerDirView2 = this.j;
                    if (fileManagerDirView2.a == 3) {
                        while (i < fileManagerDirView2.b.size()) {
                            iVar = (i) fileManagerDirView2.b.get(i);
                            if (iVar.j != EFileCategoryType.E_XLFILE_UPPER) {
                                iVar.a = true;
                            }
                            i++;
                        }
                    } else if (fileManagerDirView2.a == 2) {
                        while (i < fileManagerDirView2.b.size()) {
                            iVar = (i) fileManagerDirView2.b.get(i);
                            if (!iVar.g() && iVar.j != EFileCategoryType.E_XLFILE_UPPER) {
                                iVar.a = true;
                            }
                            i++;
                        }
                    }
                }
                this.j.a();
            default:
                break;
        }
    }

    public void onBackPressed() {
        setResult(this.i);
        finish();
        com.xunlei.downloadprovider.commonview.a.a.d(this);
    }

    private void e() {
        this.j.setCanEditable(1);
        this.n.setVisibility(XZBDevice.Wait);
        this.u.setVisibility(XZBDevice.Wait);
        this.l.setVisibility(0);
        this.k.setClickable(true);
        b.b(this.j.getData());
        this.j.c();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.j.getState() != 1 && !this.h) {
                e();
                return true;
            } else if (this.j.f()) {
                this.j.e();
                return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        FileManagerDirView fileManagerDirView = this.j;
        if (i >= 0) {
            fileManagerDirView.d.size();
        }
        while (fileManagerDirView.d.size() - 1 > i) {
            fileManagerDirView.g();
        }
        fileManagerDirView.b();
        fileManagerDirView.a();
        fileManagerDirView.setSelection(fileManagerDirView.getLastBrowserRecord().b);
        if (this.r != null && this.r.isShowing()) {
            this.r.dismiss();
        }
    }

    protected final void b(List<i> list) {
        if (list.size() == 1) {
            e();
            this.j.getData().removeAll(list);
            this.j.c();
        } else {
            e();
            this.j.b();
            this.j.a();
        }
        this.i = 32768;
    }

    protected final void a(com.xunlei.downloadprovider.filemanager.ui.g.a aVar) {
        if (aVar.a) {
            i hVar;
            this.j.getData().remove(aVar.d);
            if (new File(aVar.c).isDirectory()) {
                hVar = new h();
            } else {
                hVar = new i();
            }
            hVar.a(aVar.c);
            this.j.getData().add(hVar);
            this.j.c();
            XLToast.a(this, XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u91cd\u547d\u540d\u6210\u529f\uff01");
            this.i = 32768;
            return;
        }
        XLToast.a(this, XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u91cd\u547d\u540d\u5931\u8d25\uff01");
    }

    public final String d() {
        return b.a(this.t) ? "primarySDcard" : "slaverSDCard";
    }
}
