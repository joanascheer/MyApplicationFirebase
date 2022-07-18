package br.com.zup.myapplicationfirebase.ui.main.viewmodel

import android.content.Intent
import android.net.Uri
import androidx.core.os.bundleOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.myapplicationfirebase.data.datasource.model.Phrase
import br.com.zup.myapplicationfirebase.domain.repository.AuthRepository
import br.com.zup.myapplicationfirebase.domain.repository.PhrasesRepository
import br.com.zup.myapplicationfirebase.ui.phrases.view.PhrasesActivity
import br.com.zup.myapplicationfirebase.utils.PHRASE_SAVED_MSG

class MainViewModel : ViewModel() {
    private val authRepository = AuthRepository()
    private val phrasesRepository = PhrasesRepository()

    private val _phraseResponse = MutableLiveData<Phrase>()
    val phraseResponse: LiveData<Phrase> = _phraseResponse

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getName() = authRepository.getName()

    fun logout() = authRepository.logout()

    fun savePhrase() {
        val phrase = _phraseResponse.value?.phrase.toString()
        val phrasePath = getPhrasePath()
        phrasesRepository.databaseReference().child(phrasePath).setValue(phrase) {
                error, _ ->
            if (error != null) {
                _message.value = error.message
            } else {
                _message.value = PHRASE_SAVED_MSG
            }
        }
    }


    private fun getPhrasePath(): String {
        return _phraseResponse.value?.phrase.toString()
//        val uri: Uri = Uri.parse(phrase)
//        return uri.lastPathSegment?.replace(".jpg", "")
    }
}