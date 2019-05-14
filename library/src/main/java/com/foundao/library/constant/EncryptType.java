package com.foundao.library.constant;

/**
 * 加密类型
 */
public enum EncryptType {

    MD5("MD5"), SHA256("SHA-256"), SHA512("SHA-512");

    private String value;

    EncryptType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }}