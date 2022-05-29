package com.wss.wssadminclient.auth;

import com.wss.wssadminclient.dao.AdminDao;

import java.util.Base64;

public class PassCodeUtil {
    public static final int encodeCycle = 10;

    public static String encodePassword(String password) {
        String encodedPassword = password;
        for (int i = 0; i < encodeCycle; ++i) {
            encodedPassword = Base64.getEncoder().encodeToString(encodedPassword.getBytes());
        }
        return encodedPassword;
    }


    public static String decodePassword(String password) {
        String decodedPassword = password;
        String decodedPass = password;
        for (int i = 0; i < encodeCycle; ++i) {
            byte[] decodedBytes = Base64.getDecoder().decode(decodedPass);
            decodedPassword = new String(decodedBytes);
            decodedPass = decodedPassword;
        }
        return decodedPassword;
    }

}
