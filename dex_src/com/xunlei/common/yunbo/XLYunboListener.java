package com.xunlei.common.yunbo;

public class XLYunboListener {
    private boolean m_bGlobal;

    public XLYunboListener() {
        this.m_bGlobal = false;
    }

    public boolean OnObtainVideoList(int i, String str, int i2, XLYB_VODINFO[] xlyb_vodinfoArr, int i3, Object obj) {
        return true;
    }

    public boolean OnAddVideo2Favorite(int i, String str, int i2, XLYB_ADDVIDEO[] xlyb_addvideoArr, Object obj) {
        return true;
    }

    public boolean OnDeleteVideo(int i, String str, int i2, Object obj) {
        return true;
    }

    public boolean OnObtainBtSubfileList(int i, String str, int i2, String str2, XLYB_BTSUBFILE[] xlyb_btsubfileArr, int i3, Object obj) {
        return true;
    }

    public boolean OnObtainVideoPlayUrl(int i, String str, int i2, XLYB_PLAYINFO xlyb_playinfo, Object obj) {
        return true;
    }

    public boolean OnObtainVideoProcess(int i, String str, int i2, XLYB_VIDEOPROCESS[] xlyb_videoprocessArr, Object obj) {
        return true;
    }

    public boolean OnObtainVideoScreenShot(int i, String str, int i2, String str2, byte[] bArr, Object obj) {
        return true;
    }

    public boolean OnObtainVideoPosters(int i, String str, int i2, String str2, byte[] bArr, Object obj) {
        return true;
    }

    protected final void setGlobal(boolean z) {
        this.m_bGlobal = z;
    }

    protected final boolean isGlobal() {
        return this.m_bGlobal;
    }
}
