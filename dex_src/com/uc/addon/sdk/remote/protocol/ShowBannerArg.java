package com.uc.addon.sdk.remote.protocol;

import android.os.Bundle;

public class ShowBannerArg implements BaseArg {
    public BannerBuilderRemote bannerBuilder;

    public boolean checkArgs() {
        return this.bannerBuilder != null;
    }

    public void fromBundle(Bundle bundle) {
        bundle.setClassLoader(BannerBuilderRemote.class.getClassLoader());
        this.bannerBuilder = (BannerBuilderRemote) bundle.getParcelable("key_banner_builder");
    }

    public void toBundle(Bundle bundle) {
        bundle.putParcelable("key_banner_builder", this.bannerBuilder);
    }
}
