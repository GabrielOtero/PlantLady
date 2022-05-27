package br.com.ladyplant.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.ladyplant.R
import br.com.ladyplant.ui.explore.ExploreFragment

class MainFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflate = inflater.inflate(R.layout.main_fragment, container, false)
        val exploreBtn = inflate.findViewById<Button>(R.id.explore_btn)
        exploreBtn.setOnClickListener(this)
        viewModel.method()
        return inflate
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.explore_btn -> parentFragmentManager.beginTransaction()
                .add(R.id.container, ExploreFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}
