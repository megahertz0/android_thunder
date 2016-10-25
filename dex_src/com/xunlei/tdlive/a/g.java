package com.xunlei.tdlive.a;

import android.graphics.Point;
import android.util.SparseArray;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.tdlive.c;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.tdlive.protocol.XLLiveRequest.JsonCallBack;
import com.xunlei.xiazaibao.BuildConfig;
import java.util.HashSet;

// compiled from: GiftAdapter.java
class g implements JsonCallBack {
    final /* synthetic */ Runnable a;
    final /* synthetic */ c b;

    g(c cVar, Runnable runnable) {
        this.b = cVar;
        this.a = runnable;
    }

    public void onResponse(int i, String str, JsonWrapper jsonWrapper) {
        if (i == 0) {
            synchronized (c.b(this.b)) {
                c.a(this.b, Boolean.valueOf(true));
            }
            HashSet hashSet = new HashSet();
            SparseArray sparseArray = new SparseArray();
            JsonWrapper object = jsonWrapper.getObject(SocializeProtocolConstants.PROTOCOL_KEY_DATA, "[]");
            JsonWrapper array = object.getArray("giftInfo", "[]");
            String string = object.getString("path", BuildConfig.VERSION_NAME);
            JsonWrapper jsonWrapper2 = new JsonWrapper("[]");
            for (int i2 = 0; i2 < array.getLength(); i2++) {
                JsonWrapper array2 = array.getArray(i2, "[]");
                for (int i3 = 0; i3 < array2.getLength(); i3++) {
                    JsonWrapper array3 = jsonWrapper2.getArray(i3 / 4);
                    if (array3 == null) {
                        array3 = new JsonWrapper("[]");
                        jsonWrapper2.put(i3 / 4, array3);
                    }
                    JsonWrapper object2 = array2.getObject(i3, "{}");
                    object2.putString(WeiXinShareContent.TYPE_IMAGE, c.a(string, object2.getInt("giftid", 0)));
                    array3.add(object2);
                    Point point = new Point(i3 / 4, array3.getLength() - 1);
                    sparseArray.put(object2.getInt("giftid", 0), point);
                    if (object2.getInt("level", 0) == 0 && object2.getInt("giftnum", 0) <= 0 && object2.getInt("remaintime", 0) > 0) {
                        hashSet.add(Integer.valueOf(point.x));
                    }
                }
            }
            c.a(this.b, sparseArray);
            c.a(this.b, hashSet);
            this.b.a(jsonWrapper2);
            if (!c.e(this.b).isEmpty()) {
                c.f(this.b).d();
            }
        }
        if (c.c(this.b) != null) {
            c.c(this.b).a(true, false);
        }
        if (this.a != null) {
            this.a.run();
        }
        if (i == 0) {
            new Thread(new h(this)).start();
        }
        this.b.c();
    }
}
