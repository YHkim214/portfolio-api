/*
 * *
 *  *packageName    : ${PACKAGE_NAME}
 *  * fileName       : ${NAME}
 *  * author         : ${USER}
 *  * date           : ${DATE}
 *  * description    :
 *  * ===========================================================
 *  * DATE              AUTHOR             NOTE
 *  * -----------------------------------------------------------
 *  * ${DATE}        ${USER}       최초 생성
 *
 */

package com.yoonho.holostats.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.common
 * fileName       : PageInfo
 * author         : kim-yoonho
 * date           : 2/1/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/1/24        kim-yoonho       최초 생성
 */
@Data
@AllArgsConstructor
public class PageInfo {
    private Integer start;
    private Integer size;

    public static PageInfo build(Integer page, Integer size) {
        Integer start = (page - 1) * size;

        return new PageInfo(start, size);
    }

}
