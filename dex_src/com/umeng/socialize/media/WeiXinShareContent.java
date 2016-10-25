package com.umeng.socialize.media;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.tencent.mm.sdk.modelmsg.WXEmojiObject;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXVideoObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.utils.BitmapUtils;
import com.umeng.socialize.utils.ContextUtil;
import com.umeng.socialize.utils.Log;
import com.xunlei.common.pay.XLPayErrorCode;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

public class WeiXinShareContent {
    public static final String TYPE_EMOJI = "emoji";
    public static final String TYPE_IMAGE = "image";
    public static final String TYPE_MUSIC = "music";
    public static final String TYPE_TEXT = "text";
    public static final String TYPE_TEXT_IMAGE = "text_image";
    public static final String TYPE_VIDEO = "video";
    private final String DEFAULT_TITLE;
    private final int DESCRIPTION_LIMIT;
    private final int IMAGE_LIMIT;
    private final int SHOW_COMPRESS_TOAST;
    private final int SHOW_TITLE_TOAST;
    private final int THUMB_LIMIT;
    private final int THUMB_SIZE;
    private final int TITLE_LIMIT;
    private ShareContent mShareContent;
    public String mShareType;
    private String mTargetUrl;
    private String mText;
    private String mTitle;
    private WXMediaMessage mWxMediaMessage;
    private UMediaObject uMediaObject;

    public WeiXinShareContent(ShareContent shareContent) {
        this.DEFAULT_TITLE = "\u5206\u4eab\u5230\u5fae\u4fe1";
        this.mWxMediaMessage = null;
        this.THUMB_SIZE = 150;
        this.THUMB_LIMIT = 32768;
        this.IMAGE_LIMIT = 102400;
        this.TITLE_LIMIT = 512;
        this.DESCRIPTION_LIMIT = 1024;
        this.SHOW_COMPRESS_TOAST = 1;
        this.SHOW_TITLE_TOAST = 2;
        this.mShareContent = shareContent;
        this.mTitle = shareContent.mTitle;
        this.mText = shareContent.mText;
        this.uMediaObject = shareContent.mMedia;
        this.mTargetUrl = shareContent.mTargetUrl;
    }

    public void parseMediaType() {
        if (!TextUtils.isEmpty(this.mText) && this.uMediaObject == null) {
            this.mShareType = TYPE_TEXT;
        } else if (this.uMediaObject != null && (this.uMediaObject instanceof UMEmoji)) {
            this.mShareType = TYPE_EMOJI;
        } else if (TextUtils.isEmpty(this.mText) && this.uMediaObject != null && (this.uMediaObject instanceof UMImage)) {
            this.mShareType = TYPE_IMAGE;
        } else if (this.uMediaObject != null && (this.uMediaObject instanceof UMusic)) {
            this.mShareType = TYPE_MUSIC;
        } else if (this.uMediaObject != null && (this.uMediaObject instanceof UMVideo)) {
            this.mShareType = TYPE_VIDEO;
        } else if (!TextUtils.isEmpty(this.mText) && this.uMediaObject != null && (this.uMediaObject instanceof UMImage)) {
            this.mShareType = TYPE_TEXT_IMAGE;
        }
    }

