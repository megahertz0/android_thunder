package com.baidu.mobads.j;

import java.io.File;

class h implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ g b;

    h(g gVar, String str) {
        this.b = gVar;
        this.a = str;
    }

    public void run() {
        String[] list = new File(this.a).list();
        if (list != null && list.length > 0) {
            for (String str : list) {
                File file = new File(str);
                long currentTimeMillis = System.currentTimeMillis() - file.lastModified();
                if (file.exists() && currentTimeMillis > 604800000) {
                    file.delete();
                }
            }
        }
    }
}
