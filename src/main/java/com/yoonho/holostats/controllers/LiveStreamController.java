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

import com.yoonho.holostats.common.CommonController;
import com.yoonho.holostats.common.ResponseEntityWrapper;
import com.yoonho.holostats.dtos.request.GetLiveStreamRequestDto;
import com.yoonho.holostats.models.liveStream.LiveStream;
import com.yoonho.holostats.services.liveStream.LiveStreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * packageName    : com.yoonho.holostats.controllers
 * fileName       : LiveStreamController
 * author         : kim-yoonho
 * date           : 1/19/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 1/19/24        kim-yoonho       최초 생성
 */

@RestController
@RequestMapping("/api/liveStream")
public class LiveStreamController extends CommonController {

    private final LiveStreamService liveStreamService;

    public LiveStreamController(LiveStreamService liveStreamService) {
        this.liveStreamService = liveStreamService;
    }

    @GetMapping("/list")
    public ResponseEntity getLiveStreamList(@RequestBody GetLiveStreamRequestDto getLiveStreamRequestDto) {
        List<LiveStream> liveStreamList = liveStreamService.getLiveStream(getLiveStreamRequestDto);

        return ResponseEntityWrapper.success(liveStreamList);
    }

}
