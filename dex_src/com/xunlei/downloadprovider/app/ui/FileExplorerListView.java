package com.xunlei.downloadprovider.app.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.a.k;
import com.xunlei.downloadprovider.app.ui.FileExplorerListView.d;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.tdlive.im.ChatMessage;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.android.agoo.common.AgooConstants;
import org.android.spdy.SpdyProtocol;
import org.apache.commons.logging.impl.SimpleLog;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class FileExplorerListView extends ListView {
    public static final Comparator<File> g;
    public List<Map<String, Object>> a;
    public String b;
    public TextView c;
    public c d;
    public int e;
    public Context f;
    private String h;
    private ArrayList<String> i;
    private File j;
    private b k;

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

    public static interface b {
        void onClick(String str);
    }

    public class c extends BaseAdapter {
        public final int getCount() {
            return FileExplorerListView.this.size();
        }

        public final Object getItem(int i) {
            return null;
        }

        public final long getItemId(int i) {
            return 0;
        }

        public final View getView(int i, View view, ViewGroup viewGroup) {
            String str = (String) ((Map) FileExplorerListView.this.get(i)).get("fileName");
            if (!str.equals("\u8fd4\u56de\u5230\u4e0a\u4e00\u7ea7\u76ee\u5f55")) {
                d dVar;
                if (view == null) {
                    dVar = new d();
                    view = LayoutInflater.from(FileExplorerListView.this.f).inflate(R.layout.bt_item_local_file, null);
                    FileExplorerListView.this = (ImageView) view.findViewById(R.id.bt_item_local_file_icon);
                    dVar.b = (TextView) view.findViewById(R.id.bt_item_local_file_name);
                    view.setTag(dVar);
                } else {
                    d dVar2 = (d) view.getTag();
                    if (dVar2 != null && FileExplorerListView.this == null && dVar2.b == null) {
                        dVar2 = null;
                    }
                    if (dVar2 == null) {
                        dVar = new d();
                        view = LayoutInflater.from(FileExplorerListView.this.f).inflate(R.layout.bt_item_local_file, null);
                        FileExplorerListView.this = (ImageView) view.findViewById(R.id.bt_item_local_file_icon);
                        dVar.b = (TextView) view.findViewById(R.id.bt_item_local_file_name);
                        view.setTag(dVar);
                    } else {
                        dVar = dVar2;
                    }
                }
                dVar.b.setText((String) ((Map) FileExplorerListView.this.get(i)).get("fileName"));
                if (((Integer) ((Map) FileExplorerListView.this.get(i)).get(AgooConstants.MESSAGE_TYPE)).intValue() == 0) {
                    FileExplorerListView.this.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_folder);
                    return view;
                }
                switch (AnonymousClass_1.a[XLFileTypeUtil.a(str).ordinal()]) {
                    case SimpleLog.LOG_LEVEL_TRACE:
                        FileExplorerListView.this.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_music);
                        return view;
                    case SimpleLog.LOG_LEVEL_DEBUG:
                        FileExplorerListView.this.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_video);
                        return view;
                    case MqttConnectOptions.MQTT_VERSION_3_1:
                        FileExplorerListView.this.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_apk);
                        return view;
                    case MqttConnectOptions.MQTT_VERSION_3_1_1:
                        FileExplorerListView.this.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_rar);
                        return view;
                    case SimpleLog.LOG_LEVEL_ERROR:
                        FileExplorerListView.this.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_text);
                        return view;
                    case SimpleLog.LOG_LEVEL_FATAL:
                        FileExplorerListView.this.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_image);
                        return view;
                    case SimpleLog.LOG_LEVEL_OFF:
                        FileExplorerListView.this.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_torrent);
                        return view;
                    case SpdyProtocol.PUBKEY_SEQ_ADASH:
                    case SpdyProtocol.PUBKEY_PSEQ_ADASH:
                        FileExplorerListView.this.setImageResource(com.xunlei.downloadprovidershare.R.drawable.ic_dl_other);
                        return view;
                    default:
                        return view;
                }
            } else if (view == null) {
                dVar = new d();
                view = LayoutInflater.from(FileExplorerListView.this.f).inflate(R.layout.bt_item_local_file_up_btn, null);
                FileExplorerListView.this = null;
                dVar.b = null;
                view.setTag(dVar);
                return view;
            } else {
                dVar = (d) view.getTag();
                if (!(dVar == null || FileExplorerListView.this == null || dVar.b == null)) {
                    dVar = null;
                }
                if (dVar != null) {
                    return view;
                }
                dVar = new d();
                view = LayoutInflater.from(FileExplorerListView.this.f).inflate(R.layout.bt_item_local_file_up_btn, null);
                FileExplorerListView.this = null;
                dVar.b = null;
                view.setTag(dVar);
                return view;
            }
        }
    }

    public final class d {
        public ImageView a;
        public TextView b;
    }

    public FileExplorerListView(Context context) {
        super(context);
        this.b = k.e();
        this.h = null;
        this.i = null;
        this.f = context;
    }

    public FileExplorerListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = k.e();
        this.h = null;
        this.i = null;
        this.f = context;
    }

    public FileExplorerListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = k.e();
        this.h = null;
        this.i = null;
        this.f = context;
    }

    public List<Map<String, Object>> getData() {
        switch (this.e) {
            case ChatMessage.FLAG_SYS_NOTIFY:
                return getDataFromFilePath();
            case XLPayErrorCode.XLP_GATE_PARAM_ERROR:
                return getDataFromFilePathAndFilter();
            case XLPayErrorCode.XLP_GATE_GEN_ERROR:
                return getDataFromFilePathList();
            default:
                return null;
        }
    }

    private List<Map<String, Object>> getDataFromFilePath() {
        int i = 0;
        List<Map<String, Object>> arrayList = new ArrayList();
        File file = new File(this.b);
        this.j = file;
        File[] listFiles = file.listFiles();
        new StringBuilder("getDataFromFilePath mDir = ").append(this.b);
        if (!this.b.equalsIgnoreCase(k.e())) {
            Map hashMap = new HashMap();
            hashMap.put(SetKey.TITLE, "\u8fd4\u56de\u5230\u4e0a\u4e00\u7ea7\u76ee\u5f55");
            Object parent = file.getParent();
            if (!(parent == null || parent.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR))) {
                parent = parent + MqttTopic.TOPIC_LEVEL_SEPARATOR;
            }
            hashMap.put("info", parent);
            hashMap.put("fileName", "\u8fd4\u56de\u5230\u4e0a\u4e00\u7ea7\u76ee\u5f55");
            hashMap.put(AgooConstants.MESSAGE_TYPE, Integer.valueOf(0));
            arrayList.add(hashMap);
        }
        if (listFiles != null) {
            Arrays.sort(listFiles, g);
            for (int i2 = 0; i2 < listFiles.length; i2++) {
                if (listFiles[i2].isDirectory()) {
                    Map hashMap2 = new HashMap();
                    hashMap2.put(SetKey.TITLE, listFiles[i2].getName());
                    Object path = listFiles[i2].getPath();
                    if (!(path == null || path.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR))) {
                        path = path + MqttTopic.TOPIC_LEVEL_SEPARATOR;
                    }
                    hashMap2.put("info", path);
                    hashMap2.put("fileName", listFiles[i2].getName());
                    hashMap2.put(AgooConstants.MESSAGE_TYPE, Integer.valueOf(0));
                    arrayList.add(hashMap2);
                }
            }
            while (i < listFiles.length) {
                if (!listFiles[i].isDirectory()) {
                    Map hashMap3 = new HashMap();
                    hashMap3.put(SetKey.TITLE, listFiles[i].getName());
                    hashMap3.put("info", listFiles[i].getPath());
                    hashMap3.put("fileName", listFiles[i].getName());
                    hashMap3.put(AgooConstants.MESSAGE_TYPE, Integer.valueOf(1));
                    arrayList.add(hashMap3);
                }
                i++;
            }
        }
        return arrayList;
    }

    private List<Map<String, Object>> getDataFromFilePathAndFilter() {
        List dataFromFilePath = getDataFromFilePath();
        if (this.h == null) {
            return null;
        }
        int i;
        List<Map<String, Object>> arrayList = new ArrayList();
        if (this.b.equals(k.e())) {
            i = 0;
        } else {
            arrayList.add(dataFromFilePath.get(0));
            i = 1;
        }
        while (i < dataFromFilePath.size()) {
            Map map = (Map) dataFromFilePath.get(i);
            if (((Integer) map.get(AgooConstants.MESSAGE_TYPE)).intValue() == 0) {
                arrayList.add(map);
            } else if (((String) map.get("fileName")).endsWith(this.h)) {
                arrayList.add(map);
            }
            i++;
        }
        return arrayList;
    }

    private List<Map<String, Object>> getDataFromFilePathList() {
        List<Map<String, Object>> arrayList = new ArrayList();
        Iterator it = this.i.iterator();
        while (it.hasNext()) {
            File file = new File((String) it.next());
            Map hashMap = new HashMap();
            hashMap.put(SetKey.TITLE, file.getName());
            hashMap.put("info", file.getPath());
            hashMap.put("fileName", file.getName());
            hashMap.put(AgooConstants.MESSAGE_TYPE, Integer.valueOf(1));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public final void a() {
        if (this.e == 1000 || this.e == 1001) {
            CharSequence path = this.j.getPath();
            if (!(path == null || path.endsWith(MqttTopic.TOPIC_LEVEL_SEPARATOR))) {
                path = path + MqttTopic.TOPIC_LEVEL_SEPARATOR;
            }
            this.c.setText(path);
        }
    }

    public void setOnTorrentFileClickListener(b bVar) {
        this.k = bVar;
    }

    static {
        g = new a();
    }
}
