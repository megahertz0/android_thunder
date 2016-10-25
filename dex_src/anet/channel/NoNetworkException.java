package anet.channel;

// compiled from: Taobao
public class NoNetworkException extends Exception {
    private SessionRequest a;

    public NoNetworkException(SessionRequest sessionRequest) {
        this.a = sessionRequest;
    }

    public String toString() {
        return new StringBuilder("NoNetwork ").append(super.toString()).toString();
    }
}
