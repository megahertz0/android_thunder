package com.xunlei.tdlive;

import android.text.TextUtils;
import android.widget.ImageView;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.tdlive.util.a;
import com.xunlei.tdlive.util.q;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import com.xunlei.xiazaibao.BuildConfig;

// compiled from: LiveGiftDialog.java
class k implements JsonCallBack {
    final /* synthetic */ i a;

    k(i iVar) {
        this.a = iVar;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0 && jsonWrapper != null) {
            JsonWrapper object = jsonWrapper.getArray(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "[]").getObject(0, "{}");
            if (object != null) {
                ImageView imageView = (ImageView) this.a.findViewById(R.id.activity);
                imageView.setTag(object);
                Object string = object.getString(WeiXinShareContent.TYPE_IMAGE, BuildConfig.VERSION_NAME);
                Object string2 = object.getString(SHubBatchQueryKeys.url, BuildConfig.VERSION_NAME);
                if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                    a.a(this.a.getContext()).a(imageView, string);
                    q.e("activity").a("activity_gift_page_show").a("target", string2).b(new String[]{"target"});
                }
            }
        }
    }
}
