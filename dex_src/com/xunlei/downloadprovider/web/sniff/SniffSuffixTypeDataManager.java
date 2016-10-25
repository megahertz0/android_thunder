package com.xunlei.downloadprovider.web.sniff;

import com.umeng.socialize.editorpage.ShareActivity;
import com.xunlei.downloadprovider.util.sniff.f;
import com.xunlei.xllib.b.d;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public final class SniffSuffixTypeDataManager {
    public static final String[] a;
    public static final String[] b;
    public static final String[] c;
    public static final String[] d;
    public static final String[] e;
    public static final String[] f;
    public static final String[] g;
    private static SniffSuffixTypeDataManager o;
    public List<b> h;
    public List<b> i;
    public List<b> j;
    public List<b> k;
    public List<b> l;
    public List<b> m;
    public Set<String> n;

    public enum SuffixDataType {
        video,
        torrent,
        zip,
        music,
        app,
        doc;

        static {
            video = new com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager.SuffixDataType("video", 0);
            torrent = new com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager.SuffixDataType("torrent", 1);
            zip = new com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager.SuffixDataType("zip", 2);
            music = new com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager.SuffixDataType("music", 3);
            app = new com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager.SuffixDataType("app", 4);
            doc = new com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager.SuffixDataType("doc", 5);
            a = new com.xunlei.downloadprovider.web.sniff.SniffSuffixTypeDataManager.SuffixDataType[]{video, torrent, zip, music, app, doc};
        }
    }

    public SniffSuffixTypeDataManager() {
        this.h = new ArrayList();
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = new ArrayList();
        this.l = new ArrayList();
        this.m = new ArrayList();
    }

    static {
        a = new String[]{"mp4", "avi", "rmvb", "rm", "3gp", "flv", "wmv", "mkv", "mpg", "mov"};
        b = new String[]{"torrent"};
        c = new String[]{"rar", "zip", "7z"};
        d = new String[]{"mp3", "wav", "ram", "wma", "amr", "aac"};
        e = new String[]{"apk"};
        f = new String[]{ShareActivity.KEY_TEXT, "rtf", "doc", "docx", "ppt", "pptx", "xls", "xlsx", "pdf"};
        g = new String[]{"mp4", "avi", "rmvb", "rm", "3gp", "flv", "wmv", "mkv", "mpg", "mov", "torrent", "magnet", "rar", "zip", "7z", "mp3", "wav", "ram", "wma", "amr", "aac", "apk", ShareActivity.KEY_TEXT, "rtf", "doc", "docx", "ppt", "pptx", "xls", "xlsx", "pdf"};
        o = null;
    }

    public static SniffSuffixTypeDataManager a() {
        if (o == null) {
            o = new SniffSuffixTypeDataManager();
        }
        return o;
    }

    public final void a(String str, OperSniffSuffixSet operSniffSuffixSet) {
        if (this.n == null) {
            this.n = f.b();
        }
        if (operSniffSuffixSet.equals(OperSniffSuffixSet.add) && !this.n.contains(str)) {
            this.n.add(str);
            if (str.equals("torrent")) {
                this.n.add("magnet");
            }
        }
        if (operSniffSuffixSet.equals(OperSniffSuffixSet.delete) && this.n.contains(str)) {
            this.n.remove(str);
            if (str.equals("torrent")) {
                this.n.remove("magnet");
            }
        }
        f.a(this.n);
    }

    public final void b() {
        if (!d.a(this.h)) {
            this.h.clear();
        }
        for (String str : a) {
            b bVar = new b();
            bVar.a = str;
            bVar.b = this.n.contains(str);
            this.h.add(bVar);
        }
    }

    public final void c() {
        if (!d.a(this.i)) {
            this.i.clear();
        }
        for (String str : b) {
            b bVar = new b();
            bVar.a = str;
            bVar.b = this.n.contains(str);
            this.i.add(bVar);
        }
    }

    public final void d() {
        if (!d.a(this.j)) {
            this.j.clear();
        }
        for (String str : c) {
            b bVar = new b();
            bVar.a = str;
            bVar.b = this.n.contains(str);
            this.j.add(bVar);
        }
    }

    public final void e() {
        if (!d.a(this.k)) {
            this.k.clear();
        }
        for (String str : d) {
            b bVar = new b();
            bVar.a = str;
            bVar.b = this.n.contains(str);
            this.k.add(bVar);
        }
    }

    public final void f() {
        if (!d.a(this.l)) {
            this.l.clear();
        }
        for (String str : e) {
            b bVar = new b();
            bVar.a = str;
            bVar.b = this.n.contains(str);
            this.l.add(bVar);
        }
    }

    public final void g() {
        if (!d.a(this.m)) {
            this.m.clear();
        }
        for (String str : f) {
            b bVar = new b();
            bVar.a = str;
            bVar.b = this.n.contains(str);
            this.m.add(bVar);
        }
    }
}
