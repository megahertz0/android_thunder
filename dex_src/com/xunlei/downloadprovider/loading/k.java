package com.xunlei.downloadprovider.loading;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.text.TextUtils;
import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import com.umeng.a;
import com.umeng.socialize.common.SocializeConstants;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.tdlive.R;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.android.agoo.message.MessageService;

// compiled from: LoadingUtil.java
public class k {
    private static final String a;

    static {
        a = k.class.getSimpleName();
    }

    public static int a() {
        SharedPreferences sharedPreferences = BrothersApplication.a().getSharedPreferences(ParamKey.COUNT, 0);
        return sharedPreferences != null ? sharedPreferences.getInt(BrothersApplication.a().getString(R.string.version), 0) : 0;
    }

    public static void a(int i) {
        SharedPreferences sharedPreferences = BrothersApplication.a().getSharedPreferences(ParamKey.COUNT, 0);
        if (sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            edit.putInt(BrothersApplication.a().getString(R.string.version), i);
            edit.commit();
        }
    }

    public static int b() {
        SharedPreferences sharedPreferences = BrothersApplication.a().getSharedPreferences(ParamKey.COUNT, 0);
        if (sharedPreferences == null) {
            return 0;
        }
        String string = BrothersApplication.a().getString(R.string.version);
        int i = sharedPreferences.getInt(string, 0) + 1;
        Editor edit = sharedPreferences.edit();
        edit.putInt(string, i);
        edit.commit();
        return i;
    }

    public static void a(String str) {
        SharedPreferences sharedPreferences = BrothersApplication.a().getSharedPreferences(new StringBuilder("loading_sp_").append(str).toString(), 0);
        if (sharedPreferences != null) {
            sharedPreferences.edit().clear();
            sharedPreferences.edit().commit();
        }
    }

    public static void a(h hVar, String str, String str2) {
        SharedPreferences sharedPreferences = BrothersApplication.a().getSharedPreferences(new StringBuilder("loading_sp_").append(str2).toString(), 0);
        if (hVar != null) {
            if (sharedPreferences != null) {
                sharedPreferences.edit().putString("loading_key_haveimg", hVar.a).putString(str2, str).putString("local_key_start_date", hVar.e).putString("local_key_end_date", hVar.f).putString("local_skip_button_text", hVar.g).putString("local_skip_title", hVar.i).putString("local_skip_url", hVar.j).putString("local_skip", hVar.h).putString(SocializeConstants.WEIBO_ID, hVar.b).putString("display_times", hVar.k).putString("duration", hVar.l).commit();
            }
        } else if (sharedPreferences != null) {
            sharedPreferences.edit().putString("loading_key_haveimg", MessageService.MSG_DB_READY_REPORT).putString(str2, null).putString("local_key_start_date", a.d).putString("local_key_end_date", a.d).putString("local_skip_button_text", a.d).putString("local_skip_title", a.d).putString("local_skip_url", a.d).putString("local_skip", a.d).putString(SocializeConstants.WEIBO_ID, a.d).putString("display_times", a.d).putString("duration", a.d).commit();
        }
    }

    public static h b(String str) {
        SharedPreferences sharedPreferences = BrothersApplication.a().getSharedPreferences(new StringBuilder("loading_sp_").append(str).toString(), 0);
        if (sharedPreferences == null) {
            return null;
        }
        h hVar = new h();
        hVar.a = sharedPreferences.getString("loading_key_haveimg", a.d);
        hVar.e = sharedPreferences.getString("local_key_start_date", a.d);
        hVar.f = sharedPreferences.getString("local_key_end_date", a.d);
        hVar.a();
        hVar.g = sharedPreferences.getString("local_skip_button_text", a.d);
        hVar.h = sharedPreferences.getString("local_skip", a.d);
        hVar.i = sharedPreferences.getString("local_skip_title", a.d);
        hVar.j = sharedPreferences.getString("local_skip_url", a.d);
        hVar.b = sharedPreferences.getString(SocializeConstants.WEIBO_ID, a.d);
        hVar.k = sharedPreferences.getString("display_times", a.d);
        hVar.l = sharedPreferences.getString("duration", a.d);
        return hVar;
    }

    public static Bitmap c(String str) {
        Object string;
        FileInputStream fileInputStream;
        Bitmap bitmap = null;
        SharedPreferences sharedPreferences = BrothersApplication.a().getSharedPreferences(new StringBuilder("loading_sp_").append(str).toString(), 0);
        if (sharedPreferences != null) {
            string = sharedPreferences.getString(str, null);
        } else {
            string = null;
        }
        if (!TextUtils.isEmpty(string)) {
            Options options = new Options();
            options.inDither = false;
            options.inPurgeable = true;
            options.inTempStorage = new byte[12288];
            try {
                File file = new File(string);
                if (file.exists()) {
                    fileInputStream = new FileInputStream(file);
                    if (fileInputStream != null) {
                        try {
                            bitmap = BitmapFactory.decodeFileDescriptor(fileInputStream.getFD(), null, options);
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        } catch (IOException e2) {
                            e2.printStackTrace();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            }
                        } catch (OutOfMemoryError e3) {
                            e3.printStackTrace();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e222) {
                                    e222.printStackTrace();
                                }
                            }
                        }
                    }
                }
            } catch (FileNotFoundException e4) {
                e4.printStackTrace();
                fileInputStream = null;
            }
        }
        return bitmap;
    }
}
