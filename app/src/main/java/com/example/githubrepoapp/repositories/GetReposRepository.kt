package com.example.githubrepoapp.repositories

import android.annotation.SuppressLint
import com.example.githubrepoapp.api.RepoGitHubApi
import com.example.githubrepoapp.models.ListGitHubResponseItem
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class GetReposRepository @Inject constructor(
    private var api: RepoGitHubApi
){
    @SuppressLint("CheckResult")
    fun getRepos(
        page : Int,
        onDone: ((result: ArrayList<ListGitHubResponseItem>?) -> Unit),
        onError: ((error: Throwable?) -> Unit),
    ){
        api
            .getRepos(page)
            .subscribeOn(Schedulers.newThread())
            .subscribe(
                {
                    onDone.invoke(it)
                },
                { error ->
                    onDone.invoke(null)
                    onError.invoke(error)
                },
                {
                }
            )
    }
}