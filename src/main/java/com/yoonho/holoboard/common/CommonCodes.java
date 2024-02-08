package com.yoonho.holoboard.common;

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
    public static final class BBS_STATUS {
        public static final class PUBLIC {
            public static final String CODE = "PUBLIC";
            public static final String DESC = "공개";
            public static final String VAL = "null";
        }
        public static final class HIDDEN {
            public static final String CODE = "HIDDEN";
            public static final String DESC = "비공개";
            public static final String VAL = "null";
        }
    }
    public static final class BBS_TYPE {
        public static final class NORMAL {
            public static final String CODE = "NORMAL";
            public static final String DESC = "일반";
            public static final String VAL = "null";
        }
        public static final class REPLY {
            public static final String CODE = "REPLY";
            public static final String DESC = "댓글";
            public static final String VAL = "null";
        }
        public static final class NOTICE {
            public static final String CODE = "NOTICE";
            public static final String DESC = "공지사항";
            public static final String VAL = "null";
        }
    }
    public static final class CHANNEL_STATUS {
        public static final class ACTIVE {
            public static final String CODE = "ACTIVE";
            public static final String DESC = "활성화된 채널";
            public static final String VAL = "null";
        }
        public static final class INACTIVE {
            public static final String CODE = "INACTIVE";
            public static final String DESC = "비활성화된 채널";
            public static final String VAL = "null";
        }
    }
    public static final class ERROR_CODE {
        public static final class API {
            public static final String CODE = "API";
            public static final String DESC = "api 오류가 발생 했습니다.";
            public static final String VAL = "999";
        }
        public static final class NONE {
            public static final String CODE = "NONE";
            public static final String DESC = "api 통신 성공.";
            public static final String VAL = "200";
        }
        public static final class DUP_MEMBER {
            public static final String CODE = "DUP_MEMBER";
            public static final String DESC = "중복된 아이디 입니다";
            public static final String VAL = "901";
        }
    }
    public static final class FILE_TYPE {
        public static final class THUMBNAIL {
            public static final String CODE = "THUMBNAIL";
            public static final String DESC = "섬네일";
            public static final String VAL = "null";
        }
    }
    public static final class LIVE_STREAM_STATUS {
        public static final class UPCOMING {
            public static final String CODE = "UPCOMING";
            public static final String DESC = "예정";
            public static final String VAL = "null";
        }
        public static final class LIVE {
            public static final String CODE = "LIVE";
            public static final String DESC = "진행중";
            public static final String VAL = "null";
        }
        public static final class END {
            public static final String CODE = "END";
            public static final String DESC = "종료";
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
        public static final class REGISTERED {
            public static final String CODE = "REGISTERED";
            public static final String DESC = "회원가입 완료";
            public static final String VAL = "null";
        }
        public static final class SUSPENDED {
            public static final String CODE = "SUSPENDED";
            public static final String DESC = "회원 사용 중지";
            public static final String VAL = "null";
        }
        public static final class DELETED {
            public static final String CODE = "DELETED";
            public static final String DESC = "회원 탈퇴";
            public static final String VAL = "null";
        }
    }
}