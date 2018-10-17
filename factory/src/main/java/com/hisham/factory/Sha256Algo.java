package com.hisham.factory;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by Hisham on 17/Oct/2018 - 20:26
 */
public class Sha256Algo implements EncryptionAlgorithm {
    @Override
    public String decrypt(String plainText) {
        return DigestUtils.sha256Hex(plainText);
    }
}
