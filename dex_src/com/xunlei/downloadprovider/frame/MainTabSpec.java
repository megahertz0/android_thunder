package com.xunlei.downloadprovider.frame;

public final class MainTabSpec {
    public static final Tab[] a;

    public enum Tab {
        THUNDER("thunder"),
        SEARCH("search"),
        FIND("find"),
        USER("user");
        private String a;
        private int b;
        private int c;

        static {
            String str = "thunder";
            THUNDER = new com.xunlei.downloadprovider.frame.MainTabSpec.Tab("THUNDER", 0, "thunder");
            str = "search";
            SEARCH = new com.xunlei.downloadprovider.frame.MainTabSpec.Tab("SEARCH", 1, "search");
            str = "find";
            FIND = new com.xunlei.downloadprovider.frame.MainTabSpec.Tab("FIND", 2, "find");
            str = "user";
            USER = new com.xunlei.downloadprovider.frame.MainTabSpec.Tab("USER", 3, "user");
            d = new com.xunlei.downloadprovider.frame.MainTabSpec.Tab[]{THUNDER, SEARCH, FIND, USER};
        }

        private Tab(String str) {
            this.a = str;
            if (str.equals("thunder")) {
                this.b = 2130838638;
                this.c = 2131231691;
            } else if (str.equals("search")) {
                this.b = 2130838643;
                this.c = 2131231701;
            } else if (str.equals("find")) {
                this.b = 2130838639;
                this.c = 2131231688;
            } else if (str.equals("user")) {
                this.b = 2130838644;
                this.c = 2131231703;
            }
        }

        public final String getTag() {
            return this.a;
        }

        public final int getIcon() {
            return this.b;
        }

        public final int getText() {
            return this.c;
        }
    }

    static {
        a = new Tab[]{Tab.THUNDER, Tab.SEARCH, Tab.FIND, Tab.USER};
    }
}
