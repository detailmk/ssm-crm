package com.shsxt.crm.base.util;

import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Base64加密/解密工具类
 */
public class Base64Util {

    /**
     * 解密
     *
     * @param decoderStr
     * @return
     */
    public static String decoder(String decoderStr) {
        if (StringUtils.isBlank(decoderStr))
            return null;

        try {
            return new String(Base64.decode(decoderStr.getBytes("UTF-8")));
        } catch (Base64DecodingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 加密
     *
     * @param encoderStr
     * @return
     */
    public static String encoder(String encoderStr) {
        try {
            return Base64.encode(encoderStr.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        String result = encoder("10");
        System.out.println(result);
        System.out.println(decoder(result));
    }

}