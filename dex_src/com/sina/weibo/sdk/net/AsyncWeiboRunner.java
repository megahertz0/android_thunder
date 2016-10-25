package com.sina.weibo.sdk.net;

import android.content.Context;
import android.os.AsyncTask;
import com.sina.weibo.sdk.cmd.WbAppActivator;
import com.sina.weibo.sdk.exception.WeiboException;

public class AsyncWeiboRunner {
    private Context mContext;

    class AnonymousClass_1 extends Thread {
        private final /* synthetic */ String val$httpMethod;
        private final /* synthetic */ RequestListener val$listener;
        private final /* synthetic */ WeiboParameters val$params;
        private final /* synthetic */ String val$url;

        AnonymousClass_1(String str, String str2, WeiboParameters weiboParameters, RequestListener requestListener) {
            this.val$url = str;
            this.val$httpMethod = str2;
            this.val$params = weiboParameters;
            this.val$listener = requestListener;
        }

        public void run() {
            try {
                String openUrl = HttpManager.openUrl(AsyncWeiboRunner.this.mContext, this.val$url, this.val$httpMethod, this.val$params);
                if (this.val$listener != null) {
                    this.val$listener.onComplete(openUrl);
                }
            } catch (WeiboException e) {
                if (this.val$listener != null) {
                    this.val$listener.onWeiboException(e);
                }
            }
        }
    }

    private static class AsyncTaskResult<T> {
        private WeiboException error;
        private T result;

        public T getResult() {
            return this.result;
        }

        public WeiboException getError() {
            return this.error;
        }

        public AsyncTaskResult(T t) {
            this.result = t;
        }

        public AsyncTaskResult(WeiboException weiboException) {
            this.error = weiboException;
        }
    }

    static class RequestRunner extends AsyncTask<Void, Void, AsyncTaskResult<String>> {
        private final Context mContext;
        private final String mHttpMethod;
        private final RequestListener mListener;
        private final WeiboParameters mParams;
        private final String mUrl;

        public RequestRunner(Context context, String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
            this.mContext = context;
            this.mUrl = str;
            this.mParams = weiboParameters;
            this.mHttpMethod = str2;
            this.mListener = requestListener;
        }

        protected AsyncTaskResult<String> doInBackground(Void... voidArr) {
            try {
                return new AsyncTaskResult(HttpManager.openUrl(this.mContext, this.mUrl, this.mHttpMethod, this.mParams));
            } catch (WeiboException e) {
                return new AsyncTaskResult(e);
            }
        }

        protected void onPreExecute() {
        }

        protected void onPostExecute(AsyncTaskResult<String> asyncTaskResult) {
            WeiboException error = asyncTaskResult.getError();
            if (error != null) {
                this.mListener.onWeiboException(error);
            } else {
                this.mListener.onComplete((String) asyncTaskResult.getResult());
            }
        }
    }

    public AsyncWeiboRunner(Context context) {
        this.mContext = context;
    }

    @Deprecated
    public void requestByThread(String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        new AnonymousClass_1(str, str2, weiboParameters, requestListener).start();
    }

    public String request(String str, WeiboParameters weiboParameters, String str2) throws WeiboException {
        WbAppActivator.getInstance(this.mContext, weiboParameters.getAppKey()).activateApp();
        return HttpManager.openUrl(this.mContext, str, str2, weiboParameters);
    }

    public void requestAsync(String str, WeiboParameters weiboParameters, String str2, RequestListener requestListener) {
        WbAppActivator.getInstance(this.mContext, weiboParameters.getAppKey()).activateApp();
        new RequestRunner(this.mContext, str, weiboParameters, str2, requestListener).execute(new Void[1]);
    }
}
