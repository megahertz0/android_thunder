package com.qq.e.comm.managers.plugin;

import android.content.Context;
import anet.channel.security.ISecurity;
import com.qq.e.comm.net.NetworkCallBack;
import com.qq.e.comm.net.NetworkClient.Priority;
import com.qq.e.comm.net.NetworkClientImpl;
import com.qq.e.comm.net.rr.PlainRequest;
import com.qq.e.comm.net.rr.Request;
import com.qq.e.comm.net.rr.Request.Method;
import com.qq.e.comm.net.rr.Response;
import com.qq.e.comm.util.FileUtil;
import com.qq.e.comm.util.GDTLogger;
import com.qq.e.comm.util.Md5Util;
import com.qq.e.comm.util.StringUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.android.agoo.message.MessageService;

final class a {
    private static final Pattern a;
    private final Context b;

    class a implements NetworkCallBack {
        private final String a;
        private final int b;

        public a(String str, int i) {
            this.a = str;
            this.b = i;
        }

        private static String a(Response response, File file) {
            String str = null;
            try {
                MessageDigest instance = MessageDigest.getInstance(ISecurity.SIGN_ALGORITHM_MD5);
                InputStream streamContent = response.getStreamContent();
                try {
                    fileOutputStream = new FileOutputStream(file);
                } catch (IOException e) {
                    e = e;
                    fileOutputStream = null;
                    GDTLogger.e("IOException While Update Plugin", e);
                    FileUtil.tryClose(streamContent);
                    FileUtil.tryClose(fileOutputStream);
                    return null;
                } catch (NoSuchAlgorithmException e2) {
                    e = e2;
                    fileOutputStream = null;
                    GDTLogger.e("MD5SUMException While Update Plugin", e);
                    FileUtil.tryClose(streamContent);
                    FileUtil.tryClose(fileOutputStream);
                    return str;
                } catch (Throwable e3) {
                    fileOutputStream = null;
                    r0 = e3;
                    FileUtil.tryClose(streamContent);
                    FileUtil.tryClose(fileOutputStream);
                    throw r0;
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = streamContent.read(bArr);
                        if (read > 0) {
                            instance.update(bArr, 0, read);
                            fileOutputStream.write(bArr, 0, read);
                        } else {
                            FileUtil.tryClose(streamContent);
                            FileUtil.tryClose(fileOutputStream);
                            str = Md5Util.byteArrayToHexString(instance.digest());
                            FileUtil.tryClose(streamContent);
                            FileUtil.tryClose(fileOutputStream);
                            return str;
                        }
                    }
                } catch (IOException e4) {
                    e3 = e4;
                } catch (NoSuchAlgorithmException e5) {
                    e3 = e5;
                    GDTLogger.e("MD5SUMException While Update Plugin", e3);
                    FileUtil.tryClose(streamContent);
                    FileUtil.tryClose(fileOutputStream);
                    return str;
                }
            } catch (Throwable th) {
                r0 = th;
            }
        }

        private boolean a(File file) {
            try {
                StringUtil.writeTo(this.b + "#####" + this.a, file);
                return true;
            } catch (Throwable e) {
                GDTLogger.e("IOException While Update Plugin", e);
                return false;
            }
        }

        public final void onException(Exception exception) {
            GDTLogger.w("Exception While Update Plugin", exception);
        }

        public final void onResponse(Request request, Response response) {
            if (response.getStatusCode() == 200) {
                try {
                    File file = new File(a.this.b.getDir("e_qq_com_plugin", 0), "gdt_plugin.tmp");
                    File file2 = new File(a.this.b.getDir("e_qq_com_plugin", 0), "gdt_plugin.tmp.sig");
                    if (!com.qq.e.comm.util.a.a().b(this.a, a(response, file))) {
                        file.delete();
                        GDTLogger.report(String.format("Fail to update plugin while verifying,sig=%s,md5=%s", new Object[]{this.a, r0}));
                    } else if (a(file2) && FileUtil.renameTo(file, c.b(a.this.b))) {
                        FileUtil.renameTo(file2, c.d(a.this.b));
                    }
                    GDTLogger.d(new StringBuilder("TIMESTAMP_AFTER_DOWNPLUGIN:").append(System.nanoTime()).append(";sig=").append(this.a).toString());
                    return;
                } catch (Throwable th) {
                    GDTLogger.e("UnknownException While Update Plugin", th);
                    GDTLogger.d(new StringBuilder("TIMESTAMP_AFTER_DOWNPLUGIN:").append(System.nanoTime()).append(";sig=").append(this.a).toString());
                }
            }
            GDTLogger.report(new StringBuilder("DownLoad Plugin Jar Status error,response status code=").append(response.getStatusCode()).toString());
        }
    }

    static {
        a = Pattern.compile(".*plugin\\.dex-(\\d+)\\.jar.*");
    }

    public a(Context context) {
        this.b = context.getApplicationContext();
    }

    public final void a(String str, String str2) {
        if (!StringUtil.isEmpty(str) && !StringUtil.isEmpty(str2)) {
            int i;
            String str3 = MessageService.MSG_DB_READY_REPORT;
            Matcher matcher = a.matcher(str2);
            if (matcher.matches()) {
                str3 = matcher.group(1);
            }
            int parseInteger = StringUtil.parseInteger(str3, 0);
            if (parseInteger < 532) {
                GDTLogger.i(new StringBuilder("online plugin version is smaller than asset plugin version").append(parseInteger).append(",532.download give up").toString());
                i = 0;
            } else {
                i = 1;
            }
            if (i != 0) {
                NetworkClientImpl.getInstance().submit(new PlainRequest(str2, Method.GET, null), Priority.High, new a(str, parseInteger));
            }
        }
    }
}
