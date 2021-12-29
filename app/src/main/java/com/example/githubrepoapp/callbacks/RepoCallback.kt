package com.example.githubrepoapp.callbacks

import com.example.githubrepoapp.models.ListGitHubResponseItem

interface RepoCallback {
    fun onClickRepo(item : ListGitHubResponseItem)
}