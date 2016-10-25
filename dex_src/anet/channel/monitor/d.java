package anet.channel.monitor;

import com.xunlei.xiazaibao.sdk.XZBDevice;

// compiled from: Taobao
class d {
    private long a;
    private double b;
    private double c;
    private double d;
    private double e;
    private double f;
    private double g;
    private double h;
    private double i;
    private double j;
    private double k;

    d() {
        this.a = 0;
        this.i = 0.0d;
        this.j = 0.0d;
        this.k = 0.0d;
    }

    public double a(double d, double d2) {
        double d3 = d / d2;
        if (d3 >= 8.0d) {
            if (this.a == 0) {
                this.i = d3;
                this.h = this.i;
                this.d = this.h * 0.1d;
                this.c = this.h * 0.02d;
                this.e = (0.1d * this.h) * this.h;
            } else if (this.a == 1) {
                this.j = d3;
                this.h = this.j;
            } else {
                double d4 = d3 - this.j;
                this.i = this.j;
                this.j = d3;
                this.b = d3 / 0.95d;
                this.g = this.b - (0.95d * this.h);
                Object obj = null;
                double sqrt = Math.sqrt(this.d);
                if (this.g >= 4.0d * sqrt) {
                    obj = 1;
                    this.g = (sqrt * 2.0d) + (0.75d * this.g);
                } else if (this.g <= -4.0d * sqrt) {
                    obj = XZBDevice.DOWNLOAD_LIST_RECYCLE;
                    this.g = (sqrt * -1.0d) + (0.75d * this.g);
                }
                this.d = Math.min(Math.max(Math.abs((1.05d * this.d) - ((0.0025d * this.g) * this.g)), 0.8d * this.d), 1.25d * this.d);
                this.f = this.e / ((0.9025d * this.e) + this.d);
                this.h = ((d4 * 1.0526315789473684d) + this.h) + (this.f * this.g);
                if (r0 == 1) {
                    this.h = Math.min(this.h, this.b);
                } else if (r0 == 2) {
                    this.h = Math.max(this.h, this.b);
                }
                this.e = (1.0d - (0.95d * this.f)) * (this.e + this.c);
            }
            if (this.h < 0.0d) {
                this.k = this.j * 0.7d;
                this.h = this.k;
            } else {
                this.k = this.h;
            }
            return this.k;
        } else if (this.a != 0) {
            return this.k;
        } else {
            this.k = d3;
            return this.k;
        }
    }

    public void a() {
        this.a = 0;
        this.k = 0.0d;
    }
}
