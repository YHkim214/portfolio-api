package com.yoonho.holoboard.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.common
 * fileName       : PageInfo
 * author         : kim-yoonho
 * date           : 2/1/24
 * description    : 페이징 처리를 위한 객체
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
