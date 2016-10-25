package com.ta.utdid2.device;

import com.ta.utdid2.android.utils.AESUtils;
import com.ta.utdid2.android.utils.Base64;
import com.xunlei.tdlive.R;
import com.xunlei.xiazaibao.sdk.XZBDevice;
import java.util.Random;

public class UTUtdidHelper {
    private static Random random;
    private String mAESKey;

    static {
        random = new Random();
    }

    public UTUtdidHelper() {
        this.mAESKey = "XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H";
        this.mAESKey = Base64.encodeToString(this.mAESKey.getBytes(), XZBDevice.DOWNLOAD_LIST_RECYCLE);
    }

    public String pack(byte[] bArr) {
        return AESUtils.encrypt(this.mAESKey, Base64.encodeToString(bArr, XZBDevice.DOWNLOAD_LIST_RECYCLE));
    }

    public String packUtdidStr(String str) {
        return AESUtils.encrypt(this.mAESKey, str);
    }

    public String dePack(String str) {
        return AESUtils.decrypt(this.mAESKey, str);
    }

    public static String generateRandomUTDID() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 24; i++) {
            stringBuffer.append((char) (random.nextInt(R.styleable.AppCompatTheme_actionMenuTextAppearance) + 65));
        }
        return stringBuffer.toString();
    }
}
