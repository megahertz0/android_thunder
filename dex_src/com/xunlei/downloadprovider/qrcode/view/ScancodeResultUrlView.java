package com.xunlei.downloadprovider.qrcode.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.sina.weibo.sdk.component.GameManager;
import com.umeng.a;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.android.spdy.SpdyAgent;

public final class ScancodeResultUrlView {
    public View a;
    public TextView b;
    public TextView c;
    public TextView d;
    public ImageView e;

    public enum TXTVIEW_TYPE {
        File_Size,
        File_Name,
        File_Url;

        static {
            File_Size = new com.xunlei.downloadprovider.qrcode.view.ScancodeResultUrlView.TXTVIEW_TYPE("File_Size", 0);
            File_Name = new com.xunlei.downloadprovider.qrcode.view.ScancodeResultUrlView.TXTVIEW_TYPE("File_Name", 1);
            File_Url = new com.xunlei.downloadprovider.qrcode.view.ScancodeResultUrlView.TXTVIEW_TYPE("File_Url", 2);
            a = new com.xunlei.downloadprovider.qrcode.view.ScancodeResultUrlView.TXTVIEW_TYPE[]{File_Size, File_Name, File_Url};
        }
    }

    public ScancodeResultUrlView(View view) {
        this.a = view;
    }

    public final void a(int i) {
        this.a.setVisibility(i);
    }

    public final void b(int i) {
        this.b.setVisibility(XZBDevice.Wait);
        this.c.setVisibility(XZBDevice.Wait);
        this.d.setVisibility(XZBDevice.Wait);
        switch (i) {
            case SpdyAgent.ACCS_TEST_SERVER:
                this.b.setVisibility(0);
                this.c.setVisibility(0);
                this.d.setVisibility(0);
            case SpdyAgent.ACCS_ONLINE_SERVER:
                this.c.setVisibility(0);
            default:
                break;
        }
    }

    public final void a(TXTVIEW_TYPE txtview_type, String str) {
        if (str != null) {
            try {
                String decode = URLDecoder.decode(str, GameManager.DEFAULT_CHARSET);
                if (decode.startsWith("fileName=") && decode.contains(";h")) {
                    str = str.substring(0, str.indexOf(";h") + 1);
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (TXTVIEW_TYPE.File_Name == txtview_type) {
            if (str == null || str.equals(a.d)) {
                this.c.setVisibility(XZBDevice.Wait);
                return;
            }
            this.c.setVisibility(0);
            this.c.setText(str);
        } else if (TXTVIEW_TYPE.File_Size == txtview_type) {
            this.b.setText(str);
        } else if (TXTVIEW_TYPE.File_Url == txtview_type) {
            this.d.setText(str);
        }
    }

    public final void c(int i) {
        this.e.setBackgroundResource(i);
    }
}
