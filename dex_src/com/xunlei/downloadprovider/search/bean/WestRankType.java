package com.xunlei.downloadprovider.search.bean;

public enum WestRankType {
    MOVIE,
    TELEPLAY,
    VARIETY,
    ANIME,
    HOT_SEARCH;
    private String[] a;

    static {
        MOVIE = new WestRankType("MOVIE", 0);
        TELEPLAY = new WestRankType("TELEPLAY", 1);
        VARIETY = new WestRankType("VARIETY", 2);
        ANIME = new WestRankType("ANIME", 3);
        HOT_SEARCH = new WestRankType("HOT_SEARCH", 4);
        b = new WestRankType[]{MOVIE, TELEPLAY, VARIETY, ANIME, HOT_SEARCH};
    }

    public final String getName() {
        return this.a[ordinal()];
    }
}
