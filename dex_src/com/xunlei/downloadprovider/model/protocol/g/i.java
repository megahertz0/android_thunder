package com.xunlei.downloadprovider.model.protocol.g;

import android.os.Bundle;
import android.os.Message;
import com.xunlei.downloadprovider.b.c.f;
import com.xunlei.thundersniffer.sniff.sniffer.internal.server.SvrProtocol.SHubBatchQueryKeys;
import org.json.JSONException;
import org.json.JSONObject;

// compiled from: XunleiScanCodeBox.java
public final class i extends f {
    final /* synthetic */ e a;

    public i(e eVar) {
        this.a = eVar;
    }

    public final Object parseJson(JSONObject jSONObject) throws JSONException {
        new StringBuilder("JSON:").append(jSONObject.toString());
        this.mErrCode = jSONObject.getInt("status");
        if (this.mErrCode == 0) {
            String string = jSONObject.getString("fileurl");
            String string2 = jSONObject.getString(SHubBatchQueryKeys.filename);
            String string3 = jSONObject.getString(SHubBatchQueryKeys.filesize);
            String string4 = jSONObject.getString("optype");
            Bundle bundle = new Bundle();
            bundle.putString("fileurl", string);
            bundle.putString(SHubBatchQueryKeys.filename, string2);
            bundle.putString(SHubBatchQueryKeys.filesize, string3);
            bundle.putString("optype", string4);
            if (this.a.mListener != null) {
                Message message = new Message();
                message.setData(bundle);
                message.what = 1;
                this.a.mListener.sendMessage(message);
            }
        } else if (this.a.mListener != null) {
            this.a.mListener.sendEmptyMessage(-1);
        }
        return null;
    }
}
