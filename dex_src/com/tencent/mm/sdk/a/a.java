package com.tencent.mm.sdk.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.b.b;
import com.tencent.mm.sdk.b.h;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.constants.ConstantsAPI;

public final class a {

    public static class a {
        public String W;
        public String X;
        public String Y;
        public Bundle Z;
        public int flags;

        public a() {
            this.flags = -1;
        }

        public final String toString() {
            return new StringBuilder("targetPkgName:").append(this.W).append(", targetClassName:").append(this.X).append(", content:").append(this.Y).append(", flags:").append(this.flags).append(", bundle:").append(this.Z).toString();
        }
    }

    public static boolean a(Context context, a aVar) {
        boolean z = false;
        if (context == null) {
            b.b("MicroMsg.SDK.MMessageAct", "send fail, invalid argument");
            return false;
        } else if (h.h(aVar.W)) {
            b.b("MicroMsg.SDK.MMessageAct", new StringBuilder("send fail, invalid targetPkgName, targetPkgName = ").append(aVar.W).toString());
            return false;
        } else {
            if (h.h(aVar.X)) {
                aVar.X = aVar.W + ".wxapi.WXEntryActivity";
            }
            b.e("MicroMsg.SDK.MMessageAct", new StringBuilder("send, targetPkgName = ").append(aVar.W).append(", targetClassName = ").append(aVar.X).toString());
            Intent intent = new Intent();
            intent.setClassName(aVar.W, aVar.X);
            if (aVar.Z != null) {
                intent.putExtras(aVar.Z);
            }
            String packageName = context.getPackageName();
            intent.putExtra(ConstantsAPI.SDK_VERSION, Build.SDK_INT);
            intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
            intent.putExtra(ConstantsAPI.CONTENT, aVar.Y);
            intent.putExtra(ConstantsAPI.CHECK_SUM, com.tencent.mm.sdk.a.a.b.a(aVar.Y, Build.SDK_INT, packageName));
            if (aVar.flags == -1) {
                intent.addFlags(268435456).addFlags(134217728);
            } else {
                intent.setFlags(aVar.flags);
            }
            try {
                context.startActivity(intent);
                b.e("MicroMsg.SDK.MMessageAct", new StringBuilder("send mm message, intent=").append(intent).toString());
                z = true;
                return z;
            } catch (Exception e) {
                b.a("MicroMsg.SDK.MMessageAct", "send fail, ex = %s", e.getMessage());
                return z;
            }
        }
    }
}
