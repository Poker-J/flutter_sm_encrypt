package com.wit.encrypt.sm_encrypt.model;

public class Sm2KeyPairModel {
    
    String privateKeys;
    String publicKeys;

    public Sm2KeyPairModel(String privateKeys, String publicKeys) {
        this.privateKeys = privateKeys;
        this.publicKeys = publicKeys;
    }

    public String getPrivateKeys() {
        return privateKeys;
    }

    public void setPrivateKeys(String privateKeys) {
        this.privateKeys = privateKeys;
    }

    public String getPublicKeys() {
        return publicKeys;
    }

    public void setPublicKeys(String publicKeys) {
        this.publicKeys = publicKeys;
    }
}
