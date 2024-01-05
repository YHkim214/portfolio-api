package com.yoonho.holostats.services;

import com.yoonho.holostats.models.DbFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * packageName    : com.yoonho.holostats.services
 * fileName       : FileServiceImpl
 * author         : kim-yoonho
 * date           : 12/21/23
 * description    : 파일 처리관련 서비스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/21/23        kim-yoonho       최초 생성
 */

public interface FileService {
    DbFile registerFile(MultipartFile multipartFile, Integer memberId, String FileType) throws IOException;
}
