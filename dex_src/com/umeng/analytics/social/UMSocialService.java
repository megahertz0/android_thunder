package com.umeng.analytics.social;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import com.umeng.analytics.social.UMSocialService.b;
import com.umeng.socialize.common.SocializeConstants;
import org.json.JSONObject;

public abstract class UMSocialService {

    private static class a extends AsyncTask<Void, Void, d> {
        String a;
        String b;
        b c;
        UMPlatformData[] d;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((d) obj);
        }

        public a(String[] strArr, b bVar, UMPlatformData[] uMPlatformDataArr) {
            this.a = strArr[0];
            this.b = strArr[1];
            this.c = bVar;
            this.d = uMPlatformDataArr;
        }

        protected void onPreExecute() {
            if (this.c != null) {
                this.c.a();
            }
        }

        protected d a(Void... voidArr) {
            String a;
            if (TextUtils.isEmpty(this.b)) {
                a = c.a(this.a);
            } else {
                a = c.a(this.a, this.b);
            }
            try {
                int i;
                JSONObject jSONObject = new JSONObject(a);
                int optInt = jSONObject.optInt("st");
                if (optInt == 0) {
                    i = -404;
                } else {
                    i = optInt;
                }
                d dVar = new d(i);
                String optString = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                if (!TextUtils.isEmpty(optString)) {
                    dVar.a(optString);
                }
                Object optString2 = jSONObject.optString(SocializeConstants.JSON_DATA);
                if (TextUtils.isEmpty(optString2)) {
                    return dVar;
                }
                dVar.b(optString2);
                return dVar;
            } catch (Exception e) {
                return new d(-99, e);
            }
        }

        protected void a(d dVar) {
            if (this.c != null) {
                this.c.a(dVar, this.d);
            }
        }
    }

    public static interface b {
        void a();

        void a(d dVar, UMPlatformData... uMPlatformDataArr);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(android.content.Context r3, com.umeng.analytics.social.UMSocialService.b r4, java.lang.String r5, com.umeng.analytics.social.UMPlatformData... r6) {
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.social.UMSocialService.a(android.content.Context, com.umeng.analytics.social.UMSocialService$b, java.lang.String, com.umeng.analytics.social.UMPlatformData[]):void");
        /*
        r0 = 0;
        if (r6 == 0) goto L_0x001c;
    L_0x0003:
        r1 = r6.length;	 Catch:{ a -> 0x0017, Exception -> 0x002c }
    L_0x0004:
        if (r0 >= r1) goto L_0x001c;
    L_0x0006:
        r2 = r6[r0];	 Catch:{ a -> 0x0017, Exception -> 0x002c }
        r2 = r2.isValid();	 Catch:{ a -> 0x0017, Exception -> 0x002c }
        if (r2 != 0) goto L_0x0019;
    L_0x000e:
        r0 = new com.umeng.analytics.social.a;	 Catch:{ a -> 0x0017, Exception -> 0x002c }
        r1 = "parameter is not valid.";
        r0.<init>(r1);	 Catch:{ a -> 0x0017, Exception -> 0x002c }
        throw r0;	 Catch:{ a -> 0x0017, Exception -> 0x002c }
    L_0x0017:
        r0 = move-exception;
    L_0x0018:
        return;
    L_0x0019:
        r0 = r0 + 1;
        goto L_0x0004;
    L_0x001c:
        r0 = com.umeng.analytics.social.f.a(r3, r5, r6);	 Catch:{ a -> 0x0017, Exception -> 0x002c }
        r1 = new com.umeng.analytics.social.UMSocialService$a;	 Catch:{ a -> 0x0017, Exception -> 0x002c }
        r1.<init>(r0, r4, r6);	 Catch:{ a -> 0x0017, Exception -> 0x002c }
        r0 = 0;
        r0 = new java.lang.Void[r0];	 Catch:{ a -> 0x0017, Exception -> 0x002c }
        r1.execute(r0);	 Catch:{ a -> 0x0017, Exception -> 0x002c }
        goto L_0x0018;
    L_0x002c:
        r0 = move-exception;
        goto L_0x0018;
        */
    }

    public static void share(Context context, String str, UMPlatformData... uMPlatformDataArr) {
        a(context, null, str, uMPlatformDataArr);
    }

    public static void share(Context context, UMPlatformData... uMPlatformDataArr) {
        a(context, null, null, uMPlatformDataArr);
    }
}
