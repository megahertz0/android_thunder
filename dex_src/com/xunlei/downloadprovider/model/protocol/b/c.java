package com.xunlei.downloadprovider.model.protocol.b;

import com.umeng.socialize.media.WeiXinShareContent;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.downloadprovider.model.protocol.b.h.a;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.eclipse.paho.client.mqttv3.MqttTopic;

// compiled from: DownloadJsonData.java
final class c implements a {
    final /* synthetic */ b a;

    c(b bVar) {
        this.a = bVar;
    }

    public final void a(j jVar) {
        a.b;
        a.b;
        new StringBuilder("start save JSONData --> ").append(System.currentTimeMillis() - a.a);
        if (jVar != null) {
            try {
                if (!(jVar.d <= 0 || jVar.b == null || jVar.b.equals(BuildConfig.VERSION_NAME) || jVar.f == null)) {
                    String valueOf = String.valueOf(jVar.d);
                    String str = new String(jVar.f.toString().getBytes(), CharsetConvert.UTF_8);
                    String str2 = jVar.b;
                    File file = null;
                    if (k.a()) {
                        if ("joke".equals(str2)) {
                            file = new File(com.xunlei.downloadprovider.businessutil.a.e() + "joke/");
                        } else if ("pic".equals(str2)) {
                            file = new File(com.xunlei.downloadprovider.businessutil.a.e() + "pic/");
                        } else if (WeiXinShareContent.TYPE_VIDEO.equals(str2)) {
                            file = new File(com.xunlei.downloadprovider.businessutil.a.e() + "video/");
                        }
                        if (file == null || !file.exists()) {
                            file.mkdirs();
                        }
                        File file2 = new File(file.getAbsolutePath() + MqttTopic.TOPIC_LEVEL_SEPARATOR + valueOf);
                        if (!file2.exists()) {
                            file2.createNewFile();
                        }
                        FileOutputStream fileOutputStream = new FileOutputStream(file2);
                        fileOutputStream.write(str.getBytes());
                        fileOutputStream.close();
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        a.b;
        new StringBuilder("finish save JSONData --> ").append(System.currentTimeMillis() - a.a);
        a.b;
    }
}
