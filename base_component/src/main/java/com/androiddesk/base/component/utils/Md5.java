package com.androiddesk.base.component.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

    private Md5() {
    }

    private static synchronized MessageDigest checkAlgorithm() {
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new NullPointerException("No md5 algorithm found");
        }
        return messageDigest;
    }

    public final static String digest32(String src) {
        if (src == null) {
            return null;
        }
        MessageDigest messageDigest = checkAlgorithm();
        byte[] ret = null;
        try {
            ret = messageDigest.digest(src.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return ret == null ? null : bytesToHex(ret);
    }

    public final static String digest32(String src, String charset) {
        if (src == null) {
            return null;
        }
        MessageDigest messageDigest = checkAlgorithm();
        byte[] ret = null;
        try {
            ret = messageDigest.digest(src.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return ret == null ? null : bytesToHex(ret);
    }

    public final static String digest32(File src) {
        if (!src.exists() || !src.isFile()) {
            return null;
        }
        MessageDigest digest = checkAlgorithm();
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            in = new FileInputStream(src);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    public final static String digest32(byte[] src) {
        if (src == null) {
            return null;
        }
        MessageDigest messageDigest = checkAlgorithm();
        byte[] ret = messageDigest.digest(src);
        return ret == null ? null : bytesToHex(ret);
    }

    public final static String digest16(String src) {
        String encrypt = digest32(src);
        return encrypt == null ? null : encrypt.substring(8, 24);
    }

    public final static String digest16(String src, String charset) {
        String encrypt = digest32(src, charset);
        return encrypt == null ? null : encrypt.substring(8, 24);
    }

    public final static String digest16(File src) throws IOException {
        String encrypt = digest32(src);
        return encrypt == null ? null : encrypt.substring(8, 24);
    }

    public final static String digest16(byte[] src) {
        String encrypt = digest32(src);
        return encrypt == null ? null : encrypt.substring(8, 24);
    }

    private final static String bytesToHex(byte[] bytes) {
        return StringUtils.bytes2Hex(bytes);
    }
}
