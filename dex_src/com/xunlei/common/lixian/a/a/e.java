package com.xunlei.common.lixian.a.a;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import com.xunlei.common.encrypt.CharsetConvert;
import com.xunlei.common.encrypt.HextoChar;
import com.xunlei.common.encrypt.SHA1;
import com.xunlei.common.lixian.a.g;
import com.xunlei.xiazaibao.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.android.spdy.SpdyProtocol;

public final class e {
    private static final int a = 524288;
    private static int b = 20;
    private static String c = "ISO-8859-1";
    private byte[] d;
    private byte[] e;
    private Map f;
    private Map g;
    private final byte[] h;
    private final String i;
    private final List j;
    private final Set k;
    private final Date l;
    private final String m;
    private final String n;
    private final String o;
    private final long p;
    private List q;
    private final boolean r;

    private e(byte[] bArr, boolean z) {
        this.d = bArr;
        this.r = z;
        this.f = a.a(new ByteArrayInputStream(this.d)).f();
        this.g = ((b) this.f.get("info")).f();
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        g.a(this.g, byteArrayOutputStream);
        this.e = byteArrayOutputStream.toByteArray();
        this.h = SHA1.encrypt(this.e);
        this.i = b(this.h);
        this.j = new ArrayList();
        this.k = new HashSet();
        Date date = new Date(100000);
        this.m = BuildConfig.VERSION_NAME;
        this.n = BuildConfig.VERSION_NAME;
        this.o = ((b) this.g.get(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME)).a(CharsetConvert.UTF_8);
        this.q = new LinkedList();
        if (this.g.containsKey("files")) {
            for (b bVar : ((b) this.g.get("files")).e()) {
                Map f = bVar.f();
                StringBuilder stringBuilder = new StringBuilder();
                for (b bVar2 : ((b) f.get("path")).e()) {
                    stringBuilder.append(File.separator).append(bVar2.a(CharsetConvert.UTF_8));
                }
                this.q.add(new g(new File(this.o, stringBuilder.toString()), ((b) f.get("length")).c().longValue()));
            }
        } else {
            this.q.add(new g(new File(this.o), ((b) this.g.get("length")).c().longValue()));
        }
        long j = 0;
        for (g gVar : this.q) {
            j = gVar.b + j;
        }
        this.p = j;
    }

    private static int a(StringBuilder stringBuilder, List list) {
        try {
            int size = list.size();
            for (Future future : list) {
                stringBuilder.append((String) future.get());
            }
            list.clear();
            return size;
        } catch (Throwable e) {
            throw new IOException("Error while hashing the torrent data!", e);
        }
    }

