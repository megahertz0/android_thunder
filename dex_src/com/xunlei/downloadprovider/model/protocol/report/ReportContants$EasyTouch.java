package com.xunlei.downloadprovider.model.protocol.report;

public interface ReportContants$EasyTouch {

    public enum From {
        thunder,
        movie,
        book,
        app,
        teleplay,
        short_video,
        mv,
        anime,
        variety,
        subject,
        easytouch;

        static {
            thunder = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From("thunder", 0);
            movie = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From("movie", 1);
            book = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From("book", 2);
            app = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From("app", 3);
            teleplay = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From("teleplay", 4);
            short_video = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From("short_video", 5);
            mv = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From("mv", 6);
            anime = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From("anime", 7);
            variety = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From("variety", 8);
            subject = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From("subject", 9);
            easytouch = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From("easytouch", 10);
            a = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.EasyTouch.From[]{thunder, movie, book, app, teleplay, short_video, mv, anime, variety, subject, easytouch};
        }
    }
}
