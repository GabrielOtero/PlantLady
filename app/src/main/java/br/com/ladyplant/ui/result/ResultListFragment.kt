package br.com.ladyplant.ui.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.com.ladyplant.R
import br.com.ladyplant.ui.base.BaseFragment

class ResultListFragment : BaseFragment() {

    companion object {
        fun newInstance() = ResultListFragment()
    }

    private val viewModel: ResultListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_list_fragment, container, false)
    }

    override fun observeViewState() {
        viewModel.viewState.loading.observe(
            viewLifecycleOwner
        ) { loading ->
            if (loading) {
                println("@@@ LOADING...")
            } else {
                println("@@@ NOT LOADING!!")
            }
        }
        viewModel.viewState.action.observe(
            viewLifecycleOwner
        ) { action ->
            when (action) {
                is ResultListViewState.Action.ShowByTypeResult -> println("@@@" + action.list)
                is ResultListViewState.Action.ShowError -> println("@@@" + action.errorMsg)
            }
        }
    }
}
