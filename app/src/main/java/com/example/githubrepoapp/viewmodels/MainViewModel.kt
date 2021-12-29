package com.example.githubrepoapp.viewmodels

import androidx.lifecycle.ViewModel
import com.example.githubrepoapp.models.ListGitHubResponseItem
import com.example.githubrepoapp.repositories.GetReposRepository
import com.example.githubrepoapp.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    var getReposRepository : GetReposRepository
) : ViewModel(){
    var responseListLiveData = SingleLiveEvent<ArrayList<ListGitHubResponseItem>?>()
    var currentSize = -1
    var page = 1
    var isLoading = false

    fun getRepos(){
        getReposRepository.getRepos(page, {
            currentSize = it?.size?:-1
            if(it != null) page++
            isLoading = false
            val list = responseListLiveData.value?: arrayListOf()
            list.addAll(it?: arrayListOf())
            responseListLiveData.postValue(list)
        },{
            println()
        })
    }

    fun canLoadingNewPage() = currentSize == -1 || currentSize == 30
}