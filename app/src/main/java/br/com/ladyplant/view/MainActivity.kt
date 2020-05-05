package br.com.ladyplant.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import br.com.ladyplant.R
import br.com.ladyplant.domain.base.BaseActivity
import br.com.ladyplant.view.explore.ExploreActivity
import br.com.ladyplant.view.quiz.QuizActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        explore_btn.setOnClickListener(this)
        quizz_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v) {
            explore_btn -> startActivity(Intent(this, ExploreActivity::class.java))
            quizz_btn -> startActivity(Intent(this, QuizActivity::class.java))
        }
    }
}
