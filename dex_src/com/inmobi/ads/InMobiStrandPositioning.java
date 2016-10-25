package com.inmobi.ads;

import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class InMobiStrandPositioning {
    private static final String TAG;

    public static class InMobiClientPositioning {
        public static final int NO_REPEAT = Integer.MAX_VALUE;
        private static final String TAG;
        private List<Integer> mAdPositions;
        private int mRepeatInterval;

        static {
            TAG = com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning.class.getSimpleName();
        }

        public InMobiClientPositioning() {
            this.mRepeatInterval = Integer.MAX_VALUE;
            this.mAdPositions = new ArrayList();
        }

        public com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning addFixedPosition(int i) {
            if (i < 0) {
                Logger.a(InternalLogLevel.ERROR, TAG, "Ad positions must be non-negative");
            } else {
                int binarySearch = Collections.binarySearch(this.mAdPositions, Integer.valueOf(i));
                if (binarySearch < 0) {
                    this.mAdPositions.add(binarySearch ^ -1, Integer.valueOf(i));
                }
            }
            return this;
        }

        List<Integer> getFixedPositions() {
            return this.mAdPositions;
        }

        public com.inmobi.ads.InMobiStrandPositioning.InMobiClientPositioning enableRepeatingPositions(int i) {
            if (i <= 1) {
                Logger.a(InternalLogLevel.ERROR, TAG, "Repeating interval must be greater than 1");
                this.mRepeatInterval = Integer.MAX_VALUE;
            } else {
                this.mRepeatInterval = i;
            }
            return this;
        }

        int getRepeatingInterval() {
            return this.mRepeatInterval;
        }
    }

    static {
        TAG = InMobiStrandPositioning.class.getSimpleName();
    }
}
