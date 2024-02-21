package com.yoonho.holoboard.utils;

import java.util.Collection;

/**
 * packageName    : com.yoonho.holostats.utils
 * fileName       : CollectionUtil
 * author         : kim-yoonho
 * date           : 1/17/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/17/24        kim-yoonho       최초 생성
 */
public class CollectionUtil {
    public static boolean isNullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
