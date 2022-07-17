package br.com.zup.myapplicationfirebase.ui.phrases.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.myapplicationfirebase.databinding.ActivityPhrasesBinding
import br.com.zup.myapplicationfirebase.ui.phrases.view.adapter.PhrasesAdapter
import br.com.zup.myapplicationfirebase.ui.phrases.viewmodel.PhrasesViewModel

class PhrasesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhrasesBinding

    private val viewModel: PhrasesViewModel by lazy {
        ViewModelProvider(this)[PhrasesViewModel::class.java]
    }

    private val adapter: PhrasesAdapter by lazy {
        PhrasesAdapter(arrayListOf())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhrasesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.getList()
        showRecyclerView()
        initObservers()

    }

    private fun showRecyclerView() {
        binding.rvFrases.layoutManager = LinearLayoutManager(this)
        binding.rvFrases.adapter = adapter
    }

    private fun initObservers() {
        viewModel.phrasesState.observe(this) {
            adapter.updatePhraseList(it.toMutableList())
        }

        viewModel.messageState.observe(this) {
            loadMessage(it)
        }

        viewModel
    }

    private fun loadMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            this.finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}