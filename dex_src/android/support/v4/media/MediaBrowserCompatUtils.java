package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning;
import java.util.List;

public class MediaBrowserCompatUtils {
    public static boolean areSameOptions(Bundle bundle, Bundle bundle2) {
        if (bundle == bundle2) {
            return true;
        }
        return bundle == null ? bundle2.getInt(EXTRA_PAGE, -1) == -1 && bundle2.getInt(EXTRA_PAGE_SIZE, -1) == -1 : bundle2 == null ? bundle.getInt(EXTRA_PAGE, -1) == -1 && bundle.getInt(EXTRA_PAGE_SIZE, -1) == -1 : bundle.getInt(EXTRA_PAGE, -1) == bundle2.getInt(EXTRA_PAGE, -1) && bundle.getInt(EXTRA_PAGE_SIZE, -1) == bundle2.getInt(EXTRA_PAGE_SIZE, -1);
    }

    public static boolean hasDuplicatedItems(Bundle bundle, Bundle bundle2) {
        int i;
        int i2 = InMobiClientPositioning.NO_REPEAT;
        int i3 = bundle == null ? -1 : bundle.getInt(EXTRA_PAGE, -1);
        int i4 = bundle2 == null ? -1 : bundle2.getInt(EXTRA_PAGE, -1);
        int i5 = bundle == null ? -1 : bundle.getInt(EXTRA_PAGE_SIZE, -1);
        int i6 = bundle2 == null ? -1 : bundle2.getInt(EXTRA_PAGE_SIZE, -1);
        if (i3 == -1 || i5 == -1) {
            i3 = Integer.MAX_VALUE;
            Object obj = null;
        } else {
            i3 = i5 * (i3 - 1);
            i5 = i3;
            i3 = (i3 + i5) - 1;
        }
        if (i4 == -1 || i6 == -1) {
            i = 0;
        } else {
            i = i6 * (i4 - 1);
            i2 = (i + i6) - 1;
        }
        if (i5 > i || i > i3) {
            return i5 <= i2 && i2 <= i3;
        } else {
            return true;
        }
    }

    public static List<MediaItem> applyOptions(List<MediaItem> list, Bundle bundle) {
        int i = bundle.getInt(EXTRA_PAGE, -1);
        int i2 = bundle.getInt(EXTRA_PAGE_SIZE, -1);
        if (i == -1 && i2 == -1) {
            return list;
        }
        int i3 = i2 * (i - 1);
        int i4 = i3 + i2;
        if (i <= 0 || i2 <= 0 || i3 >= list.size()) {
            return null;
        }
        if (i4 > list.size()) {
            i4 = list.size();
        }
        return list.subList(i3, i4);
    }
}
