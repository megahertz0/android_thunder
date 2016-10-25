package com.xunlei.downloadprovider.ad.recommend;

import java.util.ArrayList;
import java.util.List;

public final class RecommendADConst {

    public enum RecommendSSPAdMapping {
        ALL_FIRST("1137", 0, 0),
        ALL_SECOND("1138", 0, 1),
        ALL_THIRD("1139", 0, 2),
        RUNNING_FIRST("1140", 1, 0),
        RUNNING_SECOND("1141", 1, 1),
        RUNNING_THIRD("1142", 1, 2),
        DONE_FIRST("1143", 2, 0),
        DONE_SECOND("1144", 2, 1),
        DONE_THIRD("1145", 2, 2);
        public int pageIndex;
        public int position;
        public String positionId;

        static {
            String str = "1137";
            int i = 0;
            int i2 = 0;
            ALL_FIRST = new com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping("ALL_FIRST", 0, "1137", 0, 0);
            String str2 = "1138";
            int i3 = 0;
            int i4 = 1;
            ALL_SECOND = new com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping("ALL_SECOND", 1, "1138", 0, 1);
            str2 = "1139";
            i3 = 0;
            i4 = 2;
            ALL_THIRD = new com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping("ALL_THIRD", 2, "1139", 0, 2);
            str2 = "1140";
            i3 = 1;
            i4 = 0;
            RUNNING_FIRST = new com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping("RUNNING_FIRST", 3, "1140", 1, 0);
            str2 = "1141";
            i3 = 1;
            i4 = 1;
            RUNNING_SECOND = new com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping("RUNNING_SECOND", 4, "1141", 1, 1);
            str2 = "1142";
            i3 = 1;
            i4 = 2;
            RUNNING_THIRD = new com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping("RUNNING_THIRD", 5, "1142", 1, 2);
            str2 = "1143";
            i3 = 2;
            i4 = 0;
            DONE_FIRST = new com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping("DONE_FIRST", 6, "1143", 2, 0);
            str2 = "1144";
            i3 = 2;
            i4 = 1;
            DONE_SECOND = new com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping("DONE_SECOND", 7, "1144", 2, 1);
            str2 = "1145";
            i3 = 2;
            i4 = 2;
            DONE_THIRD = new com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping("DONE_THIRD", 8, "1145", 2, 2);
            a = new com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping[]{ALL_FIRST, ALL_SECOND, ALL_THIRD, RUNNING_FIRST, RUNNING_SECOND, RUNNING_THIRD, DONE_FIRST, DONE_SECOND, DONE_THIRD};
        }

        private RecommendSSPAdMapping(String str, int i, int i2) {
            this.positionId = str;
            this.pageIndex = i;
            this.position = i2;
        }

        public static com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping fromPositionId(String str) {
            com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping recommendSSPAdMapping = null;
            com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping[] values = values();
            int length = values.length;
            int i = 0;
            while (i < length) {
                com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping recommendSSPAdMapping2 = values[i];
                if (!recommendSSPAdMapping2.positionId.equals(str)) {
                    recommendSSPAdMapping2 = recommendSSPAdMapping;
                }
                i++;
                recommendSSPAdMapping = recommendSSPAdMapping2;
            }
            return recommendSSPAdMapping;
        }

        public static com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping fromLocal(int i, int i2) {
            com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping recommendSSPAdMapping = null;
            com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping[] values = values();
            int length = values.length;
            int i3 = 0;
            while (i3 < length) {
                com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping recommendSSPAdMapping2 = values[i3];
                if (recommendSSPAdMapping2.pageIndex != i || recommendSSPAdMapping2.position != i2) {
                    recommendSSPAdMapping2 = recommendSSPAdMapping;
                }
                i3++;
                recommendSSPAdMapping = recommendSSPAdMapping2;
            }
            return recommendSSPAdMapping;
        }

        public static List<com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping> fromPageIndex(int i) {
            List<com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping> arrayList = new ArrayList(3);
            for (com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping recommendSSPAdMapping : values()) {
                if (recommendSSPAdMapping.pageIndex == i) {
                    arrayList.add(recommendSSPAdMapping);
                }
            }
            return arrayList;
        }

        public static List<String> getPositionIds(int i) {
            List<String> arrayList = new ArrayList(3);
            for (com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping recommendSSPAdMapping : values()) {
                if (recommendSSPAdMapping.pageIndex == i) {
                    arrayList.add(recommendSSPAdMapping.positionId);
                }
            }
            return arrayList;
        }

        public static List<String> getAllPositionIds() {
            List<String> arrayList = new ArrayList(9);
            for (com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping recommendSSPAdMapping : values()) {
                arrayList.add(recommendSSPAdMapping.positionId);
            }
            return arrayList;
        }

        public static int getPositionCount(int i) {
            int i2 = 0;
            com.xunlei.downloadprovider.ad.recommend.RecommendADConst.RecommendSSPAdMapping[] values = values();
            int length = values.length;
            for (int i3 = 0; i3 < length; i3++) {
                if (values[i3].pageIndex == i) {
                    i2++;
                }
            }
            return i2;
        }
    }
}
