package com.zj.resourceprojects.util;

import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * 加密工具类
 */
public class CryptographyUtil {
    public final static String SALT="code";

    public static String md5(String str,String salt){
        return new Md5Hash(str,salt).toString();
    }

//    public static void main(String[] args) {
//        String s="12345";
//        System.out.println(CryptographyUtil.md5(s, SALT));
//    }

}
