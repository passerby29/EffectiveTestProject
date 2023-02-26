package dev.passerby.effectivetestproject.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.passerby.effectivetestproject.R
import dev.passerby.effectivetestproject.presentation.fragments.SignInFragment

class StartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.startContainer, SignInFragment())
            .addToBackStack(null)
            .commit()
    }
}