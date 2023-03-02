package dev.passerby.effectivetestproject.presentation.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.passerby.effectivetestproject.R
import dev.passerby.effectivetestproject.presentation.fragments.LoginFragment
import dev.passerby.effectivetestproject.presentation.fragments.SignInFragment

class StartActivity : AppCompatActivity(),
    SignInFragment.OnEditingFinishedListenerSignIn,
    LoginFragment.OnEditingFinishedListenerLogin {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        supportActionBar?.hide()
        supportFragmentManager.beginTransaction().replace(R.id.startContainer, SignInFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onEditingFinishedSignIn() {
        Toast.makeText(
            this@StartActivity,
            "Account created successfully!",
            Toast.LENGTH_SHORT
        )
            .show()
        supportFragmentManager.beginTransaction().replace(R.id.startContainer, LoginFragment())
            .addToBackStack(null)
            .commit()
    }

    override fun onEditingFinishedLogin() {
        super.onEditingFinishedLogin()
        Toast.makeText(
            this@StartActivity,
            "Login successful!",
            Toast.LENGTH_SHORT
        )
            .show()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}