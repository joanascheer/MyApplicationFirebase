package br.com.zup.myapplicationfirebase.ui.main.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import br.com.zup.myapplicationfirebase.R
import br.com.zup.myapplicationfirebase.databinding.ActivityLoginBinding
import br.com.zup.myapplicationfirebase.databinding.ActivityMainBinding
import br.com.zup.myapplicationfirebase.ui.login.view.LoginActivity
import br.com.zup.myapplicationfirebase.ui.main.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initObserver()
        getData()

        binding.tvLogout.setOnClickListener {
            logOut()
        }
    }

    private fun getData() {
        val name = viewModel.getName()
        binding.tvName.text = "$name"
    }

    private fun logOut() {
            viewModel.logout()
            this.finish()
            goToLogin()
            true
    }

    private fun initObserver() {

        viewModel.errorMessage.observe(this) {
            loadErrorMessage(it)
        }
    }

    private fun loadErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }

}