package com.xunlei.downloadprovider.player;

import android.os.SystemClock;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import com.xunlei.analytics.b.c;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.app.BrothersApplication;
import com.xunlei.downloadprovider.player.a.b;
import com.xunlei.downloadprovider.player.wrapper.a;
import com.xunlei.downloadprovider.player.wrapper.d;
import com.xunlei.downloadprovider.player.wrapper.e;
import com.xunlei.xllib.b.j;
import org.android.spdy.SpdyProtocol;

// compiled from: ThunderMediaPlayer.java
final class ad implements e {
    final /* synthetic */ a a;
    final /* synthetic */ ab b;

    ad(ab abVar, a aVar) {
        this.b = abVar;
        this.a = aVar;
    }

    public final void a(d dVar, int i) {
        MediaPlayerControllerView mediaPlayerControllerView = this.b.o;
        int i2 = (mediaPlayerControllerView.j * i) / 100;
        mediaPlayerControllerView.g.setSecondaryProgress(i2);
        mediaPlayerControllerView.f.setSecondaryProgress(i2);
    }

    public final void a(d dVar) {
        new StringBuilder("onCompletion--state=").append(this.b.f());
        this.b.b(false);
        if (!this.b.z) {
            this.b.l();
            this.b.a(false);
        }
        if (this.b.r != null) {
            this.b.r.onCompletion(dVar.b);
        }
    }

    public final boolean a(d dVar, int i, int i2) {
        new StringBuilder("onError--what=").append(i).append("|extra=").append(i2).append("|state=").append(this.b.f());
        this.b.b(true);
        return true;
    }

    public final boolean b(d dVar, int i, int i2) {
        new StringBuilder("onInfo--what=").append(i).append("|extra=").append(i2).append("|state=").append(this.b.f());
        return true;
    }

    public final void c(d dVar, int i, int i2) {
        new StringBuilder("onVideoSizeChanged--width=").append(i).append("|height=").append(i2).append("|state=").append(this.b.f());
        this.b.j = i;
        this.b.k = i2;
        ab.a(this.b, this.b.h, this.b.i, i, i2);
    }

    public final void b(d dVar) {
        new StringBuilder("onPrepared--state=").append(this.b.f());
        if (this.b.g != null) {
            int c = this.a.c();
            this.b.q.setMediaDuration(c);
            this.b.g.c = c;
        }
        this.b.c();
        if (this.b.g != null) {
            ai aiVar = this.b.g.d;
            if (aiVar.g > 0 && Math.abs(aiVar.g - this.b.g.c) > 1000) {
                this.b.a(aiVar.g);
            }
        }
        ab abVar = this.b;
        if (abVar.g != null) {
            ai aiVar2 = abVar.g.d;
            if (!"homepage_ad".equals(aiVar2.i)) {
                b bVar = new b();
                bVar.a = aiVar2.i;
                bVar.b = "online_shortvideo";
                bVar.l = "online_url";
                bVar.d = "feed_player";
                bVar.c = "mp4";
                bVar.e = "unknown";
                bVar.i = abVar.j + "*" + abVar.k;
                if (abVar.i() && !abVar.j()) {
                    bVar.j = (long) (abVar.g() / 1000);
                }
                bVar.k = abVar.c;
                bVar.m = aiVar2.c;
                bVar.o = aiVar2.a;
                bVar.t = SystemClock.elapsedRealtime() - abVar.t;
                bVar.u = abVar.u ? c.f : "0";
                bVar.q = aiVar2.j;
                bVar.r = com.xunlei.downloadprovider.a.b.q();
                bVar.s = com.xunlei.xllib.a.b.d(abVar.d);
                com.xunlei.downloadprovider.homepage.recommend.b.a.a(BrothersApplication.a).a(aiVar2.c, aiVar2.d, com.xunlei.downloadprovider.a.b.d(), abVar.g(), "start", 0);
                a.a(bVar);
            }
        }
    }

    public final void a(int i) {
        if (this.b.g != null) {
            this.b.g.b = i;
        }
        MediaPlayerControllerView mediaPlayerControllerView = this.b.o;
        if (i >= 0) {
            mediaPlayerControllerView.g.setProgress(i);
            mediaPlayerControllerView.f.setProgress(i);
            mediaPlayerControllerView.i.setText(j.b((long) i));
            if (i > 0 && mediaPlayerControllerView.e.getVisibility() == 0) {
                Animation loadAnimation = AnimationUtils.loadAnimation(mediaPlayerControllerView.getContext(), R.anim.media_player_poster_hide_animation);
                loadAnimation.setDuration(200);
                mediaPlayerControllerView.e.setVisibility(SpdyProtocol.PUBKEY_SEQ_ADASH);
                mediaPlayerControllerView.e.startAnimation(loadAnimation);
            }
        }
        if (this.b.g != null && !this.b.g.a) {
            this.b.v();
        }
    }
}
