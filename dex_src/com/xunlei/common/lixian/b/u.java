package com.xunlei.common.lixian.b;

import com.xunlei.common.lixian.XLLX_DOWNLOADSTATUS;
import com.xunlei.common.lixian.XLLX_INDEXINFO;
import com.xunlei.common.lixian.XLLX_INITDATA;
import com.xunlei.common.lixian.XLLX_PEERINFO;
import com.xunlei.common.lixian.XLLX_TASKDETAIL;
import com.xunlei.common.lixian.XLLixianFileType;
import com.xunlei.common.lixian.XLLixianListener;
import com.xunlei.common.lixian.XLLixianRequestBase;
import com.xunlei.common.lixian.XLLixianTask;
import com.xunlei.common.lixian.a.a;
import com.xunlei.common.lixian.a.b;
import com.xunlei.common.lixian.a.d;
import com.xunlei.common.lixian.a.e;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public final class u extends XLLixianRequestBase {
    private List a;

    public u() {
        this.a = new LinkedList();
    }

    private static boolean a() {
        return false;
    }

    private static boolean a(byte[] bArr, XLLX_INDEXINFO xllx_indexinfo) {
        b bVar = new b(bArr);
        try {
            xllx_indexinfo.result = bVar.a();
            xllx_indexinfo.taskid = bVar.b();
            xllx_indexinfo.gcid = bVar.e();
            xllx_indexinfo.res_type = bVar.a();
            xllx_indexinfo.cid = bVar.e();
            xllx_indexinfo.filesize = bVar.b();
            xllx_indexinfo.emule_hash = bVar.e();
            xllx_indexinfo.bt_info_hash = bVar.e();
            xllx_indexinfo.fileindex = bVar.a();
            xllx_indexinfo.bcid = bVar.e();
            xllx_indexinfo.parthash = bVar.e();
            xllx_indexinfo.aichhash = bVar.e();
            xllx_indexinfo.query_seed_svr_threshold = bVar.a();
            xllx_indexinfo.pub_filesize_threshold = bVar.a();
            xllx_indexinfo.link_rank = bVar.a();
            xllx_indexinfo.dspider_ctrl_flag = bVar.a();
            xllx_indexinfo.file_suffix = XLLixianRequestBase.readUTF8(bVar);
            xllx_indexinfo.gcid_part_size = bVar.a();
            xllx_indexinfo.gcid_level = bVar.a();
            xllx_indexinfo.control_flag = bVar.a();
            xllx_indexinfo.download_strategy = bVar.a();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean a(byte[] bArr, XLLX_TASKDETAIL xllx_taskdetail) {
        b bVar = new b(bArr);
        try {
            xllx_taskdetail.status = bVar.a();
            xllx_taskdetail.message = XLLixianRequestBase.readUTF8(bVar);
            xllx_taskdetail.taskid = bVar.b();
            xllx_taskdetail.taskname = XLLixianRequestBase.readUTF8(bVar);
            xllx_taskdetail.download_status = XLLX_DOWNLOADSTATUS.get(bVar.a());
            xllx_taskdetail.filesize = bVar.b();
            xllx_taskdetail.filetype = new XLLixianFileType(bVar.a());
            xllx_taskdetail.speed = bVar.a();
            xllx_taskdetail.progress = bVar.a();
            xllx_taskdetail.usedTime = bVar.a();
            xllx_taskdetail.lixian_url = XLLixianRequestBase.readUTF8(bVar);
            xllx_taskdetail.ref_url = XLLixianRequestBase.readUTF8(bVar);
            xllx_taskdetail.cookies = bVar.e();
            xllx_taskdetail.cid = bVar.e();
            xllx_taskdetail.gcid = bVar.e();
            xllx_taskdetail.leftLiveTime = bVar.a();
            int a = bVar.a();
            xllx_taskdetail.extLixianUrllist = new String[a];
            for (int i = 0; i < a; i++) {
                xllx_taskdetail.extLixianUrllist[i] = XLLixianRequestBase.readUTF8(bVar);
            }
            List i2 = bVar.i();
            xllx_taskdetail.peerList = new XLLX_PEERINFO[i2.size()];
            for (a = 0; a < i2.size(); a++) {
                xllx_taskdetail.peerList[a] = new XLLX_PEERINFO();
                ((b) i2.get(a)).j();
            }
            xllx_taskdetail.url = XLLixianRequestBase.readUTF8(bVar);
            xllx_taskdetail.commit_time = bVar.b();
            xllx_taskdetail.classvalue = bVar.b();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void a(long j) {
        this.a.add(Long.valueOf(j));
    }

    public final boolean execute() {
        if (this.a.size() <= 0) {
            return false;
        }
        a aVar = new a((short) 3);
        e eVar = new e();
        try {
            XLLX_INITDATA initData = super.getInitData();
            eVar.a(initData.userJumpKey);
            eVar.a(initData.userId);
            eVar.a(this.a.size());
            for (int i = 0; i < this.a.size(); i++) {
                eVar.a(((Long) this.a.get(i)).longValue());
            }
            eVar.a(initData.peerId);
            eVar.a(0);
            eVar.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        aVar.a(eVar.a());
        d.a().a(aVar.c(), new v(this));
        return true;
    }

    public final boolean fireEvent(XLLixianListener xLLixianListener, Object... objArr) {
        return xLLixianListener.OnUpdateLixianTasksDetail(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (XLLixianTask[]) objArr[3], objArr[4]);
    }
}
