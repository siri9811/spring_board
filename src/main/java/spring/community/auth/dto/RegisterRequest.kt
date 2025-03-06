package spring.community.auth.dto

import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "로그인 객체")
data class RegisterRequest(
    @field:Schema(name = "email", example = "email@gmail.com")
    var email: String,
    @field:Schema(name = "username", example = "username")
    var username: String,
    @field:Schema(name = "password", example = "password")
    var password: String,
)
