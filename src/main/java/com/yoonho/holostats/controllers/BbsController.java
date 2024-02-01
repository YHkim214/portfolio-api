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

package com.yoonho.holostats.controllers;

import com.yoonho.holostats.common.ResponseEntityWrapper;
import com.yoonho.holostats.dtos.request.GetBbsListRequestDto;
import com.yoonho.holostats.dtos.response.GetBbsListResponseDto;
import com.yoonho.holostats.services.bbs.BbsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.yoonho.holostats.controllers
 * fileName       : BbsController
 * author         : kim-yoonho
 * date           : 1/31/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/31/24        kim-yoonho       최초 생성
 */
@RestController
@RequestMapping("/api/bbs")
public class BbsController {

    private final BbsService bbsService;

    public BbsController(BbsService bbsService) {
        this.bbsService = bbsService;
    }

    @GetMapping("/list")
    public ResponseEntity getBbsList(@RequestBody GetBbsListRequestDto getBbsListRequestDto) {
        GetBbsListResponseDto getBbsListResponseDto = bbsService.getBbsList(getBbsListRequestDto);

        return ResponseEntityWrapper.success(getBbsListResponseDto);
    }

}
