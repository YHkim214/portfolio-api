package com.yoonho.holostats.common;

/**
 * packageName    : com.yoonho.holostats.common
 * fileName       : CommonCodes
 * author         : kim-yoonho
 * date           : 12/27/23
 * description    : 공통코드 클래스
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 12/27/23        kim-yoonho       최초 생성
 */
public class CommonCodes {
    public static final class CHANNEL_STATUS {
        public static final class CHANNEL_STATUS_ACTIVE {
            public static final String CODE = "CHANNEL_STATUS_ACTIVE";
            public static final String DESC = "활성화된 채널";
            public static final String VAL = "null";
        }
    }
    public static final class ERROR_CODE {
        public static final class ERROR_CODE_API {
            public static final String CODE = "ERROR_CODE_API";
            public static final String DESC = "api 오류가 발생 했습니다.";
            public static final String VAL = "999";
        }
        public static final class ERROR_CODE_NONE {
            public static final String CODE = "ERROR_CODE_NONE";
            public static final String DESC = "api 통신 성공.";
            public static final String VAL = "200";
        }
        public static final class ERROR_CODE_DUP_MEMBER {
            public static final String CODE = "ERROR_CODE_DUP_MEMBER";
            public static final String DESC = "중복된 아이디 입니다";
            public static final String VAL = "901";
        }
    }
    public static final class FILE_TYPE {
        public static final class FILE_TYPE_THUMBNAIL {
            public static final String CODE = "FILE_TYPE_THUMBNAIL";
            public static final String DESC = "섬네일";
            public static final String VAL = "null";
        }
    }
    public static final class MEMBER_ROLE {
        public static final class ROLE_USER {
            public static final String CODE = "ROLE_USER";
            public static final String DESC = "유저";
            public static final String VAL = "null";
        }
        public static final class ROLE_ADMIN {
            public static final String CODE = "ROLE_ADMIN";
            public static final String DESC = "관리자";
            public static final String VAL = "null";
        }
    }
    public static final class MEMBER_STATUS {
        public static final class MEMBER_STATUS_REGISTERED {
            public static final String CODE = "MEMBER_STATUS_REGISTERED";
            public static final String DESC = "회원가입 완료";
            public static final String VAL = "null";
        }
        public static final class MEMBER_STATUS_SUSPENDED {
            public static final String CODE = "MEMBER_STATUS_SUSPENDED";
            public static final String DESC = "회원 사용 중지";
            public static final String VAL = "null";
        }
        public static final class MEMBER_STATUS_DELETED {
            public static final String CODE = "MEMBER_STATUS_DELETED";
            public static final String DESC = "회원 탈퇴";
            public static final String VAL = "null";
        }
    }
}
