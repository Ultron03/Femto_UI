package com.example.femto_ui.authentication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AuthViewModelFactory(private val repo: Repo,
    private val repo1: Repo1
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AuthViewModel(repo,repo1) as T
    }
}