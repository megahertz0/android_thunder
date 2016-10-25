package com.xunlei.common.lixian;

import com.umeng.a;
import com.xunlei.common.lixian.b.g;
import com.xunlei.common.lixian.b.i;
import com.xunlei.common.lixian.b.k;
import com.xunlei.common.lixian.b.m;
import java.util.LinkedList;
import java.util.List;

public class XLLixianBtTask extends XLLixianTask {
    private static final long serialVersionUID = 6266189819780592817L;
    private List m_btFileIndexList;
    private byte[] m_btSeedFileContent;

    protected XLLixianBtTask(long j, int i) {
        super(j, i);
        this.m_btFileIndexList = new LinkedList();
        this.m_btSeedFileContent = null;
    }

    public byte[] getBtSeedFileContent() {
        return this.m_btSeedFileContent;
    }

    public int getFileCount() {
        return this.m_btFileIndexList.size();
    }

    public XLLX_BTFILE getFileIndex(int i) {
        return (i < 0 || i >= this.m_btFileIndexList.size()) ? null : (XLLX_BTFILE) this.m_btFileIndexList.get(i);
    }

    public String getTorrentInfoHash() {
        String str = this.m_taskdetail.url;
        if (str.isEmpty()) {
            return a.d;
        }
        int indexOf = str.indexOf("//");
        if (indexOf == -1) {
            return a.d;
        }
        indexOf += 2;
        return indexOf >= str.length() ? a.d : str.substring(indexOf);
    }

    public int obtainBtFileList(Object obj, XLLixianListener xLLixianListener) {
        i iVar = new i();
        iVar.putUserData(obj);
        iVar.a(getTaskId());
        iVar.a(getTorrentInfoHash());
        iVar.attachListener(xLLixianListener);
        iVar.attachListener(new d(this));
        return iVar.commitTask();
    }

    public int obtainBtSeedFile(Object obj, XLLixianListener xLLixianListener) {
        k kVar = new k();
        kVar.putUserData(obj);
        kVar.a(getTorrentInfoHash());
        kVar.attachListener(new c(this));
        kVar.attachListener(xLLixianListener);
        return kVar.commitTask();
    }

    public int obtainSubTasks(int i, int i2, int i3, int i4, Object obj, XLLixianListener xLLixianListener) {
        g gVar = new g();
        gVar.a(getTaskId());
        gVar.a(i, i2, i3, i4);
        gVar.putUserData(obj);
        gVar.attachListener(xLLixianListener);
        return gVar.commitTask();
    }

    public int updateTaskDetail(XLLixianListener xLLixianListener) {
        m mVar = new m();
        mVar.a(getTaskId());
        mVar.attachListener(xLLixianListener);
        return mVar.commitTask();
    }
}
