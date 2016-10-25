package com.tencent.open.t;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.tencent.connect.auth.QQAuth;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.connect.common.Constants;
import com.tencent.open.utils.Global;
import com.tencent.open.utils.HttpUtils;
import com.tencent.tauth.IUiListener;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.umeng.socialize.editorpage.ShareActivity;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// compiled from: ProGuard
public class Weibo extends BaseApi {
    public Weibo(Context context, QQAuth qQAuth, QQToken qQToken) {
        super(qQAuth, qQToken);
    }

    public Weibo(Context context, QQToken qQToken) {
        super(qQToken);
    }

    public void getWeiboInfo(IUiListener iUiListener) {
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "user/get_info", composeCGIParams(), Constants.HTTP_GET, new TempRequestListener(iUiListener));
    }

    public void sendText(String str, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        String str2 = ParamKey.CONTENT;
        if (str == null) {
            str = a.d;
        }
        composeCGIParams.putString(str2, str);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "t/add_t", composeCGIParams, Constants.HTTP_POST, new TempRequestListener(iUiListener));
    }

    public void sendPicText(String str, String str2, IUiListener iUiListener) {
        InputStream inputStream = null;
        Object tempRequestListener = new TempRequestListener(iUiListener);
        try {
            ByteArrayOutputStream byteArrayOutputStream;
            InputStream fileInputStream = new FileInputStream(str2);
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    Bundle composeCGIParams = composeCGIParams();
                    String str3 = ParamKey.CONTENT;
                    if (str == null) {
                        str = a.d;
                    }
                    composeCGIParams.putString(str3, str);
                    composeCGIParams.putByteArray(ShareActivity.KEY_PIC, bArr);
                    HttpUtils.requestAsync(this.mToken, Global.getContext(), "t/add_pic_t", composeCGIParams, Constants.HTTP_POST, tempRequestListener);
                } catch (IOException e3) {
                    e = e3;
                    inputStream = fileInputStream;
                    try {
                        IOException e4;
                        tempRequestListener.onIOException(e4);
                        if (byteArrayOutputStream != null) {
                            try {
                                byteArrayOutputStream.close();
                            } catch (IOException e42) {
                                e42.printStackTrace();
                            }
                        }
                        if (inputStream == null) {
                            try {
                                inputStream.close();
                            } catch (IOException e422) {
                                e422.printStackTrace();
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
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    throw th2;
                }
            } catch (IOException e5) {
                e422 = e5;
                byteArrayOutputStream = null;
                inputStream = fileInputStream;
                tempRequestListener.onIOException(e422);
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
            e422 = e6;
            byteArrayOutputStream = null;
            tempRequestListener.onIOException(e422);
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

    public void nickTips(String str, int i, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        String str2 = "match";
        if (str == null) {
            str = a.d;
        }
        composeCGIParams.putString(str2, str);
        composeCGIParams.putString("reqnum", String.valueOf(i));
        HttpUtils.requestAsync(this.mToken, Global.getContext(), Constants.GRAPH_NICK_TIPS, composeCGIParams, Constants.HTTP_GET, new TempRequestListener(iUiListener));
    }

    public void atFriends(int i, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString("reqnum", String.valueOf(i));
        HttpUtils.requestAsync(this.mToken, Global.getContext(), Constants.GRAPH_INTIMATE_FRIENDS, composeCGIParams, Constants.HTTP_GET, new TempRequestListener(iUiListener));
    }

    public void deleteText(String str, IUiListener iUiListener) {
        Bundle composeCGIParams = composeCGIParams();
        composeCGIParams.putString(SocializeConstants.WEIBO_ID, str);
        HttpUtils.requestAsync(this.mToken, Global.getContext(), "t/del_t", composeCGIParams, Constants.HTTP_POST, new TempRequestListener(iUiListener));
    }
}
