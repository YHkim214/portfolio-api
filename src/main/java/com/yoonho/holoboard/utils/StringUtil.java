package com.yoonho.holoboard.utils;

import java.util.Random;

/**
 * packageName    : com.yoonho.holostats.utils
 * fileName       : StringUtil
 * author         : kim-yoonho
 * date           : 1/5/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/5/24        kim-yoonho       최초 생성
 */
public class StringUtil {

    public static String getFileExtension(String fileName) {
        String extension = "";

        if(fileName == null) {
            return extension;
        }

        int i = fileName.indexOf(".");
        if(i > 0) {
            extension = fileName.substring(i + 1);
        }
        return extension;
    }

    public static String getRandomGeneratedString(int length) {
        int leftLimit = 97;
        int rightLimit = 122;

        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return generatedString;
    }

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty() || string.isBlank();
    }

    public static String processRequestAccessToken(String requestAccessToken) {
        if(isNullOrEmpty(requestAccessToken)) return "";
        return requestAccessToken.substring(7, requestAccessToken.length());
    }

}
