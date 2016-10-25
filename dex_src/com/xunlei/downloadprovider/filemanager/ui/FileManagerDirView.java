package com.xunlei.downloadprovider.filemanager.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.a.g;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.filemanager.model.h;
import com.xunlei.downloadprovider.filemanager.model.i;
import com.xunlei.downloadprovider.filemanager.ui.FileManagerDirView.e;
import com.xunlei.downloadprovider.model.protocol.report.StatReporter;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FileManagerDirView extends ListView implements OnItemClickListener, OnItemLongClickListener {
    public int a;
    public List<i> b;
    Comparator<i> c;
    public final ArrayList<a> d;
    private d e;
    private c f;
    private Context g;
    private b h;
    private String i;
    private String j;
    private final String k;
    private String l;
    private boolean m;

    public static interface c {
        void a(int i, String str, List<i> list);
    }

    public static interface d {
        boolean a(int i);
    }

    public static class a {
        public String a;
        public int b;
    }

    public class b extends BaseAdapter {
        private LayoutInflater b;
        private int c;
        private int d;

        public b() {
            this.b = (LayoutInflater) FileManagerDirView.this.g.getSystemService("layout_inflater");
            this.c = g.a(FileManagerDirView.this.getContext(), 50.0f);
            this.d = g.a(FileManagerDirView.this.getContext(), 48.0f);
        }

        public final int getCount() {
            return FileManagerDirView.this.b.size();
        }

        public final Object getItem(int i) {
            return FileManagerDirView.this.b.get(i);
        }

        public final long getItemId(int i) {
            return (long) i;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            e eVar;
            if (view == null) {
                view = this.b.inflate(2130968756, null);
                e eVar2 = new e();
                FileManagerDirView.this = (ImageView) view.findViewById(R.id.iv_icon);
                eVar2.b = (TextView) view.findViewById(2131755916);
                eVar2.c = (TextView) view.findViewById(2131755918);
                eVar2.d = (TextView) view.findViewById(2131755919);
                eVar2.e = (TextView) view.findViewById(2131755913);
                view.setTag(eVar2);
                eVar = eVar2;
            } else {
                eVar = (e) view.getTag();
            }
            TextView textView = (TextView) view.findViewById(2131755915);
            i iVar = (i) getItem(i);
            if (iVar.j == EFileCategoryType.E_XLFILE_UPPER) {
                textView.setVisibility(0);
                eVar.b.setVisibility(XZBDevice.Wait);
                eVar.d.setVisibility(XZBDevice.Wait);
                textView.setText(iVar.g);
                FileManagerDirView.this.setImageResource(2130838202);
                eVar.b.setText(iVar.g);
                eVar.e.setVisibility(XZBDevice.Wait);
                eVar.d.setVisibility(XZBDevice.Wait);
                eVar.c.setVisibility(XZBDevice.Wait);
            } else {
                CharSequence string;
                textView.setVisibility(XZBDevice.Wait);
                eVar.b.setVisibility(0);
                eVar.e.setVisibility(0);
                eVar.d.setVisibility(0);
                eVar.c.setVisibility(0);
                FileManagerDirView.this.setImageResource(XLFileTypeUtil.b(iVar.g));
                if (FileManagerDirView.this.i.equals(iVar.g)) {
                    string = FileManagerDirView.this.getContext().getString(2131231974);
                } else if (FileManagerDirView.this.j.equals(iVar.g)) {
                    string = FileManagerDirView.this.getContext().getString(2131232399);
                } else {
                    string = iVar.a();
                }
                eVar.b.setText(string);
                eVar.d.setText(iVar.f());
                if (iVar.g()) {
                    h hVar = (h) iVar;
                    String c = hVar.c();
                    if (hVar.d) {
                        eVar.c.setVisibility(0);
                        long a = k.a(c);
                        eVar.c.setText(new StringBuilder("\u53ef\u7528:").append(com.xunlei.downloadprovider.d.a.a(a - k.b(c), (int) XZBDevice.DOWNLOAD_LIST_RECYCLE)).append("  \u603b\u5927\u5c0f:").append(com.xunlei.downloadprovider.d.a.a(a, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE)).toString());
                        eVar.d.setVisibility(XZBDevice.Wait);
                    } else {
                        eVar.c.setVisibility(XZBDevice.Wait);
                        eVar.d.setVisibility(0);
                    }
                } else {
                    eVar.c.setVisibility(0);
                    eVar.c.setText(com.xunlei.downloadprovider.d.a.a(iVar.i, (int) XZBDevice.DOWNLOAD_LIST_RECYCLE));
                }
                if (FileManagerDirView.this == 3 || (FileManagerDirView.this == 2 && !iVar.g())) {
                    eVar.e.setVisibility(0);
                    eVar.e.setSelected(FileManagerDirView.this);
                } else {
                    eVar.e.setVisibility(XZBDevice.Wait);
                }
            }
            return view;
        }
    }

    public final class e {
        public ImageView a;
        public TextView b;
        public TextView c;
        public TextView d;
        public TextView e;
    }

    public FileManagerDirView(Context context) {
        super(context);
        this.a = 1;
        this.k = getClass().getSimpleName();
        this.m = false;
        this.c = new d(this);
        this.d = new ArrayList();
        this.g = context;
        i();
    }

    public FileManagerDirView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = 1;
        this.k = getClass().getSimpleName();
        this.m = false;
        this.c = new d(this);
        this.d = new ArrayList();
        this.g = context;
        i();
    }

    public FileManagerDirView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = 1;
        this.k = getClass().getSimpleName();
        this.m = false;
        this.c = new d(this);
        this.d = new ArrayList();
        this.g = context;
        i();
    }

    public void setSDCardName(String str) {
        this.l = str;
    }

    public void setOnFileItemClickListener(d dVar) {
        this.e = dVar;
    }

    public void setOnDirViewStateChangeListener(c cVar) {
        this.f = cVar;
    }

    public void setRootPath(String str) {
        if ("#*sdcard.choose@!~".endsWith(str)) {
            b("#*sdcard.choose@!~");
        } else {
            b(str);
        }
    }

    public void setCurrentPath(String str) {
        String substring;
        if (!str.endsWith(File.separator)) {
            str = str + File.separator;
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (com.xunlei.downloadprovider.filemanager.model.b.a(str)) {
            b(this.i);
            substring = str.substring(this.i.length());
            stringBuffer.append(this.i);
        } else {
            b(this.j);
            substring = str.substring(this.j.length());
            stringBuffer.append(this.j);
        }
        while (true) {
            int indexOf = substring.indexOf(File.separator);
            if (indexOf != -1) {
                String substring2 = substring.substring(0, indexOf + 1);
                substring = substring.substring(indexOf + 1);
                stringBuffer.append(substring2);
                b(stringBuffer.toString());
            } else {
                return;
            }
        }
    }

    public void setJustShowDir(boolean z) {
        this.m = z;
    }

    public final void a() {
        c();
        String lastBrowserRecordPath = getLastBrowserRecordPath();
        if (this.f != null) {
            this.f.a(this.a, lastBrowserRecordPath, this.b);
        }
    }

    public void setSelection(String str) {
        int i = 0;
        while (i < this.b.size()) {
            if (str.equals(((i) this.b.get(i)).c())) {
                break;
            }
            i++;
        }
        i = -1;
        if (i != -1) {
            setSelection(i);
        }
    }

    public final void b() {
        this.b = getFileDatabyRootPath();
        h();
    }

    public final void c() {
        h();
        this.h.notifyDataSetChanged();
    }

    public final void d() {
        i();
        b();
        this.h = new b();
        setAdapter(this.h);
        setOnItemClickListener(this);
        setOnItemLongClickListener(this);
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a != 1) {
            return false;
        }
        if (((i) this.b.get(i)).j == EFileCategoryType.E_XLFILE_UPPER) {
            return false;
        }
        return this.e != null ? this.e.a(i) : true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        boolean z = true;
        i iVar = (i) this.b.get(i);
        if (this.a != 3) {
            if (this.a == 2) {
                if (iVar.j == EFileCategoryType.E_XLFILE_UPPER) {
                    e();
                    return;
                } else if (!iVar.g()) {
                    if (iVar.a) {
                        z = false;
                    }
                    iVar.a = z;
                    a();
                    return;
                }
            } else if (iVar.j == EFileCategoryType.E_XLFILE_UPPER) {
                e();
                return;
            } else if (!iVar.g()) {
                com.xunlei.downloadprovider.h.b.a(this.g, iVar.g, null, true);
                StatReporter.reportClick(XLErrorCode.ALI_AUTH_USER_CANCLE, "openfile", this.l);
                return;
            }
            int firstVisiblePosition = getFirstVisiblePosition();
            if (this.d.size() > 0) {
                ((a) this.d.get(this.d.size() - 1)).b = firstVisiblePosition;
            }
            b(iVar.g);
            new StringBuilder("filePathclick = ").append(iVar.g);
            b();
            a();
            setSelection(0);
        } else if (iVar.j != EFileCategoryType.E_XLFILE_UPPER) {
            if (iVar.a) {
                z = false;
            }
            iVar.a = z;
            a();
        }
    }

    public List<i> getData() {
        return this.b;
    }

    public final void e() {
        if (this.d.size() > 1) {
            g();
            b();
            a();
            setSelection(getLastBrowserRecord().b);
        }
    }

    public void setLimitPath(String str) {
    }

    public void setCanEditable(int i) {
        this.a = i;
        a();
    }

    public int getState() {
        return this.a;
    }

    public String getCurrentPath() {
        return getLastBrowserRecord() != null ? getLastBrowserRecord().a : null;
    }

    public List<i> getSelectFiles() {
        List<i> arrayList = new ArrayList();
        for (i iVar : this.b) {
            if (iVar.a && iVar.j != EFileCategoryType.E_XLFILE_UPPER) {
                arrayList.add(iVar);
            }
        }
        return arrayList;
    }

    public List<a> getBrowserRecords() {
        return this.d;
    }

    public final boolean f() {
        return this.d.size() > 1;
    }

    private List<i> getFileDatabyRootPath() {
        List<i> arrayList = new ArrayList();
        String lastBrowserRecordPath = getLastBrowserRecordPath();
        if (lastBrowserRecordPath == null || !lastBrowserRecordPath.startsWith("#*sdcard.choose@!~")) {
            File[] listFiles = new File(lastBrowserRecordPath).listFiles(new e(this));
            if (listFiles == null) {
                return arrayList;
            }
            i hVar;
            int length = listFiles.length;
            for (int i = 0; i < length; i++) {
                File file = listFiles[i];
                if (file.isDirectory()) {
                    hVar = new h();
                    hVar.j = EFileCategoryType.E_XLDIR_CATEGORY;
                } else {
                    hVar = new i();
                    hVar.j = XLFileTypeUtil.a(file.getName());
                }
                hVar.h = file.lastModified();
                hVar.g = file.getAbsolutePath();
                hVar.i = file.length();
                arrayList.add(hVar);
            }
            if (this.d.size() > 1) {
                hVar = new i();
                hVar.g = "\u8fd4\u56de\u4e0a\u4e00\u7ea7";
                hVar.j = EFileCategoryType.E_XLFILE_UPPER;
                arrayList.add(0, hVar);
            }
            return arrayList;
        }
        if (!TextUtils.isEmpty(this.i)) {
            h hVar2 = new h();
            hVar2.d = true;
            hVar2.a(this.i);
            arrayList.add(hVar2);
        }
        if (!TextUtils.isEmpty(this.j)) {
            hVar2 = new h();
            hVar2.d = true;
            hVar2.a(this.j);
            arrayList.add(hVar2);
        }
        return arrayList;
    }

    private void h() {
        Collections.sort(this.b, this.c);
    }

    public final String a(String str) {
        if (str == null) {
            return com.umeng.a.d;
        }
        if (str == null || !str.startsWith("#*sdcard.choose@!~")) {
            return com.xunlei.downloadprovider.filemanager.model.b.a(str) ? str.replace(this.i, new StringBuilder("/").append(getContext().getString(2131231974)).append("/").toString()) : str.replace(this.j, new StringBuilder("/").append(getContext().getString(2131232399)).append("/").toString());
        } else {
            return getContext().getString(2131232401);
        }
    }

    private void i() {
        this.i = k.b();
        this.j = k.c();
    }

    private void b(String str) {
        a aVar = new a();
        aVar.a = str;
        aVar.b = 0;
        this.d.add(aVar);
    }

    public final a g() {
        return this.d.size() > 0 ? (a) this.d.remove(this.d.size() - 1) : null;
    }

    public a getLastBrowserRecord() {
        return this.d.size() > 0 ? (a) this.d.get(this.d.size() - 1) : null;
    }

    private String getLastBrowserRecordPath() {
        if (this.d.size() <= 0) {
            return null;
        }
        a aVar = (a) this.d.get(this.d.size() - 1);
        if (aVar != null) {
            return aVar.a.endsWith(File.separator) ? aVar.a : aVar.a + File.separator;
        } else {
            return null;
        }
    }
}
