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
import com.yoonho.holostats.dtos.response.GetLiveStreamResponseDto;
import com.yoonho.holostats.services.liveStream.LiveStreamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /** 라이브 스트리밍 목록 반환 **/
    @GetMapping("/list")
    public ResponseEntity<?> getLiveStreamList(@RequestParam("date") String date) {
        List<GetLiveStreamResponseDto> liveStreamList = liveStreamService.getLiveStreamList(new GetLiveStreamRequestDto(date));

        return ResponseEntityWrapper.success(liveStreamList);
    }

    @GetMapping("/{lsId}")
    public ResponseEntity<?> getLiveStream(@PathVariable("lsId") Integer lsId) {
        GetLiveStreamResponseDto getLiveStreamResponseDto = liveStreamService.getLiveStreamById(lsId);
        return ResponseEntityWrapper.success(getLiveStreamResponseDto);
    }

}
