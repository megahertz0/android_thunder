package com.xunlei.downloadprovider.app.ui;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
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
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.app.ui.FileManageView.d;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.commonview.XLToast;
import com.xunlei.downloadprovider.commonview.XLToast.XLToastType;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.tdlive.WebBrowserActivity;
import com.xunlei.tdlive.sdk.IHost;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.android.spdy.SpdyAgent;

public class FileManageView extends ListView implements OnItemClickListener, OnItemLongClickListener {
    public static final Comparator<File> k;
    public List<Map<String, Object>> a;
    public String b;
    public String c;
    public List<Map<String, Object>> d;
    public boolean e;
    public String f;
    public b g;
    public String h;
    public int i;
    public boolean j;
    private ArrayList<String> l;
    private TextView m;
    private boolean n;
    private Context o;
    private c p;
    private boolean q;
    private int r;
    private boolean s;
    private boolean t;
    private Comparator<File> u;

    static /* synthetic */ class AnonymousClass_1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[EFileCategoryType.values().length];
            try {
                a[EFileCategoryType.E_MUSIC_CATEGORY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[EFileCategoryType.E_VIDEO_CATEGORY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[EFileCategoryType.E_SOFTWARE_CATEGORY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[EFileCategoryType.E_ZIP_CATEGORY.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[EFileCategoryType.E_BOOK_CATEGORY.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[EFileCategoryType.E_PICTURE_CATEGORY.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[EFileCategoryType.E_TORRENT_CATEGORY.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[EFileCategoryType.E_EXE_CATEGORY.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[EFileCategoryType.E_OTHER_CATEGORY.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    private static final class a implements Comparator<File> {
        private a() {
        }

        public final /* synthetic */ int compare(Object obj, Object obj2) {
            return ((File) obj).getName().compareToIgnoreCase(((File) obj2).getName());
        }
    }

    public class b extends BaseAdapter {
        public final int getCount() {
            return FileManageView.this.size();
        }

        public final Object getItem(int i) {
            return null;
        }

        public final long getItemId(int i) {
            return 0;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            d dVar;
            Map map = (Map) FileManageView.this.get(i);
            String str = (String) map.get("fileName");
            String str2 = (String) map.get(WebBrowserActivity.EXTRA_TITLE);
            int intValue = ((Integer) map.get(JsInterface.FUNPLAY_AD_TRPE)).intValue();
            if (view == null) {
                dVar = new d();
                view = LayoutInflater.from(FileManageView.this.o).inflate(2130968757, null);
                FileManageView.this = (ImageView) view.findViewById(2131755426);
                dVar.b = (TextView) view.findViewById(2131755427);
                dVar.e = view.findViewById(2131755428);
                dVar.c = (TextView) view.findViewById(2131755921);
                dVar.d = (TextView) view.findViewById(2131755922);
                view.setTag(dVar);
            } else {
                d dVar2 = (d) view.getTag();
                if (dVar2 == null) {
                    dVar = new d();
                    view = LayoutInflater.from(FileManageView.this.o).inflate(2130968757, null);
                    FileManageView.this = (ImageView) view.findViewById(2131755426);
                    dVar.b = (TextView) view.findViewById(2131755427);
                    dVar.c = (TextView) view.findViewById(2131755921);
                    dVar.e = view.findViewById(2131755428);
                    view.setTag(dVar);
                } else {
                    dVar = dVar2;
                }
            }
            dVar.b.setText(str);
            if (FileManageView.this.t) {
                CharSequence substring;
                String str3 = (String) map.get("info");
                int lastIndexOf = str3.lastIndexOf("/");
                if (lastIndexOf != -1) {
                    substring = str3.substring(0, lastIndexOf);
                }
                dVar.d.setVisibility(0);
                dVar.d.setText(substring);
            } else {
                dVar.d.setVisibility(XZBDevice.Wait);
            }
            if (intValue != 0) {
                if (FileManageView.this.s) {
                    dVar.c.setVisibility(0);
                    dVar.c.setText((String) map.get("fileSize"));
                } else {
                    dVar.c.setVisibility(XZBDevice.Wait);
                }
                switch (AnonymousClass_1.a[XLFileTypeUtil.a(str).ordinal()]) {
                    case SpdyAgent.ACCS_ONLINE_SERVER:
                        FileManageView.this.setImageResource(R.drawable.ic_dl_music);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_RECYCLE:
                        FileManageView.this.setImageResource(R.drawable.ic_dl_video);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_FAILED:
                        FileManageView.this.setImageResource(R.drawable.ic_dl_apk);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_ALL:
                        FileManageView.this.setImageResource(R.drawable.ic_dl_rar);
                        break;
                    case XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED:
                        FileManageView.this.setImageResource(R.drawable.ic_dl_text);
                        break;
                    case R.styleable.Toolbar_contentInsetEnd:
                        FileManageView.this.setImageResource(R.drawable.ic_dl_image);
                        break;
                    case R.styleable.Toolbar_contentInsetLeft:
                        FileManageView.this.setImageResource(R.drawable.ic_dl_torrent);
                        break;
                    case XZBDevice.Wait:
                    case XZBDevice.Pause:
                        FileManageView.this.setImageResource(R.drawable.ic_dl_other);
                        break;
                    default:
                        break;
                }
            } else if (str2.equals("../")) {
                FileManageView.this.setImageResource(2130838202);
                dVar.c.setVisibility(XZBDevice.Wait);
            } else {
                if (str2.equals(FileManageView.this.o.getString(2131231973)) || str2.equals(FileManageView.this.o.getString(2131232398))) {
                    FileManageView.this.setImageResource(2130838203);
                } else {
                    FileManageView.this.setImageResource(R.drawable.ic_dl_folder);
                }
                if (FileManageView.this.s && "///homepage".equals(FileManageView.this.b)) {
                    dVar.c.setVisibility(0);
                    dVar.c.setText((String) map.get("fileSize"));
                } else {
                    dVar.c.setVisibility(XZBDevice.Wait);
                }
            }
            if (!FileManageView.this.j || str2.equals("../")) {
                dVar.e.setVisibility(XZBDevice.Wait);
            } else {
                dVar.e.setVisibility(0);
                dVar.e.setSelected(FileManageView.b(map));
            }
            return view;
        }
    }

    public static interface c {
        boolean a(String str);
    }

    public final class d {
        public ImageView a;
        public TextView b;
        public TextView c;
        public TextView d;
        public View e;
    }

    public FileManageView(Context context) {
        super(context);
        this.b = k.e();
        this.c = null;
        this.l = null;
        this.e = false;
        this.f = null;
        this.h = null;
        this.j = false;
        this.n = true;
        this.q = false;
        this.r = 0;
        this.s = false;
        this.t = false;
        this.u = new b(this);
        this.o = context;
    }

    public FileManageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = k.e();
        this.c = null;
        this.l = null;
        this.e = false;
        this.f = null;
        this.h = null;
        this.j = false;
        this.n = true;
        this.q = false;
        this.r = 0;
        this.s = false;
        this.t = false;
        this.u = new b(this);
        this.o = context;
    }

    public FileManageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = k.e();
        this.c = null;
        this.l = null;
        this.e = false;
        this.f = null;
        this.h = null;
        this.j = false;
        this.n = true;
        this.q = false;
        this.r = 0;
        this.s = false;
        this.t = false;
        this.u = new b(this);
        this.o = context;
    }

    public void setNameSize(boolean z) {
        this.s = z;
    }

    public void setNeedMoreInfo(boolean z) {
        this.t = z;
    }

    private boolean b(String str) {
        if (this.d == null || str == null) {
            return false;
        }
        for (Map map : this.d) {
            if (map != null) {
                String str2 = (String) map.get("info");
                if (str2 != null && com.xunlei.downloadprovider.d.c.a(str2, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setCanEdit(boolean z) {
        this.n = z;
    }

    public final void a(ArrayList<String> arrayList) {
        this.i = 1002;
        this.l = arrayList;
        this.b = null;
        a();
    }

    public final void a(String str, String str2) {
        if ("///homepage".equals(str)) {
            this.i = 1003;
            this.f = null;
        } else {
            this.i = 1000;
        }
        this.b = str;
        this.c = str2;
        a();
    }

    public final void a(String str) {
        this.b = str;
        if (this.i == 1003) {
            if (this.b.equals("///homepage")) {
                this.f = null;
            } else if (b(this.b) && this.f == null) {
                new StringBuilder("get entrance : ").append(this.b);
                this.f = this.b;
                if (this.e) {
                    if (this.b.endsWith(File.separator)) {
                        this.b += com.xunlei.downloadprovider.businessutil.a.g();
                    } else {
                        this.b += File.separator + com.xunlei.downloadprovider.businessutil.a.g();
                    }
                    String str2 = this.b;
                    File file = new File(str2);
                    if (!file.exists()) {
                        new StringBuilder("mkdir : ").append(str2).append(", ret = ").append(file.mkdir());
                    }
                }
            }
        }
        a();
    }

    public final void a() {
        Object obj = 1;
        this.a = getData();
        String str = null;
        if (this.i == 1000 || this.i == 1003) {
            if (this.b.equals("///homepage")) {
                str = "/";
            } else {
                str = this.b;
                if (!(str == null || str.endsWith("/"))) {
                    str = str + "/";
                }
            }
        } else if (this.i == 1002) {
            str = this.h.startsWith("///homepage") ? "/" : this.h;
        }
        if (this.m != null) {
            CharSequence replaceFirst;
            Object obj2;
            String b = k.b();
            String c = k.c();
            String str2 = new String(replaceFirst);
            if (b == null || b.length() <= 0 || k.a(b) <= 0) {
                obj2 = null;
            } else {
                int i = 1;
            }
            if (c == null || c.length() <= 0 || k.a(c) <= 0) {
                obj = null;
            }
            if (obj2 == null || obj == null) {
                if (obj2 != null) {
                    replaceFirst = str2.replaceFirst(b, new StringBuilder("/").append(this.o.getString(2131231973)).append("/").toString());
                } else if (obj != null) {
                    replaceFirst = str2.replaceFirst(c, new StringBuilder("/").append(this.o.getString(2131232398)).append("/").toString());
                }
            } else if (b.length() > c.length()) {
                if (str2.startsWith(c)) {
                    replaceFirst = str2.replaceFirst(c, new StringBuilder("/").append(this.o.getString(2131232398)).append("/").toString());
                }
                if (str2.startsWith(b)) {
                    replaceFirst = str2.replaceFirst(b, new StringBuilder("/").append(this.o.getString(2131231973)).append("/").toString());
                }
            } else {
                if (str2.startsWith(b)) {
                    replaceFirst = str2.replaceFirst(b, new StringBuilder("/").append(this.o.getString(2131231973)).append("/").toString());
                }
                if (str2.startsWith(c)) {
                    replaceFirst = str2.replaceFirst(c, new StringBuilder("/").append(this.o.getString(2131232398)).append("/").toString());
                }
            }
            this.m.setText(replaceFirst);
        }
        this.g.notifyDataSetChanged();
    }

    public void setAllItemSelectedState(boolean z) {
        for (Map map : this.a) {
            if (map != null) {
                a(map, z);
            }
        }
    }

    public List<Map<String, Object>> getData() {
        new StringBuilder("mType = ").append(this.i);
        switch (this.i) {
            case IHost.HOST_NOFITY_REFRESH_LIST:
                return getDataFromFilePathAndFilter();
            case IHost.HOST_NOFITY_PAGE_DESELECTED:
                return c(this.l);
            case JsInterface.MSG_JS_OPENWINDOW_WITH_DOWNLOADLISTEX:
                new StringBuilder("entrance = ").append(this.f);
                return this.f == null ? this.d : getDataFromFilePathAndFilter();
            default:
                return null;
        }
    }

    public void setLimitInDirectory(String str) {
        if (str != null) {
            this.h = str;
            if (!this.h.endsWith("/")) {
                this.h += "/";
            }
        }
    }

    public final boolean b() {
        return this.h == null || this.b == null || !com.xunlei.downloadprovider.d.c.a(this.h, this.b);
    }

    public final Map<String, Object> a(File file) {
        Object obj;
        Map<String, Object> hashMap = new HashMap();
        hashMap.put(WebBrowserActivity.EXTRA_TITLE, "../");
        if (this.f != null && com.xunlei.downloadprovider.d.c.a(this.f, this.b)) {
            obj = "///homepage";
        } else if (b(this.b)) {
            obj = "///homepage";
            this.f = this.b;
        } else {
            obj = file.getParent();
            if (!(obj == null || obj.endsWith("/"))) {
                obj = obj + "/";
            }
        }
        hashMap.put("info", obj);
        hashMap.put("fileName", "\u8fd4\u56de\u4e0a\u4e00\u7ea7");
        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(0));
        return hashMap;
    }

    @SuppressLint({"DefaultLocale"})
    private List<Map<String, Object>> getDataFromFilePath() {
        List<Map<String, Object>> arrayList = new ArrayList();
        File file = new File(this.b);
        File[] listFiles = file.listFiles();
        new StringBuilder("getDataFromFilePath mDir = ").append(this.b);
        if (b()) {
            arrayList.add(a(file));
        }
        if (listFiles != null) {
            if ((this.b + "/").toLowerCase().contains(com.xunlei.downloadprovider.businessutil.a.g().toLowerCase() + "/")) {
                Arrays.sort(listFiles, this.u);
            } else {
                Arrays.sort(listFiles, k);
            }
            int length = listFiles.length;
            for (int i = 0; i < length; i++) {
                File file2 = listFiles[i];
                if (file2 != null && !file2.getName().startsWith(".")) {
                    if (file2.isDirectory()) {
                        Map hashMap = new HashMap();
                        hashMap.put(WebBrowserActivity.EXTRA_TITLE, file2.getName());
                        Object path = file2.getPath();
                        if (!(path == null || path.endsWith("/"))) {
                            path = path + "/";
                        }
                        hashMap.put("info", path);
                        hashMap.put("fileName", file2.getName());
                        hashMap.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(0));
                        arrayList.add(hashMap);
                    } else {
                        Map hashMap2 = new HashMap();
                        hashMap2.put(WebBrowserActivity.EXTRA_TITLE, file2.getName());
                        hashMap2.put("info", file2.getPath());
                        hashMap2.put("fileName", file2.getName());
                        hashMap2.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(1));
                        if (this.s) {
                            hashMap2.put("fileSize", com.xunlei.downloadprovider.d.a.a(file2.length(), (int) XZBDevice.DOWNLOAD_LIST_RECYCLE));
                        }
                        arrayList.add(hashMap2);
                    }
                }
            }
        }
        return arrayList;
    }

    @SuppressLint({"DefaultLocale"})
    private List<Map<String, Object>> getDataFromFilePathAndFilter() {
        List<Map<String, Object>> dataFromFilePath = getDataFromFilePath();
        if (this.c == null) {
            return dataFromFilePath;
        }
        List<Map<String, Object>> arrayList = new ArrayList();
        for (Map map : dataFromFilePath) {
            if (map != null) {
                if (((Integer) map.get(JsInterface.FUNPLAY_AD_TRPE)).intValue() == 0) {
                    arrayList.add(map);
                } else if (((String) map.get("fileName")).toLowerCase().endsWith(this.c)) {
                    arrayList.add(map);
                }
            }
        }
        return arrayList;
    }

    private List<Map<String, Object>> c(ArrayList<String> arrayList) {
        List<Map<String, Object>> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            File file = new File((String) it.next());
            Map hashMap = new HashMap();
            hashMap.put(WebBrowserActivity.EXTRA_TITLE, file.getName());
            hashMap.put("info", file.getPath());
            hashMap.put("fileName", file.getName());
            if (file.isDirectory()) {
                hashMap.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(0));
            } else {
                hashMap.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(1));
                if (this.s) {
                    hashMap.put("fileSize", com.xunlei.downloadprovider.d.a.a(file.length(), (int) XZBDevice.DOWNLOAD_LIST_RECYCLE));
                }
            }
            arrayList2.add(hashMap);
        }
        return arrayList2;
    }

    public final void b(ArrayList<String> arrayList) {
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                Map hashMap = new HashMap();
                File file = new File(str);
                hashMap.put(WebBrowserActivity.EXTRA_TITLE, file.getName());
                hashMap.put("info", file.getPath());
                hashMap.put("fileName", file.getName());
                hashMap.put(JsInterface.FUNPLAY_AD_TRPE, Integer.valueOf(1));
                this.a.add(hashMap);
            }
            this.g.notifyDataSetChanged();
        }
    }

    public final void c() {
        if (this.a != null) {
            this.a.clear();
            this.g.notifyDataSetChanged();
        }
    }

    public void setPathView(TextView textView) {
        this.m = textView;
    }

    public void setOnFileOperateListener(c cVar) {
        this.p = cVar;
    }

    private static boolean b(Map<String, Object> map) {
        Boolean bool = (Boolean) map.get("selected");
        return bool != null && bool.booleanValue();
    }

    private void a(Map<String, Object> map, boolean z) {
        boolean z2 = true;
        String str = (String) map.get("info");
        if (!"../".equals((String) map.get(WebBrowserActivity.EXTRA_TITLE))) {
            new StringBuilder("to setSelected, path = ").append(str).append(", value = ").append(z);
            Boolean bool = (Boolean) map.get("selected");
            if ((bool == null ? false : bool.booleanValue()) != z) {
                if (z) {
                    this.r++;
                } else {
                    this.r--;
                }
            }
            map.put("selected", Boolean.valueOf(z));
            if (b()) {
                boolean z3;
                if (this.r + 1 == this.a.size()) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                this.q = z3;
                return;
            }
            if (this.r != this.a.size()) {
                z2 = false;
            }
            this.q = z2;
        }
    }

    public int getSelectedItemCount() {
        return this.r;
    }

    private int getFileItemCount() {
        int size = this.a.size();
        return (size <= 0 || !((Map) this.a.get(0)).get(WebBrowserActivity.EXTRA_TITLE).equals("../")) ? size : size - 1;
    }

    static {
        k = new a();
    }

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i >= 0 && i < this.a.size()) {
            if (this.j) {
                onItemClick(adapterView, view, i, j);
            } else {
                boolean z;
                if (!this.n) {
                    z = false;
                } else if (this.i == 1003 && this.f == null) {
                    z = false;
                } else if (getFileItemCount() == 0) {
                    z = false;
                } else {
                    z = true;
                }
                if (z && !((String) ((Map) this.a.get(i)).get(WebBrowserActivity.EXTRA_TITLE)).equals("../")) {
                    this.j = true;
                    this.q = false;
                    setAllItemSelectedState(false);
                    this.r = 0;
                    if (i >= 0) {
                        a((Map) this.a.get(i), true);
                    }
                    this.g.notifyDataSetChanged();
                }
            }
        }
        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i >= 0 && i < this.a.size()) {
            Map map = (Map) this.a.get(i);
            String str = (String) map.get("info");
            Integer num = (Integer) map.get(JsInterface.FUNPLAY_AD_TRPE);
            new StringBuilder("mDir = ").append(this.b).append(", position = ").append(i);
            if (this.j) {
                a(map, !b(map));
                this.g.notifyDataSetChanged();
            } else if (num.intValue() == 0) {
                a(str);
            } else if (!new File(str).exists()) {
                XLToast.a(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u6587\u4ef6\u4e0d\u5b58\u5728");
            } else if (this.p != null) {
                this.p.a(str);
            } else {
                try {
                    com.xunlei.downloadprovider.h.b.a(str, null, true);
                } catch (IllegalArgumentException e) {
                    XLToast.a(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u627e\u4e0d\u5230\u9002\u5408\u7684\u5e94\u7528\u6253\u5f00\u6587\u4ef6");
                } catch (ActivityNotFoundException e2) {
                    XLToast.a(getContext(), XLToastType.XLTOAST_TYPE_ALARM, "\u627e\u4e0d\u5230\u9002\u5408\u7684\u5e94\u7528\u6253\u5f00\u6587\u4ef6");
                }
            }
        }
    }
}
