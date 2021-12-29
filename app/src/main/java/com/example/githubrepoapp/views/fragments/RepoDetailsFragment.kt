package com.example.githubrepoapp.views.fragments

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.githubrepoapp.R
import com.example.githubrepoapp.databinding.RepoDetailsBinding
import com.example.githubrepoapp.models.ListGitHubResponseItem
import com.example.githubrepoapp.viewmodels.MainViewModel

import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat

@AndroidEntryPoint
class RepoDetailsFragment : Fragment() {
    private lateinit var listGitHubResponseItem : ListGitHubResponseItem
    private lateinit var binding : RepoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_details,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRepoDetails(listGitHubResponseItem)
    }

    private fun setRepoDetails(item : ListGitHubResponseItem) {
        binding.repoNameDetails.text = item.name ?: ""
        binding.repoFullNameDetails.text = item.fullName ?: ""
        binding.repoDescriptionDetails.text = item.description ?: ""

        binding.repoHtmlDetails.text = item.htmlUrl ?: ""

        binding.languageDetails.text = item.language ?: ""

        val watchersCountText = binding.root.context.getString(R.string.watchers_count)
        binding.watchersDetails.text = "$watchersCountText ${item.watchersCount}"

        var inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        var outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

        val createdAtText = binding.root.context.getString(R.string.created_at)
        val lastDataUpdateDate = inputFormat.parse(item.createdAt)
        binding.createdDate.text = "$createdAtText ${outputFormat.format(lastDataUpdateDate)}"
        val updatedAtText = binding.root.context.getString(R.string.updated_at)
        val updatedAtTextDate = inputFormat.parse(item.updatedAt)
        binding.updatedDate.text = "$updatedAtText ${outputFormat.format(updatedAtTextDate)}"
    }

    companion object {
        @JvmStatic
        fun newInstance(item : ListGitHubResponseItem) =
            RepoDetailsFragment().apply {
                listGitHubResponseItem = item
            }
    }
}