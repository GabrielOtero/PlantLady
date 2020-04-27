package br.com.ladyplant

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import br.com.ladyplant.quiz.QuizActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
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
