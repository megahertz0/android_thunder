package com.umeng.socialize.net.base;

import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.net.utils.UClient;

public class SocializeClient extends UClient {
    public static final String BASE_URL = "http://log.umsns.com/";

    public SocializeReseponse execute(SocializeRequest socializeRequest) {
        if (SocializeConstants.DEBUG_MODE) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
        }
        socializeRequest.setBaseUrl(BASE_URL);
        return (SocializeReseponse) super.execute(socializeRequest, socializeRequest.mResponseClz);
    }
}
