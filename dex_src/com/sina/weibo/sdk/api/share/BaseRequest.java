package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.WeiboAppManager.WeiboInfo;
import com.sina.weibo.sdk.constant.WBConstants;
import com.sina.weibo.sdk.constant.WBConstants.Base;

public abstract class BaseRequest extends Base {
    public String packageName;

    abstract boolean check(Context context, WeiboInfo weiboInfo, VersionCheckHandler versionCheckHandler);

    public void toBundle(Bundle bundle) {
        bundle.putInt(WBConstants.COMMAND_TYPE_KEY, getType());
        bundle.putString(WBConstants.TRAN, this.transaction);
    }

    public void fromBundle(Bundle bundle) {
        this.transaction = bundle.getString(WBConstants.TRAN);
        this.packageName = bundle.getString(Base.APP_PKG);
    }
}
