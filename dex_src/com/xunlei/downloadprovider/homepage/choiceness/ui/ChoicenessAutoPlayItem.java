package com.xunlei.downloadprovider.homepage.choiceness.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.homepage.choiceness.ChoicenessReporter;
import com.xunlei.downloadprovider.homepage.choiceness.ui.a.e;
import com.xunlei.downloadprovider.player.MediaPlayerState;
import com.xunlei.downloadprovider.player.a.a;
import com.xunlei.downloadprovider.player.a.b;
import com.xunlei.downloadprovider.player.a.d;
import com.xunlei.downloadprovider.player.ab;
import com.xunlei.downloadprovider.player.q;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity;
import com.xunlei.downloadprovider.web.base.ShortMovieDetailActivity.From;
import com.xunlei.thundersniffer.sniff.sniffer.SnifferProtocol.SetKey;

public class ChoicenessAutoPlayItem extends ChoicenessVideoPlayItemView implements b {
    private a d;

    public final /* bridge */ /* synthetic */ boolean a(int i, e eVar) {
        return a((com.xunlei.downloadprovider.homepage.choiceness.a.a.a) eVar);
    }

    public ChoicenessAutoPlayItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChoicenessAutoPlayItem(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public final void a(ab abVar) {
        super.a(abVar);
        if (abVar != null) {
            abVar.a(new a(this, abVar));
            abVar.b(1);
            abVar.a(new b(this));
        }
        if (this.d != null) {
            this.d.b(this);
        }
    }

    public final void b(ab abVar) {
        super.b(abVar);
        abVar.a(null);
        if (this.d != null) {
            this.d.b(null);
        }
    }

    public int getVisibilityPercents() {
        return d.a(getContext(), this.c, getHolder().i);
    }

    public int getPosition() {
        return this.a.a;
    }

    public final void c() {
        if (!this.b) {
            this.b = true;
            a(true);
        }
    }

    public final void d() {
        this.b = false;
        if (getThunderMediaPlayer() != null) {
            q.a().a(getThunderMediaPlayer());
        }
    }

    public View getLayout() {
        return getHolder().i;
    }

    public final boolean e() {
        return (getThunderMediaPlayer() == null || getThunderMediaPlayer().e.b.a.a() == MediaPlayerState.IDLE) ? this.b : true;
    }

    public final boolean f() {
        return true;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_poster:
            case R.id.item_play_icon:
                a();
                com.xunlei.downloadprovider.homepage.choiceness.a.a.a choicenessInfo = getChoicenessInfo();
                ChoicenessReporter.a(choicenessInfo.d, choicenessInfo.b, "pic", choicenessInfo.f());
            default:
                break;
        }
    }

    public final boolean a(com.xunlei.downloadprovider.homepage.choiceness.a.a.a aVar) {
        a();
        ChoicenessReporter.a(aVar.d, aVar.b, SetKey.TITLE, aVar.f());
        return true;
    }

    protected String getPlayerFrom() {
        return "homepage_feed_auto";
    }

    private void a() {
        com.xunlei.downloadprovider.homepage.choiceness.a.a.a choicenessInfo = getChoicenessInfo();
        ab thunderMediaPlayer = getThunderMediaPlayer();
        int i = -1;
        if (thunderMediaPlayer == null) {
            thunderMediaPlayer = q.a().b("home_player");
        }
        if (thunderMediaPlayer != null) {
            i = thunderMediaPlayer.a;
        }
        getAdapter().b = false;
        ShortMovieDetailActivity.a(getContext(), i, From.HOME_VIDEO_AUTO, choicenessInfo.e(), false);
    }
}
