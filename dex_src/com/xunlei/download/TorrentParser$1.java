package com.xunlei.download;

import android.os.Handler;
import android.os.Message;
import com.xunlei.download.TorrentParser.ParseResult;

class TorrentParser$1 extends Handler {
    final /* synthetic */ TorrentParser a;

    TorrentParser$1(TorrentParser torrentParser) {
        this.a = torrentParser;
    }

    public void handleMessage(Message message) {
        if (TorrentParser.a(this.a) == null) {
            return;
        }
        if (message.what == 6537) {
            TorrentParser.a(this.a).onTorrentParseCompleted((ParseResult) message.obj);
        } else if (message.what == 6536) {
            TorrentParser.a(this.a).onTorrentParseBegin();
        }
    }
}
