package com.sina.weibo.sdk.component.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.cmd.WbAppActivator;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.sina.weibo.sdk.component.view.CommentComponentView.Category;
import com.sina.weibo.sdk.utils.ResourceManager;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;

public class CommentComponentView extends FrameLayout {
    private static final String ALREADY_COMMENT_EN = "Comment";
    private static final String ALREADY_COMMENT_ZH_CN = "\u5fae\u535a\u70ed\u8bc4";
    private static final String ALREADY_COMMENT_ZH_TW = "\u5fae\u535a\u71b1\u8a55";
    private static final String COMMENT_H5 = "http://widget.weibo.com/distribution/socail_comments_sdk.php";
    private RequestParam mCommentParam;
    private LinearLayout mContentLy;

    public enum Category {
        MOVIE("1001"),
        TRAVEL("1002");
        private String mVal;

        static {
            String str = "1001";
            MOVIE = new com.sina.weibo.sdk.component.view.CommentComponentView.Category("MOVIE", 0, "1001");
            str = "1002";
            TRAVEL = new com.sina.weibo.sdk.component.view.CommentComponentView.Category("TRAVEL", 1, "1002");
            ENUM$VALUES = new com.sina.weibo.sdk.component.view.CommentComponentView.Category[]{MOVIE, TRAVEL};
        }

        private Category(String str) {
            this.mVal = str;
        }

        public final String getValue() {
            return this.mVal;
        }
    }

    public static class RequestParam {
        private String mAccessToken;
        private String mAppKey;
        private WeiboAuthListener mAuthlistener;
        private Category mCategory;
        private String mContent;
        private String mTopic;

        private RequestParam() {
        }

        public static com.sina.weibo.sdk.component.view.CommentComponentView.RequestParam createRequestParam(String str, String str2, String str3, String str4, Category category, WeiboAuthListener weiboAuthListener) {
            com.sina.weibo.sdk.component.view.CommentComponentView.RequestParam requestParam = new com.sina.weibo.sdk.component.view.CommentComponentView.RequestParam();
            requestParam.mAppKey = str;
            requestParam.mAccessToken = str2;
            requestParam.mTopic = str3;
            requestParam.mContent = str4;
            requestParam.mCategory = category;
            requestParam.mAuthlistener = weiboAuthListener;
            return requestParam;
        }

        public static com.sina.weibo.sdk.component.view.CommentComponentView.RequestParam createRequestParam(String str, String str2, String str3, Category category, WeiboAuthListener weiboAuthListener) {
            com.sina.weibo.sdk.component.view.CommentComponentView.RequestParam requestParam = new com.sina.weibo.sdk.component.view.CommentComponentView.RequestParam();
            requestParam.mAppKey = str;
            requestParam.mTopic = str2;
            requestParam.mContent = str3;
            requestParam.mCategory = category;
            requestParam.mAuthlistener = weiboAuthListener;
            return requestParam;
        }
    }

    public CommentComponentView(Context context) {
        super(context);
        init(context);
    }

    public CommentComponentView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public CommentComponentView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    private void init(Context context) {
        this.mContentLy = new LinearLayout(context);
        this.mContentLy.setOrientation(0);
        this.mContentLy.setLayoutParams(new LayoutParams(-2, -2));
        View imageView = new ImageView(context);
        imageView.setImageDrawable(ResourceManager.getDrawable(context, "sdk_weibo_logo.png"));
        LayoutParams layoutParams = new LinearLayout.LayoutParams(ResourceManager.dp2px(getContext(), R.styleable.Toolbar_navigationIcon), ResourceManager.dp2px(getContext(), R.styleable.Toolbar_navigationIcon));
        layoutParams.gravity = 16;
        imageView.setLayoutParams(layoutParams);
        View textView = new TextView(context);
        textView.setText(ResourceManager.getString(context, ALREADY_COMMENT_EN, ALREADY_COMMENT_ZH_CN, ALREADY_COMMENT_ZH_TW));
        textView.setTextColor(-32256);
        textView.setTextSize(XZBDevice.DOWNLOAD_LIST_RECYCLE, 15.0f);
        textView.setIncludeFontPadding(false);
        LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 16;
        layoutParams2.leftMargin = ResourceManager.dp2px(getContext(), XZBDevice.DOWNLOAD_LIST_ALL);
        textView.setLayoutParams(layoutParams2);
        this.mContentLy.addView(imageView);
        this.mContentLy.addView(textView);
        addView(this.mContentLy);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                CommentComponentView.this.execAttented();
            }
        });
    }

    public void setCommentParam(RequestParam requestParam) {
        this.mCommentParam = requestParam;
    }

    private void execAttented() {
        WbAppActivator.getInstance(getContext(), this.mCommentParam.mAppKey).activateApp();
        WidgetRequestParam widgetRequestParam = new WidgetRequestParam(getContext());
        widgetRequestParam.setUrl(COMMENT_H5);
        widgetRequestParam.setSpecifyTitle(ResourceManager.getString(getContext(), ALREADY_COMMENT_EN, ALREADY_COMMENT_ZH_CN, ALREADY_COMMENT_ZH_TW));
        widgetRequestParam.setAppKey(this.mCommentParam.mAppKey);
        widgetRequestParam.setCommentTopic(this.mCommentParam.mTopic);
        widgetRequestParam.setCommentContent(this.mCommentParam.mContent);
        widgetRequestParam.setCommentCategory(this.mCommentParam.mCategory.getValue());
        widgetRequestParam.setAuthListener(this.mCommentParam.mAuthlistener);
        widgetRequestParam.setToken(this.mCommentParam.mAccessToken);
        Bundle createRequestParamBundle = widgetRequestParam.createRequestParamBundle();
        Intent intent = new Intent(getContext(), WeiboSdkBrowser.class);
        intent.putExtras(createRequestParamBundle);
        getContext().startActivity(intent);
    }
}
