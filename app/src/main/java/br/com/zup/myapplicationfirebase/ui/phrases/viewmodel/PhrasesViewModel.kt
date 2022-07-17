package br.com.zup.myapplicationfirebase.ui.phrases.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.myapplicationfirebase.domain.repository.PhrasesRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class PhrasesViewModel : ViewModel() {
    private val phrasesRepository = PhrasesRepository()

    private var _phrasesListState = MutableLiveData<List<kotlin.String>>()
    val phrasesState: LiveData<List<kotlin.String>> = _phrasesListState

    private var _messageState = MutableLiveData<kotlin.String>()
    val messageState: LiveData<kotlin.String> = _messageState

    fun getList() {
        phrasesRepository.getPhrasesList()
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {

                    val phrasesList = mutableListOf<kotlin.String>()

                    for (resultSnapshot in snapshot.children) {
                        val saveResponse = resultSnapshot.getValue(String::class.java)
                        saveResponse?.let { phrasesList.add(it.toString()) }
                    }
                    _phrasesListState.value = phrasesList
                }

                override fun onCancelled(error: DatabaseError) {
                    _messageState.value = error.message
                }
            })
    }



}