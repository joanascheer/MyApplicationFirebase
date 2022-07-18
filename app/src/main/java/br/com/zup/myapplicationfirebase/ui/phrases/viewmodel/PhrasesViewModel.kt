package br.com.zup.myapplicationfirebase.ui.phrases.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.myapplicationfirebase.data.datasource.model.Phrase
import br.com.zup.myapplicationfirebase.domain.model.User
import br.com.zup.myapplicationfirebase.domain.repository.AuthRepository
import br.com.zup.myapplicationfirebase.domain.repository.PhrasesRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class PhrasesViewModel : ViewModel() {
    private val phrasesRepository = PhrasesRepository()
    private val authRepository = AuthRepository()

    private var _phrasesListState = MutableLiveData<List<Phrase>>()
    val phrasesState: LiveData<List<Phrase>> = _phrasesListState

    private var _messageState = MutableLiveData<String>()
    val messageState: LiveData<String> = _messageState

    fun getList() {
        phrasesRepository.getPhrasesList()
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val phrasesList = mutableListOf<Phrase>()

                    for (resultSnapshot in snapshot.children) {
                        val saveResponse = resultSnapshot.getValue(Phrase::class.java)
                        saveResponse?.let { phrasesList.add(it) }
                    }
                    _phrasesListState.value = phrasesList
                }

                override fun onCancelled(error: DatabaseError) {
                    _messageState.value = error.message
                }
            })
    }

    fun getCurrentUser() = authRepository.getCurrentUser()



}