package com.hisham.factory;

import java.io.FileOutputStream;
import java.io.IOException;

public class PersistedFileFollowingStrategy {

    private final String path;
    private final String contents;
    private final EncryptionAlgorithm algo;
    public PersistedFileFollowingStrategy(String path, String contents, EncryptionAlgorithm algo) {
        this.path = path;
        this.contents = contents;
        this.algo = algo;
    }
    public void persist() {
        String cipher = algo.decrypt(contents);
        try (FileOutputStream outputStream = new FileOutputStream(path)) {
            outputStream.write(cipher.getBytes());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        PersistedFileFollowingStrategy file = new PersistedFileFollowingStrategy("/foo/bar/text.txt",
                "Hello, world!", new Sha256Algo());
        file.persist();
    }
}
