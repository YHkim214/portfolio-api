package com.yoonho.holostats.utils;

import com.yoonho.holostats.models.CommonCode;
import com.yoonho.holostats.repositories.CommonCodeRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * packageName    : com.yoonho.holostats.utils
 * fileName       : CommonCodeGenerator
 * author         : kim-yoonho
 * date           : 12/21/23
 * description    : 공통코드 클래스 파일 생성을 위한 유틸클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/21/23        kim-yoonho       최초 생성
 */
@Component
public class CommonCodeGenerator {

    private final CommonCodeRepository commonCodeRepository;

    public CommonCodeGenerator(CommonCodeRepository commonCodeRepository) {
        this.commonCodeRepository = commonCodeRepository;
    }

    public String getCommonCodeClassString() {
        List<CommonCode> commonCodeList = commonCodeRepository.getAllCommonCodes();

        String curCommonCodeGroup = "";

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("public class CommonCodes {\n");

        for(CommonCode commonCode: commonCodeList) {
            if(!curCommonCodeGroup.equals(commonCode.getCommonCodeGroup())) {
                if(!curCommonCodeGroup.equals("")) {
                    stringBuilder.append("\t}\n");
                }
                stringBuilder.append("\tpublic static final class "+ commonCode.getCommonCodeGroup() + " {\n");
                curCommonCodeGroup = commonCode.getCommonCodeGroup();
            }
            stringBuilder.append("\t\tpublic static final class " +commonCode.getCommonCode()+ " {\n");

            stringBuilder.append("\t\t\tpublic static final String CODE = \"" + commonCode.getCommonCode() + "\";\n");
            stringBuilder.append("\t\t\tpublic static final String DESC = \"" + commonCode.getCommonCodeDesc() + "\";\n");

            stringBuilder.append("\t\t}\n");
        }

        stringBuilder.append("\t}\n");
        stringBuilder.append("}");

        return stringBuilder.toString();
    }
}
