package com.example.githubrepoapp.views.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.example.githubrepoapp.R
import com.example.githubrepoapp.databinding.SplashFragmentBinding
import com.example.githubrepoapp.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class SplashFragment : Fragment() {
    private lateinit var binding : SplashFragmentBinding
    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRepos()
        observable()

    }

    private fun observable() {
        viewModel.responseListLiveData.observe(viewLifecycleOwner,{
            goToRepoListFragment()
        })
    }

    private fun goToRepoListFragment() {
        val newFragment: Fragment = RepoListFragment.newInstance()
        parentFragmentManager.beginTransaction().replace(R.id.frameLayout, newFragment).commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SplashFragment().apply {
            }
    }
}