    public WXMediaMessage getWxMediaMessage() {
        WXMediaMessage wXMediaMessage = null;
        if (this.mShareContent.mMedia == null) {
            if (!TextUtils.isEmpty(this.mShareContent.mText)) {
                Log.i("--->", "text share..");
                wXMediaMessage = buildTextParams();
            }
        } else if (this.mShareContent.mMedia instanceof UMEmoji) {
            wXMediaMessage = buildEmojiParams();
        } else if (TextUtils.isEmpty(this.mShareContent.mText) && (this.mShareContent.mMedia instanceof UMImage)) {
            Log.d("weixin", "picture share");
            wXMediaMessage = buildImageParams();
        } else if (this.mShareContent.mMedia instanceof UMusic) {
            wXMediaMessage = buildMusicParams();
        } else if (this.mShareContent.mMedia instanceof UMVideo) {
            wXMediaMessage = buildVideoParams();
        } else if (!TextUtils.isEmpty(this.mShareContent.mText) && (this.mShareContent.mMedia instanceof UMImage)) {
            Log.d("\u56fe\u6587\u5206\u4eab..");
            wXMediaMessage = buildTextImageParams();
        }
        if (wXMediaMessage != null) {
            byte[] bArr = wXMediaMessage.thumbData;
            if (bArr != null && bArr.length > 32768) {
                wXMediaMessage.thumbData = compressBitmap(bArr, 32768);
                Log.d(new StringBuilder("\u538b\u7f29\u4e4b\u540e\u7f29\u7565\u56fe\u5927\u5c0f : ").append(wXMediaMessage.thumbData.length / 1024).append(" KB.").toString());
            }
            if (TextUtils.isEmpty(wXMediaMessage.title) || wXMediaMessage.title.getBytes().length < 512) {
                this.mTitle = "\u5206\u4eab\u5230\u5fae\u4fe1";
            } else {
                wXMediaMessage.title = new String(wXMediaMessage.title.getBytes(), 0, 512);
            }
            if (!TextUtils.isEmpty(wXMediaMessage.description) && wXMediaMessage.description.getBytes().length >= 1024) {
                wXMediaMessage.description = new String(wXMediaMessage.description.getBytes(), 0, 1024);
            }
        }
        return wXMediaMessage;
    }

