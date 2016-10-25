package com.xunlei.downloadprovider.vod;

import android.view.View;
import android.view.View.OnClickListener;
import com.xunlei.downloadprovider.R;
import com.xunlei.downloadprovider.vod.VodUtil.SharpnessValue;

// compiled from: PopupDefinitionChoiceView.java
final class d implements OnClickListener {
    final /* synthetic */ PopupDefinitionChoiceView a;

    d(PopupDefinitionChoiceView popupDefinitionChoiceView) {
        this.a = popupDefinitionChoiceView;
    }

    public final void onClick(View view) {
        PopupDefinitionChoiceView.a;
        new StringBuilder("mOnBtnClickListener onClick ! mOnDefinitionChoicedListener = ").append(this.a.l);
        SharpnessValue sharpnessValue = null;
        switch (view.getId()) {
            case R.id.vod_definition_choice_btn_hd3:
                sharpnessValue = SharpnessValue.hd3;
                break;
            case R.id.vod_definition_choice_btn_hd2:
                sharpnessValue = SharpnessValue.hd2;
                break;
            case R.id.vod_definition_choice_btn_mp4:
                sharpnessValue = SharpnessValue.mp4;
                break;
            case R.id.vod_definition_choice_btn_flv:
                sharpnessValue = SharpnessValue.flv;
                break;
        }
        this.a.setChoicedDefinition(sharpnessValue);
        if (this.a.l != null && sharpnessValue != null) {
            this.a.l;
        }
    }
}
