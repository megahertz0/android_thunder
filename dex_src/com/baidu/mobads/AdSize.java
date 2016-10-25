package com.baidu.mobads;

public enum AdSize {
    Banner(0),
    Square(1),
    InterstitialGame(6),
    InterstitialReader(7),
    InterstitialRefresh(9),
    InterstitialOther(10),
    InterstitialForVideoBeforePlay(12),
    InterstitialForVideoPausePlay(13),
    PrerollVideoNative(15);
    private int a;

    static {
        Banner = new AdSize("Banner", 0, 0);
        Square = new AdSize("Square", 1, 1);
        InterstitialGame = new AdSize("InterstitialGame", 2, 6);
        InterstitialReader = new AdSize("InterstitialReader", 3, 7);
        InterstitialRefresh = new AdSize("InterstitialRefresh", 4, 9);
        InterstitialOther = new AdSize("InterstitialOther", 5, 10);
        InterstitialForVideoBeforePlay = new AdSize("InterstitialForVideoBeforePlay", 6, 12);
        InterstitialForVideoPausePlay = new AdSize("InterstitialForVideoPausePlay", 7, 13);
        PrerollVideoNative = new AdSize("PrerollVideoNative", 8, 15);
        b = new AdSize[]{Banner, Square, InterstitialGame, InterstitialReader, InterstitialRefresh, InterstitialOther, InterstitialForVideoBeforePlay, InterstitialForVideoPausePlay, PrerollVideoNative};
    }

    public final int getValue() {
        return this.a;
    }

    private AdSize(int i) {
        this.a = i;
    }
}
