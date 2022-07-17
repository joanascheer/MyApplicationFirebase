package br.com.zup.myapplicationfirebase.ui.phrases.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.zup.myapplicationfirebase.databinding.PhrasesItemBinding

class PhrasesAdapter(
    private var phrasesList: MutableList<String>,
) : RecyclerView.Adapter<PhrasesAdapter.ViewHolder>() {

    class ViewHolder(val binding: PhrasesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun showPhrase(phrase: String) {
            binding.tvFrase.text = phrase
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PhrasesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val phrase = phrasesList[position]
        holder.showPhrase(phrase)
    }

    override fun getItemCount() = phrasesList.size

    fun updatePhraseList(newList: MutableList<String>) {
        phrasesList = newList
        notifyDataSetChanged()
    }
}