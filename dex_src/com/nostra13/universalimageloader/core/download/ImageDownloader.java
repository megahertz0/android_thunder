package com.nostra13.universalimageloader.core.download;

import anet.channel.util.HttpConstant;
import com.alipay.sdk.cons.b;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.umeng.a;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public interface ImageDownloader {

    public enum Scheme {
        HTTP(HttpConstant.HTTP),
        HTTPS(b.a),
        FILE("file"),
        CONTENT(ParamKey.CONTENT),
        ASSETS("assets"),
        DRAWABLE("drawable"),
        UNKNOWN(a.d);
        private String a;
        private String b;

        static {
            String str = HttpConstant.HTTP;
            HTTP = new com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme("HTTP", 0, HttpConstant.HTTP);
            str = b.a;
            HTTPS = new com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme("HTTPS", 1, b.a);
            str = "file";
            FILE = new com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme("FILE", 2, "file");
            str = ParamKey.CONTENT;
            CONTENT = new com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme("CONTENT", 3, ParamKey.CONTENT);
            str = "assets";
            ASSETS = new com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme("ASSETS", 4, "assets");
            String str2 = "drawable";
            DRAWABLE = new com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme("DRAWABLE", 5, "drawable");
            str2 = a.d;
            UNKNOWN = new com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme("UNKNOWN", 6, a.d);
            c = new com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme[]{HTTP, HTTPS, FILE, CONTENT, ASSETS, DRAWABLE, UNKNOWN};
        }

        private Scheme(String str) {
            this.a = str;
            this.b = str + HttpConstant.SCHEME_SPLIT;
        }

        public static com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme ofUri(String str) {
            if (str != null) {
                com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme[] values = values();
                int length = values.length;
                for (int i = 0; i < length; i++) {
                    com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme scheme = values[i];
                    if (scheme.a(str)) {
                        return scheme;
                    }
                }
            }
            return UNKNOWN;
        }

        private boolean a(String str) {
            return str.toLowerCase(Locale.US).startsWith(this.b);
        }

        public final String wrap(String str) {
            return this.b + str;
        }

        public final String crop(String str) {
            if (a(str)) {
                return str.substring(this.b.length());
            }
            throw new IllegalArgumentException(String.format("URI [%1$s] doesn't have expected scheme [%2$s]", new Object[]{str, this.a}));
        }
    }

    InputStream a(String str, Object obj) throws IOException;
}
