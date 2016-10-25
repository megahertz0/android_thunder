package anetwork.channel.entity;

// compiled from: Taobao
public final class a implements anetwork.channel.a {
    private final String a;
    private final String b;

    public a(String str, String str2) {
        if (str == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        this.a = str;
        this.b = str2;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }
}
