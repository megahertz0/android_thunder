package com.xunlei.downloadprovider.download.tasklist.list.a.a;

import com.xunlei.downloadprovider.download.tasklist.list.a.b.a;
import com.xunlei.downloadprovider.download.tasklist.list.a.b.b;
import java.util.List;

// compiled from: BaiduShowHandler.java
public final class e extends a implements a {
    private String c;

    public e(m$a com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a) {
        super(com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a);
        this.c = h.a(com_xunlei_downloadprovider_download_tasklist_list_a_a_m_a);
    }

    public final void a() {
        if (this.b != null) {
            b bVar = (b) a.a().b(this.b.b()).get(h.a(this.b));
            if (bVar != null) {
                this.b.a(bVar);
            } else if (this.a != null) {
                this.a.a();
            }
        }
    }

    public final void a(List<b> list) {
        a.a().b(this.b.b()).put(this.c, list.get(0));
        if (this.c.equals(h.a(this.b))) {
            a();
        }
    }

    public final void a(int i) {
        if (this.c.equals(h.a(this.b))) {
            a();
        }
    }
}
