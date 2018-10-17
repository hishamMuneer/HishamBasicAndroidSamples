package com.hisham.factory;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Hisham on 17/Oct/2018 - 20:23
 */
public abstract class Encryptor {
    public void writeToDisk(String plainText, String fileName){
        EncryptionAlgorithm algorithm = getEncryptionAlgorithm();
        String cipher = algorithm.decrypt(plainText);
        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            outputStream.write(cipher.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract EncryptionAlgorithm getEncryptionAlgorithm();
}