    public static e a(File file) {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr, 0, 1024);
            if (read > 0 && read <= 1024) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else if (read <= 0) {
                byteArrayOutputStream.flush();
                fileInputStream.close();
                e eVar = new e(byteArrayOutputStream.toByteArray(), false);
                byteArrayOutputStream.close();
                return eVar;
            }
        }
    }

    private static e a(File file, URI uri, String str) {
        return a(file, null, uri, null, str);
    }

    private static e a(File file, List list, String str) {
        return a(file, null, null, list, str);
    }

    private static e a(File file, List list, URI uri, String str) {
        return a(file, list, uri, null, str);
    }

    private static e a(File file, List list, URI uri, List list2, String str) {
        if (list != null) {
            list.isEmpty();
        }
        Map hashMap = new HashMap();
        if (uri != null) {
            hashMap.put("announce", new b(uri.toString()));
        }
        if (list2 != null) {
            List linkedList = new LinkedList();
            for (List<URI> list3 : list2) {
                List linkedList2 = new LinkedList();
                for (URI uri2 : list3) {
                    linkedList2.add(new b(uri2.toString()));
                }
                linkedList.add(new b(linkedList2));
            }
            hashMap.put("announce-list", new b(linkedList));
        }
        hashMap.put("creation date", new b(new Date().getTime() / 1000));
        hashMap.put("created by", new b(str));
        Map treeMap = new TreeMap();
        treeMap.put(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_NAME, new b(file.getName()));
        treeMap.put("piece length", new b(524288));
        if (list == null || list.isEmpty()) {
            treeMap.put("length", new b(file.length()));
            treeMap.put("pieces", new b(a(Arrays.asList(new File[]{file})), CharsetConvert.ISO_8859_1));
        } else {
            List linkedList3 = new LinkedList();
            for (File parentFile : list) {
                File parentFile2;
                Map hashMap2 = new HashMap();
                hashMap2.put("length", new b(parentFile2.length()));
                List linkedList4 = new LinkedList();
                while (parentFile2 != null && !parentFile2.equals(file)) {
                    linkedList4.addFirst(new b(parentFile2.getName()));
                    parentFile2 = parentFile2.getParentFile();
                }
                hashMap2.put("path", new b(linkedList4));
                linkedList3.add(new b(hashMap2));
            }
            treeMap.put("files", new b(linkedList3));
            treeMap.put("pieces", new b(a(list), CharsetConvert.ISO_8859_1));
        }
        hashMap.put("info", new b(treeMap));
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        g.a(new b(hashMap), byteArrayOutputStream);
        return new e(byteArrayOutputStream.toByteArray(), true);
    }

    private static e a(File file, List list, List list2, String str) {
        return a(file, list, null, list2, str);
    }

    private static e a(File file, boolean z) {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = fileInputStream.read(bArr, 0, 1024);
            if (read > 0 && read <= 1024) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else if (read <= 0) {
                byteArrayOutputStream.flush();
                fileInputStream.close();
                e eVar = new e(byteArrayOutputStream.toByteArray(), false);
                byteArrayOutputStream.close();
                return eVar;
            }
        }
    }

    private static String a(String str) {
        try {
            return b(str.getBytes(CharsetConvert.ISO_8859_1));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    private static String a(List list) {
        int m = m();
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(m);
        ByteBuffer allocate = ByteBuffer.allocate(a);
        List linkedList = new LinkedList();
        StringBuilder stringBuilder = new StringBuilder();
        System.nanoTime();
        for (File file : list) {
            file.length();
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel channel = fileInputStream.getChannel();
            int i = SpdyProtocol.PUBKEY_SEQ_OPEN;
            while (channel.read(allocate) > 0) {
                if (allocate.remaining() == 0) {
                    allocate.clear();
                    linkedList.add(newFixedThreadPool.submit(new f(allocate)));
                }
                if (linkedList.size() >= m) {
                    a(stringBuilder, linkedList);
                }
                if ((((double) channel.position()) / ((double) channel.size())) * 100.0d > ((double) i)) {
                    i += 10;
                }
            }
            channel.close();
            fileInputStream.close();
        }
        if (allocate.position() > 0) {
            allocate.limit(allocate.position());
            allocate.position(0);
            linkedList.add(newFixedThreadPool.submit(new f(allocate)));
        }
        a(stringBuilder, linkedList);
        newFixedThreadPool.shutdown();
        while (!newFixedThreadPool.isTerminated()) {
            Thread.sleep(10);
        }
        System.nanoTime();
        return stringBuilder.toString();
    }

    private void a(OutputStream outputStream) {
        outputStream.write(this.d);
    }

    private static byte[] a(byte[] bArr) {
        return SHA1.encrypt(bArr);
    }

    private static String b(File file) {
        return a(Arrays.asList(new File[]{file}));
    }

    private static String b(byte[] bArr) {
        return new String(HextoChar.bytes_to_hex(bArr, bArr.length));
    }

    private String d() {
        return this.m;
    }

    private String e() {
        return this.n;
    }

    private long f() {
        return this.p;
    }

    private boolean g() {
        return this.q.size() > 1;
    }

    private byte[] h() {
        return this.h;
    }

    private byte[] i() {
        return this.d;
    }

    private List j() {
        return this.j;
    }

    private int k() {
        return this.k.size();
    }

    private boolean l() {
        return this.r;
    }

    private static int m() {
        String str = System.getenv("TTORRENT_HASHING_THREADS");
        if (str != null) {
            try {
                int parseInt = Integer.parseInt(str);
                if (parseInt > 0) {
                    return parseInt;
                }
            } catch (NumberFormatException e) {
            }
        }
        return Runtime.getRuntime().availableProcessors();
    }

    public final String a() {
        return this.o;
    }

    public final List b() {
        List linkedList = new LinkedList();
        for (g gVar : this.q) {
            linkedList.add(gVar.a.getPath());
        }
        return linkedList;
    }

    public final String c() {
        return this.i;
    }

    public final String toString() {
        return this.o;
    }
}
