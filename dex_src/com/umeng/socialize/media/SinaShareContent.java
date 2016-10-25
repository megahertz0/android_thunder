package com.umeng.socialize.media;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.MusicObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.VideoObject;
import com.sina.weibo.sdk.api.VoiceObject;
import com.sina.weibo.sdk.api.WebpageObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.utils.Utility;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.common.ResContainer;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.editorpage.ShareActivity;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.xunlei.tdlive.R;
import com.xunlei.thundersniffer.sniff.sniffer.ThunderSniffer;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class SinaShareContent extends SimpleShareContent {
    private final int IMAGE_LIMIT;
    private final int THUMB_LIMIT;
    private Context context;
    private UMImage mExtra;

    public SinaShareContent(ShareContent shareContent) {
        super(shareContent);
        this.THUMB_LIMIT = 24576;
        this.IMAGE_LIMIT = 153600;
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMVideo)) {
            setVideo((UMVideo) shareContent.mMedia);
        }
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMusic)) {
            setMusic((UMusic) shareContent.mMedia);
        }
        if (shareContent.mExtra != null) {
            this.mExtra = (UMImage) shareContent.mExtra;
        }
    }

    public void SetContext(Context context) {
        this.context = context;
    }

    public WeiboMultiMessage getMessage() {
        WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
        weiboMultiMessage.textObject = getTextObj();
        if (getImage() != null) {
            weiboMultiMessage.imageObject = getImageObj();
        }
        if (!TextUtils.isEmpty(getTargeturl())) {
            weiboMultiMessage.mediaObject = getWebpageObj();
        }
        if (getMusic() != null) {
            weiboMultiMessage.mediaObject = getMusicObj();
            Log.d(ShareActivity.KEY_PLATFORM, "share music");
        }
        if (getVideo() != null) {
            weiboMultiMessage.mediaObject = getVideoObj();
            Log.d(ShareActivity.KEY_PLATFORM, "share video");
        }
        return weiboMultiMessage;
    }

    private TextObject getTextObj() {
        TextObject textObject = new TextObject();
        textObject.text = getText();
        return textObject;
    }

    private ImageObject getImageObj() {
        ImageObject imageObject = new ImageObject();
        if (getImage().asBitmap() != null) {
            byte[] asBinImage = getImage().asBinImage();
            imageObject.setImageObject(BitmapFactory.decodeByteArray(asBinImage, 0, asBinImage.length));
        }
        return imageObject;
    }

    private WebpageObject getWebpageObj() {
        Bitmap decodeByteArray;
        WebpageObject webpageObject = new WebpageObject();
        webpageObject.identify = Utility.generateGUID();
        if (TextUtils.isEmpty(getTitle())) {
            webpageObject.title = "\u5206\u4eab\u94fe\u63a5";
        } else {
            webpageObject.title = getTitle();
        }
        webpageObject.description = getText();
        if (this.mExtra != null) {
            byte[] asBinImage = this.mExtra.asBinImage();
            if (this.mExtra.asBinImage().length > 24576) {
                decodeByteArray = BitmapFactory.decodeByteArray(compressBitmap(asBinImage, 24576), 0, compressBitmap(asBinImage, 24576).length);
            } else {
                decodeByteArray = this.mExtra.asBitmap();
            }
        } else {
            decodeByteArray = BitmapFactory.decodeResource(this.context.getResources(), ResContainer.getResourceId(this.context, "drawable", "sina_web_default"));
        }
        webpageObject.setThumbImage(decodeByteArray);
        webpageObject.actionUrl = getTargeturl();
        webpageObject.defaultText = getText();
        Log.d(ThunderSniffer.JSINTERFACE_NAMESPACE, new StringBuilder("args check:").append(webpageObject.checkArgs()).toString());
        return webpageObject;
    }

    public byte[] Bitmap2Bytes(Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(CompressFormat.PNG, R.styleable.AppCompatTheme_buttonStyle, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    private MusicObject getMusicObj() {
        MusicObject musicObject = new MusicObject();
        musicObject.identify = Utility.generateGUID();
        if (TextUtils.isEmpty(getTitle())) {
            musicObject.title = "\u5206\u4eab\u97f3\u4e50";
        } else {
            musicObject.title = getTitle();
        }
        musicObject.description = getMusic().mText;
        Bitmap bitmap = null;
        byte[] compressBitmap;
        if (getMusic().getThumbImage() != null) {
            compressBitmap = compressBitmap(getMusic().getThumbImage().asBinImage(), 24576);
            if (compressBitmap != null) {
                bitmap = BitmapFactory.decodeByteArray(compressBitmap, 0, compressBitmap.length);
            }
        } else if (TextUtils.isEmpty(getMusic().getThumb())) {
            bitmap = BitmapFactory.decodeResource(this.context.getResources(), ResContainer.getResourceId(this.context, "drawable", "ic_logo"));
        } else {
            compressBitmap = compressBitmap(new UMImage(ContextUtil.getContext(), getMusic().getThumb()).asBinImage(), 24576);
            if (compressBitmap != null) {
                bitmap = BitmapFactory.decodeByteArray(compressBitmap, 0, compressBitmap.length);
                Log.d("UM", "get thumb bitmap");
            }
        }
        musicObject.setThumbImage(bitmap);
        musicObject.actionUrl = getMusic().toUrl();
        if (!TextUtils.isEmpty(getMusic().getLowBandDataUrl())) {
            musicObject.dataUrl = getMusic().getLowBandDataUrl();
        }
        if (!TextUtils.isEmpty(getMusic().getHighBandDataUrl())) {
            musicObject.dataHdUrl = getMusic().getHighBandDataUrl();
        }
        if (!TextUtils.isEmpty(getMusic().getH5Url())) {
            musicObject.h5Url = getMusic().getH5Url();
        }
        if (getMusic().getDuration() > 0) {
            musicObject.duration = getMusic().getDuration();
        } else {
            musicObject.duration = 10;
        }
        if (!TextUtils.isEmpty(getMusic().getDescription())) {
            musicObject.description = getMusic().getDescription();
        }
        if (!TextUtils.isEmpty(getText())) {
            musicObject.defaultText = getText();
        }
        return musicObject;
    }

    private VideoObject getVideoObj() {
        VideoObject videoObject = new VideoObject();
        videoObject.identify = Utility.generateGUID();
        if (TextUtils.isEmpty(getTitle())) {
            videoObject.title = "\u5206\u4eab\u89c6\u9891";
        } else {
            videoObject.title = getTitle();
        }
        videoObject.description = getText();
        Bitmap bitmap = null;
        byte[] compressBitmap;
        if (getVideo().getThumbImage() != null) {
            compressBitmap = compressBitmap(getVideo().getThumbImage().asBinImage(), 24576);
            if (compressBitmap != null) {
                bitmap = BitmapFactory.decodeByteArray(compressBitmap, 0, compressBitmap.length);
            }
        } else if (TextUtils.isEmpty(getVideo().getThumb())) {
            bitmap = BitmapFactory.decodeResource(this.context.getResources(), ResContainer.getResourceId(this.context, "drawable", "ic_logo"));
        } else {
            compressBitmap = new UMImage(ContextUtil.getContext(), getVideo().getThumb()).asBinImage();
            if (compressBitmap != null) {
                bitmap = BitmapFactory.decodeByteArray(compressBitmap, 0, compressBitmap.length);
            }
        }
        videoObject.setThumbImage(bitmap);
        videoObject.actionUrl = getVideo().toUrl();
        if (!TextUtils.isEmpty(getVideo().getLowBandDataUrl())) {
            videoObject.dataUrl = getVideo().getLowBandDataUrl();
        }
        if (!TextUtils.isEmpty(getVideo().getHighBandDataUrl())) {
            videoObject.dataHdUrl = getVideo().getHighBandDataUrl();
        }
        if (!TextUtils.isEmpty(getVideo().getH5Url())) {
            videoObject.h5Url = getVideo().getH5Url();
        }
        if (getVideo().getDuration() > 0) {
            videoObject.duration = getVideo().getDuration();
        } else {
            videoObject.duration = 10;
        }
        if (!TextUtils.isEmpty(getVideo().getDescription())) {
            videoObject.description = getVideo().getDescription();
        }
        videoObject.defaultText = "Video \u5206\u4eab\u89c6\u9891";
        return videoObject;
    }

    private VoiceObject getVoiceObj() {
        return null;
    }

    private byte[] compressBitmap(byte[] bArr, int i) {
        if (bArr == null || bArr.length < i) {
            Log.d(SocializeConstants.JSON_DATA, new StringBuilder("weibo data size:").append(bArr.length).toString());
        } else {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            int i2 = 0;
            int i3 = 1;
            while (i2 == 0 && i3 <= 20) {
                decodeByteArray.compress(CompressFormat.JPEG, (int) (Math.pow(0.8d, (double) i3) * 100.0d), byteArrayOutputStream);
                if (byteArrayOutputStream.size() < i) {
                    i2 = 1;
                } else {
                    byteArrayOutputStream.reset();
                    i3++;
                }
            }
            bArr = byteArrayOutputStream.toByteArray();
            if (!decodeByteArray.isRecycled()) {
                decodeByteArray.recycle();
            }
        }
        return bArr;
    }
}
