package com.xunlei.downloadprovider.ad.recommend;

import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.view.View;
import com.xunlei.downloadprovider.ad.recommend.a.b;
import com.xunlei.downloadprovider.ad.recommend.a.b.a;
import java.util.List;

// compiled from: IRecommendAdContract.java
public interface a {

    // compiled from: IRecommendAdContract.java
    public static interface a extends com.xunlei.downloadprovider.ad.recommend.b.a<b> {
        void a();

        void a(com.xunlei.downloadprovider.ad.common.a aVar, View view, int i);

        void b();
    }

    // compiled from: IRecommendAdContract.java
    public static interface b extends com.xunlei.downloadprovider.ad.recommend.view.a {

        // compiled from: IBaseModel.java
        public static interface b<T> {
            void a(a aVar);

            void a(List<T> list);
        }

        void a();

        void a(int i);

        void a(OnClickListener onClickListener, OnClickListener onClickListener2);

        void a(String str);

        void a(boolean z);

        boolean a(List<com.xunlei.downloadprovider.ad.common.a> list);

        int b();

        Context c();

        void d();

        void e();
    }
}
