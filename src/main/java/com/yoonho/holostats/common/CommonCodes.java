package com.yoonho.holostats.common;

/*공통코드 객체*/
public class CommonCodes {
    public static final class MEMBER_ROLE {
        public static final class ROLE_USER {
            public static final String CODE = "ROLE_USER";
            public static final String DESC = "유저";
        }
        public static final class ROLE_ADMIN {
            public static final String CODE = "ROLE_ADMIN";
            public static final String DESC = "관리자";
        }
    }
    public static final class MEMBER_STATUS {
        public static final class MEMBER_STATUS_REGISTERED {
            public static final String CODE = "MEMBER_STATUS_REGISTERED";
            public static final String DESC = "회원가입 완료";
        }
        public static final class MEMBER_STATUS_SUSPENDED {
            public static final String CODE = "MEMBER_STATUS_SUSPENDED";
            public static final String DESC = "회원 사용 중지";
        }
        public static final class MEMBER_STATUS_DELETED {
            public static final String CODE = "MEMBER_STATUS_DELETED";
            public static final String DESC = "회원 탈퇴";
        }
    }
}


