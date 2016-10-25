package com.baidu.mobads.j;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.baidu.mobads.openad.c.d;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader;
import com.baidu.mobads.openad.interfaces.download.IOAdDownloader.DownloadStatus;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;

public class g {
    protected final long a;
    private Context b;

    class a implements Observer {
        private final Handler b;
        private final String c;

        public a(Handler handler, String str) {
            this.b = handler;
            this.c = str;
        }

        public void update(Observable observable, Object obj) {
            IOAdDownloader iOAdDownloader = (IOAdDownloader) observable;
            if (iOAdDownloader.getState() == DownloadStatus.COMPLETED) {
                g.this.a(this.b, iOAdDownloader.getOutputPath());
                g.this.a(this.c);
            }
            if (iOAdDownloader.getState() == DownloadStatus.ERROR) {
                g.this.a(this.b);
                g.this.a(this.c);
            }
        }
    }

    public g(Context context) {
        this.a = 604800000;
        this.b = context;
    }

    public void a(String str) {
        new Thread(new h(this, str)).start();
    }

    public void a(String str, String str2, String str3, Handler handler) {
        File file = new File(str2 + str3);
        if (file.exists()) {
            a(handler, file.getAbsolutePath());
            return;
        }
        try {
            IOAdDownloader createSimpleFileDownloader = d.a(this.b).createSimpleFileDownloader(new URL(str), str2, str3, false);
            createSimpleFileDownloader.addObserver(new a(handler, str2));
            createSimpleFileDownloader.start();
        } catch (MalformedURLException e) {
            a(handler);
        }
    }

    private void a(Handler handler, String str) {
        a(handler, Boolean.valueOf(true), str);
    }

    private void a(Handler handler) {
        a(handler, Boolean.valueOf(false), null);
    }

    private void a(Handler handler, Boolean bool, String str) {
        try {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 0;
            Bundle bundle = new Bundle();
            bundle.putBoolean("caching_result", bool.booleanValue());
            bundle.putString("local_creative_url", str);
            obtainMessage.setData(bundle);
            handler.sendMessage(obtainMessage);
        } catch (Throwable e) {
            j.a().d(e);
        }
    }
}
