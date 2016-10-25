package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLX_TASKRESPSTATUS;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.common.lixian.a.d;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class e extends XLLixianRequestBase {
    private List a;
    private int b;

    public e() {
        this.a = new LinkedList();
        this.b = 2;
    }

    static /* synthetic */ void a(e eVar, byte[] bArr, XLLX_TASKRESPSTATUS xllx_taskrespstatus) {
        b bVar = new b(bArr);
        try {
            xllx_taskrespstatus.status = bVar.a();
            xllx_taskrespstatus.msg = XLLixianRequestBase.readUTF8(bVar);
            xllx_taskrespstatus.taskid = bVar.b();
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

    private static void a(byte[] bArr, XLLX_TASKRESPSTATUS xllx_taskrespstatus) {
        b bVar = new b(bArr);
        try {
            xllx_taskrespstatus.status = bVar.a();
            xllx_taskrespstatus.msg = XLLixianRequestBase.readUTF8(bVar);
            xllx_taskrespstatus.taskid = bVar.b();
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

    public final void a(int i) {
        this.b = i;
    }

    public final void a(long j) {
        this.a.add(Long.valueOf(j));
    }

    public final boolean execute() {
        a aVar = new a((short) 10);
        com.xunlei.common.lixian.a.e eVar = new com.xunlei.common.lixian.a.e();
        XLLX_INITDATA initData = super.getInitData();
        try {
            eVar.a(initData.userJumpKey);
            eVar.a(initData.userId);
            eVar.c(initData.userVipLevel);
            eVar.c(this.b);
            eVar.a(this.a.size());
            for (Long l : this.a) {
                eVar.a(l.longValue());
            }
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
        d.a().a(aVar.c(), new f(this));
        return true;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        if (this.b == 2) {
            return xLLixianListener.OnDeleteTasksToTrash(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLLX_TASKRESPSTATUS[]) objArr[3], objArr[4]);
        }
        return xLLixianListener.OnDeleteTasksFromTrash(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLLX_TASKRESPSTATUS[]) objArr[3], objArr[4]);
    }
}
