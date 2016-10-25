package com.ta.utdid2.device;

import com.ta.utdid2.android.utils.AESUtils;
import com.ta.utdid2.android.utils.Base64;
import com.ta.utdid2.android.utils.StringUtils;

public class UTUtdidHelper2 {
    private String mAESKey;

    public UTUtdidHelper2() {
        this.mAESKey = "XwYp8WL8bm6S4wu6yEYmLGy4RRRdJDIhxCBdk3CiNZTwGoj1bScVZEeVp9vBiiIsgwDtqZHP8QLoFM6o6MRYjW8QqyrZBI654mqoUk5SOLDyzordzOU5QhYguEJh54q3K1KqMEXpdEQJJjs1Urqjm2s4jgPfCZ4hMuIjAMRrEQluA7FeoqWMJOwghcLcPVleQ8PLzAcaKidybmwhvNAxIyKRpbZlcDjNCcUvsJYvyzEA9VUIaHkIAJ62lpA3EE3H";
        this.mAESKey = Base64.encodeToString(this.mAESKey.getBytes(), 0);
    }

    public String dePack(String str) {
        return AESUtils.decrypt(this.mAESKey, str);
    }

    public String dePackWithBase64(String str) {
        String decrypt = AESUtils.decrypt(this.mAESKey, str);
        if (StringUtils.isEmpty(decrypt)) {
            return null;
        }
        try {
            return new String(Base64.decode(decrypt, 0));
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
