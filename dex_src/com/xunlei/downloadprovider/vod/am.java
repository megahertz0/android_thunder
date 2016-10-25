package com.xunlei.downloadprovider.vod;

import android.content.Context;
import android.os.Message;
import com.xunlei.downloadprovider.a.h.a;
import com.xunlei.downloadprovider.frame.MainTabActivity;
import com.xunlei.downloadprovider.service.DownloadService;
import com.xunlei.downloadprovider.service.downloads.TaskInfo;
import com.xunlei.downloadprovider.service.downloads.TorrentSeedInfo;
import com.xunlei.downloadprovider.web.core.JsInterface;
import com.xunlei.tdlive.R;
import com.xunlei.xllib.a.b;
import java.util.List;

// compiled from: VodPlayerForBtActivity.java
final class am implements a {
    final /* synthetic */ VodPlayerForBtActivity a;

    am(VodPlayerForBtActivity vodPlayerForBtActivity) {
        this.a = vodPlayerForBtActivity;
    }

    public final void a(Message message) {
        StringBuilder stringBuilder;
        switch (message.what) {
            case R.styleable.AppCompatTheme_ratingBarStyleSmall:
                if (message.obj instanceof TaskInfo) {
                    TaskInfo taskInfo = (TaskInfo) message.obj;
                    if (taskInfo.mTaskStatus == 8 && taskInfo.mUrl.equals(this.a.f)) {
                        VodPlayerForBtActivity.a;
                        if (!this.a.g) {
                            new a(taskInfo.mFilePath + taskInfo.mFileName, this.a.i).execute(new Void[0]);
                            this.a.g = true;
                        }
                    }
                }
            case JsInterface.MSG_JS_BACK_TO_UC:
                com.xunlei.downloadprovider.thirdpart.thirdpartycallplay.a.a(this.a.e);
                this.a.finish();
            case JsInterface.MSG_JS_GO_TO_MAIN_PAGE:
                Context context = this.a;
                if (context != null) {
                    MainTabActivity.a(context, "thunder");
                }
            case JsInterface.MSG_JS_VOD_PLAY_FOR_COOPERATION:
                if (message.obj instanceof String) {
                    String str = (String) message.obj;
                    VodUtil.a();
                    VodUtil.b(this.a, str);
                }
            case 3000:
                List list = (List) message.obj;
                StringBuilder stringBuilder2 = new StringBuilder("{\"code\":\"");
                stringBuilder2.append("1\", \"result\":");
                stringBuilder2.append("{\"ip\":\"");
                stringBuilder2.append(b.i(this.a));
                stringBuilder2.append("\", \"torrentUrl\":\"");
                stringBuilder2.append(this.a.d);
                stringBuilder2.append("\", \"packageName\":\"");
                stringBuilder2.append(this.a.e);
                stringBuilder2.append("\", \"infos\":[");
                String str2 = "\"}, ";
                int size = list.size();
                int i = 0;
                while (i < size) {
                    TorrentSeedInfo torrentSeedInfo = (TorrentSeedInfo) list.get(i);
                    if (i == 0) {
                        DownloadService.a();
                        DownloadService.f();
                    }
                    stringBuilder2.append("{\"fileindex\":\"").append(torrentSeedInfo.mFileIndex);
                    stringBuilder2.append("\", \"filename\":\"").append(torrentSeedInfo.mFileName).append(i == size + -1 ? "\"}" : str2);
                    i++;
                }
                stringBuilder2.append("], \"infohash\":\"").append(null).append("\"}}");
                stringBuilder = new StringBuilder();
                stringBuilder.append(String.format("javascript:window.seedHandle('%s')", new Object[]{stringBuilder2.toString()}));
                VodPlayerForBtActivity.a;
                new StringBuilder("sb.toString() --> ").append(stringBuilder2.toString());
                this.a.b.a(stringBuilder.toString());
            case 3001:
                new StringBuilder("{\"code\":\"").append("0\"}");
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(String.format("javascript:window.seedHandle('%s')", new Object[]{stringBuilder.toString()}));
                this.a.b.a(stringBuilder3.toString());
            default:
                break;
        }
    }
}
