package com.xunlei.downloadprovider.download.taskDetail.widget;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.xllib.R;
import org.apache.commons.logging.impl.SimpleLog;

// compiled from: TaskDetailSpeedInfoView.java
final class k implements OnClickListener {
    final /* synthetic */ TaskDetailSpeedInfoView a;

    k(TaskDetailSpeedInfoView taskDetailSpeedInfoView) {
        this.a = taskDetailSpeedInfoView;
    }

    public final void onClick(View view) {
        if (this.a.a.H.getMaxLine() == 2) {
            this.a.a.H.setMaxLine(R.styleable.AppCompatTheme_buttonStyle);
            this.a.a.I.setImageResource(com.xunlei.downloadprovider.R.drawable.detail_speed_arrow_up);
            return;
        }
        this.a.a.H.setMaxLine(SimpleLog.LOG_LEVEL_DEBUG);
        this.a.a.I.setImageResource(com.xunlei.downloadprovider.R.drawable.detail_speed_arrow_down);
    }
}
