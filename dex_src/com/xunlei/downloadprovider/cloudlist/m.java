package com.xunlei.downloadprovider.cloudlist;

import com.xunlei.common.yunbo.XLYB_BTSUBFILE;
import com.xunlei.common.yunbo.XLYunboListener;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil;
import com.xunlei.downloadprovider.businessutil.XLFileTypeUtil.EFileCategoryType;
import com.xunlei.downloadprovider.cloudlist.a.a;
import com.xunlei.downloadprovider.cloudlist.a.b;
import java.io.UnsupportedEncodingException;

// compiled from: CloudVodBTSubFileObtaner.java
final class m extends XLYunboListener {
    final /* synthetic */ l a;

    m(l lVar) {
        this.a = lVar;
    }

    public final boolean OnObtainBtSubfileList(int i, String str, int i2, String str2, XLYB_BTSUBFILE[] xlyb_btsubfileArr, int i3, Object obj) {
        if (i == 0) {
            boolean z;
            if (xlyb_btsubfileArr == null || xlyb_btsubfileArr.length <= 0) {
                z = true;
            } else {
                String str3 = null;
                if (this.a.e != null) {
                    str3 = this.a.e.createtime;
                }
                int length = xlyb_btsubfileArr.length;
                for (int i4 = 0; i4 < length; i4++) {
                    XLYB_BTSUBFILE xlyb_btsubfile = xlyb_btsubfileArr[i4];
                    a aVar = new a();
                    String str4 = xlyb_btsubfile.filename;
                    try {
                        str4 = com.xunlei.downloadprovider.util.c.a.d(str4);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e2) {
                        e2.printStackTrace();
                    }
                    aVar.a = str4;
                    aVar.b = xlyb_btsubfile.fileindex;
                    aVar.c = xlyb_btsubfile.filesize;
                    aVar.d = str2;
                    aVar.e = xlyb_btsubfile.cid;
                    aVar.f = xlyb_btsubfile.gcid;
                    aVar.g = str3;
                    if (XLFileTypeUtil.a(str4) == EFileCategoryType.E_VIDEO_CATEGORY) {
                        aVar.h = true;
                    } else {
                        aVar.h = false;
                    }
                    this.a.a.add(aVar);
                }
                z = false;
            }
            if (this.a.b != null) {
                b bVar = this.a.b;
                this.a.a.size();
                bVar.a(i, obj, z);
            }
        } else {
            l.a(this.a, i, obj);
        }
        return true;
    }
}
