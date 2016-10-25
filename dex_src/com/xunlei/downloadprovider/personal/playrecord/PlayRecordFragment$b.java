package com.xunlei.downloadprovider.personal.playrecord;

import android.content.Intent;
import com.xunlei.downloadprovider.model.g;
import com.xunlei.downloadprovider.url.DownData;
import com.xunlei.downloadprovider.vod.protocol.i;
import com.xunlei.downloadprovider.web.BrowserUtil.StartFromType;
import java.util.List;

public interface PlayRecordFragment$b {
    void a();

    void a(Intent intent);

    void a(DownData downData, g gVar);

    void a(i iVar);

    void a(CharSequence charSequence);

    void a(List<DownData> list, StartFromType startFromType);

    void a(boolean z);

    void b();

    void c();
}
