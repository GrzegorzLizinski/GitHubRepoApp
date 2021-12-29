package com.example.githubrepoapp.api

import com.example.githubrepoapp.models.ListGitHubResponseItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoGitHubApi {

    @GET("/orgs/allegro/repos")
    fun getRepos(
        @Query("page") page: Int?): Observable<ArrayList<ListGitHubResponseItem>?>
}