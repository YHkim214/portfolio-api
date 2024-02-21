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
import com.yoonho.holoboard.dtos.request.*;
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

    /*게시글 등록*/
    @PostMapping("/insert")
    public ResponseEntity<?> insertBbs(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody InsertBbsRequestDto insertBbsRequestDto) {
        String userName = jwtGenerator.getUserName(StringUtil.processRequestAccessToken(accessToken));

        bbsService.insertBbs(userName, insertBbsRequestDto);

        return ResponseEntityWrapper.success(null);
    }

    /*게시글 목록 반환*/
    @GetMapping("/list/{lsId}")
    public ResponseEntity<?> getBbsList(
            @RequestHeader(name = "Authorization", defaultValue = "") String accessToken,
            GetBbsListRequestDto getBbsListRequestDto,
            @PathVariable("lsId") Integer lsId) {
        String userName = "";

        if(!StringUtil.isNullOrEmpty(accessToken)) {
            userName = jwtGenerator.getUserName(StringUtil.processRequestAccessToken(accessToken));
        }

        getBbsListRequestDto.setLsId(lsId);

        GetBbsListResponseDto getBbsListResponseDto = bbsService.getBbsList(userName, getBbsListRequestDto);

        return ResponseEntityWrapper.success(getBbsListResponseDto);
    }

    /*게시글 추천*/
    @GetMapping("/recommend/{bbsId}")
    public ResponseEntity<?> recommend(
            @RequestHeader("Authorization") String accessToken,
            RecommendRequestDto recommendRequestDto,
            @PathVariable("bbsId") Integer bbsId) {
        String userName = "";

        if(!StringUtil.isNullOrEmpty(accessToken)) {
            userName = jwtGenerator.getUserName(StringUtil.processRequestAccessToken(accessToken));
        }

        recommendRequestDto.setBbsId(bbsId);

        bbsService.recommend(userName, recommendRequestDto);

        return ResponseEntityWrapper.success(null);
    }


    @PostMapping("/update")
    public ResponseEntity<?> update(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody UpdateBbsRequestDto updateBbsRequestDto) {
        String userName = "";

        if(!StringUtil.isNullOrEmpty(accessToken)) {
            userName = jwtGenerator.getUserName(StringUtil.processRequestAccessToken(accessToken));
        }

        bbsService.updateBbs(userName, updateBbsRequestDto);

        return  ResponseEntityWrapper.success(null);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delete(
            @RequestHeader("Authorization") String accessToken,
            @RequestBody DeleteBbsRequestDto deleteBbsRequestDto
            ) {
        String userName = "";

        if(!StringUtil.isNullOrEmpty(accessToken)) {
            userName = jwtGenerator.getUserName(StringUtil.processRequestAccessToken(accessToken));
        }

        bbsService.deleteBbs(userName, deleteBbsRequestDto);

        return  ResponseEntityWrapper.success(null);
    }

}
