package com.xunlei.tdlive.protocol;

import com.xunlei.tdlive.util.f.f;
import java.io.File;

public class XLLiveRequestSimple extends XLLiveRequest {
    private String mName;
    private File mUpload;
    private String mUrl;

    public XLLiveRequestSimple(String str, String str2, String str3) {
        this(str, str2, str3, null, null);
    }

    public XLLiveRequestSimple(String str, String str2, String str3, String str4, File file) {
        super(str, str2);
        this.mUrl = str3;
        this.mName = str4;
        this.mUpload = file;
    }

    protected void onAddBodyParams(f fVar) {
        if (this.mName != null && this.mName.length() > 0 && this.mUpload != null && this.mUpload.exists()) {
            fVar.a(this.mName, this.mUpload);
        }
    }

    protected boolean useHttpPost() {
        return (this.mName == null || this.mName.length() <= 0 || this.mUpload == null) ? false : true;
    }

    protected String onGetURL() {
        return this.mUrl;
    }
}
