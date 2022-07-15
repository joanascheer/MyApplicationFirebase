package br.com.zup.myapplicationfirebase.ui.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.zup.myapplicationfirebase.databinding.ActivityLoginBinding
import br.com.zup.myapplicationfirebase.domain.model.User
import br.com.zup.myapplicationfirebase.ui.login.viewmodel.LoginViewModel
import br.com.zup.myapplicationfirebase.ui.main.view.MainActivity
import br.com.zup.myapplicationfirebase.ui.register.view.RegisterActivity
import br.com.zup.myapplicationfirebase.utils.USER_KEY
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }

    override fun onStart() {
        super.onStart()
        val actualuser = viewModel.getCurrentUser()
        actualuser?.reload()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initObservers()

        binding.tvRegister.setOnClickListener {
            goRegister()
        }

        binding.btnLogin.setOnClickListener {
            val user = getData()
            viewModel.validateData(user)
        }
    }

    private fun getData(): User {
        return User(
            email = binding.etLoginEmail.text.toString(),
            password = binding.etLoginPassword.text.toString()
        )
    }

    private fun initObservers() {
        viewModel.loginState.observe(this) {
            goHome(it)
        }

        viewModel.errorState.observe(this) {
            Snackbar.make(
                binding.root,
                it,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun goRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun goHome(user: User) {
        val intent = Intent(this, MainActivity::class.java).apply {
            putExtra(USER_KEY, user)
        }

        startActivity(intent)
    }
}