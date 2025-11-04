package com.example.effectivemobiletest.ui.login

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val loginButtonEnabled: Boolean = false
)
