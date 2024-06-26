package com.example.kotlin_practice.global.error.exception

enum class ErrorCode(
    val statusCode: Int,
    val message: String
) {

    // jwt
    EXPIRED_TOKEN(401, "만료된 토큰입니다."),
    INVALID_TOKEN(401, "검증되지 않은 토큰입니다."),

    // user
    PASSWORD_MISS_MATCH(401, "비밀번호가 일치하지 않습니다."),

    // post
    POST_NOT_FOUND(404, "게시글을 찾을 수 없습니다."),

    // general
    BAD_REQUEST(400, "프론트 탓..."),
    INTERNAL_SERVER_ERROR(500, "서버 탓...");
}

