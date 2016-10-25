package com.baidu.mobad.chuilei;

import android.view.View;

public interface BaiduChuileiResponse {
    String getImageUrl();

    String getTitle();

    void handleClick(View view);

    void handleClick(View view, int i);

    void recordImpression(View view);
}
