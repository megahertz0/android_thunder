package com.tencent.connect;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.stat.DeviceInfo;
import com.tencent.tauth.IUiListener;
import org.android.agoo.message.MessageService;

// compiled from: ProGuard
public class UserInfo extends BaseApi {
    public static final String GRAPH_OPEN_ID = "oauth2.0/m_me";

    public UserInfo(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public UserInfo(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public void getUserInfo(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "user/get_simple_userinfo", composeCGIParams(), Constants.HTTP_GET, new TempRequestListener(this, iUiListener));
    }

    public void getVipUserInfo(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "user/get_vip_info", composeCGIParams(), Constants.HTTP_GET, new TempRequestListener(this, iUiListener));
    }

    public void getVipUserRichInfo(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "user/get_vip_rich_info", composeCGIParams(), Constants.HTTP_GET, new TempRequestListener(this, iUiListener));
    }

    public void getTenPayAddr(IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString(DeviceInfo.TAG_VERSION, MessageService.MSG_DB_NOTIFY_REACHED);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "cft_info/get_tenpay_addr", composeCGIParams, Constants.HTTP_GET, new TempRequestListener(this, iUiListener));
    }

    public void getOpenId(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.mToken, Global.getContext(), GRAPH_OPEN_ID, composeCGIParams(), Constants.HTTP_GET, new TempRequestListener(this, iUiListener));
    }
}
