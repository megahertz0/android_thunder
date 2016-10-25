package anetwork.channel.http;

@Deprecated
// compiled from: Taobao
public final class NetworkStatusHelper {

    // compiled from: Taobao
    public enum NetworkStatus {
        NONE,
        NO,
        GPRS,
        CDMA,
        EDGE,
        G3,
        G4,
        WIFI;

        static {
            NONE = new anetwork.channel.http.NetworkStatusHelper.NetworkStatus("NONE", 0);
            NO = new anetwork.channel.http.NetworkStatusHelper.NetworkStatus("NO", 1);
            GPRS = new anetwork.channel.http.NetworkStatusHelper.NetworkStatus("GPRS", 2);
            CDMA = new anetwork.channel.http.NetworkStatusHelper.NetworkStatus("CDMA", 3);
            EDGE = new anetwork.channel.http.NetworkStatusHelper.NetworkStatus("EDGE", 4);
            G3 = new anetwork.channel.http.NetworkStatusHelper.NetworkStatus("G3", 5);
            G4 = new anetwork.channel.http.NetworkStatusHelper.NetworkStatus("G4", 6);
            WIFI = new anetwork.channel.http.NetworkStatusHelper.NetworkStatus("WIFI", 7);
            a = new anetwork.channel.http.NetworkStatusHelper.NetworkStatus[]{NONE, NO, GPRS, CDMA, EDGE, G3, G4, WIFI};
        }
    }
}
