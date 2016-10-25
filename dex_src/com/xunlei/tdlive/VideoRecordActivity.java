package com.xunlei.tdlive;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ProgressBar;
import com.xunlei.tdlive.base.BaseActivity;
import com.xunlei.tdlive.control.VideoPlayView;
import com.xunlei.tdlive.control.VideoPlayView.a;
import com.xunlei.tdlive.control.VideoRecordView;
import org.android.spdy.SpdyProtocol;
import org.android.spdy.TnetStatusCode;

public class VideoRecordActivity extends BaseActivity implements OnClickListener, a, VideoRecordView.a {
    private VideoRecordView a;
    private VideoPlayView b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private ProgressBar h;
    private int i;

    public VideoRecordActivity() {
        this.i = 0;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFormat(TnetStatusCode.EASY_REQ_STATE_PROCESS_RSP_FAIL);
        setContentView(R.layout.xllive_activity_video_record);
        c();
    }

    private void c() {
        this.c = findViewById(R.id.first_page);
        this.d = findViewById(R.id.record_page);
        this.e = findViewById(R.id.lack_time_page);
        this.f = findViewById(R.id.record_finish_page);
        this.g = findViewById(R.id.uploading_page);
        this.h = (ProgressBar) findViewById(R.id.progress_bar);
        this.a = (VideoRecordView) findViewById(R.id.video_record_view);
        this.a.setOnVideoRecordListenner(this);
        this.b = (VideoPlayView) findViewById(R.id.video_play_view);
        this.b.setOnVideoPlayListener(this);
        this.b.setOnClickListener(this);
        findViewById(R.id.first_start).setOnClickListener(this);
        findViewById(R.id.record_stop).setOnClickListener(this);
        findViewById(R.id.lack_time_camera).setOnClickListener(this);
        findViewById(R.id.lack_time_light).setOnClickListener(this);
        findViewById(R.id.lack_time_close).setOnClickListener(this);
        findViewById(R.id.lack_time_start).setOnClickListener(this);
        findViewById(R.id.record_finish_retry).setOnClickListener(this);
        findViewById(R.id.record_finish_upload).setOnClickListener(this);
    }

    public void onClick(View view) {
        boolean z = true;
        int id = view.getId();
        if (id == R.id.first_start || id == R.id.lack_time_start || id == R.id.record_finish_retry) {
            a(R.id.record_page);
            this.a.startRecord();
            this.i = 0;
            setTimer(0, 100);
            this.h.setProgress(0);
            this.b.stop();
        } else if (id == R.id.record_stop) {
            this.a.stopRecord();
        } else if (id == R.id.lack_time_camera) {
            r2 = this.a;
            if (this.a.isFrontCamera()) {
                z = false;
            }
            r2.selectFrontCamera(z);
        } else if (id == R.id.lack_time_light) {
            r2 = this.a;
            if (this.a.isFlashLightOpen()) {
                z = false;
            }
            r2.openFlashLight(z);
        } else if (id == R.id.lack_time_close) {
            finish();
        } else if (id != R.id.record_finish_upload && id == R.id.video_play_view) {
            if (this.b.isPlaying()) {
                this.b.stop();
                return;
            }
            this.b.play();
            a(R.id.video_play_view);
        }
    }

    private void a(int i) {
        if (i == R.id.record_page) {
            this.c.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.d.setVisibility(0);
            this.a.setVisibility(0);
            this.b.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        } else if (i == R.id.lack_time_page) {
            this.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.e.setVisibility(0);
        } else if (i == R.id.record_finish_page) {
            this.d.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.f.setVisibility(0);
            this.a.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.b.setVisibility(0);
        } else if (i == R.id.uploading_page) {
            this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
            this.g.setVisibility(0);
        } else if (i == R.id.video_play_view) {
            this.f.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
        }
    }

    public void a() {
        killTimer(0);
        if (this.i < 50) {
            a(R.id.lack_time_page);
        } else {
            a(R.id.record_finish_page);
        }
        this.i = 0;
    }

    public void onTimer(int i) {
        this.i++;
        new StringBuilder("onTimer, mSecondPassed=").append(this.i);
        this.h.setProgress(this.i);
        if (this.i == 300) {
            this.a.stopRecord();
        }
    }

    public void b() {
        a(R.id.record_finish_page);
    }
}
