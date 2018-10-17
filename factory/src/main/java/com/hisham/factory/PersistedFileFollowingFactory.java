package com.hisham.factory;

public class PersistedFileFollowingFactory {

    private final String path;
    private final String contents;
    private final Encryptor encryptor;
    public PersistedFileFollowingFactory(String path, String contents, Encryptor encryptor) {
        this.path = path;
        this.contents = contents;
        this.encryptor = encryptor;
    }
    public void persist() {
        encryptor.writeToDisk(contents, path);
    }

    public static void main(String args[]){
        PersistedFileFollowingFactory file = new PersistedFileFollowingFactory("/foo/bar/text.txt", "Hello, world!", new Sha256Encryptor());
        file.persist();
    }
}
