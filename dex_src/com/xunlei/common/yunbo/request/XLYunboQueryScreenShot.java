package com.xunlei.common.yunbo.request;

import com.xunlei.common.httpclient.BaseHttpClientListener;
import com.xunlei.common.yunbo.XLYunboListener;
import com.xunlei.common.yunbo.XLYunboRequestBase;
import com.xunlei.common.yunbo.XLYunboRequestHandler;
import com.xunlei.xiazaibao.BuildConfig;
import org.android.spdy.SpdyProtocol;
import org.android.spdy.TnetStatusCode;
import org.apache.http.Header;

public class XLYunboQueryScreenShot extends XLYunboRequestBase {
    private boolean m_bPosters;
    private int m_height;
    private String m_videoGcid;
    private int m_width;

    public XLYunboQueryScreenShot() {
        this.m_videoGcid = null;
        this.m_width = 0;
        this.m_height = 0;
        this.m_bPosters = false;
    }

    public void setVideos(String str, boolean z, int i, int i2) {
        this.m_videoGcid = str;
        this.m_bPosters = z;
        this.m_height = i2;
        this.m_width = i;
    }

    public boolean fireEvent(XLYunboListener xLYunboListener, Object... objArr) {
        if (this.m_bPosters) {
            return xLYunboListener.OnObtainVideoPosters(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (String) objArr[3], (byte[]) objArr[4], objArr[5]);
        }
        return xLYunboListener.OnObtainVideoScreenShot(((Integer) objArr[0]).intValue(), (String) objArr[1], ((Integer) objArr[2]).intValue(), (String) objArr[3], (byte[]) objArr[4], objArr[5]);
    }

    public boolean execute() {
        if (!(this.m_videoGcid == null || this.m_videoGcid.compareToIgnoreCase(BuildConfig.VERSION_NAME) == 0)) {
            try {
                int parseInt = Integer.parseInt(this.m_videoGcid.substring(0, 1), SpdyProtocol.CUSTOM) % 5;
                String str = this.m_bPosters ? "poster" : "pic";
                if (this.m_width <= 0 && this.m_height > 0) {
                    str = String.format("http://i%d.xlpan.kanimg.com/%s/%s_X%d.jpg", new Object[]{Integer.valueOf(parseInt), str, this.m_videoGcid, Integer.valueOf(this.m_height)});
                } else if (this.m_width > 0 && this.m_height <= 0) {
                    str = String.format("http://i%d.xlpan.kanimg.com/%s/%s_%dX.jpg", new Object[]{Integer.valueOf(parseInt), str, this.m_videoGcid, Integer.valueOf(this.m_width)});
                } else if (this.m_width > 0 && this.m_height > 0) {
                    str = String.format("http://i%d.xlpan.kanimg.com/%s/%s_%dX%d.jpg", new Object[]{Integer.valueOf(parseInt), str, this.m_videoGcid, Integer.valueOf(this.m_width), Integer.valueOf(this.m_height)});
                }
                XLYunboRequestHandler.getHandler().get(str, new BaseHttpClientListener() {
                    public void onSuccess(int i, Header[] headerArr, byte[] bArr) {
                        if (i == 200) {
                            XLYunboQueryScreenShot.this.fireListener(new Object[]{Integer.valueOf(0), BuildConfig.VERSION_NAME, Integer.valueOf(XLYunboQueryScreenShot.this.getId()), XLYunboQueryScreenShot.this.m_videoGcid, bArr, XLYunboQueryScreenShot.this.getUserData()});
                            return;
                        }
                        XLYunboQueryScreenShot.this.fireListener(new Object[]{Integer.valueOf(i), BuildConfig.VERSION_NAME, Integer.valueOf(XLYunboQueryScreenShot.this.getId()), XLYunboQueryScreenShot.this.m_videoGcid, null, XLYunboQueryScreenShot.this.getUserData()});
                    }

                    public void onFailure(Throwable th, byte[] bArr) {
                        XLYunboQueryScreenShot.this.fireListener(new Object[]{Integer.valueOf(TnetStatusCode.EASY_REQ_STAGE_SEND_FAIL), th.getMessage(), Integer.valueOf(XLYunboQueryScreenShot.this.getId()), XLYunboQueryScreenShot.this.m_videoGcid, null, XLYunboQueryScreenShot.this.getUserData()});
                    }
                });
            } catch (NumberFormatException e) {
            }
        }
        return false;
    }
}
