package com.umeng.socialize.media;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.common.QueuedWork;
import com.umeng.socialize.weixin.net.WXAuthUtils;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.impl.SimpleLog;
import org.json.JSONException;
import org.json.JSONObject;

public class WeixinExtra {

    final class AnonymousClass_1 implements Runnable {
        final /* synthetic */ UMAuthListener val$umAuthListener;
        final /* synthetic */ String val$url;

        class AnonymousClass_1 implements Runnable {
            final /* synthetic */ JSONObject val$jsonObject;

            AnonymousClass_1(JSONObject jSONObject) {
                this.val$jsonObject = jSONObject;
            }

            public void run() {
                Map hashMap = new HashMap();
                if (this.val$jsonObject.optInt("errcode", -1) == 0) {
                    hashMap.put("result", this.val$jsonObject.toString());
                    AnonymousClass_1.this.val$umAuthListener.onComplete(SHARE_MEDIA.WEIXIN, SimpleLog.LOG_LEVEL_DEBUG, hashMap);
                    return;
                }
                AnonymousClass_1.this.val$umAuthListener.onError(SHARE_MEDIA.WEIXIN, SimpleLog.LOG_LEVEL_DEBUG, new Throwable(this.val$jsonObject.toString()));
            }
        }

        AnonymousClass_1(String str, UMAuthListener uMAuthListener) {
            this.val$url = str;
            this.val$umAuthListener = uMAuthListener;
        }

        public final void run() {
            try {
                QueuedWork.runInMain(new AnonymousClass_1(new JSONObject(WXAuthUtils.request(this.val$url))));
            } catch (JSONException e) {
            }
        }
    }

    public static void isAccessTokenValid(String str, String str2, UMAuthListener uMAuthListener) {
        new Thread(new AnonymousClass_1(new StringBuilder("https://api.weixin.qq.com/sns/auth?access_token=").append(str).append("&openid=").append(str2).toString(), uMAuthListener)).start();
    }
}
