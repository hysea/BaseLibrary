package com.foundao.library;

import com.foundao.library.constant.EncryptType;
import com.foundao.library.utils.EncryptUtils;

import org.junit.Test;

/**
 * 加密测试
 * create by hysea on 2019/5/13
 */
public class EncryptTest {

    @Test
    public void enumTest() {
        System.out.println(EncryptType.MD5.getValue());
        System.out.println(EncryptType.SHA256.getValue());
        System.out.println(EncryptType.SHA512.getValue());
    }

    @Test
    public void md5Test() {
        System.out.println(EncryptUtils.md5Encrypt("abcdef"));
    }
}
