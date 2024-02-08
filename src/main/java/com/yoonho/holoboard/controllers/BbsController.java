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

package com.yoonho.holoboard.controllers;

import com.yoonho.holoboard.common.ResponseEntityWrapper;
import com.yoonho.holoboard.configs.security.JwtGenerator;
import com.yoonho.holoboard.dtos.request.GetBbsListRequestDto;
import com.yoonho.holoboard.dtos.request.InsertBbsRequestDto;
import com.yoonho.holoboard.dtos.response.GetBbsListResponseDto;
import com.yoonho.holoboard.services.bbs.BbsService;
import com.yoonho.holoboard.utils.StringUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    private final JwtGenerator jwtGenerator;

    public BbsController(BbsService bbsService, JwtGenerator jwtGenerator) {
        this.bbsService = bbsService;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/insertBbs")
    public ResponseEntity<?> insertBbs(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody InsertBbsRequestDto insertBbsRequestDto) {
        String userName = jwtGenerator.getUserName(StringUtil.processRequestAccessToken(accessToken));

        bbsService.insertBbs(userName, insertBbsRequestDto);

        return ResponseEntityWrapper.success(null);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getBbsList(GetBbsListRequestDto getBbsListRequestDto) {
        GetBbsListResponseDto getBbsListResponseDto = bbsService.getBbsList(getBbsListRequestDto);

        return ResponseEntityWrapper.success(getBbsListResponseDto);
    }

}