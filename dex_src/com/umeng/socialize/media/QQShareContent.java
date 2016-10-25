package com.umeng.socialize.media;

import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.Config;
import com.umeng.socialize.ShareContent;
import com.umeng.socialize.utils.BitmapUtils;
import com.umeng.socialize.utils.Log;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;

public class QQShareContent extends SimpleShareContent {
    protected static final String DEFAULT_TARGET_URL = "http://wsq.umeng.com/";
    public Map<String, String> mExtraData;
    public int mShareType;

    public QQShareContent(ShareContent shareContent) {
        super(shareContent);
        this.mShareType = 1;
        this.mExtraData = new HashMap();
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMusic)) {
            setMusic((UMusic) shareContent.mMedia);
        }
        if (shareContent.mMedia != null && (shareContent.mMedia instanceof UMVideo)) {
            setVideo((UMVideo) shareContent.mMedia);
        }
    }

    public Bundle buildParams() {
        Bundle bundle = new Bundle();
        bundle.putString("summary", getText());
        if (getImage() != null && TextUtils.isEmpty(getText())) {
            this.mShareType = 5;
            buildImageParams(bundle);
        } else if (getVideo() == null && getMusic() == null) {
            buildTextImageParams(bundle);
        } else {
            this.mShareType = 2;
            buildAudioParams(bundle);
        }
        bundle.putInt("req_type", this.mShareType);
        if (TextUtils.isEmpty(getTargeturl())) {
            setTargeturl(DEFAULT_TARGET_URL);
        }
        bundle.putString("targetUrl", getTargeturl());
        if (TextUtils.isEmpty(getTitle())) {
            bundle.putString(SetKey.TITLE, " ");
        } else {
            bundle.putString(SetKey.TITLE, getTitle());
        }
        if (Config.QQWITHQZONE == 1) {
            bundle.putInt("cflag", 1);
        } else if (Config.QQWITHQZONE == 2) {
            bundle.putInt("cflag", SimpleLog.LOG_LEVEL_DEBUG);
        }
        if (!TextUtils.isEmpty(Config.appName)) {
            bundle.putString("appName", Config.appName);
        }
        return bundle;
    }

    private void buildImageParams(Bundle bundle) {
        parseImage(getImage());
        String str = (String) this.mExtraData.get(Constant.IMAGE_PATH_LOCAL);
        String str2 = (String) this.mExtraData.get(Constant.IMAGE_PATH_URL);
        if (!TextUtils.isEmpty(str) && BitmapUtils.isFileExist(str)) {
            bundle.putString("imageLocalUrl", str);
        } else if (!TextUtils.isEmpty(str2)) {
            bundle.putString("imageUrl", str2);
        }
    }

    private void buildTextImageParams(Bundle bundle) {
        buildImageParams(bundle);
    }

    private void buildAudioParams(Bundle bundle) {
        UMediaObject uMediaObject;
        if (getMusic() != null) {
            UMusic music = getMusic();
            parseMusic();
            uMediaObject = music;
        } else if (getVideo() != null) {
            UMVideo video = getVideo();
            parseVideo();
            uMediaObject = video;
        } else {
            uMediaObject = null;
        }
        String str = (String) this.mExtraData.get(Constant.IMAGE_PATH_LOCAL);
        String str2 = (String) this.mExtraData.get(Constant.IMAGE_PATH_URL);
        if (!TextUtils.isEmpty(str) && BitmapUtils.isFileExist(str)) {
            bundle.putString("imageLocalUrl", str);
        } else if (!TextUtils.isEmpty(str2)) {
            bundle.putString("imageUrl", str2);
        }
        bundle.putString(Constant.AUDIO_URL, uMediaObject.toUrl());
    }

    protected void parseImage(UMImage uMImage) {
        if (uMImage != null) {
            String str = BuildConfig.VERSION_NAME;
            Log.v("10.12", new StringBuilder("image=").append(uMImage).toString());
            if (TextUtils.isEmpty(getTargeturl())) {
                if (TextUtils.isEmpty(uMImage.getTargetUrl())) {
                    setTargeturl(uMImage.toUrl());
                } else {
                    setTargeturl(uMImage.getTargetUrl());
                }
            }
            String toUrl = uMImage.toUrl();
            if (uMImage.asFileImage() != null) {
                str = uMImage.asFileImage().toString();
            }
            if (!BitmapUtils.isFileExist(str)) {
                str = BuildConfig.VERSION_NAME;
            }
            Log.v("10.12", new StringBuilder("image path =").append(str).toString());
            this.mExtraData.put(Constant.IMAGE_PATH_LOCAL, str);
            this.mExtraData.put(Constant.IMAGE_PATH_URL, toUrl);
        }
    }

    protected void parseMusic() {
        UMusic music = getMusic();
        this.mExtraData.put(Constant.AUDIO_URL, music.toUrl());
        boolean isEmpty = TextUtils.isEmpty(getTargeturl());
        if (TextUtils.isEmpty(music.getThumb())) {
            parseImage(music.getThumbImage());
        } else {
            this.mExtraData.put(Constant.IMAGE_PATH_URL, music.getThumb());
        }
        if (!TextUtils.isEmpty(music.getTitle())) {
            setTitle(music.getTitle());
        }
        if (!isEmpty) {
            return;
        }
        if (TextUtils.isEmpty(music.getTargetUrl())) {
            setTargeturl(music.toUrl());
        } else {
            setTargeturl(music.getTargetUrl());
        }
    }

    protected void parseVideo() {
        UMVideo video = getVideo();
        this.mExtraData.put(Constant.AUDIO_URL, video.toUrl());
        boolean isEmpty = TextUtils.isEmpty(getTargeturl());
        if (TextUtils.isEmpty(video.getThumb())) {
            parseImage(video.getThumbImage());
        } else {
            this.mExtraData.put(Constant.IMAGE_PATH_URL, video.getThumb());
        }
        if (!TextUtils.isEmpty(video.getTitle())) {
            setTitle(video.getTitle());
        }
        if (!isEmpty) {
            return;
        }
        if (TextUtils.isEmpty(video.getTargetUrl())) {
            setTargeturl(video.toUrl());
        } else {
            setTargeturl(video.getTargetUrl());
        }
    }
}
