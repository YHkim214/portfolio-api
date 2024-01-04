package com.yoonho.holostats.services;

import com.yoonho.holostats.models.DbFile;
import com.yoonho.holostats.repositories.FileRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * packageName    : com.yoonho.holostats.services
 * fileName       : FileServiceImpl
 * author         : kim-yoonho
 * date           : 12/21/23
 * description    : 파일 처리관련 서비스 구현체
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/21/23        kim-yoonho       최초 생성
 */

@Service
public class FileServiceImpl implements FileService{

    @Value("${file.path}")
    private String filePath;

    private final FileRepository fileRepository;

    private final Logger log =  LoggerFactory.getLogger(this.getClass().getSimpleName());

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void registerThumbnailFile(MultipartFile multipartFile, Integer memberId) throws IOException {
        String relSaveDir = filePath + File.separator + memberId;
        File relFile = new File(relSaveDir);

        if(!relFile.exists()) {
            relFile.mkdirs();
        }

        DbFile dbfile = new DbFile();

        multipartFile.transferTo(new File(relSaveDir + File.separator + "11.jpeg"));

    }
}
