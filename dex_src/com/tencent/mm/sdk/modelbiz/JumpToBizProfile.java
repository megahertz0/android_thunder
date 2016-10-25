package com.tencent.mm.sdk.modelbiz;

import android.os.Bundle;
import com.tencent.mm.sdk.b.b;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.xunlei.tdlive.R;

public class JumpToBizProfile {
    public static final int JUMP_TO_HARD_WARE_BIZ_PROFILE = 1;
    public static final int JUMP_TO_NORMAL_BIZ_PROFILE = 0;

    public static class Req extends BaseReq {
        private static final int EXT_MSG_LENGTH = 1024;
        private static final String TAG = "MicroMsg.SDK.JumpToBizProfile.Req";
        public String extMsg;
        public int profileType;
        public String toUserName;

        public Req() {
            this.profileType = 0;
        }

        public boolean checkArgs() {
            if (this.toUserName == null || this.toUserName.length() == 0) {
                b.b(TAG, "checkArgs fail, toUserName is invalid");
                return false;
            } else if (this.extMsg != null && this.extMsg.length() > 1024) {
                b.b(TAG, "ext msg is not null, while the length exceed 1024 bytes");
                return false;
            } else if (this.profileType != 1 || (this.extMsg != null && this.extMsg.length() != 0)) {
                return true;
            } else {
                b.b(TAG, "scene is jump to hardware profile, while extmsg is null");
                return false;
            }
        }

        public void fromBundle(Bundle bundle) {
            super.fromBundle(bundle);
            this.toUserName = bundle.getString("_wxapi_jump_to_biz_profile_req_to_user_name");
            this.extMsg = bundle.getString("_wxapi_jump_to_biz_profile_req_ext_msg");
        }

        public int getType() {
            return R.styleable.Toolbar_contentInsetLeft;
        }

        public void toBundle(Bundle bundle) {
            super.toBundle(bundle);
            bundle.putString("_wxapi_jump_to_biz_profile_req_to_user_name", this.toUserName);
            bundle.putString("_wxapi_jump_to_biz_profile_req_ext_msg", this.extMsg);
            bundle.putInt("_wxapi_jump_to_biz_profile_req_scene", 0);
            bundle.putInt("_wxapi_jump_to_biz_profile_req_profile_type", this.profileType);
        }
    }
}
