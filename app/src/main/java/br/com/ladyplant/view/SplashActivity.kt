package br.com.ladyplant.view

import android.content.Intent
import android.os.Bundle
import br.com.ladyplant.domain.base.BaseActivity


class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
