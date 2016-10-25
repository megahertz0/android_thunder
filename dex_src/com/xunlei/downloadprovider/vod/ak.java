package com.xunlei.downloadprovider.vod;

import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import com.tencent.open.SocialConstants;
import com.xunlei.common.member.XLErrorCode;
import com.xunlei.downloadprovider.service.downloads.task.a;
import com.xunlei.downloadprovider.service.g;
import com.xunlei.tdlive.R;
import org.json.JSONObject;

// compiled from: VodPlayerActivity.java
final class ak implements Callback {
    final /* synthetic */ VodPlayerActivity a;

    ak(VodPlayerActivity vodPlayerActivity) {
        this.a = vodPlayerActivity;
    }

    public final boolean handleMessage(Message message) {
        int i = -1;
        int GetPosition;
        switch (message.what) {
            case R.styleable.AppCompatTheme_buttonBarNeutralButtonStyle:
                this.a.mPausedTaskIds = ((a) message.obj).a;
                break;
            case XLErrorCode.OAUTH_REG_TOKEN_ERROR:
                if (message.obj == null || this.a.mVodPlayerParams == null) {
                    this.a.handleOpenError(2131233166);
                } else {
                    VodUtil.b = ((g) message.obj).b;
                    VodUtil.a = true;
                    this.a.checkVodServerInited();
                }
                break;
            case XLErrorCode.OAUTH_REF_TOKEN_ERROR:
                VodUtil.a = false;
                VodUtil.b = 0;
                this.a.handleOpenError(2131233166);
                break;
            case XLErrorCode.OAUTH_CID_INVALID:
                VodUtil.a = false;
                VodUtil.b = 0;
                this.a.handleOpenError(2131233166);
                break;
            case 77892:
                if (!(this.a.isFinishing() || this.a.aPlayerAndroid == null || this.a.mVodPlayerParams == null)) {
                    if (this.a.mVideoDuration <= 0) {
                        this.a.mVideoDuration = this.a.aPlayerAndroid.GetDuration();
                    }
                    GetPosition = this.a.aPlayerAndroid.GetPosition();
                    if (GetPosition < 0 || GetPosition > this.a.mVideoDuration) {
                        GetPosition = 0;
                    }
                    this.a.mCurPlayPos = GetPosition;
                    if (this.a.mVodPlayerParams.a() != null) {
                        this.a.mVodPlayerParams.a().s = GetPosition;
                    }
                    Object GetConfig = this.a.aPlayerAndroid.GetConfig(R.styleable.AppCompatTheme_actionModeCloseDrawable);
                    if (!TextUtils.isEmpty(GetConfig) && !VodUtil.a(this.a.mVodPlayerParams.b)) {
                        GetPosition = Integer.parseInt(GetConfig);
                    } else if (!VodUtil.b(this.a.mVodPlayerParams.b) || this.a.mTaskInfo == null) {
                        GetPosition = -1;
                    } else {
                        GetPosition = (int) (((((float) this.a.mTaskInfo.mDownloadedSize) * 1.0f) / ((float) this.a.mTaskInfo.mFileSize)) * ((float) this.a.mVideoDuration));
                    }
                    VodPlayerView access$1000 = this.a.mVodPlayerView;
                    int access$3000 = this.a.mVideoDuration;
                    if (!this.a.isSeeking) {
                        i = this.a.mCurPlayPos;
                    }
                    access$1000.updatePlayPosition(access$3000, i, GetPosition);
                    this.a.processVideoStatusText();
                    if (this.a.mVodPlayerParams != null && this.a.mVodPlayerParams.b()) {
                        this.a.sendTimeEventToThirdPart(this.a.mVodPlayerParams);
                    }
                }
                break;
            case 77898:
                GetPosition = message.arg1;
                if (GetPosition == -268435452) {
                    this.a.handlePlayingError(String.valueOf(GetPosition));
                } else if (GetPosition == 0) {
                    this.a.handleOpenError(this.a.getResources().getString(2131233164));
                }
                break;
            case 453674045:
                JSONObject jSONObject = (JSONObject) message.obj;
                if (jSONObject != null) {
                    this.a.mDlnaInfo.a = jSONObject.optString(SocialConstants.PARAM_URL);
                    this.a.mDlnaInfo.b = true;
                }
                break;
        }
        return true;
    }
}
