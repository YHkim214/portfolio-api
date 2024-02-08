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

package com.yoonho.holoboard.services.file;

import com.yoonho.holoboard.models.DbFile;
import com.yoonho.holoboard.repositories.FileRepository;
import com.yoonho.holoboard.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @Value("${file.upload.base}")
    private String fileBase;

    private final FileRepository fileRepository;

    private final Logger log =  LoggerFactory.getLogger(this.getClass().getSimpleName());

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public DbFile registerFile(MultipartFile multipartFile, Integer memberId, String fileType) throws IOException {

        Path filePath = Paths.get("." + fileBase).toAbsolutePath().normalize();

        String saveDir = filePath + File.separator + memberId;
        String saveUrl = fileBase + File.separator + memberId;

        String fileName = StringUtil.getRandomGeneratedString(8) + "." + StringUtil.getFileExtension(multipartFile.getOriginalFilename());

        File saveDirFile = new File(saveDir);

        if(!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }

        multipartFile.transferTo(new File(saveDir + File.separator + fileName));

        DbFile dbfile = new DbFile();

        dbfile.setFileName(fileName);
        dbfile.setFileType(fileType);
        dbfile.setFilePath(saveDir + File.separator + fileName);
        dbfile.setFileUrl(saveUrl + File.separator + fileName);
        dbfile.setFileExt(StringUtil.getFileExtension(multipartFile.getOriginalFilename()));
        dbfile.setFileSize(multipartFile.getSize());

        fileRepository.insertFile(dbfile);

        return dbfile;
    }
}
