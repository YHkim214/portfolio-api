package com.yoonho.holostats.common;

/*공통코드 객체*/
public class CommonCodes {
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
