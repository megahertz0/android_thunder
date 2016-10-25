package com.xunlei.tdlive.play.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.xunlei.tdlive.R;

public class RePlayButtonBar extends LinearLayout {
    public View full_screen_btn;
    public TextView process_txt;
    public SeekBar seekBar;
    public View share_btn;
    public ImageView start_stop_btn;

    public RePlayButtonBar(Context context) {
        super(context);
        a();
    }

    public RePlayButtonBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public RePlayButtonBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.xllive_replay_button_bar, this, true);
        this.share_btn = findViewById(R.id.replay_share_btn);
        this.full_screen_btn = findViewById(R.id.replay_full_screen_btn);
        this.start_stop_btn = (ImageView) findViewById(R.id.replay_start_stop_btn);
        this.seekBar = (SeekBar) findViewById(R.id.replay_seek_bar);
        this.process_txt = (TextView) findViewById(R.id.replay_process_txt);
    }
}
