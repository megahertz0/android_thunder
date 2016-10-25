package android.support.v4.app;

import android.app.Activity;
import android.content.Intent;
import android.view.ActionProvider;
import android.view.MenuItem;
import android.widget.ShareActionProvider;

class ShareCompatICS {
    private static final String HISTORY_FILENAME_PREFIX = ".sharecompat_";

    ShareCompatICS() {
    }

    public static void configureMenuItem(MenuItem menuItem, Activity activity, Intent intent) {
        ActionProvider actionProvider = menuItem.getActionProvider();
        if (actionProvider instanceof ShareActionProvider) {
            ShareActionProvider shareActionProvider = (ShareActionProvider) actionProvider;
        } else {
            actionProvider = new ShareActionProvider(activity);
        }
        actionProvider.setShareHistoryFileName(new StringBuilder(HISTORY_FILENAME_PREFIX).append(activity.getClass().getName()).toString());
        actionProvider.setShareIntent(intent);
        menuItem.setActionProvider(actionProvider);
    }
}
