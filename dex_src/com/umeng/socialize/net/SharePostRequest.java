package com.umeng.socialize.net;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.a;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.common.ImageFormat;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.net.base.SocializeRequest;
import com.umeng.socialize.net.base.SocializeRequest.RequestMethod;
import com.umeng.socialize.net.base.SocializeReseponse;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.umeng.socialize.net.utils.URequest.FilePair;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;
import java.util.Map;

public class SharePostRequest extends SocializeRequest {
    private static final String a = "/share/add/";
    private static final int b = 9;
    private String c;
    private String d;
    private ShareContent e;

    public SharePostRequest(Context context, String str, String str2, ShareContent shareContent) {
        super(context, a.d, SocializeReseponse.class, 9, RequestMethod.POST);
        this.mContext = context;
        this.c = str;
        this.d = str2;
        this.e = shareContent;
        Log.e(new StringBuilder("xxxx content=").append(shareContent.mMedia).toString());
    }

    public void onPrepareRequest() {
        addStringParams("to", this.c);
        addStringParams("ct", this.e.mText);
        addStringParams("usid", this.d);
        addStringParams("ak", SocializeUtils.getAppkey(this.mContext));
        addStringParams("ek", Config.EntityKey);
        if (this.e.mLocation != null) {
            addStringParams("lc", this.e.mLocation.toString());
        }
        addMediaParams(this.e.mMedia);
    }

    protected String getPath() {
        return new StringBuilder(a).append(SocializeUtils.getAppkey(this.mContext)).append("/").append(Config.EntityKey).append("/").toString();
    }

    public Map<String, FilePair> getFilePair() {
        if (this.e == null || this.e.mMedia == null || this.e.mMedia.isUrlMedia()) {
            return super.getFilePair();
        }
        Map<String, FilePair> filePair = super.getFilePair();
        if (this.e.mMedia instanceof UMImage) {
            UMImage uMImage = (UMImage) this.e.mMedia;
            uMImage.asFileImage().getPath();
            Object asBinImage = uMImage.asBinImage();
            String checkFormat = ImageFormat.checkFormat(asBinImage);
            if (TextUtils.isEmpty(checkFormat)) {
                checkFormat = "png";
            }
            String str = System.currentTimeMillis();
            Log.e(new StringBuilder("xxxx filedata=").append(asBinImage).toString());
            filePair.put(SocializeProtocolConstants.PROTOCOL_KEY_IMAGE, new FilePair(str + "." + checkFormat, asBinImage));
        }
        return filePair;
    }
}
