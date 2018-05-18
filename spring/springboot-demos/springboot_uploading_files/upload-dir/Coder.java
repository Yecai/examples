package com.bdplatform.core.security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
/**
 *@author: ehan
 *@company: 北京鼎力创世科技有限公司
 **/
public class Coder {
    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }
    

    /**
     * BASE64加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

}
