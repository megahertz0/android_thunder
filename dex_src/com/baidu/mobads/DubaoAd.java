package com.baidu.mobads;

import android.app.Activity;
import com.baidu.mobads.j.m;
import com.baidu.mobads.production.e.b;

public class DubaoAd {
    private b a;

    public static class Position {
        public static final int POSITION_LEFT = 0;
        public static final int POSITION_RIGHT = 1;
        private int a;
        private double b;

        public Position(int i, double d) {
            this.a = i;
            this.b = d;
        }

        public int getmLeftOrRight() {
            return this.a;
        }

        public double getmTopMarginPercent() {
            return this.b;
        }
    }

    public DubaoAd(Activity activity, String str, Position position) {
        this.a = null;
        if (position == null) {
            m.a().f().e("parameter position can not be null");
        } else {
            this.a = new b(activity, str, position.getmLeftOrRight() == 0, position.getmTopMarginPercent());
        }
    }

    public void destroy() {
        if (this.a != null) {
            this.a.l();
            this.a = null;
        }
    }
}