    private byte[] compressBitmap(byte[] bArr, int i) {
        if (bArr != null && bArr.length >= i) {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            int i2 = 0;
            int i3 = 1;
            while (i2 == 0 && i3 <= 10) {
                int pow = (int) (Math.pow(0.8d, (double) i3) * 100.0d);
                Log.d(new StringBuilder("quality = ").append(pow).toString());
                decodeByteArray.compress(CompressFormat.JPEG, pow, byteArrayOutputStream);
                Log.d(new StringBuilder("WeiXin Thumb Size = ").append(byteArrayOutputStream.toByteArray().length / 1024).append(" KB").toString());
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
            if (bArr != null && bArr.length <= 0) {
                Log.e("### \u60a8\u7684\u539f\u59cb\u56fe\u7247\u592a\u5927,\u5bfc\u81f4\u7f29\u7565\u56fe\u538b\u7f29\u8fc7\u540e\u8fd8\u5927\u4e8e32KB,\u8bf7\u5c06\u5206\u4eab\u5230\u5fae\u4fe1\u7684\u56fe\u7247\u8fdb\u884c\u9002\u5f53\u7f29\u5c0f.");
            }
        }
        return bArr;
    }

    private WXMediaMessage buildEmojiParams() {
        UMEmoji uMEmoji = (UMEmoji) this.mShareContent.mMedia;
        UMImage uMImage = uMEmoji.mSrcImage;
        String toString = uMImage.asFileImage().toString();
        WXEmojiObject wXEmojiObject = new WXEmojiObject();
        if (uMEmoji.mSrcImage.isUrlMedia()) {
            toString = BitmapUtils.getFileName(uMImage.toUrl());
            if (!new File(toString).exists()) {
                BitmapUtils.loadImage(uMImage.toUrl(), XLPayErrorCode.XLP_BD_PAYING, XLPayErrorCode.XLP_BD_PAYING);
            }
        }
        wXEmojiObject.emojiPath = toString;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXEmojiObject;
        if (uMEmoji.getThumbImage() != null) {
            wXMediaMessage.thumbData = uMEmoji.mThumb.toByte();
        } else if (TextUtils.isEmpty(uMEmoji.getThumb())) {
            wXMediaMessage.thumbData = uMEmoji.mSrcImage.toByte();
        } else {
            Bitmap loadImage = BitmapUtils.loadImage(uMEmoji.getThumb(), XLPayErrorCode.XLP_BD_PAYING, XLPayErrorCode.XLP_BD_PAYING);
            wXMediaMessage.thumbData = BitmapUtils.bitmap2Bytes(loadImage);
            loadImage.recycle();
        }
        wXMediaMessage.title = this.mTitle;
        wXMediaMessage.description = this.mShareContent.mText;
        return wXMediaMessage;
    }

    private WXMediaMessage buildMusicParams() {
        UMusic uMusic = (UMusic) this.mShareContent.mMedia;
        WXMusicObject wXMusicObject = new WXMusicObject();
        if (!TextUtils.isEmpty(uMusic.getTargetUrl())) {
            wXMusicObject.musicUrl = uMusic.getTargetUrl();
        } else if (TextUtils.isEmpty(this.mShareContent.mTargetUrl)) {
            wXMusicObject.musicUrl = "http://wsq.umeng.com";
        } else {
            wXMusicObject.musicUrl = this.mShareContent.mTargetUrl;
        }
        wXMusicObject.musicDataUrl = uMusic.toUrl();
        if (!TextUtils.isEmpty(uMusic.getLowBandDataUrl())) {
            wXMusicObject.musicLowBandDataUrl = uMusic.getLowBandDataUrl();
        }
        if (!TextUtils.isEmpty(uMusic.getLowBandUrl())) {
            wXMusicObject.musicLowBandUrl = uMusic.getLowBandUrl();
        }
        WXMediaMessage buildMediaMessage = buildMediaMessage();
        buildMediaMessage.mediaObject = wXMusicObject;
        if (!TextUtils.isEmpty(uMusic.getTitle())) {
            buildMediaMessage.title = uMusic.getTitle();
        } else if (TextUtils.isEmpty(this.mShareContent.mTitle)) {
            buildMediaMessage.title = "\u5206\u4eab\u97f3\u9891";
        } else {
            buildMediaMessage.title = this.mShareContent.mTitle;
        }
        buildMediaMessage.description = this.mShareContent.mText;
        buildMediaMessage.mediaObject = wXMusicObject;
        if (uMusic.getThumb() != null) {
            if (!(BuildConfig.VERSION_NAME.equals(uMusic.getThumb()) && uMusic.getThumb() == null)) {
                byte[] asBinImage = uMusic.getThumbImage() != null ? uMusic.getThumbImage().asBinImage() : !TextUtils.isEmpty(uMusic.getThumb()) ? new UMImage(ContextUtil.getContext(), uMusic.getThumb()).asBinImage() : null;
                if (asBinImage != null) {
                    Log.d("share with thumb");
                    buildMediaMessage.thumbData = asBinImage;
                }
            }
        }
        return buildMediaMessage;
    }

    private WXMediaMessage buildTextParams() {
        WXTextObject wXTextObject = new WXTextObject();
        wXTextObject.text = this.mShareContent.mText;
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        wXMediaMessage.mediaObject = wXTextObject;
        wXMediaMessage.description = this.mShareContent.mText;
        wXMediaMessage.title = this.mTitle;
        return wXMediaMessage;
    }

    private WXMediaMessage buildImageParams() {
        UMImage uMImage = (UMImage) this.mShareContent.mMedia;
        WXImageObject wXImageObject = new WXImageObject();
        WXMediaMessage buildMediaMessage = buildMediaMessage();
        if (uMImage.isUrlMedia()) {
            wXImageObject.imageUrl = uMImage.asUrlImage();
            wXImageObject.imageData = uMImage.asBinImage();
            buildMediaMessage.mediaObject = wXImageObject;
            return buildMediaMessage;
        }
        wXImageObject.imageData = uMImage.asBinImage();
        if (wXImageObject.imageData.length > 102400) {
            wXImageObject.imageData = BitmapUtils.compressBitmap(wXImageObject.imageData, 102400);
        }
        buildMediaMessage.mediaObject = wXImageObject;
        return buildMediaMessage;
    }

    private WXMediaMessage buildVideoParams() {
        UMVideo uMVideo = (UMVideo) this.mShareContent.mMedia;
        WXVideoObject wXVideoObject = new WXVideoObject();
        wXVideoObject.videoUrl = uMVideo.toUrl();
        if (!TextUtils.isEmpty(uMVideo.getLowBandUrl())) {
            wXVideoObject.videoLowBandUrl = uMVideo.getLowBandUrl();
        }
        WXMediaMessage buildMediaMessage = buildMediaMessage();
        buildMediaMessage.mediaObject = wXVideoObject;
        if (TextUtils.isEmpty(this.mShareContent.mTitle)) {
            buildMediaMessage.title = "\u5206\u4eab\u89c6\u9891";
        } else {
            buildMediaMessage.title = this.mShareContent.mTitle;
        }
        buildMediaMessage.description = this.mShareContent.mText;
        byte[] asBinImage = !TextUtils.isEmpty(uMVideo.getThumb()) ? new UMImage(ContextUtil.getContext(), uMVideo.getThumb()).asBinImage() : uMVideo.getThumbImage() != null ? uMVideo.getThumbImage().asBinImage() : null;
        if (asBinImage != null && asBinImage.length > 0) {
            buildMediaMessage.thumbData = asBinImage;
        }
        return buildMediaMessage;
    }

    private WXMediaMessage buildTextImageParams() {
        if (TextUtils.isEmpty(this.mTargetUrl)) {
            this.mTargetUrl = "http://wsq.umeng.com";
        }
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        wXWebpageObject.webpageUrl = this.mTargetUrl;
        WXMediaMessage buildMediaMessage = buildMediaMessage();
        buildMediaMessage.title = this.mTitle;
        buildMediaMessage.description = this.mShareContent.mText;
        buildMediaMessage.mediaObject = wXWebpageObject;
        return buildMediaMessage;
    }

    private WXMediaMessage buildMediaMessage() {
        Object toString;
        Object obj = null;
        UMImage uMImage;
        String asUrlImage;
        String str;
        if (this.mShareContent.mMedia instanceof UMImage) {
            uMImage = (UMImage) this.mShareContent.mMedia;
            if (uMImage.asFileImage() != null) {
                toString = uMImage.asFileImage().toString();
                Log.d("localPath", toString);
            } else {
                asUrlImage = uMImage.asUrlImage();
                toString = null;
                str = asUrlImage;
            }
        } else if (this.mShareContent.mMedia instanceof UMVideo) {
            String str2;
            uMImage = ((UMVideo) this.mShareContent.mMedia).getThumbImage();
            if (uMImage == null) {
                str2 = null;
            } else if (uMImage == null || uMImage.asFileImage() == null) {
                str2 = uMImage.asUrlImage();
            } else {
                toString = uMImage.asFileImage().toString();
            }
            asUrlImage = str2;
            toString = null;
            str = asUrlImage;
        } else {
            if (this.mShareContent.mMedia instanceof UMusic) {
                uMImage = ((UMusic) this.mShareContent.mMedia).getThumbImage();
                if (uMImage != null) {
                    if (uMImage == null || uMImage.asFileImage() == null) {
                        asUrlImage = uMImage.asUrlImage();
                        toString = null;
                        str = asUrlImage;
                    } else {
                        toString = uMImage.asFileImage().toString();
                    }
                }
            }
            toString = null;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage();
        if (!TextUtils.isEmpty(obj)) {
            wXMediaMessage.thumbData = BitmapUtils.bitmap2Bytes(BitmapUtils.loadImage(obj, XLPayErrorCode.XLP_BD_PAYING, XLPayErrorCode.XLP_BD_PAYING));
        } else if (!TextUtils.isEmpty(toString)) {
            Bitmap thumbFromCache = getThumbFromCache(toString);
            Log.d("localBitmap", String.valueOf(thumbFromCache));
            wXMediaMessage.setThumbImage(thumbFromCache);
            if (!(thumbFromCache == null || thumbFromCache.isRecycled())) {
                thumbFromCache.recycle();
            }
        }
        return wXMediaMessage;
    }

    private Bitmap getThumbFromCache(String str) {
        Log.d("imagePath", str);
        if (!BitmapUtils.isFileExist(str)) {
            return null;
        }
        Log.d("imagePath", new StringBuilder("iamge exist:").append(str).toString());
        if (BitmapUtils.isNeedScale(str, 32768)) {
            Bitmap bitmapFromFile = BitmapUtils.getBitmapFromFile(str, XLPayErrorCode.XLP_BD_PAYING, XLPayErrorCode.XLP_BD_PAYING);
            Log.d("imagePath", new StringBuilder("bitmap exist resize:").append(bitmapFromFile).toString());
            return bitmapFromFile;
        }
        bitmapFromFile = BitmapUtils.getBitmapFromFile(str);
        Log.d("imagePath", new StringBuilder("bitmap exist:").append(bitmapFromFile).toString());
        return bitmapFromFile;
    }
}
