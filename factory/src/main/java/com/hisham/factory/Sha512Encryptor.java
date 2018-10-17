package com.hisham.factory;

/**
 * Created by Hisham on 17/Oct/2018 - 20:29
 */
public class Sha512Encryptor extends Encryptor {
    @Override
    EncryptionAlgorithm getEncryptionAlgorithm() {
        return new Sha256Algo();
    }
}
