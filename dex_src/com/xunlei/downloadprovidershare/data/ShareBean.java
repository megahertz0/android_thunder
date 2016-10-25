package com.xunlei.downloadprovidershare.data;

import com.sina.weibo.sdk.component.GameManager;
import com.xunlei.xllib.b.k;

public final class ShareBean {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public boolean g;
    public String h;
    public int i;
    public a j;
    public long k;
    public long l;
    public OperationType m;

    public enum OperationType {
        None,
        CopyUrl,
        Qr,
        SystemShare,
        Download,
        Upload,
        Accuse;

        static {
            None = new com.xunlei.downloadprovidershare.data.ShareBean.OperationType("None", 0);
            CopyUrl = new com.xunlei.downloadprovidershare.data.ShareBean.OperationType("CopyUrl", 1);
            Qr = new com.xunlei.downloadprovidershare.data.ShareBean.OperationType("Qr", 2);
            SystemShare = new com.xunlei.downloadprovidershare.data.ShareBean.OperationType("SystemShare", 3);
            Download = new com.xunlei.downloadprovidershare.data.ShareBean.OperationType("Download", 4);
            Upload = new com.xunlei.downloadprovidershare.data.ShareBean.OperationType("Upload", 5);
            Accuse = new com.xunlei.downloadprovidershare.data.ShareBean.OperationType("Accuse", 6);
            a = new com.xunlei.downloadprovidershare.data.ShareBean.OperationType[]{None, CopyUrl, Qr, SystemShare, Download, Upload, Accuse};
        }
    }

    public ShareBean(String str, String str2, String str3, String str4) {
        this("other", str, str2, str3, str4);
    }

    public ShareBean(String str, String str2, String str3, String str4, String str5) {
        this.m = OperationType.None;
        this.e = str;
        this.a = k.b(str2, GameManager.DEFAULT_CHARSET);
        this.b = k.b(str3, GameManager.DEFAULT_CHARSET);
        this.c = k.b(str4, GameManager.DEFAULT_CHARSET);
        this.d = k.b(str5, GameManager.DEFAULT_CHARSET);
    }

    public final String toString() {
        return new StringBuilder("ShareBean{targetUrl='").append(this.a).append('\'').append(", imgUrl='").append(this.b).append('\'').append(", title='").append(this.c).append('\'').append(", content='").append(this.d).append('\'').append(", from='").append(this.e).append('\'').append(", to='").append(this.f).append('\'').append(", isVideo=").append(this.g).append(", storid='").append(this.h).append('\'').append(", taskResouceId=").append(this.i).append(", taskInfo=").append(this.j).append(", funId=").append(this.k).append(", taskId=").append(this.l).append(", operationType=").append(this.m).append('}').toString();
    }

    public final boolean a() {
        return this.l > 0;
    }
}
