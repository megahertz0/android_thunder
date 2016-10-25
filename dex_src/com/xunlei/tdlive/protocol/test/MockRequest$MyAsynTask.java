package com.xunlei.tdlive.protocol.test;

import android.os.AsyncTask;
import com.xunlei.tdlive.modal.JsonWrapper;
import com.xunlei.xiazaibao.BuildConfig;

private final class MockRequest$MyAsynTask extends AsyncTask<String, Integer, Integer> {
    final /* synthetic */ MockRequest this$0;

    private MockRequest$MyAsynTask(MockRequest mockRequest) {
        this.this$0 = mockRequest;
    }

    protected final Integer doInBackground(String... strArr) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected final void onPostExecute(Integer num) {
        if (MockRequest.access$100(this.this$0) != null) {
            MockRequest.access$100(this.this$0).onResponse(0, BuildConfig.VERSION_NAME, (JsonWrapper) MockRequest.access$200(this.this$0));
        } else if (MockRequest.access$300(this.this$0) != null) {
            MockRequest.access$300(this.this$0).onResponse(0, BuildConfig.VERSION_NAME, MockRequest.access$200(this.this$0));
        }
    }
}
