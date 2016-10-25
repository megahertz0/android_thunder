package com.xunlei.xiazaibao.sdk;

import com.xunlei.xiazaibao.sdk.base.AsynTask;
import com.xunlei.xiazaibao.sdk.base.SyncHttpTask.HttpResponse;
import com.xunlei.xiazaibao.sdk.synctasks.XZBGetCardTitle;
import com.xunlei.xiazaibao.sdk.synctasks.XZBGetCardTitle.CardTitle;
import com.xunlei.xiazaibao.sdk.synctasks.XZBGetCardTitle.CardTitleItem;
import com.xunlei.xiazaibao.sdk.tools.XZBLog;

class XZBDeviceManager$3 extends AsynTask {
    final /* synthetic */ XZBDeviceManager this$0;
    final /* synthetic */ Object val$userData;

    XZBDeviceManager$3(XZBDeviceManager xZBDeviceManager, Object obj, String str, Object obj2, Object obj3) {
        this.this$0 = xZBDeviceManager;
        this.val$userData = obj3;
        super(obj, str, obj2);
    }

    public void run() {
        HttpResponse httpGet = new XZBGetCardTitle().httpGet();
        if (httpGet.getStatusCode() != 200) {
            fireCallback(new Object[]{Integer.valueOf(httpGet.getStatusCode()), Integer.valueOf(getTaskId()), null, httpGet.getMsg(), this.val$userData});
            return;
        }
        CardTitle parseJson = XZBGetCardTitle.parseJson(httpGet.getStringBody());
        if (!(parseJson == null || parseJson.xzb_titles == null)) {
            for (CardTitleItem cardTitleItem : parseJson.xzb_titles) {
                if (cardTitleItem != null && cardTitleItem.type == 1) {
                    XZBLog.d(XZBDeviceManager.access$000(), new StringBuilder("main_title = ").append(cardTitleItem.main_title).append(" sub_title = ").append(cardTitleItem.sub_title).toString());
                    this.this$0.setMainTitle(cardTitleItem.main_title);
                    this.this$0.setSubTitle(cardTitleItem.sub_title);
                }
            }
        }
        fireCallback(new Object[]{Integer.valueOf(0), Integer.valueOf(getTaskId()), parseJson, httpGet.getMsg(), this.val$userData});
    }
}
