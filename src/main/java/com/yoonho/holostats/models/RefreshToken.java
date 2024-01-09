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

package com.yoonho.holostats.models;

import lombok.Data;

/**
 * packageName    : com.yoonho.holostats.models
 * fileName       : RefreshToken
 * author         : kim-yoonho
 * date           : 1/9/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/9/24        kim-yoonho       최초 생성
 */

@Data
public class RefreshToken {
    private Integer refreshTokenId;
    private String refreshToken;
    private Integer memberId;

    public RefreshToken(String refreshToken, Integer memberId) {
        this.refreshToken = refreshToken;
        this.memberId = memberId;
    }
}
