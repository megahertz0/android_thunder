package com.umeng.socialize.media;

import android.os.Bundle;
import android.text.TextUtils;
import com.umeng.socialize.ShareContent;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;
import java.util.ArrayList;
import org.apache.commons.logging.impl.SimpleLog;

public class QZoneShareContent extends QQShareContent {
    private UMediaObject uMedia;

    public QZoneShareContent(ShareContent shareContent) {
        super(shareContent);
        this.uMedia = shareContent.mMedia;
    }

    public UMediaObject getMedia() {
        return this.uMedia;
    }

    public Bundle buildParamsQzone() {
        Bundle bundle = new Bundle();
        String text = getText();
        int i = 1;
        UMediaObject uMediaObject = this.uMedia;
        if ((uMediaObject instanceof UMImage) && TextUtils.isEmpty(getText())) {
            i = SimpleLog.LOG_LEVEL_ERROR;
            setShareToImage(bundle);
        } else if ((uMediaObject instanceof UMVideo) || (uMediaObject instanceof UMusic)) {
            i = SimpleLog.LOG_LEVEL_DEBUG;
            setShareToAudio(bundle);
        } else {
            setShareToTextAndImage(bundle);
        }
        bundle.putString("summary", text);
        ArrayList arrayList = new ArrayList();
        CharSequence string = bundle.getString("imageUrl");
        bundle.remove("imageUrl");
        if (!TextUtils.isEmpty(string)) {
            arrayList.add(string);
        }
        bundle.putStringArrayList("imageUrl", arrayList);
        bundle.putInt("req_type", i);
        if (TextUtils.isEmpty(bundle.getString(SetKey.TITLE))) {
            bundle.putString(SetKey.TITLE, "\u5206\u4eab\u5230QQ\u7a7a\u95f4");
        }
        if (TextUtils.isEmpty(bundle.getString("targetUrl"))) {
            bundle.putString("targetUrl", "http://wsq.umeng.com/");
        }
        this.mExtraData.clear();
        return bundle;
    }

    private void setShareToTextAndImage(Bundle bundle) {
        setShareToImage(bundle);
    }

    private void setShareToAudio(Bundle bundle) {
        if (getMusic() != null || getVideo() != null) {
            UMediaObject music;
            if (getMusic() != null) {
                parseMusic();
            } else if (getVideo() != null) {
                parseVideo();
            }
            String str = (String) this.mExtraData.get(Constant.IMAGE_PATH_LOCAL);
            if (TextUtils.isEmpty(str)) {
                str = (String) this.mExtraData.get(Constant.IMAGE_PATH_URL);
            }
            if (getMusic() != null) {
                music = getMusic();
            } else {
                music = getVideo();
            }
            bundle.putString("imageUrl", str);
            bundle.putString("targetUrl", getTargeturl());
            bundle.putString(Constant.AUDIO_URL, music.toUrl());
            bundle.putString(SetKey.TITLE, getTitle());
        }
    }

    private void setShareToImage(Bundle bundle) {
        parseImage(getImage());
        String str = (String) this.mExtraData.get(Constant.IMAGE_PATH_LOCAL);
        if (TextUtils.isEmpty(str)) {
            str = (String) this.mExtraData.get(Constant.IMAGE_PATH_URL);
        }
        bundle.putString("imageUrl", str);
        if (TextUtils.isEmpty(getTargeturl())) {
            setTargeturl((String) this.mExtraData.get(Constant.IMAGE_TARGETURL));
        }
        if (TextUtils.isEmpty(getTargeturl())) {
            setTargeturl("http://wsq.umeng.com/");
        }
        bundle.putString("targetUrl", getTargeturl());
        bundle.putString(SetKey.TITLE, getTitle());
    }
}
