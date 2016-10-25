package com.tencent.open.qzone;

import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;
import com.umeng.a;
import com.xunlei.tdlive.WebBrowserActivity;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.android.agoo.message.MessageService;

// compiled from: ProGuard
public class Albums extends BaseApi {

    // compiled from: ProGuard
    public enum AlbumSecurity {
        publicToAll(MessageService.MSG_DB_NOTIFY_REACHED),
        privateOnly(MessageService.MSG_DB_NOTIFY_CLICK),
        friendsOnly(MessageService.MSG_ACCS_READY_REPORT),
        needQuestion(Constants.VIA_SHARE_TYPE_TEXT);
        private final String a;

        static {
            String str = MessageService.MSG_DB_NOTIFY_REACHED;
            publicToAll = new com.tencent.open.qzone.Albums.AlbumSecurity("publicToAll", 0, MessageService.MSG_DB_NOTIFY_REACHED);
            str = MessageService.MSG_DB_NOTIFY_CLICK;
            privateOnly = new com.tencent.open.qzone.Albums.AlbumSecurity("privateOnly", 1, MessageService.MSG_DB_NOTIFY_CLICK);
            str = MessageService.MSG_ACCS_READY_REPORT;
            friendsOnly = new com.tencent.open.qzone.Albums.AlbumSecurity("friendsOnly", 2, MessageService.MSG_ACCS_READY_REPORT);
            str = Constants.VIA_SHARE_TYPE_TEXT;
            needQuestion = new com.tencent.open.qzone.Albums.AlbumSecurity("needQuestion", 3, Constants.VIA_SHARE_TYPE_TEXT);
            b = new com.tencent.open.qzone.Albums.AlbumSecurity[]{publicToAll, privateOnly, friendsOnly, needQuestion};
        }

        private AlbumSecurity(String str) {
            this.a = str;
        }

        public final String getSecurity() {
            return this.a;
        }
    }

    public Albums(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public Albums(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public void listAlbum(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "photo/list_album", composeCGIParams(), Constants.HTTP_GET, new TempRequestListener(iUiListener));
    }

    public void listPhotos(String str, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        String str2 = "albumid";
        if (str == null) {
            str = a.d;
        }
        composeCGIParams.putString(str2, str);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "photo/list_photo", composeCGIParams, Constants.HTTP_GET, new TempRequestListener(iUiListener));
    }

    public void uploadPicture(String str, String str2, String str3, String str4, String str5, IUiListener iUiListener) {
        ByteArrayOutputStream byteArrayOutputStream;
        IOException e;
        InputStream inputStream = null;
        Object tempRequestListener = new TempRequestListener(iUiListener);
        try {
            InputStream fileInputStream = new FileInputStream(str);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException e22) {
                        e22.printStackTrace();
                    }
                    Bundle composeCGIParams = composeCGIParams();
                    File file = new File(str);
                    composeCGIParams.putByteArray(SocialConstants.PARAM_AVATAR_URI, bArr);
                    String str6 = "photodesc";
                    if (str2 == null) {
                        str2 = a.d;
                    }
                    composeCGIParams.putString(str6, str2);
                    composeCGIParams.putString(WebBrowserActivity.EXTRA_TITLE, file.getName());
                    if (str3 != null) {
                        str6 = "albumid";
                        if (str3 == null) {
                            str3 = a.d;
                        }
                        composeCGIParams.putString(str6, str3);
                    }
                    str6 = "x";
                    if (str4 == null) {
                        str4 = a.d;
                    }
                    composeCGIParams.putString(str6, str4);
                    str6 = "y";
                    if (str5 == null) {
                        str5 = a.d;
                    }
                    composeCGIParams.putString(str6, str5);
                    HttpUtils.requestAsync(this.mToken, Global.getContext(), "photo/upload_pic", composeCGIParams, Constants.HTTP_POST, tempRequestListener);
                } catch (IOException e3) {
                    e = e3;
                    inputStream = fileInputStream;
                    try {
                        tempRequestListener.onIOException(e);
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        if (inputStream == null) {
                            try {
                                inputStream.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        fileInputStream = inputStream;
                        if (byteArrayOutputStream != null) {
                            byteArrayOutputStream.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th2;
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e2222) {
                            e2222.printStackTrace();
                        }
                    }
                    throw th2;
                }
            } catch (IOException e5) {
                e42 = e5;
                byteArrayOutputStream = null;
                inputStream = fileInputStream;
                tempRequestListener.onIOException(e42);
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (inputStream == null) {
                    inputStream.close();
                }
            } catch (Throwable th4) {
                th2 = th4;
                byteArrayOutputStream = null;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th2;
            }
        } catch (IOException e6) {
            e42 = e6;
            byteArrayOutputStream = null;
            tempRequestListener.onIOException(e42);
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (inputStream == null) {
                inputStream.close();
            }
        } catch (Throwable th5) {
            th2 = th5;
            byteArrayOutputStream = null;
            fileInputStream = null;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th2;
        }
    }

    public void addAlbum(String str, String str2, AlbumSecurity albumSecurity, String str3, String str4, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        String str5 = "albumname";
        if (str == null) {
            str = a.d;
        }
        composeCGIParams.putString(str5, str);
        str5 = "albumdesc";
        if (str2 == null) {
            str2 = a.d;
        }
        composeCGIParams.putString(str5, str2);
        composeCGIParams.putString("priv", albumSecurity == null ? AlbumSecurity.publicToAll.getSecurity() : albumSecurity.getSecurity());
        str5 = "question";
        if (str3 == null) {
            str3 = a.d;
        }
        composeCGIParams.putString(str5, str3);
        str5 = "answer";
        if (str4 == null) {
            str4 = a.d;
        }
        composeCGIParams.putString(str5, str4);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "photo/add_album", composeCGIParams, Constants.HTTP_POST, new TempRequestListener(iUiListener));
    }
}
