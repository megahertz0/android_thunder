package com.xunlei.thundersniffer.sniff.sniffer;

import android.text.TextUtils;
import java.util.List;

final class l implements SnifferSvrGetOperation$SnifferSvrGetListener {
    final /* synthetic */ SnifferTitleCacheOperation a;

    l(SnifferTitleCacheOperation snifferTitleCacheOperation) {
        this.a = snifferTitleCacheOperation;
    }

    public final void onSnifferSvrGetFinish(String str, int i, List<String> list, List<at> list2, List<at> list3, SnifferSvrGetOperation$a snifferSvrGetOperation$a) {
        this.a.finish();
    }

    public final boolean onSnifferSvrGetResult(String str, at atVar) {
        if (!TextUtils.isEmpty(atVar.q)) {
            this.a.mResults.put(atVar.q, atVar);
        } else if (!TextUtils.isEmpty(atVar.r)) {
            this.a.mResults.put(atVar.r, atVar);
        }
        if (this.a.e != null) {
            this.a.e.onSnifferSvrTitleCacheGetResult(str, atVar);
        }
        return false;
    }
}
