package com.wipro.excercise.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wipro.excercise.R
import com.wipro.excercise.di.DaggerMainComponent
import com.wipro.excercise.networking.APIService
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    internal lateinit var apiService: APIService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerMainComponent.factory().create(this).inject(this)
    }

    private val adapter = DataAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        main_srl.isRefreshing = true
        viewModel.refresh(apiService) { onError() }
        main_srl.setOnRefreshListener { viewModel.refresh(apiService) { onError() } }
        main_list.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        main_list.adapter = adapter
        viewModel.liveList.observe(viewLifecycleOwner,
            Observer { list ->
                main_srl.isRefreshing = false
                list?.let {
                    adapter.updateList(it.rows)
                    (requireActivity() as AppCompatActivity).supportActionBar?.title = it.title
                } ?: onError()
            })
    }

    private fun onError() {
        main_srl.isRefreshing = false
        Snackbar.make(main_srl, R.string.error_message, Snackbar.LENGTH_SHORT).show()
    }
}
