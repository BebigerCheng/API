package com.smt.pc.Interface.utils;

import sun.misc.BASE64Encoder;
import java.security.MessageDigest;

/**
 * The type Md 5 utils.
 *
 * @author cl
 */
public class Md5Utils {

    /**
     * Get md 5 code string.
     *
     * @param str the str
     * @return the string
     */
    public static String getMD5Code(String str){
        String newstr = null;
        try {
            //确定计算方法
            MessageDigest md5=MessageDigest.getInstance("MD5");
            BASE64Encoder base64en = new BASE64Encoder();
            //加密后的字符串
            newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newstr;
    }

    /*public static void main(String[] args) {
        String md5Code = MD5Utils.getMD5Code("123457");
        System.out.println(md5Code);
    }*/
}
