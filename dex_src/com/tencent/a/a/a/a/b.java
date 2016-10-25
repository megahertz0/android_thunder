package com.tencent.a.a.a.a;

import android.content.Context;
import android.os.Environment;
import com.umeng.message.MsgConstant;
import com.xiaomi.mipush.sdk.MiPushClient;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

final class b extends f {
    b(Context context) {
        super(context);
    }

    protected final void a(String str) {
        synchronized (this) {
            a.d(Environment.getExternalStorageDirectory() + "/" + h.f("6X8Y4XdM2Vhvn0I="));
            try {
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(Environment.getExternalStorageDirectory(), h.f("6X8Y4XdM2Vhvn0KfzcEatGnWaNU="))));
                bufferedWriter.write(h.f("4kU71lN96TJUomD1vOU9lgj9Tw==") + MiPushClient.ACCEPT_TIME_SEPARATOR + str);
                bufferedWriter.write("\n");
                bufferedWriter.close();
            } catch (Exception e) {
            }
        }
    }

    protected final boolean a() {
        return h.a(this.a, MsgConstant.PERMISSION_WRITE_EXTERNAL_STORAGE) && Environment.getExternalStorageState().equals("mounted");
    }

    protected final String b() {
        String str;
        synchronized (this) {
            try {
                for (String str2 : a.a(new File(Environment.getExternalStorageDirectory(), h.f("6X8Y4XdM2Vhvn0KfzcEatGnWaNU=")))) {
                    String[] split = str2.split(MiPushClient.ACCEPT_TIME_SEPARATOR);
                    if (split.length == 2 && split[0].equals(h.f("4kU71lN96TJUomD1vOU9lgj9Tw=="))) {
                        new StringBuilder("read mid from InternalStorage:").append(split[1]);
                        str2 = split[1];
                        break;
                    }
                }
                str2 = null;
            } catch (IOException e) {
                str2 = null;
            }
        }
        return str2;
    }
}
