package com.xunlei.common.lixian.b;

import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.encrypt.URLCoder;
import com.xunlei.common.lixian.XLLX_BTFILE;
import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.common.lixian.a.d;
import com.xunlei.common.lixian.a.e;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.IOException;

public final class i extends XLLixianRequestBase {
    private long a;
    private String b;

    public i() {
        this.a = 0;
        this.b = BuildConfig.VERSION_NAME;
    }

    private static void a(b bVar, XLLX_BTFILE xllx_btfile) {
        try {
            xllx_btfile.fileindex = bVar.a();
            xllx_btfile.filename = new String(URLCoder.decode(CharsetConvert.correctGBUrlCodeString(bVar.a(CharsetConvert.UTF_8)), "GB18030").getBytes(CharsetConvert.UTF_8), CharsetConvert.UTF_8);
            xllx_btfile.filesize = bVar.b();
            xllx_btfile.pitchon = bVar.a();
            try {
                bVar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e2) {
            try {
                e2.printStackTrace();
                try {
                    bVar.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    bVar.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    static /* synthetic */ void a(i iVar, b bVar, XLLX_BTFILE xllx_btfile) {
        try {
            xllx_btfile.fileindex = bVar.a();
            xllx_btfile.filename = new String(URLCoder.decode(CharsetConvert.correctGBUrlCodeString(bVar.a(CharsetConvert.UTF_8)), "GB18030").getBytes(CharsetConvert.UTF_8), CharsetConvert.UTF_8);
            xllx_btfile.filesize = bVar.b();
            xllx_btfile.pitchon = bVar.a();
            try {
                bVar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e2) {
            try {
                e2.printStackTrace();
                try {
                    bVar.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    bVar.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
    }

    public final void a(long j) {
        this.a = j;
    }

    public final void a(String str) {
        this.b = str;
    }

    public final boolean execute() {
        if (this.b.isEmpty() || this.a == 0) {
            return false;
        }
        a aVar = new a((short) 22);
        e eVar = new e();
        XLLX_INITDATA initData = super.getInitData();
        try {
            eVar.a(initData.userJumpKey);
            eVar.a(initData.userId);
            eVar.c(initData.userVipLevel);
            eVar.a(this.b);
            eVar.a(BuildConfig.VERSION_NAME);
            eVar.a(this.a);
            eVar.flush();
            aVar.a(eVar.a());
            try {
                eVar.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e2) {
            try {
                e2.printStackTrace();
                try {
                    eVar.close();
                } catch (IOException e22) {
                    e22.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    eVar.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        }
        d.a().a(aVar.c(), new j(this));
        return true;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        return xLLixianListener.OnObtainBtFileList(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLLX_BTFILE[]) objArr[3], (String) objArr[4], objArr[5]);
    }
}
