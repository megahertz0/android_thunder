package anet.channel.util;

import anet.channel.strategy.dispatch.a;
import anet.channel.strategy.n;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// compiled from: Taobao
public class g {
    private static Map<String, Integer> a;

    static {
        Map hashMap = new HashMap();
        a = hashMap;
        hashMap.put("html", Integer.valueOf(XZBDevice.DOWNLOAD_LIST_RECYCLE));
        a.put("htm", Integer.valueOf(XZBDevice.DOWNLOAD_LIST_RECYCLE));
        a.put("css", Integer.valueOf(XZBDevice.DOWNLOAD_LIST_FAILED));
        a.put("js", Integer.valueOf(XZBDevice.DOWNLOAD_LIST_FAILED));
        a.put("json", Integer.valueOf(XZBDevice.DOWNLOAD_LIST_ALL));
        a.put("webp", Integer.valueOf(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED));
        a.put("png", Integer.valueOf(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED));
        a.put("jpg", Integer.valueOf(XZBDevice.DOWNLOAD_LIST_UNCOMPLETED_AND_FAILED));
        a.put("zip", Integer.valueOf(XZBDevice.Pause));
        a.put("bin", Integer.valueOf(XZBDevice.Pause));
    }

    public static int a(URL url) {
        if (url == null) {
            throw new IllegalArgumentException("url is null!");
        }
        String host = url.getHost();
        if (n.c(host)) {
            return 0;
        }
        if (a.a().equalsIgnoreCase(host)) {
            return 1;
        }
        host = d.a(url);
        if (host == null) {
            return XZBDevice.DOWNLOAD_LIST_ALL;
        }
        Integer num = (Integer) a.get(host);
        return num != null ? num.intValue() : R.styleable.Toolbar_contentInsetEnd;
    }
}
