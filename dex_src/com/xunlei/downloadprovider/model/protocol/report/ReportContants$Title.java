package com.xunlei.downloadprovider.model.protocol.report;

public interface ReportContants$Title {

    public enum TitleFrom {
        metro,
        movie,
        book,
        app,
        teleplay,
        short_video,
        mv,
        anime,
        variety,
        subject,
        rank,
        thunder,
        radar,
        recommend,
        slide;

        static {
            metro = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("metro", 0);
            movie = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("movie", 1);
            book = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("book", 2);
            app = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("app", 3);
            teleplay = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("teleplay", 4);
            short_video = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("short_video", 5);
            mv = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("mv", 6);
            anime = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("anime", 7);
            variety = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("variety", 8);
            subject = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("subject", 9);
            rank = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("rank", 10);
            thunder = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("thunder", 11);
            radar = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("radar", 12);
            recommend = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("recommend", 13);
            slide = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom("slide", 14);
            a = new com.xunlei.downloadprovider.model.protocol.report.ReportContants.Title.TitleFrom[]{metro, movie, book, app, teleplay, short_video, mv, anime, variety, subject, rank, thunder, radar, recommend, slide};
        }
    }
}
