package com.example.githubrepoapp.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubrepoapp.R
import com.example.githubrepoapp.callbacks.RepoCallback
import com.example.githubrepoapp.databinding.RepoListFragmentBinding
import com.example.githubrepoapp.models.ListGitHubResponseItem
import com.example.githubrepoapp.viewmodels.MainViewModel
import com.example.githubrepoapp.views.adapters.ReposAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoListFragment : Fragment(), RepoCallback {

    private var directionDown = false
    private var mScrollY = 0
    private lateinit var adapter : ReposAdapter
    private lateinit var binding : RepoListFragmentBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_repo_list,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val response = viewModel.responseListLiveData.value

        val orgsNameText = binding.root.context.getString(R.string.orgs_name)
        binding.orgsName.text = "$orgsNameText"

        setGlideImage()
        setCountryAdapter(response?: arrayListOf())
        scrollListener()
        observable()
    }

    private fun observable() {
        viewModel.responseListLiveData.observe(viewLifecycleOwner,{
            updateAdapter(it?: arrayListOf())

            binding.progressBarGet.visibility = View.GONE

        })
    }

    private fun setCountryAdapter(result : ArrayList<ListGitHubResponseItem>) {
        adapter = ReposAdapter(result, this)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            RepoListFragment().apply {
            }
    }

    private fun scrollListener(){
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                mScrollY += dy
                directionDown = dy > 0
            }
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                println("directionDown $directionDown")
                if (recyclerView.canScrollVertically(1).not()
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                    && directionDown && !viewModel.isLoading && viewModel.canLoadingNewPage()) {
                    viewModel.isLoading = true
                    binding.progressBarGet.visibility = View.VISIBLE
                    getNewRepos()

                }
            }
        })
    }

    private fun updateAdapter(result: ArrayList<ListGitHubResponseItem>) {
        val positionStart = if(result.size > viewModel.responseListLiveData.value?.lastIndex?:0) viewModel.responseListLiveData.value?.lastIndex else 0.0
        adapter.arrayList = result
        adapter.notifyItemRangeChanged(positionStart?.toInt()?:0, adapter.itemCount)
    }

    private fun getNewRepos() {
        viewModel.getRepos()
    }


    override fun onClickRepo(item : ListGitHubResponseItem) {
        goToRepoDetails(item)
    }

    private fun goToRepoDetails(item : ListGitHubResponseItem) {
        val newFragment: Fragment = RepoDetailsFragment.newInstance(item)
        parentFragmentManager.beginTransaction().replace(R.id.frameLayout, newFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setGlideImage(){

        val descriptionImage = binding.orgsImage

        Glide
            .with(this)
            .load("https://avatars.githubusercontent.com/u/562236?s=200&v=4")
            .fitCenter()
            .into(descriptionImage)
    }
}