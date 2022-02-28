package br.com.ladyplant.ui.explore

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import br.com.ladyplant.R
import br.com.ladyplant.ui.result.ResultListFragment

class ExploreFragment : Fragment(), View.OnClickListener {

    companion object {
        fun newInstance() = ExploreFragment()
    }

    private lateinit var viewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflate = inflater.inflate(R.layout.explore_fragment, container, false)
        val cactusBtn = inflate.findViewById<Button>(R.id.cactus_btn)
        cactusBtn.setOnClickListener(this)
        return inflate
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ExploreViewModel::class.java)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.cactus_btn -> parentFragmentManager.beginTransaction()
                .add(R.id.container, ResultListFragment())
                .addToBackStack(null)
                .commit()
        }
    }

}