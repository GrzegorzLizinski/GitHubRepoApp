package com.example.githubrepoapp.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrepoapp.R
import com.example.githubrepoapp.callbacks.RepoCallback
import com.example.githubrepoapp.databinding.RepoRow
import com.example.githubrepoapp.models.ListGitHubResponseItem
import com.mikhaellopez.rxanimation.RxAnimation
import com.mikhaellopez.rxanimation.press


class ReposAdapter(var arrayList : ArrayList<ListGitHubResponseItem>, private var callback : RepoCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val adapterRow = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.repo_row,
            parent,
            false
        )
        return RepoView(adapterRow as RepoRow, callback)
    }

    class RepoView(private var binding : RepoRow, private var callback : RepoCallback) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListGitHubResponseItem) {
            binding.repoName.text = item.name ?: ""
            binding.repoDescription.text = item.description ?: ""
            binding.language.text = item.language ?: ""

            val watchersCountText = binding.root.context.getString(R.string.watchers_count)
            binding.watchers.text = "$watchersCountText ${item.watchersCount}"

            binding.cardView.setOnClickListener {
                RxAnimation.from(it).press(duration = 300).subscribe {
                    callback.onClickRepo(item)
                }
            }
        }
    }

    override fun onBindViewHolder(viewHolder : RecyclerView.ViewHolder, position: Int) {
        val holder = viewHolder as RepoView
        val result = arrayList[position]
        holder.bind(result)
    }

    override fun getItemCount(): Int {
        return if (arrayList.isNullOrEmpty()) 0 else arrayList.size
    }
}